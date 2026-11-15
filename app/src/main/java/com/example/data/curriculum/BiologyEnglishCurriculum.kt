package com.example.data.curriculum

object BiologyEnglishCurriculum {

  val biologyTrack =
    SubjectTrack(
      id = "biology",
      title = "Biology Mastery",
      shortName = "Biology",
      tagline = "Mendelian Genetics, Sickle-Cell Counseling, Bioenergetics & Ecology",
      examCoverage = "JAMB 40 Qs • WAEC Paper 1, 2 & Specimen Practical",
      modules =
        listOf(
          CourseModule(
            id = "bio_mod_1",
            subjectId = "biology",
            indexLabel = "Module 01",
            title = "Genetics, Heredity & Bioenergetics",
            summary = "Predict Punnett cross probabilities and analyze cellular energy pathways.",
            lessons =
              listOf(
                Lesson(
                  id = "bio_gen_01",
                  moduleId = "bio_mod_1",
                  subjectId = "biology",
                  title = "Mendelian Crosses, Codominance & Sickle-Cell Inheritance",
                  durationMinutes = 16,
                  syllabusTag = "JAMB Sec. D.2 • WAEC Genetics",
                  learningOutcomes =
                    listOf(
                      "Construct F₁ and F₂ Punnett squares for monohybrid and codominant crosses.",
                      "Calculate clinical genotype probabilities for HbA/HbS hemoglobin and ABO blood groups.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Monohybrid Ratios vs Codominance",
                        explanation =
                          "In complete dominance (Tt × Tt), the phenotypic ratio is 3:1 while genotypic ratio is 1:2:1. In codominance or incomplete dominance (HbA/HbS × HbA/HbS), the heterozygote shows an intermediate/distinct phenotype, so BOTH phenotypic and genotypic ratios are 1:2:1.",
                        keyFormula = "Tt × Tt ⇒ 3:1 Pheno | 1:2:1 Geno   •   HbAS × HbAS ⇒ 25% AA : 50% AS : 25% SS",
                        examTip =
                          "ABO blood grouping exhibits BOTH multiple alleles (Iᴬ, Iᴮ, i) AND codominance (Iᴬ and Iᴮ are codominant in AB).",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Pre-Marital Genotype Counseling Cross",
                        examSource = "WAEC SSCE 2021 • Paper 2 Q4",
                        problemStatement =
                          "Both parents have the sickle-cell trait (Hb^A Hb^S). Using a genetic cross, state the probability that their first child will suffer from sickle-cell anemia.",
                        steps =
                          listOf(
                            "Step 1 — Parental genotypes: Hb^A Hb^S × Hb^A Hb^S.",
                            "Step 2 — Gametes from each parent: Hb^A and Hb^S.",
                            "Step 3 — F₁ offspring combinations: Hb^A Hb^A (1), Hb^A Hb^S (2), Hb^S Hb^S (1).",
                            "Step 4 — Probability of Hb^S Hb^S (sickle-cell anemia) = 1 out of 4 = 25% (0.25).",
                          ),
                        finalAnswer = "1/4 or 25%",
                        examinerTrap =
                          "Remember each pregnancy is an independent genetic event! Having one SS child does not protect the next child from also having a 25% chance.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Clinical Genetic Counseling in West Africa",
                        industryDomain = "Hematology & Public Health",
                        narrative =
                          "Nigeria carries the highest sickle-cell burden globally because the Hb^S trait confers partial resistance against severe Plasmodium falciparum malaria (heterozygote advantage). Genotype screening before marriage uses Mendelian Punnett probabilities to guide informed family planning.",
                        examConnection =
                          "Links WAEC pedigree diagrams directly to codominance and natural selection.",
                      ),
                    ),
                  labType = PracticalLabType.MENDELIAN_GENETICS,
                  labBrief =
                    "Use the Genetics Cross Lab below: test AS × AS vs AA × SS vs AS × SS and inspect the live Punnett Square breakdown.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_bio_gen_1",
                        topic = "Genetics",
                        examType = "JAMB",
                        year = 2023,
                        question =
                          "A man with blood group A (heterozygous Iᴬi) marries a woman with blood group B (heterozygous Iᴮi). How many distinct blood group phenotypes can their children have?",
                        options = listOf("2", "3", "4", "1"),
                        correctIndex = 2,
                        hint = "Cross (Iᴬ, i) × (Iᴮ, i) in a 2×2 grid.",
                        stepByStepSolution =
                          "Offspring genotypes: IᴬIᴮ (Group AB), Iᴬi (Group A), Iᴮi (Group B), ii (Group O) — all 4 blood groups are possible!",
                      ),
                    ),
                ),
              ),
          ),
        ),
      pastQuestions =
        listOf(
          PracticeQuestion(
            id = "pq_bio_1",
            topic = "Genetics",
            examType = "JAMB",
            year = 2022,
            question =
              "If a carrier woman (XᴴXʰ) marries a normal man (XᴴY), what percentage of their SONS will be hemophilic?",
            options = listOf("0%", "25%", "50%", "100%"),
            correctIndex = 2,
            hint = "Look ONLY among the sons (XᴴY and XʰY), not all 4 offspring.",
            stepByStepSolution =
              "Sons inherit Y from father and Xᴴ or Xʰ from mother. 1 of 2 sons (XʰY) is affected = 50% of sons (25% of all children).",
          ),
          PracticeQuestion(
            id = "pq_bio_2",
            topic = "Cell Bioenergetics",
            examType = "WAEC",
            year = 2021,
            question =
              "Which organelle is the site of the Krebs cycle and oxidative phosphorylation in eukaryotic cells?",
            options = listOf("Ribosome", "Golgi apparatus", "Mitochondrion", "Lysosome"),
            correctIndex = 2,
            hint = "Known as the powerhouse of ATP synthesis.",
            stepByStepSolution =
              "Glycolysis occurs in the cytoplasm, while the Krebs cycle and electron transport chain occur inside the mitochondrion.",
            waecMarkingScheme = "A1: Mitochondrion.",
          ),
        ),
    )

  val englishTrack =
    SubjectTrack(
      id = "english",
      title = "Use of English",
      shortName = "English",
      tagline = "Syntactic Concord, Oral Stress Patterns, Lexis & Comprehension Mastery",
      examCoverage = "JAMB 60 Compulsory Qs • WAEC Essay, Comprehension & Oral",
      modules =
        listOf(
          CourseModule(
            id = "eng_mod_1",
            subjectId = "english",
            indexLabel = "Module 01",
            title = "Oral English Stress & Concord Rules",
            summary = "Conquer JAMB syllable stress shifts and tricky subject-verb agreement structures.",
            lessons =
              listOf(
                Lesson(
                  id = "eng_oral_01",
                  moduleId = "eng_mod_1",
                  subjectId = "english",
                  title = "Syllable Stress Shifts & Proximity Concord",
                  durationMinutes = 15,
                  syllabusTag = "JAMB Lexis & Oral • WAEC Paper 3",
                  learningOutcomes =
                    listOf(
                      "Apply suffix-driven primary stress rules (-tion, -ic, -ity, -graphy).",
                      "Resolve subject-verb concord with 'along with', 'neither...nor', and collective nouns.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Suffix Stress Attraction Rule",
                        explanation =
                          "Words ending in -tion/-sion, -ic/-ical, and -ity almost always place primary stress on the syllable IMMEDIATELY BEFORE the suffix (e.g., e-du-CA-tion, pho-to-GRA-phic, u-ni-VER-si-ty). Two-syllable noun/verb pairs stress the 1st syllable as Noun (CON-duct) and 2nd as Verb (con-DUCT).",
                        keyFormula = "Suffixes (-tion, -ic, -ity) ⇒ Stress Penultimate (1 before suffix)",
                        examTip =
                          "Watch for 'as well as', 'together with', and 'accompanied by': they do NOT make a singular subject plural — always match the FIRST subject!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Primary Stress & Parenthetical Concord",
                        examSource = "JAMB UTME 2023 • Use of English",
                        problemStatement =
                          "1. Identify primary stress in 'photographic'. 2. Choose the right verb: 'The principal, together with the prefects, ___ addressing the assembly.'",
                        steps =
                          listOf(
                            "Step 1 — 'photographic' ends in -ic, pulling stress to the syllable before -ic: pho-to-GRA-phic (3rd syllable).",
                            "Step 2 — In 'The principal, together with the prefects...', ignore the parenthetical phrase between commas; the head subject is singular ('The principal') => 'is'.",
                          ),
                        finalAnswer = "pho-to-GRA-phic  |  'is'",
                        examinerTrap =
                          "With 'neither...nor' or 'either...or', the verb agrees with the CLOSER subject (Proximity Concord), unlike 'together with' which agrees with the FIRST subject!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Broadcast Journalism & Legal Drafting Clarity",
                        industryDomain = "Media & Law",
                        narrative =
                          "News anchors and legal advocates rely on accurate stress placement to distinguish nouns from verbs ('CON-tract' vs 'con-TRACT') and exact syntactic concord to prevent ambiguity in statutes and contracts.",
                        examConnection =
                          "Accounts for 15+ recurring marks across JAMB Use of English and WAEC Oral English.",
                      ),
                    ),
                  labType = PracticalLabType.ORAL_ENGLISH_STRESS,
                  labBrief =
                    "Test your ear and syntax in the Studio below: inspect how suffixes shift primary stress across related words and build concord sentences interactively.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_eng_oral_1",
                        topic = "Oral Stress",
                        examType = "JAMB",
                        year = 2023,
                        question = "Choose the option with the correct primary stress for 'demarcation':",
                        options = listOf("DE-mar-ca-tion", "de-MAR-ca-tion", "de-mar-CA-tion", "de-mar-ca-TION"),
                        correctIndex = 2,
                        hint = "Words ending in -tion stress the syllable immediately preceding -tion.",
                        stepByStepSolution = "de-mar-CA-tion places primary stress on CA, right before -tion.",
                      ),
                    ),
                ),
              ),
          ),
        ),
      pastQuestions =
        listOf(
          PracticeQuestion(
            id = "pq_eng_1",
            topic = "Concord",
            examType = "JAMB",
            year = 2023,
            question = "Neither the coach nor the players ___ satisfied with the referee's decision.",
            options = listOf("was", "is", "were", "has been"),
            correctIndex = 2,
            hint = "With 'neither...nor', apply Proximity Concord to the closer noun ('players').",
            stepByStepSolution = "'players' is plural and closest to the verb gap, requiring the plural verb 'were'.",
          ),
          PracticeQuestion(
            id = "pq_eng_2",
            topic = "Oral Stress",
            examType = "JAMB",
            year = 2022,
            question = "Which option has a DIFFERENT stress pattern from the others?",
            options = listOf("PHO-to-graph", "CON-duct (noun)", "im-PORT (verb)", "SUB-ject (noun)"),
            correctIndex = 2,
            hint = "Check whether each word is a noun (1st syllable) or verb (2nd syllable).",
            stepByStepSolution = "PHO-to-graph, CON-duct, and SUB-ject carry initial stress; im-PORT (verb) stresses the 2nd syllable.",
          ),
        ),
    )
}
