package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.OutlinedFlag
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.ActiveCbtSessionEntity
import com.example.data.model.Paper2AttemptEntity
import com.example.ui.components.Paper2DrillView
import com.example.ui.components.QuestionDiagramCard
import com.example.ui.components.ScientificCalculatorSheet
import com.example.ui.components.ScientificText
import com.example.ui.components.TopRightMenuButton
import com.example.ui.theme.semanticColors
import com.example.ui.viewmodel.CbtExamStage
import com.example.ui.viewmodel.CbtExamState
import com.example.ui.viewmodel.QuestionReviewItem
import java.util.Locale

enum class ExamLabMode(val label: String) {
  SINGLE_CBT("Single Subject"),
  UTME_COMBO("4-Subject UTME (/400)"),
  PAPER_2("Paper 2 Theory"),
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ExamSimulatorScreen(
  initialSubjectId: String?,
  initialLabMode: ExamLabMode = ExamLabMode.SINGLE_CBT,
  labModeRequestNonce: Int = 0,
  cbtState: CbtExamState,
  cbtShowCalculator: Boolean = true,
  dueReviewsCount: Int = 0,
  resumableSession: ActiveCbtSessionEntity? = null,
  paper2Attempts: List<Paper2AttemptEntity> = emptyList(),
  onSelectSubject: (String) -> Unit,
  onSelectBoard: (String) -> Unit,
  onSelectQuestionLimit: (Int) -> Unit,
  onToggleComboElective: (String) -> Unit = {},
  onStartExam: (subjectId: String, examBoard: String, questionLimit: Int) -> Unit,
  onStartUtmeCombo: (electives: List<String>, questionsPerSubject: Int, isFullUtme180: Boolean) -> Unit = { _, _, _ -> },
  onSwitchComboSubjectTab: (String) -> Unit = {},
  onSelectAnswer: (questionIndex: Int, optionIndex: Int) -> Unit,
  onToggleFlag: (Int) -> Unit,
  onGoToQuestion: (Int) -> Unit,
  onNextQuestion: () -> Unit,
  onPreviousQuestion: () -> Unit,
  onTogglePauseTimer: () -> Unit,
  onSubmitExam: () -> Unit,
  onRetakeExam: () -> Unit,
  onResumeSavedCbt: () -> Unit = {},
  onDiscardSavedCbt: () -> Unit = {},
  onSavePaper2Attempt: (
    promptId: String,
    subjectId: String,
    title: String,
    studentWorking: String,
    checkedStepIndices: List<Int>,
    selfScoreMarks: Int,
    totalMarks: Int,
  ) -> Unit = { _, _, _, _, _, _, _ -> },
  onDrillMissedQuestions: (List<QuestionReviewItem>) -> Unit = {},
  onSaveToNotebook: (subjectId: String, title: String, content: String) -> Unit = { _, _, _ -> },
  onOpenMenu: () -> Unit = {},
  onReturnToSetup: () -> Unit,
  modifier: Modifier = Modifier,
) {
  var activeLabMode by remember { mutableStateOf(initialLabMode) }
  var comboQuestionsPerSubject by remember { mutableIntStateOf(10) }
  var isFullUtme180 by remember { mutableStateOf(false) }
  var showCalculator by remember { mutableStateOf(false) }
  var showQuestionGrid by remember { mutableStateOf(false) }
  var showSubmitConfirm by remember { mutableStateOf(false) }
  var gridFilter by remember { mutableIntStateOf(0) }

  LaunchedEffect(initialSubjectId) {
    if (!initialSubjectId.isNullOrBlank() && cbtState.stage == CbtExamStage.SETUP) {
      onSelectSubject(initialSubjectId)
    }
  }
  LaunchedEffect(initialLabMode, labModeRequestNonce) {
    if (labModeRequestNonce > 0) {
      activeLabMode = initialLabMode
    }
  }

  val semantics = MaterialTheme.semanticColors

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    when (cbtState.stage) {
      CbtExamStage.SETUP -> {
        val selectedSubjectId = cbtState.selectedSubjectId
        val selectedExamBoard = cbtState.selectedExamBoard
        val selectedQuestionLimit = cbtState.selectedQuestionLimit
        val previewQuestions = CurriculumCatalog.pastQuestionsFor(selectedSubjectId, selectedExamBoard)
        val availableCount =
          if (previewQuestions.isNotEmpty()) previewQuestions.size
          else CurriculumCatalog.pastQuestionsFor(selectedSubjectId, "ALL").size
        val effectiveCount =
          if (selectedQuestionLimit <= 0) availableCount
          else minOf(selectedQuestionLimit, availableCount)
        val savedQuestionCount =
          remember(resumableSession?.questionIdsJson) {
            runCatching { org.json.JSONArray(resumableSession?.questionIdsJson ?: "[]").length() }.getOrDefault(0)
          }
        val savedAnsweredCount =
          remember(resumableSession?.selectedAnswersJson) {
            runCatching { org.json.JSONObject(resumableSession?.selectedAnswersJson ?: "{}").length() }.getOrDefault(0)
          }

        LazyColumn(
          modifier = Modifier.widthIn(max = 880.dp).fillMaxSize(),
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
          item {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = "CBT & Paper 2 Exam Lab",
                  style = MaterialTheme.typography.headlineMedium,
                  color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                  text = "JAMB UTME • WAEC WASSCE • NECO SSCE",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
              }
              TopRightMenuButton(badgeCount = dueReviewsCount, onClick = onOpenMenu)
            }
          }

          if (resumableSession != null && savedQuestionCount > 0) {
            item {
              val savedMins = resumableSession.remainingSeconds / 60
              val savedSecs = resumableSession.remainingSeconds % 60
              val savedTime = String.format(Locale.US, "%02d:%02d", savedMins, savedSecs)
              val savedSubjectTitle =
                if (resumableSession.isUtmeComboMode) "4-Subject UTME Combo"
                else CurriculumCatalog.trackById(resumableSession.selectedSubjectId)?.shortName ?: resumableSession.selectedSubjectId
              Column(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .border(1.5.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(18.dp))
                    .padding(14.dp)
                    .testTag("resume_cbt_banner"),
                verticalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                Text(
                  text = "RESUME INTERRUPTED MOCK PAPER",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onTertiaryContainer,
                  fontWeight = FontWeight.Bold,
                )
                Text(
                  text = "$savedSubjectTitle (${resumableSession.selectedExamBoard}) • $savedAnsweredCount/$savedQuestionCount answered • $savedTime left",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onTertiaryContainer,
                  fontWeight = FontWeight.SemiBold,
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                  Button(
                    onClick = onResumeSavedCbt,
                    modifier = Modifier.weight(1f).minimumInteractiveComponentSize().testTag("resume_cbt_button"),
                    shape = RoundedCornerShape(12.dp),
                  ) {
                    Text("Resume Paper", fontWeight = FontWeight.Bold)
                  }
                  OutlinedButton(
                    onClick = onDiscardSavedCbt,
                    modifier = Modifier.minimumInteractiveComponentSize().testTag("discard_cbt_button"),
                    shape = RoundedCornerShape(12.dp),
                  ) {
                    Text("Discard")
                  }
                }
              }
            }
          }

          item {
            Row(
              modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              ExamLabMode.entries.forEach { mode ->
                val selected = activeLabMode == mode
                FilterChip(
                  selected = selected,
                  onClick = { activeLabMode = mode },
                  label = {
                    Text(
                      text = mode.label,
                      fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                    )
                  },
                  colors =
                    FilterChipDefaults.filterChipColors(
                      selectedContainerColor = MaterialTheme.colorScheme.primary,
                      selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                  modifier = Modifier.testTag("exam_lab_mode_${mode.name.lowercase()}"),
                )
              }
            }
          }

          item {
            Box(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(22.dp))
                  .background(
                    Brush.horizontalGradient(
                      listOf(
                        semantics.heroGradientStart,
                        semantics.heroGradientMid,
                        semantics.heroGradientEnd,
                      )
                    )
                  ),
            ) {
              Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
              ) {
                Surface(color = Color.White.copy(alpha = 0.24f), shape = RoundedCornerShape(99.dp)) {
                  Text(
                    text = "TIMED CBT • 4-SUBJECT /400 • PAPER 2",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                  )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text =
                    when (activeLabMode) {
                      ExamLabMode.SINGLE_CBT -> "JAMB, WAEC & NECO Objective Lab"
                      ExamLabMode.UTME_COMBO -> "Authentic 4-Subject UTME Combo (/400)"
                      ExamLabMode.PAPER_2 -> "WAEC & NECO Paper 2 Theory & Practicals"
                    },
                  style = MaterialTheme.typography.headlineSmall,
                  color = Color.White,
                  fontWeight = FontWeight.Bold,
                )
                Text(
                  text =
                    when (activeLabMode) {
                      ExamLabMode.SINGLE_CBT ->
                        "Syllabus-aligned practice bank, live state-managed timer, and detailed diagnostics."
                      ExamLabMode.UTME_COMBO ->
                        "Use of English + 3 Electives with live subject tabs and scaled /400 aggregate scoring."
                      ExamLabMode.PAPER_2 ->
                        "Structured essay & Alternative-to-Practical prompts with interactive M1/A1 rubrics."
                    },
                  style = MaterialTheme.typography.bodySmall,
                  color = Color.White.copy(alpha = 0.90f),
                )
              }
            }
          }

          item {
            when (activeLabMode) {
              ExamLabMode.PAPER_2 -> {
                Paper2DrillView(
                  selectedSubjectId = selectedSubjectId,
                  paper2Attempts = paper2Attempts,
                  onSelectSubject = onSelectSubject,
                  onSavePaper2Attempt = { prompt, working, checkedIndices, earnedMarks ->
                    onSavePaper2Attempt(
                      prompt.id,
                      prompt.subjectId,
                      "${prompt.examBoard} • ${prompt.topic}",
                      working,
                      checkedIndices,
                      earnedMarks,
                      prompt.totalMarks,
                    )
                  },
                  onSaveToNotebook = onSaveToNotebook,
                )
              }

              ExamLabMode.UTME_COMBO -> {
                Column(
                  modifier =
                    Modifier.fillMaxWidth()
                      .clip(RoundedCornerShape(20.dp))
                      .background(MaterialTheme.colorScheme.surface)
                      .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
                      .padding(16.dp),
                  verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                  Text(
                    text = "Compulsory Paper",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                  )
                  Surface(
                    color = semantics.successContainer,
                    shape = RoundedCornerShape(12.dp),
                    modifier =
                      Modifier.fillMaxWidth()
                        .border(1.dp, semantics.success, RoundedCornerShape(12.dp)),
                  ) {
                    Text(
                      text = "Use of English (Locked Compulsory Subject)",
                      style = MaterialTheme.typography.titleSmall,
                      color = semantics.onSuccessContainer,
                      fontWeight = FontWeight.Bold,
                      modifier = Modifier.padding(12.dp),
                    )
                  }

                  Text(
                    text = "Elective Subjects (${cbtState.selectedComboElectives.size}/3 selected)",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                  )
                  FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                  ) {
                    CurriculumCatalog.tracks.filter { it.id != "english" }.forEach { track ->
                      val picked = track.id in cbtState.selectedComboElectives
                      FilterChip(
                        selected = picked,
                        onClick = { onToggleComboElective(track.id) },
                        label = {
                          Text(
                            text = track.shortName,
                            fontWeight = if (picked) FontWeight.Bold else FontWeight.Medium,
                          )
                        },
                        colors =
                          FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                          ),
                        modifier = Modifier.testTag("combo_elective_${track.id}"),
                      )
                    }
                  }

                  Text(
                    text = "Format & Question Count",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                  )
                  FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                  ) {
                    listOf(5 to "5 × 4 (20 Qs)", 10 to "10 × 4 (40 Qs)", 15 to "15 × 4 (60 Qs)").forEach { (n, label) ->
                      val selected = !isFullUtme180 && comboQuestionsPerSubject == n
                      FilterChip(
                        selected = selected,
                        onClick = {
                          isFullUtme180 = false
                          comboQuestionsPerSubject = n
                        },
                        label = { Text(label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
                        colors =
                          FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                          ),
                      )
                    }
                    FilterChip(
                      selected = isFullUtme180,
                      onClick = { isFullUtme180 = true },
                      label = {
                        Text(
                          text = "Authentic 180 Qs (60 Eng + 3×40 • 120m)",
                          fontWeight = if (isFullUtme180) FontWeight.Bold else FontWeight.Medium,
                        )
                      },
                      colors =
                        FilterChipDefaults.filterChipColors(
                          selectedContainerColor = MaterialTheme.colorScheme.secondary,
                          selectedLabelColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                      modifier = Modifier.testTag("combo_full_180_chip"),
                    )
                  }

                  Button(
                    onClick = {
                      onStartUtmeCombo(cbtState.selectedComboElectives, comboQuestionsPerSubject, isFullUtme180)
                    },
                    enabled = cbtState.selectedComboElectives.size == 3 && !cbtState.isLoadingQuestions,
                    modifier = Modifier.fillMaxWidth().height(54.dp).testTag("start_utme_combo_button"),
                    shape = RoundedCornerShape(14.dp),
                  ) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(
                      text = if (isFullUtme180) "Start Full 180-Q UTME (/400)" else "Start 4-Subject UTME (/400)",
                      style = MaterialTheme.typography.titleMedium,
                      fontWeight = FontWeight.Bold,
                    )
                  }
                }
              }

              ExamLabMode.SINGLE_CBT -> {
                Column(
                  modifier =
                    Modifier.fillMaxWidth()
                      .clip(RoundedCornerShape(20.dp))
                      .background(MaterialTheme.colorScheme.surface)
                      .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
                      .padding(16.dp),
                  verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
              Text(
                text = "Exam Board",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
              )
              FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                listOf("JAMB", "WAEC", "NECO", "ALL").forEach { board ->
                  val selected = selectedExamBoard == board
                  val boardLabel =
                    when (board) {
                      "JAMB" -> "JAMB UTME CBT"
                      "WAEC" -> "WAEC WASSCE + Scheme"
                      "NECO" -> "NECO SSCE + Scheme"
                      else -> "All 3 Boards Combined"
                    }
                  FilterChip(
                    selected = selected,
                    onClick = { onSelectBoard(board) },
                    label = {
                      Text(
                        text = boardLabel,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                      )
                    },
                    colors =
                      FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.background,
                        labelColor = MaterialTheme.colorScheme.onSurface,
                      ),
                    modifier = Modifier.testTag("exam_board_$board"),
                  )
                }
              }

              Text(
                text = "Subject Paper",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
              )
              FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                CurriculumCatalog.tracks.forEach { track ->
                  val selected = selectedSubjectId == track.id
                  FilterChip(
                    selected = selected,
                    onClick = { onSelectSubject(track.id) },
                    label = {
                      Text(
                        text = track.shortName,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                      )
                    },
                    colors =
                      FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.background,
                        labelColor = MaterialTheme.colorScheme.onSurface,
                      ),
                    modifier = Modifier.testTag("exam_subject_${track.id}"),
                  )
                }
              }

              Text(
                text = "Paper Length",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
              )
              FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                val limitChoices =
                  listOf(
                    10 to "10 Qs Sprint",
                    15 to "15 Qs Standard",
                    0 to "Full Bank ($availableCount Qs)",
                  )
                limitChoices.forEach { (limitValue, limitLabel) ->
                  val selected = selectedQuestionLimit == limitValue
                  FilterChip(
                    selected = selected,
                    onClick = { onSelectQuestionLimit(limitValue) },
                    label = {
                      Text(
                        text = limitLabel,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                      )
                    },
                    colors =
                      FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.background,
                        labelColor = MaterialTheme.colorScheme.onSurface,
                      ),
                    modifier = Modifier.testTag("exam_limit_$limitValue"),
                  )
                }
              }

              Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(14.dp),
                modifier =
                  Modifier.fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(14.dp)),
              ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                  Text(
                    text = "Offline Paper Blueprint: $effectiveCount of $availableCount Syllabus Practice Questions",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                  )
                  Text(
                    text =
                      when (selectedExamBoard) {
                        "JAMB" ->
                          "Fetched from local Room SQLite bank with live countdown clock, per-question timing & post-exam review."
                        "WAEC" ->
                          "Includes WAEC Objective + Method/Accuracy (M1/A1) examiner marking schemes & offline DataStore sync."
                        "NECO" ->
                          "Includes NECO SSCE-style conceptual & practical prompts with full step-by-step derivations."
                        else ->
                          "Combined JAMB UTME, WAEC WASSCE & NECO SSCE syllabus practice bank with complete worked solutions."
                      },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                  )
                }
              }

              Button(
                onClick = { onStartExam(selectedSubjectId, selectedExamBoard, selectedQuestionLimit) },
                enabled = effectiveCount > 0 && !cbtState.isLoadingQuestions,
                modifier = Modifier.fillMaxWidth().height(54.dp).testTag("start_mock_exam_button"),
                shape = RoundedCornerShape(14.dp),
              ) {
                if (cbtState.isLoadingQuestions) {
                  CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp,
                  )
                  Spacer(modifier = Modifier.width(8.dp))
                  Text("Loading Paper...", fontWeight = FontWeight.Bold)
                } else {
                  Icon(Icons.Filled.PlayArrow, contentDescription = null)
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    "Start Timed Mock Exam",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                  )
                }
              }
            }
          }
        }
      }
    }
  }

      CbtExamStage.RUNNING -> {
        BackHandler(onBack = onReturnToSetup)

        val questions = cbtState.questions
        if (questions.isEmpty()) return@Box
        val currentIndex = cbtState.currentIndex.coerceIn(0, questions.lastIndex)
        val question = questions[currentIndex]
        val minutes = cbtState.remainingSeconds / 60
        val seconds = cbtState.remainingSeconds % 60
        val isFlagged = currentIndex in cbtState.flaggedQuestions
        val timerProgress =
          if (cbtState.totalAllowedSeconds > 0) {
            cbtState.remainingSeconds / cbtState.totalAllowedSeconds.toFloat()
          } else 1f

        Column(
          modifier = Modifier.widthIn(max = 880.dp).fillMaxSize().padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          // Top CBT State-Managed Timer & Controls Header
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(semantics.classroomBar),
          ) {
            Row(
              modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 10.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
              ) {
                Icon(
                  Icons.Filled.Timer,
                  contentDescription = "Remaining time",
                  tint =
                    if (cbtState.remainingSeconds < 60) MaterialTheme.colorScheme.error
                    else semantics.classroomAccent,
                )
                Text(
                  text = String.format(Locale.US, "%02d:%02d", minutes, seconds),
                  style =
                    MaterialTheme.typography.titleMedium.copy(
                      fontFamily = FontFamily.Default,
                      fontFeatureSettings = "tnum",
                    ),
                  color =
                    if (cbtState.remainingSeconds < 60) MaterialTheme.colorScheme.error
                    else semantics.onClassroomBar,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.testTag("cbt_timer_text"),
                )
                IconButton(
                  onClick = onTogglePauseTimer,
                  modifier = Modifier.minimumInteractiveComponentSize().testTag("cbt_pause_timer_button"),
                ) {
                  Icon(
                    imageVector = if (cbtState.isTimerPaused) Icons.Filled.PlayArrow else Icons.Filled.Pause,
                    contentDescription = if (cbtState.isTimerPaused) "Resume timer" else "Pause timer",
                    tint = semantics.onClassroomBar,
                    modifier = Modifier.size(18.dp),
                  )
                }
                IconButton(
                  onClick = { showQuestionGrid = true },
                  modifier = Modifier.minimumInteractiveComponentSize().testTag("cbt_question_grid_button"),
                ) {
                  Icon(
                    imageVector = Icons.Filled.GridView,
                    contentDescription = "Open Question Map Grid",
                    tint = semantics.onClassroomBar,
                    modifier = Modifier.size(19.dp),
                  )
                }
                if (cbtShowCalculator) {
                  IconButton(
                    onClick = { showCalculator = true },
                    modifier = Modifier.minimumInteractiveComponentSize().testTag("cbt_calculator_button"),
                  ) {
                    Icon(
                      imageVector = Icons.Filled.Calculate,
                      contentDescription = "Open Scientific Calculator",
                      tint = semantics.classroomAccent,
                      modifier = Modifier.size(20.dp),
                    )
                  }
                }
              }

              Text(
                text =
                  if (cbtState.isTimerPaused) "PAUSED"
                  else "${cbtState.selectedAnswers.size}/${questions.size}",
                style = MaterialTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
                color = if (cbtState.isTimerPaused) semantics.classroomAccent else semantics.classroomSubtext,
                fontWeight = FontWeight.Bold,
              )

              Button(
                onClick = { showSubmitConfirm = true },
                shape = RoundedCornerShape(10.dp),
                colors =
                  ButtonDefaults.buttonColors(
                    containerColor = semantics.classroomAccent,
                    contentColor = semantics.onClassroomAccent,
                  ),
                modifier = Modifier.minimumInteractiveComponentSize().testTag("submit_cbt_button"),
              ) {
                Text("Submit", fontWeight = FontWeight.Bold)
              }
            }
            LinearProgressIndicator(
              progress = { timerProgress.coerceIn(0f, 1f) },
              modifier = Modifier.fillMaxWidth().height(4.dp),
              color =
                if (cbtState.remainingSeconds < 60) MaterialTheme.colorScheme.error
                else semantics.classroomAccent,
              trackColor = Color.White.copy(alpha = 0.22f),
            )
          }

          // Live 4-Subject UTME Combo Subject Switcher Tabs
          if (cbtState.isUtmeComboMode && cbtState.comboSubjectTabs.isNotEmpty()) {
            Row(
              modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              cbtState.comboSubjectTabs.forEach { subId ->
                val isTabActive = cbtState.activeComboSubjectTab == subId
                val shortLabel = CurriculumCatalog.trackById(subId)?.shortName ?: subId
                FilterChip(
                  selected = isTabActive,
                  onClick = { onSwitchComboSubjectTab(subId) },
                  label = {
                    Text(
                      text = shortLabel,
                      fontWeight = if (isTabActive) FontWeight.Bold else FontWeight.Medium,
                    )
                  },
                  colors =
                    FilterChipDefaults.filterChipColors(
                      selectedContainerColor = MaterialTheme.colorScheme.primary,
                      selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                  modifier = Modifier.testTag("utme_subject_tab_$subId"),
                )
              }
            }
          }

          // CBT Question Navigator Grid
          Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            questions.indices.forEach { i ->
              val answered = cbtState.selectedAnswers.containsKey(i)
              val flagged = i in cbtState.flaggedQuestions
              val active = i == currentIndex
              val pillBg =
                when {
                  active -> MaterialTheme.colorScheme.primary
                  flagged -> MaterialTheme.colorScheme.tertiaryContainer
                  answered -> semantics.successContainer
                  else -> MaterialTheme.colorScheme.surface
                }
              val pillBorder =
                when {
                  active -> MaterialTheme.colorScheme.primary
                  flagged -> MaterialTheme.colorScheme.tertiary
                  answered -> semantics.success
                  else -> MaterialTheme.colorScheme.outline
                }
              val pillText =
                when {
                  active -> MaterialTheme.colorScheme.onPrimary
                  flagged -> MaterialTheme.colorScheme.onTertiaryContainer
                  answered -> semantics.onSuccessContainer
                  else -> MaterialTheme.colorScheme.onSurface
                }
              Box(
                modifier =
                  Modifier.size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(pillBg)
                    .border(1.5.dp, pillBorder, RoundedCornerShape(10.dp))
                    .clickable { onGoToQuestion(i) },
                contentAlignment = Alignment.Center,
              ) {
                Text(
                  text = "${i + 1}",
                  style = MaterialTheme.typography.labelLarge.copy(fontFeatureSettings = "tnum"),
                  color = pillText,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }

          // Single Question Card (One-by-One Display)
          LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            item {
              Column(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
              ) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically,
                ) {
                  Text(
                    text = "Q${currentIndex + 1}/${questions.size} • ${question.boardStyleLabel} • ${question.topic}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                  )
                  IconButton(onClick = { onToggleFlag(currentIndex) }) {
                    Icon(
                      imageVector = if (isFlagged) Icons.Filled.Flag else Icons.Filled.OutlinedFlag,
                      contentDescription = "Flag question for review",
                      tint =
                        if (isFlagged) MaterialTheme.colorScheme.tertiary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                  }
                }

                if (!question.passageText.isNullOrBlank()) {
                  var showPassage by remember(question.id) { mutableStateOf(true) }
                  Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(12.dp),
                    modifier =
                      Modifier.fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(12.dp))
                        .testTag("cbt_passage_card"),
                  ) {
                    Column(
                      modifier = Modifier.padding(12.dp),
                      verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                      Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                      ) {
                        Text(
                          text = "PASSAGE / CONTEXT EXTRACT",
                          style = MaterialTheme.typography.labelSmall,
                          color = MaterialTheme.colorScheme.onSecondaryContainer,
                          fontWeight = FontWeight.Bold,
                        )
                        Text(
                          text = if (showPassage) "Hide Passage" else "Read Passage",
                          style = MaterialTheme.typography.labelSmall,
                          color = MaterialTheme.colorScheme.primary,
                          fontWeight = FontWeight.Bold,
                          modifier = Modifier.clickable { showPassage = !showPassage }.padding(4.dp),
                        )
                      }
                      if (showPassage) {
                        ScientificText(
                          text = question.passageText,
                          style = MaterialTheme.typography.bodyMedium,
                          color = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                      }
                    }
                  }
                }

                ScientificText(
                  text = question.question,
                  style = MaterialTheme.typography.titleMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                )

                question.diagramSpec?.let { spec ->
                  QuestionDiagramCard(
                    spec = spec,
                    modifier = Modifier.padding(vertical = 4.dp).testTag("cbt_question_diagram"),
                  )
                }

                question.options.forEachIndexed { optIdx, optText ->
                  val chosen = cbtState.selectedAnswers[currentIndex] == optIdx
                  Row(
                    modifier =
                      Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                          if (chosen) MaterialTheme.colorScheme.primaryContainer
                          else MaterialTheme.colorScheme.background
                        )
                        .border(
                          1.5.dp,
                          if (chosen) MaterialTheme.colorScheme.primary
                          else MaterialTheme.colorScheme.outline,
                          RoundedCornerShape(12.dp),
                        )
                        .clickable { onSelectAnswer(currentIndex, optIdx) }
                        .testTag("cbt_option_$optIdx")
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                  ) {
                    Box(
                      modifier =
                        Modifier.size(28.dp)
                          .clip(CircleShape)
                          .background(
                            if (chosen) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surfaceVariant
                          ),
                      contentAlignment = Alignment.Center,
                    ) {
                      Text(
                        text = ('A' + optIdx).toString(),
                        style = MaterialTheme.typography.labelMedium,
                        color =
                          if (chosen) MaterialTheme.colorScheme.onPrimary
                          else MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                      )
                    }
                    ScientificText(
                      text = optText,
                      style = MaterialTheme.typography.bodyLarge,
                      color =
                        if (chosen) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurface,
                      fontWeight = if (chosen) FontWeight.SemiBold else FontWeight.Normal,
                    )
                  }
                }
              }
            }
          }

          // Prev / Next Controls
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(
              onClick = onPreviousQuestion,
              enabled = currentIndex > 0,
              modifier = Modifier.weight(1f).height(50.dp).testTag("cbt_prev_button"),
              shape = RoundedCornerShape(14.dp),
            ) {
              Text("← Previous")
            }
            Button(
              onClick = {
                if (currentIndex < questions.lastIndex) onNextQuestion() else showSubmitConfirm = true
              },
              modifier = Modifier.weight(1f).height(50.dp).testTag("cbt_next_button"),
              shape = RoundedCornerShape(14.dp),
            ) {
              Text(
                text = if (currentIndex < questions.lastIndex) "Next →" else "Finish & Grade",
                fontWeight = FontWeight.Bold,
              )
            }
          }
        }
      }

      CbtExamStage.SUMMARY -> {
        val summary = cbtState.resultSummary
        if (summary != null) {
          PostExamSummaryScreen(
            summary = summary,
            onRetakeMock = onRetakeExam,
            onDrillMissed = { onDrillMissedQuestions(summary.reviewItems) },
            onSaveToNotebook = onSaveToNotebook,
            onBackToSetup = onReturnToSetup,
            modifier = Modifier.fillMaxSize(),
          )
        }
      }
    }
  }

  if (showQuestionGrid && cbtState.stage == CbtExamStage.RUNNING) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val allIndices = cbtState.questions.indices.toList()
    val filteredIndices =
      when (gridFilter) {
        1 -> allIndices.filter { !cbtState.selectedAnswers.containsKey(it) }
        2 -> allIndices.filter { it in cbtState.flaggedQuestions }
        else -> allIndices
      }
    ModalBottomSheet(
      onDismissRequest = { showQuestionGrid = false },
      sheetState = sheetState,
      containerColor = MaterialTheme.colorScheme.surface,
    ) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = "Question Navigator (${cbtState.selectedAnswers.size}/${cbtState.questions.size})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
          )
          TextButton(onClick = { showQuestionGrid = false }) { Text("Close") }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          val unansCount = cbtState.questions.size - cbtState.selectedAnswers.size
          val flagCount = cbtState.flaggedQuestions.size
          listOf(0 to "All (${cbtState.questions.size})", 1 to "Unanswered ($unansCount)", 2 to "Flagged ($flagCount)")
            .forEach { (fIdx, fLabel) ->
              FilterChip(
                selected = gridFilter == fIdx,
                onClick = { gridFilter = fIdx },
                label = { Text(fLabel, style = MaterialTheme.typography.labelSmall) },
              )
            }
        }
        if (filteredIndices.isEmpty()) {
          Text(
            text = "No questions match this filter.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(vertical = 12.dp),
          )
        } else {
          LazyColumn(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            items(filteredIndices.chunked(6).size) { rowIdx ->
              val chunk = filteredIndices.chunked(6)[rowIdx]
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                chunk.forEach { qIdx ->
                  val answered = cbtState.selectedAnswers.containsKey(qIdx)
                  val flagged = qIdx in cbtState.flaggedQuestions
                  val active = qIdx == cbtState.currentIndex
                  val bg =
                    when {
                      active -> MaterialTheme.colorScheme.primary
                      flagged -> MaterialTheme.colorScheme.tertiaryContainer
                      answered -> semantics.successContainer
                      else -> MaterialTheme.colorScheme.background
                    }
                  val fg =
                    when {
                      active -> MaterialTheme.colorScheme.onPrimary
                      flagged -> MaterialTheme.colorScheme.onTertiaryContainer
                      answered -> semantics.onSuccessContainer
                      else -> MaterialTheme.colorScheme.onSurface
                    }
                  Box(
                    modifier =
                      Modifier.weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(bg)
                        .border(
                          1.dp,
                          if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                          RoundedCornerShape(10.dp),
                        )
                        .clickable {
                          onGoToQuestion(qIdx)
                          showQuestionGrid = false
                        },
                    contentAlignment = Alignment.Center,
                  ) {
                    Text(
                      text = "${qIdx + 1}",
                      style = MaterialTheme.typography.labelLarge.copy(fontFeatureSettings = "tnum"),
                      color = fg,
                      fontWeight = FontWeight.Bold,
                    )
                  }
                }
                repeat(6 - chunk.size) {
                  Spacer(modifier = Modifier.weight(1f))
                }
              }
            }
          }
        }
      }
    }
  }

  if (showSubmitConfirm && cbtState.stage == CbtExamStage.RUNNING) {
    val total = cbtState.questions.size
    val answered = cbtState.selectedAnswers.size
    val unanswered = (total - answered).coerceAtLeast(0)
    val flagged = cbtState.flaggedQuestions.size
    val firstUnanswered = cbtState.questions.indices.firstOrNull { !cbtState.selectedAnswers.containsKey(it) }
    AlertDialog(
      onDismissRequest = { showSubmitConfirm = false },
      title = { Text("Submit Mock Paper?", fontWeight = FontWeight.Bold) },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Text("Answered: $answered / $total")
          Text("Unanswered: $unanswered")
          Text("Flagged for review: $flagged")
          if (unanswered > 0) {
            Text(
              text = "You still have $unanswered unanswered question(s). Submitting now will mark them as skipped.",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.error,
            )
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            showSubmitConfirm = false
            onSubmitExam()
          },
          modifier = Modifier.testTag("confirm_submit_cbt_button"),
        ) {
          Text("Submit Now")
        }
      },
      dismissButton = {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
          if (firstUnanswered != null) {
            TextButton(
              onClick = {
                showSubmitConfirm = false
                onGoToQuestion(firstUnanswered)
              }
            ) {
              Text("Jump Unanswered")
            }
          }
          TextButton(onClick = { showSubmitConfirm = false }) { Text("Cancel") }
        }
      },
    )
  }

  if (showCalculator) {
    ScientificCalculatorSheet(onDismiss = { showCalculator = false })
  }
}
