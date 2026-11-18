package com.example.data.curriculum

object EnglishCurriculumPart3 {

  val module5: CourseModule =
    CourseModule(
      id = "eng_mod_5",
      subjectId = "english",
      indexLabel = "Module 05",
      title = "Consonants, Word/Emphatic Stress & Comprehension / Essay Craft",
      summary = "Master IPA Consonants & Silent Letters, Syllable & Emphatic Stress rules, Rhymes, and full-mark WAEC Summary & Essay strategies.",
      lessons =
        listOf(
          Lesson(
            id = "eng_13_consonants_silent",
            moduleId = "eng_mod_5",
            subjectId = "english",
            title = "Consonants, Voicing (-ed / -s), & Silent Letters",
            durationMinutes = 19,
            syllabusTag = "JAMB Sec. C2 • WAEC Orals: Consonants & Inflection Rules",
            learningOutcomes =
              listOf(
                "Distinguish Voiced throat-buzz (/b, d, g, v, z, ð, dʒ/) from Voiceless whisper (/p, t, k, f, s, θ, tʃ, ʃ/).",
                "Predict exact plural '-s' (/s, z, ɪz/) and past '-ed' (/t, d, ɪd/) endings automatically!",
                "Spot common Silent Letters ('psalm, subtle, plumb, sword, corps, chassis, rendezvous').",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. Put Two Fingers on Your Throat! (Voiced vs Voiceless & '-ed / -s' Rules)",
                  explanation =
                      "Rest two fingers lightly on your throat and whisper 'SSSS' (no buzz = Voiceless!) then hum 'ZZZZ' (throat vibrates = Voiced!). Here is magic: Past tense '-ed' copies its neighbour! After Voiceless (/p, k, f, s, ʃ, tʃ/), '-ed' sounds like /t/ ('kissed = kist', 'laughed = laft')! After Voiced sounds, '-ed' sounds like /d/ ('played')! After /t/ or /d/ itself, it adds a whole syllable /ɪd/ ('wanted, landed')!",
                  keyFormula = "-\\text{ed}:\\; /\\text{t}/\\text{ after voiceless}   |   /\\text{d}/\\text{ after voiced}   |   /\\text{ɪd}/\\text{ after } t,d",
                  examTip =
                      "Watch the two 'TH' sounds: THin / θink / breath = Voiceless /θ/; THe / THis / breathe / brother = Voiced /ð/!",
                ),
                ConceptBlock(
                  heading = "2. Infamous Silent Letters & Tricky Digraphs ('ch', 'gh', 's')",
                  explanation =
                      "Don't let eyes fool ears! Silent B: 'debt, doubt, subtle, bomb, plumber'. Silent P: 'psalm, receipt, pneumonia, corps (/kɔː/!)'. Silent S: 'island, aisle, debris, chassis'. Look out for 'CH': 'church' = /tʃ/, 'machine / chalet / chef / champagne' = /ʃ/ ('sh'!), 'chemist / chaos / chord / echo' = /k/!",
                  keyFormula = "/\\text{ʃ}/:\\; \\text{nation, sugar, chef, ocean}   |   /\\text{ʒ}/:\\; \\text{measure, vision, garage}   |   /\\text{dʒ}/:\\; \\text{judge, soldier, edge}",
                  examTip =
                      "JAMB favorite: 'OF' ends in voiced /v/, whereas 'OFF' ends in voiceless /f/! And 'houSe' (noun) = /s/, while 'to houSe' (verb) = /z/!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Decoding '-ed' Past Tense Sounds",
                  examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Which word has the SAME '-ed' ending sound as 'WASH<u>ED</u>' (/t/): (A) begged (B) heated (C) packed (D) played?",
                  steps =
                    listOf(
                      "Step 1 — Root 'wash' ends in voiceless /ʃ/ ⇒ '-ed' pronounces as /t/ ('washt').",
                      "Step 2 — Check options: begged (/d/), heated (/ɪd/), PACKED (ends in voiceless /k/ ⇒ 'packt' = /t/!), played (/d/).",
                    ),
                  finalAnswer = "(C) packed (/t/)",
                  examinerTrap = "Assuming '-ed' ALWAYS sounds like /d/ or /ɪd/!",
                ),
                WorkedExample(
                  title = "Pinpointing /θ/ vs /ð/ and Silent 'b'",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement = "Pick the word containing the same consonant sound as the underlined in '<u>th</u>ose' (/ð/): (A) author (B) breath (C) bathe (D) bath.",
                  steps =
                    listOf(
                      "Step 1 — 'those' starts with VOICED /ð/ (throat vibrates).",
                      "Step 2 — author (/θ/), breath (/θ/), BATHE (/ð/ voiced verb!), bath (/θ/ voiceless noun).",
                    ),
                  finalAnswer = "(C) bathe (/ð/)",
                  examinerTrap = "'Bath/Breath' (nouns, no final -e) use voiceless /θ/; 'Bathe/Breathe' (verbs, with -e) vibrate voiced /ð/!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "French Loanwords in Diplomacy & Engineering",
                  industryDomain = "Automotive & Hospitality",
                  narrative =
                      "Auto engineers say 'chassis' (/ˈʃæsi/), chefs say 'cuisine / chalet / champagne' (/ʃ/), and diplomats say 'corps' (/kɔː/) accurately without sounding foreign.",
                  examConnection = "French loanwords often keep silent final letters and soft 'ch' = /ʃ/.",
                ),
              ),
            labType = PracticalLabType.PHONETICS_CONSONANTS_LAB,
            labBrief =
              "Test Voiced vs Voiceless '-ed/-s' inflection endings, switch between /θ/ vs /ð/ minimal pairs, and reveal Silent Letters live!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_eng_13_1",
                  topic = "Voiceless '-ed' Ending",
                  examType = "JAMB",
                  year = 2024,
                  question = "Which word ends with the same consonant sound as 'laugh<u>ed</u>' (/t/)?",
                  options = listOf("loved", "waited", "missed", "roared"),
                  correctIndex = 2,
                  hint = "'Laugh' ends in voiceless /f/; 'miss' ends in voiceless /s/.",
                  stepByStepSolution = "Both 'laughed' (/lɑːft/) and 'missed' (/mɪst/) end in voiceless /t/.",
                ),
                PracticeQuestion(
                  id = "ex_eng_13_2",
                  topic = "Voiced /ʒ/ Sound",
                  examType = "WAEC",
                  year = 2023,
                  question = "Identify the word containing the consonant sound /ʒ/ as in 'trea<u>s</u>ure':",
                  options = listOf("pressure", "decision", "mission", "passion"),
                  correctIndex = 1,
                  hint = "Vowel + '-sion' = voiced /ʒ/ ('vision, decision'); consonant + '-ssion' = voiceless /ʃ/!",
                  stepByStepSolution = "'Decision' /dɪˈsɪʒən/ contains voiced palatal fricative /ʒ/.",
                ),
                PracticeQuestion(
                  id = "ex_eng_13_3",
                  topic = "Silent Letters",
                  examType = "JAMB",
                  year = 2025,
                  question = "Which word contains a completely SILENT letter 'p'?",
                  options = listOf("capture", "receipt", "capable", "chapter"),
                  correctIndex = 1,
                  hint = "Same family as 'psalm' and 'pneumonia'.",
                  stepByStepSolution = "'Receipt' is pronounced /rɪˈsiːt/ with silent p.",
                ),
                PracticeQuestion(
                  id = "ex_eng_13_4",
                  topic = "Tricky 'ch' Digraph",
                  examType = "WAEC",
                  year = 2022,
                  question = "Pick the word whose 'ch' is pronounced /k/ (NOT /tʃ/ or /ʃ/):",
                  options = listOf("chief", "machine", "chaos", "brochure"),
                  correctIndex = 2,
                  hint = "Greek-origin science/philosophy words use /k/.",
                  stepByStepSolution = "'Chaos' (/ˈkeɪɒs/) uses /k/ (machine & brochure use /ʃ/; chief uses /tʃ/).",
                ),
                PracticeQuestion(
                  id = "ex_eng_13_5",
                  topic = "Throat Buzz /θ/ vs /ð/",
                  examType = "JAMB",
                  year = 2021,
                  question = "Which word shares the voiceless /θ/ sound with '<u>th</u>ink'?",
                  options = listOf("worthy", "breathe", "ether", "brother"),
                  correctIndex = 2,
                  hint = "Worthy, breathe, and brother all vibrate voiced /ð/.",
                  stepByStepSolution = "'Ether' /ˈiːθə/ (and 'healthy, author, bath') uses voiceless /θ/.",
                ),
                PracticeQuestion(
                  id = "ex_eng_13_6",
                  topic = "Nasal /ŋ/ Sound",
                  examType = "NECO",
                  year = 2024,
                  question = "Which word contains the nasal velar /ŋ/ sound?",
                  options = listOf("thin", "singer", "sinner", "gentle"),
                  correctIndex = 1,
                  hint = "'ng' (and 'n' before k/g like 'bank') produces /ŋ/.",
                  stepByStepSolution = "'Singer' /ˈsɪŋə/ uses the /ŋ/ nasal sound.",
                ),
              ),
          ),

          Lesson(
            id = "eng_14_stress_rhyme",
            moduleId = "eng_mod_5",
            subjectId = "english",
            title = "Syllable Stress, Suffix Shifts, Emphatic Stress & Rhymes",
            durationMinutes = 20,
            syllabusTag = "JAMB Sec. C3 • WAEC Stress Patterns & Rhyme",
            learningOutcomes =
              listOf(
                "Predict primary stress on 2-syllable Noun/Verb twins (REcord vs reCORD; OBject vs obJECT).",
                "Apply Suffix Stress Rules: '-tion / -sion / -ic / -ian' pull stress to the SECOND-TO-LAST syllable; '-cy / -ty / -phy / -gy / -al' pull stress 3 FROM THE END!",
                "Solve 100% of Emphatic Stress questions by finding the option that CONTRADICTS ONLY the CAPS word!",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. The 3 Unbreakable Word-Stress Shortcuts!",
                  explanation =
                      "Rule A (2-Syllable Twin Rule): NOUN/ADJECTIVE = stress FIRST syllable ('CONduct, EXport, PREsent, REbel'); VERB action = stress SECOND syllable ('conDUCT, exPORT, preSENT, reBEL')! Rule B (-tion/-ic/-ian = Penultimate): Stress lands RIGHT BEFORE '-tion / -ic / -ian' ('inforMAtion, eLECtric, muSIcian')! Rule C (-gy/-phy/-ty/-cy/-ical = 3rd From Back): Count 3 back from the end ('biOlogy, phoTOgraphy, deMOcracy, uNIversity, poLItical')!",
                  keyFormula = "\\text{-tion/-ic/-ian} \\implies \\text{2nd-last}   |   \\text{-gy/-phy/-ty/-cy} \\implies \\text{3rd-last}   |   \\text{-ee/-eer/-esque} \\implies \\text{ON itself!}",
                  examTip =
                      "Words ending in '-ee', '-eer', '-oo', '-ette', '-esque' pull stress straight onto the ENDING itself: 'employEE, enginEER, cigaRETTE, picturESQUE'!",
                ),
                ConceptBlock(
                  heading = "2. Emphatic Stress & Rhymes (Easiest Full Marks in JAMB!)",
                  explanation =
                      "In Emphatic Stress, ONE word is typed in ALL CAPS (e.g., 'Ada bought a NEW dress yesterday.'). To pick the right question that matches this reply: keep EVERY non-caps word identical ('Ada, bought, dress, yesterday'), and CONTRADICT ONLY the CAPS word ('Did Ada buy an OLD dress yesterday?')! For Rhymes: stress + final vowel + ending consonant must match from the last stressed syllable onward (ignoring silent letters: 'comb / home', 'reign / vein', 'climb / thyme'!)!",
                  keyFormula = "\\text{Emphatic Rule: Match ALL non-CAPS words} + \\text{Contradict ONLY the ALL-CAPS word!}",
                  examTip =
                      "NEVER rhyme 'love' with 'move' or 'cough' with 'bough'—check exact spoken vowel sound!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Applying '-phy' and '-ic' Suffix Stress Shifts",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement =
                      "Mark primary stress (CAPS) on: (1) photography, (2) photographic, (3) democracy.",
                  steps =
                    listOf(
                      "Step 1 — 'photography' ends in '-phy' → 3rd from end: pho-TO-gra-phy (2nd syllable!).",
                      "Step 2 — 'photographic' ends in '-ic' → 2nd from end (right before '-ic'): pho-to-GRA-phic (3rd syllable!).",
                      "Step 3 — 'democracy' ends in '-cy' → 3rd from end: de-MO-cra-cy (2nd syllable!).",
                    ),
                  finalAnswer = "pho-TO-gra-phy  |  pho-to-GRA-phic  |  de-MO-cra-cy",
                  examinerTrap = "Saying 'PHOtography' or 'DEmocracy' puts stress one syllable too early!",
                ),
                WorkedExample(
                  title = "Cracking Emphatic Stress in 5 Seconds",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement =
                      "Statement: 'The principal praised MUSA for honesty.' Pick the question this statement answers cleanly.",
                  steps =
                    listOf(
                      "Step 1 — CAPS word is MUSA (keep 'principal, praised, honesty' unchanged!).",
                      "Step 2 — Replace MUSA with another person: 'Did the principal praise NGOZI for honesty?'",
                    ),
                  finalAnswer = "Did the principal praise Ngozi for honesty?",
                  examinerTrap = "Picking 'Did the principal SCOLD Musa for honesty?' contradicts the wrong word!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Public Speaking & Persuasive Pitch Delivery",
                  industryDomain = "Communications & Law",
                  narrative =
                      "Placing tonic stress intentionally lets one 6-word sentence ('I didn't say he stole it') carry 6 completely distinct courtroom accusations depending on which word gets emphasized!",
                  examConnection = "Tonic emphasis reverses presuppositions cleanly.",
                ),
              ),
            labType = PracticalLabType.ORAL_ENGLISH_STRESS,
            labBrief =
              "Tap syllables on Noun/Verb pairs, '-tion/-ic/-phy/-ee' suffixes, and toggle CAPS Emphatic words to see stressed acoustic peaks!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_eng_14_1",
                  topic = "Noun vs Verb Stress",
                  examType = "JAMB",
                  year = 2024,
                  question = "Which option shows the correct primary stress for the VERB in: 'Don't ___ our progress'?",
                  options = listOf("OB-ject", "ob-JECT", "OB-ject-ing", "ob-ject-TION"),
                  correctIndex = 1,
                  hint = "Two-syllable verbs carry stress on the SECOND syllable.",
                  stepByStepSolution = "Verb 'ob-JECT' contrasts with noun 'OB-ject'.",
                ),
                PracticeQuestion(
                  id = "ex_eng_14_2",
                  topic = "Suffix Stress Shift",
                  examType = "WAEC",
                  year = 2023,
                  question = "Choose the correct syllable stress pattern for 'university':",
                  options = listOf("U-ni-ver-si-ty", "u-NI-ver-si-ty", "u-ni-VER-si-ty", "u-ni-ver-SI-ty"),
                  correctIndex = 2,
                  hint = "'-ty' counts 3 syllables back from the end!",
                  stepByStepSolution = "Count 3 back (-ty = 5, -si- = 4, -VER- = 3): u-ni-VER-si-ty.",
                ),
                PracticeQuestion(
                  id = "ex_eng_14_3",
                  topic = "Emphatic Stress",
                  examType = "JAMB",
                  year = 2025,
                  question = "Reply: 'My uncle travels to Kano by TRAIN.' Which question does this statement correctly answer?",
                  options = listOf(
                    "Does your cousin travel to Kano by train?",
                    "Does your uncle travel to Lagos by train?",
                    "Does your uncle travel to Kano by bus?",
                    "Did your uncle return from Kano by train?",
                  ),
                  correctIndex = 2,
                  hint = "Keep uncle, travel, Kano unchanged; replace TRAIN with another transport mode!",
                  stepByStepSolution = "'by bus' directly contrasts the CAPS focus 'TRAIN'.",
                ),
                PracticeQuestion(
                  id = "ex_eng_14_4",
                  topic = "Rhyme Matching",
                  examType = "WAEC",
                  year = 2022,
                  question = "Which word forms a true rhyme with 'REIGN'?",
                  options = listOf("foreign", "vein", "sign", "pine"),
                  correctIndex = 1,
                  hint = "Both sound /eɪn/ ('reign' rhymes with 'rain' and 'vein'!).",
                  stepByStepSolution = "'Reign' /reɪn/ and 'vein' /veɪn/ share identical stressed rhyme.",
                ),
                PracticeQuestion(
                  id = "ex_eng_14_5",
                  topic = "French-Suffix Stress",
                  examType = "JAMB",
                  year = 2021,
                  question = "Identify the primary stress in 'referee':",
                  options = listOf("RE-fe-ree", "re-FE-ree", "re-fe-REE", "RE-fer-ee"),
                  correctIndex = 2,
                  hint = "'-ee' pulls primary stress directly onto itself!",
                  stepByStepSolution = "re-fe-REE carries main stress on the final syllable.",
                ),
                PracticeQuestion(
                  id = "ex_eng_14_6",
                  topic = "Suffix '-ic' Rule",
                  examType = "NECO",
                  year = 2024,
                  question = "Where does primary stress fall in 'economic'?",
                  options = listOf("E-co-no-mic", "e-CO-no-mic", "e-co-NO-mic", "e-co-no-MIC"),
                  correctIndex = 2,
                  hint = "Rule: syllable immediately BEFORE '-ic'!",
                  stepByStepSolution = "'-ic' forces stress onto '-NO-': e-co-NO-mic (compare e-CO-no-my!).",
                ),
              ),
          ),

          Lesson(
            id = "eng_15_comprehension_essay",
            moduleId = "eng_mod_5",
            subjectId = "english",
            title = "Comprehension, Summary Paraphrasing & Essay Genres",
            durationMinutes = 22,
            syllabusTag = "JAMB Sec. D • WAEC Paper 2 Comprehension, Summary & Essay",
            learningOutcomes =
              listOf(
                "Identify Author's Tone/Attitude, Main Topic Sentence & Figurative Metaphors without personal outside bias.",
                "Write strict WAEC Summary answers in COMPLETE OWN-WORD SENTENCES (zero wholesale lifting, zero extra examples!).",
                "Apply exact structural formats for Formal Letters (2 addresses + signature!), Informal Letters, Speeches & Articles.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. The 4 Golden WAEC Summary Rules (Stop Losing Half Marks!)",
                  explanation =
                      "Summary is the easiest A1 goldmine if you obey 4 simple rules: (1) Answer in CLEAN COMPLETE SENTENCES (starting with Subject + Verb—never broken phrases!); (2) PARAPHRASE in your own clear words (wholesale copy-pasting from the passage loses HALF marks automatically!); (3) Include ONE main point per numbered sentence if asked for 'three reasons'; (4) CUT OUT all statistics, brackets, analogies, and 'for example...' lists!",
                  keyFormula = "\\text{Summary Formula: Complete Sentence + Own Words + Zero Preamble/Examples}",
                  examTip =
                      "Comprehension Vocab Substitution rule: when replacing an underlined passage word, test YOUR word straight back inside the original sentence—same tense, same singular/plural!",
                ),
                ConceptBlock(
                  heading = "2. Essay & Letter Formats (Formal vs Informal Checklist)",
                  explanation =
                      "Formal Letter (to Editor/Principal/Ministry): TWO addresses (yours top-right, recipient's left), Date, Salutation ('Dear Sir/Madam,'), Clear Centered/Underlined Heading/Title, zero slang/contractions ('cannot', not 'can't'), and Sign-off strictly 'Yours faithfully,' + Signature + Full Name! Informal Letter (friend/parent): ONLY ONE address (yours top-right), 'Dear Ada,' and 'Yours sincerely,' (or 'Your loving friend,') + First Name ONLY—NO signature!",
                  keyFormula = "\\text{Formal: 2 Addresses + Title + 'Yours faithfully,' + Signature}   |   \\text{Informal: 1 Address + 'Yours sincerely,' + First Name}",
                  examTip =
                      "NEVER write an apostrophe inside 'Your's'! It is ALWAYS 'Yours faithfully,' ('Yours' = Capital Y, lowercase f/s, comma after!)!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Fixing a Lifted Phrase into a Full-Mark WAEC Summary Sentence",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement =
                      "Passage: 'Moreover, relentless gully erosion washes away fertile topsoil while farmers cut down protective forests for firewood.' Summarize two causes of low crop yield in ONE sentence each.",
                  steps =
                    listOf(
                      "Step 1 — Avoid lifting 'relentless gully erosion washes away fertile topsoil' verbatim.",
                      "Step 2 — Complete own-word sentence 1: 'Heavy rainwater erosion removes nutrient-rich upper soil.'",
                      "Step 3 — Complete own-word sentence 2: 'Indiscriminate tree felling leaves farmland exposed.'",
                    ),
                  finalAnswer = "Complete, concise own-word sentences with zero copied chunks",
                  examinerTrap = "Writing fragmented bullets like '1. Washing away of fertile topsoil' drops marks for incomplete sentence structure!",
                ),
                WorkedExample(
                  title = "Catching Formal Letter Format Errors",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement = "Spot 3 fatal format errors in closing a Formal Letter to a Newspaper Editor with 'Yours Sincerely, Bisi.'",
                  steps =
                    listOf(
                      "Step 1 — Formal letter to 'Dear Sir,' MUST close with 'Yours faithfully,' (never 'sincerely'!).",
                      "Step 2 — Capital 'S' in Sincerely is wrong (lowercase second word: 'faithfully').",
                      "Step 3 — Missing handwritten Signature above Full Name (first name + surname).",
                    ),
                  finalAnswer = "Yours faithfully, [Signature] Adeyemi Bisi",
                  examinerTrap = "Writing 'Yours faithfully' on informal letters or using 'can't/won't' inside formal applications!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Executive Briefings & Policy Memos",
                  industryDomain = "Public Administration & Management",
                  narrative =
                      "Decision makers rely on crisp 1-page executive summaries distilling 40-page technical reports without losing key causal accuracy.",
                  examConnection = "Pure real-world equivalent of WAEC Summary skill.",
                ),
              ),
            labType = PracticalLabType.COMPREHENSION_ESSAY_LAB,
            labBrief =
              "Inspect side-by-side Lifted vs Full-Mark Summary drafts and verify Formal/Informal Letter & Article anatomy step-by-step!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_eng_15_1",
                  topic = "WAEC Summary Penalty",
                  examType = "WAEC",
                  year = 2024,
                  question = "Under WAEC Summary marking guidelines, copying a whole passage clause word-for-word ('mindless lifting') attracts:",
                  options = listOf("Bonus precision marks", "A deduction/zero credit for mechanical lifting", "Extra spelling marks", "Full marks automatically"),
                  correctIndex = 1,
                  hint = "Summary tests paraphrasing in your own concise sentences.",
                  stepByStepSolution = "WAEC penalizes wholesale lifting and non-sentence phrase answers.",
                ),
                PracticeQuestion(
                  id = "ex_eng_15_2",
                  topic = "Formal Letter Closing",
                  examType = "WAEC",
                  year = 2023,
                  question = "Which subscription properly closes a formal letter addressed to 'Dear Sir,'?",
                  options = listOf("Your's Faithfully, Tunde", "Yours sincerely, Tunde", "Yours faithfully, [Signature] Tunde Okoro", "Ever yours, Tunde Okoro"),
                  correctIndex = 2,
                  hint = "No apostrophe, lowercase 'f', followed by signature and full name.",
                  stepByStepSolution = "'Yours faithfully,' + signature + full name is mandatory for formal letters.",
                ),
                PracticeQuestion(
                  id = "ex_eng_15_3",
                  topic = "Comprehension Synonym Fit",
                  examType = "JAMB",
                  year = 2025,
                  question = "When replacing a passage verb 'EXPLORED' with a synonym, your replacement MUST:",
                  options = listOf("Be an adjective", "Stay in the Past Tense to fit the passage grammar seamlessly", "Be longer than 10 letters", "Start a new paragraph"),
                  correctIndex = 1,
                  hint = "Substitutability rule: exact tense + part of speech.",
                  stepByStepSolution = "Synonyms must slot directly into the original grammatical slot.",
                ),
                PracticeQuestion(
                  id = "ex_eng_15_4",
                  topic = "Informal Letter Format",
                  examType = "WAEC",
                  year = 2022,
                  question = "How many addresses belong at the top of an informal letter to your cousin?",
                  options = listOf("Two addresses", "Zero addresses", "One address (writer's address only)", "Three addresses"),
                  correctIndex = 2,
                  hint = "Only formal letters carry the recipient's official address.",
                  stepByStepSolution = "Informal letters contain solely the writer's address and date.",
                ),
                PracticeQuestion(
                  id = "ex_eng_15_5",
                  topic = "Reading Main Idea",
                  examType = "JAMB",
                  year = 2021,
                  question = "A comprehension question asking for 'the author's attitude' requires evaluating:",
                  options = listOf("Your own personal politics", "Word choice, tone & evaluative adjectives used in the text", "Only the very last word", "The number of commas"),
                  correctIndex = 1,
                  hint = "Judge strictly from textual evidence (e.g., skeptical, optimistic, cautionary).",
                  stepByStepSolution = "Tone is inferred strictly from the writer's diction and stance.",
                ),
                PracticeQuestion(
                  id = "ex_eng_15_6",
                  topic = "Debate / Speech Opening",
                  examType = "NECO",
                  year = 2024,
                  question = "Which opening feature is compulsory at the start of an inter-school Debate Speech?",
                  options = listOf("Two addresses", "Vocatives acknowledging Chairman, Judges, Timekeeper & Audience", "Bibliography", "Yours faithfully"),
                  correctIndex = 1,
                  hint = "Live spoken greeting to the panel and floor.",
                  stepByStepSolution = "Debates and formal speeches open with structured audience vocatives.",
                ),
              ),
          ),
        ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion("pq_eng_01", "Uncountable Nouns", "JAMB", 2025, "We must purchase new laboratory ____ before WAEC practicals start.", listOf("apparatus", "apparatuses", "an apparatus", "equipments"), 0, "'Apparatus' and 'equipment' serve as mass nouns without extra -es.", "Standard academic plural remains 'apparatus' (or 'pieces of apparatus')."),
      PracticeQuestion("pq_eng_02", "Grammatical Function", "WAEC", 2024, "In 'The girl WHO WON THE ESSAY PRIZE beamed joyfully', 'WHO WON THE ESSAY PRIZE' is:", listOf("Adjectival clause qualifying 'girl'", "Noun clause object", "Adverbial clause of time", "Noun phrase"), 0, "Sits immediately after 'girl' describing which girl.", "Relative/Adjectival clause qualifying 'the girl'."),
      PracticeQuestion("pq_eng_03", "Dangling Modifier", "JAMB", 2024, "Spot the error-free sentence:", listOf("Entering the dark room, the candle blew out.", "Entering the dark room, she tripped over a stool.", "To win the medal, hard training was done.", "Tired after work, the bed looked soft."), 1, "Place the actual human doer right after the comma.", "'she' entered the dark room and tripped."),
      PracticeQuestion("pq_eng_04", "Conditionals", "WAEC", 2023, "Had the pilot checked the fuel gauge earlier, the emergency landing ____ necessary.", listOf("would not have been", "will not be", "would not be", "was not"), 0, "Type 3 inverted past conditional.", "Pairs 'Had + V₃' with 'would not have been'."),
      PracticeQuestion("pq_eng_05", "High Time Rule", "NECO", 2023, "It is about time the landlord ____ that leaking roof.", listOf("repaired", "repairs", "repair", "has repaired"), 0, "'It is about/high time + Subj' takes Simple Past V₂.", "Subjunctive overdue past requires 'repaired'."),
      PracticeQuestion("pq_eng_06", "Concord", "WAEC", 2024, "Neither the captain nor his teammates ____ happy with the referee's decision.", listOf("were", "was", "is", "has been"), 0, "Closer subject 'teammates' is plural.", "Proximity rule selects plural 'were'."),
      PracticeQuestion("pq_eng_07", "Concord Trap", "JAMB", 2025, "A large number of candidates ____ already printed their exam slips.", listOf("have", "has", "is", "was"), 0, "'A number of' = several → plural; 'The number of' → singular.", "'A number of candidates' takes plural 'have'."),
      PracticeQuestion("pq_eng_08", "Question Tags", "WAEC", 2022, "Scarcely anyone survived the landslide, ____?", listOf("did they", "didn't they", "did he", "was it"), 0, "'Scarcely' is negative → positive tag; indefinite 'anyone' → 'they'.", "'did they?'."),
      PracticeQuestion("pq_eng_09", "Synonyms", "JAMB", 2024, "NEAREST in meaning: 'The judge commended the witness for his VERACITY.'", listOf("truthfulness", "nervousness", "eloquence", "hostility"), 0, "Root 'ver-' (verify, verity) = truth.", "Veracity means habitual truthfulness."),
      PracticeQuestion("pq_eng_10", "Antonyms", "NECO", 2023, "OPPOSITE in meaning: 'The governor gave an IMPROMPTU speech at the stadium.'", listOf("prepared / rehearsed", "short", "inspiring", "spontaneous"), 0, "Impromptu = unscripted on the spot.", "Opposite is rehearsed / prepared."),
      PracticeQuestion("pq_eng_11", "Idioms", "JAMB", 2022, "'Our chemistry teacher always calls a SPADE A SPADE.' This means she:", listOf("Speaks bluntly and honestly without sugarcoating", "Loves gardening", "Uses big Latin words", "Changes topics quickly"), 0, "Direct frank honesty.", "Calls things plainly as they are."),
      PracticeQuestion("pq_eng_12", "Phrasal Verbs", "WAEC", 2025, "The fire brigade took three hours to ____ the blaze at the market.", listOf("put out", "put off", "put up", "put down"), 0, "Extinguish a fire.", "'Put out' means extinguish."),
      PracticeQuestion("pq_eng_13", "Prepositions", "JAMB", 2021, "The accused was eventually acquitted ____ all charges of conspiracy.", listOf("of", "from", "with", "about"), 0, "Fixed legal collocation: acquit OF.", "One is charged WITH, convicted OF, and acquitted OF."),
      PracticeQuestion("pq_eng_14", "Confusables", "WAEC", 2024, "Wait until the bus comes to a ____ halt before stepping off.", listOf("stationary", "stationery", "steady", "statuary"), 0, "Not moving = stationARy.", "Spelling 'stationary' denotes immobile."),
      PracticeQuestion("pq_eng_15", "Register", "NECO", 2023, "A newspaper's official statement expressing its own stand on national policy is an:", listOf("editorial", "obituary", "epitaph", "appendix"), 0, "Journalism opinion column by editors.", "Editorial."),
      PracticeQuestion("pq_eng_16", "Vowels /iː/ vs /ɪ/", "WAEC", 2021, "Which word shares the vowel sound in 'qu<u>ay</u>' (/iː/)?", listOf("way", "say", "key", "day"), 2, "'Quay' (wharf) is pronounced identically to 'key'!", "Key /kiː/ matches quay /kiː/."),
      PracticeQuestion("pq_eng_17", "Diphthongs", "JAMB", 2024, "Which word contains the diphthong /aɪ/ as in 'h<u>ei</u>ght'?", listOf("weight", "aisle", "eight", "obey"), 1, "'Aisle' sounds like 'I'll' (/aɪl/); others use /eɪ/.", "Aisle /aɪl/."),
      PracticeQuestion("pq_eng_18", "Vowels /ʌ/", "WAEC", 2022, "Identify the word containing central short /ʌ/ as in 'c<u>u</u>p':", listOf("put", "blood", "food", "foot"), 1, "'Blood' & 'flood' pronounce /ʌ/ despite 'oo' spelling!", "Blood /blʌd/."),
      PracticeQuestion("pq_eng_19", "Consonant /ʃ/", "JAMB", 2025, "Which word contains the consonant sound /ʃ/ as in '<u>sh</u>oe'?", listOf("treasure", "champagne", "vision", "church"), 1, "French loan 'ch' sounds like 'sh'.", "Champagne /ʃæmˈpeɪn/."),
      PracticeQuestion("pq_eng_20", "Consonant '-s' Plural", "NECO", 2023, "How is the plural '-s' pronounced in 'cap<u>s</u>'?", listOf("/s/", "/z/", "/ɪz/", "silent"), 0, "After voiceless /p/, '-s' stays voiceless /s/.", "/s/."),
      PracticeQuestion("pq_eng_21", "Word Stress", "JAMB", 2022, "Which word is stressed on the THIRD syllable?", listOf("DEmocracy", "photoGRAphic", "uniVERsity", "Both photoGRAphic and uniVERsity"), 3, "pho-to-GRA-phic (3rd) and u-ni-VER-si-ty (3rd).", "Both follow -ic (2nd-last) and -ty (3rd-last) rules onto syllable 3."),
      PracticeQuestion("pq_eng_22", "Emphatic Stress", "WAEC", 2024, "Answer: 'Ngozi visited her AUNT in Abuja.' Pick the matching question.", listOf("Did Ngozi visit her uncle in Abuja?", "Did Kemi visit her aunt in Abuja?", "Did Ngozi visit her aunt in Kano?", "Did Ngozi phone her aunt in Abuja?"), 0, "Contradict ONLY 'AUNT' (→ uncle).", "Everything else stays identical; 'uncle' opposes 'AUNT'."),
      PracticeQuestion("pq_eng_23", "Rhymes", "JAMB", 2021, "Which word rhymes cleanly with 'CATCH'?", listOf("watch", "hatch", "wash", "much"), 1, "Same /ætʃ/ rime.", "Catch and hatch rhyme (/ætʃ/)."),
      PracticeQuestion("pq_eng_24", "Comprehension Summary", "NECO", 2025, "Which closing punctuation & capitalization is right for a Formal Letter?", listOf("Yours faithfully,", "Your's Faithfully,", "Yours Sincerely,", "Faithfully your's,"), 0, "No apostrophe; lowercase second word; trailing comma.", "Yours faithfully,"),
    )
}
