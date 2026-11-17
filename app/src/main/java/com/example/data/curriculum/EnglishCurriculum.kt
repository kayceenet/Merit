package com.example.data.curriculum

object EnglishCurriculum {

  val track =
    SubjectTrack(
      id = "english",
      title = "Use of English Mastery",
      shortName = "English",
      tagline = "Grammar, Concord, Lexis, Oral Stress Phonetics, Comprehension & Essay Mechanics",
      examCoverage = "JAMB 60 Compulsory Qs • WAEC Paper 1 (Obj), Paper 2 (Essay/Summary) & Paper 3 (Oral)",
      modules =
        listOf(
          CourseModule(
            id = "eng_mod_1",
            subjectId = "english",
            indexLabel = "Module 01",
            title = "Sentence Architecture, Clauses & Tense Mastery",
            summary = "Build from parts of speech and clause patterns to conditionals, subjunctives, and pronoun case control.",
            lessons =
              listOf(
                Lesson(
                  id = "eng_01_clauses_phrases",
                  moduleId = "eng_mod_1",
                  subjectId = "english",
                  title = "Parts of Speech, Clauses, Phrases & Modifiers",
                  durationMinutes = 18,
                  syllabusTag = "JAMB Sec. A.1 • WAEC Grammatical Structures",
                  learningOutcomes =
                    listOf(
                      "Distinguish phrases (no finite verb) from noun, adjectival (relative), and adverbial clauses.",
                      "Eliminate dangling participles, faulty parallelism, and wrong 'who' vs 'whom' pronoun cases.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1 (From Scratch): Phrase vs Clause Check",
                        explanation =
                          "A PHRASE is a group of words WITHOUT a finite verb (subject + tense-carrying verb), e.g., 'on the wooden table'. A CLAUSE contains a subject + finite verb! Identify dependent clause type by WHAT QUESTION it answers: Noun Clause acts as subject/object ('What she said surprised us'); Adjectival/Relative Clause qualifies a noun ('who won the prize'); Adverbial Clause tells how/when/why/although ('because it rained').",
                        keyFormula = "\\text{Clause = Subject + Finite Verb}   |   \\text{Who = Subject (he)} \\;\\cdot\\; \\text{Whom = Object (him)}",
                        examTip =
                          "WAEC Paper 2 Comprehension ALWAYS asks: 'What grammatical name is given to the underlined expression and what is its function?' Answer cleanly: e.g., 'Adjectival clause; it qualifies the noun student'!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Dangling Modifiers & Pronoun Case",
                        explanation =
                          "Always match an opening participle phrase to the VERY FIRST noun after the comma! Wrong: 'Walking into the lab, the beaker shattered.' (The beaker can't walk!). Right: 'Walking into the lab, Kemi saw the beaker shatter.' After prepositions ('between you and ___', 'to ___'), ALWAYS use Object Pronouns (me, him, her, whom — NEVER 'between you and I'!).",
                        keyFormula = "\\text{After Preposition (to/for/between)} \\Rightarrow \\text{me / him / her / us / whom}",
                        examTip =
                          "JAMB loves testing 'It is high time we ___' and 'between you and ___'!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Grammatical Name & Function in WAEC Comprehension",
                        examSource = "WAEC SSCE 2023 • Paper 2 Q(g)",
                        problemStatement =
                          "In the sentence 'The scholarship which my sister won transformed our family', state (i) the grammatical name and (ii) the function of 'which my sister won'.",
                        steps =
                          listOf(
                            "Step 1 — Check for finite verb: 'my sister won' has subject 'my sister' + finite verb 'won' => Clause.",
                            "Step 2 — Introduced by relative pronoun 'which' right after noun 'The scholarship' => Adjectival (Relative) Clause.",
                            "Step 3 — Function: It qualifies (modifies) the noun 'The scholarship'.",
                          ),
                        finalAnswer = "Adjectival (Relative) clause; qualifies the noun 'scholarship'",
                        examinerTrap = "Writing 'Relative phrase' loses all marks because 'won' is a finite verb!",
                      ),
                      WorkedExample(
                        title = "Pronoun Case After Preposition",
                        examSource = "JAMB UTME 2022 • Use of English Q19",
                        problemStatement =
                          "Fill the blank: 'Between you and ___, that chemistry experiment looked tricky.'",
                        steps =
                          listOf(
                            "Step 1 — 'Between' is a preposition taking object pronouns.",
                            "Step 2 — Subject pronouns ('I', 'she', 'he') are ungrammatical after prepositions; pick 'me'.",
                          ),
                        finalAnswer = "me",
                        examinerTrap = "People hypercorrect to 'between you and I' — examiners ALWAYS penalize it!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Editorial Headlines & Unambiguous Legal Contracts",
                        industryDomain = "Journalism & Law",
                        narrative =
                          "Ambiguous modifiers in newspaper leads or tenancy agreements trigger costly misreadings, whereas crisp clause placement keeps legal liability crystal clear.",
                        examConnection = "Directly builds sentence error-spotting accuracy for JAMB Lexis & Structure.",
                      ),
                      RealLifeScenario(
                        title = "Formal Academic Abstract Writing",
                        industryDomain = "University Research Communication",
                        narrative =
                          "First-year university reports replace choppy fragments with well-linked subordinate adverbial and relative clauses.",
                        examConnection = "Improves WAEC Paper 2 Expression scores.",
                      ),
                    ),
                  labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
                  labBrief =
                    "Use the Concord & Clause Architecture Studio below to inspect subject-clause alignment and error-proof sentence construction!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_eng_01_1",
                        topic = "Pronoun Case",
                        examType = "JAMB",
                        year = 2024,
                        question = "Choose the grammatically correct option: 'The prize was shared equally between Toyin and ___.'",
                        options = listOf("I", "me", "myself", "we"),
                        correctIndex = 1,
                        hint = "'between' is a preposition requiring object case.",
                        stepByStepSolution = "Prepositions govern objective pronouns: 'between Toyin and me' (test by dropping Toyin: 'between me').",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_01_2",
                        topic = "Grammatical Names",
                        examType = "WAEC",
                        year = 2023,
                        question = "Identify the grammatical name of the clause: 'BEFORE THE RAIN STARTED, we packed the books inside.'",
                        options = listOf("Noun clause", "Adjectival clause", "Adverbial clause of time", "Prepositional phrase"),
                        correctIndex = 2,
                        hint = "Contains subject 'the rain' + verb 'started' and answers 'WHEN?'.",
                        stepByStepSolution = "Introduced by time subordinator 'before' with finite verb 'started', modifying 'packed' -> Adverbial clause of time.",
                        waecMarkingScheme = "A1: Adverbial clause (of time).",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_01_3",
                        topic = "Modifiers",
                        examType = "JAMB",
                        year = 2022,
                        question = "Which sentence avoids a dangling modifier?",
                        options =
                          listOf(
                            "Running across the field, the bell rang loudly.",
                            "Running across the field, Emeka heard the bell ring.",
                            "Having finished the test, the paper was collected.",
                            "To pass JAMB well, past questions must be studied.",
                          ),
                        correctIndex = 1,
                        hint = "The person performing 'Running' must appear right after the comma.",
                        stepByStepSolution = "'Emeka' actually did the running in option B; the bell/paper cannot run or finish tests.",
                      ),
                    ),
                ),
                Lesson(
                  id = "eng_02_tenses_conditionals",
                  moduleId = "eng_mod_1",
                  subjectId = "english",
                  title = "Tenses, Conditionals, 'High Time' & Subjunctive Mood",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. A.2 • WAEC Tense & Mood",
                  learningOutcomes =
                    listOf(
                      "Apply Zero, 1st, 2nd (unreal present), and 3rd (unreal past: had + V₃ -> would have + V₃) conditionals.",
                      "Master unreal-past triggers: 'It is high time...', 'I wish...', 'would rather', and 'If I were...'.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: The Three Exam Conditionals & Subjunctive Unreal Past",
                        explanation =
                          "1st Conditional (real future): If + Present Simple -> will + Base Verb ('If he studies, he will pass'). 2nd Conditional (imaginary present): If + Past Simple ('were' for all persons!) -> would + Base Verb ('If I were taller, I would play'). 3rd Conditional (unfulfilled past regret): If + Past Perfect (had + V₃) -> would have + V₃ ('If she had left early, she would have caught the bus').",
                        keyFormula = "\\text{2nd: If } S + V_2\\ (\\text{were}) \\Rightarrow \\text{would } V_1   |   \\text{3rd: If } S + \\text{had } V_3 \\Rightarrow \\text{would have } V_3",
                        examTip =
                          "'Had I known...' means 'If I had known...' — pair it ONLY with 'would have + past participle' (NEVER 'would had' or 'will have')!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: 'It is high time...' & 'Look forward to...'",
                        explanation =
                          "When expressing urgency in English, 'It is (high/about) time + SUBJECT' ALWAYS takes a PAST TENSE verb referring to right now: 'It is high time we LEFT' (NOT 'leave' or 'should leave'!). Meanwhile, 'look forward to', 'used to (accustomed)', and 'object to' take a GERUND (-ing), NOT a bare infinitive: 'I look forward to SEEING you'!",
                        keyFormula = "\\text{It is high time + Subject + Past Tense } (V_2)   |   \\text{look forward to + } V\\text{-ing}",
                        examTip =
                          "Remember 'Used to + V₁' means past habit ('I used to swim'); 'Be/Get used to + V-ing' means accustomed ('I am used to waking early')!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Inverted Third Conditional",
                        examSource = "JAMB UTME 2024 • Use of English Q22",
                        problemStatement =
                          "Complete the sentence: 'Had the goalkeeper stretched a second earlier, he ___ the penalty.'",
                        steps =
                          listOf(
                            "Step 1 — 'Had the goalkeeper stretched...' is an inverted 3rd conditional (= If he had stretched...).",
                            "Step 2 — Result clause strictly requires 'would have + past participle (saved)'.",
                          ),
                        finalAnswer = "would have saved",
                        examinerTrap = "Never pick 'would save' (2nd conditional) after 'Had + subject + V₃'!",
                      ),
                      WorkedExample(
                        title = "'It Is High Time' + 'Look Forward To' Pair",
                        examSource = "WAEC SSCE 2022 • Paper 1 Q41",
                        problemStatement =
                          "Choose the correct verbs: (1) 'It is high time the committee ___ its report.' (2) 'We look forward to ___ from you soon.'",
                        steps =
                          listOf(
                            "Step 1 — After 'It is high time + subject (the committee)', use unreal past tense: 'submitted'.",
                            "Step 2 — In 'look forward to', 'to' is a preposition requiring -ing gerund: 'hearing'.",
                          ),
                        finalAnswer = "(1) submitted  |  (2) hearing",
                        examinerTrap = "'It is high time TO submit' uses base 'submit' (no subject!), whereas 'high time WE submitted' uses past tense!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Formal Cover Letters & Diplomat Statements",
                        industryDomain = "Corporate & Civil Service Writing",
                        narrative =
                          "Closing an internship application with 'I look forward to hearing from you' instead of 'hear' signals polished mastery of English prepositional verbs immediately.",
                        examConnection = "Directly prevents a common Nigerian English usage error penalized by WAEC.",
                      ),
                      RealLifeScenario(
                        title = "Post-Match Punditry & Incident Root-Cause Reports",
                        industryDomain = "Aviation & Engineering Reviews",
                        narrative =
                          "Engineers use exact 3rd conditionals ('Had the valve been shut, pressure would not have spiked') to separate counterfactuals from facts.",
                        examConnection = "Reinforces unreal past perfect structure.",
                      ),
                    ),
                  labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
                  labBrief =
                    "Test Tense, Conditional & Clause triggers interactively in the Syntax Studio below!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_eng_02_1",
                        topic = "Tense & Mood",
                        examType = "JAMB",
                        year = 2025,
                        question = "It is high time the candidates ___ preparing for their practical exams.",
                        options = listOf("start", "started", "will start", "are starting"),
                        correctIndex = 1,
                        hint = "'It is high time + subject' requires the past tense form.",
                        stepByStepSolution = "Following 'It is high time' + noun subject ('the candidates'), standard English requires simple past 'started'.",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_02_2",
                        topic = "Conditionals",
                        examType = "WAEC",
                        year = 2023,
                        question = "If I had seen the warning sign earlier, I ___ into that flooded street.",
                        options = listOf("would not drive", "will not drive", "would not have driven", "had not driven"),
                        correctIndex = 2,
                        hint = "'If + had seen' is 3rd Conditional (unreal past).",
                        stepByStepSolution = "3rd Conditional matches 'had + V₃' in the if-clause with 'would (not) have + V₃' -> 'would not have driven'.",
                        waecMarkingScheme = "A1: would not have driven.",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_02_3",
                        topic = "Gerunds",
                        examType = "JAMB",
                        year = 2023,
                        question = "All final-year scholars are looking forward to ___ their university admission letters.",
                        options = listOf("receive", "receiving", "received", "have receive"),
                        correctIndex = 1,
                        hint = "'to' inside 'look forward to' acts as a preposition.",
                        stepByStepSolution = "'look forward to' requires a noun or gerund (-ing) -> 'receiving'.",
                      ),
                    ),
                ),
                Lesson(
                  id = "eng_03_concord_tags",
                  moduleId = "eng_mod_1",
                  subjectId = "english",
                  title = "Subject-Verb Concord, Question Tags & Punctuation",
                  durationMinutes = 22,
                  syllabusTag = "JAMB Concord • WAEC Objective Mechanics",
                  learningOutcomes =
                    listOf(
                      "Apply 6 high-yield Concord laws ('together with', 'neither...nor', 'more than one', 'many a', collective nouns, 'one of the...who').",
                      "Form fail-safe Question Tags (+ statement -> − tag; 'seldom/hardly' -> + tag).",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: The Big 6 JAMB/WAEC Concord Rules",
                        explanation =
                          "1) PARENTHETICALS ('together with, as well as, accompanied by'): Verb agrees with the FIRST subject! ('The teacher, along with his students, IS here'). 2) PROXIMITY ('either...or, neither...nor'): Verb agrees with the CLOSER noun ('Neither Tunde nor his friends WERE late'). 3) 'A number of' = PLURAL ('are'); 'The number of' = SINGULAR ('is')! 4) 'Many a student' & 'More than one candidate' = SINGULAR verb! 5) 'One of the students who ___': plural antecedent 'students' => PLURAL verb ('have'!), unless 'the ONLY one who...'!",
                        keyFormula = "\\text{The number of } \\Rightarrow \\text{is}   |   \\text{A number of } \\Rightarrow \\text{are}   |   \\text{neither..nor} \\Rightarrow \\text{closer noun}",
                        examTip =
                          "Uncountable trap nouns ALWAYS take SINGULAR verbs & NO '-s': information, equipment, furniture, luggage, advice, cutlery, stationery!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Question Tags & Negative Adverbs",
                        explanation =
                          "Positive statement -> Negative tag ('She passed, didn't she?'); Negative statement -> Positive tag ('He can't swim, can he?'). Beware HIDDEN NEGATIVES ('hardly, seldom, scarcely, rarely, barely, few, little') — they count as NEGATIVE statements, so use a POSITIVE tag ('He seldom visits, DOES he?')! Always tag 'Let's...' with 'shall we?' and 'I am...' with 'aren't I?'!",
                        keyFormula = "+\\text{Statement} \\Rightarrow -\\text{Tag}   |   \\text{seldom / hardly} \\Rightarrow +\\text{Tag}   |   \\text{Let's} \\Rightarrow \\text{shall we?}",
                        examTip =
                          "Never repeat a noun in a question tag ('The boy ran, didn't the boy?' is WRONG -> 'didn't he?')!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Tricky Multiple-Concord Sentence Audit",
                        examSource = "JAMB UTME 2024 • Use of English Q29",
                        problemStatement =
                          "Choose the correct verb for each: (a) 'A number of physics textbooks ___ missing.' (b) 'The number of applicants ___ risen sharply.'",
                        steps =
                          listOf(
                            "Step 1 — 'A number of X' means 'Several X' -> plural verb 'are'.",
                            "Step 2 — 'The number of X' refers to ONE single numerical count -> singular verb 'has'.",
                          ),
                        finalAnswer = "(a) are  |  (b) has",
                        examinerTrap = "Don't look at the plural noun 'applicants' after 'The number of' — look at the head word 'number'!",
                      ),
                      WorkedExample(
                        title = "Tagging Hidden Negative Sentences",
                        examSource = "WAEC SSCE 2023 • Paper 1 Q35",
                        problemStatement =
                          "Supply the correct question tag: 'She barely recognized her cousin after ten years, ___?'",
                        steps =
                          listOf(
                            "Step 1 — 'barely' carries negative sense ('almost did not').",
                            "Step 2 — Past tense verb 'recognized' requires positive past auxiliary -> 'did she?'.",
                          ),
                        finalAnswer = "did she?",
                        examinerTrap = "Choosing 'didn't she?' is a double negative error!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Broadcast Anchor & Parliamentary Minutes Accuracy",
                        industryDomain = "News Broadcasting & Governance",
                        narrative =
                          "Newsreaders distinguish 'The committee IS divided' vs 'Members ARE' and avoid pluralizing collective equipment ('equipments' is non-standard!) in official reports.",
                        examConnection = "Directly polishes grammatical concord under exam pressure.",
                      ),
                      RealLifeScenario(
                        title = "Diplomatic Politeness with Question Tags",
                        industryDomain = "Professional Communication",
                        narrative =
                          "Speakers soften directives ('Let's wrap up early, shall we?') and verify facts crisply using balanced auxiliary polarity.",
                        examConnection = "Reinforces imperative and hidden-negative tag rules.",
                      ),
                    ),
                  labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
                  labBrief =
                    "Practice Concord rules ('along with', 'neither...nor', 'The number of') and Question Tags interactively in the Syntax Studio below!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_eng_03_1",
                        topic = "Concord",
                        examType = "JAMB",
                        year = 2025,
                        question = "The principal, together with all the prefects, ___ inspecting the new science laboratory.",
                        options = listOf("are", "were", "is", "have been"),
                        correctIndex = 2,
                        hint = "'together with' introduces a parenthetical phrase; match the first noun.",
                        stepByStepSolution = "The main subject is singular ('The principal'), so singular verb 'is' agrees.",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_03_2",
                        topic = "Question Tags",
                        examType = "WAEC",
                        year = 2023,
                        question = "Bola hardly arrives late for morning assembly, ___?",
                        options = listOf("doesn't she", "does she", "isn't she", "did she"),
                        correctIndex = 1,
                        hint = "'hardly' makes the clause negative in meaning.",
                        stepByStepSolution = "A negative statement ('hardly arrives') pairs with a positive present simple tag -> 'does she?'.",
                        waecMarkingScheme = "A1: does she?",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_03_3",
                        topic = "Uncountable Nouns",
                        examType = "JAMB",
                        year = 2024,
                        question = "Choose the correct sentence:",
                        options =
                          listOf(
                            "The lab lacks several new equipments.",
                            "All the furnitures in the hall was painted.",
                            "The laboratory needs some new equipment.",
                            "He gave me many good advices.",
                          ),
                        correctIndex = 2,
                        hint = "Non-count nouns never take '-s' or 'many'.",
                        stepByStepSolution = "'equipment', 'furniture', and 'advice' are uncountable -> 'some new equipment' is exact.",
                      ),
                    ),
                ),
              ),
          ),
          CourseModule(
            id = "eng_mod_2",
            subjectId = "english",
            indexLabel = "Module 02",
            title = "Lexis, Antonyms, Synonyms, Idioms & Prepositions",
            summary = "Master contextual vocabulary, exact prepositional collocations, opposite-in-meaning traps, and figurative idioms.",
            lessons =
              listOf(
                Lesson(
                  id = "eng_04_lexis_antonyms_synonyms",
                  moduleId = "eng_mod_2",
                  subjectId = "english",
                  title = "Contextual Synonyms, Antonyms & Confusable Words",
                  durationMinutes = 18,
                  syllabusTag = "JAMB Lexis • WAEC Paper 1 Sec. 1 & 2",
                  learningOutcomes =
                    listOf(
                      "Use sentence-clue polarity and register to pick exact synonyms ('nearest in meaning') and antonyms ('most nearly opposite').",
                      "Distinguish classic confusable pairs (compliment/complement, stationery/stationary, elicit/illicit, affect/effect).",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: The Contrast-Clue Strategy for Antonyms & Synonyms",
                        explanation =
                          "NEVER define words in isolation! Read contrast connectors ('but, whereas, although, yet, unlike') and cause connectors ('therefore, because'). In 'Though the witness seemed RELUCTANT at first, he later became ___', 'Though...later' signals an ANTONYM shift toward EAGER/WILLING!",
                        keyFormula = "\\text{Part of Speech Lock: Adj} \\leftrightarrow \\text{Adj}   |   \\text{Verb} \\leftrightarrow \\text{Verb}   |   \\text{Check Prefix/Root}",
                        examTip =
                          "Match grammatical form too! If the underlined word is past-tense verb or plural noun, its replacement MUST share that same grammatical class!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: High-Frequency Confusables & Prefixes",
                        explanation =
                          "STATIONARY = motionless ('a' = at rest); STATIONERY = paper/pens ('e' = envelopes!). COMPLIMENT = praise ('i' = I praise); COMPLEMENT = completes nicely. AFFECT (Verb) alters something; EFFECT (Noun) is the result! Remember 'INFAMOUS' is NOT opposite of famous — it means famous for something EVIL; 'INVALUABLE' means PRICELESS (not worthless!).",
                        keyFormula = "\\text{StationEry = Envelopes}   |   \\text{ComplEment = CompletE}   |   \\text{Invaluable = Priceless}",
                        examTip =
                          "'Flammable' and 'Inflammable' BOTH mean catches fire easily! Its true antonym is NON-FLAMMABLE!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "WAEC Opposite-in-Meaning Gap Clues",
                        examSource = "WAEC SSCE 2023 • Paper 1 Q8",
                        problemStatement =
                          "Choose the word MOST NEARLY OPPOSITE to the capitalized word: 'While Obi was FRUGAL with his pocket money, his twin brother was utterly ___.'",
                        steps =
                          listOf(
                            "Step 1 — 'While' signals direct contrast between the twins.",
                            "Step 2 — 'Frugal' means thrifty / careful with money; opposite is 'extravagant' or 'spendthrift' (NOT 'stingy', which is a synonym!).",
                          ),
                        finalAnswer = "extravagant",
                        examinerTrap = "JAMB/WAEC deliberately plant a SYNONYM ('thrifty/miserly') inside antonym questions to catch hasty readers!",
                      ),
                      WorkedExample(
                        title = "Prefix Trap ('Invaluable' & 'Disinterested')",
                        examSource = "JAMB UTME 2022 • Use of English Q12",
                        problemStatement =
                          "Pick the NEAREST in meaning to: 'The mentor offered INVALUABLE guidance before our medical interview.'",
                        steps =
                          listOf(
                            "Step 1 — Break word: too valuable to have a price tag.",
                            "Step 2 — Invaluable = priceless / extremely helpful (opposite of 'worthless').",
                          ),
                        finalAnswer = "priceless",
                        examinerTrap = "Similarly, 'disinterested' means IMPARTIAL/UNBIASED (fair judge), whereas 'uninterested' means bored!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Clinical & Courtroom Precision Vocabulary",
                        industryDomain = "Medicine & Judiciary",
                        narrative =
                          "A judge must be 'disinterested' (impartial), never 'uninterested' (inattentive), just as drug labels must clearly separate 'complimentary' from 'complementary' therapies.",
                        examConnection = "Builds pinpoint lexical discrimination.",
                      ),
                      RealLifeScenario(
                        title = "Technical Procurement Invoices",
                        industryDomain = "Business Operations",
                        narrative =
                          "Ordering office 'stationery' vs 'stationary' generators prevents costly administrative confusion.",
                        examConnection = "Reinforces spelling-meaning pairs tested annually.",
                      ),
                    ),
                  labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
                  labBrief =
                    "Review grammatical category matching and collocation patterns in the Syntax & Lexis Studio below!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_eng_04_1",
                        topic = "Antonyms",
                        examType = "JAMB",
                        year = 2025,
                        question = "Choose the option MOST NEARLY OPPOSITE to 'MITIGATE': 'The governor introduced relief funds to MITIGATE flood hardship.'",
                        options = listOf("Alleviate", "Aggravate", "Soothe", "Soften"),
                        correctIndex = 1,
                        hint = "Mitigate = make less severe; look for 'make worse'.",
                        stepByStepSolution = "Alleviate/soothe/soften are synonyms of mitigate; its antonym is 'aggravate' (worsen).",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_04_2",
                        topic = "Synonyms",
                        examType = "WAEC",
                        year = 2023,
                        question = "Choose the word NEAREST IN MEANING to 'DISINTERESTED': 'A mediator must remain disinterested during arbitration.'",
                        options = listOf("Bored", "Impartial", "Hostile", "Careless"),
                        correctIndex = 1,
                        hint = "Without personal stake or bias.",
                        stepByStepSolution = "Disinterested means objective, fair, and free from personal financial or emotional bias ('impartial').",
                        waecMarkingScheme = "A1: Impartial / Unbiased.",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_04_3",
                        topic = "Confusable Words",
                        examType = "JAMB",
                        year = 2024,
                        question = "The new physics textbook neatly ___ the laboratory practical manual.",
                        options = listOf("compliments", "complements", "conforms", "completes with"),
                        correctIndex = 1,
                        hint = "Means goes well together / makes complete.",
                        stepByStepSolution = "'Complements' (with 'e') means enhances or completes a pair; 'compliments' (with 'i') means praises.",
                      ),
                    ),
                ),
                Lesson(
                  id = "eng_05_idioms_collocations",
                  moduleId = "eng_mod_2",
                  subjectId = "english",
                  title = "Idioms, Phrasal Verbs & Prepositional Collocations",
                  durationMinutes = 18,
                  syllabusTag = "JAMB Idioms • WAEC Lexis Sec. 4 & 5",
                  learningOutcomes =
                    listOf(
                      "Decode common WAEC/JAMB idioms without falling for literal word-for-word traps.",
                      "Pair verbs and adjectives with exact standard prepositions (congratulate ON, abstain FROM, cope WITH, averse TO).",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Decoding Idioms Contextually",
                        explanation =
                          "Idioms carry FIGURATIVE meaning — eliminate any option repeating the literal object! Key exam idioms: 'take the bull by the horns' = confront a tough problem boldly; 'sit on the fence' = refuse to take sides; 'leave no stone unturned' = try every possible method; 'hit the nail on the head' = state the exact truth; 'eat humble pie' = admit fault meekly.",
                        keyFormula = "\\text{Never pick literal meaning!}   |   \\text{'throw in the towel' = surrender}",
                        examTip =
                          "Notice 'a white elephant' = a pricey useless project; 'at sixes and sevens' = in total confusion!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Non-Negotiable Preposition Collocations",
                        explanation =
                          "Correct prepositions often differ from casual speech: congratulate ON (not 'for'!), abstain FROM, abstain/refrain FROM, cope WITH (not 'cope up with'!), comprise OF is WRONG -> say 'comprises 5 chapters' or 'consists OF 5 chapters'! Also: prefer A TO B (never 'prefer A than B'!), superior TO, inferior TO, charge WITH (a crime), accuse OF!",
                        keyFormula = "\\text{comprise (no 'of')}   |   \\text{consist OF}   |   \\text{prefer X TO Y}   |   \\text{congratulate ON}",
                        examTip =
                          "'Discuss', 'emphasize', 'demand', and 'request' take NO 'about/on/for' when used as transitive verbs ('discuss the topic', NOT 'discuss about')!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Avoiding Redundant Prepositions",
                        examSource = "JAMB UTME 2024 • Use of English Q33",
                        problemStatement =
                          "Spot the error-free sentence: (A) 'We discussed about the exam.' (B) 'I prefer biology than chemistry.' (C) 'She congratulated him on his scholarship.' (D) 'I can't cope up with the noise.'",
                        steps =
                          listOf(
                            "Step 1 — Drop 'about' after 'discuss'; use 'prefer X TO Y'; drop 'up' after 'cope'.",
                            "Step 2 — Only option (C) pairs 'congratulate' properly with 'on'.",
                          ),
                        finalAnswer = "(C) 'She congratulated him on his scholarship.'",
                        examinerTrap = "Always say 'cope WITH' (never 'cope up with') and 'comprises' (never 'comprises of')!",
                      ),
                      WorkedExample(
                        title = "Figurative Idiom Selection",
                        examSource = "WAEC SSCE 2022 • Paper 1 Q24",
                        problemStatement =
                          "'Rather than choosing sides in the prefect dispute, Ayo decided to sit on the fence.' What does this mean?",
                        steps =
                          listOf(
                            "Step 1 — Eliminate any literal answer about sitting on a wall.",
                            "Step 2 — 'Sit on the fence' means to remain neutral / avoid committing to either side.",
                          ),
                        finalAnswer = "Remain neutral without taking either side",
                        examinerTrap = "Never pick a literal paraphrase in idiom questions!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Diplomatic Speeches & Formal Presentations",
                        industryDomain = "Public Speaking & Policy",
                        narrative =
                          "Polished speakers seamlessly use 'shed crocodile tears' (fake grief) or 'break the ice' while avoiding non-standard prepositions like 'discuss about'.",
                        examConnection = "Builds sharp ear for standard collocations and idiomatic sense.",
                      ),
                      RealLifeScenario(
                        title = "Project Audit Reviews",
                        industryDomain = "Civic Infrastructure",
                        narrative =
                          "Journalists label an abandoned multi-billion-naira stadium a 'white elephant' — costly to maintain yet delivering little public value.",
                        examConnection = "Exact figurative usage tested repeatedly by WAEC.",
                      ),
                    ),
                  labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
                  labBrief =
                    "Test tricky verb-preposition pairs cleanly in the Syntax & Lexis Studio below!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_eng_05_1",
                        topic = "Prepositions",
                        examType = "JAMB",
                        year = 2025,
                        question = "Most candidates prefer reading quiet library notes ___ cramming overnight.",
                        options = listOf("than", "from", "to", "against"),
                        correctIndex = 2,
                        hint = "'Prefer X ___ Y' always takes one Latinate preposition.",
                        stepByStepSolution = "Standard English strictly pairs 'prefer X to Y' (never 'than').",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_05_2",
                        topic = "Idioms",
                        examType = "WAEC",
                        year = 2023,
                        question = "After three months of searching in vain, the detectives finally THREW IN THE TOWEL. This means they:",
                        options = listOf("Cleaned the office", "Gave up / admitted defeat", "Found the hidden clue", "Celebrated loudly"),
                        correctIndex = 1,
                        hint = "From boxing, when a corner throws a towel into the ring.",
                        stepByStepSolution = "'Throw in the towel' means to surrender or stop trying after facing defeat.",
                        waecMarkingScheme = "A1: Surrendered / stopped trying.",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_05_3",
                        topic = "Collocations",
                        examType = "JAMB",
                        year = 2023,
                        question = "The suspect was charged ___ armed robbery before the high court.",
                        options = listOf("for", "of", "with", "on"),
                        correctIndex = 2,
                        hint = "Notice 'accused OF' vs 'charged ___'.",
                        stepByStepSolution = "You accuse someone OF a crime, but charge someone WITH a crime.",
                      ),
                    ),
                ),
                Lesson(
                  id = "eng_06_registers_cloze",
                  moduleId = "eng_mod_2",
                  subjectId = "english",
                  title = "Vocabulary Registers & Cloze Passage Precision",
                  durationMinutes = 16,
                  syllabusTag = "JAMB Cloze (10 Qs) • WAEC Register Passage",
                  learningOutcomes =
                    listOf(
                      "Recognize domain-specific vocabulary (Banking, Judiciary, Medicine, Aviation, Agriculture, Journalism).",
                      "Combine grammar clues (plurality/articles) + semantic register to fill WAEC/JAMB cloze gaps reliably.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: High-Yield Domain Registers",
                        explanation =
                          "Cloze passages remove technical words from one coherent field! Banking: collateral, overdraft, dividend, liquidity, ledger; Law: plaintiff (suer), defendant, subpoena (summons), verdict, bailiff; Health: diagnosis, prognosis (likely outcome), anesthesia, immunity, benign; Publishing/Press: masthead, editorial, scoop, circulation, proofread.",
                        keyFormula = "\\text{Legal: Plaintiff vs Defendant}   |   \\text{Medical: Diagnosis vs Prognosis}",
                        examTip =
                          "Before choosing a cloze option, check the words IMMEDIATELY before and after the blank: 'an ___' forces vowel sound; 'for ___' may require '-ing'!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: 3-Pass Cloze Passage Technique",
                        explanation =
                          "Pass 1: Read the ENTIRE passage first without filling anything to grasp the domain & tone. Pass 2: Check both COLLOCATION ('take a ___', 'in ___ of') and GRAMMAR ('which', singular vs plural). Pass 3: Re-read smoothly with choices inserted to verify flow.",
                        keyFormula = "\\text{1. Skim Domain} \\rightarrow \\text{2. Match Collocation + Grammar} \\rightarrow \\text{3. Re-read Check}",
                        examTip =
                          "Watch Agriculture vs Law vs Finance traps: a bank grants a 'loan' against 'collateral' (not 'mortgage' unless property/house!).",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Judicial vs Medical Register Precision",
                        examSource = "WAEC SSCE 2022 • Paper 1 Register Passage",
                        problemStatement =
                          "Fill the medical register gaps: 'After examining X-rays, Dr. Musa made an accurate (1) ___ of malaria, though the long-term (2) ___ looked bright.'",
                        steps =
                          listOf(
                            "Step 1 — Naming a disease from symptoms/tests = 'diagnosis'.",
                            "Step 2 — Forecasting recovery outlook = 'prognosis'.",
                          ),
                        finalAnswer = "(1) diagnosis  |  (2) prognosis",
                        examinerTrap = "Don't swap diagnosis (current finding) with prognosis (future recovery outlook)!",
                      ),
                      WorkedExample(
                        title = "Aviation Register Collocation",
                        examSource = "JAMB UTME 2023 • Cloze Passage",
                        problemStatement =
                          "'Before take-off, the captain verified fuel levels in the (1) ___ while air-traffic controllers cleared the (2) ___.'",
                        steps =
                          listOf(
                            "Step 1 — Pilot cabin = 'cockpit'.",
                            "Step 2 — Paved strip for plane take-off/landing = 'runway' (NOT 'tarmac' alone or 'platform'!).",
                          ),
                        finalAnswer = "(1) cockpit  |  (2) runway",
                        examinerTrap = "Trains use 'platform'; planes use 'cockpit' and 'runway'!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Interdisciplinary Technical Literacy",
                        industryDomain = "Media & Public Sector",
                        narrative =
                          "Reading economic budgets, hospital discharge summaries, or supreme court rulings accurately depends on fluency across domain-specific English registers.",
                        examConnection = "Directly builds speed on JAMB's 10-question Cloze section.",
                      ),
                      RealLifeScenario(
                        title = "Insurance & Banking Literacy",
                        industryDomain = "Personal Finance",
                        narrative =
                          "Knowing 'premium', 'indemnity', 'beneficiary', and 'collateral' protects young adults when reading bank agreements.",
                        examConnection = "High-frequency WAEC Commerce/Banking register terms.",
                      ),
                    ),
                  labType = PracticalLabType.SYNTAX_LEXIS_STUDIO,
                  labBrief =
                    "Inspect grammar and collocation alignment rules in the Syntax Studio before tackling cloze passages!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_eng_06_1",
                        topic = "Legal Register",
                        examType = "JAMB",
                        year = 2024,
                        question = "In a civil court suit, the person who initiates legal action against another is the:",
                        options = listOf("Defendant", "Plaintiff", "Magistrate", "Witness"),
                        correctIndex = 1,
                        hint = "Defendant defends; this party brings the complaint.",
                        stepByStepSolution = "The Plaintiff brings a civil lawsuit; the Defendant responds to the claim.",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_06_2",
                        topic = "Banking Register",
                        examType = "WAEC",
                        year = 2023,
                        question = "The valuable asset pledged to a bank as security before obtaining a business loan is called:",
                        options = listOf("Dividend", "Collateral", "Remittance", "Overdraft"),
                        correctIndex = 1,
                        hint = "Lender seizes it if borrower defaults.",
                        stepByStepSolution = "Collateral is pledged property backing repayment of a loan.",
                        waecMarkingScheme = "A1: Collateral.",
                      ),
                      PracticeQuestion(
                        id = "ex_eng_06_3",
                        topic = "Press Register",
                        examType = "JAMB",
                        year = 2022,
                        question = "An exclusive breaking news story published ahead of rival newspapers is termed a:",
                        options = listOf("Caption", "Scoop", "Masthead", "Obituary"),
                        correctIndex = 1,
                        hint = "Journalistic slang for beating every competitor to print.",
                        stepByStepSolution = "A 'scoop' is an exclusive news story broken first by one outlet.",
                      ),
                    ),
                ),
              ),
          ),
        ) + EnglishCurriculumPart2.modules,
      pastQuestions = EnglishCurriculumPart2.pastQuestions,
    )
}
