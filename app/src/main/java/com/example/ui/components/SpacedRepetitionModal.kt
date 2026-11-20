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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
  val totalBatch = (reviewedInSession + dueCards.size).coerceAtLeast(1).coerceAtMost(batchSize)
  val progressRatio =
    if (currentCard == null) 1f
    else ((reviewedInSession + 1).toFloat() / totalBatch.toFloat()).coerceIn(0.08f, 1f)

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.onBackground,
  ) {
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .navigationBarsPadding()
          .padding(horizontal = 18.dp, vertical = 10.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp),
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
            text = "Active Recall Deck",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
          )
        }
        Text(
          text =
            if (currentCard != null) "${reviewedInSession + 1} / $totalBatch"
            else "$allCardsCount saved",
          style = MaterialTheme.typography.labelLarge.copy(fontFeatureSettings = "tnum"),
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontWeight = FontWeight.SemiBold,
        )
      }

      LinearProgressIndicator(
        progress = { progressRatio },
        modifier = Modifier.fillMaxWidth().height(5.dp).clip(CircleShape),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.outlineVariant,
      )

      if (currentCard == null) {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .shadow(4.dp, RoundedCornerShape(24.dp))
              .clip(RoundedCornerShape(24.dp))
              .background(MaterialTheme.colorScheme.surface)
              .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(24.dp))
              .padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = null,
            tint = semantics.success,
            modifier = Modifier.size(44.dp),
          )
          Text(
            text =
              if (reviewedInSession > 0) "Recall Round Complete! (+${reviewedInSession * 5} XP)"
              else if (allCardsCount > 0) "You are completely caught up for today!"
              else "No Recall Cards Yet",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
          )
          Text(
            text =
              if (allCardsCount > 0) {
                "Your SM-2 schedule (Days 1, 3, 7, 14, 30) will resurface cards right before forgetting occurs."
              } else {
                "Master any lesson quiz or miss a CBT mock question to automatically enqueue flashcards here."
              },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
          )
          Button(
            onClick = onDismiss,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp).height(50.dp),
            shape = RoundedCornerShape(16.dp),
          ) {
            Text("Back to Study", fontWeight = FontWeight.Bold)
          }
        }
      } else {
        // Elevated pure white flashcard matching Top-Center mockup
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.14f),
              )
              .clip(RoundedCornerShape(24.dp))
              .background(MaterialTheme.colorScheme.surface)
              .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(24.dp))
              .padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Surface(
              color = MaterialTheme.colorScheme.primaryContainer,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = currentCard.subjectId.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
            Text(
              text = "Interval ${currentCard.intervalDays}d • Reps ${currentCard.repetitions}",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }

          ScientificText(
            text = currentCard.prompt,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
          )

          if (!answerRevealed) {
            Spacer(modifier = Modifier.height(6.dp))
            Button(
              onClick = { answerRevealed = true },
              modifier = Modifier.fillMaxWidth().height(50.dp).testTag("spaced_reveal_button"),
              shape = RoundedCornerShape(16.dp),
            ) {
              Icon(Icons.Filled.Visibility, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(Modifier.padding(horizontal = 4.dp))
              Text("Reveal Answer & Application", fontWeight = FontWeight.Bold)
            }
          } else {
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "Verified Answer",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
              )
              ScientificText(
                text = currentCard.answer,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
              )
              if (currentCard.explanation.isNotBlank()) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = "Example / Exam Application",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  fontWeight = FontWeight.SemiBold,
                )
                ScientificText(
                  text = currentCard.explanation,
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                )
              }
            }
          }
        }

        if (answerRevealed) {
          // Coral / Deep Indigo / Cornflower pill buttons matching Top-Center phone in mockup
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(
              Triple(0, "Again\n1d", semantics.coralAccent.copy(alpha = 0.85f) to Color.White),
              Triple(1, "Hard\n2d", semantics.coralAccent to Color.White),
              Triple(2, "Good\n3d+", semantics.indigoAccent to Color.White),
              Triple(3, "Excellent\n7d+", MaterialTheme.colorScheme.primary to Color.White),
            ).forEach { (quality, label, colors) ->
              Button(
                onClick = {
                  answerRevealed = false
                  reviewedInSession++
                  onRateCard(currentCard.id, quality)
                },
                modifier = Modifier.weight(1f).height(54.dp).testTag("spaced_rate_$quality"),
                shape = RoundedCornerShape(14.dp),
                colors =
                  ButtonDefaults.buttonColors(
                    containerColor = colors.first,
                    contentColor = colors.second,
                  ),
              ) {
                Text(
                  text = label,
                  style = MaterialTheme.typography.labelSmall,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Center,
                )
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
    }
  }
}
