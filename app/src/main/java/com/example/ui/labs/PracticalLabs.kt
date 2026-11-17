package com.example.ui.labs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText

@Composable
fun PracticalLabWorkspace(
  lessonId: String,
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
          text = "TOPIC-AUTHENTIC VIRTUAL LAB",
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
