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
import com.example.data.local.LineSpacingScale
import com.example.data.local.ThemePalette

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
  val heroGradientStart: Color,
  val heroGradientMid: Color,
  val heroGradientEnd: Color,
  val coralAccent: Color,
  val indigoAccent: Color,
  val periwinkleAccent: Color,
  val softTintSurface: Color,
)

private val LightSemanticColors =
  MeritSemanticColors(
    success = MasteryEmerald,
    onSuccess = Color.White,
    successContainer = MasteryEmeraldLight,
    onSuccessContainer = Color(0xFF043832),
    classroomBar = StudioDeepNavy,
    onClassroomBar = Color.White,
    classroomSubtext = Color(0xFFDCE7FF),
    classroomAccent = Color(0xFF7CA7FF),
    onClassroomAccent = Color(0xFF091A42),
    heroGradientStart = StudioBlue,
    heroGradientMid = StudioLavenderMid,
    heroGradientEnd = StudioCoral,
    coralAccent = StudioCoral,
    indigoAccent = StudioIndigo,
    periwinkleAccent = StudioPeriwinkle,
    softTintSurface = StudioSoftTintVariant,
  )

private val DarkSemanticColors =
  MeritSemanticColors(
    success = MasteryEmeraldBright,
    onSuccess = Color(0xFF022C22),
    successContainer = MasteryEmeraldDarkContainer,
    onSuccessContainer = Color(0xFFD1FAE5),
    classroomBar = Color(0xFF141B38),
    onClassroomBar = Color(0xFFF8FAFC),
    classroomSubtext = Color(0xFFD0DCFF),
    classroomAccent = Color(0xFF89B4FF),
    onClassroomAccent = Color(0xFF071636),
    heroGradientStart = Color(0xFF4772D6),
    heroGradientMid = Color(0xFF6B5BC4),
    heroGradientEnd = Color(0xFFD45B6B),
    coralAccent = DarkStudioCoral,
    indigoAccent = DarkStudioIndigo,
    periwinkleAccent = Color(0xFF9F9EE8),
    softTintSurface = NightSurfaceVariant,
  )

val LocalMeritSemanticColors = staticCompositionLocalOf { LightSemanticColors }

val LocalFontScale = staticCompositionLocalOf { FontSizeScale.STANDARD.multiplier }

val LocalLineSpacingMultiplier = staticCompositionLocalOf { LineSpacingScale.STANDARD.multiplier }

val LocalBoldEquations = staticCompositionLocalOf { false }

val MaterialTheme.semanticColors: MeritSemanticColors
  @Composable get() = LocalMeritSemanticColors.current

private val RoyalIndigoDarkScheme =
  darkColorScheme(
    primary = DarkStudioBlue,
    onPrimary = DarkStudioOnBlue,
    primaryContainer = DarkStudioBlueContainer,
    onPrimaryContainer = DarkStudioOnBlueContainer,
    secondary = DarkStudioIndigo,
    onSecondary = DarkStudioOnIndigo,
    secondaryContainer = DarkStudioIndigoContainer,
    onSecondaryContainer = DarkStudioOnIndigoContainer,
    tertiary = DarkStudioCoral,
    onTertiary = DarkStudioOnCoral,
    tertiaryContainer = DarkStudioCoralContainer,
    onTertiaryContainer = DarkStudioOnCoralContainer,
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

private val RoyalIndigoLightScheme =
  lightColorScheme(
    primary = StudioBlue,
    onPrimary = StudioOnBlue,
    primaryContainer = StudioBlueContainer,
    onPrimaryContainer = StudioOnBlueContainer,
    secondary = StudioIndigo,
    onSecondary = StudioOnIndigo,
    secondaryContainer = StudioIndigoContainer,
    onSecondaryContainer = StudioOnIndigoContainer,
    tertiary = StudioCoral,
    onTertiary = StudioOnCoral,
    tertiaryContainer = StudioCoralContainer,
    onTertiaryContainer = StudioOnCoralContainer,
    error = LightError,
    onError = LightOnError,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = StudioIceBackground,
    onBackground = StudioInkOnBackground,
    surface = PureCardSurface,
    onSurface = StudioInkOnBackground,
    surfaceVariant = StudioSoftTintVariant,
    onSurfaceVariant = StudioMutedSlateInk,
    outline = StudioOutline,
    outlineVariant = StudioHairlineVariant,
  )

private val EmeraldDarkScheme =
  RoyalIndigoDarkScheme.copy(
    primary = DarkEmeraldPrimary,
    onPrimary = DarkEmeraldOnPrimary,
    primaryContainer = DarkEmeraldContainer,
    onPrimaryContainer = DarkEmeraldOnContainer,
    secondary = DarkStudioBlue,
    onSecondary = DarkStudioOnBlue,
    secondaryContainer = DarkStudioBlueContainer,
    onSecondaryContainer = DarkStudioOnBlueContainer,
  )

private val EmeraldLightScheme =
  RoyalIndigoLightScheme.copy(
    primary = EmeraldPrimary,
    onPrimary = Color.White,
    primaryContainer = EmeraldContainer,
    onPrimaryContainer = EmeraldOnContainer,
    secondary = StudioBlue,
    onSecondary = Color.White,
    secondaryContainer = StudioBlueContainer,
    onSecondaryContainer = StudioOnBlueContainer,
  )

private val WarmAmberDarkScheme =
  RoyalIndigoDarkScheme.copy(
    primary = Color(0xFFFBBF24),
    onPrimary = Color(0xFF2D1600),
    primaryContainer = Color(0xFF543207),
    onPrimaryContainer = Color(0xFFFEF3C7),
    secondary = DarkEmeraldPrimary,
    onSecondary = DarkEmeraldOnPrimary,
    tertiary = DarkStudioBlue,
    background = DarkWarmBg,
    surface = DarkWarmSurface,
    surfaceVariant = DarkWarmVariant,
  )

private val WarmAmberLightScheme =
  RoyalIndigoLightScheme.copy(
    primary = WarmAmberPrimary,
    onPrimary = Color.White,
    primaryContainer = WarmAmberContainer,
    onPrimaryContainer = WarmAmberOnContainer,
    secondary = EmeraldPrimary,
    tertiary = StudioBlue,
    background = WarmPaperBg,
    surface = PureCardSurface,
    surfaceVariant = WarmPaperVariant,
  )

private val OledHighContrastDarkScheme =
  RoyalIndigoDarkScheme.copy(
    primary = OledPrimaryBlue,
    onPrimary = OledOnPrimary,
    primaryContainer = OledPrimaryContainer,
    onPrimaryContainer = OledOnPrimaryContainer,
    secondary = MasteryEmeraldBright,
    onSecondary = Color(0xFF002419),
    background = OledBlack,
    onBackground = Color.White,
    surface = OledCardSurface,
    onSurface = Color.White,
    surfaceVariant = OledElevatedVariant,
    onSurfaceVariant = Color(0xFFE2E8F0),
    outline = Color(0xFF94A3B8),
    outlineVariant = Color(0xFF475569),
  )

private val OledHighContrastLightScheme =
  RoyalIndigoLightScheme.copy(
    primary = OledLightPrimary,
    onPrimary = Color.White,
    primaryContainer = OledLightPrimaryContainer,
    onPrimaryContainer = Color(0xFF001944),
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    surfaceVariant = Color(0xFFEDF2FA),
    onSurfaceVariant = Color(0xFF1E293B),
    outline = Color(0xFF334155),
    outlineVariant = Color(0xFF94A3B8),
  )

private val CrimsonDarkScheme =
  RoyalIndigoDarkScheme.copy(
    primary = DarkCrimsonPrimary,
    onPrimary = DarkCrimsonOnPrimary,
    primaryContainer = DarkCrimsonContainer,
    onPrimaryContainer = DarkCrimsonOnContainer,
    secondary = DarkStudioBlue,
    onSecondary = DarkStudioOnBlue,
    secondaryContainer = DarkStudioBlueContainer,
    onSecondaryContainer = DarkStudioOnBlueContainer,
  )

private val CrimsonLightScheme =
  RoyalIndigoLightScheme.copy(
    primary = CrimsonPrimary,
    onPrimary = Color.White,
    primaryContainer = CrimsonContainer,
    onPrimaryContainer = CrimsonOnContainer,
    secondary = StudioBlue,
    onSecondary = Color.White,
    secondaryContainer = StudioBlueContainer,
    onSecondaryContainer = StudioOnBlueContainer,
  )

private fun semanticColorsFor(palette: ThemePalette, darkTheme: Boolean): MeritSemanticColors =
  when (palette) {
    ThemePalette.ROYAL_INDIGO -> if (darkTheme) DarkSemanticColors else LightSemanticColors
    ThemePalette.EMERALD_SCHOLAR ->
      (if (darkTheme) DarkSemanticColors else LightSemanticColors).copy(
        classroomBar = if (darkTheme) Color(0xFF062E26) else Color(0xFF0F766E),
        classroomSubtext = Color(0xFFD1FAE5),
        classroomAccent = Color(0xFF34D399),
        heroGradientStart = if (darkTheme) Color(0xFF0D9488) else Color(0xFF14B8A6),
        heroGradientMid = if (darkTheme) Color(0xFF3B6FD4) else Color(0xFF5A8DEE),
        heroGradientEnd = if (darkTheme) Color(0xFF5256B8) else Color(0xFF7D7CC2),
      )
    ThemePalette.WARM_AMBER ->
      (if (darkTheme) DarkSemanticColors else LightSemanticColors).copy(
        classroomBar = if (darkTheme) Color(0xFF2B1C06) else Color(0xFF92400E),
        classroomSubtext = Color(0xFFFEF3C7),
        classroomAccent = Color(0xFFFBBF24),
        onClassroomAccent = Color(0xFF2D1600),
        heroGradientStart = Color(0xFFD97706),
        heroGradientMid = Color(0xFFE07A68),
        heroGradientEnd = Color(0xFFF67E87),
      )
    ThemePalette.OLED_HIGH_CONTRAST ->
      (if (darkTheme) DarkSemanticColors else LightSemanticColors).copy(
        classroomBar = if (darkTheme) Color(0xFF070B12) else Color(0xFF0F296B),
        classroomSubtext = Color.White,
        classroomAccent = Color(0xFF60A5FA),
      )
    ThemePalette.CRIMSON_DISTINCTION ->
      (if (darkTheme) DarkSemanticColors else LightSemanticColors).copy(
        classroomBar = if (darkTheme) Color(0xFF330A1B) else Color(0xFF9D174D),
        classroomSubtext = Color(0xFFFFE4EC),
        classroomAccent = Color(0xFFFDA4AF),
        onClassroomAccent = Color(0xFF4C0519),
        heroGradientStart = Color(0xFFBE185D),
        heroGradientMid = Color(0xFF7D5CC9),
        heroGradientEnd = Color(0xFF5A8DEE),
      )
  }

val MeritPrepShapes =
  Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(26.dp),
  )

private fun ColorScheme.withContrastBoost(
  darkTheme: Boolean,
  boosted: Boolean,
  isDynamic: Boolean,
): ColorScheme =
  if (darkTheme) {
    copy(
      background = when {
        boosted -> OledBlack
        isDynamic -> NightSlateBackground
        else -> background
      },
      surface = when {
        boosted -> OledCardSurface
        isDynamic -> NightCardSurface
        else -> surface
      },
      surfaceVariant = when {
        boosted -> OledElevatedVariant
        isDynamic -> NightSurfaceVariant
        else -> surfaceVariant
      },
      onBackground = if (boosted) Color.White else NightOnBackground,
      onSurface = if (boosted) Color.White else NightOnBackground,
      onSurfaceVariant = if (boosted) Color(0xFFE2E8F0) else NightOnSurfaceVariant,
      outline = if (boosted) Color(0xFF94A3B8) else NightOutline,
      outlineVariant = if (boosted) Color(0xFF475569) else NightOutlineVariant,
    )
  } else {
    copy(
      background = when {
        boosted -> Color.White
        isDynamic -> StudioIceBackground
        else -> background
      },
      surface = when {
        boosted -> Color.White
        isDynamic -> PureCardSurface
        else -> surface
      },
      surfaceVariant = when {
        boosted -> Color(0xFFEDF2FA)
        isDynamic -> StudioSoftTintVariant
        else -> surfaceVariant
      },
      onBackground = if (boosted) Color.Black else StudioInkOnBackground,
      onSurface = if (boosted) Color.Black else StudioInkOnBackground,
      onSurfaceVariant = if (boosted) Color(0xFF1E293B) else StudioMutedSlateInk,
      outline = if (boosted) Color(0xFF334155) else StudioOutline,
      outlineVariant = if (boosted) Color(0xFF94A3B8) else StudioHairlineVariant,
    )
  }

@Composable
fun MeritPrepTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = true,
  themePalette: ThemePalette = ThemePalette.ROYAL_INDIGO,
  highContrastBoost: Boolean = false,
  fontSizeScale: FontSizeScale = FontSizeScale.STANDARD,
  lineSpacingScale: LineSpacingScale = LineSpacingScale.STANDARD,
  boldEquations: Boolean = false,
  content: @Composable () -> Unit,
) {
  val useDynamic =
    dynamicColor &&
      !highContrastBoost &&
      themePalette != ThemePalette.OLED_HIGH_CONTRAST &&
      Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

  val baseScheme =
    when {
      useDynamic -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      themePalette == ThemePalette.EMERALD_SCHOLAR ->
        if (darkTheme) EmeraldDarkScheme else EmeraldLightScheme
      themePalette == ThemePalette.WARM_AMBER ->
        if (darkTheme) WarmAmberDarkScheme else WarmAmberLightScheme
      themePalette == ThemePalette.OLED_HIGH_CONTRAST ->
        if (darkTheme) OledHighContrastDarkScheme else OledHighContrastLightScheme
      themePalette == ThemePalette.CRIMSON_DISTINCTION ->
        if (darkTheme) CrimsonDarkScheme else CrimsonLightScheme
      else -> if (darkTheme) RoyalIndigoDarkScheme else RoyalIndigoLightScheme
    }

  val colorScheme =
    if (useDynamic || highContrastBoost) {
      baseScheme.withContrastBoost(
        darkTheme = darkTheme,
        boosted = highContrastBoost,
        isDynamic = useDynamic,
      )
    } else {
      baseScheme
    }

  val semantics = semanticColorsFor(themePalette, darkTheme)

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
    LocalLineSpacingMultiplier provides lineSpacingScale.multiplier,
    LocalBoldEquations provides boldEquations,
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography =
        scaledTypography(
          scale = fontSizeScale.multiplier,
          lineSpacingMultiplier = lineSpacingScale.multiplier,
        ),
      shapes = MeritPrepShapes,
      content = content,
    )
  }
}
