package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
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
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.Paper2AndPlacementBank
import com.example.ui.theme.semanticColors

private enum class PlacementStep { SELECT_SUBJECT, QUIZ, RESULT }

@Composable
fun PlacementAssessmentDialog(
  initialSubjectId: String? = null,
  alreadyPassedSubjects: Set<String>,
  onCompletePlacement: (subjectId: String, scorePercent: Int) -> Unit,
  onDismiss: () -> Unit,
) {
  var step by remember {
    mutableStateOf(
      if (initialSubjectId != null) PlacementStep.QUIZ else PlacementStep.SELECT_SUBJECT
    )
  }
  var selectedSubjectId by remember {
    mutableStateOf(initialSubjectId ?: CurriculumCatalog.tracks.first().id)
  }
  val questions = remember(selectedSubjectId) {
    Paper2AndPlacementBank.placementQuestionsFor(selectedSubjectId)
  }
  var qIndex by remember(selectedSubjectId) { mutableIntStateOf(0) }
  val chosenAnswers = remember(selectedSubjectId) { mutableStateMapOf<Int, Int>() }
  val semantics = MaterialTheme.semanticColors

  AlertDialog(
    onDismissRequest = onDismiss,
    title = {
      Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
          text = "SS3 Diagnostic Placement Check",
          style = MaterialTheme.typography.titleLarge,
          color = MaterialTheme.colorScheme.onSurface,
          fontWeight = FontWeight.Bold,
        )
        Text(
          text =
            when (step) {
              PlacementStep.SELECT_SUBJECT ->
                "Score 75%+ on an 8-item diagnostic check to fast-unlock the foundational 60% of a subject track."
              PlacementStep.QUIZ ->
                "${CurriculumCatalog.trackById(selectedSubjectId)?.shortName ?: selectedSubjectId} • Item ${qIndex + 1} of ${questions.size}"
              PlacementStep.RESULT ->
                "Diagnostic Placement Result"
            },
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }
    },
    text = {
      Column(
        modifier = Modifier.fillMaxWidth().heightIn(max = 440.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        when (step) {
          PlacementStep.SELECT_SUBJECT -> {
            CurriculumCatalog.tracks.forEach { track ->
              val passed = track.id in alreadyPassedSubjects
              val active = selectedSubjectId == track.id
              Row(
                modifier =
                  Modifier.fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                      if (active) MaterialTheme.colorScheme.primaryContainer
                      else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
                    )
                    .border(
                      width = if (active) 1.5.dp else 1.dp,
                      color =
                        if (active) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.outlineVariant,
                      shape = RoundedCornerShape(12.dp),
                    )
                    .clickable { selectedSubjectId = track.id }
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .testTag("placement_subject_${track.id}"),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = track.title,
                    style = MaterialTheme.typography.titleSmall,
                    color =
                      if (active) MaterialTheme.colorScheme.onPrimaryContainer
                      else MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                  )
                  Text(
                    text = "${track.allLessons.size} lessons in track",
                    style = MaterialTheme.typography.bodySmall,
                    color =
                      if (active) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                      else MaterialTheme.colorScheme.onSurfaceVariant,
                  )
                }
                Surface(
                  color =
                    if (passed) semantics.successContainer
                    else MaterialTheme.colorScheme.secondaryContainer,
                  shape = RoundedCornerShape(99.dp),
                ) {
                  Text(
                    text = if (passed) "UNLOCKED" else "CHECK",
                    style = MaterialTheme.typography.labelSmall,
                    color =
                      if (passed) semantics.onSuccessContainer
                      else MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                  )
                }
              }
            }
          }

          PlacementStep.QUIZ -> {
            val currentQ = questions.getOrNull(qIndex)
            if (currentQ != null) {
              LinearProgressIndicator(
                progress = { (qIndex + 1) / questions.size.coerceAtLeast(1).toFloat() },
                modifier = Modifier.fillMaxWidth().height(6.dp),
              )
              Text(
                text = currentQ.topic,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
              )
              ScientificText(
                text = currentQ.question,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
              )
              val chosen = chosenAnswers[qIndex]
              currentQ.options.forEachIndexed { idx, optText ->
                val isSelected = chosen == idx
                Row(
                  modifier =
                    Modifier.fillMaxWidth()
                      .minimumInteractiveComponentSize()
                      .clip(RoundedCornerShape(10.dp))
                      .background(
                        if (isSelected) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.background
                      )
                      .border(
                        1.2.dp,
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.outlineVariant,
                        RoundedCornerShape(10.dp),
                      )
                      .clickable { chosenAnswers[qIndex] = idx }
                      .padding(10.dp)
                      .testTag("placement_option_$idx"),
                  horizontalArrangement = Arrangement.spacedBy(8.dp),
                  verticalAlignment = Alignment.CenterVertically,
                ) {
                  Text(
                    text = "${'A' + idx}.",
                    style = MaterialTheme.typography.labelLarge,
                    color =
                      if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                      else MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                  )
                  ScientificText(
                    text = optText,
                    style = MaterialTheme.typography.bodySmall,
                    color =
                      if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                      else MaterialTheme.colorScheme.onSurface,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                  )
                }
              }
            }
          }

          PlacementStep.RESULT -> {
            val total = questions.size.coerceAtLeast(1)
            val correct = questions.indices.count { chosenAnswers[it] == questions[it].correctIndex }
            val pct = ((correct * 100) / total).coerceIn(0, 100)
            val passed = pct >= 75
            Surface(
              color = if (passed) semantics.successContainer else MaterialTheme.colorScheme.errorContainer,
              shape = RoundedCornerShape(14.dp),
              modifier = Modifier.fillMaxWidth(),
            ) {
              Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
              ) {
                Text(
                  text = if (passed) "Placement Passed — $pct% ($correct/$total)" else "Score: $pct% ($correct/$total)",
                  style = MaterialTheme.typography.titleMedium,
                  color = if (passed) semantics.onSuccessContainer else MaterialTheme.colorScheme.onErrorContainer,
                  fontWeight = FontWeight.Bold,
                )
                Text(
                  text =
                    if (passed) {
                      "Foundational lessons unlocked! Start directly at intermediate & SS3 revision modules."
                    } else {
                      "75% is required to skip foundational modules. Begin from Lesson 1 to strengthen prerequisites cleanly."
                    },
                  style = MaterialTheme.typography.bodySmall,
                  color = if (passed) semantics.onSuccessContainer else MaterialTheme.colorScheme.onErrorContainer,
                )
              }
            }
          }
        }
      }
    },
    confirmButton = {
      when (step) {
        PlacementStep.SELECT_SUBJECT -> {
          Button(
            onClick = {
              qIndex = 0
              chosenAnswers.clear()
              step = PlacementStep.QUIZ
            },
            enabled = questions.isNotEmpty(),
            modifier = Modifier.minimumInteractiveComponentSize().testTag("start_placement_quiz_button"),
          ) {
            Text("Start 8-Item Check", fontWeight = FontWeight.Bold)
          }
        }

        PlacementStep.QUIZ -> {
          val isLast = qIndex >= questions.lastIndex
          Button(
            onClick = {
              if (!isLast) {
                qIndex++
              } else {
                val total = questions.size.coerceAtLeast(1)
                val correct = questions.indices.count { chosenAnswers[it] == questions[it].correctIndex }
                val pct = ((correct * 100) / total).coerceIn(0, 100)
                onCompletePlacement(selectedSubjectId, pct)
                step = PlacementStep.RESULT
              }
            },
            enabled = chosenAnswers.containsKey(qIndex),
            modifier = Modifier.minimumInteractiveComponentSize().testTag("placement_next_submit_button"),
          ) {
            Text(if (isLast) "Grade Placement" else "Next →", fontWeight = FontWeight.Bold)
          }
        }

        PlacementStep.RESULT -> {
          Button(
            onClick = onDismiss,
            modifier = Modifier.minimumInteractiveComponentSize(),
          ) {
            Text("Done", fontWeight = FontWeight.Bold)
          }
        }
      }
    },
    dismissButton = {
      if (step == PlacementStep.QUIZ && qIndex > 0) {
        OutlinedButton(onClick = { qIndex-- }) {
          Text("← Back")
        }
      } else if (step != PlacementStep.RESULT) {
        TextButton(onClick = onDismiss) {
          Text("Cancel")
        }
      }
    },
  )
}
