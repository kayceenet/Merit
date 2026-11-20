package com.example.data.curriculum

object ChemistryCurriculum {

  val track: SubjectTrack by lazy {
    SubjectTrack(
      id = "chemistry",
      title = "Chemistry Specialization",
      shortName = "Chemistry",
      subtitle = "15 Zero-to-A1 lessons covering Separation, Atomic Orbitals, Bonding, Moles, Gas Laws, Titration, Electrolysis & Organic Chemistry",
      modules = module1And2 + ChemistryCurriculumPart2.module3And4 + listOf(ChemistryCurriculumPart3.module5),
      pastQuestions = ChemistryCurriculumPart3.pastQuestions,
    )
  }

  val module1And2: List<CourseModule> =
    listOf(
      CourseModule(
        id = "chem_mod_1",
        subjectId = "chemistry",
        indexLabel = "Module 01",
        title = "Separation Techniques, Atomic Orbitals & Chemical Bonding",
        summary = "Start from zero: purifying mixtures, states of matter, quantum orbitals (s, p, d), Periodic Table trends, and ionic/covalent bonds.",
        lessons =
          listOf(
            Lesson(
              id = "chem_01_separation_kinetic",
              moduleId = "chem_mod_1",
              subjectId = "chemistry",
              title = "Mixtures, Separation Techniques & Kinetic Theory",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 1–2 • WAEC Separation & Matter",
              learningOutcomes =
                listOf(
                  "Tell physical mixtures apart from chemical compounds and elements cleanly.",
                  "Pick the exact separation technique (Separating Funnel, Fractional Distillation, Chromatography, Sublimation).",
                  "Explain heating/cooling plateaus using Kinetic Theory of Solid, Liquid & Gas particles.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Elements, Compounds, and Mixtures",
                    explanation =
                      "An Element consists solely of atoms with the same atomic number (such as pure gold Au or oxygen O₂), whereas a Compound chemically bonds two or more elements in a fixed mass ratio (like H₂O, which cannot be separated by filtration). A Mixture physically blends two or more substances whose components retain their individual properties and can be separated via boiling point, density, solubility, or adsorption differences. In paper chromatography, the retention factor R_f equals the distance moved by the solute spot divided by the distance moved by the solvent front.",
                    keyFormula = "\\text{Air} = \\text{Mixture (N}_2 + \\text{O}_2 + \\text{Ar)}   |   \\text{Brass} = \\text{Cu} + \\text{Zn (Alloy)}   |   R_f = \\frac{\\text{Solute distance}}{\\text{Solvent front}}",
                    examTip =
                      "Match method to property: Immiscible liquids (oil + water) → Separating Funnel; Miscible liquids with close boiling points (ethanol 78°C + water 100°C) → Fractional Distillation; Sublimable solids (NH₄Cl, I₂, camphor) → Sublimation.",
                  ),
                  ConceptBlock(
                    heading = "Kinetic Theory, Heating Plateaus & Purity Criteria",
                    explanation =
                      "In a solid, particles vibrate about fixed lattice positions. Heating ice raises temperature until 0°C, where the thermometer stalls horizontally because supplied energy breaks rigid intermolecular bonds (latent heat of fusion) rather than raising average kinetic energy. A pure substance melts at one sharp melting point (MP) and boils at one sharp boiling point (BP); dissolved impurities depress MP below 0°C and elevate BP above 100°C.",
                    keyFormula = "\\text{Impurity added} \\implies \\text{MP drops below } 0^\\circ\\text{C},\\quad \\text{BP rises above } 100^\\circ\\text{C}",
                    examTip =
                      "Brownian motion—the erratic motion of smoke or pollen suspended in a fluid—provides direct kinetic evidence of invisible, rapidly colliding molecules.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Separating a 3-Way Dry Mixture",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "Describe how to obtain pure dry samples of Iron filings, Sand, and Common Salt (NaCl) from their mixture.",
                    steps =
                      listOf(
                        "Step 1 — Pass a magnet over the dry mix to pull out Iron filings.",
                        "Step 2 — Add water and stir: NaCl dissolves, Sand does not. Filter to recover Sand residue.",
                        "Step 3 — Evaporate the salt filtrate to dryness (or crystallize) to obtain pure NaCl.",
                      ),
                    finalAnswer = "Magnet → Dissolve & Filter (Sand) → Evaporate filtrate (Salt)",
                    examinerTrap = "Don't heat ammonium chloride solution to dryness expecting NH₄Cl crystals if it sublimes—use crystallization!",
                  ),
                  WorkedExample(
                    title = "Identifying Pure Water by Boiling Range",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A colourless liquid boils steadily at 100.0°C and freezes at 0.0°C at 1 atm. A second sample boils from 102°C to 105°C. Identify both.",
                    steps =
                      listOf(
                        "Step 1 — Sharp constant MP (0°C) and BP (100°C) prove the first sample is pure water.",
                        "Step 2 — Elevated boiling range (102–105°C) proves the second sample contains dissolved impurities.",
                      ),
                    finalAnswer = "1st: Pure water  |  2nd: Impure solution",
                    examinerTrap = "Anhydrous CuSO₄ turns blue for ANY water present—only sharp MP/BP proves PURITY!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Crude Oil Fractional Distillation Towers",
                    industryDomain = "Petrochemical Refining",
                    narrative =
                      "Tall refinery fractionating columns condense rising vapour at different heights by boiling point—collecting Petrol (gasoline) near the cool top and Bitumen tar at the hot base.",
                    examConnection = "Same physics as lab ethanol-water fractional distillation.",
                  ),
                ),
              labType = PracticalLabType.SEPARATION_ATOMIC_LAB,
              labBrief =
                "Select real JAMB mixtures (Crude Oil, Ink Dyes, Oil & Water, Sand + Iodine) to run the exact separation apparatus and check purity!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_01_1",
                    topic = "Fractional Distillation",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which method best separates a miscible mixture of ethanol (b.p. 78°C) and water (b.p. 100°C)?",
                    options = listOf("Simple distillation", "Fractional distillation", "Separating funnel", "Chromatography"),
                    correctIndex = 1,
                    hint = "Miscible liquids with close boiling points require a fractionating column.",
                    stepByStepSolution = "Fractional distillation separates miscible liquids whose boiling points are close.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_01_2",
                    topic = "Sublimation",
                    examType = "WAEC",
                    year = 2023,
                    question = "How would you separate a dry mixture of ammonium chloride and sodium chloride?",
                    options = listOf("Filtration", "Sublimation", "Separating funnel", "Centrifugation"),
                    correctIndex = 1,
                    hint = "NH₄Cl turns straight from solid to vapour on gentle heating.",
                    stepByStepSolution = "NH₄Cl sublimes and re-solidifies on a cool funnel while NaCl remains behind.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_01_3",
                    topic = "Chromatography",
                    examType = "JAMB",
                    year = 2025,
                    question = "Paper chromatography separates leaf pigments primarily based on differences in:",
                    options = listOf("Boiling points", "Densities", "Solubility in mobile solvent & adsorption", "Magnetic attraction"),
                    correctIndex = 2,
                    hint = "Faster-dissolving dyes climb higher up the filter paper.",
                    stepByStepSolution = "Components partition differently between the moving solvent and stationary paper.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_01_4",
                    topic = "Purity Test",
                    examType = "WAEC",
                    year = 2022,
                    question = "Adding table salt to pure ice causes the melting point of the ice to:",
                    options = listOf("Rise above 0°C", "Drop below 0°C", "Stay fixed at 0°C", "Turn into sublimation"),
                    correctIndex = 1,
                    hint = "Salt lowers freezing/melting point (used to de-ice roads!).",
                    stepByStepSolution = "Impurities disrupt the crystal lattice and depress melting point below 0°C.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_01_5",
                    topic = "Compounds vs Mixtures",
                    examType = "JAMB",
                    year = 2021,
                    question = "Which of the following is a chemical COMPOUND rather than a mixture?",
                    options = listOf("Air", "Bronze", "Sea water", "Ethanol"),
                    correctIndex = 3,
                    hint = "Ethanol has a fixed formula C₂H₅OH.",
                    stepByStepSolution = "Air, bronze (Cu+Sn alloy), and sea water are physical mixtures; ethanol is chemically bonded C₂H₅OH.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_01_6",
                    topic = "Crystallization",
                    examType = "NECO",
                    year = 2024,
                    question = "Why is hydrated CuSO₄·5H₂O recovered by crystallization rather than heating to dryness?",
                    options = listOf("It sublimates", "Strong heating drives off water of crystallization", "It catches fire", "It is insoluble"),
                    correctIndex = 1,
                    hint = "Dry heating turns blue CuSO₄·5H₂O into white anhydrous powder.",
                    stepByStepSolution = "Evaporation to dryness destroys crystal lattice and removes water of crystallization.",
                  ),
                ),
            ),

            Lesson(
              id = "chem_02_atomic_orbitals",
              moduleId = "chem_mod_1",
              subjectId = "chemistry",
              title = "Atomic Structure, Isotopes & Electron Orbitals",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 4 • WAEC Atomic Structure & Orbitals",
              learningOutcomes =
                listOf(
                  "Count protons, neutrons, and electrons in neutral atoms and ions (X^{n+}, X^{m-}).",
                  "Calculate Relative Atomic Mass (A_r) from isotope mass percentages.",
                  "Write s-p-d electron configurations (1s^2\\; 2s^2\\; 2p^6\\ldots) up to Z = 30 using Aufbau, Pauli & Hund.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Inside the Atom & Isotopes (Same Protons, Different Weight)",
                    explanation =
                      "Picture a stadium: a tiny heavy Nucleus sits at the center holding positively charged Protons (+1) and zero-charge Neutrons (0), while feather-light Electrons (−1) zoom around outside. Atomic Number Z = number of Protons (the atom's unchangeable ID card!). Mass Number A = Protons + Neutrons. Isotopes are twins with the SAME Z (same element!) but extra Neutrons—so they react identically chemically, only differing in mass!",
                    keyFormula = "\\text{Mass No. } A = p + n   |   A_r = \\frac{\\sum (\\%\\text{abundance} \\times \\text{isotope mass})}{100}",
                    examTip =
                      "Removing electrons makes a POSITIVE cation (+): ²⁷Al³⁺ has 13 protons, 14 neutrons, and 13 − 3 = 10 electrons!",
                  ),
                  ConceptBlock(
                    heading = "Quantum Hotel Rooms (1s, 2s, 2p, 3s, 3p, 4s, 3d)",
                    explanation =
                      "Electrons fill shells like guests checking into a hotel from the cheapest ground floor up (Aufbau Principle). Each room holds at most 2 electrons spinning opposite ways (Pauli Exclusion), and guests grab empty beds first before sharing a double room (Hund's Rule)! Secret: 4s fills BEFORE 3d because 4s sits slightly lower in energy.",
                    keyFormula = "1s^2\\; 2s^2\\; 2p^6\\; 3s^2\\; 3p^6\\; 4s^2\\; 3d^{10}",
                    examTip =
                      "Watch transition metal exceptions: Cr (24) is [Ar] 4s¹ 3d⁵ (half-filled d⁵ stability) and Cu (29) is [Ar] 4s¹ 3d¹⁰!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Calculating Relative Atomic Mass of Chlorine",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "Chlorine consists of 75% ³⁵Cl and 25% ³⁷Cl. Calculate its relative atomic mass.",
                    steps =
                      listOf(
                        "Step 1 — Weighted average = [(75 × 35) + (25 × 37)] / 100.",
                        "Step 2 — (2625 + 925) / 100 = 3550 / 100 = 35.5.",
                      ),
                    finalAnswer = "Aᵣ = 35.5",
                    examinerTrap = "Just averaging (35 + 37)/2 = 36 ignores the 75% vs 25% abundance!",
                  ),
                  WorkedExample(
                    title = "Subshell Configuration of Fe³⁺ (Z = 26)",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Write the electron configuration of Iron(III) ion, ₂₆Fe³⁺.",
                    steps =
                      listOf(
                        "Step 1 — Neutral Fe (26e⁻): 1s² 2s² 2p⁶ 3s² 3p⁶ 4s² 3d⁶.",
                        "Step 2 — When forming cations, outermost 4s electrons leave FIRST! Remove 2 from 4s and 1 from 3d.",
                        "Step 3 — Fe³⁺ (23e⁻): 1s² 2s² 2p⁶ 3s² 3p⁶ 3d⁵ (extra half-filled 3d⁵ stability!).",
                      ),
                    finalAnswer = "1s² 2s² 2p⁶ 3s² 3p⁶ 3d⁵",
                    examinerTrap = "Removing 3 electrons from 3d to leave 4s² 3d³ is WRONG—4s leaves first!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Fireworks Colours & Medical Isotope Imaging",
                    industryDomain = "Pyrotechnics & Nuclear Medicine",
                    narrative =
                      "When electrons heated in a flame drop back down energy shells, they shoot out exact colours—Sodium glows yellow, Copper glows green, Strontium glows red!",
                    examConnection = "Explains both Bohr quantized energy jumps and laboratory flame tests.",
                  ),
                ),
              labType = PracticalLabType.SEPARATION_ATOMIC_LAB,
              labBrief =
                "Step Atomic Number Z from 1 to 20 to watch K/L/M/N shells and 1s² 2s² 2p⁶ orbitals populate live and predict valency!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_02_1",
                    topic = "Subatomic Particles",
                    examType = "JAMB",
                    year = 2024,
                    question = "How many protons, neutrons, and electrons are in the sulfide ion ³²₁₆S²⁻?",
                    options = listOf("16p, 16n, 18e", "16p, 16n, 14e", "18p, 16n, 16e", "16p, 32n, 18e"),
                    correctIndex = 0,
                    hint = "2− charge means gained 2 extra electrons.",
                    stepByStepSolution = "p = 16, n = 32 − 16 = 16, e = 16 + 2 = 18.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_02_2",
                    topic = "Isotope Average Mass",
                    examType = "WAEC",
                    year = 2023,
                    question = "Element X has isotopes ²⁰X (90%) and ²²X (10%). Find its relative atomic mass.",
                    options = listOf("20.2", "21.0", "20.5", "21.8"),
                    correctIndex = 0,
                    hint = "0.90(20) + 0.10(22).",
                    stepByStepSolution = "18.0 + 2.2 = 20.2.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_02_3",
                    topic = "Hund's Rule",
                    examType = "JAMB",
                    year = 2025,
                    question = "How many unpaired electrons exist in a ground-state Nitrogen atom (Z = 7)?",
                    options = listOf("1", "2", "3", "5"),
                    correctIndex = 2,
                    hint = "Configuration is 1s² 2s² 2p³ across three 2p orbitals.",
                    stepByStepSolution = "By Hund's rule, the three 2p electrons occupy separate p_x, p_y, p_z orbitals → 3 unpaired.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_02_4",
                    topic = "Rutherford Experiment",
                    examType = "WAEC",
                    year = 2022,
                    question = "Rutherford's alpha-scattering gold-foil experiment proved that an atom has:",
                    options = listOf("Evenly spread mass", "A tiny dense positive nucleus", "Stationary neutrons", "Electrons inside the nucleus"),
                    correctIndex = 1,
                    hint = "Most alpha particles passed straight through empty space; a few bounced sharply.",
                    stepByStepSolution = "Tiny deflection fraction indicates a minute, massive positively charged center.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_02_5",
                    topic = "Quantum Rules",
                    examType = "JAMB",
                    year = 2023,
                    question = "Which orbital fills immediately AFTER 3p?",
                    options = listOf("3d", "4s", "4p", "3s"),
                    correctIndex = 1,
                    hint = "Aufbau order: 3p → 4s → 3d.",
                    stepByStepSolution = "4s has slightly lower energy than 3d, so 4s fills before 3d.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_02_6",
                    topic = "Isotope Properties",
                    examType = "NECO",
                    year = 2024,
                    question = "Which property differs between ³⁵Cl and ³⁷Cl?",
                    options = listOf("Number of protons", "Valence electrons", "Chemical reactivity", "Rate of diffusion"),
                    correctIndex = 3,
                    hint = "Heavier mass 37 diffuses slower than mass 35.",
                    stepByStepSolution = "Isotopes share chemical behaviour (same electrons) but differ in physical mass & diffusion rate.",
                  ),
                ),
            ),

            Lesson(
              id = "chem_03_periodic_bonding",
              moduleId = "chem_mod_1",
              subjectId = "chemistry",
              title = "Periodic Table Trends, Ionic, Covalent & Hydrogen Bonds",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 5–6 • WAEC Periodicity & Bonding",
              learningOutcomes =
                listOf(
                  "Predict Atomic Size, Ionization Energy & Electronegativity across Periods and down Groups.",
                  "Distinguish Electrovalent (Ionic), Covalent, Dative (Coordinate), Metallic & Hydrogen bonds.",
                  "Explain why HF/H₂O/NH₃ boil weirdly high and predict molecular shapes (Linear, Trigonal, Tetrahedral).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Periodic Table Cheat-Map (Across vs Down)",
                    explanation =
                      "Rows (Periods) share the same number of shells; Columns (Groups) share outer electrons! Going LEFT → RIGHT across a period adds extra protons into the SAME shell, pulling electrons tighter—so Atom Size SHRINKS, while Ionization Energy & Electronegativity SHOOT UP (Fluorine is the tightest bully!). Going DOWN a group adds new fat shells—so Size GROWS and holding electrons gets weaker!",
                    keyFormula = "\\text{Across Period } (\\to):\\; r \\downarrow,\\; IE \\uparrow,\\; EN \\uparrow   |   \\text{Down Group } (\\downarrow):\\; r \\uparrow,\\; IE \\downarrow",
                    examTip =
                      "Transition metals (d-block) show: (1) Variable oxidation states (Fe²⁺/Fe³⁺), (2) Coloured ions, (3) Catalytic power, (4) Complex ion formation!",
                  ),
                  ConceptBlock(
                    heading = "Chemical Bonds: Sharing vs Stealing vs H-Bonding",
                    explanation =
                      "Metal + Non-metal? The non-metal STEALS valence electrons → Ionic Bond (high melting point, conducts electricity ONLY when molten or dissolved!). Two Non-metals? They SHARE pairs → Covalent Bond (low melting point, poor conductor). One atom donates BOTH shared electrons (like NH₃ + H⁺ → NH₄⁺)? That's a Dative/Coordinate bond! Hydrogen stuck to F, O, or N creates sticky Hydrogen Bonds—that's why H₂O is a liquid while H₂S is a smelly gas!",
                    keyFormula = "\\text{CO}_2\\text{ Linear } (180^\\circ)   |   \\text{NH}_3\\text{ Pyramidal } (107^\\circ)   |   \\text{H}_2\\text{O Bent } (104.5^\\circ)   |   \\text{CH}_4\\text{ Tetra } (109.5^\\circ)",
                    examTip =
                      "NH₄Cl holds ALL THREE: ionic bond between NH₄⁺ & Cl⁻, covalent N−H bonds, AND one dative N→H⁺ bond!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Predicting Compound Formula & Bond Type from Z",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "Element X (Z = 12) combines with Y (Z = 17). Predict the formula, bond type, and melting point behaviour.",
                    steps =
                      listOf(
                        "Step 1 — X(12): 2, 8, 2 (loses 2e⁻ → X²⁺ metal, Group 2).",
                        "Step 2 — Y(17): 2, 8, 7 (gains 1e⁻ → Y⁻ halogen, Group 17).",
                        "Step 3 — Swap valencies: XY₂ (Electrovalent/Ionic crystal; high MP; dissolves & conducts in water).",
                      ),
                    finalAnswer = "XY₂ • Electrovalent (Ionic)",
                    examinerTrap = "Don't write X₂Y—valency 2 on X crosses over to subscript 2 on Y!",
                  ),
                  WorkedExample(
                    title = "Why Water Boils at 100°C vs H₂S at −60°C",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Oxygen and Sulphur are both in Group 16, yet H₂O is liquid at 25°C while H₂S is a gas. Explain.",
                    steps =
                      listOf(
                        "Step 1 — Oxygen is strongly electronegative and tiny.",
                        "Step 2 — H₂O molecules link together via intermolecular HYDROGEN BONDS.",
                        "Step 3 — H₂S only has weak van der Waals dipole attractions, needing far less heat to boil.",
                      ),
                    finalAnswer = "Intermolecular Hydrogen Bonding in H₂O",
                    examinerTrap = "Do NOT say covalent O−H bonds break when water boils—INTERMOLECULAR H-bonds break!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Diamond Cutting Blades vs Graphite Pencil Leads",
                    industryDomain = "Materials Science",
                    narrative =
                      "Both Diamond and Graphite are pure Carbon! Diamond locks all 4 electrons in rigid tetrahedra (ultra-hard insulator), while Graphite leaves 1 delocalized electron per atom sliding between flat sheets (soft conductor).",
                    examConnection = "Classic JAMB allotrope & bonding contrast question.",
                  ),
                ),
              labType = PracticalLabType.BONDING_PERIODIC_LAB,
              labBrief =
                "Compare any two elements' electronegativity ΔEN and valence shells to simulate electron transfer (Ionic) vs sharing (Covalent) and molecular shape.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_03_1",
                    topic = "Periodic Trends",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which atom has the largest atomic radius among Li, Be, F, and Na?",
                    options = listOf("Li", "Be", "F", "Na"),
                    correctIndex = 3,
                    hint = "Radius increases down a group (extra shell) and decreases left-to-right.",
                    stepByStepSolution = "Na (Period 3) has 3 shells vs 2 shells for Li, Be, F.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_03_2",
                    topic = "Hydrogen Bonding",
                    examType = "WAEC",
                    year = 2023,
                    question = "Which compound exhibits intermolecular hydrogen bonding?",
                    options = listOf("CH₄", "HCl", "NH₃", "H₂S"),
                    correctIndex = 2,
                    hint = "H must be directly bonded to F, O, or N.",
                    stepByStepSolution = "Ammonia (NH₃) has H attached directly to highly electronegative Nitrogen.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_03_3",
                    topic = "Coordinate Bonding",
                    examType = "JAMB",
                    year = 2025,
                    question = "The bond formed when NH₃ donates a lone pair to H⁺ forming NH₄⁺ is:",
                    options = listOf("Electrovalent", "Metallic", "Dative covalent", "Van der Waals"),
                    correctIndex = 2,
                    hint = "One donor supplies BOTH electrons of the shared pair.",
                    stepByStepSolution = "A shared pair donated by a single atom is a dative (coordinate) covalent bond.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_03_4",
                    topic = "Molecular Geometry",
                    examType = "WAEC",
                    year = 2022,
                    question = "What is the shape and bond angle of a Methane (CH₄) molecule?",
                    options = listOf("Linear, 180°", "Trigonal planar, 120°", "Tetrahedral, 109.5°", "Bent, 104.5°"),
                    correctIndex = 2,
                    hint = "4 bond pairs and 0 lone pairs around sp³ Carbon.",
                    stepByStepSolution = "CH₄ is symmetrical Tetrahedral with 109.5° H–C–H bond angles.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_03_5",
                    topic = "Transition Metals",
                    examType = "JAMB",
                    year = 2021,
                    question = "Which characteristic is NOT typical of transition elements?",
                    options = listOf("Coloured hydrated ions", "Variable oxidation states", "Fixed single valency", "Catalytic activity"),
                    correctIndex = 2,
                    hint = "s-block metals like Na/Ca have fixed valency; transition metals vary.",
                    stepByStepSolution = "Transition metals have partially filled d-orbitals giving multiple oxidation states.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_03_6",
                    topic = "Ionic Conductance",
                    examType = "NECO",
                    year = 2024,
                    question = "Solid NaCl does not conduct electricity because its:",
                    options = listOf("Electrons are shared", "Ions are locked in a rigid crystal lattice", "Molecules are neutral", "Sodium evaporates"),
                    correctIndex = 1,
                    hint = "Current requires mobile charge carriers.",
                    stepByStepSolution = "In solid NaCl, Na⁺ and Cl⁻ ions cannot move until melted or dissolved in water.",
                  ),
                ),
            ),
          ),
      ),

      CourseModule(
        id = "chem_mod_2",
        subjectId = "chemistry",
        indexLabel = "Module 02",
        title = "Stoichiometry, Gas Laws & Acid-Base Titration",
        summary = "Master Mole calculations, Empirical Formulas, Boyle/Charles/Ideal Gas/Graham's laws, Volumetric Titration, pH & Salt Hydrolysis.",
        lessons =
          listOf(
            Lesson(
              id = "chem_04_stoichiometry_moles",
              moduleId = "chem_mod_2",
              subjectId = "chemistry",
              title = "Mole Concept, Empirical Formula & Stoichiometry",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 7 • WAEC Quantitative Chemistry",
              learningOutcomes =
                listOf(
                  "Convert effortlessly among Grams (m/M), Gas Volume at STP (V / 22.4 dm³), Concentration (C × V_L), and Particles (n × N_A).",
                  "Determine Empirical Formula and Molecular Formula from percentage composition.",
                  "Identify the Limiting Reactant and calculate percentage yield or purity.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "What Is 1 Mole? (The Chemist's Dozen!)",
                    explanation =
                      "A dozen eggs always means 12 eggs—whether tiny quail eggs or giant ostrich eggs. Similarly, 1 MOLE always means 6.02 × 10²³ particles (Avogadro's giant dozen!). Because hydrogen atoms weigh 1 u while carbon weighs 12 u, 1 mole simply weighs its Molecular Mass (Mᵣ) in grams—and ANY 1 mole of gas at STP fills 22.4 dm³!",
                    keyFormula = "n = \\frac{\\text{mass}}{M_r} = \\frac{V_{\\text{STP}}}{22.4\\text{ dm}^3} = C(\\text{mol/dm}^3)\\times V(\\text{dm}^3) = \\frac{N}{6.02\\times 10^{23}}",
                    examTip =
                      "Volumes in titration/stoichiometry are often given in cm³! ALWAYS divide cm³ by 1000 to get dm³ BEFORE multiplying C × V!",
                  ),
                  ConceptBlock(
                    heading = "Empirical Formula in 3 Easy Steps",
                    explanation =
                      "Divide each element's % (or grams) by its Atomic Mass Aᵣ → Divide all results by the SMALLEST number → Double/triple only if you get .5 or .33 to reach clean whole numbers! Molecular Formula is simply (Empirical Formula)ₙ where n = Molar Mass / Empirical Mass.",
                    keyFormula = "\\text{Molecular Mass} = n \\times \\text{Empirical Mass}   |   \\text{Vapour Density } VD = \\frac{M_r}{2}",
                    examTip =
                      "Watch for 'Vapour Density' in JAMB! Molar Mass Mᵣ is ALWAYS 2 × Vapour Density!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Gas Volume at STP from Marble Decomposition",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "What volume of CO₂ at STP is liberated when 25 g of CaCO₃ is heated completely? (Ca=40, C=12, O=16; 1 mol gas = 22.4 dm³)",
                    steps =
                      listOf(
                        "Step 1 — Balanced equation: CaCO₃ → CaO + CO₂ (1 : 1 ratio).",
                        "Step 2 — Mᵣ(CaCO₃) = 40 + 12 + 48 = 100 g/mol ⇒ moles = 25/100 = 0.25 mol.",
                        "Step 3 — Vol CO₂ = 0.25 × 22.4 = 5.6 dm³.",
                      ),
                    finalAnswer = "5.6 dm³",
                    examinerTrap = "Check the mole ratio in the balanced equation first—not every reaction is 1:1!",
                  ),
                  WorkedExample(
                    title = "Empirical to Molecular Formula from Vapour Density",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "A hydrocarbon contains 85.7% C and 14.3% H, and its vapour density is 21. Find its molecular formula. (C=12, H=1)",
                    steps =
                      listOf(
                        "Step 1 — Moles: C = 85.7/12 = 7.14; H = 14.3/1 = 14.3.",
                        "Step 2 — Divide by 7.14: C = 1, H = 2 ⇒ Empirical = CH₂ (mass = 14).",
                        "Step 3 — Mᵣ = 2 × VD = 2 × 21 = 42 ⇒ n = 42/14 = 3 ⇒ C₃H₆.",
                      ),
                    finalAnswer = "C₃H₆ (Propene)",
                    examinerTrap = "Forgetting to double Vapour Density (using 21 instead of 42)!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Car Airbag Inflation Stoichiometry",
                    industryDomain = "Automotive Safety",
                    narrative =
                      "Car engineers pack the exact gram dose of Sodium Azide (NaN₃) so a crash decomposes it into precisely 60 dm³ of N₂ gas in 30 milliseconds!",
                    examConnection = "Real-world mass-to-gas-volume mole stoichiometry.",
                  ),
                ),
              labType = PracticalLabType.STOICHIOMETRY_MOLE_LAB,
              labBrief =
                "Adjust reactant grams & molar mass Mᵣ to calculate exact moles, identify the Limiting Reactant & read theoretical STP gas yield!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_04_1",
                    topic = "Molar Concentration",
                    examType = "JAMB",
                    year = 2024,
                    question = "What mass of NaOH (Mᵣ = 40) is needed to make 250 cm³ of a 0.2 mol/dm³ solution?",
                    options = listOf("2.0 g", "4.0 g", "8.0 g", "0.5 g"),
                    correctIndex = 0,
                    hint = "Convert 250 cm³ to 0.25 dm³, multiply by 0.2 mol/dm³, then × 40 g/mol.",
                    stepByStepSolution = "n = 0.2 × 0.25 = 0.05 mol; mass = 0.05 × 40 = 2.0 g.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_04_2",
                    topic = "Number of Molecules",
                    examType = "WAEC",
                    year = 2023,
                    question = "How many moles of oxygen atoms are in 2.2 g of CO₂? (C=12, O=16)",
                    options = listOf("0.05 mol", "0.10 mol", "0.20 mol", "0.50 mol"),
                    correctIndex = 1,
                    hint = "2.2 g / 44 = 0.05 mol CO₂, and EACH CO₂ contains 2 O atoms!",
                    stepByStepSolution = "0.05 mol CO₂ × 2 = 0.10 mol O atoms.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_04_3",
                    topic = "Empirical Formula",
                    examType = "JAMB",
                    year = 2025,
                    question = "An oxide of nitrogen contains 30.4% N and 69.6% O. Find its empirical formula. (N=14, O=16)",
                    options = listOf("NO", "NO₂", "N₂O", "N₂O₃"),
                    correctIndex = 1,
                    hint = "N: 30.4/14 ≈ 2.17; O: 69.6/16 = 4.35.",
                    stepByStepSolution = "Ratio N:O = 2.17 : 4.35 = 1 : 2 ⇒ NO₂.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_04_4",
                    topic = "Limiting Reactant",
                    examType = "WAEC",
                    year = 2022,
                    question = "In 2H₂ + O₂ → 2H₂O, 4 moles of H₂ mix with 3 moles of O₂. How many moles of H₂O form?",
                    options = listOf("6 mol", "4 mol", "3 mol", "2 mol"),
                    correctIndex = 1,
                    hint = "4 mol H₂ only needs 2 mol O₂, so H₂ runs out first!",
                    stepByStepSolution = "H₂ is limiting; 4 mol H₂ yields 4 mol H₂O (1 mol O₂ left excess).",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_04_5",
                    topic = "Percentage Composition",
                    examType = "JAMB",
                    year = 2021,
                    question = "Calculate the percentage by mass of Nitrogen in NH₄NO₃. (N=14, H=1, O=16)",
                    options = listOf("17.5%", "35.0%", "28.0%", "40.0%"),
                    correctIndex = 1,
                    hint = "Formula has 2 Nitrogen atoms; Mᵣ = 14 + 4 + 14 + 48 = 80.",
                    stepByStepSolution = "(28 / 80) × 100 = 35.0%.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_04_6",
                    topic = "Water Dilution",
                    examType = "NECO",
                    year = 2024,
                    question = "100 cm³ of 0.5 M HCl is diluted with distilled water to 500 cm³. Find the new molarity.",
                    options = listOf("0.1 M", "0.2 M", "0.25 M", "2.5 M"),
                    correctIndex = 0,
                    hint = "Use C₁V₁ = C₂V₂.",
                    stepByStepSolution = "(0.5 × 100) / 500 = 0.1 mol/dm³.",
                  ),
                ),
            ),

            Lesson(
              id = "chem_05_gas_laws",
              moduleId = "chem_mod_2",
              subjectId = "chemistry",
              title = "Gas Laws, Ideal Gas PV=nRT & Graham Diffusion",
              durationMinutes = 19,
              syllabusTag = "JAMB Sec. 3 • WAEC Kinetic Gas Laws",
              learningOutcomes =
                listOf(
                  "Apply Boyle's (P₁V₁ = P₂V₂), Charles's (V₁/T₁ = V₂/T₂), and General Gas equations strictly in Kelvin.",
                  "Use Dalton's Law of Partial Pressures and Ideal Gas PV = nRT.",
                  "Predict faster vs slower diffusing gases using Graham's Law: Rate ∝ 1/√Mᵣ.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "The Piston Trio: Squeeze (Boyle), Heat (Charles) & Dalton Mix",
                    explanation =
                      "Imagine gas molecules bouncing inside a syringe: push the plunger IN (higher Pressure P), and Volume V shrinks proportionally (Boyle: P₁V₁ = P₂V₂). Heat the gas (higher KELVIN temp T), molecules bounce harder pushing the piston OUT (Charles: V₁/T₁ = V₂/T₂). Put two different gases in one jar? Total Pressure = sum of individual pressures (Dalton)!",
                    keyFormula = "\\frac{P_1 V_1}{T_1} = \\frac{P_2 V_2}{T_2}   |   PV = nRT   |   T(\\text{K}) = t(^\\circ\\text{C}) + 273",
                    examTip =
                      "NEVER plug Celsius directly into Charles/Ideal Gas Law! Always add +273 first (27°C = 300 K)!",
                  ),
                  ConceptBlock(
                    heading = "Graham's Law: Light Runners Sprint Faster!",
                    explanation =
                      "At the same temperature, tiny light molecules like Hydrogen (Mᵣ = 2) sprint WAY faster than heavy clumsy Chlorine (Mᵣ = 71). Specifically, speed/rate scales inversely with the SQUARE ROOT of Molar Mass: if Gas B is 4× heavier than Gas A, Gas B diffuses √4 = 2× SLOWER (takes 2× longer time!).",
                    keyFormula = "\\frac{R_1}{R_2} = \\frac{t_2}{t_1} = \\sqrt{\\frac{M_2}{M_1}} = \\sqrt{\\frac{d_2}{d_1}}",
                    examTip =
                      "Watch Time t vs Rate R! Faster gas has HIGHER Rate R₁ but SMALLER Time t₁—so t₁/t₂ = √(M₁/M₂)!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Combined Gas Law Heating Under Pressure",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A gas occupies 400 cm³ at 27°C and 760 mmHg. What volume will it occupy at 127°C and 380 mmHg?",
                    steps =
                      listOf(
                        "Step 1 — Convert Celsius to Kelvin: T₁ = 300 K, T₂ = 400 K.",
                        "Step 2 — V₂ = (P₁ V₁ T₂) / (T₁ P₂) = (760 × 400 × 400) / (300 × 380).",
                        "Step 3 — 760/380 = 2 ⇒ V₂ = (2 × 1600) / 3 = 1066.7 cm³.",
                      ),
                    finalAnswer = "1066.7 cm³",
                    examinerTrap = "Using 127/27 directly instead of 400 K / 300 K!",
                  ),
                  WorkedExample(
                    title = "Graham Diffusion Molar Mass of Unknown Gas",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "100 cm³ of Oxygen (Mᵣ = 32) diffuses in 20 s. Under the same conditions, 100 cm³ of gas Y diffuses in 10 s. Find Mᵣ of Y.",
                    steps =
                      listOf(
                        "Step 1 — Same volume, so t_Y / t_O₂ = √(M_Y / M_O₂).",
                        "Step 2 — 10 / 20 = 1/2 = √(M_Y / 32).",
                        "Step 3 — Square both sides: 1/4 = M_Y / 32 ⇒ M_Y = 8.",
                      ),
                    finalAnswer = "Mᵣ = 8",
                    examinerTrap = "Gas Y finished in HALF the time (10 s vs 20 s), so Y MUST be lighter than O₂!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "NH₃ + HCl White Ring Lab & Scuba Bends",
                    industryDomain = "Analytical Chemistry & Diving",
                    narrative =
                      "Place cotton wool soaked in NH₃ (Mᵣ=17) and HCl (Mᵣ=36.5) at opposite ends of a glass tube—because NH₃ is lighter, the white NH₄Cl smoke ring always forms CLOSER to the HCl end!",
                    examConnection = "Top-rated WAEC practical demonstration of Graham's Law.",
                  ),
                ),
              labType = PracticalLabType.GAS_LAW_PISTON,
              labBrief =
                "Drag Temperature (K), Pressure (atm), and Gas Molar Mass sliders to compress the gas piston and test Graham's effusion speeds.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_05_1",
                    topic = "Charles's Law",
                    examType = "JAMB",
                    year = 2024,
                    question = "At constant pressure, a gas of volume 300 cm³ at 27°C is cooled to −73°C. Find the new volume.",
                    options = listOf("200 cm³", "100 cm³", "450 cm³", "250 cm³"),
                    correctIndex = 0,
                    hint = "T₁ = 300 K, T₂ = −73 + 273 = 200 K.",
                    stepByStepSolution = "V₂ = 300 × (200/300) = 200 cm³.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_05_2",
                    topic = "Graham's Law",
                    examType = "WAEC",
                    year = 2023,
                    question = "Compare the diffusion rate of Helium (Mᵣ = 4) to Methane (Mᵣ = 16).",
                    options = listOf("He is 2× faster", "He is 4× faster", "CH₄ is 2× faster", "Equal rates"),
                    correctIndex = 0,
                    hint = "√(16 / 4) = √4.",
                    stepByStepSolution = "R_He / R_CH₄ = √(16/4) = 2:1 (Helium diffuses twice as fast).",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_05_3",
                    topic = "Dalton's Partial Pressure",
                    examType = "JAMB",
                    year = 2025,
                    question = "A 5 atm gas mixture contains 2 mol N₂ and 3 mol O₂. What is the partial pressure of N₂?",
                    options = listOf("2 atm", "3 atm", "2.5 atm", "1 atm"),
                    correctIndex = 0,
                    hint = "P_N₂ = (mole fraction of N₂) × Total Pressure.",
                    stepByStepSolution = "(2 / (2+3)) × 5 = 0.4 × 5 = 2 atm.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_05_4",
                    topic = "Ideal Gas Deviations",
                    examType = "WAEC",
                    year = 2022,
                    question = "Real gases behave MOST like an Ideal Gas under which conditions?",
                    options = listOf("Low T, High P", "High T, Low P", "Low T, Low P", "High T, High P"),
                    correctIndex = 1,
                    hint = "High heat overcomes attractions; low pressure keeps particles far apart.",
                    stepByStepSolution = "High Temperature & Low Pressure minimize intermolecular forces & volume.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_05_5",
                    topic = "Avogadro's Law",
                    examType = "JAMB",
                    year = 2021,
                    question = "30 cm³ of CO reacts with 20 cm³ of O₂ (2CO + O₂ → 2CO₂). Total gas volume after cooling is:",
                    options = listOf("30 cm³", "35 cm³", "50 cm³", "40 cm³"),
                    correctIndex = 1,
                    hint = "30 CO consumes 15 O₂ leaving 5 O₂ excess + 30 CO₂ formed!",
                    stepByStepSolution = "5 cm³ excess O₂ + 30 cm³ CO₂ produced = 35 cm³.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_05_6",
                    topic = "Ideal Gas Equation",
                    examType = "NECO",
                    year = 2024,
                    question = "In PV = nRT, which two graphs produce a straight line through the origin at constant T and n?",
                    options = listOf("P vs V", "P vs 1/V", "V vs T(°C)", "P vs V²"),
                    correctIndex = 1,
                    hint = "P = (nRT) × (1/V) matches y = mx.",
                    stepByStepSolution = "P plotted against 1/V gives a straight line passing through (0,0).",
                  ),
                ),
            ),

            Lesson(
              id = "chem_06_acids_bases_titration",
              moduleId = "chem_mod_2",
              subjectId = "chemistry",
              title = "Acids, Bases, Salts, pH & Volumetric Titration",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 8 • WAEC Acids, Bases & Volumetric Analysis",
              learningOutcomes =
                listOf(
                  "Compare Arrhenius, Brønsted-Lowry (proton donor/acceptor), and Lewis acid definitions.",
                  "Calculate pH = −log₁₀[H⁺] and predict Salt Hydrolysis pH (acidic, neutral, alkaline).",
                  "Solve Acid-Base titration molarity & % purity using C_A V_A / (C_B V_B) = n_A / n_B.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "pH Scale, Strong vs Weak & Salt Hydrolysis",
                    explanation =
                      "pH runs from 0 (strong battery acid HCl) through 7 (pure water) to 14 (strong base NaOH). Every 1-unit drop in pH means 10× MORE H⁺ ions! Surprise WAEC trap: not every salt is pH 7! Strong Acid + Weak Base (like NH₄Cl) dissolves to give an ACIDIC solution (pH < 7); Weak Acid + Strong Base (like Na₂CO₃ or CH₃COONa) gives an ALKALINE solution (pH > 7)!",
                    keyFormula = "\\text{pH} = -\\log_{10}[\\text{H}^+],\\quad \\text{pH} + \\text{pOH} = 14   |   \\text{Weak Acid+Strong Base} \\implies \\text{pH} > 7",
                    examTip =
                      "Match the indicator: Methyl Orange (pH 3.1–4.4) for Strong Acid vs Weak Base (Na₂CO₃); Phenolphthalein (pH 8.3–10) for Weak Acid (CH₃COOH) vs Strong Base!",
                  ),
                  ConceptBlock(
                    heading = "Titration Mastery Formula (Never Forget Mole Ratio!)",
                    explanation =
                      "H₂SO₄ carries TWO H⁺ protons per molecule, whereas NaOH carries ONE OH⁻—so 1 mole of H₂SO₄ neutralizes 2 moles of NaOH! Always write the balanced equation first to read n_A and n_B before plugging into C_A V_A / (C_B V_B) = n_A / n_B.",
                    keyFormula = "\\frac{C_A V_A}{C_B V_B} = \\frac{n_A}{n_B}   |   \\%\\text{ Purity} = \\frac{\\text{Pure Mass}}{\\text{Impure Mass}} \\times 100\\%",
                    examTip =
                      "During titration prep: rinse Burette with Acid, Pipette with Base, and Conical Flask ONLY with Distilled Water!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Diprotic Acid H₂SO₄ vs NaOH Titration",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "25.0 cm³ of 0.10 mol/dm³ NaOH completely neutralized 20.0 cm³ of H₂SO₄. Calculate the concentration of H₂SO₄ in g/dm³ (H=1, S=32, O=16).",
                    steps =
                      listOf(
                        "Step 1 — Balanced eq: H₂SO₄ + 2NaOH → Na₂SO₄ + 2H₂O ⇒ n_A = 1, n_B = 2.",
                        "Step 2 — (C_A × 20.0) / (0.10 × 25.0) = 1 / 2 ⇒ C_A = 2.5 / 40 = 0.0625 mol/dm³.",
                        "Step 3 — Mᵣ(H₂SO₄) = 98 ⇒ g/dm³ = 0.0625 × 98 = 6.125 g/dm³.",
                      ),
                    finalAnswer = "0.0625 mol/dm³ (6.125 g/dm³)",
                    examinerTrap = "Forgetting the 1:2 stoichiometry factor turns 0.0625 into 0.125 (double error)!",
                  ),
                  WorkedExample(
                    title = "pH of Diluted HCl & Alkaline NaOH",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Calculate the pH of (a) 0.001 M HCl and (b) 0.01 M NaOH at 25°C.",
                    steps =
                      listOf(
                        "Step 1 — [H⁺] = 10⁻³ M ⇒ pH = −log(10⁻³) = 3.",
                        "Step 2 — For NaOH: [OH⁻] = 10⁻² M ⇒ pOH = 2 ⇒ pH = 14 − 2 = 12.",
                      ),
                    finalAnswer = "(a) pH = 3  |  (b) pH = 12",
                    examinerTrap = "Never compute −log(0.01) = 2 as the pH of NaOH—that's pOH!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Buffer Blood Chemistry & Antacid Neutralization",
                    industryDomain = "Clinical Medicine",
                    narrative =
                      "Your blood uses an H₂CO₃/HCO₃⁻ buffer to pin pH strictly between 7.35 and 7.45, while milk of magnesia Mg(OH)₂ neutralizes excess stomach HCl gently.",
                    examConnection = "Direct real-life application of weak-acid/conjugate-base buffers.",
                  ),
                ),
              labType = PracticalLabType.TITRATION_PH,
              labBrief =
                "Release NaOH titrant drop-by-drop from the burette into HCl or CH₃COOH to watch the S-curve jump across pH 7 and flip indicator colour!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_06_1",
                    topic = "pH Calculation",
                    examType = "JAMB",
                    year = 2024,
                    question = "What is the pH of a 0.005 mol/dm³ solution of H₂SO₄ (complete ionization)?",
                    options = listOf("2.0", "2.3", "3.0", "1.0"),
                    correctIndex = 0,
                    hint = "Each H₂SO₄ releases 2 H⁺ ⇒ [H⁺] = 2 × 0.005 = 0.01 = 10⁻² M!",
                    stepByStepSolution = "pH = −log₁₀(10⁻²) = 2.0.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_06_2",
                    topic = "Salt Hydrolysis",
                    examType = "WAEC",
                    year = 2023,
                    question = "An aqueous solution of Ammonium Chloride (NH₄Cl) turns blue litmus red because:",
                    options = listOf("Cl⁻ reacts with water", "NH₄⁺ hydrolyzes to produce H₃O⁺", "It is an acid salt", "NH₃ evaporates"),
                    correctIndex = 1,
                    hint = "NH₄Cl = Weak Base (NH₃) + Strong Acid (HCl).",
                    stepByStepSolution = "NH₄⁺ + H₂O ⇌ NH₃ + H₃O⁺ makes the solution acidic (pH < 7).",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_06_3",
                    topic = "Titration Mole Ratio",
                    examType = "JAMB",
                    year = 2025,
                    question = "25 cm³ of 0.1 M Na₂CO₃ requires 25 cm³ of HCl for complete neutralization. Molarity of HCl is:",
                    options = listOf("0.05 M", "0.10 M", "0.20 M", "0.40 M"),
                    correctIndex = 2,
                    hint = "Na₂CO₃ + 2HCl → 2NaCl + H₂O + CO₂ (ratio 1:2).",
                    stepByStepSolution = "C_A(25) / (0.1 × 25) = 2/1 ⇒ C_A = 0.20 mol/dm³.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_06_4",
                    topic = "Efflorescence & Deliquescence",
                    examType = "WAEC",
                    year = 2022,
                    question = "Which substance absorbs moisture from air AND dissolves in it to form a solution (Deliquescent)?",
                    options = listOf("Conc. H₂SO₄", "Na₂CO₃·10H₂O", "Anhydrous CaCl₂", "Silica gel"),
                    correctIndex = 2,
                    hint = "Conc. H₂SO₄ is hygroscopic (stays liquid); Na₂CO₃·10H₂O is efflorescent.",
                    stepByStepSolution = "Solid CaCl₂ and NaOH pellets absorb water until turning into liquid solution.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_06_5",
                    topic = "Amphoteric Oxides",
                    examType = "JAMB",
                    year = 2021,
                    question = "Which pair of oxides are BOTH amphoteric (react with both acids and alkalis)?",
                    options = listOf("CaO, MgO", "ZnO, Al₂O₃", "SO₂, NO₂", "CO, NO"),
                    correctIndex = 1,
                    hint = "Remember ZAP Metals: Zinc, Aluminium, Lead (PbO).",
                    stepByStepSolution = "ZnO, Al₂O₃, and PbO neutralize both HCl and NaOH.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_06_6",
                    topic = "Basicity of Acid",
                    examType = "NECO",
                    year = 2024,
                    question = "What is the basicity of Ethanoic Acid (CH₃COOH)?",
                    options = listOf("1", "2", "3", "4"),
                    correctIndex = 0,
                    hint = "Only the terminal –COOH hydrogen ionizes in water!",
                    stepByStepSolution = "The 3 methyl hydrogens stay bound to C; only 1 H⁺ ionizes → monobasic (1).",
                  ),
                ),
            ),
          ),
      ),
    )
}
