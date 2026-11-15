package com.example.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.MeritPrepDatabase
import com.example.data.model.UserProfile
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class MeritPrepRepositoryTest {

  private lateinit var db: MeritPrepDatabase
  private lateinit var repository: MeritPrepRepository

  @Before
  fun setup() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room.inMemoryDatabaseBuilder(context, MeritPrepDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    repository = MeritPrepRepository(db.meritPrepDao())
  }

  @After
  fun tearDown() {
    db.close()
  }

  @Test
  fun `ensureProfile and updateGoal persist locally`() = runTest {
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
  fun `lesson progress exam attempts and study notes persist locally`() = runTest {
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

    val noteId = repository.createStudyNote(
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
  }
}
