package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.CurriculumProgression
import com.example.data.curriculum.Lesson
import com.example.data.local.ThemeMode
import com.example.data.model.UserProfile
import com.example.data.repository.MeritPrepRepository
import com.example.ui.components.AcademyMenuSheet
import com.example.ui.components.DisplaySettingsDialog
import com.example.ui.components.PlacementAssessmentDialog
import com.example.ui.components.SpacedRepetitionModal
import com.example.ui.screens.AnalyticsProfileScreen
import com.example.ui.screens.CatalogScreen
import com.example.ui.screens.ExamLabMode
import com.example.ui.screens.ExamSimulatorScreen
import com.example.ui.screens.HomeDashboardScreen
import com.example.ui.screens.LessonClassroomScreen
import com.example.ui.screens.NotebookScreen
import com.example.ui.theme.MeritPrepTheme
import com.example.ui.theme.semanticColors
import com.example.ui.viewmodel.MeritPrepViewModel
import com.example.ui.viewmodel.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private enum class TopTab(
  val label: String,
  val activeIcon: ImageVector,
  val inactiveIcon: ImageVector,
) {
  HOME("Materials", Icons.Filled.Dashboard, Icons.Outlined.Dashboard),
  CATALOG("Syllabus", Icons.Filled.Folder, Icons.Outlined.Folder),
  EXAMS("Exam Lab", Icons.Filled.Quiz, Icons.Outlined.Quiz),
  NOTEBOOK("Notebook", Icons.Filled.Book, Icons.Outlined.Book),
  ANALYTICS("Profile", Icons.Filled.Analytics, Icons.Outlined.Analytics),
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MeritPrepAppRoot()
    }
  }
}

@Composable
private fun MeritPrepAppRoot(
  currentUserId: String = UserProfile.DEFAULT_PROFILE_ID,
  displayName: String = "Scholar",
) {
  val viewModel: MeritPrepViewModel =
    viewModel(
      key = currentUserId,
      factory =
        viewModelFactory {
          initializer {
            val app =
              checkNotNull(this[APPLICATION_KEY]) {
                "APPLICATION_KEY missing from CreationExtras"
              }
            MeritPrepViewModel(MeritPrepRepository(app), currentUserId, displayName)
          }
        },
    )

  val themePreferences by viewModel.themePreferences.collectAsStateWithLifecycle()
  val systemDark = isSystemInDarkTheme()
  val effectiveDarkTheme =
    when (themePreferences.themeMode) {
      ThemeMode.SYSTEM -> systemDark
      ThemeMode.LIGHT -> false
      ThemeMode.DARK -> true
    }

  MeritPrepTheme(
    darkTheme = effectiveDarkTheme,
    dynamicColor = themePreferences.useDynamicColor,
    themePalette = themePreferences.themePalette,
    highContrastBoost = themePreferences.highContrastBoost,
    fontSizeScale = themePreferences.fontSizeScale,
    lineSpacingScale = themePreferences.lineSpacingScale,
    boldEquations = themePreferences.boldEquations,
  ) {
    MeritPrepAppShell(
      viewModel = viewModel,
      isDarkTheme = effectiveDarkTheme,
    )
  }
}

@Composable
private fun MeritPrepAppShell(
  viewModel: MeritPrepViewModel,
  isDarkTheme: Boolean,
) {
  val context = LocalContext.current
  val coroutineScope = rememberCoroutineScope()

  val profileState by viewModel.profileState.collectAsStateWithLifecycle()
  val progressState by viewModel.progressState.collectAsStateWithLifecycle()
  val attemptsState by viewModel.attemptsState.collectAsStateWithLifecycle()
  val notesState by viewModel.notesState.collectAsStateWithLifecycle()
  val statusMessage by viewModel.statusMessage.collectAsStateWithLifecycle()
  val structuredModules by viewModel.structuredModules.collectAsStateWithLifecycle()
  val offlineSnapshot by viewModel.offlineSnapshot.collectAsStateWithLifecycle()
  val themePreferences by viewModel.themePreferences.collectAsStateWithLifecycle()
  val cbtState by viewModel.cbtState.collectAsStateWithLifecycle()
  val resumableCbtSession by viewModel.resumableCbtSession.collectAsStateWithLifecycle()
  val paper2Attempts by viewModel.paper2Attempts.collectAsStateWithLifecycle()
  val dueReviewCards by viewModel.dueReviewCards.collectAsStateWithLifecycle()
  val allReviewCards by viewModel.allReviewCards.collectAsStateWithLifecycle()
  val signedInGoogleUser by viewModel.signedInGoogleUser.collectAsStateWithLifecycle()
  val isCloudSyncing by viewModel.isCloudSyncing.collectAsStateWithLifecycle()

  val profile = (profileState as? UiState.Success)?.data
  val progressList = (progressState as? UiState.Success)?.data ?: emptyList()
  val attempts = (attemptsState as? UiState.Success)?.data ?: emptyList()
  val notes = (notesState as? UiState.Success)?.data ?: emptyList()

  var activeTab by remember { mutableStateOf(TopTab.HOME) }
  var selectedCatalogSubjectId by remember { mutableStateOf<String?>(null) }
  var selectedExamSubjectId by remember { mutableStateOf<String?>(null) }
  var activeLesson by remember { mutableStateOf<Lesson?>(null) }

  var showMenuSheet by remember { mutableStateOf(false) }
  var showQuickActionSheet by remember { mutableStateOf(false) }
  var showSettingsPortal by remember { mutableStateOf(false) }
  var showSpacedReviewModal by remember { mutableStateOf(false) }
  var showPlacementDialog by remember { mutableStateOf(false) }
  var placementSubjectArg by remember { mutableStateOf<String?>(null) }
  var examLabMode by remember { mutableStateOf(ExamLabMode.SINGLE_CBT) }
  var examLabModeNonce by remember { mutableIntStateOf(0) }

  val exportBackupLauncher =
    rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/json")) { uri ->
      if (uri != null) {
        coroutineScope.launch {
          val result = viewModel.exportJsonBackup()
          if (result.isSuccess) {
            val writeRes = runCatching {
              withContext(Dispatchers.IO) {
                context.contentResolver.openOutputStream(uri)?.use { out ->
                  out.write(result.getOrThrow().toByteArray(Charsets.UTF_8))
                } ?: error("Unable to open output stream")
              }
            }
            if (writeRes.isSuccess) {
              viewModel.postStatusMessage("Exported portable JSON study backup")
            } else {
              viewModel.postStatusMessage(
                "Backup export failed: ${writeRes.exceptionOrNull()?.message ?: "Cannot write file"}"
              )
            }
          } else {
            viewModel.postStatusMessage(
              "Backup export failed: ${result.exceptionOrNull()?.message ?: "Unknown error"}"
            )
          }
        }
      }
    }

  val importBackupLauncher =
    rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
      if (uri != null) {
        coroutineScope.launch {
          val readRes = runCatching {
            withContext(Dispatchers.IO) {
              context.contentResolver.openInputStream(uri)?.use { input ->
                input.bufferedReader().readText()
              } ?: error("Unable to read backup file")
            }
          }
          if (readRes.isSuccess) {
            viewModel.importJsonBackup(readRes.getOrThrow())
          } else {
            viewModel.postStatusMessage(
              "Restore failed: ${readRes.exceptionOrNull()?.message ?: "Cannot read file"}"
            )
          }
        }
      }
    }

  val snackbarHostState = remember { SnackbarHostState() }
  LaunchedEffect(statusMessage) {
    val msg = statusMessage
    if (!msg.isNullOrBlank()) {
      snackbarHostState.showSnackbar(msg)
      viewModel.clearStatusMessage()
    }
  }

  BackHandler(
    enabled =
      activeLesson == null &&
        activeTab != TopTab.HOME &&
        !showQuickActionSheet &&
        !showMenuSheet &&
        !showSettingsPortal &&
        !showSpacedReviewModal &&
        !showPlacementDialog
  ) {
    activeTab = TopTab.HOME
  }

  Box(modifier = Modifier.fillMaxSize()) {
    val openLesson = activeLesson
    if (openLesson != null) {
      val lessonProgress = progressList.find { it.lessonId == openLesson.id }
      val trackLessons =
        remember(structuredModules, openLesson.subjectId) {
          val dbLessons =
            structuredModules.filter { it.subjectId == openLesson.subjectId }.flatMap { it.lessons }
          if (dbLessons.isNotEmpty()) dbLessons
          else CurriculumCatalog.trackById(openLesson.subjectId)?.allLessons.orEmpty()
        }
      val nextLesson =
        remember(trackLessons, openLesson.id) {
          CurriculumProgression.nextLessonAfter(trackLessons, openLesson.id)
        }
      LessonClassroomScreen(
        lesson = openLesson,
        progress = lessonProgress,
        nextLesson = nextLesson,
        themePreferences = themePreferences,
        dueReviewsCount = dueReviewCards.size,
        onSetThemeMode = viewModel::setThemeMode,
        onSetThemePalette = viewModel::setThemePalette,
        onSetHighContrastBoost = viewModel::setHighContrastBoost,
        onSetUseDynamicColor = viewModel::setUseDynamicColor,
        onSetFontSizeScale = viewModel::setFontSizeScale,
        onSetLineSpacingScale = viewModel::setLineSpacingScale,
        onSetBoldEquations = viewModel::setBoldEquations,
        onOpenMenu = { showMenuSheet = true },
        onBack = { activeLesson = null },
        onMarkPillarComplete = { pillar, score ->
          viewModel.markPillarCompleted(openLesson, pillar, lessonProgress, score)
        },
        onSaveNote = { title, content ->
          viewModel.createStudyNote(openLesson.id, openLesson.subjectId, title, content, true)
        },
        onEarnLabXp = { xpDelta, _ ->
          viewModel.awardGamifiedLabXp(openLesson.id, xpDelta)
        },
        onOpenNextLesson = { upcoming -> activeLesson = upcoming },
      )
    } else {
      BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useRail = maxWidth >= 720.dp

        Scaffold(
          containerColor = MaterialTheme.colorScheme.background,
          snackbarHost = { SnackbarHost(snackbarHostState) },
          bottomBar = {
            if (!useRail) {
              StudioDockedBottomBar(
                activeTab = activeTab,
                dueReviewsCount = dueReviewCards.size,
                onSelectTab = { activeTab = it },
                onCenterActionClick = { showQuickActionSheet = true },
              )
            }
          },
        ) { innerPadding ->
          Row(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            if (useRail) {
              NavigationRail(
                modifier = Modifier.fillMaxHeight(),
                containerColor = MaterialTheme.colorScheme.surface,
              ) {
                TopTab.entries.forEach { tab ->
                  val selected = tab == activeTab
                  NavigationRailItem(
                    selected = selected,
                    onClick = { activeTab = tab },
                    icon = {
                      Icon(
                        imageVector = if (selected) tab.activeIcon else tab.inactiveIcon,
                        contentDescription = tab.label,
                      )
                    },
                    label = { Text(tab.label) },
                    colors =
                      NavigationRailItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                      ),
                    modifier = Modifier.testTag("rail_${tab.name.lowercase()}"),
                  )
                }
              }
            }

            when (activeTab) {
              TopTab.HOME ->
                HomeDashboardScreen(
                  profile = profile,
                  structuredModules = structuredModules,
                  progressList = progressList,
                  attempts = attempts,
                  notes = notes,
                  isDarkTheme = isDarkTheme,
                  themePreferences = themePreferences,
                  placementPassedSubjects = offlineSnapshot.placementPassedSubjects,
                  todayXp = offlineSnapshot.todayXp,
                  dueReviewsCount = dueReviewCards.size,
                  totalReviewCardsCount = allReviewCards.size,
                  onToggleDarkTheme = { viewModel.toggleDarkTheme(isDarkTheme) },
                  onSetThemeMode = viewModel::setThemeMode,
                  onSetUseDynamicColor = viewModel::setUseDynamicColor,
                  onSetFontSizeScale = viewModel::setFontSizeScale,
                  onOpenLesson = { lesson -> activeLesson = lesson },
                  onOpenTrack = { track ->
                    selectedCatalogSubjectId = track.id
                    activeTab = TopTab.CATALOG
                  },
                  onOpenExamLab = { subjectId ->
                    selectedExamSubjectId = subjectId
                    activeTab = TopTab.EXAMS
                  },
                  onOpenUtmeCombo = {
                    examLabMode = ExamLabMode.UTME_COMBO
                    examLabModeNonce++
                    activeTab = TopTab.EXAMS
                  },
                  onOpenPaper2Lab = {
                    examLabMode = ExamLabMode.PAPER_2
                    examLabModeNonce++
                    activeTab = TopTab.EXAMS
                  },
                  onOpenNotebook = { activeTab = TopTab.NOTEBOOK },
                  onOpenSpacedReview = { showSpacedReviewModal = true },
                  onOpenMenu = { showMenuSheet = true },
                  modifier = Modifier.weight(1f),
                )

              TopTab.CATALOG ->
                CatalogScreen(
                  initialSubjectId = selectedCatalogSubjectId,
                  structuredModules = structuredModules,
                  progressList = progressList,
                  placementPassedSubjects = offlineSnapshot.placementPassedSubjects,
                  passThresholdPercent = themePreferences.masteryPassPercent.toLong(),
                  dueReviewsCount = dueReviewCards.size,
                  onOpenPlacementCheck = { subId ->
                    placementSubjectArg = subId
                    showPlacementDialog = true
                  },
                  onOpenSpacedReview = { showSpacedReviewModal = true },
                  onOpenExamLab = { subjectId ->
                    selectedExamSubjectId = subjectId
                    examLabMode = ExamLabMode.SINGLE_CBT
                    examLabModeNonce++
                    activeTab = TopTab.EXAMS
                  },
                  onOpenPaper2Lab = {
                    examLabMode = ExamLabMode.PAPER_2
                    examLabModeNonce++
                    activeTab = TopTab.EXAMS
                  },
                  onOpenMenu = { showMenuSheet = true },
                  onOpenLesson = { lesson -> activeLesson = lesson },
                  modifier = Modifier.weight(1f),
                )

              TopTab.EXAMS ->
                ExamSimulatorScreen(
                  initialSubjectId = selectedExamSubjectId,
                  initialLabMode = examLabMode,
                  labModeRequestNonce = examLabModeNonce,
                  cbtState = cbtState,
                  cbtShowCalculator = themePreferences.cbtShowCalculator,
                  dueReviewsCount = dueReviewCards.size,
                  resumableSession = resumableCbtSession,
                  paper2Attempts = paper2Attempts,
                  onSelectSubject = viewModel::selectExamSubject,
                  onSelectBoard = viewModel::selectExamBoard,
                  onSelectQuestionLimit = viewModel::selectQuestionLimit,
                  onToggleComboElective = viewModel::toggleComboElective,
                  onStartExam = viewModel::startMockExam,
                  onStartUtmeCombo = { electives, perSub, isFull180 ->
                    viewModel.startUtmeComboExam(electives, perSub, isFull180)
                  },
                  onSwitchComboSubjectTab = viewModel::switchComboSubjectTab,
                  onSelectAnswer = viewModel::selectCbtAnswer,
                  onToggleFlag = viewModel::toggleFlagCbtQuestion,
                  onGoToQuestion = viewModel::goToCbtQuestion,
                  onNextQuestion = viewModel::nextCbtQuestion,
                  onPreviousQuestion = viewModel::previousCbtQuestion,
                  onTogglePauseTimer = viewModel::togglePauseCbtTimer,
                  onSubmitExam = viewModel::submitCbtExam,
                  onRetakeExam = viewModel::retakeLastMock,
                  onResumeSavedCbt = viewModel::resumeSavedCbtExam,
                  onDiscardSavedCbt = viewModel::discardSavedCbtExam,
                  onSavePaper2Attempt = viewModel::savePaper2Attempt,
                  onDrillMissedQuestions = viewModel::startMissedQuestionsDrill,
                  onSaveToNotebook = { subjectId, title, content ->
                    viewModel.createStudyNote("cbt_review", subjectId, title, content, true)
                  },
                  onOpenMenu = { showMenuSheet = true },
                  onReturnToSetup = viewModel::returnToCbtSetup,
                  modifier = Modifier.weight(1f),
                )

              TopTab.NOTEBOOK ->
                NotebookScreen(
                  notes = notes,
                  dueReviewsCount = dueReviewCards.size,
                  onOpenSpacedReview = { showSpacedReviewModal = true },
                  onOpenMenu = { showMenuSheet = true },
                  onCreateNote = { lessonId, subjectId, title, content, bookmarked ->
                    viewModel.createStudyNote(lessonId, subjectId, title, content, bookmarked)
                  },
                  onToggleBookmark = { note -> viewModel.toggleBookmark(note) },
                  onDeleteNote = { noteId -> viewModel.deleteStudyNote(noteId) },
                  modifier = Modifier.weight(1f),
                )

              TopTab.ANALYTICS ->
                AnalyticsProfileScreen(
                  profile = profile,
                  attempts = attempts,
                  progressList = progressList,
                  offlineSnapshot = offlineSnapshot,
                  themePreferences = themePreferences,
                  paper2Attempts = paper2Attempts,
                  dueReviewsCount = dueReviewCards.size,
                  onSetThemeMode = viewModel::setThemeMode,
                  onSetThemePalette = viewModel::setThemePalette,
                  onSetHighContrastBoost = viewModel::setHighContrastBoost,
                  onSetUseDynamicColor = viewModel::setUseDynamicColor,
                  onSetFontSizeScale = viewModel::setFontSizeScale,
                  onOpenSettingsPortal = { showSettingsPortal = true },
                  onOpenNotebook = { activeTab = TopTab.NOTEBOOK },
                  onOpenMenu = { showMenuSheet = true },
                  onPracticeWeakSubject = { subjectId ->
                    selectedExamSubjectId = subjectId
                    activeTab = TopTab.EXAMS
                  },
                  onUpdateGoal = { name, exam, score -> viewModel.updateGoal(name, exam, score) },
                  modifier = Modifier.weight(1f),
                )
            }
          }
        }
      }
    }

    if (showQuickActionSheet) {
      QuickStudyLauncherSheet(
        dueReviewsCount = dueReviewCards.size,
        notesCount = notes.size,
        onOpenSingleCbt = {
          showQuickActionSheet = false
          examLabMode = ExamLabMode.SINGLE_CBT
          examLabModeNonce++
          activeTab = TopTab.EXAMS
        },
        onOpenUtme180 = {
          showQuickActionSheet = false
          examLabMode = ExamLabMode.UTME_COMBO
          examLabModeNonce++
          activeTab = TopTab.EXAMS
        },
        onOpenSpacedReview = {
          showQuickActionSheet = false
          showSpacedReviewModal = true
        },
        onOpenNotebook = {
          showQuickActionSheet = false
          activeTab = TopTab.NOTEBOOK
        },
        onOpenPaper2 = {
          showQuickActionSheet = false
          examLabMode = ExamLabMode.PAPER_2
          examLabModeNonce++
          activeTab = TopTab.EXAMS
        },
        onOpenPlacement = {
          showQuickActionSheet = false
          placementSubjectArg = selectedCatalogSubjectId
          showPlacementDialog = true
        },
        onDismiss = { showQuickActionSheet = false },
      )
    }

    if (showMenuSheet) {
      AcademyMenuSheet(
        profile = profile,
        themePreferences = themePreferences,
        isDarkTheme = isDarkTheme,
        todayXp = offlineSnapshot.todayXp,
        dueReviewsCount = dueReviewCards.size,
        totalReviewCardsCount = allReviewCards.size,
        placementPassedCount = offlineSnapshot.placementPassedSubjects.size,
        signedInUser = signedInGoogleUser,
        isCloudSyncing = isCloudSyncing,
        onToggleDarkTheme = { viewModel.toggleDarkTheme(isDarkTheme) },
        onOpenSettingsPortal = { showSettingsPortal = true },
        onOpenSpacedReview = { showSpacedReviewModal = true },
        onOpenUtmeCombo = {
          activeLesson = null
          examLabMode = ExamLabMode.UTME_COMBO
          examLabModeNonce++
          activeTab = TopTab.EXAMS
        },
        onOpenPaper2Lab = {
          activeLesson = null
          examLabMode = ExamLabMode.PAPER_2
          examLabModeNonce++
          activeTab = TopTab.EXAMS
        },
        onOpenPlacementCheck = {
          placementSubjectArg = activeLesson?.subjectId ?: selectedCatalogSubjectId
          showPlacementDialog = true
        },
        onOpenNotebook = {
          activeLesson = null
          activeTab = TopTab.NOTEBOOK
        },
        onOpenAnalytics = {
          activeLesson = null
          activeTab = TopTab.ANALYTICS
        },
        onExportJsonBackup = {
          exportBackupLauncher.launch("meritprep_backup.json")
        },
        onImportJsonBackup = {
          importBackupLauncher.launch(arrayOf("application/json", "text/plain"))
        },
        onGoogleSignInAndSync = { viewModel.signInAndSyncWithGoogle(context) },
        onGoogleSignOut = { viewModel.signOutGoogle(context) },
        onDismiss = { showMenuSheet = false },
      )
    }

    if (showSettingsPortal) {
      DisplaySettingsDialog(
        themePreferences = themePreferences,
        onSetThemeMode = viewModel::setThemeMode,
        onSetUseDynamicColor = viewModel::setUseDynamicColor,
        onSetFontSizeScale = viewModel::setFontSizeScale,
        onSetThemePalette = viewModel::setThemePalette,
        onSetHighContrastBoost = viewModel::setHighContrastBoost,
        onSetLineSpacingScale = viewModel::setLineSpacingScale,
        onSetBoldEquations = viewModel::setBoldEquations,
        onSetCbtSecondsPerQuestion = viewModel::setCbtSecondsPerQuestion,
        onSetCbtAutoAdvance = viewModel::setCbtAutoAdvance,
        onSetCbtShowCalculator = viewModel::setCbtShowCalculator,
        onSetShuffleOptionsOnRetry = viewModel::setShuffleOptionsOnRetry,
        onSetMasteryPassPercent = viewModel::setMasteryPassPercent,
        onSetDailyXpTarget = viewModel::setDailyXpTarget,
        onSetSpacedReviewBatchSize = viewModel::setSpacedReviewBatchSize,
        onExportBackup = {
          showSettingsPortal = false
          exportBackupLauncher.launch("meritprep_backup.json")
        },
        onImportBackup = {
          showSettingsPortal = false
          importBackupLauncher.launch(arrayOf("application/json", "text/plain"))
        },
        onOpenCloudSync = { viewModel.signInAndSyncWithGoogle(context) },
        cloudStatusLabel =
          signedInGoogleUser?.let { "Synced: ${it.displayName} (Tap to Sync)" }
            ?: "Google Sign-In & Cloud Sync",
        onDismiss = { showSettingsPortal = false },
      )
    }

    if (showSpacedReviewModal) {
      SpacedRepetitionModal(
        dueCards = dueReviewCards,
        allCardsCount = allReviewCards.size,
        batchSize = themePreferences.spacedReviewBatchSize,
        onRateCard = viewModel::rateSpacedReviewCard,
        onDismiss = { showSpacedReviewModal = false },
      )
    }

    if (showPlacementDialog) {
      PlacementAssessmentDialog(
        initialSubjectId = placementSubjectArg,
        alreadyPassedSubjects = offlineSnapshot.placementPassedSubjects,
        onCompletePlacement = viewModel::completePlacementCheck,
        onDismiss = { showPlacementDialog = false },
      )
    }
  }
}

/**
 * Custom white Studio bottom bar with 4 balanced destinations and a raised centered '+' gradient orb,
 * matching the mockup's bottom navigation shell while respecting system navigation bar insets.
 */
@Composable
private fun StudioDockedBottomBar(
  activeTab: TopTab,
  dueReviewsCount: Int,
  onSelectTab: (TopTab) -> Unit,
  onCenterActionClick: () -> Unit,
) {
  val semantics = MaterialTheme.semanticColors
  val navTabs = listOf(TopTab.HOME, TopTab.CATALOG, TopTab.EXAMS, TopTab.ANALYTICS)

  Box(
    modifier = Modifier.fillMaxWidth(),
    contentAlignment = Alignment.BottomCenter,
  ) {
    Surface(
      color = MaterialTheme.colorScheme.surface,
      shadowElevation = 10.dp,
      modifier = Modifier.fillMaxWidth(),
    ) {
      Column {
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
        Row(
          modifier =
            Modifier.fillMaxWidth()
              .navigationBarsPadding()
              .height(64.dp)
              .padding(horizontal = 8.dp),
          horizontalArrangement = Arrangement.SpaceAround,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          StudioNavIconItem(
            tab = navTabs[0],
            selected = activeTab == navTabs[0],
            showDot = false,
            onClick = { onSelectTab(navTabs[0]) },
            modifier = Modifier.weight(1f).testTag("nav_home"),
          )
          StudioNavIconItem(
            tab = navTabs[1],
            selected = activeTab == navTabs[1],
            showDot = false,
            onClick = { onSelectTab(navTabs[1]) },
            modifier = Modifier.weight(1f).testTag("nav_catalog"),
          )
          // Reserved center slot for the floating + orb
          Spacer(modifier = Modifier.width(68.dp))
          StudioNavIconItem(
            tab = navTabs[2],
            selected = activeTab == navTabs[2],
            showDot = false,
            onClick = { onSelectTab(navTabs[2]) },
            modifier = Modifier.weight(1f).testTag("nav_exams"),
          )
          StudioNavIconItem(
            tab = navTabs[3],
            selected = activeTab == navTabs[3],
            showDot = dueReviewsCount > 0,
            onClick = { onSelectTab(navTabs[3]) },
            modifier = Modifier.weight(1f).testTag("nav_analytics"),
          )
        }
      }
    }

    // Floating center '+' orb matching the Studio mockup
    Box(
      modifier =
        Modifier.navigationBarsPadding()
          .padding(bottom = 26.dp),
      contentAlignment = Alignment.TopEnd,
    ) {
      Box(
        modifier =
          Modifier.minimumInteractiveComponentSize()
            .size(56.dp)
            .shadow(
              elevation = 10.dp,
              shape = CircleShape,
              ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.45f),
              spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.55f),
            )
            .clip(CircleShape)
            .background(
              Brush.linearGradient(
                listOf(semantics.heroGradientStart, MaterialTheme.colorScheme.primary)
              )
            )
            .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
            .clickable(onClick = onCenterActionClick)
            .testTag("nav_center_fab"),
        contentAlignment = Alignment.Center,
      ) {
        Icon(
          imageVector = Icons.Filled.Add,
          contentDescription = "Quick Study & Mock Launcher",
          tint = Color.White,
          modifier = Modifier.size(28.dp),
        )
      }
      if (dueReviewsCount > 0) {
        Box(
          modifier =
            Modifier.offset(x = (-2).dp, y = 2.dp)
              .size(12.dp)
              .clip(CircleShape)
              .background(semantics.coralAccent)
              .border(2.dp, MaterialTheme.colorScheme.surface, CircleShape)
        )
      }
    }
  }
}

@Composable
private fun StudioNavIconItem(
  tab: TopTab,
  selected: Boolean,
  showDot: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val semantics = MaterialTheme.semanticColors
  val iconTint =
    if (selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurfaceVariant

  Column(
    modifier =
      modifier
        .minimumInteractiveComponentSize()
        .clip(RoundedCornerShape(14.dp))
        .clickable(onClick = onClick)
        .padding(vertical = 6.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(2.dp),
  ) {
    Box(contentAlignment = Alignment.TopEnd) {
      Icon(
        imageVector = if (selected) tab.activeIcon else tab.inactiveIcon,
        contentDescription = tab.label,
        tint = iconTint,
        modifier = Modifier.size(23.dp),
      )
      if (showDot) {
        Box(
          modifier =
            Modifier.offset(x = 3.dp, y = (-1).dp)
              .size(7.dp)
              .clip(CircleShape)
              .background(semantics.coralAccent)
        )
      }
    }
    Text(
      text = tab.label,
      style = MaterialTheme.typography.labelSmall,
      color = iconTint,
      fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuickStudyLauncherSheet(
  dueReviewsCount: Int,
  notesCount: Int,
  onOpenSingleCbt: () -> Unit,
  onOpenUtme180: () -> Unit,
  onOpenSpacedReview: () -> Unit,
  onOpenNotebook: () -> Unit,
  onOpenPaper2: () -> Unit,
  onOpenPlacement: () -> Unit,
  onDismiss: () -> Unit,
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val semantics = MaterialTheme.semanticColors

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
  ) {
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .navigationBarsPadding()
          .padding(horizontal = 18.dp, vertical = 10.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      Text(
        text = "Quick Study Launcher",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
      )
      Text(
        text = "Jump straight into practice drills, flashcards, or your saved formula sheets.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )

      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        QuickLauncherCard(
          icon = Icons.Filled.Quiz,
          title = "Timed CBT",
          subtitle = "JAMB • WAEC • NECO",
          tint = MaterialTheme.colorScheme.primary,
          modifier = Modifier.weight(1f),
          onClick = onOpenSingleCbt,
        )
        QuickLauncherCard(
          icon = Icons.Filled.School,
          title = "180-Q UTME",
          subtitle = "4 Subjects /400",
          tint = semantics.indigoAccent,
          modifier = Modifier.weight(1f),
          onClick = onOpenUtme180,
        )
      }

      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        QuickLauncherCard(
          icon = Icons.Filled.Psychology,
          title = "Recall Cards",
          subtitle = if (dueReviewsCount > 0) "$dueReviewsCount due now" else "Review deck",
          tint = semantics.coralAccent,
          modifier = Modifier.weight(1f),
          onClick = onOpenSpacedReview,
        )
        QuickLauncherCard(
          icon = Icons.AutoMirrored.Filled.MenuBook,
          title = "Notebook",
          subtitle = "$notesCount saved notes",
          tint = semantics.success,
          modifier = Modifier.weight(1f),
          onClick = onOpenNotebook,
        )
      }

      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        QuickLauncherCard(
          icon = Icons.AutoMirrored.Filled.Assignment,
          title = "Paper 2 Theory",
          subtitle = "WAEC Essay & Lab",
          tint = MaterialTheme.colorScheme.primary,
          modifier = Modifier.weight(1f),
          onClick = onOpenPaper2,
        )
        QuickLauncherCard(
          icon = Icons.AutoMirrored.Outlined.MenuBook,
          title = "SS3 Check",
          subtitle = "Unlock 60% fast",
          tint = semantics.periwinkleAccent,
          modifier = Modifier.weight(1f),
          onClick = onOpenPlacement,
        )
      }

      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}

@Composable
private fun QuickLauncherCard(
  icon: ImageVector,
  title: String,
  subtitle: String,
  tint: Color,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Column(
    modifier =
      modifier
        .clip(RoundedCornerShape(18.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f))
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
        .clickable(onClick = onClick)
        .padding(14.dp),
    verticalArrangement = Arrangement.spacedBy(6.dp),
  ) {
    Box(
      modifier =
        Modifier.size(36.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(tint.copy(alpha = 0.14f)),
      contentAlignment = Alignment.Center,
    ) {
      Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(20.dp))
    }
    Text(
      text = title,
      style = MaterialTheme.typography.titleSmall,
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.Bold,
    )
    Text(
      text = subtitle,
      style = MaterialTheme.typography.labelSmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
  }
}
