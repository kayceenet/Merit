package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.curriculum.QuestionDiagramSpec
import com.example.ui.theme.semanticColors
import kotlin.math.cos
import kotlin.math.sin

/**
 * Clean, theme-aware vector schematic card attached to JAMB/WAEC/NECO diagram questions so candidates
 * inspect labeled circuits, ray optics, curves, bearings, Punnett grids, and supply/demand shifts
 * natively offline.
 */
@Composable
fun QuestionDiagramCard(
  spec: QuestionDiagramSpec,
  modifier: Modifier = Modifier,
) {
  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val ink = MaterialTheme.colorScheme.onSurface
  val muted = MaterialTheme.colorScheme.onSurfaceVariant
  val errorColor = MaterialTheme.colorScheme.error
  val successColor = MaterialTheme.semanticColors.success
  val textMeasurer = rememberTextMeasurer()

  val caption =
    when (spec) {
      QuestionDiagramSpec.OHMIC_SERIES_PARALLEL_CIRCUIT ->
        "Schematic: 12 V DC Source → 4 Ω Series → Parallel Branch (6 Ω || 6 Ω)"
      QuestionDiagramSpec.PROJECTILE_TRAJECTORY ->
        "Schematic: Parabolic Launch (u = 40 m/s, θ = 30°, Range R & Peak H)"
      QuestionDiagramSpec.CONVEX_LENS_RAYS ->
        "Schematic: Converging Convex Lens Ray Diagram (Object beyond 2F)"
      QuestionDiagramSpec.CIRCLE_THEOREM_CHORD ->
        "Schematic: Circle Center O (∠AOB = 108°) & Circumference Angle ∠ACB"
      QuestionDiagramSpec.BEARING_COMPASS_TRIANGLE ->
        "Schematic: Right Displacement Triangle (6 km North, 8 km East)"
      QuestionDiagramSpec.SUPPLY_DEMAND_TAX_SHIFT ->
        "Schematic: Specific Indirect Tax Shifts Supply Left (S₁ → S₂)"
      QuestionDiagramSpec.TITRATION_PH_CURVE ->
        "Schematic: Strong Acid–Strong Base Titration Curve (Eq. at 25.0 cm³)"
      QuestionDiagramSpec.ENZYME_TEMP_CURVE ->
        "Schematic: Enzyme Reaction Rate vs. Temperature (°C)"
      QuestionDiagramSpec.PUNNETT_GENETICS_GRID ->
        "Schematic: Monohybrid Punnett Matrix (Bb × Bb)"
      QuestionDiagramSpec.VERNIER_MICROMETER_SCALE ->
        "Schematic: Micrometer Screw Gauge Sleeve (4.5 mm) + Thimble (22 div)"
    }

  Column(
    modifier =
      modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.background)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
        .padding(10.dp)
        .testTag("question_diagram_${spec.name.lowercase()}"),
    verticalArrangement = Arrangement.spacedBy(6.dp),
  ) {
    Text(
      text = caption,
      style = MaterialTheme.typography.labelSmall,
      color = primary,
      fontWeight = FontWeight.Bold,
    )
    Canvas(modifier = Modifier.fillMaxWidth().height(136.dp)) {
      val w = size.width
      val h = size.height
      val labelStyle = TextStyle(color = ink, fontSize = 11.sp, fontWeight = FontWeight.Bold)
      val subStyle = TextStyle(color = muted, fontSize = 10.sp, fontWeight = FontWeight.Medium)
      val dashed = PathEffect.dashPathEffect(floatArrayOf(8f, 6f), 0f)

      fun label(txt: String, x: Float, y: Float, style: TextStyle = labelStyle) {
        drawText(textMeasurer = textMeasurer, text = txt, topLeft = Offset(x, y), style = style)
      }

      when (spec) {
        QuestionDiagramSpec.OHMIC_SERIES_PARALLEL_CIRCUIT -> {
          val topY = h * 0.25f
          val botY = h * 0.80f
          val leftX = w * 0.12f
          val rightX = w * 0.88f
          val midTopY = h * 0.42f
          val midBotY = h * 0.64f
          // Outer rails & Battery
          drawLine(ink, Offset(leftX, topY), Offset(leftX, h * 0.47f), strokeWidth = 4f)
          drawLine(ink, Offset(leftX, h * 0.56f), Offset(leftX, botY), strokeWidth = 4f)
          drawLine(primary, Offset(leftX - 16f, h * 0.47f), Offset(leftX + 16f, h * 0.47f), strokeWidth = 5f)
          drawLine(ink, Offset(leftX - 9f, h * 0.56f), Offset(leftX + 9f, h * 0.56f), strokeWidth = 6f)
          label("12V", leftX - 36f, h * 0.44f)
          // Top wire to 4 ohm series resistor
          drawLine(ink, Offset(leftX, topY), Offset(w * 0.28f, topY), strokeWidth = 4f)
          drawRect(primary.copy(alpha = 0.2f), Offset(w * 0.28f, topY - 11f), Size(w * 0.16f, 22f))
          drawRect(primary, Offset(w * 0.28f, topY - 11f), Size(w * 0.16f, 22f), style = Stroke(3f))
          label("4 Ω", w * 0.33f, topY - 28f)
          drawLine(ink, Offset(w * 0.44f, topY), Offset(w * 0.55f, topY), strokeWidth = 4f)
          // Parallel split
          drawLine(ink, Offset(w * 0.55f, midTopY), Offset(w * 0.55f, midBotY), strokeWidth = 4f)
          drawLine(ink, Offset(w * 0.55f, topY), Offset(w * 0.55f, midBotY), strokeWidth = 3f)
          drawLine(ink, Offset(w * 0.55f, midTopY), Offset(w * 0.62f, midTopY), strokeWidth = 3f)
          drawRect(secondary, Offset(w * 0.62f, midTopY - 10f), Size(w * 0.16f, 20f), style = Stroke(3f))
          label("6 Ω", w * 0.67f, midTopY - 26f)
          drawLine(ink, Offset(w * 0.78f, midTopY), Offset(rightX, midTopY), strokeWidth = 3f)
          drawLine(ink, Offset(w * 0.55f, midBotY), Offset(w * 0.62f, midBotY), strokeWidth = 3f)
          drawRect(secondary, Offset(w * 0.62f, midBotY - 10f), Size(w * 0.16f, 20f), style = Stroke(3f))
          label("6 Ω", w * 0.67f, midBotY + 12f)
          drawLine(ink, Offset(w * 0.78f, midBotY), Offset(rightX, midBotY), strokeWidth = 3f)
          drawLine(ink, Offset(rightX, midTopY), Offset(rightX, botY), strokeWidth = 4f)
          drawLine(ink, Offset(leftX, botY), Offset(rightX, botY), strokeWidth = 4f)
        }

        QuestionDiagramSpec.PROJECTILE_TRAJECTORY -> {
          val groundY = h * 0.82f
          val startX = w * 0.10f
          val endX = w * 0.88f
          drawLine(muted, Offset(startX, groundY), Offset(endX, groundY), strokeWidth = 3f)
          val arc =
            Path().apply {
              moveTo(startX, groundY)
              quadraticTo((startX + endX) / 2f, h * 0.04f, endX - 24f, groundY)
            }
          drawPath(arc, color = primary, style = Stroke(width = 5f, cap = StrokeCap.Round))
          // Velocity vector
          drawLine(errorColor, Offset(startX, groundY), Offset(startX + w * 0.17f, h * 0.34f), strokeWidth = 4f)
          label("u = 40 m/s", startX + 8f, h * 0.25f)
          label("θ = 30°", startX + 38f, groundY - 22f, subStyle)
          label("Range R = ?", (startX + endX) / 2f - 30f, groundY + 4f)
        }

        QuestionDiagramSpec.CONVEX_LENS_RAYS -> {
          val axisY = h * 0.55f
          val lensX = w * 0.55f
          val objX = w * 0.18f
          drawLine(muted, Offset(w * 0.05f, axisY), Offset(w * 0.95f, axisY), strokeWidth = 2f)
          drawOval(primary, Offset(lensX - 10f, h * 0.12f), Size(20f, h * 0.80f), style = Stroke(4f))
          // Object > 2F
          drawLine(ink, Offset(objX, axisY), Offset(objX, h * 0.26f), strokeWidth = 5f)
          label("Object (>2F)", objX - 22f, axisY + 6f, subStyle)
          label("F", w * 0.38f, axisY + 6f, subStyle)
          label("F'", w * 0.72f, axisY + 6f, subStyle)
          // Principal ray 1: parallel then through F'
          val focusX = w * 0.72f
          val imgX = w * 0.80f
          val imgTopY = h * 0.71f
          drawLine(secondary, Offset(objX, h * 0.26f), Offset(lensX, h * 0.26f), strokeWidth = 3f)
          drawLine(secondary, Offset(lensX, h * 0.26f), Offset(imgX, imgTopY), strokeWidth = 3f)
          // Ray 2: straight through optical center
          drawLine(errorColor, Offset(objX, h * 0.26f), Offset(imgX, imgTopY), strokeWidth = 2.5f)
          drawLine(successColor, Offset(imgX, axisY), Offset(imgX, imgTopY), strokeWidth = 5f)
          label("Image", imgX - 14f, axisY - 20f, subStyle)
          label("2F'", w * 0.88f, axisY + 6f, subStyle)
          drawCircle(muted, radius = 4f, center = Offset(focusX, axisY))
        }

        QuestionDiagramSpec.CIRCLE_THEOREM_CHORD -> {
          val cx = w * 0.50f
          val cy = h * 0.52f
          val r = h * 0.42f
          drawCircle(ink, radius = r, center = Offset(cx, cy), style = Stroke(3.5f))
          val pO = Offset(cx, cy)
          val pC = Offset(cx + r * cos(Math.toRadians(-72.0)).toFloat(), cy + r * sin(Math.toRadians(-72.0)).toFloat())
          val pA = Offset(cx + r * cos(Math.toRadians(145.0)).toFloat(), cy + r * sin(Math.toRadians(145.0)).toFloat())
          val pB = Offset(cx + r * cos(Math.toRadians(37.0)).toFloat(), cy + r * sin(Math.toRadians(37.0)).toFloat())
          drawLine(primary, pO, pA, strokeWidth = 3.5f)
          drawLine(primary, pO, pB, strokeWidth = 3.5f)
          drawLine(secondary, pC, pA, strokeWidth = 3f)
          drawLine(secondary, pC, pB, strokeWidth = 3f)
          drawCircle(ink, 4f, pO)
          label("O (108°)", cx - 22f, cy + 4f)
          label("C (?)", pC.x + 6f, pC.y - 12f)
          label("A", pA.x - 16f, pA.y)
          label("B", pB.x + 8f, pB.y)
        }

        QuestionDiagramSpec.BEARING_COMPASS_TRIANGLE -> {
          val ax = w * 0.30f
          val ay = h * 0.82f
          val bx = w * 0.30f
          val by = h * 0.22f
          val cx = w * 0.72f
          val cy = h * 0.22f
          drawLine(muted, Offset(ax, h * 0.08f), Offset(ax, h * 0.88f), strokeWidth = 2f, pathEffect = dashed)
          drawLine(ink, Offset(ax, ay), Offset(bx, by), strokeWidth = 4f)
          drawLine(ink, Offset(bx, by), Offset(cx, cy), strokeWidth = 4f)
          drawLine(primary, Offset(ax, ay), Offset(cx, cy), strokeWidth = 4f)
          label("6 km N", ax - 54f, h * 0.48f)
          label("8 km E", w * 0.47f, by - 18f)
          label("θ", ax + 10f, ay - 28f)
        }

        QuestionDiagramSpec.SUPPLY_DEMAND_TAX_SHIFT -> {
          val leftX = w * 0.16f
          val rightX = w * 0.86f
          val topY = h * 0.12f
          val botY = h * 0.84f
          drawLine(ink, Offset(leftX, topY), Offset(leftX, botY), strokeWidth = 3f)
          drawLine(ink, Offset(leftX, botY), Offset(rightX, botY), strokeWidth = 3f)
          // Demand D
          drawLine(primary, Offset(w * 0.24f, h * 0.20f), Offset(w * 0.80f, h * 0.76f), strokeWidth = 4f)
          label("D", w * 0.81f, h * 0.70f)
          // S1
          drawLine(secondary, Offset(w * 0.30f, h * 0.76f), Offset(w * 0.82f, h * 0.24f), strokeWidth = 4f)
          label("S₁", w * 0.82f, h * 0.18f)
          // Shifted S2 (up/left)
          drawLine(errorColor, Offset(w * 0.22f, h * 0.62f), Offset(w * 0.66f, h * 0.16f), strokeWidth = 4f)
          label("S₂ (Tax)", w * 0.60f, h * 0.08f)
        }

        QuestionDiagramSpec.TITRATION_PH_CURVE -> {
          val leftX = w * 0.14f
          val botY = h * 0.84f
          drawLine(ink, Offset(leftX, h * 0.10f), Offset(leftX, botY), strokeWidth = 3f)
          drawLine(ink, Offset(leftX, botY), Offset(w * 0.90f, botY), strokeWidth = 3f)
          val eqX = w * 0.52f
          drawLine(muted, Offset(eqX, h * 0.14f), Offset(eqX, botY), strokeWidth = 2f, pathEffect = dashed)
          val curve =
            Path().apply {
              moveTo(leftX + 6f, h * 0.76f)
              lineTo(eqX - 18f, h * 0.72f)
              lineTo(eqX, h * 0.48f)
              lineTo(eqX + 18f, h * 0.20f)
              lineTo(w * 0.86f, h * 0.16f)
            }
          drawPath(curve, primary, style = Stroke(width = 4.5f, cap = StrokeCap.Round))
          label("pH 7", leftX - 4f, h * 0.42f, subStyle)
          label("25.0 cm³", eqX - 24f, botY + 2f, subStyle)
        }

        QuestionDiagramSpec.ENZYME_TEMP_CURVE -> {
          val leftX = w * 0.14f
          val botY = h * 0.84f
          drawLine(ink, Offset(leftX, h * 0.12f), Offset(leftX, botY), strokeWidth = 3f)
          drawLine(ink, Offset(leftX, botY), Offset(w * 0.88f, botY), strokeWidth = 3f)
          val curve =
            Path().apply {
              moveTo(leftX, botY)
              quadraticTo(w * 0.42f, h * 0.48f, w * 0.54f, h * 0.18f)
              quadraticTo(w * 0.68f, h * 0.22f, w * 0.78f, botY)
            }
          drawPath(curve, secondary, style = Stroke(width = 4.5f, cap = StrokeCap.Round))
          label("38°C Opt", w * 0.45f, h * 0.04f, subStyle)
          label("Denatured (>45°C)", w * 0.62f, h * 0.56f, subStyle)
        }

        QuestionDiagramSpec.PUNNETT_GENETICS_GRID -> {
          val gx = w * 0.34f
          val gy = h * 0.22f
          val cellW = w * 0.16f
          val cellH = h * 0.34f
          drawRect(ink, Offset(gx, gy), Size(cellW * 2, cellH * 2), style = Stroke(3f))
          drawLine(ink, Offset(gx + cellW, gy), Offset(gx + cellW, gy + cellH * 2), strokeWidth = 2.5f)
          drawLine(ink, Offset(gx, gy + cellH), Offset(gx + cellW * 2, gy + cellH), strokeWidth = 2.5f)
          label("B", gx + cellW * 0.42f, gy - 20f)
          label("b", gx + cellW * 1.42f, gy - 20f)
          label("B", gx - 20f, gy + cellH * 0.30f)
          label("b", gx - 20f, gy + cellH * 1.30f)
          label("BB", gx + cellW * 0.32f, gy + cellH * 0.30f)
          label("Bb", gx + cellW * 1.32f, gy + cellH * 0.30f)
          label("Bb", gx + cellW * 0.32f, gy + cellH * 1.30f)
          label("bb", gx + cellW * 1.32f, gy + cellH * 1.30f)
        }

        QuestionDiagramSpec.VERNIER_MICROMETER_SCALE -> {
          val yMid = h * 0.50f
          // Sleeve
          drawRect(primary.copy(alpha = 0.12f), Offset(w * 0.12f, yMid - 22f), Size(w * 0.46f, 44f))
          drawRect(ink, Offset(w * 0.12f, yMid - 22f), Size(w * 0.46f, 44f), style = Stroke(3f))
          drawLine(ink, Offset(w * 0.12f, yMid), Offset(w * 0.58f, yMid), strokeWidth = 3f)
          for (i in 0..4) {
            val tickX = w * 0.18f + i * (w * 0.08f)
            drawLine(ink, Offset(tickX, yMid - 14f), Offset(tickX, yMid), strokeWidth = 2.5f)
            drawLine(ink, Offset(tickX + w * 0.04f, yMid), Offset(tickX + w * 0.04f, yMid + 12f), strokeWidth = 2f)
          }
          // Rotating Thimble
          drawRect(secondary.copy(alpha = 0.14f), Offset(w * 0.58f, yMid - 34f), Size(w * 0.24f, 68f))
          drawRect(secondary, Offset(w * 0.58f, yMid - 34f), Size(w * 0.24f, 68f), style = Stroke(3f))
          label("4.5 mm sleeve", w * 0.18f, yMid + 28f, subStyle)
          label("22 div", w * 0.62f, yMid - 8f)
        }
      }
    }
  }
}
