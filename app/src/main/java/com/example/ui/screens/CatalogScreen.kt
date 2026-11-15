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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.model.LessonProgress
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.MasteryEmerald

@Composable
fun CatalogScreen(
  initialSubjectId: String?,
  progressList: List<LessonProgress>,
  onOpenLesson: (Lesson) -> Unit,
  modifier: Modifier = Modifier,
) {
  var selectedSubjectId by remember(initialSubjectId) {
    mutableStateOf(initialSubjectId ?: CurriculumCatalog.tracks.first().id)
  }
  val activeTrack = CurriculumCatalog.trackById(selectedSubjectId) ?: CurriculumCatalog.tracks.first()
  val progressMap = remember(progressList) { progressList.associateBy { it.lessonId } }

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    Column(modifier = Modifier.widthIn(max = 920.dp).fillMaxSize()) {
      // Subject Filter Tabs
      Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Text("Systematic Syllabus Tracks", style = MaterialTheme.typography.headlineMedium)
        Row(
          modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          CurriculumCatalog.tracks.forEach { track ->
            FilterChip(
              selected = track.id == activeTrack.id,
              onClick = { selectedSubjectId = track.id },
              label = { Text(track.shortName) },
              modifier = Modifier.testTag("catalog_filter_${track.id}"),
            )
          }
        }
      }

      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        item {
          Surface(
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.fillMaxWidth(),
          ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(activeTrack.title, style = MaterialTheme.typography.titleLarge)
              Text(
                text = activeTrack.tagline,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
              )
              Text(
                text = "${activeTrack.examCoverage} • ${activeTrack.pastQuestions.size} archived past questions",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
              )
            }
          }
        }

        items(activeTrack.modules, key = { it.id }) { courseModule ->
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
          ) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
              Text(
                text = courseModule.indexLabel.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
              )
              Text(text = courseModule.title, style = MaterialTheme.typography.titleLarge)
              Text(
                text = courseModule.summary,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }

            courseModule.lessons.forEach { lesson ->
              val prog = progressMap[lesson.id]
              val stepsDone = prog?.completedSteps?.size ?: 0
              val isDone = prog?.isCompleted == true

              Row(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                      1.dp,
                      if (isDone) MasteryEmerald.copy(alpha = 0.45f) else MaterialTheme.colorScheme.outlineVariant,
                      RoundedCornerShape(16.dp),
                    )
                    .clickable { onOpenLesson(lesson) }
                    .testTag("lesson_row_${lesson.id}")
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
              ) {
                Icon(
                  imageVector = if (isDone) Icons.Filled.CheckCircle else Icons.Filled.PlayCircle,
                  contentDescription = if (isDone) "Completed" else "Start lesson",
                  tint = if (isDone) MasteryEmerald else MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(28.dp),
                )
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                  Text(text = lesson.title, style = MaterialTheme.typography.titleSmall)
                  Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                  ) {
                    Text(
                      text = "${lesson.durationMinutes} min • ${lesson.syllabusTag}",
                      style = MaterialTheme.typography.bodySmall,
                      color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                  }
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
                      text = lesson.labType.title,
                      style = MaterialTheme.typography.labelSmall,
                      color = MaterialTheme.colorScheme.secondary,
                    )
                  }
                }
                Surface(
                  color =
                    if (isDone) MasteryEmerald.copy(alpha = 0.14f)
                    else MaterialTheme.colorScheme.surfaceVariant,
                  shape = CircleShape,
                ) {
                  Text(
                    text = "$stepsDone/${LessonPillar.entries.size}",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFontFamily),
                    color = if (isDone) MasteryEmerald else MaterialTheme.colorScheme.onSurfaceVariant,
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
