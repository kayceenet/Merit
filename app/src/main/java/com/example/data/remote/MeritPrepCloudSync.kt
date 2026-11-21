package com.example.data.remote

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.R
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.StudyNote
import com.example.data.model.UserProfile
import com.example.data.repository.MeritPrepRepository
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.Date
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

data class GoogleUserSnapshot(
  val uid: String,
  val displayName: String,
  val email: String?,
)

data class CloudSyncResultSummary(
  val syncedProgressCount: Int,
  val syncedAttemptsCount: Int,
  val syncedNotesCount: Int,
)

object MeritPrepCloudSync {
  private const val TAG = "MeritPrepCloudSync"

  fun currentGoogleUser(): GoogleUserSnapshot? {
    val user = runCatching { Firebase.auth.currentUser }.getOrNull() ?: return null
    return GoogleUserSnapshot(
      uid = user.uid,
      displayName = user.displayName?.takeIf { it.isNotBlank() } ?: "Scholar",
      email = user.email,
    )
  }

  suspend fun signInWithGoogle(activityContext: Context): Result<GoogleUserSnapshot> {
    return try {
      val webClientId = activityContext.getString(R.string.default_web_client_id)
      val googleOption =
        GetSignInWithGoogleOption.Builder(webClientId)
          .build()
      val request = GetCredentialRequest.Builder().addCredentialOption(googleOption).build()
      val credentialManager = CredentialManager.create(activityContext)
      val response = credentialManager.getCredential(activityContext, request)
      val credential = response.credential
      if (credential is CustomCredential &&
        credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
      ) {
        val googleCred = GoogleIdTokenCredential.createFrom(credential.data)
        val firebaseCred = GoogleAuthProvider.getCredential(googleCred.idToken, null)
        val authResult = Firebase.auth.signInWithCredential(firebaseCred).await()
        val fbUser = authResult.user
          ?: return Result.failure(IllegalStateException("Google authentication completed without user handle"))
        Result.success(
          GoogleUserSnapshot(
            uid = fbUser.uid,
            displayName = fbUser.displayName?.takeIf { it.isNotBlank() } ?: "Scholar",
            email = fbUser.email,
          )
        )
      } else {
        Result.failure(IllegalStateException("Unexpected credential type received"))
      }
    } catch (e: CancellationException) {
      throw e
    } catch (e: GetCredentialException) {
      Log.w(TAG, "CredentialManager Google Sign-In canceled/failed", e)
      Result.failure(IllegalStateException(e.message ?: "Google Sign-In was canceled"))
    } catch (e: Exception) {
      Log.w(TAG, "Google Sign-In failed", e)
      Result.failure(e)
    }
  }

  suspend fun signOut(context: Context) {
    runCatching { Firebase.auth.signOut() }
    runCatching {
      CredentialManager.create(context).clearCredentialState(ClearCredentialStateRequest())
    }
  }

  suspend fun syncNow(
    context: Context,
    repository: MeritPrepRepository,
    localUserId: String,
  ): Result<CloudSyncResultSummary> {
    return try {
      val fbUser = Firebase.auth.currentUser
        ?: return Result.failure(IllegalStateException("Please sign in with Google before syncing."))
      val uid = fbUser.uid
      val db = FirebaseFirestore.getInstance(context.getString(R.string.firestore_database_id))
      val userDoc = db.collection("users").document(uid)

      // 1. Pull remote state first so multi-device progress is preserved safely
      val remoteProfileSnap = userDoc.get().await()
      val remoteProfile =
        if (remoteProfileSnap.exists()) {
          UserProfile(
            id = uid,
            userId = uid,
            displayName = remoteProfileSnap.getString("displayName") ?: "Scholar",
            targetExam = remoteProfileSnap.getString("targetExam") ?: "BOTH",
            targetScore = remoteProfileSnap.getLong("targetScore") ?: 320L,
            streakDays = remoteProfileSnap.getLong("streakDays") ?: 1L,
            totalXp = remoteProfileSnap.getLong("totalXp") ?: 0L,
            createdAt = remoteProfileSnap.getTimestamp("createdAt")?.toDate()?.time ?: System.currentTimeMillis(),
            updatedAt = remoteProfileSnap.getTimestamp("updatedAt")?.toDate()?.time ?: System.currentTimeMillis(),
          )
        } else null

      val remoteProgressSnap = userDoc.collection("lessonProgress").get().await()
      val remoteProgressTimestamps = mutableMapOf<String, Timestamp>()
      val remoteProgress =
        remoteProgressSnap.documents.mapNotNull { doc ->
          val lessonId = doc.getString("lessonId") ?: return@mapNotNull null
          doc.getTimestamp("createdAt")?.let { remoteProgressTimestamps[lessonId] = it }
          val rawSteps = doc.get("completedSteps") as? List<*>
          LessonProgress(
            id = "${localUserId}_$lessonId",
            userId = localUserId,
            lessonId = lessonId,
            subjectId = doc.getString("subjectId") ?: "",
            moduleId = doc.getString("moduleId") ?: "",
            completedSteps = rawSteps?.mapNotNull { it as? String }.orEmpty(),
            exerciseScore = doc.getLong("exerciseScore") ?: 0L,
            isCompleted = doc.getBoolean("isCompleted") ?: false,
            createdAt = doc.getTimestamp("createdAt")?.toDate()?.time ?: System.currentTimeMillis(),
            updatedAt = doc.getTimestamp("updatedAt")?.toDate()?.time ?: System.currentTimeMillis(),
          )
        }

      val remoteAttemptsSnap = userDoc.collection("examAttempts").get().await()
      val remoteAttempts =
        remoteAttemptsSnap.documents.mapNotNull { doc ->
          val rawWeak = doc.get("weakTopics") as? List<*>
          ExamAttempt(
            id = doc.id,
            userId = localUserId,
            examType = doc.getString("examType") ?: "JAMB",
            subjectId = doc.getString("subjectId") ?: "",
            subjectTitle = doc.getString("subjectTitle") ?: "",
            correctCount = doc.getLong("correctCount") ?: 0L,
            totalQuestions = (doc.getLong("totalQuestions") ?: 1L).coerceAtLeast(1L),
            percentage = doc.getLong("percentage") ?: 0L,
            durationSeconds = doc.getLong("durationSeconds") ?: 0L,
            weakTopics = rawWeak?.mapNotNull { it as? String }.orEmpty(),
            createdAt = doc.getTimestamp("createdAt")?.toDate()?.time ?: System.currentTimeMillis(),
          )
        }

      val remoteNotesSnap = userDoc.collection("studyNotes").get().await()
      val remoteNoteTimestamps = mutableMapOf<String, Timestamp>()
      val remoteNotes =
        remoteNotesSnap.documents.mapNotNull { doc ->
          doc.getTimestamp("createdAt")?.let { remoteNoteTimestamps[doc.id] = it }
          StudyNote(
            id = doc.id,
            userId = localUserId,
            lessonId = doc.getString("lessonId") ?: "",
            subjectId = doc.getString("subjectId") ?: "",
            title = doc.getString("title") ?: "Note",
            content = doc.getString("content") ?: "",
            isBookmarked = doc.getBoolean("isBookmarked") ?: false,
            createdAt = doc.getTimestamp("createdAt")?.toDate()?.time ?: System.currentTimeMillis(),
            updatedAt = doc.getTimestamp("updatedAt")?.toDate()?.time ?: System.currentTimeMillis(),
          )
        }

      repository.applyMergedCloudBundle(
        userId = localUserId,
        remoteProfile = remoteProfile,
        remoteProgress = remoteProgress,
        remoteAttempts = remoteAttempts,
        remoteNotes = remoteNotes,
      )

      // 2. Push merged local state back to Firestore adhering strictly to firestore.rules
      val mergedBundle = repository.exportUserBundle(localUserId)
      val profileToPush = mergedBundle.profile ?: UserProfile(displayName = fbUser.displayName ?: "Scholar")
      val safeTargetExam =
        when (profileToPush.targetExam.uppercase()) {
          "JAMB" -> "JAMB"
          "WAEC" -> "WAEC"
          "NECO" -> "NECO"
          "ALL" -> "ALL"
          else -> "BOTH"
        }
      val profilePayload =
        mapOf(
          "userId" to uid,
          "displayName" to profileToPush.displayName.ifBlank { "Scholar" }.take(80),
          "targetExam" to safeTargetExam,
          "targetScore" to profileToPush.targetScore.coerceIn(160L, 400L),
          "streakDays" to profileToPush.streakDays.coerceIn(0L, 3650L),
          "totalXp" to profileToPush.totalXp.coerceIn(0L, 1_000_000L),
          "createdAt" to (remoteProfileSnap.getTimestamp("createdAt") ?: FieldValue.serverTimestamp()),
          "updatedAt" to FieldValue.serverTimestamp(),
        )
      userDoc.set(profilePayload, SetOptions.merge()).await()

      mergedBundle.progress.forEach { lp ->
        if (lp.lessonId.matches(VALID_ID_REGEX) && lp.subjectId.isNotBlank() && lp.moduleId.isNotBlank()) {
          val ref = userDoc.collection("lessonProgress").document(lp.lessonId)
          val createdTs: Any = remoteProgressTimestamps[lp.lessonId] ?: FieldValue.serverTimestamp()
          val payload =
            mapOf(
              "userId" to uid,
              "lessonId" to lp.lessonId,
              "subjectId" to lp.subjectId.take(64),
              "moduleId" to lp.moduleId.take(64),
              "completedSteps" to lp.completedSteps.map { it.take(64) }.take(10),
              "exerciseScore" to lp.exerciseScore.coerceIn(0L, 100L),
              "isCompleted" to lp.isCompleted,
              "createdAt" to createdTs,
              "updatedAt" to FieldValue.serverTimestamp(),
            )
          ref.set(payload, SetOptions.merge()).await()
        }
      }

      val remoteAttemptIds = remoteAttempts.map { it.id }.toSet()
      mergedBundle.attempts.filter { it.id !in remoteAttemptIds && it.id.matches(VALID_ID_REGEX) }.forEach { att ->
        val safeBoard =
          when (att.examType.uppercase()) {
            "WAEC" -> "WAEC"
            "NECO" -> "NECO"
            "ALL" -> "ALL"
            else -> "JAMB"
          }
        val total = att.totalQuestions.coerceIn(1L, 200L)
        val correct = att.correctCount.coerceIn(0L, total)
        val payload =
          mapOf(
            "userId" to uid,
            "examType" to safeBoard,
            "subjectId" to att.subjectId.ifBlank { "general" }.take(64),
            "subjectTitle" to att.subjectTitle.ifBlank { "CBT Mock (${att.examType})" }.take(100),
            "correctCount" to correct,
            "totalQuestions" to total,
            "percentage" to ((correct * 100L) / total).coerceIn(0L, 100L),
            "durationSeconds" to att.durationSeconds.coerceIn(0L, 18000L),
            "weakTopics" to att.weakTopics.map { it.take(64) }.take(10),
            "createdAt" to FieldValue.serverTimestamp(),
          )
        userDoc.collection("examAttempts").document(att.id).set(payload).await()
      }

      mergedBundle.notes.forEach { note ->
        if (note.id.matches(VALID_ID_REGEX)) {
          val ref = userDoc.collection("studyNotes").document(note.id)
          val createdTs: Any = remoteNoteTimestamps[note.id] ?: FieldValue.serverTimestamp()
          val payload =
            mapOf(
              "userId" to uid,
              "lessonId" to note.lessonId.ifBlank { "general" }.take(128),
              "subjectId" to note.subjectId.ifBlank { "general" }.take(64),
              "title" to note.title.ifBlank { "Formula Note" }.take(120),
              "content" to note.content.ifBlank { "Saved concept" }.take(2000),
              "isBookmarked" to note.isBookmarked,
              "createdAt" to createdTs,
              "updatedAt" to FieldValue.serverTimestamp(),
            )
          ref.set(payload, SetOptions.merge()).await()
        }
      }

      Result.success(
        CloudSyncResultSummary(
          syncedProgressCount = mergedBundle.progress.size,
          syncedAttemptsCount = mergedBundle.attempts.size,
          syncedNotesCount = mergedBundle.notes.size,
        )
      )
    } catch (e: CancellationException) {
      throw e
    } catch (e: Exception) {
      Log.w(TAG, "Cloud sync failed", e)
      Result.failure(e)
    }
  }

  private val VALID_ID_REGEX = Regex("^[a-zA-Z0-9_\\-]{1,128}$")
}
