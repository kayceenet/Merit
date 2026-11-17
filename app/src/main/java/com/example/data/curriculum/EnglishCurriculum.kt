package com.example.data.curriculum

object EnglishCurriculum {

  val track: SubjectTrack by lazy {
    SubjectTrack(
      id = "english",
      title = "Use of English Specialization",
      shortName = "English",
      subtitle = "15 Zero-to-A1 lessons covering Clauses, Concord, Idioms, Registers, Oral Stress/IPA & Comprehension",
      modules = module1And2 + EnglishCurriculumPart2.module3And4 + listOf(EnglishCurriculumPart3.module5),
      pastQuestions = EnglishCurriculumPart3.pastQuestions,
    )
  }

  val module1And2: List<CourseModule> =
    listOf(
      CourseModule(
        id = "eng_mod_1",
        subjectId = "english",
        indexLabel = "Module 01",
        title = "Grammar Foundations: Word Classes, Phrases, Clauses & Sentence Craft",
        summary = "Start from zero: word building (prefixes/suffixes), WAEC clause & grammatical function identification, sentence types & dangling modifiers.",
        lessons =
          listOf(
            Lesson(
              id = "eng_01_parts_affixes",
              moduleId = "eng_mod_1",
              subjectId = "english",
              title = "Parts of Speech, Determiners & Word-Building Affixes",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. A1 • WAEC Lexis & Word Classes",
              learningOutcomes =
                listOf(
                  "Identify word class by HOW a word acts in a sentence rather than memorizing dictionary labels.",
                  "Pick correct Adjective Order & Countable vs Uncountable Determiners ('fewer/many' vs 'less/much').",
                  "Predict meanings & negatives cleanly using prefixes (un-, dis-, mis-, in-/im-/il-/ir-) and suffixes.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. A Word's Job Decides Its Part of Speech!",
                    explanation =
                      "Look at the word 'FAST': 'A fast car' (Adjective describing noun), 'She runs fast' (Adverb modifying verb), 'They fast during Ramadan' (Verb action), 'Break your fast' (Noun thing)! Same spelling, four jobs! Nouns name things, Pronouns replace nouns, Verbs show action/being, Adjectives decorate Nouns, and Adverbs spice up Verbs, Adjectives, or other Adverbs!",
                    keyFormula = "\\text{Countable Plurals: few / many / number}   |   \\text{Uncountable Mass: little / much / amount (information, furniture, luggage!)}",
                    examTip =
                      "TRAP ALERT: 'Information', 'furniture', 'luggage', 'equipment', 'advice', and 'news' are NEVER pluralized with '-s'—always say 'a piece of advice', NEVER 'advices'!",
                  ),
                  ConceptBlock(
                    heading = "2. Quantifiers & Pronoun Case Tricks",
                    explanation =
                      "When sharing between TWO items: use 'between', 'each other', and comparative '-er' (taller). With THREE OR MORE: use 'among', 'one another', and superlative '-est' (tallest)! For pronouns: remove the friend's name to hear the right pronoun—'She gave [my brother and] ME a book', NOT 'I'!",
                    keyFormula = "\\text{Subject: I, he, she, they, who}   |   \\text{Object (after verb/prep): me, him, her, them, whom}",
                    examTip =
                      "'Between you and ME' is ALWAYS right (because 'between' is a preposition taking object 'me', never 'between you and I'!).",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Solving Uncountable Noun & Determiner Traps",
                    examSource = "JAMB UTME 2024 • Use of English Q3",
                    problemStatement = "Choose the correct sentence: (A) She gave me many informations. (B) She gave me much useful information.",
                    steps =
                      listOf(
                        "Step 1 — 'Information' is an uncountable abstract noun: it NEVER takes plural '-s' or 'many'.",
                        "Step 2 — Pair uncountable nouns with 'much', 'a great deal of', or 'items/pieces of'.",
                      ),
                    finalAnswer = "(B) She gave me much useful information.",
                    examinerTrap = "'Staffs', 'equipments', 'luggages', and 'cutleries' are classic non-standard plural traps!",
                  ),
                  WorkedExample(
                    title = "Testing Subject vs Object Pronouns",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q8",
                    problemStatement = "Fill the gap: 'The principal scolded Ada and ____ for arriving late.' (I / me)",
                    steps =
                      listOf(
                        "Step 1 — Cover 'Ada and': would you say 'The principal scolded I'? NO!",
                        "Step 2 — You say 'scolded me' (direct object of 'scolded'). Therefore: 'Ada and me'.",
                      ),
                    finalAnswer = "me",
                    examinerTrap = "Hypercorrection makes students pick 'I' blindly after 'and'—always drop the other name to test!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Proofreading Scholarship & Visa Applications",
                    industryDomain = "Academic Communications",
                    narrative =
                      "Admissions committees instantly spot non-standard uncountable plurals like 'rich experiences and informations' or 'between you and I'.",
                    examConnection = "Covers 8–10 Lexis & Structure marks in every JAMB English sitting.",
                  ),
                ),
              labType = PracticalLabType.PARTS_CLAUSES_LAB,
              labBrief =
                "Inspect tricky determiners, uncountable nouns, pronoun-case tests & negative prefixes step-by-step interactively!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_01_1",
                    topic = "Uncountable Nouns",
                    examType = "JAMB",
                    year = 2024,
                    question = "The fire gutted the laboratory and destroyed all the expensive ____.",
                    options = listOf("equipments", "equipment", "an equipment", "machineries"),
                    correctIndex = 1,
                    hint = "'Equipment' and 'machinery' are mass nouns without '-s'.",
                    stepByStepSolution = "'Equipment' stays singular in form even when referring to multiple apparatus.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_01_2",
                    topic = "Pronoun Case",
                    examType = "WAEC",
                    year = 2023,
                    question = "This secret must remain strictly between you and ____.",
                    options = listOf("I", "myself", "me", "we"),
                    correctIndex = 2,
                    hint = "'Between' is a preposition requiring object case.",
                    stepByStepSolution = "Prepositions govern object pronouns: 'between you and me'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_01_3",
                    topic = "Countable vs Mass Quantifiers",
                    examType = "JAMB",
                    year = 2025,
                    question = "This term, ____ students registered for Extra Maths than last year.",
                    options = listOf("less", "fewer", "little", "much"),
                    correctIndex = 1,
                    hint = "Can you count students (1, 2, 3)? Then use 'fewer', not 'less'!",
                    stepByStepSolution = "Use 'fewer' with countable plurals and 'less' with uncountable quantities.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_01_4",
                    topic = "Reciprocal Pronouns",
                    examType = "WAEC",
                    year = 2022,
                    question = "All twelve players congratulated ____ after lifting the trophy.",
                    options = listOf("each other", "one another", "themselves", "theirself"),
                    correctIndex = 1,
                    hint = "Two → each other; three or more → one another.",
                    stepByStepSolution = "Twelve players (> 2) take 'one another'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_01_5",
                    topic = "Comparative vs Superlative",
                    examType = "JAMB",
                    year = 2021,
                    question = "Of the twin sisters, Tolu is definitely the ____.",
                    options = listOf("smartest", "smarter", "most smart", "more smartest"),
                    correctIndex = 1,
                    hint = "Twins = TWO people → comparative '-er', NOT superlative '-est'!",
                    stepByStepSolution = "Comparing two items requires comparative form ('the smarter').",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_01_6",
                    topic = "Negative Prefix",
                    examType = "WAEC",
                    year = 2024,
                    question = "The word 'INFLAMMABLE' actually means:",
                    options = listOf("Impossible to burn", "Fireproof", "Easily catching fire", "Extinguished"),
                    correctIndex = 2,
                    hint = "Here 'in-' means 'into flames' (like 'enflame'), NOT 'not'!",
                    stepByStepSolution = "Both 'flammable' and 'inflammable' mean combustible; the opposite is 'non-flammable'.",
                  ),
                ),
            ),

            Lesson(
              id = "eng_02_clauses_functions",
              moduleId = "eng_mod_1",
              subjectId = "english",
              title = "Phrases, Clauses & WAEC Grammatical Functions",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. A2 • WAEC Comprehension Grammatical Name & Function",
              learningOutcomes =
                listOf(
                  "Tell Phrase (NO finite verb!) apart from Clause (HAS Subject + Finite Verb!) instantly.",
                  "Identify Noun Clauses, Adjectival (Relative) Clauses & Adverbial Clauses accurately in WAEC comprehension.",
                  "State exact 2-part WAEC Grammatical Name + Grammatical Function phrasing for full marks.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Phrase vs Clause: Check for the Heartbeat (Finite Verb!)",
                    explanation =
                      "A Phrase is a team of words WITHOUT a Subject + Tense-carrying Verb (e.g., 'in the pouring rain', 'to win the race'). A Clause HAS a Subject doing an active Verb with tense (e.g., 'WHEN the bell rang'). Every WAEC Comprehension paper asks two questions on an underlined part: (i) What is its grammatical NAME? (ii) What is its FUNCTION?",
                    keyFormula = "\\text{No Tense Verb} \\implies \\text{PHRASE}   |   \\text{Subject + Tense Verb} \\implies \\text{CLAUSE}",
                    examTip =
                      "Never guess by starting word alone! Look at HOW IT ACTS: (1) Replace whole group with 'IT' → Noun Clause! (2) Sitting right behind a Noun saying 'which one?' → Adjectival Clause! (3) Telling HOW, WHEN, WHERE, or WHY the main verb happened → Adverbial Clause!",
                  ),
                  ConceptBlock(
                    heading = "2. Exact WAEC Full-Mark Answers for Grammatical Function",
                    explanation =
                      "Noun Clause functions as 'Subject of the verb ...' or 'Object of the verb ...'! Adjectival Clause ALWAYS 'modifies/qualifies the noun ...'! Adverbial Clause ALWAYS 'modifies the verb ...'! Pro tip: 'THAT he passed shocked everyone' → replace underlined with 'IT' ('IT shocked everyone') → Noun Clause, Subject of verb 'shocked'!",
                    keyFormula = "\\text{Noun Clause} \\to \\text{Subj/Obj of verb}   |   \\text{Adjectival} \\to \\text{Qualifies noun}   |   \\text{Adverbial} \\to \\text{Modifies verb}",
                    examTip =
                      "Notice the subtle difference: Adjectival 'qualifies' a Noun; Adverbial 'modifies' a Verb!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Distinguishing Same-Word Clause Jobs ('where she hid')",
                    examSource = "WAEC SSCE 2024 • Comprehension Paper 2 Q4(g)",
                    problemStatement =
                      "Contrast the underlined clause '...where she hid the keys' in: (1) 'I know WHERE SHE HID THE KEYS.' vs (2) 'The drawer WHERE SHE HID THE KEYS was locked.'",
                    steps =
                      listOf(
                        "Step 1 — In (1), swap with 'IT': 'I know IT' makes full sense! → NOUN CLAUSE, Object of the verb 'know'.",
                        "Step 2 — In (2), it sits right after noun 'drawer' telling which drawer → ADJECTIVAL (Relative) CLAUSE, qualifying the noun 'drawer'.",
                      ),
                    finalAnswer = "(1) Noun Clause (Obj of 'know')  |  (2) Adjectival Clause (Qualifies 'drawer')",
                    examinerTrap = "Assuming every 'where/when' clause is automatically Adverbial—always test 'IT' and check for a preceding Noun!",
                  ),
                  WorkedExample(
                    title = "Identifying Prepositional vs Participial Phrase",
                    examSource = "JAMB UTME 2023 • Use of English Q11",
                    problemStatement = "What grammatical structure is 'ON THE WOODEN TABLE' in 'The keys on the wooden table vanished'?",
                    steps =
                      listOf(
                        "Step 1 — Zero finite verb inside 'on the wooden table' ⇒ Phrase (not Clause!).",
                        "Step 2 — Starts with preposition 'on' and tells WHICH keys ⇒ Prepositional Phrase acting adjectivally (modifying 'keys').",
                      ),
                    finalAnswer = "Prepositional Phrase modifying noun 'keys'",
                    examinerTrap = "Writing 'Clause' when there is no finite verb loses both marks immediately!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Legal Contract Precision & Reading Comprehension Mastery",
                    industryDomain = "Law & Journalism",
                    narrative =
                      "Misreading whether a 'which/who' clause restricts a single noun or adds an optional comma side-note has decided multimillion-dollar contract lawsuits.",
                    examConnection = "Zero ambiguity between Restrictive ('that') and Non-restrictive (comma ', which') relative clauses.",
                  ),
                ),
              labType = PracticalLabType.PARTS_CLAUSES_LAB,
              labBrief =
                "Click highlighted phrases & clauses in live WAEC passages to test the 'IT-swap' rule and inspect exact WAEC Marking-Scheme Name + Function answers!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_02_1",
                    topic = "Noun Clause Subject",
                    examType = "WAEC",
                    year = 2024,
                    question = "In 'WHAT THE WITNESS SAID surprised the judge', the capitalized expression is a:",
                    options = listOf("Adjectival clause", "Noun clause (Subject of 'surprised')", "Adverbial clause of reason", "Prepositional phrase"),
                    correctIndex = 1,
                    hint = "Replace 'WHAT THE WITNESS SAID' with 'IT': 'IT surprised the judge'.",
                    stepByStepSolution = "Contains finite verb 'said' and acts as Subject of 'surprised' → Noun Clause.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_02_2",
                    topic = "Adjectival Clause",
                    examType = "JAMB",
                    year = 2023,
                    question = "Identify the italicized group in: 'The engineer WHO DESIGNED THE BRIDGE won an award.'",
                    options = listOf("Noun clause", "Adjectival (Relative) clause qualifying 'engineer'", "Adverbial phrase", "Gerund phrase"),
                    correctIndex = 1,
                    hint = "Follows and specifies the noun 'engineer'.",
                    stepByStepSolution = "Has finite verb 'designed' and qualifies noun 'engineer' → Adjectival clause.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_02_3",
                    topic = "Adverbial Clause of Concession",
                    examType = "WAEC",
                    year = 2025,
                    question = "In 'ALTHOUGH IT RAINED HEAVILY, the match continued', the capitalized clause is an Adverbial Clause of:",
                    options = listOf("Time", "Condition", "Concession (modifying 'continued')", "Result"),
                    correctIndex = 2,
                    hint = "'Although/Though/Even though' introduces unexpected contrast = Concession.",
                    stepByStepSolution = "Introduced by 'although', modifying the main verb 'continued' → Adverbial Clause of Concession.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_02_4",
                    topic = "Phrase vs Clause",
                    examType = "JAMB",
                    year = 2022,
                    question = "Which highlighted group is a PHRASE rather than a clause?",
                    options = listOf("When she arrived", "Because he studied", "After the heavy downpour", "That you won"),
                    correctIndex = 2,
                    hint = "Look for the option with NO finite verb!",
                    stepByStepSolution = "'After the heavy downpour' has only preposition + noun group, zero finite verb.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_02_5",
                    topic = "Adverbial Clause of Condition",
                    examType = "WAEC",
                    year = 2021,
                    question = "In 'UNLESS YOU HURRY, we shall miss the bus', what is the grammatical function of the capitalized clause?",
                    options = listOf("Qualifies 'bus'", "Modifies the verb 'shall miss'", "Object of 'miss'", "Subject of 'we'"),
                    correctIndex = 1,
                    hint = "Condition adverbial clause modifies the main clause verb.",
                    stepByStepSolution = "Adverbial clause of condition modifying 'shall miss'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_02_6",
                    topic = "Object of Preposition",
                    examType = "JAMB",
                    year = 2024,
                    question = "In 'Success depends on HOW HARD YOU WORK', 'HOW HARD YOU WORK' is a:",
                    options = listOf("Noun clause (Complement of preposition 'on')", "Adverbial clause of manner", "Adjectival clause", "Verb phrase"),
                    correctIndex = 0,
                    hint = "Try 'Success depends on IT'!",
                    stepByStepSolution = "Replaces noun 'effort' directly after preposition 'on' → Noun clause, object of preposition 'on'.",
                  ),
                ),
            ),

            Lesson(
              id = "eng_03_sentences_modifiers",
              moduleId = "eng_mod_1",
              subjectId = "english",
              title = "Sentence Types, Relative Pronouns & Dangling Modifiers",
              durationMinutes = 19,
              syllabusTag = "JAMB Sec. A3 • WAEC Sentence Structure & Style",
              learningOutcomes =
                listOf(
                  "Classify Simple, Compound (FANBOYS), Complex (Main + Subordinate), and Compound-Complex sentences.",
                  "Spot & repair Dangling Participle Modifiers ('Walking to school, a snake bit him' — who walked?!).",
                  "Choose cleanly between who / whom / whose / which / that.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Simple, Compound (FANBOYS) & Complex Building Blocks",
                    explanation =
                      "1 Independent Clause = SIMPLE ('Chidi laughed.'). 2 Independent equal clauses glued with a FANBOYS word (For, And, Nor, But, Or, Yet, So) = COMPOUND ('Chidi laughed, AND Ada cheered.'). 1 Boss Main Clause + 1+ dependent Subordinate Clauses ('because', 'although', 'if', 'which') = COMPLEX!",
                    keyFormula = "\\text{Compound: FANBOYS (For, And, Nor, But, Or, Yet, So)}   |   \\text{Complex: Because / Although / Which / If}",
                    examTip =
                      "Joining two full sentences with ONLY a comma ('I woke up, I brushed') is a Comma Splice error—use a semicolon (;), full stop, or add ', and'!",
                  ),
                  ConceptBlock(
                    heading = "2. Fixing Hilarious Dangling Modifiers!",
                    explanation =
                      "Read carefully: 'Running down the stairs, my bag fell off!' Wait—did your BACKPACK sprout legs and run down the stairs?! When a sentence starts with an '-ing' or '-ed' opening phrase, the VERY FIRST word right after the comma MUST be the person who did that action: 'Running down the stairs, I dropped my bag!'",
                    keyFormula = "\\text{Participle Opening, [Actual Doer!] ...}   |   \\text{Possessive Whose} \\neq \\text{Who's (Who is)}",
                    examTip =
                      "'The dog which barked' / 'The dog that barked' (things/animals); 'The boy who won' (people). Never write 'The boy which won'!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Repairing a Dangling Modifier",
                    examSource = "JAMB UTME 2024 • Use of English Q15",
                    problemStatement =
                      "Spot why 'Driving past the stadium, the scoreboard caught my eye' is faulty and pick the grammatically sound fix.",
                    steps =
                      listOf(
                        "Step 1 — 'The scoreboard' sits immediately after the comma, wrongly implying the scoreboard was driving a car!",
                        "Step 2 — Put 'I' (the driver) directly after the comma: 'Driving past the stadium, I noticed the scoreboard.'",
                      ),
                    finalAnswer = "Driving past the stadium, I noticed the scoreboard.",
                    examinerTrap = "The faulty sentence sounds fine in casual speech—always check WHO performs the opening '-ing' verb!",
                  ),
                  WorkedExample(
                    title = "Selecting Between Who and Whom Quickly",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q12",
                    problemStatement = "Fill in: 'The candidate ____ the panel interviewed impressed everyone.' (who / whom)",
                    steps =
                      listOf(
                        "Step 1 — Turn the relative part around: 'The panel interviewed ____.' → 'interviewed HIM/HER' (Object!).",
                        "Step 2 — Him/Her = Object ⇒ 'whom'!",
                      ),
                    finalAnswer = "whom",
                    examinerTrap = "HE = who; HIM = whom. Even simpler: 'who' is ALWAYS followed closely by its own verb ('who WON'), while 'whom' meets another subject ('whom WE met')!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Headline Clarity in Journalism",
                    industryDomain = "Editorial Publishing",
                    narrative =
                      "Newspaper copy editors ruthlessly hunt misplaced modifiers like 'Police arrested suspect with stolen laptop wearing pyjamas' (who wore the pyjamas—the laptop?!).",
                    examConnection = "Places modifiers directly next to the exact noun they describe.",
                  ),
                ),
              labType = PracticalLabType.PARTS_CLAUSES_LAB,
              labBrief =
                "Test FANBOYS Compound joins vs Complex subordinators and fix hilarious Dangling Participle traps interactively!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_03_1",
                    topic = "Dangling Modifier",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which sentence is grammatically FREE of a dangling modifier?",
                    options = listOf(
                      "Having finished dinner, the dishes were washed.",
                      "Walking into the hall, a loud cheer greeted the winner.",
                      "Having read the letter, she smiled happily.",
                      "Flying over Lagos, the lagoon looked sparkling.",
                    ),
                    correctIndex = 2,
                    hint = "Who finished dinner / walked / flew? Make sure THAT person appears right after the comma!",
                    stepByStepSolution = "In option C, 'she' both read the letter and smiled happily.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_03_2",
                    topic = "Relative Pronouns",
                    examType = "WAEC",
                    year = 2023,
                    question = "That is the architect ____ bridge won the national design prize.",
                    options = listOf("who's", "whose", "which", "whom"),
                    correctIndex = 1,
                    hint = "Shows possession ('his/her bridge').",
                    stepByStepSolution = "'Whose' is the possessive relative pronoun (whereas 'who's' means 'who is').",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_03_3",
                    topic = "Sentence Types",
                    examType = "JAMB",
                    year = 2025,
                    question = "'She revised her notes thoroughly, yet she still missed the deadline.' This sentence is:",
                    options = listOf("Simple", "Compound", "Complex", "Fragment"),
                    correctIndex = 1,
                    hint = "Two independent clauses joined by FANBOYS conjunction 'yet'.",
                    stepByStepSolution = "Coordinating conjunction 'yet' joins two complete main clauses → Compound sentence.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_03_4",
                    topic = "Parallelism",
                    examType = "WAEC",
                    year = 2022,
                    question = "Complete cleanly: 'Our coach values punctuality, teamwork, and ____.'",
                    options = listOf("to be honest", "honesty", "being honest always", "honestly"),
                    correctIndex = 1,
                    hint = "Noun, Noun, ... keep the list rhythm identical!",
                    stepByStepSolution = "Parallel structure aligns nouns: punctuality, teamwork, and honesty.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_03_5",
                    topic = "Comma Splice",
                    examType = "JAMB",
                    year = 2021,
                    question = "How should you fix the comma splice: 'The rain stopped, the sun appeared.'?",
                    options = listOf("Leave as is", "Change comma to semicolon (;) or add 'and'", "Remove 'the'", "Add another comma"),
                    correctIndex = 1,
                    hint = "A bare comma cannot glue two independent sentences.",
                    stepByStepSolution = "Use a semicolon, period, or comma + coordinating conjunction.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_03_6",
                    topic = "Correlative Conjunctions",
                    examType = "WAEC",
                    year = 2024,
                    question = "'Scarcely had the teacher entered ____ the noise ceased.'",
                    options = listOf("than", "when", "then", "since"),
                    correctIndex = 1,
                    hint = "'No sooner ... THAN', but 'Hardly / Scarcely ... WHEN'!",
                    stepByStepSolution = "Fixed correlative pair: Scarcely / Hardly ... when.",
                  ),
                ),
            ),
          ),
      ),

      CourseModule(
        id = "eng_mod_2",
        subjectId = "english",
        indexLabel = "Module 02",
        title = "Tenses, Subjunctives, Concord, Question Tags & Punctuation",
        summary = "Eliminate every grammar trap in JAMB & WAEC: 12 tenses, Conditionals, 'It is high time + PAST', Concord & Question Tags.",
        lessons =
          listOf(
            Lesson(
              id = "eng_04_tenses_conditionals",
              moduleId = "eng_mod_2",
              subjectId = "english",
              title = "Tenses Mastery & The 4 Conditional Clauses",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. A4 • WAEC Sequence of Tenses & Conditionals",
              learningOutcomes =
                listOf(
                  "Select Simple Past vs Present Perfect vs Past Perfect ('earlier of two past events') accurately.",
                  "Apply Zero, First, Second ('If I WERE you...'), and Third ('If he HAD studied, he WOULD HAVE passed') Conditionals.",
                  "Recognize inverted Conditionals ('Had he known...' = 'If he had known...').",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Past Perfect ('Had + Done') vs Present Perfect ('Has/Have + Done')",
                    explanation =
                      "Whenever TWO things happened in the past, the ONE THAT HAPPENED FIRST wears 'HAD + Past Participle' (Past Perfect), and the second wears Simple Past! Example: 'When we reached the bus stop, the bus HAD already left' (Leaving happened BEFORE reaching!). Note: Present Perfect ('has eaten') NEVER pairs with a finished yesterday timestamp like 'yesterday' or 'in 2020'—use Simple Past ('ate') for finished past dates!",
                    keyFormula = "\\text{1st Past Event: had } + V_3   |   \\text{2nd Past Event: } V_2\\; |\\; \\text{Since/For (still continuing): has/have } + V_3",
                    examTip =
                      "Watch out for irregular V₃ past participles: Go/Went/GONE; Sing/Sang/SUNG; Beat/Beat/BEATEN; Lie(down)/Lay/LAIN; Lay(put)/Laid/LAID; Hang(picture)/HUNG vs Hang(criminal)/HANGED!",
                  ),
                  ConceptBlock(
                    heading = "2. The 3 Famous Conditional Equations (Lock the Matching Pairs!)",
                    explanation =
                      "Treat English Conditionals like balancing algebra equations: Type 1 (Real Future): If + Present → WILL ('If it rains, I will stay'). Type 2 (Imaginary Now): If + Past ('WERE'!) → WOULD ('If I WERE the president, I would build labs'). Type 3 (Regret Over Past—cannot change!): If + HAD + V₃ → WOULD HAVE + V₃!",
                    keyFormula = "\\text{1: If } V_1 \\to \\text{will } V_1   |   \\text{2: If } V_2/\\text{were} \\to \\text{would } V_1   |   \\text{3: If had } V_3 \\to \\text{would have } V_3",
                    examTip =
                      "JAMB loves hiding 'If' via inversion: 'HAD I known, I would NOT have gone' (means 'If I had known...')!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Third Conditional Inverted Clause",
                    examSource = "JAMB UTME 2024 • Use of English Q18",
                    problemStatement = "Complete: 'Had the goalie dived a second earlier, he ____ the penalty.'",
                    steps =
                      listOf(
                        "Step 1 — 'Had the goalie dived' = 'If the goalie had dived' (Type 3 Past Regret).",
                        "Step 2 — Pair with 'would have + V₃' ⇒ 'would have saved'.",
                      ),
                    finalAnswer = "would have saved",
                    examinerTrap = "Picking 'would save' (Type 2) instead of 'would have saved' (Type 3)!",
                  ),
                  WorkedExample(
                    title = "Fixed Past Timestamp vs Ongoing Action",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q16",
                    problemStatement = "Choose right tense: 'My uncle ____ to Abuja two weeks ago.' (has travelled / travelled)",
                    steps =
                      listOf(
                        "Step 1 — 'two weeks ago' pins a finished dead-and-gone time in the past.",
                        "Step 2 — Finished past timestamps forbid Present Perfect ('has travelled') → use Simple Past 'travelled'.",
                      ),
                    finalAnswer = "travelled",
                    examinerTrap = "Saying 'has travelled yesterday' is a top Nigerian English & JAMB trap!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Historical Timelines & Project Reporting",
                    industryDomain = "Business & History Reporting",
                    narrative =
                      "Mixing Simple Past with Past Perfect keeps chronological order crystal clear in medical charts ('The patient had collapsed before paramedics arrived').",
                    examConnection = "Also note 'used to + base verb' (past habit) vs 'is used to + -ing verb' (accustomed now!).",
                  ),
                ),
              labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
              labBrief =
                "Test Type 1/2/3 Conditional pairs, flip 'Had I known...' inversions, and check irregular verb triplets live!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_04_1",
                    topic = "Conditional Type 2",
                    examType = "JAMB",
                    year = 2024,
                    question = "If I ____ you, I would accept that scholarship immediately.",
                    options = listOf("am", "was", "were", "had been"),
                    correctIndex = 2,
                    hint = "Hypothetical unreal advice takes plural subjunctive 'were' even for 'I/he/she'!",
                    stepByStepSolution = "Type 2 hypothetical condition uses 'were' + 'would accept'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_04_2",
                    topic = "Past Perfect Order",
                    examType = "WAEC",
                    year = 2023,
                    question = "By the time the firefighters arrived, the blaze ____ three shops.",
                    options = listOf("destroyed", "has destroyed", "had destroyed", "was destroying"),
                    correctIndex = 2,
                    hint = "Destruction occurred BEFORE the firefighters arrived.",
                    stepByStepSolution = "Earlier past action requires Past Perfect 'had destroyed'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_04_3",
                    topic = "Accustomed Structure",
                    examType = "JAMB",
                    year = 2025,
                    question = "Living near the railway, my grandmother is now used to ____ train whistles at night.",
                    options = listOf("hear", "hearing", "heard", "hears"),
                    correctIndex = 1,
                    hint = "'BE used to' treats 'to' as a preposition → takes gerund '-ing'!",
                    stepByStepSolution = "'is used to + noun/gerund (-ing)' means accustomed to.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_04_4",
                    topic = "Type 3 Conditional",
                    examType = "WAEC",
                    year = 2022,
                    question = "If Ada had checked the timetable carefully, she ____ the first train.",
                    options = listOf("would catch", "will catch", "would not have missed", "did not miss"),
                    correctIndex = 2,
                    hint = "If + had V₃ pairs ONLY with would (not) have + V₃.",
                    stepByStepSolution = "Type 3 past unreal pairs past perfect with 'would not have missed'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_04_5",
                    topic = "Since vs For + Perfect",
                    examType = "JAMB",
                    year = 2021,
                    question = "Mr. Balogun ____ Biology in this college since 2016.",
                    options = listOf("is teaching", "taught", "has been teaching", "teaches"),
                    correctIndex = 2,
                    hint = "Started in 2016 and STILL continuing up to now!",
                    stepByStepSolution = "Unbroken duration up to present uses Present Perfect Continuous ('has been teaching').",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_04_6",
                    topic = "Irregular Verbs",
                    examType = "WAEC",
                    year = 2024,
                    question = "The armed robber was sentenced to death and ____ at dawn.",
                    options = listOf("hung", "hanged", "hangs", "hanging"),
                    correctIndex = 1,
                    hint = "Pictures are HUNG on walls; condemned people are HANGED!",
                    stepByStepSolution = "Standard English uses 'hanged' specifically for execution.",
                  ),
                ),
            ),

            Lesson(
              id = "eng_05_subjunctive_passive",
              moduleId = "eng_mod_2",
              subjectId = "english",
              title = "Subjunctive ('High Time'), Causatives & Reported Speech",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. A5 • WAEC Mood, Voice & Direct/Indirect Speech",
              learningOutcomes =
                listOf(
                  "Master the #1 JAMB secret: 'It is high time / I would rather / I wish' + SIMPLE PAST tense!",
                  "Distinguish Mandative Subjunctive ('I insist that he GO / BE there') and Causative 'have something DONE'.",
                  "Backshift tenses, pronouns & time adverbs ('now → then', 'tomorrow → the following day') in Reported Speech.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. The Crazy Time-Warp Rule ('It is high time we LEFT!')",
                    explanation =
                      "Even though you mean NOW or SOON, four urgent phrases force the verb into SIMPLE PAST tense: 'It is high time...', 'It is about time...', 'I would rather you...', and 'I wish I...'! Why? Because you're imagining something overdue or unreal—so say 'It is high time we WENT home' (NEVER 'go' or 'should go')!",
                    keyFormula = "\\text{It is (high) time + Subj + } V_2\\; |\\; \\text{Would rather + Subj + } V_2\\; |\\; \\text{Insist/Suggest that + Subj + } V_{\\text{base}}",
                    examTip =
                      "Notice the difference when there is NO second subject: 'I'd rather STAY' (bare verb!), vs 'I'd rather YOU STAYED' (past tense V₂)!",
                  ),
                  ConceptBlock(
                    heading = "2. Passive Voice & Reported Speech Backshifting",
                    explanation =
                      "Active focuses on DOER ('Ada broke the vase'); Passive promotes RECEIVER ('The vase WAS BROKEN by Ada'—always BE-verb + V₃!). Watch Reported Speech: when the reporting verb is past ('He SAID that...'), step every verb ONE notch backward: Present → Past; Will → Would; Can → Could; 'yesterday' → 'the previous day'! Exception: eternal facts ('The Earth orbits the Sun') stay Present!",
                    keyFormula = "\\text{Causative: Have / Get + Object + } V_3\\; (\\text{'have my hair CUT'})   |   \\text{Look forward to + } V\\text{-ing!}",
                    examTip =
                      "Verb phrases ending in preposition 'to' take '-ing': 'look forward to SEEING you', 'committed to HELPING', 'object to PAYING'!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "High Time & Would Rather Trap",
                    examSource = "JAMB UTME 2024 • Use of English Q22",
                    problemStatement = "Complete: 'It is high time the committee ____ a final decision.'",
                    steps =
                      listOf(
                        "Step 1 — Recognise urgent subjunctive trigger 'It is high time + Subject (the committee)'.",
                        "Step 2 — Must take Simple Past tense V₂ ⇒ 'took' / 'made' (NOT 'takes', 'take', or 'has made').",
                      ),
                    finalAnswer = "made / took",
                    examinerTrap = "Choosing Present Simple 'makes'—the most missed question pattern in JAMB English!",
                  ),
                  WorkedExample(
                    title = "Reported Question Word Order Flip",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q21",
                    problemStatement = "Convert to Indirect Speech: He asked me, 'Where do you live?'",
                    steps =
                      listOf(
                        "Step 1 — In indirect reported questions, drop 'do/did' and revert to normal Statement order (Subject BEFORE Verb!).",
                        "Step 2 — Backshift 'live' → 'lived', 'you' → 'I' ⇒ 'He asked me where I lived.' (No question mark!).",
                      ),
                    finalAnswer = "He asked me where I lived.",
                    examinerTrap = "Never write 'He asked me where did I live?' in Indirect Speech!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Formal Board Resolutions & News Reporting",
                    industryDomain = "Corporate Governance & Media",
                    narrative =
                      "Meeting minutes preserve Mandative Subjunctive ('The board moved that the treasurer SUBMIT an audit') while journalists backshift quotes accurately.",
                    examConnection = "Explains why 'moved that he submit' (no '-s'!) is 100% standard grammar.",
                  ),
                ),
              labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
              labBrief =
                "Test 'It is high time / I'd rather' tense triggers, Causative passive repairs, and Direct → Indirect speech backshifts live!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_05_1",
                    topic = "High Time Structure",
                    examType = "JAMB",
                    year = 2024,
                    question = "It is high time you ____ blaming your roommates for your grades.",
                    options = listOf("stop", "stopped", "will stop", "have stopped"),
                    correctIndex = 1,
                    hint = "'It is high time + Subject' requires Simple Past V₂!",
                    stepByStepSolution = "'It is high time you stopped...' expresses overdue present necessity using past tense form.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_05_2",
                    topic = "To + Gerund Trap",
                    examType = "WAEC",
                    year = 2023,
                    question = "We are eagerly looking forward to ____ your grandparents next holiday.",
                    options = listOf("visit", "visiting", "visited", "be visiting"),
                    correctIndex = 1,
                    hint = "'to' inside 'look forward to' is a preposition, NOT infinitive marker!",
                    stepByStepSolution = "Prepositions take '-ing' gerunds: 'looking forward to visiting'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_05_3",
                    topic = "Causative Have",
                    examType = "JAMB",
                    year = 2025,
                    question = "My phone screen cracked, so I went to Computer Village to have it ____.",
                    options = listOf("repair", "repairing", "repaired", "repairs"),
                    correctIndex = 2,
                    hint = "Have + [Object] + Past Participle V₃ (done by someone else).",
                    stepByStepSolution = "Passive causative: 'have it repaired'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_05_4",
                    topic = "Mandative Subjunctive",
                    examType = "WAEC",
                    year = 2022,
                    question = "The Principal insisted that every prefect ____ present at morning assembly.",
                    options = listOf("is", "was", "be", "were"),
                    correctIndex = 2,
                    hint = "'Insist/Recommend/Demand that + Subject + BARE infinitive (be / go)'.",
                    stepByStepSolution = "Mandative subjunctive uses bare base form 'be' without '-s' or tense inflection.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_05_5",
                    topic = "Reported Speech",
                    examType = "JAMB",
                    year = 2021,
                    question = "Convert: 'I saw your brother here yesterday,' Bola told Emeka.",
                    options = listOf(
                      "Bola told Emeka that she had seen his brother there the previous day.",
                      "Bola told Emeka that she saw his brother here yesterday.",
                      "Bola asked Emeka if she saw his brother there.",
                      "Bola told Emeka she has seen her brother.",
                    ),
                    correctIndex = 0,
                    hint = "saw → had seen; here → there; yesterday → the previous day.",
                    stepByStepSolution = "Full backshift changes tense, place adverb, and time expression consistently.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_05_6",
                    topic = "Passive Continuous",
                    examType = "WAEC",
                    year = 2024,
                    question = "Change to Passive: 'The mechanics are servicing the school bus.'",
                    options = listOf(
                      "The school bus was serviced by mechanics.",
                      "The school bus is being serviced by the mechanics.",
                      "The school bus has been serviced.",
                      "The mechanics are being serviced.",
                    ),
                    correctIndex = 1,
                    hint = "Keep Present Continuous using 'is BEING + V₃'!",
                    stepByStepSolution = "Present Continuous Passive inserts 'being': 'is being serviced'.",
                  ),
                ),
            ),

            Lesson(
              id = "eng_06_concord",
              moduleId = "eng_mod_2",
              subjectId = "english",
              title = "Subject-Verb Agreement (Concord) Mastery",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. A6 • WAEC Concord & Agreement Rules",
              learningOutcomes =
                listOf(
                  "Apply the 12 core JAMB Concord laws without being fooled by intervening phrases.",
                  "Distinguish Parenthetical additions ('as well as / along with') from Proximity ('Neither ... nor').",
                  "Handle 'Each / Every', 'A number of' vs 'The number of', Collective Nouns & 'More than one'.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Ignorable Sidekicks vs Neither/Nor Proximity",
                    explanation =
                      "Spot the difference cleanly! When phrases like 'AS WELL AS', 'ALONG WITH', 'TOGETHER WITH', or 'IN ADDITION TO' appear, put invisible brackets around them and obey ONLY the FIRST noun ('The Governor, along with his commissioners, IS visiting'!). BUT with 'NEITHER...NOR' or 'EITHER...OR', the verb obeys whichever noun sits CLOSER to the blank ('Neither the captain nor the players ARE ready' vs 'Neither the players nor the captain IS ready')!",
                    keyFormula = "S_1\\text{ as well as } S_2 \\implies \\text{Obeys } S_1   |   \\text{Either } S_1 \\text{ or } S_2 \\implies \\text{Obeys } S_2\\text{ (Closer!)}",
                    examTip =
                      "'Each', 'Every', 'Everyone', 'Nobody', 'One of the boys', and 'Many a student' are ALL strictly SINGULAR!",
                  ),
                  ConceptBlock(
                    heading = "2. The 'A Number Of' vs 'The Number Of' & Twin Subjects Trick",
                    explanation =
                      "'A number of students' just means 'Several students' → PLURAL verb ('A number of books ARE missing')! 'THE number of students' talks about ONE specific count → SINGULAR verb ('THE number IS 45')! Also look at 'Bread and butter' or 'Rice and stew': served together as ONE meal = SINGULAR ('Rice and beans IS my favourite')! And watch 'The Principal and Treasurer' (ONLY ONE 'the' = 1 person wearing two hats → SINGULAR) vs 'THE Principal and THE Treasurer' (TWO 'the's = 2 people → PLURAL)!",
                    keyFormula = "\\text{A number of} \\to \\text{Plural (ARE)}   |   \\text{The number of} \\to \\text{Singular (IS)}   |   \\text{One of + Plural Noun} \\to \\text{Singular Verb}",
                    examTip =
                      "Measurements taken as one sum take SINGULAR verb: 'Five million Naira IS huge', 'Ten kilometres IS far to walk'!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Relative Pronoun Inside 'One of the Boys Who...'",
                    examSource = "JAMB UTME 2024 • Use of English Q25",
                    problemStatement =
                      "Compare: (A) 'One of the boys ____ missing.' vs (B) 'She is one of the girls who ____ always early.'",
                    steps =
                      listOf(
                        "Step 1 — In (A), subject is 'One' ⇒ SINGULAR verb ('is' missing).",
                        "Step 2 — In (B), 'who' sits right after plural noun 'girls' (several girls are early, she is one of them) ⇒ PLURAL verb ('are')! ONLY if 'ONLY one of the girls who...' appears does it flip back to singular!",
                      ),
                    finalAnswer = "(A) is  |  (B) are (unless 'the ONLY one' precedes!)",
                    examinerTrap = "The subtle 'One of the [Pl] + who' trap separates 75 from 90+ JAMB scorers!",
                  ),
                  WorkedExample(
                    title = "Along With vs Neither/Nor",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q24",
                    problemStatement = "Choose right verbs: (1) 'The choir leader, together with twenty singers, ____ arrived.' (2) 'Neither Ada nor her friends ____ coming.'",
                    steps =
                      listOf(
                        "Step 1 — 'together with twenty singers' is parenthetical ⇒ obey singular 'leader' = HAS arrived.",
                        "Step 2 — 'Neither ... nor' obeys closer subject 'her friends' = ARE coming.",
                      ),
                    finalAnswer = "(1) has  |  (2) are",
                    examinerTrap = "Hearing the plural word 'singers' right before the gap tricks ears into picking 'have'!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Legal Drafting & Academic Editing",
                    industryDomain = "Journalism & Legal Writing",
                    narrative =
                      "Official bulletins treat companies and committees ('Nigeria IS...', 'The jury HAS reached...') as single institutions when acting together.",
                    examConnection = "Collective nouns take Singular when united, Plural only when acting as split individuals.",
                  ),
                ),
              labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
              labBrief =
                "Switch tricky subjects ('Neither/Nor', 'As well as', 'A/The number of', 'One of the...who') to watch the Concord engine highlight the true controlling noun!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_eng_06_1",
                    topic = "Parenthetical Subject",
                    examType = "JAMB",
                    year = 2024,
                    question = "The Managing Director, as well as his three deputies, ____ travelling tonight.",
                    options = listOf("are", "were", "is", "have been"),
                    correctIndex = 2,
                    hint = "Ignore ', as well as his three deputies,' and match 'The Managing Director'.",
                    stepByStepSolution = "'As well as' does not create a compound plural subject; match singular MD → 'is'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_06_2",
                    topic = "The Number Of vs A Number Of",
                    examType = "WAEC",
                    year = 2023,
                    question = "The number of candidates applying for Medicine this year ____ surprisingly high.",
                    options = listOf("are", "were", "is", "have been"),
                    correctIndex = 2,
                    hint = "'THE number' is one specific statistic.",
                    stepByStepSolution = "'The number of...' takes a singular verb ('is').",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_06_3",
                    topic = "Neither...Nor Proximity",
                    examType = "JAMB",
                    year = 2025,
                    question = "Neither the biology teacher nor the lab assistants ____ responsible for the broken beaker.",
                    options = listOf("was", "is", "were", "has been"),
                    correctIndex = 2,
                    hint = "Match the subject closest to the verb: 'the lab assistants'.",
                    stepByStepSolution = "Proximity concord aligns with plural 'assistants' → 'were'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_06_4",
                    topic = "Distributive Pronouns",
                    examType = "WAEC",
                    year = 2022,
                    question = "Every boy and every girl in the hall ____ given a workbook.",
                    options = listOf("were", "are", "was", "have been"),
                    correctIndex = 2,
                    hint = "'Every ... and every ...' emphasizes individuals → singular!",
                    stepByStepSolution = "Compounds modified by 'each' or 'every' always take singular verbs ('was').",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_06_5",
                    topic = "Single Article Two Roles",
                    examType = "JAMB",
                    year = 2021,
                    question = "My mentor and benefactor ____ arriving at noon today.",
                    options = listOf("are", "were", "is", "have"),
                    correctIndex = 2,
                    hint = "Only ONE determiner ('My') before both titles = ONE person!",
                    stepByStepSolution = "'My mentor and benefactor' refers to one individual → singular 'is'.",
                  ),
                  PracticeQuestion(
                    id = "ex_eng_06_6",
                    topic = "Plural-Form Singular Nouns",
                    examType = "WAEC",
                    year = 2024,
                    question = "Mathematics, alongside Physics and Economics, ____ logical reasoning.",
                    options = listOf("require", "requires", "are requiring", "have required"),
                    correctIndex = 1,
                    hint = "'Mathematics' ends in -s yet is a single academic subject!",
                    stepByStepSolution = "Subject names ending in -ics (Mathematics, Physics, News, Measles) take singular 'requires'.",
                  ),
                ),
            ),
          ),
      ),
    )
}
