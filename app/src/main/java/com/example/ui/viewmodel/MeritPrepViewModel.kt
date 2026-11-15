package com.example.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.StudyNote
import com.example.data.model.UserProfile
import com.example.data.repository.MeritPrepRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface UiState<out T> {
  data object Loading : UiState<Nothing>
  data class Success<T>(val data: T) : UiState<T>
  data class Error(val message: String) : UiState<Nothing>
}

class MeritPrepViewModel(
  private val repository: MeritPrepRepository,
  private val currentUserId: String = UserProfile.DEFAULT_PROFILE_ID,
  private val initialDisplayName: String = "Scholar",
) : ViewModel() {

  private val _statusMessage = MutableStateFlow<String?>(null)
  val statusMessage: StateFlow<String?> = _statusMessage.asStateFlow()

  init {
    viewModelScope.launch {
      repository.ensureProfile(currentUserId, initialDisplayName)
    }
  }

  val profileState: StateFlow<UiState<UserProfile?>> =
    repository
      .observeProfile(currentUserId)
      .map<UserProfile?, UiState<UserProfile?>> { UiState.Success(it) }
      .catch { error ->
        if (error is CancellationException) throw error
        Log.w(TAG, "Error observing profile", error)
        emit(UiState.Error(error.message ?: "Unable to load profile"))
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = UiState.Loading,
      )

  val progressState: StateFlow<UiState<List<LessonProgress>>> =
    repository
      .observeLessonProgress(currentUserId)
      .map<List<LessonProgress>, UiState<List<LessonProgress>>> { UiState.Success(it) }
      .catch { error ->
        if (error is CancellationException) throw error
        Log.w(TAG, "Error observing progress", error)
        emit(UiState.Error(error.message ?: "Unable to load lesson progress"))
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = UiState.Loading,
      )

  val attemptsState: StateFlow<UiState<List<ExamAttempt>>> =
    repository
      .observeExamAttempts(currentUserId)
      .map<List<ExamAttempt>, UiState<List<ExamAttempt>>> { UiState.Success(it) }
      .catch { error ->
        if (error is CancellationException) throw error
        Log.w(TAG, "Error observing exam attempts", error)
        emit(UiState.Error(error.message ?: "Unable to load exam analytics"))
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = UiState.Loading,
      )

  val notesState: StateFlow<UiState<List<StudyNote>>> =
    repository
      .observeStudyNotes(currentUserId)
      .map<List<StudyNote>, UiState<List<StudyNote>>> { UiState.Success(it) }
      .catch { error ->
        if (error is CancellationException) throw error
        Log.w(TAG, "Error observing study notes", error)
        emit(UiState.Error(error.message ?: "Unable to load study notes"))
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = UiState.Loading,
      )

  fun clearStatusMessage() {
    _statusMessage.value = null
  }

  fun markPillarCompleted(
    lesson: Lesson,
    pillar: LessonPillar,
    existingProgress: LessonProgress?,
    exerciseScore: Long? = null,
  ) {
    viewModelScope.launch {
      val currentSteps = existingProgress?.completedSteps ?: emptyList()
      val updatedSteps = (currentSteps + pillar.key).distinct()
      val score = exerciseScore ?: existingProgress?.exerciseScore ?: 0L
      val completedNow = updatedSteps.size >= LessonPillar.entries.size || score >= 60L
      val res =
        repository.saveLessonProgress(
          userId = currentUserId,
          lessonId = lesson.id,
          subjectId = lesson.subjectId,
          moduleId = lesson.moduleId,
          completedSteps = updatedSteps,
          exerciseScore = score,
          isCompleted = completedNow,
        )
      if (res.isSuccess) {
        val currentStreak =
          (profileState.value as? UiState.Success)?.data?.streakDays?.coerceAtLeast(1L) ?: 1L
        val xpBonus = if (pillar == LessonPillar.EXERCISE) 40L else 15L
        repository.awardXp(currentUserId, xpBonus, currentStreak)
        _statusMessage.value = "Saved ${pillar.shortLabel} progress (+${xpBonus} XP)"
      } else {
        _statusMessage.value = "Could not sync lesson progress."
      }
    }
  }

  fun recordMockExam(
    examType: String,
    subjectId: String,
    subjectTitle: String,
    correctCount: Long,
    totalQuestions: Long,
    durationSeconds: Long,
    weakTopics: List<String>,
  ) {
    viewModelScope.launch {
      val res =
        repository.recordExamAttempt(
          userId = currentUserId,
          examType = examType,
          subjectId = subjectId,
          subjectTitle = subjectTitle,
          correctCount = correctCount,
          totalQuestions = totalQuestions,
          durationSeconds = durationSeconds,
          weakTopics = weakTopics,
        )
      if (res.isSuccess) {
        val currentStreak =
          (profileState.value as? UiState.Success)?.data?.streakDays?.coerceAtLeast(1L) ?: 1L
        val xp = 30L + (correctCount * 10L)
        repository.awardXp(currentUserId, xp, currentStreak)
        _statusMessage.value = "Exam attempt saved (+${xp} XP)"
      } else {
        _statusMessage.value = "Unable to save exam score."
      }
    }
  }

  fun updateGoal(displayName: String, targetExam: String, targetScore: Long) {
    viewModelScope.launch {
      val res = repository.updateGoal(currentUserId, displayName, targetExam, targetScore)
      _statusMessage.value =
        if (res.isSuccess) "Academic goals updated" else "Failed to update goals"
    }
  }

  fun createStudyNote(
    lessonId: String,
    subjectId: String,
    title: String,
    content: String,
    isBookmarked: Boolean,
  ) {
    viewModelScope.launch {
      val res =
        repository.createStudyNote(
          userId = currentUserId,
          lessonId = lessonId,
          subjectId = subjectId,
          title = title,
          content = content,
          isBookmarked = isBookmarked,
        )
      _statusMessage.value =
        if (res.isSuccess) "Saved to Formula Notebook" else "Failed to save note"
    }
  }

  fun toggleBookmark(note: StudyNote) {
    viewModelScope.launch {
      repository.updateStudyNote(
        userId = currentUserId,
        noteId = note.id,
        title = note.title,
        content = note.content,
        isBookmarked = !note.isBookmarked,
      )
    }
  }

  fun updateStudyNote(noteId: String, title: String, content: String, isBookmarked: Boolean) {
    viewModelScope.launch {
      val res = repository.updateStudyNote(currentUserId, noteId, title, content, isBookmarked)
      _statusMessage.value = if (res.isSuccess) "Note updated" else "Could not update note"
    }
  }

  fun deleteStudyNote(noteId: String) {
    viewModelScope.launch {
      val res = repository.deleteStudyNote(currentUserId, noteId)
      _statusMessage.value = if (res.isSuccess) "Note deleted" else "Could not delete note"
    }
  }

  private companion object {
    const val TAG = "MeritPrepVM"
    const val STOP_TIMEOUT_MS = 5000L
  }
}
