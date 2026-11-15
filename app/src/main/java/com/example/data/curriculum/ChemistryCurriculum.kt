package com.example.data.curriculum

object ChemistryCurriculum {

  val track =
    SubjectTrack(
      id = "chemistry",
      title = "Chemistry Mastery",
      shortName = "Chemistry",
      tagline = "Stoichiometry, Acid-Base Titration, Gas Laws & Organic Synthesis",
      examCoverage = "JAMB 40 Qs • WAEC Paper 1, 2 & Quantitative Analysis",
      modules =
        listOf(
          CourseModule(
            id = "chem_mod_1",
            subjectId = "chemistry",
            indexLabel = "Module 01",
            title = "Volumetric Analysis, pH & Gas Laws",
            summary = "Master molarity titration ratios, pH logarithmic scales, and the Ideal Gas Equation.",
            lessons =
              listOf(
                Lesson(
                  id = "chem_acid_01",
                  moduleId = "chem_mod_1",
                  subjectId = "chemistry",
                  title = "Acid-Base Titration, pH & Indicator Selection",
                  durationMinutes = 18,
                  syllabusTag = "JAMB Sec. 7.3 • WAEC Practical",
                  learningOutcomes =
                    listOf(
                      "Apply C_A·V_A / (C_B·V_B) = n_A / n_B accurately from balanced chemical equations.",
                      "Select Phenolphthalein vs Methyl Orange based on strong/weak equivalence-point pH.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Stoichiometric Titration Equation",
                        explanation =
                          "At the equivalence point of an acid-base neutralization, the mole ratio of reacted acid to base equals their stoichiometric coefficients (n_A / n_B) in the balanced equation. Always balance the equation first!",
                        keyFormula = "(C_A · V_A) / (C_B · V_B) = n_A / n_B   |   pH = −log₁₀[H⁺]",
                        examTip =
                          "Strong Acid + Weak Base (e.g., HCl + Na₂CO₃) has equivalence pH < 7 ⇒ use Methyl Orange! Weak Acid + Strong Base (CH₃COOH + NaOH) has equivalence pH > 7 ⇒ use Phenolphthalein.",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Titration of HCl against Sodium Trioxocarbonate(IV)",
                        examSource = "WAEC SSCE 2022 • Practical / Paper 2",
                        problemStatement =
                          "25.0 cm³ of 0.10 mol/dm³ Na₂CO₃ solution required 20.0 cm³ of HCl for complete neutralization. Calculate the molar concentration of the HCl.",
                        steps =
                          listOf(
                            "Step 1 — Balanced equation: Na₂CO₃ + 2HCl → 2NaCl + H₂O + CO₂ (n_A = 2, n_B = 1).",
                            "Step 2 — Substitute into formula: (C_A × 20.0) / (0.10 × 25.0) = 2 / 1.",
                            "Step 3 — Solve for C_A: C_A = (2 × 0.10 × 25.0) / 20.0 = 5.0 / 20.0 = 0.25 mol/dm³.",
                          ),
                        finalAnswer = "0.25 mol/dm³",
                        examinerTrap =
                          "Using n_A/n_B = 1/1 instead of 2/1 because Na₂CO₃ is dibasic is the #1 WAEC volumetric error.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Water Treatment Quality Control & Blood Buffering",
                        industryDomain = "Environmental & Clinical Chemistry",
                        narrative =
                          "Municipal water engineers titrate river water samples with standard acid to measure carbonate alkalinity before dosing alum coagulants. Similarly, human blood relies on the HCO₃⁻ / H₂CO₃ buffer system to hold arterial pH tightly between 7.35 and 7.45.",
                        examConnection =
                          "Explains why Methyl Orange endpoints signal complete carbonate neutralization in WAEC practicals.",
                      ),
                    ),
                  labType = PracticalLabType.TITRATION_PH,
                  labBrief =
                    "Run the virtual titration below: drip 0.10 M NaOH into 25 mL of 0.10 M HCl. Watch the pH jump sharply from pH 3 to pH 11 right at V = 25.0 mL, then switch to Weak Acid (CH₃COOH) to observe the buffer region!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_chem_acid_1",
                        topic = "Acids & pH",
                        examType = "JAMB",
                        year = 2023,
                        question = "What is the pH of a 0.001 mol/dm³ solution of HCl (fully ionized)?",
                        options = listOf("1", "2", "3", "4"),
                        correctIndex = 2,
                        hint = "Express 0.001 as 10⁻³.",
                        stepByStepSolution = "[H⁺] = 10⁻³ mol/dm³. pH = −log₁₀(10⁻³) = 3.",
                      ),
                    ),
                ),
                Lesson(
                  id = "chem_gas_02",
                  moduleId = "chem_mod_1",
                  subjectId = "chemistry",
                  title = "Ideal Gas Equation, Graham's Law & Mole Volume",
                  durationMinutes = 15,
                  syllabusTag = "JAMB Sec. 3.2 • WAEC Kinetic Theory",
                  learningOutcomes =
                    listOf(
                      "Convert °C to Kelvin (T = t + 273) before applying PV = nRT.",
                      "Apply Graham's Law of Diffusion relating rates inversely to √M.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Combined Gas & Graham's Diffusion Laws",
                        explanation =
                          "Gas laws ALWAYS require absolute temperature in Kelvin. At constant temperature and pressure, lighter gases diffuse faster: Rate₁ / Rate₂ = √(M₂ / M₁).",
                        keyFormula =
                          "(P₁V₁)/T₁ = (P₂V₂)/T₂   |   PV = nRT   |   R₁/R₂ = √(M₂/M₁)",
                        examTip =
                          "Methane (M = 16) diffuses twice as fast as Sulfur(IV) oxide (M = 64) because √(64/16) = √4 = 2!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Graham's Law Molecular Mass Determination",
                        examSource = "JAMB UTME 2021 • Chemistry Q10",
                        problemStatement =
                          "100 cm³ of Oxygen (O₂, M = 32) diffuses through a porous plug in 20 s. How long will 100 cm³ of Hydrogen (H₂, M = 2) take under the same conditions?",
                        steps =
                          listOf(
                            "Step 1 — Equal volumes mean Rate ∝ 1/time, so t₂/t₁ = √(M₂/M₁).",
                            "Step 2 — t_H2 / 20 = √(2 / 32) = √(1/16) = 1/4.",
                            "Step 3 — t_H2 = 20 / 4 = 5.0 s.",
                          ),
                        finalAnswer = "5.0 seconds",
                        examinerTrap =
                          "Rate is inversely proportional to time! Faster diffusion means SHORTER time, not longer.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "CNG Cylinder Pressure & Cooking Gas Safety",
                        industryDomain = "Petrochemical & Energy Engineering",
                        narrative =
                          "Compressed Natural Gas (CNG) vehicle tanks experience pressure spikes in tropical afternoon heat according to P₁/T₁ = P₂/T₂. Meanwhile, lighter methane leaks disperse rapidly upward via Graham's diffusion law compared to heavier LPG butane/propane.",
                        examConnection =
                          "Connects Kelvin temperature scaling and molar mass diffusion rates.",
                      ),
                    ),
                  labType = PracticalLabType.TITRATION_PH,
                  labBrief =
                    "Use the titration & concentration bench to observe how molar concentration scales linearly with mole count n/V.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_chem_gas_1",
                        topic = "Gas Laws",
                        examType = "WAEC",
                        year = 2020,
                        question =
                          "A gas occupies 200 cm³ at 27 °C. What volume will it occupy at 327 °C under constant pressure?",
                        options = listOf("100 cm³", "300 cm³", "400 cm³", "600 cm³"),
                        correctIndex = 2,
                        hint = "Convert 27 °C and 327 °C to Kelvin (+273).",
                        stepByStepSolution =
                          "T₁ = 27 + 273 = 300 K; T₂ = 327 + 273 = 600 K. By Charles' Law, V₂ = V₁(T₂/T₁) = 200 × (600/300) = 400 cm³.",
                      ),
                    ),
                ),
              ),
          ),
        ),
      pastQuestions =
        listOf(
          PracticeQuestion(
            id = "pq_chem_1",
            topic = "Titration",
            examType = "JAMB",
            year = 2023,
            question =
              "20.0 cm³ of 0.25 mol/dm³ H₂SO₄ is completely neutralized by 25.0 cm³ of NaOH. What is the concentration of NaOH?",
            options = listOf("0.20 mol/dm³", "0.40 mol/dm³", "0.50 mol/dm³", "0.80 mol/dm³"),
            correctIndex = 1,
            hint = "H₂SO₄ + 2NaOH → Na₂SO₄ + 2H₂O, so n_A = 1, n_B = 2.",
            stepByStepSolution =
              "(0.25 × 20) / (C_B × 25) = 1 / 2 => 5 / (25 C_B) = 0.5 => C_B = 10 / 25 = 0.40 mol/dm³.",
          ),
          PracticeQuestion(
            id = "pq_chem_2",
            topic = "Organic Chemistry",
            examType = "WAEC",
            year = 2022,
            question =
              "Which reagent distinguishes terminal alkynes (e.g., ethyne) from alkenes?",
            options =
              listOf(
                "Bromine water",
                "Acidified KMnO₄",
                "Ammoniacal silver trioxonitrate(V)",
                "Sodium metal",
              ),
            correctIndex = 2,
            hint = "Both alkenes and alkynes decolorize bromine water and KMnO₄.",
            stepByStepSolution =
              "Terminal alkynes form a white/yellow precipitate with ammoniacal AgNO₃ (or red ppt with Cu₂Cl₂), whereas alkenes do not react.",
            waecMarkingScheme = "A1: Ammoniacal AgNO₃ / Cu₂Cl₂ forms insoluble acetylide precipitate.",
          ),
        ),
    )
}
