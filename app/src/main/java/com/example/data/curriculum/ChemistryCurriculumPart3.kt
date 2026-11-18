package com.example.data.curriculum

object ChemistryCurriculumPart3 {

  val module5: CourseModule =
    CourseModule(
      id = "chem_mod_5",
      subjectId = "chemistry",
      indexLabel = "Module 05",
      title = "Organic Chemistry & WAEC Qualitative Salt Analysis",
      summary = "Master Alkanes/Alkenes/Alkynes, Isomerism, Alkanols, Esters, Soaps, Polymers & WAEC Cation/Anion drop-and-excess bench tests.",
      lessons =
        listOf(
          Lesson(
            id = "chem_13_organic_hydrocarbons",
            moduleId = "chem_mod_5",
            subjectId = "chemistry",
            title = "Hydrocarbons: Alkanes, Alkenes, Alkynes, Benzene & Isomers",
            durationMinutes = 22,
            syllabusTag = "JAMB Sec. 15.1 • WAEC Hydrocarbons & Isomerism",
            learningOutcomes =
              listOf(
                "Identify Alkanes (CₙH₂ₙ₊₂), Alkenes (CₙH₂ₙ), and Alkynes (CₙH₂ₙ₋₂) and name IUPAC branched isomers.",
                "Contrast Alkane SUBSTITUTION (with Cl₂/UV) against Alkene/Alkyne ADDITION (decolorizing Bromine water).",
                "Distinguish terminal alkynes using Ammoniacal Cu₂Cl₂ / AgNO₃ and explain Octane Rating / Cracking.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. Saturated vs Unsaturated Hydrocarbons (Holding Hands!)",
                  explanation =
                    "Carbon ALWAYS makes 4 bonds. In Alkanes (single bonds, saturated), every hand is full—so Chlorine can only swap places with a Hydrogen atom (SUBSTITUTION under sunlight!). Alkenes (double bond C=C) and Alkynes (triple bond C≡C) are unsaturated—they open up an extra bond to ADD Br₂ or H₂ cleanly! Drop brown Bromine water into an alkene and it turns COLOURLESS instantly!",
                  keyFormula = "\\text{Alkane: } C_nH_{2n+2}   |   \\text{Alkene: } C_nH_{2n}   |   \\text{Alkyne: } C_nH_{2n-2}   |   \\text{Homolog diff } = CH_2\\; (14\\text{ g})",
                  examTip =
                    "Wait: BOTH Alkenes and Alkynes decolorize Br₂ water! How to tell Ethyne (terminal H−C≡C−H) apart from Ethene? Ethyne gives a RED precipitate with ammoniacal Copper(I) chloride!",
                ),
                ConceptBlock(
                  heading = "2. Isomers, Petroleum Cracking & Geometric Cis/Trans",
                  explanation =
                    "Structural Isomers have the SAME molecular formula (e.g., C₄H₁₀) but different skeletons (straight butane vs branched 2-methylpropane). Petroleum Cracking snaps long heavy kerosene chains into high-demand Petrol + useful Ethene gas! Watch Octane Number: branched chains like 2,2,4-trimethylpentane (Iso-octane = 100) burn smoothly without engine knocking!",
                  keyFormula = "\\text{C}_{10}\\text{H}_{22} \\xrightarrow{\\text{Crack}} \\text{C}_8\\text{H}_{18}\\text{ (petrol)} + \\text{C}_2\\text{H}_4\\text{ (ethene)}",
                  examTip =
                    "Chiral carbon = carbon holding 4 DIFFERENT groups (optical isomerism); Cis/Trans requires different groups on BOTH ends of a C=C double bond!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Counting Structural Isomers of C₅H₁₂",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement = "Name all three structural isomers of Pentane (C₅H₁₂).",
                  steps =
                    listOf(
                      "Step 1 — Straight 5-carbon chain: Pentane.",
                      "Step 2 — 4-carbon chain with 1 methyl branch: 2-methylbutane.",
                      "Step 3 — 3-carbon chain with 2 methyl branches: 2,2-dimethylpropane.",
                    ),
                  finalAnswer = "Pentane, 2-methylbutane, 2,2-dimethylpropane",
                  examinerTrap = "3-methylbutane is the SAME molecule as 2-methylbutane counted from the right—always number from the end nearest the branch!",
                ),
                WorkedExample(
                  title = "IUPAC Naming of Branched Alkene",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement = "Name CH₃−CH=C(CH₃)−CH₃.",
                  steps =
                    listOf(
                      "Step 1 — Longest chain containing C=C has 4 carbons (butene).",
                      "Step 2 — Number from the end giving C=C the lowest locant: starts at C2 from either side, but numbering right-to-left gives methyl branch at C2 (2 < 3)!",
                      "Step 3 — 2-methylbut-2-ene.",
                    ),
                  finalAnswer = "2-methylbut-2-ene",
                  examinerTrap = "Double bond C=C priority ALWAYS beats alkyl branches when choosing numbering direction!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Oxy-Acetylene Welding & Fruit Ripening Ethene",
                  industryDomain = "Metal Fabrication & Agriculture",
                  narrative =
                    "Burning Ethyne (C₂H₂) in pure oxygen hits 3000°C to slice steel plates, while tiny traces of Ethene gas trigger bananas to ripen yellow in warehouses.",
                    examConnection = "CaC₂ + 2H₂O → Ca(OH)₂ + C₂H₂ is the lab synthesis of ethyne.",
                ),
              ),
            labType = PracticalLabType.ORGANIC_HYDROCARBON_LAB,
            labBrief =
              "Switch among Alkane, Alkene, Alkyne, and Benzene to test Bromine-water decolorization, Baeyer KMnO₄ & Copper(I) terminal alkyne precipitates!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_chem_13_1",
                  topic = "Functional Test",
                  examType = "JAMB",
                  year = 2024,
                  question = "Which reagent gives a brick-red precipitate ONLY with terminal alkynes like Ethyne, NOT Ethene?",
                  options = listOf("Bromine water", "Acidified KMnO₄", "Ammoniacal Copper(I) chloride", "Fehling's solution"),
                  correctIndex = 2,
                  hint = "Both decolorize Br₂ and KMnO₄; only ≡C−H forms red Cu₂C₂.",
                  stepByStepSolution = "Ethyne reacts with Cu(NH₃)₂⁺ to precipitate red Copper(I) dicarbide.",
                ),
                PracticeQuestion(
                  id = "ex_chem_13_2",
                  topic = "Alkane Substitution",
                  examType = "WAEC",
                  year = 2023,
                  question = "Methane reacts with excess Chlorine in diffused sunlight to ultimately form:",
                  options = listOf("CH₂Cl₂", "C₂H₆", "CCl₄ and HCl", "CH₃OH"),
                  correctIndex = 2,
                  hint = "All 4 H atoms get substituted step-by-step.",
                  stepByStepSolution = "Free-radical substitution replaces H one by one up to Tetrachloromethane (CCl₄).",
                ),
                PracticeQuestion(
                  id = "ex_chem_13_3",
                  topic = "Octane Rating",
                  examType = "JAMB",
                  year = 2025,
                  question = "Which hydrocarbon has an assigned Octane Number of 100 for anti-knocking petrol?",
                  options = listOf("n-Heptane", "2,2,4-trimethylpentane", "Methane", "Benzene"),
                  correctIndex = 1,
                  hint = "Heptane = 0; Iso-octane = 100.",
                  stepByStepSolution = "2,2,4-trimethylpentane (iso-octane) burns smoothly and defines Octane 100.",
                ),
                PracticeQuestion(
                  id = "ex_chem_13_4",
                  topic = "Homologous Mass Step",
                  examType = "WAEC",
                  year = 2022,
                  question = "Successive members of any homologous series differ in molecular mass by:",
                  options = listOf("12 g", "14 g", "16 g", "28 g"),
                  correctIndex = 1,
                  hint = "One CH₂ unit = 12 + 2(1).",
                  stepByStepSolution = "CH₂ adds 14 molar mass units per step.",
                ),
                PracticeQuestion(
                  id = "ex_chem_13_5",
                  topic = "Markovnikov / Hydration",
                  examType = "JAMB",
                  year = 2021,
                  question = "Passing Ethene with steam over H₃PO₄ catalyst at 300°C produces:",
                  options = listOf("Ethane", "Ethanol", "Ethanoic acid", "Ethyl ethanoate"),
                  correctIndex = 1,
                  hint = "CH₂=CH₂ + H₂O → CH₃CH₂OH.",
                  stepByStepSolution = "Catalytic hydration of ethene manufactures industrial ethanol.",
                ),
                PracticeQuestion(
                  id = "ex_chem_13_6",
                  topic = "Cis-Trans Isomerism",
                  examType = "NECO",
                  year = 2024,
                  question = "Which alkene exhibits geometric (cis-trans) isomerism?",
                  options = listOf("Ethene", "Propene", "But-1-ene", "But-2-ene"),
                  correctIndex = 3,
                  hint = "Each double-bonded carbon needs two DIFFERENT attached groups.",
                  stepByStepSolution = "But-2-ene (CH₃−CH=CH−CH₃) has H and CH₃ on both C=C carbons.",
                ),
              ),
          ),

          Lesson(
            id = "chem_14_alkanols_esters_polymers",
            moduleId = "chem_mod_5",
            subjectId = "chemistry",
            title = "Alkanols, Alkanoic Acids, Esters, Soaps & Polymers",
            durationMinutes = 22,
            syllabusTag = "JAMB Sec. 15.2–15.4 • WAEC Oxygenated Organics & Macromolecules",
            learningOutcomes =
              listOf(
                "Distinguish Primary (→ Aldehyde → Acid), Secondary (→ Ketone), and Tertiary (resists!) Alkanols.",
                "Write Esterification (Acid + Alcohol ⇌ Ester + Water) and Saponification (Fat + NaOH → Soap + Glycerol).",
                "Classify Addition Polymers (Polythene, PVC) vs Condensation Polymers (Nylon, Terylene, Proteins, Starch).",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. Alcohol Oxidation, Esters (Fruity Smells!) & Soaps",
                  explanation =
                    "Warm Primary Ethanol with orange acidified K₂Cr₂O₇ and it oxidizes first to Ethanal, then to Vinegar (Ethanoic Acid), turning the tube GREEN! Secondary alcohol stops at a Ketone; Tertiary has no α-H so orange stays unchanged! Mix Acid + Alcohol + a drop of Conc. H₂SO₄ and you cook up a sweet-smelling fruit ESTER! Boil vegetable fat/ester with caustic NaOH instead and you make SOAP + Glycerol (Saponification)!",
                  keyFormula = "\\text{RCOOH} + \\text{R}'\\text{OH} \\underset{\\text{H}_2\\text{SO}_4}{\\rightleftharpoons} \\text{RCOOR}' + \\text{H}_2\\text{O}   |   \\text{Fat} + 3\\text{NaOH} \\to \\text{Soap} + \\text{Glycerol}",
                  examTip =
                    "How to name an Ester RCOOR'? The '-OH alcohol' part goes FIRST as '-yl', and the '-COOH acid' part comes SECOND as '-anoate' (e.g., Ethanoic acid + Methanol = METHYL ETHANOATE)!",
                ),
                ConceptBlock(
                  heading = "2. Addition vs Condensation Polymers & Biomolecules",
                  explanation =
                    "Addition polymers (Polythene from Ethene, PVC from Chloroethene, Polystyrene) snap open C=C double bonds with ZERO side-product! Condensation polymers (Nylon-6,6, Terylene polyester, Starch, Proteins) link two different ends together while spitting out a tiny H₂O or HCl molecule at every join!",
                  keyFormula = "\\text{Starch} \\xrightarrow{\\text{amylase/H}^+} \\text{Glucose} \\xrightarrow{\\text{yeast zymase}} 2\\text{C}_2\\text{H}_5\\text{OH} + 2\\text{CO}_2",
                  examTip =
                    "Nylon and Proteins BOTH share the Peptide/Amide link (−CONH−); Terylene and Fats share the Ester link (−COO−)!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Naming & Synthesizing Fruity Esters",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement =
                      "Propan-1-ol reacts with Ethanoic acid under reflux with Conc. H₂SO₄. Name the product and state the catalyst's two roles.",
                  steps =
                    listOf(
                      "Step 1 — Alcohol is Propan-1-ol → 'Propyl'; Acid is Ethanoic → 'ethanoate' ⇒ Propyl ethanoate.",
                      "Step 2 — Conc. H₂SO₄ acts as (i) Acid Catalyst AND (ii) Dehydrating Agent removing H₂O to shift equilibrium right.",
                    ),
                  finalAnswer = "Propyl ethanoate",
                  examinerTrap = "Reversing the ester name to 'Ethyl propanoate' swaps which molecule was the acid vs alcohol!",
                ),
                WorkedExample(
                  title = "Fermentation Gas Volume from Glucose",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement =
                      "90 g of Glucose (C₆H₁₂O₆, Mᵣ = 180) is fermented completely by yeast. What volume of CO₂ forms at STP?",
                  steps =
                    listOf(
                      "Step 1 — C₆H₁₂O₆ → 2C₂H₅OH + 2CO₂ (1 mol glucose → 2 mol CO₂!).",
                      "Step 2 — Moles glucose = 90/180 = 0.5 mol ⇒ moles CO₂ = 2 × 0.5 = 1.0 mol.",
                      "Step 3 — Volume at STP = 1.0 × 22.4 = 22.4 dm³.",
                    ),
                  finalAnswer = "22.4 dm³",
                  examinerTrap = "Forgetting the '2' coefficient before CO₂ in fermentation!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Palm Kernel Soap Making & Biodegradable Polyesters",
                  industryDomain = "Consumer Goods & Materials",
                  narrative =
                    "Local black soap combines potash alkali (KOH from plantain ash) with palm kernel oil triglycerides in classic saponification, releasing glycerol moisturizer.",
                  examConnection = "Adding common NaCl (brine) 'salts out' soap curd by reducing soap solubility!",
                ),
              ),
            labType = PracticalLabType.ORGANIC_HYDROCARBON_LAB,
            labBrief =
              "Combine Alkanols & Carboxylic Acids in the Esterification bench, verify green Cr³⁺ alcohol oxidation, and inspect Nylon vs Polythene polymer links.",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_chem_14_1",
                  topic = "Alkanol Classes",
                  examType = "JAMB",
                  year = 2024,
                  question = "Oxidation of a secondary alkanol like Propan-2-ol yields a:",
                  options = listOf("Carboxylic acid", "Aldehyde", "Ketone (Alkanone)", "Alkene"),
                  correctIndex = 2,
                  hint = "Primary → Aldehyde → Acid; Secondary → Alkanone.",
                  stepByStepSolution = "Propan-2-ol oxidizes cleanly to Propanone (a ketone).",
                ),
                PracticeQuestion(
                  id = "ex_chem_14_2",
                  topic = "Ester Naming",
                  examType = "WAEC",
                  year = 2023,
                  question = "Name the ester CH₃CH₂COOCH₃.",
                  options = listOf("Ethyl ethanoate", "Methyl propanoate", "Propyl methanoate", "Methyl ethanoate"),
                  correctIndex = 1,
                  hint = "3-carbon acid side (CH₃CH₂COO−) + 1-carbon alcohol side (−CH₃).",
                  stepByStepSolution = "Alcohol methyl + Acid propanoate = Methyl propanoate.",
                ),
                PracticeQuestion(
                  id = "ex_chem_14_3",
                  topic = "Saponification",
                  examType = "JAMB",
                  year = 2025,
                  question = "Alkaline hydrolysis of vegetable oil with NaOH produces soap and which by-product?",
                  options = listOf("Ethanol", "Glycerol (Propane-1,2,3-triol)", "Methanol", "Ethanoic acid"),
                  correctIndex = 1,
                  hint = "Fats are triesters of glycerol!",
                  stepByStepSolution = "Glycerol (propane-1,2,3-triol) is released alongside sodium carboxylate soap.",
                ),
                PracticeQuestion(
                  id = "ex_chem_14_4",
                  topic = "Condensation Polymers",
                  examType = "WAEC",
                  year = 2022,
                  question = "Which synthetic polymer is formed by condensation polymerization?",
                  options = listOf("Polyethene", "Polystyrene", "Polyvinyl chloride (PVC)", "Nylon-6,6"),
                  correctIndex = 3,
                  hint = "Polyamides and Polyesters eliminate small H₂O/HCl molecules.",
                  stepByStepSolution = "Nylon-6,6 forms from hexanedioic acid + diaminohexane eliminating H₂O.",
                ),
                PracticeQuestion(
                  id = "ex_chem_14_5",
                  topic = "Distinguishing Acids vs Esters",
                  examType = "JAMB",
                  year = 2021,
                  question = "Which reagent gives brisk effervescence of CO₂ with Ethanoic acid?",
                  options = listOf("Sodium metal only", "Na₂CO₃ / NaHCO₃", "Fehling's solution", "Bromine water"),
                  correctIndex = 1,
                  hint = "Alkanols react with Na metal, but ONLY Alkanoic Acids fizz with NaHCO₃!",
                  stepByStepSolution = "CH₃COOH + NaHCO₃ → CH₃COONa + H₂O + CO₂↑.",
                ),
                PracticeQuestion(
                  id = "ex_chem_14_6",
                  topic = "Dehydration of Ethanol",
                  examType = "NECO",
                  year = 2024,
                  question = "Heating Ethanol with excess Conc. H₂SO₄ at 170°C produces:",
                  options = listOf("Ethoxyethane", "Ethene", "Ethanoic acid", "Ethane"),
                  correctIndex = 1,
                  hint = "At 140°C (excess ethanol) → Ether; at 170°C (excess acid) → Alkene.",
                  stepByStepSolution = "C₂H₅OH → C₂H₄ + H₂O at 170°C.",
                ),
              ),
          ),

          Lesson(
            id = "chem_15_qualitative_nuclear",
            moduleId = "chem_mod_5",
            subjectId = "chemistry",
            title = "Qualitative Cation/Anion Tests & Nuclear Chemistry",
            durationMinutes = 22,
            syllabusTag = "JAMB Sec. 16 • WAEC Practical Paper 3 & Radioactivity",
            learningOutcomes =
              listOf(
                "Identify Ca²⁺, Zn²⁺, Al³⁺, Pb²⁺, Cu²⁺, Fe²⁺, Fe³⁺, and NH₄⁺ using NaOH(aq) & NH₃(aq) in drops vs excess.",
                "Identify Cl⁻, SO₄²⁻, CO₃²⁻, NO₃⁻ (Brown Ring!), and SO₃²⁻ with signature precipitates & gases.",
                "Balance nuclear α/β equations and distinguish nuclear fusion vs chemical reactions.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. The WAEC Drop-and-Excess Cation Chart (Guaranteed 25+ Marks!)",
                  explanation =
                    "Drop NaOH(aq) or NH₃(aq) into a mysterious metal salt! Colours spot Transition metals instantly: Cu²⁺ = BLUE gelatinous ppt; Fe²⁺ = DIRTY GREEN ppt; Fe³⁺ = REDDISH-BROWN ppt! What about WHITE precipitates (Ca²⁺, Al³⁺, Pb²⁺, Zn²⁺)? Add EXCESS NaOH: ZAP (Zn²⁺, Al³⁺, Pb²⁺) DISSOLVE into colourless solution, while Ca²⁺ STAYS insoluble! Then test with EXCESS NH₃: ONLY Zn²⁺ (and Cu²⁺ deep blue!) dissolves in excess NH₃—Al³⁺ & Pb²⁺ stay white!",
                  keyFormula = "\\text{Excess NaOH dissolves: } \\text{Zn}^{2+},\\text{ Al}^{3+},\\text{ Pb}^{2+}   |   \\text{Excess NH}_3\\text{ dissolves: } \\text{Zn}^{2+},\\; \\text{Cu}^{2+}\\text{ (deep blue)}",
                  examTip =
                    "Distinguish Al³⁺ vs Pb²⁺ cleanly: add dilute HCl or KI(aq)—Pb²⁺ makes bright YELLOW PbI₂ precipitate (or white PbCl₂ soluble on heating), Al³⁺ stays clear!",
                ),
                ConceptBlock(
                  heading = "2. Essential Anion Tests & Nuclear vs Chemical Reactions",
                  explanation =
                    "SO₄²⁻ + BaCl₂ + dil. HCl = White ppt INSOLUBLE in acid (SO₃²⁻ dissolves!). Cl⁻ + AgNO₃ + dil. HNO₃ = White curdy ppt soluble in excess NH₃(aq)! NO₃⁻ + fresh FeSO₄ + Conc. H₂SO₄ down tube wall = BROWN RING! In Nuclear reactions, ONLY the nucleus changes (α loses ⁴He; β converts neutron → proton + ⁰₋₁e, raising Z by +1!).",
                  keyFormula = "\\text{CO}_3^{2-} \\xrightarrow{\\text{H}^+} \\text{CO}_2\\text{ (turns limewater milky)}   |   \\text{NO}_3^- \\xrightarrow{\\text{Fe}^{2+}/\\text{H}_2\\text{SO}_4} \\text{Brown Ring}",
                  examTip =
                    "NH₄⁺ gives NO precipitate with NaOH—warm it gently and pungent NH₃ gas turns moist red litmus BLUE!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Pinpointing an Unknown Salt X",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement =
                      "Salt X gives a white precipitate with NaOH soluble in excess, a white precipitate with NH₃ INSOLUBLE in excess, and a bright yellow precipitate with KI(aq). Identify X's cation.",
                  steps =
                    listOf(
                      "Step 1 — Soluble in excess NaOH narrows to {Zn²⁺, Al³⁺, Pb²⁺}.",
                      "Step 2 — Insoluble in excess NH₃ eliminates Zn²⁺, leaving {Al³⁺, Pb²⁺}.",
                      "Step 3 — Bright yellow precipitate with KI(aq) is PbI₂ ⇒ Cation is Pb²⁺.",
                    ),
                  finalAnswer = "Lead(II) ion, Pb²⁺",
                  examinerTrap = "Confusing Al³⁺ with Pb²⁺—always check HCl, SO₄²⁻, or KI for Pb²⁺!",
                ),
                WorkedExample(
                  title = "Balancing Nuclear Beta Emission",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement = "Carbon-14 (¹⁴₆C) undergoes β-decay. Identify the daughter nuclide.",
                  steps =
                    listOf(
                      "Step 1 — ¹⁴₆C → ᴬ_Z X + ⁰₋₁e.",
                      "Step 2 — Mass A = 14 − 0 = 14; Atomic number Z = 6 − (−1) = 7 ⇒ Nitrogen-14 (¹⁴₇N).",
                    ),
                  finalAnswer = "¹⁴₇N (Nitrogen-14)",
                  examinerTrap = "Beta decay INCREASES atomic number by +1 while keeping mass number constant!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Borehole Water Testing & Carbon-14 Archaeological Dating",
                  industryDomain = "Environmental Lab & Archaeology",
                  narrative =
                      "Water labs test borehole samples with AgNO₃ and BaCl₂ to detect unsafe chloride/sulfate levels, while ¹⁴C decay dates ancient Nok terracotta artifacts.",
                  examConnection = "Why add dil. HCl/HNO₃ before BaCl₂ or AgNO₃? To destroy interfering CO₃²⁻ ions first!",
                ),
              ),
            labType = PracticalLabType.QUALITATIVE_ANALYSIS_LAB,
            labBrief =
              "Pick any unknown cation or anion, add NaOH/NH₃ drops vs excess or AgNO₃/BaCl₂, and inspect authentic WAEC precipitates live!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_chem_15_1",
                  topic = "Copper(II) NH₃ Test",
                  examType = "JAMB",
                  year = 2024,
                  question = "Adding excess aqueous NH₃ to a pale-blue CuSO₄ solution produces:",
                  options = listOf("White insoluble ppt", "Deep royal-blue solution", "Dirty green ppt", "Reddish-brown ppt"),
                  correctIndex = 1,
                  hint = "Forms [Cu(NH₃)₄]²⁺ tetraamminecopper(II) complex.",
                  stepByStepSolution = "Initial blue Cu(OH)₂ dissolves in excess NH₃ to form a deep royal-blue solution.",
                ),
                PracticeQuestion(
                  id = "ex_chem_15_2",
                  topic = "Cation Discrimination",
                  examType = "WAEC",
                  year = 2023,
                  question = "Which pair of cations BOTH dissolve in excess NaOH(aq) ONLY, staying insoluble in excess NH₃(aq)?",
                  options = listOf("Ca²⁺ and Zn²⁺", "Al³⁺ and Pb²⁺", "Cu²⁺ and Fe²⁺", "Zn²⁺ and Pb²⁺"),
                  correctIndex = 1,
                  hint = "Zn²⁺ dissolves in BOTH NaOH and NH₃; Ca²⁺ in NEITHER.",
                  stepByStepSolution = "Al³⁺ and Pb²⁺ form amphoteric hydroxides soluble in NaOH(xs) but insoluble in NH₃(xs).",
                ),
                PracticeQuestion(
                  id = "ex_chem_15_3",
                  topic = "Sulfate Confirmation",
                  examType = "JAMB",
                  year = 2025,
                  question = "To confirm SO₄²⁻ ions, add BaCl₂(aq) followed by dilute HCl. Why is HCl added?",
                  options = listOf("Speed up ppt", "Dissolve any BaCO₃ or BaSO₃ leaving pure insoluble BaSO₄", "Turn litmus blue", "Oxidize chloride"),
                  correctIndex = 1,
                  hint = "BaCO₃ & BaSO₃ dissolve in HCl; BaSO₄ does NOT.",
                  stepByStepSolution = "Acidifying distinguishes true BaSO₄ from carbonate or sulfite precipitates.",
                ),
                PracticeQuestion(
                  id = "ex_chem_15_4",
                  topic = "Brown Ring Test",
                  examType = "WAEC",
                  year = 2022,
                  question = "The Brown Ring test with fresh FeSO₄ and Conc. H₂SO₄ specifically confirms which anion?",
                  options = listOf("Cl⁻", "CO₃²⁻", "SO₄²⁻", "NO₃⁻"),
                  correctIndex = 3,
                  hint = "Forms [Fe(H₂O)₅(NO)]²⁺ brown complex at the acid interface.",
                  stepByStepSolution = "Trioxonitrate(V), NO₃⁻, forms a distinct brown ring.",
                ),
                PracticeQuestion(
                  id = "ex_chem_15_5",
                  topic = "Iron(II) vs Iron(III)",
                  examType = "JAMB",
                  year = 2021,
                  question = "A salt gives a dirty-green precipitate with NaOH that turns reddish-brown along the rim on standing in air. The cation is:",
                  options = listOf("Fe³⁺", "Fe²⁺", "Cr³⁺", "Cu²⁺"),
                  correctIndex = 1,
                  hint = "Air oxidizes Fe(OH)₂ slowly into Fe(OH)₃.",
                  stepByStepSolution = "Fe²⁺ gives dirty-green Fe(OH)₂ which oxidizes on standing to brown Fe(OH)₃.",
                ),
                PracticeQuestion(
                  id = "ex_chem_15_6",
                  topic = "Nuclear Decay Balance",
                  examType = "NECO",
                  year = 2024,
                  question = "If ²²⁶₈₈Ra emits one alpha particle (⁴₂He), the resulting nuclide has mass and atomic number:",
                  options = listOf("222, 86", "222, 90", "226, 89", "224, 86"),
                  correctIndex = 0,
                  hint = "Subtract 4 from A and 2 from Z.",
                  stepByStepSolution = "A = 226 − 4 = 222; Z = 88 − 2 = 86 (²²²₈₆Rn).",
                ),
              ),
          ),
        ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion("pq_chem_01", "Separation", "JAMB", 2025, "Chromatography Rf value equals:", listOf("Solute distance / Solvent front", "Solvent front / Solute distance", "Mass / Volume", "Time × Rate"), 0, "Spot never beats the solvent front, so Rf ≤ 1.", "Rf = distance moved by dye / distance moved by solvent."),
      PracticeQuestion("pq_chem_02", "Atomic Orbitals", "WAEC", 2024, "How many electrons can completely fill the p-subshell of shell n=2?", listOf("6", "2", "10", "14"), 0, "Three p orbitals × 2e⁻ each.", "3 × 2 = 6 electrons (2p⁶)."),
      PracticeQuestion("pq_chem_03", "Periodic Trends", "JAMB", 2024, "Which element has the highest electronegativity?", listOf("Fluorine", "Oxygen", "Chlorine", "Sodium"), 0, "Top-right non-noble corner.", "Fluorine (4.0) is the most electronegative element."),
      PracticeQuestion("pq_chem_04", "Bonding", "WAEC", 2023, "Which substance conducts electricity in BOTH solid and molten states?", listOf("Magnesium", "Sodium chloride", "Diamond", "Sulphur"), 0, "Metallic delocalized electron sea.", "Magnesium has free mobile electrons in solid and liquid states."),
      PracticeQuestion("pq_chem_05", "Mole Concept", "NECO", 2023, "What volume of O₂ at STP is needed to completely burn 2 moles of C₂H₆? (2C₂H₆ + 7O₂ → 4CO₂ + 6H₂O)", listOf("156.8 dm³", "78.4 dm³", "44.8 dm³", "22.4 dm³"), 0, "7 moles O₂ per 2 moles C₂H₆.", "7 × 22.4 = 156.8 dm³."),
      PracticeQuestion("pq_chem_06", "Gas Laws", "WAEC", 2024, "Under identical conditions, which gas diffuses fastest?", listOf("NH₃ (M=17)", "CO (M=28)", "CO₂ (M=44)", "SO₂ (M=64)"), 0, "Lightest gas wins Graham's race.", "NH₃ has lowest molar mass (17)."),
      PracticeQuestion("pq_chem_07", "Titration", "JAMB", 2025, "Best indicator for titrating weak CH₃COOH against strong NaOH?", listOf("Phenolphthalein", "Methyl orange", "Methyl red", "Litmus"), 0, "Equivalence point pH is ~8.7 (alkaline).", "Phenolphthalein changes sharply at pH 8.3–10.0."),
      PracticeQuestion("pq_chem_08", "Salt Hydrolysis", "WAEC", 2022, "An aqueous solution of Na₂CO₃ has pH:", listOf("Above 7 (Alkaline)", "Exactly 7", "Below 7 (Acidic)", "Zero"), 0, "Strong base NaOH + Weak acid H₂CO₃.", "CO₃²⁻ hydrolyzes water producing OH⁻ → pH > 7."),
      PracticeQuestion("pq_chem_09", "Thermochemistry", "JAMB", 2024, "Breaking a covalent Cl−Cl bond is always:", listOf("Endothermic (ΔH > 0)", "Exothermic (ΔH < 0)", "Spontaneous at 0 K", "Nuclear"), 0, "Takes energy input to snap bonds.", "Bond breaking absorbs energy (+ΔH)."),
      PracticeQuestion("pq_chem_10", "Kinetics", "NECO", 2023, "Raising temperature by 10°C roughly doubles reaction rate mainly because:", listOf("Far more particles exceed Activation Energy Eₐ", "Activation energy Eₐ drops", "Equilibrium constant K_c halves", "Pressure drops"), 0, "Kinetic energy distribution shifts above Eₐ threshold.", "More colliding particles possess E ≥ Eₐ."),
      PracticeQuestion("pq_chem_11", "Equilibrium", "JAMB", 2022, "In Haber Process N₂ + 3H₂ ⇌ 2NH₃ (ΔH = −92 kJ), higher pressure:", listOf("Increases NH₃ yield", "Decreases NH₃ yield", "Has no effect", "Changes K_c"), 0, "4 gas moles → 2 gas moles.", "High pressure shifts equilibrium toward fewer gas moles (NH₃)."),
      PracticeQuestion("pq_chem_12", "Redox", "WAEC", 2025, "Find the oxidation state of Mn in KMnO₄.", listOf("+7", "+5", "+4", "+2"), 0, "+1 + x + 4(−2) = 0.", "x = +7."),
      PracticeQuestion("pq_chem_13", "Electrochemistry", "JAMB", 2021, "In any electrolytic or galvanic cell, Reduction ALWAYS occurs at the:", listOf("Cathode", "Anode", "Salt bridge", "External wire"), 0, "RED CAT.", "Cathode gains electrons (Reduction)."),
      PracticeQuestion("pq_chem_14", "Faraday Law", "WAEC", 2024, "Calculate charge required to deposit 1 mole of Mg²⁺ from molten MgCl₂.", listOf("193,000 C", "96,500 C", "48,250 C", "289,500 C"), 0, "2 Faradays = 2 × 96,500 C.", "193,000 C."),
      PracticeQuestion("pq_chem_15", "Hard Water", "NECO", 2023, "Which ion causes permanent hardness that boiling CANNOT remove?", listOf("Ca²⁺ paired with SO₄²⁻", "Na⁺ paired with Cl⁻", "Ca²⁺ paired with HCO₃⁻", "K⁺ paired with NO₃⁻"), 0, "CaSO₄ stays dissolved on boiling.", "CaSO₄ / MgSO₄ cause permanent hardness."),
      PracticeQuestion("pq_chem_16", "Gas Testing", "WAEC", 2021, "Which gas turns acidified K₂Cr₂O₇ paper from orange to green?", listOf("SO₂", "CO₂", "N₂", "O₂"), 0, "SO₂ is a strong reducing agent.", "SO₂ reduces Cr⁶⁺ (orange) to Cr³⁺ (green)."),
      PracticeQuestion("pq_chem_17", "Metals", "JAMB", 2024, "Which metal is extracted exclusively by electrolysis of its fused salt?", listOf("Aluminium", "Iron", "Zinc", "Copper"), 0, "Metals above Carbon in reactivity need electrolysis.", "Aluminium is extracted by electrolysis of Al₂O₃ in molten cryolite."),
      PracticeQuestion("pq_chem_18", "Alloys", "WAEC", 2022, "Bronze is an alloy composed mainly of:", listOf("Copper and Tin", "Copper and Zinc", "Iron and Carbon", "Lead and Antimony"), 0, "Sn = Tin.", "Bronze = Cu + Sn."),
      PracticeQuestion("pq_chem_19", "Hydrocarbons", "JAMB", 2025, "General formula of saturated open-chain alkanes is:", listOf("CₙH₂ₙ₊₂", "CₙH₂ₙ", "CₙH₂ₙ₋₂", "CₙH₂ₙ₊₁OH"), 0, "Try CH₄ (n=1): 2(1)+2 = 4.", "CₙH₂ₙ₊₂."),
      PracticeQuestion("pq_chem_20", "Petroleum", "NECO", 2023, "Converting heavy gas-oil fractions into smaller high-octane petrol molecules is called:", listOf("Cracking", "Reforming", "Polymerization", "Fermentation"), 0, "C−C bond thermal/catalytic cleavage.", "Thermal or catalytic cracking."),
      PracticeQuestion("pq_chem_21", "Esters", "JAMB", 2022, "Ethanol reacts with Methanoic acid to yield which ester?", listOf("Ethyl methanoate", "Methyl ethanoate", "Ethyl ethanoate", "Propyl methanoate"), 0, "Alcohol ethyl + Acid methanoate.", "Ethyl methanoate (HCOOC₂H₅)."),
      PracticeQuestion("pq_chem_22", "Biomolecules", "WAEC", 2024, "Hydrolysis of proteins ultimately yields:", listOf("Amino acids", "Glucose", "Fatty acids", "Ethene"), 0, "Peptide −CONH− bonds break to amino monomers.", "Amino acids."),
      PracticeQuestion("pq_chem_23", "Qualitative Analysis", "JAMB", 2021, "A salt forms white ppt with AgNO₃(aq) soluble in excess aqueous NH₃. The anion is:", listOf("Cl⁻", "SO₄²⁻", "I⁻", "NO₃⁻"), 0, "AgCl dissolves cleanly in NH₃(aq); AgI does not.", "Chloride ion, Cl⁻."),
      PracticeQuestion("pq_chem_24", "Nuclear Chemistry", "NECO", 2025, "Heavy nuclei splitting into lighter nuclei with huge energy release is:", listOf("Nuclear fission", "Nuclear fusion", "Beta decay", "Cracking"), 0, "Used in power reactors (²³⁵U).", "Nuclear fission."),
    )
}
