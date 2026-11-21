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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumProgression
import com.example.ui.components.RocketScoreHero
import com.example.ui.components.ScientificText
import com.example.ui.components.StudioMetricTile
import com.example.ui.theme.semanticColors
import com.example.ui.viewmodel.ExamResultSummary
import com.example.ui.viewmodel.QuestionReviewItem
import java.util.Locale

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
  onDrillMissed: (() -> Unit)? = null,
  onSaveToNotebook: ((subjectId: String, title: String, content: String) -> Unit)? = null,
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
  val formattedTime = String.format(Locale.US, "%02d:%02d", minutesTaken, secondsTaken)
  val avgSecondsPerQuestion =
    if (summary.totalQuestions > 0) summary.timeTakenSeconds / summary.totalQuestions else 0

  val masteryBand = CurriculumProgression.bandForPercentage(summary.percentage.toLong())
  val headlineText =
    when {
      summary.percentage >= 80 -> "Outstanding Distinction!"
      summary.percentage >= 60 -> "Good job! Keep it up!"
      summary.percentage >= 40 -> "Solid effort — keep pushing!"
      else -> "Keep practicing — you've got this!"
    }
  val totalQ = summary.totalQuestions.coerceAtLeast(1).toFloat()
  val missedCount = summary.reviewItems.count { !it.isCorrect }

  Box(
    modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    contentAlignment = Alignment.TopCenter,
  ) {
    LazyColumn(
      modifier = Modifier.widthIn(max = 900.dp).fillMaxSize().testTag("post_exam_summary_screen"),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      // Minimal Top Header matching Left Phone ("Round results   ✕")
      item {
        Box(modifier = Modifier.fillMaxWidth()) {
          Text(
            text = "Exam results",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Center),
          )
          IconButton(
            onClick = onBackToSetup,
            modifier =
              Modifier.align(Alignment.CenterEnd)
                .minimumInteractiveComponentSize()
                .testTag("post_exam_back_button"),
          ) {
            Icon(
              imageVector = Icons.Filled.Close,
              contentDescription = "Close and return to Exam Lab",
              tint = MaterialTheme.colorScheme.onSurface,
            )
          }
        }
      }

      // Centered Encouragement Title + Rocket Circular Gauge matching Left Phone
      item {
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Text(
            text = headlineText,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
          )

          Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Surface(
              color = MaterialTheme.colorScheme.primaryContainer,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = "${summary.examBoard} • ${summary.subjectTitle}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
            if (summary.isUtmeCombo && summary.utmeAggregateOutOf400 != null) {
              Surface(
                color = semantics.indigoAccent,
                shape = RoundedCornerShape(99.dp),
              ) {
                Text(
                  text = "UTME ${summary.utmeAggregateOutOf400} / 400",
                  style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                  color = Color.White,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                )
              }
            }
          }

          RocketScoreHero(
            percentage = summary.percentage,
            displayText = "${summary.percentage}%",
            modifier = Modifier.padding(vertical = 4.dp).testTag("post_exam_percentage"),
          )

          Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
              Icon(
                Icons.Filled.Timer,
                contentDescription = "Time taken",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp),
              )
              Text(
                text = "Time $formattedTime • ${avgSecondsPerQuestion}s/Q",
                style = MaterialTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
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
                tint = semantics.coralAccent,
                modifier = Modifier.size(16.dp),
              )
              Text(
                text = "+${summary.xpEarned} XP • ${masteryBand.label}",
                style = MaterialTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
              )
            }
          }
        }
      }

      // 3 White Metric Tiles Row matching Left Phone ("Incorrect | Skipped | Correct")
      item {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          StudioMetricTile(
            count = summary.wrongCount,
            label = "Incorrect",
            barColor = semantics.coralAccent,
            fillRatio = summary.wrongCount / totalQ,
            modifier = Modifier.weight(1f),
          )
          StudioMetricTile(
            count = summary.unansweredCount,
            label = "Skipped",
            barColor = semantics.indigoAccent,
            fillRatio = summary.unansweredCount / totalQ,
            modifier = Modifier.weight(1f),
          )
          StudioMetricTile(
            count = summary.correctCount,
            label = "Correct",
            barColor = MaterialTheme.colorScheme.primary,
            fillRatio = summary.correctCount / totalQ,
            modifier = Modifier.weight(1f),
          )
        }
      }

      // UTME 4-Subject Score Breakdown Chips (if UTME Combo mode)
      if (summary.isUtmeCombo && summary.comboBreakdowns.isNotEmpty()) {
        item {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Text(
              text = "Subject UTME Breakdown (/100 each)",
              style = MaterialTheme.typography.titleSmall,
              color = MaterialTheme.colorScheme.onSurface,
              fontWeight = FontWeight.Bold,
            )
            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
              summary.comboBreakdowns.forEach { bd ->
                Surface(
                  color = MaterialTheme.colorScheme.primaryContainer,
                  shape = RoundedCornerShape(99.dp),
                ) {
                  Text(
                    text = "${bd.subjectName}: ${bd.scaledOutOf100}/100",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                  )
                }
              }
            }
          }
        }
      }

      // Full-Width Royal Blue Pill CTA ("Start new round") + Drill Missed Button
      item {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Button(
            onClick = onRetakeMock,
            modifier = Modifier.fillMaxWidth().height(52.dp).testTag("post_exam_retake_button"),
            shape = RoundedCornerShape(16.dp),
            colors =
              ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
              ),
          ) {
            Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Start new round",
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.Bold,
            )
          }

          if (missedCount > 0 && onDrillMissed != null) {
            OutlinedButton(
              onClick = onDrillMissed,
              modifier = Modifier.fillMaxWidth().height(48.dp).testTag("drill_missed_questions_button"),
              shape = RoundedCornerShape(16.dp),
            ) {
              Icon(Icons.Filled.FitnessCenter, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(8.dp))
              Text("Drill Missed Questions Now ($missedCount Qs)", fontWeight = FontWeight.Bold)
            }
          }
        }
      }

      // Weak Topics Highlight
      if (summary.weakTopics.isNotEmpty()) {
        item {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Text(
              text = "Topics Flagged for Revision",
              style = MaterialTheme.typography.titleSmall,
              color = MaterialTheme.colorScheme.onSurface,
              fontWeight = FontWeight.Bold,
            )
            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              summary.weakTopics.forEach { topic ->
                Surface(
                  color = MaterialTheme.colorScheme.errorContainer,
                  shape = RoundedCornerShape(99.dp),
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
            fontWeight = FontWeight.Bold,
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
                border = null,
                modifier = Modifier.testTag("summary_filter_${filter.name.lowercase()}"),
              )
            }
          }
        }
      }

      items(filteredItems, key = { it.questionIndex }) { item ->
        QuestionReviewCard(
          item = item,
          fallbackSubjectId = summary.subjectId,
          onSaveToNotebook = onSaveToNotebook,
        )
      }

      item { Spacer(modifier = Modifier.height(16.dp)) }
    }
  }
}

@Composable
private fun QuestionReviewCard(
  item: QuestionReviewItem,
  fallbackSubjectId: String = "general",
  onSaveToNotebook: ((subjectId: String, title: String, content: String) -> Unit)? = null,
) {
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
      item.isCorrect -> semantics.success.copy(alpha = 0.65f)
      item.isUnanswered -> MaterialTheme.colorScheme.outlineVariant
      else -> semantics.coralAccent.copy(alpha = 0.7f)
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
        .shadow(3.dp, RoundedCornerShape(20.dp))
        .clip(RoundedCornerShape(20.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, borderColor, RoundedCornerShape(20.dp))
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
        text = "${q.boardStyleLabel} • ${item.timeSpentSeconds}s",
        style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }

    if (!q.passageText.isNullOrBlank()) {
      Surface(
        color = semantics.softTintSurface,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
      ) {
        ScientificText(
          text = q.passageText,
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurface,
          modifier = Modifier.padding(12.dp),
        )
      }
    }

    ScientificText(
      text = q.question,
      style = MaterialTheme.typography.titleSmall,
      color = MaterialTheme.colorScheme.onSurface,
    )

    val userIdx = item.selectedOptionIndex
    if (userIdx != null && userIdx in q.options.indices) {
      val userBg =
        if (item.isCorrect) semantics.successContainer
        else MaterialTheme.colorScheme.errorContainer
      val userFg =
        if (item.isCorrect) semantics.onSuccessContainer
        else MaterialTheme.colorScheme.onErrorContainer
      Row(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(userBg)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Icon(
          imageVector = if (item.isCorrect) Icons.Filled.CheckCircle else Icons.Filled.Cancel,
          contentDescription = null,
          tint = userFg,
          modifier = Modifier.size(18.dp),
        )
        ScientificText(
          text = "Your Answer (${'A' + userIdx}): ${q.options[userIdx]}",
          style = MaterialTheme.typography.bodyMedium,
          color = userFg,
          fontWeight = FontWeight.SemiBold,
        )
      }
    } else {
      Surface(
        color = semantics.softTintSurface,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
      ) {
        Text(
          text = "Your Answer: Not answered (Skipped)",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(12.dp),
        )
      }
    }

    if (!item.isCorrect && q.correctIndex in q.options.indices) {
      Row(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(semantics.successContainer)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Icon(
          imageVector = Icons.Filled.CheckCircle,
          contentDescription = "Correct option",
          tint = semantics.onSuccessContainer,
          modifier = Modifier.size(18.dp),
        )
        ScientificText(
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
          .clip(RoundedCornerShape(14.dp))
          .background(semantics.softTintSurface)
          .padding(12.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      Text(
        text = "Step-by-Step Solution",
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
      )
      ScientificText(
        text = q.stepByStepSolution,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      if (!q.waecMarkingScheme.isNullOrBlank()) {
        ScientificText(
          text = "Examiner Marking Scheme: ${q.waecMarkingScheme}",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold,
        )
      }
      if (!item.isCorrect && q.remediationTip.isNotBlank()) {
        ScientificText(
          text = "Remediation Focus: ${q.remediationTip}",
          style = MaterialTheme.typography.labelSmall,
          color = semantics.coralAccent,
          fontWeight = FontWeight.SemiBold,
        )
      }
      if (onSaveToNotebook != null) {
        OutlinedButton(
          onClick = {
            onSaveToNotebook(
              item.subjectId.ifBlank { fallbackSubjectId.ifBlank { "general" } },
              "${q.topic} — Solution",
              "${q.question}\n\n${q.stepByStepSolution}",
            )
          },
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth().padding(top = 4.dp).height(48.dp),
        ) {
          Icon(Icons.Filled.BookmarkAdd, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(Modifier.width(6.dp))
          Text("Save Explanation to Notebook", fontWeight = FontWeight.SemiBold)
        }
      }
    }
  }
}
