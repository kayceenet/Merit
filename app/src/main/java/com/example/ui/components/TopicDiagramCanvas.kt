package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.PracticalLabType
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

@Composable
fun SubjectHeroGraphic(
  subjectId: String,
  modifier: Modifier = Modifier,
) {
  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val tertiary = MaterialTheme.colorScheme.tertiary
  val onSurface = MaterialTheme.colorScheme.onSurface
  val surfaceBg = MaterialTheme.colorScheme.surfaceVariant
  val textMeasurer = rememberTextMeasurer()

  val drawableId: Int? =
    when (subjectId) {
      "physics" -> R.drawable.img_gfx_physics_concepts_1794889560041
      "mathematics" -> R.drawable.img_gfx_math_concepts_1794889561184
      "chemistry" -> R.drawable.img_gfx_chem_concepts_1794889560937
      "english" -> R.drawable.img_gfx_english_concepts_1794889563070
      else -> null
    }

  Box(
    modifier =
      modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(14.dp))
        .background(surfaceBg)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp)),
  ) {
    if (drawableId != null) {
      Image(
        painter = painterResource(id = drawableId),
        contentDescription = "$subjectId visual concept illustration",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().height(136.dp),
      )
    } else {
      // Custom 100%-accurate programmatic Biology Visual Atlas (Plant Cell • 4-Chamber Heart RA/RV/LA/LV • DNA)
      Canvas(modifier = Modifier.fillMaxWidth().height(148.dp)) {
        val w = size.width
        val h = size.height
        val labelStyle = TextStyle(color = onSurface, fontSize = 10.sp, fontWeight = FontWeight.Bold)

        // Left: Plant Cell
        val cellLeft = w * 0.04f
        val cellW = w * 0.27f
        drawRoundRect(
          color = Color(0xFF2E7D32),
          topLeft = Offset(cellLeft, h * 0.12f),
          size = Size(cellW, h * 0.68f),
          cornerRadius = CornerRadius(12f, 12f),
          style = Stroke(width = 5f),
        )
        drawRoundRect(
          color = Color(0xFF81C784).copy(alpha = 0.25f),
          topLeft = Offset(cellLeft + 6f, h * 0.12f + 6f),
          size = Size(cellW - 12f, h * 0.68f - 12f),
          cornerRadius = CornerRadius(8f, 8f),
        )
        // Large vacuole
        drawOval(
          color = Color(0xFF29B6F6).copy(alpha = 0.35f),
          topLeft = Offset(cellLeft + cellW * 0.28f, h * 0.24f),
          size = Size(cellW * 0.56f, h * 0.40f),
        )
        // Nucleus + Chloroplasts
        drawCircle(color = tertiary, radius = 13f, center = Offset(cellLeft + cellW * 0.22f, h * 0.54f))
        drawOval(color = Color(0xFF1B5E20), topLeft = Offset(cellLeft + cellW * 0.14f, h * 0.22f), size = Size(16f, 9f))
        drawOval(color = Color(0xFF1B5E20), topLeft = Offset(cellLeft + cellW * 0.64f, h * 0.66f), size = Size(16f, 9f))
        drawCenteredLabel(textMeasurer, "Plant Cell", Offset(cellLeft + cellW * 0.5f, h * 0.88f), labelStyle)

        // Center: 4-Chamber Heart accurately labeled RA, RV, LA, LV
        val hLeft = w * 0.36f
        val hW = w * 0.28f
        val midX = hLeft + hW * 0.5f
        val topY = h * 0.14f
        val boxH = h * 0.64f
        // Right side (patient's right = viewer's left, deoxygenated blue)
        drawRoundRect(
          color = Color(0xFF1565C0).copy(alpha = 0.24f),
          topLeft = Offset(hLeft, topY),
          size = Size(hW * 0.49f, boxH * 0.45f),
          cornerRadius = CornerRadius(10f, 10f),
        )
        drawRoundRect(
          color = Color(0xFF1565C0).copy(alpha = 0.38f),
          topLeft = Offset(hLeft, topY + boxH * 0.48f),
          size = Size(hW * 0.49f, boxH * 0.52f),
          cornerRadius = CornerRadius(10f, 10f),
        )
        // Left side (oxygenated red)
        drawRoundRect(
          color = Color(0xFFC62828).copy(alpha = 0.24f),
          topLeft = Offset(midX + 2f, topY),
          size = Size(hW * 0.49f, boxH * 0.45f),
          cornerRadius = CornerRadius(10f, 10f),
        )
        drawRoundRect(
          color = Color(0xFFC62828).copy(alpha = 0.42f),
          topLeft = Offset(midX + 2f, topY + boxH * 0.48f),
          size = Size(hW * 0.49f, boxH * 0.52f),
          cornerRadius = CornerRadius(10f, 10f),
        )
        val chStyle = TextStyle(color = onSurface, fontSize = 11.sp, fontWeight = FontWeight.ExtraBold)
        drawCenteredLabel(textMeasurer, "RA", Offset(hLeft + hW * 0.24f, topY + boxH * 0.22f), chStyle)
        drawCenteredLabel(textMeasurer, "RV", Offset(hLeft + hW * 0.24f, topY + boxH * 0.74f), chStyle)
        drawCenteredLabel(textMeasurer, "LA", Offset(midX + hW * 0.25f, topY + boxH * 0.22f), chStyle)
        drawCenteredLabel(textMeasurer, "LV", Offset(midX + hW * 0.25f, topY + boxH * 0.74f), chStyle)
        drawCenteredLabel(textMeasurer, "Heart (RA/RV/LA/LV)", Offset(midX, h * 0.88f), labelStyle)

        // Right: DNA Base Pairs A=T, G=C
        val dLeft = w * 0.69f
        val dW = w * 0.26f
        for (i in 0..5) {
          val t = i / 5f
          val y = h * 0.16f + t * (h * 0.58f)
          val x1 = dLeft + dW * 0.5f + sin(t * PI.toFloat() * 1.6f) * (dW * 0.34f)
          val x2 = dLeft + dW * 0.5f - sin(t * PI.toFloat() * 1.6f) * (dW * 0.34f)
          drawLine(
            color = if (i % 2 == 0) primary else secondary,
            start = Offset(x1, y),
            end = Offset(x2, y),
            strokeWidth = 3.5f,
          )
          drawCircle(color = Color(0xFF2E7D32), radius = 4.5f, center = Offset(x1, y))
          drawCircle(color = tertiary, radius = 4.5f, center = Offset(x2, y))
        }
        drawCenteredLabel(textMeasurer, "DNA (A=T, G≡C)", Offset(dLeft + dW * 0.5f, h * 0.88f), labelStyle)
      }
    }
  }
}

@Composable
fun TopicDiagramCard(
  lesson: Lesson,
  modifier: Modifier = Modifier,
) {
  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val tertiary = MaterialTheme.colorScheme.tertiary
  val errorColor = MaterialTheme.colorScheme.error
  val onSurface = MaterialTheme.colorScheme.onSurface
  val outline = MaterialTheme.colorScheme.outlineVariant
  val textMeasurer = rememberTextMeasurer()

  Column(
    modifier =
      modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, outline, RoundedCornerShape(16.dp))
        .padding(14.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(6.dp),
      ) {
        Text(
          text = "VISUAL CONCEPT DIAGRAM",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSecondaryContainer,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
        )
      }
      Text(
        text = lesson.labType.title.take(28),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }

    Canvas(modifier = Modifier.fillMaxWidth().height(152.dp)) {
      val w = size.width
      val h = size.height
      val labelStyle = TextStyle(color = onSurface, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
      val accentStyle = TextStyle(color = primary, fontSize = 11.sp, fontWeight = FontWeight.Bold)

      when (lesson.labType) {
        PracticalLabType.MEASUREMENT_VECTOR_LAB -> {
          // Resultant Triangle F1 + F2 = R
          val o = Offset(w * 0.14f, h * 0.78f)
          val p1 = Offset(w * 0.62f, h * 0.78f)
          val p2 = Offset(w * 0.34f, h * 0.24f)
          val rEnd = Offset(w * 0.82f, h * 0.24f)
          drawLine(primary, o, p1, strokeWidth = 5f)
          drawLine(secondary, o, p2, strokeWidth = 5f)
          drawLine(outline, p1, rEnd, strokeWidth = 2.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f)))
          drawLine(outline, p2, rEnd, strokeWidth = 2.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f)))
          drawLine(tertiary, o, rEnd, strokeWidth = 6f)
          drawCenteredLabel(textMeasurer, "F₁ (Horizontal)", Offset(w * 0.38f, h * 0.88f), labelStyle)
          drawCenteredLabel(textMeasurer, "F₂", Offset(w * 0.19f, h * 0.48f), labelStyle)
          drawCenteredLabel(textMeasurer, "Resultant R = √(F₁²+F₂²+2F₁F₂cosθ)", Offset(w * 0.56f, h * 0.13f), accentStyle)
        }

        PracticalLabType.PROJECTILE_KINEMATICS -> {
          val groundY = h * 0.82f
          drawLine(outline, Offset(w * 0.08f, groundY), Offset(w * 0.92f, groundY), strokeWidth = 3f)
          val path = Path().apply {
            moveTo(w * 0.12f, groundY)
            for (i in 1..40) {
              val t = i / 40f
              val x = w * 0.12f + t * w * 0.76f
              val y = groundY - 4f * t * (1f - t) * (h * 0.62f)
              lineTo(x, y)
            }
          }
          drawPath(path, primary, style = Stroke(width = 5f))
          drawLine(secondary, Offset(w * 0.5f, groundY), Offset(w * 0.5f, groundY - h * 0.62f), strokeWidth = 2.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f)))
          drawCircle(tertiary, radius = 8f, center = Offset(w * 0.5f, groundY - h * 0.62f))
          drawCenteredLabel(textMeasurer, "v_y = 0 at H_max", Offset(w * 0.5f, h * 0.10f), accentStyle)
          drawCenteredLabel(textMeasurer, "Range R = u² sin(2θ) / g", Offset(w * 0.5f, h * 0.91f), labelStyle)
        }

        PracticalLabType.TORQUE_MACHINES_LAB -> {
          val pivot = Offset(w * 0.45f, h * 0.72f)
          drawLine(primary, Offset(w * 0.14f, h * 0.52f), Offset(w * 0.86f, h * 0.52f), strokeWidth = 8f)
          drawCircle(tertiary, radius = 10f, center = pivot)
          drawRect(secondary, topLeft = Offset(w * 0.16f, h * 0.30f), size = Size(44f, 36f))
          drawRect(primary, topLeft = Offset(w * 0.76f, h * 0.35f), size = Size(32f, 28f))
          drawCenteredLabel(textMeasurer, "F₁·d₁ (ACW)", Offset(w * 0.25f, h * 0.16f), labelStyle)
          drawCenteredLabel(textMeasurer, "Pivot", Offset(w * 0.45f, h * 0.86f), accentStyle)
          drawCenteredLabel(textMeasurer, "F₂·d₂ (CW)", Offset(w * 0.76f, h * 0.18f), labelStyle)
        }

        PracticalLabType.SHM_FLUIDS_LAB -> {
          val top = Offset(w * 0.5f, h * 0.10f)
          drawLine(outline, Offset(w * 0.25f, h * 0.10f), Offset(w * 0.75f, h * 0.10f), strokeWidth = 5f)
          drawLine(outline, top, Offset(w * 0.5f, h * 0.75f), strokeWidth = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f)))
          val bob = Offset(w * 0.70f, h * 0.68f)
          drawLine(primary, top, bob, strokeWidth = 4f)
          drawCircle(secondary, radius = 16f, center = bob)
          drawCenteredLabel(textMeasurer, "v = max at center (x = 0)", Offset(w * 0.45f, h * 0.85f), labelStyle)
          drawCenteredLabel(textMeasurer, "T = 2π√(L/g)", Offset(w * 0.74f, h * 0.26f), accentStyle)
        }

        PracticalLabType.THERMAL_EXPANSION_CALORIMETRY -> {
          // Heating Curve Ice -> Water -> Steam plateaus
          val pts = listOf(
            Offset(w * 0.10f, h * 0.82f),
            Offset(w * 0.24f, h * 0.62f),
            Offset(w * 0.44f, h * 0.62f),
            Offset(w * 0.60f, h * 0.32f),
            Offset(w * 0.80f, h * 0.32f),
            Offset(w * 0.92f, h * 0.16f),
          )
          for (i in 0 until pts.lastIndex) {
            drawLine(
              color = if (i == 1 || i == 3) tertiary else primary,
              start = pts[i],
              end = pts[i + 1],
              strokeWidth = 5f,
            )
          }
          drawCenteredLabel(textMeasurer, "0°C Melt (mL_f)", Offset(w * 0.34f, h * 0.74f), labelStyle)
          drawCenteredLabel(textMeasurer, "100°C Boil (mL_v)", Offset(w * 0.70f, h * 0.20f), accentStyle)
        }

        PracticalLabType.WAVE_OPTICS_BENCH -> {
          val midY = h * 0.52f
          val lensX = w * 0.52f
          drawLine(outline, Offset(w * 0.08f, midY), Offset(w * 0.92f, midY), strokeWidth = 2.5f)
          drawOval(secondary.copy(alpha = 0.28f), topLeft = Offset(lensX - 10f, h * 0.14f), size = Size(20f, h * 0.76f))
          drawLine(primary, Offset(w * 0.14f, h * 0.26f), Offset(lensX, h * 0.26f), strokeWidth = 4f)
          drawLine(primary, Offset(lensX, h * 0.26f), Offset(w * 0.84f, h * 0.70f), strokeWidth = 4f)
          drawLine(tertiary, Offset(w * 0.14f, h * 0.26f), Offset(w * 0.84f, h * 0.70f), strokeWidth = 3f)
          drawCenteredLabel(textMeasurer, "Object (u)", Offset(w * 0.16f, h * 0.86f), labelStyle)
          drawCenteredLabel(textMeasurer, "Convex Lens", Offset(lensX, h * 0.08f), accentStyle)
          drawCenteredLabel(textMeasurer, "Real Image (v)", Offset(w * 0.82f, h * 0.86f), labelStyle)
        }

        PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB, PracticalLabType.OHMS_CIRCUIT, PracticalLabType.EM_TRANSFORMER_AC_LAB -> {
          drawRect(primary, topLeft = Offset(w * 0.18f, h * 0.24f), size = Size(w * 0.64f, h * 0.52f), style = Stroke(4f))
          drawRect(Color(0xFFFFFFFF), topLeft = Offset(w * 0.36f, h * 0.16f), size = Size(w * 0.28f, h * 0.16f))
          drawRect(secondary, topLeft = Offset(w * 0.36f, h * 0.16f), size = Size(w * 0.28f, h * 0.16f), style = Stroke(4f))
          drawCenteredLabel(
            textMeasurer,
            when (lesson.labType) {
              PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB -> "C = ε₀A / d  (Parallel vs Series)"
              PracticalLabType.EM_TRANSFORMER_AC_LAB -> "V_s / V_p = N_s / N_p  |  Z = √(R² + (X_L−X_C)²)"
              else -> "EMF E = I(R + r)  •  V_term = IR"
            },
            Offset(w * 0.5f, h * 0.88f),
            accentStyle,
          )
          drawCenteredLabel(textMeasurer, "Load / Coil", Offset(w * 0.5f, h * 0.24f), labelStyle)
        }

        PracticalLabType.QUANTUM_NUCLEAR_LAB -> {
          val path = Path().apply {
            moveTo(w * 0.10f, h * 0.18f)
            for (i in 1..30) {
              val t = i / 30f
              val y = h * 0.82f - (0.5f.pow(t * 3.5f)) * h * 0.64f
              lineTo(w * 0.10f + t * w * 0.80f, y)
            }
          }
          drawPath(path, primary, style = Stroke(5f))
          drawCircle(tertiary, radius = 7f, center = Offset(w * 0.33f, h * 0.82f - 0.5f * h * 0.64f))
          drawCenteredLabel(textMeasurer, "N₀/2 at 1 t½", Offset(w * 0.42f, h * 0.40f), accentStyle)
          drawCenteredLabel(textMeasurer, "N = N₀(1/2)ⁿ  •  E_k(max) = hf − W₀", Offset(w * 0.50f, h * 0.90f), labelStyle)
        }

        PracticalLabType.SETS_VENN_LAB -> {
          drawCircle(primary.copy(alpha = 0.25f), radius = h * 0.36f, center = Offset(w * 0.40f, h * 0.48f))
          drawCircle(primary, radius = h * 0.36f, center = Offset(w * 0.40f, h * 0.48f), style = Stroke(4f))
          drawCircle(secondary.copy(alpha = 0.25f), radius = h * 0.36f, center = Offset(w * 0.60f, h * 0.48f))
          drawCircle(secondary, radius = h * 0.36f, center = Offset(w * 0.60f, h * 0.48f), style = Stroke(4f))
          drawCenteredLabel(textMeasurer, "A only", Offset(w * 0.30f, h * 0.48f), labelStyle)
          drawCenteredLabel(textMeasurer, "A ∩ B", Offset(w * 0.50f, h * 0.48f), accentStyle)
          drawCenteredLabel(textMeasurer, "B only", Offset(w * 0.70f, h * 0.48f), labelStyle)
        }

        PracticalLabType.POLYGON_CIRCLE_THEOREM_LAB -> {
          val c = Offset(w * 0.50f, h * 0.52f)
          val r = h * 0.40f
          drawCircle(outline, radius = r, center = c, style = Stroke(4f))
          val a = Offset(c.x - r * 0.78f, c.y + r * 0.62f)
          val b = Offset(c.x + r * 0.78f, c.y + r * 0.62f)
          val top = Offset(c.x, c.y - r)
          drawLine(primary, a, top, strokeWidth = 4f)
          drawLine(primary, b, top, strokeWidth = 4f)
          drawLine(tertiary, a, c, strokeWidth = 4f)
          drawLine(tertiary, b, c, strokeWidth = 4f)
          drawCircle(tertiary, radius = 6f, center = c)
          drawCenteredLabel(textMeasurer, "θ (Circumference)", Offset(w * 0.50f, h * 0.08f), labelStyle)
          drawCenteredLabel(textMeasurer, "2θ at Center O", Offset(w * 0.50f, h * 0.62f), accentStyle)
        }

        PracticalLabType.TRIG_BEARING_LAB -> {
          val c = Offset(w * 0.35f, h * 0.54f)
          val r = h * 0.36f
          drawCircle(outline, radius = r, center = c, style = Stroke(3f))
          drawLine(outline, Offset(c.x, c.y - r), Offset(c.x, c.y + r), strokeWidth = 2f)
          drawLine(outline, Offset(c.x - r, c.y), Offset(c.x + r, c.y), strokeWidth = 2f)
          val p = Offset(c.x + r * cos(0.75f), c.y - r * sin(0.75f))
          drawLine(primary, c, p, strokeWidth = 5f)
          drawCenteredLabel(textMeasurer, "N (000°)", Offset(c.x, h * 0.08f), accentStyle)
          drawCenteredLabel(textMeasurer, "Clockwise Bearing θ", Offset(w * 0.74f, h * 0.38f), labelStyle)
          drawCenteredLabel(textMeasurer, "Back = θ ± 180°", Offset(w * 0.74f, h * 0.58f), accentStyle)
        }

        PracticalLabType.QUADRATIC_TANGENT, PracticalLabType.COORDINATE_CALCULUS_LAB -> {
          drawLine(outline, Offset(w * 0.1f, h * 0.75f), Offset(w * 0.9f, h * 0.75f), strokeWidth = 2.5f)
          val curve = Path().apply {
            moveTo(w * 0.16f, h * 0.16f)
            for (i in 1..40) {
              val t = i / 40f
              val x = w * 0.16f + t * w * 0.68f
              val u = (t - 0.5f) * 2f
              val y = h * 0.80f - (1f - u * u) * h * 0.56f
              lineTo(x, y)
            }
          }
          drawPath(curve, primary, style = Stroke(5f))
          drawLine(tertiary, Offset(w * 0.30f, h * 0.24f), Offset(w * 0.70f, h * 0.24f), strokeWidth = 3.5f)
          drawCenteredLabel(textMeasurer, "Vertex / Stationary dy/dx = 0", Offset(w * 0.50f, h * 0.12f), accentStyle)
          drawCenteredLabel(textMeasurer, "Roots r₁, r₂ (Δ = b² − 4ac)", Offset(w * 0.50f, h * 0.89f), labelStyle)
        }

        PracticalLabType.SEPARATION_ATOMIC_LAB, PracticalLabType.BONDING_PERIODIC_LAB -> {
          val c = Offset(w * 0.30f, h * 0.50f)
          drawCircle(primary.copy(alpha = 0.2f), radius = 14f, center = c)
          drawCircle(primary, radius = 34f, center = c, style = Stroke(3f))
          drawCircle(secondary, radius = 56f, center = c, style = Stroke(3f))
          drawCircle(tertiary, radius = 6f, center = Offset(c.x + 34f, c.y))
          drawCircle(tertiary, radius = 6f, center = Offset(c.x - 34f, c.y))
          drawCircle(primary, radius = 6f, center = Offset(c.x, c.y - 56f))
          drawCenteredLabel(textMeasurer, "K(2) • L(8) • M(8) • N(2)", Offset(w * 0.72f, h * 0.34f), accentStyle)
          drawCenteredLabel(textMeasurer, "Electronegativity & Shell Orbitals", Offset(w * 0.72f, h * 0.58f), labelStyle)
        }

        PracticalLabType.TITRATION_PH -> {
          // S-curve titration jump at pH 7
          drawLine(outline, Offset(w * 0.12f, h * 0.50f), Offset(w * 0.90f, h * 0.50f), strokeWidth = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f)))
          val p = Path().apply {
            moveTo(w * 0.14f, h * 0.80f)
            lineTo(w * 0.45f, h * 0.75f)
            lineTo(w * 0.50f, h * 0.22f)
            lineTo(w * 0.86f, h * 0.16f)
          }
          drawPath(p, primary, style = Stroke(5f))
          drawCircle(tertiary, radius = 8f, center = Offset(w * 0.475f, h * 0.49f))
          drawCenteredLabel(textMeasurer, "Acid pH < 7", Offset(w * 0.28f, h * 0.65f), labelStyle)
          drawCenteredLabel(textMeasurer, "Equivalence Jump", Offset(w * 0.70f, h * 0.48f), accentStyle)
          drawCenteredLabel(textMeasurer, "Alkaline pH > 7", Offset(w * 0.72f, h * 0.28f), labelStyle)
        }

        PracticalLabType.ENERGETICS_KINETICS_LAB -> {
          val p = Path().apply {
            moveTo(w * 0.10f, h * 0.42f)
            lineTo(w * 0.30f, h * 0.42f)
            quadraticTo(w * 0.48f, h * 0.04f, w * 0.66f, h * 0.74f)
            lineTo(w * 0.88f, h * 0.74f)
          }
          drawPath(p, primary, style = Stroke(5f))
          val cat = Path().apply {
            moveTo(w * 0.30f, h * 0.42f)
            quadraticTo(w * 0.48f, h * 0.22f, w * 0.66f, h * 0.74f)
          }
          drawPath(cat, secondary, style = Stroke(3.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f))))
          drawCenteredLabel(textMeasurer, "Catalyst lowers Eₐ (ΔH unchanged!)", Offset(w * 0.56f, h * 0.14f), accentStyle)
          drawCenteredLabel(textMeasurer, "Products (ΔH < 0 Exothermic)", Offset(w * 0.72f, h * 0.86f), labelStyle)
        }

        PracticalLabType.BIOENERGETICS_ENZYME_LAB -> {
          val bell = Path().apply {
            moveTo(w * 0.12f, h * 0.80f)
            quadraticTo(w * 0.50f, h * 0.02f, w * 0.68f, h * 0.44f)
            lineTo(w * 0.78f, h * 0.82f)
          }
          drawPath(bell, primary, style = Stroke(5f))
          drawCircle(tertiary, radius = 7f, center = Offset(w * 0.46f, h * 0.28f))
          drawCenteredLabel(textMeasurer, "Optimum ~37°C", Offset(w * 0.46f, h * 0.14f), accentStyle)
          drawCenteredLabel(textMeasurer, "Denatured >50°C", Offset(w * 0.80f, h * 0.56f), TextStyle(color = errorColor, fontSize = 10.sp, fontWeight = FontWeight.Bold))
        }

        PracticalLabType.MENDELIAN_GENETICS -> {
          val gx = w * 0.32f
          val gy = h * 0.16f
          val cell = w * 0.18f
          drawRect(outline, topLeft = Offset(gx, gy), size = Size(cell * 2, 92f), style = Stroke(3f))
          drawLine(outline, Offset(gx + cell, gy), Offset(gx + cell, gy + 92f), strokeWidth = 2.5f)
          drawLine(outline, Offset(gx, gy + 46f), Offset(gx + cell * 2, gy + 46f), strokeWidth = 2.5f)
          drawCenteredLabel(textMeasurer, "AA (25%)", Offset(gx + cell * 0.5f, gy + 23f), accentStyle)
          drawCenteredLabel(textMeasurer, "AS (25%)", Offset(gx + cell * 1.5f, gy + 23f), labelStyle)
          drawCenteredLabel(textMeasurer, "AS (25%)", Offset(gx + cell * 0.5f, gy + 69f), labelStyle)
          drawCenteredLabel(textMeasurer, "SS (25%)", Offset(gx + cell * 1.5f, gy + 69f), TextStyle(color = errorColor, fontSize = 10.sp, fontWeight = FontWeight.Bold))
          drawCenteredLabel(textMeasurer, "AS × AS Punnett Grid", Offset(w * 0.50f, h * 0.88f), labelStyle)
        }

        PracticalLabType.ECOLOGY_ENERGY_PYRAMID -> {
          val tiers = listOf("10 kJ Hawk (3°)" to 0.24f, "100 kJ Lizard (2°)" to 0.42f, "1,000 kJ Locust (1°)" to 0.60f, "10,000 kJ Grass (Prod)" to 0.78f)
          tiers.forEachIndexed { i, (txt, frac) ->
            val bw = w * frac
            val x = (w - bw) / 2f
            val y = h * 0.10f + i * 32f
            drawRoundRect(primary.copy(alpha = 0.2f + i * 0.18f), topLeft = Offset(x, y), size = Size(bw, 26f), cornerRadius = CornerRadius(6f, 6f))
            drawCenteredLabel(textMeasurer, txt, Offset(w * 0.5f, y + 13f), labelStyle)
          }
        }

        PracticalLabType.ORAL_ENGLISH_STRESS -> {
          // Stress mountain peaks
          val xs = listOf(0.20f, 0.40f, 0.62f, 0.82f)
          val hs = listOf(0.35f, 0.35f, 0.85f, 0.30f)
          val syls = listOf("in", "for", "MA ★", "tion")
          xs.forEachIndexed { idx, fx ->
            val bh = h * hs[idx] * 0.72f
            drawRoundRect(
              color = if (idx == 2) primary else secondary.copy(alpha = 0.35f),
              topLeft = Offset(w * fx - 24f, h * 0.76f - bh),
              size = Size(48f, bh),
              cornerRadius = CornerRadius(8f, 8f),
            )
            drawCenteredLabel(textMeasurer, syls[idx], Offset(w * fx, h * 0.88f), if (idx == 2) accentStyle else labelStyle)
          }
        }

        else -> {
          // Clean general visual framework diagram tailored with lesson title
          drawRoundRect(primary.copy(alpha = 0.14f), topLeft = Offset(w * 0.08f, h * 0.18f), size = Size(w * 0.24f, h * 0.52f), cornerRadius = CornerRadius(12f, 12f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.38f, h * 0.18f), size = Size(w * 0.24f, h * 0.52f), cornerRadius = CornerRadius(12f, 12f))
          drawRoundRect(tertiary.copy(alpha = 0.18f), topLeft = Offset(w * 0.68f, h * 0.18f), size = Size(w * 0.24f, h * 0.52f), cornerRadius = CornerRadius(12f, 12f))
          drawLine(primary, Offset(w * 0.32f, h * 0.44f), Offset(w * 0.38f, h * 0.44f), strokeWidth = 4f)
          drawLine(secondary, Offset(w * 0.62f, h * 0.44f), Offset(w * 0.68f, h * 0.44f), strokeWidth = 4f)
          drawCenteredLabel(textMeasurer, "1. Core Rule", Offset(w * 0.20f, h * 0.44f), labelStyle)
          drawCenteredLabel(textMeasurer, "2. Mechanism", Offset(w * 0.50f, h * 0.44f), accentStyle)
          drawCenteredLabel(textMeasurer, "3. Exam Output", Offset(w * 0.80f, h * 0.44f), labelStyle)
          drawCenteredLabel(textMeasurer, lesson.title.take(44), Offset(w * 0.50f, h * 0.86f), labelStyle)
        }
      }
    }
  }
}

private fun DrawScope.drawCenteredLabel(
  textMeasurer: TextMeasurer,
  text: String,
  center: Offset,
  style: TextStyle,
) {
  val measured = textMeasurer.measure(text, style = style)
  val x = (center.x - measured.size.width / 2f).coerceIn(4f, (size.width - measured.size.width - 4f).coerceAtLeast(4f))
  val y = (center.y - measured.size.height / 2f).coerceIn(2f, (size.height - measured.size.height - 2f).coerceAtLeast(2f))
  drawText(textLayoutResult = measured, topLeft = Offset(x, y))
}
