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
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.PracticeQuestion
import com.example.data.model.LessonProgress
import com.example.ui.labs.PracticalLabWorkspace
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.MasteryEmerald

@Composable
fun LessonClassroomScreen(
  lesson: Lesson,
  progress: LessonProgress?,
  onBack: () -> Unit,
  onMarkPillarComplete: (LessonPillar, Long?) -> Unit,
  onSaveNote: (title: String, content: String) -> Unit,
) {
  BackHandler(onBack = onBack)

  var selectedPillar by remember(lesson.id) { mutableStateOf(LessonPillar.CONCEPT) }
  var showNoteDialog by remember { mutableStateOf(false) }
  val completedSteps = progress?.completedSteps?.toSet() ?: emptySet()

  Column(
    modifier =
      Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .statusBarsPadding()
        .navigationBarsPadding(),
  ) {
    // Udacity-style Classroom Top Header
    Row(
      modifier =
        Modifier.fillMaxWidth()
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
          .padding(horizontal = 8.dp, vertical = 8.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      IconButton(onClick = onBack, modifier = Modifier.testTag("classroom_back_button")) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to curriculum")
      }
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = lesson.title,
          style = MaterialTheme.typography.titleMedium,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
        Text(
          text = "${lesson.syllabusTag} • ${completedSteps.size}/${LessonPillar.entries.size} pillars",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.secondary,
        )
      }
      IconButton(
        onClick = { showNoteDialog = true },
        modifier = Modifier.testTag("classroom_add_note_button"),
      ) {
        Icon(
          Icons.Filled.BookmarkAdd,
          contentDescription = "Save formula note",
          tint = MaterialTheme.colorScheme.primary,
        )
      }
    }

    // Adaptive Split-Workspace (Tablet/Foldable sidebar vs Handheld top pillar stepper)
    BoxWithConstraints(modifier = Modifier.weight(1f).fillMaxWidth()) {
      val isWide = maxWidth >= 720.dp

      if (isWide) {
        Row(modifier = Modifier.fillMaxSize()) {
          // Left Udacity-style Syllabus Pillar Sidebar
          Column(
            modifier =
              Modifier.width(250.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Text(
              text = "MASTERY PILLARS",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            )
            LessonPillar.entries.forEach { pillar ->
              val isDone = pillar.key in completedSteps
              val active = pillar == selectedPillar
              Row(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                      if (active) MaterialTheme.colorScheme.primaryContainer
                      else MaterialTheme.colorScheme.background
                    )
                    .clickable { selectedPillar = pillar }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                Icon(
                  imageVector = if (isDone) Icons.Filled.CheckCircle else Icons.Filled.Check,
                  contentDescription = null,
                  tint =
                    if (isDone) MasteryEmerald
                    else if (active) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                  modifier = Modifier.size(18.dp),
                )
                Text(
                  text = pillar.fullLabel,
                  style = MaterialTheme.typography.labelLarge,
                  color =
                    if (active) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onSurface,
                )
              }
            }
          }

          LessonPillarBody(
            lesson = lesson,
            selectedPillar = selectedPillar,
            isPillarCompleted = selectedPillar.key in completedSteps,
            onMarkComplete = { score -> onMarkPillarComplete(selectedPillar, score) },
            onNextPillar = {
              val nextIdx = (selectedPillar.ordinal + 1).coerceAtMost(LessonPillar.entries.lastIndex)
              selectedPillar = LessonPillar.entries[nextIdx]
            },
            modifier = Modifier.weight(1f).fillMaxHeight(),
          )
        }
      } else {
        Column(modifier = Modifier.fillMaxSize()) {
          // Handheld Horizontal Pillar Stepper
          Row(
            modifier =
              Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            LessonPillar.entries.forEach { pillar ->
              val active = pillar == selectedPillar
              val isDone = pillar.key in completedSteps
              Surface(
                color =
                  when {
                    active -> MaterialTheme.colorScheme.primary
                    isDone -> MasteryEmerald.copy(alpha = 0.14f)
                    else -> MaterialTheme.colorScheme.surfaceVariant
                  },
                shape = RoundedCornerShape(99.dp),
                modifier =
                  Modifier.clip(RoundedCornerShape(99.dp))
                    .clickable { selectedPillar = pillar }
                    .testTag("pillar_tab_${pillar.key}"),
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                  if (isDone) {
                    Icon(
                      imageVector = Icons.Filled.CheckCircle,
                      contentDescription = "Completed",
                      tint = if (active) MaterialTheme.colorScheme.onPrimary else MasteryEmerald,
                      modifier = Modifier.size(15.dp),
                    )
                  }
                  Text(
                    text = pillar.shortLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color =
                      when {
                        active -> MaterialTheme.colorScheme.onPrimary
                        isDone -> MasteryEmerald
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                      },
                    fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                  )
                }
              }
            }
          }

          LessonPillarBody(
            lesson = lesson,
            selectedPillar = selectedPillar,
            isPillarCompleted = selectedPillar.key in completedSteps,
            onMarkComplete = { score -> onMarkPillarComplete(selectedPillar, score) },
            onNextPillar = {
              val nextIdx = (selectedPillar.ordinal + 1).coerceAtMost(LessonPillar.entries.lastIndex)
              selectedPillar = LessonPillar.entries[nextIdx]
            },
            modifier = Modifier.weight(1f).fillMaxWidth(),
          )
        }
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
  selectedPillar: LessonPillar,
  isPillarCompleted: Boolean,
  onMarkComplete: (Long?) -> Unit,
  onNextPillar: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier =
      modifier
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 16.dp, vertical = 16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp),
  ) {
    Text(
      text = selectedPillar.fullLabel,
      style = MaterialTheme.typography.headlineSmall,
    )

    when (selectedPillar) {
      LessonPillar.CONCEPT -> {
        // Learning Outcomes Card
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(18.dp))
              .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f))
              .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          Text(
            text = "SYLLABUS OBJECTIVES",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
          )
          lesson.learningOutcomes.forEach { outcome ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
              Text("•", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
              Text(outcome, style = MaterialTheme.typography.bodyMedium)
            }
          }
        }

        lesson.concepts.forEach { block ->
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Text(block.heading, style = MaterialTheme.typography.titleMedium)
            Text(block.explanation, style = MaterialTheme.typography.bodyLarge)
            if (!block.keyFormula.isNullOrBlank()) {
              Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
              ) {
                Text(
                  text = block.keyFormula,
                  style = MaterialTheme.typography.bodyMedium.copy(fontFamily = JetBrainsMonoFontFamily),
                  color = MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(12.dp),
                )
              }
            }
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f))
                  .padding(12.dp),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Icon(
                Icons.Filled.Lightbulb,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(18.dp),
              )
              Text(
                text = "Examiner Tip: ${block.examTip}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
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
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
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
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
            Text(example.title, style = MaterialTheme.typography.titleMedium)
            Text(
              text = example.problemStatement,
              style = MaterialTheme.typography.bodyLarge,
              fontWeight = FontWeight.Medium,
            )
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
              example.steps.forEach { step ->
                Text(
                  text = step,
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
              }
            }
            Surface(
              color = MasteryEmerald.copy(alpha = 0.14f),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier.fillMaxWidth(),
            ) {
              Text(
                text = "Final Answer: ${example.finalAnswer}",
                style = MaterialTheme.typography.titleSmall.copy(fontFamily = JetBrainsMonoFontFamily),
                color = MasteryEmerald,
                modifier = Modifier.padding(12.dp),
              )
            }
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .background(MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.45f))
                  .padding(12.dp),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Icon(
                Icons.Filled.WarningAmber,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(18.dp),
              )
              Text(
                text = "Common Trap: ${example.examinerTrap}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onErrorContainer,
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
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
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
              )
            }
            Text(scenario.title, style = MaterialTheme.typography.titleMedium)
            Text(scenario.narrative, style = MaterialTheme.typography.bodyLarge)
            Surface(
              color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier.fillMaxWidth(),
            ) {
              Text(
                text = "Exam Connection: ${scenario.examConnection}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(12.dp),
              )
            }
          }
        }
      }

      LessonPillar.LAB -> {
        PracticalLabWorkspace(
          labType = lesson.labType,
          labBrief = lesson.labBrief,
        )
      }

      LessonPillar.EXERCISE -> {
        InlineExerciseSection(
          questions = lesson.exercises,
          onGraded = { pct -> onMarkComplete(pct) },
        )
      }
    }

    // Bottom Action Bar inside Lesson Body
    if (selectedPillar != LessonPillar.EXERCISE) {
      Row(
        modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        OutlinedButton(
          onClick = { onMarkComplete(null) },
          modifier = Modifier.weight(1f).height(50.dp).testTag("mark_pillar_complete"),
          shape = RoundedCornerShape(14.dp),
        ) {
          Text(if (isPillarCompleted) "Completed ✓" else "Mark Complete (+15 XP)")
        }
        Button(
          onClick = {
            if (!isPillarCompleted) onMarkComplete(null)
            onNextPillar()
          },
          modifier = Modifier.weight(1f).height(50.dp).testTag("next_pillar_button"),
          shape = RoundedCornerShape(14.dp),
        ) {
          Text("Next Pillar →")
        }
      }
    }
    Spacer(modifier = Modifier.height(20.dp))
  }
}

@Composable
private fun InlineExerciseSection(
  questions: List<PracticeQuestion>,
  onGraded: (Long) -> Unit,
) {
  val selectedAnswers = remember { mutableStateMapOf<String, Int>() }
  val hintShown = remember { mutableStateMapOf<String, Boolean>() }
  var submitted by remember { mutableStateOf(false) }

  Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
    questions.forEachIndexed { idx, q ->
      val chosen = selectedAnswers[q.id]
      Column(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        Text(
          text = "Q${idx + 1} • ${q.examType} ${q.year}",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.secondary,
        )
        Text(text = q.question, style = MaterialTheme.typography.titleMedium)

        q.options.forEachIndexed { optIdx, optText ->
          val isSelected = chosen == optIdx
          val borderColor =
            when {
              submitted && optIdx == q.correctIndex -> MasteryEmerald
              submitted && isSelected && optIdx != q.correctIndex -> MaterialTheme.colorScheme.error
              isSelected -> MaterialTheme.colorScheme.primary
              else -> MaterialTheme.colorScheme.outlineVariant
            }
          val bgColor =
            when {
              submitted && optIdx == q.correctIndex -> MasteryEmerald.copy(alpha = 0.12f)
              submitted && isSelected && optIdx != q.correctIndex ->
                MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f)
              isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
              else -> MaterialTheme.colorScheme.background
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
                Modifier.size(26.dp)
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
                  else MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
            Text(text = optText, style = MaterialTheme.typography.bodyMedium)
          }
        }

        if (!submitted) {
          TextButton(onClick = { hintShown[q.id] = !(hintShown[q.id] ?: false) }) {
            Text(if (hintShown[q.id] == true) "Hide Hint" else "Show Academic Hint")
          }
          if (hintShown[q.id] == true) {
            Text(
              text = "Hint: ${q.hint}",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.tertiary,
            )
          }
        } else {
          Surface(
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "Step-by-Step Solution:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
              )
              Text(q.stepByStepSolution, style = MaterialTheme.typography.bodySmall)
              if (!q.waecMarkingScheme.isNullOrBlank()) {
                Text(
                  text = "WAEC Marking Scheme: ${q.waecMarkingScheme}",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.secondary,
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
          val correct = questions.count { selectedAnswers[it.id] == it.correctIndex }
          val pct = if (questions.isEmpty()) 100L else ((correct * 100L) / questions.size)
          onGraded(pct)
        },
        enabled = selectedAnswers.size == questions.size,
        modifier = Modifier.fillMaxWidth().height(52.dp).testTag("submit_inline_exercise"),
        shape = RoundedCornerShape(14.dp),
      ) {
        Text("Check Answers & Claim +40 XP")
      }
    } else {
      val correct = questions.count { selectedAnswers[it.id] == it.correctIndex }
      Surface(
        color = MasteryEmerald.copy(alpha = 0.14f),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
      ) {
        Text(
          text = "Score: $correct / ${questions.size} mastered! Progress synced to cloud.",
          style = MaterialTheme.typography.titleSmall,
          color = MasteryEmerald,
          modifier = Modifier.padding(14.dp),
        )
      }
    }
  }
}
