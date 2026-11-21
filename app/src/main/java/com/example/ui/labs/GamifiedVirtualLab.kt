package com.example.ui.labs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText
import com.example.ui.theme.semanticColors
import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GamifiedVirtualLab(
  lessonId: String,
  labType: PracticalLabType,
  modifier: Modifier = Modifier,
  onRewardEarned: (xpDelta: Int, missionCompleted: Boolean) -> Unit = { _, _ -> },
) {
  val rounds = remember(lessonId, labType) { GamifiedLabChallenges.roundsFor(labType) }
  var roundIdx by remember(lessonId, labType) { mutableIntStateOf(0) }
  var xp by remember(lessonId, labType) { mutableIntStateOf(0) }
  var streak by remember(lessonId, labType) { mutableIntStateOf(0) }
  var completedRounds by remember(lessonId, labType) { mutableIntStateOf(0) }
  var perfectHits by remember(lessonId, labType) { mutableIntStateOf(0) }
  var showHint by remember(lessonId, labType, roundIdx) { mutableStateOf(false) }

  val currentRound = rounds.getOrNull(roundIdx)
  val semantics = MaterialTheme.semanticColors
  val rankTitle =
    when {
      xp >= 260 -> "Distinction Laureate"
      xp >= 140 -> "Lab Specialist"
      xp >= 50 -> "Field Technician"
      else -> "Lab Apprentice"
    }

  Column(
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(12.dp),
  ) {
    // 1. Mission Telemetry HUD
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f))
          .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.35f), RoundedCornerShape(16.dp))
          .padding(14.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          Icon(
            imageVector = Icons.Filled.EmojiEvents,
            contentDescription = "Gamified lab badge",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp),
          )
          Column {
            Text(
              text = "GAMIFIED SIMULATION MISSION",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
            )
            Text(
              text = rankTitle,
              style = MaterialTheme.typography.titleSmall,
              color = MaterialTheme.colorScheme.onSurface,
              fontWeight = FontWeight.ExtraBold,
            )
          }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
          Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(99.dp),
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
              Icon(
                imageVector = Icons.Filled.Whatshot,
                contentDescription = "Streak multiplier",
                tint = if (streak > 0) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(14.dp),
              )
              Text(
                text = "${streak}x Streak",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
              )
            }
          }
          Surface(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(99.dp),
          ) {
            Text(
              text = "$xp XP",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onPrimary,
              fontWeight = FontWeight.ExtraBold,
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            )
          }
        }
      }

      LinearProgressIndicator(
        progress = { (completedRounds.toFloat() / rounds.size.coerceAtLeast(1)).coerceIn(0f, 1f) },
        modifier = Modifier.fillMaxWidth().height(7.dp).clip(RoundedCornerShape(99.dp)),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
      )
      Text(
        text = "Challenge ${(roundIdx + 1).coerceAtMost(rounds.size)} of ${rounds.size} • Precision Hits: $perfectHits/${rounds.size}",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }

    if (completedRounds >= rounds.size || currentRound == null) {
      // Mission Complete Summary Card
      Column(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(semantics.successContainer)
            .border(1.dp, semantics.success, RoundedCornerShape(16.dp))
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Icon(
          imageVector = Icons.Filled.EmojiEvents,
          contentDescription = null,
          tint = semantics.onSuccessContainer,
          modifier = Modifier.size(36.dp),
        )
        Text(
          text = "Mission Complete • $rankTitle",
          style = MaterialTheme.typography.titleMedium,
          color = semantics.onSuccessContainer,
          fontWeight = FontWeight.ExtraBold,
        )
        Text(
          text = "Total Earned: $xp XP across ${rounds.size} precision trials ($perfectHits/${rounds.size} target locks).",
          style = MaterialTheme.typography.bodyMedium,
          color = semantics.onSuccessContainer,
        )
        OutlinedButton(
          onClick = {
            roundIdx = 0
            xp = 0
            streak = 0
            completedRounds = 0
            perfectHits = 0
            showHint = false
          },
          modifier = Modifier.heightIn(min = 48.dp).testTag("gamified_lab_restart"),
        ) {
          Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
          Text("Replay Simulation Mission", modifier = Modifier.padding(start = 6.dp))
        }
      }
      return
    }

    // 2. Active Round Workspace
    var trialSubmitted by remember(lessonId, roundIdx) { mutableStateOf(false) }
    var lastAccuracyPct by remember(lessonId, roundIdx) { mutableIntStateOf(0) }
    var lastHitSuccess by remember(lessonId, roundIdx) { mutableStateOf(false) }
    var earnedThisRound by remember(lessonId, roundIdx) { mutableIntStateOf(0) }

    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
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
          shape = RoundedCornerShape(8.dp),
        ) {
          Text(
            text = "ROUND ${roundIdx + 1}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
          )
        }
        Surface(
          color = MaterialTheme.colorScheme.surfaceVariant,
          shape = RoundedCornerShape(99.dp),
          modifier =
            Modifier.clip(RoundedCornerShape(99.dp))
              .minimumInteractiveComponentSize()
              .clickable { showHint = !showHint }
              .testTag("gamified_lab_hint_toggle"),
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
          ) {
            Icon(
              imageVector = Icons.Filled.Lightbulb,
              contentDescription = "Toggle hint",
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(14.dp),
            )
            Text(
              text = if (showHint) "Hide Hint" else "Hint",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.SemiBold,
            )
          }
        }
      }

      Text(
        text = currentRound.title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
      )
      ScientificText(
        text = currentRound.scenario,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      Surface(
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
      ) {
        ScientificText(
          text = "Objective: ${currentRound.objective}",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(10.dp),
        )
      }

      AnimatedVisibility(visible = showHint) {
        Surface(
          color = MaterialTheme.colorScheme.tertiaryContainer,
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier.fillMaxWidth(),
        ) {
          ScientificText(
            text = "Coach Hint: ${currentRound.hint}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier.padding(10.dp),
          )
        }
      }

      when (currentRound) {
        is GamifiedRound.SliderChallenge -> {
          var p1 by remember(lessonId, roundIdx) { mutableFloatStateOf(currentRound.param1Initial) }
          var p2 by remember(lessonId, roundIdx) { mutableFloatStateOf(currentRound.param2Initial) }
          val liveOutput = remember(p1, p2, currentRound) { currentRound.computeOutput(p1, p2) }

          TargetMeterGauge(
            liveValue = liveOutput,
            targetValue = currentRound.targetValue,
            tolerance = currentRound.tolerance,
            range = currentRound.gaugeRange,
            label = currentRound.outputLabel,
            unit = currentRound.outputUnit,
          )

          LabeledLabSlider(
            label = currentRound.param1Label,
            valueText = formatLabNumber(p1) + if (currentRound.param1Unit.isNotBlank()) " ${currentRound.param1Unit}" else "",
            value = p1,
            onValueChange = { if (!trialSubmitted) p1 = it },
            valueRange = currentRound.param1Range,
          )

          if (currentRound.param2Label != null) {
            LabeledLabSlider(
              label = currentRound.param2Label,
              valueText = formatLabNumber(p2) + if (currentRound.param2Unit.isNotBlank()) " ${currentRound.param2Unit}" else "",
              value = p2,
              onValueChange = { if (!trialSubmitted) p2 = it },
              valueRange = currentRound.param2Range,
            )
          }

          if (!trialSubmitted) {
            Button(
              onClick = {
                val span = abs(currentRound.gaugeRange.endInclusive - currentRound.gaugeRange.start).coerceAtLeast(1f)
                val diff = abs(liveOutput - currentRound.targetValue)
                val hit = diff <= currentRound.tolerance
                val accuracy =
                  if (hit) 100
                  else ((1f - (diff / (span * 0.45f)).coerceIn(0f, 1f)) * 85f).roundToInt().coerceIn(5, 92)
                lastHitSuccess = hit
                lastAccuracyPct = accuracy
                if (hit) {
                  streak += 1
                  perfectHits += 1
                  earnedThisRound = 80 + (streak * 20)
                } else {
                  streak = 0
                  earnedThisRound = (accuracy / 3).coerceAtLeast(10)
                }
                xp += earnedThisRound
                completedRounds += 1
                trialSubmitted = true
                onRewardEarned(earnedThisRound, completedRounds >= rounds.size)
              },
              modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).testTag("gamified_lab_submit"),
            ) {
              Icon(Icons.Filled.RocketLaunch, contentDescription = null, modifier = Modifier.size(18.dp))
              Text("Lock & Score Trial", modifier = Modifier.padding(start = 8.dp), fontWeight = FontWeight.Bold)
            }
          }
        }

        is GamifiedRound.ConsoleChallenge -> {
          var selA by remember(lessonId, roundIdx) { mutableIntStateOf(0) }
          var selB by remember(lessonId, roundIdx) { mutableIntStateOf(0) }

          Text(
            text = currentRound.controlALabel,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
          )
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            currentRound.controlAOptions.forEachIndexed { idx, opt ->
              val picked = selA == idx
              Surface(
                color = if (picked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(10.dp),
                modifier =
                  Modifier.clip(RoundedCornerShape(10.dp))
                    .minimumInteractiveComponentSize()
                    .clickable(enabled = !trialSubmitted) { selA = idx }
                    .testTag("gamified_console_a_$idx"),
              ) {
                ScientificText(
                  text = opt,
                  style = MaterialTheme.typography.labelMedium,
                  color = if (picked) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                  fontWeight = if (picked) FontWeight.Bold else FontWeight.Medium,
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                )
              }
            }
          }

          Text(
            text = currentRound.controlBLabel,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
          )
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            currentRound.controlBOptions.forEachIndexed { idx, opt ->
              val picked = selB == idx
              Surface(
                color = if (picked) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(10.dp),
                modifier =
                  Modifier.clip(RoundedCornerShape(10.dp))
                    .minimumInteractiveComponentSize()
                    .clickable(enabled = !trialSubmitted) { selB = idx }
                    .testTag("gamified_console_b_$idx"),
              ) {
                ScientificText(
                  text = opt,
                  style = MaterialTheme.typography.labelMedium,
                  color = if (picked) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurface,
                  fontWeight = if (picked) FontWeight.Bold else FontWeight.Medium,
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                )
              }
            }
          }

          Surface(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(10.dp),
            modifier =
              Modifier.fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp)),
          ) {
            ScientificText(
              text = currentRound.observePreview(selA, selB),
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Medium,
              modifier = Modifier.padding(10.dp),
            )
          }

          if (!trialSubmitted) {
            Button(
              onClick = {
                val hit = selA == currentRound.correctAIndex && selB == currentRound.correctBIndex
                val partial = (selA == currentRound.correctAIndex || selB == currentRound.correctBIndex)
                lastHitSuccess = hit
                lastAccuracyPct = when {
                  hit -> 100
                  partial -> 50
                  else -> 0
                }
                if (hit) {
                  streak += 1
                  perfectHits += 1
                  earnedThisRound = 80 + (streak * 20)
                } else {
                  streak = 0
                  earnedThisRound = if (partial) 25 else 10
                }
                xp += earnedThisRound
                completedRounds += 1
                trialSubmitted = true
                onRewardEarned(earnedThisRound, completedRounds >= rounds.size)
              },
              modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).testTag("gamified_lab_submit"),
            ) {
              Icon(Icons.Filled.RocketLaunch, contentDescription = null, modifier = Modifier.size(18.dp))
              Text("Verify & Score Setup", modifier = Modifier.padding(start = 8.dp), fontWeight = FontWeight.Bold)
            }
          }
        }
      }

      AnimatedVisibility(visible = trialSubmitted) {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(if (lastHitSuccess) semantics.successContainer else MaterialTheme.colorScheme.errorContainer)
              .border(
                1.dp,
                if (lastHitSuccess) semantics.success else MaterialTheme.colorScheme.error,
                RoundedCornerShape(12.dp),
              )
              .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          val fg =
            if (lastHitSuccess) semantics.onSuccessContainer
            else MaterialTheme.colorScheme.onErrorContainer
          Text(
            text =
              if (lastHitSuccess) "Target Locked ($lastAccuracyPct% Precision • +$earnedThisRound XP)"
              else "Off-Target ($lastAccuracyPct% Accuracy • +$earnedThisRound XP)",
            style = MaterialTheme.typography.titleSmall,
            color = fg,
            fontWeight = FontWeight.ExtraBold,
          )
          ScientificText(
            text = currentRound.explanation,
            style = MaterialTheme.typography.bodySmall,
            color = fg,
          )
          Button(
            onClick = {
              if (roundIdx < rounds.lastIndex) {
                roundIdx += 1
              } else {
                roundIdx = rounds.size
              }
            },
            modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).testTag("gamified_lab_next"),
          ) {
            Text(
              text = if (roundIdx < rounds.lastIndex) "Next Challenge Round" else "View Mission Summary",
              fontWeight = FontWeight.Bold,
            )
          }
        }
      }
    }
  }
}

@Composable
private fun TargetMeterGauge(
  liveValue: Float,
  targetValue: Float,
  tolerance: Float,
  range: ClosedFloatingPointRange<Float>,
  label: String,
  unit: String,
) {
  val span = abs(range.endInclusive - range.start).coerceAtLeast(0.001f)
  val liveFraction = ((liveValue - range.start) / span).coerceIn(0f, 1f)
  val targetStartFrac = (((targetValue - tolerance) - range.start) / span).coerceIn(0f, 1f)
  val targetEndFrac = (((targetValue + tolerance) - range.start) / span).coerceIn(0f, 1f)
  val inZone = abs(liveValue - targetValue) <= tolerance

  val primary = MaterialTheme.colorScheme.primary
  val track = MaterialTheme.colorScheme.surfaceVariant
  val success = MaterialTheme.semanticColors.success
  val danger = MaterialTheme.colorScheme.error

  Column(
    modifier =
      Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.background)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
        .padding(12.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Column {
        Text(
          text = label.uppercase(),
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontWeight = FontWeight.Bold,
        )
        Text(
          text = "${formatLabNumber(liveValue)} $unit".trim(),
          style = MaterialTheme.typography.titleMedium.copy(fontFeatureSettings = "tnum"),
          color = if (inZone) success else primary,
          fontWeight = FontWeight.ExtraBold,
        )
      }
      Box(
        modifier =
          Modifier.clip(RoundedCornerShape(99.dp))
            .background(if (inZone) MaterialTheme.semanticColors.successContainer else MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 10.dp, vertical = 4.dp),
      ) {
        Text(
          text = "Target: ${formatLabNumber(targetValue)} $unit".trim(),
          style = MaterialTheme.typography.labelSmall,
          color = if (inZone) MaterialTheme.semanticColors.onSuccessContainer else MaterialTheme.colorScheme.onSurfaceVariant,
          fontWeight = FontWeight.Bold,
        )
      }
    }

    Canvas(modifier = Modifier.fillMaxWidth().height(20.dp)) {
      val barTop = size.height * 0.25f
      val barH = size.height * 0.5f
      drawRoundRect(
        color = track,
        topLeft = Offset(0f, barTop),
        size = Size(size.width, barH),
        cornerRadius = CornerRadius(12f, 12f),
      )
      val zoneX = targetStartFrac * size.width
      val zoneW = ((targetEndFrac - targetStartFrac) * size.width).coerceAtLeast(10f)
      drawRoundRect(
        color = success.copy(alpha = 0.45f),
        topLeft = Offset(zoneX, barTop - 2f),
        size = Size(zoneW, barH + 4f),
        cornerRadius = CornerRadius(8f, 8f),
      )
      val markerX = (liveFraction * size.width).coerceIn(6f, size.width - 6f)
      drawCircle(
        color = if (inZone) success else danger,
        radius = size.height * 0.44f,
        center = Offset(markerX, size.height * 0.5f),
      )
    }
  }
}

private fun formatLabNumber(value: Float): String {
  val absV = abs(value)
  return when {
    absV >= 100f -> String.format(Locale.US, "%.1f", value)
    absV >= 10f -> String.format(Locale.US, "%.2f", value)
    else -> String.format(Locale.US, "%.2f", value)
  }
}
