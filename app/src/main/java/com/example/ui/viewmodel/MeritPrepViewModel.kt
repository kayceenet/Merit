package com.example.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.curriculum.CourseModule
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.CurriculumProgression
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.PracticeQuestion
import com.example.data.curriculum.QuestionShuffler
import com.example.data.local.FontSizeScale
import com.example.data.local.LineSpacingScale
import com.example.data.local.OfflineProgressSnapshot
import com.example.data.local.ThemeMode
import com.example.data.local.ThemePalette
import com.example.data.local.ThemePreferences
import com.example.data.model.ActiveCbtSessionEntity
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.Paper2AttemptEntity
import com.example.data.model.SpacedReviewCardEntity
import com.example.data.model.StudyNote
import com.example.data.model.UserProfile
import com.example.data.remote.GoogleUserSnapshot
import com.example.data.remote.MeritPrepCloudSync
import com.example.data.repository.MeritPrepRepository
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.roundToInt
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

  private val _signedInGoogleUser = MutableStateFlow(MeritPrepCloudSync.currentGoogleUser())
  val signedInGoogleUser: StateFlow<GoogleUserSnapshot?> = _signedInGoogleUser.asStateFlow()

  private val _isCloudSyncing = MutableStateFlow(false)
  val isCloudSyncing: StateFlow<Boolean> = _isCloudSyncing.asStateFlow()

  private var examTimerJob: Job? = null
  private val seenCbtIdsByTrack = mutableMapOf<String, MutableSet<String>>()
  private val lastCbtRoundIdsByTrack = mutableMapOf<String, Set<String>>()

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

  val dueReviewCards: StateFlow<List<SpacedReviewCardEntity>> =
    repository
      .observeDueReviewCards(currentUserId, limit = 30)
      .catch { error ->
        if (error is CancellationException) throw error
        emit(emptyList())
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = emptyList(),
      )

  val allReviewCards: StateFlow<List<SpacedReviewCardEntity>> =
    repository
      .observeAllReviewCards(currentUserId)
      .catch { error ->
        if (error is CancellationException) throw error
        emit(emptyList())
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = emptyList(),
      )

  val paper2Attempts: StateFlow<List<Paper2AttemptEntity>> =
    repository
      .observePaper2Attempts(currentUserId)
      .catch { error ->
        if (error is CancellationException) throw error
        emit(emptyList())
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = emptyList(),
      )

  val resumableCbtSession: StateFlow<ActiveCbtSessionEntity?> =
    repository
      .observeActiveCbtSession(currentUserId)
      .catch { error ->
        if (error is CancellationException) throw error
        emit(null)
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
        initialValue = null,
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

  fun postStatusMessage(message: String) {
    _statusMessage.value = message
  }

  // --- Display & Study Preferences ---

  fun toggleDarkTheme(currentlyDark: Boolean) {
    viewModelScope.launch { repository.toggleDarkTheme(currentlyDark) }
  }

  fun setThemeMode(mode: ThemeMode) {
    viewModelScope.launch { repository.setThemeMode(mode) }
  }

  fun setThemePalette(palette: ThemePalette) {
    viewModelScope.launch { repository.setThemePalette(palette) }
  }

  fun setHighContrastBoost(enabled: Boolean) {
    viewModelScope.launch { repository.setHighContrastBoost(enabled) }
  }

  fun setUseDynamicColor(enabled: Boolean) {
    viewModelScope.launch { repository.setUseDynamicColor(enabled) }
  }

  fun setFontSizeScale(scale: FontSizeScale) {
    viewModelScope.launch { repository.setFontSizeScale(scale) }
  }

  fun setLineSpacingScale(scale: LineSpacingScale) {
    viewModelScope.launch { repository.setLineSpacingScale(scale) }
  }

  fun setBoldEquations(enabled: Boolean) {
    viewModelScope.launch { repository.setBoldEquations(enabled) }
  }

  fun setCbtSecondsPerQuestion(seconds: Int) {
    viewModelScope.launch { repository.setCbtSecondsPerQuestion(seconds) }
  }

  fun setCbtAutoAdvance(enabled: Boolean) {
    viewModelScope.launch { repository.setCbtAutoAdvance(enabled) }
  }

  fun setCbtShowCalculator(enabled: Boolean) {
    viewModelScope.launch { repository.setCbtShowCalculator(enabled) }
  }

  fun setShuffleOptionsOnRetry(enabled: Boolean) {
    viewModelScope.launch { repository.setShuffleOptionsOnRetry(enabled) }
  }

  fun setMasteryPassPercent(percent: Int) {
    viewModelScope.launch { repository.setMasteryPassPercent(percent) }
  }

  fun setDailyXpTarget(xpTarget: Int) {
    viewModelScope.launch { repository.setDailyXpTarget(xpTarget) }
  }

  fun setSpacedReviewBatchSize(batchSize: Int) {
    viewModelScope.launch { repository.setSpacedReviewBatchSize(batchSize) }
  }

  // --- Spaced Repetition & Placement ---

  fun rateSpacedReviewCard(cardId: String, quality: Int) {
    viewModelScope.launch {
      val res = repository.rateSpacedReviewCard(cardId, quality)
      if (res.isSuccess) {
        repository.recordXpWithAutoStreak(currentUserId, 5L)
        val days = res.getOrNull()?.intervalDays ?: 1
        _statusMessage.value = "Scheduled next recall in $days day(s) (+5 XP)"
      }
    }
  }

  fun completePlacementCheck(subjectId: String, scorePercent: Int) {
    viewModelScope.launch {
      val trackName = CurriculumCatalog.trackById(subjectId)?.shortName ?: subjectId
      if (scorePercent >= 75) {
        repository.recordPlacementPassed(subjectId)
        repository.recordXpWithAutoStreak(currentUserId, 60L)
        _statusMessage.value =
          "SS3 Placement Passed ($scorePercent%) — foundational $trackName chapters unlocked (+60 XP)"
      } else {
        _statusMessage.value =
          "Placement scored $scorePercent%. Reach 75%+ or build step-by-step from Lesson 1."
      }
    }
  }

  fun awardGamifiedLabXp(lessonId: String, xpDelta: Int) {
    val safeXp = xpDelta.toLong().coerceIn(0L, 120L)
    if (safeXp <= 0L) return
    viewModelScope.launch {
      repository.recordCompletedLab(currentUserId, lessonId, safeXp)
      _statusMessage.value = "Lab challenge completed (+${safeXp} XP)"
    }
  }

  fun savePaper2Attempt(
    promptId: String,
    subjectId: String,
    examBoard: String,
    studentWorking: String,
    checkedIndices: List<Int>,
    earnedMarks: Int,
    maxMarks: Int,
  ) {
    viewModelScope.launch {
      val res =
        repository.savePaper2Attempt(
          userId = currentUserId,
          promptId = promptId,
          subjectId = subjectId,
          examBoard = examBoard,
          studentWorking = studentWorking,
          checkedRubricIndices = checkedIndices,
          earnedMarks = earnedMarks,
          maxMarks = maxMarks,
        )
      _statusMessage.value =
        if (res.isSuccess) "Paper 2 working & score saved ($earnedMarks/$maxMarks)"
        else "Could not save Paper 2 attempt"
    }
  }

  // --- CBT Mock Exam Engine (Single Subject, 4-Subject UTME Combo /400, Missed Drill) ---

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

  fun selectQuestionLimit(limit: Int) {
    _cbtState.update {
      if (it.stage == CbtExamStage.SETUP) it.copy(selectedQuestionLimit = limit) else it
    }
  }

  fun toggleComboElective(subjectId: String) {
    if (subjectId == "english") return
    _cbtState.update { state ->
      if (state.stage != CbtExamStage.SETUP) return@update state
      val current = state.selectedComboElectives.toMutableList()
      if (subjectId in current) {
        if (current.size > 1) current.remove(subjectId)
      } else {
        if (current.size >= 3) current.removeAt(0)
        current.add(subjectId)
      }
      state.copy(selectedComboElectives = current)
    }
  }

  fun startMockExam(
    subjectId: String? = null,
    examBoard: String? = null,
    questionLimit: Int? = null,
  ) {
    examTimerJob?.cancel()
    val targetSubject = subjectId ?: _cbtState.value.selectedSubjectId
    val targetBoard = examBoard ?: _cbtState.value.selectedExamBoard
    val targetLimit = questionLimit ?: _cbtState.value.selectedQuestionLimit
    val prefs = themePreferences.value

    _cbtState.update {
      it.copy(
        selectedSubjectId = targetSubject,
        selectedExamBoard = targetBoard,
        selectedQuestionLimit = targetLimit,
        isUtmeComboMode = false,
        isLoadingQuestions = true,
      )
    }

    viewModelScope.launch {
      val allQuestions = repository.fetchPastQuestionsForCbt(targetSubject, targetBoard)
      val limit = if (targetLimit > 0) minOf(targetLimit, allQuestions.size) else allQuestions.size
      val trackKey = "${targetSubject}_${targetBoard}"
      val seen = seenCbtIdsByTrack.getOrPut(trackKey) { mutableSetOf() }
      val lastRound = lastCbtRoundIdsByTrack[trackKey].orEmpty()
      val questions =
        if (prefs.shuffleOptionsOnRetry) {
          QuestionShuffler.prepareFreshQuestionSet(
            pool = allQuestions,
            count = limit,
            previouslySeenIds = seen,
            lastRoundIds = lastRound,
          )
        } else {
          allQuestions.take(limit)
        }
      if (questions.isEmpty()) {
        _cbtState.update { it.copy(isLoadingQuestions = false) }
        _statusMessage.value = "No past questions found for this track."
        return@launch
      }
      val pickedIds = questions.map { it.id }.toSet()
      seen.addAll(pickedIds)
      lastCbtRoundIdsByTrack[trackKey] = pickedIds
      if (seen.size >= allQuestions.size) {
        seen.clear()
        seen.addAll(pickedIds)
      }
      val totalAllowed = (questions.size * prefs.cbtSecondsPerQuestion).coerceAtLeast(120)
      _cbtState.value =
        CbtExamState(
          stage = CbtExamStage.RUNNING,
          selectedSubjectId = targetSubject,
          selectedExamBoard = targetBoard,
          selectedQuestionLimit = targetLimit,
          isUtmeComboMode = false,
          selectedComboElectives = _cbtState.value.selectedComboElectives,
          comboQuestionSubjects = List(questions.size) { targetSubject },
          comboSubjectTabs = emptyList(),
          activeComboSubjectTab = null,
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
      persistActiveCbtSession()
    }
  }

  fun startUtmeComboExam(
    electives: List<String>? = null,
    questionsPerSubject: Int = 10,
    isFullUtme180: Boolean = false,
  ) {
    examTimerJob?.cancel()
    val chosenElectives = (electives ?: _cbtState.value.selectedComboElectives).distinct().take(3)
    val comboSubjects = (listOf("english") + chosenElectives).distinct()
    val prefs = themePreferences.value

    _cbtState.update {
      it.copy(
        isUtmeComboMode = true,
        isLoadingQuestions = true,
      )
    }

    viewModelScope.launch {
      val combinedQuestions = mutableListOf<PracticeQuestion>()
      val questionSubjectIds = mutableListOf<String>()
      comboSubjects.forEach { subId ->
        val targetCount =
          if (isFullUtme180) {
            if (subId == "english") 60 else 40
          } else {
            questionsPerSubject
          }
        val jambPool = repository.fetchPastQuestionsForCbt(subId, "JAMB")
        val trackKey = "combo_${subId}"
        val seen = seenCbtIdsByTrack.getOrPut(trackKey) { mutableSetOf() }
        val lastRound = lastCbtRoundIdsByTrack[trackKey].orEmpty()
        val pickedJamb =
          QuestionShuffler.prepareFreshQuestionSet(
            pool = jambPool,
            count = minOf(targetCount, jambPool.size),
            previouslySeenIds = seen,
            lastRoundIds = lastRound,
          )
        val pickedIds = pickedJamb.map { it.id }.toMutableSet()
        val extraNeeded = (targetCount - pickedJamb.size).coerceAtLeast(0)
        val toppedUp =
          if (extraNeeded > 0) {
            val allPool =
              repository.fetchPastQuestionsForCbt(subId, "ALL").filter { it.id !in pickedIds }
            val extra =
              QuestionShuffler.prepareFreshQuestionSet(
                pool = allPool,
                count = minOf(extraNeeded, allPool.size),
                previouslySeenIds = seen,
                lastRoundIds = lastRound,
              )
            pickedJamb + extra
          } else {
            pickedJamb
          }
        seen.addAll(toppedUp.map { it.id })
        lastCbtRoundIdsByTrack[trackKey] = toppedUp.map { it.id }.toSet()
        toppedUp.forEach { q ->
          combinedQuestions += q
          questionSubjectIds += subId
        }
      }

      if (combinedQuestions.isEmpty()) {
        _cbtState.update { it.copy(isLoadingQuestions = false, isUtmeComboMode = false) }
        _statusMessage.value = "Unable to build 4-subject UTME combo right now."
        return@launch
      }

      val totalAllowed =
        if (isFullUtme180) 7200
        else (combinedQuestions.size * prefs.cbtSecondsPerQuestion).coerceAtLeast(300)
      _cbtState.value =
        CbtExamState(
          stage = CbtExamStage.RUNNING,
          selectedSubjectId = "utme_combo",
          selectedExamBoard = "JAMB",
          selectedQuestionLimit = combinedQuestions.size,
          isUtmeComboMode = true,
          selectedComboElectives = chosenElectives,
          comboQuestionSubjects = questionSubjectIds,
          comboSubjectTabs = comboSubjects,
          activeComboSubjectTab = comboSubjects.firstOrNull(),
          questions = combinedQuestions,
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
      persistActiveCbtSession()
    }
  }

  fun switchComboSubjectTab(subjectId: String) {
    _cbtState.update { state ->
      if (state.stage != CbtExamStage.RUNNING) return@update state
      val targetIndex = state.comboQuestionSubjects.indexOfFirst { it == subjectId }
      if (targetIndex in state.questions.indices) {
        state.copy(
          currentIndex = targetIndex,
          activeComboSubjectTab = subjectId,
        )
      } else state
    }
  }

  fun startMissedQuestionsDrill(reviewItems: List<QuestionReviewItem>) {
    val missedQuestions = reviewItems.filter { !it.isCorrect }.map { it.question }
    if (missedQuestions.isEmpty()) {
      _statusMessage.value = "Zero missed questions in this paper!"
      return
    }
    examTimerJob?.cancel()
    val reshuffled =
      missedQuestions.shuffled().map { q -> QuestionShuffler.shuffleOptions(q) }
    val pace = themePreferences.value.cbtSecondsPerQuestion
    val totalAllowed = (reshuffled.size * pace).coerceAtLeast(120)
    val prevSubject = _cbtState.value.resultSummary?.subjectId ?: _cbtState.value.selectedSubjectId
    _cbtState.value =
      CbtExamState(
        stage = CbtExamStage.RUNNING,
        selectedSubjectId = prevSubject,
        selectedExamBoard = "ALL",
        selectedQuestionLimit = reshuffled.size,
        isUtmeComboMode = false,
        comboQuestionSubjects = List(reshuffled.size) { prevSubject },
        comboSubjectTabs = emptyList(),
        activeComboSubjectTab = null,
        questions = reshuffled,
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
    persistActiveCbtSession()
  }

  fun goToCbtQuestion(index: Int) {
    _cbtState.update { state ->
      if (state.stage == CbtExamStage.RUNNING && index in state.questions.indices) {
        val tabSub = state.comboQuestionSubjects.getOrNull(index)
        state.copy(
          currentIndex = index,
          activeComboSubjectTab = tabSub ?: state.activeComboSubjectTab,
        )
      } else {
        state
      }
    }
    persistActiveCbtSession()
  }

  fun nextCbtQuestion() {
    _cbtState.update { state ->
      if (state.stage == CbtExamStage.RUNNING && state.currentIndex < state.questions.lastIndex) {
        val nextIdx = state.currentIndex + 1
        state.copy(
          currentIndex = nextIdx,
          activeComboSubjectTab = state.comboQuestionSubjects.getOrNull(nextIdx) ?: state.activeComboSubjectTab,
        )
      } else {
        state
      }
    }
    persistActiveCbtSession()
  }

  fun previousCbtQuestion() {
    _cbtState.update { state ->
      if (state.stage == CbtExamStage.RUNNING && state.currentIndex > 0) {
        val prevIdx = state.currentIndex - 1
        state.copy(
          currentIndex = prevIdx,
          activeComboSubjectTab = state.comboQuestionSubjects.getOrNull(prevIdx) ?: state.activeComboSubjectTab,
        )
      } else {
        state
      }
    }
    persistActiveCbtSession()
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
    persistActiveCbtSession()
    if (themePreferences.value.cbtAutoAdvance) {
      viewModelScope.launch {
        delay(220L)
        val snap = _cbtState.value
        if (snap.stage == CbtExamStage.RUNNING &&
          snap.currentIndex == questionIndex &&
          snap.currentIndex < snap.questions.lastIndex
        ) {
          nextCbtQuestion()
        }
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
    persistActiveCbtSession()
  }

  fun resumeSavedCbtExam() {
    viewModelScope.launch {
      val saved = repository.getActiveCbtSessionOnce(currentUserId) ?: return@launch
      val questionIds = decodeStringListJson(saved.questionIdsJson)
      if (questionIds.isEmpty()) {
        repository.clearActiveCbtSession(currentUserId)
        return@launch
      }
      val allById =
        CurriculumCatalog.tracks
          .flatMap { track -> repository.fetchPastQuestionsForCbt(track.id, "ALL") }
          .associateBy { it.id }
      val restoredQuestions = questionIds.mapNotNull { id -> allById[id] }
      if (restoredQuestions.isEmpty()) {
        repository.clearActiveCbtSession(currentUserId)
        _statusMessage.value = "Saved CBT paper could not be restored."
        return@launch
      }
      val selectedAnswers = decodeIntMapJson(saved.selectedAnswersJson)
      val perQuestionSeconds = decodeIntMapJson(saved.perQuestionSecondsJson)
      val flagged = decodeStringListJson(saved.flaggedQuestionsJson).mapNotNull { it.toIntOrNull() }.toSet()
      val selectedElectives = decodeStringListJson(saved.selectedComboElectivesJson)
      val comboTabs = decodeStringListJson(saved.comboSubjectTabsJson)
      val comboSubjects = decodeStringListJson(saved.comboQuestionSubjectsJson)
      val safeIdx = saved.currentIndex.coerceIn(0, restoredQuestions.lastIndex)

      examTimerJob?.cancel()
      _cbtState.value =
        CbtExamState(
          stage = CbtExamStage.RUNNING,
          selectedSubjectId = saved.selectedSubjectId,
          selectedExamBoard = saved.selectedExamBoard,
          selectedQuestionLimit = saved.selectedQuestionLimit.coerceAtLeast(restoredQuestions.size),
          isUtmeComboMode = saved.isUtmeComboMode,
          selectedComboElectives = selectedElectives.ifEmpty { comboTabs.filter { it != "english" } }.ifEmpty { _cbtState.value.selectedComboElectives },
          comboQuestionSubjects = comboSubjects,
          comboSubjectTabs = comboTabs,
          activeComboSubjectTab = saved.activeComboSubjectTab ?: comboSubjects.getOrNull(safeIdx) ?: comboTabs.firstOrNull(),
          questions = restoredQuestions,
          currentIndex = safeIdx,
          selectedAnswers = selectedAnswers,
          flaggedQuestions = flagged,
          perQuestionSeconds = perQuestionSeconds,
          remainingSeconds = saved.remainingSeconds.coerceAtLeast(30),
          elapsedSeconds = saved.elapsedSeconds.coerceAtLeast(0),
          totalAllowedSeconds = saved.totalAllowedSeconds.coerceAtLeast(120),
          isTimerPaused = false,
          isLoadingQuestions = false,
          resultSummary = null,
        )
      launchExamTimer()
      _statusMessage.value = "Resumed mock paper (${selectedAnswers.size}/${restoredQuestions.size} answered)"
    }
  }

  fun discardSavedCbtExam() {
    viewModelScope.launch { repository.clearActiveCbtSession(currentUserId) }
  }

  private fun persistActiveCbtSession() {
    val state = _cbtState.value
    if (state.stage != CbtExamStage.RUNNING || state.questions.isEmpty()) return
    val entity =
      ActiveCbtSessionEntity(
        userId = currentUserId,
        selectedSubjectId = state.selectedSubjectId,
        selectedExamBoard = state.selectedExamBoard,
        selectedQuestionLimit = state.selectedQuestionLimit,
        isUtmeComboMode = state.isUtmeComboMode,
        selectedComboElectivesJson = JSONArray(state.selectedComboElectives).toString(),
        comboQuestionSubjectsJson = JSONArray(state.comboQuestionSubjects).toString(),
        comboSubjectTabsJson = JSONArray(state.comboSubjectTabs).toString(),
        activeComboSubjectTab = state.activeComboSubjectTab,
        questionIdsJson = JSONArray(state.questions.map { it.id }).toString(),
        currentIndex = state.currentIndex,
        selectedAnswersJson =
          JSONObject().apply {
            state.selectedAnswers.forEach { (k, v) -> put(k.toString(), v) }
          }.toString(),
        flaggedQuestionsJson = JSONArray(state.flaggedQuestions.map { it.toString() }).toString(),
        perQuestionSecondsJson =
          JSONObject().apply {
            state.perQuestionSeconds.forEach { (k, v) -> put(k.toString(), v) }
          }.toString(),
        remainingSeconds = state.remainingSeconds,
        elapsedSeconds = state.elapsedSeconds,
        totalAllowedSeconds = state.totalAllowedSeconds,
        updatedAtMillis = System.currentTimeMillis(),
      )
    viewModelScope.launch { repository.saveActiveCbtSession(entity) }
  }

  private fun decodeStringListJson(raw: String): List<String> =
    runCatching {
      val arr = JSONArray(raw)
      List(arr.length()) { i -> arr.optString(i, "") }.filter { it.isNotBlank() }
    }.getOrDefault(emptyList())

  private fun decodeIntMapJson(raw: String): Map<Int, Int> =
    runCatching {
      val obj = JSONObject(raw)
      buildMap {
        val keys = obj.keys()
        while (keys.hasNext()) {
          val k = keys.next()
          val ki = k.toIntOrNull()
          if (ki != null) put(ki, obj.getInt(k))
        }
      }
    }.getOrDefault(emptyMap())

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
          subjectId = state.comboQuestionSubjects.getOrNull(idx) ?: state.selectedSubjectId,
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

    val comboBreakdowns =
      if (state.isUtmeComboMode && state.comboSubjectTabs.isNotEmpty()) {
        state.comboSubjectTabs.map { subId ->
          val subItems = reviewItems.filter { it.subjectId == subId }
          val subTotal = subItems.size.coerceAtLeast(1)
          val subCorrect = subItems.count { it.isCorrect }
          val scaled = ((subCorrect.toDouble() / subTotal.toDouble()) * 100.0).roundToInt().coerceIn(0, 100)
          ComboSubjectBreakdown(
            subjectId = subId,
            subjectName = CurriculumCatalog.trackById(subId)?.shortName ?: subId,
            correctCount = subCorrect,
            totalQuestions = subItems.size,
            scaledOutOf100 = scaled,
          )
        }
      } else emptyList()

    val utmeAggregate =
      if (comboBreakdowns.isNotEmpty()) comboBreakdowns.sumOf { it.scaledOutOf100 }.coerceIn(0, 400)
      else null

    val trackTitle =
      if (state.isUtmeComboMode) {
        "UTME 4-Subject Combo (${utmeAggregate ?: 0}/400)"
      } else {
        CurriculumCatalog.trackById(state.selectedSubjectId)?.shortName ?: state.selectedSubjectId
      }

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
        isUtmeCombo = state.isUtmeComboMode,
        utmeAggregateOutOf400 = utmeAggregate,
        comboBreakdowns = comboBreakdowns,
      )

    _cbtState.update {
      it.copy(
        stage = CbtExamStage.SUMMARY,
        isTimerPaused = false,
        resultSummary = summary,
      )
    }

    // Clear saved in-progress mock & auto-enqueue missed CBT questions into SM-2 Spaced Repetition Deck
    viewModelScope.launch {
      repository.clearActiveCbtSession(currentUserId)
      reviewItems.filter { !it.isCorrect }.take(12).forEach { item ->
        repository.seedReviewCardFromQuestion(
          userId = currentUserId,
          subjectId = item.subjectId.ifBlank { state.selectedSubjectId },
          lessonId = "",
          question = item.question,
          dueImmediately = true,
        )
      }
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
    viewModelScope.launch { repository.clearActiveCbtSession(currentUserId) }
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
    if (state.isUtmeComboMode) {
      val wasFull180 = state.selectedQuestionLimit >= 180 || state.totalAllowedSeconds == 7200
      val perSub = if (wasFull180) 40 else (state.selectedQuestionLimit / 4).coerceAtLeast(5)
      startUtmeComboExam(state.selectedComboElectives, questionsPerSubject = perSub, isFullUtme180 = wasFull180)
    } else {
      startMockExam(state.selectedSubjectId, state.selectedExamBoard)
    }
  }

  // --- Learning Modules & Progress ---

  fun markPillarCompleted(
    lesson: Lesson,
    pillar: LessonPillar,
    existingProgress: LessonProgress?,
    exerciseScore: Long? = null,
  ) {
    viewModelScope.launch {
      val passThreshold = themePreferences.value.masteryPassPercent.toLong()
      val currentSteps = existingProgress?.completedSteps ?: emptyList()
      val passedExercise =
        if (pillar == LessonPillar.EXERCISE) {
          (exerciseScore ?: 0L) >= passThreshold
        } else {
          true
        }
      val updatedSteps =
        if (passedExercise) {
          (currentSteps + pillar.key).distinct()
        } else {
          currentSteps.filterNot { it == LessonPillar.EXERCISE.key }.distinct()
        }
      val previousScore = existingProgress?.exerciseScore ?: 0L
      val score =
        if (exerciseScore != null) maxOf(previousScore, exerciseScore) else previousScore
      val allPillarsCompleted =
        LessonPillar.entries.all { required -> required.key in updatedSteps }
      val completedNow =
        (existingProgress?.isCompleted == true) ||
          (allPillarsCompleted && score >= passThreshold)

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
        if (completedNow) {
          repository.seedReviewCardsFromLesson(currentUserId, lesson)
        }
        val xpBonus =
          when {
            pillar == LessonPillar.EXERCISE && passedExercise -> 40L
            pillar == LessonPillar.EXERCISE -> 10L
            else -> 15L
          }
        repository.recordXpWithAutoStreak(currentUserId, xpBonus)
        _statusMessage.value =
          when {
            pillar == LessonPillar.EXERCISE && passedExercise ->
              "Mastered (${score}%)! Next lesson unlocked (+${xpBonus} XP)"
            pillar == LessonPillar.EXERCISE ->
              "Scored ${exerciseScore ?: 0}% — reach ${passThreshold}%+ to unlock the next lesson"
            else -> "Saved ${pillar.shortLabel} (+${xpBonus} XP)"
          }
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
        val xp = 30L + (correctCount * 10L)
        repository.recordXpWithAutoStreak(currentUserId, xp)
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

  // --- Portable JSON Backup & Optional Google Cloud Sync ---

  suspend fun exportJsonBackup(): Result<String> = repository.exportBackupJson(currentUserId)

  fun importJsonBackup(rawJson: String) {
    viewModelScope.launch {
      val res = repository.importBackupJson(currentUserId, rawJson)
      _statusMessage.value =
        if (res.isSuccess) {
          "Restored ${res.getOrDefault(0)} study records from backup"
        } else {
          "Backup restore failed: ${res.exceptionOrNull()?.message ?: "Invalid file"}"
        }
    }
  }

  fun signInAndSyncWithGoogle(activityContext: Context) {
    if (_isCloudSyncing.value) return
    viewModelScope.launch {
      _isCloudSyncing.value = true
      val existing = MeritPrepCloudSync.currentGoogleUser()
      val userRes =
        if (existing != null) Result.success(existing)
        else MeritPrepCloudSync.signInWithGoogle(activityContext)
      if (userRes.isSuccess) {
        val account = userRes.getOrNull()
        _signedInGoogleUser.value = account
        val syncRes = MeritPrepCloudSync.syncNow(activityContext, repository, currentUserId)
        _statusMessage.value =
          if (syncRes.isSuccess) {
            val s = syncRes.getOrNull()
            "Cloud synced (${s?.syncedProgressCount ?: 0} lessons, ${s?.syncedAttemptsCount ?: 0} mocks)"
          } else {
            "Signed in as ${account?.displayName}; cloud sync paused (${syncRes.exceptionOrNull()?.message ?: "offline"})"
          }
      } else {
        _statusMessage.value =
          userRes.exceptionOrNull()?.message ?: "Google Sign-In was canceled"
      }
      _isCloudSyncing.value = false
    }
  }

  fun signOutGoogle(context: Context) {
    viewModelScope.launch {
      MeritPrepCloudSync.signOut(context)
      _signedInGoogleUser.value = null
      _statusMessage.value = "Signed out of Google Cloud Sync (local progress kept)"
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
