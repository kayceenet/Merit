package com.example.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.curriculum.CourseModule
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.local.OfflineProgressSnapshot
import com.example.data.local.ThemeMode
import com.example.data.local.ThemePreferences
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.StudyNote
import com.example.data.model.UserProfile
import com.example.data.repository.MeritPrepRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
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

  private val _cbtState = MutableStateFlow(CbtExamState())
  val cbtState: StateFlow<CbtExamState> = _cbtState.asStateFlow()

  private var examTimerJob: Job? = null

  init {
    viewModelScope.launch {
      repository.seedCurriculumIfNeeded()
      repository.ensureProfile(currentUserId, initialDisplayName)
    }
  }

  val themePreferences: StateFlow<ThemePreferences> =
    repository.themePreferences.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
      initialValue = ThemePreferences(),
    )

  val offlineSnapshot: StateFlow<OfflineProgressSnapshot> =
    repository.offlineSnapshot.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
      initialValue = OfflineProgressSnapshot(),
    )

  val structuredModules: StateFlow<List<CourseModule>> =
    repository
      .observeStructuredModules()
      .catch { error ->
        if (error is CancellationException) throw error
        emit(CurriculumCatalog.tracks.flatMap { it.modules })
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = CurriculumCatalog.tracks.flatMap { it.modules },
      )

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

  fun toggleDarkTheme(currentlyDark: Boolean) {
    viewModelScope.launch { repository.toggleDarkTheme(currentlyDark) }
  }

  fun setThemeMode(mode: ThemeMode) {
    viewModelScope.launch { repository.setThemeMode(mode) }
  }

  fun setUseDynamicColor(enabled: Boolean) {
    viewModelScope.launch { repository.setUseDynamicColor(enabled) }
  }

  // --- CBT Mock Exam Engine (Room-backed Questions + State-Managed Timer) ---

  fun selectExamSubject(subjectId: String) {
    _cbtState.update {
      if (it.stage == CbtExamStage.SETUP) it.copy(selectedSubjectId = subjectId) else it
    }
  }

  fun selectExamBoard(board: String) {
    _cbtState.update {
      if (it.stage == CbtExamStage.SETUP) it.copy(selectedExamBoard = board) else it
    }
  }

  fun startMockExam(subjectId: String? = null, examBoard: String? = null) {
    examTimerJob?.cancel()
    val targetSubject = subjectId ?: _cbtState.value.selectedSubjectId
    val targetBoard = examBoard ?: _cbtState.value.selectedExamBoard

    _cbtState.update {
      it.copy(
        selectedSubjectId = targetSubject,
        selectedExamBoard = targetBoard,
        isLoadingQuestions = true,
      )
    }

    viewModelScope.launch {
      val questions = repository.fetchPastQuestionsForCbt(targetSubject, targetBoard)
      if (questions.isEmpty()) {
        _cbtState.update { it.copy(isLoadingQuestions = false) }
        _statusMessage.value = "No past questions found for this track."
        return@launch
      }
      val totalAllowed = (questions.size * 75).coerceAtLeast(180)
      _cbtState.value =
        CbtExamState(
          stage = CbtExamStage.RUNNING,
          selectedSubjectId = targetSubject,
          selectedExamBoard = targetBoard,
          questions = questions,
          currentIndex = 0,
          selectedAnswers = emptyMap(),
          flaggedQuestions = emptySet(),
          perQuestionSeconds = emptyMap(),
          remainingSeconds = totalAllowed,
          elapsedSeconds = 0,
          totalAllowedSeconds = totalAllowed,
          isTimerPaused = false,
          isLoadingQuestions = false,
          resultSummary = null,
        )
      launchExamTimer()
    }
  }

  private fun launchExamTimer() {
    examTimerJob?.cancel()
    examTimerJob =
      viewModelScope.launch {
        while (isActive) {
          delay(1000L)
          val snapshot = _cbtState.value
          if (snapshot.stage != CbtExamStage.RUNNING) break
          if (snapshot.isTimerPaused) continue

          val nextRemaining = (snapshot.remainingSeconds - 1).coerceAtLeast(0)
          val nextElapsed = snapshot.elapsedSeconds + 1
          val currentQ = snapshot.currentIndex
          val updatedPerQ = snapshot.perQuestionSeconds.toMutableMap()
          updatedPerQ[currentQ] = (updatedPerQ[currentQ] ?: 0) + 1

          _cbtState.update {
            it.copy(
              remainingSeconds = nextRemaining,
              elapsedSeconds = nextElapsed,
              perQuestionSeconds = updatedPerQ,
            )
          }

          if (nextRemaining <= 0) {
            submitCbtExam()
            break
          }
        }
      }
  }

  fun togglePauseCbtTimer() {
    _cbtState.update { state ->
      if (state.stage == CbtExamStage.RUNNING) state.copy(isTimerPaused = !state.isTimerPaused)
      else state
    }
  }

  fun goToCbtQuestion(index: Int) {
    _cbtState.update { state ->
      if (state.stage == CbtExamStage.RUNNING && index in state.questions.indices) {
        state.copy(currentIndex = index)
      } else {
        state
      }
    }
  }

  fun nextCbtQuestion() {
    _cbtState.update { state ->
      if (state.stage == CbtExamStage.RUNNING && state.currentIndex < state.questions.lastIndex) {
        state.copy(currentIndex = state.currentIndex + 1)
      } else {
        state
      }
    }
  }

  fun previousCbtQuestion() {
    _cbtState.update { state ->
      if (state.stage == CbtExamStage.RUNNING && state.currentIndex > 0) {
        state.copy(currentIndex = state.currentIndex - 1)
      } else {
        state
      }
    }
  }

  fun selectCbtAnswer(questionIndex: Int, optionIndex: Int) {
    _cbtState.update { state ->
      if (state.stage != CbtExamStage.RUNNING || questionIndex !in state.questions.indices) {
        state
      } else {
        val nextAnswers = state.selectedAnswers.toMutableMap()
        nextAnswers[questionIndex] = optionIndex
        state.copy(selectedAnswers = nextAnswers)
      }
    }
  }

  fun toggleFlagCbtQuestion(questionIndex: Int) {
    _cbtState.update { state ->
      if (state.stage != CbtExamStage.RUNNING) return@update state
      val updated = state.flaggedQuestions.toMutableSet()
      if (questionIndex in updated) updated.remove(questionIndex) else updated.add(questionIndex)
      state.copy(flaggedQuestions = updated)
    }
  }

  fun submitCbtExam() {
    val state = _cbtState.value
    if (state.stage != CbtExamStage.RUNNING) return
    examTimerJob?.cancel()

    val questions = state.questions
    val total = questions.size.coerceAtLeast(1)
    val reviewItems =
      questions.mapIndexed { idx, q ->
        QuestionReviewItem(
          questionIndex = idx,
          question = q,
          selectedOptionIndex = state.selectedAnswers[idx],
          timeSpentSeconds = state.perQuestionSeconds[idx] ?: 0,
        )
      }

    val correctCount = reviewItems.count { it.isCorrect }
    val unansweredCount = reviewItems.count { it.isUnanswered }
    val wrongCount = total - correctCount - unansweredCount
    val percentage = ((correctCount * 100) / total).coerceIn(0, 100)
    val timeTaken = state.elapsedSeconds.coerceAtLeast(1)
    val weakTopics =
      reviewItems
        .filter { !it.isCorrect }
        .map { it.question.topic }
        .distinct()
    val xpEarned = 30L + (correctCount * 10L)
    val trackTitle =
      CurriculumCatalog.trackById(state.selectedSubjectId)?.shortName ?: state.selectedSubjectId

    val summary =
      ExamResultSummary(
        examBoard = state.selectedExamBoard,
        subjectId = state.selectedSubjectId,
        subjectTitle = trackTitle,
        correctCount = correctCount,
        wrongCount = wrongCount,
        unansweredCount = unansweredCount,
        totalQuestions = questions.size,
        percentage = percentage,
        timeTakenSeconds = timeTaken,
        totalAllowedSeconds = state.totalAllowedSeconds,
        xpEarned = xpEarned,
        weakTopics = weakTopics,
        reviewItems = reviewItems,
      )

    _cbtState.update {
      it.copy(
        stage = CbtExamStage.SUMMARY,
        isTimerPaused = false,
        resultSummary = summary,
      )
    }

    recordMockExam(
      examType = state.selectedExamBoard,
      subjectId = state.selectedSubjectId,
      subjectTitle = trackTitle,
      correctCount = correctCount.toLong(),
      totalQuestions = total.toLong(),
      durationSeconds = timeTaken.toLong(),
      weakTopics = weakTopics,
    )
  }

  fun returnToCbtSetup() {
    examTimerJob?.cancel()
    _cbtState.update {
      it.copy(
        stage = CbtExamStage.SETUP,
        isTimerPaused = false,
        resultSummary = null,
      )
    }
  }

  fun retakeLastMock() {
    val state = _cbtState.value
    startMockExam(state.selectedSubjectId, state.selectedExamBoard)
  }

  // --- Learning Modules & Progress ---

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

  override fun onCleared() {
    examTimerJob?.cancel()
    super.onCleared()
  }

  private companion object {
    const val TAG = "MeritPrepVM"
    const val STOP_TIMEOUT_MS = 5000L
  }
}
