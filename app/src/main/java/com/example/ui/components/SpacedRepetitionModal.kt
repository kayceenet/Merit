package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.SpacedReviewCardEntity
import com.example.ui.theme.semanticColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpacedRepetitionModal(
  dueCards: List<SpacedReviewCardEntity>,
  allCardsCount: Int,
  batchSize: Int,
  onRateCard: (cardId: String, quality: Int) -> Unit,
  onDismiss: () -> Unit,
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val semantics = MaterialTheme.semanticColors
  var reviewedInSession by remember { mutableIntStateOf(0) }
  var answerRevealed by remember { mutableStateOf(false) }

  val currentCard =
    if (reviewedInSession < batchSize) dueCards.firstOrNull() else null

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
  ) {
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .navigationBarsPadding()
          .padding(horizontal = 16.dp, vertical = 10.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Icon(
            Icons.Filled.Psychology,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
          )
          Text(
            text = "SM-2 Active Recall Deck",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
          )
        }
        Surface(
          color = MaterialTheme.colorScheme.primaryContainer,
          shape = RoundedCornerShape(99.dp),
        ) {
          Text(
            text =
              if (currentCard != null) "${dueCards.size} due • Done: $reviewedInSession"
              else "$allCardsCount saved",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
          )
        }
      }

      if (currentCard == null) {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(16.dp))
              .background(semantics.successContainer)
              .border(1.5.dp, semantics.success, RoundedCornerShape(16.dp))
              .padding(20.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = null,
            tint = semantics.success,
            modifier = Modifier.size(36.dp),
          )
          Text(
            text =
              if (reviewedInSession > 0) "Recall Session Complete! (+${reviewedInSession * 5} XP)"
              else if (allCardsCount > 0) "You are completely caught up for today!"
              else "No Recall Cards Yet",
            style = MaterialTheme.typography.titleMedium,
            color = semantics.onSuccessContainer,
            fontWeight = FontWeight.Bold,
          )
          Text(
            text =
              if (allCardsCount > 0) {
                "Your SM-2 memory intervals (Days 1, 3, 7, 14, 30) will bring cards back right before forgetting occurs."
              } else {
                "Master any lesson quiz or miss a CBT mock question to automatically enqueue targeted active recall flashcards."
              },
            style = MaterialTheme.typography.bodySmall,
            color = semantics.onSuccessContainer,
          )
          Button(
            onClick = onDismiss,
            modifier = Modifier.fillMaxWidth().padding(top = 6.dp).height(48.dp),
            shape = RoundedCornerShape(12.dp),
          ) {
            Text("Back to Study", fontWeight = FontWeight.Bold)
          }
        }
      } else {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(16.dp))
              .background(MaterialTheme.colorScheme.background)
              .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
              .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
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
                text = currentCard.subjectId.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
            Text(
              text = "Interval: ${currentCard.intervalDays}d • Reps: ${currentCard.repetitions}",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }

          ScientificText(
            text = currentCard.prompt,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
          )

          if (!answerRevealed) {
            Button(
              onClick = { answerRevealed = true },
              modifier = Modifier.fillMaxWidth().height(48.dp).testTag("spaced_reveal_button"),
              shape = RoundedCornerShape(12.dp),
            ) {
              Icon(Icons.Filled.Visibility, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(Modifier.padding(horizontal = 4.dp))
              Text("Reveal Answer & Explanation", fontWeight = FontWeight.Bold)
            }
          } else {
            Surface(
              color = semantics.successContainer,
              shape = RoundedCornerShape(12.dp),
              modifier =
                Modifier.fillMaxWidth()
                  .border(1.dp, semantics.success, RoundedCornerShape(12.dp)),
            ) {
              Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
              ) {
                Text(
                  text = "ANSWER",
                  style = MaterialTheme.typography.labelSmall,
                  color = semantics.onSuccessContainer,
                  fontWeight = FontWeight.Bold,
                )
                ScientificText(
                  text = currentCard.answer,
                  style = MaterialTheme.typography.titleSmall,
                  color = semantics.onSuccessContainer,
                  fontWeight = FontWeight.Bold,
                )
                if (currentCard.explanation.isNotBlank()) {
                  ScientificText(
                    text = currentCard.explanation,
                    style = MaterialTheme.typography.bodySmall,
                    color = semantics.onSuccessContainer,
                  )
                }
              }
            }

            Text(
              text = "Rate how easily you recalled this:",
              style = MaterialTheme.typography.labelMedium,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              listOf(
                Triple(0, "Forgot\n(1d)", MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onErrorContainer),
                Triple(1, "Hard\n(2d)", MaterialTheme.colorScheme.tertiaryContainer to MaterialTheme.colorScheme.onTertiaryContainer),
                Triple(2, "Good\n(3d+)", MaterialTheme.colorScheme.primaryContainer to MaterialTheme.colorScheme.onPrimaryContainer),
                Triple(3, "Easy\n(7d+)", semantics.successContainer to semantics.onSuccessContainer),
              ).forEach { (quality, label, colors) ->
                OutlinedButton(
                  onClick = {
                    answerRevealed = false
                    reviewedInSession++
                    onRateCard(currentCard.id, quality)
                  },
                  modifier = Modifier.weight(1f).height(56.dp).testTag("spaced_rate_$quality"),
                  shape = RoundedCornerShape(12.dp),
                  colors = ButtonDefaults.outlinedButtonColors(containerColor = colors.first, contentColor = colors.second),
                ) {
                  Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                  )
                }
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
    }
  }
}
