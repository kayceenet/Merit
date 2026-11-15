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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.ui.theme.MasteryEmerald
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnalyticsProfileScreen(
  profile: UserProfile?,
  attempts: List<ExamAttempt>,
  progressList: List<LessonProgress>,
  onUpdateGoal: (displayName: String, targetExam: String, targetScore: Long) -> Unit,
  onSignOut: () -> Unit,
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

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    LazyColumn(
      modifier = Modifier.widthIn(max = 880.dp).fillMaxSize(),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      item {
        Text("Performance Analytics & Goals", style = MaterialTheme.typography.headlineMedium)
      }

      // Score Trend Chart Card
      item {
        val barColor = MaterialTheme.colorScheme.primary
        val passColor = MasteryEmerald
        val gridColor = MaterialTheme.colorScheme.outlineVariant

        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(20.dp))
              .background(MaterialTheme.colorScheme.surface)
              .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
              .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          Text("Recent Mock Exam Accuracy (%)", style = MaterialTheme.typography.titleMedium)
          if (recentAttempts.isEmpty()) {
            Text(
              text = "Complete your first JAMB CBT or WAEC mock in the Exam Lab to generate score telemetry.",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          } else {
            Canvas(
              modifier =
                Modifier.fillMaxWidth()
                  .height(150.dp)
                  .clip(RoundedCornerShape(14.dp))
                  .background(MaterialTheme.colorScheme.background)
                  .padding(12.dp)
            ) {
              val h = size.height
              val w = size.width
              drawLine(gridColor, Offset(0f, h * 0.3f), Offset(w, h * 0.3f), strokeWidth = 1.5f)
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
          }

          if (weakTopicCounts.isNotEmpty()) {
            Text("Topics Needing Revision", style = MaterialTheme.typography.labelMedium)
            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              weakTopicCounts.take(6).forEach { (topic, count) ->
                Surface(
                  color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.65f),
                  shape = RoundedCornerShape(99.dp),
                ) {
                  Text(
                    text = "$topic (${count}× missed)",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onErrorContainer,
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
              .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
              .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          Text("Academic Target Profile", style = MaterialTheme.typography.titleMedium)
          OutlinedTextField(
            value = displayName,
            onValueChange = { displayName = it },
            label = { Text("Display Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
          )

          Text("Primary Exam Focus:", style = MaterialTheme.typography.labelMedium)
          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("BOTH", "JAMB", "WAEC").forEach { mode ->
              FilterChip(
                selected = targetExam == mode,
                onClick = { targetExam = mode },
                label = { Text(mode) },
              )
            }
          }

          Text(
            text = "UTME Target Score: ${targetScore.roundToInt()} / 400",
            style = MaterialTheme.typography.labelMedium.copy(fontFamily = JetBrainsMonoFontFamily),
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
            Text("Save Study Goals")
          }
        }
      }

      // Past Attempts History
      if (attempts.isNotEmpty()) {
        item {
          Text("Exam History (${attempts.size})", style = MaterialTheme.typography.titleMedium)
        }
        items(attempts.take(8), key = { it.id }) { att ->
          Row(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column {
              Text(
                text = "${att.examType} • ${att.subjectTitle}",
                style = MaterialTheme.typography.titleSmall,
              )
              Text(
                text = "${att.correctCount}/${att.totalQuestions} correct • ${att.durationSeconds}s",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
            Text(
              text = "${att.percentage}%",
              style = MaterialTheme.typography.titleMedium.copy(fontFamily = JetBrainsMonoFontFamily),
              color = if (att.percentage >= 70L) MasteryEmerald else MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
            )
          }
        }
      }

      item {
        OutlinedButton(
          onClick = onSignOut,
          modifier = Modifier.fillMaxWidth().height(50.dp).testTag("sign_out_button"),
          shape = RoundedCornerShape(14.dp),
        ) {
          Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null)
          Text("  Sign Out", style = MaterialTheme.typography.labelLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
      }
    }
  }
}
