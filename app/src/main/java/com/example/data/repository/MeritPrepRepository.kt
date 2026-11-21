package com.example.data.repository

import android.content.Context
import com.example.data.curriculum.CourseModule
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.CurriculumEntityMapper
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.PracticeQuestion
import com.example.data.local.CachedExamResultSnapshot
import com.example.data.local.FontSizeScale
import com.example.data.local.LineSpacingScale
import com.example.data.local.MeritPrepDao
import com.example.data.local.MeritPrepDatabase
import com.example.data.local.OfflinePreferencesRepository
import com.example.data.local.OfflineProgressSnapshot
import com.example.data.local.ThemeMode
import com.example.data.local.ThemePalette
import com.example.data.local.ThemePreferences
import com.example.data.model.ActiveCbtSessionEntity
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.Paper2AttemptEntity
import com.example.data.model.SpacedReviewCardEntity
import com.example.data.model.StudyNote
import com.example.data.model.UserProfile
import java.time.LocalDate
import java.util.UUID
import kotlin.math.roundToInt
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.json.JSONArray
import org.json.JSONObject

data class UserSyncBundle(
  val profile: UserProfile?,
  val progress: List<LessonProgress>,
  val attempts: List<ExamAttempt>,
  val notes: List<StudyNote>,
)

class MeritPrepRepository(
  private val dao: MeritPrepDao,
  private val preferencesRepository: OfflinePreferencesRepository? = null,
) {

  constructor(context: Context) : this(
    dao = MeritPrepDatabase.getInstance(context).meritPrepDao(),
    preferencesRepository = OfflinePreferencesRepository(context),
  )

  val themePreferences: Flow<ThemePreferences> =
    preferencesRepository?.themePreferences ?: flowOf(ThemePreferences())

  val offlineSnapshot: Flow<OfflineProgressSnapshot> =
    preferencesRepository?.offlineSnapshot ?: flowOf(OfflineProgressSnapshot())

  suspend fun setThemeMode(mode: ThemeMode) {
    preferencesRepository?.setThemeMode(mode)
  }

  suspend fun toggleDarkTheme(currentlyDark: Boolean) {
    preferencesRepository?.toggleDarkTheme(currentlyDark)
  }

  suspend fun setThemePalette(palette: ThemePalette) {
    preferencesRepository?.setThemePalette(palette)
  }

  suspend fun setHighContrastBoost(enabled: Boolean) {
    preferencesRepository?.setHighContrastBoost(enabled)
  }

  suspend fun setUseDynamicColor(enabled: Boolean) {
    preferencesRepository?.setUseDynamicColor(enabled)
  }

  suspend fun setFontSizeScale(scale: FontSizeScale) {
    preferencesRepository?.setFontSizeScale(scale)
  }

  suspend fun setLineSpacingScale(scale: LineSpacingScale) {
    preferencesRepository?.setLineSpacingScale(scale)
  }

  suspend fun setBoldEquations(enabled: Boolean) {
    preferencesRepository?.setBoldEquations(enabled)
  }

  suspend fun setCbtSecondsPerQuestion(seconds: Int) {
    preferencesRepository?.setCbtSecondsPerQuestion(seconds)
  }

  suspend fun setCbtAutoAdvance(enabled: Boolean) {
    preferencesRepository?.setCbtAutoAdvance(enabled)
  }

  suspend fun setCbtShowCalculator(enabled: Boolean) {
    preferencesRepository?.setCbtShowCalculator(enabled)
  }

  suspend fun setShuffleOptionsOnRetry(enabled: Boolean) {
    preferencesRepository?.setShuffleOptionsOnRetry(enabled)
  }

  suspend fun setMasteryPassPercent(percent: Int) {
    preferencesRepository?.setMasteryPassPercent(percent)
  }

  suspend fun setDailyXpTarget(xpTarget: Int) {
    preferencesRepository?.setDailyXpTarget(xpTarget)
  }

  suspend fun setSpacedReviewBatchSize(batchSize: Int) {
    preferencesRepository?.setSpacedReviewBatchSize(batchSize)
  }

  suspend fun recordPlacementPassed(subjectId: String) {
    preferencesRepository?.recordPlacementPassed(subjectId)
  }

  suspend fun seedCurriculumIfNeeded() {
    try {
      val storedVersion = preferencesRepository?.getCurriculumVersion() ?: CURRENT_CURRICULUM_VERSION
      val moduleCount = dao.getModuleCount()
      val chapterCount = dao.getChapterCount()
      val pqCount = dao.getPastQuestionCount()
      if (storedVersion == CURRENT_CURRICULUM_VERSION && moduleCount > 0 && chapterCount > 0 && pqCount > 0) {
        return
      }
      val expectedModules = CurriculumEntityMapper.buildModuleEntities()
      val expectedChapters = CurriculumEntityMapper.buildChapterEntities()
      val expectedQuestions = CurriculumEntityMapper.buildPastQuestionEntities()
      val needsRefresh =
        storedVersion != CURRENT_CURRICULUM_VERSION ||
          moduleCount != expectedModules.size ||
          chapterCount != expectedChapters.size ||
          pqCount != expectedQuestions.size

      if (needsRefresh) {
        dao.replaceCurriculum(expectedModules, expectedChapters, expectedQuestions)
        preferencesRepository?.setCurriculumVersion(CURRENT_CURRICULUM_VERSION)
      }
    } catch (e: CancellationException) {
      throw e
    } catch (_: Exception) {
      // Fallback safely if database initialization fails on first frame
    }
  }

  fun observeStructuredModules(): Flow<List<CourseModule>> =
    combine(dao.observeAllModules(), dao.observeAllChapters()) { modules, chapters ->
      if (modules.isEmpty() || chapters.isEmpty()) {
        CurriculumCatalog.tracks.flatMap { it.modules }
      } else {
        CurriculumEntityMapper.toDomainModules(modules, chapters)
      }
    }

  suspend fun fetchPastQuestionsForCbt(
    subjectId: String,
    examBoard: String,
  ): List<PracticeQuestion> {
    seedCurriculumIfNeeded()
    val fromDb =
      if (examBoard.equals("ALL", ignoreCase = true)) {
        dao.getPastQuestionsBySubject(subjectId)
      } else {
        val boardSpecific = dao.getPastQuestionsBySubjectAndBoard(subjectId, examBoard)
        if (boardSpecific.isNotEmpty()) boardSpecific else dao.getPastQuestionsBySubject(subjectId)
      }
    return if (fromDb.isNotEmpty()) {
      fromDb.map(CurriculumEntityMapper::toDomainQuestion)
    } else {
      val fallback = CurriculumCatalog.pastQuestionsFor(subjectId, examBoard)
      if (fallback.isNotEmpty()) fallback else CurriculumCatalog.pastQuestionsFor(subjectId, "ALL")
    }
  }

  fun observeProfile(userId: String): Flow<UserProfile?> = dao.observeProfile(userId)

  suspend fun ensureProfile(userId: String, displayName: String): Result<UserProfile> {
    return try {
      val existing = dao.getProfileOnce(userId)
      if (existing != null) {
        Result.success(existing)
      } else {
        val now = System.currentTimeMillis()
        val safeName = displayName.trim().ifBlank { "Scholar" }.take(80)
        val created =
          UserProfile(
            id = userId,
            userId = userId,
            displayName = safeName,
            targetExam = "BOTH",
            targetScore = 320L,
            streakDays = 1L,
            totalXp = 0L,
            createdAt = now,
            updatedAt = now,
          )
        dao.upsertProfile(created)
        Result.success(created)
      }
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  suspend fun updateGoal(
    userId: String,
    displayName: String,
    targetExam: String,
    targetScore: Long,
  ): Result<Unit> {
    return try {
      val now = System.currentTimeMillis()
      val existing = dao.getProfileOnce(userId) ?: UserProfile(id = userId, userId = userId, createdAt = now)
      val safeName = displayName.trim().ifBlank { "Scholar" }.take(80)
      val safeExam = if (targetExam in listOf("ALL", "BOTH", "JAMB", "WAEC", "NECO")) targetExam else "BOTH"
      val safeScore = targetScore.coerceIn(160L, 400L)
      dao.upsertProfile(
        existing.copy(
          displayName = safeName,
          targetExam = safeExam,
          targetScore = safeScore,
          updatedAt = now,
        )
      )
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  suspend fun awardXp(userId: String, xpDelta: Long, streakDays: Long): Result<Unit> {
    return try {
      val now = System.currentTimeMillis()
      val existing = dao.getProfileOnce(userId) ?: UserProfile(id = userId, userId = userId, createdAt = now)
      val safeDelta = xpDelta.coerceIn(0L, 5000L)
      val safeStreak = streakDays.coerceIn(0L, 3650L)
      val updatedXp = (existing.totalXp + safeDelta).coerceIn(0L, 1_000_000L)
      dao.upsertProfile(
        existing.copy(
          totalXp = updatedXp,
          streakDays = safeStreak,
          updatedAt = now,
        )
      )
      preferencesRepository?.recordLessonSnapshot(
        lessonId = "",
        isCompleted = false,
        totalXp = updatedXp,
        streakDays = safeStreak,
      )
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  suspend fun recordXpWithAutoStreak(
    userId: String,
    xpDelta: Long,
    epochDay: Long = LocalDate.now().toEpochDay(),
  ): Result<Unit> {
    return try {
      val now = System.currentTimeMillis()
      val existing = dao.getProfileOnce(userId) ?: UserProfile(id = userId, userId = userId, createdAt = now)
      val safeDelta = xpDelta.coerceIn(0L, 5000L)
      val computedStreak =
        preferencesRepository?.recordDailyActivityXp(
          xpDelta = safeDelta,
          currentStreakFallback = existing.streakDays,
          epochDay = epochDay,
        )?.first ?: existing.streakDays.coerceAtLeast(1L)
      val updatedXp = (existing.totalXp + safeDelta).coerceIn(0L, 1_000_000L)
      dao.upsertProfile(
        existing.copy(
          totalXp = updatedXp,
          streakDays = computedStreak.coerceIn(1L, 3650L),
          updatedAt = now,
        )
      )
      preferencesRepository?.recordLessonSnapshot(
        lessonId = "",
        isCompleted = false,
        totalXp = updatedXp,
        streakDays = computedStreak,
      )
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  suspend fun recordCompletedLab(userId: String, labKey: String, xpDelta: Long = 25L): Result<Unit> {
    return try {
      preferencesRepository?.recordCompletedLab(labKey)
      if (xpDelta > 0L) {
        recordXpWithAutoStreak(userId = userId, xpDelta = xpDelta)
      }
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  // --- Paper 2 Theory Persistence ---

  fun observePaper2Attempts(userId: String): Flow<List<Paper2AttemptEntity>> =
    dao.observePaper2Attempts(userId)

  suspend fun savePaper2Attempt(
    userId: String,
    promptId: String,
    subjectId: String,
    examBoard: String,
    studentWorking: String,
    checkedRubricIndices: List<Int>,
    earnedMarks: Int,
    maxMarks: Int,
  ): Result<Unit> {
    return try {
      val now = System.currentTimeMillis()
      val safeMax = maxMarks.coerceAtLeast(1)
      val safeEarned = earnedMarks.coerceIn(0, safeMax)
      val indicesJson = JSONArray(checkedRubricIndices.distinct().sorted()).toString()
      dao.upsertPaper2Attempt(
        Paper2AttemptEntity(
          id = "${userId}_$promptId",
          userId = userId,
          promptId = promptId,
          subjectId = subjectId.take(64),
          topic = examBoard.take(64),
          studentWorking = studentWorking.take(4000),
          checkedStepIndicesJson = indicesJson,
          earnedMarks = safeEarned,
          totalMarks = safeMax,
          updatedAtMillis = now,
        )
      )
      val xpReward = (15L + safeEarned * 2L).coerceAtMost(60L)
      recordXpWithAutoStreak(userId = userId, xpDelta = xpReward)
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  // --- Interrupted CBT Session Recovery ---

  fun observeActiveCbtSession(userId: String): Flow<ActiveCbtSessionEntity?> =
    dao.observeActiveCbtSession(userId)

  suspend fun getActiveCbtSessionOnce(userId: String): ActiveCbtSessionEntity? =
    dao.getActiveCbtSessionOnce(userId)

  suspend fun saveActiveCbtSession(session: ActiveCbtSessionEntity) {
    runCatching { dao.upsertActiveCbtSession(session) }
  }

  suspend fun clearActiveCbtSession(userId: String) {
    runCatching { dao.clearActiveCbtSession(userId) }
  }

  fun observeLessonProgress(userId: String): Flow<List<LessonProgress>> =
    dao.observeLessonProgress(userId)

  suspend fun saveLessonProgress(
    userId: String,
    lessonId: String,
    subjectId: String,
    moduleId: String,
    completedSteps: List<String>,
    exerciseScore: Long,
    isCompleted: Boolean,
  ): Result<Unit> {
    return try {
      val docId = "${userId}_$lessonId"
      val now = System.currentTimeMillis()
      val existing = dao.getLessonProgressOnce(docId)
      val boundedSteps = completedSteps.distinct().take(10)
      val boundedScore = exerciseScore.coerceIn(0L, 100L)
      dao.upsertLessonProgress(
        LessonProgress(
          id = docId,
          userId = userId,
          lessonId = lessonId,
          subjectId = subjectId.take(64),
          moduleId = moduleId.take(64),
          completedSteps = boundedSteps,
          exerciseScore = boundedScore,
          isCompleted = isCompleted,
          createdAt = existing?.createdAt ?: now,
          updatedAt = now,
        )
      )
      preferencesRepository?.recordLessonSnapshot(
        lessonId = lessonId,
        isCompleted = isCompleted,
      )
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  fun observeExamAttempts(userId: String): Flow<List<ExamAttempt>> = dao.observeExamAttempts(userId)

  suspend fun recordExamAttempt(
    userId: String,
    examType: String,
    subjectId: String,
    subjectTitle: String,
    correctCount: Long,
    totalQuestions: Long,
    durationSeconds: Long,
    weakTopics: List<String>,
  ): Result<String> {
    return try {
      val attemptId = "att_${UUID.randomUUID().toString().replace("-", "").take(20)}"
      val safeTotal = totalQuestions.coerceIn(1L, 200L)
      val safeCorrect = correctCount.coerceIn(0L, safeTotal)
      val percentage = ((safeCorrect * 100L) / safeTotal).coerceIn(0L, 100L)
      val safeExamType =
        when {
          examType.equals("WAEC", ignoreCase = true) -> "WAEC"
          examType.equals("NECO", ignoreCase = true) -> "NECO"
          examType.equals("ALL", ignoreCase = true) -> "ALL"
          else -> "JAMB"
        }
      val safeWeakTopics = weakTopics.distinct().map { it.take(64) }.take(10)
      val now = System.currentTimeMillis()
      val safeDuration = durationSeconds.coerceIn(0L, 18000L)

      dao.insertExamAttempt(
        ExamAttempt(
          id = attemptId,
          userId = userId,
          examType = safeExamType,
          subjectId = subjectId.take(64),
          subjectTitle = subjectTitle.take(100),
          correctCount = safeCorrect,
          totalQuestions = safeTotal,
          percentage = percentage,
          durationSeconds = safeDuration,
          weakTopics = safeWeakTopics,
          createdAt = now,
        )
      )
      preferencesRepository?.recordExamResultSnapshot(
        CachedExamResultSnapshot(
          subjectId = subjectId.take(64),
          subjectTitle = subjectTitle.take(100),
          examBoard = safeExamType,
          correctCount = safeCorrect.toInt(),
          totalQuestions = safeTotal.toInt(),
          percentage = percentage.toInt(),
          timeTakenSeconds = safeDuration.toInt(),
          timestampMs = now,
        )
      )
      Result.success(attemptId)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  fun observeStudyNotes(userId: String): Flow<List<StudyNote>> = dao.observeStudyNotes(userId)

  suspend fun createStudyNote(
    userId: String,
    lessonId: String,
    subjectId: String,
    title: String,
    content: String,
    isBookmarked: Boolean,
  ): Result<String> {
    return try {
      val noteId = "note_${UUID.randomUUID().toString().replace("-", "").take(20)}"
      val now = System.currentTimeMillis()
      val safeTitle = title.trim().ifBlank { "Revision Note" }.take(120)
      val safeContent = content.trim().ifBlank { "Key concept" }.take(2000)
      dao.upsertStudyNote(
        StudyNote(
          id = noteId,
          userId = userId,
          lessonId = lessonId.take(128),
          subjectId = subjectId.take(64),
          title = safeTitle,
          content = safeContent,
          isBookmarked = isBookmarked,
          createdAt = now,
          updatedAt = now,
        )
      )
      Result.success(noteId)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  suspend fun updateStudyNote(
    userId: String,
    noteId: String,
    title: String,
    content: String,
    isBookmarked: Boolean,
  ): Result<Unit> {
    return try {
      val now = System.currentTimeMillis()
      val existing = dao.getStudyNoteOnce(noteId) ?: return Result.failure(IllegalStateException("Note not found"))
      val safeTitle = title.trim().ifBlank { "Revision Note" }.take(120)
      val safeContent = content.trim().ifBlank { "Key concept" }.take(2000)
      dao.upsertStudyNote(
        existing.copy(
          userId = userId,
          title = safeTitle,
          content = safeContent,
          isBookmarked = isBookmarked,
          updatedAt = now,
        )
      )
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  suspend fun deleteStudyNote(userId: String, noteId: String): Result<Unit> {
    return try {
      dao.deleteStudyNote(userId, noteId)
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  // --- Spaced Repetition Queue (SM-2 Scheduling on Day 1/3/7/14/30) ---

  fun observeDueReviewCards(
    userId: String,
    nowMillis: Long = System.currentTimeMillis(),
    limit: Int = 20,
  ): Flow<List<SpacedReviewCardEntity>> =
    dao.observeDueReviewCards(userId, nowMillis, limit.coerceIn(1, 50))

  fun observeAllReviewCards(userId: String): Flow<List<SpacedReviewCardEntity>> =
    dao.observeAllReviewCards(userId)

  suspend fun seedReviewCardFromQuestion(
    userId: String,
    subjectId: String,
    lessonId: String,
    question: PracticeQuestion,
    dueImmediately: Boolean = true,
  ) {
    val cardId = "${userId}_sr_${question.id}"
    val existing = dao.getReviewCardOnce(cardId)
    val now = System.currentTimeMillis()
    val correctOption =
      question.options.getOrElse(question.correctIndex) { "" }
    val explanationText =
      if (question.stepByStepSolution.isNotBlank()) question.stepByStepSolution else question.hint
    if (existing == null) {
      dao.upsertReviewCard(
        SpacedReviewCardEntity(
          id = cardId,
          userId = userId,
          subjectId = subjectId.ifBlank { "general" },
          lessonId = lessonId,
          questionId = question.id,
          topic = question.topic,
          prompt = question.question,
          answer = correctOption,
          explanation = explanationText,
          intervalDays = 1,
          easeFactor = 2.5,
          repetitions = 0,
          dueAtMillis = if (dueImmediately) now else now + DAY_MILLIS,
          lastReviewedAtMillis = 0L,
          updatedAtMillis = now,
        )
      )
    } else if (dueImmediately) {
      dao.upsertReviewCard(
        existing.copy(
          intervalDays = 1,
          repetitions = 0,
          dueAtMillis = now,
          updatedAtMillis = now,
        )
      )
    }
  }

  suspend fun seedReviewCardsFromLesson(userId: String, lesson: Lesson) {
    lesson.exercises.take(2).forEachIndexed { idx, q ->
      val cardId = "${userId}_sr_${q.id}"
      if (dao.getReviewCardOnce(cardId) == null) {
        val now = System.currentTimeMillis()
        dao.upsertReviewCard(
          SpacedReviewCardEntity(
            id = cardId,
            userId = userId,
            subjectId = lesson.subjectId,
            lessonId = lesson.id,
            questionId = q.id,
            topic = lesson.title,
            prompt = q.question,
            answer = q.options.getOrElse(q.correctIndex) { "" },
            explanation = q.stepByStepSolution,
            intervalDays = 1,
            easeFactor = 2.5,
            repetitions = 0,
            dueAtMillis = if (idx == 0) now else now + DAY_MILLIS,
            lastReviewedAtMillis = 0L,
            updatedAtMillis = now,
          )
        )
      }
    }
  }

  /**
   * Quality: 0 = Forgot (reset 1 day), 1 = Hard (1-2 days), 2 = Good (Day 1->3->7->14->30 SM-2), 3 = Easy.
   */
  suspend fun rateSpacedReviewCard(
    cardId: String,
    quality: Int,
    nowMillis: Long = System.currentTimeMillis(),
  ): Result<SpacedReviewCardEntity> {
    return try {
      val card = dao.getReviewCardOnce(cardId) ?: return Result.failure(IllegalStateException("Card not found"))
      val q = quality.coerceIn(0, 3)
      val nextReps: Int
      val nextInterval: Int
      val nextEase: Double
      when (q) {
        0 -> {
          nextReps = 0
          nextInterval = 1
          nextEase = (card.easeFactor - 0.25).coerceAtLeast(1.3)
        }
        1 -> {
          nextReps = (card.repetitions).coerceAtLeast(1)
          nextInterval = if (card.intervalDays <= 1) 2 else (card.intervalDays * 1.2).roundToInt().coerceAtLeast(2)
          nextEase = (card.easeFactor - 0.1).coerceAtLeast(1.3)
        }
        2 -> {
          nextReps = card.repetitions + 1
          nextInterval =
            when (nextReps) {
              1 -> 1
              2 -> 3
              3 -> 7
              4 -> 14
              else -> (card.intervalDays * card.easeFactor).roundToInt().coerceIn(15, 60)
            }
          nextEase = (card.easeFactor + 0.05).coerceAtMost(2.8)
        }
        else -> {
          nextReps = card.repetitions + 1
          nextInterval =
            when (nextReps) {
              1 -> 3
              2 -> 7
              3 -> 14
              else -> (card.intervalDays * (card.easeFactor + 0.2)).roundToInt().coerceIn(21, 90)
            }
          nextEase = (card.easeFactor + 0.15).coerceAtMost(3.0)
        }
      }
      val updated =
        card.copy(
          repetitions = nextReps,
          intervalDays = nextInterval,
          easeFactor = nextEase,
          dueAtMillis = nowMillis + (nextInterval * DAY_MILLIS),
          lastReviewedAtMillis = nowMillis,
          updatedAtMillis = nowMillis,
        )
      dao.upsertReviewCard(updated)
      Result.success(updated)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  // --- Portable JSON Backup (SAF) & Cloud Merge ---

  suspend fun exportUserBundle(userId: String): UserSyncBundle =
    UserSyncBundle(
      profile = dao.getProfileOnce(userId),
      progress = dao.getAllLessonProgressOnce(userId),
      attempts = dao.getAllExamAttemptsOnce(userId),
      notes = dao.getAllStudyNotesOnce(userId),
    )

  suspend fun applyMergedCloudBundle(
    userId: String,
    remoteProfile: UserProfile?,
    remoteProgress: List<LessonProgress>,
    remoteAttempts: List<ExamAttempt>,
    remoteNotes: List<StudyNote>,
  ) {
    val localProfile = dao.getProfileOnce(userId)
    if (remoteProfile != null) {
      val mergedProfile =
        if (localProfile == null) {
          remoteProfile.copy(id = userId, userId = userId)
        } else {
          localProfile.copy(
            displayName =
              if (remoteProfile.updatedAt > localProfile.updatedAt) remoteProfile.displayName
              else localProfile.displayName,
            totalXp = maxOf(localProfile.totalXp, remoteProfile.totalXp),
            streakDays = maxOf(localProfile.streakDays, remoteProfile.streakDays),
            targetScore =
              if (remoteProfile.updatedAt > localProfile.updatedAt) remoteProfile.targetScore
              else localProfile.targetScore,
            updatedAt = maxOf(localProfile.updatedAt, remoteProfile.updatedAt),
          )
        }
      dao.upsertProfile(mergedProfile)
    }

    val localProgressMap = dao.getAllLessonProgressOnce(userId).associateBy { it.lessonId }
    val completedIds = localProgressMap.values.filter { it.isCompleted }.map { it.lessonId }.toMutableSet()
    remoteProgress.forEach { remote ->
      val local = localProgressMap[remote.lessonId]
      val mergedSteps = ((local?.completedSteps.orEmpty()) + remote.completedSteps).distinct().take(10)
      val mergedScore = maxOf(local?.exerciseScore ?: 0L, remote.exerciseScore)
      val mergedDone = (local?.isCompleted == true) || remote.isCompleted
      if (mergedDone) completedIds.add(remote.lessonId)
      dao.upsertLessonProgress(
        LessonProgress(
          id = "${userId}_${remote.lessonId}",
          userId = userId,
          lessonId = remote.lessonId,
          subjectId = remote.subjectId.ifBlank { local?.subjectId.orEmpty() },
          moduleId = remote.moduleId.ifBlank { local?.moduleId.orEmpty() },
          completedSteps = mergedSteps,
          exerciseScore = mergedScore,
          isCompleted = mergedDone,
          createdAt = minOf(local?.createdAt ?: remote.createdAt, remote.createdAt),
          updatedAt = maxOf(local?.updatedAt ?: 0L, remote.updatedAt),
        )
      )
    }

    val localAttemptIds = dao.getAllExamAttemptsOnce(userId).map { it.id }.toSet()
    remoteAttempts.filter { it.id !in localAttemptIds }.forEach { remote ->
      dao.insertExamAttempt(remote.copy(userId = userId))
    }

    val localNotesMap = dao.getAllStudyNotesOnce(userId).associateBy { it.id }
    remoteNotes.forEach { remote ->
      val local = localNotesMap[remote.id]
      if (local == null || remote.updatedAt > local.updatedAt) {
        dao.upsertStudyNote(remote.copy(userId = userId))
      }
    }

    val latestProfile = dao.getProfileOnce(userId)
    if (latestProfile != null) {
      preferencesRepository?.syncProgressIds(
        completedIds = completedIds,
        xp = latestProfile.totalXp,
        streakDays = latestProfile.streakDays,
      )
    }
  }

  suspend fun exportBackupJson(userId: String): Result<String> {
    return try {
      val profile = dao.getProfileOnce(userId)
      val progressList = dao.getAllLessonProgressOnce(userId)
      val attemptsList = dao.getAllExamAttemptsOnce(userId)
      val notesList = dao.getAllStudyNotesOnce(userId)
      val cardsList = dao.getAllReviewCardsOnce(userId)
      val paper2List = dao.getAllPaper2AttemptsOnce(userId)
      val snapshot = offlineSnapshot.first()
      val prefs = themePreferences.first()

      val root =
        JSONObject().apply {
          put("schemaVersion", 1)
          put("exportedAtMillis", System.currentTimeMillis())
          if (profile != null) {
            put(
              "profile",
              JSONObject().apply {
                put("displayName", profile.displayName)
                put("targetExam", profile.targetExam)
                put("targetScore", profile.targetScore)
                put("streakDays", profile.streakDays)
                put("totalXp", profile.totalXp)
              },
            )
          }
          put("placementPassedSubjects", JSONArray(snapshot.placementPassedSubjects.toList()))
          put(
            "preferences",
            JSONObject().apply {
              put("themeMode", prefs.themeMode.name)
              put("themePalette", prefs.themePalette.name)
              put("highContrastBoost", prefs.highContrastBoost)
              put("fontSizeScale", prefs.fontSizeScale.name)
              put("lineSpacingScale", prefs.lineSpacingScale.name)
              put("boldEquations", prefs.boldEquations)
              put("cbtSecondsPerQuestion", prefs.cbtSecondsPerQuestion)
              put("cbtAutoAdvance", prefs.cbtAutoAdvance)
              put("cbtShowCalculator", prefs.cbtShowCalculator)
              put("shuffleOptionsOnRetry", prefs.shuffleOptionsOnRetry)
              put("masteryPassPercent", prefs.masteryPassPercent)
              put("dailyXpTarget", prefs.dailyXpTarget)
              put("spacedReviewBatchSize", prefs.spacedReviewBatchSize)
            },
          )
          put(
            "lessonProgress",
            JSONArray().apply {
              progressList.forEach { lp ->
                put(
                  JSONObject().apply {
                    put("lessonId", lp.lessonId)
                    put("subjectId", lp.subjectId)
                    put("moduleId", lp.moduleId)
                    put("completedSteps", JSONArray(lp.completedSteps))
                    put("exerciseScore", lp.exerciseScore)
                    put("isCompleted", lp.isCompleted)
                    put("createdAt", lp.createdAt)
                    put("updatedAt", lp.updatedAt)
                  }
                )
              }
            },
          )
          put(
            "examAttempts",
            JSONArray().apply {
              attemptsList.forEach { att ->
                put(
                  JSONObject().apply {
                    put("id", att.id)
                    put("examType", att.examType)
                    put("subjectId", att.subjectId)
                    put("subjectTitle", att.subjectTitle)
                    put("correctCount", att.correctCount)
                    put("totalQuestions", att.totalQuestions)
                    put("percentage", att.percentage)
                    put("durationSeconds", att.durationSeconds)
                    put("weakTopics", JSONArray(att.weakTopics))
                    put("createdAt", att.createdAt)
                  }
                )
              }
            },
          )
          put(
            "studyNotes",
            JSONArray().apply {
              notesList.forEach { n ->
                put(
                  JSONObject().apply {
                    put("id", n.id)
                    put("lessonId", n.lessonId)
                    put("subjectId", n.subjectId)
                    put("title", n.title)
                    put("content", n.content)
                    put("isBookmarked", n.isBookmarked)
                    put("createdAt", n.createdAt)
                    put("updatedAt", n.updatedAt)
                  }
                )
              }
            },
          )
          put(
            "spacedReviewCards",
            JSONArray().apply {
              cardsList.forEach { c ->
                put(
                  JSONObject().apply {
                    put("id", c.id)
                    put("subjectId", c.subjectId)
                    put("lessonId", c.lessonId)
                    put("questionId", c.questionId)
                    put("topic", c.topic)
                    put("prompt", c.prompt)
                    put("answer", c.answer)
                    put("explanation", c.explanation)
                    put("intervalDays", c.intervalDays)
                    put("easeFactor", c.easeFactor)
                    put("repetitions", c.repetitions)
                    put("dueAtMillis", c.dueAtMillis)
                    put("lastReviewedAtMillis", c.lastReviewedAtMillis)
                    put("updatedAtMillis", c.updatedAtMillis)
                  }
                )
              }
            },
          )
          put(
            "paper2Attempts",
            JSONArray().apply {
              paper2List.forEach { p2 ->
                put(
                  JSONObject().apply {
                    put("promptId", p2.promptId)
                    put("subjectId", p2.subjectId)
                    put("topic", p2.topic)
                    put("studentWorking", p2.studentWorking)
                    put("checkedStepIndicesJson", p2.checkedStepIndicesJson)
                    put("earnedMarks", p2.earnedMarks)
                    put("totalMarks", p2.totalMarks)
                    put("updatedAtMillis", p2.updatedAtMillis)
                  }
                )
              }
            },
          )
        }
      Result.success(root.toString(2))
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  suspend fun importBackupJson(userId: String, rawJson: String): Result<Int> {
    return try {
      val root = JSONObject(rawJson)
      var restoredCount = 0
      val profileObj = root.optJSONObject("profile")
      if (profileObj != null) {
        val now = System.currentTimeMillis()
        val existing = dao.getProfileOnce(userId) ?: UserProfile(id = userId, userId = userId, createdAt = now)
        val merged =
          existing.copy(
            displayName = profileObj.optString("displayName", existing.displayName).take(80),
            targetExam = profileObj.optString("targetExam", existing.targetExam),
            targetScore = profileObj.optLong("targetScore", existing.targetScore).coerceIn(160L, 400L),
            streakDays = maxOf(existing.streakDays, profileObj.optLong("streakDays", 1L)),
            totalXp = maxOf(existing.totalXp, profileObj.optLong("totalXp", 0L)),
            updatedAt = now,
          )
        dao.upsertProfile(merged)
        restoredCount++
      }

      root.optJSONArray("placementPassedSubjects")?.let { arr ->
        val placement = (0 until arr.length()).mapNotNull { arr.optString(it).takeIf(String::isNotBlank) }.toSet()
        if (placement.isNotEmpty()) {
          preferencesRepository?.syncPlacementPassedSubjects(placement)
        }
      }

      root.optJSONObject("preferences")?.let { p ->
        ThemeMode.entries.find { it.name == p.optString("themeMode") }?.let { setThemeMode(it) }
        ThemePalette.entries.find { it.name == p.optString("themePalette") }?.let { setThemePalette(it) }
        if (p.has("highContrastBoost")) setHighContrastBoost(p.optBoolean("highContrastBoost"))
        FontSizeScale.entries.find { it.name == p.optString("fontSizeScale") }?.let { setFontSizeScale(it) }
        LineSpacingScale.entries.find { it.name == p.optString("lineSpacingScale") }?.let { setLineSpacingScale(it) }
        if (p.has("boldEquations")) setBoldEquations(p.optBoolean("boldEquations"))
        if (p.has("cbtSecondsPerQuestion")) setCbtSecondsPerQuestion(p.optInt("cbtSecondsPerQuestion", 60))
        if (p.has("cbtAutoAdvance")) setCbtAutoAdvance(p.optBoolean("cbtAutoAdvance"))
        if (p.has("cbtShowCalculator")) setCbtShowCalculator(p.optBoolean("cbtShowCalculator"))
        if (p.has("shuffleOptionsOnRetry")) setShuffleOptionsOnRetry(p.optBoolean("shuffleOptionsOnRetry"))
        if (p.has("masteryPassPercent")) setMasteryPassPercent(p.optInt("masteryPassPercent", 80))
        if (p.has("dailyXpTarget")) setDailyXpTarget(p.optInt("dailyXpTarget", 100))
        if (p.has("spacedReviewBatchSize")) setSpacedReviewBatchSize(p.optInt("spacedReviewBatchSize", 10))
      }

      val completedIds = mutableSetOf<String>()
      root.optJSONArray("lessonProgress")?.let { arr ->
        for (i in 0 until arr.length()) {
          val obj = arr.optJSONObject(i) ?: continue
          val lessonId = obj.optString("lessonId")
          if (lessonId.isBlank()) continue
          val stepsArr = obj.optJSONArray("completedSteps")
          val steps =
            if (stepsArr != null) List(stepsArr.length()) { idx -> stepsArr.optString(idx) }.filter { it.isNotBlank() }
            else emptyList()
          val done = obj.optBoolean("isCompleted", false)
          if (done) completedIds.add(lessonId)
          dao.upsertLessonProgress(
            LessonProgress(
              id = "${userId}_$lessonId",
              userId = userId,
              lessonId = lessonId,
              subjectId = obj.optString("subjectId"),
              moduleId = obj.optString("moduleId"),
              completedSteps = steps,
              exerciseScore = obj.optLong("exerciseScore", 0L).coerceIn(0L, 100L),
              isCompleted = done,
              createdAt = obj.optLong("createdAt", System.currentTimeMillis()),
              updatedAt = obj.optLong("updatedAt", System.currentTimeMillis()),
            )
          )
          restoredCount++
        }
      }

      root.optJSONArray("examAttempts")?.let { arr ->
        for (i in 0 until arr.length()) {
          val obj = arr.optJSONObject(i) ?: continue
          val id = obj.optString("id").ifBlank { "att_imp_$i" }
          val weakArr = obj.optJSONArray("weakTopics")
          val weak =
            if (weakArr != null) List(weakArr.length()) { idx -> weakArr.optString(idx) }.filter { it.isNotBlank() }
            else emptyList()
          dao.insertExamAttempt(
            ExamAttempt(
              id = id,
              userId = userId,
              examType = obj.optString("examType", "JAMB"),
              subjectId = obj.optString("subjectId"),
              subjectTitle = obj.optString("subjectTitle"),
              correctCount = obj.optLong("correctCount", 0L),
              totalQuestions = obj.optLong("totalQuestions", 1L).coerceAtLeast(1L),
              percentage = obj.optLong("percentage", 0L),
              durationSeconds = obj.optLong("durationSeconds", 0L),
              weakTopics = weak,
              createdAt = obj.optLong("createdAt", System.currentTimeMillis()),
            )
          )
          restoredCount++
        }
      }

      root.optJSONArray("studyNotes")?.let { arr ->
        for (i in 0 until arr.length()) {
          val obj = arr.optJSONObject(i) ?: continue
          val id = obj.optString("id").ifBlank { "note_imp_$i" }
          dao.upsertStudyNote(
            StudyNote(
              id = id,
              userId = userId,
              lessonId = obj.optString("lessonId"),
              subjectId = obj.optString("subjectId"),
              title = obj.optString("title", "Saved Note"),
              content = obj.optString("content", ""),
              isBookmarked = obj.optBoolean("isBookmarked", true),
              createdAt = obj.optLong("createdAt", System.currentTimeMillis()),
              updatedAt = obj.optLong("updatedAt", System.currentTimeMillis()),
            )
          )
          restoredCount++
        }
      }

      root.optJSONArray("spacedReviewCards")?.let { arr ->
        val restoredCards = mutableListOf<SpacedReviewCardEntity>()
        for (i in 0 until arr.length()) {
          val obj = arr.optJSONObject(i) ?: continue
          val qId = obj.optString("questionId").ifBlank { "q_$i" }
          restoredCards +=
            SpacedReviewCardEntity(
              id = "${userId}_sr_$qId",
              userId = userId,
              subjectId = obj.optString("subjectId"),
              lessonId = obj.optString("lessonId"),
              questionId = qId,
              topic = obj.optString("topic"),
              prompt = obj.optString("prompt"),
              answer = obj.optString("answer"),
              explanation = obj.optString("explanation"),
              intervalDays = obj.optInt("intervalDays", 1),
              easeFactor = obj.optDouble("easeFactor", 2.5),
              repetitions = obj.optInt("repetitions", 0),
              dueAtMillis = obj.optLong("dueAtMillis", System.currentTimeMillis()),
              lastReviewedAtMillis = obj.optLong("lastReviewedAtMillis", 0L),
              updatedAtMillis = obj.optLong("updatedAtMillis", System.currentTimeMillis()),
            )
        }
        if (restoredCards.isNotEmpty()) {
          dao.upsertReviewCards(restoredCards)
          restoredCount += restoredCards.size
        }
      }

      root.optJSONArray("paper2Attempts")?.let { arr ->
        for (i in 0 until arr.length()) {
          val obj = arr.optJSONObject(i) ?: continue
          val promptId = obj.optString("promptId")
          if (promptId.isBlank()) continue
          dao.upsertPaper2Attempt(
            Paper2AttemptEntity(
              id = "${userId}_$promptId",
              userId = userId,
              promptId = promptId,
              subjectId = obj.optString("subjectId"),
              topic = obj.optString("topic", obj.optString("examBoard", "WAEC")),
              studentWorking = obj.optString("studentWorking"),
              checkedStepIndicesJson = obj.optString("checkedStepIndicesJson", obj.optString("checkedIndicesCsv", "[]")),
              earnedMarks = obj.optInt("earnedMarks", 0),
              totalMarks = obj.optInt("totalMarks", obj.optInt("maxMarks", 1)).coerceAtLeast(1),
              updatedAtMillis = obj.optLong("updatedAtMillis", obj.optLong("updatedAt", System.currentTimeMillis())),
            )
          )
          restoredCount++
        }
      }

      val p = dao.getProfileOnce(userId)
      if (p != null) {
        preferencesRepository?.syncProgressIds(completedIds, p.totalXp, p.streakDays)
      }
      Result.success(restoredCount)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  internal companion object {
    const val CURRENT_CURRICULUM_VERSION = 11
    private const val DAY_MILLIS = 86_400_000L
  }
}
