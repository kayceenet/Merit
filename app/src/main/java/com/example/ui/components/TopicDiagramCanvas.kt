package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.PracticalLabType
import com.example.ui.theme.semanticColors
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

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
  val surfaceBg = MaterialTheme.colorScheme.surface
  val outline = MaterialTheme.colorScheme.outline
  val isDark = surfaceBg.luminance() < 0.5f
  val successColor = MaterialTheme.semanticColors.success
  val textMeasurer = rememberTextMeasurer()

  Column(
    modifier =
      modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(surfaceBg)
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
          text = "CONCEPT SCHEMATIC",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSecondaryContainer,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
        )
      }
      Text(
        text = lesson.syllabusTag,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 8.dp),
      )
    }

    Canvas(modifier = Modifier.fillMaxWidth().height(160.dp)) {
      val w = size.width
      val h = size.height
      val labelStyle = TextStyle(color = onSurface, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
      val accentStyle = TextStyle(color = primary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
      val errStyle = TextStyle(color = errorColor, fontSize = 10.sp, fontWeight = FontWeight.Bold)

      // 1. Dispatch exact lesson-level schematics where multiple lessons share a lab domain
      when (lesson.id) {
        "phys_01_units" -> {
          // Micrometer Screw Gauge & Vernier Schematic
          drawRect(primary.copy(alpha = 0.14f), topLeft = Offset(w * 0.10f, h * 0.34f), size = Size(w * 0.42f, h * 0.24f))
          drawRect(primary, topLeft = Offset(w * 0.10f, h * 0.34f), size = Size(w * 0.42f, h * 0.24f), style = Stroke(3f))
          drawLine(onSurface, Offset(w * 0.10f, h * 0.46f), Offset(w * 0.52f, h * 0.46f), strokeWidth = 2.5f)
          for (i in 0..7) {
            val x = w * 0.14f + i * (w * 0.048f)
            drawLine(onSurface, Offset(x, h * 0.34f), Offset(x, h * 0.46f), strokeWidth = 2f)
            if (i < 7) {
              drawLine(secondary, Offset(x + w * 0.024f, h * 0.46f), Offset(x + w * 0.024f, h * 0.58f), strokeWidth = 1.5f)
            }
          }
          // Rotating Thimble
          drawRoundRect(
            color = secondary.copy(alpha = 0.22f),
            topLeft = Offset(w * 0.52f, h * 0.26f),
            size = Size(w * 0.36f, h * 0.40f),
            cornerRadius = CornerRadius(10f, 10f),
          )
          drawRoundRect(
            color = secondary,
            topLeft = Offset(w * 0.52f, h * 0.26f),
            size = Size(w * 0.36f, h * 0.40f),
            cornerRadius = CornerRadius(10f, 10f),
            style = Stroke(3f),
          )
          for (j in 0..4) {
            val y = h * 0.32f + j * (h * 0.07f)
            drawLine(onSurface, Offset(w * 0.52f, y), Offset(w * 0.61f, y), strokeWidth = 2f)
          }
          drawCenteredLabel(textMeasurer, "Main Sleeve (0.5 mm marks)", Offset(w * 0.31f, h * 0.20f), labelStyle)
          drawCenteredLabel(textMeasurer, "Thimble (0.01 mm)", Offset(w * 0.70f, h * 0.15f), accentStyle)
          drawCenteredLabel(textMeasurer, "True = Observed − (±Zero Error)", Offset(w * 0.50f, h * 0.84f), accentStyle)
          return@Canvas
        }

        "phys_05_work_machines" -> {
          // Pulley / Machine Effort vs Load Schematic
          val center = Offset(w * 0.50f, h * 0.24f)
          drawCircle(outline, radius = 20f, center = center, style = Stroke(4f))
          drawLine(primary, Offset(w * 0.36f, h * 0.24f), Offset(w * 0.36f, h * 0.66f), strokeWidth = 4f)
          drawLine(secondary, Offset(w * 0.64f, h * 0.24f), Offset(w * 0.64f, h * 0.58f), strokeWidth = 4f)
          drawRoundRect(primary.copy(alpha = 0.25f), topLeft = Offset(w * 0.29f, h * 0.66f), size = Size(w * 0.14f, 28f), cornerRadius = CornerRadius(6f, 6f))
          drawCircle(tertiary, radius = 9f, center = Offset(w * 0.64f, h * 0.61f))
          drawCenteredLabel(textMeasurer, "Load (L)", Offset(w * 0.36f, h * 0.75f), labelStyle)
          drawCenteredLabel(textMeasurer, "Effort (E)", Offset(w * 0.68f, h * 0.61f), labelStyle)
          drawCenteredLabel(textMeasurer, "Efficiency = (MA / VR) × 100%", Offset(w * 0.50f, h * 0.90f), accentStyle)
          return@Canvas
        }

        "phys_07_fluids_elasticity" -> {
          // Archimedes Submerged Body + Upthrust vs Weight
          drawRoundRect(secondary.copy(alpha = 0.16f), topLeft = Offset(w * 0.25f, h * 0.24f), size = Size(w * 0.50f, h * 0.58f), cornerRadius = CornerRadius(8f, 8f))
          drawRect(outline, topLeft = Offset(w * 0.25f, h * 0.24f), size = Size(w * 0.50f, h * 0.58f), style = Stroke(3f))
          drawRoundRect(primary.copy(alpha = 0.45f), topLeft = Offset(w * 0.43f, h * 0.40f), size = Size(w * 0.14f, 34f), cornerRadius = CornerRadius(6f, 6f))
          drawLine(tertiary, Offset(w * 0.50f, h * 0.40f), Offset(w * 0.50f, h * 0.16f), strokeWidth = 4.5f)
          drawLine(errorColor, Offset(w * 0.50f, h * 0.62f), Offset(w * 0.50f, h * 0.80f), strokeWidth = 4.5f)
          drawCenteredLabel(textMeasurer, "Upthrust U = ρVg ↑", Offset(w * 0.50f, h * 0.10f), accentStyle)
          drawCenteredLabel(textMeasurer, "Weight W = mg ↓", Offset(w * 0.50f, h * 0.90f), errStyle)
          return@Canvas
        }

        "phys_08_heat_expansion" -> {
          // Bimetallic Strip Differential Expansion on Heating
          drawRoundRect(primary.copy(alpha = 0.65f), topLeft = Offset(w * 0.18f, h * 0.34f), size = Size(w * 0.64f, 14f), cornerRadius = CornerRadius(4f, 4f))
          drawRoundRect(tertiary.copy(alpha = 0.70f), topLeft = Offset(w * 0.18f, h * 0.48f), size = Size(w * 0.64f, 14f), cornerRadius = CornerRadius(4f, 4f))
          drawCenteredLabel(textMeasurer, "Brass (α = 1.9×10⁻⁵ K⁻¹ — expands more)", Offset(w * 0.50f, h * 0.22f), accentStyle)
          drawCenteredLabel(textMeasurer, "Steel (α = 1.2×10⁻⁵ K⁻¹)", Offset(w * 0.50f, h * 0.66f), labelStyle)
          drawCenteredLabel(textMeasurer, "ΔL = L₀ α Δθ  (Brass bends on outside when heated)", Offset(w * 0.50f, h * 0.86f), accentStyle)
          return@Canvas
        }

        "phys_10_waves_sound" -> {
          // Closed vs Open Pipe Harmonics Standing Wave
          drawRect(outline, topLeft = Offset(w * 0.14f, h * 0.24f), size = Size(w * 0.72f, h * 0.42f), style = Stroke(3f))
          drawLine(onSurface, Offset(w * 0.14f, h * 0.24f), Offset(w * 0.14f, h * 0.66f), strokeWidth = 6f)
          val upper = Path().apply {
            moveTo(w * 0.14f, h * 0.45f)
            quadraticTo(w * 0.50f, h * 0.22f, w * 0.86f, h * 0.24f)
          }
          val lower = Path().apply {
            moveTo(w * 0.14f, h * 0.45f)
            quadraticTo(w * 0.50f, h * 0.68f, w * 0.86f, h * 0.66f)
          }
          drawPath(upper, primary, style = Stroke(4f))
          drawPath(lower, secondary, style = Stroke(4f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f))))
          drawCircle(tertiary, radius = 7f, center = Offset(w * 0.14f, h * 0.45f))
          drawCenteredLabel(textMeasurer, "Node (N) at Closed End", Offset(w * 0.26f, h * 0.14f), labelStyle)
          drawCenteredLabel(textMeasurer, "Antinode (A)", Offset(w * 0.80f, h * 0.14f), accentStyle)
          drawCenteredLabel(textMeasurer, "Closed Pipe L = λ/4 (Odd f, 3f, 5f)  |  Open L = λ/2", Offset(w * 0.50f, h * 0.84f), accentStyle)
          return@Canvas
        }

        "chem_01_separation_kinetic" -> {
          // Chromatography Strip Rf = dy / ds
          drawRoundRect(outline.copy(alpha = 0.14f), topLeft = Offset(w * 0.22f, h * 0.14f), size = Size(w * 0.56f, h * 0.64f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(outline, topLeft = Offset(w * 0.22f, h * 0.14f), size = Size(w * 0.56f, h * 0.64f), cornerRadius = CornerRadius(10f, 10f), style = Stroke(3f))
          drawLine(onSurface, Offset(w * 0.26f, h * 0.66f), Offset(w * 0.74f, h * 0.66f), strokeWidth = 2.5f)
          drawLine(secondary, Offset(w * 0.26f, h * 0.24f), Offset(w * 0.74f, h * 0.24f), strokeWidth = 2.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f)))
          drawCircle(primary, radius = 9f, center = Offset(w * 0.40f, h * 0.40f))
          drawCircle(tertiary, radius = 9f, center = Offset(w * 0.60f, h * 0.52f))
          drawCenteredLabel(textMeasurer, "Solvent Front", Offset(w * 0.50f, h * 0.12f), accentStyle)
          drawCenteredLabel(textMeasurer, "Baseline", Offset(w * 0.50f, h * 0.72f), labelStyle)
          drawCenteredLabel(textMeasurer, "R_f = Dye Distance ÷ Solvent Front Distance (< 1)", Offset(w * 0.50f, h * 0.89f), accentStyle)
          return@Canvas
        }

        "bio_01_diversity_microscopy" -> {
          // Compound Microscope Optical Path & Magnification Triangle
          drawRoundRect(primary.copy(alpha = 0.22f), topLeft = Offset(w * 0.22f, h * 0.18f), size = Size(w * 0.56f, h * 0.20f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "Image / Drawing Size (I)", Offset(w * 0.50f, h * 0.28f), accentStyle)
          drawRoundRect(secondary.copy(alpha = 0.22f), topLeft = Offset(w * 0.14f, h * 0.44f), size = Size(w * 0.34f, h * 0.22f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "Actual Size (A)", Offset(w * 0.31f, h * 0.55f), labelStyle)
          drawRoundRect(tertiary.copy(alpha = 0.22f), topLeft = Offset(w * 0.52f, h * 0.44f), size = Size(w * 0.34f, h * 0.22f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "Magnification (M)", Offset(w * 0.69f, h * 0.55f), labelStyle)
          drawCenteredLabel(textMeasurer, "M = Eyepiece × Objective  |  1 mm = 1,000 μm", Offset(w * 0.50f, h * 0.82f), accentStyle)
          return@Canvas
        }

        "bio_04_nutrition_digestion", "bio_05_transport_circulation" -> {
          // 4-Chamber Heart Diagram (RA, RV, LA, LV)
          val deoxyCol = if (isDark) Color(0xFF60A5FA) else Color(0xFF1565C0)
          val oxyCol = if (isDark) Color(0xFFF87171) else Color(0xFFC62828)
          val hLeft = w * 0.24f
          val hW = w * 0.52f
          val midX = hLeft + hW * 0.5f
          val topY = h * 0.12f
          val boxH = h * 0.62f
          drawRoundRect(deoxyCol.copy(alpha = 0.24f), topLeft = Offset(hLeft, topY), size = Size(hW * 0.48f, boxH * 0.46f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(deoxyCol, topLeft = Offset(hLeft, topY), size = Size(hW * 0.48f, boxH * 0.46f), cornerRadius = CornerRadius(10f, 10f), style = Stroke(2.5f))
          drawRoundRect(deoxyCol.copy(alpha = 0.34f), topLeft = Offset(hLeft, topY + boxH * 0.50f), size = Size(hW * 0.48f, boxH * 0.50f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(deoxyCol, topLeft = Offset(hLeft, topY + boxH * 0.50f), size = Size(hW * 0.48f, boxH * 0.50f), cornerRadius = CornerRadius(10f, 10f), style = Stroke(3f))
          drawRoundRect(oxyCol.copy(alpha = 0.24f), topLeft = Offset(midX + 4f, topY), size = Size(hW * 0.48f, boxH * 0.46f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(oxyCol, topLeft = Offset(midX + 4f, topY), size = Size(hW * 0.48f, boxH * 0.46f), cornerRadius = CornerRadius(10f, 10f), style = Stroke(2.5f))
          drawRoundRect(oxyCol.copy(alpha = 0.34f), topLeft = Offset(midX + 4f, topY + boxH * 0.50f), size = Size(hW * 0.48f, boxH * 0.50f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(oxyCol, topLeft = Offset(midX + 4f, topY + boxH * 0.50f), size = Size(hW * 0.48f, boxH * 0.50f), cornerRadius = CornerRadius(10f, 10f), style = Stroke(3.5f))
          drawCenteredLabel(textMeasurer, "RA (Deoxy)", Offset(hLeft + hW * 0.24f, topY + boxH * 0.23f), labelStyle)
          drawCenteredLabel(textMeasurer, "RV → Lungs", Offset(hLeft + hW * 0.24f, topY + boxH * 0.75f), labelStyle)
          drawCenteredLabel(textMeasurer, "LA (O₂)", Offset(midX + hW * 0.25f, topY + boxH * 0.23f), labelStyle)
          drawCenteredLabel(textMeasurer, "LV → Aorta", Offset(midX + hW * 0.25f, topY + boxH * 0.75f), labelStyle)
          drawCenteredLabel(textMeasurer, "Thick LV muscle pumps oxygenated blood across body", Offset(w * 0.50f, h * 0.87f), accentStyle)
          return@Canvas
        }

        "bio_07_nervous_eye_ear" -> {
          // Eye Lens Defects: Myopia vs Hypermetropia
          val c = Offset(w * 0.45f, h * 0.45f)
          drawCircle(outline, radius = h * 0.30f, center = c, style = Stroke(3.5f))
          drawOval(secondary.copy(alpha = 0.35f), topLeft = Offset(c.x - h * 0.28f, h * 0.25f), size = Size(14f, h * 0.40f))
          drawLine(primary, Offset(w * 0.10f, h * 0.34f), Offset(c.x - h * 0.25f, h * 0.34f), strokeWidth = 3f)
          drawLine(primary, Offset(c.x - h * 0.25f, h * 0.34f), Offset(c.x + h * 0.14f, h * 0.45f), strokeWidth = 3f)
          drawCircle(errorColor, radius = 6f, center = Offset(c.x + h * 0.14f, h * 0.45f))
          drawCenteredLabel(textMeasurer, "Myopia: Focus IN FRONT of Retina → Fix: Concave Lens", Offset(w * 0.50f, h * 0.84f), accentStyle)
          return@Canvas
        }

        "bio_15_microbes_vectors_conservation" -> {
          // Vector -> Pathogen -> Host Disease Control Triangle
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.08f, h * 0.24f), size = Size(w * 0.24f, 44f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.38f, h * 0.24f), size = Size(w * 0.24f, 44f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(tertiary.copy(alpha = 0.18f), topLeft = Offset(w * 0.68f, h * 0.24f), size = Size(w * 0.24f, 44f), cornerRadius = CornerRadius(10f, 10f))
          drawLine(primary, Offset(w * 0.32f, h * 0.38f), Offset(w * 0.38f, h * 0.38f), strokeWidth = 3.5f)
          drawLine(secondary, Offset(w * 0.62f, h * 0.38f), Offset(w * 0.68f, h * 0.38f), strokeWidth = 3.5f)
          drawCenteredLabel(textMeasurer, "Vector (Anopheles)", Offset(w * 0.20f, h * 0.38f), labelStyle)
          drawCenteredLabel(textMeasurer, "Plasmodium", Offset(w * 0.50f, h * 0.38f), accentStyle)
          drawCenteredLabel(textMeasurer, "Human RBCs", Offset(w * 0.80f, h * 0.38f), labelStyle)
          drawCenteredLabel(textMeasurer, "Break vector breeding sites + treated nets to halt transmission", Offset(w * 0.50f, h * 0.78f), labelStyle)
          return@Canvas
        }
      }

      // 2. Exhaustive LabType schematic dispatch (every enum branch covered explicitly — zero generic fallback)
      when (lesson.labType) {
        PracticalLabType.MEASUREMENT_VECTOR_LAB -> {
          val o = Offset(w * 0.14f, h * 0.76f)
          val p1 = Offset(w * 0.62f, h * 0.76f)
          val p2 = Offset(w * 0.34f, h * 0.24f)
          val rEnd = Offset(w * 0.82f, h * 0.24f)
          drawLine(primary, o, p1, strokeWidth = 5f)
          drawLine(secondary, o, p2, strokeWidth = 5f)
          drawLine(outline, p1, rEnd, strokeWidth = 2.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f)))
          drawLine(outline, p2, rEnd, strokeWidth = 2.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f)))
          drawLine(tertiary, o, rEnd, strokeWidth = 6f)
          drawCenteredLabel(textMeasurer, "Force F₁", Offset(w * 0.38f, h * 0.88f), labelStyle)
          drawCenteredLabel(textMeasurer, "Force F₂", Offset(w * 0.19f, h * 0.48f), labelStyle)
          drawCenteredLabel(textMeasurer, "R = √(F₁² + F₂² + 2F₁F₂cosθ)", Offset(w * 0.56f, h * 0.12f), accentStyle)
        }

        PracticalLabType.PROJECTILE_KINEMATICS -> {
          val groundY = h * 0.80f
          drawLine(outline, Offset(w * 0.08f, groundY), Offset(w * 0.92f, groundY), strokeWidth = 3f)
          val path = Path().apply {
            moveTo(w * 0.12f, groundY)
            for (i in 1..40) {
              val t = i / 40f
              val x = w * 0.12f + t * w * 0.76f
              val y = groundY - 4f * t * (1f - t) * (h * 0.60f)
              lineTo(x, y)
            }
          }
          drawPath(path, primary, style = Stroke(width = 5f))
          drawLine(secondary, Offset(w * 0.5f, groundY), Offset(w * 0.5f, groundY - h * 0.60f), strokeWidth = 2.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f)))
          drawCircle(tertiary, radius = 8f, center = Offset(w * 0.5f, groundY - h * 0.60f))
          drawCenteredLabel(textMeasurer, "Peak H_max (v_y = 0)", Offset(w * 0.5f, h * 0.10f), accentStyle)
          drawCenteredLabel(textMeasurer, "Horizontal Range R = u² sin(2θ) / g", Offset(w * 0.5f, h * 0.91f), labelStyle)
        }

        PracticalLabType.TORQUE_MACHINES_LAB -> {
          val pivot = Offset(w * 0.45f, h * 0.70f)
          drawLine(primary, Offset(w * 0.14f, h * 0.50f), Offset(w * 0.86f, h * 0.50f), strokeWidth = 8f)
          drawCircle(tertiary, radius = 10f, center = pivot)
          drawRect(secondary, topLeft = Offset(w * 0.16f, h * 0.28f), size = Size(44f, 34f))
          drawRect(primary, topLeft = Offset(w * 0.76f, h * 0.34f), size = Size(32f, 26f))
          drawCenteredLabel(textMeasurer, "ACW Moment F₁·d₁", Offset(w * 0.25f, h * 0.14f), labelStyle)
          drawCenteredLabel(textMeasurer, "Fulcrum / Pivot", Offset(w * 0.45f, h * 0.86f), accentStyle)
          drawCenteredLabel(textMeasurer, "CW Moment F₂·d₂", Offset(w * 0.76f, h * 0.16f), labelStyle)
        }

        PracticalLabType.SHM_FLUIDS_LAB -> {
          val top = Offset(w * 0.5f, h * 0.10f)
          drawLine(outline, Offset(w * 0.25f, h * 0.10f), Offset(w * 0.75f, h * 0.10f), strokeWidth = 5f)
          drawLine(outline, top, Offset(w * 0.5f, h * 0.74f), strokeWidth = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f)))
          val bob = Offset(w * 0.70f, h * 0.66f)
          drawLine(primary, top, bob, strokeWidth = 4f)
          drawCircle(secondary, radius = 16f, center = bob)
          drawCenteredLabel(textMeasurer, "Max speed at center (x = 0)", Offset(w * 0.45f, h * 0.86f), labelStyle)
          drawCenteredLabel(textMeasurer, "Period T = 2π√(L/g)", Offset(w * 0.76f, h * 0.28f), accentStyle)
        }

        PracticalLabType.THERMAL_EXPANSION_CALORIMETRY -> {
          val pts = listOf(
            Offset(w * 0.10f, h * 0.82f),
            Offset(w * 0.24f, h * 0.62f),
            Offset(w * 0.44f, h * 0.62f),
            Offset(w * 0.60f, h * 0.32f),
            Offset(w * 0.80f, h * 0.32f),
            Offset(w * 0.92f, h * 0.16f),
          )
          for (i in 0 until pts.lastIndex) {
            drawLine(color = if (i == 1 || i == 3) tertiary else primary, start = pts[i], end = pts[i + 1], strokeWidth = 5f)
          }
          drawCenteredLabel(textMeasurer, "0°C Melt (Q = mL_f)", Offset(w * 0.34f, h * 0.74f), labelStyle)
          drawCenteredLabel(textMeasurer, "100°C Boil (Q = mL_v)", Offset(w * 0.70f, h * 0.18f), accentStyle)
        }

        PracticalLabType.WAVE_OPTICS_BENCH -> {
          val midY = h * 0.52f
          val lensX = w * 0.52f
          drawLine(outline, Offset(w * 0.08f, midY), Offset(w * 0.92f, midY), strokeWidth = 2.5f)
          drawOval(secondary.copy(alpha = 0.28f), topLeft = Offset(lensX - 10f, h * 0.14f), size = Size(20f, h * 0.74f))
          drawLine(primary, Offset(w * 0.14f, h * 0.26f), Offset(lensX, h * 0.26f), strokeWidth = 4f)
          drawLine(primary, Offset(lensX, h * 0.26f), Offset(w * 0.84f, h * 0.70f), strokeWidth = 4f)
          drawLine(tertiary, Offset(w * 0.14f, h * 0.26f), Offset(w * 0.84f, h * 0.70f), strokeWidth = 3f)
          drawCenteredLabel(textMeasurer, "Object (u)", Offset(w * 0.16f, h * 0.86f), labelStyle)
          drawCenteredLabel(textMeasurer, "Convex Lens (1/u + 1/v = 1/f)", Offset(lensX, h * 0.08f), accentStyle)
          drawCenteredLabel(textMeasurer, "Image (v)", Offset(w * 0.82f, h * 0.86f), labelStyle)
        }

        PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB, PracticalLabType.OHMS_CIRCUIT, PracticalLabType.EM_TRANSFORMER_AC_LAB -> {
          drawRect(primary, topLeft = Offset(w * 0.18f, h * 0.22f), size = Size(w * 0.64f, h * 0.50f), style = Stroke(4f))
          drawRoundRect(surfaceBg, topLeft = Offset(w * 0.36f, h * 0.14f), size = Size(w * 0.28f, h * 0.16f), cornerRadius = CornerRadius(6f, 6f))
          drawRoundRect(secondary, topLeft = Offset(w * 0.36f, h * 0.14f), size = Size(w * 0.28f, h * 0.16f), cornerRadius = CornerRadius(6f, 6f), style = Stroke(4f))
          drawCenteredLabel(
            textMeasurer,
            when (lesson.labType) {
              PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB -> "C_p = C₁ + C₂  |  1/C_s = 1/C₁ + 1/C₂  |  U = ½CV²"
              PracticalLabType.EM_TRANSFORMER_AC_LAB -> "V_s / V_p = N_s / N_p  |  Z = √(R² + (X_L − X_C)²)"
              else -> "EMF E = I(R + r)  •  Terminal Voltage V = IR"
            },
            Offset(w * 0.5f, h * 0.88f),
            accentStyle,
          )
          drawCenteredLabel(textMeasurer, "Circuit Component", Offset(w * 0.5f, h * 0.22f), labelStyle)
        }

        PracticalLabType.QUANTUM_NUCLEAR_LAB -> {
          val path = Path().apply {
            moveTo(w * 0.10f, h * 0.18f)
            for (i in 1..30) {
              val t = i / 30f
              val y = h * 0.80f - (0.5f.pow(t * 3.5f)) * h * 0.62f
              lineTo(w * 0.10f + t * w * 0.80f, y)
            }
          }
          drawPath(path, primary, style = Stroke(5f))
          drawCircle(tertiary, radius = 7f, center = Offset(w * 0.33f, h * 0.80f - 0.5f * h * 0.62f))
          drawCenteredLabel(textMeasurer, "N₀/2 at 1 Half-Life (t½)", Offset(w * 0.44f, h * 0.38f), accentStyle)
          drawCenteredLabel(textMeasurer, "N = N₀(1/2)ⁿ  •  E_k(max) = hf − W₀", Offset(w * 0.50f, h * 0.90f), labelStyle)
        }

        PracticalLabType.NUMBER_BASE_INDICES_LAB -> {
          // Repeated Division Base Ladder + Modulo Clock Ring
          val clockC = Offset(w * 0.24f, h * 0.48f)
          drawCircle(primary, radius = h * 0.28f, center = clockC, style = Stroke(3.5f))
          drawCenteredLabel(textMeasurer, "Mod n Ring", clockC, labelStyle)
          drawRoundRect(secondary.copy(alpha = 0.16f), topLeft = Offset(w * 0.48f, h * 0.20f), size = Size(w * 0.44f, h * 0.54f), cornerRadius = CornerRadius(10f, 10f))
          drawCenteredLabel(textMeasurer, "45 ÷ 2 = 22 R 1", Offset(w * 0.70f, h * 0.34f), accentStyle)
          drawCenteredLabel(textMeasurer, "Read Remainders Upward ↑", Offset(w * 0.70f, h * 0.54f), labelStyle)
          drawCenteredLabel(textMeasurer, "Base Conversion & Indices: aⁿ × aᵐ = aⁿ⁺ᵐ", Offset(w * 0.50f, h * 0.88f), accentStyle)
        }

        PracticalLabType.SETS_VENN_LAB -> {
          drawCircle(primary.copy(alpha = 0.24f), radius = h * 0.35f, center = Offset(w * 0.40f, h * 0.46f))
          drawCircle(primary, radius = h * 0.35f, center = Offset(w * 0.40f, h * 0.46f), style = Stroke(4f))
          drawCircle(secondary.copy(alpha = 0.24f), radius = h * 0.35f, center = Offset(w * 0.60f, h * 0.46f))
          drawCircle(secondary, radius = h * 0.35f, center = Offset(w * 0.60f, h * 0.46f), style = Stroke(4f))
          drawCenteredLabel(textMeasurer, "A only", Offset(w * 0.29f, h * 0.46f), labelStyle)
          drawCenteredLabel(textMeasurer, "A ∩ B", Offset(w * 0.50f, h * 0.46f), accentStyle)
          drawCenteredLabel(textMeasurer, "B only", Offset(w * 0.71f, h * 0.46f), labelStyle)
          drawCenteredLabel(textMeasurer, "n(A ∪ B) = n(A) + n(B) − n(A ∩ B)", Offset(w * 0.50f, h * 0.89f), labelStyle)
        }

        PracticalLabType.ALGEBRA_VARIATION_LAB -> {
          val ox = w * 0.16f
          val oy = h * 0.80f
          drawLine(outline, Offset(ox, h * 0.14f), Offset(ox, oy), strokeWidth = 2.5f)
          drawLine(outline, Offset(ox, oy), Offset(w * 0.88f, oy), strokeWidth = 2.5f)
          drawLine(primary, Offset(ox, oy), Offset(w * 0.82f, h * 0.20f), strokeWidth = 4.5f)
          val hyper = Path().apply {
            moveTo(w * 0.24f, h * 0.18f)
            for (i in 1..25) {
              val x = w * 0.24f + i * (w * 0.58f / 25f)
              val y = oy - (h * 0.58f) / (1f + i * 0.28f)
              lineTo(x, y)
            }
          }
          drawPath(hyper, tertiary, style = Stroke(4.5f))
          drawCenteredLabel(textMeasurer, "Direct (y = kx)", Offset(w * 0.72f, h * 0.16f), accentStyle)
          drawCenteredLabel(textMeasurer, "Inverse (y = k/x)", Offset(w * 0.72f, h * 0.68f), labelStyle)
        }

        PracticalLabType.QUADRATIC_TANGENT, PracticalLabType.COORDINATE_CALCULUS_LAB -> {
          drawLine(outline, Offset(w * 0.1f, h * 0.75f), Offset(w * 0.9f, h * 0.75f), strokeWidth = 2.5f)
          val curve = Path().apply {
            moveTo(w * 0.16f, h * 0.16f)
            for (i in 1..40) {
              val t = i / 40f
              val x = w * 0.16f + t * w * 0.68f
              val u = (t - 0.5f) * 2f
              val y = h * 0.78f - (1f - u * u) * h * 0.54f
              lineTo(x, y)
            }
          }
          drawPath(curve, primary, style = Stroke(5f))
          drawLine(tertiary, Offset(w * 0.30f, h * 0.24f), Offset(w * 0.70f, h * 0.24f), strokeWidth = 3.5f)
          drawCenteredLabel(textMeasurer, "Vertex / Stationary Point (dy/dx = 0)", Offset(w * 0.50f, h * 0.11f), accentStyle)
          drawCenteredLabel(textMeasurer, "Gradient m = Δy/Δx  •  Discriminant Δ = b² − 4ac", Offset(w * 0.50f, h * 0.89f), labelStyle)
        }

        PracticalLabType.SEQUENCE_MATRIX_LAB -> {
          // AP vs GP bars or 2x2 Determinant Grid
          if (lesson.id == "math_07_matrices_vectors") {
            val gx = w * 0.32f
            val gy = h * 0.18f
            val cell = w * 0.18f
            drawRect(outline, topLeft = Offset(gx, gy), size = Size(cell * 2, h * 0.54f), style = Stroke(3.5f))
            drawLine(primary, Offset(gx + cell * 0.25f, gy + 16f), Offset(gx + cell * 1.75f, gy + h * 0.44f), strokeWidth = 4f)
            drawLine(errorColor, Offset(gx + cell * 1.75f, gy + 16f), Offset(gx + cell * 0.25f, gy + h * 0.44f), strokeWidth = 3f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f)))
            drawCenteredLabel(textMeasurer, "a", Offset(gx + cell * 0.4f, gy + 20f), accentStyle)
            drawCenteredLabel(textMeasurer, "b", Offset(gx + cell * 1.6f, gy + 20f), errStyle)
            drawCenteredLabel(textMeasurer, "c", Offset(gx + cell * 0.4f, gy + h * 0.42f), errStyle)
            drawCenteredLabel(textMeasurer, "d", Offset(gx + cell * 1.6f, gy + h * 0.42f), accentStyle)
            drawCenteredLabel(textMeasurer, "det(A) = ad − bc  (Non-singular when ad − bc ≠ 0)", Offset(w * 0.50f, h * 0.86f), accentStyle)
          } else {
            val heights = listOf(0.18f, 0.28f, 0.40f, 0.54f, 0.72f)
            heights.forEachIndexed { idx, frac ->
              val x = w * 0.16f + idx * (w * 0.15f)
              val bh = h * frac * 0.76f
              drawRoundRect(
                color = if (idx % 2 == 0) primary else secondary,
                topLeft = Offset(x, h * 0.76f - bh),
                size = Size(w * 0.10f, bh),
                cornerRadius = CornerRadius(6f, 6f),
              )
              drawCenteredLabel(textMeasurer, "T${idx + 1}", Offset(x + w * 0.05f, h * 0.85f), labelStyle)
            }
            drawCenteredLabel(textMeasurer, "AP: Tₙ = a + (n−1)d   |   GP: Tₙ = arⁿ⁻¹", Offset(w * 0.50f, h * 0.12f), accentStyle)
          }
        }

        PracticalLabType.POLYGON_CIRCLE_THEOREM_LAB -> {
          val c = Offset(w * 0.50f, h * 0.52f)
          val r = h * 0.38f
          drawCircle(outline, radius = r, center = c, style = Stroke(4f))
          val a = Offset(c.x - r * 0.78f, c.y + r * 0.62f)
          val b = Offset(c.x + r * 0.78f, c.y + r * 0.62f)
          val top = Offset(c.x, c.y - r)
          drawLine(primary, a, top, strokeWidth = 4f)
          drawLine(primary, b, top, strokeWidth = 4f)
          drawLine(tertiary, a, c, strokeWidth = 4f)
          drawLine(tertiary, b, c, strokeWidth = 4f)
          drawCircle(tertiary, radius = 6f, center = c)
          drawCenteredLabel(textMeasurer, "Circumference Angle θ", Offset(w * 0.50f, h * 0.08f), labelStyle)
          drawCenteredLabel(textMeasurer, "Center Angle 2θ", Offset(w * 0.50f, h * 0.64f), accentStyle)
        }

        PracticalLabType.MENSURATION_3D_LAB -> {
          // Cylinder + Cone Side-by-Side (V_cone = 1/3 V_cylinder)
          drawOval(outline, topLeft = Offset(w * 0.16f, h * 0.18f), size = Size(w * 0.22f, 18f), style = Stroke(3f))
          drawLine(primary, Offset(w * 0.16f, h * 0.24f), Offset(w * 0.16f, h * 0.68f), strokeWidth = 3.5f)
          drawLine(primary, Offset(w * 0.38f, h * 0.24f), Offset(w * 0.38f, h * 0.68f), strokeWidth = 3.5f)
          drawOval(outline, topLeft = Offset(w * 0.16f, h * 0.62f), size = Size(w * 0.22f, 18f), style = Stroke(3f))
          // Cone
          drawLine(secondary, Offset(w * 0.70f, h * 0.20f), Offset(w * 0.58f, h * 0.68f), strokeWidth = 4f)
          drawLine(secondary, Offset(w * 0.70f, h * 0.20f), Offset(w * 0.82f, h * 0.68f), strokeWidth = 4f)
          drawOval(outline, topLeft = Offset(w * 0.58f, h * 0.62f), size = Size(w * 0.24f, 18f), style = Stroke(3f))
          drawCenteredLabel(textMeasurer, "Cylinder: πr²h", Offset(w * 0.27f, h * 0.86f), labelStyle)
          drawCenteredLabel(textMeasurer, "Cone: ⅓πr²h (l² = r² + h²)", Offset(w * 0.70f, h * 0.86f), accentStyle)
        }

        PracticalLabType.TRIG_BEARING_LAB -> {
          val c = Offset(w * 0.35f, h * 0.52f)
          val r = h * 0.35f
          drawCircle(outline, radius = r, center = c, style = Stroke(3f))
          drawLine(outline, Offset(c.x, c.y - r), Offset(c.x, c.y + r), strokeWidth = 2f)
          drawLine(outline, Offset(c.x - r, c.y), Offset(c.x + r, c.y), strokeWidth = 2f)
          val p = Offset(c.x + r * cos(0.75f), c.y - r * sin(0.75f))
          drawLine(primary, c, p, strokeWidth = 5f)
          drawCenteredLabel(textMeasurer, "N (000°)", Offset(c.x, h * 0.08f), accentStyle)
          drawCenteredLabel(textMeasurer, "Clockwise Bearing θ", Offset(w * 0.74f, h * 0.38f), labelStyle)
          drawCenteredLabel(textMeasurer, "Back-Bearing = θ ± 180°", Offset(w * 0.74f, h * 0.58f), accentStyle)
        }

        PracticalLabType.PROBABILITY_STATISTICS_LAB -> {
          // Probability Tree + Dispersion Distribution
          val root = Offset(w * 0.14f, h * 0.46f)
          val b1 = Offset(w * 0.44f, h * 0.24f)
          val b2 = Offset(w * 0.44f, h * 0.68f)
          drawLine(primary, root, b1, strokeWidth = 4f)
          drawLine(secondary, root, b2, strokeWidth = 4f)
          drawLine(primary, b1, Offset(w * 0.74f, h * 0.16f), strokeWidth = 3f)
          drawLine(secondary, b1, Offset(w * 0.74f, h * 0.34f), strokeWidth = 3f)
          drawCircle(tertiary, radius = 6f, center = root)
          drawCenteredLabel(textMeasurer, "P(A) · P(B|A)", Offset(w * 0.80f, h * 0.16f), accentStyle)
          drawCenteredLabel(textMeasurer, "Add constant: SD unchanged • Multiply: SD scales", Offset(w * 0.52f, h * 0.88f), labelStyle)
        }

        PracticalLabType.SEPARATION_ATOMIC_LAB, PracticalLabType.BONDING_PERIODIC_LAB -> {
          val c = Offset(w * 0.30f, h * 0.48f)
          drawCircle(primary.copy(alpha = 0.2f), radius = 14f, center = c)
          drawCircle(primary, radius = 34f, center = c, style = Stroke(3f))
          drawCircle(secondary, radius = 56f, center = c, style = Stroke(3f))
          drawCircle(tertiary, radius = 6f, center = Offset(c.x + 34f, c.y))
          drawCircle(tertiary, radius = 6f, center = Offset(c.x - 34f, c.y))
          drawCircle(primary, radius = 6f, center = Offset(c.x, c.y - 56f))
          drawCenteredLabel(textMeasurer, "K(2) • L(8) • M(8) • N(2)", Offset(w * 0.72f, h * 0.34f), accentStyle)
          drawCenteredLabel(textMeasurer, "ΔEN ≥ 1.7 → Ionic | < 1.7 → Covalent", Offset(w * 0.72f, h * 0.58f), labelStyle)
        }

        PracticalLabType.STOICHIOMETRY_MOLE_LAB -> {
          // Central Mole Hub Conversion Map
          drawRoundRect(primary.copy(alpha = 0.22f), topLeft = Offset(w * 0.36f, h * 0.32f), size = Size(w * 0.28f, h * 0.32f), cornerRadius = CornerRadius(12f, 12f))
          drawRoundRect(primary, topLeft = Offset(w * 0.36f, h * 0.32f), size = Size(w * 0.28f, h * 0.32f), cornerRadius = CornerRadius(12f, 12f), style = Stroke(3f))
          drawCenteredLabel(textMeasurer, "MOLES (n)", Offset(w * 0.50f, h * 0.48f), accentStyle)
          drawCenteredLabel(textMeasurer, "Mass ÷ M_r", Offset(w * 0.16f, h * 0.48f), labelStyle)
          drawCenteredLabel(textMeasurer, "STP Vol ÷ 22.4 dm³", Offset(w * 0.84f, h * 0.48f), labelStyle)
          drawCenteredLabel(textMeasurer, "Particles ÷ 6.02×10²³  |  n = CV", Offset(w * 0.50f, h * 0.86f), labelStyle)
        }

        PracticalLabType.GAS_LAW_PISTON -> {
          // Cylinder + Moving Piston PV = nRT
          drawRect(outline, topLeft = Offset(w * 0.22f, h * 0.16f), size = Size(w * 0.56f, h * 0.58f), style = Stroke(4f))
          drawRect(primary.copy(alpha = 0.35f), topLeft = Offset(w * 0.22f, h * 0.32f), size = Size(w * 0.56f, 14f))
          drawCircle(secondary, radius = 6f, center = Offset(w * 0.38f, h * 0.58f))
          drawCircle(secondary, radius = 6f, center = Offset(w * 0.54f, h * 0.50f))
          drawCircle(tertiary, radius = 6f, center = Offset(w * 0.64f, h * 0.62f))
          drawCenteredLabel(textMeasurer, "P↓ Force", Offset(w * 0.50f, h * 0.24f), accentStyle)
          drawCenteredLabel(textMeasurer, "PV = nRT  •  T(K) = °C + 273  •  Rate ∝ 1/√M", Offset(w * 0.50f, h * 0.88f), labelStyle)
        }

        PracticalLabType.TITRATION_PH -> {
          drawLine(outline, Offset(w * 0.12f, h * 0.48f), Offset(w * 0.90f, h * 0.48f), strokeWidth = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f)))
          val p = Path().apply {
            moveTo(w * 0.14f, h * 0.78f)
            lineTo(w * 0.45f, h * 0.73f)
            lineTo(w * 0.50f, h * 0.22f)
            lineTo(w * 0.86f, h * 0.16f)
          }
          drawPath(p, primary, style = Stroke(5f))
          drawCircle(tertiary, radius = 8f, center = Offset(w * 0.475f, h * 0.47f))
          drawCenteredLabel(textMeasurer, "Acid pH < 7", Offset(w * 0.28f, h * 0.65f), labelStyle)
          drawCenteredLabel(textMeasurer, "Equivalence Jump (C_aV_a/n_a = C_bV_b/n_b)", Offset(w * 0.56f, h * 0.89f), accentStyle)
          drawCenteredLabel(textMeasurer, "Alkali pH > 7", Offset(w * 0.72f, h * 0.28f), labelStyle)
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
          drawCenteredLabel(textMeasurer, "Catalyst lowers E_a (ΔH unchanged)", Offset(w * 0.56f, h * 0.14f), accentStyle)
          drawCenteredLabel(textMeasurer, "Exothermic Products (ΔH < 0)", Offset(w * 0.72f, h * 0.86f), labelStyle)
        }

        PracticalLabType.REDOX_ELECTROLYSIS_LAB -> {
          // Electrolytic Cell: Anode (+) & Cathode (-)
          drawRect(outline, topLeft = Offset(w * 0.22f, h * 0.26f), size = Size(w * 0.56f, h * 0.50f), style = Stroke(3f))
          drawRect(errorColor.copy(alpha = 0.70f), topLeft = Offset(w * 0.32f, h * 0.18f), size = Size(18f, h * 0.46f))
          drawRect(primary.copy(alpha = 0.70f), topLeft = Offset(w * 0.66f, h * 0.18f), size = Size(18f, h * 0.46f))
          drawCenteredLabel(textMeasurer, "Anode (OX)", Offset(w * 0.34f, h * 0.10f), errStyle)
          drawCenteredLabel(textMeasurer, "Cathode (RED)", Offset(w * 0.68f, h * 0.10f), accentStyle)
          drawCenteredLabel(textMeasurer, "m = (M · I · t) / (n · 96,500 C)   •   OIL RIG", Offset(w * 0.50f, h * 0.88f), accentStyle)
        }

        PracticalLabType.INORGANIC_GASES_METALS_LAB, PracticalLabType.QUALITATIVE_ANALYSIS_LAB -> {
          // 3 Diagnostic Test Tubes
          val blueSol = if (isDark) Color(0xFF60A5FA) else Color(0xFF1E88E5)
          val greenSol = if (isDark) Color(0xFF4ADE80) else Color(0xFF2E7D32)
          val amberSol = if (isDark) Color(0xFFFBBF24) else Color(0xFFB45309)
          val tubes = listOf("Cu²⁺ → Blue/Royal NH₃" to blueSol, "Fe²⁺ → Dirty Green" to greenSol, "Fe³⁺ → Red-Brown" to amberSol)
          tubes.forEachIndexed { idx, (label, col) ->
            val x = w * (0.18f + idx * 0.28f)
            drawRoundRect(outline, topLeft = Offset(x, h * 0.18f), size = Size(w * 0.14f, h * 0.54f), cornerRadius = CornerRadius(18f, 18f), style = Stroke(3f))
            drawRoundRect(col.copy(alpha = 0.58f), topLeft = Offset(x + 3f, h * 0.42f), size = Size(w * 0.14f - 6f, h * 0.28f), cornerRadius = CornerRadius(14f, 14f))
            drawCenteredLabel(textMeasurer, label, Offset(x + w * 0.07f, h * 0.84f), labelStyle)
          }
        }

        PracticalLabType.ORGANIC_HYDROCARBON_LAB -> {
          // Alkane -> Alkene -> Alkyne Bonding & Functional Test
          drawRoundRect(primary.copy(alpha = 0.15f), topLeft = Offset(w * 0.08f, h * 0.24f), size = Size(w * 0.24f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.38f, h * 0.24f), size = Size(w * 0.24f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(tertiary.copy(alpha = 0.20f), topLeft = Offset(w * 0.68f, h * 0.24f), size = Size(w * 0.24f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "C−C (Alkane)", Offset(w * 0.20f, h * 0.38f), labelStyle)
          drawCenteredLabel(textMeasurer, "C=C (Decolor Br₂)", Offset(w * 0.50f, h * 0.38f), accentStyle)
          drawCenteredLabel(textMeasurer, "C≡C (Red Cu₂C₂)", Offset(w * 0.80f, h * 0.38f), labelStyle)
          drawCenteredLabel(textMeasurer, "CₙH₂ₙ₊₂  |  CₙH₂ₙ  |  CₙH₂ₙ₋₂  |  RCOOR' Esterification", Offset(w * 0.50f, h * 0.80f), accentStyle)
        }

        PracticalLabType.CELL_OSMOSIS_LAB -> {
          // Hypotonic Turgid vs Hypertonic Plasmolyzed Cell
          drawRoundRect(successColor.copy(alpha = 0.20f), topLeft = Offset(w * 0.12f, h * 0.20f), size = Size(w * 0.30f, h * 0.52f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(successColor, topLeft = Offset(w * 0.12f, h * 0.20f), size = Size(w * 0.30f, h * 0.52f), cornerRadius = CornerRadius(10f, 10f), style = Stroke(3.5f))
          drawRoundRect(errorColor.copy(alpha = 0.18f), topLeft = Offset(w * 0.58f, h * 0.20f), size = Size(w * 0.30f, h * 0.52f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(outline, topLeft = Offset(w * 0.58f, h * 0.20f), size = Size(w * 0.30f, h * 0.52f), cornerRadius = CornerRadius(10f, 10f), style = Stroke(3.5f))
          drawOval(errorColor.copy(alpha = 0.45f), topLeft = Offset(w * 0.65f, h * 0.30f), size = Size(w * 0.16f, h * 0.30f))
          drawCenteredLabel(textMeasurer, "Hypotonic → Turgid", Offset(w * 0.27f, h * 0.82f), accentStyle)
          drawCenteredLabel(textMeasurer, "Hypertonic → Plasmolysis", Offset(w * 0.73f, h * 0.82f), errStyle)
        }

        PracticalLabType.BIOENERGETICS_ENZYME_LAB -> {
          val bell = Path().apply {
            moveTo(w * 0.12f, h * 0.78f)
            quadraticTo(w * 0.50f, h * 0.04f, w * 0.68f, h * 0.44f)
            lineTo(w * 0.78f, h * 0.80f)
          }
          drawPath(bell, primary, style = Stroke(5f))
          drawCircle(tertiary, radius = 7f, center = Offset(w * 0.46f, h * 0.28f))
          drawCenteredLabel(textMeasurer, "Optimum ~37°C", Offset(w * 0.46f, h * 0.14f), accentStyle)
          drawCenteredLabel(textMeasurer, "Denatured >50°C", Offset(w * 0.80f, h * 0.54f), errStyle)
          drawCenteredLabel(textMeasurer, "6CO₂ + 6H₂O → C₆H₁₂O₆ + 6O₂  |  38 ATP Aerobic", Offset(w * 0.50f, h * 0.90f), labelStyle)
        }

        PracticalLabType.NUTRITION_TRANSPORT_LAB -> {
          // Digestive & Food Reagent Pathways
          val reagents = listOf("Iodine → Starch (Blue-Black)", "Benedict's → Glucose (Brick-Red)", "Biuret → Protein (Purple)")
          reagents.forEachIndexed { idx, txt ->
            val y = h * (0.20f + idx * 0.24f)
            drawRoundRect(primary.copy(alpha = 0.14f), topLeft = Offset(w * 0.12f, y), size = Size(w * 0.76f, 28f), cornerRadius = CornerRadius(8f, 8f))
            drawCenteredLabel(textMeasurer, txt, Offset(w * 0.50f, y + 14f), labelStyle)
          }
        }

        PracticalLabType.RESPIRATION_KIDNEY_LAB -> {
          // Nephron & Thoracic Pressure-Volume Loop
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.08f, h * 0.22f), size = Size(w * 0.25f, 48f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.38f, h * 0.22f), size = Size(w * 0.24f, 48f), cornerRadius = CornerRadius(10f, 10f))
          drawRoundRect(tertiary.copy(alpha = 0.20f), topLeft = Offset(w * 0.67f, h * 0.22f), size = Size(w * 0.25f, 48f), cornerRadius = CornerRadius(10f, 10f))
          drawCenteredLabel(textMeasurer, "Bowman's Ultrafiltration", Offset(w * 0.205f, h * 0.36f), labelStyle)
          drawCenteredLabel(textMeasurer, "PCT Glucose 100%", Offset(w * 0.50f, h * 0.36f), accentStyle)
          drawCenteredLabel(textMeasurer, "ADH Water Reabsorb", Offset(w * 0.795f, h * 0.36f), labelStyle)
          drawCenteredLabel(textMeasurer, "Inhalation: Diaphragm flattens → Volume ↑, Thoracic Pressure ↓", Offset(w * 0.50f, h * 0.80f), accentStyle)
        }

        PracticalLabType.NERVOUS_ENDOCRINE_LAB -> {
          // Negative Feedback Pancreas Glucose Loop
          drawRoundRect(errorColor.copy(alpha = 0.16f), topLeft = Offset(w * 0.12f, h * 0.20f), size = Size(w * 0.34f, 40f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.54f, h * 0.20f), size = Size(w * 0.34f, 40f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "High Glucose → INSULIN", Offset(w * 0.29f, h * 0.33f), errStyle)
          drawCenteredLabel(textMeasurer, "Low Glucose → GLUCAGON", Offset(w * 0.71f, h * 0.33f), accentStyle)
          drawCenteredLabel(textMeasurer, "Target Homeostasis (~90 mg/dL)  •  Synapse + Reflex Arc", Offset(w * 0.50f, h * 0.78f), labelStyle)
        }

        PracticalLabType.REPRODUCTION_FLOWER_LAB -> {
          // 28-Day Hormone Timeline (Day 1 -> 14 LH Surge -> 28)
          drawLine(outline, Offset(w * 0.10f, h * 0.68f), Offset(w * 0.90f, h * 0.68f), strokeWidth = 3f)
          val lh = Path().apply {
            moveTo(w * 0.12f, h * 0.64f)
            lineTo(w * 0.44f, h * 0.58f)
            lineTo(w * 0.50f, h * 0.18f)
            lineTo(w * 0.56f, h * 0.58f)
            lineTo(w * 0.88f, h * 0.62f)
          }
          drawPath(lh, primary, style = Stroke(5f))
          drawCircle(tertiary, radius = 7f, center = Offset(w * 0.50f, h * 0.18f))
          drawCenteredLabel(textMeasurer, "Day 14: LH Surge (Ovulation)", Offset(w * 0.50f, h * 0.09f), accentStyle)
          drawCenteredLabel(textMeasurer, "Day 1–5 Menses   |   Day 15–28 Progesterone sustains lining", Offset(w * 0.50f, h * 0.84f), labelStyle)
        }

        PracticalLabType.MENDELIAN_GENETICS -> {
          val gx = w * 0.32f
          val gy = h * 0.14f
          val cell = w * 0.18f
          drawRect(outline, topLeft = Offset(gx, gy), size = Size(cell * 2, 92f), style = Stroke(3f))
          drawLine(outline, Offset(gx + cell, gy), Offset(gx + cell, gy + 92f), strokeWidth = 2.5f)
          drawLine(outline, Offset(gx, gy + 46f), Offset(gx + cell * 2, gy + 46f), strokeWidth = 2.5f)
          drawCenteredLabel(textMeasurer, "AA (25%)", Offset(gx + cell * 0.5f, gy + 23f), accentStyle)
          drawCenteredLabel(textMeasurer, "AS (25%)", Offset(gx + cell * 1.5f, gy + 23f), labelStyle)
          drawCenteredLabel(textMeasurer, "AS (25%)", Offset(gx + cell * 0.5f, gy + 69f), labelStyle)
          drawCenteredLabel(textMeasurer, "SS (25%)", Offset(gx + cell * 1.5f, gy + 69f), errStyle)
          drawCenteredLabel(textMeasurer, "AS × AS Punnett Cross (1 AA : 2 AS : 1 SS)", Offset(w * 0.50f, h * 0.88f), labelStyle)
        }

        PracticalLabType.EVOLUTION_VARIATION_LAB -> {
          // Frequency Shifts Over Generations
          drawLine(outline, Offset(w * 0.12f, h * 0.72f), Offset(w * 0.88f, h * 0.72f), strokeWidth = 2.5f)
          drawLine(primary, Offset(w * 0.14f, h * 0.62f), Offset(w * 0.86f, h * 0.22f), strokeWidth = 5f)
          drawLine(errorColor, Offset(w * 0.14f, h * 0.24f), Offset(w * 0.86f, h * 0.64f), strokeWidth = 4f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f)))
          drawCenteredLabel(textMeasurer, "Adapted Variant ↑ Across Generations", Offset(w * 0.52f, h * 0.14f), accentStyle)
          drawCenteredLabel(textMeasurer, "Continuous vs Discontinuous Variation  •  Natural Selection", Offset(w * 0.50f, h * 0.86f), labelStyle)
        }

        PracticalLabType.ECOLOGY_ENERGY_PYRAMID -> {
          val tiers = listOf("10 kJ Hawk (3°)" to 0.26f, "100 kJ Lizard (2°)" to 0.44f, "1,000 kJ Locust (1°)" to 0.62f, "10,000 kJ Grass (Prod)" to 0.80f)
          tiers.forEachIndexed { i, (txt, frac) ->
            val bw = w * frac
            val x = (w - bw) / 2f
            val y = h * 0.08f + i * 33f
            drawRoundRect(primary.copy(alpha = 0.2f + i * 0.18f), topLeft = Offset(x, y), size = Size(bw, 26f), cornerRadius = CornerRadius(6f, 6f))
            drawCenteredLabel(textMeasurer, txt, Offset(w * 0.5f, y + 13f), labelStyle)
          }
        }

        PracticalLabType.BIOGEOCHEMICAL_SOIL_LAB -> {
          // Nitrogen Cycle 4-Node Ring
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.10f, h * 0.18f), size = Size(w * 0.36f, 36f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.54f, h * 0.18f), size = Size(w * 0.36f, 36f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(tertiary.copy(alpha = 0.18f), topLeft = Offset(w * 0.10f, h * 0.52f), size = Size(w * 0.36f, 36f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(errorColor.copy(alpha = 0.16f), topLeft = Offset(w * 0.54f, h * 0.52f), size = Size(w * 0.36f, 36f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "Rhizobium: N₂ → NH₄⁺", Offset(w * 0.28f, h * 0.30f), accentStyle)
          drawCenteredLabel(textMeasurer, "Nitrosomonas → NO₂⁻", Offset(w * 0.72f, h * 0.30f), labelStyle)
          drawCenteredLabel(textMeasurer, "Nitrobacter → NO₃⁻", Offset(w * 0.28f, h * 0.64f), labelStyle)
          drawCenteredLabel(textMeasurer, "Pseudomonas → N₂ ↑", Offset(w * 0.72f, h * 0.64f), errStyle)
          drawCenteredLabel(textMeasurer, "Loam balances water retention, aeration & humus", Offset(w * 0.50f, h * 0.89f), labelStyle)
        }

        PracticalLabType.PARTS_CLAUSES_LAB -> {
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.08f, h * 0.24f), size = Size(w * 0.25f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.37f, h * 0.24f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(tertiary.copy(alpha = 0.18f), topLeft = Offset(w * 0.67f, h * 0.24f), size = Size(w * 0.25f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "Noun Clause", Offset(w * 0.205f, h * 0.38f), accentStyle)
          drawCenteredLabel(textMeasurer, "Adjectival Clause", Offset(w * 0.50f, h * 0.38f), labelStyle)
          drawCenteredLabel(textMeasurer, "Adverbial Clause", Offset(w * 0.795f, h * 0.38f), labelStyle)
          drawCenteredLabel(textMeasurer, "Function Test: Acts as S/O/C? Qualifies Noun? Modifies Verb?", Offset(w * 0.50f, h * 0.78f), labelStyle)
        }

        PracticalLabType.SYNTAX_LEXIS_STUDIO -> {
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.10f, h * 0.20f), size = Size(w * 0.80f, 32f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(secondary.copy(alpha = 0.16f), topLeft = Offset(w * 0.10f, h * 0.48f), size = Size(w * 0.80f, 32f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "Parenthetical ('as well as / with'): Match FIRST Subject", Offset(w * 0.50f, h * 0.30f), accentStyle)
          drawCenteredLabel(textMeasurer, "Correlative ('Neither...nor'): Match CLOSER Subject", Offset(w * 0.50f, h * 0.58f), labelStyle)
          drawCenteredLabel(textMeasurer, "Question Tags invert statement polarity (+ statement → − tag)", Offset(w * 0.50f, h * 0.86f), labelStyle)
        }

        PracticalLabType.LEXIS_IDIOMS_REGISTER_LAB -> {
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.08f, h * 0.22f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.37f, h * 0.22f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(tertiary.copy(alpha = 0.18f), topLeft = Offset(w * 0.66f, h * 0.22f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "Context Field", Offset(w * 0.21f, h * 0.36f), labelStyle)
          drawCenteredLabel(textMeasurer, "Preposition Pair", Offset(w * 0.50f, h * 0.36f), accentStyle)
          drawCenteredLabel(textMeasurer, "Register Match", Offset(w * 0.79f, h * 0.36f), labelStyle)
          drawCenteredLabel(textMeasurer, "Legal / Medical / Financial Lexis & Prepositional Collocations", Offset(w * 0.50f, h * 0.78f), labelStyle)
        }

        PracticalLabType.ORAL_ENGLISH_STRESS -> {
          val xs = listOf(0.20f, 0.40f, 0.62f, 0.82f)
          val hs = listOf(0.35f, 0.35f, 0.84f, 0.30f)
          val syls = listOf("in", "for", "MA ★", "tion")
          xs.forEachIndexed { idx, fx ->
            val bh = h * hs[idx] * 0.70f
            drawRoundRect(
              color = if (idx == 2) primary else secondary.copy(alpha = 0.35f),
              topLeft = Offset(w * fx - 24f, h * 0.74f - bh),
              size = Size(48f, bh),
              cornerRadius = CornerRadius(8f, 8f),
            )
            drawCenteredLabel(textMeasurer, syls[idx], Offset(w * fx, h * 0.87f), if (idx == 2) accentStyle else labelStyle)
          }
        }

        PracticalLabType.PHONETICS_CONSONANTS_LAB -> {
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.08f, h * 0.20f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.37f, h * 0.20f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(tertiary.copy(alpha = 0.18f), topLeft = Offset(w * 0.66f, h * 0.20f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "/t/ after p,k,f,s", Offset(w * 0.21f, h * 0.34f), labelStyle)
          drawCenteredLabel(textMeasurer, "/d/ after voiced", Offset(w * 0.50f, h * 0.34f), accentStyle)
          drawCenteredLabel(textMeasurer, "/ɪd/ after t, d", Offset(w * 0.79f, h * 0.34f), labelStyle)
          drawCenteredLabel(textMeasurer, "IPA Sound Contrast & -ed Past Allomorph Rule", Offset(w * 0.50f, h * 0.80f), accentStyle)
        }

        PracticalLabType.COMPREHENSION_ESSAY_LAB -> {
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.08f, h * 0.20f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.37f, h * 0.20f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(tertiary.copy(alpha = 0.18f), topLeft = Offset(w * 0.66f, h * 0.20f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "1. Locate Idea", Offset(w * 0.21f, h * 0.34f), labelStyle)
          drawCenteredLabel(textMeasurer, "2. Paraphrase", Offset(w * 0.50f, h * 0.34f), accentStyle)
          drawCenteredLabel(textMeasurer, "3. One Sentence", Offset(w * 0.79f, h * 0.34f), labelStyle)
          drawCenteredLabel(textMeasurer, "Avoid Lifted-Phrase Penalties • Format Formal Letters Properly", Offset(w * 0.50f, h * 0.80f), labelStyle)
        }

        PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB -> {
          drawLine(onSurface, Offset(w * 0.14f, h * 0.12f), Offset(w * 0.14f, h * 0.78f), 2.5f)
          drawLine(onSurface, Offset(w * 0.14f, h * 0.78f), Offset(w * 0.62f, h * 0.78f), 2.5f)
          drawLine(primary, Offset(w * 0.20f, h * 0.18f), Offset(w * 0.58f, h * 0.70f), 4f)
          drawLine(successColor, Offset(w * 0.20f, h * 0.70f), Offset(w * 0.58f, h * 0.18f), 4f)
          drawLine(errorColor, Offset(w * 0.20f, h * 0.56f), Offset(w * 0.58f, h * 0.08f), 3f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f), 0f))
          drawCircle(tertiary, radius = 7f, center = Offset(w * 0.39f, h * 0.44f))
          drawCenteredLabel(textMeasurer, "E* (P*, Q*)", Offset(w * 0.46f, h * 0.48f), accentStyle)
          drawCenteredLabel(textMeasurer, "|PED| = %ΔQ / %ΔP", Offset(w * 0.79f, h * 0.26f), accentStyle)
          drawCenteredLabel(textMeasurer, "Excise Tax Shift", Offset(w * 0.79f, h * 0.50f), errStyle)
          drawCenteredLabel(textMeasurer, "Demand & Supply Equilibrium • Elasticity & Cost Curves", Offset(w * 0.50f, h * 0.90f), labelStyle)
        }

        PracticalLabType.ECONOMICS_MACRO_POLICY_LAB -> {
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.08f, h * 0.22f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.37f, h * 0.22f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(tertiary.copy(alpha = 0.18f), topLeft = Offset(w * 0.66f, h * 0.22f), size = Size(w * 0.26f, 44f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "k = 1 / (1−MPC)", Offset(w * 0.21f, h * 0.36f), accentStyle)
          drawCenteredLabel(textMeasurer, "MV = PT", Offset(w * 0.50f, h * 0.36f), labelStyle)
          drawCenteredLabel(textMeasurer, "ToT = Px/Pm×100", Offset(w * 0.79f, h * 0.36f), labelStyle)
          drawCenteredLabel(textMeasurer, "Circular Flow: I + G + X = S + T + M • CBN OMO Policy", Offset(w * 0.50f, h * 0.80f), labelStyle)
        }

        PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB -> {
          drawRoundRect(primary.copy(alpha = 0.16f), topLeft = Offset(w * 0.08f, h * 0.18f), size = Size(w * 0.25f, 42f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(secondary.copy(alpha = 0.18f), topLeft = Offset(w * 0.375f, h * 0.18f), size = Size(w * 0.25f, 42f), cornerRadius = CornerRadius(8f, 8f))
          drawRoundRect(tertiary.copy(alpha = 0.18f), topLeft = Offset(w * 0.67f, h * 0.18f), size = Size(w * 0.25f, 42f), cornerRadius = CornerRadius(8f, 8f))
          drawCenteredLabel(textMeasurer, "Legislature", Offset(w * 0.205f, h * 0.31f), accentStyle)
          drawCenteredLabel(textMeasurer, "Executive", Offset(w * 0.50f, h * 0.31f), labelStyle)
          drawCenteredLabel(textMeasurer, "Judiciary", Offset(w * 0.795f, h * 0.31f), labelStyle)
          drawCenteredLabel(textMeasurer, "Federal Lists: Exclusive (Fed) • Concurrent • Residual (States)", Offset(w * 0.50f, h * 0.78f), accentStyle)
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
