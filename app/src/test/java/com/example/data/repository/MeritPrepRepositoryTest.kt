package com.example.data.repository

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.MeritPrepDatabase
import com.example.data.local.OfflinePreferencesRepository
import com.example.data.local.ThemeMode
import com.example.data.model.UserProfile
import com.example.ui.viewmodel.CbtExamStage
import com.example.ui.viewmodel.MeritPrepViewModel
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
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
    val dataStore = PreferenceDataStoreFactory.create(produceFile = { prefsFile })
    prefsRepo = OfflinePreferencesRepository(dataStore)
    repository = MeritPrepRepository(db.meritPrepDao(), prefsRepo)
  }

  @After
  fun tearDown() {
    db.close()
    if (prefsFile.exists()) prefsFile.delete()
    Dispatchers.resetMain()
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
      runCurrent()
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
}
