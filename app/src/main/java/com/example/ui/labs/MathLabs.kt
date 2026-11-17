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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText
import com.example.ui.theme.semanticColors
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

@Composable
internal fun MathLabDispatcher(lessonId: String, labType: PracticalLabType) {
  when (labType) {
    PracticalLabType.NUMBER_BASE_INDICES_LAB -> {
      if (lessonId == "math_01_bases_modular") NumberBaseModularLab() else IndicesLogSimulatorLab()
    }
    PracticalLabType.SETS_VENN_LAB -> VennDiagramLab()
    PracticalLabType.ALGEBRA_VARIATION_LAB -> VariationInequalityLab()
    PracticalLabType.QUADRATIC_TANGENT -> QuadraticRootsVertexLab()
    PracticalLabType.SEQUENCE_MATRIX_LAB -> {
      if (lessonId == "math_06_sequences_binary") ApGpProgressionLab() else MatrixDeterminantLab()
    }
    PracticalLabType.POLYGON_CIRCLE_THEOREM_LAB -> {
      if (lessonId == "math_08_polygons_parallels") PolygonAngleLab() else CircleTheoremWorkbenchLab()
    }
    PracticalLabType.MENSURATION_3D_LAB -> MensurationSolidsLab()
    PracticalLabType.TRIG_BEARING_LAB -> TrigBearingCompassLab()
    PracticalLabType.COORDINATE_CALCULUS_LAB -> CalculusTangentAreaLab()
    PracticalLabType.PROBABILITY_STATISTICS_LAB -> {
      if (lessonId == "math_14_perm_comb_prob") PermCombProbLab() else StatisticsDispersionLab()
    }
    else -> VennDiagramLab()
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun NumberBaseModularLab() {
  var value10 by remember { mutableIntStateOf(45) }
  var base by remember { mutableIntStateOf(2) }
  var modN by remember { mutableIntStateOf(7) }
  val converted = value10.toString(base).uppercase()
  val modResult = value10 % modN

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Base 10" to "$value10",
        "Base $base" to "$converted($base)",
        "Mod $modN" to "$modResult (mod $modN)",
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf(2, 5, 8, 16).forEach { b ->
        FilterChip(selected = base == b, onClick = { base = b }, label = { Text("Base $b") })
      }
    }
    Text("Decimal Number: $value10  |  Modulo Clock: Mod $modN", style = MaterialTheme.typography.labelLarge)
    Slider(value = value10.toFloat(), onValueChange = { value10 = it.roundToInt() }, valueRange = 1f..255f)
    Slider(value = modN.toFloat(), onValueChange = { modN = it.roundToInt() }, valueRange = 2f..12f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IndicesLogSimulatorLab() {
  var base by remember { mutableIntStateOf(2) }
  var exp by remember { mutableIntStateOf(4) }
  val value = base.toDouble().pow(exp.toDouble()).roundToInt()

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Index Form" to "$base^$exp = $value",
        "Log Form" to "log_$base($value) = $exp",
        "Inverse 1/$base^$exp" to "1/$value",
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf(2, 3, 5, 10).forEach { b ->
        FilterChip(selected = base == b, onClick = { base = b }, label = { Text("Base $b") })
      }
    }
    Text("Exponent Power n = $exp", style = MaterialTheme.typography.labelLarge)
    Slider(value = exp.toFloat(), onValueChange = { exp = it.roundToInt() }, valueRange = 1f..6f)
  }
}

@Composable
private fun VennDiagramLab() {
  var nU by remember { mutableIntStateOf(50) }
  var nA by remember { mutableIntStateOf(30) }
  var nB by remember { mutableIntStateOf(24) }
  var overlap by remember { mutableIntStateOf(10) }
  val maxOverlap = minOf(nA, nB)
  val clampedOverlap = overlap.coerceIn(0, maxOverlap)
  val aOnly = nA - clampedOverlap
  val bOnly = nB - clampedOverlap
  val union = aOnly + clampedOverlap + bOnly
  val neither = (nU - union).coerceAtLeast(0)
  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Canvas(modifier = Modifier.fillMaxWidth().height(130.dp)) {
      val r = size.height * 0.40f
      drawCircle(primary.copy(alpha = 0.28f), radius = r, center = Offset(size.width * 0.40f, size.height * 0.5f))
      drawCircle(primary, radius = r, center = Offset(size.width * 0.40f, size.height * 0.5f), style = Stroke(3.5f))
      drawCircle(secondary.copy(alpha = 0.28f), radius = r, center = Offset(size.width * 0.60f, size.height * 0.5f))
      drawCircle(secondary, radius = r, center = Offset(size.width * 0.60f, size.height * 0.5f), style = Stroke(3.5f))
    }
    LabMetricBanner(
      listOf(
        "A only / B only" to "$aOnly / $bOnly",
        "Both n(A∩B)" to "$clampedOverlap",
        "Neither" to "$neither",
      )
    )
    Text("n(U) = $nU  •  n(A) = $nA  •  n(B) = $nB  •  n(A∪B) = $union", style = MaterialTheme.typography.labelLarge)
    Slider(value = nA.toFloat(), onValueChange = { nA = it.roundToInt() }, valueRange = 5f..40f)
    Slider(value = nB.toFloat(), onValueChange = { nB = it.roundToInt() }, valueRange = 5f..40f)
    Slider(value = clampedOverlap.toFloat(), onValueChange = { overlap = it.roundToInt() }, valueRange = 0f..maxOverlap.toFloat().coerceAtLeast(1f))
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun VariationInequalityLab() {
  var isDirect by remember { mutableStateOf(true) }
  var k by remember { mutableFloatStateOf(12f) }
  var xVal by remember { mutableFloatStateOf(3f) }
  val yVal = if (isDirect) k * xVal else k / xVal

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Constant k" to String.format("%.1f", k),
        "Input x" to String.format("%.1f", xVal),
        "Output y" to String.format("%.2f", yVal),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = isDirect, onClick = { isDirect = true }, label = { Text("Direct (y = kx)") })
      FilterChip(selected = !isDirect, onClick = { isDirect = false }, label = { Text("Inverse (y = k/x)") })
    }
    Slider(value = k, onValueChange = { k = it }, valueRange = 2f..36f)
    Slider(value = xVal, onValueChange = { xVal = it }, valueRange = 1f..12f)
  }
}

@Composable
private fun QuadraticRootsVertexLab() {
  var a by remember { mutableFloatStateOf(1f) }
  var b by remember { mutableFloatStateOf(-4f) }
  var c by remember { mutableFloatStateOf(3f) }
  val disc = b * b - 4f * a * c
  val vertexX = -b / (2f * a)
  val vertexY = a * vertexX * vertexX + b * vertexX + c
  val primary = MaterialTheme.colorScheme.primary
  val outline = MaterialTheme.colorScheme.outlineVariant

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Canvas(modifier = Modifier.fillMaxWidth().height(135.dp)) {
      val midX = size.width / 2f
      val midY = size.height * 0.62f
      drawLine(outline, Offset(12f, midY), Offset(size.width - 12f, midY), strokeWidth = 2f)
      drawLine(outline, Offset(midX, 8f), Offset(midX, size.height - 8f), strokeWidth = 2f)
      val path = Path()
      for (i in 0..40) {
        val x = -6f + i * 0.3f
        val y = a * x * x + b * x + c
        val sx = midX + x * 24f
        val sy = (midY - y * 8f).coerceIn(6f, size.height - 6f)
        if (i == 0) path.moveTo(sx, sy) else path.lineTo(sx, sy)
      }
      drawPath(path, primary, style = Stroke(4.5f))
    }
    LabMetricBanner(
      listOf(
        "Discriminant Δ" to String.format("%.1f", disc),
        "Vertex (h, k)" to String.format("(%.1f, %.1f)", vertexX, vertexY),
        "Sum α+β / Prod" to String.format("%.1f / %.1f", -b / a, c / a),
      )
    )
    Text("b = ${String.format("%.1f", b)}  |  c = ${String.format("%.1f", c)}", style = MaterialTheme.typography.labelLarge)
    Slider(value = b, onValueChange = { b = (it * 2).roundToInt() / 2f }, valueRange = -8f..8f)
    Slider(value = c, onValueChange = { c = (it * 2).roundToInt() / 2f }, valueRange = -8f..8f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ApGpProgressionLab() {
  var isGp by remember { mutableStateOf(false) }
  var a by remember { mutableFloatStateOf(3f) }
  var step by remember { mutableFloatStateOf(2f) }
  var n by remember { mutableIntStateOf(5) }
  val tn = if (!isGp) a + (n - 1) * step else a * step.pow(n - 1)
  val sn = if (!isGp) (n / 2f) * (2f * a + (n - 1) * step) else if (abs(step - 1f) < 0.01f) a * n else a * (step.pow(n) - 1f) / (step - 1f)

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Term T_$n" to String.format("%.1f", tn),
        "Sum S_$n" to String.format("%.1f", sn),
        "Type" to if (isGp) "GP (×r)" else "AP (+d)",
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = !isGp, onClick = { isGp = false }, label = { Text("Arithmetic (AP)") })
      FilterChip(selected = isGp, onClick = { isGp = true; if (step > 3f) step = 2f }, label = { Text("Geometric (GP)") })
    }
    Text("a = ${a.roundToInt()}  |  ${if (isGp) "r" else "d"} = ${String.format("%.1f", step)}  |  n = $n", style = MaterialTheme.typography.labelLarge)
    Slider(value = step, onValueChange = { step = (it * 2).roundToInt() / 2f }, valueRange = if (isGp) 0.5f..3.0f else 1f..8f)
    Slider(value = n.toFloat(), onValueChange = { n = it.roundToInt() }, valueRange = 2f..8f)
  }
}

@Composable
private fun MatrixDeterminantLab() {
  var a by remember { mutableIntStateOf(4) }
  var b by remember { mutableIntStateOf(2) }
  var c by remember { mutableIntStateOf(3) }
  var d by remember { mutableIntStateOf(5) }
  val det = a * d - b * c

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Matrix" to "[[$a, $b], [$c, $d]]",
        "det = ad − bc" to "$det",
        "Invertible?" to if (det != 0) "YES (A⁻¹ exists)" else "SINGULAR (0!)",
      )
    )
    Text("Adjust Row 1 [a=$a, b=$b] and d=$d", style = MaterialTheme.typography.labelLarge)
    Slider(value = a.toFloat(), onValueChange = { a = it.roundToInt() }, valueRange = -5f..8f)
    Slider(value = b.toFloat(), onValueChange = { b = it.roundToInt() }, valueRange = -5f..8f)
    Slider(value = d.toFloat(), onValueChange = { d = it.roundToInt() }, valueRange = -5f..8f)
  }
}

@Composable
private fun PolygonAngleLab() {
  var n by remember { mutableIntStateOf(6) }
  val sumInt = (n - 2) * 180
  val eachInt = sumInt / n.toFloat()
  val eachExt = 360f / n

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Sides n" to "$n",
        "Interior Angle" to String.format("%.1f°", eachInt),
        "Exterior Angle" to String.format("%.1f°", eachExt),
      )
    )
    Text("Regular Polygon Sides n = $n  •  Interior Sum = $sumInt°", style = MaterialTheme.typography.labelLarge)
    Slider(value = n.toFloat(), onValueChange = { n = it.roundToInt() }, valueRange = 3f..12f)
  }
}

@Composable
private fun CircleTheoremWorkbenchLab() {
  var circAngleDeg by remember { mutableIntStateOf(42) }
  val centerAngle = circAngleDeg * 2
  val cyclicOpp = 180 - circAngleDeg
  val primary = MaterialTheme.colorScheme.primary
  val tertiary = MaterialTheme.colorScheme.tertiary
  val outline = MaterialTheme.colorScheme.outlineVariant

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Canvas(modifier = Modifier.fillMaxWidth().height(145.dp)) {
      val center = Offset(size.width / 2f, size.height * 0.54f)
      val r = size.height * 0.42f
      drawCircle(outline, radius = r, center = center, style = Stroke(3.5f))
      val leftPt = Offset(center.x - r * 0.78f, center.y + r * 0.62f)
      val rightPt = Offset(center.x + r * 0.78f, center.y + r * 0.62f)
      val topPt = Offset(center.x + (circAngleDeg - 45) * 1.4f, center.y - r * 0.92f)
      drawLine(primary, leftPt, topPt, strokeWidth = 4f)
      drawLine(primary, rightPt, topPt, strokeWidth = 4f)
      drawLine(tertiary, leftPt, center, strokeWidth = 4f)
      drawLine(tertiary, rightPt, center, strokeWidth = 4f)
      drawCircle(tertiary, radius = 6f, center = center)
    }
    LabMetricBanner(
      listOf(
        "Circumference θ" to "${circAngleDeg}°",
        "Center Angle 2θ" to "${centerAngle}°",
        "Cyclic Opposite" to "${cyclicOpp}°",
      )
    )
    Slider(value = circAngleDeg.toFloat(), onValueChange = { circAngleDeg = it.roundToInt() }, valueRange = 20f..80f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MensurationSolidsLab() {
  var solid by remember { mutableIntStateOf(0) } // 0=Cone, 1=Cylinder, 2=Sphere
  var r by remember { mutableFloatStateOf(6f) }
  var h by remember { mutableFloatStateOf(8f) }
  val vol = when (solid) {
    0 -> (1.0 / 3.0) * PI * r * r * h
    1 -> PI * r * r * h
    else -> (4.0 / 3.0) * PI * r * r * r
  }
  val area = when (solid) {
    0 -> {
      val l = sqrt(r * r + h * h)
      PI * r * (l + r)
    }
    1 -> 2.0 * PI * r * (r + h)
    else -> 4.0 * PI * r * r
  }

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Volume" to String.format("%.1f cm³", vol),
        "Total Area" to String.format("%.1f cm²", area),
        "Slant l" to if (solid == 0) String.format("%.1f cm", sqrt(r * r + h * h)) else "N/A",
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf("Cone", "Cylinder", "Sphere").forEachIndexed { i, s ->
        FilterChip(selected = solid == i, onClick = { solid = i }, label = { Text(s) })
      }
    }
    Text("Radius r = ${r.roundToInt()} cm  |  Height h = ${h.roundToInt()} cm", style = MaterialTheme.typography.labelLarge)
    Slider(value = r, onValueChange = { r = it }, valueRange = 2f..14f)
    if (solid != 2) Slider(value = h, onValueChange = { h = it }, valueRange = 3f..20f)
  }
}

@Composable
private fun TrigBearingCompassLab() {
  var bearingDeg by remember { mutableIntStateOf(60) }
  val rad = (90 - bearingDeg) * PI / 180.0
  val backBearing = (bearingDeg + 180) % 360
  val sinV = sin(bearingDeg * PI / 180.0)
  val cosV = cos(bearingDeg * PI / 180.0)
  val primary = MaterialTheme.colorScheme.primary
  val outline = MaterialTheme.colorScheme.outlineVariant

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Canvas(modifier = Modifier.fillMaxWidth().height(135.dp)) {
      val c = Offset(size.width / 2f, size.height / 2f)
      val r = size.height * 0.42f
      drawCircle(outline, radius = r, center = c, style = Stroke(3f))
      drawLine(outline, Offset(c.x, c.y - r), Offset(c.x, c.y + r), strokeWidth = 2f)
      drawLine(outline, Offset(c.x - r, c.y), Offset(c.x + r, c.y), strokeWidth = 2f)
      val tip = Offset(c.x + r * cos(rad).toFloat(), c.y - r * sin(rad).toFloat())
      drawLine(primary, c, tip, strokeWidth = 5f)
      drawCircle(primary, radius = 7f, center = tip)
    }
    LabMetricBanner(
      listOf(
        "Bearing" to String.format("%03d°", bearingDeg),
        "Back-Bearing" to String.format("%03d°", backBearing),
        "sin / cos θ" to String.format("%.2f / %.2f", sinV, cosV),
      )
    )
    Slider(value = bearingDeg.toFloat(), onValueChange = { bearingDeg = it.roundToInt() }, valueRange = 0f..359f)
  }
}

@Composable
private fun CalculusTangentAreaLab() {
  var x0 by remember { mutableFloatStateOf(2.0f) }
  // Curve: y = 0.5 x^2 => dy/dx = x; Integral 0..x0 = x0^3 / 6
  val y0 = 0.5f * x0 * x0
  val slope = x0
  val area = (x0 * x0 * x0) / 6f

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Point (x₀, y₀)" to String.format("(%.1f, %.2f)", x0, y0),
        "Slope dy/dx = x₀" to String.format("%.2f", slope),
        "Area ∫₀ˣ⁰ y dx" to String.format("%.2f", area),
      )
    )
    Text("Slide x₀ along y = ½x²: x₀ = ${String.format("%.1f", x0)}", style = MaterialTheme.typography.labelLarge)
    Slider(value = x0, onValueChange = { x0 = (it * 2).roundToInt() / 2f }, valueRange = 0.5f..5.0f)
  }
}

@Composable
private fun PermCombProbLab() {
  var n by remember { mutableIntStateOf(6) }
  var r by remember { mutableIntStateOf(2) }
  val safeR = r.coerceAtMost(n)
  fun fact(k: Int): Long = if (k <= 1) 1L else (2..k).fold(1L) { acc, v -> acc * v }
  val nPr = fact(n) / fact(n - safeR)
  val nCr = nPr / fact(safeR)

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "nPr (Order Matters)" to "$nPr",
        "nCr (Committee)" to "$nCr",
        "1-Draw Odds" to "1 / $nCr",
      )
    )
    Text("Pool Size n = $n  |  Choose r = $safeR", style = MaterialTheme.typography.labelLarge)
    Slider(value = n.toFloat(), onValueChange = { n = it.roundToInt() }, valueRange = 3f..9f)
    Slider(value = safeR.toFloat(), onValueChange = { r = it.roundToInt() }, valueRange = 1f..n.toFloat())
  }
}

@Composable
private fun StatisticsDispersionLab() {
  var shift by remember { mutableIntStateOf(0) }
  var scale by remember { mutableIntStateOf(1) }
  val base = listOf(2, 4, 6, 8, 10)
  val data = base.map { it * scale + shift }
  val mean = data.average()
  val variance = data.map { (it - mean) * (it - mean) }.average()
  val sd = sqrt(variance)

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Mean x̄" to String.format("%.1f", mean),
        "Variance σ²" to String.format("%.1f", variance),
        "Std Dev σ" to String.format("%.2f", sd),
      )
    )
    Text("Data Set: ${data.joinToString(", ")}", style = MaterialTheme.typography.labelLarge)
    Text("Add Constant (+$shift) — Notice SD stays constant! Multiply (×$scale) — Scales SD!", style = MaterialTheme.typography.bodySmall)
    Slider(value = shift.toFloat(), onValueChange = { shift = it.roundToInt() }, valueRange = 0f..10f)
    Slider(value = scale.toFloat(), onValueChange = { scale = it.roundToInt() }, valueRange = 1f..3f)
  }
}
