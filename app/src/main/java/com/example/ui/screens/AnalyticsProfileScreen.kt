package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.UserProfile
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.semanticColors
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnalyticsProfileScreen(
  profile: UserProfile?,
  attempts: List<ExamAttempt>,
  progressList: List<LessonProgress>,
  onUpdateGoal: (displayName: String, targetExam: String, targetScore: Long) -> Unit,
  modifier: Modifier = Modifier,
) {
  var displayName by remember(profile?.displayName) {
    mutableStateOf(profile?.displayName ?: "Scholar")
  }
  var targetExam by remember(profile?.targetExam) {
    mutableStateOf(profile?.targetExam ?: "BOTH")
  }
  var targetScore by remember(profile?.targetScore) {
    mutableFloatStateOf((profile?.targetScore ?: 320L).toFloat())
  }

  val recentAttempts = remember(attempts) { attempts.take(6).reversed() }
  val weakTopicCounts =
    remember(attempts) {
      attempts.flatMap { it.weakTopics }.groupingBy { it }.eachCount().entries.sortedByDescending { it.value }
    }
  val masteredLessons = remember(progressList) { progressList.count { it.isCompleted } }
  val avgScore =
    remember(attempts) {
      if (attempts.isEmpty()) 0 else attempts.map { it.percentage.toInt() }.average().roundToInt()
    }
  val semantics = MaterialTheme.semanticColors

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    LazyColumn(
      modifier = Modifier.widthIn(max = 880.dp).fillMaxSize(),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      item {
        Text(
          text = "Performance Analytics & Goals",
          style = MaterialTheme.typography.headlineMedium,
          color = MaterialTheme.colorScheme.onBackground,
        )
      }

      // 4-Metric Udacity Telemetry Summary Strip
      item {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          TelemetryTile("MASTERED", "$masteredLessons", Modifier.weight(1f))
          TelemetryTile("AVG MOCK", if (attempts.isEmpty()) "—" else "$avgScore%", Modifier.weight(1f))
          TelemetryTile("XP", "${profile?.totalXp ?: 0}", Modifier.weight(1f))
          TelemetryTile("STREAK", "${profile?.streakDays ?: 1}d", Modifier.weight(1f))
        }
      }

      // Score Trend Chart Card
      item {
        val barColor = MaterialTheme.colorScheme.primary
        val passColor = semantics.success
        val gridColor = MaterialTheme.colorScheme.outline

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
              text = "Recent Mock Exam Accuracy (%)",
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
              text = "70% pass line",
              style = MaterialTheme.typography.labelSmall,
              color = semantics.success,
              fontWeight = FontWeight.Bold,
            )
          }

          if (recentAttempts.isEmpty()) {
            Surface(
              color = MaterialTheme.colorScheme.background,
              shape = RoundedCornerShape(14.dp),
              modifier =
                Modifier.fillMaxWidth()
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp)),
            ) {
              Text(
                text = "Complete your first JAMB CBT or WAEC mock in the Exam Lab to generate score telemetry.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(14.dp),
              )
            }
          } else {
            Canvas(
              modifier =
                Modifier.fillMaxWidth()
                  .height(155.dp)
                  .clip(RoundedCornerShape(14.dp))
                  .background(MaterialTheme.colorScheme.background)
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
                  .padding(12.dp)
            ) {
              val h = size.height
              val w = size.width
              drawLine(gridColor, Offset(0f, h * 0.3f), Offset(w, h * 0.3f), strokeWidth = 2f)
              val slotW = w / recentAttempts.size.coerceAtLeast(1)
              val barW = (slotW * 0.52f).coerceAtMost(64f)
              recentAttempts.forEachIndexed { idx, att ->
                val pct = (att.percentage.toFloat() / 100f).coerceIn(0.05f, 1f)
                val barH = h * pct
                val left = idx * slotW + (slotW - barW) / 2f
                drawRoundRect(
                  color = if (att.percentage >= 70L) passColor else barColor,
                  topLeft = Offset(left, h - barH),
                  size = Size(barW, barH),
                  cornerRadius = CornerRadius(12f, 12f),
                )
              }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
              recentAttempts.forEach { att ->
                Text(
                  text = "${att.percentage}%",
                  style = MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFontFamily),
                  color = if (att.percentage >= 70L) semantics.success else MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }

          if (weakTopicCounts.isNotEmpty()) {
            Text(
              text = "Topics Needing Revision",
              style = MaterialTheme.typography.labelMedium,
              color = MaterialTheme.colorScheme.onSurface,
              fontWeight = FontWeight.Bold,
            )
            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              weakTopicCounts.take(6).forEach { (topic, count) ->
                Surface(
                  color = MaterialTheme.colorScheme.errorContainer,
                  shape = RoundedCornerShape(99.dp),
                  modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(99.dp)),
                ) {
                  Text(
                    text = "$topic (${count}× missed)",
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

      // Academic Goal Settings Card
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
          Text(
            text = "Academic Target Profile",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
          )
          OutlinedTextField(
            value = displayName,
            onValueChange = { displayName = it },
            label = { Text("Display Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
          )

          Text(
            text = "Primary Exam Focus:",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
          )
          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("BOTH", "JAMB", "WAEC").forEach { mode ->
              val selected = targetExam == mode
              FilterChip(
                selected = selected,
                onClick = { targetExam = mode },
                label = { Text(mode, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
                colors =
                  FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                  ),
              )
            }
          }

          Text(
            text = "UTME Target Score: ${targetScore.roundToInt()} / 400",
            style = MaterialTheme.typography.labelMedium.copy(fontFamily = JetBrainsMonoFontFamily),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
          )
          Slider(
            value = targetScore,
            onValueChange = { targetScore = it },
            valueRange = 180f..390f,
          )

          Button(
            onClick = {
              onUpdateGoal(displayName, targetExam, targetScore.roundToInt().toLong())
            },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("save_goals_button"),
            shape = RoundedCornerShape(14.dp),
          ) {
            Text("Save Study Goals", fontWeight = FontWeight.Bold)
          }
        }
      }

      // Past Attempts History
      if (attempts.isNotEmpty()) {
        item {
          Text(
            text = "Exam History (${attempts.size})",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
          )
        }
        items(attempts.take(8), key = { it.id }) { att ->
          Row(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column {
              Text(
                text = "${att.examType} • ${att.subjectTitle}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
              )
              Text(
                text = "${att.correctCount}/${att.totalQuestions} correct • ${att.durationSeconds}s",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
            Surface(
              color =
                if (att.percentage >= 70L) semantics.successContainer
                else MaterialTheme.colorScheme.primaryContainer,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = "${att.percentage}%",
                style = MaterialTheme.typography.titleSmall.copy(fontFamily = JetBrainsMonoFontFamily),
                color =
                  if (att.percentage >= 70L) semantics.onSuccessContainer
                  else MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
          }
        }
      }

      item {
        Spacer(modifier = Modifier.height(16.dp))
      }
    }
  }
}

@Composable
private fun TelemetryTile(
  label: String,
  value: String,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier =
      modifier
        .clip(RoundedCornerShape(14.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
        .padding(horizontal = 10.dp, vertical = 12.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(2.dp),
  ) {
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontWeight = FontWeight.Bold,
    )
    Text(
      text = value,
      style = MaterialTheme.typography.titleMedium.copy(fontFamily = JetBrainsMonoFontFamily),
      color = MaterialTheme.colorScheme.primary,
      fontWeight = FontWeight.Bold,
    )
  }
}
