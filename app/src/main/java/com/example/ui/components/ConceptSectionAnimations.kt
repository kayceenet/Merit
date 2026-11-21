package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.theme.semanticColors
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

private enum class SectionVisualMode {
  MICROMETER_VERNIER,
  VECTOR_COMPONENTS,
  PROJECTILE_PARABOLA,
  LEVER_TORQUE_ORBIT,
  WAVE_HARMONIC,
  OPTICS_RAY_LENS,
  CIRCUIT_CAPACITOR,
  QUANTUM_DECAY,
  BASE_MODULAR_CLOCK,
  PARABOLA_CALCULUS,
  CIRCLE_TRIG_COMPASS,
  STATISTICS_BELL_OGIVE,
  BOHR_BOND_LATTICE,
  TITRATION_GAS_PISTON,
  KINETICS_ELECTROLYSIS,
  CELL_OSMOSIS_ENZYME,
  CIRCULATION_GENETICS_HELIX,
  ECOLOGY_NITROGEN_PYRAMID,
  SYNTAX_STRESS_PHONETICS,
  ECONOMICS_SUPPLY_PPC,
  GOVERNMENT_BRANCHES_LISTS,
}

private fun resolveSectionMode(
  heading: String,
  sectionIndex: Int,
  labType: PracticalLabType,
): SectionVisualMode {
  val h = heading.lowercase()
  return when {
    "micrometer" in h || "vernier" in h || "zero error" in h || "fundamental" in h || "dimension" in h ->
      SectionVisualMode.MICROMETER_VERNIER
    "vector" in h || "parallelogram" in h || "river" in h || "equilibrant" in h ->
      SectionVisualMode.VECTOR_COMPONENTS
    "projectile" in h || "kinematic" in h || "braking" in h || "velocity-time" in h ->
      SectionVisualMode.PROJECTILE_PARABOLA
    "moment" in h || "equilibrium" in h || "machine" in h || "pulley" in h || "gravitation" in h || "stability" in h ->
      SectionVisualMode.LEVER_TORQUE_ORBIT
    "wave" in h || "sound" in h || "drum" in h || "sonar" in h || "resonance" in h || "shm" in h || "pendulum" in h ->
      SectionVisualMode.WAVE_HARMONIC
    "optic" in h || "lens" in h || "refraction" in h || "fibre" in h || "telescope" in h || "eye" in h ->
      SectionVisualMode.OPTICS_RAY_LENS
    "circuit" in h || "ohm" in h || "capacitor" in h || "emf" in h || "potentiometer" in h || "transformer" in h || "induct" in h ->
      SectionVisualMode.CIRCUIT_CAPACITOR
    "photoelectric" in h || "quantum" in h || "nuclear" in h || "half-life" in h || "decay" in h || "logic gate" in h || "diode" in h ->
      SectionVisualMode.QUANTUM_DECAY
    "base" in h || "modular" in h || "percentage error" in h || "binary" in h || "index" in h || "logarithm" in h ->
      SectionVisualMode.BASE_MODULAR_CLOCK
    "calculus" in h || "integral" in h || "derivative" in h || "quadratic" in h || "parabol" in h || "linear programming" in h || "variation" in h ->
      SectionVisualMode.PARABOLA_CALCULUS
    "circle" in h || "cyclic" in h || "tangent" in h || "polygon" in h || "bearing" in h || "trig" in h || "earth" in h || "cone" in h ->
      SectionVisualMode.CIRCLE_TRIG_COMPASS
    "probability" in h || "statistics" in h || "variance" in h || "ogive" in h || "permutation" in h || "combination" in h || "matrix" in h || "venn" in h ->
      SectionVisualMode.STATISTICS_BELL_OGIVE
    "bohr" in h || "orbital" in h || "bonding" in h || "hydrogen bond" in h || "periodic" in h || "benzene" in h || "hydrocarbon" in h ->
      SectionVisualMode.BOHR_BOND_LATTICE
    "titration" in h || "acid" in h || "base" in h || "buffer" in h || "gas law" in h || "effusion" in h || "separation" in h || "distillation" in h ->
      SectionVisualMode.TITRATION_GAS_PISTON
    "equilibrium" in h || "haber" in h || "catalyst" in h || "enthalpy" in h || "electrolysis" in h || "redox" in h || "galvanic" in h || "blast" in h || "cation" in h ->
      SectionVisualMode.KINETICS_ELECTROLYSIS
    "osmosis" in h || "cell" in h || "enzyme" in h || "photosynthesis" in h || "respiration" in h || "mineral" in h ->
      SectionVisualMode.CELL_OSMOSIS_ENZYME
    "heart" in h || "circulation" in h || "kidney" in h || "nephron" in h || "dna" in h || "mendel" in h || "dihybrid" in h || "menstrual" in h ->
      SectionVisualMode.CIRCULATION_GENETICS_HELIX
    "ecology" in h || "pyramid" in h || "trophic" in h || "nitrogen" in h || "succession" in h || "biome" in h || "vector" in h ->
      SectionVisualMode.ECOLOGY_NITROGEN_PYRAMID
    labType in listOf(
      PracticalLabType.PARTS_CLAUSES_LAB,
      PracticalLabType.SYNTAX_LEXIS_STUDIO,
      PracticalLabType.LEXIS_IDIOMS_REGISTER_LAB,
      PracticalLabType.ORAL_ENGLISH_STRESS,
      PracticalLabType.PHONETICS_CONSONANTS_LAB,
      PracticalLabType.COMPREHENSION_ESSAY_LAB,
    ) -> SectionVisualMode.SYNTAX_STRESS_PHONETICS
    labType in listOf(
      PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB,
      PracticalLabType.ECONOMICS_MACRO_POLICY_LAB,
    ) -> SectionVisualMode.ECONOMICS_SUPPLY_PPC
    labType == PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB ->
      SectionVisualMode.GOVERNMENT_BRANCHES_LISTS
    else -> {
      val fallbackPool =
        when (labType) {
          PracticalLabType.MEASUREMENT_VECTOR_LAB -> SectionVisualMode.MICROMETER_VERNIER
          PracticalLabType.PROJECTILE_KINEMATICS -> SectionVisualMode.PROJECTILE_PARABOLA
          PracticalLabType.TORQUE_MACHINES_LAB -> SectionVisualMode.LEVER_TORQUE_ORBIT
          PracticalLabType.SHM_FLUIDS_LAB, PracticalLabType.WAVE_OPTICS_BENCH -> SectionVisualMode.WAVE_HARMONIC
          PracticalLabType.THERMAL_EXPANSION_CALORIMETRY, PracticalLabType.GAS_LAW_PISTON, PracticalLabType.TITRATION_PH ->
            SectionVisualMode.TITRATION_GAS_PISTON
          PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB, PracticalLabType.OHMS_CIRCUIT, PracticalLabType.EM_TRANSFORMER_AC_LAB ->
            SectionVisualMode.CIRCUIT_CAPACITOR
          PracticalLabType.QUANTUM_NUCLEAR_LAB -> SectionVisualMode.QUANTUM_DECAY
          PracticalLabType.NUMBER_BASE_INDICES_LAB -> SectionVisualMode.BASE_MODULAR_CLOCK
          PracticalLabType.SETS_VENN_LAB, PracticalLabType.PROBABILITY_STATISTICS_LAB -> SectionVisualMode.STATISTICS_BELL_OGIVE
          PracticalLabType.ALGEBRA_VARIATION_LAB, PracticalLabType.QUADRATIC_TANGENT, PracticalLabType.COORDINATE_CALCULUS_LAB ->
            SectionVisualMode.PARABOLA_CALCULUS
          PracticalLabType.POLYGON_CIRCLE_THEOREM_LAB, PracticalLabType.MENSURATION_3D_LAB, PracticalLabType.TRIG_BEARING_LAB ->
            SectionVisualMode.CIRCLE_TRIG_COMPASS
          PracticalLabType.SEQUENCE_MATRIX_LAB -> SectionVisualMode.STATISTICS_BELL_OGIVE
          PracticalLabType.SEPARATION_ATOMIC_LAB, PracticalLabType.BONDING_PERIODIC_LAB, PracticalLabType.ORGANIC_HYDROCARBON_LAB ->
            SectionVisualMode.BOHR_BOND_LATTICE
          PracticalLabType.STOICHIOMETRY_MOLE_LAB, PracticalLabType.ENERGETICS_KINETICS_LAB, PracticalLabType.REDOX_ELECTROLYSIS_LAB, PracticalLabType.INORGANIC_GASES_METALS_LAB, PracticalLabType.QUALITATIVE_ANALYSIS_LAB ->
            SectionVisualMode.KINETICS_ELECTROLYSIS
          PracticalLabType.CELL_OSMOSIS_LAB, PracticalLabType.BIOENERGETICS_ENZYME_LAB ->
            SectionVisualMode.CELL_OSMOSIS_ENZYME
          PracticalLabType.NUTRITION_TRANSPORT_LAB, PracticalLabType.RESPIRATION_KIDNEY_LAB, PracticalLabType.NERVOUS_ENDOCRINE_LAB, PracticalLabType.REPRODUCTION_FLOWER_LAB, PracticalLabType.MENDELIAN_GENETICS, PracticalLabType.EVOLUTION_VARIATION_LAB ->
            SectionVisualMode.CIRCULATION_GENETICS_HELIX
          PracticalLabType.ECOLOGY_ENERGY_PYRAMID, PracticalLabType.BIOGEOCHEMICAL_SOIL_LAB ->
            SectionVisualMode.ECOLOGY_NITROGEN_PYRAMID
          else -> SectionVisualMode.MICROMETER_VERNIER
        }
      if (sectionIndex % 2 == 1 && fallbackPool == SectionVisualMode.WAVE_HARMONIC) {
        SectionVisualMode.OPTICS_RAY_LENS
      } else {
        fallbackPool
      }
    }
  }
}

@Composable
fun ConceptSectionAnimationCard(
  lessonId: String,
  sectionIndex: Int,
  heading: String,
  labType: PracticalLabType,
  modifier: Modifier = Modifier,
) {
  val mode = remember(lessonId, sectionIndex, heading, labType) {
    resolveSectionMode(heading, sectionIndex, labType)
  }
  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val tertiary = MaterialTheme.colorScheme.tertiary
  val errorColor = MaterialTheme.colorScheme.error
  val onSurface = MaterialTheme.colorScheme.onSurface
  val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant
  val outline = MaterialTheme.colorScheme.outlineVariant
  val successColor = MaterialTheme.semanticColors.success
  val textMeasurer = rememberTextMeasurer()

  var isAnimating by remember(lessonId, sectionIndex) { mutableStateOf(true) }
  var pausedPhase by remember(lessonId, sectionIndex) { mutableFloatStateOf(0.25f) }
  val transition = rememberInfiniteTransition(label = "section_anim_${lessonId}_$sectionIndex")
  val livePhase by
    transition.animateFloat(
      initialValue = 0f,
      targetValue = 1f,
      animationSpec =
        infiniteRepeatable(
          animation = tween(durationMillis = 4200, easing = LinearEasing),
          repeatMode = RepeatMode.Restart,
        ),
      label = "sec_phase",
    )
  val phase =
    if (isAnimating) {
      pausedPhase = livePhase
      livePhase
    } else {
      pausedPhase
    }
  val osc = 0.5f - 0.5f * cos(2f * PI.toFloat() * phase)
  val variantSeed = (sectionIndex * 0.19f) % 1f

  Column(
    modifier =
      modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(surfaceVariant.copy(alpha = 0.45f))
        .border(1.dp, outline, RoundedCornerShape(12.dp))
        .padding(10.dp),
    verticalArrangement = Arrangement.spacedBy(6.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = sectionAnimationLabel(mode, sectionIndex),
        style = MaterialTheme.typography.labelSmall,
        color = primary,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.weight(1f).padding(end = 8.dp),
      )
      Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = CircleShape,
        modifier =
          Modifier.clip(CircleShape)
            .border(1.dp, outline, CircleShape)
            .clickable { isAnimating = !isAnimating },
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(3.dp),
        ) {
          Icon(
            imageVector = if (isAnimating) Icons.Filled.Pause else Icons.Filled.PlayArrow,
            contentDescription = if (isAnimating) "Pause section animation" else "Play section animation",
            tint = primary,
            modifier = Modifier.size(13.dp),
          )
          Text(
            text = if (isAnimating) "Live" else "Paused",
            style = MaterialTheme.typography.labelSmall,
            color = primary,
            fontWeight = FontWeight.SemiBold,
          )
        }
      }
    }

    Canvas(modifier = Modifier.fillMaxWidth().height(112.dp)) {
      drawSectionAnimation(
        mode = mode,
        phase = phase,
        osc = osc,
        variant = variantSeed,
        sectionIndex = sectionIndex,
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
        success = successColor,
        error = errorColor,
        ink = onSurface,
        grid = outline,
        tm = textMeasurer,
      )
    }
  }
}

private fun sectionAnimationLabel(mode: SectionVisualMode, sectionIndex: Int): String =
  when (mode) {
    SectionVisualMode.MICROMETER_VERNIER -> "Animated Model • Precision Scale & Error Offset"
    SectionVisualMode.VECTOR_COMPONENTS -> "Animated Model • Orthogonal Resultant Resolution"
    SectionVisualMode.PROJECTILE_PARABOLA -> "Animated Model • Trajectory & Kinematic Motion"
    SectionVisualMode.LEVER_TORQUE_ORBIT -> "Animated Model • Moment Equilibrium & Orbital Force"
    SectionVisualMode.WAVE_HARMONIC -> "Animated Model • Periodic Wave & Harmonic Oscillation"
    SectionVisualMode.OPTICS_RAY_LENS -> "Animated Model • Principal Optical Ray Tracing"
    SectionVisualMode.CIRCUIT_CAPACITOR -> "Animated Model • Charge Drift & Potential Loop"
    SectionVisualMode.QUANTUM_DECAY -> "Animated Model • Photon Emission & Half-Life Curve"
    SectionVisualMode.BASE_MODULAR_CLOCK -> "Animated Model • Base Weights & Modular Cycle"
    SectionVisualMode.PARABOLA_CALCULUS -> "Animated Model • Gradient Tangent & Curve Integral"
    SectionVisualMode.CIRCLE_TRIG_COMPASS -> "Animated Model • Angular Locus & Trigonometric Unit"
    SectionVisualMode.STATISTICS_BELL_OGIVE -> "Animated Model • Distribution Curve & Matrix Grid"
    SectionVisualMode.BOHR_BOND_LATTICE -> "Animated Model • Valence Orbitals & Molecular Link"
    SectionVisualMode.TITRATION_GAS_PISTON -> "Animated Model • Titration Curve & Gas Chamber"
    SectionVisualMode.KINETICS_ELECTROLYSIS -> "Animated Model • Catalyst Barrier & Electrode Flow"
    SectionVisualMode.CELL_OSMOSIS_ENZYME -> "Animated Model • Membrane Transport & Enzyme Rate"
    SectionVisualMode.CIRCULATION_GENETICS_HELIX -> "Animated Model • Biological Cycle & Genetic Flow"
    SectionVisualMode.ECOLOGY_NITROGEN_PYRAMID -> "Animated Model • Trophic Transfer & Cycle Flux"
    SectionVisualMode.SYNTAX_STRESS_PHONETICS ->
      if (sectionIndex % 2 == 0) "Animated Model • Clause Structure & Grammar Scope"
      else "Animated Model • Stress Peak & Phonetic Pulse"
    SectionVisualMode.ECONOMICS_SUPPLY_PPC -> "Animated Model • Market Curve Shift & Equilibrium"
    SectionVisualMode.GOVERNMENT_BRANCHES_LISTS -> "Animated Model • Constitutional Checks & Federal Tiers"
  }

private fun DrawScope.drawLabel(
  tm: TextMeasurer,
  text: String,
  x: Float,
  y: Float,
  color: Color,
  bold: Boolean = false,
) {
  val layout =
    tm.measure(
      text = text,
      style =
        TextStyle(
          color = color,
          fontSize = 10.sp,
          fontWeight = if (bold) FontWeight.Bold else FontWeight.Medium,
        ),
    )
  drawText(
    textLayoutResult = layout,
    topLeft = Offset(x.coerceIn(2f, (size.width - layout.size.width - 2f).coerceAtLeast(2f)), y.coerceIn(2f, (size.height - layout.size.height - 2f).coerceAtLeast(2f))),
  )
}

private fun DrawScope.drawSectionAnimation(
  mode: SectionVisualMode,
  phase: Float,
  osc: Float,
  variant: Float,
  sectionIndex: Int,
  primary: Color,
  secondary: Color,
  tertiary: Color,
  success: Color,
  error: Color,
  ink: Color,
  grid: Color,
  tm: TextMeasurer,
) {
  val w = size.width
  val h = size.height
  // Subtle baseline grid
  drawLine(grid.copy(alpha = 0.5f), Offset(0f, h - 16f), Offset(w, h - 16f), 1.2f)

  when (mode) {
    SectionVisualMode.MICROMETER_VERNIER -> {
      val barY = h * 0.45f
      drawRoundRect(primary.copy(alpha = 0.18f), Offset(20f, barY - 14f), Size(w * 0.55f, 28f), CornerRadius(6f))
      for (i in 0..10) {
        val tx = 28f + i * (w * 0.048f)
        drawLine(ink.copy(alpha = 0.7f), Offset(tx, barY - 12f), Offset(tx, barY + if (i % 5 == 0) 6f else 0f), 2f)
      }
      val sliderX = 28f + (w * 0.32f) * osc + variant * 18f
      drawRoundRect(secondary.copy(alpha = 0.25f), Offset(sliderX, barY - 18f), Size(w * 0.26f, 36f), CornerRadius(6f))
      drawRoundRect(secondary, Offset(sliderX, barY - 18f), Size(w * 0.26f, 36f), CornerRadius(6f), style = Stroke(2f))
      drawLabel(tm, "Main + Thimble = ${(2.1f + osc * 1.4f).let { "%.2f mm".format(it) }}", 20f, 8f, primary, true)
      drawLabel(tm, "Offset e₀ Check", sliderX + 8f, h - 22f, secondary, true)
    }

    SectionVisualMode.VECTOR_COMPONENTS -> {
      val origin = Offset(w * 0.16f, h * 0.78f)
      val angle = 0.35f + 0.55f * osc
      val mag = minOf(w * 0.52f, h * 0.64f)
      val tip = Offset(origin.x + mag * cos(angle), origin.y - mag * sin(angle))
      drawLine(grid, origin, Offset(w * 0.85f, origin.y), 1.5f)
      drawLine(grid, origin, Offset(origin.x, h * 0.15f), 1.5f)
      drawLine(secondary, origin, Offset(tip.x, origin.y), 4f, StrokeCap.Round)
      drawLine(tertiary, Offset(tip.x, origin.y), tip, 4f, StrokeCap.Round, PathEffect.dashPathEffect(floatArrayOf(8f, 6f)))
      drawLine(primary, origin, tip, 5f, StrokeCap.Round)
      drawCircle(primary, 6f, tip)
      drawLabel(tm, "F = 10 N", (origin.x + tip.x) * 0.45f - 10f, (origin.y + tip.y) * 0.46f - 14f, primary, true)
      drawLabel(tm, "Fx = F cosθ", (origin.x + tip.x) * 0.48f, origin.y + 4f, secondary, true)
      drawLabel(tm, "Fy = F sinθ", tip.x + 6f, (origin.y + tip.y) * 0.5f, tertiary, true)
    }

    SectionVisualMode.PROJECTILE_PARABOLA -> {
      val startX = w * 0.10f
      val baseY = h * 0.78f
      val span = w * 0.78f
      val peak = h * (0.48f + 0.12f * (sectionIndex % 2))
      val path = Path().apply {
        moveTo(startX, baseY)
        quadraticTo(startX + span * 0.5f, baseY - peak * 2f, startX + span, baseY)
      }
      drawPath(path, primary.copy(alpha = 0.45f), style = Stroke(3f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f))))
      val t = phase
      val px = startX + span * t
      val py = baseY - 4f * peak * t * (1f - t)
      drawCircle(secondary, 8f, Offset(px, py))
      drawLine(success, Offset(px, py), Offset(px + 28f, py), 3f, StrokeCap.Round)
      val vy = 26f * (0.5f - t)
      drawLine(error, Offset(px, py), Offset(px, py - vy), 3f, StrokeCap.Round)
      drawLabel(tm, "vx constant", px + 10f, py - 14f, success, true)
      drawLabel(tm, if (abs(t - 0.5f) < 0.08f) "vy = 0 at Peak" else "ay = −g", startX + 8f, 6f, primary, true)
    }

    SectionVisualMode.LEVER_TORQUE_ORBIT -> {
      val cx = w * 0.5f
      val fulY = h * 0.68f
      val tilt = (osc - 0.5f) * 0.16f
      val arm = w * 0.34f
      val leftPt = Offset(cx - arm * cos(tilt), fulY - arm * sin(tilt))
      val rightPt = Offset(cx + arm * cos(tilt), fulY + arm * sin(tilt))
      drawPath(Path().apply {
        moveTo(cx, fulY)
        lineTo(cx - 12f, fulY + 18f)
        lineTo(cx + 12f, fulY + 18f)
        close()
      }, ink)
      drawLine(primary, leftPt, rightPt, 5f, StrokeCap.Round)
      drawCircle(secondary, 9f, leftPt.copy(y = leftPt.y - 10f))
      drawCircle(tertiary, 11f, rightPt.copy(y = rightPt.y - 12f))
      drawLabel(tm, "Anticlockwise F₁d₁", 16f, 8f, secondary, true)
      drawLabel(tm, "Clockwise F₂d₂", w * 0.62f, 8f, tertiary, true)
    }

    SectionVisualMode.WAVE_HARMONIC -> {
      val cy = h * 0.50f
      val amp = h * 0.28f
      val p = Path()
      for (i in 0..80) {
        val frac = i / 80f
        val x = 18f + frac * (w - 36f)
        val y = cy - amp * sin(2f * PI.toFloat() * (frac * 2.2f - phase))
        if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
      }
      drawPath(p, primary, style = Stroke(3.5f, cap = StrokeCap.Round))
      val nodeX = 18f + (w - 36f) * 0.45f
      val dotY = cy - amp * sin(2f * PI.toFloat() * (0.45f * 2.2f - phase))
      drawLine(grid, Offset(nodeX, cy - amp - 6f), Offset(nodeX, cy + amp + 6f), 1.5f)
      drawCircle(secondary, 7f, Offset(nodeX, dotY))
      drawLabel(tm, "v = fλ  |  T = 1/f", 16f, 6f, primary, true)
      drawLabel(tm, "Particle oscillation", nodeX + 8f, 6f, secondary)
    }

    SectionVisualMode.OPTICS_RAY_LENS -> {
      val lensX = w * 0.52f
      val axisY = h * 0.54f
      drawLine(grid, Offset(14f, axisY), Offset(w - 14f, axisY), 1.5f)
      drawOval(primary.copy(alpha = 0.22f), Offset(lensX - 9f, h * 0.14f), Size(18f, h * 0.80f))
      drawOval(primary, Offset(lensX - 9f, h * 0.14f), Size(18f, h * 0.80f), style = Stroke(2.2f))
      val objX = w * (0.14f + 0.14f * osc)
      val objTop = Offset(objX, h * 0.26f)
      val fX = lensX + w * 0.18f
      val imgX = w * 0.85f
      val imgTip = Offset(imgX, h * (0.80f - 0.12f * osc))
      drawLine(secondary, Offset(objX, axisY), objTop, 4f, StrokeCap.Round)
      drawLine(primary, objTop, Offset(lensX, objTop.y), 2.5f)
      drawLine(primary, Offset(lensX, objTop.y), imgTip, 2.5f)
      drawLine(tertiary, objTop, imgTip, 2.2f)
      drawCircle(success, 5f, Offset(fX, axisY))
      drawLabel(tm, "u (Object)", objX - 10f, axisY + 4f, secondary, true)
      drawLabel(tm, "F", fX - 3f, axisY + 5f, success, true)
      drawLabel(tm, "1/u + 1/v = 1/f", 14f, 6f, primary, true)
    }

    SectionVisualMode.CIRCUIT_CAPACITOR -> {
      val left = w * 0.16f
      val right = w * 0.84f
      val top = h * 0.24f
      val bot = h * 0.74f
      drawRoundRect(primary.copy(alpha = 0.75f), Offset(left, top), Size(right - left, bot - top), CornerRadius(10f), style = Stroke(3f))
      // Cell at left
      drawLine(secondary, Offset(left - 10f, top + 16f), Offset(left + 10f, top + 16f), 4f)
      drawLine(secondary, Offset(left - 5f, top + 24f), Offset(left + 5f, top + 24f), 4f)
      // Load resistor block at top
      drawRoundRect(tertiary, Offset(w * 0.42f, top - 9f), Size(w * 0.16f, 18f), CornerRadius(4f))
      // Animated charge electrons moving around loop
      for (k in 0..3) {
        val u = (phase + k * 0.25f) % 1f
        val x = left + u * (right - left)
        drawCircle(success, 5f, Offset(x, bot))
      }
      drawLabel(tm, "I = E / (R + r)", w * 0.38f, top + 14f, primary, true)
      drawLabel(tm, "Conventional & Electron Charge Flow", w * 0.25f, bot + 5f, success)
    }

    SectionVisualMode.QUANTUM_DECAY -> {
      // Photon packet + half-life exponential decay curve
      val startX = w * 0.45f
      val endX = w * 0.90f
      val baseY = h * 0.80f
      val path = Path()
      for (i in 0..40) {
        val f = i / 40f
        val x = startX + f * (endX - startX)
        val y = baseY - (h * 0.56f) * kotlin.math.exp(-2.6f * f)
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
      }
      drawPath(path, primary, style = Stroke(3.2f))
      val photonX = 24f + osc * (startX - 48f)
      drawCircle(tertiary, 7f, Offset(photonX, h * 0.45f))
      drawLabel(tm, "hf = Φ + Kmax", 16f, 8f, tertiary, true)
      drawLabel(tm, "N = N₀(1/2)ⁿ", startX + 12f, 8f, primary, true)
    }

    SectionVisualMode.BASE_MODULAR_CLOCK -> {
      val cx = w * 0.72f
      val cy = h * 0.52f
      val r = h * 0.34f
      drawCircle(primary.copy(alpha = 0.25f), r, Offset(cx, cy), style = Stroke(4f))
      val handAng = (-PI / 2.0 + 2.0 * PI * phase).toFloat()
      drawLine(secondary, Offset(cx, cy), Offset(cx + r * 0.8f * cos(handAng), cy + r * 0.8f * sin(handAng)), 3.5f, StrokeCap.Round)
      // Bit blocks on left
      for (bit in 0..3) {
        val active = ((sectionIndex + bit + (phase * 4).toInt()) % 2) == 0
        val bx = 22f + bit * 34f
        drawRoundRect(if (active) primary else grid.copy(alpha = 0.5f), Offset(bx, h * 0.42f), Size(26f, 26f), CornerRadius(6f))
        drawLabel(tm, if (active) "1" else "0", bx + 9f, h * 0.45f, if (active) Color.White else ink, true)
      }
      drawLabel(tm, "Place Values & Mod n Wrap", 18f, 8f, primary, true)
    }

    SectionVisualMode.PARABOLA_CALCULUS -> {
      val midX = w * 0.50f
      val vertexY = h * 0.72f
      val p = Path()
      for (i in 0..50) {
        val nx = (i / 50f) * 2f - 1f
        val x = 24f + (i / 50f) * (w - 48f)
        val y = vertexY - nx * nx * (h * 0.52f)
        if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
      }
      drawPath(p, primary, style = Stroke(3.2f))
      val ux = -0.7f + 1.4f * osc
      val px = midX + ux * (w - 48f) * 0.5f
      val py = vertexY - ux * ux * (h * 0.52f)
      val slope = 2f * ux
      drawLine(secondary, Offset(px - 36f, py + 36f * slope * 0.45f), Offset(px + 36f, py - 36f * slope * 0.45f), 3f)
      drawCircle(tertiary, 6f, Offset(px, py))
      drawLabel(tm, "dy/dx Tangent & Turning Point", 16f, 6f, secondary, true)
    }

    SectionVisualMode.CIRCLE_TRIG_COMPASS -> {
      val cx = w * 0.30f
      val cy = h * 0.52f
      val r = h * 0.35f
      drawCircle(primary.copy(alpha = 0.7f), r, Offset(cx, cy), style = Stroke(2.5f))
      val ang = (phase * 2f * PI.toFloat())
      val pt = Offset(cx + r * cos(ang), cy - r * sin(ang))
      drawLine(secondary, Offset(cx, cy), pt, 3f)
      drawLine(tertiary, Offset(cx, cy), Offset(pt.x, cy), 2.5f)
      drawCircle(primary, 5f, pt)
      // Right: sine wave
      val waveStart = w * 0.54f
      val wp = Path()
      for (i in 0..40) {
        val f = i / 40f
        val wx = waveStart + f * (w * 0.40f)
        val wy = cy - r * 0.8f * sin(f * 2f * PI.toFloat() + ang)
        if (i == 0) wp.moveTo(wx, wy) else wp.lineTo(wx, wy)
      }
      drawPath(wp, secondary, style = Stroke(2.8f))
      drawLabel(tm, "Unit Circle • Bearings & Circle Angles", 14f, 6f, primary, true)
    }

    SectionVisualMode.STATISTICS_BELL_OGIVE -> {
      val baseY = h * 0.80f
      val path = Path()
      for (i in 0..60) {
        val f = i / 60f
        val z = (f - 0.5f) * 4.4f
        val y = baseY - h * 0.56f * kotlin.math.exp(-0.5f * z * z)
        val x = 22f + f * (w - 44f)
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
      }
      drawPath(path, primary, style = Stroke(3.2f))
      val markerX = 22f + (0.28f + 0.44f * osc) * (w - 44f)
      drawLine(secondary, Offset(markerX, h * 0.20f), Offset(markerX, baseY), 2.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 4f)))
      drawLabel(tm, "Mean μ, Quartiles & Spread σ", 16f, 6f, primary, true)
    }

    SectionVisualMode.BOHR_BOND_LATTICE -> {
      val cx = w * 0.34f
      val cy = h * 0.54f
      drawCircle(error, 10f, Offset(cx, cy))
      drawCircle(primary.copy(alpha = 0.55f), 24f, Offset(cx, cy), style = Stroke(2f))
      drawCircle(secondary.copy(alpha = 0.55f), 38f, Offset(cx, cy), style = Stroke(2f))
      val eAng = phase * 2f * PI.toFloat()
      drawCircle(primary, 5f, Offset(cx + 24f * cos(eAng), cy + 24f * sin(eAng)))
      drawCircle(secondary, 5f, Offset(cx + 38f * cos(-eAng * 0.7f), cy + 38f * sin(-eAng * 0.7f)))
      // Adjacent bonded atom
      val cx2 = w * 0.72f
      drawLine(success, Offset(cx + 38f, cy), Offset(cx2 - 22f, cy), 4f)
      drawCircle(tertiary, 16f, Offset(cx2, cy), style = Stroke(3f))
      drawLabel(tm, "Valence Shells & Bond Sharing", 16f, 6f, primary, true)
    }

    SectionVisualMode.TITRATION_GAS_PISTON -> {
      val ax0 = w * 0.18f
      val ax1 = w * 0.85f
      val ay1 = h * 0.80f
      drawLine(grid, Offset(ax0, h * 0.16f), Offset(ax0, ay1), 2f)
      drawLine(grid, Offset(ax0, ay1), Offset(ax1, ay1), 2f)
      val eqX = ax0 + (ax1 - ax0) * 0.55f
      val sig = Path().apply {
        moveTo(ax0, ay1 - 12f)
        lineTo(eqX - 18f, ay1 - 18f)
        lineTo(eqX + 18f, h * 0.22f)
        lineTo(ax1, h * 0.20f)
      }
      drawPath(sig, primary, style = Stroke(3.2f))
      val dropX = ax0 + (ax1 - ax0) * osc
      drawCircle(secondary, 6f, Offset(dropX, if (dropX < eqX) ay1 - 16f else h * 0.21f))
      drawLabel(tm, "Equivalence Jump & Indicator Endpoint", ax0, 6f, primary, true)
    }

    SectionVisualMode.KINETICS_ELECTROLYSIS -> {
      val sX = 24f
      val eX = w - 24f
      val baseY = h * 0.76f
      val peakUncat = Path().apply {
        moveTo(sX, baseY - 20f)
        cubicTo(w * 0.35f, h * 0.08f, w * 0.60f, h * 0.08f, eX, baseY - 6f)
      }
      val peakCat = Path().apply {
        moveTo(sX, baseY - 20f)
        cubicTo(w * 0.35f, h * 0.38f, w * 0.60f, h * 0.38f, eX, baseY - 6f)
      }
      drawPath(peakUncat, error.copy(alpha = 0.5f), style = Stroke(2.2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f))))
      drawPath(peakCat, success, style = Stroke(3.2f))
      drawCircle(primary, 6f, Offset(sX + (eX - sX) * osc, h * 0.42f + 16f * sin(osc * PI.toFloat())))
      drawLabel(tm, "Lowered Ea (Catalyst) / ΔH Unchanged", 16f, 6f, success, true)
    }

    SectionVisualMode.CELL_OSMOSIS_ENZYME -> {
      val cx = w * 0.28f
      val cy = h * 0.54f
      val rx = 28f + 14f * osc
      drawCircle(primary.copy(alpha = 0.2f), rx, Offset(cx, cy))
      drawCircle(primary, rx, Offset(cx, cy), style = Stroke(2.8f))
      // Right: Enzyme activity optimum bell curve
      val startX = w * 0.54f
      val optPath = Path()
      for (i in 0..40) {
        val f = i / 40f
        val d = (f - 0.58f) * 3.5f
        val y = h * 0.80f - h * 0.52f * kotlin.math.exp(-d * d)
        val x = startX + f * (w * 0.40f)
        if (i == 0) optPath.moveTo(x, y) else optPath.lineTo(x, y)
      }
      drawPath(optPath, secondary, style = Stroke(3f))
      drawLabel(tm, "Osmotic Turgor vs Enzyme Optimum", 14f, 6f, primary, true)
    }

    SectionVisualMode.CIRCULATION_GENETICS_HELIX -> {
      val cy = h * 0.52f
      val p1 = Path()
      val p2 = Path()
      for (i in 0..48) {
        val f = i / 48f
        val x = 22f + f * (w - 44f)
        val ang = f * 3.0f * PI.toFloat() + phase * 2f * PI.toFloat()
        val y1 = cy + 24f * sin(ang)
        val y2 = cy - 24f * sin(ang)
        if (i == 0) {
          p1.moveTo(x, y1)
          p2.moveTo(x, y2)
        } else {
          p1.lineTo(x, y1)
          p2.lineTo(x, y2)
        }
        if (i % 6 == 0) {
          drawLine(grid, Offset(x, y1), Offset(x, y2), 2f)
        }
      }
      drawPath(p1, primary, style = Stroke(3f))
      drawPath(p2, secondary, style = Stroke(3f))
      drawLabel(tm, "Genetic Allele Pairing & Cycle Flow", 16f, 6f, primary, true)
    }

    SectionVisualMode.ECOLOGY_NITROGEN_PYRAMID -> {
      val cx = w * 0.50f
      val widths = listOf(w * 0.72f, w * 0.46f, w * 0.24f)
      val labels = listOf("Producers 100%", "Primary 10%", "Secondary 1%")
      val cols = listOf(success, primary, tertiary)
      widths.forEachIndexed { idx, bw ->
        val topY = h * 0.68f - idx * 24f
        drawRoundRect(cols[idx].copy(alpha = 0.24f + 0.1f * osc), Offset(cx - bw / 2f, topY), Size(bw, 20f), CornerRadius(5f))
        drawRoundRect(cols[idx], Offset(cx - bw / 2f, topY), Size(bw, 20f), CornerRadius(5f), style = Stroke(1.8f))
        drawLabel(tm, labels[idx], cx - 38f, topY + 2f, cols[idx], true)
      }
    }

    SectionVisualMode.SYNTAX_STRESS_PHONETICS -> {
      val bars = 5
      val spacing = (w - 48f) / bars
      val activeIdx = (phase * bars).toInt().coerceIn(0, bars - 1)
      for (i in 0 until bars) {
        val bx = 24f + i * spacing + 6f
        val barH = if (i == activeIdx) h * 0.56f else h * 0.26f
        val col = if (i == activeIdx) primary else secondary.copy(alpha = 0.45f)
        drawRoundRect(col, Offset(bx, h * 0.80f - barH), Size(spacing - 14f, barH), CornerRadius(6f))
      }
      drawLabel(tm, "Clause Slots, Word Stress Peak & IPA Contrast", 16f, 6f, primary, true)
    }

    SectionVisualMode.ECONOMICS_SUPPLY_PPC -> {
      val x0 = w * 0.18f
      val x1 = w * 0.82f
      val yTop = h * 0.20f
      val yBot = h * 0.80f
      drawLine(grid, Offset(x0, yTop), Offset(x0, yBot), 2f)
      drawLine(grid, Offset(x0, yBot), Offset(x1, yBot), 2f)
      val shift = (osc - 0.5f) * 28f
      drawLine(primary, Offset(x0 + 18f, yTop + 6f), Offset(x1 - 14f, yBot - 8f), 3.2f)
      drawLine(secondary, Offset(x0 + 22f + shift, yBot - 8f), Offset(x1 - 14f + shift, yTop + 6f), 3.2f)
      val eqX = (x0 + x1) * 0.5f + shift * 0.5f
      val eqY = (yTop + yBot) * 0.5f
      drawCircle(tertiary, 6.5f, Offset(eqX, eqY))
      drawLabel(tm, "Equilibrium P*, Q* & Curve Shift", x0 + 6f, 6f, primary, true)
    }

    SectionVisualMode.GOVERNMENT_BRANCHES_LISTS -> {
      val boxes = listOf("Legislature", "Executive", "Judiciary")
      val bw = (w - 64f) / 3f
      val active = (phase * 3).toInt().coerceIn(0, 2)
      boxes.forEachIndexed { idx, name ->
        val bx = 18f + idx * (bw + 14f)
        val col = if (idx == active) primary else secondary
        drawRoundRect(col.copy(alpha = if (idx == active) 0.24f else 0.10f), Offset(bx, h * 0.34f), Size(bw, 44f), CornerRadius(8f))
        drawRoundRect(col, Offset(bx, h * 0.34f), Size(bw, 44f), CornerRadius(8f), style = Stroke(2f))
        drawLabel(tm, name, bx + 8f, h * 0.45f, col, true)
      }
      drawLine(tertiary, Offset(18f + bw, h * 0.56f), Offset(w - 18f - bw, h * 0.56f), 2.5f)
      drawLabel(tm, "Separation of Powers & Constitutional Checks", 16f, 6f, primary, true)
    }
  }
}
