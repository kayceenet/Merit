package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.SubjectTrack
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.UserProfile
import com.example.ui.theme.DeepNavyBanner
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.MasteryEmerald
import kotlin.math.roundToInt

@Composable
fun HomeDashboardScreen(
  profile: UserProfile?,
  progressList: List<LessonProgress>,
  attempts: List<ExamAttempt>,
  onOpenLesson: (Lesson) -> Unit,
  onOpenTrack: (SubjectTrack) -> Unit,
  onOpenExamLab: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  val allLessons = CurriculumCatalog.allLessons()
  val completedIds = progressList.filter { it.isCompleted }.map { it.lessonId }.toSet()
  val nextLesson = allLessons.firstOrNull { it.id !in completedIds } ?: allLessons.first()

  val avgAccuracy =
    if (attempts.isEmpty()) 0 else attempts.map { it.percentage.toInt() }.average().roundToInt()
  val projectedJamb =
    if (attempts.isEmpty()) 260 else (180 + (avgAccuracy * 2.2)).roundToInt().coerceIn(160, 400)
  val recentWeakTopic = attempts.firstOrNull()?.weakTopics?.firstOrNull()

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    LazyColumn(
      modifier = Modifier.widthIn(max = 880.dp).fillMaxSize(),
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      // Top Greeting + Streak & XP Bar
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "Welcome back, ${profile?.displayName?.substringBefore(" ") ?: "Scholar"}",
              style = MaterialTheme.typography.headlineMedium,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
            )
            Text(
              text = "Target: ${profile?.targetExam ?: "BOTH"} • Goal ${profile?.targetScore ?: 320}/400",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatBadge(
              icon = Icons.Filled.LocalFireDepartment,
              text = "${profile?.streakDays ?: 1}d",
              containerColor = MaterialTheme.colorScheme.tertiaryContainer,
              contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            )
            StatBadge(
              icon = Icons.Filled.Bolt,
              text = "${profile?.totalXp ?: 0} XP",
              containerColor = MaterialTheme.colorScheme.primaryContainer,
              contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
          }
        }
      }

      // Udacity-Style Resume Learning Hero Banner
      item {
        Box(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(24.dp))
              .background(DeepNavyBanner),
        ) {
          Image(
            painter = painterResource(id = R.drawable.img_hero_academy_1794740698817),
            contentDescription = "Academy learning hero artwork",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
          )
          Box(
            modifier =
              Modifier.matchParentSize()
                .background(
                  Brush.horizontalGradient(
                    colors = listOf(Color(0xEB071430), Color(0xBD0A1D45), Color(0x550A1D45))
                  )
                )
          )
          Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Surface(
              color = MaterialTheme.colorScheme.secondary,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = "NEXT SYSTEMATIC LESSON",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
            Text(
              text = nextLesson.title,
              style = MaterialTheme.typography.headlineSmall,
              color = Color.White,
            )
            Text(
              text = "${nextLesson.syllabusTag} • ${nextLesson.durationMinutes} min • Includes ${nextLesson.labType.title}",
              style = MaterialTheme.typography.bodySmall,
              color = Color(0xFFD6E2FF),
            )
            Spacer(modifier = Modifier.height(2.dp))
            Button(
              onClick = { onOpenLesson(nextLesson) },
              modifier = Modifier.height(48.dp).testTag("resume_lesson_button"),
              shape = RoundedCornerShape(14.dp),
              colors =
                ButtonDefaults.buttonColors(
                  containerColor = MaterialTheme.colorScheme.tertiary,
                  contentColor = Color.White,
                ),
            ) {
              Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Enter Classroom", style = MaterialTheme.typography.labelLarge)
            }
          }
        }
      }

      // Exam Readiness & Quick CBT Action Strip
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          Column(
            modifier =
              Modifier.weight(1f)
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              Icon(Icons.Filled.TrackChanges, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
              Text("UTME Projection", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text(
              text = "$projectedJamb / 400",
              style = MaterialTheme.typography.headlineSmall.copy(fontFamily = JetBrainsMonoFontFamily),
              color = MaterialTheme.colorScheme.primary,
            )
            Text(
              text = if (attempts.isEmpty()) "Take 1st CBT mock to calibrate" else "$avgAccuracy% avg across ${attempts.size} mocks",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }

          Column(
            modifier =
              Modifier.weight(1f)
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.55f))
                .border(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.35f), RoundedCornerShape(18.dp))
                .clickable { onOpenExamLab("physics") }
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              Icon(Icons.Filled.Quiz, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(18.dp))
              Text("Quick CBT Drill", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSecondaryContainer)
            }
            Text(
              text = "JAMB & WAEC",
              style = MaterialTheme.typography.titleLarge,
              color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            Text(
              text = recentWeakTopic?.let { "Fix weak topic: $it →" } ?: "Timed past questions →",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.secondary,
              fontWeight = FontWeight.SemiBold,
            )
          }
        }
      }

      // Section Header: Coursera-style Subject Tracks
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Column {
            Text("Subject Mastery Tracks", style = MaterialTheme.typography.titleLarge)
            Text(
              text = "${completedIds.size} of ${allLessons.size} systematic lessons mastered",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
        }
      }

      items(CurriculumCatalog.tracks, key = { it.id }) { track ->
        val trackLessons = track.allLessons
        val doneCount = trackLessons.count { it.id in completedIds }
        val ratio = if (trackLessons.isEmpty()) 0f else doneCount / trackLessons.size.toFloat()

        SubjectTrackCard(
          track = track,
          completedLessons = doneCount,
          totalLessons = trackLessons.size,
          progressRatio = ratio,
          onClick = { onOpenTrack(track) },
        )
      }
    }
  }
}

@Composable
private fun StatBadge(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  text: String,
  containerColor: Color,
  contentColor: Color,
) {
  Surface(
    color = containerColor,
    shape = RoundedCornerShape(99.dp),
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      Icon(icon, contentDescription = null, tint = contentColor, modifier = Modifier.size(16.dp))
      Text(
        text = text,
        style = MaterialTheme.typography.labelMedium.copy(fontFamily = JetBrainsMonoFontFamily),
        color = contentColor,
        fontWeight = FontWeight.Bold,
      )
    }
  }
}

@Composable
fun SubjectTrackCard(
  track: SubjectTrack,
  completedLessons: Int,
  totalLessons: Int,
  progressRatio: Float,
  onClick: () -> Unit,
) {
  Column(
    modifier =
      Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
        .clickable(onClick = onClick)
        .testTag("track_card_${track.id}")
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Row(
        modifier = Modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
      ) {
        Box(
          modifier =
            Modifier.size(44.dp)
              .clip(RoundedCornerShape(14.dp))
              .background(MaterialTheme.colorScheme.primaryContainer),
          contentAlignment = Alignment.Center,
        ) {
          Icon(
            imageVector = Icons.Filled.Science,
            contentDescription = track.title,
            tint = MaterialTheme.colorScheme.primary,
          )
        }
        Column {
          Text(text = track.title, style = MaterialTheme.typography.titleMedium)
          Text(
            text = track.examCoverage,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
          )
        }
      }
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
        contentDescription = "Open ${track.title}",
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }

    Text(
      text = track.tagline,
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
          text = "$completedLessons / $totalLessons lessons completed",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
          text = "${(progressRatio * 100).roundToInt()}%",
          style = MaterialTheme.typography.labelSmall,
          color = if (progressRatio >= 1f) MasteryEmerald else MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold,
        )
      }
      LinearProgressIndicator(
        progress = { progressRatio },
        modifier = Modifier.fillMaxWidth().height(7.dp).clip(CircleShape),
        color = if (progressRatio >= 1f) MasteryEmerald else MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
      )
    }
  }
}
