package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Native Android system typography with user-configurable reading scale and line spacing
fun scaledTypography(
  scale: Float = 1.08f,
  lineSpacingMultiplier: Float = 1.44f,
): Typography {
  val s = scale.coerceIn(0.85f, 1.45f)
  val ls = lineSpacingMultiplier.coerceIn(1.20f, 1.75f)
  return Typography(
    displaySmall =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = (30f * s).sp,
        lineHeight = (36f * s).sp,
        letterSpacing = (-0.25).sp,
      ),
    headlineMedium =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = (23f * s).sp,
        lineHeight = (29f * s).sp,
        letterSpacing = (-0.15).sp,
      ),
    headlineSmall =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = (20f * s).sp,
        lineHeight = (26.5f * s).sp,
      ),
    titleLarge =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = (18.5f * s).sp,
        lineHeight = (18.5f * s * ls.coerceAtMost(1.48f)).sp,
      ),
    titleMedium =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = (16.5f * s).sp,
        lineHeight = (16.5f * s * ls.coerceAtMost(1.48f)).sp,
      ),
    titleSmall =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = (14.5f * s).sp,
        lineHeight = (14.5f * s * ls.coerceAtMost(1.48f)).sp,
      ),
    bodyLarge =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = (15.5f * s).sp,
        lineHeight = (15.5f * s * ls).sp,
      ),
    bodyMedium =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = (14.5f * s).sp,
        lineHeight = (14.5f * s * ls).sp,
      ),
    bodySmall =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = (13.2f * s).sp,
        lineHeight = (13.2f * s * ls).sp,
      ),
    labelLarge =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = (13.5f * s).sp,
        lineHeight = (19f * s).sp,
      ),
    labelMedium =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = (12.2f * s).sp,
        lineHeight = (17f * s).sp,
      ),
    labelSmall =
      TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = (11.2f * s).sp,
        lineHeight = (15f * s).sp,
      ),
  )
}

val Typography: Typography = scaledTypography(1.08f, 1.44f)
