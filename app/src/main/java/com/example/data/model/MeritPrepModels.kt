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
