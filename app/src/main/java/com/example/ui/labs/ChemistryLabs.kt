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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText
import kotlin.math.roundToInt
import kotlin.math.sqrt

@Composable
internal fun ChemistryLabDispatcher(lessonId: String, labType: PracticalLabType) {
  when (labType) {
    PracticalLabType.SEPARATION_ATOMIC_LAB -> {
      if (lessonId == "chem_01_separation_kinetic") SeparationBenchLab() else BohrOrbitalBuilderLab()
    }
    PracticalLabType.BONDING_PERIODIC_LAB -> PeriodicBondingLab()
    PracticalLabType.STOICHIOMETRY_MOLE_LAB -> StoichiometryLimitingLab()
    PracticalLabType.GAS_LAW_PISTON -> GasLawPistonLab()
    PracticalLabType.TITRATION_PH -> AcidBaseTitrationLab()
    PracticalLabType.ENERGETICS_KINETICS_LAB -> {
      if (lessonId == "chem_07_thermochemistry") EnthalpyBarrierLab() else LeChatelierHaberLab()
    }
    PracticalLabType.REDOX_ELECTROLYSIS_LAB -> {
      if (lessonId == "chem_09_redox_cells") RedoxCellEmfLab() else FaradayElectrolysisLab()
    }
    PracticalLabType.INORGANIC_GASES_METALS_LAB -> {
      if (lessonId == "chem_11_nonmetals_water") GasIdentificationLab() else MetalReactivityFurnaceLab()
    }
    PracticalLabType.ORGANIC_HYDROCARBON_LAB -> {
      if (lessonId == "chem_14_alkanols_esters_polymers") FunctionalGroupPolymerLab() else OrganicSeriesLab()
    }
    PracticalLabType.QUALITATIVE_ANALYSIS_LAB -> QualitativeCationBenchLab()
    else -> AcidBaseTitrationLab()
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SeparationBenchLab() {
  var mixIdx by remember { mutableIntStateOf(0) }
  val mixtures = listOf("Ethanol + Water", "Kerosene + Water", "NH₄Cl + NaCl", "Plant Leaf Pigments")
  val methods = listOf("Fractional Distillation", "Separating Funnel", "Sublimation", "Paper Chromatography")
  val reasons = listOf(
    "Miscible liquids separated by close boiling points (78°C vs 100°C).",
    "Immiscible liquids form distinct density layers separated by opening tap.",
    "NH₄Cl vaporizes directly to gas on gentle heating leaving NaCl behind.",
    "Dyes travel at different Rf speeds up cellulose paper in moving solvent.",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      mixtures.forEachIndexed { idx, name ->
        FilterChip(selected = mixIdx == idx, onClick = { mixIdx = idx }, label = { Text(name.substringBefore(" +")) })
      }
    }
    LabMetricBanner(listOf("Mixture" to mixtures[mixIdx], "Apparatus" to methods[mixIdx]))
    ScientificText(reasons[mixIdx], style = MaterialTheme.typography.bodySmall)
  }
}

@Composable
private fun BohrOrbitalBuilderLab() {
  var atomicZ by remember { mutableIntStateOf(11) }
  val symbols = listOf("", "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca")
  val k = minOf(atomicZ, 2)
  val l = (atomicZ - 2).coerceIn(0, 8)
  val m = (atomicZ - 10).coerceIn(0, 8)
  val n = (atomicZ - 18).coerceIn(0, 2)
  val shells = listOf(k, l, m, n).filter { it > 0 }.joinToString(", ")
  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Canvas(modifier = Modifier.fillMaxWidth().height(130.dp)) {
      val center = Offset(size.width / 2f, size.height / 2f)
      drawCircle(primary, radius = 14f, center = center)
      listOf(k, l, m, n).forEachIndexed { sIdx, count ->
        if (count > 0) {
          val rad = 28f + sIdx * 16f
          drawCircle(secondary.copy(alpha = 0.5f), radius = rad, center = center, style = Stroke(2.5f))
        }
      }
    }
    LabMetricBanner(
      listOf(
        "Element" to "${symbols[atomicZ]} (Z = $atomicZ)",
        "Shells (K,L,M,N)" to shells,
        "Group Type" to if (k + l + m + n == atomicZ && atomicZ in listOf(2, 10, 18)) "Noble Gas" else if ((atomicZ <= 2 && atomicZ == 1) || listOf(3, 4, 11, 12, 13, 19, 20).contains(atomicZ)) "Metal" else "Non-Metal",
      )
    )
    LabeledLabSlider(
      label = "Atomic Number (Protons Z)",
      valueText = "Z = $atomicZ (${symbols[atomicZ]})",
      value = atomicZ.toFloat(),
      onValueChange = { atomicZ = it.roundToInt() },
      valueRange = 1f..20f,
    )
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PeriodicBondingLab() {
  var pairIdx by remember { mutableIntStateOf(0) }
  val pairs = listOf("Na (0.9) + Cl (3.0)" to "Ionic Transfer (Na⁺Cl⁻, ΔEN=2.1)", "C (2.5) + H (2.1)" to "Non-Polar Covalent (CH₄)", "O (3.5) + H (2.1)" to "Polar Covalent + H-Bonding (H₂O)", "N (3.0) + H⁺" to "Dative Coordinate Bond (NH₄⁺)")
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      pairs.forEachIndexed { idx, (label, _) ->
        FilterChip(selected = pairIdx == idx, onClick = { pairIdx = idx }, label = { Text(label.substringBefore(" (") + "+" + label.substringAfter("+ ").substringBefore(" (")) })
      }
    }
    LabMetricBanner(listOf("Pair" to pairs[pairIdx].first, "Predicted Bond" to pairs[pairIdx].second))
  }
}

@Composable
private fun StoichiometryLimitingLab() {
  // 2 H2 + O2 -> 2 H2O; adjust moles H2 and O2
  var h2Moles by remember { mutableFloatStateOf(4f) }
  var o2Moles by remember { mutableFloatStateOf(3f) }
  val h2NeededForO2 = 2f * o2Moles
  val limiting = if (h2Moles < h2NeededForO2) "H₂ runs out first!" else if (h2Moles > h2NeededForO2) "O₂ runs out first!" else "Exact Stoichiometric Mix"
  val waterMoles = minOf(h2Moles, 2f * o2Moles)

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Limiting Reactant" to limiting.substringBefore(" "),
        "H₂O Yield" to String.format("%.1f mol (%.0f g)", waterMoles, waterMoles * 18f),
        "STP Vol" to String.format("%.1f dm³", waterMoles * 22.4f),
      )
    )
    ScientificText("Reaction Equation: 2H_2 + O_2 → 2H_2O", style = MaterialTheme.typography.labelLarge)
    LabeledLabSlider(
      label = "Moles of Hydrogen H_2 Supplied",
      valueText = String.format("%.1f mol", h2Moles),
      value = h2Moles,
      onValueChange = { h2Moles = (it * 2).roundToInt() / 2f },
      valueRange = 1f..8f,
    )
    LabeledLabSlider(
      label = "Moles of Oxygen O_2 Supplied",
      valueText = String.format("%.1f mol", o2Moles),
      value = o2Moles,
      onValueChange = { o2Moles = (it * 2).roundToInt() / 2f },
      valueRange = 0.5f..5f,
    )
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GasLawPistonLab() {
  var tempK by remember { mutableFloatStateOf(300f) }
  var pressAtm by remember { mutableFloatStateOf(1.0f) }
  var molarMass by remember { mutableFloatStateOf(32f) } // O2 default
  val volL = (1.0f * 0.0821f * tempK) / pressAtm
  val grahamSpeedRatio = sqrt(32.0 / molarMass.toDouble())

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Volume (1 mol)" to String.format("%.2f dm³", volL),
        "Kelvin Temp" to "${tempK.roundToInt()} K (${tempK.roundToInt() - 273}°C)",
        "Effusion vs O₂" to String.format("%.2f×", grahamSpeedRatio),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = molarMass < 5f, onClick = { molarMass = 4f }, label = { Text("He (M=4)") })
      FilterChip(selected = molarMass in 15f..18f, onClick = { molarMass = 16f }, label = { Text("CH₄ (M=16)") })
      FilterChip(selected = molarMass in 30f..34f, onClick = { molarMass = 32f }, label = { Text("O₂ (M=32)") })
      FilterChip(selected = molarMass > 60f, onClick = { molarMass = 64f }, label = { Text("SO₂ (M=64)") })
    }
    LabeledLabSlider(
      label = "Absolute Temperature T",
      valueText = "${tempK.roundToInt()} K (${tempK.roundToInt() - 273}°C)",
      value = tempK,
      onValueChange = { tempK = it },
      valueRange = 200f..600f,
    )
    LabeledLabSlider(
      label = "Gas Pressure P",
      valueText = String.format("%.2f atm", pressAtm),
      value = pressAtm,
      onValueChange = { pressAtm = it },
      valueRange = 0.5f..4.0f,
    )
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AcidBaseTitrationLab() {
  var naohMl by remember { mutableFloatStateOf(12f) }
  var weakAcid by remember { mutableStateOf(false) }
  // 25 mL 0.1 M acid -> eq at 25 mL
  val pH = when {
    naohMl < 24.5f -> if (weakAcid) 3.8f + (naohMl / 25f) * 1.9f else 1.0f + (naohMl / 25f) * 2.5f
    naohMl <= 25.5f -> if (weakAcid) 8.7f else 7.0f
    else -> 11.5f + ((naohMl - 25f) / 15f) * 1.5f
  }
  val indicatorColor = if (pH >= 8.3f) "PINK (Phenolphthalein Endpoint!)" else if (pH <= 3.1f) "RED (Methyl Orange)" else "Colourless / Yellow"

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "NaOH Added" to String.format("%.1f cm³", naohMl),
        "Flask pH" to String.format("%.2f", pH.coerceIn(0f, 14f)),
        "Indicator" to indicatorColor.substringBefore(" ("),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = !weakAcid, onClick = { weakAcid = false }, label = { Text("Strong HCl (Eq pH 7)") })
      FilterChip(selected = weakAcid, onClick = { weakAcid = true }, label = { Text("Weak CH₃COOH (Eq pH 8.7)") })
    }
    LabeledLabSlider(
      label = "Volume of 0.1 M NaOH Delivered from Burette",
      valueText = String.format("%.1f cm³", naohMl),
      value = naohMl,
      onValueChange = { naohMl = it },
      valueRange = 0f..40f,
    )
    ScientificText("Observation: $indicatorColor", style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EnthalpyBarrierLab() {
  var isExo by remember { mutableStateOf(true) }
  var catalystOn by remember { mutableStateOf(false) }
  val ea = if (catalystOn) 35 else 80
  val deltaH = if (isExo) -92 else +58

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "ΔH Sign" to "${if (deltaH > 0) "+" else ""}$deltaH kJ/mol",
        "Activation Eₐ" to "$ea kJ",
        "Effect on ΔH" to "Unchanged by Catalyst!",
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      FilterChip(selected = isExo, onClick = { isExo = true }, label = { Text("Exothermic (ΔH < 0)") })
      FilterChip(selected = !isExo, onClick = { isExo = false }, label = { Text("Endothermic (ΔH > 0)") })
      FilterChip(selected = catalystOn, onClick = { catalystOn = !catalystOn }, label = { Text(if (catalystOn) "Catalyst ON ✓" else "Add Catalyst") })
    }
  }
}

@Composable
private fun LeChatelierHaberLab() {
  var pressureAtm by remember { mutableFloatStateOf(200f) }
  var tempC by remember { mutableFloatStateOf(450f) }
  // N2 + 3H2 <=> 2NH3, dH = -92 kJ. Higher P shifts right, higher T shifts left
  val yieldPct = ((pressureAtm / 300f) * 55f * (500f / tempC)).coerceIn(8f, 88f)

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Pressure" to "${pressureAtm.roundToInt()} atm",
        "Temperature" to "${tempC.roundToInt()} °C",
        "NH₃ Yield" to String.format("%.1f%%", yieldPct),
      )
    )
    LabeledLabSlider(
      label = "Reaction Chamber Pressure P",
      valueText = "${pressureAtm.roundToInt()} atm",
      value = pressureAtm,
      onValueChange = { pressureAtm = it },
      valueRange = 50f..400f,
    )
    LabeledLabSlider(
      label = "Furnace Temperature T",
      valueText = "${tempC.roundToInt()} °C",
      value = tempC,
      onValueChange = { tempC = it },
      valueRange = 250f..650f,
    )
    ScientificText("N_2 + 3H_2 ⇌ 2NH_3 (4 mol gas → 2 mol gas): High pressure raises NH_3 yield; ~450 °C balances rate with iron catalyst.", style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RedoxCellEmfLab() {
  var cellIdx by remember { mutableIntStateOf(0) }
  val cells = listOf(
    Triple("Zn | Zn²⁺ || Cu²⁺ | Cu", "+1.10 V", "Anode (Oxidation): Zn → Zn²⁺ + 2e⁻ (−0.76 V)  •  Cathode (Reduction): Cu²⁺ + 2e⁻ → Cu (+0.34 V)"),
    Triple("Mg | Mg²⁺ || Ag⁺ | Ag", "+3.17 V", "Anode (Oxidation): Mg (−2.37 V)  •  Cathode (Reduction): Ag⁺ (+0.80 V)  •  High voltage cell!"),
    Triple("Fe | Fe²⁺ || Cu²⁺ | Cu", "+0.78 V", "Anode (Oxidation): Fe (−0.44 V)  •  Cathode (Reduction): Cu²⁺ (+0.34 V)  •  Explains sacrificial zinc/galvanizing!"),
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf("Zn / Cu (Daniell)", "Mg / Ag", "Fe / Cu").forEachIndexed { i, title ->
        FilterChip(selected = cellIdx == i, onClick = { cellIdx = i }, label = { Text(title) })
      }
    }
    LabMetricBanner(listOf("Cell Notation" to cells[cellIdx].first, "Standard E°_cell" to cells[cellIdx].second))
    ScientificText(cells[cellIdx].third, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FaradayElectrolysisLab() {
  var currentA by remember { mutableFloatStateOf(2.0f) }
  var mins by remember { mutableFloatStateOf(15f) }
  var ionIdx by remember { mutableIntStateOf(1) } // 0=Ag+(108,1), 1=Cu2+(64,2), 2=Al3+(27,3)
  val ions = listOf("Ag⁺ (1e⁻)" to Pair(108f, 1), "Cu²⁺ (2e⁻)" to Pair(64f, 2), "Al³⁺ (3e⁻)" to Pair(27f, 3))
  val (molar, valency) = ions[ionIdx].second
  val q = currentA * mins * 60f
  val massPlated = (molar * q) / (valency * 96500f)

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      listOf(
        "Charge Q = It" to "${q.roundToInt()} C",
        "Faradays" to String.format("%.3f F", q / 96500f),
        "Cathode Mass" to String.format("%.3f g", massPlated),
      )
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      ions.forEachIndexed { i, (label, _) ->
        FilterChip(selected = ionIdx == i, onClick = { ionIdx = i }, label = { Text(label) })
      }
    }
    LabeledLabSlider(
      label = "Steady Electrolysis Current I",
      valueText = String.format("%.1f A", currentA),
      value = currentA,
      onValueChange = { currentA = it },
      valueRange = 0.5f..10f,
    )
    LabeledLabSlider(
      label = "Electrolysis Duration t",
      valueText = "${mins.roundToInt()} min",
      value = mins,
      onValueChange = { mins = it },
      valueRange = 5f..60f,
    )
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GasIdentificationLab() {
  var gasIdx by remember { mutableIntStateOf(0) }
  val gases = listOf("NH₃ (Ammonia)", "Cl₂ (Chlorine)", "SO₂ (Sulphur IV)", "CO₂ (Carbon IV)")
  val results = listOf(
    "Pungent smell • Turns damp red litmus BLUE • Dense white NH₄Cl fumes with HCl • Dry with CaO ONLY!",
    "Greenish-yellow • Bleaches damp litmus permanently via OXIDATION • Displaces Br⁻/I⁻.",
    "Turns acidified K₂Cr₂O₇ paper ORANGE → GREEN (reduces Cr⁶⁺) • Temporary bleacher.",
    "Odourless • Turns clear Limewater Ca(OH)₂ MILKY white (insoluble CaCO₃), clears in excess!",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      gases.forEachIndexed { idx, g ->
        FilterChip(selected = gasIdx == idx, onClick = { gasIdx = idx }, label = { Text(g.substringBefore(" ")) })
      }
    }
    LabMetricBanner(listOf("Selected Gas" to gases[gasIdx]))
    ScientificText(results[gasIdx], style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MetalReactivityFurnaceLab() {
  var metalIdx by remember { mutableIntStateOf(0) }
  val metals = listOf("Aluminium (Al)", "Iron (Fe)", "Copper (Cu)", "Sodium (Na)")
  val extractions = listOf(
    "Electrolysis of Bauxite Al₂O₃ dissolved in molten Cryolite (950°C) — too reactive for Carbon!",
    "Blast Furnace reduction of Haematite Fe₂O₃ by CO + CaCO₃ removing SiO₂ as CaSiO₃ slag.",
    "Roasting Chalcopyrite sulfide ore followed by Electrolytic Refining (Copper dissolves from anode).",
    "Downs Cell Electrolysis of molten NaCl (+ CaCl₂ to lower melting point to 600°C).",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      metals.forEachIndexed { idx, m ->
        FilterChip(selected = metalIdx == idx, onClick = { metalIdx = idx }, label = { Text(m.substringBefore(" ")) })
      }
    }
    LabMetricBanner(listOf("Metal" to metals[metalIdx]))
    ScientificText(extractions[metalIdx], style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FunctionalGroupPolymerLab() {
  var idx by remember { mutableIntStateOf(0) }
  val pathways = listOf(
    "1° Alkanol Oxidation" to "Ethanol + acidified K₂Cr₂O₇ (Orange → Green) → Ethanal → Ethanoic Acid (CH₃COOH).",
    "Esterification" to "Ethanol + Ethanoic acid (+ Conc. H₂SO₄, heat) ⇌ Ethyl ethanoate (fruity pear aroma) + H₂O.",
    "Saponification (Soap)" to "Vegetable Fat/Palm Oil + 3 NaOH (boil) → Soap (Sodium carboxylate) + Glycerol.",
    "Addition vs Condensation" to "Addition (Ethene → Polythene, zero byproduct) vs Condensation (Nylon / Terylene / Starch, eliminates H₂O).",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      pathways.forEachIndexed { i, (label, _) ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(label.substringBefore(" (")) })
      }
    }
    LabMetricBanner(listOf("Organic Pathway" to pathways[idx].first))
    ScientificText(pathways[idx].second, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OrganicSeriesLab() {
  var idx by remember { mutableIntStateOf(0) }
  val series = listOf("Alkane (Ethane)", "Alkene (Ethene)", "Alkyne (Ethyne)", "Ester (Ethyl ethanoate)")
  val reactions = listOf(
    "Saturated C₂H₆ • Substitution with Cl₂/UV • Does NOT decolorize Br₂ water.",
    "Unsaturated C=C • Decolorizes brown Br₂ water & purple KMnO₄ • Polymerizes to Polythene.",
    "Triple bond H−C≡C−H • Decolorizes Br₂ AND forms RED Cu₂C₂ precipitate with Cu(NH₃)₂⁺!",
    "CH₃COOC₂H₅ • Sweet fruity pear scent • Made from Ethanol + Ethanoic acid (+ Conc. H₂SO₄).",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      series.forEachIndexed { i, s ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(s.substringBefore(" ")) })
      }
    }
    LabMetricBanner(listOf("Homologous Group" to series[idx]))
    ScientificText(reactions[idx], style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QualitativeCationBenchLab() {
  var cationIdx by remember { mutableIntStateOf(0) }
  val cations = listOf("Cu²⁺", "Fe²⁺", "Fe³⁺", "Zn²⁺", "Pb²⁺", "Ca²⁺", "NH₄⁺")
  val naohDrops = listOf("Pale blue ppt", "Dirty green ppt", "Reddish-brown ppt", "White gelatinous ppt", "White chalky ppt", "White powdery ppt", "No ppt (NH₃ gas on warming)")
  val excessNh3 = listOf("Soluble → Deep Royal Blue!", "Insoluble", "Insoluble", "Soluble → Colourless!", "Insoluble (Yellow with KI!)", "No ppt / Faint", "Turns moist red litmus BLUE")

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
      cations.forEachIndexed { idx, c ->
        FilterChip(selected = cationIdx == idx, onClick = { cationIdx = idx }, label = { Text(c) })
      }
    }
    LabMetricBanner(
      listOf(
        "Cation" to cations[cationIdx],
        "+ NaOH (Drops)" to naohDrops[cationIdx],
        "+ Excess NH₃" to excessNh3[cationIdx],
      )
    )
  }
}
