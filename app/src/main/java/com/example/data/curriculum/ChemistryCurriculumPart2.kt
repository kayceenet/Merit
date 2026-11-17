package com.example.data.curriculum

object ChemistryCurriculumPart2 {

  val modules: List<CourseModule> =
    listOf(
      CourseModule(
        id = "chem_mod_3",
        subjectId = "chemistry",
        indexLabel = "Module 03",
        title = "Energetics, Kinetics, Equilibrium & Electrochemistry",
        summary = "Master enthalpy changes, collision theory, Le Chatelier's Principle, redox states, and Faraday's electrolysis laws.",
        lessons =
          listOf(
            Lesson(
              id = "chem_07_energetics_kinetics",
              moduleId = "chem_mod_3",
              subjectId = "chemistry",
              title = "Enthalpy Changes, Collision Theory & Rate Factors",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 8 • WAEC Energetics & Rates",
              learningOutcomes =
                listOf(
                  "Distinguish exothermic (ΔH < 0) vs endothermic (ΔH > 0) energy profiles and compute ΔG = ΔH − TΔS.",
                  "Predict how concentration, surface area, temperature, and catalysts speed up reaction rates.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Exothermic, Endothermic & Gibbs Free Energy",
                    explanation =
                      "EXOTHERMIC reactions release heat to the surroundings (ΔH negative, test tube feels HOT — e.g., combustion, neutralization). ENDOTHERMIC reactions absorb heat (ΔH positive, test tube feels COLD — e.g., dissolving NH₄Cl). A reaction occurs SPONTANEOUSLY only if Gibbs Free Energy ΔG < 0!",
                    keyFormula = "\\Delta G = \\Delta H - T\\Delta S   |   \\text{Spontaneous when } \\Delta G < 0",
                    examTip =
                      "A catalyst LOWERS Activation Energy E_a by providing an alternative pathway — it NEVER alters ΔH or equilibrium yield!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Collision Theory & Reaction Rates",
                    explanation =
                      "Particles react ONLY if they collide with energy ≥ Activation Energy (E_a) and proper orientation. Grinding marble chips into powder boosts SURFACE AREA; warming increases fraction of particles with E ≥ E_a (every 10 °C rise roughly doubles rate!).",
                    keyFormula = "\\text{Rate} = \\frac{\\Delta[\\text{Product}]}{\\Delta t}   |   E_a(\\text{catalyst}) < E_a(\\text{uncatalyzed})",
                    examTip =
                      "Surface area increases initial gas evolution rate with HCl, NOT total gas volume (unless reactant mass increased)!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Spontaneity Criterion with ΔG",
                    examSource = "JAMB UTME 2024 • Chemistry Q24",
                    problemStatement =
                      "For a reaction at 300 K, ΔH = −20,000 J/mol and ΔS = −50 J/(mol·K). Calculate ΔG and state whether the process is spontaneous.",
                    steps =
                      listOf(
                        "Step 1 — Match units cleanly in Joules: T·ΔS = 300 × (−50) = −15,000 J/mol.",
                        "Step 2 — ΔG = −20,000 − (−15,000) = −5,000 J/mol (−5 kJ/mol).",
                        "Step 3 — Since ΔG < 0, the reaction is spontaneous.",
                      ),
                    finalAnswer = "ΔG = −5.0 kJ/mol (Spontaneous)",
                    examinerTrap = "NEVER subtract J/(mol·K) directly from kJ/mol without converting to the same unit first!",
                  ),
                  WorkedExample(
                    title = "Calorimetry Heat of Neutralization",
                    examSource = "WAEC SSCE 2022 • Paper 2 Q6",
                    problemStatement =
                      "When 50 cm³ of 1.0 M HCl reacts with 50 cm³ of 1.0 M NaOH (total mass = 100 g, c = 4.2 J/g·K), temperature rises from 25.0 °C to 31.8 °C. Find heat released.",
                    steps =
                      listOf(
                        "Step 1 — ΔT = 31.8 − 25.0 = 6.8 K.",
                        "Step 2 — Q = m·c·ΔT = 100 × 4.2 × 6.8 = 2856 J = 2.86 kJ (for 0.05 mol => ΔH = −57.1 kJ/mol).",
                      ),
                    finalAnswer = "2.86 kJ released (ΔH = −57.1 kJ/mol)",
                    examinerTrap = "Remember total solution mass is V_acid + V_base = 100 g!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Automobile Catalytic Converters",
                    industryDomain = "Automotive Emissions Control",
                    narrative =
                      "Platinum-rhodium honeycomb catalysts coat fine porous ceramic channels to provide huge surface area and low E_a, converting toxic CO and NOₓ into harmless N₂ and CO₂ instantly.",
                    examConnection = "Illustrates heterogeneous catalysis and surface area kinetics.",
                  ),
                  RealLifeScenario(
                    title = "Refrigeration vs Cold-Pack First Aid",
                    industryDomain = "Sports Medicine",
                    narrative =
                      "Instant cold packs crush an inner water pouch into ammonium nitrate crystals, absorbing heat endothermically (ΔH > 0) to reduce ankle swelling on the pitch.",
                    examConnection = "Classic example of an endothermic dissolution process.",
                  ),
                ),
              labType = PracticalLabType.GAS_LAW_PISTON,
              labBrief =
                "Increase Temperature in the Particle Kinetic lab below to see average kinetic energy rise, causing far more particles to exceed activation threshold E_a!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_07_1",
                    topic = "Energetics",
                    examType = "JAMB",
                    year = 2024,
                    question = "Adding a positive catalyst to a chemical reaction alters:",
                    options = listOf("The enthalpy change ΔH", "The activation energy E_a", "The equilibrium constant K", "Total product yield"),
                    correctIndex = 1,
                    hint = "Catalysts open a lower energy barrier path.",
                    stepByStepSolution = "Catalysts lower activation energy E_a equally for forward and reverse paths without shifting ΔH or yield.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_07_2",
                    topic = "Rates of Reaction",
                    examType = "WAEC",
                    year = 2023,
                    question = "Powdered CaCO₃ reacts faster with dilute HCl than lump marble chips of the same mass because powder has greater:",
                    options = listOf("Concentration", "Surface area", "Activation energy", "Molar mass"),
                    correctIndex = 1,
                    hint = "Grinding exposes far more CaCO₃ surface ions to H⁺ collisions simultaneously.",
                    stepByStepSolution = "Crushing solid increases exposed surface area, raising effective collision frequency.",
                    waecMarkingScheme = "A1: Larger surface area exposed for collisions.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_07_3",
                    topic = "Thermodynamics",
                    examType = "JAMB",
                    year = 2022,
                    question = "At equilibrium in a reversible chemical process, what is the value of Gibbs free energy change ΔG?",
                    options = listOf("ΔG > 0", "ΔG < 0", "ΔG = 0", "ΔG = ΔH"),
                    correctIndex = 2,
                    hint = "Neither forward nor reverse process is thermodynamically favored.",
                    stepByStepSolution = "At equilibrium ΔG = 0 (since forward and reverse free energies balance).",
                  ),
                ),
            ),
            Lesson(
              id = "chem_08_equilibrium_le_chatelier",
              moduleId = "chem_mod_3",
              subjectId = "chemistry",
              title = "Chemical Equilibrium & Le Chatelier's Principle",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 8.3 • WAEC Equilibrium",
              learningOutcomes =
                listOf(
                  "Write equilibrium constant expressions K_c omitting pure solids and liquids.",
                  "Predict equilibrium shifts under changes in pressure, temperature, concentration, and catalyst.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Dynamic Equilibrium & Le Chatelier's Shifts",
                    explanation =
                      "At dynamic equilibrium, forward rate = reverse rate and macroscopic concentrations stay constant. LE CHATELIER'S PRINCIPLE: if a system at equilibrium is disturbed, it shifts to OPPOSE the change! Raising Pressure shifts toward FEWER gas moles; raising Temperature shifts in the ENDOTHERMIC direction (consumes heat).",
                    keyFormula = "N_{2(g)} + 3H_{2(g)} \\rightleftharpoons 2NH_{3(g)} \\quad (\\Delta H = -92\\text{ kJ})",
                    examTip =
                      "Haber Process: 4 gas moles -> 2 gas moles (high pressure favors NH₃!); exothermic forward (moderately low T favors yield, ~450 °C + Iron catalyst balances speed!).",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Equilibrium Constant K_c Rules",
                    explanation =
                      "For aA + bB ⇌ cC + dD, K_c = [C]^c[D]^d / ([A]^a[B]^b). Include ONLY gases (g) and aqueous solutes (aq) — NEVER pure solids (s) or pure liquids (l)! ONLY TEMPERATURE changes the numerical value of K_c.",
                    keyFormula = "K_c = \\frac{[C]^c[D]^d}{[A]^a[B]^b}   |   \\text{Only Temperature alters } K_c!",
                    examTip =
                      "Adding a catalyst reaches equilibrium FASTER, but K_c and final equilibrium % yield remain identical!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Contact Process SO₃ Equilibrium Analysis",
                    examSource = "WAEC SSCE 2023 • Paper 2 Q5",
                    problemStatement =
                      "Consider 2SO₂(g) + O₂(g) ⇌ 2SO₃(g), ΔH = −197 kJ. Predict how (a) increasing pressure and (b) raising temperature affect SO₃ yield.",
                    steps =
                      listOf(
                        "Step 1 — Count gas moles: Left = 2 + 1 = 3 moles; Right = 2 moles.",
                        "Step 2 — Increasing pressure shifts toward FEWER gas moles (Right) => SO₃ yield INCREASES.",
                        "Step 3 — Forward reaction is exothermic (ΔH < 0). Raising temperature favors the endothermic reverse direction => SO₃ yield DECREASES.",
                      ),
                    finalAnswer = "(a) Yield increases; (b) Yield decreases",
                    examinerTrap = "Using V₂O₅ catalyst increases SPEED only, NOT equilibrium SO₃ concentration!",
                  ),
                  WorkedExample(
                    title = "Pressure-Invariant Equilibrium Reaction",
                    examSource = "JAMB UTME 2021 • Chemistry Q26",
                    problemStatement =
                      "For H₂(g) + I₂(g) ⇌ 2HI(g), what happens when total vessel pressure is doubled at constant temperature?",
                    steps =
                      listOf(
                        "Step 1 — Reactant gas moles = 1 + 1 = 2; Product gas moles = 2.",
                        "Step 2 — Equal gas moles on both sides means pressure changes cause NO shift in equilibrium position.",
                      ),
                    finalAnswer = "No effect on equilibrium position or K_c",
                    examinerTrap = "Always sum ONLY gaseous moles on each side!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Notore & Indorama Ammonia Fertilizer Synthesis",
                    industryDomain = "Nigerian Petrochemical Industry",
                    narrative =
                      "Natural gas provides hydrogen for Nigeria's urea plants via the Haber-Bosch equilibrium (200 atm, 450 °C, finely divided Fe catalyst), continually liquefying NH₃ out to drive conversion forward.",
                    examConnection = "Canonical WAEC industrial chemistry equilibrium case study.",
                  ),
                  RealLifeScenario(
                    title = "Carbonated Soft Drink CO₂ Fizz Equilibrium",
                    industryDomain = "Beverage Bottling",
                    narrative =
                      "Pressurized CO₂(g) ⇌ CO₂(aq) ⇌ H₂CO₃ keeps drinks fizzy while capped; cracking the cap drops pressure and drives gas out.",
                    examConnection = "Everyday example of pressure effects on gas-solution equilibrium.",
                  ),
                ),
              labType = PracticalLabType.TITRATION_PH,
              labBrief =
                "Inspect how dynamic weak-acid equilibrium (CH₃COOH ⇌ CH₃COO⁻ + H⁺) buffers pH smoothly before full neutralization in the Titration Lab.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_08_1",
                    topic = "Equilibrium",
                    examType = "JAMB",
                    year = 2024,
                    question = "In the Haber synthesis N₂(g) + 3H₂(g) ⇌ 2NH₃(g) (ΔH = −92 kJ), maximum yield of NH₃ is favored by:",
                    options =
                      listOf(
                        "High temperature, low pressure",
                        "Low temperature, high pressure",
                        "High temperature, high pressure",
                        "Adding more Fe catalyst",
                      ),
                    correctIndex = 1,
                    hint = "Forward side has fewer gas moles (2 vs 4) and releases heat.",
                    stepByStepSolution = "High pressure pushes toward 2 moles (NH₃) and lower temperature favors the exothermic forward direction.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_08_2",
                    topic = "Equilibrium",
                    examType = "WAEC",
                    year = 2022,
                    question = "Which single factor changes the numerical value of the equilibrium constant K_c?",
                    options = listOf("Concentration", "Pressure", "Temperature", "Catalyst"),
                    correctIndex = 2,
                    hint = "All other disturbances shift Q until Q returns to the same K_c.",
                    stepByStepSolution = "Only a change in temperature shifts the actual numerical value of K_c.",
                    waecMarkingScheme = "A1: Temperature.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_08_3",
                    topic = "Equilibrium",
                    examType = "JAMB",
                    year = 2023,
                    question = "Which reaction is UNAFFECTED in yield by changing vessel pressure?",
                    options =
                      listOf(
                        "2NO(g) + O₂(g) ⇌ 2NO₂(g)",
                        "PCl₅(g) ⇌ PCl₃(g) + Cl₂(g)",
                        "N₂(g) + O₂(g) ⇌ 2NO(g)",
                        "2SO₂(g) + O₂(g) ⇌ 2SO₃(g)",
                      ),
                    correctIndex = 2,
                    hint = "Look for equal total gas moles on left and right sides.",
                    stepByStepSolution = "N₂(g) + O₂(g) ⇌ 2NO(g) has 2 gas moles on each side (Δn = 0), so pressure has no net shift.",
                  ),
                ),
            ),
            Lesson(
              id = "chem_09_redox_electrolysis",
              moduleId = "chem_mod_3",
              subjectId = "chemistry",
              title = "Redox Balancing, Electrode Potentials & Faraday's Electrolysis",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 9 • WAEC Electrochemistry",
              learningOutcomes =
                listOf(
                  "Assign oxidation numbers to identify oxidizing/reducing agents via OIL-RIG.",
                  "Predict selective ion discharge and calculate deposited metal mass via m = (I·t·M)/(n·F).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Oxidation Numbers & OIL-RIG Redox",
                    explanation =
                      "OIL-RIG: Oxidation Is Loss of electrons (Oxidation Number RISES!); Reduction Is Gain of electrons (Oxidation Number DROPS!). The Oxidizing Agent gets reduced itself (e.g., purple MnO₄⁻ drops from +7 to colorless Mn²⁺; orange Cr₂O₇²⁻ drops from +6 to green Cr³⁺).",
                        keyFormula = "\\text{Rules: } H = +1,\\ O = -2,\\ \\sum \\text{Ox No.} = \\text{Ion Charge}",
                        examTip =
                          "KMnO₄ turns purple -> colorless and acidified K₂Cr₂O₇ turns orange -> green when oxidizing reducing agents like SO₂ or Fe²⁺!",
                      ),
                  ConceptBlock(
                    heading = "Step 2: Electrolysis & Faraday's First Law",
                    explanation =
                      "In an electrolytic cell, Anode is POSITIVE (Oxidation occurs here!); Cathode is NEGATIVE (Reduction deposits metals or liberates H₂!). Preferential discharge depends on (1) Position in Electrochemical Series, (2) Ion Concentration (brine discharges Cl⁻ instead of OH⁻), and (3) Electrode Nature (Cu anode dissolves in CuSO₄!).",
                    keyFormula = "Q = I \\cdot t   |   m = \\frac{I \\cdot t \\cdot M}{n \\cdot F}   \\quad (F = 96,500\\text{ C/mol } e^-)",
                    examTip =
                      "Notice Cu²⁺ requires n = 2 Faradays (2 × 96,500 C) per mole, Ag⁺ requires n = 1, and Al³⁺ requires n = 3!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Oxidation State of Chromium in Dichromate",
                    examSource = "JAMB UTME 2023 • Chemistry Q16",
                    problemStatement =
                      "Determine the oxidation number of Cr in K₂Cr₂O₇ (and hence its IUPAC name).",
                    steps =
                      listOf(
                        "Step 1 — Neutral compound sum = 0: 2(+1) + 2(Cr) + 7(−2) = 0.",
                        "Step 2 — 2 + 2Cr − 14 = 0 => 2Cr = +12 => Cr = +6.",
                      ),
                    finalAnswer = "+6 (Potassium heptaoxodichromate(VI))",
                    examinerTrap = "Don't forget to divide +12 by 2 because there are TWO Cr atoms!",
                  ),
                  WorkedExample(
                    title = "Faraday's Law Copper Cathode Deposition",
                    examSource = "WAEC SSCE 2024 • Paper 2 Q8",
                    problemStatement =
                      "A steady current of 5.0 A flows through aqueous CuSO₄ for 32 minutes 10 seconds (1930 s). Calculate the mass of copper deposited at the cathode. (Cu = 64, F = 96,500 C/mol)",
                    steps =
                      listOf(
                        "Step 1 — Total charge Q = I · t = 5.0 × 1930 = 9,650 C.",
                        "Step 2 — Cu²⁺ + 2e⁻ -> Cu (so n = 2 Faradays per mole Cu).",
                        "Step 3 — Moles of Cu = 9,650 / (2 × 96,500) = 0.05 mol.",
                        "Step 4 — Mass m = 0.05 × 64 = 3.2 g.",
                      ),
                    finalAnswer = "3.2 g",
                    examinerTrap = "Always convert minutes into SECONDS before computing Q = I·t!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Electroplating & Aluminum Bauxite Smelting",
                    industryDomain = "Metallurgy & Surface Finishing",
                    narrative =
                      "Automakers electroplate rust-resistant chrome onto steel bumpers while aluminum smelters reduce molten Al₂O₃ in cryolite at carbon cathodes using massive DC Faraday currents.",
                    examConnection = "Explains electrolytic purification of copper and industrial Al extraction.",
                  ),
                  RealLifeScenario(
                    title = "Chlor-Alkali Brine Electrolysis",
                    industryDomain = "Chemical Manufacturing",
                    narrative =
                      "Passing current through concentrated NaCl (brine) simultaneously manufactures Cl₂ gas (water disinfection), H₂, and NaOH (soap making).",
                    examConnection = "Tests concentration effect discharging Cl⁻ over OH⁻ at the anode.",
                  ),
                ),
              labType = PracticalLabType.OHMS_CIRCUIT,
              labBrief =
                "Use the DC Current analyzer below to see how circuit current I determines charge flow Q = I·t during electrolytic plating.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_09_1",
                    topic = "Faraday Electrolysis",
                    examType = "JAMB",
                    year = 2025,
                    question = "How many Faradays of electricity are required to deposit 27 g of Aluminum from molten Al₂O₃? (Al = 27)",
                    options = listOf("1 F", "2 F", "3 F", "4 F"),
                    correctIndex = 2,
                    hint = "Al³⁺ + 3e⁻ -> Al.",
                    stepByStepSolution = "27 g = 1 mole Al. Depositing 1 mol Al from Al³⁺ requires 3 moles of electrons = 3 F.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_09_2",
                    topic = "Redox Reactions",
                    examType = "WAEC",
                    year = 2023,
                    question = "In the reaction 2Fe²⁺ + Cl₂ -> 2Fe³⁺ + 2Cl⁻, chlorine acts as:",
                    options = listOf("Reducing agent", "Oxidizing agent", "Catalyst", "Dehydrating agent"),
                    correctIndex = 1,
                    hint = "Cl₂ gains electrons to become Cl⁻ (reduction).",
                    stepByStepSolution = "Cl₂ accepts electrons from Fe²⁺ (gets reduced from 0 to −1), making Cl₂ the oxidizing agent.",
                    waecMarkingScheme = "A1: Oxidizing agent (gains electrons).",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_09_3",
                    topic = "Electrolysis",
                    examType = "JAMB",
                    year = 2022,
                    question = "When dilute aqueous CuSO₄ is electrolyzed using COPPER electrodes, what happens to the anode?",
                    options = listOf("Oxygen gas evolves", "Hydrogen evolves", "The copper anode dissolves", "Blue color fades"),
                    correctIndex = 2,
                    hint = "Active copper electrodes dissolve instead of discharging SO₄²⁻ or OH⁻.",
                    stepByStepSolution = "Active Cu anode oxidizes into Cu²⁺(aq), keeping electrolyte concentration constant while refining copper.",
                  ),
                ),
            ),
          ),
      ),
      CourseModule(
        id = "chem_mod_4",
        subjectId = "chemistry",
        indexLabel = "Module 04",
        title = "Non-Metals, Metals & Organic Chemistry Mastery",
        summary = "Master Haber/Contact non-metals, blast furnace metallurgy, qualitative salt tests, and IUPAC organic synthesis.",
        lessons =
          listOf(
            Lesson(
              id = "chem_10_inorganic_qualitative",
              moduleId = "chem_mod_4",
              subjectId = "chemistry",
              title = "Non-Metals, Metallurgy & Qualitative Salt Analysis",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 10–12 • WAEC Paper 3 Qualitative",
              learningOutcomes =
                listOf(
                  "Explain Contact Process (H₂SO₄), Blast Furnace (Fe), and Hall-Héroult (Al) extractions.",
                  "Identify cations (Cu²⁺, Fe²⁺, Fe³⁺, Zn²⁺, Al³⁺, Pb²⁺, NH₄⁺) and anions (CO₃²⁻, SO₄²⁻, Cl⁻, NO₃⁻) cleanly.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Amphoteric vs Specific Cation Tests (NaOH & NH₃)",
                    explanation =
                      "Adding aqueous NaOH dropwise then in excess separates cations sharply: Cu²⁺ gives blue ppt (insoluble in excess NaOH; dissolves to DEEP BLUE in excess NH₃!); Fe²⁺ gives dirty-green ppt; Fe³⁺ gives reddish-brown ppt. Meanwhile ZINC, ALUMINUM, and LEAD ('ZAP'!) hydroxides are AMPHOTERIC — white ppt DISSOLVES in excess NaOH!",
                    keyFormula = "\\text{Amphoteric ('ZAP'): } Zn(OH)_2,\\ Al(OH)_3,\\ Pb(OH)_2\\ \\text{dissolve in excess NaOH}",
                    examTip =
                      "Among 'ZAP', ONLY Zn²⁺ dissolves in BOTH excess NaOH AND excess aqueous NH₃! Al³⁺ and Pb²⁺ stay insoluble in excess NH₃!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Anion & Metal Extraction Tests",
                    explanation =
                      "Anions: CO₃²⁻ effervesces CO₂ (turns lime water milky); SO₄²⁻ gives white BaSO₄ ppt INSOLUBLE in dilute HCl/HNO₃; Cl⁻ gives white AgCl ppt that dissolves in NH₃(aq); NO₃⁻ gives the Brown Ring with fresh FeSO₄ + conc. H₂SO₄. In Iron extraction, Limestone CaCO₃ removes sandy SiO₂ impurity as molten slag (CaSiO₃)!",
                    keyFormula = "SO_4^{2-} + Ba^{2+} \\rightarrow BaSO_4\\downarrow\\ (\\text{insoluble in dil. acid})",
                    examTip =
                      "Why add dilute HCl BEFORE BaCl₂ in the sulfate test? To destroy CO₃²⁻/SO₃²⁻ so only true SO₄²⁻ gives a white precipitate!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Distinguishing Zn²⁺ from Al³⁺ in WAEC Practical",
                    examSource = "WAEC SSCE 2023 • Paper 3 Practical",
                    problemStatement =
                      "Two unlabeled tubes contain Zn(NO₃)₂(aq) and Al(NO₃)₃(aq). Adding excess NaOH dissolves white precipitates in BOTH. State one reagent that distinguishes them cleanly.",
                    steps =
                      listOf(
                        "Step 1 — Add aqueous Ammonia (NH₃) dropwise, then in excess to fresh portions of both.",
                        "Step 2 — Both form white gelatinous ppt initially, but ONLY Zn(OH)₂ dissolves in excess NH₃(aq) forming colourless [Zn(NH₃)₄]²⁺.",
                      ),
                    finalAnswer = "Excess aqueous NH₃ (dissolves Zn(OH)₂ only; Al(OH)₃ remains)",
                    examinerTrap = "Do NOT suggest HCl or NaOH to separate Zn²⁺ and Al³⁺ — both react identically with both!",
                  ),
                  WorkedExample(
                    title = "Role of Limestone in Blast Furnace Iron Extraction",
                    examSource = "JAMB UTME 2022 • Chemistry Q34",
                    problemStatement =
                      "Write the chemical equation showing how calcium trioxocarbonate(IV) removes silica impurity in iron smelting.",
                    steps =
                      listOf(
                        "Step 1 — Heat decomposes CaCO₃ -> CaO + CO₂.",
                        "Step 2 — Basic CaO reacts with acidic silica gangue: CaO + SiO₂ -> CaSiO₃ (molten calcium trioxosilicate(IV) slag).",
                      ),
                    finalAnswer = "CaO + SiO₂ -> CaSiO₃ (slag)",
                    examinerTrap = "Limestone does NOT reduce iron ore (CO reduces Fe₂O₃); limestone removes acidic SiO₂!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Ajaokuta Iron Ore Smelting Chemistry",
                    industryDomain = "Heavy Metallurgy",
                    narrative =
                      "Iron(III) oxide hematite ore is charged with coke and limestone so carbon monoxide reduces Fe₂O₃ to pig iron while basic CaO scrubs silica into buoyant slag.",
                    examConnection = "Directly tests Blast Furnace zones and equations.",
                  ),
                  RealLifeScenario(
                    title = "Acid Rain & Sulfate Pollution Assays",
                    industryDomain = "Environmental Monitoring",
                    narrative =
                      "Water analysts acidify rainwater samples and add BaCl₂ to gravimetrically quantify sulfate contamination from industrial SO₂ emissions.",
                    examConnection = "Demonstrates the classic BaCl₂/acid sulfate identification test.",
                  ),
                ),
              labType = PracticalLabType.TITRATION_PH,
              labBrief =
                "Inspect how aqueous hydroxide concentration shifts precipitation and dissolution equilibria for amphoteric hydroxides.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_10_1",
                    topic = "Qualitative Analysis",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which cation forms a white precipitate soluble in excess NaOH AND also soluble in excess aqueous NH₃?",
                    options = listOf("Al³⁺", "Pb²⁺", "Zn²⁺", "Ca²⁺"),
                    correctIndex = 2,
                    hint = "Remember ZAP in NaOH, and ONLY Z in excess NH₃.",
                    stepByStepSolution = "Zn²⁺ forms amphoteric Zn(OH)₂ soluble in excess NaOH and forms tetraamminezinc(II) complex in excess NH₃.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_10_2",
                    topic = "Anion Tests",
                    examType = "WAEC",
                    year = 2023,
                    question = "A salt gives a white precipitate with BaCl₂(aq) that remains insoluble after adding dilute HCl. The anion present is:",
                    options = listOf("CO₃²⁻", "SO₃²⁻", "SO₄²⁻", "NO₃⁻"),
                    correctIndex = 2,
                    hint = "BaCO₃ and BaSO₃ dissolve in dilute HCl; BaSO₄ does not.",
                    stepByStepSolution = "Barium tetraoxosulfate(VI) (BaSO₄) is insoluble in dilute mineral acids, confirming SO₄²⁻.",
                    waecMarkingScheme = "A1: SO₄²⁻ / tetraoxosulfate(VI) ion.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_10_3",
                    topic = "Metals & Extraction",
                    examType = "JAMB",
                    year = 2021,
                    question = "In the extraction of aluminum by electrolysis of alumina (Al₂O₃), cryolite (Na₃AlF₆) is added primarily to:",
                    options =
                      listOf(
                        "Oxidize carbon anodes",
                        "Lower the melting point of alumina",
                        "Prevent iron rusting",
                        "Increase oxygen evolution",
                      ),
                    correctIndex = 1,
                    hint = "Pure Al₂O₃ melts above 2000 °C!",
                    stepByStepSolution = "Cryolite lowers the operating melting temperature from ~2045 °C down to ~950 °C and improves conductivity.",
                  ),
                ),
            ),
            Lesson(
              id = "chem_11_organic_chemistry",
              moduleId = "chem_mod_4",
              subjectId = "chemistry",
              title = "Organic Chemistry: Homologous Series, Isomers & Esterification",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 13 • WAEC Organic Chemistry",
              learningOutcomes =
                listOf(
                  "Apply IUPAC naming and functional-group reactions across Alkanes, Alkenes, Alkynes, Alkanols, and Alkanoic Acids.",
                  "Distinguish unsaturated tests (Br₂ water), terminal alkyne precipitation (AgNO₃), esterification, and saponification.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Hydrocarbons, Functional Groups & Isomerism",
                    explanation =
                      "Alkanes (CₙH₂ₙ₊₂, single C−C) undergo SUBSTITUTION with halogens in UV light. Alkenes (CₙH₂ₙ, C=C) and Alkynes (CₙH₂ₙ₋₂, C≡C) are UNSATURATED — both decolorize reddish-brown Bromine Water rapidly by ADDITION! Structural isomers share the same molecular formula (e.g., Butane vs 2-methylpropane, C₄H₁₀) with different bonding.",
                    keyFormula = "\\text{Alkane } C_nH_{2n+2}\\ |\\ \\text{Alkene } C_nH_{2n}\\ |\\ \\text{Alkyne } C_nH_{2n-2}",
                    examTip =
                      "Terminal alkynes (HC≡CH) react with ammoniacal AgNO₃ to give a white/yellow precipitate (alkenes CANNOT do this!).",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Alkanols, Oxidation, Esterification & Polymers",
                    explanation =
                      "Primary alkanols (1°, −CH₂OH) oxidize via K₂Cr₂O₇/H⁺ to Alkanals then Alkanoic Acids; Secondary (2°) oxidize to Alkanones; Tertiary (3°) resist oxidation! Carboxylic Acid + Alkanol (conc. H₂SO₄ catalyst, heat) reversibly yields sweet-smelling ESTER + Water. Boiling fats/esters with NaOH yields SOAP + glycerol (Saponification)!",
                    keyFormula = "RCOOH + R'OH \\xrightleftharpoons{H_2SO_4} RCOOR' + H_2O   |   \\text{Fat} + 3\\text{NaOH} \\rightarrow \\text{Soap} + \\text{Glycerol}",
                    examTip =
                      "Always name Esters backward from Alcohol first + Acid second: Ethanol + Ethanoic acid -> Ethyl ethanoate!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "IUPAC Naming & Isomers of C₄H₉OH / C₅H₁₂",
                    examSource = "JAMB UTME 2024 • Chemistry Q38",
                    problemStatement =
                      "Give the IUPAC name of CH₃−CH(CH₃)−CH=CH₂ and state how it reacts with HBr.",
                    steps =
                      listOf(
                        "Step 1 — Number longest carbon chain containing the double bond C=C from the end giving C=C the lowest locant (right -> left).",
                        "Step 2 — C=C is at C1, methyl group is on C3 => 3-methylbut-1-ene.",
                        "Step 3 — Markovnikov addition of HBr puts H on C1 (more H's) and Br on C2 -> 2-bromo-3-methylbutane.",
                      ),
                    finalAnswer = "3-methylbut-1-ene -> 2-bromo-3-methylbutane",
                    examinerTrap = "Double bond gets priority numbering over alkyl substituents!",
                  ),
                  WorkedExample(
                    title = "Distinguishing Primary Alkanol & Alkanoic Acid",
                    examSource = "WAEC SSCE 2023 • Paper 2 Q10",
                    problemStatement =
                      "How do you chemically distinguish Ethanol (CH₃CH₂OH) from Ethanoic acid (CH₃COOH) in the laboratory?",
                    steps =
                      listOf(
                        "Step 1 — Add solid/aqueous Na₂CO₃ or NaHCO₃ to separate samples.",
                        "Step 2 — Ethanoic acid produces brisk effervescence of CO₂ (turns lime water milky); Ethanol shows no effervescence.",
                      ),
                    finalAnswer = "Brisk CO₂ effervescence with Na₂CO₃/NaHCO₃ confirms CH₃COOH",
                    examinerTrap = "Sodium metal reacts with BOTH alkanols and acids (releasing H₂), so use carbonate/bicarbonate to test acidity specifically!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Palm-Oil Soap Saponification & Perfume Esters",
                    industryDomain = "Consumer & Fragrance Manufacturing",
                    narrative =
                      "Local soap makers saponify bleached palm-oil triglycerides with caustic soda (NaOH) to craft hard soaps plus moisturizing glycerol, then scent them with fruity ethyl butanoate esters.",
                    examConnection = "Directly connects esterification reversibility and irreversible alkaline saponification.",
                  ),
                  RealLifeScenario(
                    title = "Polythene Packaging vs Nylon Condensation Fibers",
                    industryDomain = "Polymer Engineering",
                    narrative =
                      "Addition polymerization opens ethene C=C double bonds into pure polyethene bags without by-products, whereas condensation polymerization links diamines + diacids into Nylon-6,6 while eliminating H₂O.",
                    examConnection = "Frequent WAEC question comparing addition vs condensation polymerization.",
                  ),
                ),
              labType = PracticalLabType.TITRATION_PH,
              labBrief =
                "Inspect weak organic acid behavior (CH₃COOH) in the Titration Lab to observe how carboxylic acidity differs from strong mineral acids!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_11_1",
                    topic = "Organic Chemistry",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which ester is formed when methanol reacts with propanoic acid in the presence of concentrated H₂SO₄?",
                    options = listOf("Propyl methanoate", "Methyl propanoate", "Methyl ethanoate", "Ethyl propanoate"),
                    correctIndex = 1,
                    hint = "Alkyl part comes from the alcohol (methanol -> methyl); -oate comes from the acid (propanoic -> propanoate).",
                    stepByStepSolution = "CH₃OH + CH₃CH₂COOH -> CH₃CH₂COOCH₃ (Methyl propanoate) + H₂O.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_11_2",
                    topic = "Alkanols & Oxidation",
                    examType = "WAEC",
                    year = 2023,
                    question = "Complete oxidation of ethanol by excess acidified K₂Cr₂O₇ produces:",
                    options = listOf("Ethene", "Ethanal only", "Ethanoic acid", "Ethyl ethanoate"),
                    correctIndex = 2,
                    hint = "1° alcohols oxidize through aldehyde straight to carboxylic acid under reflux.",
                    stepByStepSolution = "CH₃CH₂OH -> [CH₃CHO] -> CH₃COOH (ethanoic acid), turning dichromate from orange to green.",
                    waecMarkingScheme = "A1: Ethanoic acid (CH₃COOH).",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_11_3",
                    topic = "Hydrocarbons",
                    examType = "JAMB",
                    year = 2024,
                    question = "How many structural isomers exist for the alkane pentane (C₅H₁₂)?",
                    options = listOf("2", "3", "4", "5"),
                    correctIndex = 1,
                    hint = "Draw n-pentane, 2-methylbutane, and 2,2-dimethylpropane.",
                    stepByStepSolution = "C₅H₁₂ has exactly 3 structural isomers: pentane, 2-methylbutane, and 2,2-dimethylpropane.",
                  ),
                ),
            ),
          ),
      ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion(
        id = "pq_chem_2025_1",
        topic = "Stoichiometry",
        examType = "JAMB",
        year = 2025,
        question = "Calculate the mass of NaOH required to prepare 250 cm³ of a 0.20 mol/dm³ solution (Na=23, O=16, H=1).",
        options = listOf("1.0 g", "2.0 g", "4.0 g", "8.0 g"),
        correctIndex = 1,
        hint = "Moles n = C × V(dm³) = 0.20 × 0.25.",
        stepByStepSolution = "n = 0.20 × 0.250 = 0.05 mol. Molar mass NaOH = 40 g/mol => Mass = 0.05 × 40 = 2.0 g.",
      ),
      PracticeQuestion(
        id = "pq_chem_2025_2",
        topic = "Volumetric Titration",
        examType = "WAEC",
        year = 2025,
        question = "20.0 cm³ of 0.25 mol/dm³ H₂SO₄ is completely neutralized by 25.0 cm³ of NaOH. What is the molar concentration of NaOH?",
        options = listOf("0.20 mol/dm³", "0.40 mol/dm³", "0.50 mol/dm³", "0.80 mol/dm³"),
        correctIndex = 1,
        hint = "H₂SO₄ + 2NaOH -> Na₂SO₄ + 2H₂O (n_A = 1, n_B = 2).",
        stepByStepSolution = "(0.25 × 20) / (C_B × 25) = 1 / 2 => 5 / (25 C_B) = 0.5 => C_B = 0.40 mol/dm³.",
        waecMarkingScheme = "M1: Mole ratio 1:2; A1: 0.40 mol/dm³.",
      ),
      PracticeQuestion(
        id = "pq_chem_2024_1",
        topic = "Periodic Table",
        examType = "JAMB",
        year = 2024,
        question = "Which halogen exists as a reddish-brown volatile liquid at room temperature?",
        options = listOf("Fluorine", "Chlorine", "Bromine", "Iodine"),
        correctIndex = 2,
        hint = "F₂ and Cl₂ are gases; I₂ is a dark shiny solid.",
        stepByStepSolution = "Bromine (Br₂) is the only non-metal liquid at room temperature.",
      ),
      PracticeQuestion(
        id = "pq_chem_2024_2",
        topic = "Organic Chemistry",
        examType = "WAEC",
        year = 2024,
        question = "Dehydration of ethanol by excess concentrated H₂SO₄ at 170 °C yields:",
        options = listOf("Ethoxyethane", "Ethene", "Ethyne", "Ethane"),
        correctIndex = 1,
        hint = "At 140 °C with excess ethanol, ether forms; at 170 °C alkene forms.",
        stepByStepSolution = "CH₃CH₂OH --(conc. H₂SO₄, 170 °C)--> CH₂=CH₂ (ethene) + H₂O.",
        waecMarkingScheme = "A1: Ethene.",
      ),
      PracticeQuestion(
        id = "pq_chem_2023_1",
        topic = "Organic Chemistry",
        examType = "WAEC",
        year = 2023,
        question = "Which reagent distinguishes terminal alkynes (e.g., ethyne) from alkenes?",
        options = listOf("Bromine water", "Acidified KMnO₄", "Ammoniacal silver trioxonitrate(V)", "Sodium metal"),
        correctIndex = 2,
        hint = "Both alkenes and alkynes decolorize bromine water and KMnO₄.",
        stepByStepSolution = "Terminal alkynes form a white/yellow precipitate with ammoniacal AgNO₃ (or red ppt with Cu₂Cl₂), whereas alkenes do not.",
        waecMarkingScheme = "A1: Ammoniacal AgNO₃ forms insoluble acetylide precipitate.",
      ),
      PracticeQuestion(
        id = "pq_chem_2023_2",
        topic = "Gas Laws",
        examType = "JAMB",
        year = 2023,
        question = "A gas occupies 200 cm³ at 27 °C. What volume will it occupy at 327 °C under constant pressure?",
        options = listOf("100 cm³", "300 cm³", "400 cm³", "600 cm³"),
        correctIndex = 2,
        hint = "Convert temperatures to Kelvin: 300 K and 600 K.",
        stepByStepSolution = "V₂ = V₁(T₂/T₁) = 200 × (600 / 300) = 400 cm³.",
      ),
      PracticeQuestion(
        id = "pq_chem_2022_1",
        topic = "Electrolysis",
        examType = "JAMB",
        year = 2022,
        question = "During the electrolysis of concentrated sodium chloride solution (brine) using carbon electrodes, the main product at the anode is:",
        options = listOf("Oxygen", "Chlorine", "Hydrogen", "Sodium"),
        correctIndex = 1,
        hint = "High Cl⁻ concentration overrides standard electrode potential of OH⁻.",
        stepByStepSolution = "In concentrated NaCl, Cl⁻ is preferentially oxidized at the anode to liberate Chlorine gas (Cl₂).",
      ),
      PracticeQuestion(
        id = "pq_chem_2022_2",
        topic = "Water & Hard Water",
        examType = "WAEC",
        year = 2022,
        question = "Temporary hardness of water is caused by the presence of dissolved:",
        options = listOf("Calcium sulfate", "Magnesium chloride", "Calcium hydrogentrioxocarbonate(IV)", "Sodium carbonate"),
        correctIndex = 2,
        hint = "Boiling precipitates insoluble CaCO₃ from bicarbonate.",
        stepByStepSolution = "Ca(HCO₃)₂(aq) decomposes upon boiling into insoluble CaCO₃ + H₂O + CO₂, removing temporary hardness.",
        waecMarkingScheme = "A1: Ca(HCO₃)₂ or Mg(HCO₃)₂.",
      ),
      PracticeQuestion(
        id = "pq_chem_2021_1",
        topic = "Oxidation States",
        examType = "JAMB",
        year = 2021,
        question = "What is the oxidation number of Manganese in KMnO₄?",
        options = listOf("+2", "+4", "+6", "+7"),
        correctIndex = 3,
        hint = "+1 + Mn + 4(−2) = 0.",
        stepByStepSolution = "1 + Mn − 8 = 0 => Mn = +7.",
      ),
      PracticeQuestion(
        id = "pq_chem_2021_2",
        topic = "Air & Gases",
        examType = "WAEC",
        year = 2021,
        question = "Which gas turns moist red litmus paper blue and forms dense white fumes with concentrated HCl?",
        options = listOf("CO₂", "SO₂", "NH₃", "Cl₂"),
        correctIndex = 2,
        hint = "It is the only common alkaline gas.",
        stepByStepSolution = "Ammonia (NH₃) is alkaline (red -> blue) and combines with HCl vapor to form white NH₄Cl fumes.",
        waecMarkingScheme = "A1: Ammonia (NH₃).",
      ),
      PracticeQuestion(
        id = "pq_chem_2020_1",
        topic = "Stoichiometry",
        examType = "JAMB",
        year = 2020,
        question = "What is the percentage by mass of Nitrogen in urea, CO(NH₂)₂? (C=12, O=16, N=14, H=1)",
        options = listOf("23.3%", "35.0%", "46.7%", "53.3%"),
        correctIndex = 2,
        hint = "Molar mass = 12 + 16 + 2(14 + 2) = 60 g/mol; N mass = 28.",
        stepByStepSolution = "%N = (28 / 60) × 100% = 46.7%.",
      ),
      PracticeQuestion(
        id = "pq_chem_2020_2",
        topic = "Kinetics",
        examType = "WAEC",
        year = 2020,
        question = "When acidified KMnO₄ reacts with oxalic acid, Mn²⁺ formed speeds up the reaction automatically. Here Mn²⁺ acts as:",
        options = listOf("An inhibitor", "A promoter", "An autocatalyst", "A reducing agent"),
        correctIndex = 2,
        hint = "A reaction product catalyzing its own reaction is an autocatalyst.",
        stepByStepSolution = "Mn²⁺ generated during reduction catalyzes further oxidation of oxalate -> autocatalysis.",
        waecMarkingScheme = "A1: Autocatalyst.",
      ),
      PracticeQuestion(
        id = "pq_chem_2019_1",
        topic = "Polymers & Biomolecules",
        examType = "JAMB",
        year = 2019,
        question = "Which pair represents natural condensation polymers?",
        options = listOf("Polyethene and PVC", "Starch and Proteins", "Polystyrene and Perspex", "Teflon and Nylon"),
        correctIndex = 1,
        hint = "Glucose unites releasing H₂O (starch); amino acids link releasing H₂O (proteins).",
        stepByStepSolution = "Both starch (polysaccharide) and proteins (polypeptides) are natural condensation polymers.",
      ),
      PracticeQuestion(
        id = "pq_chem_2019_2",
        topic = "Metals",
        examType = "WAEC",
        year = 2019,
        question = "Galvanizing prevents iron from rusting by coating it with a thin layer of:",
        options = listOf("Tin", "Zinc", "Copper", "Lead"),
        correctIndex = 1,
        hint = "Sacrificial metal more reactive than iron.",
        stepByStepSolution = "Zinc is higher than iron in the electrochemical series and sacrifices itself preferentially even if scratched.",
        waecMarkingScheme = "A1: Zinc (sacrificial protection).",
      ),
      PracticeQuestion(
        id = "pq_chem_2018_1",
        topic = "Atomic Bonding",
        examType = "JAMB",
        year = 2018,
        question = "Which solid conducts electricity in the solid state due to delocalized valence electrons?",
        options = listOf("Sodium chloride", "Diamond", "Graphite", "Sulfur"),
        correctIndex = 2,
        hint = "Three carbons bond per atom leaving one free π electron per carbon.",
        stepByStepSolution = "Graphite has mobile delocalized electrons between hexagonal layers; NaCl only conducts when molten or aqueous.",
      ),
      PracticeQuestion(
        id = "pq_chem_2018_2",
        topic = "Water Purity",
        examType = "WAEC",
        year = 2018,
        question = "In municipal water treatment, alum (potassium aluminum sulfate) is added primarily to cause:",
        options = listOf("Disinfection", "Coagulation / flocculation of fine suspended clay", "Water softening", "pH neutralization"),
        correctIndex = 1,
        hint = "Chlorine disinfects; alum clumps muddy particles together.",
        stepByStepSolution = "Al³⁺ ions coagulate negatively charged colloidal mud particles so they settle out rapidly.",
        waecMarkingScheme = "A1: Coagulation/flocculation of suspended particles.",
      ),
    )
}
