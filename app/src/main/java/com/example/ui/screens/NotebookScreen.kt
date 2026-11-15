package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.StudyNote
import com.example.ui.theme.JetBrainsMonoFontFamily

@Composable
fun NotebookScreen(
  notes: List<StudyNote>,
  onCreateNote: (lessonId: String, subjectId: String, title: String, content: String, isBookmarked: Boolean) -> Unit,
  onToggleBookmark: (StudyNote) -> Unit,
  onDeleteNote: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  var onlyBookmarked by remember { mutableStateOf(false) }
  var showCreateDialog by remember { mutableStateOf(false) }
  val displayed = remember(notes, onlyBookmarked) {
    if (onlyBookmarked) notes.filter { it.isBookmarked } else notes
  }

  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    Column(modifier = Modifier.widthIn(max = 880.dp).fillMaxSize()) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Text("Formula & Revision Notebook", style = MaterialTheme.typography.headlineMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          FilterChip(
            selected = !onlyBookmarked,
            onClick = { onlyBookmarked = false },
            label = { Text("All Notes (${notes.size})") },
          )
          FilterChip(
            selected = onlyBookmarked,
            onClick = { onlyBookmarked = true },
            label = { Text("Pinned (${notes.count { it.isBookmarked }})") },
          )
        }
      }

      if (displayed.isEmpty()) {
        Box(
          modifier = Modifier.weight(1f).fillMaxWidth().padding(24.dp),
          contentAlignment = Alignment.Center,
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Icon(
              Icons.Filled.EditNote,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.padding(bottom = 4.dp),
            )
            Text("No revision cards saved yet", style = MaterialTheme.typography.titleMedium)
            Text(
              text = "Tap + to save key equations, mnemonics, or WAEC definitions synced to your cloud account.",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
        }
      } else {
        LazyColumn(
          modifier = Modifier.weight(1f).fillMaxWidth(),
          contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          items(displayed, key = { it.id }) { note ->
            Column(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(18.dp))
                  .background(MaterialTheme.colorScheme.surface)
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
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
                ) {
                  Text(
                    text = note.subjectId.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
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
              Text(text = note.title, style = MaterialTheme.typography.titleMedium)
              Surface(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
              ) {
                Text(
                  text = note.content,
                  style = MaterialTheme.typography.bodyMedium.copy(fontFamily = JetBrainsMonoFontFamily),
                  modifier = Modifier.padding(12.dp),
                )
              }
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
          Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            CurriculumCatalog.tracks.take(3).forEach { t ->
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
            label = { Text("Formula or Key Explanation") },
            minLines = 3,
            modifier = Modifier.fillMaxWidth(),
          )
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
