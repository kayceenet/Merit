package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.OutlinedFlag
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.curriculum.CurriculumCatalog
import com.example.ui.components.ScientificText
import com.example.ui.theme.CourseraAzureAccent
import com.example.ui.theme.CourseraAzureInk
import com.example.ui.theme.CourseraDeepNavy
import com.example.ui.theme.CourseraNavyElevated
import com.example.ui.theme.semanticColors
import com.example.ui.viewmodel.CbtExamStage
import com.example.ui.viewmodel.CbtExamState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExamSimulatorScreen(
  initialSubjectId: String?,
  cbtState: CbtExamState,
  onSelectSubject: (String) -> Unit,
  onSelectBoard: (String) -> Unit,
  onSelectQuestionLimit: (Int) -> Unit,
  onStartExam: (subjectId: String, examBoard: String, questionLimit: Int) -> Unit,
  onSelectAnswer: (questionIndex: Int, optionIndex: Int) -> Unit,
  onToggleFlag: (Int) -> Unit,
  onGoToQuestion: (Int) -> Unit,
  onNextQuestion: () -> Unit,
  onPreviousQuestion: () -> Unit,
  onTogglePauseTimer: () -> Unit,
  onSubmitExam: () -> Unit,
  onRetakeExam: () -> Unit,
  onReturnToSetup: () -> Unit,
  modifier: Modifier = Modifier,
) {
  LaunchedEffect(initialSubjectId) {
    if (!initialSubjectId.isNullOrBlank() && cbtState.stage == CbtExamStage.SETUP) {
      onSelectSubject(initialSubjectId)
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

        LazyColumn(
          modifier = Modifier.widthIn(max = 880.dp).fillMaxSize(),
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
          item {
            Box(
              modifier =
                Modifier.fillMaxWidth()
                  .height(175.dp)
                  .clip(RoundedCornerShape(20.dp))
                  .background(CourseraDeepNavy)
                  .border(1.5.dp, CourseraNavyElevated, RoundedCornerShape(20.dp)),
            ) {
              Image(
                painter = painterResource(id = R.drawable.img_cbt_banner_1794740699279),
                contentDescription = "CBT Exam Simulator Laboratory",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
              )
              Box(
                modifier =
                  Modifier.fillMaxSize()
                    .background(
                      Brush.horizontalGradient(
                        listOf(Color(0xF500255C), Color(0xD900255C), Color(0x880D3575))
                      )
                    )
              )
              Column(
                modifier = Modifier.fillMaxSize().padding(18.dp),
                verticalArrangement = Arrangement.Bottom,
              ) {
                Surface(color = CourseraAzureAccent, shape = RoundedCornerShape(6.dp)) {
                  Text(
                    text = "TIMED CBT • JAMB • WAEC • NECO",
                    style = MaterialTheme.typography.labelSmall,
                    color = CourseraAzureInk,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                  )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                  text = "JAMB, WAEC & NECO Exam Lab",
                  style = MaterialTheme.typography.headlineSmall,
                  color = Color.White,
                )
                Text(
                  text = "Syllabus-aligned practice bank, state-managed timer, and detailed post-exam diagnostics.",
                  style = MaterialTheme.typography.bodySmall,
                  color = Color(0xFFE2E8F0),
                )
              }
            }
          }

          item {
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
                text = "1. Select Exam Board Mode",
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
                text = "2. Select Subject Paper",
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
                text = "3. Paper Length",
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
                horizontalArrangement = Arrangement.spacedBy(6.dp),
              ) {
                Icon(
                  Icons.Filled.Timer,
                  contentDescription = "Remaining time",
                  tint = if (cbtState.remainingSeconds < 60) Color(0xFFFCA5A5) else CourseraAzureAccent,
                )
                Text(
                  text = String.format("%02d:%02d", minutes, seconds),
                  style =
                    MaterialTheme.typography.titleMedium.copy(
                      fontFamily = FontFamily.Default,
                      fontFeatureSettings = "tnum",
                    ),
                  color = if (cbtState.remainingSeconds < 60) Color(0xFFFCA5A5) else Color.White,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.testTag("cbt_timer_text"),
                )
                IconButton(
                  onClick = onTogglePauseTimer,
                  modifier = Modifier.size(36.dp).testTag("cbt_pause_timer_button"),
                ) {
                  Icon(
                    imageVector = if (cbtState.isTimerPaused) Icons.Filled.PlayArrow else Icons.Filled.Pause,
                    contentDescription = if (cbtState.isTimerPaused) "Resume timer" else "Pause timer",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp),
                  )
                }
              }

              Text(
                text =
                  if (cbtState.isTimerPaused) "PAUSED"
                  else "${cbtState.selectedAnswers.size}/${questions.size} Answered",
                style = MaterialTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
                color = if (cbtState.isTimerPaused) Color(0xFFFDE68A) else semantics.classroomSubtext,
                fontWeight = FontWeight.Bold,
              )

              Button(
                onClick = onSubmitExam,
                shape = RoundedCornerShape(10.dp),
                colors =
                  ButtonDefaults.buttonColors(
                    containerColor = CourseraAzureAccent,
                    contentColor = CourseraAzureInk,
                  ),
                modifier = Modifier.testTag("submit_cbt_button"),
              ) {
                Text("Submit", fontWeight = FontWeight.Bold)
              }
            }
            LinearProgressIndicator(
              progress = { timerProgress.coerceIn(0f, 1f) },
              modifier = Modifier.fillMaxWidth().height(4.dp),
              color = if (cbtState.remainingSeconds < 60) Color(0xFFFCA5A5) else CourseraAzureAccent,
              trackColor = CourseraNavyElevated,
            )
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

                ScientificText(
                  text = question.question,
                  style = MaterialTheme.typography.titleMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                )

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
                if (currentIndex < questions.lastIndex) onNextQuestion() else onSubmitExam()
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
            onBackToSetup = onReturnToSetup,
            modifier = Modifier.fillMaxSize(),
          )
        }
      }
    }
  }
}
