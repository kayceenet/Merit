package com.example.data.repository

import android.content.Context
import com.example.R
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.StudyNote
import com.example.data.model.UserProfile
import com.example.data.remote.OperationType
import com.example.data.remote.handleFirestoreError
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.UUID

class MeritPrepRepository(private val db: FirebaseFirestore) {

  constructor(
    context: Context,
  ) : this(
    FirebaseFirestore.getInstance(
      context.applicationContext.getString(R.string.firestore_database_id)
    )
  )

  private fun userDocRef(userId: String) = db.collection("users").document(userId)

  private fun lessonProgressCol(userId: String) =
    userDocRef(userId).collection("lessonProgress")

  private fun examAttemptsCol(userId: String) =
    userDocRef(userId).collection("examAttempts")

  private fun studyNotesCol(userId: String) =
    userDocRef(userId).collection("studyNotes")

  private fun currentUidOrNull(): String? = runCatching { Firebase.auth.currentUser?.uid }.getOrNull()

  fun observeProfile(userId: String): Flow<UserProfile?> {
    val docRef = userDocRef(userId)
    return docRef
      .snapshots()
      .map { snapshot ->
        if (snapshot.exists()) {
          snapshot.toObject(
            UserProfile::class.java,
            DocumentSnapshot.ServerTimestampBehavior.ESTIMATE,
          )
        } else {
          null
        }
      }
      .catch { error ->
        logFlowErrorIfSignedIn(error, userId, OperationType.GET, docRef.path)
        throw error
      }
  }

  suspend fun ensureProfile(userId: String, displayName: String): Result<UserProfile> {
    val docRef = userDocRef(userId)
    return try {
      val existing = docRef.get().await()
      if (existing.exists()) {
        val profile =
          existing.toObject(
            UserProfile::class.java,
            DocumentSnapshot.ServerTimestampBehavior.ESTIMATE,
          ) ?: UserProfile(userId = userId, displayName = displayName)
        Result.success(profile)
      } else {
        val safeName = displayName.trim().ifBlank { "Scholar" }.take(80)
        val payload =
          mapOf(
            "userId" to userId,
            "displayName" to safeName,
            "targetExam" to "BOTH",
            "targetScore" to 320L,
            "streakDays" to 1L,
            "totalXp" to 0L,
            "createdAt" to FieldValue.serverTimestamp(),
            "updatedAt" to FieldValue.serverTimestamp(),
          )
        docRef.set(payload).await()
        Result.success(UserProfile(id = userId, userId = userId, displayName = safeName))
      }
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.WRITE, docRef.path)
      Result.failure(e)
    }
  }

  suspend fun updateGoal(
    userId: String,
    displayName: String,
    targetExam: String,
    targetScore: Long,
  ): Result<Unit> {
    val docRef = userDocRef(userId)
    return try {
      val safeName = displayName.trim().ifBlank { "Scholar" }.take(80)
      val safeExam = if (targetExam in listOf("BOTH", "JAMB", "WAEC")) targetExam else "BOTH"
      val safeScore = targetScore.coerceIn(160L, 400L)
      docRef
        .update(
          mapOf(
            "displayName" to safeName,
            "targetExam" to safeExam,
            "targetScore" to safeScore,
            "updatedAt" to FieldValue.serverTimestamp(),
          )
        )
        .await()
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.UPDATE, docRef.path)
      Result.failure(e)
    }
  }

  suspend fun awardXp(userId: String, xpDelta: Long, streakDays: Long): Result<Unit> {
    val docRef = userDocRef(userId)
    return try {
      val safeDelta = xpDelta.coerceIn(0L, 5000L)
      val safeStreak = streakDays.coerceIn(0L, 3650L)
      docRef
        .update(
          mapOf(
            "totalXp" to FieldValue.increment(safeDelta),
            "streakDays" to safeStreak,
            "updatedAt" to FieldValue.serverTimestamp(),
          )
        )
        .await()
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.UPDATE, docRef.path)
      Result.failure(e)
    }
  }

  fun observeLessonProgress(userId: String): Flow<List<LessonProgress>> {
    val colRef = lessonProgressCol(userId)
    return colRef
      .snapshots()
      .map { snapshot ->
        snapshot.toObjects(
          LessonProgress::class.java,
          DocumentSnapshot.ServerTimestampBehavior.ESTIMATE,
        )
      }
      .catch { error ->
        logFlowErrorIfSignedIn(error, userId, OperationType.LIST, colRef.path)
        throw error
      }
  }

  suspend fun saveLessonProgress(
    userId: String,
    lessonId: String,
    subjectId: String,
    moduleId: String,
    completedSteps: List<String>,
    exerciseScore: Long,
    isCompleted: Boolean,
  ): Result<Unit> {
    val docRef = lessonProgressCol(userId).document(lessonId)
    return try {
      val snapshot = docRef.get().await()
      val boundedSteps = completedSteps.distinct().take(10)
      val boundedScore = exerciseScore.coerceIn(0L, 100L)
      if (snapshot.exists()) {
        docRef
          .update(
            mapOf(
              "completedSteps" to boundedSteps,
              "exerciseScore" to boundedScore,
              "isCompleted" to isCompleted,
              "updatedAt" to FieldValue.serverTimestamp(),
            )
          )
          .await()
      } else {
        docRef
          .set(
            mapOf(
              "userId" to userId,
              "lessonId" to lessonId,
              "subjectId" to subjectId.take(64),
              "moduleId" to moduleId.take(64),
              "completedSteps" to boundedSteps,
              "exerciseScore" to boundedScore,
              "isCompleted" to isCompleted,
              "createdAt" to FieldValue.serverTimestamp(),
              "updatedAt" to FieldValue.serverTimestamp(),
            )
          )
          .await()
      }
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.WRITE, docRef.path)
      Result.failure(e)
    }
  }

  fun observeExamAttempts(userId: String): Flow<List<ExamAttempt>> {
    val colRef = examAttemptsCol(userId)
    return colRef
      .snapshots()
      .map { snapshot ->
        snapshot
          .toObjects(
            ExamAttempt::class.java,
            DocumentSnapshot.ServerTimestampBehavior.ESTIMATE,
          )
          .sortedByDescending { it.createdAt ?: Timestamp(0, 0) }
      }
      .catch { error ->
        logFlowErrorIfSignedIn(error, userId, OperationType.LIST, colRef.path)
        throw error
      }
  }

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
    val attemptId = "att_${UUID.randomUUID().toString().replace("-", "").take(20)}"
    val docRef = examAttemptsCol(userId).document(attemptId)
    return try {
      val safeTotal = totalQuestions.coerceIn(1L, 200L)
      val safeCorrect = correctCount.coerceIn(0L, safeTotal)
      val percentage = ((safeCorrect * 100L) / safeTotal).coerceIn(0L, 100L)
      val safeExamType = if (examType == "WAEC") "WAEC" else "JAMB"
      val safeWeakTopics = weakTopics.distinct().map { it.take(64) }.take(10)

      docRef
        .set(
          mapOf(
            "userId" to userId,
            "examType" to safeExamType,
            "subjectId" to subjectId.take(64),
            "subjectTitle" to subjectTitle.take(100),
            "correctCount" to safeCorrect,
            "totalQuestions" to safeTotal,
            "percentage" to percentage,
            "durationSeconds" to durationSeconds.coerceIn(0L, 18000L),
            "weakTopics" to safeWeakTopics,
            "createdAt" to FieldValue.serverTimestamp(),
          )
        )
        .await()
      Result.success(attemptId)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.CREATE, docRef.path)
      Result.failure(e)
    }
  }

  fun observeStudyNotes(userId: String): Flow<List<StudyNote>> {
    val colRef = studyNotesCol(userId)
    return colRef
      .snapshots()
      .map { snapshot ->
        snapshot
          .toObjects(
            StudyNote::class.java,
            DocumentSnapshot.ServerTimestampBehavior.ESTIMATE,
          )
          .sortedByDescending { it.updatedAt ?: it.createdAt ?: Timestamp(0, 0) }
      }
      .catch { error ->
        logFlowErrorIfSignedIn(error, userId, OperationType.LIST, colRef.path)
        throw error
      }
  }

  suspend fun createStudyNote(
    userId: String,
    lessonId: String,
    subjectId: String,
    title: String,
    content: String,
    isBookmarked: Boolean,
  ): Result<String> {
    val noteId = "note_${UUID.randomUUID().toString().replace("-", "").take(20)}"
    val docRef = studyNotesCol(userId).document(noteId)
    return try {
      val safeTitle = title.trim().ifBlank { "Revision Note" }.take(120)
      val safeContent = content.trim().ifBlank { "Key concept" }.take(2000)
      docRef
        .set(
          mapOf(
            "userId" to userId,
            "lessonId" to lessonId.take(128),
            "subjectId" to subjectId.take(64),
            "title" to safeTitle,
            "content" to safeContent,
            "isBookmarked" to isBookmarked,
            "createdAt" to FieldValue.serverTimestamp(),
            "updatedAt" to FieldValue.serverTimestamp(),
          )
        )
        .await()
      Result.success(noteId)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.CREATE, docRef.path)
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
    val docRef = studyNotesCol(userId).document(noteId)
    return try {
      val safeTitle = title.trim().ifBlank { "Revision Note" }.take(120)
      val safeContent = content.trim().ifBlank { "Key concept" }.take(2000)
      docRef
        .update(
          mapOf(
            "title" to safeTitle,
            "content" to safeContent,
            "isBookmarked" to isBookmarked,
            "updatedAt" to FieldValue.serverTimestamp(),
          )
        )
        .await()
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.UPDATE, docRef.path)
      Result.failure(e)
    }
  }

  suspend fun deleteStudyNote(userId: String, noteId: String): Result<Unit> {
    val docRef = studyNotesCol(userId).document(noteId)
    return try {
      docRef.delete().await()
      Result.success(Unit)
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.DELETE, docRef.path)
      Result.failure(e)
    }
  }

  private fun logFlowErrorIfSignedIn(
    error: Throwable,
    userId: String,
    operationType: OperationType,
    path: String,
  ) {
    val firestoreError =
      generateSequence(error) { it.cause }
        .filterIsInstance<FirebaseFirestoreException>()
        .firstOrNull()
    if (firestoreError == null && error is CancellationException) return
    val exceptionToLog = firestoreError ?: error
    if (currentUidOrNull() == userId) {
      handleFirestoreError(exceptionToLog, operationType, path)
    }
  }
}
