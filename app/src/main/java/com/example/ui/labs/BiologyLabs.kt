package com.example.ui.labs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
internal fun BiologyLabDispatcher(lessonId: String, labType: PracticalLabType) {
  when (labType) {
    PracticalLabType.CELL_OSMOSIS_LAB -> {
      if (lessonId == "bio_01_diversity_microscopy") MicroscopyMagnificationLab() else OsmosisCellBathLab()
    }
    PracticalLabType.BIOENERGETICS_ENZYME_LAB -> BioenergeticsAndEnzymeLab()
    PracticalLabType.NUTRITION_TRANSPORT_LAB -> {
      if (lessonId == "bio_04_nutrition_digestion") FoodTestsBenchLab() else HeartCirculationLab()
    }
    PracticalLabType.RESPIRATION_KIDNEY_LAB -> BreathingKidneyLab()
    PracticalLabType.NERVOUS_ENDOCRINE_LAB -> {
      if (lessonId == "bio_07_nervous_eye_ear") EyeAccommodationDefectsLab() else BloodSugarHormoneLab()
    }
    PracticalLabType.REPRODUCTION_FLOWER_LAB -> ReproductionAndCycleLab()
    PracticalLabType.MENDELIAN_GENETICS -> PunnettGeneticsLab()
    PracticalLabType.EVOLUTION_VARIATION_LAB -> VariationSelectionLab()
    PracticalLabType.ECOLOGY_ENERGY_PYRAMID -> {
      if (lessonId == "bio_13_biomes_adaptations") NigerianBiomesLab() else TrophicEnergyPyramidLab()
    }
    PracticalLabType.BIOGEOCHEMICAL_SOIL_LAB -> {
      if (lessonId == "bio_15_microbes_vectors_conservation") DiseaseVectorsConservationLab() else NitrogenCycleSoilLab()
    }
    else -> OsmosisCellBathLab()
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MicroscopyMagnificationLab() {
  var eyepiece by remember { mutableIntStateOf(10) }
  var objective by remember { mutableIntStateOf(40) }
  var drawingCm by remember { mutableFloatStateOf(4.0f) }
  val totalMag = eyepiece * objective
  val actualUm = (drawingCm * 10_000f) / totalMag

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Total Mag" to "×$totalMag",
        "Drawing Size" to "${String.format("%.1f", drawingCm)} cm",
        "Actual Cell" to String.format("%.1f μm", actualUm),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf(4, 10, 40, 100).forEach { obj ->
        FilterChip(selected = objective == obj, onClick = { objective = obj }, label = { Text("×$obj Obj") })
      }
    }
    Slider(value = drawingCm, onValueChange = { drawingCm = it }, valueRange = 1f..10f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OsmosisCellBathLab() {
  var bathIdx by remember { mutableIntStateOf(0) } // 0=Hypotonic, 1=Isotonic, 2=Hypertonic
  val baths = listOf("Distilled Water (Hypotonic)", "0.9% Saline (Isotonic)", "20% Sucrose (Hypertonic)")
  val plantState = listOf("TURGID (Vacuole swollen; Wall prevents bursting)", "FLACCID (Zero net water flow)", "PLASMOLYZED (Membrane pulls away from wall)")
  val rbcState = listOf("HAEMOLYSIS (Swells and bursts cleanly!)", "NORMAL BICONCAVE SHAPE", "CRENATED (Shriveled & spiked)")

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      baths.forEachIndexed { idx, b ->
        FilterChip(selected = bathIdx == idx, onClick = { bathIdx = idx }, label = { Text(b.substringBefore(" (")) })
      }
    }
    LabMetricBanner(
      listOf(
        "Plant Cell" to plantState[bathIdx].substringBefore(" ("),
        "Red Blood Cell" to rbcState[bathIdx].substringBefore(" ("),
      )
    )
    ScientificText("Plant: ${plantState[bathIdx]}\nAnimal RBC: ${rbcState[bathIdx]}", style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BioenergeticsAndEnzymeLab() {
  var mode by remember { mutableIntStateOf(0) } // 0=Enzymes, 1=Photosynthesis & ATP
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = mode == 0, onClick = { mode = 0 }, label = { Text("Enzyme Temp & pH") })
      FilterChip(selected = mode == 1, onClick = { mode = 1 }, label = { Text("Photosynthesis & ATP") })
    }
    if (mode == 0) {
      EnzymeActivityLab()
    } else {
      var lightIntensity by remember { mutableFloatStateOf(70f) }
      var co2Pct by remember { mutableFloatStateOf(0.04f) }
      val limiting =
        when {
          lightIntensity < 35f -> "Light Intensity (Light-dependent ATP/NADPH)"
          co2Pct < 0.04f -> "CO₂ Concentration (Calvin Cycle)"
          else -> "Temperature / Enzyme Saturation"
        }
      LabMetricBanner(
        listOf(
          "O₂ Bubbles" to "${((lightIntensity * co2Pct * 12f).roundToInt()).coerceAtLeast(1)}/min",
          "Aerobic ATP" to "38 ATP / Glucose",
          "Limiting Factor" to limiting.substringBefore(" ("),
        )
      )
      Slider(value = lightIntensity, onValueChange = { lightIntensity = it }, valueRange = 10f..100f)
      Slider(value = co2Pct, onValueChange = { co2Pct = it }, valueRange = 0.01f..0.10f)
      ScientificText("Active Constraint: $limiting", style = MaterialTheme.typography.bodySmall)
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EnzymeActivityLab() {
  var tempC by remember { mutableFloatStateOf(37f) }
  var enzymeIdx by remember { mutableIntStateOf(0) } // 0=Pepsin (pH 2), 1=Amylase (pH 7), 2=Trypsin (pH 8.5)
  val enzymes = listOf("Pepsin (pH 2)", "Salivary Amylase (pH 7)", "Trypsin (pH 8.5)")
  val ratePct = when {
    tempC > 52f -> 0f
    tempC > 40f -> (100f * (52f - tempC) / 12f).coerceIn(0f, 100f)
    else -> (100f * exp(-((tempC - 37f) / 18f).pow(2))).coerceIn(8f, 100f)
  }
  val state = if (tempC < 10f) "Inactivated (Cold Sleep)" else if (tempC > 50f) "DENATURED Permanently!" else "Active Lock-and-Key"

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      enzymes.forEachIndexed { idx, e ->
        FilterChip(selected = enzymeIdx == idx, onClick = { enzymeIdx = idx }, label = { Text(e) })
      }
    }
    LabMetricBanner(
      listOf(
        "Temp" to "${tempC.roundToInt()} °C",
        "Reaction Rate" to "${ratePct.roundToInt()}%",
        "Status" to state.substringBefore(" ("),
      )
    )
    Slider(value = tempC, onValueChange = { tempC = it }, valueRange = 0f..65f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FoodTestsBenchLab() {
  var foodIdx by remember { mutableIntStateOf(0) }
  val foods = listOf("Starch (Yam)", "Glucose (Reducing)", "Sucrose (Non-reducing)", "Egg Albumin (Protein)")
  val reagents = listOf("Iodine Solution", "Benedict's + Heat", "HCl + Neutralize + Benedict's", "Biuret (NaOH + CuSO₄)")
  val colours = listOf("Blue-Black Colour", "Brick-Red Precipitate", "Brick-Red ONLY after Acid Boil", "Violet / Purple Ring")

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      foods.forEachIndexed { idx, f ->
        FilterChip(selected = foodIdx == idx, onClick = { foodIdx = idx }, label = { Text(f.substringBefore(" (")) })
      }
    }
    LabMetricBanner(listOf("Reagent" to reagents[foodIdx], "Positive Result" to colours[foodIdx]))
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HeartCirculationLab() {
  var step by remember { mutableIntStateOf(0) }
  val stations = listOf(
    "1. Vena Cava → RA" to "Deoxygenated body blood enters Right Atrium (RA)",
    "2. RA → RV → Pulm Artery" to "Tricuspid opens; RV pumps DEOXYGENATED blood to Lungs",
    "3. Pulm Vein → LA" to "Fresh OXYGENATED blood returns from lungs to Left Atrium (LA)",
    "4. LA → LV → Aorta" to "Thick-walled Left Ventricle blasts O₂ blood across the entire body!",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
      stations.forEachIndexed { idx, (label, _) ->
        FilterChip(selected = step == idx, onClick = { step = idx }, label = { Text(label.substringAfter(". ")) })
      }
    }
    LabMetricBanner(listOf("Heart Step" to stations[step].first))
    ScientificText(stations[step].second, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BreathingKidneyLab() {
  var dehydration by remember { mutableFloatStateOf(0.75f) }
  var inhale by remember { mutableStateOf(true) }
  val adhLevel = if (dehydration > 0.6f) "HIGH ADH (Aquaporins open)" else "LOW ADH (Excess water passed)"
  val urine = if (dehydration > 0.6f) "Small concentrated dark urine" else "Large pale dilute urine"

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = inhale, onClick = { inhale = true }, label = { Text("Inhalation (Diaphragm Flat)") })
      FilterChip(selected = !inhale, onClick = { inhale = false }, label = { Text("Exhalation (Diaphragm Dome)") })
    }
    LabMetricBanner(
      listOf(
        "Breathing" to if (inhale) "Volume ↑, P_chest ↓" else "Volume ↓, P_chest ↑",
        "ADH Signal" to adhLevel.substringBefore(" ("),
        "Urine Output" to urine.substringBefore(" "),
      )
    )
    Text("Body Dehydration Level: ${(dehydration * 100).roundToInt()}%  •  $urine", style = MaterialTheme.typography.labelLarge)
    Slider(value = dehydration, onValueChange = { dehydration = it }, valueRange = 0.1f..1.0f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EyeAccommodationDefectsLab() {
  var eyeIdx by remember { mutableIntStateOf(0) } // 0=Normal, 1=Myopia, 2=Hypermetropia
  val labels = listOf("Normal Retina", "Myopia (Short-Sighted)", "Hypermetropia (Long-Sighted)")
  val focus = listOf("Sharp ON Retina", "Falls IN FRONT of Retina", "Falls BEHIND Retina")
  val lens = listOf("None Needed", "CONCAVE Diverging Lens", "CONVEX Converging Lens")

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      labels.forEachIndexed { i, l ->
        FilterChip(selected = eyeIdx == i, onClick = { eyeIdx = i }, label = { Text(l.substringBefore(" (")) })
      }
    }
    LabMetricBanner(listOf("Ray Focus" to focus[eyeIdx], "Corrective Lens" to lens[eyeIdx]))
  }
}

@Composable
private fun BloodSugarHormoneLab() {
  var glucoseMg by remember { mutableFloatStateOf(145f) }
  val activeHormone = when {
    glucoseMg > 115f -> "INSULIN (Liver stores glucose → Glycogen)"
    glucoseMg < 75f -> "GLUCAGON (Liver breaks glycogen → Glucose)"
    else -> "Homeostatic Balance (~90 mg/dL)"
  }
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Blood Glucose" to "${glucoseMg.roundToInt()} mg/dL",
        "Pancreas Release" to activeHormone.substringBefore(" ("),
      )
    )
    Slider(value = glucoseMg, onValueChange = { glucoseMg = it }, valueRange = 45f..210f)
    ScientificText(activeHormone, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ReproductionAndCycleLab() {
  var plantMode by remember { mutableStateOf(false) }
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = !plantMode, onClick = { plantMode = false }, label = { Text("Human 28-Day Cycle") })
      FilterChip(selected = plantMode, onClick = { plantMode = true }, label = { Text("Flower Double Fertilization") })
    }
    if (!plantMode) {
      MenstrualHormoneCycleLab()
    } else {
      var stage by remember { mutableIntStateOf(0) }
      val flowerSteps = listOf(
        "Pollen Grain Lands" to "Insect or wind transfers haploid pollen (n) onto sticky stigma; pollen tube digests style.",
        "Male Gamete 1 + Egg" to "Fuses with haploid egg cell inside ovule → Diploid Zygote (2n) → Embryo.",
        "Male Gamete 2 + Polar Nuclei" to "Fuses with 2 polar nuclei → Triploid Primary Endosperm Nucleus (3n) food store!",
        "Ovary → Fruit; Ovule → Seed" to "Ovary wall (pericarp) swells into Fruit while hardened ovule integuments form Testa (seed coat).",
      )
      FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        flowerSteps.forEachIndexed { idx, (label, _) ->
          FilterChip(selected = stage == idx, onClick = { stage = idx }, label = { Text(label.substringBefore(" +")) })
        }
      }
      LabMetricBanner(listOf("Angiosperm Stage" to flowerSteps[stage].first))
      ScientificText(flowerSteps[stage].second, style = MaterialTheme.typography.bodySmall)
    }
  }
}

@Composable
private fun MenstrualHormoneCycleLab() {
  var day by remember { mutableIntStateOf(14) }
  val phase = when {
    day <= 5 -> "Menstruation (Low Estrogen & Progesterone; lining sheds)"
    day <= 13 -> "Follicular Phase (FSH matures follicle; Estrogen rebuilds uterus)"
    day == 14 -> "OVULATION (Sharp Pituitary LH Surge releases ovum!)"
    else -> "Luteal Phase (Corpus Luteum secretes Progesterone to sustain lining)"
  }
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Cycle Day" to "Day $day / 28",
        "Peak Event" to if (day == 14) "LH Surge + Ovum!" else phase.substringBefore(" ("),
      )
    )
    Slider(value = day.toFloat(), onValueChange = { day = it.roundToInt() }, valueRange = 1f..28f)
    ScientificText(phase, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PunnettGeneticsLab() {
  var p1 by remember { mutableStateOf("AS") }
  var p2 by remember { mutableStateOf("AS") }
  val options = listOf("AA", "AS", "SS")
  val grid = listOf(
    listOf("${p1[0]}${p2[0]}", "${p1[0]}${p2[1]}"),
    listOf("${p1[1]}${p2[0]}", "${p1[1]}${p2[1]}"),
  ).flatten().map { it.toCharArray().sorted().joinToString("") } // A before S -> AS!
  val aaCount = grid.count { it == "AA" } * 25
  val asCount = grid.count { it == "AS" } * 25
  val ssCount = grid.count { it == "SS" } * 25

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      options.forEach { g ->
        FilterChip(selected = p1 == g, onClick = { p1 = g }, label = { Text("Mom $g") })
      }
      options.forEach { g ->
        FilterChip(selected = p2 == g, onClick = { p2 = g }, label = { Text("Dad $g") })
      }
    }
    LabMetricBanner(
      listOf(
        "Normal AA" to "$aaCount%",
        "Carrier AS" to "$asCount%",
        "Sickle SS" to "$ssCount%",
      )
    )
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun VariationSelectionLab() {
  var isDarkForest by remember { mutableStateOf(true) }
  var generation by remember { mutableIntStateOf(3) }
  val darkPct = if (isDarkForest) (20 + generation * 16).coerceAtMost(94) else (60 - generation * 12).coerceAtLeast(10)

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = isDarkForest, onClick = { isDarkForest = true }, label = { Text("Soot-Darkened Bark") })
      FilterChip(selected = !isDarkForest, onClick = { isDarkForest = false }, label = { Text("Clean Lichen Pale Bark") })
    }
    LabMetricBanner(
      listOf(
        "Generation" to "Gen $generation",
        "Dark Moths" to "$darkPct%",
        "Pale Moths" to "${100 - darkPct}%",
      )
    )
    Slider(value = generation.toFloat(), onValueChange = { generation = it.roundToInt() }, valueRange = 0f..5f)
  }
}

@Composable
private fun TrophicEnergyPyramidLab() {
  var producerKcal by remember { mutableFloatStateOf(10_000f) }
  var effPct by remember { mutableFloatStateOf(10f) }
  val f = effPct / 100f
  val primary = producerKcal * f
  val secondary = primary * f
  val tertiary = secondary * f

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Herbivore 1°" to "${primary.roundToInt()} kJ",
        "Lizard 2°" to "${secondary.roundToInt()} kJ",
        "Hawk 3°" to "${tertiary.roundToInt()} kJ",
      )
    )
    Text("Producer Grass: ${producerKcal.roundToInt()} kJ  |  Transfer Efficiency: ${effPct.roundToInt()}%", style = MaterialTheme.typography.labelLarge)
    Slider(value = effPct, onValueChange = { effPct = it.roundToInt().toFloat() }, valueRange = 5f..20f)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun NigerianBiomesLab() {
  var biomeIdx by remember { mutableIntStateOf(0) }
  val biomes = listOf(
    "Mangrove Swamp" to "Niger Delta tidal waterlogging • Red Mangrove stilt roots & pneumatophores for anaerobic O₂ breathing.",
    "Tropical Rainforest" to "Southern Nigeria (>2000 mm rain) • Stratified multi-canopy trees, buttress roots, broad drip-tip leaves.",
    "Guinea / Sudan Savanna" to "Middle Belt to North • Tall grasses, scattered fire-resistant Baobab & Shea trees, thick corky bark.",
    "Sahel Xerophyte Zone" to "Far Northeast (<500 mm rain) • Acacia thorns, waxy cuticle, sunken stomata & deep taproots.",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      biomes.forEachIndexed { i, (label, _) ->
        FilterChip(selected = biomeIdx == i, onClick = { biomeIdx = i }, label = { Text(label.substringBefore(" ")) })
      }
    }
    LabMetricBanner(listOf("Nigerian Biome" to biomes[biomeIdx].first))
    ScientificText(biomes[biomeIdx].second, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun NitrogenCycleSoilLab() {
  var stage by remember { mutableIntStateOf(0) }
  val steps = listOf(
    "Nitrogen Fixation" to "Rhizobium in legume root nodules turns N₂ → NH₄⁺",
    "Nitrification (O₂)" to "Nitrosomonas (NH₄⁺→NO₂⁻) then Nitrobacter (NO₂⁻→NO₃⁻)",
    "Denitrification" to "Anaerobic Pseudomonas in waterlogged soil wastes NO₃⁻ → N₂!",
    "Loam vs Clay vs Sand" to "Loam balances aeration + water + humus; Clay waterlogs; Sand leaches.",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      steps.forEachIndexed { idx, (label, _) ->
        FilterChip(selected = stage == idx, onClick = { stage = idx }, label = { Text(label) })
      }
    }
    LabMetricBanner(listOf("Process" to steps[stage].first))
    ScientificText(steps[stage].second, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DiseaseVectorsConservationLab() {
  var idx by remember { mutableIntStateOf(0) }
  val items = listOf(
    "Anopheles (Malaria)" to "Pathogen: Plasmodium protozoon • Control: Clear stagnant gutters, larvicide & insecticide-treated nets.",
    "Tsetse Fly (Sleeping Sickness)" to "Pathogen: Trypanosoma • Control: Clear riverside brush & treat infected cattle.",
    "Freshwater Snail (Bilharzia)" to "Pathogen: Schistosoma • Control: Avoid wading in infested streams & enforce sanitation.",
    "Biodiversity Reserves" to "Yankari Game Reserve & Cross River National Park protect endangered wildlife via anti-poaching & reforestation.",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      items.forEachIndexed { i, (label, _) ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(label.substringBefore(" (")) })
      }
    }
    LabMetricBanner(listOf("Public Health Target" to items[idx].first))
    ScientificText(items[idx].second, style = MaterialTheme.typography.bodySmall)
  }
}
