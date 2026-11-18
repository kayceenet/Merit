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
import com.example.data.local.FontSizeScale

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
    classroomBar = CourseraDeepNavy,
    onClassroomBar = Color.White,
    classroomSubtext = Color(0xFFD6E6FF),
    classroomAccent = CourseraAzureAccent,
    onClassroomAccent = CourseraAzureInk,
  )

private val DarkSemanticColors =
  MeritSemanticColors(
    success = MasteryEmeraldBright,
    onSuccess = Color(0xFF022C22),
    successContainer = MasteryEmeraldDarkContainer,
    onSuccessContainer = Color(0xFFD1FAE5),
    classroomBar = CourseraDeepNavy,
    onClassroomBar = Color.White,
    classroomSubtext = Color(0xFFD6E6FF),
    classroomAccent = CourseraAzureAccent,
    onClassroomAccent = CourseraAzureInk,
  )

val LocalMeritSemanticColors = staticCompositionLocalOf { LightSemanticColors }

val LocalFontScale = staticCompositionLocalOf { FontSizeScale.STANDARD.multiplier }

val MaterialTheme.semanticColors: MeritSemanticColors
  @Composable get() = LocalMeritSemanticColors.current

private val DarkColorScheme =
  darkColorScheme(
    primary = DarkCourseraBlue,
    onPrimary = DarkCourseraOnBlue,
    primaryContainer = DarkCourseraBlueContainer,
    onPrimaryContainer = DarkCourseraOnBlueContainer,
    secondary = DarkCourseraTeal,
    onSecondary = DarkCourseraOnTeal,
    secondaryContainer = DarkCourseraTealContainer,
    onSecondaryContainer = DarkCourseraOnTealContainer,
    tertiary = DarkScholarAmber,
    onTertiary = DarkScholarOnAmber,
    tertiaryContainer = DarkScholarAmberContainer,
    onTertiaryContainer = DarkScholarOnAmberContainer,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = DarkOnErrorContainer,
    background = NightSlateBackground,
    onBackground = NightOnBackground,
    surface = NightCardSurface,
    onSurface = NightOnBackground,
    surfaceVariant = NightSurfaceVariant,
    onSurfaceVariant = NightOnSurfaceVariant,
    outline = NightOutline,
    outlineVariant = NightOutlineVariant,
  )

private val LightColorScheme =
  lightColorScheme(
    primary = CourseraBlue,
    onPrimary = CourseraOnBlue,
    primaryContainer = CourseraBlueContainer,
    onPrimaryContainer = CourseraOnBlueContainer,
    secondary = CourseraTeal,
    onSecondary = CourseraOnTeal,
    secondaryContainer = CourseraTealContainer,
    onSecondaryContainer = CourseraOnTealContainer,
    tertiary = ScholarAmber,
    onTertiary = ScholarOnAmber,
    tertiaryContainer = ScholarAmberContainer,
    onTertiaryContainer = ScholarOnAmberContainer,
    error = LightError,
    onError = LightOnError,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = PorcelainBackground,
    onBackground = InkOnBackground,
    surface = PureCardSurface,
    onSurface = InkOnBackground,
    surfaceVariant = CourseraSurfaceVariant,
    onSurfaceVariant = MutedInkOnSurfaceVariant,
    outline = HairlineOutline,
    outlineVariant = SubtleOutlineVariant,
  )

val MeritPrepShapes =
  Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
  )

/**
 * Ensures dynamic Material 3 palettes retain crisp borders and surface separation for readability.
 */
private fun ColorScheme.withHighContrastAccessibility(darkTheme: Boolean): ColorScheme =
  if (darkTheme) {
    copy(
      background = NightSlateBackground,
      surface = NightCardSurface,
      onBackground = NightOnBackground,
      onSurface = NightOnBackground,
      onSurfaceVariant = NightOnSurfaceVariant,
      outline = NightOutline,
      outlineVariant = NightOutlineVariant,
    )
  } else {
    copy(
      background = PorcelainBackground,
      surface = PureCardSurface,
      onBackground = InkOnBackground,
      onSurface = InkOnBackground,
      onSurfaceVariant = MutedInkOnSurfaceVariant,
      outline = HairlineOutline,
      outlineVariant = SubtleOutlineVariant,
    )
  }

@Composable
fun MeritPrepTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = true,
  fontSizeScale: FontSizeScale = FontSizeScale.STANDARD,
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

  CompositionLocalProvider(
    LocalMeritSemanticColors provides semantics,
    LocalFontScale provides fontSizeScale.multiplier,
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = scaledTypography(fontSizeScale.multiplier),
      shapes = MeritPrepShapes,
      content = content,
    )
  }
}
