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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.data.model.StudyNote
import com.example.ui.components.EquationDisplayCard
import com.example.ui.components.TopRightMenuButton

@Composable
fun NotebookScreen(
  notes: List<StudyNote>,
  dueReviewsCount: Int = 0,
  onOpenSpacedReview: () -> Unit = {},
  onOpenMenu: () -> Unit = {},
  onCreateNote: (lessonId: String, subjectId: String, title: String, content: String, isBookmarked: Boolean) -> Unit,
  onToggleBookmark: (StudyNote) -> Unit,
  onDeleteNote: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  var onlyBookmarked by remember { mutableStateOf(false) }
  var searchQuery by remember { mutableStateOf("") }
  var showCreateDialog by remember { mutableStateOf(false) }

  val displayed = remember(notes, onlyBookmarked, searchQuery) {
    notes
      .filter { !onlyBookmarked || it.isBookmarked }
      .filter {
        searchQuery.isBlank() ||
          it.title.contains(searchQuery, ignoreCase = true) ||
          it.content.contains(searchQuery, ignoreCase = true) ||
          it.subjectId.contains(searchQuery, ignoreCase = true)
      }
  }

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    Column(modifier = Modifier.widthIn(max = 880.dp).fillMaxSize()) {
      Column(
        modifier =
          Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = "Formula & Revision Notebook",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f),
          )
          TopRightMenuButton(badgeCount = dueReviewsCount, onClick = onOpenMenu)
        }
        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = { Text("Search formulas, topics, or subjects...") },
          leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
          singleLine = true,
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(14.dp),
        )
        Row(
          modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          FilterChip(
            selected = !onlyBookmarked,
            onClick = { onlyBookmarked = false },
            label = { Text("All Notes (${notes.size})", fontWeight = FontWeight.SemiBold) },
            colors =
              FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
              ),
          )
          FilterChip(
            selected = onlyBookmarked,
            onClick = { onlyBookmarked = true },
            label = { Text("Pinned (${notes.count { it.isBookmarked }})", fontWeight = FontWeight.SemiBold) },
            colors =
              FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
              ),
          )
          FilterChip(
            selected = dueReviewsCount > 0,
            onClick = onOpenSpacedReview,
            leadingIcon = {
              Icon(Icons.Filled.Psychology, contentDescription = null, modifier = Modifier.size(16.dp))
            },
            label = {
              Text(
                text = if (dueReviewsCount > 0) "Recall Deck ($dueReviewsCount)" else "Recall Deck",
                fontWeight = FontWeight.SemiBold,
              )
            },
            colors =
              FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                selectedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
              ),
            modifier = Modifier.testTag("notebook_spaced_review_chip"),
          )
        }
      }

      if (displayed.isEmpty()) {
        Box(
          modifier = Modifier.weight(1f).fillMaxWidth().padding(24.dp),
          contentAlignment = Alignment.Center,
        ) {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Icon(
              Icons.Filled.EditNote,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
            )
            Text(
              text = if (notes.isEmpty()) "No revision cards saved yet" else "No matching notes",
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
              text = "Tap + to save key equations, mnemonics, or WAEC definitions locally on your device.",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
        }
      } else {
        LazyColumn(
          modifier = Modifier.weight(1f).fillMaxWidth(),
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          items(displayed, key = { it.id }) { note ->
            Column(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(18.dp))
                  .background(MaterialTheme.colorScheme.surface)
                  .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
                  .padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Surface(
                  color = MaterialTheme.colorScheme.secondaryContainer,
                  shape = RoundedCornerShape(99.dp),
                  modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(99.dp)),
                ) {
                  Text(
                    text = note.subjectId.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                  )
                }
                Row {
                  IconButton(onClick = { onToggleBookmark(note) }) {
                    Icon(
                      imageVector =
                        if (note.isBookmarked) Icons.Filled.Bookmark
                        else Icons.Filled.BookmarkBorder,
                      contentDescription = "Pin note",
                      tint =
                        if (note.isBookmarked) MaterialTheme.colorScheme.tertiary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                  }
                  IconButton(onClick = { onDeleteNote(note.id) }) {
                    Icon(
                      imageVector = Icons.Filled.DeleteOutline,
                      contentDescription = "Delete note",
                      tint = MaterialTheme.colorScheme.error,
                    )
                  }
                }
              }
              Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
              )
              EquationDisplayCard(formula = note.content)
            }
          }
          item { Spacer(modifier = Modifier.height(72.dp)) }
        }
      }
    }

    FloatingActionButton(
      onClick = { showCreateDialog = true },
      modifier =
        Modifier.align(Alignment.BottomEnd)
          .padding(20.dp)
          .testTag("add_note_fab"),
      containerColor = MaterialTheme.colorScheme.primary,
      contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
      Icon(Icons.Filled.Add, contentDescription = "Add revision note")
    }
  }

  if (showCreateDialog) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var subjectId by remember { mutableStateOf("physics") }

    AlertDialog(
      onDismissRequest = { showCreateDialog = false },
      title = { Text("New Formula / Note Card") },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            CurriculumCatalog.tracks.forEach { t ->
              FilterChip(
                selected = subjectId == t.id,
                onClick = { subjectId = t.id },
                label = { Text(t.shortName) },
              )
            }
          }
          OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title (e.g. Snell's Law)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
          )
          OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("LaTeX, AsciiMath, Chem or Text Formula") },
            placeholder = { Text("e.g. \\frac{u^2 \\sin^2\\theta}{2g} or 6.02e23") },
            minLines = 3,
            modifier = Modifier.fillMaxWidth(),
          )
          Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            listOf(
              "10^{n}" to "10^{-3}",
              "frac" to "\\frac{a}{b}",
              "sqrt" to "\\sqrt{x}",
              "H₂O" to "H_2O",
              "θ" to "\\theta",
              "Δ" to "\\Delta",
              "→" to "->",
              "⇌" to "<=>",
            ).forEach { (chipLabel, snippet) ->
              Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(8.dp),
                modifier =
                  Modifier.clip(RoundedCornerShape(8.dp))
                    .clickable { content = if (content.isBlank()) snippet else "$content $snippet" },
              ) {
                Text(
                  text = chipLabel,
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                )
              }
            }
          }
          if (content.isNotBlank()) {
            EquationDisplayCard(
              formula = content,
              badgeLabel = "LIVE PREVIEW",
            )
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            if (title.isNotBlank() && content.isNotBlank()) {
              onCreateNote("custom_note", subjectId, title, content, true)
              showCreateDialog = false
            }
          }
        ) {
          Text("Save")
        }
      },
      dismissButton = {
        TextButton(onClick = { showCreateDialog = false }) { Text("Cancel") }
      },
    )
  }
}
