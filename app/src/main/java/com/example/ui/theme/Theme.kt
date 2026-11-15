package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme =
  darkColorScheme(
    primary = DarkCobaltPrimary,
    onPrimary = DarkCobaltOnPrimary,
    primaryContainer = DarkCobaltContainer,
    onPrimaryContainer = DarkCobaltOnContainer,
    secondary = DarkTealSecondary,
    onSecondary = DarkTealOnSecondary,
    secondaryContainer = DarkTealContainer,
    onSecondaryContainer = DarkTealOnContainer,
    tertiary = DarkAmberTertiary,
    onTertiary = DarkAmberOnTertiary,
    tertiaryContainer = DarkAmberContainer,
    onTertiaryContainer = DarkAmberOnContainer,
    background = ObservatoryBackground,
    onBackground = ObservatoryOnBackground,
    surface = ObservatorySurface,
    onSurface = ObservatoryOnBackground,
    surfaceVariant = ObservatorySurfaceVariant,
    onSurfaceVariant = ObservatoryOnSurfaceVariant,
    outline = ObservatoryOutline,
    outlineVariant = ObservatorySurfaceVariant,
  )

private val LightColorScheme =
  lightColorScheme(
    primary = CobaltPrimary,
    onPrimary = CobaltOnPrimary,
    primaryContainer = CobaltPrimaryContainer,
    onPrimaryContainer = CobaltOnPrimaryContainer,
    secondary = LabTealSecondary,
    onSecondary = LabTealOnSecondary,
    secondaryContainer = LabTealContainer,
    onSecondaryContainer = LabTealOnSecondaryContainer,
    tertiary = ScholarAmberTertiary,
    onTertiary = ScholarAmberOnTertiary,
    tertiaryContainer = ScholarAmberContainer,
    onTertiaryContainer = ScholarAmberOnTertiaryContainer,
    background = PearlBackground,
    onBackground = SlateOnBackground,
    surface = PureSurface,
    onSurface = SlateOnBackground,
    surfaceVariant = SlateSurfaceVariant,
    onSurfaceVariant = MutedSlateOnSurfaceVariant,
    outline = HairlineOutline,
    outlineVariant = SubtleOutlineVariant,
  )

val MeritPrepShapes =
  Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp),
  )

@Composable
fun MeritPrepTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    shapes = MeritPrepShapes,
    content = content,
  )
}
