package com.example.data.repository

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.CurriculumProgression
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.QuestionShuffler
import com.example.data.local.FontSizeScale
import com.example.data.local.MeritPrepDatabase
import com.example.data.local.OfflinePreferencesRepository
import com.example.data.local.ThemeMode
import com.example.data.model.UserProfile
import com.example.ui.viewmodel.CbtExamStage
import com.example.ui.viewmodel.MeritPrepViewModel
import java.io.File
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class MeritPrepRepositoryTest {

  private lateinit var context: Context
  private lateinit var db: MeritPrepDatabase
  private lateinit var prefsFile: File
  private lateinit var dataStoreScope: CoroutineScope
  private lateinit var prefsRepo: OfflinePreferencesRepository
  private lateinit var repository: MeritPrepRepository
  private val testDispatcher = StandardTestDispatcher()

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
    context = ApplicationProvider.getApplicationContext()
    db =
      Room.inMemoryDatabaseBuilder(context, MeritPrepDatabase::class.java)
        .allowMainThreadQueries()
        .setQueryExecutor { it.run() }
        .setTransactionExecutor { it.run() }
        .build()
    prefsFile = File(context.filesDir, "test_prefs_${System.nanoTime()}.preferences_pb")
    dataStoreScope = CoroutineScope(testDispatcher + SupervisorJob())
    val dataStore =
      PreferenceDataStoreFactory.create(
        scope = dataStoreScope,
        produceFile = { prefsFile },
      )
    prefsRepo = OfflinePreferencesRepository(dataStore)
    repository = MeritPrepRepository(db.meritPrepDao(), prefsRepo)
  }

  @After
  fun tearDown() {
    dataStoreScope.cancel()
    db.close()
    if (prefsFile.exists()) prefsFile.delete()
    Dispatchers.resetMain()
  }

  private fun TestScope.awaitStage(viewModel: MeritPrepViewModel, target: CbtExamStage) {
    val deadline = System.currentTimeMillis() + 5_000L
    while (viewModel.cbtState.value.stage != target && System.currentTimeMillis() < deadline) {
      runCurrent()
      if (viewModel.cbtState.value.stage == target) break
      Thread.sleep(10)
    }
  }

  @Test
  fun `ensureProfile and updateGoal persist locally`() = runTest(testDispatcher) {
    val uid = UserProfile.DEFAULT_PROFILE_ID
    val created = repository.ensureProfile(uid, "Chidi").getOrThrow()
    assertEquals("Chidi", created.displayName)

    repository.updateGoal(uid, "Adaeze", "JAMB", 350L).getOrThrow()
    repository.awardXp(uid, 50L, 4L).getOrThrow()

    val loaded = repository.observeProfile(uid).first()!!
    assertEquals("Adaeze", loaded.displayName)
    assertEquals("JAMB", loaded.targetExam)
    assertEquals(350L, loaded.targetScore)
    assertEquals(50L, loaded.totalXp)
    assertEquals(4L, loaded.streakDays)
  }

  @Test
  fun `lesson progress exam attempts and study notes persist locally`() = runTest(testDispatcher) {
    val uid = UserProfile.DEFAULT_PROFILE_ID

    repository.saveLessonProgress(
      userId = uid,
      lessonId = "phy_proj",
      subjectId = "physics",
      moduleId = "mechanics",
      completedSteps = listOf("concept", "lab"),
      exerciseScore = 80L,
      isCompleted = true,
    ).getOrThrow()

    val progress = repository.observeLessonProgress(uid).first()
    assertEquals(1, progress.size)
    assertEquals(listOf("concept", "lab"), progress.first().completedSteps)
    assertTrue(progress.first().isCompleted)

    repository.recordExamAttempt(
      userId = uid,
      examType = "JAMB",
      subjectId = "physics",
      subjectTitle = "Physics",
      correctCount = 9L,
      totalQuestions = 10L,
      durationSeconds = 240L,
      weakTopics = listOf("Optics"),
    ).getOrThrow()

    val attempts = repository.observeExamAttempts(uid).first()
    assertEquals(1, attempts.size)
    assertEquals(90L, attempts.first().percentage)
    assertEquals(listOf("Optics"), attempts.first().weakTopics)

    val noteId =
      repository.createStudyNote(
        userId = uid,
        lessonId = "phy_proj",
        subjectId = "physics",
        title = "Range Formula",
        content = "R = u^2 sin(2theta) / g",
        isBookmarked = true,
      ).getOrThrow()

    val notes = repository.observeStudyNotes(uid).first()
    assertEquals(1, notes.size)
    assertEquals("Range Formula", notes.first().title)

    repository.deleteStudyNote(uid, noteId).getOrThrow()
    assertTrue(repository.observeStudyNotes(uid).first().isEmpty())

    // Verify DataStore offline snapshot recorded progress & exam summary
    val snapshot = repository.offlineSnapshot.first()
    assertTrue("phy_proj" in snapshot.completedLessonIds)
    assertEquals(1, snapshot.totalExamsTaken)
    assertEquals(90, snapshot.recentResults.first().percentage)
  }

  @Test
  fun `datastore theme preferences toggle dark mode and dynamic colors`() = runTest(testDispatcher) {
    val initial = repository.themePreferences.first()
    assertEquals(ThemeMode.SYSTEM, initial.themeMode)
    assertTrue(initial.useDynamicColor)

    repository.toggleDarkTheme(currentlyDark = false)
    assertEquals(ThemeMode.DARK, repository.themePreferences.first().themeMode)

    repository.toggleDarkTheme(currentlyDark = true)
    assertEquals(ThemeMode.LIGHT, repository.themePreferences.first().themeMode)

    repository.setUseDynamicColor(false)
    assertFalse(repository.themePreferences.first().useDynamicColor)

    assertEquals(FontSizeScale.STANDARD, initial.fontSizeScale)
    repository.setFontSizeScale(FontSizeScale.LARGE)
    assertEquals(FontSizeScale.LARGE, repository.themePreferences.first().fontSizeScale)
  }

  @Test
  fun `room seeds structured modules and cbt engine grades post exam summary`() =
    runTest(testDispatcher) {
      repository.seedCurriculumIfNeeded()

      val modules = repository.observeStructuredModules().first()
      assertTrue(modules.isNotEmpty())
      assertTrue(modules.flatMap { it.lessons }.isNotEmpty())

      val questions = repository.fetchPastQuestionsForCbt("physics", "JAMB")
      assertTrue(questions.isNotEmpty())

      val viewModel = MeritPrepViewModel(repository)
      runCurrent()

      viewModel.startMockExam("physics", "JAMB")
      awaitStage(viewModel, CbtExamStage.RUNNING)
      advanceTimeBy(2_000L)
      runCurrent()

      val running = viewModel.cbtState.value
      assertEquals(CbtExamStage.RUNNING, running.stage)
      assertTrue(running.questions.isNotEmpty())
      assertEquals(2, running.elapsedSeconds)

      // Answer Q0 correctly and Q1 (if present) incorrectly
      val q0 = running.questions[0]
      viewModel.selectCbtAnswer(0, q0.correctIndex)
      if (running.questions.size > 1) {
        val q1 = running.questions[1]
        val wrongOption = (q1.correctIndex + 1) % q1.options.size
        viewModel.selectCbtAnswer(1, wrongOption)
      }

      viewModel.submitCbtExam()
      advanceUntilIdle()

      val finished = viewModel.cbtState.value
      assertEquals(CbtExamStage.SUMMARY, finished.stage)
      val summary = finished.resultSummary
      assertNotNull(summary)
      assertEquals(1, summary!!.correctCount)
      assertEquals(running.questions.size, summary.reviewItems.size)
      assertTrue(summary.reviewItems[0].isCorrect)
      if (running.questions.size > 1) {
        assertFalse(summary.reviewItems[1].isCorrect)
        assertFalse(summary.reviewItems[1].isUnanswered)
      }
    }

  @Test
  fun `full seven-subject jamb waec curriculum seeds and resync preserves user progress`() =
    runTest(testDispatcher) {
      repository.seedCurriculumIfNeeded()

      assertEquals(7, CurriculumCatalog.tracks.size)
      assertNotNull(CurriculumCatalog.trackById("economics"))
      assertNotNull(CurriculumCatalog.trackById("government"))

      val modules = repository.observeStructuredModules().first()
      assertEquals(35, modules.size)
      val lessons = modules.flatMap { it.lessons }
      assertEquals(96, lessons.size)
      assertTrue(
        lessons.all {
          it.concepts.size >= 5 &&
            it.vocabulary.isNotEmpty() &&
            it.misconceptions.isNotEmpty() &&
            it.guidedPractice != null &&
            it.masteryProject != null &&
            it.masteryProject!!.verificationTasks.size >= 2 &&
            it.cheatSheet != null &&
            it.exercises.size >= 6
        }
      )
      assertTrue(
        lessons.flatMap { it.workedExamples }.none {
          it.examSource.contains("2024") || it.examSource.contains("2025")
        }
      )

      // Verify every subject has syllabus-aligned CBT banks for JAMB, WAEC, and NECO without fake year citations
      CurriculumCatalog.tracks.forEach { track ->
        val allQuestions = repository.fetchPastQuestionsForCbt(track.id, "ALL")
        val necoQuestions = repository.fetchPastQuestionsForCbt(track.id, "NECO")
        assertTrue("Expected >= 40 questions for ${track.id}", allQuestions.size >= 40)
        assertTrue("Expected NECO questions for ${track.id}", necoQuestions.isNotEmpty())
        assertTrue(
          "Expected honest board style labels for ${track.id}",
          allQuestions.none { it.boardStyleLabel.contains("2024") || it.boardStyleLabel.contains("2025") },
        )
      }

      // Verify English Comprehension & Cloze passages survive Room persistence
      val englishJamb = repository.fetchPastQuestionsForCbt("english", "JAMB")
      val comprehensionQs = englishJamb.filter { !it.passageText.isNullOrBlank() }
      assertTrue("Expected >= 10 English passage questions, got ${comprehensionQs.size}", comprehensionQs.size >= 10)
      assertTrue(comprehensionQs.any { it.id.contains("pq_eng_25") })
      assertTrue(comprehensionQs.any { it.id.contains("pq_eng_30") })

      // Save user lesson progress before simulating an outdated curriculum version
      val firstLesson = CurriculumCatalog.tracks.first().allLessons.first()
      repository.saveLessonProgress(
        userId = UserProfile.DEFAULT_PROFILE_ID,
        lessonId = firstLesson.id,
        subjectId = firstLesson.subjectId,
        moduleId = firstLesson.moduleId,
        completedSteps = LessonPillar.entries.map { it.key },
        exerciseScore = 100L,
        isCompleted = true,
      ).getOrThrow()

      prefsRepo.setCurriculumVersion(1)
      repository.seedCurriculumIfNeeded()

      assertEquals(8, prefsRepo.getCurriculumVersion())
      assertEquals(96, repository.observeStructuredModules().first().flatMap { it.lessons }.size)
      val preserved =
        repository.observeLessonProgress(UserProfile.DEFAULT_PROFILE_ID).first()
          .firstOrNull { it.lessonId == firstLesson.id }
      assertNotNull(preserved)
      assertTrue(preserved!!.isCompleted)
    }

  @Test
  fun `sequential lesson and pillar locking gates next lesson behind 80 percent mastery`() =
    runTest(testDispatcher) {
      val physicsLessons = CurriculumCatalog.trackById("physics")!!.allLessons
      val lesson1 = physicsLessons[0]
      val lesson2 = physicsLessons[1]

      // Initially lesson 1 is unlocked, lesson 2 is locked
      val initial1 = CurriculumProgression.unlockStateForLesson(physicsLessons, lesson1.id, emptyMap())
      val initial2 = CurriculumProgression.unlockStateForLesson(physicsLessons, lesson2.id, emptyMap())
      assertTrue(initial1.isUnlocked)
      assertFalse(initial2.isUnlocked)
      assertEquals(lesson1.title, initial2.prerequisiteLessonTitle)

      // Within lesson 1, only CONCEPT is unlocked initially
      assertTrue(CurriculumProgression.isPillarUnlocked(LessonPillar.CONCEPT, emptySet()))
      assertFalse(CurriculumProgression.isPillarUnlocked(LessonPillar.EXAMPLES, emptySet()))
      assertFalse(CurriculumProgression.isPillarUnlocked(LessonPillar.EXERCISE, emptySet()))

      val viewModel = MeritPrepViewModel(repository)
      advanceUntilIdle()

      // Complete first 5 pillars sequentially via ViewModel
      val nonExercisePillars =
        listOf(
          LessonPillar.CONCEPT,
          LessonPillar.EXAMPLES,
          LessonPillar.SCENARIO,
          LessonPillar.LAB,
          LessonPillar.PROJECT,
        )
      nonExercisePillars.forEach { pillar ->
        val current =
          repository.observeLessonProgress(UserProfile.DEFAULT_PROFILE_ID).first()
            .firstOrNull { it.lessonId == lesson1.id }
        viewModel.markPillarCompleted(lesson1, pillar, current)
        advanceUntilIdle()
      }

      // Attempt Mastery Quiz with 67% (< 80%) -> lesson 2 must remain locked
      var progress1 =
        repository.observeLessonProgress(UserProfile.DEFAULT_PROFILE_ID).first()
          .first { it.lessonId == lesson1.id }
      viewModel.markPillarCompleted(lesson1, LessonPillar.EXERCISE, progress1, exerciseScore = 67L)
      advanceUntilIdle()

      progress1 =
        repository.observeLessonProgress(UserProfile.DEFAULT_PROFILE_ID).first()
          .first { it.lessonId == lesson1.id }
      assertFalse(progress1.isCompleted)
      assertFalse(
        CurriculumProgression.unlockStateForLesson(
          physicsLessons,
          lesson2.id,
          mapOf(lesson1.id to progress1),
        ).isUnlocked
      )

      // Retake Mastery Quiz with 85% (>= 80%) -> lesson 1 mastered & lesson 2 unlocked!
      viewModel.markPillarCompleted(lesson1, LessonPillar.EXERCISE, progress1, exerciseScore = 85L)
      advanceUntilIdle()

      progress1 =
        repository.observeLessonProgress(UserProfile.DEFAULT_PROFILE_ID).first()
          .first { it.lessonId == lesson1.id }
      assertTrue(progress1.isCompleted)
      assertTrue(
        CurriculumProgression.unlockStateForLesson(
          physicsLessons,
          lesson2.id,
          mapOf(lesson1.id to progress1),
        ).isUnlocked
      )
      assertEquals(lesson2.id, CurriculumProgression.nextUnlockedLesson(physicsLessons, mapOf(lesson1.id to progress1))?.id)
    }

  @Test
  fun `cbt exam respects ALL board option and configurable question limit`() =
    runTest(testDispatcher) {
      repository.seedCurriculumIfNeeded()
      val viewModel = MeritPrepViewModel(repository)
      runCurrent()

      viewModel.startMockExam(subjectId = "chemistry", examBoard = "ALL", questionLimit = 10)
      awaitStage(viewModel, CbtExamStage.RUNNING)

      val running = viewModel.cbtState.value
      assertEquals(CbtExamStage.RUNNING, running.stage)
      assertEquals("ALL", running.selectedExamBoard)
      assertEquals(10, running.questions.size)
      viewModel.returnToCbtSetup()
    }

  @Test
  fun `question shuffler and cbt retake serve fresh non-repeating questions and preserve option truth`() =
    runTest(testDispatcher) {
      repository.seedCurriculumIfNeeded()
      val lesson = CurriculumCatalog.lessonById("phys_01_units")!!
      val round1 = QuestionShuffler.prepareFreshQuestionSet(lesson.exercises, count = 3)
      val round2 =
        QuestionShuffler.prepareFreshQuestionSet(
          pool = lesson.exercises,
          count = 3,
          previouslySeenIds = round1.map { it.id }.toSet(),
          lastRoundIds = round1.map { it.id }.toSet(),
        )
      assertEquals(3, round1.size)
      assertEquals(3, round2.size)
      assertTrue((round1.map { it.id }.toSet() intersect round2.map { it.id }.toSet()).isEmpty())

      round1.forEach { shuffled ->
        val orig = lesson.exercises.first { it.id == shuffled.id }
        assertEquals(orig.options[orig.correctIndex], shuffled.options[shuffled.correctIndex])
      }

      val viewModel = MeritPrepViewModel(repository)
      runCurrent()

      viewModel.startMockExam(subjectId = "physics", examBoard = "ALL", questionLimit = 10)
      awaitStage(viewModel, CbtExamStage.RUNNING)
      val attempt1Ids = viewModel.cbtState.value.questions.map { it.id }.toSet()
      viewModel.submitCbtExam()
      awaitStage(viewModel, CbtExamStage.SUMMARY)

      viewModel.retakeLastMock()
      awaitStage(viewModel, CbtExamStage.RUNNING)
      val attempt2Ids = viewModel.cbtState.value.questions.map { it.id }.toSet()
      assertEquals(10, attempt2Ids.size)
      assertTrue((attempt1Ids intersect attempt2Ids).isEmpty())
      viewModel.returnToCbtSetup()
    }

  @Test
  fun `expanded theme and study settings persist across repository updates`() =
    runTest(testDispatcher) {
      repository.setThemePalette(com.example.data.local.ThemePalette.OLED_HIGH_CONTRAST)
      repository.setHighContrastBoost(true)
      repository.setLineSpacingScale(com.example.data.local.LineSpacingScale.RELAXED)
      repository.setBoldEquations(true)
      repository.setCbtSecondsPerQuestion(90)
      repository.setCbtAutoAdvance(true)
      repository.setCbtShowCalculator(false)
      repository.setShuffleOptionsOnRetry(false)
      repository.setMasteryPassPercent(75)
      repository.setDailyXpTarget(150)
      repository.setSpacedReviewBatchSize(15)

      val prefs = repository.themePreferences.first()
      assertEquals(com.example.data.local.ThemePalette.OLED_HIGH_CONTRAST, prefs.themePalette)
      assertTrue(prefs.highContrastBoost)
      assertEquals(com.example.data.local.LineSpacingScale.RELAXED, prefs.lineSpacingScale)
      assertTrue(prefs.boldEquations)
      assertEquals(90, prefs.cbtSecondsPerQuestion)
      assertTrue(prefs.cbtAutoAdvance)
      assertFalse(prefs.cbtShowCalculator)
      assertFalse(prefs.shuffleOptionsOnRetry)
      assertEquals(75, prefs.masteryPassPercent)
      assertEquals(150, prefs.dailyXpTarget)
      assertEquals(15, prefs.spacedReviewBatchSize)
    }

  @Test
  fun `sm2 spaced repetition placement unlock json backup and utme 400 combo work end to end`() =
    runTest(testDispatcher) {
      repository.seedCurriculumIfNeeded()
      val uid = UserProfile.DEFAULT_PROFILE_ID
      repository.ensureProfile(uid, "Tunde").getOrThrow()

      // 1. SM-2 Spaced Repetition seeding & interval progression
      val physicsLesson = CurriculumCatalog.trackById("physics")!!.allLessons.first()
      repository.seedReviewCardsFromLesson(uid, physicsLesson)
      val allCards = repository.observeAllReviewCards(uid).first()
      assertTrue(allCards.isNotEmpty())
      val firstCard = allCards.first()
      val ratedEasy = repository.rateSpacedReviewCard(firstCard.id, quality = 3).getOrThrow()
      assertTrue(ratedEasy.intervalDays >= 3)
      val ratedForgot = repository.rateSpacedReviewCard(firstCard.id, quality = 0).getOrThrow()
      assertEquals(1, ratedForgot.intervalDays)

      // 2. SS3 Placement fast-unlock opens foundational 60% of track
      val physicsLessons = CurriculumCatalog.trackById("physics")!!.allLessons
      val midIndex = (physicsLessons.size * 0.4).toInt().coerceAtLeast(1)
      val midLesson = physicsLessons[midIndex]
      assertFalse(
        CurriculumProgression.unlockStateForLesson(
          orderedTrackLessons = physicsLessons,
          lessonId = midLesson.id,
          progressMap = emptyMap(),
          placementPassedSubjects = emptySet(),
        ).isUnlocked
      )
      assertTrue(
        CurriculumProgression.unlockStateForLesson(
          orderedTrackLessons = physicsLessons,
          lessonId = midLesson.id,
          progressMap = emptyMap(),
          placementPassedSubjects = setOf("physics"),
        ).isUnlocked
      )

      // 3. Portable JSON backup export & import round-trip
      val noteId =
        repository.createStudyNote(
          userId = uid,
          lessonId = physicsLesson.id,
          subjectId = "physics",
          title = "Impulse Formula",
          content = "J = F \\Delta t = m(v - u)",
          isBookmarked = true,
        ).getOrThrow()
      val backupJson = repository.exportBackupJson(uid).getOrThrow()
      repository.deleteStudyNote(uid, noteId).getOrThrow()
      assertTrue(repository.observeStudyNotes(uid).first().isEmpty())

      val restoredCount = repository.importBackupJson(uid, backupJson).getOrThrow()
      assertTrue(restoredCount >= 1)
      assertTrue(repository.observeStudyNotes(uid).first().any { it.title == "Impulse Formula" })

      // 4. 4-Subject UTME Combo (/400) mode
      val viewModel = MeritPrepViewModel(repository)
      runCurrent()
      viewModel.startUtmeComboExam(electives = listOf("mathematics", "physics", "chemistry"), questionsPerSubject = 5)
      awaitStage(viewModel, CbtExamStage.RUNNING)
      val runningCombo = viewModel.cbtState.value
      assertTrue(runningCombo.isUtmeComboMode)
      assertEquals(20, runningCombo.questions.size)
      assertEquals(listOf("english", "mathematics", "physics", "chemistry"), runningCombo.comboSubjectTabs)

      viewModel.submitCbtExam()
      awaitStage(viewModel, CbtExamStage.SUMMARY)
      val comboSummary = viewModel.cbtState.value.resultSummary!!
      assertTrue(comboSummary.isUtmeCombo)
      assertNotNull(comboSummary.utmeAggregateOutOf400)
      assertEquals(4, comboSummary.comboBreakdowns.size)
    }

  @Test
  fun `authentic 180-question full utme combo allocates 60 english plus 40 per elective with 120 minute timer`() =
    runTest(testDispatcher) {
      repository.seedCurriculumIfNeeded()
      val viewModel = MeritPrepViewModel(repository)
      runCurrent()

      viewModel.startUtmeComboExam(
        electives = listOf("mathematics", "physics", "economics"),
        isFullUtme180 = true,
      )
      awaitStage(viewModel, CbtExamStage.RUNNING)

      val running = viewModel.cbtState.value
      assertTrue(running.isUtmeComboMode)
      assertEquals(7200, running.totalAllowedSeconds)
      assertEquals(180, running.questions.size)
      assertEquals(listOf("english", "mathematics", "physics", "economics"), running.comboSubjectTabs)
      assertEquals(60, running.comboQuestionSubjects.count { it == "english" })
      assertEquals(40, running.comboQuestionSubjects.count { it == "mathematics" })
      assertEquals(40, running.comboQuestionSubjects.count { it == "physics" })
      assertEquals(40, running.comboQuestionSubjects.count { it == "economics" })
      viewModel.returnToCbtSetup()
    }
}
