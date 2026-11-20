package com.example.ui.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudSync
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.local.FontSizeScale
import com.example.data.local.LineSpacingScale
import com.example.data.local.ThemeMode
import com.example.data.local.ThemePalette
import com.example.data.local.ThemePreferences

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DisplaySettingsDialog(
  themePreferences: ThemePreferences,
  onSetThemeMode: (ThemeMode) -> Unit,
  onSetUseDynamicColor: (Boolean) -> Unit,
  onSetFontSizeScale: (FontSizeScale) -> Unit,
  onSetThemePalette: (ThemePalette) -> Unit = {},
  onSetHighContrastBoost: (Boolean) -> Unit = {},
  onSetLineSpacingScale: (LineSpacingScale) -> Unit = {},
  onSetBoldEquations: (Boolean) -> Unit = {},
  onSetCbtSecondsPerQuestion: (Int) -> Unit = {},
  onSetCbtAutoAdvance: (Boolean) -> Unit = {},
  onSetCbtShowCalculator: (Boolean) -> Unit = {},
  onSetShuffleOptionsOnRetry: (Boolean) -> Unit = {},
  onSetMasteryPassPercent: (Int) -> Unit = {},
  onSetDailyXpTarget: (Int) -> Unit = {},
  onSetSpacedReviewBatchSize: (Int) -> Unit = {},
  onExportBackup: (() -> Unit)? = null,
  onImportBackup: (() -> Unit)? = null,
  onOpenCloudSync: (() -> Unit)? = null,
  cloudStatusLabel: String? = null,
  onDismiss: () -> Unit,
) {
  val supportsDynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

  AlertDialog(
    onDismissRequest = onDismiss,
    title = {
      Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
          text = "Settings & Study Portal",
          style = MaterialTheme.typography.titleLarge,
          color = MaterialTheme.colorScheme.onSurface,
          fontWeight = FontWeight.Bold,
        )
        Text(
          text = "Appearance, reading ergonomics, exam pacing & progression",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }
    },
    text = {
      Column(
        modifier = Modifier.fillMaxWidth().heightIn(max = 460.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp),
      ) {
        SettingsSectionHeader("Theme & Contrast")
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          ThemeMode.entries.forEach { mode ->
            val selected = themePreferences.themeMode == mode
            FilterChip(
              selected = selected,
              onClick = { onSetThemeMode(mode) },
              label = {
                Text(
                  text = mode.label,
                  fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                )
              },
              colors =
                FilterChipDefaults.filterChipColors(
                  selectedContainerColor = MaterialTheme.colorScheme.primary,
                  selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                ),
              modifier = Modifier.testTag("dialog_theme_mode_${mode.name.lowercase()}"),
            )
          }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          ThemePalette.entries.forEach { palette ->
            val selected = themePreferences.themePalette == palette
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .background(
                    if (selected) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
                  )
                  .border(
                    width = if (selected) 1.5.dp else 1.dp,
                    color =
                      if (selected) MaterialTheme.colorScheme.primary
                      else MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(12.dp),
                  )
                  .clickable { onSetThemePalette(palette) }
                  .padding(horizontal = 12.dp, vertical = 10.dp)
                  .testTag("dialog_palette_${palette.name.lowercase()}"),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
              Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Box(
                  modifier =
                    Modifier.size(16.dp)
                      .clip(CircleShape)
                      .background(Color(palette.lightSwatchHex))
                      .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                )
                Box(
                  modifier =
                    Modifier.size(16.dp)
                      .clip(CircleShape)
                      .background(Color(palette.darkSwatchHex))
                      .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                )
              }
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = palette.label,
                  style = MaterialTheme.typography.titleSmall,
                  color =
                    if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onSurface,
                  fontWeight = FontWeight.Bold,
                )
                Text(
                  text = palette.tagline,
                  style = MaterialTheme.typography.bodySmall,
                  color =
                    if (selected) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                )
              }
            }
          }
        }

        SettingsSwitchRow(
          title = "High-Contrast Ink Boost",
          subtitle = "Pure black/white surfaces & sharp AAA borders",
          checked = themePreferences.highContrastBoost,
          onCheckedChange = onSetHighContrastBoost,
          testTag = "switch_high_contrast",
        )

        SettingsSwitchRow(
          title = "Material 3 Dynamic Color",
          subtitle =
            when {
              !supportsDynamic -> "Requires Android 12+"
              themePreferences.highContrastBoost ||
                themePreferences.themePalette == ThemePalette.OLED_HIGH_CONTRAST ->
                "Paused while High-Contrast palette is active"
              else -> "Blend primary accent with device wallpaper"
            },
          checked = themePreferences.useDynamicColor,
          onCheckedChange = onSetUseDynamicColor,
          enabled =
            supportsDynamic &&
              !themePreferences.highContrastBoost &&
              themePreferences.themePalette != ThemePalette.OLED_HIGH_CONTRAST,
          testTag = "switch_dynamic_color",
        )

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        SettingsSectionHeader("Reading & Formula Clarity")

        Text(
          text = "Text Scale",
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          FontSizeScale.entries.forEach { scale ->
            val selected = themePreferences.fontSizeScale == scale
            FilterChip(
              selected = selected,
              onClick = { onSetFontSizeScale(scale) },
              label = { Text(scale.label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
              colors =
                FilterChipDefaults.filterChipColors(
                  selectedContainerColor = MaterialTheme.colorScheme.primary,
                  selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                ),
              modifier = Modifier.testTag("dialog_font_scale_${scale.name.lowercase()}"),
            )
          }
        }

        Text(
          text = "Line Spacing Comfort",
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          LineSpacingScale.entries.forEach { spacing ->
            val selected = themePreferences.lineSpacingScale == spacing
            FilterChip(
              selected = selected,
              onClick = { onSetLineSpacingScale(spacing) },
              label = { Text(spacing.label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
              colors =
                FilterChipDefaults.filterChipColors(
                  selectedContainerColor = MaterialTheme.colorScheme.primary,
                  selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                ),
              modifier = Modifier.testTag("dialog_line_spacing_${spacing.name.lowercase()}"),
            )
          }
        }

        SettingsSwitchRow(
          title = "Bold Scientific Equations",
          subtitle = "Thicker weight for fractions, roots & indices",
          checked = themePreferences.boldEquations,
          onCheckedChange = onSetBoldEquations,
          testTag = "switch_bold_equations",
        )

        Surface(
          color = MaterialTheme.colorScheme.surfaceVariant,
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
          ) {
            Text(
              text =
                "Live Preview (${themePreferences.fontSizeScale.label} · ${themePreferences.lineSpacingScale.label})",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
            )
            ScientificText(
              text =
                "Percentage error: \\%Error = \\frac{|E|}{|\\text{True}|} \\times 100\\% and R = \\sqrt{F_1^2 + F_2^2 + 2F_1F_2\\cos\\theta}.",
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        SettingsSectionHeader("CBT Exam Simulator")

        Text(
          text = "Seconds per Question",
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          listOf(45, 60, 75, 90).forEach { sec ->
            val selected = themePreferences.cbtSecondsPerQuestion == sec
            FilterChip(
              selected = selected,
              onClick = { onSetCbtSecondsPerQuestion(sec) },
              label = { Text("${sec}s", fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
              colors =
                FilterChipDefaults.filterChipColors(
                  selectedContainerColor = MaterialTheme.colorScheme.primary,
                  selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                ),
              modifier = Modifier.testTag("dialog_cbt_pace_$sec"),
            )
          }
        }

        SettingsSwitchRow(
          title = "Show On-Screen Calculator",
          subtitle = "Scientific trig & log keypad during CBT & lessons",
          checked = themePreferences.cbtShowCalculator,
          onCheckedChange = onSetCbtShowCalculator,
          testTag = "switch_cbt_calculator",
        )

        SettingsSwitchRow(
          title = "Auto-Advance After Selection",
          subtitle = "Move smoothly to next item after picking an option",
          checked = themePreferences.cbtAutoAdvance,
          onCheckedChange = onSetCbtAutoAdvance,
          testTag = "switch_cbt_autoadvance",
        )

        SettingsSwitchRow(
          title = "Reshuffle Questions & Options on Retry",
          subtitle = "Prevents position memorization across retakes",
          checked = themePreferences.shuffleOptionsOnRetry,
          onCheckedChange = onSetShuffleOptionsOnRetry,
          testTag = "switch_shuffle_retry",
        )

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        SettingsSectionHeader("Mastery & Spaced Revision")

        Text(
          text = "Lesson Mastery Pass Threshold (${themePreferences.masteryPassPercent}%)",
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          listOf(70, 75, 80, 85, 90).forEach { pct ->
            val selected = themePreferences.masteryPassPercent == pct
            FilterChip(
              selected = selected,
              onClick = { onSetMasteryPassPercent(pct) },
              label = { Text("$pct%", fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
              colors =
                FilterChipDefaults.filterChipColors(
                  selectedContainerColor = MaterialTheme.colorScheme.primary,
                  selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                ),
              modifier = Modifier.testTag("dialog_mastery_pct_$pct"),
            )
          }
        }

        Text(
          text = "Daily XP Goal (${themePreferences.dailyXpTarget} XP)",
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          listOf(50, 100, 150, 200).forEach { xp ->
            val selected = themePreferences.dailyXpTarget == xp
            FilterChip(
              selected = selected,
              onClick = { onSetDailyXpTarget(xp) },
              label = { Text("$xp XP") },
              colors =
                FilterChipDefaults.filterChipColors(
                  selectedContainerColor = MaterialTheme.colorScheme.primary,
                  selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
          }
        }

        Text(
          text = "Spaced Recall Deck Batch Size (${themePreferences.spacedReviewBatchSize} cards)",
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          listOf(5, 10, 15, 20).forEach { batch ->
            val selected = themePreferences.spacedReviewBatchSize == batch
            FilterChip(
              selected = selected,
              onClick = { onSetSpacedReviewBatchSize(batch) },
              label = { Text("$batch") },
              colors =
                FilterChipDefaults.filterChipColors(
                  selectedContainerColor = MaterialTheme.colorScheme.primary,
                  selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
          }
        }

        if (onExportBackup != null || onImportBackup != null || onOpenCloudSync != null) {
          HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
          SettingsSectionHeader("Backup & Optional Cloud Sync")
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            if (onExportBackup != null) {
              OutlinedButton(
                onClick = onExportBackup,
                modifier = Modifier.weight(1f).minimumInteractiveComponentSize(),
              ) {
                Icon(Icons.Filled.Upload, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Export JSON", maxLines = 1)
              }
            }
            if (onImportBackup != null) {
              OutlinedButton(
                onClick = onImportBackup,
                modifier = Modifier.weight(1f).minimumInteractiveComponentSize(),
              ) {
                Icon(Icons.Filled.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Restore JSON", maxLines = 1)
              }
            }
          }
          if (onOpenCloudSync != null) {
            OutlinedButton(
              onClick = onOpenCloudSync,
              modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize(),
            ) {
              Icon(Icons.Filled.CloudSync, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(Modifier.width(8.dp))
              Text(cloudStatusLabel ?: "Google Sign-In & Cloud Sync")
            }
          }
        }
      }
    },
    confirmButton = {
      Button(
        onClick = onDismiss,
        modifier = Modifier.minimumInteractiveComponentSize().testTag("settings_done_button"),
      ) {
        Text("Done", fontWeight = FontWeight.Bold)
      }
    },
  )
}

@Composable
private fun SettingsSectionHeader(title: String) {
  Text(
    text = title,
    style = MaterialTheme.typography.labelLarge,
    color = MaterialTheme.colorScheme.primary,
    fontWeight = FontWeight.Bold,
  )
}

@Composable
private fun SettingsSwitchRow(
  title: String,
  subtitle: String,
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit,
  enabled: Boolean = true,
  testTag: String,
) {
  Row(
    modifier =
      Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
        .padding(horizontal = 12.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Spacer(Modifier.height(2.dp))
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }
    Switch(
      checked = checked,
      onCheckedChange = onCheckedChange,
      enabled = enabled,
      modifier = Modifier.testTag(testTag),
    )
  }
}
