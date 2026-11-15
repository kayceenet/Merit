package com.example.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp

@IgnoreExtraProperties
data class UserProfile(
  @DocumentId val id: String = "",
  val userId: String = "",
  val displayName: String = "Scholar",
  val targetExam: String = "BOTH",
  val targetScore: Long = 320L,
  val streakDays: Long = 1L,
  val totalXp: Long = 0L,
  @ServerTimestamp val createdAt: Timestamp? = null,
  @ServerTimestamp val updatedAt: Timestamp? = null,
)

@IgnoreExtraProperties
data class LessonProgress(
  @DocumentId val id: String = "",
  val userId: String = "",
  val lessonId: String = "",
  val subjectId: String = "",
  val moduleId: String = "",
  val completedSteps: List<String> = emptyList(),
  val exerciseScore: Long = 0L,
  val isCompleted: Boolean = false,
  @ServerTimestamp val createdAt: Timestamp? = null,
  @ServerTimestamp val updatedAt: Timestamp? = null,
)

@IgnoreExtraProperties
data class ExamAttempt(
  @DocumentId val id: String = "",
  val userId: String = "",
  val examType: String = "JAMB",
  val subjectId: String = "",
  val subjectTitle: String = "",
  val correctCount: Long = 0L,
  val totalQuestions: Long = 0L,
  val percentage: Long = 0L,
  val durationSeconds: Long = 0L,
  val weakTopics: List<String> = emptyList(),
  @ServerTimestamp val createdAt: Timestamp? = null,
)

@IgnoreExtraProperties
data class StudyNote(
  @DocumentId val id: String = "",
  val userId: String = "",
  val lessonId: String = "",
  val subjectId: String = "",
  val title: String = "",
  val content: String = "",
  val isBookmarked: Boolean = false,
  @ServerTimestamp val createdAt: Timestamp? = null,
  @ServerTimestamp val updatedAt: Timestamp? = null,
)
