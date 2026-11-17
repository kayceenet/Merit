package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.example.data.curriculum.CourseModule
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.model.LessonProgress
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.UdacityCyan
import com.example.ui.theme.UdacityCyanInk
import com.example.ui.theme.semanticColors
import kotlin.math.roundToInt

@Composable
fun CatalogScreen(
  initialSubjectId: String?,
  structuredModules: List<CourseModule>,
  progressList: List<LessonProgress>,
  onOpenLesson: (Lesson) -> Unit,
  modifier: Modifier = Modifier,
) {
  var selectedSubjectId by remember(initialSubjectId) {
    mutableStateOf(initialSubjectId ?: CurriculumCatalog.tracks.first().id)
  }
  val activeTrack = CurriculumCatalog.trackById(selectedSubjectId) ?: CurriculumCatalog.tracks.first()
  val modulesForTrack =
    remember(structuredModules, activeTrack.id) {
      val fromDb = structuredModules.filter { it.subjectId == activeTrack.id }
      if (fromDb.isNotEmpty()) fromDb else activeTrack.modules
    }
  val progressMap = remember(progressList) { progressList.associateBy { it.lessonId } }
  val semantics = MaterialTheme.semanticColors

  val trackLessons = remember(modulesForTrack) { modulesForTrack.flatMap { it.lessons } }
  val masteredCount = trackLessons.count { progressMap[it.id]?.isCompleted == true }
  val trackRatio = if (trackLessons.isEmpty()) 0f else masteredCount / trackLessons.size.toFloat()
  val continueLesson = trackLessons.firstOrNull { progressMap[it.id]?.isCompleted != true } ?: trackLessons.firstOrNull()

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    Column(modifier = Modifier.widthIn(max = 920.dp).fillMaxSize()) {
      // Top Subject Selector Bar
      Column(
        modifier =
          Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Text(
          text = "Syllabus Tracks & Labs",
          style = MaterialTheme.typography.headlineMedium,
          color = MaterialTheme.colorScheme.onSurface,
        )
        Row(
          modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          CurriculumCatalog.tracks.forEach { track ->
            val selected = track.id == activeTrack.id
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
              modifier = Modifier.testTag("catalog_filter_${track.id}"),
            )
          }
        }
      }

      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        // Udacity Dark Navy Program Header Banner
        item {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(semantics.classroomBar)
                .border(1.5.dp, Color(0xFF28427B), RoundedCornerShape(20.dp))
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Surface(color = UdacityCyan, shape = RoundedCornerShape(6.dp)) {
                Text(
                  text = "${modulesForTrack.size} MODULES • ${trackLessons.size} CHAPTERS",
                  style = MaterialTheme.typography.labelSmall,
                  color = UdacityCyanInk,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                )
              }
              Text(
                text = "${(trackRatio * 100).roundToInt()}% Complete",
                style = MaterialTheme.typography.labelMedium.copy(fontFamily = JetBrainsMonoFontFamily),
                color = Color(0xFF7DD3FC),
                fontWeight = FontWeight.Bold,
              )
            }
            Text(
              text = activeTrack.title,
              style = MaterialTheme.typography.headlineSmall,
              color = semantics.onClassroomBar,
            )
            Text(
              text = activeTrack.tagline,
              style = MaterialTheme.typography.bodySmall,
              color = Color(0xFFE2E8F0),
            )
            Text(
              text = "${activeTrack.examCoverage} • ${activeTrack.pastQuestions.size} archived past questions (stored offline in Room)",
              style = MaterialTheme.typography.labelSmall,
              color = semantics.classroomSubtext,
            )
            LinearProgressIndicator(
              progress = { trackRatio.coerceAtLeast(0.03f) },
              modifier = Modifier.fillMaxWidth().height(7.dp).clip(CircleShape),
              color = UdacityCyan,
              trackColor = Color(0xFF1E3A6E),
            )
            if (continueLesson != null) {
              Button(
                onClick = { onOpenLesson(continueLesson) },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors =
                  ButtonDefaults.buttonColors(
                    containerColor = UdacityCyan,
                    contentColor = UdacityCyanInk,
                  ),
              ) {
                Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Continue: ${continueLesson.title}",
                  maxLines = 1,
                  style = MaterialTheme.typography.labelLarge,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }
        }

        // Udacity-Style Module Parts with Room-Backed Chapter Cards
        itemsIndexed(modulesForTrack, key = { _, m -> m.id }) { partIndex, courseModule ->
          val partMastered = courseModule.lessons.count { progressMap[it.id]?.isCompleted == true }
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
              verticalAlignment = Alignment.Top,
            ) {
              Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                  text = "PART 0${partIndex + 1} • ${courseModule.indexLabel.uppercase()}",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold,
                )
                Text(
                  text = courseModule.title,
                  style = MaterialTheme.typography.titleLarge,
                  color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                  text = courseModule.summary,
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
              }
              Surface(
                color =
                  if (partMastered == courseModule.lessons.size) semantics.successContainer
                  else MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(99.dp),
              ) {
                Text(
                  text = "$partMastered/${courseModule.lessons.size}",
                  style = MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFontFamily),
                  color =
                    if (partMastered == courseModule.lessons.size) semantics.onSuccessContainer
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                )
              }
            }

            courseModule.lessons.forEachIndexed { lessonIdx, lesson ->
              val prog = progressMap[lesson.id]
              val stepsDone = prog?.completedSteps?.size ?: 0
              val isDone = prog?.isCompleted == true

              Row(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                      1.5.dp,
                      if (isDone) semantics.success else MaterialTheme.colorScheme.outlineVariant,
                      RoundedCornerShape(14.dp),
                    )
                    .clickable { onOpenLesson(lesson) }
                    .testTag("lesson_row_${lesson.id}")
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
              ) {
                Box(
                  modifier =
                    Modifier.size(34.dp)
                      .clip(CircleShape)
                      .background(
                        if (isDone) semantics.successContainer
                        else MaterialTheme.colorScheme.primaryContainer
                      )
                      .border(
                        1.5.dp,
                        if (isDone) semantics.success else MaterialTheme.colorScheme.primary,
                        CircleShape,
                      ),
                  contentAlignment = Alignment.Center,
                ) {
                  if (isDone) {
                    Icon(
                      imageVector = Icons.Filled.CheckCircle,
                      contentDescription = "Completed",
                      tint = semantics.success,
                      modifier = Modifier.size(20.dp),
                    )
                  } else {
                    Text(
                      text = "${lessonIdx + 1}",
                      style = MaterialTheme.typography.labelLarge.copy(fontFamily = JetBrainsMonoFontFamily),
                      color = MaterialTheme.colorScheme.onPrimaryContainer,
                      fontWeight = FontWeight.Bold,
                    )
                  }
                }

                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                  Text(
                    text = lesson.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                  )
                  Text(
                    text = "${lesson.durationMinutes} min • ${lesson.syllabusTag}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                  )
                  Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                  ) {
                    Icon(
                      imageVector = Icons.Filled.Science,
                      contentDescription = null,
                      tint = MaterialTheme.colorScheme.secondary,
                      modifier = Modifier.size(14.dp),
                    )
                    Text(
                      text = "Lab: ${lesson.labType.title}",
                      style = MaterialTheme.typography.labelSmall,
                      color = MaterialTheme.colorScheme.secondary,
                      fontWeight = FontWeight.Bold,
                    )
                  }
                }

                Surface(
                  color =
                    if (isDone) semantics.successContainer
                    else MaterialTheme.colorScheme.surfaceVariant,
                  shape = RoundedCornerShape(99.dp),
                  modifier =
                    Modifier.border(
                      1.dp,
                      if (isDone) semantics.success else MaterialTheme.colorScheme.outlineVariant,
                      RoundedCornerShape(99.dp),
                    ),
                ) {
                  Text(
                    text = if (isDone) "MASTERED" else "$stepsDone/${LessonPillar.entries.size}",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFontFamily),
                    color =
                      if (isDone) semantics.onSuccessContainer
                      else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
                  )
                }
              }
            }
          }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
      }
    }
  }
}
