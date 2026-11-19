package com.example.ui.labs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText
import com.example.ui.theme.semanticColors
import kotlin.math.roundToInt

@Composable
internal fun SocialScienceLabDispatcher(lessonId: String, labType: PracticalLabType) {
  when (labType) {
    PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB -> ElasticityMarketSimulatorLab()
    PracticalLabType.ECONOMICS_MACRO_POLICY_LAB -> MacroMonetaryPolicyLab()
    PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB -> GovernmentConstitutionalLab(lessonId)
    else -> ElasticityMarketSimulatorLab()
  }
}

@Composable
private fun ElasticityMarketSimulatorLab() {
  var pedAbs by remember { mutableFloatStateOf(0.6f) }
  var priceStepPct by remember { mutableFloatStateOf(20f) }
  var exciseTax by remember { mutableFloatStateOf(30f) }

  val qChangePct = -pedAbs * priceStepPct
  val newQFactor = (1f + qChangePct / 100f).coerceAtLeast(0.05f)
  val newPFactor = 1f + priceStepPct / 100f
  val trChangePct = (newPFactor * newQFactor - 1f) * 100f

  // Assume supply PES ~ 1.0 for clean incidence split: buyer share = PES / (PES + PED)
  val pes = 1.0f
  val buyerTaxShare = (pes / (pes + pedAbs)).coerceIn(0.05f, 0.95f)
  val buyerBurden = exciseTax * buyerTaxShare
  val sellerBurden = exciseTax - buyerBurden

  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val errorColor = MaterialTheme.colorScheme.error
  val successColor = MaterialTheme.semanticColors.success

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      items =
        listOf(
          "|PED|" to String.format("%.2f (%s)", pedAbs, if (pedAbs > 1f) "Elastic" else if (pedAbs < 0.95f) "Inelastic" else "Unitary"),
          "TR Change" to String.format("%+.1f%%", trChangePct),
          "Buyer Tax Share" to "${(buyerTaxShare * 100).roundToInt()}%",
        )
    )

    Canvas(
      modifier =
        Modifier.fillMaxWidth()
          .height(140.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
          .padding(12.dp)
    ) {
      val w = size.width
      val h = size.height
      // Axes
      drawLine(secondary.copy(alpha = 0.5f), Offset(16f, 10f), Offset(16f, h - 14f), 3f)
      drawLine(secondary.copy(alpha = 0.5f), Offset(16f, h - 14f), Offset(w - 10f, h - 14f), 3f)

      // Demand curve steepness governed inversely by |PED|
      val steepShift = ((2.2f - pedAbs.coerceIn(0.2f, 2.2f)) / 2.0f) * (h * 0.35f)
      val dStart = Offset(w * 0.22f, h * 0.14f)
      val dEnd = Offset(w * 0.85f - steepShift * 0.45f, h * 0.82f)
      drawLine(primary, dStart, dEnd, strokeWidth = 5f, cap = StrokeCap.Round)

      // Base Supply S0 and Tax-shifted S1
      val taxShiftY = (exciseTax / 50f) * (h * 0.25f)
      drawLine(successColor, Offset(w * 0.20f, h * 0.80f), Offset(w * 0.82f, h * 0.18f), strokeWidth = 4f, cap = StrokeCap.Round)
      drawLine(
        errorColor,
        Offset(w * 0.20f, (h * 0.80f - taxShiftY).coerceAtLeast(14f)),
        Offset(w * 0.82f, (h * 0.18f - taxShiftY).coerceAtLeast(10f)),
        strokeWidth = 4f,
        cap = StrokeCap.Round,
      )
    }

    LabeledLabSlider(
      label = "Price Elasticity |PED| (Low = Necessity, High = Close Substitutes)",
      valueText = String.format("%.2f", pedAbs),
      value = pedAbs,
      onValueChange = { pedAbs = it },
      valueRange = 0.2f..2.5f,
    )
    LabeledLabSlider(
      label = "Firm Price Hike (%ΔP) to Test Total Revenue Direction",
      valueText = "${priceStepPct.roundToInt()}%",
      value = priceStepPct,
      onValueChange = { priceStepPct = it },
      valueRange = 5f..40f,
    )
    LabeledLabSlider(
      label = "Indirect Excise Tax Per Unit (NGN)",
      valueText = "₦${exciseTax.roundToInt()}",
      value = exciseTax,
      onValueChange = { exciseTax = it },
      valueRange = 0f..50f,
    )

    Surface(
      color = MaterialTheme.colorScheme.secondaryContainer,
      shape = RoundedCornerShape(10.dp),
      modifier = Modifier.fillMaxWidth(),
    ) {
      ScientificText(
        text =
          String.format(
            "Tax Incidence Split: Buyers absorb ₦%.1f (%d%%), Sellers absorb ₦%.1f (%d%%). Note how raising price when |PED| < 1 raises Total Revenue, whereas raising price when |PED| > 1 cuts Total Revenue.",
            buyerBurden,
            (buyerTaxShare * 100).roundToInt(),
            sellerBurden,
            100 - (buyerTaxShare * 100).roundToInt(),
          ),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = Modifier.padding(10.dp),
      )
    }
  }
}

@Composable
private fun MacroMonetaryPolicyLab() {
  var mpc by remember { mutableFloatStateOf(0.75f) }
  var stimulusB by remember { mutableFloatStateOf(40f) }
  var reserveRatioPct by remember { mutableFloatStateOf(20f) }

  val mps = (1f - mpc).coerceAtLeast(0.05f)
  val k = 1f / mps
  val deltaY = k * stimulusB
  val bankMult = 100f / reserveRatioPct.coerceAtLeast(5f)
  val totalDepositsFrom10k = 10_000f * bankMult

  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    LabMetricBanner(
      items =
        listOf(
          "Multiplier k" to String.format("%.2f×", k),
          "GDP Expansion ΔY" to String.format("₦%.1fB", deltaY),
          "Credit Mult (1/r)" to String.format("%.1f×", bankMult),
        )
    )

    Canvas(
      modifier =
        Modifier.fillMaxWidth()
          .height(110.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(MaterialTheme.colorScheme.background)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
          .padding(12.dp)
    ) {
      val w = size.width
      val h = size.height
      val maxDisplay = 400f
      val stimRatio = (stimulusB / maxDisplay).coerceIn(0.06f, 0.9f)
      val gdpRatio = (deltaY / maxDisplay).coerceIn(0.08f, 0.96f)

      // Stimulus bar
      drawRect(secondary, topLeft = Offset(10f, h * 0.15f), size = androidx.compose.ui.geometry.Size(w * stimRatio, h * 0.28f))
      // Multiplied ΔY bar
      drawRect(primary, topLeft = Offset(10f, h * 0.56f), size = androidx.compose.ui.geometry.Size(w * gdpRatio, h * 0.28f))
    }

    LabeledLabSlider(
      label = "Marginal Propensity to Consume (MPC = ΔC / ΔY)",
      valueText = String.format("%.2f (MPS %.2f)", mpc, mps),
      value = mpc,
      onValueChange = { mpc = it },
      valueRange = 0.40f..0.90f,
    )
    LabeledLabSlider(
      label = "Autonomous Fiscal / Investment Injection ΔG or ΔI",
      valueText = "₦${stimulusB.roundToInt()}B",
      value = stimulusB,
      onValueChange = { stimulusB = it },
      valueRange = 10f..100f,
    )
    LabeledLabSlider(
      label = "CBN Statutory Cash Reserve Ratio r (%)",
      valueText = "${reserveRatioPct.roundToInt()}%",
      value = reserveRatioPct,
      onValueChange = { reserveRatioPct = it },
      valueRange = 5f..40f,
    )

    Surface(
      color = MaterialTheme.colorScheme.secondaryContainer,
      shape = RoundedCornerShape(10.dp),
      modifier = Modifier.fillMaxWidth(),
    ) {
      ScientificText(
        text =
          String.format(
            "An initial ₦10,000 bank deposit creates up to ₦%,.0f total deposits (Net New Credit ₦%,.0f). Notice how raising Reserve Ratio r shrinks 1/r credit expansion sharply during inflation!",
            totalDepositsFrom10k,
            totalDepositsFrom10k - 10_000f,
          ),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = Modifier.padding(10.dp),
      )
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GovernmentConstitutionalLab(lessonId: String) {
  var systemTab by remember { mutableIntStateOf(if (lessonId.contains("1963")) 1 else 0) }
  var listItemIdx by remember { mutableIntStateOf(0) }
  var congressSharePct by remember { mutableFloatStateOf(68f) }

  val listAudits =
    listOf(
      "Minting Currency / CBN" to "EXCLUSIVE FEDERAL LIST → Any State currency law is automatically VOID.",
      "University Standards" to "CONCURRENT LIST → Valid for States, EXCEPT where conflicting Federal Act PREVAILS.",
      "Chieftaincy / Markets" to "RESIDUAL LIST (STATES) → States legislate exclusively; omitted from central lists.",
      "Public Health / Electricity" to "CONCURRENT LIST → Shared Federal-State jurisdiction (Federal covers clashes).",
    )

  val overridePasses = congressSharePct >= 66.7f

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf("1999 Presidential System", "1963 Parliamentary System").forEachIndexed { idx, label ->
        val selected = systemTab == idx
        FilterChip(
          selected = selected,
          onClick = { systemTab = idx },
          label = { Text(label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
          colors =
            FilterChipDefaults.filterChipColors(
              selectedContainerColor = MaterialTheme.colorScheme.primary,
              selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            ),
        )
      }
    }

    if (systemTab == 0) {
      LabMetricBanner(
        items =
          listOf(
            "Executive Model" to "Single Head (Pres.)",
            "Ministers in NASS?" to "Strictly Barred (Sep.)",
            "Removal Check" to "2/3 Impeachment",
          )
      )
    } else {
      LabMetricBanner(
        items =
          listOf(
            "Executive Model" to "Dual (Pres + PM)",
            "Ministers in Parlt?" to "Mandatory MPs (Fusion)",
            "Removal Check" to "Simple No-Confidence",
          )
      )
    }

    Text(
      text = "Federal List Constitutionality Checker (1999 Schedule II)",
      style = MaterialTheme.typography.labelLarge,
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.Bold,
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listAudits.forEachIndexed { i, (label, _) ->
        FilterChip(
          selected = listItemIdx == i,
          onClick = { listItemIdx = i },
          label = { Text(label, style = MaterialTheme.typography.labelSmall) },
        )
      }
    }
    Surface(
      color = MaterialTheme.colorScheme.surfaceVariant,
      shape = RoundedCornerShape(10.dp),
      modifier = Modifier.fillMaxWidth(),
    ) {
      ScientificText(
        text = listAudits[listItemIdx].second,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(10.dp),
      )
    }

    LabeledLabSlider(
      label = "Presidential Veto Override Vote Across Both Houses (%)",
      valueText = "${congressSharePct.roundToInt()}% (${if (overridePasses) "≥ 2/3 OVERRIDES!" else "< 2/3 Veto Stands"})",
      value = congressSharePct,
      onValueChange = { congressSharePct = it },
      valueRange = 40f..90f,
    )
  }
}
