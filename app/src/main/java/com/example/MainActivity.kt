package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.Lesson
import com.example.data.repository.MeritPrepRepository
import com.example.ui.auth.AuthScreen
import com.example.ui.auth.signOut
import com.example.ui.screens.AnalyticsProfileScreen
import com.example.ui.screens.CatalogScreen
import com.example.ui.screens.ExamSimulatorScreen
import com.example.ui.screens.HomeDashboardScreen
import com.example.ui.screens.LessonClassroomScreen
import com.example.ui.screens.NotebookScreen
import com.example.ui.theme.MeritPrepTheme
import com.example.ui.viewmodel.MeritPrepViewModel
import com.example.ui.viewmodel.UiState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

private enum class TopTab(
  val label: String,
  val activeIcon: ImageVector,
  val inactiveIcon: ImageVector,
) {
  HOME("Learn", Icons.Filled.Home, Icons.Outlined.Home),
  CATALOG("Tracks", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
  EXAMS("Exam Lab", Icons.Filled.Quiz, Icons.Outlined.Quiz),
  NOTEBOOK("Notebook", Icons.Filled.Book, Icons.Outlined.Book),
  ANALYTICS("Analytics", Icons.Filled.Analytics, Icons.Outlined.Analytics),
}

internal fun FirebaseAuth.authStateFlow(): Flow<FirebaseUser?> = callbackFlow {
  val listener = FirebaseAuth.AuthStateListener { auth -> trySend(auth.currentUser) }
  addAuthStateListener(listener)
  awaitClose { removeAuthStateListener(listener) }
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MeritPrepTheme {
        AppNavigation()
      }
    }
  }
}

@Composable
fun AppNavigation(auth: FirebaseAuth = Firebase.auth) {
  val currentUser by
    auth.authStateFlow().collectAsStateWithLifecycle(initialValue = auth.currentUser)
  val user = currentUser

  if (user == null) {
    AuthScreen(onAuthSuccess = {})
  } else {
    key(user.uid) {
      MeritPrepAppShell(
        currentUserId = user.uid,
        displayName = user.displayName ?: user.email?.substringBefore("@") ?: "Scholar",
      )
    }
  }
}

@Composable
private fun MeritPrepAppShell(
  currentUserId: String,
  displayName: String,
) {
  val context = LocalContext.current
  val coroutineScope = rememberCoroutineScope()
  val credentialManager = remember { CredentialManager.create(context) }

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
            val databaseId = app.getString(R.string.firestore_database_id)
            val db = FirebaseFirestore.getInstance(databaseId)
            MeritPrepViewModel(MeritPrepRepository(db), currentUserId, displayName)
          }
        },
    )

  val profileState by viewModel.profileState.collectAsStateWithLifecycle()
  val progressState by viewModel.progressState.collectAsStateWithLifecycle()
  val attemptsState by viewModel.attemptsState.collectAsStateWithLifecycle()
  val notesState by viewModel.notesState.collectAsStateWithLifecycle()
  val statusMessage by viewModel.statusMessage.collectAsStateWithLifecycle()

  val profile = (profileState as? UiState.Success)?.data
  val progressList = (progressState as? UiState.Success)?.data ?: emptyList()
  val attempts = (attemptsState as? UiState.Success)?.data ?: emptyList()
  val notes = (notesState as? UiState.Success)?.data ?: emptyList()

  var activeTab by remember { mutableStateOf(TopTab.HOME) }
  var selectedCatalogSubjectId by remember { mutableStateOf<String?>(null) }
  var selectedExamSubjectId by remember { mutableStateOf<String?>(null) }
  var activeLesson by remember { mutableStateOf<Lesson?>(null) }

  val snackbarHostState = remember { SnackbarHostState() }
  LaunchedEffect(statusMessage) {
    val msg = statusMessage
    if (!msg.isNullOrBlank()) {
      snackbarHostState.showSnackbar(msg)
      viewModel.clearStatusMessage()
    }
  }

  val openLesson = activeLesson
  if (openLesson != null) {
    val lessonProgress = progressList.find { it.lessonId == openLesson.id }
    LessonClassroomScreen(
      lesson = openLesson,
      progress = lessonProgress,
      onBack = { activeLesson = null },
      onMarkPillarComplete = { pillar, score ->
        viewModel.markPillarCompleted(openLesson, pillar, lessonProgress, score)
      },
      onSaveNote = { title, content ->
        viewModel.createStudyNote(openLesson.id, openLesson.subjectId, title, content, true)
      },
    )
    return
  }

  BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
    val useRail = maxWidth >= 720.dp

    Scaffold(
      snackbarHost = { SnackbarHost(snackbarHostState) },
      bottomBar = {
        if (!useRail) {
          NavigationBar {
            TopTab.entries.forEach { tab ->
              val selected = tab == activeTab
              NavigationBarItem(
                selected = selected,
                onClick = { activeTab = tab },
                icon = {
                  Icon(
                    imageVector = if (selected) tab.activeIcon else tab.inactiveIcon,
                    contentDescription = tab.label,
                  )
                },
                label = { Text(tab.label) },
                modifier = Modifier.testTag("nav_${tab.name.lowercase()}"),
              )
            }
          }
        }
      },
    ) { innerPadding ->
      Row(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        if (useRail) {
          NavigationRail(modifier = Modifier.fillMaxHeight()) {
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
                modifier = Modifier.testTag("rail_${tab.name.lowercase()}"),
              )
            }
          }
        }

        when (activeTab) {
          TopTab.HOME ->
            HomeDashboardScreen(
              profile = profile,
              progressList = progressList,
              attempts = attempts,
              onOpenLesson = { lesson -> activeLesson = lesson },
              onOpenTrack = { track ->
                selectedCatalogSubjectId = track.id
                activeTab = TopTab.CATALOG
              },
              onOpenExamLab = { subjectId ->
                selectedExamSubjectId = subjectId
                activeTab = TopTab.EXAMS
              },
              modifier = Modifier.weight(1f),
            )

          TopTab.CATALOG ->
            CatalogScreen(
              initialSubjectId = selectedCatalogSubjectId,
              progressList = progressList,
              onOpenLesson = { lesson -> activeLesson = lesson },
              modifier = Modifier.weight(1f),
            )

          TopTab.EXAMS ->
            ExamSimulatorScreen(
              initialSubjectId = selectedExamSubjectId,
              onRecordExamAttempt = { examType, subjectId, subjectTitle, correct, total, dur, weak ->
                viewModel.recordMockExam(examType, subjectId, subjectTitle, correct, total, dur, weak)
              },
              modifier = Modifier.weight(1f),
            )

          TopTab.NOTEBOOK ->
            NotebookScreen(
              notes = notes,
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
              onUpdateGoal = { name, exam, score -> viewModel.updateGoal(name, exam, score) },
              onSignOut = {
                signOut(credentialManager = credentialManager, scope = coroutineScope)
              },
              modifier = Modifier.weight(1f),
            )
        }
      }
    }
  }
}
