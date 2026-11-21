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
import androidx.compose.material.icons.filled.Save
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
import com.example.data.model.Paper2AttemptEntity
import com.example.ui.theme.semanticColors

@Composable
fun Paper2DrillView(
  selectedSubjectId: String,
  paper2Attempts: List<Paper2AttemptEntity> = emptyList(),
  onSelectSubject: (String) -> Unit,
  onSavePaper2Attempt: (
    prompt: Paper2Prompt,
    studentWorking: String,
    checkedIndices: List<Int>,
    earnedMarks: Int,
  ) -> Unit = { _, _, _, _ -> },
  onSaveToNotebook: (subjectId: String, title: String, content: String) -> Unit,
  modifier: Modifier = Modifier,
) {
  val prompts = remember(selectedSubjectId) {
    Paper2AndPlacementBank.paper2PromptsFor(selectedSubjectId)
  }
  val attemptsByPromptId = remember(paper2Attempts) {
    paper2Attempts.associateBy { it.promptId }
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
        text = "Show full working in the scratchpad, reveal the M1/A1/B1 examiner rubric with offline keyword diagnostics, and save your theory marks.",
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
      val savedAttempt = attemptsByPromptId[prompt.id]
      Paper2PromptCard(
        prompt = prompt,
        savedAttempt = savedAttempt,
        onSaveWorkingAndScore = { working, indices, marks ->
          onSavePaper2Attempt(prompt, working, indices, marks)
        },
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
  savedAttempt: Paper2AttemptEntity?,
  onSaveWorkingAndScore: (String, List<Int>, Int) -> Unit,
  onSaveToNotebook: () -> Unit,
) {
  var studentWorking by remember(prompt.id, savedAttempt?.updatedAtMillis) {
    mutableStateOf(savedAttempt?.studentWorking.orEmpty())
  }
  var schemeRevealed by remember(prompt.id) { mutableStateOf(false) }
  val checkedSteps = remember(prompt.id, savedAttempt?.updatedAtMillis) {
    mutableStateMapOf<Int, Boolean>().apply {
      savedAttempt?.checkedStepIndicesJson
        ?.split(Regex("""[,;\[\]\s]+"""))
        ?.mapNotNull { it.trim().toIntOrNull() }
        ?.forEach { idx -> put(idx, true) }
    }
  }
  val semantics = MaterialTheme.semanticColors
  val earnedMarks =
    prompt.rubricSteps.mapIndexed { idx, step ->
      if (checkedSteps[idx] == true) step.marks else 0
    }.sum()
  val checkedIndices = checkedSteps.filterValues { it }.keys.sorted()

  val keywordDiagnostic = remember(prompt.id, studentWorking, schemeRevealed) {
    if (!schemeRevealed) null
    else evaluatePaper2WorkingKeywords(studentWorking, prompt)
  }

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
      Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
        if (savedAttempt != null) {
          Surface(color = semantics.successContainer, shape = RoundedCornerShape(99.dp)) {
            Text(
              text = "Saved ${savedAttempt.earnedMarks}/${savedAttempt.totalMarks}",
              style = MaterialTheme.typography.labelSmall,
              color = semantics.onSuccessContainer,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            )
          }
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
      modifier = Modifier.fillMaxWidth().testTag("paper2_input_${prompt.id}"),
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

          if (keywordDiagnostic != null) {
            Surface(
              color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.fillMaxWidth(),
            ) {
              Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                  text = "Offline Working Check: ${keywordDiagnostic.matchedCount}/${keywordDiagnostic.totalCount} rubric key terms found",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSecondaryContainer,
                  fontWeight = FontWeight.Bold,
                )
                if (keywordDiagnostic.matchedTerms.isNotEmpty()) {
                  Text(
                    text = "Detected: ${keywordDiagnostic.matchedTerms.joinToString(", ")}",
                    style = MaterialTheme.typography.bodySmall,
                    color = semantics.success,
                  )
                }
                if (keywordDiagnostic.missingTerms.isNotEmpty()) {
                  Text(
                    text = "Verify you included: ${keywordDiagnostic.missingTerms.take(5).joinToString(", ")}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                  )
                }
              }
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
                  .padding(10.dp)
                  .testTag("paper2_rubric_${prompt.id}_$idx"),
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

          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
              onClick = { onSaveWorkingAndScore(studentWorking, checkedIndices, earnedMarks) },
              modifier = Modifier.weight(1f).height(48.dp).testTag("paper2_save_score_${prompt.id}"),
              shape = RoundedCornerShape(10.dp),
            ) {
              Icon(Icons.Filled.Save, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(Modifier.width(6.dp))
              Text("Save Score", maxLines = 1, fontWeight = FontWeight.Bold)
            }
            OutlinedButton(
              onClick = onSaveToNotebook,
              modifier = Modifier.weight(1f).height(48.dp),
              shape = RoundedCornerShape(10.dp),
            ) {
              Icon(Icons.Filled.BookmarkAdd, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(Modifier.width(6.dp))
              Text("Save Note", maxLines = 1)
            }
          }
        }
      }
    }
  }
}

private data class Paper2KeywordDiagnostic(
  val matchedTerms: List<String>,
  val missingTerms: List<String>,
) {
  val totalCount: Int get() = matchedTerms.size + missingTerms.size
  val matchedCount: Int get() = matchedTerms.size
}

private fun evaluatePaper2WorkingKeywords(working: String, prompt: Paper2Prompt): Paper2KeywordDiagnostic {
  val stopWords = setOf(
    "with", "from", "that", "this", "then", "when", "into", "using", "value", "step", "mark",
    "correct", "state", "find", "calculate", "show", "give", "formula", "answer", "unit", "units",
  )
  val rawSource = (prompt.rubricSteps.map { it.description } + prompt.modelSolution).joinToString(" ")
  val keywords =
    Regex("""[A-Za-z0-9²³ΩΔμ%]+""")
      .findAll(rawSource)
      .map { it.value }
      .filter { token -> token.length >= 4 && token.lowercase() !in stopWords }
      .distinctBy { it.lowercase() }
      .take(8)
      .toList()
  val normalizedWorking = working.lowercase()
  val matched = keywords.filter { kw -> normalizedWorking.contains(kw.lowercase()) }
  val missing = keywords.filterNot { kw -> normalizedWorking.contains(kw.lowercase()) }
  return Paper2KeywordDiagnostic(matchedTerms = matched, missingTerms = missing)
}
