package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.data.curriculum.Paper2Prompt
import com.example.ui.theme.semanticColors

@Composable
fun Paper2DrillView(
  selectedSubjectId: String,
  onSelectSubject: (String) -> Unit,
  onSaveToNotebook: (subjectId: String, title: String, content: String) -> Unit,
  modifier: Modifier = Modifier,
) {
  val prompts = remember(selectedSubjectId) {
    Paper2AndPlacementBank.paper2PromptsFor(selectedSubjectId)
  }

  Column(
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(12.dp),
  ) {
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
          .padding(14.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Text(
        text = "WAEC WASSCE & NECO Paper 2 Theory & Alternative-to-Practical",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
      )
      Text(
        text = "Show full working first in the scratchpad, then reveal the M1/A1/B1 examiner marking scheme to grade every step.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        CurriculumCatalog.tracks.forEach { track ->
          val selected = track.id == selectedSubjectId
          FilterChip(
            selected = selected,
            onClick = { onSelectSubject(track.id) },
            label = { Text(track.shortName, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
            colors =
              FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
              ),
          )
        }
      }
    }

    prompts.forEach { prompt ->
      Paper2PromptCard(
        prompt = prompt,
        onSaveToNotebook = {
          onSaveToNotebook(
            prompt.subjectId,
            "Paper 2: ${prompt.topic}",
            "${prompt.prompt}\n\n${prompt.modelSolution}",
          )
        },
      )
    }
  }
}

@Composable
private fun Paper2PromptCard(
  prompt: Paper2Prompt,
  onSaveToNotebook: () -> Unit,
) {
  var studentWorking by remember(prompt.id) { mutableStateOf("") }
  var schemeRevealed by remember(prompt.id) { mutableStateOf(false) }
  val checkedSteps = remember(prompt.id) { mutableStateMapOf<Int, Boolean>() }
  val semantics = MaterialTheme.semanticColors
  val earnedMarks =
    prompt.rubricSteps.mapIndexed { idx, step ->
      if (checkedSteps[idx] == true) step.marks else 0
    }.sum()

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
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(99.dp)) {
        Text(
          text = prompt.examBoard,
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSecondaryContainer,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
        )
      }
      Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(99.dp)) {
        Text(
          text = "${prompt.totalMarks} MARKS",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
        )
      }
    }

    Text(
      text = prompt.topic,
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.Bold,
    )

    ScientificText(
      text = prompt.prompt,
      style = MaterialTheme.typography.bodyLarge,
      color = MaterialTheme.colorScheme.onSurface,
    )

    OutlinedTextField(
      value = studentWorking,
      onValueChange = { studentWorking = it },
      label = { Text("Your Theory Working / Essay Outline Scratchpad") },
      placeholder = { Text("Write formulas, substitutions, units, or key points before checking...") },
      minLines = 3,
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(12.dp),
    )

    Button(
      onClick = { schemeRevealed = !schemeRevealed },
      modifier = Modifier.fillMaxWidth().height(48.dp).testTag("paper2_reveal_${prompt.id}"),
      shape = RoundedCornerShape(12.dp),
    ) {
      Icon(Icons.Filled.Visibility, contentDescription = null, modifier = Modifier.size(18.dp))
      Spacer(Modifier.width(6.dp))
      Text(
        text = if (schemeRevealed) "Hide Examiner Marking Scheme" else "Reveal Marking Scheme & Model Solution",
        fontWeight = FontWeight.Bold,
      )
    }

    if (schemeRevealed) {
      Surface(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(12.dp),
        modifier =
          Modifier.fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp)),
      ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text(
              text = "EXAMINER M1 / A1 / B1 RUBRIC",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
            )
            Surface(
              color =
                if (earnedMarks == prompt.totalMarks) semantics.successContainer
                else MaterialTheme.colorScheme.primaryContainer,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = "Self-Score: $earnedMarks / ${prompt.totalMarks}",
                style = MaterialTheme.typography.labelSmall,
                color =
                  if (earnedMarks == prompt.totalMarks) semantics.onSuccessContainer
                  else MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
              )
            }
          }

          prompt.rubricSteps.forEachIndexed { idx, markStep ->
            val checked = checkedSteps[idx] == true
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(10.dp))
                  .background(
                    if (checked) semantics.successContainer
                    else MaterialTheme.colorScheme.surface
                  )
                  .border(
                    1.dp,
                    if (checked) semantics.success else MaterialTheme.colorScheme.outlineVariant,
                    RoundedCornerShape(10.dp),
                  )
                  .clickable { checkedSteps[idx] = !checked }
                  .padding(10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Icon(
                imageVector = if (checked) Icons.Filled.CheckBox else Icons.Filled.CheckBoxOutlineBlank,
                contentDescription = "Tick earned mark",
                tint = if (checked) semantics.success else MaterialTheme.colorScheme.onSurfaceVariant,
              )
              Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(6.dp),
              ) {
                Text(
                  text = "${markStep.code} (${markStep.marks})",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSecondaryContainer,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                )
              }
              ScientificText(
                text = markStep.description,
                style = MaterialTheme.typography.bodySmall,
                color = if (checked) semantics.onSuccessContainer else MaterialTheme.colorScheme.onSurface,
              )
            }
          }

          Surface(
            color = semantics.successContainer,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "COMPLETE MODEL SOLUTION",
                style = MaterialTheme.typography.labelSmall,
                color = semantics.onSuccessContainer,
                fontWeight = FontWeight.Bold,
              )
              ScientificText(
                text = prompt.modelSolution,
                style = MaterialTheme.typography.bodySmall,
                color = semantics.onSuccessContainer,
              )
            }
          }

          OutlinedButton(
            onClick = onSaveToNotebook,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(10.dp),
          ) {
            Icon(Icons.Filled.BookmarkAdd, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text("Save Model Solution to Notebook")
          }
        }
      }
    }
  }
}
