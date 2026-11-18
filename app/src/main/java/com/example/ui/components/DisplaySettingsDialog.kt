package com.example.ui.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.local.FontSizeScale
import com.example.data.local.ThemeMode
import com.example.data.local.ThemePreferences

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DisplaySettingsDialog(
  themePreferences: ThemePreferences,
  onSetThemeMode: (ThemeMode) -> Unit,
  onSetUseDynamicColor: (Boolean) -> Unit,
  onSetFontSizeScale: (FontSizeScale) -> Unit,
  onDismiss: () -> Unit,
) {
  val supportsDynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

  AlertDialog(
    onDismissRequest = onDismiss,
    title = {
      Text(
        text = "Display & Reading Settings",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface,
      )
    },
    text = {
      Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
          text = "Text Size",
          style = MaterialTheme.typography.labelLarge,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold,
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
              label = {
                Text(
                  text = scale.label,
                  fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                )
              },
              colors =
                FilterChipDefaults.filterChipColors(
                  selectedContainerColor = MaterialTheme.colorScheme.primary,
                  selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                ),
              modifier = Modifier.testTag("dialog_font_scale_${scale.name.lowercase()}"),
            )
          }
        }

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
              text = "Reading Preview (${themePreferences.fontSizeScale.label})",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
            )
            ScientificText(
              text = "The resultant force R = √(F_1^2 + F_2^2 + 2 F_1 F_2 cos θ) determines equilibrium.",
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }
        }

        Text(
          text = "Theme Mode",
          style = MaterialTheme.typography.labelLarge,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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

        Row(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(MaterialTheme.colorScheme.background)
              .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
              .padding(horizontal = 12.dp, vertical = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
            Text(
              text = "Material 3 Dynamic Color",
              style = MaterialTheme.typography.titleSmall,
              color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
              text = if (supportsDynamic) "Adapt accent palette to device theme" else "Requires Android 12+",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
          Switch(
            checked = themePreferences.useDynamicColor,
            onCheckedChange = onSetUseDynamicColor,
            enabled = supportsDynamic,
          )
        }
      }
    },
    confirmButton = {
      Button(onClick = onDismiss) {
        Text("Done", fontWeight = FontWeight.Bold)
      }
    },
  )
}
