package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.UdacityCyan
import com.example.ui.theme.UdacityCyanInk
import com.example.ui.theme.semanticColors
import com.example.ui.viewmodel.ExamResultSummary
import com.example.ui.viewmodel.QuestionReviewItem

private enum class ReviewFilter(val label: String) {
  ALL("All"),
  CORRECT("Correct"),
  INCORRECT("Incorrect"),
  SKIPPED("Skipped"),
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PostExamSummaryScreen(
  summary: ExamResultSummary,
  onRetakeMock: () -> Unit,
  onBackToSetup: () -> Unit,
  modifier: Modifier = Modifier,
) {
  BackHandler(onBack = onBackToSetup)

  var activeFilter by remember(summary) { mutableStateOf(ReviewFilter.ALL) }
  val semantics = MaterialTheme.semanticColors

  val filteredItems =
    remember(summary, activeFilter) {
      when (activeFilter) {
        ReviewFilter.ALL -> summary.reviewItems
        ReviewFilter.CORRECT -> summary.reviewItems.filter { it.isCorrect }
        ReviewFilter.INCORRECT -> summary.reviewItems.filter { !it.isCorrect && !it.isUnanswered }
        ReviewFilter.SKIPPED -> summary.reviewItems.filter { it.isUnanswered }
      }
    }

  val minutesTaken = summary.timeTakenSeconds / 60
  val secondsTaken = summary.timeTakenSeconds % 60
  val formattedTime = String.format("%02d:%02d", minutesTaken, secondsTaken)
  val avgSecondsPerQuestion =
    if (summary.totalQuestions > 0) summary.timeTakenSeconds / summary.totalQuestions else 0

  val verdictText =
    when {
      summary.percentage >= 80 -> "DISTINCTION"
      summary.percentage >= 70 -> "EXAM READY"
      summary.percentage >= 50 -> "DEVELOPING"
      else -> "NEEDS REVISION"
    }

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    LazyColumn(
      modifier = Modifier.widthIn(max = 900.dp).fillMaxSize().testTag("post_exam_summary_screen"),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
      // Udacity Dark Navy Post-Exam Hero Report Card
      item {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(22.dp))
              .background(semantics.classroomBar)
              .border(1.5.dp, Color(0xFF28427B), RoundedCornerShape(22.dp))
              .padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Surface(color = UdacityCyan, shape = RoundedCornerShape(6.dp)) {
              Text(
                text = "${summary.examBoard} • ${summary.subjectTitle.uppercase()}",
                style = MaterialTheme.typography.labelSmall,
                color = UdacityCyanInk,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
              )
            }
            Surface(
              color =
                if (summary.percentage >= 70) semantics.successContainer
                else MaterialTheme.colorScheme.tertiaryContainer,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = verdictText,
                style = MaterialTheme.typography.labelSmall,
                color =
                  if (summary.percentage >= 70) semantics.onSuccessContainer
                  else MaterialTheme.colorScheme.onTertiaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
          ) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
              Text(
                text = "${summary.percentage}%",
                style =
                  MaterialTheme.typography.displaySmall.copy(
                    fontFamily = JetBrainsMonoFontFamily
                  ),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("post_exam_percentage"),
              )
              Text(
                text = "${summary.correctCount} of ${summary.totalQuestions} questions correct",
                style = MaterialTheme.typography.titleSmall,
                color = semantics.classroomSubtext,
              )
            }

            Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
              ) {
                Icon(
                  Icons.Filled.Timer,
                  contentDescription = "Time taken",
                  tint = UdacityCyan,
                  modifier = Modifier.size(16.dp),
                )
                Text(
                  text = "Time: $formattedTime",
                  style =
                    MaterialTheme.typography.titleSmall.copy(
                      fontFamily = JetBrainsMonoFontFamily
                    ),
                  color = Color.White,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.testTag("post_exam_time_taken"),
                )
              }
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
              ) {
                Icon(
                  Icons.Filled.Bolt,
                  contentDescription = "XP earned",
                  tint = Color(0xFFFBBF24),
                  modifier = Modifier.size(16.dp),
                )
                Text(
                  text = "+${summary.xpEarned} XP • ${avgSecondsPerQuestion}s/Q",
                  style = MaterialTheme.typography.labelSmall,
                  color = Color(0xFFFDE68A),
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }

          LinearProgressIndicator(
            progress = { (summary.percentage / 100f).coerceIn(0.03f, 1f) },
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
            color = if (summary.percentage >= 70) semantics.success else UdacityCyan,
            trackColor = Color(0xFF1E3A6E),
          )

          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(
              onClick = onBackToSetup,
              modifier = Modifier.weight(1f).height(48.dp).testTag("post_exam_back_button"),
              shape = RoundedCornerShape(12.dp),
              colors =
                ButtonDefaults.outlinedButtonColors(
                  contentColor = Color.White,
                ),
            ) {
              Text("Exam Lab", fontWeight = FontWeight.SemiBold)
            }
            Button(
              onClick = onRetakeMock,
              modifier = Modifier.weight(1f).height(48.dp).testTag("post_exam_retake_button"),
              shape = RoundedCornerShape(12.dp),
              colors =
                ButtonDefaults.buttonColors(
                  containerColor = UdacityCyan,
                  contentColor = UdacityCyanInk,
                ),
            ) {
              Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Retake Paper", fontWeight = FontWeight.Bold)
            }
          }
        }
      }

      // Score Breakdown Tiles (Correct / Incorrect / Skipped)
      item {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          BreakdownMetricCard(
            label = "CORRECT",
            count = summary.correctCount,
            containerColor = semantics.successContainer,
            contentColor = semantics.onSuccessContainer,
            borderColor = semantics.success,
            modifier = Modifier.weight(1f),
          )
          BreakdownMetricCard(
            label = "INCORRECT",
            count = summary.wrongCount,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer,
            borderColor = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
          )
          BreakdownMetricCard(
            label = "SKIPPED",
            count = summary.unansweredCount,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            borderColor = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.weight(1f),
          )
        }
      }

      // Weak Topics Highlight
      if (summary.weakTopics.isNotEmpty()) {
        item {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Text(
              text = "Topics Flagged for Revision",
              style = MaterialTheme.typography.titleSmall,
              color = MaterialTheme.colorScheme.onSurface,
            )
            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              summary.weakTopics.forEach { topic ->
                Surface(
                  color = MaterialTheme.colorScheme.errorContainer,
                  shape = RoundedCornerShape(99.dp),
                  modifier =
                    Modifier.border(1.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(99.dp)),
                ) {
                  Text(
                    text = topic,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                  )
                }
              }
            }
          }
        }
      }

      // Filter Strip for Question Breakdown
      item {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "Question-by-Question Review",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
          )
          Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            ReviewFilter.entries.forEach { filter ->
              val count =
                when (filter) {
                  ReviewFilter.ALL -> summary.reviewItems.size
                  ReviewFilter.CORRECT -> summary.correctCount
                  ReviewFilter.INCORRECT -> summary.wrongCount
                  ReviewFilter.SKIPPED -> summary.unansweredCount
                }
              val selected = activeFilter == filter
              FilterChip(
                selected = selected,
                onClick = { activeFilter = filter },
                label = {
                  Text(
                    text = "${filter.label} ($count)",
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                  )
                },
                colors =
                  FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.surface,
                    labelColor = MaterialTheme.colorScheme.onSurface,
                  ),
                modifier = Modifier.testTag("summary_filter_${filter.name.lowercase()}"),
              )
            }
          }
        }
      }

      // Detailed Question Cards with User Answer vs Correct Answer
      items(filteredItems, key = { it.questionIndex }) { item ->
        QuestionReviewCard(item = item)
      }

      item { Spacer(modifier = Modifier.height(16.dp)) }
    }
  }
}

@Composable
private fun BreakdownMetricCard(
  label: String,
  count: Int,
  containerColor: Color,
  contentColor: Color,
  borderColor: Color,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier =
      modifier
        .clip(RoundedCornerShape(14.dp))
        .background(containerColor)
        .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
        .padding(vertical = 12.dp, horizontal = 8.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(2.dp),
  ) {
    Text(
      text = "$count",
      style = MaterialTheme.typography.titleLarge.copy(fontFamily = JetBrainsMonoFontFamily),
      color = contentColor,
      fontWeight = FontWeight.Bold,
    )
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall,
      color = contentColor,
      fontWeight = FontWeight.Bold,
    )
  }
}

@Composable
private fun QuestionReviewCard(item: QuestionReviewItem) {
  val semantics = MaterialTheme.semanticColors
  val q = item.question
  val statusBg =
    when {
      item.isCorrect -> semantics.successContainer
      item.isUnanswered -> MaterialTheme.colorScheme.tertiaryContainer
      else -> MaterialTheme.colorScheme.errorContainer
    }
  val statusFg =
    when {
      item.isCorrect -> semantics.onSuccessContainer
      item.isUnanswered -> MaterialTheme.colorScheme.onTertiaryContainer
      else -> MaterialTheme.colorScheme.onErrorContainer
    }
  val borderColor =
    when {
      item.isCorrect -> semantics.success
      item.isUnanswered -> MaterialTheme.colorScheme.tertiary
      else -> MaterialTheme.colorScheme.error
    }
  val statusIcon =
    when {
      item.isCorrect -> Icons.Filled.CheckCircle
      item.isUnanswered -> Icons.Filled.RadioButtonUnchecked
      else -> Icons.Filled.Cancel
    }
  val statusLabel =
    when {
      item.isCorrect -> "CORRECT"
      item.isUnanswered -> "SKIPPED"
      else -> "INCORRECT"
    }

  Column(
    modifier =
      Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(18.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.5.dp, borderColor, RoundedCornerShape(18.dp))
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Surface(color = statusBg, shape = RoundedCornerShape(99.dp)) {
        Row(
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
          Icon(
            imageVector = statusIcon,
            contentDescription = statusLabel,
            tint = statusFg,
            modifier = Modifier.size(14.dp),
          )
          Text(
            text = "Q${item.questionIndex + 1} • $statusLabel",
            style = MaterialTheme.typography.labelSmall,
            color = statusFg,
            fontWeight = FontWeight.Bold,
          )
        }
      }
      Text(
        text = "${q.examType} ${q.year} • ${item.timeSpentSeconds}s",
        style = MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFontFamily),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }

    Text(
      text = q.question,
      style = MaterialTheme.typography.titleSmall,
      color = MaterialTheme.colorScheme.onSurface,
    )

    // User Selection vs Correct Answer comparison
    val userIdx = item.selectedOptionIndex
    if (userIdx != null && userIdx in q.options.indices) {
      val userBg =
        if (item.isCorrect) semantics.successContainer
        else MaterialTheme.colorScheme.errorContainer
      val userFg =
        if (item.isCorrect) semantics.onSuccessContainer
        else MaterialTheme.colorScheme.onErrorContainer
      val userBorder =
        if (item.isCorrect) semantics.success else MaterialTheme.colorScheme.error
      Row(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(userBg)
            .border(1.dp, userBorder, RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Icon(
          imageVector = if (item.isCorrect) Icons.Filled.CheckCircle else Icons.Filled.Cancel,
          contentDescription = null,
          tint = userFg,
          modifier = Modifier.size(18.dp),
        )
        Text(
          text = "Your Answer (${'A' + userIdx}): ${q.options[userIdx]}",
          style = MaterialTheme.typography.bodyMedium,
          color = userFg,
          fontWeight = FontWeight.SemiBold,
        )
      }
    } else {
      Surface(
        color = MaterialTheme.colorScheme.tertiaryContainer,
        shape = RoundedCornerShape(10.dp),
        modifier =
          Modifier.fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(10.dp)),
      ) {
        Text(
          text = "Your Answer: Not answered (Skipped)",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onTertiaryContainer,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(10.dp),
        )
      }
    }

    if (!item.isCorrect && q.correctIndex in q.options.indices) {
      Row(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(semantics.successContainer)
            .border(1.dp, semantics.success, RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Icon(
          imageVector = Icons.Filled.CheckCircle,
          contentDescription = "Correct option",
          tint = semantics.onSuccessContainer,
          modifier = Modifier.size(18.dp),
        )
        Text(
          text = "Correct Answer (${'A' + q.correctIndex}): ${q.options[q.correctIndex]}",
          style = MaterialTheme.typography.bodyMedium,
          color = semantics.onSuccessContainer,
          fontWeight = FontWeight.Bold,
        )
      }
    }

    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(12.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
          .padding(12.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      Text(
        text = "Step-by-Step Solution",
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
      )
      Text(
        text = q.stepByStepSolution,
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
