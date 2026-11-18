package com.example.data.repository

import android.content.Context
import com.example.data.curriculum.CourseModule
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.CurriculumEntityMapper
import com.example.data.curriculum.PracticeQuestion
import com.example.data.local.CachedExamResultSnapshot
import com.example.data.local.MeritPrepDao
import com.example.data.local.MeritPrepDatabase
import com.example.data.local.OfflinePreferencesRepository
import com.example.data.local.OfflineProgressSnapshot
import com.example.data.local.ThemeMode
import com.example.data.local.ThemePreferences
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.StudyNote
import com.example.data.model.UserProfile
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import java.util.UUID

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

  suspend fun setUseDynamicColor(enabled: Boolean) {
    preferencesRepository?.setUseDynamicColor(enabled)
  }

  suspend fun seedCurriculumIfNeeded() {
    try {
      val expectedModules = CurriculumEntityMapper.buildModuleEntities()
      val expectedChapters = CurriculumEntityMapper.buildChapterEntities()
      val expectedQuestions = CurriculumEntityMapper.buildPastQuestionEntities()
      val storedVersion = preferencesRepository?.getCurriculumVersion() ?: CURRENT_CURRICULUM_VERSION
      val needsRefresh =
        storedVersion != CURRENT_CURRICULUM_VERSION ||
          dao.getModuleCount() != expectedModules.size ||
          dao.getChapterCount() != expectedChapters.size ||
          dao.getPastQuestionCount() != expectedQuestions.size

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

  private companion object {
    const val CURRENT_CURRICULUM_VERSION = 5
  }
}
