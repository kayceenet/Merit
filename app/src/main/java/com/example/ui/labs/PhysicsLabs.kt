package com.example.ui.labs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText
import com.example.ui.theme.semanticColors
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
internal fun PhysicsLabDispatcher(lessonId: String, labType: PracticalLabType) {
  when (labType) {
    PracticalLabType.MEASUREMENT_VECTOR_LAB -> {
      if (lessonId == "phys_01_units") VernierMicrometerLab() else VectorParallelogramLab()
    }
    PracticalLabType.PROJECTILE_KINEMATICS -> ProjectileKinematicsLab()
    PracticalLabType.TORQUE_MACHINES_LAB -> {
      if (lessonId == "phys_05_machines_pressure") SimpleMachineEfficiencyLab() else MomentBalanceLab()
    }
    PracticalLabType.SHM_FLUIDS_LAB -> {
      if (lessonId == "phys_07_fluids_elasticity") ArchimedesBuoyancyLab() else PendulumShmLab()
    }
    PracticalLabType.THERMAL_EXPANSION_CALORIMETRY -> {
      if (lessonId == "phys_08_heat_expansion") LinearExpansionLab() else CalorimetryPhaseLab()
    }
    PracticalLabType.WAVE_OPTICS_BENCH -> {
      when (lessonId) {
        "phys_10_waves_sound" -> SoundHarmonicsPipeLab()
        else -> WaveOpticsBenchLab()
      }
    }
    PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB -> CapacitorNetworkLab()
    PracticalLabType.OHMS_CIRCUIT -> OhmsCircuitLab()
    PracticalLabType.EM_TRANSFORMER_AC_LAB -> {
      if (lessonId == "phys_14_em_ac") TransformerAcResonanceLab() else LogicGateCircuitLab()
    }
    PracticalLabType.QUANTUM_NUCLEAR_LAB -> {
      if (lessonId == "phys_16_nuclear") HalfLifeDecayLab() else PhotoelectricQuantumLab()
    }
    else -> ProjectileKinematicsLab()
  }
}

@Composable
private fun VernierMicrometerLab() {
  var zeroErrorMm by remember { mutableFloatStateOf(0.03f) }
  var sleeveMm by remember { mutableFloatStateOf(3.5f) }
  var thimbleDiv by remember { mutableIntStateOf(28) }
  val observed = sleeveMm + thimbleDiv * 0.01f
  val trueReading = observed - zeroErrorMm

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Observed" to String.format("%.2f mm", observed),
        "Zero Error" to String.format("%+.2f mm", zeroErrorMm),
        "True Diameter" to String.format("%.2f mm", trueReading),
      )
    )
    Text("Main Sleeve: ${String.format("%.1f", sleeveMm)} mm", style = MaterialTheme.typography.labelLarge)
    Slider(value = sleeveMm, onValueChange = { sleeveMm = (it * 2f).roundToInt() / 2f }, valueRange = 0f..10f)
    Text("Thimble Division (×0.01 mm): $thimbleDiv", style = MaterialTheme.typography.labelLarge)
    Slider(value = thimbleDiv.toFloat(), onValueChange = { thimbleDiv = it.roundToInt() }, valueRange = 0f..49f)
    Text("Zero Error: ${String.format("%+.2f", zeroErrorMm)} mm", style = MaterialTheme.typography.labelLarge)
    Slider(value = zeroErrorMm, onValueChange = { zeroErrorMm = (it * 100f).roundToInt() / 100f }, valueRange = -0.10f..0.10f)
    ScientificText("True Reading = Observed − Zero Error (Positive error subtracts; Negative error adds!)", style = MaterialTheme.typography.bodySmall)
  }
}

@Composable
private fun VectorParallelogramLab() {
  var f1 by remember { mutableFloatStateOf(30f) }
  var f2 by remember { mutableFloatStateOf(40f) }
  var angleDeg by remember { mutableFloatStateOf(90f) }
  val rad = angleDeg * PI / 180.0
  val r = sqrt(f1 * f1 + f2 * f2 + 2.0 * f1 * f2 * cos(rad))
  val dirDeg = atan2(f2 * sin(rad), f1 + f2 * cos(rad)) * 180.0 / PI
  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val tertiary = MaterialTheme.colorScheme.tertiary

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Canvas(modifier = Modifier.fillMaxWidth().height(155.dp)) {
      val origin = Offset(size.width * 0.22f, size.height * 0.78f)
      val scale = 2.0f
      val p1 = Offset(origin.x + f1 * scale, origin.y)
      val p2 = Offset(origin.x + f2 * scale * cos(rad).toFloat(), origin.y - f2 * scale * sin(rad).toFloat())
      val pr = Offset(p1.x + (p2.x - origin.x), p2.y)
      drawLine(primary, origin, p1, strokeWidth = 5f)
      drawLine(secondary, origin, p2, strokeWidth = 5f)
      drawLine(tertiary, origin, pr, strokeWidth = 6f)
      drawCircle(tertiary, radius = 7f, center = pr)
    }
    LabMetricBanner(
      listOf(
        "Resultant R" to String.format("%.1f N", r),
        "Angle to F₁" to String.format("%.1f°", dirDeg),
        "Equilibrant" to String.format("%.1f N opp", r),
      )
    )
    Text("Force F₁: ${f1.roundToInt()} N  |  Force F₂: ${f2.roundToInt()} N  |  θ = ${angleDeg.roundToInt()}°", style = MaterialTheme.typography.labelLarge)
    Slider(value = f1, onValueChange = { f1 = it }, valueRange = 10f..60f)
    Slider(value = f2, onValueChange = { f2 = it }, valueRange = 10f..60f)
    Slider(value = angleDeg, onValueChange = { angleDeg = it }, valueRange = 0f..180f)
  }
}

@Composable
private fun ProjectileKinematicsLab() {
  var speed by remember { mutableFloatStateOf(30f) }
  var angleDeg by remember { mutableFloatStateOf(45f) }
  var gravity by remember { mutableFloatStateOf(10f) }
  val rad = angleDeg * PI / 180.0
  val flightTime = (2.0 * speed * sin(rad)) / gravity
  val maxHeight = (speed * speed * sin(rad) * sin(rad)) / (2.0 * gravity)
  val range = (speed * speed * sin(2.0 * rad)) / gravity
  val primary = MaterialTheme.colorScheme.primary
  val tertiary = MaterialTheme.colorScheme.tertiary
  val outline = MaterialTheme.colorScheme.outlineVariant

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Canvas(modifier = Modifier.fillMaxWidth().height(160.dp)) {
      val gy = size.height * 0.85f
      drawLine(outline, Offset(12f, gy), Offset(size.width - 12f, gy), strokeWidth = 3f)
      val maxR = (60f * 60f) / 9.8f
      val sx = (size.width - 40f) / maxR
      val sy = (size.height * 0.72f) / 140f
      val path = Path().apply {
        moveTo(20f, gy)
        for (i in 1..40) {
          val t = flightTime * i / 40.0
          val x = 20f + (speed * cos(rad) * t).toFloat() * sx
          val y = gy - (speed * sin(rad) * t - 0.5 * gravity * t * t).toFloat().coerceAtLeast(0f) * sy
          lineTo(x, y)
        }
      }
      drawPath(path, primary, style = Stroke(5f))
      drawCircle(tertiary, radius = 7f, center = Offset(20f + (range * 0.5).toFloat() * sx, gy - maxHeight.toFloat() * sy))
    }
    LabMetricBanner(
      listOf(
        "Max Height" to String.format("%.1f m", maxHeight),
        "Range R" to String.format("%.1f m", range),
        "Time T" to String.format("%.2f s", flightTime),
      )
    )
    Text("Speed u = ${speed.roundToInt()} m/s  |  Angle θ = ${angleDeg.roundToInt()}°  |  g = ${String.format("%.1f", gravity)} m/s²", style = MaterialTheme.typography.labelLarge)
    Slider(value = speed, onValueChange = { speed = it }, valueRange = 10f..60f)
    Slider(value = angleDeg, onValueChange = { angleDeg = it }, valueRange = 10f..85f)
  }
}

@Composable
private fun MomentBalanceLab() {
  var fLeft by remember { mutableFloatStateOf(40f) }
  var dLeft by remember { mutableFloatStateOf(3.0f) }
  var dRight by remember { mutableFloatStateOf(4.0f) }
  val balancingFright = (fLeft * dLeft) / dRight
  val acwTorque = fLeft * dLeft
  val semantics = MaterialTheme.semanticColors

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "ACW Moment" to String.format("%.1f N·m", acwTorque),
        "Balancing CW F₂" to String.format("%.1f N", balancingFright),
        "Pivot Reaction" to String.format("%.1f N", fLeft + balancingFright),
      )
    )
    Text("Left Force F₁: ${fLeft.roundToInt()} N at ${String.format("%.1f", dLeft)} m", style = MaterialTheme.typography.labelLarge)
    Slider(value = fLeft, onValueChange = { fLeft = it }, valueRange = 10f..80f)
    Slider(value = dLeft, onValueChange = { dLeft = it }, valueRange = 0.5f..5.0f)
    Text("Right Lever Arm d₂: ${String.format("%.1f", dRight)} m", style = MaterialTheme.typography.labelLarge)
    Slider(value = dRight, onValueChange = { dRight = it }, valueRange = 0.5f..5.0f)
    ScientificText("Equilibrium requires ∑F = 0 and Clockwise Moment = Anticlockwise Moment.", color = semantics.success, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SimpleMachineEfficiencyLab() {
  var vr by remember { mutableIntStateOf(4) }
  var load by remember { mutableFloatStateOf(400f) }
  var effort by remember { mutableFloatStateOf(125f) }
  val ma = load / effort
  val eff = (ma / vr) * 100f

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Mech Adv (L/E)" to String.format("%.2f", ma),
        "Vel Ratio (VR)" to "$vr",
        "Efficiency" to String.format("%.1f%%", eff.coerceAtMost(100f)),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf(2, 4, 5, 6).forEach { pulleys ->
        FilterChip(selected = vr == pulleys, onClick = { vr = pulleys }, label = { Text("VR = $pulleys") })
      }
    }
    Text("Load L = ${load.roundToInt()} N  |  Effort E = ${effort.roundToInt()} N", style = MaterialTheme.typography.labelLarge)
    Slider(value = load, onValueChange = { load = it }, valueRange = 100f..800f)
    Slider(value = effort, onValueChange = { effort = it }, valueRange = (load / vr).coerceAtLeast(30f)..400f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PendulumShmLab() {
  var lengthM by remember { mutableFloatStateOf(1.0f) }
  var gravity by remember { mutableFloatStateOf(9.81f) }
  val period = 2.0 * PI * sqrt(lengthM / gravity.toDouble())
  val freq = 1.0 / period

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Period T" to String.format("%.2f s", period),
        "Frequency f" to String.format("%.2f Hz", freq),
        "T² / L" to String.format("%.2f s²/m", (period * period) / lengthM),
      )
    )
    Text("String Length L: ${String.format("%.2f", lengthM)} m", style = MaterialTheme.typography.labelLarge)
    Slider(value = lengthM, onValueChange = { lengthM = it }, valueRange = 0.25f..2.50f)
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = gravity > 9f, onClick = { gravity = 9.81f }, label = { Text("Earth (9.81)") })
      FilterChip(selected = gravity < 3f, onClick = { gravity = 1.62f }, label = { Text("Moon (1.62)") })
    }
    ScientificText("Notice Pendulum Mass is omitted—Period T depends ONLY on √(L/g)!", style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ArchimedesBuoyancyLab() {
  var solidDensity by remember { mutableFloatStateOf(7800f) }
  var fluidDensity by remember { mutableFloatStateOf(1000f) }
  val volCm3 = 100f
  val weightAirN = (solidDensity * (volCm3 / 1_000_000f)) * 10f
  val upthrustN = (fluidDensity * (volCm3 / 1_000_000f)) * 10f
  val apparentN = (weightAirN - upthrustN).coerceAtLeast(0f)
  val floats = solidDensity <= fluidDensity

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Weight in Air" to String.format("%.2f N", weightAirN),
        "Upthrust" to String.format("%.2f N", upthrustN),
        "Status" to if (floats) "FLOATS" else String.format("Apparent %.2f N", apparentN),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = solidDensity < 900f, onClick = { solidDensity = 700f }, label = { Text("Wood (700)") })
      FilterChip(selected = solidDensity in 2500f..3000f, onClick = { solidDensity = 2700f }, label = { Text("Alum (2700)") })
      FilterChip(selected = solidDensity > 7000f, onClick = { solidDensity = 7800f }, label = { Text("Steel (7800)") })
    }
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = fluidDensity < 1100f, onClick = { fluidDensity = 1000f }, label = { Text("Water (1000)") })
      FilterChip(selected = fluidDensity > 5000f, onClick = { fluidDensity = 13600f }, label = { Text("Mercury (13600)") })
    }
    ScientificText("Archimedes Principle: Upthrust = Weight of displaced fluid = ρ_fluid · V_sub · g.", style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LinearExpansionLab() {
  var l0 by remember { mutableFloatStateOf(2.0f) }
  var deltaT by remember { mutableFloatStateOf(100f) }
  var materialIdx by remember { mutableIntStateOf(0) }
  val alphas = listOf(1.2e-5, 1.9e-5, 2.4e-5)
  val names = listOf("Steel", "Brass", "Aluminium")
  val deltaLmm = l0 * alphas[materialIdx] * deltaT * 1000.0

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Original L₀" to String.format("%.1f m", l0),
        "Expansivity α" to "${alphas[materialIdx]} /K",
        "ΔL Gain" to String.format("%.2f mm", deltaLmm),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      names.forEachIndexed { i, n ->
        FilterChip(selected = materialIdx == i, onClick = { materialIdx = i }, label = { Text(n) })
      }
    }
    Text("Temperature Rise Δθ: ${deltaT.roundToInt()} °C", style = MaterialTheme.typography.labelLarge)
    Slider(value = deltaT, onValueChange = { deltaT = it }, valueRange = 10f..250f)
    Text("Original Bar Length L₀: ${String.format("%.1f", l0)} m", style = MaterialTheme.typography.labelLarge)
    Slider(value = l0, onValueChange = { l0 = it }, valueRange = 1f..10f)
  }
}

@Composable
private fun CalorimetryPhaseLab() {
  var heatKj by remember { mutableFloatStateOf(180f) }
  // 1 kg ice from -20°C (c_ice=2.1): 42kJ to 0°C -> +334 kJ melt (to 376 kJ) -> +420 kJ heat water (to 796 kJ) -> boil
  val tempC = when {
    heatKj < 42f -> -20f + heatKj / 2.1f
    heatKj <= 376f -> 0f
    heatKj < 796f -> (heatKj - 376f) / 4.2f
    else -> 100f
  }
  val phase = when {
    heatKj < 42f -> "Solid Ice Warming"
    heatKj <= 376f -> "Melting Plateau (0°C • Latent Heat L_f)"
    heatKj < 796f -> "Liquid Water Warming (mcΔT)"
    else -> "Boiling Plateau (100°C • L_v)"
  }
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Heat Supplied Q" to "${heatKj.roundToInt()} kJ",
        "Temperature" to String.format("%.1f °C", tempC),
        "Phase State" to phase.substringBefore(" ("),
      )
    )
    Text("Total Thermal Energy Applied to 1 kg H₂O: ${heatKj.roundToInt()} kJ", style = MaterialTheme.typography.labelLarge)
    Slider(value = heatKj, onValueChange = { heatKj = it }, valueRange = 0f..900f)
    ScientificText("Status: $phase — Notice Temperature freezes flat across phase changes!", style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SoundHarmonicsPipeLab() {
  var pipeLen by remember { mutableFloatStateOf(0.85f) }
  var isClosed by remember { mutableStateOf(true) }
  val vSound = 340f
  val f1 = if (isClosed) vSound / (4f * pipeLen) else vSound / (2f * pipeLen)
  val overtoneRatio = if (isClosed) "f, 3f, 5f (Odd only!)" else "f, 2f, 3f (All harmonics)"

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Fundamental f₀" to String.format("%.1f Hz", f1),
        "Wavelength λ₁" to String.format("%.2f m", vSound / f1),
        "Harmonics" to if (isClosed) "Odd (1,3,5)" else "All (1,2,3)",
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = isClosed, onClick = { isClosed = true }, label = { Text("Closed Pipe (L = λ/4)") })
      FilterChip(selected = !isClosed, onClick = { isClosed = false }, label = { Text("Open Pipe (L = λ/2)") })
    }
    Text("Air Column Length L = ${String.format("%.2f", pipeLen)} m  |  Pattern: $overtoneRatio", style = MaterialTheme.typography.labelLarge)
    Slider(value = pipeLen, onValueChange = { pipeLen = it }, valueRange = 0.25f..1.50f)
  }
}

@Composable
private fun WaveOpticsBenchLab() {
  var focal by remember { mutableFloatStateOf(15f) }
  var uDist by remember { mutableFloatStateOf(30f) }
  val denom = if (abs(uDist - focal) > 0.2f) (uDist - focal) else 0.2f
  val vDist = (focal * uDist) / denom
  val mag = abs(vDist / uDist)
  val imageNature = if (uDist > focal) "Real & Inverted" else "Virtual & Erect (Magnifier)"

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Focal f" to "${focal.roundToInt()} cm",
        "Object u" to "${uDist.roundToInt()} cm",
        "Image v" to String.format("%.1f cm", vDist),
      )
    )
    Text("Magnification M = ${String.format("%.2f", mag)}×  •  Nature: $imageNature", style = MaterialTheme.typography.labelLarge)
    Slider(value = focal, onValueChange = { focal = it }, valueRange = 10f..25f)
    Text("Object Distance u = ${uDist.roundToInt()} cm", style = MaterialTheme.typography.labelLarge)
    Slider(value = uDist, onValueChange = { uDist = it }, valueRange = 5f..60f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CapacitorNetworkLab() {
  var c1 by remember { mutableFloatStateOf(6f) }
  var c2 by remember { mutableFloatStateOf(3f) }
  var voltage by remember { mutableFloatStateOf(12f) }
  var isParallel by remember { mutableStateOf(true) }
  val cTotal = if (isParallel) c1 + c2 else (c1 * c2) / (c1 + c2)
  val energyUj = 0.5f * cTotal * voltage * voltage

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Total C_eq" to String.format("%.2f μF", cTotal),
        "Charge Q" to String.format("%.1f μC", cTotal * voltage),
        "Energy U" to String.format("%.0f μJ", energyUj),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = isParallel, onClick = { isParallel = true }, label = { Text("Parallel (C₁+C₂)") })
      FilterChip(selected = !isParallel, onClick = { isParallel = false }, label = { Text("Series (1/C₁+1/C₂)") })
    }
    Text("C₁ = ${c1.roundToInt()} μF  |  C₂ = ${c2.roundToInt()} μF  |  V = ${voltage.roundToInt()} V", style = MaterialTheme.typography.labelLarge)
    Slider(value = c1, onValueChange = { c1 = it }, valueRange = 1f..20f)
    Slider(value = c2, onValueChange = { c2 = it }, valueRange = 1f..20f)
    Slider(value = voltage, onValueChange = { voltage = it }, valueRange = 3f..24f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OhmsCircuitLab() {
  var emf by remember { mutableFloatStateOf(12f) }
  var r1 by remember { mutableFloatStateOf(4f) }
  var r2 by remember { mutableFloatStateOf(4f) }
  var internalR by remember { mutableFloatStateOf(0.5f) }
  var isSeries by remember { mutableStateOf(true) }
  val rExt = if (isSeries) r1 + r2 else (r1 * r2) / (r1 + r2)
  val current = emf / (rExt + internalR)
  val terminalV = current * rExt

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "R_ext" to String.format("%.2f Ω", rExt),
        "Current I" to String.format("%.2f A", current),
        "Terminal V" to String.format("%.2f V", terminalV),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = isSeries, onClick = { isSeries = true }, label = { Text("Series R₁+R₂") })
      FilterChip(selected = !isSeries, onClick = { isSeries = false }, label = { Text("Parallel R₁||R₂") })
    }
    Text("EMF = ${emf.roundToInt()} V • Internal r = ${String.format("%.1f", internalR)} Ω • R₁=${r1.roundToInt()}Ω, R₂=${r2.roundToInt()}Ω", style = MaterialTheme.typography.labelLarge)
    Slider(value = emf, onValueChange = { emf = it }, valueRange = 3f..24f)
    Slider(value = r1, onValueChange = { r1 = it }, valueRange = 1f..20f)
    Slider(value = r2, onValueChange = { r2 = it }, valueRange = 1f..20f)
  }
}

@Composable
private fun TransformerAcResonanceLab() {
  var vp by remember { mutableFloatStateOf(240f) }
  var np by remember { mutableIntStateOf(600) }
  var ns by remember { mutableIntStateOf(30) }
  val vs = vp * (ns.toFloat() / np.toFloat())
  val kind = if (ns > np) "Step-Up" else if (ns < np) "Step-Down" else "1:1 Isolator"

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Turns Np : Ns" to "$np : $ns",
        "Output Vs" to String.format("%.1f V", vs),
        "Type" to kind,
      )
    )
    Text("Primary Turns Np = $np  |  Secondary Turns Ns = $ns  |  Vp = ${vp.roundToInt()} V", style = MaterialTheme.typography.labelLarge)
    Slider(value = np.toFloat(), onValueChange = { np = it.roundToInt() }, valueRange = 100f..1000f)
    Slider(value = ns.toFloat(), onValueChange = { ns = it.roundToInt() }, valueRange = 20f..1200f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LogicGateCircuitLab() {
  var gate by remember { mutableIntStateOf(0) } // 0=AND, 1=OR, 2=NAND, 3=XOR
  var inA by remember { mutableStateOf(true) }
  var inB by remember { mutableStateOf(false) }
  val gates = listOf("AND", "OR", "NAND", "XOR")
  val out = when (gate) {
    0 -> inA && inB
    1 -> inA || inB
    2 -> !(inA && inB)
    else -> inA != inB
  }

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Input A" to if (inA) "1 (HIGH)" else "0 (LOW)",
        "Input B" to if (inB) "1 (HIGH)" else "0 (LOW)",
        "Output Y" to if (out) "1 (ON)" else "0 (OFF)",
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      gates.forEachIndexed { idx, g ->
        FilterChip(selected = gate == idx, onClick = { gate = idx }, label = { Text(g) })
      }
    }
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = inA, onClick = { inA = !inA }, label = { Text("Toggle A (${if (inA) 1 else 0})") })
      FilterChip(selected = inB, onClick = { inB = !inB }, label = { Text("Toggle B (${if (inB) 1 else 0})") })
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PhotoelectricQuantumLab() {
  var freqFactor by remember { mutableFloatStateOf(8.0f) } // ×10^14 Hz
  var workFunctionEv by remember { mutableFloatStateOf(2.1f) } // Potassium/Cesium/Zinc
  val photonEv = 0.4136f * freqFactor // h*f in eV (since h = 4.136e-15 eV·s)
  val ekMax = (photonEv - workFunctionEv).coerceAtLeast(0f)
  val emits = photonEv > workFunctionEv

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Photon E (hf)" to String.format("%.2f eV", photonEv),
        "Work Fn W₀" to String.format("%.2f eV", workFunctionEv),
        "Max KE" to if (emits) String.format("%.2f eV", ekMax) else "0 (Below f₀)",
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = workFunctionEv < 2.2f, onClick = { workFunctionEv = 2.1f }, label = { Text("Cesium (2.1 eV)") })
      FilterChip(selected = workFunctionEv in 2.2f..3.0f, onClick = { workFunctionEv = 2.4f }, label = { Text("Sodium (2.4 eV)") })
      FilterChip(selected = workFunctionEv > 3.5f, onClick = { workFunctionEv = 4.3f }, label = { Text("Zinc (4.3 eV)") })
    }
    Text("Light Frequency f = ${String.format("%.1f", freqFactor)} × 10¹⁴ Hz", style = MaterialTheme.typography.labelLarge)
    Slider(value = freqFactor, onValueChange = { freqFactor = it }, valueRange = 4.0f..14.0f)
  }
}

@Composable
private fun HalfLifeDecayLab() {
  var n0 by remember { mutableFloatStateOf(1600f) }
  var halfLives by remember { mutableFloatStateOf(2f) }
  val remaining = n0 * 0.5f.pow(halfLives)

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Initial N₀" to "${n0.roundToInt()}",
        "Half-Lives n" to String.format("%.1f t½", halfLives),
        "Remaining N" to "${remaining.roundToInt()}",
      )
    )
    Text("Number of Half-Lives Elapsed (t / t½): ${String.format("%.1f", halfLives)}", style = MaterialTheme.typography.labelLarge)
    Slider(value = halfLives, onValueChange = { halfLives = (it * 2f).roundToInt() / 2f }, valueRange = 0f..5f)
    ScientificText("Formula: N = N₀ × (1/2)ⁿ — Remaining fraction = ${String.format("%.1f%%", 100f * 0.5f.pow(halfLives))}.", style = MaterialTheme.typography.bodySmall)
  }
}
