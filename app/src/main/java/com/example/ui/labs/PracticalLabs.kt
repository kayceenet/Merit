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
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText
import com.example.ui.theme.semanticColors
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

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
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp),
  ) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
      Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(99.dp),
      ) {
        Text(
          text = "INTERACTIVE VIRTUAL LAB",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
        )
      }
      Text(
        text = labType.title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface,
      )
      ScientificText(
        text = labBrief,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }

    when (labType) {
      PracticalLabType.PROJECTILE_KINEMATICS -> ProjectileKinematicsLab()
      PracticalLabType.OHMS_CIRCUIT -> OhmsCircuitLab()
      PracticalLabType.WAVE_OPTICS_BENCH -> WaveOpticsBenchLab()
      PracticalLabType.QUADRATIC_TANGENT -> QuadraticTangentLab()
      PracticalLabType.TITRATION_PH -> TitrationPhLab()
      PracticalLabType.GAS_LAW_PISTON -> GasLawPistonLab()
      PracticalLabType.MENDELIAN_GENETICS -> MendelianGeneticsLab()
      PracticalLabType.ECOLOGY_ENERGY_PYRAMID -> EcologyEnergyPyramidLab()
      PracticalLabType.ORAL_ENGLISH_STRESS -> OralEnglishStressLab()
      PracticalLabType.SYNTAX_LEXIS_STUDIO -> SyntaxLexisStudioLab()
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
    ScientificText(
      text = label,
      style = MaterialTheme.typography.labelSmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontWeight = FontWeight.Bold,
    )
    ScientificText(
      text = value,
      style = MaterialTheme.typography.titleSmall.copy(fontFeatureSettings = "tnum"),
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
          .clip(RoundedCornerShape(14.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
    ) {
      val pad = 28f
      val plotW = size.width - pad * 2f
      val plotH = size.height - pad * 2f
      val maxRefRange = 360.0
      val maxRefHeight = 160.0

      for (i in 0..4) {
        val y = pad + plotH * (i / 4f)
        drawLine(gridColor, Offset(pad, y), Offset(pad + plotW, y), strokeWidth = 1.5f)
      }
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
      ScientificText(
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
          .clip(RoundedCornerShape(14.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
    ) {
      val left = 50f
      val right = size.width - 50f
      val top = 36f
      val bottom = size.height - 32f
      val midY = (top + bottom) / 2f

      drawLine(primary, Offset(left, top), Offset(left, midY - 12f), strokeWidth = 5f)
      drawLine(tertiary, Offset(left - 18f, midY - 12f), Offset(left + 18f, midY - 12f), strokeWidth = 6f)
      drawLine(tertiary, Offset(left - 10f, midY + 12f), Offset(left + 10f, midY + 12f), strokeWidth = 6f)
      drawLine(primary, Offset(left, midY + 12f), Offset(left, bottom), strokeWidth = 5f)

      if (isParallel) {
        val branchX1 = left + (right - left) * 0.3f
        val branchX2 = left + (right - left) * 0.8f
        drawLine(primary, Offset(left, top), Offset(branchX2, top), strokeWidth = 5f)
        drawLine(primary, Offset(left, bottom), Offset(branchX2, bottom), strokeWidth = 5f)
        drawLine(secondary, Offset(branchX1, top), Offset(branchX1, bottom), strokeWidth = 6f)
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
private fun WaveOpticsBenchLab() {
  var incidentDeg by remember { mutableFloatStateOf(35f) }
  var nMedium by remember { mutableFloatStateOf(1.50f) } // Glass default
  var frequencyHz by remember { mutableFloatStateOf(5.0f) }

  val sinTheta2 = sin(incidentDeg * PI / 180.0) / nMedium
  val refractedDeg = (asin(sinTheta2.coerceIn(-1.0, 1.0)) * 180.0 / PI).toFloat()
  val criticalDeg = (asin((1.0 / nMedium).coerceIn(-1.0, 1.0)) * 180.0 / PI).toFloat()
  val waveSpeed = 300f / nMedium
  val wavelengthNm = waveSpeed / frequencyHz

  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val tertiary = MaterialTheme.colorScheme.tertiary
  val outline = MaterialTheme.colorScheme.outline

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = abs(nMedium - 1.33f) < 0.03f, onClick = { nMedium = 1.33f }, label = { Text("Water (1.33)") })
      FilterChip(selected = abs(nMedium - 1.50f) < 0.03f, onClick = { nMedium = 1.50f }, label = { Text("Glass (1.50)") })
      FilterChip(selected = abs(nMedium - 2.42f) < 0.03f, onClick = { nMedium = 2.42f }, label = { Text("Diamond (2.42)") })
    }

    Canvas(
      modifier =
        Modifier.fillMaxWidth()
          .height(180.dp)
          .clip(RoundedCornerShape(14.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
    ) {
      val midY = size.height / 2f
      val midX = size.width / 2f
      // Medium 2 shaded block
      drawRect(color = primary.copy(alpha = 0.08f), topLeft = Offset(0f, midY), size = Size(size.width, midY))
      // Interface & normal
      drawLine(outline, Offset(0f, midY), Offset(size.width, midY), strokeWidth = 3f)
      drawLine(
        color = outline.copy(alpha = 0.7f),
        start = Offset(midX, 12f),
        end = Offset(midX, size.height - 12f),
        strokeWidth = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f)),
      )

      val len = size.height * 0.44f
      val incRad = incidentDeg * PI / 180.0
      val refRad = refractedDeg * PI / 180.0

      val startX = (midX - len * sin(incRad)).toFloat()
      val startY = (midY - len * cos(incRad)).toFloat()
      drawLine(tertiary, Offset(startX, startY), Offset(midX, midY), strokeWidth = 6f)

      val endX = (midX + len * sin(refRad)).toFloat()
      val endY = (midY + len * cos(refRad)).toFloat()
      drawLine(secondary, Offset(midX, midY), Offset(endX, endY), strokeWidth = 6f)
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      LabMetricTile("REFRACT r", String.format("%.1f°", refractedDeg), Modifier.weight(1f))
      LabMetricTile("CRITICAL c", String.format("%.1f°", criticalDeg), Modifier.weight(1f))
      LabMetricTile("v in MEDIUM", String.format("%.2f×10⁸", 3.0f / nMedium), Modifier.weight(1f))
    }

    Text("Angle of Incidence i = ${incidentDeg.toInt()}°", style = MaterialTheme.typography.labelMedium)
    Slider(value = incidentDeg, onValueChange = { incidentDeg = it }, valueRange = 5f..80f)

    Text("Wave Frequency f = ${String.format("%.1f", frequencyHz)} × 10¹⁴ Hz (Relative λ = ${String.format("%.1f", wavelengthNm)})", style = MaterialTheme.typography.labelMedium)
    Slider(value = frequencyHz, onValueChange = { frequencyHz = it }, valueRange = 3.0f..8.0f)
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
          .clip(RoundedCornerShape(14.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
    ) {
      val cx = size.width / 2f
      val cy = size.height * 0.55f
      val scaleX = size.width / 12f
      val scaleY = size.height / 24f

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

    ScientificText(
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
          .clip(RoundedCornerShape(14.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
    ) {
      val pad = 26f
      val w = size.width - pad * 2f
      val h = size.height - pad * 2f

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

@Composable
private fun GasLawPistonLab() {
  var tempCelsius by remember { mutableFloatStateOf(27f) }
  var pressureAtm by remember { mutableFloatStateOf(1.0f) }
  var molarMass by remember { mutableFloatStateOf(16f) } // CH4 default

  val tempKelvin = tempCelsius + 273f
  // 1 mole ideal gas volume in dm³: V = nRT/P, R = 0.0821 L·atm/(mol·K)
  val volumeLitres = (0.0821f * tempKelvin) / pressureAtm
  val grahamSpeedVsO2 = sqrt(32.0 / molarMass).toFloat()

  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val outline = MaterialTheme.colorScheme.outline

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = abs(molarMass - 2f) < 0.5f, onClick = { molarMass = 2f }, label = { Text("H₂ (M=2)") })
      FilterChip(selected = abs(molarMass - 16f) < 0.5f, onClick = { molarMass = 16f }, label = { Text("CH₄ (M=16)") })
      FilterChip(selected = abs(molarMass - 64f) < 0.5f, onClick = { molarMass = 64f }, label = { Text("SO₂ (M=64)") })
    }

    Canvas(
      modifier =
        Modifier.fillMaxWidth()
          .height(110.dp)
          .clip(RoundedCornerShape(14.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
    ) {
      val fillFrac = (volumeLitres / 60f).coerceIn(0.12f, 0.92f)
      val chamberW = size.width - 40f
      val gasW = chamberW * fillFrac
      drawRoundRect(
        color = primary.copy(alpha = 0.22f),
        topLeft = Offset(20f, 20f),
        size = Size(gasW, size.height - 40f),
        cornerRadius = CornerRadius(10f, 10f),
      )
      drawLine(secondary, Offset(20f + gasW, 14f), Offset(20f + gasW, size.height - 14f), strokeWidth = 10f)
      drawRect(outline, topLeft = Offset(20f, 20f), size = Size(chamberW, size.height - 40f), style = Stroke(3f))
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      LabMetricTile("KELVIN (T)", "${tempKelvin.toInt()} K", Modifier.weight(1f))
      LabMetricTile("VOLUME (V)", String.format("%.1f dm³", volumeLitres), Modifier.weight(1f))
      LabMetricTile("DIFFUSE vs O₂", String.format("%.2f×", grahamSpeedVsO2), Modifier.weight(1f))
    }

    Text("Temperature t = ${tempCelsius.toInt()} °C (${tempKelvin.toInt()} K)", style = MaterialTheme.typography.labelMedium)
    Slider(value = tempCelsius, onValueChange = { tempCelsius = it }, valueRange = 0f..350f)

    Text("Pressure P = ${String.format("%.2f", pressureAtm)} atm", style = MaterialTheme.typography.labelMedium)
    Slider(value = pressureAtm, onValueChange = { pressureAtm = it }, valueRange = 0.5f..3.0f)
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
                  .clip(RoundedCornerShape(12.dp))
                  .background(bg)
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
                  .padding(vertical = 14.dp),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = "Hb$child",
                style = MaterialTheme.typography.titleMedium.copy(fontFeatureSettings = "tnum"),
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

@Composable
private fun EcologyEnergyPyramidLab() {
  var producerEnergy by remember { mutableFloatStateOf(10000f) }
  var transferEffPct by remember { mutableFloatStateOf(10f) }

  val eff = transferEffPct / 100f
  val primaryConsumer = producerEnergy * eff
  val secondaryConsumer = primaryConsumer * eff
  val tertiaryConsumer = secondaryConsumer * eff

  val semantics = MaterialTheme.semanticColors
  val levels =
    listOf(
      Triple("Tertiary Consumers (Hawks)", tertiaryConsumer, MaterialTheme.colorScheme.error),
      Triple("Secondary Consumers (Snakes)", secondaryConsumer, MaterialTheme.colorScheme.tertiary),
      Triple("Primary Consumers (Grasshoppers)", primaryConsumer, MaterialTheme.colorScheme.primary),
      Triple("Producers (Maize / Grass)", producerEnergy, semantics.success),
    )

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    levels.forEachIndexed { idx, (name, energy, barColor) ->
      val widthFraction = (0.35f + idx * 0.21f).coerceIn(0.3f, 1f)
      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Text(name, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.SemiBold)
          Text(
            "${energy.toInt()} kcal",
            style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
            color = barColor,
            fontWeight = FontWeight.Bold,
          )
        }
        Box(
          modifier =
            Modifier.fillMaxWidth(widthFraction)
              .height(16.dp)
              .clip(RoundedCornerShape(6.dp))
              .background(barColor.copy(alpha = 0.85f))
        )
      }
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      LabMetricTile("PRODUCERS", "${producerEnergy.toInt()} kcal", Modifier.weight(1f))
      LabMetricTile("PRIMARY", "${primaryConsumer.toInt()} kcal", Modifier.weight(1f))
      LabMetricTile("TERTIARY", "${String.format("%.1f", tertiaryConsumer)} kcal", Modifier.weight(1f))
    }

    Text("Producer Fixed Solar Energy = ${producerEnergy.toInt()} kcal", style = MaterialTheme.typography.labelMedium)
    Slider(value = producerEnergy, onValueChange = { producerEnergy = it }, valueRange = 2000f..20000f)

    Text("Lindeman Trophic Transfer Efficiency = ${transferEffPct.toInt()}% (90% lost as heat)", style = MaterialTheme.typography.labelMedium)
    Slider(value = transferEffPct, onValueChange = { transferEffPct = it }, valueRange = 5f..20f)
  }
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
          .clip(RoundedCornerShape(14.dp))
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
            style = MaterialTheme.typography.titleSmall,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SyntaxLexisStudioLab() {
  val rules = remember {
    listOf(
      Triple(
        "Parenthetical Subject ('as well as')",
        "The Vice-Chancellor, as well as the Deans, ___ attending convocation.",
        "Correct: IS (ignore the parenthetical phrase between commas; match 'Vice-Chancellor').",
      ),
      Triple(
        "Proximity Concord ('neither...nor')",
        "Neither the captain nor the defenders ___ ready for the penalty kick.",
        "Correct: WERE (verb agrees with closer plural noun 'defenders').",
      ),
      Triple(
        "Subjunctive / 'It is high time'",
        "It is high time the candidates ___ revising past questions.",
        "Correct: STARTED ('It is high time + subject' takes past tense verb!).",
      ),
      Triple(
        "Emphatic Contrast Focus",
        "ADEWALE bought a RED bicycle yesterday.",
        "If 'RED' is stressed, question must test color: 'Did Adewale buy a BLUE bicycle yesterday?'",
      ),
    )
  }
  var idx by remember { mutableIntStateOf(0) }
  val active = rules[idx]

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Text("Select a High-Yield Grammar / Lexis Pattern:", style = MaterialTheme.typography.labelMedium)
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
      rules.forEachIndexed { i, item ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(item.first) })
      }
    }
    Surface(
      color = MaterialTheme.colorScheme.background,
      shape = RoundedCornerShape(12.dp),
      modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp)),
    ) {
      Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(text = active.second, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
        Text(text = active.third, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
      }
    }
  }
}
