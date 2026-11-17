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
import androidx.compose.foundation.layout.IntrinsicSize
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
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.PracticeQuestion
import com.example.data.model.LessonProgress
import com.example.ui.components.EquationDisplayCard
import com.example.ui.components.ScientificText
import com.example.ui.labs.PracticalLabWorkspace
import com.example.ui.theme.UdacityCyan
import com.example.ui.theme.semanticColors

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
  val semantics = MaterialTheme.semanticColors
  val classroomRatio = completedSteps.size / LessonPillar.entries.size.toFloat()

  Column(
    modifier =
      Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .statusBarsPadding()
        .navigationBarsPadding(),
  ) {
    // Udacity Signature Dark Navy Classroom Top Bar + Progress Rail
    Column(modifier = Modifier.fillMaxWidth().background(semantics.classroomBar)) {
      Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        IconButton(onClick = onBack, modifier = Modifier.testTag("classroom_back_button")) {
          Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back to curriculum",
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
            text = "${lesson.syllabusTag} • ${completedSteps.size}/${LessonPillar.entries.size} pillars completed",
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
        color = UdacityCyan,
        trackColor = Color(0xFF1E3A6E),
      )
    }

    // Adaptive Split-Workspace (Tablet/Foldable sidebar vs Handheld numbered stepper)
    BoxWithConstraints(modifier = Modifier.weight(1f).fillMaxWidth()) {
      val isWide = maxWidth >= 720.dp

      if (isWide) {
        Row(modifier = Modifier.fillMaxSize()) {
          // Left Udacity Classroom Sidebar
          Column(
            modifier =
              Modifier.width(260.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Text(
              text = "CLASSROOM SYLLABUS",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            )
            LessonPillar.entries.forEachIndexed { idx, pillar ->
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
                    .border(
                      1.5.dp,
                      if (active) MaterialTheme.colorScheme.primary
                      else MaterialTheme.colorScheme.outlineVariant,
                      RoundedCornerShape(12.dp),
                    )
                    .clickable { selectedPillar = pillar }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
              ) {
                Icon(
                  imageVector = if (isDone) Icons.Filled.CheckCircle else Icons.Filled.Check,
                  contentDescription = null,
                  tint =
                    if (isDone) semantics.success
                    else if (active) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.size(18.dp),
                )
                Text(
                  text = "${idx + 1}. ${pillar.fullLabel}",
                  style = MaterialTheme.typography.labelLarge,
                  color =
                    if (active) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onSurface,
                  fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                )
              }
            }
          }

          LessonPillarBody(
            lesson = lesson,
            selectedPillar = selectedPillar,
            modifier = Modifier.weight(1f).fillMaxHeight(),
            onMarkExerciseComplete = { score -> onMarkPillarComplete(selectedPillar, score) },
          )
        }
      } else {
        Column(modifier = Modifier.fillMaxSize()) {
          // Handheld High-Contrast Numbered Stepper
          Row(
            modifier =
              Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            LessonPillar.entries.forEachIndexed { idx, pillar ->
              val active = pillar == selectedPillar
              val isDone = pillar.key in completedSteps
              val bgColor =
                when {
                  active -> MaterialTheme.colorScheme.primary
                  isDone -> semantics.successContainer
                  else -> MaterialTheme.colorScheme.background
                }
              val borderColor =
                when {
                  active -> MaterialTheme.colorScheme.primary
                  isDone -> semantics.success
                  else -> MaterialTheme.colorScheme.outline
                }
              val textColor =
                when {
                  active -> MaterialTheme.colorScheme.onPrimary
                  isDone -> semantics.onSuccessContainer
                  else -> MaterialTheme.colorScheme.onSurface
                }

              Surface(
                color = bgColor,
                shape = RoundedCornerShape(99.dp),
                modifier =
                  Modifier.clip(RoundedCornerShape(99.dp))
                    .border(1.5.dp, borderColor, RoundedCornerShape(99.dp))
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
                      tint = if (active) MaterialTheme.colorScheme.onPrimary else semantics.success,
                      modifier = Modifier.size(16.dp),
                    )
                  }
                  Text(
                    text = "${idx + 1}. ${pillar.shortLabel}",
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
            selectedPillar = selectedPillar,
            modifier = Modifier.weight(1f).fillMaxWidth(),
            onMarkExerciseComplete = { score -> onMarkPillarComplete(selectedPillar, score) },
          )
        }
      }
    }

    // Sticky Udacity Classroom Footer Controls
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
          selectedPillar = LessonPillar.entries[prevIdx]
        },
        enabled = selectedPillar.ordinal > 0,
        modifier = Modifier.height(48.dp),
        shape = RoundedCornerShape(12.dp),
      ) {
        Text("← Prev")
      }

      if (selectedPillar != LessonPillar.EXERCISE) {
        OutlinedButton(
          onClick = { onMarkPillarComplete(selectedPillar, null) },
          modifier = Modifier.weight(1f).height(48.dp).testTag("mark_pillar_complete"),
          shape = RoundedCornerShape(12.dp),
        ) {
          Text(
            text = if (isCurrentDone) "Completed ✓" else "Complete (+15 XP)",
            maxLines = 1,
          )
        }

        Button(
          onClick = {
            if (!isCurrentDone) onMarkPillarComplete(selectedPillar, null)
            val nextIdx = (selectedPillar.ordinal + 1).coerceAtMost(LessonPillar.entries.lastIndex)
            selectedPillar = LessonPillar.entries[nextIdx]
          },
          modifier = Modifier.weight(1f).height(48.dp).testTag("next_pillar_button"),
          shape = RoundedCornerShape(12.dp),
          colors =
            ButtonDefaults.buttonColors(
              containerColor = MaterialTheme.colorScheme.primary,
              contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
          Text("Next →", fontWeight = FontWeight.Bold)
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
  selectedPillar: LessonPillar,
  onMarkExerciseComplete: (Long?) -> Unit,
  modifier: Modifier = Modifier,
) {
  val semantics = MaterialTheme.semanticColors

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
      color = MaterialTheme.colorScheme.onBackground,
    )

    when (selectedPillar) {
      LessonPillar.CONCEPT -> {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(16.dp))
              .background(MaterialTheme.colorScheme.primaryContainer)
              .border(1.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
              .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          Text(
            text = "SYLLABUS OBJECTIVES",
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

        lesson.concepts.forEach { block ->
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
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
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
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
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
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
          labType = lesson.labType,
          labBrief = lesson.labBrief,
        )
      }

      LessonPillar.EXERCISE -> {
        InlineExerciseSection(
          questions = lesson.exercises,
          onGraded = { pct -> onMarkExerciseComplete(pct) },
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))
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
  val semantics = MaterialTheme.semanticColors

  Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
    questions.forEachIndexed { idx, q ->
      val chosen = selectedAnswers[q.id]
      Column(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
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
          val correct = questions.count { selectedAnswers[it.id] == it.correctIndex }
          val pct = if (questions.isEmpty()) 100L else ((correct * 100L) / questions.size)
          onGraded(pct)
        },
        enabled = selectedAnswers.size == questions.size,
        modifier = Modifier.fillMaxWidth().height(52.dp).testTag("submit_inline_exercise"),
        shape = RoundedCornerShape(14.dp),
      ) {
        Text("Check Answers & Claim +40 XP", fontWeight = FontWeight.Bold)
      }
    } else {
      val correct = questions.count { selectedAnswers[it.id] == it.correctIndex }
      Surface(
        color = semantics.successContainer,
        shape = RoundedCornerShape(16.dp),
        modifier =
          Modifier.fillMaxWidth()
            .border(1.5.dp, semantics.success, RoundedCornerShape(16.dp)),
      ) {
        Text(
          text = "Score: $correct / ${questions.size} mastered! Progress saved to your device.",
          style = MaterialTheme.typography.titleSmall,
          color = semantics.onSuccessContainer,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(14.dp),
        )
      }
    }
  }
}
