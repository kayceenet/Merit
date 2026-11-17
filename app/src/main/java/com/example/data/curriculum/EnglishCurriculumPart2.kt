package com.example.data.curriculum

object EnglishCurriculumPart2 {

  val modules: List<CourseModule> =
    listOf(
      CourseModule(
        id = "eng_mod_3",
        subjectId = "english",
        indexLabel = "Module 03",
        title = "Oral English: Phonetics, Word Stress & Emphatic Stress",
        summary = "Master JAMB/WAEC vowel & consonant contrasts, suffix stress attraction, noun-verb stress shifts, and contrastive emphatic answers.",
        lessons =
          listOf(
            Lesson(
              id = "eng_07_vowels_consonants",
              moduleId = "eng_mod_3",
              subjectId = "english",
              title = "Vowels, Diphthongs, Consonants & Silent Letters",
              durationMinutes = 20,
              syllabusTag = "JAMB Oral (15 Qs) • WAEC Paper 3 Oral",
              learningOutcomes =
                listOf(
                  "Contrast short vs long pure vowels (/ɪ/ vs /iː/, /ʊ/ vs /uː/, /æ/ vs /ɑː/) and central schwa/ʌ distinctions.",
                  "Identify consonant pairs (voiceless /θ/ 'thin' vs voiced /ð/ 'this', /ʃ/ vs /tʃ/, /s/ vs /z/) and silent letters.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1 (From Scratch): Never Trust English Spelling — Sound It Out!",
                    explanation =
                      "English spelling fools people: 'quay' rhymes with KEY /iː/, 'sword' drops 'w' (/sɔːd/), 'comb/bomb/debt' drop 'b', 'plumber' drops 'b', 'castle/listen' drop 't'! Compare short vs long vowels carefully: sit /ɪ/ vs seat /iː/; pull /ʊ/ vs pool /uː/; cup /ʌ/ ('flood', 'blood', 'love', 'rough') vs cap /æ/ vs father /ɑː/!",
                    keyFormula = "/\\Lambda/: \\text{cup, blood, flood, love}   |   /i:/: \\text{quay, people, key, police}   |   /u:/: \\text{shoe, soup, tomb}",
                    examTip =
                      "Past '-ed' suffix shifts sound by the LAST consonant before -ed: voiceless (/p,k,f,s,ʃ,tʃ/) -> /t/ ('passed, laughed, watched'); /t,d/ -> /ɪd/ ('wanted'); everything else -> /d/!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: High-Trap Consonants (/θ, ð, ʃ, tʃ, dʒ, ŋ/)",
                    explanation =
                      "Notice 'ch' is NOT always /tʃ/ (church)! In Greek loanwords ('chemistry, chorus, stomach, charisma') ch = /k/; in French loans ('machine, chalet, chef, champagne') ch = /ʃ/! Separate voiceless /θ/ ('think, bath, three') from voiced /ð/ ('this, bathe, breathe, brother'). And plural '-s' after /p,t,k,f/ sounds /s/ ('books'), after vowels/voiced consonants sounds /z/ ('bags, boys')!",
                    keyFormula = "\\text{ch}: /t\\int/\\ (\\text{church})\\ \\mid\\ /k/\\ (\\text{chorus})\\ \\mid\\ /\\int/\\ (\\text{machine, chef})",
                    examTip =
                      "Notice NOUN 'bath' ends in /θ/, while VERB 'bathe' ends in voiced /ð/ — exact same for 'breath' (/θ/) vs 'breathe' (/ð/)!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding Matching Vowel Sound Across Different Spellings",
                    examSource = "JAMB UTME 2024 • Oral English Q49",
                    problemStatement =
                      "Choose the word containing the SAME vowel sound as the 'oo' in 'FLOOD': (A) food (B) wood (C) blood (D) boot.",
                    steps =
                      listOf(
                        "Step 1 — Say 'flood': short central /ʌ/ (just like 'cup' and 'cut'!).",
                        "Step 2 — Check options: food /uː/, wood /ʊ/, blood /ʌ/, boot /uː/.",
                        "Step 3 — 'blood' matches /ʌ/ identically!",
                      ),
                    finalAnswer = "(C) blood (/ʌ/)",
                    examinerTrap = "Don't judge by double 'oo' spelling — 'flood/blood' = /ʌ/, 'book/wood' = /ʊ/, 'food/boot' = /uː/!",
                  ),
                  WorkedExample(
                    title = "Silent Consonants & Consonant Clusters",
                    examSource = "WAEC SSCE 2023 • Paper 3 Oral",
                    problemStatement =
                      "Which word has a silent consonant matching 'DEBT': (A) habit (B) subtle (C) amber (D) ribbon?",
                    steps =
                      listOf(
                        "Step 1 — In 'debt', the letter 'b' is silent (/det/).",
                        "Step 2 — In 'subtle', the 'b' is ALSO silent (/sʌt.əl/), whereas 'b' is pronounced in habit, amber, ribbon.",
                      ),
                    finalAnswer = "(B) subtle",
                    examinerTrap = "Check silent letters: 'subtle, doubt, plumber, tomb, lamb, womb' ALL drop 'b'!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Radio Broadcaster Diction & Global Clarity",
                    industryDomain = "Broadcast Media & Voice Coaching",
                    narrative =
                      "Broadcast journalists distinguish 'seat/sit', 'cheap/sheep', and 'bathe/bath' cleanly so international listeners understand news reports effortlessly.",
                    examConnection = "Builds phoneme discrimination directly for JAMB's 15 Oral items.",
                  ),
                  RealLifeScenario(
                    title = "International Phonetic Alphabet (IPA) Dictionary Decoding",
                    industryDomain = "Lexicography & Speech Tech",
                    narrative =
                      "Dictionaries print /kɪ/ for 'quay' and /ˈkwaɪər/ for 'choir' so learners pronounce irregular English loanwords accurately on first sight.",
                    examConnection = "Reinforces decoding silent letters and loanword spellings.",
                  ),
                ),
              labType = PracticalLabType.ORAL_ENGLISH_STRESS,
              labBrief =
                "Explore syllable beats, vowel focus, and suffix stress rules in the Oral English Studio below!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_07_1",
                    topic = "Vowel Sounds",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which word has the SAME vowel sound as the 'ea' in 'GREAT' (/eɪ/)?",
                    options = listOf("Beat", "Bread", "Steak", "Heart"),
                    correctIndex = 2,
                    hint = "Wait, late, break, steak share the /eɪ/ diphthong.",
                    stepByStepSolution = "'Great', 'break', and 'steak' share /eɪ/ (unlike beat /iː/, bread /e/, heart /ɑː/).",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_07_2",
                    topic = "Consonant Contrast",
                    examType = "WAEC",
                    year = 2023,
                    question = "Pick the word with the SAME initial consonant sound as 'CHALET':",
                    options = listOf("Church", "Chorus", "Chef", "Chair"),
                    correctIndex = 2,
                    hint = "Both 'chalet' and one other option are French loanwords starting with /ʃ/.",
                    stepByStepSolution = "'Chalet' and 'chef' start with /ʃ/ (same as 'shoe'), whereas church/chair use /tʃ/ and chorus uses /k/.",
                    waecMarkingScheme = "A1: Chef (/ʃ/).",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_07_3",
                    topic = "Past Tense '-ed'",
                    examType = "JAMB",
                    year = 2022,
                    question = "In which word does the final '-ed' ending sound as /t/?",
                    options = listOf("Played", "Wanted", "Laughed", "Begged"),
                    correctIndex = 2,
                    hint = "Look for a voiceless final consonant sound before '-ed' (/f/).",
                    stepByStepSolution = "'Laugh' ends in voiceless /f/, so 'laughed' is pronounced /lɑːft/.",
                  ),
                ),
            ),
            Lesson(
              id = "eng_08_word_stress_patterns",
              moduleId = "eng_mod_3",
              subjectId = "english",
              title = "Word Stress Shifts, Suffix Attractors & Rhymes",
              durationMinutes = 20,
              syllabusTag = "JAMB Stress (10 Qs) • WAEC Paper 3 Sec. 4–5",
              learningOutcomes =
                listOf(
                  "Place primary stress on the penultimate syllable before -tion, -sion, -ic, -ical, -ity, -graphy, and -logy.",
                  "Distinguish 2-syllable Noun/Adjective (1st syllable: CON-duct, RE-cord) vs Verb (2nd syllable: con-DUCT, re-CORD) stress.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Suffix-Driven Stress Rules (Instant Accuracy!)",
                    explanation =
                      "Rule 1 (-TION, -SION, -IC, -ICAL): Primary stress lands IMMEDIATELY BEFORE the suffix! E.g., e-du-CA-tion, pho-to-GRA-phic, po-LI-ti-cal (count 1 back from -ic/-ical? Stop right before -ic -> po-LI-ti-cal!). Rule 2 (-ITY, -GRAPHY, -LOGY, -METRY): Stress lands TWO syllables from the end (just before the ending): u-ni-VER-si-ty, bi-O-lo-gy, pho-TO-gra-phy! Rule 3 (-EE, -EER, -ETTE, -ESE, -OO): Stress lands ON the suffix itself: re-fu-GEE, en-gi-NEER, ca-ssETTE!",
                    keyFormula = "-tion/-ic \\Rightarrow \\text{1 before}   |   -ity/-logy/-graphy \\Rightarrow \\text{right before suffix}   |   -ee/-eer \\Rightarrow \\text{ON suffix}",
                    examTip =
                      "Watch how stress JUMPS along word families: PHO-to-graph (1st) -> pho-TO-gra-phy (2nd) -> pho-to-GRA-phic (3rd)!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Two-Syllable Noun/Adjective vs Verb Stress",
                    explanation =
                      "Dozens of two-syllable English words act as BOTH Noun and Verb! Stress the FIRST syllable when it acts as a NOUN or ADJECTIVE ('CON-tract', 'IM-port', 'PRO-ject', 'PRE-sent', 'RE-bel'); stress the SECOND syllable when it acts as a VERB ('con-TRACT', 'im-PORT', 'pro-JECT', 'pre-SENT', 're-BEL')!",
                    keyFormula = "\\text{Noun/Adj = 1st Syllable (RE-cord)}   |   \\text{Verb = 2nd Syllable (re-CORD)}",
                    examTip =
                      "In sentence stress-shift questions, read the word IN CONTEXT first to check whether it is a Noun or a Verb!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Primary Stress Identification in Multi-Syllable Words",
                    examSource = "JAMB UTME 2024 • Oral English Q53",
                    problemStatement =
                      "Select the option carrying the correct primary stress (CAPS) for 'DEMARCATION' and 'PHOTOGRAPHIC'.",
                    steps =
                      listOf(
                        "Step 1 — 'demarcation' ends in -tion -> place stress immediately before -tion: de-mar-CA-tion.",
                        "Step 2 — 'photographic' ends in -ic -> place stress immediately before -ic: pho-to-GRA-phic.",
                      ),
                    finalAnswer = "de-mar-CA-tion  |  pho-to-GRA-phic",
                    examinerTrap = "Don't stress the noun root 'PHO-to' once '-ic' attaches — suffixes pull stress rightward!",
                  ),
                  WorkedExample(
                    title = "Odd-Stress-Pattern Detection",
                    examSource = "WAEC SSCE 2023 • Paper 3 Q38",
                    problemStatement =
                      "Pick the word with a DIFFERENT stress pattern from the others: (A) PHO-to-graph (B) CON-duct (noun) (C) im-PORT (verb) (D) SUB-ject (noun).",
                    steps =
                      listOf(
                        "Step 1 — PHO-to-graph, CON-duct (noun), and SUB-ject (noun) ALL carry 1st-syllable stress.",
                        "Step 2 — 'im-PORT' (verb) shifts stress onto the 2nd syllable.",
                      ),
                    finalAnswer = "(C) im-PORT (verb)",
                    examinerTrap = "Always check grammar tags (noun vs verb)!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Voice Assistants & Speech Recognition Parsing",
                    industryDomain = "Speech AI & Telecommunications",
                    narrative =
                      "Automated customer-care systems separate 'CON-tent' (subject matter, noun) from 'con-TENT' (satisfied, adjective) cleanly by measuring syllable pitch/amplitude peaks.",
                    examConnection = "Connects phonetic word stress directly to grammatical meaning.",
                  ),
                  RealLifeScenario(
                    title = "International Academic Presentations",
                    industryDomain = "Conference Speaking",
                    narrative =
                      "Shifting stress smoothly across 'e-CON-o-my -> e-co-NO-mic' ensures effortless comprehension at global conferences.",
                    examConnection = "Reinforces suffix stress attraction rules.",
                  ),
                ),
              labType = PracticalLabType.ORAL_ENGLISH_STRESS,
              labBrief =
                "Test suffix stress shifts (-tion, -ic, -ity, -ee) and Noun vs Verb stress flips interactively in the Oral Stress Studio below!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_08_1",
                    topic = "Word Stress",
                    examType = "JAMB",
                    year = 2025,
                    question = "Choose the option with the correct primary stress for 'DEMONSTRATION':",
                    options = listOf("DE-mon-stra-tion", "de-MON-stra-tion", "de-mon-STRA-tion", "de-mon-stra-TION"),
                    correctIndex = 2,
                    hint = "Suffix '-tion' pulls stress to the syllable directly before it.",
                    stepByStepSolution = "de-mon-STRA-tion places stress on STRA, right before '-tion'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_08_2",
                    topic = "Stress Shift",
                    examType = "WAEC",
                    year = 2023,
                    question = "Identify the stressed syllable in 'EN-gi-neer' / 'en-gi-NEER':",
                    options = listOf("EN-gi-neer", "en-GI-neer", "en-gi-NEER", "Equal stress"),
                    correctIndex = 2,
                    hint = "Words ending in '-eer', '-ee', '-ette' stress the suffix itself!",
                    stepByStepSolution = "Suffix '-eer' attracts primary stress onto the final syllable: en-gi-NEER.",
                    waecMarkingScheme = "A1: en-gi-NEER.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_08_3",
                    topic = "Noun/Verb Stress",
                    examType = "JAMB",
                    year = 2024,
                    question = "In 'The minister plans to EXPAND and EXPORT cocoa', how is 'export' stressed here?",
                    options = listOf("EX-port (1st syllable)", "ex-PORT (2nd syllable)", "Unstressed", "Both syllables equally"),
                    correctIndex = 1,
                    hint = "Follows 'to' as an action verb.",
                    stepByStepSolution = "'Export' acts as a verb here, taking 2nd-syllable stress: ex-PORT.",
                  ),
                ),
            ),
            Lesson(
              id = "eng_09_emphatic_stress",
              moduleId = "eng_mod_3",
              subjectId = "english",
              title = "Emphatic / Contrastive Stress Mastery (Guaranteed Marks!)",
              durationMinutes = 15,
              syllabusTag = "JAMB Emphatic Stress • WAEC Paper 3 Sec. 6",
              learningOutcomes =
                listOf(
                  "Match a capitalized emphatic word to the ONLY question that contradicts that capitalized word while keeping everything else identical.",
                  "Eliminate distractors that contradict un-stressed words or simply repeat the exact capitalized word.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: The Golden Contradiction Formula",
                    explanation =
                      "Emphatic stress questions highlight ONE word in ALL CAPS: e.g., 'Musa bought a RED bicycle yesterday.' The speaker is correcting someone who got ONLY that CAPS word wrong! Therefore, the correct question option MUST: (1) Keep Musa, bought, bicycle, and yesterday UNCHANGED, AND (2) Replace 'RED' with a DIFFERENT color (e.g., 'Did Musa buy a BLUE bicycle yesterday?').",
                    keyFormula = "\\text{Correct Question keeps ALL non-CAPS words} + \\text{CHANGES ONLY the CAPS word!}",
                    examTip =
                      "TRAP ALERT: Never pick an option that repeats the CAPS word itself, or uses a WH-question that keeps everything unless it literally contrasts!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Handling Verbs, Auxiliary & Function Words in CAPS",
                    explanation =
                      "When the CAPS word is a VERB ('Ada DROVE to Abuja'): contrast the action ('Did Ada FLY to Abuja?'). When the CAPS word is a POSSESSIVE ('my' -> 'your/his'): contrast ownership ('Did he steal YOUR wallet?' -> 'No, he stole MY wallet'). When the CAPS word is AUXILIARY ('CAN/DID'): test negation or modal contrast!",
                    keyFormula = "\\text{Verb CAPS} \\Rightarrow \\text{Different Verb}   |   \\text{Time CAPS} \\Rightarrow \\text{Different Time}",
                    examTip =
                      "If you mentally answer 'No, ...' + the prompt sentence to the option and it makes 100% logical sense, you found the exact winner!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Emphatic Stress Test with 'No, ...'",
                    examSource = "JAMB UTME 2024 • Use of English Q57",
                    problemStatement =
                      "Prompt: 'The principal REJECTED our excursion proposal.' Which question elicits this exact emphatic response?\n(A) Did the vice-principal reject our proposal?\n(B) Did the principal approve our excursion proposal?\n(C) Did the principal reject our sports proposal?\n(D) Did the principal reject your excursion proposal?",
                    steps =
                      listOf(
                        "Step 1 — CAPS word is the verb 'REJECTED'.",
                        "Step 2 — Check which option keeps 'The principal', 'our excursion proposal' identical and swaps ONLY 'rejected' -> (B) swaps 'rejected' with 'approve'!",
                        "Step 3 — Verify: 'Did the principal approve our excursion proposal?' -> 'No, the principal REJECTED our excursion proposal.' Exact match!",
                      ),
                    finalAnswer = "(B) Did the principal approve our excursion proposal?",
                    examinerTrap = "(A) changes 'principal', (C) changes 'excursion', (D) changes 'our' — none contrast 'REJECTED'!",
                  ),
                  WorkedExample(
                    title = "Adverbial Time Contrast",
                    examSource = "WAEC SSCE 2022 • Paper 3 Q52",
                    problemStatement =
                      "Prompt: 'Chinedu travels to Kano NEXT week.' Choose the matching question.",
                    steps =
                      listOf(
                        "Step 1 — CAPS word is 'NEXT' (time specifier).",
                        "Step 2 — Pick 'Does Chinedu travel to Kano THIS week?' (or 'Did Chinedu travel LAST week?').",
                      ),
                    finalAnswer = "Question changing ONLY 'NEXT' to THIS/LAST",
                    examinerTrap = "Don't change Kano -> Lagos or travels -> flies!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Courtroom Cross-Examination & Debates",
                    industryDomain = "Advocacy & Debate",
                    narrative =
                      "Lawyers clarify timelines sharply with vocal focus ('He signed on MONDAY, not Friday'), preventing factual ambiguity under cross-examination.",
                    examConnection = "Shows practical communicative purpose of English emphatic stress.",
                  ),
                  RealLifeScenario(
                    title = "Customer Support Verification",
                    industryDomain = "Service Operations",
                    narrative =
                      "Clearing up misheard account details relies on stressing solely the corrected digit or month.",
                    examConnection = "Reinforces single-word contrastive focus.",
                  ),
                ),
              labType = PracticalLabType.ORAL_ENGLISH_STRESS,
              labBrief =
                "Test live contrastive Emphatic Stress answers interactively in the Oral English Studio below!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_09_1",
                    topic = "Emphatic Stress",
                    examType = "JAMB",
                    year = 2025,
                    question = "Given: 'Amina baked FIVE meat pies for the party.' Which question aligns with the emphatic stress?",
                    options =
                      listOf(
                        "Did Musa bake five meat pies for the party?",
                        "Did Amina fry five meat pies for the party?",
                        "Did Amina bake ten meat pies for the party?",
                        "Did Amina bake five fish pies for the party?",
                      ),
                    correctIndex = 2,
                    hint = "Contrast ONLY the capitalized number FIVE.",
                    stepByStepSolution = "Option C keeps Amina, baked, meat pies, party constant and contrasts FIVE vs ten.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_09_2",
                    topic = "Emphatic Stress",
                    examType = "WAEC",
                    year = 2023,
                    question = "Given: 'The security guard LOCKED the main gate.' Which question matches best?",
                    options =
                      listOf(
                        "Did the gardener lock the main gate?",
                        "Did the security guard leave the main gate unlocked?",
                        "Did the security guard lock the back gate?",
                        "Did the security guard repair the main gate?",
                      ),
                    correctIndex = 1,
                    hint = "Wait: look carefully between 'repair' and 'leave unlocked' — both contrast LOCKED, check exact wording!",
                    stepByStepSolution = "Option B directly contrasts LOCKED vs unlocked while preserving guard and main gate.",
                    waecMarkingScheme = "A1: Option contradicting only LOCKED.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_09_3",
                    topic = "Emphatic Stress",
                    examType = "JAMB",
                    year = 2024,
                    question = "Given: 'Our chemistry teacher comes from ENUGU.' Choose the right question:",
                    options =
                      listOf(
                        "Does our physics teacher come from Enugu?",
                        "Does your chemistry teacher come from Enugu?",
                        "Does our chemistry teacher live in Enugu?",
                        "Does our chemistry teacher come from Owerri?",
                      ),
                    correctIndex = 3,
                    hint = "Keep 'Our chemistry teacher comes from...' intact and change ONLY the city.",
                    stepByStepSolution = "Option D keeps every other phrase identical and swaps ENUGU for Owerri.",
                  ),
                ),
            ),
          ),
      ),
      CourseModule(
        id = "eng_mod_4",
        subjectId = "english",
        indexLabel = "Module 04",
        title = "Comprehension, Summary Precision & Essay Mechanics",
        summary = "Master JAMB/WAEC reading inference, one-sentence summary distillation, and high-scoring WAEC essay structure.",
        lessons =
          listOf(
            Lesson(
              id = "eng_10_comprehension_summary",
              moduleId = "eng_mod_4",
              subjectId = "english",
              title = "Comprehension Inference, Figurative Language & Summary Rules",
              durationMinutes = 20,
              syllabusTag = "JAMB Passages • WAEC Paper 2 Sec. B & C",
              learningOutcomes =
                listOf(
                  "Identify author's tone, figures of speech (simile, metaphor, irony, personification), and implied main ideas.",
                  "Write crisp WAEC Summary answers in single clean sentences without preamble, lifting, or exceeding requested count.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: WAEC Summary Golden Scoring Checklist",
                    explanation =
                      "WAEC Paper 2 Summary deducts ½ or ALL marks for careless formatting errors! Follow the 5 Summary Commandments: (1) Write COMPLETE SENTENCES (never bullet fragments without finite verbs!). (2) NEVER copy long sentences word-for-word ('mindless lifting' scores ZERO!). (3) Start straight with the point — omit wasteful preambles like 'One of the causes mentioned in the passage is that...'. (4) Put ONLY ONE main idea per numbered sentence! (5) Stay strictly inside what the passage actually states!",
                    keyFormula = "\\text{1 Complete Sentence = 1 Distinct Idea}   |   \\text{No Lifting, No Preamble, No Outside Facts}",
                    examTip =
                      "Use concise topic openers: 'The government should... (a) build rural clinics. (b) train more nurses.' — each forms a complete grammatical sentence!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Author Attitude, Figures of Speech & Contextual Substitution",
                    explanation =
                      "Comprehension passages test: (a) Tone/Attitude (satirical, optimistic, critical, objective — check adjectives!); (b) Figures of Speech (Metaphor calls X directly Y; Personification gives human actions to non-humans; Irony states the opposite of reality); (c) Word/Phrase replacement: substitute your chosen answer BACK into the passage sentence — it MUST fit both meaning AND tense/part of speech!",
                    keyFormula = "\\text{Tone = Adjectives/Verbs}   |   \\text{Replacement Check: Fit Meaning + Exact Grammar}",
                    examTip =
                      "In JAMB comprehension, never rely on real-world knowledge if it contradicts the passage — ONLY what is supported in the text!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Converting Verbose Paragraph to WAEC Summary Sentence",
                    examSource = "WAEC SSCE 2023 • Paper 2 Summary Technique",
                    problemStatement =
                      "Text: 'Because teenage students stay scrolling social media feeds past midnight, they arrive exhausted at morning classes and lose focus during science lectures.' Question: State ONE negative effect of late-night phone use in one sentence.",
                    steps =
                      listOf(
                        "Step 1 — Strip preamble ('According to the text...') and paraphrase cleanly without lifting.",
                        "Step 2 — Draft complete sentence: 'Late-night phone browsing leaves students fatigued and unable to concentrate in class.'",
                      ),
                    finalAnswer = "Late-night phone use causes daytime fatigue and poor classroom concentration.",
                    examinerTrap = "Writing only 'Fatigue and poor concentration' (no finite verb!) fails WAEC sentence penalty!",
                  ),
                  WorkedExample(
                    title = "Spotting Figures of Speech in Passages",
                    examSource = "JAMB UTME 2022 • Comprehension Q5",
                    problemStatement =
                      "Identify the figure of speech in: 'The harsh harmattan wind whispered angrily through the cracked mud walls.'",
                    steps =
                      listOf(
                        "Step 1 — Non-human subject ('harmattan wind') performs human acts ('whispered angrily').",
                        "Step 2 — Attributing human emotions/actions to inanimate nature = Personification.",
                      ),
                    finalAnswer = "Personification",
                    examinerTrap = "Metaphor equates two unlike things; Personification specifically gives HUMAN actions/feelings to non-humans!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Executive Briefs & Boardroom Summarization",
                    industryDomain = "Management Consulting",
                    narrative =
                      "Analysts distill 40-page field reports into 5 crystal-clear actionable sentences for busy hospital or ministry directors — the exact WAEC Summary skill.",
                    examConnection = "Builds concise paraphrasing without distortion.",
                  ),
                  RealLifeScenario(
                    title = "Literary & Op-Ed Media Literacy",
                    industryDomain = "Civic Journalism",
                    narrative =
                      "Spotting irony vs neutral reporting helps readers tell satirical columns apart from literal policy statements.",
                    examConnection = "Improves accuracy on JAMB author-attitude questions.",
                  ),
                ),
              labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
              labBrief =
                    "Verify complete-sentence clause structure in the Syntax Studio so every WAEC Summary sentence earns full marks!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_10_1",
                    topic = "Summary Rules",
                    examType = "WAEC",
                    year = 2024,
                    question = "Which habit leads to mark deduction in WAEC English Summary writing?",
                    options =
                      listOf(
                        "Paraphrasing key points in your own words",
                        "Writing complete grammatical sentences",
                        "Copying full passage sentences verbatim ('lifting')",
                        "Stating one idea per numbered sentence",
                      ),
                    correctIndex = 2,
                    hint = "Examiners test whether you understood the text well enough to distill it concisely.",
                    stepByStepSolution = "Mindless word-for-word copying ('lifting') shows failure to summarize and draws heavy WAEC penalties.",
                    waecMarkingScheme = "A1: Mindless lifting / incomplete phrases.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_10_2",
                    topic = "Figures of Speech",
                    examType = "JAMB",
                    year = 2023,
                    question = "What figure of speech is illustrated in: 'Examination success is a steep ladder climbed one rung at a time'?",
                    options = listOf("Simile", "Metaphor", "Oxymoron", "Euphemism"),
                    correctIndex = 1,
                    hint = "Direct comparison WITHOUT 'like' or 'as'.",
                    stepByStepSolution = "Equating examination success directly to a steep ladder (no 'like'/'as') is a Metaphor.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_10_3",
                    topic = "Comprehension Tone",
                    examType = "JAMB",
                    year = 2022,
                    question = "When a writer exposes societal corruption through sharp humor and exaggeration, the tone is:",
                    options = listOf("Indifferent", "Satirical", "Eulogistic", "Nostalgic"),
                    correctIndex = 1,
                    hint = "Critique wrapped in wit or ridicule.",
                    stepByStepSolution = "Satire uses wit, irony, and exaggeration to criticize follies and push reform.",
                  ),
                ),
            ),
            Lesson(
              id = "eng_11_essay_mechanics",
              moduleId = "eng_mod_4",
              subjectId = "english",
              title = "WAEC Essay Formats, Letter Writing & Mechanics (COEM)",
              durationMinutes = 18,
              syllabusTag = "WAEC Paper 2 Sec. A • Essay & Letter Formats",
              learningOutcomes =
                listOf(
                  "Apply the 4 WAEC scoring pillars: Content (10), Organization (10), Expression (20), Mechanical Accuracy (10).",
                  "Format Formal Letters (two addresses, formal salutation, title, 'Yours faithfully' + signature + full name) cleanly without errors.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: WAEC's 50-Mark COEM Rubric Revealed",
                    explanation =
                      "Every WAEC Essay (450+ words) is graded out of 50 marks across four buckets: CONTENT (10 — stayed on topic, developed 3–4 solid body paragraphs), ORGANIZATION (10 — exact letter/article/speech format + smooth paragraph links), EXPRESSION (20 — rich varied clauses, crisp diction, zero slang!), MECHANICAL ACCURACY (10 — ½ mark deducted per spelling/comma/capital error up to zero!).",
                    keyFormula = "\\text{Total 50} = C(10) + O(10) + E(20) + M(10)   |   \\text{Length } \\ge 450\\text{ words}",
                    examTip =
                      "Expression carries 20 out of 50 marks — ALWAYS proofread your final 5 minutes to rescue Mechanical Accuracy marks!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Formal vs Informal Letter Formatting Rules",
                    explanation =
                      "FORMAL LETTER (to Principal, Editor, Minister): (1) Writer's Address (top right) + Date; (2) Recipient's Title & Address (left, below date); (3) 'Dear Sir,' or 'Dear Madam,' (NEVER 'Dear Sir/Ma'!); (4) Underlined/CAPS Subject Heading; (5) Formal tone — NO contractions ('don't' -> 'do not'!); (6) 'Yours faithfully,' + Signature + Full Name! INFORMAL LETTER (to friend/parent): ONE address + Date, 'Dear Chidi,', contractions allowed, 'Yours sincerely,' + First Name ONLY (no signature!).",
                    keyFormula = "\\text{Formal: 2 Addresses + Title + 'Yours faithfully,' + Sign + Full Name}   |   \\text{Informal: 'Yours sincerely,' + First Name}",
                    examTip =
                      "NEVER write 'Yours's' or 'Your's faithfully' — possessive 'Yours' NEVER takes an apostrophe! Also write 'sincerely' with small 's'!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Spotting Formal vs Semi-Formal Subscription Traps",
                    examSource = "WAEC SSCE 2023 • Paper 2 Essay Mechanics",
                    problemStatement =
                      "Spot THREE mechanical/format mistakes in this formal letter closing:\n'Your's Faithfully,\nAdeola'",
                    steps =
                      listOf(
                        "Step 1 — Remove apostrophe: 'Yours' (not 'Your's').",
                        "Step 2 — Lowercase second word: 'faithfully,' (not capital F).",
                        "Step 3 — Formal letter requires handwritten signature + FULL NAME ('Adeola Ogunleye') — first name alone is informal!",
                      ),
                    finalAnswer = "Yours faithfully,\n[Signature]\nAdeola Ogunleye",
                    examinerTrap = "Writing 'Your's Faithfully' costs BOTH Organization AND Mechanical Accuracy marks simultaneously!",
                  ),
                  WorkedExample(
                    title = "Speech & Debate Opening Protocol",
                    examSource = "WAEC SSCE 2022 • Speech Writing Prompt",
                    problemStatement =
                      "How must a formal inter-school debate speech open before stating arguments?",
                    steps =
                      listOf(
                        "Step 1 — Title stating speaker, occasion, and venue.",
                        "Step 2 — Vocative greeting in descending protocol order: 'Good morning, Mr. Chairman, Panel of Judges, Accurate Timekeeper, Co-debaters, Ladies and Gentlemen.'",
                        "Step 3 — State clear stand: 'I stand before you to support/oppose the motion...'",
                      ),
                    finalAnswer = "Title -> Descending Protocol Vocative -> Clear Motion Stand",
                    examinerTrap = "A Speech MUST maintain vocatives throughout ('Mr. Chairman, ladies and gentlemen...') rather than reading like a silent article!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Scholarship & University Motivation Letters",
                    industryDomain = "Higher Education Admissions",
                    narrative =
                      "Clean paragraphing, active voice, zero text-speak abbreviations ('u', 'btw'), and proper formal sign-offs make scholarship essays stand out to admissions committees.",
                    examConnection = "Direct application of WAEC Expression and Organization standards.",
                  ),
                  RealLifeScenario(
                    title = "Newspaper Editorial & Civic Petitions",
                    industryDomain = "Public Advocacy",
                    narrative =
                      "Submitting a Letter to the Editor follows formal two-address formatting plus concise factual paragraphs to get published in national dailies.",
                    examConnection = "Reinforces WAEC Article/Letter to Editor conventions.",
                  ),
                ),
              labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
              labBrief =
                "Check formal concord and punctuation correctness before drafting essays using the Syntax & Lexis Studio below!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_11_1",
                    topic = "Letter Format",
                    examType = "WAEC",
                    year = 2024,
                    question = "Which complimentary close is strictly standard at the end of a formal letter to a Newspaper Editor?",
                    options =
                      listOf(
                        "Yours affectionately, Ada",
                        "Your's faithfully, Adaobi Okonkwo",
                        "Yours faithfully, [Signature] Adaobi Okonkwo",
                        "Yours sincerely, [Signature] Ada",
                      ),
                    correctIndex = 2,
                    hint = "No apostrophe in 'Yours', lowercase 'faithfully', signature + full name.",
                    stepByStepSolution = "Formal letters close with 'Yours faithfully,' followed by signature and full name.",
                    waecMarkingScheme = "A1: 'Yours faithfully,' + signature + full name.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_11_2",
                    topic = "Mechanical Accuracy",
                    examType = "JAMB",
                    year = 2023,
                    question = "Which sentence is completely FREE of spelling and punctuation errors?",
                    options =
                      listOf(
                        "The principal adviced us to be punctual.",
                        "Its high time the committee submitted it's report.",
                        "Neither the accommodation nor the stationery arrived on time.",
                        "We recieved fourty copies of the syllabus.",
                      ),
                    correctIndex = 2,
                    hint = "Check: advised (verb), It's/its, received, forty, accommodation (two c's, two m's), stationery.",
                    stepByStepSolution = "Option C spells 'accommodation' (cc, mm) and 'stationery' (paper) accurately without punctuation faults.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_11_3",
                    topic = "Essay Tone",
                    examType = "WAEC",
                    year = 2022,
                    question = "Which feature should be strictly AVOIDED in a formal argumentative essay or letter of application?",
                    options =
                      listOf(
                        "Topic sentences in paragraphs",
                        "Linking transitions such as 'Furthermore' and 'However'",
                        "Colloquial slang and casual contractions like 'gonna' or 'don't'",
                        "Clear supporting evidence",
                      ),
                    correctIndex = 2,
                    hint = "Formal register forbids slang and contractions.",
                    stepByStepSolution = "Formal WAEC essays require full verb forms ('do not') and standard register devoid of slang.",
                    waecMarkingScheme = "A1: Slang and informal contractions.",
                  ),
                ),
            ),
          ),
      ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion(
        id = "pq_eng_2025_1",
        topic = "Concord",
        examType = "JAMB",
        year = 2025,
        question = "Neither the coach nor the players ___ satisfied with the referee's decision.",
        options = listOf("was", "is", "were", "has been"),
        correctIndex = 2,
        hint = "Proximity Concord matches the noun closer to the verb ('players').",
        stepByStepSolution = "'players' is plural and stands nearest the verb gap, requiring plural 'were'.",
      ),
      PracticeQuestion(
        id = "pq_eng_2025_2",
        topic = "Oral Stress",
        examType = "JAMB",
        year = 2025,
        question = "Which option has a DIFFERENT primary stress pattern from the others?",
        options = listOf("PHO-to-graph", "CON-duct (noun)", "im-PORT (verb)", "SUB-ject (noun)"),
        correctIndex = 2,
        hint = "Check whether each word acts as a noun (1st syllable) or verb (2nd syllable).",
        stepByStepSolution = "PHO-to-graph, CON-duct, and SUB-ject carry 1st-syllable stress; im-PORT (verb) stresses the 2nd syllable.",
      ),
      PracticeQuestion(
        id = "pq_eng_2024_1",
        topic = "Prepositions",
        examType = "JAMB",
        year = 2024,
        question = "The new vice-chancellor congratulated the graduating scholars ___ their distinction.",
        options = listOf("for", "on", "about", "over"),
        correctIndex = 1,
        hint = "Standard collocation: congratulate someone ___ an achievement.",
        stepByStepSolution = "Standard English strictly pairs 'congratulate' with 'on' (or 'upon'), not 'for'.",
      ),
      PracticeQuestion(
        id = "pq_eng_2024_2",
        topic = "Sentence Completion",
        examType = "WAEC",
        year = 2024,
        question = "Hardly had the whistle blown ___ the sprinters surged forward.",
        options = listOf("than", "when", "then", "since"),
        correctIndex = 1,
        hint = "'No sooner...than', whereas 'Hardly/Scarcely...___'.",
        stepByStepSolution = "Correlative conjunctions: 'Hardly/Scarcely ... WHEN', while 'No sooner ... THAN'.",
        waecMarkingScheme = "A1: when.",
      ),
      PracticeQuestion(
        id = "pq_eng_2023_1",
        topic = "Tenses",
        examType = "JAMB",
        year = 2023,
        question = "Scarcely ___ left the hall when the heavy downpour began.",
        options = listOf("we had", "had we", "did we", "have we"),
        correctIndex = 1,
        hint = "Starting with negative adverb 'Scarcely' triggers subject-auxiliary inversion.",
        stepByStepSolution = "'Scarcely had we left... when...' is the required past perfect inversion paired with simple past 'began'.",
      ),
      PracticeQuestion(
        id = "pq_eng_2023_2",
        topic = "Antonyms",
        examType = "WAEC",
        year = 2023,
        question = "Choose the word OPPOSITE in meaning to 'CANDID': 'Although his remarks were CANDID, his colleague remained evasive.'",
        options = listOf("Frank", "Blunt", "Deceitful", "Honest"),
        correctIndex = 2,
        hint = "Candid = open, truthful, straightforward.",
        stepByStepSolution = "Frank/blunt/honest are synonyms of candid; 'deceitful' is opposite.",
        waecMarkingScheme = "A1: Deceitful.",
      ),
      PracticeQuestion(
        id = "pq_eng_2022_1",
        topic = "Phonetics",
        examType = "JAMB",
        year = 2022,
        question = "Which word has the SAME consonant sound as the 'th' in 'BREATHE' (/ð/)?",
        options = listOf("Think", "Thumb", "Worthy", "Path"),
        correctIndex = 2,
        hint = "Look for the voiced dental fricative /ð/ (same as 'this/brother').",
        stepByStepSolution = "'Worthy' (/wɜːði/) shares voiced /ð/; think, thumb, and path use voiceless /θ/.",
      ),
      PracticeQuestion(
        id = "pq_eng_2022_2",
        topic = "Idioms",
        examType = "WAEC",
        year = 2022,
        question = "The new stadium became a WHITE ELEPHANT for the state government. This means it was:",
        options =
          listOf(
            "An albino wildlife reserve",
            "An expensive project bringing little practical benefit",
            "A glowing architectural triumph",
            "Painted white for independence day",
          ),
        correctIndex = 1,
        hint = "Never pick a literal meaning for an idiom.",
        stepByStepSolution = "A 'white elephant' is a costly possession or venture that drains money while proving useless.",
        waecMarkingScheme = "A1: Costly burden with little benefit.",
      ),
      PracticeQuestion(
        id = "pq_eng_2021_1",
        topic = "Emphatic Stress",
        examType = "JAMB",
        year = 2021,
        question = "Prompt: 'Tolu studies MATHEMATICS every evening.' Pick the matching emphatic question:",
        options =
          listOf(
            "Does Ada study Mathematics every evening?",
            "Does Tolu teach Mathematics every evening?",
            "Does Tolu study Physics every evening?",
            "Does Tolu study Mathematics every morning?",
          ),
        correctIndex = 2,
        hint = "Contrast ONLY the capitalized subject MATHEMATICS.",
        stepByStepSolution = "Option C preserves 'Tolu studies ... every evening' and contrasts Physics against MATHEMATICS.",
      ),
      PracticeQuestion(
        id = "pq_eng_2021_2",
        topic = "Concord",
        examType = "WAEC",
        year = 2021,
        question = "One of the scholarship finalists who ___ interviewed yesterday is my classmate.",
        options = listOf("was", "were", "is", "has been"),
        correctIndex = 1,
        hint = "Who does 'who' refer back to right before it — 'One' or 'finalists'?",
        stepByStepSolution = "Relative pronoun 'who' takes its plural antecedent 'finalists' -> 'who WERE interviewed' (while the outer 'One ... IS my classmate' stays singular!).",
        waecMarkingScheme = "A1: were.",
      ),
      PracticeQuestion(
        id = "pq_eng_2020_1",
        topic = "Vocabulary",
        examType = "JAMB",
        year = 2020,
        question = "The judge dismissed the case because the prosecutor's evidence was INCONTROVERTIBLE — Wait, if dismissed, what would describe WEAK, questionable evidence? Pick the synonym for 'DUBIOUS':",
        options = listOf("Irrefutable", "Doubtful", "Crystal-clear", "Compelling"),
        correctIndex = 1,
        hint = "Dubious stems from doubt.",
        stepByStepSolution = "'Dubious' means questionable, uncertain, or doubtful.",
      ),
      PracticeQuestion(
        id = "pq_eng_2020_2",
        topic = "Lexis",
        examType = "WAEC",
        year = 2020,
        question = "Please ensure every member ___ to the club's code of conduct before voting.",
        options = listOf("complies", "adheres", "agrees with", "conforms with"),
        correctIndex = 1,
        hint = "Match with the preposition 'to' right after the blank.",
        stepByStepSolution = "'Adhere TO' is standard ('comply WITH', not 'comply to').",
        waecMarkingScheme = "A1: adheres.",
      ),
      PracticeQuestion(
        id = "pq_eng_2019_1",
        topic = "Rhymes",
        examType = "JAMB",
        year = 2019,
        question = "Which word rhymes perfectly with 'QUAY' (/kiː/)?",
        options = listOf("Say", "Key", "Buy", "Toy"),
        correctIndex = 1,
        hint = "Harbor 'quay' sounds identical to door 'key'.",
        stepByStepSolution = "Both 'quay' and 'key' are pronounced /kiː/.",
      ),
      PracticeQuestion(
        id = "pq_eng_2019_2",
        topic = "Summary Mechanics",
        examType = "WAEC",
        year = 2019,
        question = "In a formal sentence, which correction fixes: 'The committee comprises of twelve elected doctors'?",
        options =
          listOf(
            "The committee comprises twelve elected doctors.",
            "The committee is comprise of twelve elected doctors.",
            "The committee comprise twelve elected doctors.",
            "The committee consisting twelve elected doctors.",
          ),
        correctIndex = 0,
        hint = "'Comprise' already contains the meaning 'consist of' — drop 'of'!",
        stepByStepSolution = "Say either 'comprises twelve doctors' OR 'consists of twelve doctors' — never 'comprises of'.",
        waecMarkingScheme = "A1: Remove 'of' after 'comprises'.",
      ),
      PracticeQuestion(
        id = "pq_eng_2018_1",
        topic = "Modifiers & Usage",
        examType = "JAMB",
        year = 2018,
        question = "My elder sister has been living in Ibadan ___ ten years.",
        options = listOf("since", "from", "for", "during"),
        correctIndex = 2,
        hint = "Use 'since' for a starting point (since 2016); use 'for' for a duration.",
        stepByStepSolution = "'ten years' is a time duration -> 'for ten years'.",
      ),
      PracticeQuestion(
        id = "pq_eng_2018_2",
        topic = "Spelling Mechanics",
        examType = "WAEC",
        year = 2018,
        question = "Select the correctly spelt word:",
        options = listOf("Occurence", "Acommodation", "Embarrassment", "Priviledge"),
        correctIndex = 2,
        hint = "Check double letters: occurrence (rr), accommodation (cc, mm), privilege (no 'd').",
        stepByStepSolution = "'Embarrassment' correctly doubles both 'r' and 's'.",
        waecMarkingScheme = "A1: Embarrassment.",
      ),
    )
}
