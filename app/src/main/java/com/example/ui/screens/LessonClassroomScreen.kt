package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
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
import com.example.data.curriculum.CurriculumProgression
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.PracticeQuestion
import com.example.data.curriculum.QuestionShuffler
import com.example.data.model.LessonProgress
import com.example.ui.components.EquationDisplayCard
import com.example.ui.components.ScientificText
import com.example.ui.components.SubjectHeroGraphic
import com.example.ui.components.TopicDiagramCard
import com.example.ui.labs.PracticalLabWorkspace
import com.example.ui.theme.CourseraAzureAccent
import com.example.ui.theme.semanticColors

@Composable
fun LessonClassroomScreen(
  lesson: Lesson,
  progress: LessonProgress?,
  nextLesson: Lesson? = null,
  onBack: () -> Unit,
  onMarkPillarComplete: (LessonPillar, Long?) -> Unit,
  onSaveNote: (title: String, content: String) -> Unit,
  onOpenNextLesson: ((Lesson) -> Unit)? = null,
) {
  BackHandler(onBack = onBack)

  val completedSteps = progress?.completedSteps?.distinct()?.toSet() ?: emptySet()
  var selectedPillar by remember(lesson.id) {
    val firstIncomplete =
      LessonPillar.entries.firstOrNull {
        it.key !in completedSteps && CurriculumProgression.isPillarUnlocked(it, completedSteps)
      }
    mutableStateOf(firstIncomplete ?: LessonPillar.CONCEPT)
  }
  var showNoteDialog by remember { mutableStateOf(false) }
  var lockNoticeMessage by remember { mutableStateOf<String?>(null) }

  val semantics = MaterialTheme.semanticColors
  val classroomRatio = completedSteps.size / LessonPillar.entries.size.toFloat()

  Column(
    modifier =
      Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .statusBarsPadding()
        .navigationBarsPadding(),
  ) {
    // Coursera Distraction-Free Classroom Header + Progress Rail
    Column(modifier = Modifier.fillMaxWidth().background(semantics.classroomBar)) {
      Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        IconButton(onClick = onBack, modifier = Modifier.testTag("classroom_back_button")) {
          Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back to syllabus",
            tint = semantics.onClassroomBar,
          )
        }
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = lesson.title,
            style = MaterialTheme.typography.titleMedium,
            color = semantics.onClassroomBar,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
          Text(
            text = "${lesson.syllabusTag} • ${completedSteps.size}/${LessonPillar.entries.size} sections completed",
            style = MaterialTheme.typography.labelSmall,
            color = semantics.classroomSubtext,
          )
        }
        IconButton(
          onClick = { showNoteDialog = true },
          modifier = Modifier.testTag("classroom_add_note_button"),
        ) {
          Icon(
            Icons.Filled.BookmarkAdd,
            contentDescription = "Save formula note",
            tint = semantics.classroomAccent,
          )
        }
      }
      LinearProgressIndicator(
        progress = { classroomRatio.coerceAtLeast(0.05f) },
        modifier = Modifier.fillMaxWidth().height(4.dp),
        color = CourseraAzureAccent,
        trackColor = Color(0xFF143C7B),
      )
    }

    if (!lockNoticeMessage.isNullOrBlank()) {
      Surface(
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = Modifier.fillMaxWidth(),
      ) {
        Text(
          text = lockNoticeMessage.orEmpty(),
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onTertiaryContainer,
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )
      }
    }

    // Adaptive Coursera Split-Workspace (Tablet sidebar vs Handheld stepper rail)
    BoxWithConstraints(modifier = Modifier.weight(1f).fillMaxWidth()) {
      val isWide = maxWidth >= 720.dp

      if (isWide) {
        Row(modifier = Modifier.fillMaxSize()) {
          // Left Coursera Module Outline Sidebar
          Column(
            modifier =
              Modifier.width(268.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Text(
              text = "LESSON ROADMAP",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            )
            LessonPillar.entries.forEach { pillar ->
              val isDone = pillar.key in completedSteps
              val isUnlocked = CurriculumProgression.isPillarUnlocked(pillar, completedSteps)
              val active = pillar == selectedPillar
              Row(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                      when {
                        active -> MaterialTheme.colorScheme.primaryContainer
                        !isUnlocked -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
                        else -> MaterialTheme.colorScheme.background
                      }
                    )
                    .border(
                      1.dp,
                      if (active) MaterialTheme.colorScheme.primary
                      else MaterialTheme.colorScheme.outlineVariant,
                      RoundedCornerShape(10.dp),
                    )
                    .clickable {
                      if (isUnlocked) {
                        lockNoticeMessage = null
                        selectedPillar = pillar
                      } else {
                        lockNoticeMessage =
                          "Complete ${LessonPillar.entries[(pillar.ordinal - 1).coerceAtLeast(0)].shortLabel} first to unlock ${pillar.shortLabel}."
                      }
                    }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
              ) {
                Icon(
                  imageVector =
                    when {
                      isDone -> Icons.Filled.CheckCircle
                      !isUnlocked -> Icons.Filled.Lock
                      else -> Icons.Filled.Check
                    },
                  contentDescription = null,
                  tint =
                    when {
                      isDone -> semantics.success
                      !isUnlocked -> MaterialTheme.colorScheme.onSurfaceVariant
                      active -> MaterialTheme.colorScheme.primary
                      else -> MaterialTheme.colorScheme.onSurfaceVariant
                    },
                  modifier = Modifier.size(18.dp),
                )
                Text(
                  text = pillar.fullLabel,
                  style = MaterialTheme.typography.labelLarge,
                  color =
                    when {
                      active -> MaterialTheme.colorScheme.onPrimaryContainer
                      !isUnlocked -> MaterialTheme.colorScheme.onSurfaceVariant
                      else -> MaterialTheme.colorScheme.onSurface
                    },
                  fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                )
              }
            }
          }

          LessonPillarBody(
            lesson = lesson,
            progress = progress,
            nextLesson = nextLesson,
            selectedPillar = selectedPillar,
            modifier = Modifier.weight(1f).fillMaxHeight(),
            onMarkExerciseComplete = { score -> onMarkPillarComplete(selectedPillar, score) },
            onOpenNextLesson = onOpenNextLesson,
          )
        }
      } else {
        Column(modifier = Modifier.fillMaxSize()) {
          // Handheld Coursera Sequential Section Stepper
          Row(
            modifier =
              Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            LessonPillar.entries.forEach { pillar ->
              val active = pillar == selectedPillar
              val isDone = pillar.key in completedSteps
              val isUnlocked = CurriculumProgression.isPillarUnlocked(pillar, completedSteps)
              val bgColor =
                when {
                  active -> MaterialTheme.colorScheme.primary
                  isDone -> semantics.successContainer
                  !isUnlocked -> MaterialTheme.colorScheme.surfaceVariant
                  else -> MaterialTheme.colorScheme.background
                }
              val borderColor =
                when {
                  active -> MaterialTheme.colorScheme.primary
                  isDone -> semantics.success
                  !isUnlocked -> MaterialTheme.colorScheme.outlineVariant
                  else -> MaterialTheme.colorScheme.outline
                }
              val textColor =
                when {
                  active -> MaterialTheme.colorScheme.onPrimary
                  isDone -> semantics.onSuccessContainer
                  !isUnlocked -> MaterialTheme.colorScheme.onSurfaceVariant
                  else -> MaterialTheme.colorScheme.onSurface
                }

              Surface(
                color = bgColor,
                shape = RoundedCornerShape(99.dp),
                modifier =
                  Modifier.clip(RoundedCornerShape(99.dp))
                    .border(1.2.dp, borderColor, RoundedCornerShape(99.dp))
                    .clickable {
                      if (isUnlocked) {
                        lockNoticeMessage = null
                        selectedPillar = pillar
                      } else {
                        lockNoticeMessage =
                          "Finish ${LessonPillar.entries[(pillar.ordinal - 1).coerceAtLeast(0)].shortLabel} first to unlock ${pillar.shortLabel}."
                      }
                    }
                    .testTag("pillar_tab_${pillar.key}"),
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                  when {
                    isDone ->
                      Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Completed",
                        tint = if (active) MaterialTheme.colorScheme.onPrimary else semantics.success,
                        modifier = Modifier.size(15.dp),
                      )
                    !isUnlocked ->
                      Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Locked",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(14.dp),
                      )
                  }
                  Text(
                    text = pillar.shortLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = textColor,
                    fontWeight = if (active || isDone) FontWeight.Bold else FontWeight.SemiBold,
                  )
                }
              }
            }
          }

          LessonPillarBody(
            lesson = lesson,
            progress = progress,
            nextLesson = nextLesson,
            selectedPillar = selectedPillar,
            modifier = Modifier.weight(1f).fillMaxWidth(),
            onMarkExerciseComplete = { score -> onMarkPillarComplete(selectedPillar, score) },
            onOpenNextLesson = onOpenNextLesson,
          )
        }
      }
    }

    // Sticky Coursera Classroom Footer Controls
    val isCurrentDone = selectedPillar.key in completedSteps
    Row(
      modifier =
        Modifier.fillMaxWidth()
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
          .padding(horizontal = 12.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      OutlinedButton(
        onClick = {
          val prevIdx = (selectedPillar.ordinal - 1).coerceAtLeast(0)
          lockNoticeMessage = null
          selectedPillar = LessonPillar.entries[prevIdx]
        },
        enabled = selectedPillar.ordinal > 0,
        modifier = Modifier.height(48.dp),
        shape = RoundedCornerShape(10.dp),
      ) {
        Text("← Prev")
      }

      if (selectedPillar != LessonPillar.EXERCISE) {
        OutlinedButton(
          onClick = {
            lockNoticeMessage = null
            onMarkPillarComplete(selectedPillar, null)
          },
          modifier = Modifier.weight(1f).height(48.dp).testTag("mark_pillar_complete"),
          shape = RoundedCornerShape(10.dp),
        ) {
          Text(
            text = if (isCurrentDone) "Completed ✓" else "Mark Complete",
            maxLines = 1,
          )
        }

        Button(
          onClick = {
            lockNoticeMessage = null
            if (!isCurrentDone) onMarkPillarComplete(selectedPillar, null)
            val nextIdx = (selectedPillar.ordinal + 1).coerceAtMost(LessonPillar.entries.lastIndex)
            selectedPillar = LessonPillar.entries[nextIdx]
          },
          modifier = Modifier.weight(1f).height(48.dp).testTag("next_pillar_button"),
          shape = RoundedCornerShape(10.dp),
          colors =
            ButtonDefaults.buttonColors(
              containerColor = MaterialTheme.colorScheme.primary,
              contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
          Text("Next Section →", fontWeight = FontWeight.Bold)
        }
      } else {
        Spacer(modifier = Modifier.weight(1f))
      }
    }
  }

  if (showNoteDialog) {
    var title by remember { mutableStateOf("${lesson.title.take(28)} Formula") }
    var content by remember {
      mutableStateOf(
        lesson.concepts.firstOrNull()?.keyFormula ?: lesson.learningOutcomes.firstOrNull().orEmpty()
      )
    }
    AlertDialog(
      onDismissRequest = { showNoteDialog = false },
      title = { Text("Save to Formula Notebook") },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Note Heading") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
          )
          OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Key Formula / Concept") },
            minLines = 3,
            modifier = Modifier.fillMaxWidth(),
          )
        }
      },
      confirmButton = {
        Button(
          onClick = {
            onSaveNote(title, content)
            showNoteDialog = false
          }
        ) {
          Text("Save Note")
        }
      },
      dismissButton = {
        TextButton(onClick = { showNoteDialog = false }) { Text("Cancel") }
      },
    )
  }
}

@Composable
private fun LessonPillarBody(
  lesson: Lesson,
  progress: LessonProgress?,
  nextLesson: Lesson?,
  selectedPillar: LessonPillar,
  onMarkExerciseComplete: (Long?) -> Unit,
  onOpenNextLesson: ((Lesson) -> Unit)?,
  modifier: Modifier = Modifier,
) {
  val semantics = MaterialTheme.semanticColors

  Column(
    modifier =
      modifier
        .verticalScroll(rememberScrollState(initial = 0))
        .padding(horizontal = 16.dp, vertical = 16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp),
  ) {
    Text(
      text = selectedPillar.fullLabel,
      style = MaterialTheme.typography.headlineSmall,
      color = MaterialTheme.colorScheme.onBackground,
    )

    when (selectedPillar) {
      LessonPillar.CONCEPT -> {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(14.dp))
              .background(MaterialTheme.colorScheme.primaryContainer)
              .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(14.dp))
              .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          Text(
            text = "WHAT YOU WILL MASTER IN THIS LESSON",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
          )
          lesson.learningOutcomes.forEach { outcome ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
              Text("•", color = MaterialTheme.colorScheme.onPrimaryContainer, fontWeight = FontWeight.Bold)
              ScientificText(
                text = outcome,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
              )
            }
          }
        }

        SubjectHeroGraphic(subjectId = lesson.subjectId)
        TopicDiagramCard(lesson = lesson)

        lesson.concepts.forEachIndexed { idx, block ->
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Surface(
              color = MaterialTheme.colorScheme.surfaceVariant,
              shape = RoundedCornerShape(6.dp),
            ) {
              Text(
                text = "CONCEPT BLOCK ${idx + 1} OF ${lesson.concepts.size}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
              )
            }
            Text(
              text = block.heading,
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            ScientificText(
              text = block.explanation,
              style = MaterialTheme.typography.bodyLarge,
              color = MaterialTheme.colorScheme.onSurface,
            )
            if (!block.keyFormula.isNullOrBlank()) {
              EquationDisplayCard(
                formula = block.keyFormula,
                badgeLabel = "SCIENTIFIC FORMULA",
              )
            }
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .background(MaterialTheme.colorScheme.tertiaryContainer)
                  .border(1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(12.dp))
                  .padding(12.dp),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Icon(
                Icons.Filled.Lightbulb,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.size(18.dp),
              )
              ScientificText(
                text = "Examiner Tip: ${block.examTip}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                fontWeight = FontWeight.Medium,
              )
            }
          }
        }
      }

      LessonPillar.EXAMPLES -> {
        lesson.workedExamples.forEach { example ->
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Surface(
              color = MaterialTheme.colorScheme.secondaryContainer,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = example.examSource,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
            Text(
              text = example.title,
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            ScientificText(
              text = example.problemStatement,
              style = MaterialTheme.typography.bodyLarge,
              color = MaterialTheme.colorScheme.onSurface,
              fontWeight = FontWeight.SemiBold,
            )
            Column(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .background(MaterialTheme.colorScheme.background)
                  .padding(12.dp),
              verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
              example.steps.forEach { step ->
                ScientificText(
                  text = step,
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                )
              }
            }
            Surface(
              color = semantics.successContainer,
              shape = RoundedCornerShape(12.dp),
              modifier =
                Modifier.fillMaxWidth()
                  .border(1.dp, semantics.success, RoundedCornerShape(12.dp)),
            ) {
              ScientificText(
                text = "Final Answer: ${example.finalAnswer}",
                style = MaterialTheme.typography.titleSmall,
                color = semantics.onSuccessContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(12.dp),
              )
            }
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .background(MaterialTheme.colorScheme.errorContainer)
                  .border(1.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(12.dp))
                  .padding(12.dp),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Icon(
                Icons.Filled.WarningAmber,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.size(18.dp),
              )
              ScientificText(
                text = "Common Trap: ${example.examinerTrap}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onErrorContainer,
                fontWeight = FontWeight.Medium,
              )
            }
          }
        }
      }

      LessonPillar.SCENARIO -> {
        lesson.scenarios.forEach { scenario ->
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Icon(
                Icons.Filled.Public,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
              )
              Text(
                text = scenario.industryDomain.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
              )
            }
            Text(
              text = scenario.title,
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            ScientificText(
              text = scenario.narrative,
              style = MaterialTheme.typography.bodyLarge,
              color = MaterialTheme.colorScheme.onSurface,
            )
            Surface(
              color = MaterialTheme.colorScheme.primaryContainer,
              shape = RoundedCornerShape(12.dp),
              modifier =
                Modifier.fillMaxWidth()
                  .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp)),
            ) {
              ScientificText(
                text = "Exam Connection: ${scenario.examConnection}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(12.dp),
              )
            }
          }
        }
      }

      LessonPillar.LAB -> {
        PracticalLabWorkspace(
          lessonId = lesson.id,
          labType = lesson.labType,
          labBrief = lesson.labBrief,
        )
      }

      LessonPillar.EXERCISE -> {
        InlineExerciseSection(
          lessonId = lesson.id,
          questions = lesson.exercises,
          isAlreadyMastered = progress?.isCompleted == true,
          nextLesson = nextLesson,
          onGraded = { pct -> onMarkExerciseComplete(pct) },
          onOpenNextLesson = onOpenNextLesson,
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))
  }
}

@Composable
private fun InlineExerciseSection(
  lessonId: String,
  questions: List<PracticeQuestion>,
  isAlreadyMastered: Boolean,
  nextLesson: Lesson?,
  onGraded: (Long) -> Unit,
  onOpenNextLesson: ((Lesson) -> Unit)?,
) {
  val selectedAnswers = remember(lessonId) { mutableStateMapOf<String, Int>() }
  val hintShown = remember(lessonId) { mutableStateMapOf<String, Boolean>() }
  var submitted by remember(lessonId) { mutableStateOf(false) }
  var attemptCount by remember(lessonId) { mutableIntStateOf(0) }
  var retryNonce by remember(lessonId) { mutableIntStateOf(0) }
  var seenIds by remember(lessonId) { mutableStateOf(emptySet<String>()) }
  var lastRoundIds by remember(lessonId) { mutableStateOf(emptySet<String>()) }
  val batchSize = minOf(3, questions.size).coerceAtLeast(1)
  val activeQuestions =
    remember(lessonId, retryNonce, questions) {
      QuestionShuffler.prepareFreshQuestionSet(
        pool = questions,
        count = batchSize,
        previouslySeenIds = seenIds,
        lastRoundIds = lastRoundIds,
      )
    }
  val semantics = MaterialTheme.semanticColors

  Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
    Surface(
      color = MaterialTheme.colorScheme.surfaceVariant,
      shape = RoundedCornerShape(10.dp),
      modifier = Modifier.fillMaxWidth(),
    ) {
      Text(
        text =
          "Attempt #${attemptCount + 1} • ${activeQuestions.size} Fresh Questions (Bank: ${questions.size}) • Shuffled A–D",
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
      )
    }

    activeQuestions.forEachIndexed { idx, q ->
      val chosen = selectedAnswers[q.id]
      Column(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        Text(
          text = "Q${idx + 1} • ${q.examType} ${q.year}",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold,
        )
        ScientificText(
          text = q.question,
          style = MaterialTheme.typography.titleMedium,
          color = MaterialTheme.colorScheme.onSurface,
        )

        q.options.forEachIndexed { optIdx, optText ->
          val isSelected = chosen == optIdx
          val borderColor =
            when {
              submitted && optIdx == q.correctIndex -> semantics.success
              submitted && isSelected && optIdx != q.correctIndex -> MaterialTheme.colorScheme.error
              isSelected -> MaterialTheme.colorScheme.primary
              else -> MaterialTheme.colorScheme.outline
            }
          val bgColor =
            when {
              submitted && optIdx == q.correctIndex -> semantics.successContainer
              submitted && isSelected && optIdx != q.correctIndex -> MaterialTheme.colorScheme.errorContainer
              isSelected -> MaterialTheme.colorScheme.primaryContainer
              else -> MaterialTheme.colorScheme.background
            }
          val optionTextColor =
            when {
              submitted && optIdx == q.correctIndex -> semantics.onSuccessContainer
              submitted && isSelected && optIdx != q.correctIndex -> MaterialTheme.colorScheme.onErrorContainer
              isSelected -> MaterialTheme.colorScheme.onPrimaryContainer
              else -> MaterialTheme.colorScheme.onSurface
            }

          Row(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(bgColor)
                .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
                .clickable(enabled = !submitted) { selectedAnswers[q.id] = optIdx }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Box(
              modifier =
                Modifier.size(28.dp)
                  .clip(CircleShape)
                  .background(
                    if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant
                  ),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = ('A' + optIdx).toString(),
                style = MaterialTheme.typography.labelMedium,
                color =
                  if (isSelected) MaterialTheme.colorScheme.onPrimary
                  else MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
              )
            }
            ScientificText(
              text = optText,
              style = MaterialTheme.typography.bodyMedium,
              color = optionTextColor,
              fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            )
          }
        }

        if (!submitted) {
          TextButton(onClick = { hintShown[q.id] = !(hintShown[q.id] ?: false) }) {
            Text(if (hintShown[q.id] == true) "Hide Hint" else "Show Academic Hint")
          }
          if (hintShown[q.id] == true) {
            Surface(
              color = MaterialTheme.colorScheme.tertiaryContainer,
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.fillMaxWidth(),
            ) {
              ScientificText(
                text = "Hint: ${q.hint}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.padding(10.dp),
              )
            }
          }
        } else {
          Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "Step-by-Step Solution:",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
              )
              ScientificText(
                text = q.stepByStepSolution,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
              )
              if (!q.waecMarkingScheme.isNullOrBlank()) {
                ScientificText(
                  text = "WAEC Marking Scheme: ${q.waecMarkingScheme}",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }
        }
      }
    }

    if (!submitted) {
      Button(
        onClick = {
          submitted = true
          attemptCount++
          val roundIds = activeQuestions.map { it.id }.toSet()
          val updatedSeen = seenIds + roundIds
          seenIds = if (updatedSeen.size >= questions.size) roundIds else updatedSeen
          lastRoundIds = roundIds
          val correct = activeQuestions.count { selectedAnswers[it.id] == it.correctIndex }
          val pct = if (activeQuestions.isEmpty()) 100L else ((correct * 100L) / activeQuestions.size)
          onGraded(pct)
        },
        enabled = selectedAnswers.size == activeQuestions.size,
        modifier = Modifier.fillMaxWidth().height(52.dp).testTag("submit_inline_exercise"),
        shape = RoundedCornerShape(12.dp),
      ) {
        Text("Check Answers & Grade Quiz (≥60% to Unlock)", fontWeight = FontWeight.Bold)
      }
    } else {
      val correct = activeQuestions.count { selectedAnswers[it.id] == it.correctIndex }
      val pct = if (activeQuestions.isEmpty()) 100L else ((correct * 100L) / activeQuestions.size)
      val passed = pct >= CurriculumProgression.PASS_THRESHOLD_PERCENT || isAlreadyMastered

      Column(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(if (passed) semantics.successContainer else MaterialTheme.colorScheme.errorContainer)
            .border(
              1.5.dp,
              if (passed) semantics.success else MaterialTheme.colorScheme.error,
              RoundedCornerShape(16.dp),
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        Text(
          text =
            if (passed) {
              "Lesson Mastered • $correct / ${activeQuestions.size} (${pct}%)"
            } else {
              "Not Mastered Yet • $correct / ${activeQuestions.size} (${pct}%)"
            },
          style = MaterialTheme.typography.titleMedium,
          color = if (passed) semantics.onSuccessContainer else MaterialTheme.colorScheme.onErrorContainer,
          fontWeight = FontWeight.Bold,
        )
        Text(
          text =
            if (passed) {
              if (nextLesson != null) "Next lesson unlocked: \"${nextLesson.title}\""
              else "Final lesson in this track completed! You are ready for timed Exam Lab mocks."
            } else {
              "Score at least 60% (${(activeQuestions.size * 6 + 9) / 10}/${activeQuestions.size}) to master this lesson. Tap Retry for fresh unseen questions!"
            },
          style = MaterialTheme.typography.bodySmall,
          color = if (passed) semantics.onSuccessContainer else MaterialTheme.colorScheme.onErrorContainer,
        )

        if (passed && nextLesson != null && onOpenNextLesson != null) {
          Button(
            onClick = { onOpenNextLesson(nextLesson) },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("continue_next_lesson_button"),
            shape = RoundedCornerShape(10.dp),
            colors =
              ButtonDefaults.buttonColors(
                containerColor = semantics.success,
                contentColor = semantics.onSuccess,
              ),
          ) {
            Text("Continue to Next Lesson", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(6.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
          }
          OutlinedButton(
            onClick = {
              selectedAnswers.clear()
              hintShown.clear()
              submitted = false
              retryNonce++
            },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("retry_inline_exercise"),
            shape = RoundedCornerShape(10.dp),
          ) {
            Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Practice ${activeQuestions.size} Fresh Questions", fontWeight = FontWeight.SemiBold)
          }
        } else {
          Button(
            onClick = {
              selectedAnswers.clear()
              hintShown.clear()
              submitted = false
              retryNonce++
            },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("retry_inline_exercise"),
            shape = RoundedCornerShape(10.dp),
          ) {
            Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text =
                if (passed) "Practice ${activeQuestions.size} Fresh Questions"
                else "Retry with Fresh Questions (Attempt #${attemptCount + 1})",
              fontWeight = FontWeight.Bold,
            )
          }
        }
      }
    }
  }
}
