package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfile(
  @PrimaryKey val id: String = DEFAULT_PROFILE_ID,
  val userId: String = DEFAULT_PROFILE_ID,
  val displayName: String = "Scholar",
  val targetExam: String = "BOTH",
  val targetScore: Long = 320L,
  val streakDays: Long = 1L,
  val totalXp: Long = 0L,
  val createdAt: Long = System.currentTimeMillis(),
  val updatedAt: Long = System.currentTimeMillis(),
) {
  companion object {
    const val DEFAULT_PROFILE_ID = "local_scholar"
  }
}

@Entity(tableName = "lesson_progress")
data class LessonProgress(
  @PrimaryKey val id: String = "",
  val userId: String = UserProfile.DEFAULT_PROFILE_ID,
  val lessonId: String = "",
  val subjectId: String = "",
  val moduleId: String = "",
  val completedSteps: List<String> = emptyList(),
  val exerciseScore: Long = 0L,
  val isCompleted: Boolean = false,
  val createdAt: Long = System.currentTimeMillis(),
  val updatedAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "exam_attempts")
data class ExamAttempt(
  @PrimaryKey val id: String = "",
  val userId: String = UserProfile.DEFAULT_PROFILE_ID,
  val examType: String = "JAMB",
  val subjectId: String = "",
  val subjectTitle: String = "",
  val correctCount: Long = 0L,
  val totalQuestions: Long = 0L,
  val percentage: Long = 0L,
  val durationSeconds: Long = 0L,
  val weakTopics: List<String> = emptyList(),
  val createdAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "study_notes")
data class StudyNote(
  @PrimaryKey val id: String = "",
  val userId: String = UserProfile.DEFAULT_PROFILE_ID,
  val lessonId: String = "",
  val subjectId: String = "",
  val title: String = "",
  val content: String = "",
  val isBookmarked: Boolean = false,
  val createdAt: Long = System.currentTimeMillis(),
  val updatedAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "learning_modules")
data class LearningModuleEntity(
  @PrimaryKey val id: String,
  val subjectId: String,
  val indexLabel: String,
  val title: String,
  val summary: String,
  val orderIndex: Int,
)

@Entity(tableName = "learning_chapters")
data class LearningChapterEntity(
  @PrimaryKey val id: String,
  val moduleId: String,
  val subjectId: String,
  val title: String,
  val durationMinutes: Int,
  val syllabusTag: String,
  val labTypeKey: String,
  val labBrief: String,
  val orderIndex: Int,
  val learningOutcomesJson: String,
  val conceptsJson: String,
  val workedExamplesJson: String,
  val scenariosJson: String,
  val exercisesJson: String,
)

@Entity(tableName = "past_questions")
data class PastQuestionEntity(
  @PrimaryKey val id: String,
  val subjectId: String,
  val topic: String,
  val examType: String,
  val year: Int,
  val question: String,
  val optionsJson: String,
  val correctIndex: Int,
  val hint: String,
  val stepByStepSolution: String,
  val waecMarkingScheme: String?,
  val orderIndex: Int,
)

@Entity(tableName = "spaced_review_cards")
data class SpacedReviewCardEntity(
  @PrimaryKey val id: String,
  val userId: String = UserProfile.DEFAULT_PROFILE_ID,
  val subjectId: String,
  val lessonId: String,
  val questionId: String,
  val topic: String,
  val prompt: String,
  val answer: String,
  val explanation: String,
  val intervalDays: Int = 1,
  val easeFactor: Double = 2.5,
  val repetitions: Int = 0,
  val dueAtMillis: Long = System.currentTimeMillis(),
  val lastReviewedAtMillis: Long = 0L,
  val updatedAtMillis: Long = System.currentTimeMillis(),
)

@Entity(tableName = "paper2_attempts")
data class Paper2AttemptEntity(
  @PrimaryKey val id: String,
  val userId: String = UserProfile.DEFAULT_PROFILE_ID,
  val promptId: String,
  val subjectId: String,
  val topic: String,
  val studentWorking: String,
  val checkedStepIndicesJson: String,
  val earnedMarks: Int,
  val totalMarks: Int,
  val updatedAtMillis: Long = System.currentTimeMillis(),
)

@Entity(tableName = "active_cbt_sessions")
data class ActiveCbtSessionEntity(
  @PrimaryKey val userId: String = UserProfile.DEFAULT_PROFILE_ID,
  val selectedSubjectId: String,
  val selectedExamBoard: String,
  val selectedQuestionLimit: Int,
  val isUtmeComboMode: Boolean,
  val selectedComboElectivesJson: String,
  val comboQuestionSubjectsJson: String,
  val comboSubjectTabsJson: String,
  val activeComboSubjectTab: String?,
  val questionIdsJson: String,
  val currentIndex: Int,
  val selectedAnswersJson: String,
  val flaggedQuestionsJson: String,
  val perQuestionSecondsJson: String,
  val remainingSeconds: Int,
  val elapsedSeconds: Int,
  val totalAllowedSeconds: Int,
  val updatedAtMillis: Long = System.currentTimeMillis(),
)


