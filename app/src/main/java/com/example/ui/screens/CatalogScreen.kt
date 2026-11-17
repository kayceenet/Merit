package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CourseModule
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.CurriculumProgression
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.model.LessonProgress
import com.example.ui.theme.CourseraAzureAccent
import com.example.ui.theme.CourseraAzureInk
import com.example.ui.theme.CourseraBlue
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
  val continueLesson =
    remember(trackLessons, progressMap) {
      CurriculumProgression.nextUnlockedLesson(trackLessons, progressMap)
    }
  val totalMinutes = remember(trackLessons) { trackLessons.sumOf { it.durationMinutes } }
  val bankQuestionCount =
    remember(activeTrack.id) { CurriculumCatalog.pastQuestionsFor(activeTrack.id, "ALL").size }

  // Collapsible module accordions; auto-expand the module containing the next unlocked lesson
  var expandedModuleIds by remember(activeTrack.id) {
    val activeModuleId = continueLesson?.moduleId ?: modulesForTrack.firstOrNull()?.id
    mutableStateOf(setOfNotNull(activeModuleId))
  }
  LaunchedEffect(activeTrack.id, continueLesson?.moduleId) {
    val targetMod = continueLesson?.moduleId ?: modulesForTrack.firstOrNull()?.id
    if (targetMod != null && expandedModuleIds.isEmpty()) {
      expandedModuleIds = setOf(targetMod)
    }
  }

  var lockedAlertTarget by remember { mutableStateOf<Pair<Lesson, String?>?>(null) }

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    Column(modifier = Modifier.widthIn(max = 920.dp).fillMaxSize()) {
      // Top Coursera Subject Switcher Bar
      Column(
        modifier =
          Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Text(
          text = "Course Syllabus & Mastery Modules",
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
        verticalArrangement = Arrangement.spacedBy(14.dp),
      ) {
        // Coursera Specialization Overview Banner
        item {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(semantics.classroomBar)
                .border(1.5.dp, Color(0xFF1E4C9A), RoundedCornerShape(18.dp))
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Surface(color = CourseraAzureAccent, shape = RoundedCornerShape(6.dp)) {
                Text(
                  text = "${modulesForTrack.size} MODULES • ${trackLessons.size} LESSONS",
                  style = MaterialTheme.typography.labelSmall,
                  color = CourseraAzureInk,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                )
              }
              Text(
                text = "${(trackRatio * 100).roundToInt()}% Mastered",
                style = MaterialTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
                color = CourseraAzureAccent,
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
              text = "${totalMinutes / 60}h ${totalMinutes % 60}m curriculum • $bankQuestionCount JAMB/WAEC questions • Sequential mastery lock enabled",
              style = MaterialTheme.typography.labelSmall,
              color = semantics.classroomSubtext,
            )

            LinearProgressIndicator(
              progress = { trackRatio.coerceAtLeast(0.03f) },
              modifier = Modifier.fillMaxWidth().height(7.dp).clip(CircleShape),
              color = CourseraAzureAccent,
              trackColor = Color(0xFF143C7B),
            )

            if (continueLesson != null) {
              Button(
                onClick = { onOpenLesson(continueLesson) },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(10.dp),
                colors =
                  ButtonDefaults.buttonColors(
                    containerColor = CourseraBlue,
                    contentColor = Color.White,
                  ),
              ) {
                Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Continue: ${continueLesson.title}",
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
                  style = MaterialTheme.typography.labelLarge,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }
        }

        // Coursera Collapsible Module / Week Accordions
        itemsIndexed(modulesForTrack, key = { _, m -> m.id }) { partIndex, courseModule ->
          val partMastered = courseModule.lessons.count { progressMap[it.id]?.isCompleted == true }
          val isExpanded = courseModule.id in expandedModuleIds

          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp)),
          ) {
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clickable {
                    expandedModuleIds =
                      if (isExpanded) expandedModuleIds - courseModule.id
                      else expandedModuleIds + courseModule.id
                  }
                  .padding(16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                  text = "MODULE 0${partIndex + 1} • ${courseModule.lessons.size} LESSONS",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold,
                )
                Text(
                  text = courseModule.title,
                  style = MaterialTheme.typography.titleMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                  text = courseModule.summary,
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
              }

              Spacer(modifier = Modifier.width(10.dp))

              Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Surface(
                  color =
                    if (partMastered == courseModule.lessons.size && courseModule.lessons.isNotEmpty())
                      semantics.successContainer
                    else MaterialTheme.colorScheme.surfaceVariant,
                  shape = RoundedCornerShape(99.dp),
                ) {
                  Text(
                    text = "$partMastered/${courseModule.lessons.size}",
                    style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                    color =
                      if (partMastered == courseModule.lessons.size && courseModule.lessons.isNotEmpty())
                        semantics.onSuccessContainer
                      else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                  )
                }
                Icon(
                  imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                  contentDescription = if (isExpanded) "Collapse module" else "Expand module",
                  tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
              }
            }

            AnimatedVisibility(visible = isExpanded) {
              Column(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
              ) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                courseModule.lessons.forEach { lesson ->
                  val globalLessonIndex = trackLessons.indexOfFirst { it.id == lesson.id } + 1
                  val unlockState =
                    CurriculumProgression.unlockStateForLesson(trackLessons, lesson.id, progressMap)
                  val isLocked = !unlockState.isUnlocked
                  val isDone = unlockState.isMastered
                  val stepsDone = unlockState.completedPillarsCount

                  Row(
                    modifier =
                      Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                          if (isLocked) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
                          else MaterialTheme.colorScheme.background
                        )
                        .border(
                          width = if (isDone) 1.5.dp else 1.dp,
                          color =
                            when {
                              isDone -> semantics.success
                              isLocked -> MaterialTheme.colorScheme.outlineVariant
                              else -> MaterialTheme.colorScheme.outline
                            },
                          shape = RoundedCornerShape(12.dp),
                        )
                        .clickable {
                          if (isLocked) {
                            lockedAlertTarget = lesson to unlockState.prerequisiteLessonTitle
                          } else {
                            onOpenLesson(lesson)
                          }
                        }
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
                            when {
                              isDone -> semantics.successContainer
                              isLocked -> MaterialTheme.colorScheme.surfaceVariant
                              else -> MaterialTheme.colorScheme.primaryContainer
                            }
                          )
                          .border(
                            1.5.dp,
                            when {
                              isDone -> semantics.success
                              isLocked -> MaterialTheme.colorScheme.outline
                              else -> MaterialTheme.colorScheme.primary
                            },
                            CircleShape,
                          ),
                      contentAlignment = Alignment.Center,
                    ) {
                      when {
                        isDone ->
                          Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Mastered",
                            tint = semantics.success,
                            modifier = Modifier.size(20.dp),
                          )
                        isLocked ->
                          Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Locked lesson",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp),
                          )
                        else ->
                          Text(
                            text = "$globalLessonIndex",
                            style = MaterialTheme.typography.labelLarge.copy(fontFeatureSettings = "tnum"),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                          )
                      }
                    }

                    Column(
                      modifier = Modifier.weight(1f),
                      verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                      Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.titleSmall,
                        color =
                          if (isLocked) MaterialTheme.colorScheme.onSurfaceVariant
                          else MaterialTheme.colorScheme.onSurface,
                      )
                      Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                      ) {
                        Icon(
                          imageVector = Icons.Filled.MenuBook,
                          contentDescription = null,
                          tint = MaterialTheme.colorScheme.onSurfaceVariant,
                          modifier = Modifier.size(13.dp),
                        )
                        Text(
                          text = "Reading • ${lesson.durationMinutes}m • ${lesson.exercises.size} Qs Quiz",
                          style = MaterialTheme.typography.bodySmall,
                          color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                      }
                      if (isLocked && !unlockState.prerequisiteLessonTitle.isNullOrBlank()) {
                        Text(
                          text = "Complete \"${unlockState.prerequisiteLessonTitle}\" (≥60% quiz) to unlock",
                          style = MaterialTheme.typography.labelSmall,
                          color = MaterialTheme.colorScheme.tertiary,
                          maxLines = 2,
                        )
                      } else {
                        Row(
                          horizontalArrangement = Arrangement.spacedBy(5.dp),
                          verticalAlignment = Alignment.CenterVertically,
                        ) {
                          Icon(
                            imageVector = Icons.Filled.Science,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(13.dp),
                          )
                          Text(
                            text = "Lab: ${lesson.labType.title}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                          )
                        }
                      }
                    }

                    Surface(
                      color =
                        when {
                          isDone -> semantics.successContainer
                          isLocked -> MaterialTheme.colorScheme.surfaceVariant
                          else -> MaterialTheme.colorScheme.primaryContainer
                        },
                      shape = RoundedCornerShape(99.dp),
                    ) {
                      Text(
                        text =
                          when {
                            isDone -> "MASTERED"
                            isLocked -> "LOCKED"
                            else -> "$stepsDone/${LessonPillar.entries.size}"
                          },
                        style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                        color =
                          when {
                            isDone -> semantics.onSuccessContainer
                            isLocked -> MaterialTheme.colorScheme.onSurfaceVariant
                            else -> MaterialTheme.colorScheme.onPrimaryContainer
                          },
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
                      )
                    }
                  }
                }
              }
            }
          }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
      }
    }
  }

  val pendingLock = lockedAlertTarget
  if (pendingLock != null) {
    val (lockedLesson, prereqTitle) = pendingLock
    val prereqLesson = CurriculumProgression.prerequisiteLessonFor(trackLessons, lockedLesson.id)
    val nextRunnable = continueLesson ?: prereqLesson
    AlertDialog(
      onDismissRequest = { lockedAlertTarget = null },
      icon = {
        Icon(
          Icons.Filled.Lock,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary,
        )
      },
      title = { Text("Lesson Locked by Mastery Path") },
      text = {
        Text(
          text =
            if (!prereqTitle.isNullOrBlank()) {
              "\"${lockedLesson.title}\" builds directly on \"$prereqTitle\". Complete all 5 sections and score at least 60% on the prerequisite Mastery Quiz to unlock this lesson."
            } else {
              "Complete the prerequisite lesson (≥60% quiz score) to unlock \"${lockedLesson.title}\"."
            },
          style = MaterialTheme.typography.bodyMedium,
        )
      },
      confirmButton = {
        if (nextRunnable != null && nextRunnable.id != lockedLesson.id) {
          Button(
            onClick = {
              lockedAlertTarget = null
              onOpenLesson(nextRunnable)
            }
          ) {
            Text("Go to Current Lesson")
          }
        }
      },
      dismissButton = {
        TextButton(onClick = { lockedAlertTarget = null }) { Text("Close") }
      },
    )
  }
}
