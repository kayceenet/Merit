package com.example.ui.labs

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.semanticColors
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun PracticalLabWorkspace(
  labType: PracticalLabType,
  labBrief: String,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier =
      modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp),
  ) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
      Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(99.dp),
      ) {
        Text(
          text = "INTERACTIVE PRACTICAL LAB",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSecondaryContainer,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
        )
      }
      Text(text = labType.title, style = MaterialTheme.typography.titleLarge)
      Text(
        text = labBrief,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }

    when (labType) {
      PracticalLabType.PROJECTILE_KINEMATICS -> ProjectileKinematicsLab()
      PracticalLabType.OHMS_CIRCUIT -> OhmsCircuitLab()
      PracticalLabType.QUADRATIC_TANGENT -> QuadraticTangentLab()
      PracticalLabType.TITRATION_PH -> TitrationPhLab()
      PracticalLabType.MENDELIAN_GENETICS -> MendelianGeneticsLab()
      PracticalLabType.ORAL_ENGLISH_STRESS -> OralEnglishStressLab()
    }
  }
}

@Composable
private fun LabMetricTile(
  label: String,
  value: String,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier =
      modifier
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.background)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
        .padding(horizontal = 10.dp, vertical = 8.dp),
  ) {
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontWeight = FontWeight.Bold,
    )
    Text(
      text = value,
      style = MaterialTheme.typography.titleSmall.copy(fontFamily = JetBrainsMonoFontFamily),
      color = MaterialTheme.colorScheme.primary,
      fontWeight = FontWeight.Bold,
    )
  }
}

@Composable
private fun ProjectileKinematicsLab() {
  var speed by remember { mutableFloatStateOf(40f) }
  var angleDeg by remember { mutableFloatStateOf(30f) }
  var gravity by remember { mutableFloatStateOf(10f) }

  val rad = angleDeg * PI / 180.0
  val ux = speed * cos(rad)
  val uy = speed * sin(rad)
  val timeOfFlight = (2.0 * uy) / gravity
  val maxHeight = (uy * uy) / (2.0 * gravity)
  val range = ux * timeOfFlight

  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val tertiary = MaterialTheme.colorScheme.tertiary
  val gridColor = MaterialTheme.colorScheme.outlineVariant

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Canvas(
      modifier =
        Modifier.fillMaxWidth()
          .height(190.dp)
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
    ) {
      val pad = 28f
      val plotW = size.width - pad * 2f
      val plotH = size.height - pad * 2f
      val maxRefRange = 360.0
      val maxRefHeight = 160.0

      // Grid lines
      for (i in 0..4) {
        val y = pad + plotH * (i / 4f)
        drawLine(gridColor, Offset(pad, y), Offset(pad + plotW, y), strokeWidth = 1.5f)
      }
      // Ground axis
      drawLine(
        color = secondary,
        start = Offset(pad, pad + plotH),
        end = Offset(pad + plotW, pad + plotH),
        strokeWidth = 3f,
      )

      val path = Path()
      val steps = 48
      for (i in 0..steps) {
        val frac = i / steps.toFloat()
        val t = frac * timeOfFlight
        val xMeters = ux * t
        val yMeters = (uy * t) - (0.5 * gravity * t * t)
        val px = pad + ((xMeters / maxRefRange).toFloat().coerceIn(0f, 1f) * plotW)
        val py = (pad + plotH) - ((yMeters / maxRefHeight).toFloat().coerceIn(0f, 1f) * plotH)
        if (i == 0) path.moveTo(px, py) else path.lineTo(px, py)
      }
      drawPath(path, color = primary, style = Stroke(width = 5f))

      // Peak marker
      val peakPx = pad + (((range * 0.5) / maxRefRange).toFloat().coerceIn(0f, 1f) * plotW)
      val peakPy = (pad + plotH) - ((maxHeight / maxRefHeight).toFloat().coerceIn(0f, 1f) * plotH)
      drawLine(
        color = tertiary,
        start = Offset(peakPx, peakPy),
        end = Offset(peakPx, pad + plotH),
        strokeWidth = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f)),
      )
      drawCircle(color = tertiary, radius = 9f, center = Offset(peakPx, peakPy))
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      LabMetricTile("TIME (T)", String.format("%.2f s", timeOfFlight), Modifier.weight(1f))
      LabMetricTile("MAX H", String.format("%.1f m", maxHeight), Modifier.weight(1f))
      LabMetricTile("RANGE (R)", String.format("%.1f m", range), Modifier.weight(1f))
    }

    Column {
      Text(
        text = "Initial Velocity u = ${speed.toInt()} m/s   (u_x = ${String.format("%.1f", ux)}, u_y = ${String.format("%.1f", uy)})",
        style = MaterialTheme.typography.labelMedium,
      )
      Slider(
        value = speed,
        onValueChange = { speed = it },
        valueRange = 15f..60f,
        modifier = Modifier.testTag("lab_speed_slider"),
      )

      Text(
        text = "Launch Angle θ = ${angleDeg.toInt()}°",
        style = MaterialTheme.typography.labelMedium,
      )
      Slider(
        value = angleDeg,
        onValueChange = { angleDeg = it },
        valueRange = 10f..85f,
        modifier = Modifier.testTag("lab_angle_slider"),
      )

      Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterChip(
          selected = abs(gravity - 10f) < 0.1f,
          onClick = { gravity = 10f },
          label = { Text("Exam g = 10 m/s²") },
        )
        FilterChip(
          selected = abs(gravity - 9.81f) < 0.05f,
          onClick = { gravity = 9.81f },
          label = { Text("Earth 9.81") },
        )
        FilterChip(
          selected = abs(gravity - 1.62f) < 0.05f,
          onClick = { gravity = 1.62f },
          label = { Text("Moon 1.62") },
        )
      }
    }
  }
}

@Composable
private fun OhmsCircuitLab() {
  var voltage by remember { mutableFloatStateOf(12f) }
  var r1 by remember { mutableFloatStateOf(6f) }
  var r2 by remember { mutableFloatStateOf(3f) }
  var isParallel by remember { mutableStateOf(true) }

  val rEq = if (isParallel) (r1 * r2) / (r1 + r2) else (r1 + r2)
  val totalCurrent = voltage / rEq
  val power = voltage * totalCurrent

  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val tertiary = MaterialTheme.colorScheme.tertiary

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(
        selected = isParallel,
        onClick = { isParallel = true },
        label = { Text("Parallel Network") },
      )
      FilterChip(
        selected = !isParallel,
        onClick = { isParallel = false },
        label = { Text("Series Network") },
      )
    }

    Canvas(
      modifier =
        Modifier.fillMaxWidth()
          .height(155.dp)
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
    ) {
      val left = 50f
      val right = size.width - 50f
      val top = 36f
      val bottom = size.height - 32f
      val midY = (top + bottom) / 2f

      // Battery symbol on left
      drawLine(primary, Offset(left, top), Offset(left, midY - 12f), strokeWidth = 5f)
      drawLine(tertiary, Offset(left - 18f, midY - 12f), Offset(left + 18f, midY - 12f), strokeWidth = 6f)
      drawLine(tertiary, Offset(left - 10f, midY + 12f), Offset(left + 10f, midY + 12f), strokeWidth = 6f)
      drawLine(primary, Offset(left, midY + 12f), Offset(left, bottom), strokeWidth = 5f)

      if (isParallel) {
        val branchX1 = left + (right - left) * 0.3f
        val branchX2 = left + (right - left) * 0.8f
        drawLine(primary, Offset(left, top), Offset(branchX2, top), strokeWidth = 5f)
        drawLine(primary, Offset(left, bottom), Offset(branchX2, bottom), strokeWidth = 5f)
        // Branch 1
        drawLine(secondary, Offset(branchX1, top), Offset(branchX1, bottom), strokeWidth = 6f)
        // Branch 2
        drawLine(secondary, Offset(branchX2, top), Offset(branchX2, bottom), strokeWidth = 6f)
        drawCircle(tertiary, radius = 14f, center = Offset(branchX1, midY))
        drawCircle(tertiary, radius = 14f, center = Offset(branchX2, midY))
      } else {
        drawLine(primary, Offset(left, top), Offset(right, top), strokeWidth = 5f)
        drawLine(primary, Offset(right, top), Offset(right, bottom), strokeWidth = 5f)
        drawLine(primary, Offset(left, bottom), Offset(right, bottom), strokeWidth = 5f)
        drawCircle(tertiary, radius = 15f, center = Offset(left + (right - left) * 0.35f, top))
        drawCircle(tertiary, radius = 15f, center = Offset(left + (right - left) * 0.7f, top))
      }
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      LabMetricTile("R_EQ", String.format("%.2f Ω", rEq), Modifier.weight(1f))
      LabMetricTile("CURRENT I", String.format("%.2f A", totalCurrent), Modifier.weight(1f))
      LabMetricTile("POWER P", String.format("%.1f W", power), Modifier.weight(1f))
    }

    Text("EMF V = ${voltage.toInt()} V", style = MaterialTheme.typography.labelMedium)
    Slider(value = voltage, onValueChange = { voltage = it }, valueRange = 4f..24f)

    Text("R₁ = ${r1.toInt()} Ω   |   R₂ = ${r2.toInt()} Ω", style = MaterialTheme.typography.labelMedium)
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
      Slider(value = r1, onValueChange = { r1 = it }, valueRange = 2f..12f, modifier = Modifier.weight(1f))
      Slider(value = r2, onValueChange = { r2 = it }, valueRange = 2f..12f, modifier = Modifier.weight(1f))
    }
  }
}

@Composable
private fun QuadraticTangentLab() {
  var a by remember { mutableFloatStateOf(1f) }
  var b by remember { mutableFloatStateOf(-4f) }
  var c by remember { mutableFloatStateOf(3f) }
  var x0 by remember { mutableFloatStateOf(2f) }

  val discriminant = b * b - 4f * a * c
  val slopeAtX0 = 2f * a * x0 + b
  val yAtX0 = a * x0 * x0 + b * x0 + c

  val primary = MaterialTheme.colorScheme.primary
  val tertiary = MaterialTheme.colorScheme.tertiary
  val axisColor = MaterialTheme.colorScheme.outline

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Canvas(
      modifier =
        Modifier.fillMaxWidth()
          .height(190.dp)
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
    ) {
      val cx = size.width / 2f
      val cy = size.height * 0.55f
      val scaleX = size.width / 12f
      val scaleY = size.height / 24f

      // Axes
      drawLine(axisColor, Offset(0f, cy), Offset(size.width, cy), strokeWidth = 2f)
      drawLine(axisColor, Offset(cx, 0f), Offset(cx, size.height), strokeWidth = 2f)

      val path = Path()
      val steps = 80
      for (i in 0..steps) {
        val x = -6f + (12f * i / steps.toFloat())
        val y = a * x * x + b * x + c
        val px = cx + x * scaleX
        val py = cy - y * scaleY
        if (i == 0) path.moveTo(px, py) else path.lineTo(px, py)
      }
      drawPath(path, color = primary, style = Stroke(width = 5f))

      // Tangent segment at x0
      val tx1 = x0 - 2.2f
      val ty1 = yAtX0 + slopeAtX0 * (tx1 - x0)
      val tx2 = x0 + 2.2f
      val ty2 = yAtX0 + slopeAtX0 * (tx2 - x0)
      drawLine(
        color = tertiary,
        start = Offset(cx + tx1 * scaleX, cy - ty1 * scaleY),
        end = Offset(cx + tx2 * scaleX, cy - ty2 * scaleY),
        strokeWidth = 4f,
      )
      drawCircle(color = tertiary, radius = 9f, center = Offset(cx + x0 * scaleX, cy - yAtX0 * scaleY))
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      LabMetricTile("Δ = b²−4ac", String.format("%.1f", discriminant), Modifier.weight(1f))
      LabMetricTile("dy/dx @ x₀", String.format("%.2f", slopeAtX0), Modifier.weight(1f))
      LabMetricTile("y(x₀)", String.format("%.2f", yAtX0), Modifier.weight(1f))
    }

    Text(
      text = "y = ${String.format("%.1f", a)}x² + (${String.format("%.1f", b)})x + (${String.format("%.1f", c)})   |   x₀ = ${String.format("%.1f", x0)}",
      style = MaterialTheme.typography.labelSmall,
    )
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
      Column(Modifier.weight(1f)) {
        Text("a = ${String.format("%.1f", a)}", style = MaterialTheme.typography.labelSmall)
        Slider(value = a, onValueChange = { if (abs(it) > 0.3f) a = it }, valueRange = -3f..3f)
      }
      Column(Modifier.weight(1f)) {
        Text("b = ${String.format("%.1f", b)}", style = MaterialTheme.typography.labelSmall)
        Slider(value = b, onValueChange = { b = it }, valueRange = -8f..8f)
      }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
      Column(Modifier.weight(1f)) {
        Text("c = ${String.format("%.1f", c)}", style = MaterialTheme.typography.labelSmall)
        Slider(value = c, onValueChange = { c = it }, valueRange = -6f..8f)
      }
      Column(Modifier.weight(1f)) {
        Text("Tangent x₀ = ${String.format("%.1f", x0)}", style = MaterialTheme.typography.labelSmall)
        Slider(value = x0, onValueChange = { x0 = it }, valueRange = -4f..4f)
      }
    }
  }
}

@Composable
private fun TitrationPhLab() {
  var baseAddedMl by remember { mutableFloatStateOf(20f) }
  var isStrongAcid by remember { mutableStateOf(true) }

  fun computePh(vBase: Float): Float {
    val vAcid = 25f
    val cAcid = 0.1f
    val cBase = 0.1f
    val nAcid = vAcid * cAcid
    val nBase = vBase * cBase
    val totalVol = vAcid + vBase
    return if (nBase < nAcid - 0.02f) {
      val hConc = (nAcid - nBase) / totalVol
      val strongPh = (-ln(hConc.toDouble()) / ln(10.0)).toFloat()
      if (isStrongAcid) strongPh.coerceIn(1f, 6.8f) else (strongPh + 1.8f).coerceIn(2.8f, 6.9f)
    } else if (abs(nBase - nAcid) <= 0.02f) {
      if (isStrongAcid) 7.0f else 8.7f
    } else {
      val ohConc = (nBase - nAcid) / totalVol
      val pOh = (-ln(ohConc.toDouble()) / ln(10.0)).toFloat()
      (14f - pOh).coerceIn(7.2f, 13.2f)
    }
  }

  val currentPh = computePh(baseAddedMl)
  val indicatorColor =
    when {
      currentPh < 4.4f -> Color(0xFFEF4444)
      currentPh < 8.2f -> Color(0xFFF59E0B)
      else -> Color(0xFFEC4899)
    }

  val primary = MaterialTheme.colorScheme.primary
  val gridColor = MaterialTheme.colorScheme.outlineVariant

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(
        selected = isStrongAcid,
        onClick = { isStrongAcid = true },
        label = { Text("Strong Acid (HCl)") },
      )
      FilterChip(
        selected = !isStrongAcid,
        onClick = { isStrongAcid = false },
        label = { Text("Weak Acid (CH₃COOH)") },
      )
    }

    Canvas(
      modifier =
        Modifier.fillMaxWidth()
          .height(175.dp)
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
    ) {
      val pad = 26f
      val w = size.width - pad * 2f
      val h = size.height - pad * 2f

      // pH = 7 line
      val y7 = pad + h * (1f - 7f / 14f)
      drawLine(gridColor, Offset(pad, y7), Offset(pad + w, y7), strokeWidth = 2f)

      val path = Path()
      for (i in 0..50) {
        val v = i.toFloat()
        val ph = computePh(v)
        val px = pad + (v / 50f) * w
        val py = pad + h * (1f - (ph / 14f))
        if (i == 0) path.moveTo(px, py) else path.lineTo(px, py)
      }
      drawPath(path, color = primary, style = Stroke(width = 5f))

      val curX = pad + (baseAddedMl / 50f) * w
      val curY = pad + h * (1f - (currentPh / 14f))
      drawCircle(color = indicatorColor, radius = 12f, center = Offset(curX, curY))
    }

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      LabMetricTile("NaOH ADDED", "${String.format("%.1f", baseAddedMl)} mL", Modifier.weight(1f))
      LabMetricTile("LIVE pH", String.format("%.2f", currentPh), Modifier.weight(1f))
      Row(
        modifier =
          Modifier.weight(1.2f)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f))
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Box(modifier = Modifier.size(16.dp).clip(CircleShape).background(indicatorColor))
        Text(
          text = if (currentPh >= 8.2f) "Phenolphthalein Pink" else "Colorless / Acidic",
          style = MaterialTheme.typography.labelSmall,
        )
      }
    }

    Text(
      text = "Volume of 0.10 M NaOH titrant added to 25.0 mL acid:",
      style = MaterialTheme.typography.labelMedium,
    )
    Slider(value = baseAddedMl, onValueChange = { baseAddedMl = it }, valueRange = 0f..50f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MendelianGeneticsLab() {
  var parent1 by remember { mutableStateOf("AS") }
  var parent2 by remember { mutableStateOf("AS") }

  val alleles1 = listOf(parent1[0].toString(), parent1[1].toString())
  val alleles2 = listOf(parent2[0].toString(), parent2[1].toString())
  val combos =
    listOf(
      normalizeGenotype(alleles1[0], alleles2[0]),
      normalizeGenotype(alleles1[0], alleles2[1]),
      normalizeGenotype(alleles1[1], alleles2[0]),
      normalizeGenotype(alleles1[1], alleles2[1]),
    )
  val aaPct = combos.count { it == "AA" } * 25
  val asPct = combos.count { it == "AS" } * 25
  val ssPct = combos.count { it == "SS" } * 25

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Text("Parent 1 Genotype:", style = MaterialTheme.typography.labelMedium)
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf("AA", "AS", "SS").forEach { g ->
        FilterChip(selected = parent1 == g, onClick = { parent1 = g }, label = { Text("Hb$g") })
      }
    }

    Text("Parent 2 Genotype:", style = MaterialTheme.typography.labelMedium)
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf("AA", "AS", "SS").forEach { g ->
        FilterChip(selected = parent2 == g, onClick = { parent2 = g }, label = { Text("Hb$g") })
      }
    }

    // 2x2 Punnett Grid
    val semantics = MaterialTheme.semanticColors
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      for (row in 0..1) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          for (col in 0..1) {
            val child = combos[row * 2 + col]
            val bg =
              when (child) {
                "AA" -> semantics.successContainer
                "AS" -> MaterialTheme.colorScheme.tertiaryContainer
                else -> MaterialTheme.colorScheme.errorContainer
              }
            val fg =
              when (child) {
                "AA" -> semantics.onSuccessContainer
                "AS" -> MaterialTheme.colorScheme.onTertiaryContainer
                else -> MaterialTheme.colorScheme.onErrorContainer
              }
            Box(
              modifier =
                Modifier.weight(1f)
                  .clip(RoundedCornerShape(14.dp))
                  .background(bg)
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
                  .padding(vertical = 14.dp),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = "Hb$child",
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = JetBrainsMonoFontFamily),
                color = fg,
                fontWeight = FontWeight.Bold,
              )
            }
          }
        }
      }
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      LabMetricTile("NORMAL AA", "$aaPct%", Modifier.weight(1f))
      LabMetricTile("TRAIT AS", "$asPct%", Modifier.weight(1f))
      LabMetricTile("ANEMIA SS", "$ssPct%", Modifier.weight(1f))
    }
  }
}

private fun normalizeGenotype(a: String, b: String): String =
  if (a == "A" || b == "A") {
    if (a == "A" && b == "A") "AA" else "AS"
  } else {
    "SS"
  }

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OralEnglishStressLab() {
  val wordFamilies = remember {
    listOf(
      Triple("pho-TO-gra-phy", 1, "Suffix -graphy attracts stress 2 syllables from end."),
      Triple("pho-to-GRA-phic", 2, "Suffix -ic attracts stress immediately before -ic."),
      Triple("e-du-CA-tion", 2, "Suffix -tion stresses the penultimate syllable."),
      Triple("con-DUCT (Verb)", 1, "2-syllable verbs stress the 2nd syllable."),
      Triple("CON-duct (Noun)", 0, "2-syllable nouns stress the 1st syllable."),
    )
  }
  var selectedIdx by remember { mutableIntStateOf(0) }
  val active = wordFamilies[selectedIdx]
  val syllables = active.first.substringBefore(" ").split("-")

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Text("Tap a JAMB stress pattern to inspect:", style = MaterialTheme.typography.labelMedium)
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
      wordFamilies.forEachIndexed { idx, item ->
        FilterChip(
          selected = selectedIdx == idx,
          onClick = { selectedIdx = idx },
          label = { Text(item.first) },
        )
      }
    }

    Row(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.background)
          .padding(14.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      syllables.forEachIndexed { i, syl ->
        val stressed = i == active.second
        Box(
          modifier =
            Modifier.weight(1f)
              .minimumInteractiveComponentSize()
              .clip(RoundedCornerShape(12.dp))
              .background(
                if (stressed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
              )
              .clickable { }
              .padding(vertical = 12.dp),
          contentAlignment = Alignment.Center,
        ) {
          Text(
            text = syl,
            style = MaterialTheme.typography.titleSmall.copy(fontFamily = JetBrainsMonoFontFamily),
            color = if (stressed) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = if (stressed) FontWeight.Bold else FontWeight.Medium,
          )
        }
      }
    }

    Text(
      text = "Rule: ${active.third}",
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.secondary,
      fontWeight = FontWeight.SemiBold,
    )
  }
}
