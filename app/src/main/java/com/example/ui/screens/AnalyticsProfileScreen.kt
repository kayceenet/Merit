package com.example.ui.screens

import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.local.FontSizeScale
import com.example.data.local.OfflineProgressSnapshot
import com.example.data.local.ThemeMode
import com.example.data.local.ThemePalette
import com.example.data.local.ThemePreferences
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.UserProfile
import com.example.ui.components.TopRightMenuButton
import com.example.ui.theme.semanticColors
import kotlin.math.roundToInt

private data class SubjectPerformanceStat(
  val subjectId: String,
  val shortName: String,
  val code: String,
  val averageScore: Int?,
  val attemptsCount: Int,
  val chapterMasteryPct: Int,
) {
  val effectivePerformance: Int
    get() = averageScore ?: chapterMasteryPct

  val isStrong: Boolean
    get() = (averageScore != null && averageScore >= 70) || (averageScore == null && chapterMasteryPct >= 70)

  val isWeak: Boolean
    get() = averageScore != null && averageScore < 70
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnalyticsProfileScreen(
  profile: UserProfile?,
  attempts: List<ExamAttempt>,
  progressList: List<LessonProgress>,
  offlineSnapshot: OfflineProgressSnapshot,
  themePreferences: ThemePreferences,
  dueReviewsCount: Int = 0,
  onSetThemeMode: (ThemeMode) -> Unit,
  onSetThemePalette: (ThemePalette) -> Unit = {},
  onSetHighContrastBoost: (Boolean) -> Unit = {},
  onSetUseDynamicColor: (Boolean) -> Unit,
  onSetFontSizeScale: (FontSizeScale) -> Unit = {},
  onOpenSettingsPortal: () -> Unit = {},
  onOpenMenu: () -> Unit = {},
  onPracticeWeakSubject: (String) -> Unit,
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

  val subjectStats =
    remember(attempts, progressList) {
      val completedSet = progressList.filter { it.isCompleted }.map { it.lessonId }.toSet()
      CurriculumCatalog.tracks.map { track ->
        val trackAttempts = attempts.filter { it.subjectId == track.id }
        val avg =
          if (trackAttempts.isEmpty()) null
          else trackAttempts.map { it.percentage.toInt() }.average().roundToInt()
        val lessons = track.allLessons
        val mastered = lessons.count { it.id in completedSet }
        val masteryPct = if (lessons.isEmpty()) 0 else ((mastered * 100f) / lessons.size).roundToInt()
        val code =
          when (track.id) {
            "physics" -> "PHY"
            "mathematics" -> "MTH"
            "chemistry" -> "CHM"
            "biology" -> "BIO"
            "economics" -> "ECO"
            "government" -> "GOV"
            else -> "ENG"
          }
        SubjectPerformanceStat(
          subjectId = track.id,
          shortName = track.shortName,
          code = code,
          averageScore = avg,
          attemptsCount = trackAttempts.size,
          chapterMasteryPct = masteryPct,
        )
      }
    }

  var inspectedSubjectIdx by remember { mutableIntStateOf(0) }
  val strongSubjects = remember(subjectStats) { subjectStats.filter { it.isStrong } }
  val weakSubjects = remember(subjectStats) { subjectStats.filter { it.isWeak } }
  val semantics = MaterialTheme.semanticColors

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
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
          Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
              text = "Performance & Settings",
              style = MaterialTheme.typography.headlineMedium,
              color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
              text = "Offline telemetry synced via Room & DataStore (${offlineSnapshot.totalExamsTaken} offline mocks logged)",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
          TopRightMenuButton(badgeCount = dueReviewsCount, onClick = onOpenMenu)
        }
      }

      // 4-Metric Coursera Telemetry Summary Strip
      item {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          TelemetryTile("MASTERED", "$masteredLessons", Modifier.weight(1f))
          TelemetryTile("AVG MOCK", if (attempts.isEmpty()) "—" else "$avgScore%", Modifier.weight(1f))
          TelemetryTile("XP", "${profile?.totalXp ?: offlineSnapshot.offlineXp}", Modifier.weight(1f))
          TelemetryTile("STREAK", "${profile?.streakDays ?: offlineSnapshot.offlineStreakDays}d", Modifier.weight(1f))
        }
      }

      // Subject-by-Subject Performance Visualization Chart (Strong vs Weak Areas)
      item {
        val strongColor = semantics.success
        val weakColor = MaterialTheme.colorScheme.error
        val masteryColor = MaterialTheme.colorScheme.secondary
        val gridColor = MaterialTheme.colorScheme.outline
        val trackBg = MaterialTheme.colorScheme.surfaceVariant
        val selectedStat = subjectStats.getOrNull(inspectedSubjectIdx)

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
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Subject Mastery & Quiz Performance",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
              )
              Text(
                text = "Tap any subject column below to inspect score vs. module mastery",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
            Icon(
              Icons.AutoMirrored.Filled.TrendingUp,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
            )
          }

          // Chart Legend
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            LegendDot("Strong (≥70%)", strongColor)
            LegendDot("Weak (<70%)", weakColor)
            LegendDot("Chapter Mastery", masteryColor)
          }

          // Native Compose Canvas Grouped Bar Chart across all 5 Subjects
          Canvas(
            modifier =
              Modifier.fillMaxWidth()
                .height(170.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
                .padding(horizontal = 14.dp, vertical = 14.dp)
                .testTag("subject_performance_chart")
          ) {
            val h = size.height
            val w = size.width
            val dashEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)

            // 50% and 70% pass benchmark lines
            drawLine(
              color = gridColor.copy(alpha = 0.45f),
              start = Offset(0f, h * 0.5f),
              end = Offset(w, h * 0.5f),
              strokeWidth = 2f,
              pathEffect = dashEffect,
            )
            drawLine(
              color = strongColor.copy(alpha = 0.7f),
              start = Offset(0f, h * 0.3f),
              end = Offset(w, h * 0.3f),
              strokeWidth = 2.5f,
              pathEffect = dashEffect,
            )

            val slotW = w / subjectStats.size.coerceAtLeast(1)
            val singleBarW = (slotW * 0.28f).coerceAtMost(32f)
            val gap = 6f

            subjectStats.forEachIndexed { idx, stat ->
              val centerX = idx * slotW + slotW / 2f
              val quizPct = (stat.averageScore ?: 0) / 100f
              val masteryPct = stat.chapterMasteryPct / 100f

              // Background slot highlight when selected
              if (idx == inspectedSubjectIdx) {
                drawRoundRect(
                  color = trackBg.copy(alpha = 0.65f),
                  topLeft = Offset(idx * slotW + 4f, 0f),
                  size = Size(slotW - 8f, h),
                  cornerRadius = CornerRadius(12f, 12f),
                )
              }

              // Quiz Accuracy Bar
              val quizRatio = if (stat.averageScore == null) 0.04f else quizPct.coerceIn(0.06f, 1f)
              val quizH = h * quizRatio
              val quizBarColor =
                when {
                  stat.averageScore == null -> gridColor.copy(alpha = 0.4f)
                  stat.averageScore >= 70 -> strongColor
                  else -> weakColor
                }
              drawRoundRect(
                color = quizBarColor,
                topLeft = Offset(centerX - singleBarW - gap / 2f, h - quizH),
                size = Size(singleBarW, quizH),
                cornerRadius = CornerRadius(8f, 8f),
              )

              // Chapter Mastery Bar
              val masteryRatio = masteryPct.coerceIn(0.05f, 1f)
              val masteryH = h * masteryRatio
              drawRoundRect(
                color = masteryColor,
                topLeft = Offset(centerX + gap / 2f, h - masteryH),
                size = Size(singleBarW, masteryH),
                cornerRadius = CornerRadius(8f, 8f),
              )
            }
          }

          // Interactive Subject Selector Pills directly beneath bars
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            subjectStats.forEachIndexed { idx, stat ->
              val selected = idx == inspectedSubjectIdx
              Surface(
                color =
                  if (selected) MaterialTheme.colorScheme.primaryContainer
                  else MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(10.dp),
                modifier =
                  Modifier.weight(1f)
                    .border(
                      1.5.dp,
                      if (selected) MaterialTheme.colorScheme.primary
                      else MaterialTheme.colorScheme.outlineVariant,
                      RoundedCornerShape(10.dp),
                    )
                    .clickable { inspectedSubjectIdx = idx },
              ) {
                Column(
                  modifier = Modifier.padding(vertical = 6.dp, horizontal = 2.dp),
                  horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                  Text(
                    text = stat.code,
                    style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                    color =
                      if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                      else MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                  )
                  Text(
                    text = stat.averageScore?.let { "$it%" } ?: "${stat.chapterMasteryPct}%",
                    style = MaterialTheme.typography.labelSmall,
                    color =
                      when {
                        stat.isWeak -> MaterialTheme.colorScheme.error
                        stat.isStrong -> semantics.success
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                      },
                    fontWeight = FontWeight.Bold,
                  )
                }
              }
            }
          }

          if (selectedStat != null) {
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .background(MaterialTheme.colorScheme.background)
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
                  .padding(12.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                  text = selectedStat.shortName,
                  style = MaterialTheme.typography.titleSmall,
                  color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                  text =
                    "Quiz Avg: ${selectedStat.averageScore?.let { "$it% (${selectedStat.attemptsCount} mocks)" } ?: "No mocks yet"} • Syllabus Mastery: ${selectedStat.chapterMasteryPct}%",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
              }
              OutlinedButton(
                onClick = { onPracticeWeakSubject(selectedStat.subjectId) },
                shape = RoundedCornerShape(10.dp),
              ) {
                Text("Practice", fontWeight = FontWeight.Bold)
              }
            }
          }
        }
      }

      // Strong vs Weak Areas Diagnostic Card
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
            text = "Strong vs. Weak Areas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            // Strong Areas Panel
            Column(
              modifier =
                Modifier.weight(1f)
                  .clip(RoundedCornerShape(14.dp))
                  .background(semantics.successContainer)
                  .border(1.dp, semantics.success, RoundedCornerShape(14.dp))
                  .padding(12.dp),
              verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
              ) {
                Icon(
                  Icons.Filled.CheckCircle,
                  contentDescription = null,
                  tint = semantics.onSuccessContainer,
                  modifier = Modifier.size(16.dp),
                )
                Text(
                  text = "Strong Areas",
                  style = MaterialTheme.typography.labelMedium,
                  color = semantics.onSuccessContainer,
                  fontWeight = FontWeight.Bold,
                )
              }
              if (strongSubjects.isEmpty()) {
                Text(
                  text = "Score 70%+ in any mock or master chapters to build strong areas.",
                  style = MaterialTheme.typography.bodySmall,
                  color = semantics.onSuccessContainer,
                )
              } else {
                strongSubjects.forEach { stat ->
                  Text(
                    text = "• ${stat.shortName}: ${stat.effectivePerformance}%",
                    style = MaterialTheme.typography.bodySmall,
                    color = semantics.onSuccessContainer,
                    fontWeight = FontWeight.SemiBold,
                  )
                }
              }
            }

            // Weak Areas Panel
            Column(
              modifier =
                Modifier.weight(1f)
                  .clip(RoundedCornerShape(14.dp))
                  .background(MaterialTheme.colorScheme.errorContainer)
                  .border(1.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(14.dp))
                  .padding(12.dp),
              verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
              ) {
                Icon(
                  Icons.Filled.WarningAmber,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.onErrorContainer,
                  modifier = Modifier.size(16.dp),
                )
                Text(
                  text = "Weak Areas",
                  style = MaterialTheme.typography.labelMedium,
                  color = MaterialTheme.colorScheme.onErrorContainer,
                  fontWeight = FontWeight.Bold,
                )
              }
              if (weakSubjects.isEmpty() && weakTopicCounts.isEmpty()) {
                Text(
                  text = "No weak subjects flagged yet.",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onErrorContainer,
                )
              } else {
                weakSubjects.forEach { stat ->
                  Text(
                    text = "• ${stat.shortName}: ${stat.averageScore ?: 0}% avg",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    fontWeight = FontWeight.SemiBold,
                  )
                }
                weakTopicCounts.take(3).forEach { (topic, count) ->
                  Text(
                    text = "• $topic (${count}× missed)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                  )
                }
              }
            }
          }

          if (weakSubjects.isNotEmpty()) {
            val priorityWeak = weakSubjects.minByOrNull { it.averageScore ?: 100 }
            if (priorityWeak != null) {
              Button(
                onClick = { onPracticeWeakSubject(priorityWeak.subjectId) },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
              ) {
                Text("Drill Weakest Subject: ${priorityWeak.shortName} →", fontWeight = FontWeight.Bold)
              }
            }
          }
        }
      }

      // Score Trend Chart Card (Across Past Mock Sessions)
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
              text = "Recent Mock Accuracy Trend",
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
              text = "70% pass target",
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
                text = "Complete your first JAMB CBT or WAEC mock in the Exam Lab to generate trend telemetry.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(14.dp),
              )
            }
          } else {
            Canvas(
              modifier =
                Modifier.fillMaxWidth()
                  .height(140.dp)
                  .clip(RoundedCornerShape(14.dp))
                  .background(MaterialTheme.colorScheme.background)
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
                  .padding(14.dp)
            ) {
              val h = size.height
              val w = size.width
              drawLine(gridColor, Offset(0f, h * 0.3f), Offset(w, h * 0.3f), strokeWidth = 2f)

              val count = recentAttempts.size
              val slotW = w / count.coerceAtLeast(1)
              val linePath = Path()
              recentAttempts.forEachIndexed { idx, att ->
                val pct = (att.percentage.toFloat() / 100f).coerceIn(0.05f, 1f)
                val x = idx * slotW + slotW / 2f
                val y = h - (h * pct)
                if (idx == 0) linePath.moveTo(x, y) else linePath.lineTo(x, y)
              }
              if (count > 1) {
                drawPath(
                  path = linePath,
                  color = barColor,
                  style = Stroke(width = 5f, cap = StrokeCap.Round),
                )
              }
              recentAttempts.forEachIndexed { idx, att ->
                val pct = (att.percentage.toFloat() / 100f).coerceIn(0.05f, 1f)
                val x = idx * slotW + slotW / 2f
                val y = h - (h * pct)
                drawCircle(
                  color = if (att.percentage >= 70L) passColor else barColor,
                  radius = 10f,
                  center = Offset(x, y),
                )
              }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
              recentAttempts.forEach { att ->
                Text(
                  text = "${att.subjectTitle.take(4)} ${att.percentage}%",
                  style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                  color = if (att.percentage >= 70L) semantics.success else MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }
        }
      }

      // Theme & Material 3 Dynamic Color Accessibility Card
      item {
        val supportsDynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Icon(
              Icons.Filled.DarkMode,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
            )
            Text(
              text = "Appearance & Accessibility",
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }

          Text(
            text = "Theme Mode (Persisted Offline via DataStore)",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ThemeMode.entries.forEach { mode ->
              val selected = themePreferences.themeMode == mode
              FilterChip(
                selected = selected,
                onClick = { onSetThemeMode(mode) },
                label = { Text(mode.label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
                colors =
                  FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                  ),
                modifier = Modifier.testTag("theme_mode_${mode.name.lowercase()}"),
              )
            }
          }

          Text(
            text = "Theme Palette (${themePreferences.themePalette.label})",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            ThemePalette.entries.forEach { palette ->
              val selected = themePreferences.themePalette == palette
              FilterChip(
                selected = selected,
                onClick = { onSetThemePalette(palette) },
                label = { Text(palette.label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
                colors =
                  FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                  ),
                modifier = Modifier.testTag("analytics_palette_${palette.name.lowercase()}"),
              )
            }
          }

          Row(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column(modifier = Modifier.weight(1f).padding(end = 10.dp)) {
              Text(
                text = "High-Contrast Ink Boost",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
              )
              Text(
                text = "Pure black/white surfaces & sharp AAA contrast borders",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
            Switch(
              checked = themePreferences.highContrastBoost,
              onCheckedChange = onSetHighContrastBoost,
              modifier = Modifier.testTag("analytics_high_contrast_switch"),
            )
          }

          Row(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column(modifier = Modifier.weight(1f).padding(end = 10.dp)) {
              Text(
                text = "Material 3 Dynamic Color",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
              )
              Text(
                text =
                  when {
                    !supportsDynamic -> "Requires Android 12+"
                    themePreferences.highContrastBoost ||
                      themePreferences.themePalette == ThemePalette.OLED_HIGH_CONTRAST ->
                      "Paused while High-Contrast palette is active"
                    else -> "Adapts accents from your wallpaper with high-contrast borders"
                  },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
            Switch(
              checked = themePreferences.useDynamicColor,
              onCheckedChange = onSetUseDynamicColor,
              enabled =
                supportsDynamic &&
                  !themePreferences.highContrastBoost &&
                  themePreferences.themePalette != ThemePalette.OLED_HIGH_CONTRAST,
              modifier = Modifier.testTag("dynamic_color_switch"),
            )
          }

          Text(
            text = "Reading Text Size (Character Scale)",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            FontSizeScale.entries.forEach { scale ->
              val selected = themePreferences.fontSizeScale == scale
              FilterChip(
                selected = selected,
                onClick = { onSetFontSizeScale(scale) },
                label = { Text(scale.label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
                colors =
                  FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                  ),
                modifier = Modifier.testTag("font_scale_${scale.name.lowercase()}"),
              )
            }
          }

          OutlinedButton(
            onClick = onOpenSettingsPortal,
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("analytics_open_settings_portal"),
            shape = RoundedCornerShape(12.dp),
          ) {
            Icon(Icons.Filled.Settings, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text("Open Full Settings Portal (CBT, Thresholds & Backup)", fontWeight = FontWeight.Bold)
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
            style = MaterialTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
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
                style = MaterialTheme.typography.titleSmall.copy(fontFeatureSettings = "tnum"),
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

      item { Spacer(modifier = Modifier.height(16.dp)) }
    }
  }
}

@Composable
private fun LegendDot(label: String, color: Color) {
  Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(color))
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
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
      style = MaterialTheme.typography.titleMedium.copy(fontFeatureSettings = "tnum"),
      color = MaterialTheme.colorScheme.primary,
      fontWeight = FontWeight.Bold,
    )
  }
}
