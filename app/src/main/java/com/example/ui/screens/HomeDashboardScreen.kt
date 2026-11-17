package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.outlined.Biotech
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.curriculum.CourseModule
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.SubjectTrack
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.UserProfile
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.ScholarAmberTertiary
import com.example.ui.theme.UdacityCyan
import com.example.ui.theme.UdacityCyanInk
import com.example.ui.theme.UdacityNavy
import com.example.ui.theme.UdacityNavyElevated
import com.example.ui.theme.semanticColors
import kotlin.math.roundToInt

@Composable
fun HomeDashboardScreen(
  profile: UserProfile?,
  structuredModules: List<CourseModule>,
  progressList: List<LessonProgress>,
  attempts: List<ExamAttempt>,
  isDarkTheme: Boolean,
  onToggleDarkTheme: () -> Unit,
  onOpenLesson: (Lesson) -> Unit,
  onOpenTrack: (SubjectTrack) -> Unit,
  onOpenExamLab: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  val allLessons =
    remember(structuredModules) {
      val dbLessons = structuredModules.flatMap { it.lessons }
      if (dbLessons.isNotEmpty()) dbLessons else CurriculumCatalog.allLessons()
    }
  val completedIds = progressList.filter { it.isCompleted }.map { it.lessonId }.toSet()
  val nextLesson = allLessons.firstOrNull { it.id !in completedIds } ?: allLessons.first()
  val nextLessonProgress = progressList.find { it.lessonId == nextLesson.id }
  val nextLessonPillarsDone = nextLessonProgress?.completedSteps?.size ?: 0
  val nextLessonRatio = nextLessonPillarsDone / LessonPillar.entries.size.toFloat()

  val anyConceptDone = progressList.any { LessonPillar.CONCEPT.key in it.completedSteps }
  val anyLabDone = progressList.any { LessonPillar.LAB.key in it.completedSteps }
  val anyExamPassed = attempts.any { it.percentage >= 70L }

  val avgAccuracy =
    if (attempts.isEmpty()) 0 else attempts.map { it.percentage.toInt() }.average().roundToInt()
  val projectedJamb =
    if (attempts.isEmpty()) 260 else (180 + (avgAccuracy * 2.2)).roundToInt().coerceIn(160, 400)
  val recentWeakTopic = attempts.firstOrNull()?.weakTopics?.firstOrNull()
  val semantics = MaterialTheme.semanticColors

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    LazyColumn(
      modifier = Modifier.widthIn(max = 900.dp).fillMaxSize(),
      contentPadding = PaddingValues(bottom = 24.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      // Udacity-Style Dark Navy Studio Top Header + Dark Theme Quick Toggle
      item {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .background(semantics.classroomBar)
              .padding(horizontal = 16.dp, vertical = 16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
              Text(
                text = "MERITPREP STUDIO • ${profile?.targetExam ?: "BOTH"}",
                style = MaterialTheme.typography.labelSmall,
                color = semantics.classroomAccent,
                fontWeight = FontWeight.Bold,
              )
              Text(
                text = "Welcome, ${profile?.displayName?.substringBefore(" ") ?: "Scholar"}",
                style = MaterialTheme.typography.headlineMedium,
                color = semantics.onClassroomBar,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
              )
              Text(
                text = "UTME Target ${profile?.targetScore ?: 320}/400 • ${completedIds.size}/${allLessons.size} chapters mastered",
                style = MaterialTheme.typography.bodySmall,
                color = semantics.classroomSubtext,
              )
            }
            Row(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              StatBadge(
                icon = Icons.Filled.LocalFireDepartment,
                text = "${profile?.streakDays ?: 1}d",
                containerColor = Color(0xFF422006),
                contentColor = Color(0xFFFDE68A),
                borderColor = Color(0xFFF59E0B),
              )
              StatBadge(
                icon = Icons.Filled.Bolt,
                text = "${profile?.totalXp ?: 0} XP",
                containerColor = UdacityNavyElevated,
                contentColor = Color(0xFF7DD3FC),
                borderColor = UdacityCyan,
              )
              IconButton(
                onClick = onToggleDarkTheme,
                modifier =
                  Modifier.size(40.dp)
                    .clip(CircleShape)
                    .background(UdacityNavyElevated)
                    .border(1.dp, Color(0xFF334155), CircleShape)
                    .testTag("home_dark_theme_toggle"),
              ) {
                Icon(
                  imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                  contentDescription = if (isDarkTheme) "Switch to Light Theme" else "Switch to Dark Theme",
                  tint = if (isDarkTheme) Color(0xFFFDE68A) else Color(0xFF7DD3FC),
                  modifier = Modifier.size(20.dp),
                )
              }
            }
          }
        }
      }

      // Udacity Nanodegree "Resume Classroom" Hero Card
      item {
        Box(
          modifier =
            Modifier.padding(horizontal = 16.dp)
              .fillMaxWidth()
              .clip(RoundedCornerShape(20.dp))
              .background(UdacityNavy)
              .border(1.5.dp, Color(0xFF28427B), RoundedCornerShape(20.dp)),
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
                    colors = listOf(Color(0xF507122B), Color(0xEA0B1633), Color(0xB80B1633))
                  )
                )
          )
          Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
            Box(
              modifier =
                Modifier.width(6.dp)
                  .fillMaxHeight()
                  .background(UdacityCyan)
            )
            Column(
              modifier = Modifier.weight(1f).padding(18.dp),
              verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
              Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Surface(
                  color = UdacityCyan,
                  shape = RoundedCornerShape(6.dp),
                ) {
                  Text(
                    text = "CONTINUE CLASSROOM",
                    style = MaterialTheme.typography.labelSmall,
                    color = UdacityCyanInk,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                  )
                }
                Text(
                  text = "$nextLessonPillarsDone/${LessonPillar.entries.size} pillars",
                  style = MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFontFamily),
                  color = Color(0xFFE0F2FE),
                )
              }

              Text(
                text = nextLesson.title,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
              )
              Text(
                text = "${nextLesson.syllabusTag} • ${nextLesson.durationMinutes} min • Lab: ${nextLesson.labType.title}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFE2E8F0),
              )

              LinearProgressIndicator(
                progress = { nextLessonRatio.coerceAtLeast(0.06f) },
                modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape),
                color = UdacityCyan,
                trackColor = Color(0xFF1E3A6E),
              )

              Button(
                onClick = { onOpenLesson(nextLesson) },
                modifier = Modifier.fillMaxWidth().height(48.dp).testTag("resume_lesson_button"),
                shape = RoundedCornerShape(12.dp),
                colors =
                  ButtonDefaults.buttonColors(
                    containerColor = UdacityCyan,
                    contentColor = UdacityCyanInk,
                  ),
              ) {
                Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = if (nextLessonPillarsDone > 0) "Resume Classroom" else "Enter Classroom",
                  style = MaterialTheme.typography.titleSmall,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }
        }
      }

      // Udacity-Style Daily Study Checklist
      item {
        Column(
          modifier =
            Modifier.padding(horizontal = 16.dp)
              .fillMaxWidth()
              .clip(RoundedCornerShape(18.dp))
              .background(MaterialTheme.colorScheme.surface)
              .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
              .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text(
              text = "Today's Study Checklist",
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            val doneTasks = listOf(anyConceptDone, anyLabDone, anyExamPassed).count { it }
            Surface(
              color = MaterialTheme.colorScheme.primaryContainer,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = "$doneTasks/3 DONE",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
          }

          StudyChecklistItem(
            title = "Complete 1 Concept Derivation",
            subtitle = "Build intuition in ${nextLesson.title}",
            isDone = anyConceptDone,
            onClick = { onOpenLesson(nextLesson) },
          )
          StudyChecklistItem(
            title = "Run 1 Interactive Practical Lab",
            subtitle = nextLesson.labType.title,
            isDone = anyLabDone,
            onClick = { onOpenLesson(nextLesson) },
          )
          StudyChecklistItem(
            title = "Score 70%+ in Exam Lab",
            subtitle = "Timed JAMB CBT & WAEC practice",
            isDone = anyExamPassed,
            onClick = { onOpenExamLab("physics") },
          )
        }
      }

      // High-Contrast Exam Readiness & Quick CBT Action Strip
      item {
        Row(
          modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          Column(
            modifier =
              Modifier.weight(1f)
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              Icon(
                Icons.Filled.TrackChanges,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp),
              )
              Text(
                "UTME Projection",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
              )
            }
            Text(
              text = "$projectedJamb / 400",
              style = MaterialTheme.typography.headlineSmall.copy(fontFamily = JetBrainsMonoFontFamily),
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
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
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(18.dp))
                .clickable { onOpenExamLab("physics") }
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              Icon(
                Icons.Filled.Quiz,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(18.dp),
              )
              Text(
                "Quick CBT Drill",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.SemiBold,
              )
            }
            Text(
              text = "JAMB & WAEC",
              style = MaterialTheme.typography.titleLarge,
              color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            Text(
              text = recentWeakTopic?.let { "Fix weak topic: $it →" } ?: "Start timed drill →",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSecondaryContainer,
              fontWeight = FontWeight.Bold,
            )
          }
        }
      }

      // Section Header: Nanodegree Subject Tracks
      item {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
          Text(
            text = "Nanodegree Subject Tracks",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
          )
          Text(
            text = "Structured Room-backed modules with interactive labs & past questions",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }

      items(CurriculumCatalog.tracks, key = { it.id }) { track ->
        val dbTrackLessons =
          structuredModules.filter { it.subjectId == track.id }.flatMap { it.lessons }
        val trackLessons = if (dbTrackLessons.isNotEmpty()) dbTrackLessons else track.allLessons
        val doneCount = trackLessons.count { it.id in completedIds }
        val ratio = if (trackLessons.isEmpty()) 0f else doneCount / trackLessons.size.toFloat()

        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
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
}

@Composable
private fun StudyChecklistItem(
  title: String,
  subtitle: String,
  isDone: Boolean,
  onClick: () -> Unit,
) {
  val semantics = MaterialTheme.semanticColors
  Row(
    modifier =
      Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(
          if (isDone) semantics.successContainer
          else MaterialTheme.colorScheme.background
        )
        .border(
          1.dp,
          if (isDone) semantics.success else MaterialTheme.colorScheme.outlineVariant,
          RoundedCornerShape(12.dp),
        )
        .clickable(onClick = onClick)
        .padding(horizontal = 12.dp, vertical = 10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    Icon(
      imageVector = if (isDone) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
      contentDescription = if (isDone) "Completed" else "Pending",
      tint = if (isDone) semantics.success else MaterialTheme.colorScheme.primary,
      modifier = Modifier.size(20.dp),
    )
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = if (isDone) semantics.onSuccessContainer else MaterialTheme.colorScheme.onSurface,
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = if (isDone) semantics.onSuccessContainer else MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }
    Icon(
      imageVector = Icons.AutoMirrored.Filled.ArrowForward,
      contentDescription = "Open",
      tint = if (isDone) semantics.onSuccessContainer else MaterialTheme.colorScheme.primary,
      modifier = Modifier.size(16.dp),
    )
  }
}

@Composable
private fun StatBadge(
  icon: ImageVector,
  text: String,
  containerColor: Color,
  contentColor: Color,
  borderColor: Color,
) {
  Surface(
    color = containerColor,
    shape = RoundedCornerShape(99.dp),
    modifier = Modifier.border(1.dp, borderColor, RoundedCornerShape(99.dp)),
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
  val semantics = MaterialTheme.semanticColors
  val accentColor =
    when (track.id) {
      "physics" -> UdacityCyan
      "mathematics" -> MaterialTheme.colorScheme.primary
      "chemistry" -> semantics.success
      "biology" -> MaterialTheme.colorScheme.secondary
      else -> ScholarAmberTertiary
    }
  val trackIcon =
    when (track.id) {
      "physics" -> Icons.Filled.Science
      "mathematics" -> Icons.Filled.Calculate
      "chemistry" -> Icons.Filled.Science
      "biology" -> Icons.Outlined.Biotech
      else -> Icons.Filled.MenuBook
    }

  Row(
    modifier =
      Modifier.fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clip(RoundedCornerShape(18.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
        .clickable(onClick = onClick)
        .testTag("track_card_${track.id}"),
  ) {
    Box(modifier = Modifier.width(6.dp).fillMaxHeight().background(accentColor))

    Column(
      modifier = Modifier.weight(1f).padding(16.dp),
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
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center,
          ) {
            Icon(
              imageVector = trackIcon,
              contentDescription = track.title,
              tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
          }
          Column {
            Text(
              text = track.title,
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
              text = "${track.modules.size} modules • ${track.examCoverage}",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.secondary,
              fontWeight = FontWeight.Bold,
            )
          }
        }
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowForward,
          contentDescription = "Open ${track.title}",
          tint = MaterialTheme.colorScheme.primary,
        )
      }

      Text(
        text = track.tagline,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )

      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = "$completedLessons / $totalLessons chapters mastered",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
          Surface(
            color =
              if (progressRatio >= 1f) semantics.successContainer
              else MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(99.dp),
          ) {
            Text(
              text = "${(progressRatio * 100).roundToInt()}%",
              style = MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFontFamily),
              color =
                if (progressRatio >= 1f) semantics.onSuccessContainer
                else MaterialTheme.colorScheme.onPrimaryContainer,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            )
          }
        }
        LinearProgressIndicator(
          progress = { progressRatio },
          modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
          color = if (progressRatio >= 1f) semantics.success else MaterialTheme.colorScheme.primary,
          trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
      }
    }
  }
}
