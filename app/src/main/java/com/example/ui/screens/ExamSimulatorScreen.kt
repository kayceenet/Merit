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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.UdacityCyan
import com.example.ui.theme.UdacityCyanInk
import com.example.ui.theme.UdacityNavy
import com.example.ui.theme.semanticColors
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
  var currentIndex by rememberIntStateOfZero()
  val selectedAnswers = remember { mutableStateMapOf<Int, Int>() }
  val flaggedQuestions = remember { mutableStateMapOf<Int, Boolean>() }
  var remainingSeconds by remember { mutableIntStateOf(300) }
  var totalAllowedSeconds by remember { mutableIntStateOf(300) }
  val semantics = MaterialTheme.semanticColors

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
        .map { i -> activeQuestions[i].topic }
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
                  .height(175.dp)
                  .clip(RoundedCornerShape(20.dp))
                  .background(UdacityNavy)
                  .border(1.5.dp, Color(0xFF28427B), RoundedCornerShape(20.dp)),
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
                        listOf(Color(0xF507122B), Color(0xD90B1633), Color(0x880B1633))
                      )
                    )
              )
              Column(
                modifier = Modifier.fillMaxSize().padding(18.dp),
                verticalArrangement = Arrangement.Bottom,
              ) {
                Surface(color = UdacityCyan, shape = RoundedCornerShape(6.dp)) {
                  Text(
                    text = "TIMED CBT & WAEC STUDIO",
                    style = MaterialTheme.typography.labelSmall,
                    color = UdacityCyanInk,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                  )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                  text = "JAMB CBT & WAEC Exam Lab",
                  style = MaterialTheme.typography.headlineSmall,
                  color = Color.White,
                )
                Text(
                  text = "Timed Computer-Based Test grid, Flag-for-Review, and WAEC Marking Schemes.",
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
                listOf("JAMB", "WAEC").forEach { board ->
                  val selected = selectedExamBoard == board
                  FilterChip(
                    selected = selected,
                    onClick = { selectedExamBoard = board },
                    label = {
                      Text(
                        text = if (board == "JAMB") "JAMB UTME CBT" else "WAEC SSCE + Marking Scheme",
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
                    onClick = { selectedSubjectId = track.id },
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

              Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(14.dp),
                modifier =
                  Modifier.fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(14.dp)),
              ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                  Text(
                    text = "Paper Blueprint: $effectiveCount Authentic Past Questions",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                  )
                  Text(
                    text =
                      if (selectedExamBoard == "JAMB")
                        "Includes official CBT countdown clock, question status grid, and instant UTME analytics."
                      else
                        "Includes WAEC Objective + Method/Accuracy (M1/A1) examiner marking schemes.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
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
                Text("Start Timed Mock Exam", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
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
                .background(semantics.classroomBar)
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
                tint = if (remainingSeconds < 60) Color(0xFFFCA5A5) else UdacityCyan,
              )
              Text(
                text = String.format("%02d:%02d", minutes, seconds),
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = JetBrainsMonoFontFamily),
                color = if (remainingSeconds < 60) Color(0xFFFCA5A5) else Color.White,
                fontWeight = FontWeight.Bold,
              )
            }
            Text(
              text = "${selectedAnswers.size}/${activeQuestions.size} Answered",
              style = MaterialTheme.typography.labelMedium,
              color = semantics.classroomSubtext,
            )
            Button(
              onClick = { submitMock() },
              shape = RoundedCornerShape(10.dp),
              colors =
                ButtonDefaults.buttonColors(
                  containerColor = UdacityCyan,
                  contentColor = UdacityCyanInk,
                ),
              modifier = Modifier.testTag("submit_cbt_button"),
            ) {
              Text("Submit", fontWeight = FontWeight.Bold)
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
                  Modifier.size(42.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(pillBg)
                    .border(1.5.dp, pillBorder, RoundedCornerShape(10.dp))
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
                    text = "Q${currentIndex + 1} • ${question.examType} ${question.year} • ${question.topic}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
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

                Text(
                  text = question.question,
                  style = MaterialTheme.typography.titleMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                )

                question.options.forEachIndexed { optIdx, optText ->
                  val chosen = selectedAnswers[currentIndex] == optIdx
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
                        .clickable { selectedAnswers[currentIndex] = optIdx }
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
                    Text(
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
              Text(
                text = if (currentIndex < activeQuestions.lastIndex) "Next →" else "Finish & Grade",
                fontWeight = FontWeight.Bold,
              )
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
                  .background(semantics.classroomBar)
                  .border(1.5.dp, Color(0xFF28427B), RoundedCornerShape(22.dp))
                  .padding(20.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Text(
                text = "EXAM DIAGNOSTIC REPORT",
                style = MaterialTheme.typography.labelSmall,
                color = UdacityCyan,
                fontWeight = FontWeight.Bold,
              )
              Text(
                text = "$correctCount / ${activeQuestions.size} Correct ($pct%)",
                style = MaterialTheme.typography.headlineMedium.copy(fontFamily = JetBrainsMonoFontFamily),
                color = Color.White,
              )
              Text(
                text = "Saved to your local analytics. Review step-by-step derivations below:",
                style = MaterialTheme.typography.bodySmall,
                color = semantics.classroomSubtext,
              )
              Spacer(modifier = Modifier.height(4.dp))
              Button(
                onClick = { stage = ExamStage.SETUP },
                shape = RoundedCornerShape(12.dp),
                colors =
                  ButtonDefaults.buttonColors(
                    containerColor = UdacityCyan,
                    contentColor = UdacityCyanInk,
                  ),
              ) {
                Text("Take Another Mock Paper", fontWeight = FontWeight.Bold)
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
                    if (isCorrect) semantics.success else MaterialTheme.colorScheme.error,
                    RoundedCornerShape(18.dp),
                  )
                  .padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Surface(
                color =
                  if (isCorrect) semantics.successContainer
                  else MaterialTheme.colorScheme.errorContainer,
                shape = RoundedCornerShape(99.dp),
              ) {
                Text(
                  text = "Q${idx + 1} (${q.examType} ${q.year}) • ${if (isCorrect) "CORRECT" else "MISSED"}",
                  style = MaterialTheme.typography.labelSmall,
                  color =
                    if (isCorrect) semantics.onSuccessContainer
                    else MaterialTheme.colorScheme.onErrorContainer,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                )
              }
              Text(
                text = q.question,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
              )
              Text(
                text = "Correct Option (${'A' + q.correctIndex}): ${q.options[q.correctIndex]}",
                style = MaterialTheme.typography.bodyMedium,
                color = semantics.success,
                fontWeight = FontWeight.Bold,
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
                  color = MaterialTheme.colorScheme.primary,
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

@Composable
private fun rememberIntStateOfZero() = remember { mutableIntStateOf(0) }
