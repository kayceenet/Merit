package com.example.data.repository

import com.example.base.FirestoreEmulatorTestBase
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.UUID

class MeritPrepRepositoryRuleTest : FirestoreEmulatorTestBase() {

  @Test
  fun profileAndLessonProgress_authenticatedOwner_succeedsAndEmits() = runBlocking {
    val uid = signInTestUser(ALICE_EMAIL)
    val repo = MeritPrepRepository(firestore)

    val profileResult = withTimeout(TIMEOUT_MS) { repo.ensureProfile(uid, "Adaeze Okafor") }
    assertTrue(profileResult.isSuccess)

    val goalResult = withTimeout(TIMEOUT_MS) { repo.updateGoal(uid, "Adaeze Okafor", "JAMB", 350L) }
    assertTrue(goalResult.isSuccess)

    val observedProfile =
      withTimeout(FLOW_TIMEOUT_MS) {
        repo.observeProfile(uid).first { it != null && it.targetScore == 350L }
      }
    assertNotNull(observedProfile)
    assertEquals("JAMB", observedProfile!!.targetExam)

    val lessonId = "phys_proj_${UUID.randomUUID().toString().take(8)}"
    val progressResult =
      withTimeout(TIMEOUT_MS) {
        repo.saveLessonProgress(
          userId = uid,
          lessonId = lessonId,
          subjectId = "physics",
          moduleId = "phys_m1",
          completedSteps = listOf("concept", "examples", "lab"),
          exerciseScore = 90L,
          isCompleted = true,
        )
      }
    assertTrue(progressResult.isSuccess)

    val observedProgress =
      withTimeout(FLOW_TIMEOUT_MS) {
        repo.observeLessonProgress(uid).first { list -> list.any { it.lessonId == lessonId } }
      }
    assertEquals(90L, observedProgress.first { it.lessonId == lessonId }.exerciseScore)
  }

  @Test
  fun examAttemptAndStudyNotes_authenticatedOwner_crudWorks() = runBlocking {
    val uid = signInTestUser(ALICE_EMAIL)
    val repo = MeritPrepRepository(firestore)

    val attemptResult =
      withTimeout(TIMEOUT_MS) {
        repo.recordExamAttempt(
          userId = uid,
          examType = "WAEC",
          subjectId = "chemistry",
          subjectTitle = "Chemistry",
          correctCount = 9L,
          totalQuestions = 10L,
          durationSeconds = 240L,
          weakTopics = listOf("Titration Curves"),
        )
      }
    assertTrue(attemptResult.isSuccess)
    val attemptId = attemptResult.getOrThrow()

    val attempts =
      withTimeout(FLOW_TIMEOUT_MS) {
        repo.observeExamAttempts(uid).first { list -> list.any { it.id == attemptId } }
      }
    assertEquals(90L, attempts.first { it.id == attemptId }.percentage)

    val noteResult =
      withTimeout(TIMEOUT_MS) {
        repo.createStudyNote(
          userId = uid,
          lessonId = "chem_acid_01",
          subjectId = "chemistry",
          title = "Henderson-Hasselbalch",
          content = "pH = pKa + log([A-]/[HA])",
          isBookmarked = true,
        )
      }
    assertTrue(noteResult.isSuccess)
    val noteId = noteResult.getOrThrow()

    val updateNote =
      withTimeout(TIMEOUT_MS) {
        repo.updateStudyNote(uid, noteId, "Buffer Equation", "pH = pKa + log10(base/acid)", false)
      }
    assertTrue(updateNote.isSuccess)

    val deleteNote = withTimeout(TIMEOUT_MS) { repo.deleteStudyNote(uid, noteId) }
    assertTrue(deleteNote.isSuccess)
  }

  @Test
  fun crossUserAccess_bobCannotReadOrWriteAliceCollections() = runBlocking {
    val aliceUid = signInTestUser(ALICE_EMAIL)
    val aliceRepo = MeritPrepRepository(firestore)
    withTimeout(TIMEOUT_MS) { aliceRepo.ensureProfile(aliceUid, "Alice") }

    signInTestUser(BOB_EMAIL)
    val bobRepo = MeritPrepRepository(firestore)

    var crossUserError: Throwable? = null
    try {
      withTimeout(FLOW_TIMEOUT_MS) { bobRepo.observeProfile(aliceUid).first() }
    } catch (e: Exception) {
      crossUserError = e
    }
    val firestoreEx = crossUserError?.unwrapFirestoreException()
    assertNotNull("Expected FirebaseFirestoreException for cross-user read", firestoreEx)
    assertEquals(FirebaseFirestoreException.Code.PERMISSION_DENIED, firestoreEx!!.code)
  }

  @Test
  fun unauthenticatedCaller_failsWithPermissionDenied() = runBlocking {
    auth.signOut()
    val repo = MeritPrepRepository(firestore)

    var unauthError: Throwable? = null
    try {
      withTimeout(FLOW_TIMEOUT_MS) { repo.observeStudyNotes("some_user_123").first() }
    } catch (e: Exception) {
      unauthError = e
    }
    val firestoreEx = unauthError?.unwrapFirestoreException()
    assertNotNull("Expected FirebaseFirestoreException when signed out", firestoreEx)
    assertEquals(FirebaseFirestoreException.Code.PERMISSION_DENIED, firestoreEx!!.code)
  }

  private companion object {
    const val ALICE_EMAIL = "alice_merit@test.com"
    const val BOB_EMAIL = "bob_merit@test.com"
    const val TIMEOUT_MS = 5000L
    const val FLOW_TIMEOUT_MS = 3000L
  }
}
