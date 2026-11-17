package com.example.data.curriculum

object ChemistryCurriculum {

  val track =
    SubjectTrack(
      id = "chemistry",
      title = "Chemistry Mastery",
      shortName = "Chemistry",
      tagline = "Atomic Structure, Stoichiometry, Volumetric Titration, Equilibrium & Organic Chemistry",
      examCoverage = "JAMB 40 Qs • WAEC Paper 1, 2 & Quantitative/Qualitative Practical",
      modules =
        listOf(
          CourseModule(
            id = "chem_mod_1",
            subjectId = "chemistry",
            indexLabel = "Module 01",
            title = "Foundations, Separation & Atomic Structure",
            summary = "Build from pure substances and mixture separation up to orbitals, periodic trends, and chemical bonding.",
            lessons =
              listOf(
                Lesson(
                  id = "chem_01_matter_separation",
                  moduleId = "chem_mod_1",
                  subjectId = "chemistry",
                  title = "States of Matter, Purity Criteria & Separation Techniques",
                  durationMinutes = 18,
                  syllabusTag = "JAMB Sec. 1.1–1.3 • WAEC Separation",
                  learningOutcomes =
                    listOf(
                      "Distinguish elements, compounds, and mixtures by energy change and fixed composition.",
                      "Select the exact separation technique (crystallization, fractional distillation, chromatography, separating funnel) for industrial mixtures.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1 (From Scratch): Pure Substances vs Mixtures",
                        explanation =
                          "Matter is anything with mass and volume. A COMPOUND (e.g., water H₂O) binds elements chemically in a fixed mass ratio with new properties; a MIXTURE (e.g., seawater or air) blends substances physically so components retain individual properties and vary in ratio.",
                        keyFormula = "\\text{Pure Solid: Sharp Melting Point}   |   \\text{Pure Liquid: Constant Boiling Point}",
                        examTip =
                          "Impurities ALWAYS LOWER the melting point and RAISE the boiling point over a wider temperature range — a top JAMB purity criterion!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Choosing the Right Separation Method",
                        explanation =
                          "Match technique to property difference: Insoluble solid + liquid -> Filtration; Heat-sensitive salt (CuSO₄·5H₂O) -> Crystallization (not evaporation to dryness!); Immiscible liquids (kerosene + water) -> Separating Funnel; Miscible liquids with close boiling points (ethanol 78 °C + water 100 °C) -> Fractional Distillation; Colored pigments -> Paper Chromatography.",
                        keyFormula = "R_f = \\frac{\\text{Distance moved by solute dye}}{\\text{Distance moved by solvent front}}",
                        examTip =
                          "Petroleum refining separates crude oil fractions (petrol, kerosene, diesel) by FRACTIONAL DISTILLATION based on boiling point differences!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Chromatography Rf Identification",
                        examSource = "WAEC SSCE 2023 • Paper 1 Q4",
                        problemStatement =
                          "In a paper chromatogram, a dye moves 4.5 cm while the ethanol solvent front travels 6.0 cm from the baseline. Calculate the R_f value.",
                        steps =
                          listOf(
                            "Step 1 — Recall R_f = distance traveled by dye / distance traveled by solvent front.",
                            "Step 2 — Substitute: R_f = 4.5 cm / 6.0 cm = 0.75.",
                          ),
                        finalAnswer = "0.75 (dimensionless)",
                        examinerTrap = "R_f can NEVER exceed 1.0 because solute never outruns the solvent front!",
                      ),
                      WorkedExample(
                        title = "Separating Ammonium Chloride, Sand & Salt",
                        examSource = "JAMB UTME 2022 • Chemistry Q3",
                        problemStatement =
                          "A mixture contains ammonium chloride (NH₄Cl), sodium chloride (NaCl), and fine sand. Outline the correct separation order.",
                        steps =
                          listOf(
                            "Step 1 — Gently heat mixture: NH₄Cl SUBLIMES directly from solid to vapor and re-deposits on a cool funnel.",
                            "Step 2 — Add water to remaining NaCl + sand: NaCl dissolves; filter out insoluble sand.",
                            "Step 3 — Evaporate filtrate to recover dry NaCl crystals.",
                          ),
                        finalAnswer = "Sublimation -> Dissolution -> Filtration -> Evaporation",
                        examinerTrap = "Do NOT add water first or both NH₄Cl and NaCl will dissolve together!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Dangote Refinery Crude Oil Fractionation",
                        industryDomain = "Petrochemical Refining",
                        narrative =
                          "Crude petroleum enters a towering fractionating column heated to ~350 °C: heavy bitumen stays at the bottom while kerosene, diesel, and petrol vapors rise and condense cleanly at cooler upper trays.",
                        examConnection = "Directly tests fractional distillation temperature gradients in WAEC.",
                      ),
                      RealLifeScenario(
                        title = "NAFDAC Food Dye Safety Screening",
                        industryDomain = "Analytical Quality Control",
                        narrative =
                          "Regulators spot prohibited food colorants in drinks by matching ascending chromatogram R_f values against certified dye standards.",
                        examConnection = "Demonstrates chromatography and pure-compound identity.",
                      ),
                    ),
                  labType = PracticalLabType.TITRATION_PH,
                  labBrief =
                    "Use the analytical solution bench to observe how solute concentration affects solution indicators cleanly.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_chem_01_1",
                        topic = "Separation Techniques",
                        examType = "JAMB",
                        year = 2024,
                        question = "Which pair of substances is best separated using a separating funnel?",
                        options = listOf("Ethanol and water", "Kerosene and water", "Salt and sand", "Iodine and sand"),
                        correctIndex = 1,
                        hint = "A separating funnel separates two immiscible liquids of different densities.",
                        stepByStepSolution = "Kerosene and water do not mix (immiscible) and form two distinct layers in a separating funnel.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_01_2",
                        topic = "Purity & Matter",
                        examType = "WAEC",
                        year = 2023,
                        question = "How does the presence of an impurity affect the boiling and freezing points of pure water?",
                        options =
                          listOf(
                            "Lowers boiling point, raises freezing point",
                            "Raises boiling point, lowers freezing point",
                            "Lowers both boiling and freezing points",
                            "Has no effect on either",
                          ),
                        correctIndex = 1,
                        hint = "Think of adding salt to boiling water or icy roads.",
                        stepByStepSolution = "Dissolved impurities elevate the boiling point above 100 °C and depress the freezing point below 0 °C.",
                        waecMarkingScheme = "A1: Raises b.p. and lowers f.p./m.p.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_01_3",
                        topic = "Separation Techniques",
                        examType = "JAMB",
                        year = 2022,
                        question = "Pure hydrated copper(II) tetraoxosulfate(VI) crystals are best recovered from solution by:",
                        options = listOf("Evaporation to dryness", "Crystallization", "Sublimation", "Decantation"),
                        correctIndex = 1,
                        hint = "Strong heating decomposes hydrated crystals and drives off water of crystallization.",
                        stepByStepSolution = "Crystallization allows slow cooling from a concentrated hot solution so CuSO₄·5H₂O crystals form intact.",
                      ),
                    ),
                ),
                Lesson(
                  id = "chem_02_atomic_periodic",
                  moduleId = "chem_mod_1",
                  subjectId = "chemistry",
                  title = "Atomic Structure, Orbitals, Isotopes & Periodic Trends",
                  durationMinutes = 22,
                  syllabusTag = "JAMB Sec. 2 • WAEC Atomic Theory",
                  learningOutcomes =
                    listOf(
                      "Determine protons, neutrons, electrons, relative atomic mass from isotopes, and 1s²2s²2p⁶ configurations.",
                      "Predict ionization energy, atomic radius, and electronegativity across Periods and down Groups.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Subatomic Particles & Isotopes",
                        explanation =
                          "In ᴬ_Z X, Atomic Number Z = number of protons (defines the element!). Mass Number A = protons + neutrons. Neutral atoms have electrons = protons; cations lose electrons (+), anions gain electrons (−). ISOTOPES have equal Z (identical chemical properties) but different neutrons A−Z.",
                        keyFormula = "A_r = \\frac{(\\%_1 \\times m_1) + (\\%_2 \\times m_2)}{100}   |   1s^2\\,2s^2\\,2p^6\\,3s^2\\,3p^6",
                        examTip =
                          "Remember Chromium (Z=24: [Ar]3d⁵4s¹) and Copper (Z=29: [Ar]3d¹⁰4s¹) promote a 4s electron for extra half-filled or full-d stability!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Quantum Orbitals & Periodic Table Trends",
                        explanation =
                          "Aufbau fills lower energy levels first; Hund's rule places electrons singly with parallel spins in degenerate orbitals before pairing. Moving ACROSS a Period (left -> right): nuclear charge pulls shells closer, so Atomic Radius DECREASES while Ionization Energy & Electronegativity INCREASE (Fluorine = highest electronegativity!). Moving DOWN a Group: extra shells shield valence electrons, so Atomic Radius INCREASES and Ionization Energy DECREASES.",
                        keyFormula = "\\text{Across Period: } r\\downarrow,\\ IE\\uparrow,\\ EN\\uparrow   |   \\text{Down Group: } r\\uparrow,\\ IE\\downarrow",
                        examTip =
                          "Alkali metals (Group 1) become MORE reactive down the group (easier to lose outer e⁻), whereas Halogens (Group 7) become LESS reactive down the group!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Chlorine Relative Atomic Mass from Isotopes",
                        examSource = "JAMB UTME 2024 • Chemistry Q6",
                        problemStatement =
                          "Naturally occurring chlorine contains 75% ³⁵Cl and 25% ³⁷Cl. Calculate its relative atomic mass.",
                        steps =
                          listOf(
                            "Step 1 — Weighted average: A_r = [(75 × 35) + (25 × 37)] / 100.",
                            "Step 2 — (2625 + 925) / 100 = 3550 / 100 = 35.5.",
                          ),
                        finalAnswer = "35.5",
                        examinerTrap = "Do NOT take the simple midpoint (35+37)/2 = 36 unless abundances are 50% each!",
                      ),
                      WorkedExample(
                        title = "Identifying Group & Period from s/p Configuration",
                        examSource = "WAEC SSCE 2022 • Paper 1 Q9",
                        problemStatement =
                          "Element Y has electronic configuration 1s² 2s² 2p⁶ 3s² 3p⁴. Determine its atomic number, period, group, and common ion.",
                        steps =
                          listOf(
                            "Step 1 — Total electrons = 2 + 2 + 6 + 2 + 4 = 16 (Sulfur).",
                            "Step 2 — Highest principal shell n = 3 -> Period 3.",
                            "Step 3 — Valence electrons in shell 3 = 2 + 4 = 6 -> Group VI (needs 2 electrons -> Y²⁻).",
                          ),
                        finalAnswer = "Z = 16, Period 3, Group VI, ion Y²⁻",
                        examinerTrap = "Count ALL electrons in the highest n shell (both s and p!), not just p electrons.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Lithium-Ion Battery Anodes & Ionization Energy",
                        industryDomain = "Electrochemical Energy Storage",
                        narrative =
                          "Lithium (top of Group 1) combines low atomic mass with tiny ionization energy, packing dense electrical charge per gram inside smartphone and EV cells.",
                        examConnection = "Demonstrates Group 1 low ionization energy and reactivity.",
                      ),
                      RealLifeScenario(
                        title = "Technetium-99m Diagnostic Nuclear Tracers",
                        industryDomain = "Hospital Radiology",
                        narrative =
                          "Synthetic radioisotopes undergo controlled nuclear emission while obeying exact chemical reactivity determined solely by proton number Z.",
                        examConnection = "Reinforces why isotopes share identical chemical reactions.",
                      ),
                    ),
                  labType = PracticalLabType.TITRATION_PH,
                  labBrief =
                    "Observe how periodic group elements form acidic vs basic oxides and hydroxides in aqueous solution.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_chem_02_1",
                        topic = "Atomic Structure",
                        examType = "JAMB",
                        year = 2025,
                        question = "How many electrons, protons, and neutrons are present in the ion ²⁷₁₃Al³⁺?",
                        options =
                          listOf(
                            "13 e, 13 p, 14 n",
                            "10 e, 13 p, 14 n",
                            "16 e, 13 p, 14 n",
                            "10 e, 14 p, 13 n",
                          ),
                        correctIndex = 1,
                        hint = "A +3 charge means the neutral atom (13 e⁻) lost 3 electrons; neutrons = A − Z.",
                        stepByStepSolution = "Protons = 13; Neutrons = 27 − 13 = 14; Electrons = 13 − 3 = 10.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_02_2",
                        topic = "Periodicity",
                        examType = "WAEC",
                        year = 2023,
                        question = "Which element across Period 3 has the highest first ionization energy?",
                        options = listOf("Sodium", "Aluminum", "Chlorine", "Argon"),
                        correctIndex = 3,
                        hint = "Noble gases possess stable full octets and highest effective nuclear attraction.",
                        stepByStepSolution = "Argon (Group 0/18) has a complete 3p⁶ octet across Period 3, giving the highest first ionization energy.",
                        waecMarkingScheme = "A1: Argon.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_02_3",
                        topic = "Orbitals",
                        examType = "JAMB",
                        year = 2021,
                        question = "The maximum number of electrons accommodated by a single d-subshell (5 orbitals) is:",
                        options = listOf("2", "6", "10", "14"),
                        correctIndex = 2,
                        hint = "Each orbital holds at most 2 electrons (Pauli Exclusion Principle).",
                        stepByStepSolution = "s holds 2 (1×2), p holds 6 (3×2), d holds 10 (5×2), f holds 14 (7×2).",
                      ),
                    ),
                ),
                Lesson(
                  id = "chem_03_bonding_shapes",
                  moduleId = "chem_mod_1",
                  subjectId = "chemistry",
                  title = "Chemical Bonding, Molecular Shapes & Intermolecular Forces",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. 4 • WAEC Bonding",
                  learningOutcomes =
                    listOf(
                      "Differentiate ionic (electrovalent), covalent, dative (coordinate), and metallic bonding.",
                      "Predict VSEPR molecular shapes and explain anomalous water boiling points via hydrogen bonding.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Electrovalent vs Covalent vs Coordinate Bonding",
                        explanation =
                          "IONIC bonding transfers valence electrons completely from metal to non-metal (NaCl) forming crystalline lattices that conduct ONLY when molten or aqueous. COVALENT bonding shares electron pairs between non-metals (CO₂, CH₄), giving low-melting molecules. COORDINATE (Dative) covalent bonding occurs when ONE atom supplies BOTH shared electrons — e.g., NH₃ donating its lone pair to H⁺ to form NH₄⁺!",
                        keyFormula = "NH_3 + H^+ \\rightarrow NH_4^+\\ (\\text{Coordinate/Dative})   |   H_2O\\text{: Bent } 104.5^\\circ",
                        examTip =
                          "NH₄Cl is a classic WAEC favorite containing ALL THREE bond types: covalent N-H, dative N->H⁺, AND ionic NH₄⁺Cl⁻!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: VSEPR Shapes & Hydrogen Bonding",
                        explanation =
                          "Electron pairs repel to stay maximally apart: 2 bond pairs (0 lone pairs) -> Linear 180° (CO₂, BeCl₂); 3 bond pairs -> Trigonal planar 120° (BF₃); 4 bond pairs -> Tetrahedral 109.5° (CH₄); 3 bond + 1 lone -> Trigonal pyramidal 107° (NH₃); 2 bond + 2 lone -> Bent/V-shaped 104.5° (H₂O). HYDROGEN BONDING (H attached to N, O, or F) makes H₂O boil at 100 °C instead of −80 °C like H₂S!",
                        keyFormula = "\\text{H-Bond Elements: F, O, N}   |   \\text{Boiling Pt: } H_2O (100^\\circ\\text{C}) \\gg H_2S (-60^\\circ\\text{C})",
                        examTip =
                          "Diamond has sp³ tetrahedral bonds (very hard, insulator); Graphite has sp² layers with delocalized π electrons (slippery conductor)!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "All Bond Types in Ammonium Chloride",
                        examSource = "JAMB UTME 2023 • Chemistry Q11",
                        problemStatement =
                          "Identify the types of chemical bonds present in solid ammonium trioxonitrate(V) / ammonium chloride (NH₄Cl).",
                        steps =
                          listOf(
                            "Step 1 — Inside NH₄⁺ ion: 3 normal covalent N−H bonds + 1 dative (coordinate) bond from Nitrogen's lone pair to H⁺.",
                            "Step 2 — Between NH₄⁺ and Cl⁻ ions: electrostatic Ionic (electrovalent) attraction.",
                          ),
                        finalAnswer = "Covalent, Coordinate (dative), and Ionic bonds",
                        examinerTrap = "Don't stop at 'ionic' just because it is a salt — inspect the polyatomic NH₄⁺ ion!",
                      ),
                      WorkedExample(
                        title = "Why Ethanol Dissolves in Water While Ethane Does Not",
                        examSource = "WAEC SSCE 2021 • Paper 2 Q3",
                        problemStatement =
                          "Explain briefly why ethanol (C₂H₅OH, b.p. 78 °C) is completely miscible with water whereas ethane (C₂H₆, b.p. −89 °C) is insoluble gas.",
                        steps =
                          listOf(
                            "Step 1 — Ethanol carries a polar −OH hydroxyl group.",
                            "Step 2 — It forms strong intermolecular HYDROGEN BONDS both with neighboring ethanol molecules (high b.p.) and with H₂O molecules (complete solubility).",
                          ),
                        finalAnswer = "Intermolecular hydrogen bonding with H₂O",
                        examinerTrap = "Mention HYDROGEN BONDING specifically, not just 'dispersion forces'.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Graphite Motor Brushes vs Diamond Drill Bits",
                        industryDomain = "Materials Science & Mining",
                        narrative =
                          "Both diamond and graphite are pure carbon! Diamond's rigid 3D tetrahedral sp³ lattice cuts rock, whereas graphite's flat sliding sheets carry current inside electric motors.",
                        examConnection = "Tests carbon allotropy and hybridization.",
                      ),
                      RealLifeScenario(
                        title = "Why Ice Floats & Protects Aquatic Fish",
                        industryDomain = "Hydrology & Ecology",
                        narrative =
                          "Hydrogen bonds lock ice into an open hexagonal lattice less dense than liquid water, allowing ice to float and insulate ponds underneath.",
                        examConnection = "Standard WAEC question linking hydrogen bonding to ice density.",
                      ),
                    ),
                  labType = PracticalLabType.GAS_LAW_PISTON,
                  labBrief =
                    "Use the Kinetic Gas bench to see how real gas interactions deviate from ideal non-interacting spheres.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_chem_03_1",
                        topic = "Chemical Bonding",
                        examType = "JAMB",
                        year = 2024,
                        question = "What is the geometric shape of a methane (CH₄) molecule?",
                        options = listOf("Linear", "Trigonal planar", "Tetrahedral", "Bent"),
                        correctIndex = 2,
                        hint = "Carbon forms 4 identical single bond pairs with 0 lone pairs.",
                        stepByStepSolution = "Four repelling C−H bond pairs arrange symmetrically into a regular tetrahedron (109.5°).",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_03_2",
                        topic = "Intermolecular Forces",
                        examType = "WAEC",
                        year = 2023,
                        question = "Water has a much higher boiling point than hydrogen sulfide (H₂S) mainly because water molecules possess:",
                        options = listOf("Ionic bonds", "Hydrogen bonds", "Metallic bonds", "Stronger covalent bonds"),
                        correctIndex = 1,
                        hint = "Oxygen is small and strongly electronegative, bonding to H.",
                        stepByStepSolution = "Strong intermolecular hydrogen bonds between H₂O molecules require extra thermal energy to break during boiling.",
                        waecMarkingScheme = "A1: Hydrogen bonding between water molecules.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_03_3",
                        topic = "Chemical Bonding",
                        examType = "JAMB",
                        year = 2022,
                        question = "Which bond forms when ammonia (NH₃) reacts with a proton (H⁺) to form NH₄⁺?",
                        options = listOf("Electrovalent", "Coordinate covalent", "Hydrogen", "Van der Waals"),
                        correctIndex = 1,
                        hint = "Nitrogen donates both electrons in the new N−H bond.",
                        stepByStepSolution = "Ammonia's lone pair is donated entirely to empty H⁺ orbital -> Coordinate (dative) covalent bond.",
                      ),
                    ),
                ),
              ),
          ),
          CourseModule(
            id = "chem_mod_2",
            subjectId = "chemistry",
            indexLabel = "Module 02",
            title = "Stoichiometry, Gas Laws, Solutions & Volumetric Titration",
            summary = "Master the mole concept, empirical formulas, kinetic gas equations, solubility curves, and acid-base titrations.",
            lessons =
              listOf(
                Lesson(
                  id = "chem_04_mole_stoichiometry",
                  moduleId = "chem_mod_2",
                  subjectId = "chemistry",
                  title = "The Mole Concept, Empirical Formula & Limiting Reactant",
                  durationMinutes = 22,
                  syllabusTag = "JAMB Sec. 3.1 • WAEC Stoichiometry",
                  learningOutcomes =
                    listOf(
                      "Convert cleanly between mass, moles, Avogadro particles (6.02 × 10²³), and STP molar gas volume (22.4 dm³).",
                      "Derive empirical and molecular formulas and identify limiting reactants in balanced equations.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1 (From Scratch): The Central Mole Bridge",
                        explanation =
                          "Chemists weigh grams in the lab, but atoms react in integer mole counts! One mole of ANY substance equals 6.02 × 10²³ particles. Always convert given quantities INTO MOLES first, apply the balanced equation's mole ratio, then convert OUT to mass or gas volume!",
                        keyFormula = "n = \\frac{m}{M} = \\frac{N}{6.02\\times 10^{23}} = \\frac{V_{\\text{STP}}}{22.4\\text{ dm}^3} = C \\cdot V(\\text{dm}^3)",
                        examTip =
                          "1 dm³ = 1000 cm³ = 1 Liter! Whenever volume is given in cm³, divide by 1000 (or 22,400 cm³ at STP)!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Empirical vs Molecular Formula",
                        explanation =
                          "Empirical formula gives the SIMPLEST whole-number atom ratio (e.g., CH₂O); Molecular formula gives the TRUE atom count (e.g., Glucose C₆H₁₂O₆ = (CH₂O)₆). Remember Vapor Density (VD) gives Molar Mass directly via M = 2 × VD!",
                        keyFormula = "(\\text{Empirical Mass}) \\times n = M_{\\text{molecular}}   |   M = 2 \\times \\text{Vapour Density}",
                        examTip =
                          "If a mole ratio turns out 1 : 1.5, multiply BOTH by 2 (giving 2 : 3); NEVER round 1.5 to 2!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Empirical & Molecular Formula from % Composition",
                        examSource = "WAEC SSCE 2023 • Paper 2 Q2",
                        problemStatement =
                          "A hydrocarbon contains 80% Carbon and 20% Hydrogen by mass, with a vapour density of 15. Find its molecular formula (C=12, H=1).",
                        steps =
                          listOf(
                            "Step 1 — Divide % by atomic masses: C = 80/12 = 6.67 mol; H = 20/1 = 20 mol.",
                            "Step 2 — Divide by smallest (6.67): C : H = 1 : 3 => Empirical formula = CH₃ (Empirical mass = 12 + 3 = 15).",
                            "Step 3 — Molecular mass M = 2 × VD = 2 × 15 = 30. Ratio n = 30 / 15 = 2.",
                            "Step 4 — Molecular formula = (CH₃)₂ = C₂H₆ (Ethane).",
                          ),
                        finalAnswer = "C₂H₆ (Ethane)",
                        examinerTrap = "Forgetting M = 2 × Vapour Density makes students choose CH₃ instead of C₂H₆!",
                      ),
                      WorkedExample(
                        title = "CO₂ Volume Liberated at STP from CaCO₃",
                        examSource = "JAMB UTME 2022 • Chemistry Q8",
                        problemStatement =
                          "What volume of CO₂ at STP is produced when 25 g of CaCO₃ reacts completely with excess HCl? (Ca=40, C=12, O=16; Molar gas vol = 22.4 dm³)",
                        steps =
                          listOf(
                            "Step 1 — Molar mass CaCO₃ = 40 + 12 + 48 = 100 g/mol.",
                            "Step 2 — Moles CaCO₃ = 25 / 100 = 0.25 mol.",
                            "Step 3 — CaCO₃ + 2HCl -> CaCl₂ + H₂O + CO₂ (1:1 ratio => 0.25 mol CO₂).",
                            "Step 4 — Volume at STP = 0.25 × 22.4 = 5.6 dm³.",
                          ),
                        finalAnswer = "5.6 dm³",
                        examinerTrap = "Convert dm³ to cm³ (× 1000 = 5600 cm³) if options are listed in cm³!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Vehicle Airbag Sodium Azide Gas Deployment",
                        industryDomain = "Automotive Safety Chemistry",
                        narrative =
                          "Airbag engineers weigh exact gram pellets of NaN₃ so thermal decomposition yields precisely ~67 liters of N₂ gas in 30 milliseconds — too little fails to cushion, too much bursts the bag.",
                        examConnection = "Direct real-life application of mass-to-gas-volume stoichiometry.",
                      ),
                      RealLifeScenario(
                        title = "NPK Agricultural Fertilizer Blending",
                        industryDomain = "Agronomy & Chemical Manufacturing",
                        narrative =
                          "Soil chemists compute exact % mass of Nitrogen in ammonium nitrate vs urea using molar mass ratios to match crop needs without scorching roots.",
                        examConnection = "Tests percentage composition calculations.",
                      ),
                    ),
                  labType = PracticalLabType.GAS_LAW_PISTON,
                  labBrief =
                    "Use the Ideal Gas Lab below to inspect how 1 mole of gas scales proportionally in volume as Kelvin temperature changes.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_chem_04_1",
                        topic = "Mole Concept",
                        examType = "JAMB",
                        year = 2024,
                        question = "What is the molar mass of a gas whose vapour density is 22?",
                        options = listOf("11 g/mol", "22 g/mol", "44 g/mol", "88 g/mol"),
                        correctIndex = 2,
                        hint = "Molar Mass = 2 × Vapour Density.",
                        stepByStepSolution = "M = 2 × 22 = 44 g/mol (e.g., CO₂ or propane).",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_04_2",
                        topic = "Empirical Formula",
                        examType = "WAEC",
                        year = 2023,
                        question = "An oxide of sulfur contains 50% sulfur and 50% oxygen by mass. What is its empirical formula? (S=32, O=16)",
                        options = listOf("SO", "SO₂", "SO₃", "S₂O₃"),
                        correctIndex = 1,
                        hint = "Moles: S = 50/32, O = 50/16.",
                        stepByStepSolution = "n(S) = 50/32 = 1.5625; n(O) = 50/16 = 3.125. Divide by 1.5625 => S : O = 1 : 2 => SO₂.",
                        waecMarkingScheme = "M1: mole division; A1: SO₂.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_04_3",
                        topic = "Stoichiometry",
                        examType = "JAMB",
                        year = 2023,
                        question = "How many moles of oxygen molecules are needed for complete combustion of 2 moles of methane (CH₄)?",
                        options = listOf("1 mol", "2 mol", "4 mol", "6 mol"),
                        correctIndex = 2,
                        hint = "Balance CH₄ + ?O₂ -> CO₂ + 2H₂O first.",
                        stepByStepSolution = "CH₄ + 2O₂ -> CO₂ + 2H₂O. 1 mol CH₄ needs 2 mol O₂, so 2 mol CH₄ need 4 mol O₂.",
                      ),
                    ),
                ),
                Lesson(
                  id = "chem_05_gas_laws_kinetic",
                  moduleId = "chem_mod_2",
                  subjectId = "chemistry",
                  title = "Ideal Gas Equation, Dalton's Partial Pressure & Graham's Law",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. 3.2 • WAEC Kinetic Theory",
                  learningOutcomes =
                    listOf(
                      "Apply Boyle's, Charles', General Gas (P₁V₁/T₁ = P₂V₂/T₂), and Ideal Gas (PV = nRT) laws in Kelvin.",
                      "Solve Graham's Law of Diffusion problems relating rates and times to √M.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Combined & Ideal Gas Laws (Kelvin Mandatory!)",
                        explanation =
                          "Gas molecules move in constant random elastic collisions. Volume varies inversely with pressure (Boyle's Law P₁V₁ = P₂V₂) and directly with ABSOLUTE Kelvin temperature (Charles' Law V₁/T₁ = V₂/T₂). Combined: P₁V₁/T₁ = P₂V₂/T₂, or PV = nRT where R = 0.0821 atm·dm³/(mol·K) = 8.314 J/(mol·K).",
                        keyFormula = "\\frac{P_1 V_1}{T_1} = \\frac{P_2 V_2}{T_2}   |   PV = nRT   |   T(\\text{K}) = t(^\\circ\\text{C}) + 273",
                        examTip =
                          "Water-collected gas pressure MUST subtract Saturated Vapour Pressure (SVP): P_dry_gas = P_total − SVP!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Graham's Diffusion & Avogadro's Law",
                        explanation =
                          "Lighter gas molecules move faster at the same temperature! Graham's Law: Rate₁ / Rate₂ = √(M₂ / M₁) = √(d₂ / d₁). For EQUAL VOLUMES of gas, faster rate means LESS time: t₂ / t₁ = √(M₂ / M₁).",
                        keyFormula = "\\frac{R_1}{R_2} = \\frac{t_2}{t_1} = \\sqrt{\\frac{M_2}{M_1}} = \\sqrt{\\frac{\\text{VD}_2}{\\text{VD}_1}}",
                        examTip =
                          "Methane (CH₄, M=16) diffuses twice as fast as SO₂ (M=64) because √(64/16) = 2!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Graham's Law Unknown Gas Molar Mass",
                        examSource = "JAMB UTME 2024 • Chemistry Q10",
                        problemStatement =
                          "100 cm³ of Oxygen (O₂, M = 32) diffuses in 20 s. Under identical conditions, 100 cm³ of gas X diffuses in 10 s. Find the molar mass of X.",
                        steps =
                          listOf(
                            "Step 1 — Equal volumes: t_X / t_O₂ = √(M_X / M_O₂).",
                            "Step 2 — 10 / 20 = √(M_X / 32) => 1/2 = √(M_X / 32).",
                            "Step 3 — Square both sides: 1/4 = M_X / 32 => M_X = 8 g/mol.",
                          ),
                        finalAnswer = "8 g/mol",
                        examinerTrap = "Check direction first: X took HALF the time (faster!), so X MUST be lighter than O₂ (M < 32)!",
                      ),
                      WorkedExample(
                        title = "General Gas Equation Volume Conversion",
                        examSource = "WAEC SSCE 2022 • Paper 1 Q14",
                        problemStatement =
                          "A gas occupies 400 cm³ at 27 °C and 750 mmHg. What volume will it occupy at STP (0 °C and 760 mmHg)?",
                        steps =
                          listOf(
                            "Step 1 — Convert temperatures: T₁ = 27 + 273 = 300 K; T₂ = 0 + 273 = 273 K.",
                            "Step 2 — V₂ = (P₁ V₁ T₂) / (P₂ T₁) = (750 × 400 × 273) / (760 × 300).",
                            "Step 3 — Simplify: 400/300 = 4/3 -> (750 × 4 × 91) / 760 ≈ 359.2 cm³.",
                          ),
                        finalAnswer = "359.2 cm³",
                        examinerTrap = "Using 27 °C instead of 300 K blows up the answer completely!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "CNG Auto Tanks & Cooking Gas Diffusion Safety",
                        industryDomain = "Energy & Industrial Safety",
                        narrative =
                          "Compressed Natural Gas cylinders experience elevated pressure in hot midday sun (P ∝ T), while leaked methane disperses faster through kitchen vents than heavier butane/propane LPG.",
                        examConnection = "Directly connects Gay-Lussac's pressure law and Graham's diffusion law.",
                      ),
                      RealLifeScenario(
                        title = "Deep-Sea Diver Scuba Oxygen/Helium Mixtures",
                        industryDomain = "Hyperbaric Physiology",
                        narrative =
                          "Divers breathe Heliox because Helium (M=4) diffuses rapidly without nitrogen narcosis and obeys Dalton's partial pressure law at high ocean depths.",
                        examConnection = "Tests Dalton's Law P_total = P₁ + P₂.",
                      ),
                    ),
                  labType = PracticalLabType.GAS_LAW_PISTON,
                  labBrief =
                    "Run the Ideal Gas & Graham Diffusion Lab below: raise temperature in Kelvin, compress cylinder pressure, and compare H₂ vs CH₄ vs CO₂ effusion speed!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_chem_05_1",
                        topic = "Gas Laws",
                        examType = "JAMB",
                        year = 2025,
                        question = "Arrange the gases H₂, CH₄, CO₂, and SO₂ in order of INCREASING rate of diffusion (H=1, C=12, O=16, S=32):",
                        options =
                          listOf(
                            "H₂ < CH₄ < CO₂ < SO₂",
                            "SO₂ < CO₂ < CH₄ < H₂",
                            "CO₂ < SO₂ < CH₄ < H₂",
                            "CH₄ < H₂ < CO₂ < SO₂",
                          ),
                        correctIndex = 1,
                        hint = "Heaviest molar mass diffuses SLOWEST.",
                        stepByStepSolution = "Molar masses: SO₂(64) > CO₂(44) > CH₄(16) > H₂(2). Increasing diffusion speed is heaviest -> lightest: SO₂ < CO₂ < CH₄ < H₂.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_05_2",
                        topic = "Gas Laws",
                        examType = "WAEC",
                        year = 2021,
                        question = "At constant temperature, doubling the pressure exerted on a fixed mass of ideal gas changes its volume to:",
                        options = listOf("Four times", "Double", "Half", "One-quarter"),
                        correctIndex = 2,
                        hint = "Boyle's Law: P₁V₁ = P₂V₂.",
                        stepByStepSolution = "Since V ∝ 1/P at constant T, doubling P cuts volume in half.",
                        waecMarkingScheme = "A1: Half of original volume.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_05_3",
                        topic = "Gas Laws",
                        examType = "JAMB",
                        year = 2023,
                        question = "50 cm³ of oxygen is collected over water at 20 °C and 765 mmHg. If water's saturated vapour pressure at 20 °C is 17 mmHg, what is the pressure of dry oxygen?",
                        options = listOf("782 mmHg", "765 mmHg", "748 mmHg", "731 mmHg"),
                        correctIndex = 2,
                        hint = "Dalton's Law: P_total = P_gas + SVP.",
                        stepByStepSolution = "P_O2 = 765 − 17 = 748 mmHg.",
                      ),
                    ),
                ),
                Lesson(
                  id = "chem_06_acids_titration_ph",
                  moduleId = "chem_mod_2",
                  subjectId = "chemistry",
                  title = "Acids, Bases, Salts, pH & Volumetric Titration",
                  durationMinutes = 24,
                  syllabusTag = "JAMB Sec. 7 • WAEC Practical Titration",
                  learningOutcomes =
                    listOf(
                      "Apply C_A·V_A / (C_B·V_B) = n_A / n_B and dilution law C₁V₁ = C₂V₂ with balanced equations.",
                      "Select Phenolphthalein vs Methyl Orange from strong/weak neutralization endpoints and compute pH = −log₁₀[H⁺].",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: pH Logarithmic Scale & Water Ionic Product",
                        explanation =
                          "Acids yield H⁺/H₃O⁺ ions (pH < 7); Bases accept H⁺ or yield OH⁻ (pH > 7); Neutral water has pH = 7. Notice every 1-unit drop in pH means [H⁺] increases TENFOLD! Remember pH + pOH = 14 at 25 °C.",
                        keyFormula = "\\text{pH} = -\\log_{10}[H^+]   |   \\text{pOH} = -\\log_{10}[OH^-]   |   \\text{pH} + \\text{pOH} = 14",
                        examTip =
                          "For H₂SO₄ (dibasic!), 0.005 M H₂SO₄ gives [H⁺] = 2 × 0.005 = 0.01 M = 10⁻² M => pH = 2!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: WAEC Volumetric Titration & Indicator Matching",
                        explanation =
                          "At equivalence, moles reacted match balanced coefficients: (C_A·V_A)/(C_B·V_B) = n_A/n_B. Choose indicator to span equivalence pH: Strong Acid + Weak Base (HCl + Na₂CO₃, endpoint pH < 7) -> METHYL ORANGE (pink in acid, yellow in base); Weak Acid + Strong Base (CH₃COOH + NaOH, endpoint pH > 7) -> PHENOLPHTHALEIN (colorless in acid, pink in base).",
                        keyFormula = "\\frac{C_A V_A}{C_B V_B} = \\frac{n_A}{n_B}   |   \\text{Salt Hydrolysis determines endpoint pH}",
                        examTip =
                          "Na₂CO₃ solution turns red litmus BLUE because CO₃²⁻ hydrolyzes with water to leave excess OH⁻ ions!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Titration of HCl against Sodium Trioxocarbonate(IV)",
                        examSource = "WAEC SSCE 2022 • Practical / Paper 2",
                        problemStatement =
                          "25.0 cm³ of 0.10 mol/dm³ Na₂CO₃ required 20.0 cm³ of HCl for complete neutralization using Methyl Orange. Find the HCl concentration.",
                        steps =
                          listOf(
                            "Step 1 — Balance: Na₂CO₃ + 2HCl -> 2NaCl + H₂O + CO₂ (n_A = 2, n_B = 1).",
                            "Step 2 — (C_A × 20.0) / (0.10 × 25.0) = 2 / 1.",
                            "Step 3 — C_A = (2 × 0.10 × 25.0) / 20.0 = 5.0 / 20.0 = 0.25 mol/dm³.",
                          ),
                        finalAnswer = "0.25 mol/dm³",
                        examinerTrap = "Forgetting n_A = 2 for HCl + Na₂CO₃ cuts your answer in half!",
                      ),
                      WorkedExample(
                        title = "pH of Strong Alkali (NaOH) Solution",
                        examSource = "JAMB UTME 2024 • Chemistry Q22",
                        problemStatement =
                          "Calculate the pH of a 0.01 mol/dm³ solution of sodium hydroxide (NaOH) at 25 °C.",
                        steps =
                          listOf(
                            "Step 1 — [OH⁻] = 0.01 = 10⁻² mol/dm³.",
                            "Step 2 — pOH = −log₁₀(10⁻²) = 2.",
                            "Step 3 — pH = 14 − pOH = 14 − 2 = 12.",
                          ),
                        finalAnswer = "pH = 12",
                        examinerTrap = "Taking −log₁₀(0.01) directly gives pH = 2, which would wrongly make NaOH a strong acid!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "River Water Alkalinity & Human Blood Buffering",
                        industryDomain = "Environmental & Clinical Chemistry",
                        narrative =
                          "Water engineers titrate raw river intake with H₂SO₄ before alum dosing, while human arterial blood uses HCO₃⁻/H₂CO₃ buffers to clamp pH tightly between 7.35 and 7.45.",
                        examConnection = "Connects carbonate salt hydrolysis and buffer pH control.",
                      ),
                      RealLifeScenario(
                        title = "Agricultural Soil Liming with Ca(OH)₂",
                        industryDomain = "Soil Agronomy",
                        narrative =
                          "Acidic laterite soils (pH ~4.8) lock out nutrients until farmers spread slaked lime Ca(OH)₂ to neutralize excess H⁺ up to optimal pH 6.5.",
                        examConnection = "Classic WAEC practical essay example of neutralization.",
                      ),
                    ),
                  labType = PracticalLabType.TITRATION_PH,
                  labBrief =
                    "Run the virtual titration burette below: drip 0.10 M NaOH into 25 mL of HCl or CH₃COOH and inspect the steep pH equivalence jump!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_chem_06_1",
                        topic = "Acids & pH",
                        examType = "JAMB",
                        year = 2024,
                        question = "What is the pH of a 0.0005 mol/dm³ solution of H₂SO₄ (assuming full ionization)?",
                        options = listOf("1", "2", "3", "4"),
                        correctIndex = 2,
                        hint = "H₂SO₄ releases 2 H⁺ per molecule, so [H⁺] = 2 × 0.0005 = 0.001.",
                        stepByStepSolution = "[H⁺] = 2 × 0.0005 = 0.001 = 10⁻³ M => pH = 3.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_06_2",
                        topic = "Indicators & Titration",
                        examType = "WAEC",
                        year = 2023,
                        question = "Which indicator is most suitable for titrating ethanoic acid (CH₃COOH) against sodium hydroxide (NaOH)?",
                        options = listOf("Methyl orange", "Phenolphthalein", "Litmus solution", "Methyl red"),
                        correctIndex = 1,
                        hint = "Weak acid + strong base ends at basic pH (~8.8).",
                        stepByStepSolution = "Phenolphthalein changes color in the basic pH range 8.3–10.0, matching the CH₃COONa endpoint.",
                        waecMarkingScheme = "A1: Phenolphthalein.",
                      ),
                      PracticeQuestion(
                        id = "ex_chem_06_3",
                        topic = "Volumetric Analysis",
                        examType = "JAMB",
                        year = 2023,
                        question = "What volume of water must be added to 20 cm³ of 0.50 M HCl to dilute it to 0.10 M?",
                        options = listOf("40 cm³", "60 cm³", "80 cm³", "100 cm³"),
                        correctIndex = 2,
                        hint = "Find total final volume V₂ = C₁V₁/C₂, then subtract initial 20 cm³.",
                        stepByStepSolution = "V₂ = (0.50 × 20) / 0.10 = 100 cm³ total. Water to add = 100 − 20 = 80 cm³.",
                      ),
                    ),
                ),
              ),
          ),
        ) + ChemistryCurriculumPart2.modules,
      pastQuestions = ChemistryCurriculumPart2.pastQuestions,
    )
}
