package com.example.ui.labs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText
import com.example.ui.theme.semanticColors

@Composable
fun PracticalLabWorkspace(
  lessonId: String,
  labType: PracticalLabType,
  labBrief: String,
  modifier: Modifier = Modifier,
) {
  val protocol = remember(lessonId, labType) { AcademyLabProtocols.protocolFor(lessonId, labType.title) }
  var selectedHypothesis by remember(lessonId) { mutableIntStateOf(-1) }
  val semantics = MaterialTheme.semanticColors

  Column(
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(14.dp),
  ) {
    // 1. Academy Lab Header + Aim & Variables
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
          .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Surface(
          color = MaterialTheme.colorScheme.primaryContainer,
          shape = RoundedCornerShape(99.dp),
        ) {
          Text(
            text = "TOPIC-AUTHENTIC VIRTUAL LAB",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
          )
        }
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
      Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
      ) {
        Column(
          modifier = Modifier.padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
          ScientificText(
            text = "Aim: ${protocol.aim}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
          )
          ScientificText(
            text = protocol.variables,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
          )
        }
      }
    }

    // 2. Interactive Pre-Lab Hypothesis Prediction Gate
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
          .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
      ) {
        Icon(
          imageVector = Icons.Filled.Science,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.secondary,
          modifier = Modifier.size(18.dp),
        )
        Text(
          text = "STEP 1 • PRE-LAB HYPOTHESIS PREDICTION",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.secondary,
          fontWeight = FontWeight.Bold,
        )
      }
      ScientificText(
        text = protocol.hypothesisPrompt,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurface,
      )
      protocol.hypothesisOptions.forEachIndexed { idx, opt ->
        val picked = selectedHypothesis == idx
        val answered = selectedHypothesis >= 0
        val isCorrect = idx == protocol.correctHypothesisIndex
        val bg =
          when {
            answered && isCorrect -> semantics.successContainer
            answered && picked -> MaterialTheme.colorScheme.errorContainer
            picked -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.background
          }
        val fg =
          when {
            answered && isCorrect -> semantics.onSuccessContainer
            answered && picked -> MaterialTheme.colorScheme.onErrorContainer
            else -> MaterialTheme.colorScheme.onSurface
          }
        val border =
          when {
            answered && isCorrect -> semantics.success
            answered && picked -> MaterialTheme.colorScheme.error
            picked -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.outlineVariant
          }
        Row(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(10.dp))
              .background(bg)
              .border(1.2.dp, border, RoundedCornerShape(10.dp))
              .clickable { selectedHypothesis = idx }
              .padding(horizontal = 12.dp, vertical = 10.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = "${'A' + idx}.",
            style = MaterialTheme.typography.labelLarge,
            color = fg,
            fontWeight = FontWeight.Bold,
          )
          ScientificText(
            text = opt,
            style = MaterialTheme.typography.bodySmall,
            color = fg,
            fontWeight = if (picked || (answered && isCorrect)) FontWeight.SemiBold else FontWeight.Normal,
          )
        }
      }
      if (selectedHypothesis >= 0) {
        val isHit = selectedHypothesis == protocol.correctHypothesisIndex
        Surface(
          color = if (isHit) semantics.successContainer else MaterialTheme.colorScheme.tertiaryContainer,
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier.fillMaxWidth(),
        ) {
          ScientificText(
            text = (if (isHit) "Hypothesis Confirmed — " else "Prediction Check — ") + protocol.hypothesisExplanation,
            style = MaterialTheme.typography.bodySmall,
            color =
              if (isHit) semantics.onSuccessContainer
              else MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier.padding(10.dp),
          )
        }
      }
    }

    // 3. Interactive Simulator Bench
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
          .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      Text(
        text = "STEP 2 • INTERACTIVE APPARATUS & LIVE READINGS",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
      )

      when (labType) {
        PracticalLabType.MEASUREMENT_VECTOR_LAB,
        PracticalLabType.PROJECTILE_KINEMATICS,
        PracticalLabType.TORQUE_MACHINES_LAB,
        PracticalLabType.SHM_FLUIDS_LAB,
        PracticalLabType.THERMAL_EXPANSION_CALORIMETRY,
        PracticalLabType.WAVE_OPTICS_BENCH,
        PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB,
        PracticalLabType.OHMS_CIRCUIT,
        PracticalLabType.EM_TRANSFORMER_AC_LAB,
        PracticalLabType.QUANTUM_NUCLEAR_LAB -> PhysicsLabDispatcher(lessonId = lessonId, labType = labType)

        PracticalLabType.NUMBER_BASE_INDICES_LAB,
        PracticalLabType.SETS_VENN_LAB,
        PracticalLabType.ALGEBRA_VARIATION_LAB,
        PracticalLabType.QUADRATIC_TANGENT,
        PracticalLabType.SEQUENCE_MATRIX_LAB,
        PracticalLabType.POLYGON_CIRCLE_THEOREM_LAB,
        PracticalLabType.MENSURATION_3D_LAB,
        PracticalLabType.TRIG_BEARING_LAB,
        PracticalLabType.COORDINATE_CALCULUS_LAB,
        PracticalLabType.PROBABILITY_STATISTICS_LAB -> MathLabDispatcher(lessonId = lessonId, labType = labType)

        PracticalLabType.SEPARATION_ATOMIC_LAB,
        PracticalLabType.BONDING_PERIODIC_LAB,
        PracticalLabType.STOICHIOMETRY_MOLE_LAB,
        PracticalLabType.GAS_LAW_PISTON,
        PracticalLabType.TITRATION_PH,
        PracticalLabType.ENERGETICS_KINETICS_LAB,
        PracticalLabType.REDOX_ELECTROLYSIS_LAB,
        PracticalLabType.INORGANIC_GASES_METALS_LAB,
        PracticalLabType.ORGANIC_HYDROCARBON_LAB,
        PracticalLabType.QUALITATIVE_ANALYSIS_LAB -> ChemistryLabDispatcher(lessonId = lessonId, labType = labType)

        PracticalLabType.CELL_OSMOSIS_LAB,
        PracticalLabType.BIOENERGETICS_ENZYME_LAB,
        PracticalLabType.NUTRITION_TRANSPORT_LAB,
        PracticalLabType.RESPIRATION_KIDNEY_LAB,
        PracticalLabType.NERVOUS_ENDOCRINE_LAB,
        PracticalLabType.REPRODUCTION_FLOWER_LAB,
        PracticalLabType.MENDELIAN_GENETICS,
        PracticalLabType.EVOLUTION_VARIATION_LAB,
        PracticalLabType.ECOLOGY_ENERGY_PYRAMID,
        PracticalLabType.BIOGEOCHEMICAL_SOIL_LAB -> BiologyLabDispatcher(lessonId = lessonId, labType = labType)

        PracticalLabType.PARTS_CLAUSES_LAB,
        PracticalLabType.SYNTAX_LEXIS_STUDIO,
        PracticalLabType.LEXIS_IDIOMS_REGISTER_LAB,
        PracticalLabType.ORAL_ENGLISH_STRESS,
        PracticalLabType.PHONETICS_CONSONANTS_LAB,
        PracticalLabType.COMPREHENSION_ESSAY_LAB -> EnglishLabDispatcher(lessonId = lessonId, labType = labType)

        PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB,
        PracticalLabType.ECONOMICS_MACRO_POLICY_LAB,
        PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB -> SocialScienceLabDispatcher(lessonId = lessonId, labType = labType)
      }
    }

    // 4. Investigation Procedure + Misconception Check + Exam Bridge
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
          .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      Text(
        text = "STEP 3 • VERIFICATION PROCEDURE & EXAM BRIDGE",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
      )
      protocol.procedureSteps.forEachIndexed { idx, step ->
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.Top,
        ) {
          Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = null,
            tint = semantics.success,
            modifier = Modifier.size(16.dp),
          )
          ScientificText(
            text = "${idx + 1}. $step",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
          )
        }
      }
      Row(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.errorContainer)
            .border(1.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(10.dp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top,
      ) {
        Icon(
          imageVector = Icons.Filled.WarningAmber,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onErrorContainer,
          modifier = Modifier.size(16.dp),
        )
        ScientificText(
          text = "Watch Out: ${protocol.misconceptionCheck}",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onErrorContainer,
        )
      }
      Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
      ) {
        ScientificText(
          text = "JAMB / WAEC / NECO Bridge: ${protocol.examBridge}",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          fontWeight = FontWeight.Medium,
          modifier = Modifier.padding(10.dp),
        )
      }
    }
  }
}

@Composable
internal fun LabMetricBanner(items: List<Pair<String, String>>) {
  Row(
    modifier =
      Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
        .padding(12.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    items.forEach { (label, value) ->
      Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
          text = label.uppercase(),
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontWeight = FontWeight.Bold,
        )
        Text(
          text = value,
          style = MaterialTheme.typography.titleSmall,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.ExtraBold,
        )
      }
    }
  }
}

@Composable
internal fun LabeledLabSlider(
  label: String,
  valueText: String,
  value: Float,
  onValueChange: (Float) -> Unit,
  valueRange: ClosedFloatingPointRange<Float>,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier =
      modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f))
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
        .padding(horizontal = 12.dp, vertical = 8.dp),
    verticalArrangement = Arrangement.spacedBy(2.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      ScientificText(
        text = label,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.weight(1f).padding(end = 8.dp),
      )
      Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(8.dp),
      ) {
        Text(
          text = valueText,
          style = MaterialTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
        )
      }
    }
    androidx.compose.material3.Slider(
      value = value,
      onValueChange = onValueChange,
      valueRange = valueRange,
      modifier = Modifier.fillMaxWidth(),
    )
  }
}
