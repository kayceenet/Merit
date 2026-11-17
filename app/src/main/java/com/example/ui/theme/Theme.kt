package com.example.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

@Immutable
data class MeritSemanticColors(
  val success: Color,
  val onSuccess: Color,
  val successContainer: Color,
  val onSuccessContainer: Color,
  val classroomBar: Color,
  val onClassroomBar: Color,
  val classroomSubtext: Color,
  val classroomAccent: Color,
  val onClassroomAccent: Color,
)

private val LightSemanticColors =
  MeritSemanticColors(
    success = MasteryEmerald,
    onSuccess = Color.White,
    successContainer = MasteryEmeraldLight,
    onSuccessContainer = Color(0xFF022C22),
    classroomBar = UdacityNavy,
    onClassroomBar = Color.White,
    classroomSubtext = Color(0xFFBAE6FD),
    classroomAccent = UdacityCyan,
    onClassroomAccent = UdacityCyanInk,
  )

private val DarkSemanticColors =
  MeritSemanticColors(
    success = MasteryEmeraldBright,
    onSuccess = Color(0xFF022C22),
    successContainer = MasteryEmeraldDarkContainer,
    onSuccessContainer = Color(0xFFD1FAE5),
    classroomBar = UdacityNavy,
    onClassroomBar = Color.White,
    classroomSubtext = Color(0xFFBAE6FD),
    classroomAccent = UdacityCyan,
    onClassroomAccent = UdacityCyanInk,
  )

val LocalMeritSemanticColors = staticCompositionLocalOf { LightSemanticColors }

val MaterialTheme.semanticColors: MeritSemanticColors
  @Composable get() = LocalMeritSemanticColors.current

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
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = DarkOnErrorContainer,
    background = ObservatoryBackground,
    onBackground = ObservatoryOnBackground,
    surface = ObservatorySurface,
    onSurface = ObservatoryOnBackground,
    surfaceVariant = ObservatorySurfaceVariant,
    onSurfaceVariant = ObservatoryOnSurfaceVariant,
    outline = ObservatoryOutline,
    outlineVariant = ObservatoryOutlineVariant,
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
    error = LightError,
    onError = LightOnError,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
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

/**
 * Ensures dynamic Material 3 palettes retain crisp borders and surface separation for readability.
 */
private fun ColorScheme.withHighContrastAccessibility(darkTheme: Boolean): ColorScheme =
  if (darkTheme) {
    copy(
      background = ObservatoryBackground,
      surface = ObservatorySurface,
      onBackground = ObservatoryOnBackground,
      onSurface = ObservatoryOnBackground,
      onSurfaceVariant = ObservatoryOnSurfaceVariant,
      outline = ObservatoryOutline,
      outlineVariant = ObservatoryOutlineVariant,
    )
  } else {
    copy(
      background = PearlBackground,
      surface = PureSurface,
      onBackground = SlateOnBackground,
      onSurface = SlateOnBackground,
      onSurfaceVariant = MutedSlateOnSurfaceVariant,
      outline = HairlineOutline,
      outlineVariant = SubtleOutlineVariant,
    )
  }

@Composable
fun MeritPrepTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        val dynamic =
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        dynamic.withHighContrastAccessibility(darkTheme)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }
  val semantics = if (darkTheme) DarkSemanticColors else LightSemanticColors

  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as? Activity)?.window
      if (window != null) {
        val controller = WindowCompat.getInsetsController(window, view)
        controller.isAppearanceLightStatusBars = !darkTheme
        controller.isAppearanceLightNavigationBars = !darkTheme
      }
    }
  }

  CompositionLocalProvider(LocalMeritSemanticColors provides semantics) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      shapes = MeritPrepShapes,
      content = content,
    )
  }
}
