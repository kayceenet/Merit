package com.example.ui.labs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.ScientificText
import com.example.ui.components.rememberLessonTtsController

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
  val samples = when (lessonId) {
    "eng_01_parts_affixes" -> listOf(
      "\"Much INFORMATION / Few STUDENTS\"" to "Uncountables (information, advice, equipment) take MUCH/LITTLE & singular verb; Countables take FEWER/MANY",
      "Prefix 'in-' / 'un-' / 'dis-'" to "Reverses polarity (capable → incapable, honest → dishonest) without altering root spelling",
      "Suffix '-ize' / '-tion' / '-ly'" to "Shifts word class: Nation (Noun) → National (Adj) → Nationalize (Verb) → Nationally (Adv)",
      "'Each' & 'Every'" to "Distributive determiners take SINGULAR noun + SINGULAR verb ('Each candidate HAS a slip')",
    )
    "eng_03_sentences_modifiers" -> listOf(
      "\"Walking to class\", a bike splashed Ada." to "Dangling Modifier Error! Fix: 'Walking to class, Ada was splashed by a bike.'",
      "Comma Splice Repair" to "BAD: 'He studied hard, he passed.' → GOOD: 'He studied hard, and he passed.' / '; therefore, he passed.'",
      "Simple vs Compound Sentence" to "1 Independent clause (Simple) vs 2+ Independent clauses joined by FANBOYS or ';' (Compound)",
      "Complex Sentence Spine" to "1 Main clause + 1+ Subordinate clause linked by because/although/who/which",
    )
    else -> listOf(
      "\"WHAT SHE TOLD ME\" amazed the panel." to "Noun Clause • Subject of verb 'amazed' (IT-swap works!)",
      "The prefect \"WHO LOST THE KEY\" apologized." to "Adjectival Clause • Qualifies the noun 'prefect'",
      "We trained hard \"SO THAT WE MIGHT WIN\"." to "Adverbial Clause of Purpose • Modifies verb 'trained'",
      "She believes \"THAT HONESTY PAYS\"." to "Noun Clause • Direct Object of transitive verb 'believes'",
    )
  }
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      samples.forEachIndexed { i, (label, _) ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(label.take(16)) })
      }
    }
    LabMetricBanner(listOf("Pattern" to samples[idx].first, "WAEC Analysis" to samples[idx].second))
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ConcordTagsConditionalsLab(lessonId: String) {
  var idx by remember { mutableIntStateOf(0) }
  val prompts = when (lessonId) {
    "eng_04_tenses_conditionals" -> listOf(
      "She ___ here since 2021." to "HAS BEEN (Present Perfect bridges past start 'since' to now)",
      "Type 1 Probable Condition" to "If it RAINS (Present), we WILL STAY indoors (Future 'will')",
      "Type 2 Hypothetical Condition" to "If I WERE rich (Simple Past), I WOULD BUILD a library ('would + base')",
      "Type 3 Inverted Unreal" to "'Had I known...' (= If I had known...) → 'would have + V₃'",
    )
    "eng_05_subjunctive_passive" -> listOf(
      "It is high time we ___ home." to "LEFT (Simple Past V₂ marks overdue present action!)",
      "The chair insisted that he ___ present." to "BE / STAY (Mandative subjunctive uses bare base infinitive)",
      "Active → Passive Continuous" to "'Tunde is writing a letter' → 'A letter IS BEING WRITTEN by Tunde'",
      "I wish I ___ taller." to "WERE (Counterfactual wish always uses 'were', never 'was' in formal style)",
    )
    "eng_07_tags_punctuation" -> listOf(
      "She hardly talks in class, ___?" to "DOES SHE? ('hardly/seldom/scarcely' make statement negative → positive tag)",
      "I am chosen for the debate, ___?" to "AREN'T I? (Standard positive 'I am' question tag)",
      "Let's review the notes, ___?" to "SHALL WE? ('Let's...' takes 'shall we'; imperatives take 'will you')",
      "Its vs It's" to "ITS = possessive ('its tail'); IT'S = 'it is / it has' contraction",
    )
    else -> listOf(
      "The coach, as well as the boys, ___ coming." to "IS (ignore ', as well as...'; match singular 'coach')",
      "Neither Ada nor her brothers ___ late." to "WERE (proximity agrees with closer plural 'brothers')",
      "The number of candidates ___ risen." to "HAS ('The number' = singular vs 'A number of students' = plural)",
      "Bread and butter ___ his favourite breakfast." to "IS (single combined meal notion → singular verb)",
    )
  }
  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
      prompts.forEachIndexed { i, (label, _) ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(label.take(15)) })
      }
    }
    LabMetricBanner(listOf("Prompt" to prompts[idx].first, "Rule Verdict" to prompts[idx].second))
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IdiomsRegistersLab(lessonId: String) {
  var idx by remember { mutableIntStateOf(0) }
  val entries = when (lessonId) {
    "eng_08_synonyms_antonyms" -> listOf(
      "Word-Class Lock" to "Noun prompts require Noun replacements; Verb prompts require Verb equivalents in identical tense.",
      "Precision Antonyms" to "'Magnanimous' ↔ 'Vindictive/Petty' (NOT merely 'angry'); watch 'Opposite in Meaning' headers!",
      "Context Polarity" to "'Austere budget' = strict/frugal; 'Auspicious start' = favourable/promising.",
      "Substitution Check" to "Always re-read the original sentence with your chosen option inserted before locking it.",
    )
    "eng_10_prepositions_confusables" -> listOf(
      "Congratulate / Prefer" to "Congratulate someone ON (not for); Prefer tea TO coffee (not than)",
      "Comprise vs Consist" to "Comprises 5 units (ZERO 'of'!); Consists OF 5 units",
      "Zero Redundant Preps" to "Say 'discuss the plan' (NOT discuss about), 'cope with' (NOT cope up with)",
      "Stationary vs Stationery" to "Stationary = motionless (A as in 'at rest'); Stationery = paper/pens (E as in 'envelopes')",
    )
    "eng_11_registers_cloze" -> listOf(
      "Courtroom Register" to "Plaintiff, Defendant, Bailiff, Arraign, Verdict, Acquitted OF charges",
      "Medical Register" to "Prognosis, Prescription, Scalpel, Sterilize, Ward, Outpatient",
      "Banking Register" to "Collateral, Overdraft, Dividend, Ledger, Solvency, Moratorium",
      "Aviation Register" to "Hangar (with A), Tarmac, Turbulence, Cockpit, Altitude",
    )
    else -> listOf(
      "White Elephant" to "Costly useless project (NEVER literal albino animal!)",
      "Call off vs Put off" to "'Call off' = Cancel completely; 'Put off' = Postpone till later",
      "Take After / Put Up With" to "'Take after' = Resemble; 'Put up with' = Tolerate patiently",
      "Bite the Bullet" to "Face an unavoidable hardship with courage",
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
  val ttsController = rememberLessonTtsController()
  val items = if (lessonId == "eng_13_consonants_silent") {
    listOf(
      Triple("-ed = /t/ (Voiceless)", "washed /t/, laughed /t/, missed /t/, packed /t/ (after p, k, f, s, ʃ)", "washed. laughed. missed. packed."),
      Triple("-ed = /ɪd/ (Added Syl)", "wanted /ɪd/, landed /ɪd/, hated /ɪd/ (after /t/ or /d/)", "wanted. landed. hated."),
      Triple("/θ/ vs /ð/ Minimal Pair", "Voiceless /θ/: think, bath, breath  |  Voiced /ð/: they, bathe, breathe", "think, bath, breath. Versus: they, bathe, breathe."),
      Triple("Silent Letters Classics", "psalm (p), receipt (p), subtle (b), corps /kɔː/ (p,s), sword (w)", "psalm. receipt. subtle. corps. sword."),
    )
  } else {
    listOf(
      Triple("/iː/ Long vs /ɪ/ Short", "/iː/: seat, key, quay, police  |  /ɪ/: sit, women, busy, village", "Long vowel: seat, key, quay, police. Short vowel: sit, women, busy, village."),
      Triple("/ɜː/ Central Vowel", "/ɜː/: bird, word, work, colonel, earn (beware 'w+or'!)", "bird, word, work, colonel, earn."),
      Triple("/eɪ/ vs /eə/ Diphthongs", "/eɪ/: great, break, steak, gauge  |  /eə/: air, heir, bear, wear", "great, break, steak, gauge. Versus: air, heir, bear, wear."),
      Triple("/ʌ/ & /əʊ/ Tricky Spellings", "/ʌ/: cup, blood, flood, love  |  /əʊ/: go, sew, though, dough", "cup, blood, flood, love. Versus: go, sew, though, dough."),
    )
  }
  val ttsKey = "ipa_lab_${lessonId}_$idx"
  val isSpeaking = ttsController.isSpeaking && ttsController.activeUtteranceKey == ttsKey

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      items.forEachIndexed { i, item ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(item.first.substringBefore(" Tricky").substringBefore(" Minimal")) })
      }
    }
    LabMetricBanner(listOf("IPA Pattern" to items[idx].first))
    ScientificText(items[idx].second, style = MaterialTheme.typography.bodySmall)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      OutlinedButton(
        onClick = { ttsController.toggleSpeak(ttsKey, items[idx].third) },
        modifier = Modifier.weight(1f).minimumInteractiveComponentSize(),
      ) {
        Icon(
          imageVector = if (isSpeaking) Icons.Filled.Stop else Icons.AutoMirrored.Filled.VolumeUp,
          contentDescription = "Pronounce minimal pair",
          modifier = Modifier.size(18.dp),
        )
        Spacer(Modifier.width(6.dp))
        Text(if (isSpeaking) "Stop Audio" else "Pronounce Words (${ttsController.speechRate}×)")
      }
      OutlinedButton(
        onClick = { ttsController.cycleSpeed() },
        modifier = Modifier.minimumInteractiveComponentSize(),
      ) {
        Text("${ttsController.speechRate}×")
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun StressEmphaticLab() {
  var idx by remember { mutableIntStateOf(0) }
  val ttsController = rememberLessonTtsController()
  val patterns = listOf(
    Triple("Noun vs Verb Twin", "CON-duct / EX-port / OB-ject (Noun: 1st)  vs  con-DUCT / ex-PORT / ob-JECT (Verb: 2nd)", "Noun: CONduct, EXport, OBject. Verb: conDUCT, exPORT, obJECT."),
    Triple("-tion / -ic / -ian Rule", "2nd syllable from end: in-for-MA-tion, pho-to-GRA-phic, mu-SI-cian", "information. photographic. musician."),
    Triple("-gy / -phy / -ty / -cy", "3rd syllable from end: bi-O-lo-gy, pho-TO-gra-phy, u-ni-VER-si-ty, de-MO-cra-cy", "biology. photography. university. democracy."),
    Triple("Emphatic Contrast", "'Ada bought a NEW bag.' → Q: 'Did Ada buy an OLD bag?' (Contradict ONLY the CAPS word!)", "Ada bought a NEW bag. Question: Did Ada buy an OLD bag?"),
  )
  val ttsKey = "stress_lab_$idx"
  val isSpeaking = ttsController.isSpeaking && ttsController.activeUtteranceKey == ttsKey

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      patterns.forEachIndexed { i, item ->
        FilterChip(selected = idx == i, onClick = { idx = i }, label = { Text(item.first) })
      }
    }
    LabMetricBanner(listOf("Stress Rule" to patterns[idx].first))
    ScientificText(patterns[idx].second, style = MaterialTheme.typography.bodySmall)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      OutlinedButton(
        onClick = { ttsController.toggleSpeak(ttsKey, patterns[idx].third) },
        modifier = Modifier.weight(1f).minimumInteractiveComponentSize(),
      ) {
        Icon(
          imageVector = if (isSpeaking) Icons.Filled.Stop else Icons.AutoMirrored.Filled.VolumeUp,
          contentDescription = "Listen to stress pattern",
          modifier = Modifier.size(18.dp),
        )
        Spacer(Modifier.width(6.dp))
        Text(if (isSpeaking) "Stop Audio" else "Hear Stress Contrast (${ttsController.speechRate}×)")
      }
      OutlinedButton(
        onClick = { ttsController.cycleSpeed() },
        modifier = Modifier.minimumInteractiveComponentSize(),
      ) {
        Text("${ttsController.speechRate}×")
      }
    }
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
