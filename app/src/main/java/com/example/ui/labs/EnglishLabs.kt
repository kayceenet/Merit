package com.example.ui.labs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText

@Composable
internal fun EnglishLabDispatcher(lessonId: String, labType: PracticalLabType) {
  when (labType) {
    PracticalLabType.PARTS_CLAUSES_LAB -> ClausesFunctionsLab(lessonId)
    PracticalLabType.SYNTAX_LEXIS_STUDIO -> ConcordTagsConditionalsLab(lessonId)
    PracticalLabType.LEXIS_IDIOMS_REGISTER_LAB -> IdiomsRegistersLab(lessonId)
    PracticalLabType.PHONETICS_CONSONANTS_LAB -> IpaVowelsConsonantsLab(lessonId)
    PracticalLabType.ORAL_ENGLISH_STRESS -> StressEmphaticLab()
    PracticalLabType.COMPREHENSION_ESSAY_LAB -> SummaryEssayFormatLab()
    else -> StressEmphaticLab()
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ClausesFunctionsLab(lessonId: String) {
  var idx by remember { mutableIntStateOf(0) }
  val samples = listOf(
    "\"WHAT SHE TOLD ME\" amazed the panel." to "Noun Clause • Subject of verb 'amazed' (IT-swap works!)",
    "The prefect \"WHO LOST THE KEY\" apologized." to "Adjectival Clause • Qualifies the noun 'prefect'",
    "We trained hard \"SO THAT WE MIGHT WIN\"." to "Adverbial Clause of Purpose • Modifies verb 'trained'",
    "\"Walking to class\", a bike splashed Ada." to "Dangling Modifier Error! Fix: '...she got splashed by a bike.'",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf("Noun Clause", "Adjectival", "Adverbial", "Modifier Fix").forEachIndexed { i, title ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(title) })
      }
    }
    LabMetricBanner(listOf("Sentence" to samples[idx].first, "WAEC Analysis" to samples[idx].second))
    if (lessonId == "eng_01_parts_affixes") {
      ScientificText("Countable rule: FEWER students (plural); LESS water / equipment / advice (uncountable).", style = MaterialTheme.typography.bodySmall)
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ConcordTagsConditionalsLab(lessonId: String) {
  var idx by remember { mutableIntStateOf(0) }
  val prompts = listOf(
    "The coach, as well as the boys, ___ coming." to "IS (ignore ', as well as...'; match singular 'coach')",
    "Neither Ada nor her brothers ___ late." to "WERE (proximity agrees with closer 'brothers')",
    "It is high time we ___ home." to "LEFT (overdue present uses Simple Past V₂!)",
    "She hardly talks in class, ___?" to "DOES SHE? ('hardly' makes main clause negative!)",
    "I am chosen for the debate, ___?" to "AREN'T I? (standard positive 'I am' tag)",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
      listOf("As Well As", "Neither/Nor", "High Time", "Hardly Tag", "I Am Tag").forEachIndexed { i, t ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(t) })
      }
    }
    LabMetricBanner(listOf("Prompt" to prompts[idx].first, "Rule Verdict" to prompts[idx].second))
    if (lessonId == "eng_04_tenses_conditionals") {
      ScientificText("Type 3 Inverted: 'Had I known...' = 'If I had known... + would have + V₃'.", style = MaterialTheme.typography.bodySmall)
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IdiomsRegistersLab(lessonId: String) {
  var idx by remember { mutableIntStateOf(0) }
  val entries = when (lessonId) {
    "eng_11_registers_cloze" -> listOf(
      "Courtroom Register" to "Plaintiff, Defendant, Bailiff, Arraign, Verdict, Acquitted OF charges",
      "Medical Register" to "Prognosis, Prescription, Scalpel, Sterilize, Ward, Outpatient",
      "Banking Register" to "Collateral, Overdraft, Dividend, Ledger, Solvency, Moratorium",
      "Aviation Register" to "Hangar (with A), Tarmac, Turbulence, Cockpit, Altitude",
    )
    else -> listOf(
      "White Elephant" to "Costly useless project (NEVER literal albino animal!)",
      "Call off vs Put off" to "'Call off' = Cancel completely; 'Put off' = Postpone till later",
      "Congratulate / Prefer" to "Congratulate someone ON (not for); Prefer tea TO coffee (not than)",
      "Comprise vs Consist" to "Comprises 5 units (ZERO 'of'!); Consists OF 5 units",
    )
  }
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      entries.forEachIndexed { i, (label, _) ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(label.substringBefore(" Register")) })
      }
    }
    LabMetricBanner(listOf("Key Pattern" to entries[idx].first))
    ScientificText(entries[idx].second, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IpaVowelsConsonantsLab(lessonId: String) {
  var idx by remember { mutableIntStateOf(0) }
  val items = if (lessonId == "eng_13_consonants_silent") {
    listOf(
      "-ed = /t/ (Voiceless)" to "washed /t/, laughed /t/, missed /t/, packed /t/ (after p, k, f, s, ʃ)",
      "-ed = /ɪd/ (Added Syl)" to "wanted /ɪd/, landed /ɪd/, hated /ɪd/ (after /t/ or /d/)",
      "/θ/ vs /ð/ Minimal Pair" to "Voiceless /θ/: think, bath, breath  |  Voiced /ð/: they, bathe, breathe",
      "Silent Letters Classics" to "psalm (p), receipt (p), subtle (b), corps /kɔː/ (p,s), sword (w)",
    )
  } else {
    listOf(
      "/iː/ Long vs /ɪ/ Short" to "/iː/: seat, key, quay, police  |  /ɪ/: sit, women, busy, village",
      "/ɜː/ Central Vowel" to "/ɜː/: bird, word, work, colonel, earn (beware 'w+or'!)",
      "/eɪ/ vs /eə/ Diphthongs" to "/eɪ/: great, break, steak, gauge  |  /eə/: air, heir, bear, wear",
      "/ʌ/ & /əʊ/ Tricky Spellings" to "/ʌ/: cup, blood, flood, love  |  /əʊ/: go, sew, though, dough",
    )
  }
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      items.forEachIndexed { i, (label, _) ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(label.substringBefore(" Tricky").substringBefore(" Minimal")) })
      }
    }
    LabMetricBanner(listOf("IPA Pattern" to items[idx].first))
    ScientificText(items[idx].second, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun StressEmphaticLab() {
  var idx by remember { mutableIntStateOf(0) }
  val patterns = listOf(
    "Noun vs Verb Twin" to "CON-duct / EX-port / OB-ject (Noun: 1st)  vs  con-DUCT / ex-PORT / ob-JECT (Verb: 2nd)",
    "-tion / -ic / -ian Rule" to "2nd syllable from end: in-for-MA-tion, pho-to-GRA-phic, mu-SI-cian",
    "-gy / -phy / -ty / -cy" to "3rd syllable from end: bi-O-lo-gy, pho-TO-gra-phy, u-ni-VER-si-ty, de-MO-cra-cy",
    "Emphatic Contrast" to "'Ada bought a NEW bag.' → Q: 'Did Ada buy an OLD bag?' (Contradict ONLY the CAPS word!)",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      patterns.forEachIndexed { i, (title, _) ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(title) })
      }
    }
    LabMetricBanner(listOf("Stress Rule" to patterns[idx].first))
    ScientificText(patterns[idx].second, style = MaterialTheme.typography.bodySmall)
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SummaryEssayFormatLab() {
  var idx by remember { mutableIntStateOf(0) }
  val templates = listOf(
    "WAEC Summary Paraphrase" to "BAD (Lifted): 'Washing away fertile topsoil.' → GOOD (Own Complete Sentence): 'Heavy erosion strips farmland of nutrients.'",
    "Formal Letter Blueprint" to "Writer's Addr (Top-Right) → Date → Recipient's Title/Addr (Left) → 'Dear Sir/Madam,' → Centered Heading → No Contractions → 'Yours faithfully,' + Signature + Full Name",
    "Informal Letter Blueprint" to "Writer's Addr (Top-Right) ONLY → Date → 'Dear Bisi,' → Warm conversational tone → 'Yours sincerely,' + First Name ONLY (NO signature!)",
  )
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf("Summary Rule", "Formal Letter", "Informal Letter").forEachIndexed { i, t ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(t) })
      }
    }
    LabMetricBanner(listOf("Format Checklist" to templates[idx].first))
    ScientificText(templates[idx].second, style = MaterialTheme.typography.bodySmall)
  }
}
