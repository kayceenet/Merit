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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.OutlinedFlag
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.PracticeQuestion
import com.example.ui.theme.DeepNavyBanner
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.MasteryEmerald
import kotlinx.coroutines.delay

private enum class ExamStage {
  SETUP,
  RUNNING,
  RESULTS,
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExamSimulatorScreen(
  initialSubjectId: String?,
  onRecordExamAttempt: (
    examType: String,
    subjectId: String,
    subjectTitle: String,
    correctCount: Long,
    totalQuestions: Long,
    durationSeconds: Long,
    weakTopics: List<String>,
  ) -> Unit,
  modifier: Modifier = Modifier,
) {
  var selectedSubjectId by remember(initialSubjectId) {
    mutableStateOf(initialSubjectId ?: CurriculumCatalog.tracks.first().id)
  }
  var selectedExamBoard by remember { mutableStateOf("JAMB") }
  var stage by remember { mutableStateOf(ExamStage.SETUP) }

  var activeQuestions by remember { mutableStateOf<List<PracticeQuestion>>(emptyList()) }
  var currentIndex by remember { mutableIntStateOf(0) }
  val selectedAnswers = remember { mutableStateMapOf<Int, Int>() }
  val flaggedQuestions = remember { mutableStateMapOf<Int, Boolean>() }
  var remainingSeconds by remember { mutableIntStateOf(300) }
  var totalAllowedSeconds by remember { mutableIntStateOf(300) }

  fun launchMock() {
    val allForSubject = CurriculumCatalog.pastQuestionsFor(selectedSubjectId, selectedExamBoard)
    val fallback =
      if (allForSubject.isNotEmpty()) allForSubject
      else CurriculumCatalog.pastQuestionsFor(selectedSubjectId, "ALL")
    activeQuestions = fallback
    selectedAnswers.clear()
    flaggedQuestions.clear()
    currentIndex = 0
    totalAllowedSeconds = (fallback.size * 75).coerceAtLeast(180)
    remainingSeconds = totalAllowedSeconds
    stage = ExamStage.RUNNING
  }

  fun submitMock() {
    if (stage != ExamStage.RUNNING) return
    val track = CurriculumCatalog.trackById(selectedSubjectId)
    val correctCount =
      activeQuestions.indices.count { i -> selectedAnswers[i] == activeQuestions[i].correctIndex }
    val weakTopics =
      activeQuestions.indices
        .filter { i -> selectedAnswers[i] != activeQuestions[i].correctIndex }
        .map { i -> activeQuestions[itOrIndex(i)].topic }
        .distinct()
    val elapsed = (totalAllowedSeconds - remainingSeconds).coerceAtLeast(1)
    onRecordExamAttempt(
      selectedExamBoard,
      selectedSubjectId,
      track?.shortName ?: selectedSubjectId,
      correctCount.toLong(),
      activeQuestions.size.toLong().coerceAtLeast(1L),
      elapsed.toLong(),
      weakTopics,
    )
    stage = ExamStage.RESULTS
  }

  if (stage == ExamStage.RUNNING) {
    BackHandler { stage = ExamStage.SETUP }
    LaunchedEffect(stage) {
      while (stage == ExamStage.RUNNING && remainingSeconds > 0) {
        delay(1000L)
        remainingSeconds--
      }
      if (stage == ExamStage.RUNNING && remainingSeconds <= 0) {
        submitMock()
      }
    }
  } else if (stage == ExamStage.RESULTS) {
    BackHandler { stage = ExamStage.SETUP }
  }

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    when (stage) {
      ExamStage.SETUP -> {
        val previewQuestions = CurriculumCatalog.pastQuestionsFor(selectedSubjectId, selectedExamBoard)
        val effectiveCount =
          if (previewQuestions.isNotEmpty()) previewQuestions.size
          else CurriculumCatalog.pastQuestionsFor(selectedSubjectId, "ALL").size

        LazyColumn(
          modifier = Modifier.widthIn(max = 880.dp).fillMaxSize(),
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
          item {
            Box(
              modifier =
                Modifier.fillMaxWidth()
                  .height(170.dp)
                  .clip(RoundedCornerShape(22.dp))
                  .background(DeepNavyBanner),
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
                        listOf(Color(0xEB071430), Color(0x990A1D45), Color(0x220A1D45))
                      )
                    )
              )
              Column(
                modifier = Modifier.fillMaxSize().padding(18.dp),
                verticalArrangement = Arrangement.Bottom,
              ) {
                Text(
                  text = "JAMB CBT & WAEC Exam Studio",
                  style = MaterialTheme.typography.headlineSmall,
                  color = Color.White,
                )
                Text(
                  text = "Timed Computer-Based Test grid, Flag-for-Review, and WAEC Marking Schemes.",
                  style = MaterialTheme.typography.bodySmall,
                  color = Color(0xFFD6E2FF),
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
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
                  .padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
              Text("1. Select Exam Board Mode", style = MaterialTheme.typography.titleMedium)
              Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                listOf("JAMB", "WAEC").forEach { board ->
                  FilterChip(
                    selected = selectedExamBoard == board,
                    onClick = { selectedExamBoard = board },
                    label = {
                      Text(if (board == "JAMB") "JAMB UTME CBT" else "WAEC SSCE + Marking Scheme")
                    },
                    modifier = Modifier.testTag("exam_board_$board"),
                  )
                }
              }

              Text("2. Select Subject Paper", style = MaterialTheme.typography.titleMedium)
              FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                CurriculumCatalog.tracks.forEach { track ->
                  FilterChip(
                    selected = selectedSubjectId == track.id,
                    onClick = { selectedSubjectId = track.id },
                    label = { Text(track.shortName) },
                    modifier = Modifier.testTag("exam_subject_${track.id}"),
                  )
                }
              }

              Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
              ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                  Text(
                    text = "Paper Blueprint: $effectiveCount Authentic Past Questions",
                    style = MaterialTheme.typography.titleSmall,
                  )
                  Text(
                    text =
                      if (selectedExamBoard == "JAMB")
                        "Includes official CBT countdown clock, question status grid, and instant UTME analytics."
                      else
                        "Includes WAEC Objective + Method/Accuracy (M1/A1) examiner marking schemes.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                  )
                }
              }

              Button(
                onClick = { launchMock() },
                enabled = effectiveCount > 0,
                modifier = Modifier.fillMaxWidth().height(54.dp).testTag("start_mock_exam_button"),
                shape = RoundedCornerShape(14.dp),
              ) {
                Icon(Icons.Filled.PlayArrow, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Start Timed Mock Exam", style = MaterialTheme.typography.titleMedium)
              }
            }
          }
        }
      }

      ExamStage.RUNNING -> {
        val question = activeQuestions[currentIndex]
        val minutes = remainingSeconds / 60
        val seconds = remainingSeconds % 60
        val isFlagged = flaggedQuestions[currentIndex] == true

        Column(
          modifier = Modifier.widthIn(max = 880.dp).fillMaxSize().padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          // Top CBT Timer & Submit Header
          Row(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp),
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
                tint =
                  if (remainingSeconds < 60) MaterialTheme.colorScheme.error
                  else MaterialTheme.colorScheme.primary,
              )
              Text(
                text = String.format("%02d:%02d", minutes, seconds),
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = JetBrainsMonoFontFamily),
                color =
                  if (remainingSeconds < 60) MaterialTheme.colorScheme.error
                  else MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
              )
            }
            Text(
              text = "${selectedAnswers.size}/${activeQuestions.size} Answered",
              style = MaterialTheme.typography.labelMedium,
            )
            Button(
              onClick = { submitMock() },
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.testTag("submit_cbt_button"),
            ) {
              Text("Submit")
            }
          }

          // CBT Question Navigator Grid
          Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            activeQuestions.indices.forEach { i ->
              val answered = selectedAnswers.containsKey(i)
              val flagged = flaggedQuestions[i] == true
              val active = i == currentIndex
              val pillBg =
                when {
                  active -> MaterialTheme.colorScheme.primary
                  flagged -> MaterialTheme.colorScheme.tertiaryContainer
                  answered -> MasteryEmerald.copy(alpha = 0.20f)
                  else -> MaterialTheme.colorScheme.surfaceVariant
                }
              val pillText =
                when {
                  active -> MaterialTheme.colorScheme.onPrimary
                  flagged -> MaterialTheme.colorScheme.onTertiaryContainer
                  answered -> MasteryEmerald
                  else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
              Box(
                modifier =
                  Modifier.size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(pillBg)
                    .clickable { currentIndex = i },
                contentAlignment = Alignment.Center,
              ) {
                Text(
                  text = "${i + 1}",
                  style = MaterialTheme.typography.labelLarge.copy(fontFamily = JetBrainsMonoFontFamily),
                  color = pillText,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }

          // Question Card
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
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
              ) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically,
                ) {
                  Text(
                    text = "Q${currentIndex + 1} • ${question.examType} ${question.year} • ${question.topic}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                  )
                  IconButton(onClick = { flaggedQuestions[currentIndex] = !isFlagged }) {
                    Icon(
                      imageVector = if (isFlagged) Icons.Filled.Flag else Icons.Filled.OutlinedFlag,
                      contentDescription = "Flag question for review",
                      tint =
                        if (isFlagged) MaterialTheme.colorScheme.tertiary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                  }
                }

                Text(text = question.question, style = MaterialTheme.typography.titleMedium)

                question.options.forEachIndexed { optIdx, optText ->
                  val chosen = selectedAnswers[currentIndex] == optIdx
                  Row(
                    modifier =
                      Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                          if (chosen) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f)
                          else MaterialTheme.colorScheme.background
                        )
                        .border(
                          1.5.dp,
                          if (chosen) MaterialTheme.colorScheme.primary
                          else MaterialTheme.colorScheme.outlineVariant,
                          RoundedCornerShape(12.dp),
                        )
                        .clickable { selectedAnswers[currentIndex] = optIdx }
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                  ) {
                    Box(
                      modifier =
                        Modifier.size(26.dp)
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
                          else MaterialTheme.colorScheme.onSurfaceVariant,
                      )
                    }
                    Text(text = optText, style = MaterialTheme.typography.bodyLarge)
                  }
                }
              }
            }
          }

          // Prev / Next Controls
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(
              onClick = { if (currentIndex > 0) currentIndex-- },
              enabled = currentIndex > 0,
              modifier = Modifier.weight(1f).height(50.dp),
              shape = RoundedCornerShape(14.dp),
            ) {
              Text("← Previous")
            }
            Button(
              onClick = {
                if (currentIndex < activeQuestions.lastIndex) currentIndex++ else submitMock()
              },
              modifier = Modifier.weight(1f).height(50.dp),
              shape = RoundedCornerShape(14.dp),
            ) {
              Text(if (currentIndex < activeQuestions.lastIndex) "Next →" else "Finish & Grade")
            }
          }
        }
      }

      ExamStage.RESULTS -> {
        val correctCount =
          activeQuestions.indices.count { i -> selectedAnswers[i] == activeQuestions[i].correctIndex }
        val pct =
          if (activeQuestions.isEmpty()) 0
          else (correctCount * 100) / activeQuestions.size

        LazyColumn(
          modifier = Modifier.widthIn(max = 880.dp).fillMaxSize(),
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
          item {
            Column(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(22.dp))
                  .background(MaterialTheme.colorScheme.primaryContainer)
                  .padding(20.dp),
              verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
              Text(
                text = "EXAM DIAGNOSTIC REPORT",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
              )
              Text(
                text = "$correctCount / ${activeQuestions.size} Correct ($pct%)",
                style = MaterialTheme.typography.headlineMedium.copy(fontFamily = JetBrainsMonoFontFamily),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
              )
              Text(
                text = "Recorded to your cloud analytics. Review step-by-step derivations below:",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
              )
              Spacer(modifier = Modifier.height(6.dp))
              Button(
                onClick = { stage = ExamStage.SETUP },
                shape = RoundedCornerShape(12.dp),
              ) {
                Text("Take Another Mock Paper")
              }
            }
          }

          itemsIndexed(activeQuestions) { idx, q ->
            val userChoice = selectedAnswers[idx]
            val isCorrect = userChoice == q.correctIndex
            Column(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(18.dp))
                  .background(MaterialTheme.colorScheme.surface)
                  .border(
                    1.5.dp,
                    if (isCorrect) MasteryEmerald.copy(alpha = 0.5f)
                    else MaterialTheme.colorScheme.error.copy(alpha = 0.5f),
                    RoundedCornerShape(18.dp),
                  )
                  .padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Text(
                text = "Q${idx + 1} (${q.examType} ${q.year}) • ${if (isCorrect) "CORRECT" else "MISSED"}",
                style = MaterialTheme.typography.labelSmall,
                color = if (isCorrect) MasteryEmerald else MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold,
              )
              Text(text = q.question, style = MaterialTheme.typography.titleSmall)
              Text(
                text = "Correct Option (${'A' + q.correctIndex}): ${q.options[q.correctIndex]}",
                style = MaterialTheme.typography.bodyMedium,
                color = MasteryEmerald,
                fontWeight = FontWeight.SemiBold,
              )
              Text(
                text = "Derivation: ${q.stepByStepSolution}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
              if (!q.waecMarkingScheme.isNullOrBlank()) {
                Text(
                  text = "WAEC Marking Scheme: ${q.waecMarkingScheme}",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.secondary,
                )
              }
            }
          }
        }
      }
    }
  }
}

private fun itOrIndex(i: Int): Int = i
