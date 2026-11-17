package com.example.data.curriculum

object EnglishCurriculumPart2 {

  val module3And4: List<CourseModule> =
    listOf(
      CourseModule(
        id = "eng_mod_3",
        subjectId = "english",
        indexLabel = "Module 03",
        title = "Question Tags, Punctuation, Synonyms, Antonyms & Idioms",
        summary = "Master polarity-flip Question Tags, Semicolon/Apostrophe rules, Context Lexis (Nearest/Opposite in Meaning), Idioms & Confusable Words.",
        lessons =
          listOf(
            Lesson(
              id = "eng_07_tags_punctuation",
              moduleId = "eng_mod_3",
              subjectId = "english",
              title = "Question Tags & Precision Punctuation",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. A7 • WAEC Tags & Mechanics",
              learningOutcomes =
                listOf(
                  "Apply the 3-step Question Tag flip cleanly, including sneaky negative words ('hardly', 'seldom', 'little', 'few').",
                  "Memorize exceptions: 'I am right, AREN'T I?', 'Let's go, SHALL WE?', 'Shut the door, WILL YOU?'.",
                  "Use Semicolons (;), Colons (:), Hyphens (-) & Possessive Apostrophes ('James's', 'students\' books') accurately.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. The Question Tag Seesaw (+ flips to −; − flips to +)",
                    explanation =
                      "Imagine a seesaw: Positive sentence gets a NEGATIVE tag ('She passed, DIDN'T she?'); Negative sentence gets a POSITIVE tag ('She didn't pass, DID she?'). WATCH OUT for disguised negatives! Words like 'SELDOM', 'HARDLY', 'SCARCELY', 'RARELY', 'NEVER', 'FEW', and 'LITTLE' ALREADY make the sentence negative—so their tag MUST be POSITIVE ('He seldom visits, DOES he?')!",
                    keyFormula = "\\text{I am late, AREN'T I?}   |   \\text{Let's start, SHALL WE?}   |   \\text{Everyone knows, DON'T THEY?}",
                    examTip =
                      "Spot hidden verbs: 'He'd left' = 'He HAD left' → 'hadn't he?'; 'He'd rather' = 'He WOULD rather' → 'wouldn't he?'!",
                  ),
                  ConceptBlock(
                    heading = "2. Apostrophes & Semicolons (Zero Confusion)",
                    explanation =
                      "An Apostrophe shows two jobs ONLY: Contraction (missing letters: 'it's' = 'it is'; 'don't' = 'do not') OR Ownership ('Ada's bag', 'the boys' dormitory'—plural ending in -s puts apostrophe OUTSIDE the s!). Note: 'ITS' (no apostrophe!) means belonging to it ('The dog wagged ITS tail'), never 'it's'!",
                    keyFormula = "\\text{It's} = \\text{It is / It has}   |   \\text{Its} = \\text{Belonging to it}   |   \\text{Who's} = \\text{Who is}   |   \\text{Whose} = \\text{Possessive}",
                    examTip =
                      "A Semicolon (;) joins two closely related full sentences without needing 'and', or separates long items in a messy list containing commas!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Trap Tags: Disguised Negatives & Imperatives",
                    examSource = "JAMB UTME 2024 • Use of English Q27",
                    problemStatement = "Add correct tags to: (1) 'Few students passed the test, ____?' (2) 'Let's take a break, ____?'",
                    steps =
                      listOf(
                        "Step 1 — 'Few' (without 'a') means 'almost none' (NEGATIVE!), so tag flips POSITIVE ⇒ 'did they?' (Notice 'A few' is positive ⇒ 'didn't they?').",
                        "Step 2 — 'Let's' (Let us) always pairs exclusively with 'shall we?'.",
                      ),
                    finalAnswer = "(1) did they?  |  (2) shall we?",
                    examinerTrap = "Using 'didn't they?' after bare 'Few', or 'isn't it?' as a universal Nigerian Pidgin tag!",
                  ),
                  WorkedExample(
                    title = "Everyone / Nobody Pronoun Tag",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q27",
                    problemStatement = "Complete: 'Nobody called while I was out, ____?'",
                    steps =
                      listOf(
                        "Step 1 — 'Nobody' is NEGATIVE, so the tag must be POSITIVE.",
                        "Step 2 — Indefinite human pronouns (Everyone, Someone, Nobody) take verb singular in main clause, but 'THEY' in the tag ⇒ 'did they?'.",
                      ),
                    finalAnswer = "did they?",
                    examinerTrap = "Writing 'didn't he?'—double error on both polarity and tag pronoun!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Legal Apostrophes & Conversational Politeness",
                    industryDomain = "Publishing & Client Support",
                    narrative =
                      "In 2017 a US dairy company lost $5 million in court simply over one missing serial Oxford comma in overtime law!",
                    examConnection = "Shows how punctuation literally changes legal meaning.",
                  ),
                ),
              labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
              labBrief =
                "Toggle sentence polarity, disguised negatives ('hardly', 'few'), 'I am', and 'Let's' prompts to verify exact Question Tag inversions live!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_07_1",
                    topic = "Hidden Negative Tag",
                    examType = "JAMB",
                    year = 2024,
                    question = "She has hardly eaten anything all morning, ____?",
                    options = listOf("hasn't she", "has she", "didn't she", "isn't it"),
                    correctIndex = 1,
                    hint = "'Hardly' makes the statement negative!",
                    stepByStepSolution = "Negative adverb 'hardly' flips tag positive: 'has she?'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_07_2",
                    topic = "I Am Exception Tag",
                    examType = "WAEC",
                    year = 2023,
                    question = "I am the next speaker on the list, ____?",
                    options = listOf("amn't I", "isn't it", "aren't I", "am I"),
                    correctIndex = 2,
                    hint = "Standard English uses 'aren't I?' for 'I am...'.",
                    stepByStepSolution = "Special exception: positive 'I am' takes 'aren't I?'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_07_3",
                    topic = "Contraction Expansion Tag",
                    examType = "JAMB",
                    year = 2025,
                    question = "Tunde'd rather stay indoors today, ____?",
                    options = listOf("hadn't he", "wouldn't he", "didn't he", "isn't he"),
                    correctIndex = 1,
                    hint = "'d before 'rather' stands for WOULD.",
                    stepByStepSolution = "'Tunde would rather...' → 'wouldn't he?'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_07_4",
                    topic = "Possessive Its vs It's",
                    examType = "WAEC",
                    year = 2022,
                    question = "The injured bird dragged ____ broken wing across the grass.",
                    options = listOf("it's", "its'", "its", "it is"),
                    correctIndex = 2,
                    hint = "Possessive determiner 'its' NEVER takes an apostrophe!",
                    stepByStepSolution = "Use 'its' (like his/her) for ownership.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_07_5",
                    topic = "Plural Possessive",
                    examType = "JAMB",
                    year = 2021,
                    question = "The new hostel was built specifically for ____ accommodation.",
                    options = listOf("girl's", "girls'", "girls's", "girls"),
                    correctIndex = 1,
                    hint = "Plural 'girls' already ends in s → apostrophe goes AFTER s.",
                    stepByStepSolution = "'girls'' indicates belonging to multiple female students.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_07_6",
                    topic = "Imperative Tag",
                    examType = "WAEC",
                    year = 2024,
                    question = "Pass me that beaker carefully, ____?",
                    options = listOf("shall you", "will you", "aren't you", "don't you"),
                    correctIndex = 1,
                    hint = "Polite imperatives use 'will you / won't you'.",
                    stepByStepSolution = "Requests take 'will you?' (or 'won't you?').",
                  ),
                ),
            ),

            Lesson(
              id = "eng_08_synonyms_antonyms",
              moduleId = "eng_mod_3",
              subjectId = "english",
              title = "Lexis in Context: Nearest & Opposite in Meaning",
              durationMinutes = 19,
              syllabusTag = "JAMB & WAEC Sec. B1–B2 • Synonyms & Antonyms",
              learningOutcomes =
                listOf(
                  "Use sentence context clues (contrast words 'although/however', tone, cause-effect) to decode unfamiliar words.",
                  "Check whether the question asks NEAREST IN MEANING (Synonym) or OPPOSITE IN MEANING (Antonym) BEFORE looking at options!",
                  "Verify that your chosen option preserves the exact Part of Speech and formality register.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. The 3-Second Context Clues Method (Don't Translate Out of Context!)",
                    explanation =
                      "Words shift meaning depending on their neighbours! Example: 'We must COMBAT malaria' (fight) vs 'A SPARSE crowd attended' (thin/scattered) vs 'A CANDID remark' (frank/honest). Always cover options first, read the sentence's emotional clue ('despite', 'consequently', 'therefore'), guess YOUR OWN simple replacement word, and then pick the option matching that meaning + Part of Speech!",
                    keyFormula = "\\text{Check Instructions First: OPPOSITE (Antonym) vs NEAREST (Synonym) — NEVER confuse the two sections!}",
                    examTip =
                      "High-frequency JAMB pairs: Frugal ↔ Extravagant; Ephemeral ↔ Permanent; Candid ↔ Evasive;Mitigate ↔ Aggravate; Ubiquitous ↔ Scarce; Loquacious ↔ Taciturn!",
                  ),
                  ConceptBlock(
                    heading = "2. Morphology Roots: Unlock 500 Words From 10 Latin/Greek Keys",
                    explanation =
                      "'Bene-' = GOOD (benevolent, benefit); 'Mal-' = BAD (malicious, dismal); 'Omni-' = ALL (omnipotent); 'Equi-' = EQUAL; '-phobia' = FEAR; 'Chron-' = TIME (anachronism, chronic); 'Greg-' = FLOCK/CROWD (gregarious = sociable!). Knowing word DNA lets you crack brand-new SAT/JAMB vocabulary calmly!",
                    keyFormula = "\\text{A-/An- (without): apathy, anonymous}   |   \\text{Circum- (around): circumvent}   |   \\text{Epi-/Eu- (good): eulogy}",
                    examTip =
                      "Notice Antonym rule: if the target word is a PAST tense verb or PLURAL noun, the right antonym matches its grammatical form cleanly!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Contrast Signal Cracking Antonym ('frugal' vs 'profligate')",
                    examSource = "JAMB UTME 2024 • Lexis Q31",
                    problemStatement =
                      "Opposite in meaning: 'While Kemi is remarkably FRUGAL with her allowance, her twin brother is quite ____.'",
                    steps =
                      listOf(
                        "Step 1 — Contrast word 'While' signals opposite behaviour.",
                        "Step 2 — Frugal = thrifty/careful with money ⇒ Opposite = Extravagant / Profligate / Spendthrift.",
                      ),
                    finalAnswer = "extravagant (or profligate)",
                    examinerTrap = "Misreading 'Opposite' section instructions and picking 'thrifty' (which is the synonym!)!",
                  ),
                  WorkedExample(
                    title = "Decoding Word in Context ('ameliorate')",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q33",
                    problemStatement =
                      "Nearest in meaning: 'The new rural road network will AMELIORATE farmers' transport hardships.'",
                    steps =
                      listOf(
                        "Step 1 — Context shows a positive outcome helping farmers with hardships.",
                        "Step 2 — Replace mentally: 'will IMPROVE / EASE hardships' ⇒ Option 'alleviate / improve'.",
                      ),
                    finalAnswer = "improve / alleviate",
                    examinerTrap = "Don't let negative noun 'hardships' trick you into picking 'worsen' ('aggravate')!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Reading Contracts & Academic Research Papers",
                    industryDomain = "Higher Education & Journalism",
                    narrative =
                      "Strong contextual vocabulary lets you read unfamiliar medical, economic, or legal articles fluently without halting every two lines for a dictionary.",
                    examConnection = "Directly builds comprehension speed too.",
                  ),
                ),
              labType = PracticalLabType.LEXIS_IDIOMS_REGISTER_LAB,
              labBrief =
                "Practice decoding high-yield JAMB/WAEC Latin roots, contrast clues, and nearest-vs-opposite word switches interactively!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_08_1",
                    topic = "Antonym in Context",
                    examType = "JAMB",
                    year = 2024,
                    question = "OPPOSITE in meaning: 'Unlike his TRANSPARENT elder brother, Jude is notoriously ____.'",
                    options = listOf("honest", "secretive / devious", "generous", "fragile"),
                    correctIndex = 1,
                    hint = "Transparent (moral context) = open and honest; opposite = deceptive/secretive.",
                    stepByStepSolution = "In character contexts, transparent ↔ secretive/devious (not opaque physically).",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_08_2",
                    topic = "Synonym in Context",
                    examType = "WAEC",
                    year = 2023,
                    question = "NEAREST in meaning: 'Fame on social media is often EPHEMERAL.'",
                    options = listOf("eternal", "short-lived", "lucrative", "painful"),
                    correctIndex = 1,
                    hint = "Lasting only a day or short season.",
                    stepByStepSolution = "'Ephemeral' means transitory or short-lived.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_08_3",
                    topic = "Antonym Pair",
                    examType = "JAMB",
                    year = 2025,
                    question = "OPPOSITE in meaning: 'The medicine helped MITIGATE the patient's pain.'",
                    options = listOf("soothe", "aggravate", "reduce", "diagnose"),
                    correctIndex = 1,
                    hint = "Mitigate = make milder; opposite = make worse!",
                    stepByStepSolution = "'Aggravate' / 'exacerbate' means worsen (antonym of mitigate).",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_08_4",
                    topic = "Synonym",
                    examType = "WAEC",
                    year = 2022,
                    question = "NEAREST in meaning: 'He gave a CANDID assessment of the team's defensive errors.'",
                    options = listOf("biased", "frank and honest", "sugarcoated", "hesitant"),
                    correctIndex = 1,
                    hint = "Unfiltered, straight-talking.",
                    stepByStepSolution = "'Candid' means frank, sincere, and outspoken.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_08_5",
                    topic = "Antonym",
                    examType = "JAMB",
                    year = 2021,
                    question = "OPPOSITE in meaning: 'Because he is GREGARIOUS, he enjoys large noisy parties.'",
                    options = listOf("talkative", "sociable", "reclusive / aloof", "cheerful"),
                    correctIndex = 2,
                    hint = "Root 'greg' = flock/crowd; opposite loves solitude.",
                    stepByStepSolution = "Gregarious (sociable) ↔ reclusive/introverted.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_08_6",
                    topic = "Root Decoding",
                    examType = "WAEC",
                    year = 2024,
                    question = "NEAREST in meaning: 'Smartphones have become UBIQUITOUS in modern cities.'",
                    options = listOf("obsolete", "found everywhere", "illegal", "fragile"),
                    correctIndex = 1,
                    hint = "Omnipresent.",
                    stepByStepSolution = "'Ubiquitous' means present everywhere at once.",
                  ),
                ),
            ),

            Lesson(
              id = "eng_09_idioms_phrasal_verbs",
              moduleId = "eng_mod_3",
              subjectId = "english",
              title = "Idioms, Phrasal Verbs & Figurative Meanings",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. B3 • WAEC Idiomatic Interpretation",
              learningOutcomes =
                listOf(
                  "Spot literal decoy distractors and decode figurative idioms accurately.",
                  "Master subtle prepositional shifts in high-frequency Phrasal Verbs ('put up with', 'call off', 'see through', 'take after').",
                  "Interpret understatements, irony, and rhetorical proverbs cleanly in comprehension passages.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Golden Idiom Law: NEVER Pick the Literal Picture!",
                    explanation =
                      "If a question says 'Chidi let the cat out of the bag', crossing out any option mentioning 'animals' or 'pets' immediately boosts your score! Idioms have ONE holistic figurative meaning: 'let the cat out of the bag' = accidentally spill a secret; 'kick the bucket' = die; 'bite the bullet' = face a tough situation bravely; 'turn a deaf ear' = refuse to listen; 'burn the midnight oil' = study late at night!",
                    keyFormula = "\\text{White Elephant} = \\text{Costly useless project}   |   \\text{Red Herring} = \\text{Misleading clue}   |   \\text{Cold Shoulder} = \\text{Deliberately ignore}",
                    examTip =
                      "Watch WAEC trap: 'break the ice' means ease awkward social tension—NOT smash frozen water!",
                  ),
                  ConceptBlock(
                    heading = "2. Phrasal Verb Particle Magic (Same Verb, Wildly New Meaning!)",
                    explanation =
                      "Compare 'PUT': 'Put OFF' = postpone; 'Put OUT' = extinguish a fire; 'Put UP WITH' = tolerate patiently; 'Put DOWN' = criticize/suppress! Compare 'CALL': 'Call OFF' = cancel; 'Call ON' = visit briefly / invite to speak; 'Call FOR' = demand/require! Compare 'MAKE': 'Make UP' = invent/reconcile; 'Make UP FOR' = compensate for; 'Make OUT' = discern barely!",
                    keyFormula = "\\text{Look up to} = \\text{Admire}   |   \\text{Look down on} = \\text{Despise}   |   \\text{Take after} = \\text{Resemble a parent}",
                    examTip =
                      "Never say 'cope up with'—standard English is simply 'cope WITH'!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Decoding Classic JAMB Idiom & Eliminating Literal Trap",
                    examSource = "JAMB UTME 2024 • Use of English Q36",
                    problemStatement =
                      "Choose exact interpretation: 'The chairman decided to throw in the towel after losing key board support.'",
                    steps =
                      listOf(
                        "Step 1 — Boxing origin: a trainer throws a towel into the ring to stop the match.",
                        "Step 2 — Figuratively means 'admit defeat and resign / give up' (reject literal options about drying sweat!).",
                      ),
                    finalAnswer = "Admit defeat and resign / give up",
                    examinerTrap = "Distractor options love mimicking the literal words of the idiom ('clean his face with cloth').",
                  ),
                  WorkedExample(
                    title = "Distinguishing 'Turn Down' vs 'Turn Up'",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q38",
                    problemStatement = "Select right phrasal verb: 'Because the salary was too low, Nkechi ____ the job offer.'",
                    steps =
                      listOf(
                        "Step 1 — Context shows refusal/rejection of an offer.",
                        "Step 2 — 'Turn down' = reject/refuse; 'Turn up' = arrive/appear.",
                      ),
                    finalAnswer = "turned down",
                    examinerTrap = "Don't swap 'turned down' (refused) with 'turned off' (extinguished/disgusted)!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Business News Headlines & Diplomatic Speeches",
                    industryDomain = "Financial Journalism",
                    narrative =
                      "Financial articles constantly pack idioms like 'tighten belts', 'weather the storm', 'red tape' (bureaucratic delay), and 'in the red' (in debt).",
                    examConnection = "In the black = profitable; in the red = losing money.",
                  ),
                ),
              labType = PracticalLabType.LEXIS_IDIOMS_REGISTER_LAB,
              labBrief =
                "Test common Nigerian/British exam idioms & Phrasal Verb particles ('put off / up with / out', 'take after / over') side-by-side!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_09_1",
                    topic = "Idioms",
                    examType = "JAMB",
                    year = 2024,
                    question = "What does 'The abandoned multi-billion naira stadium has become a WHITE ELEPHANT' mean?",
                    options = listOf("A tourist zoo", "An expensive monument that brings no useful return", "Painted bright white", "A rare success"),
                    correctIndex = 1,
                    hint = "Costly burden to maintain, zero profit.",
                    stepByStepSolution = "'White elephant' means a possession that costs far more than its usefulness.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_09_2",
                    topic = "Phrasal Verbs",
                    examType = "WAEC",
                    year = 2023,
                    question = "The heavy thunderstorm forced the referee to ____ the football match.",
                    options = listOf("call out", "call off", "call on", "call in"),
                    correctIndex = 1,
                    hint = "Cancel an arranged event.",
                    stepByStepSolution = "'Call off' = cancel (whereas 'put off' = postpone to a later date).",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_09_3",
                    topic = "Idiomatic Accuracy",
                    examType = "JAMB",
                    year = 2025,
                    question = "'My grandmother lives far away, so I only visit her ONCE IN A BLUE MOON.' This means I visit:",
                    options = listOf("Every night", "Very rarely", "Monthly", "During eclipses only"),
                    correctIndex = 1,
                    hint = "Rare astronomical coincidence.",
                    stepByStepSolution = "'Once in a blue moon' means extremely rarely.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_09_4",
                    topic = "Phrasal Verb Contrast",
                    examType = "WAEC",
                    year = 2022,
                    question = "Little Musa clearly ____ his grandfather—both share the exact same laugh and love for chess.",
                    options = listOf("takes over", "takes after", "takes off", "takes up"),
                    correctIndex = 1,
                    hint = "Resembles an older relative in appearance or character.",
                    stepByStepSolution = "'Take after' means resemble an ancestor.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_09_5",
                    topic = "Prepositional Phrasal Trap",
                    examType = "JAMB",
                    year = 2021,
                    question = "Identify the correct standard expression: 'I cannot ____ such noisy behaviour any longer.'",
                    options = listOf("put off with", "put up with", "cope up with", "take up with"),
                    correctIndex = 1,
                    hint = "Means endure or tolerate patiently.",
                    stepByStepSolution = "'Put up with' = tolerate; never 'cope up with'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_09_6",
                    topic = "Proverbial Idiom",
                    examType = "WAEC",
                    year = 2024,
                    question = "To say the lawyer 'BEAT ABOUT THE BUSH' during cross-examination means he:",
                    options = listOf("Searched a garden", "Avoided tackling the main point directly", "Won quickly", "Whispered softly"),
                    correctIndex = 1,
                    hint = "Indirect dodging instead of direct answer.",
                    stepByStepSolution = "'Beat about the bush' means talking round a topic instead of coming straight to the point.",
                  ),
                ),
            ),
          ),
      ),

      CourseModule(
        id = "eng_mod_4",
        subjectId = "english",
        indexLabel = "Module 04",
        title = "Prepositions, Registers & Oral Phonetics (Vowels & Consonants)",
        summary = "Eliminate preposition errors, ace Specialized Cloze Registers, and hear every IPA Monophthong, Diphthong & Consonant sound clearly.",
        lessons =
          listOf(
            Lesson(
              id = "eng_10_prepositions_confusables",
              moduleId = "eng_mod_4",
              subjectId = "english",
              title = "Fixed Prepositions & Confusable Word Pairs",
              durationMinutes = 19,
              syllabusTag = "JAMB & WAEC Sec. B4 • Collocations & Usage Errors",
              learningOutcomes =
                listOf(
                  "Memorize top 20 JAMB preposition pairings ('congratulate ON', 'charge WITH', 'accuse OF', 'comply WITH', 'prefer TO').",
                  "Eliminate Nigerian English redundancies ('return back', 'discuss about', 'enter inside', 'comprise of').",
                  "Distinguish confusable twins cleanly (Stationary/Stationery, Affect/Effect, Principle/Principal, Compliment/Complement).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Zero-Preposition Verbs & Exact Preposition Collocations",
                    explanation =
                      "Certain common verbs already carry their direction INSIDE them—adding an extra preposition is WRONG! Say: 'DISCUSS the plan' (NEVER 'discuss about'), 'ENTER the hall' (NEVER 'enter into/inside'), 'DEMAND a refund' (NEVER 'demand for'), 'COMPRISE 5 sections' (OR 'consist OF'—NEVER 'comprise of'!), 'RETURN the book' (NEVER 'return back'). On the flip side: 'CONGRATULATE someone ON' (not 'for'), 'PREFER tea TO coffee' (NEVER 'than'!), 'ACCUSED OF theft', 'CHARGED WITH fraud'!",
                    keyFormula = "\\text{Accuse OF}   |   \\text{Charge WITH}   |   \\text{Congratulate ON}   |   \\text{Comply WITH}   |   \\text{Prefer A TO B}",
                    examTip =
                      "Watch 'Arrive IN' vs 'Arrive AT': Arrive IN a huge city/country ('arrived in Abuja'); Arrive AT a building/spot ('arrived at the gate')!",
                  ),
                  ConceptBlock(
                    heading = "2. Famous Confusable Twins (Spelling Tricks That Never Fail)",
                    explanation =
                      "StationARy = pARked (standing still), StationERy = papER/pEns! PrincipAL = school pAL / main, PrincipLE = moral ruLE! Affect = Action verb, Effect = End result noun! ComplIment = praise ('I like'), ComplEment = makes complEte (e.g. complementary angles)! Prescribe (doctor writes medicine) vs Proscribe (government BANS a group)!",
                    keyFormula = "\\text{Die OF (disease: malaria)}   |   \\text{Die FROM (external cause: wound/hunger)}   |   \\text{Divide BETWEEN 2 / AMONG 3+}",
                    examTip =
                      "Compare 'Advise' (verb with s: 'Please advise me') vs 'Advice' (noun with c: 'He gave good advice')!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Eliminating Verb Redundancies & Fixing Prepositions",
                    examSource = "JAMB UTME 2024 • Use of English Q40",
                    problemStatement =
                      "Spot errors in: 'We discussed about our preference for Science than Arts and entered inside the lab.'",
                    steps =
                      listOf(
                        "Step 1 — Remove 'about' after 'discussed'.",
                        "Step 2 — Pair 'prefer' with 'to' (not 'than').",
                        "Step 3 — Remove 'inside' after 'entered'.",
                      ),
                    finalAnswer = "We discussed preferring Science to Arts and entered the lab.",
                    examinerTrap = "Hearing redundant pairings daily in conversation makes them sound deceptively normal!",
                  ),
                  WorkedExample(
                    title = "Stationary vs Stationery & Preposition Pair",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q41",
                    problemStatement = "Complete: 'The clerk ordered fresh office ____ and congratulated Bode ____ his promotion.'",
                    steps =
                      listOf(
                        "Step 1 — Paper/pens = 'stationery' (with 'er').",
                        "Step 2 — 'Congratulate' pairs strictly with 'on' (or 'upon') in formal standard English.",
                      ),
                    finalAnswer = "stationery  |  on",
                    examinerTrap = "Picking 'congratulate for' or spelling paper 'stationary'!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Official Correspondence & Court Reporting",
                    industryDomain = "Law & Corporate Admin",
                    narrative =
                      "Court reports strictly enforce 'charged WITH murder', 'convicted OF treason', 'acquitted OF all charges'—each preposition carries precise legal grammar.",
                    examConnection = "Preposition precision scores quick marks across both Cloze and Lexis sections.",
                  ),
                ),
              labType = PracticalLabType.LEXIS_IDIOMS_REGISTER_LAB,
              labBrief =
                "Test fixed collocations ('accuse of' vs 'charge with'), spot zero-preposition verbs, and pair Confusable Twins interactively!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_10_1",
                    topic = "Zero-Preposition Verb",
                    examType = "JAMB",
                    year = 2024,
                    question = "The new Science faculty ____ six modern departments.",
                    options = listOf("comprises of", "comprises", "is comprise of", "consists"),
                    correctIndex = 1,
                    hint = "'Consists OF' = 'Comprises' (NO 'of'!).",
                    stepByStepSolution = "'Comprises' already includes 'of'; never write 'comprises of'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_10_2",
                    topic = "Fixed Preposition",
                    examType = "WAEC",
                    year = 2023,
                    question = "The board warmly congratulated the captain ____ leading the team to victory.",
                    options = listOf("for", "about", "on", "with"),
                    correctIndex = 2,
                    hint = "Congratulate someone ON an achievement.",
                    stepByStepSolution = "Standard collocation is 'congratulate on'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_10_3",
                    topic = "Confusable Words",
                    examType = "JAMB",
                    year = 2025,
                    question = "Government authorities decided to ____ the violent cult group immediately.",
                    options = listOf("prescribe", "proscribe", "subscribe", "inscribe"),
                    correctIndex = 1,
                    hint = "Doctors PRESCRIBE medicine; governments PROSCRIBE (outlaw/ban) organizations.",
                    stepByStepSolution = "'Proscribe' means officially ban or outlaw.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_10_4",
                    topic = "Prefer Collocation",
                    examType = "WAEC",
                    year = 2022,
                    question = "Most candidates prefer solving Algebra ____ reading long Poetry.",
                    options = listOf("than", "more than", "to", "over"),
                    correctIndex = 2,
                    hint = "Prefer A TO B!",
                    stepByStepSolution = "'Prefer' takes preposition 'to' when comparing gerunds or nouns.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_10_5",
                    topic = "Die Of vs From",
                    examType = "JAMB",
                    year = 2021,
                    question = "Prompt clinic visits saved thousands who might otherwise have died ____ typhoid fever.",
                    options = listOf("with", "by", "of", "through"),
                    correctIndex = 2,
                    hint = "Illness/disease/hunger takes 'die OF'.",
                    stepByStepSolution = "Standard usage is 'die of' a named disease (e.g., malaria, typhoid).",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_10_6",
                    topic = "Affect vs Effect",
                    examType = "WAEC",
                    year = 2024,
                    question = "How will rising fuel prices ____ daily transport fares?",
                    options = listOf("effect", "affect", "effective", "affection"),
                    correctIndex = 1,
                    hint = "Need an Action Verb after 'will'.",
                    stepByStepSolution = "'Affect' is the verb meaning influence; 'effect' is normally a noun meaning result.",
                  ),
                ),
            ),

            Lesson(
              id = "eng_11_registers_cloze",
              moduleId = "eng_mod_4",
              subjectId = "english",
              title = "Specialized Registers & Cloze Passage Mastery",
              durationMinutes = 19,
              syllabusTag = "JAMB Sec. B5 • WAEC Registers & Cloze Passages",
              learningOutcomes =
                listOf(
                  "Decode topic-specific vocabularies across Law, Medicine, Aviation, Banking, Publishing, Farming & Election registers.",
                  "Solve Cloze fill-in-the-blank paragraphs using BOTH nearby grammar fit and domain tone.",
                  "Eliminate colloquial words when passages maintain formal professional registers.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. What Is a Register? (Speaking the Industry Language!)",
                    explanation =
                      "A hospital patient pays a FEE, receives a DIAGNOSIS & PRESCRIPTION, and lies on a WARD—nobody calls a patient a 'customer' or hospital wards 'bedrooms'! Courtrooms speak of PLAINTIFF (sues), DEFENDANT (accused), VERDICT, BAIL, SENTENCE & ACQUITTAL. Airports speak of RUNWAY, TARMAC, BOARDING PASS, TURBULENCE & ALTITUDE. Cloze passages test BOTH domain word AND grammar around each blank!",
                    keyFormula = "\\text{Banking: Ledger, Overdraft, Collateral}   |   \\text{Publishing: Manuscript, Proofread, Royalty, Blurb}",
                    examTip =
                      "Before picking a Cloze blank, read 2 words BEFORE and 2 words AFTER the gap to spot prepositions like 'to', 'a/an', or singular verbs!",
                  ),
                  ConceptBlock(
                    heading = "2. Mastering Tone & Collocation Clues in Cloze Tests",
                    explanation =
                      "If a Cloze passage describes a courtroom trial, 'witness' beats 'spectator'; in a football match, 'spectators' beat 'audience'; in a concert/theatre, 'audience' wins; in a church sermon, 'congregation' wins! Watch every verb partner: you 'LAUNCH' an appeal/ship, 'SWORN IN' an official, 'CAST' a ballot, and 'PROROGUE/ADJOURN' parliament!",
                    keyFormula = "\\text{Crowd: Spectators (Sports) / Audience (Concert) / Congregation (Church) / Electorate (Voting)}",
                    examTip =
                      "Read the whole Cloze paragraph once completely BEFORE filling blank #1—earlier blanks often rely on context revealed in sentence 4!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Cloze Domain Choice: Hospital vs Judiciary",
                    examSource = "WAEC SSCE 2024 • Section 6 Cloze",
                    problemStatement =
                      "Fill blanks: 'After examining the X-ray, the physician confirmed a positive ____ (prognosis/verdict) and wrote a ____ (recipe/prescription) for antibiotics.'",
                    steps =
                      listOf(
                        "Step 1 — 'verdict' belongs to Courts; 'prognosis/diagnosis' belongs to Medicine.",
                        "Step 2 — Cooks write a 'recipe'; doctors write a 'prescription'.",
                      ),
                    finalAnswer = "prognosis/diagnosis  |  prescription",
                    examinerTrap = "Always reject words from the wrong field even if they look superficially plausible!",
                  ),
                  WorkedExample(
                    title = "Choosing Correct Crowd Word in Context",
                    examSource = "JAMB UTME 2023 • Cloze Passage",
                    problemStatement = "'Over 60,000 enthusiastic ____ packed the National Stadium for the Cup Final.'",
                    steps =
                      listOf(
                        "Step 1 — Sporting event inside a stadium → 'spectators'.",
                        "Step 2 — 'audience' watches plays/lectures; 'congregation' attends worship.",
                      ),
                    finalAnswer = "spectators",
                    examinerTrap = "Using 'audience' generically for live outdoor sports matches!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Cross-Disciplinary Workplace Writing",
                    industryDomain = "Broadcasting & PR",
                    narrative =
                      "News anchors switch smoothly between Maritime ('berth, cargo, manifest'), Aviation ('fuselage, hangar, cockpit'), and Banking ('dividend, liquid, moratorium') registers daily.",
                    examConnection = "Worth 10–15 fast high-accuracy marks in both WAEC & JAMB!",
                  ),
                ),
              labType = PracticalLabType.LEXIS_IDIOMS_REGISTER_LAB,
              labBrief =
                "Switch among Banking, Medical, Legal, Aviation & Agriculture Register Boards to match exact terminology & crowd nouns!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_11_1",
                    topic = "Publishing Register",
                    examType = "JAMB",
                    year = 2024,
                    question = "Before a typed novel goes to print, the author submits the ____ to the editor to check for typos.",
                    options = listOf("syllabus", "manuscript", "prescription", "manifest"),
                    correctIndex = 1,
                    hint = "Publishing word for an author's unpublished book draft.",
                    stepByStepSolution = "A manuscript is an author's draft submitted for editing and printing.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_11_2",
                    topic = "Banking Register",
                    examType = "WAEC",
                    year = 2023,
                    question = "When securing a business loan, the house deed pledged as security against default is called:",
                    options = listOf("Dividend", "Collateral", "Overdraft", "Inflation"),
                    correctIndex = 1,
                    hint = "Asset pledged to guarantee repayment.",
                    stepByStepSolution = "Collateral protects the bank if a borrower defaults.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_11_3",
                    topic = "Judicial Register",
                    examType = "JAMB",
                    year = 2025,
                    question = "In a civil lawsuit, the party initiating legal action by suing someone is the:",
                    options = listOf("Defendant", "Plaintiff", "Bailiff", "Warden"),
                    correctIndex = 1,
                    hint = "Plaintiff complains/sues; Defendant defends.",
                    stepByStepSolution = "The Plaintiff brings the civil claim before the court.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_11_4",
                    topic = "Aviation Register",
                    examType = "WAEC",
                    year = 2022,
                    question = "The shed where aircraft are parked and mechanically serviced is a:",
                    options = listOf("Harbour", "Hangar", "Terminal", "Dockyard"),
                    correctIndex = 1,
                    hint = "Spelled 'hangAr' (with an 'a', unlike clothes hangEr!).",
                    stepByStepSolution = "An aircraft hangar houses planes for maintenance.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_11_5",
                    topic = "Election Register",
                    examType = "JAMB",
                    year = 2021,
                    question = "At a polling booth, citizens ____ their ballots secretly inside a screened cubicle.",
                    options = listOf("throw", "cast", "deliver", "donate"),
                    correctIndex = 1,
                    hint = "Fixed election verb collocation.",
                    stepByStepSolution = "Voters 'cast' ballots.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_11_6",
                    topic = "Medical Register",
                    examType = "WAEC",
                    year = 2024,
                    question = "To prevent infection during surgery, nurses ____ all scalpels in high-pressure steam.",
                    options = listOf("evaporate", "sterilize", "immunize", "diagnose"),
                    correctIndex = 1,
                    hint = "Destroying all microbes on instruments.",
                    stepByStepSolution = "Sterilization removes microorganisms from surgical tools.",
                  ),
                ),
            ),

            Lesson(
              id = "eng_12_vowels_diphthongs",
              moduleId = "eng_mod_4",
              subjectId = "english",
              title = "Oral English: Monophthongs (Short/Long Vowels) & Diphthongs",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. C1 • WAEC Test of Orals: Pure Vowels & Glides",
              learningOutcomes =
                listOf(
                  "Tell long vowels (/iː/, /ɑː/, /ɔː/, /uː/, /ɜː/) apart from short vowels (/ɪ/, /æ/, /ɒ/, /ʊ/, /ʌ/) even when spelling tricks you!",
                  "Spot the hidden central Schwa /ə/ in unstressed syllables ('about', 'teacher', 'doctor').",
                  "Hear the 8 English Diphthongs (/eɪ/, /aɪ/, /ɔɪ/, /əʊ/, /aʊ/, /ɪə/, /eə/, /ʊə/) accurately.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Spelling Lies — Listen With Your Mouth Shape! (Long vs Short Vowels)",
                    explanation =
                      "English spelling plays wicked pranks: 'WOMEN' looks like 'o', yet sounds like /wɪmɪn/ (same short /ɪ/ as 'sit' and 'busy'!). 'HEART' looks like 'ea', yet says /ɑː/ (long 'car / clerk / pass')! Look out for TWO dots 'ː' in IPA symbols—they mean STRETCH the vowel long: /iː/ ('seat, key, quay, people') vs quick /ɪ/ ('sit, live, women, village')! And meet English's #1 superstar vowel: lazy /ə/ (Schwa), heard in the weak 'uh' of 'About', 'doctOr', 'teachEr'!",
                    keyFormula = "/\\text{iː}/\\; (\\text{key, quay, police}) \\neq /\\text{ɪ}/\\; (\\text{women, busy, build})   |   /\\text{ɜː}/\\; (\\text{bird, work, earn, colonel})",
                    examTip =
                      "JAMB classics: 'bury' = /e/ (just like 'bed'!); 'quay' = /iː/ ('key'!); 'leopard / friend' = /e/; 'colonel' = /ɜː/ ('kernel'!)!",
                  ),
                  ConceptBlock(
                    heading = "2. The 8 Diphthongs (Two-Vowel Smooth Glides)",
                    explanation =
                      "A Diphthong slides smoothly from one vowel into another inside ONE syllable! Watch these sneaky spelling pairs: /eɪ/ ('cake, rain, GREAT, BREAK, GAUGE, STEAK'—NOT 'meat'!), /aɪ/ ('kite, height, buy, island, eye'), /əʊ/ ('go, boat, SEW, THOUGH, DOUGH'!), /aʊ/ ('house, cow, plough'), /eə/ ('care, BEAR, WEAR, HEIR, THERE'!), and /ɪə/ ('here, fear, BEER, WEIRD')!",
                    keyFormula = "/\\text{eɪ}/\\; (\\text{great, break})   |   /\\text{əʊ}/\\; (\\text{sew, though})   |   /\\text{eə}/\\; (\\text{air, heir})   |   /\\text{aɪ}/\\; (\\text{aisle, height})",
                    examTip =
                      "'Bought, caught, saw, core, sword' ALL share the long pure monophthong /ɔː/—NOT a diphthong!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Cracking /eɪ/ vs /iː/ in 'ea' Spellings",
                    examSource = "JAMB UTME 2024 • Oral English Q46",
                    problemStatement = "Which word has the SAME vowel sound as 'GREAT': (A) meat  (B) bread  (C) steak  (D) hear?",
                    steps =
                      listOf(
                        "Step 1 — Say 'great' → glides /eɪ/ (rhymes with 'gate, wait').",
                        "Step 2 — Check options: meat (/iː/), bread (/e/), STEAK (/eɪ/!), hear (/ɪə/).",
                      ),
                    finalAnswer = "(C) steak (/eɪ/)",
                    examinerTrap = "Don't match letters ('ea' looks like 'meat')—match SOUND!",
                  ),
                  WorkedExample(
                    title = "Identifying the Central Vowel /ɜː/",
                    examSource = "WAEC SSCE 2023 • Paper 3 Orals Q5",
                    problemStatement = "Match the vowel sound in 'CURSE' (/ɜː/): (A) heart  (B) word  (C) lord  (D) war.",
                    steps =
                      listOf(
                        "Step 1 — 'Curse' has long central vowel /ɜː/ (bird, nurse, girl).",
                        "Step 2 — 'wORd / wORk / wORld' also use /ɜː/ (whereas lord & war use /ɔː/, heart uses /ɑː/).",
                      ),
                    finalAnswer = "(B) word (/ɜː/)",
                    examinerTrap = "Remember 'w + or' ('word, work, world, worse') almost ALWAYS makes /ɜː/, NEVER /ɔː/!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Broadcast Pronunciation & Automatic Speech Recognition",
                    industryDomain = "Media & AI Speech Systems",
                    narrative =
                      "Clear contrast between /iː/ ('sheep, leave, seat') and /ɪ/ ('ship, live, sit') prevents embarrassing mix-ups in international broadcasting and voice dictation.",
                    examConnection = "Test of Orals accounts for 15+ guaranteed marks in JAMB & 60 marks in WAEC Paper 3!",
                  ),
                ),
              labType = PracticalLabType.PHONETICS_CONSONANTS_LAB,
              labBrief =
                "Browse IPA vowel cards (/iː/ vs /ɪ/, /ɜː/ vs /ɔː/, /eɪ/ vs /eə/) to hear trick-spelling pairs and test minimal pairs live!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_12_1",
                    topic = "Short /ɪ/ Trick Spelling",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which word contains the SAME vowel sound as the underlined vowel in 's<u>i</u>t' (/ɪ/)?",
                    options = listOf("seat", "women", "police", "key"),
                    correctIndex = 1,
                    hint = "Say 'w-I-m-i-n' aloud!",
                    stepByStepSolution = "'Women' /ˈwɪmɪn/ shares short /ɪ/ with 'sit' (all others have long /iː/).",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_12_2",
                    topic = "Long /ɑː/ vs /ɔː/",
                    examType = "WAEC",
                    year = 2023,
                    question = "Pick the word with the SAME vowel sound as 'cl<u>er</u>k' (/ɑː/ in British RP):",
                    options = listOf("work", "heart", "bird", "early"),
                    correctIndex = 1,
                    hint = "British RP pronounces 'clerk' to rhyme with 'dark' and 'heart'!",
                    stepByStepSolution = "'Clerk' and 'heart' share long back vowel /ɑː/ (work/bird/early use /ɜː/).",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_12_3",
                    topic = "Diphthong /əʊ/",
                    examType = "JAMB",
                    year = 2025,
                    question = "Choose the word that matches the diphthong in 'g<u>o</u>' (/əʊ/):",
                    options = listOf("do", "sew", "saw", "now"),
                    correctIndex = 1,
                    hint = "'Sew' a shirt sounds identical to 'so'!",
                    stepByStepSolution = "'Sew' /səʊ/ shares /əʊ/ with 'go' ('do'=/uː/, 'saw'=/ɔː/, 'now'=/aʊ/).",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_12_4",
                    topic = "Odd Vowel Out",
                    examType = "WAEC",
                    year = 2022,
                    question = "Which word has the SAME vowel sound as 'b<u>u</u>ry' (/e/)?",
                    options = listOf("fury", "friend", "hurry", "burn"),
                    correctIndex = 1,
                    hint = "'Bury' sounds identical to 'berry' (/e/)!",
                    stepByStepSolution = "Both 'bury' and 'friend' contain short front vowel /e/.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_12_5",
                    topic = "Pure Vowel /uː/ vs /ʊ/",
                    examType = "JAMB",
                    year = 2021,
                    question = "Which word contains the short vowel /ʊ/ as in 'book'?",
                    options = listOf("food", "fool", "could", "rude"),
                    correctIndex = 2,
                    hint = "Compare short 'could/put/wolf' against stretched 'food/fool/rude'.",
                    stepByStepSolution = "'Could' uses short /ʊ/; food, fool, rude all use long /uː/.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_12_6",
                    topic = "Diphthong /eə/",
                    examType = "WAEC",
                    year = 2024,
                    question = "Which word shares the vowel sound of 'h<u>eir</u>' (/eə/)?",
                    options = listOf("here", "bear", "fear", "clear"),
                    correctIndex = 1,
                    hint = "'Heir' sounds like 'air' and 'bear'!",
                    stepByStepSolution = "'Heir' and 'bear' share /eə/ ('here, fear, clear' use /ɪə/).",
                  ),
                ),
            ),
          ),
      ),
    )
}
