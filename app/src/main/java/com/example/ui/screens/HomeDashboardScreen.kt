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
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.School
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
import com.example.data.curriculum.CurriculumProgression
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.SubjectTrack
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.UserProfile
import com.example.ui.theme.CourseraAzureAccent
import com.example.ui.theme.CourseraAzureInk
import com.example.ui.theme.CourseraBlue
import com.example.ui.theme.CourseraDeepNavy
import com.example.ui.theme.CourseraNavyElevated
import com.example.ui.theme.ScholarAmber
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
  val progressMap = remember(progressList) { progressList.associateBy { it.lessonId } }
  val completedIds =
    remember(progressList) { progressList.filter { it.isCompleted }.map { it.lessonId }.toSet() }

  // Find next unlocked, unmastered lesson in track-order so the user always resumes cleanly
  val nextLesson =
    remember(structuredModules, progressMap, allLessons) {
      val orderedByTracks =
        CurriculumCatalog.tracks.mapNotNull { track ->
          val trackLessons =
            structuredModules.filter { it.subjectId == track.id }.flatMap { it.lessons }.ifEmpty {
              track.allLessons
            }
          val candidate = CurriculumProgression.nextUnlockedLesson(trackLessons, progressMap)
          if (candidate != null && progressMap[candidate.id]?.isCompleted != true) candidate else null
        }
      orderedByTracks.firstOrNull() ?: allLessons.first()
    }
  val nextLessonProgress = progressMap[nextLesson.id]
  val nextLessonPillarsDone = nextLessonProgress?.completedSteps?.distinct()?.size ?: 0
  val nextLessonRatio = nextLessonPillarsDone / LessonPillar.entries.size.toFloat()

  val anyConceptDone = progressList.any { LessonPillar.CONCEPT.key in it.completedSteps }
  val anyLabDone = progressList.any { LessonPillar.LAB.key in it.completedSteps }
  val anyExamPassed = attempts.any { it.percentage >= 70L }

  val avgAccuracy =
    if (attempts.isEmpty()) 0 else attempts.map { it.percentage.toInt() }.average().roundToInt()
  val projectedJamb =
    if (attempts.isEmpty()) 260 else (180 + (avgAccuracy * 2.2)).roundToInt().coerceIn(160, 400)
  val recentWeakTopic = attempts.firstOrNull()?.weakTopics?.firstOrNull()
  val totalPastQuestions =
    remember { CurriculumCatalog.tracks.sumOf { CurriculumCatalog.pastQuestionsFor(it.id, "ALL").size } }
  val semantics = MaterialTheme.semanticColors

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    LazyColumn(
      modifier = Modifier.widthIn(max = 900.dp).fillMaxSize(),
      contentPadding = PaddingValues(bottom = 24.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      // Coursera-Style Clean Academic Top Bar + Theme Switch
      item {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .background(semantics.classroomBar)
              .padding(horizontal = 16.dp, vertical = 16.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
              ) {
                Icon(
                  imageVector = Icons.Filled.School,
                  contentDescription = null,
                  tint = CourseraAzureAccent,
                  modifier = Modifier.size(16.dp),
                )
                Text(
                  text = "MERITPREP ACADEMY • ${profile?.targetExam ?: "JAMB • WAEC • NECO"}",
                  style = MaterialTheme.typography.labelSmall,
                  color = CourseraAzureAccent,
                  fontWeight = FontWeight.Bold,
                )
              }
              Text(
                text = "Welcome back, ${profile?.displayName?.substringBefore(" ") ?: "Scholar"}",
                style = MaterialTheme.typography.headlineMedium,
                color = semantics.onClassroomBar,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
              )
              Text(
                text = "UTME Target ${profile?.targetScore ?: 320}/400 • ${completedIds.size}/${allLessons.size} lessons mastered • $totalPastQuestions Qs",
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
                containerColor = CourseraNavyElevated,
                contentColor = Color(0xFFBAE6FD),
                borderColor = CourseraAzureAccent,
              )
              IconButton(
                onClick = onToggleDarkTheme,
                modifier =
                  Modifier.size(40.dp)
                    .clip(CircleShape)
                    .background(CourseraNavyElevated)
                    .border(1.dp, Color(0xFF28559A), CircleShape)
                    .testTag("home_dark_theme_toggle"),
              ) {
                Icon(
                  imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                  contentDescription = if (isDarkTheme) "Switch to Light Theme" else "Switch to Dark Theme",
                  tint = if (isDarkTheme) Color(0xFFFDE68A) else CourseraAzureAccent,
                  modifier = Modifier.size(20.dp),
                )
              }
            }
          }
        }
      }

      // Coursera "My Learning" Active Course Hero Card
      item {
        Box(
          modifier =
            Modifier.padding(horizontal = 16.dp)
              .fillMaxWidth()
              .clip(RoundedCornerShape(18.dp))
              .background(CourseraDeepNavy)
              .border(1.5.dp, Color(0xFF1E4C9A), RoundedCornerShape(18.dp)),
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
                    colors = listOf(Color(0xF5001E4D), Color(0xEB00255C), Color(0xC200255C))
                  )
                )
          )
          Column(
            modifier = Modifier.fillMaxWidth().padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Surface(
                color = CourseraAzureAccent,
                shape = RoundedCornerShape(6.dp),
              ) {
                Text(
                  text = "MY LEARNING • NEXT LESSON",
                  style = MaterialTheme.typography.labelSmall,
                  color = CourseraAzureInk,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                )
              }
              Text(
                text = "$nextLessonPillarsDone/${LessonPillar.entries.size} parts",
                style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                color = Color(0xFFE0F2FE),
                fontWeight = FontWeight.SemiBold,
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
              color = Color(0xFFD6E6FF),
            )

            LinearProgressIndicator(
              progress = { nextLessonRatio.coerceAtLeast(0.06f) },
              modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape),
              color = CourseraAzureAccent,
              trackColor = Color(0xFF143C7B),
            )

            Button(
              onClick = { onOpenLesson(nextLesson) },
              modifier = Modifier.fillMaxWidth().height(48.dp).testTag("resume_lesson_button"),
              shape = RoundedCornerShape(10.dp),
              colors =
                ButtonDefaults.buttonColors(
                  containerColor = CourseraBlue,
                  contentColor = Color.White,
                ),
            ) {
              Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(20.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = if (nextLessonPillarsDone > 0) "Resume Lesson" else "Start Lesson",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
              )
            }
          }
        }
      }

      // Coursera Guided Daily Study Goals
      item {
        Column(
          modifier =
            Modifier.padding(horizontal = 16.dp)
              .fillMaxWidth()
              .clip(RoundedCornerShape(16.dp))
              .background(MaterialTheme.colorScheme.surface)
              .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
              .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text(
              text = "Daily Learning Plan",
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            val doneTasks = listOf(anyConceptDone, anyLabDone, anyExamPassed).count { it }
            Surface(
              color = MaterialTheme.colorScheme.primaryContainer,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = "$doneTasks/3 GOALS",
                style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
          }

          StudyChecklistItem(
            title = "Build Zero-to-One Foundations",
            subtitle = "Complete study stages & score ${CurriculumProgression.PASS_THRESHOLD_PERCENT}%+ in ${nextLesson.title}",
            isDone = anyConceptDone,
            onClick = { onOpenLesson(nextLesson) },
          )
          StudyChecklistItem(
            title = "Complete 1 Interactive Virtual Lab",
            subtitle = nextLesson.labType.title,
            isDone = anyLabDone,
            onClick = { onOpenLesson(nextLesson) },
          )
          StudyChecklistItem(
            title = "Pass 1 Timed Exam Drill (70%+)",
            subtitle = "JAMB UTME, WAEC WASSCE & NECO SSCE Bank",
            isDone = anyExamPassed,
            onClick = { onOpenExamLab("physics") },
          )
        }
      }

      // Exam Projection & Timed CBT Practice Strip
      item {
        Row(
          modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          Column(
            modifier =
              Modifier.weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
              Icon(
                Icons.Filled.TrackChanges,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp),
              )
              Text(
                "UTME Readiness",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
              )
            }
            Text(
              text = "$projectedJamb / 400",
              style = MaterialTheme.typography.headlineSmall.copy(fontFeatureSettings = "tnum"),
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
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
                .clickable { onOpenExamLab("physics") }
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
              Icon(
                Icons.Filled.Quiz,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(18.dp),
              )
              Text(
                "Timed Exam Practice",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold,
              )
            }
            Text(
              text = "JAMB, WAEC & NECO",
              style = MaterialTheme.typography.titleLarge,
              color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Text(
              text = recentWeakTopic?.let { "Drill weak topic: $it →" } ?: "Start syllabus mock paper →",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
            )
          }
        }
      }

      // Section Header: Coursera Specializations
      item {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
          Text(
            text = "JAMB, WAEC & NECO Specializations",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
          )
          Text(
            text = "Sequential zero-to-mastery courses — score ≥${CurriculumProgression.PASS_THRESHOLD_PERCENT}% on each assessment to unlock the next lesson",
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
        val nextTrackLesson = CurriculumProgression.nextUnlockedLesson(trackLessons, progressMap)
        val bankSize = CurriculumCatalog.pastQuestionsFor(track.id, "ALL").size

        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
          SubjectTrackCard(
            track = track,
            completedLessons = doneCount,
            totalLessons = trackLessons.size,
            pastQuestionCount = bankSize,
            nextLessonTitle = nextTrackLesson?.title,
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
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
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
        style = MaterialTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
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
  pastQuestionCount: Int,
  nextLessonTitle: String?,
  progressRatio: Float,
  onClick: () -> Unit,
) {
  val semantics = MaterialTheme.semanticColors
  val accentColor =
    when (track.id) {
      "physics" -> CourseraBlue
      "mathematics" -> CourseraAzureAccent
      "chemistry" -> semantics.success
      "biology" -> MaterialTheme.colorScheme.secondary
      else -> ScholarAmber
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
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
        .clickable(onClick = onClick)
        .testTag("track_card_${track.id}"),
  ) {
    Box(modifier = Modifier.width(5.dp).fillMaxHeight().background(accentColor))

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
              text = "${track.modules.size} modules • $totalLessons lessons • $pastQuestionCount Practice Qs",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.SemiBold,
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

      if (!nextLessonTitle.isNullOrBlank()) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
          Icon(
            imageVector = Icons.Filled.LockOpen,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(14.dp),
          )
          Text(
            text = "Unlocked next: $nextLessonTitle",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
        }
      }

      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = "$completedLessons / $totalLessons lessons mastered",
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
              style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
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
          modifier = Modifier.fillMaxWidth().height(7.dp).clip(CircleShape),
          color = if (progressRatio >= 1f) semantics.success else MaterialTheme.colorScheme.primary,
          trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
      }
    }
  }
}
