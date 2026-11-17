package com.example.data.curriculum

object ChemistryCurriculumPart2 {

  val module3And4: List<CourseModule> =
    listOf(
      CourseModule(
        id = "chem_mod_3",
        subjectId = "chemistry",
        indexLabel = "Module 03",
        title = "Energetics, Kinetics, Equilibrium, Redox & Electrolysis",
        summary = "Master ΔH, Hess's Law, Entropy ΔG, Activation Energy, Le Chatelier, Oxidation States, and Faraday's Laws of Electrolysis.",
        lessons =
          listOf(
            Lesson(
              id = "chem_07_thermochemistry",
              moduleId = "chem_mod_3",
              subjectId = "chemistry",
              title = "Thermochemistry, Enthalpy, Entropy & Free Energy",
              durationMinutes = 19,
              syllabusTag = "JAMB Sec. 9 • WAEC Energy Changes & Spontaneity",
              learningOutcomes =
                listOf(
                  "Distinguish Exothermic (ΔH < 0, feels hot) from Endothermic (ΔH > 0, feels cold).",
                  "Compute ΔH using Hess's Law & Bond Energies (Bonds Broken − Bonds Formed).",
                  "Predict spontaneity using ΔG = ΔH − TΔS (negative ΔG means spontaneous).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Exothermic vs Endothermic & Bond Energy Math",
                    explanation =
                      "Breaking a bond ALWAYS costs energy (like snapping a stick—endothermic +); making a new bond RELEASES energy (exothermic −). So ΔH = Sum(Bonds Broken) − Sum(Bonds Formed)! In Exothermic reactions, products sit LOWER on the energy hill and give off heat (ΔH is NEGATIVE, e.g., burning petrol or acid + base neutralization).",
                    keyFormula = "\\Delta H = \\sum E_{\\text{broken}} - \\sum E_{\\text{formed}}   |   \\Delta G = \\Delta H - T\\Delta S",
                    examTip =
                      "Any Strong Acid + Strong Base neutralization ALWAYS releases −57.3 kJ per mole of H₂O formed!",
                  ),
                  ConceptBlock(
                    heading = "2. Entropy (ΔS) & Gibbs Free Energy (ΔG)",
                    explanation =
                      "Entropy (S) measures messiness/disorder: Gases are super messy (highest S), Liquids medium, neat Crystals lowest S! A reaction happens ALL BY ITSELF (spontaneous) whenever Gibbs Free Energy ΔG = ΔH − TΔS is NEGATIVE (< 0). That explains why ice melts on a warm day even though melting is endothermic!",
                    keyFormula = "\\Delta G < 0 \\implies \\text{Spontaneous}   |   \\Delta G = 0 \\implies \\text{Equilibrium}",
                    examTip =
                      "Melting, boiling, and making MORE gas moles (e.g., CaCO₃(s) → CaO(s) + CO₂(g)) always make ΔS POSITIVE!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Calculating ΔH Using Bond Energies",
                    examSource = "JAMB UTME 2024 • Chem Q20",
                    problemStatement =
                      "For H₂ + Cl₂ → 2HCl, given H−H = 436, Cl−Cl = 242, H−Cl = 431 kJ/mol, find ΔH.",
                    steps =
                      listOf(
                        "Step 1 — Bonds Broken = 436 + 242 = 678 kJ.",
                        "Step 2 — Bonds Formed (2 mol H−Cl) = 2 × 431 = 862 kJ.",
                        "Step 3 — ΔH = 678 − 862 = −184 kJ (Exothermic).",
                      ),
                    finalAnswer = "−184 kJ",
                    examinerTrap = "Don't swap Formed − Broken, and remember to multiply H−Cl by 2!",
                  ),
                  WorkedExample(
                    title = "Gibbs Free Energy Spontaneity Check",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q22",
                    problemStatement = "If ΔH = −20 kJ/mol and ΔS = −0.05 kJ/(mol·K) at 300 K, find ΔG.",
                    steps =
                      listOf(
                        "Step 1 — Ensure consistent units (both kJ): TΔS = 300 × (−0.05) = −15 kJ.",
                        "Step 2 — ΔG = −20 − (−15) = −5 kJ/mol (< 0 ⇒ Spontaneous!).",
                      ),
                    finalAnswer = "−5 kJ/mol (Spontaneous)",
                    examinerTrap = "Always convert ΔS from J to kJ (divide by 1000) before subtracting TΔS from ΔH!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Sports Cold Packs vs Self-Heating Meal Cans",
                    industryDomain = "Healthcare & Outdoor Gear",
                    narrative =
                      "Instant ice packs dissolve NH₄NO₃ endothermically (ΔH > 0, drawing heat from a sprained ankle), while CaO + water reacts exothermically to boil field coffee.",
                    examConnection = "Dissolution enthalpy vs hydration enthalpy sign flip.",
                  ),
                ),
              labType = PracticalLabType.ENERGETICS_KINETICS_LAB,
              labBrief =
                "Toggle Exothermic vs Endothermic enthalpy ΔH profiles and switch a Catalyst ON to observe Activation Energy Eₐ drop without shifting ΔH.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_07_1",
                    topic = "Enthalpy Sign",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which reaction always has a negative enthalpy change (ΔH < 0)?",
                    options = listOf("Thermal decomposition", "Photosynthesis", "Combustion of alkanes", "Melting of ice"),
                    correctIndex = 2,
                    hint = "Burning fuel releases heat to surroundings.",
                    stepByStepSolution = "Combustion is always exothermic (ΔH < 0).",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_07_2",
                    topic = "Hess's Law",
                    examType = "WAEC",
                    year = 2023,
                    question = "Hess's Law states that total enthalpy change of a reaction depends only on:",
                    options = listOf("Presence of a catalyst", "Initial reactants & final products", "Number of intermediate steps", "Reaction rate"),
                    correctIndex = 1,
                    hint = "Energy conservation means route doesn't matter.",
                    stepByStepSolution = "ΔH is a state function independent of the pathway taken.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_07_3",
                    topic = "Entropy Direction",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which process involves a DECREASE in entropy (ΔS < 0)?",
                    options = listOf("Evaporating ethanol", "Dissolving sugar in tea", "Freezing liquid water to ice", "N₂O₄(g) → 2NO₂(g)"),
                    correctIndex = 2,
                    hint = "Freezing locks free liquid particles into an ordered crystal.",
                    stepByStepSolution = "Liquid → Solid reduces disorder ⇒ ΔS < 0.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_07_4",
                    topic = "Standard Neutralization",
                    examType = "WAEC",
                    year = 2022,
                    question = "Why is heat of neutralization of CH₃COOH + NaOH less exothermic (−55 kJ) than HCl + NaOH (−57.3 kJ)?",
                    options = listOf("NaOH is weak", "Energy is absorbed to ionize weak ethanoic acid", "Water evaporates", "CO₂ escapes"),
                    correctIndex = 1,
                    hint = "CH₃COOH isn't 100% ionized beforehand.",
                    stepByStepSolution = "Part of the heat is consumed to dissociate unionized CH₃COOH molecules.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_07_5",
                    topic = "Gibbs Condition",
                    examType = "JAMB",
                    year = 2021,
                    question = "A reaction is spontaneous at ALL temperatures whenever:",
                    options = listOf("ΔH > 0 and ΔS < 0", "ΔH < 0 and ΔS > 0", "ΔH > 0 and ΔS > 0", "ΔH < 0 and ΔS < 0"),
                    correctIndex = 1,
                    hint = "ΔG = ΔH − TΔS must stay negative for every T > 0.",
                    stepByStepSolution = "Negative ΔH minus positive TΔS is always negative.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_07_6",
                    topic = "Calorimetry",
                    examType = "JAMB",
                    year = 2024,
                    question = "Burning 0.5 mol of fuel heats 1000 g water by 20°C (c = 4.2 J/g°C). Molar ΔH is:",
                    options = listOf("−168 kJ/mol", "−84 kJ/mol", "−42 kJ/mol", "−336 kJ/mol"),
                    correctIndex = 0,
                    hint = "Q = mcΔT = 1000 × 4.2 × 20 = 84 kJ for 0.5 mol!",
                    stepByStepSolution = "ΔH = −84 kJ / 0.5 mol = −168 kJ/mol.",
                  ),
                ),
            ),

            Lesson(
              id = "chem_08_kinetics_equilibrium",
              moduleId = "chem_mod_3",
              subjectId = "chemistry",
              title = "Reaction Rates, Catalysts & Le Chatelier Equilibrium",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 10 • WAEC Kinetics & Reversible Equilibrium",
              learningOutcomes =
                listOf(
                  "Explain how Surface Area, Concentration, Temperature (+10°C doubles rate!), and Catalysts change collision frequency.",
                  "Predict Equilibrium shift under Le Chatelier's Principle for T, P, and Concentration.",
                  "Write Equilibrium Constant K_c expressions omitting pure Solids and Liquids.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Collision Theory & How Catalysts Work",
                    explanation =
                      "Molecules must smash together HARD enough (above Activation Energy Eₐ) to react! Grinding marble lumps into powder speeds fizzing because Surface Area explodes; warming by 10°C roughly DOUBLES successful collisions; and a Catalyst builds a lower mountain tunnel—slashing Eₐ so BOTH forward & reverse reactions speed up equally WITHOUT shifting final equilibrium yield!",
                    keyFormula = "\\text{Catalyst: } E_a \\downarrow,\\; \\text{Rate} \\uparrow,\\; \\Delta H \\text{ & } K_c \\text{ unchanged!}",
                    examTip =
                      "Catalyst NEVER increases the final equilibrium yield—it simply reaches equilibrium FASTER!",
                  ),
                  ConceptBlock(
                    heading = "2. Le Chatelier's Principle: The Stubborn Seesaw!",
                    explanation =
                      "A reversible reaction at equilibrium behaves like a stubborn toddler: whatever push you apply, it shifts the OPPOSITE way! Raise Pressure? Shifts toward FEWER GAS MOLES! Add Heat (raise T)? Shifts in the ENDOTHERMIC direction (forward if ΔH > 0, backward if ΔH < 0)! Note: ONLY Temperature can change the numerical value of K_c!",
                    keyFormula = "aA + bB \\rightleftharpoons cC + dD \\implies K_c = \\frac{[C]^c[D]^d}{[A]^a[B]^b}\\; (\\text{gases/aq only})",
                    examTip =
                      "In Haber Process (N₂ + 3H₂ ⇌ 2NH₃, ΔH = −92 kJ): 4 gas moles → 2 gas moles! High Pressure + Moderately Low T + Fe Catalyst!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Le Chatelier Shifts in Contact Process (SO₃)",
                    examSource = "JAMB UTME 2024 • Chem Q23",
                    problemStatement =
                      "For 2SO₂(g) + O₂(g) ⇌ 2SO₃(g), ΔH = −197 kJ. Predict the effect of (i) raising pressure and (ii) raising temperature.",
                    steps =
                      listOf(
                        "Step 1 — Count gas moles: Left = 2 + 1 = 3 mol; Right = 2 mol.",
                        "Step 2 — Higher Pressure favors FEWER moles → shifts RIGHT (more SO₃).",
                        "Step 3 — Forward is exothermic (ΔH < 0), so Higher T shifts LEFT (endothermic) & lowers K_c.",
                      ),
                    finalAnswer = "Pressure ↑ shifts Right  |  Temp ↑ shifts Left",
                    examinerTrap = "Don't count solids (like CaCO₃(s)) when comparing gas mole counts for pressure shifts!",
                  ),
                  WorkedExample(
                    title = "Writing Homogeneous Equilibrium Constant K_c",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q24",
                    problemStatement = "Write K_c for CaCO₃(s) ⇌ CaO(s) + CO₂(g) and N₂(g) + 3H₂(g) ⇌ 2NH₃(g).",
                    steps =
                      listOf(
                        "Step 1 — Omit solids CaCO₃(s) and CaO(s): K_c = [CO₂].",
                        "Step 2 — Raise concentrations to stoichiometric coefficients: K_c = [NH₃]² / ([N₂][H₂]³).",
                      ),
                    finalAnswer = "K_c = [CO₂]  |  [NH₃]² / ([N₂][H₂]³)",
                    examinerTrap = "Solids have constant density so they NEVER appear in K_c or K_p!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Catalytic Converters & Industrial Ammonia Fertilizer",
                    industryDomain = "Chemical Engineering",
                    narrative =
                      "Car exhausts contain Platinum/Rhodium honeycombs that instantly convert toxic CO + NO into harmless CO₂ + N₂ without being consumed.",
                    examConnection = "Shows industrial compromise temperature (450°C in Haber) balancing rate vs equilibrium yield.",
                  ),
                ),
              labType = PracticalLabType.ENERGETICS_KINETICS_LAB,
              labBrief =
                "Adjust Temperature, Pressure, and Catalyst toggle on N₂ + 3H₂ ⇌ 2NH₃ to watch Le Chatelier's yield bar shift live!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_08_1",
                    topic = "Reaction Rate Factors",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which change increases reaction rate by providing an alternative pathway with LOWER activation energy?",
                    options = listOf("Increasing concentration", "Raising temperature", "Adding a positive catalyst", "Grinding solid chunks"),
                    correctIndex = 2,
                    hint = "Only a catalyst alters Eₐ itself.",
                    stepByStepSolution = "A catalyst creates an alternate mechanism requiring lower Eₐ.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_08_2",
                    topic = "Pressure & Equilibrium",
                    examType = "WAEC",
                    year = 2023,
                    question = "Pressure changes will NOT affect equilibrium position in which reaction?",
                    options = listOf("N₂(g) + 3H₂(g) ⇌ 2NH₃(g)", "2SO₂(g) + O₂(g) ⇌ 2SO₃(g)", "H₂(g) + I₂(g) ⇌ 2HI(g)", "PCl₅(g) ⇌ PCl₃(g) + Cl₂(g)"),
                    correctIndex = 2,
                    hint = "Look for equal gas moles on both sides!",
                    stepByStepSolution = "H₂ + I₂ ⇌ 2HI has 2 gas moles on Left and 2 on Right, so Δn = 0.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_08_3",
                    topic = "Equilibrium Constant",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which single factor alters the numerical value of Equilibrium Constant K_c?",
                    options = listOf("Adding more reactant", "Increasing pressure", "Adding a catalyst", "Changing temperature"),
                    correctIndex = 3,
                    hint = "K_c is strictly a function of Temperature only.",
                    stepByStepSolution = "Concentration, pressure, and catalysts leave K_c unchanged; only Temperature alters K_c.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_08_4",
                    topic = "Surface Area",
                    examType = "WAEC",
                    year = 2022,
                    question = "Powdered CaCO₃ reacts much faster with dilute HCl than lumps of the same mass because powder has:",
                    options = listOf("Higher density", "Lower activation energy", "Larger total surface area", "More moles"),
                    correctIndex = 2,
                    hint = "Breaking solids exposes far more surface particles to acid collisions.",
                    stepByStepSolution = "Greater surface area boosts collision frequency per second.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_08_5",
                    topic = "Endothermic Shift",
                    examType = "JAMB",
                    year = 2021,
                    question = "For N₂O₄(g) [colourless] ⇌ 2NO₂(g) [brown], ΔH = +58 kJ. Heating the sealed tube turns the gas:",
                    options = listOf("Lighter yellow", "Darker brown", "Colourless", "Condenses to liquid"),
                    correctIndex = 1,
                    hint = "Forward reaction is endothermic (+58 kJ).",
                    stepByStepSolution = "Raising T favors endothermic forward reaction, producing more brown NO₂.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_08_6",
                    topic = "Rate Law Order",
                    examType = "JAMB",
                    year = 2024,
                    question = "If Rate = k[A]²[B], doubling [A] while keeping [B] constant multiplies the rate by:",
                    options = listOf("2", "4", "8", "1"),
                    correctIndex = 1,
                    hint = "2² = 4.",
                    stepByStepSolution = "Second-order in A means rate increases by 2² = 4 times.",
                  ),
                ),
            ),

            Lesson(
              id = "chem_09_redox_cells",
              moduleId = "chem_mod_3",
              subjectId = "chemistry",
              title = "Redox Reactions, Oxidation Numbers & Electrochemical Cells",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 11 • WAEC Redox & Daniell Cell",
              learningOutcomes =
                listOf(
                  "Assign oxidation numbers in complex ions (MnO₄⁻, Cr₂O₇²⁻) cleanly.",
                  "Identify Oxidizing Agent vs Reducing Agent using OIL RIG.",
                  "Calculate standard cell EMF E°_cell = E°_cathode − E°_anode.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. OIL RIG & Oxidation Number Rules",
                    explanation =
                      "Oxidation Is Loss of electrons (Oxidation Number climbs UP!); Reduction Is Gain of electrons (Oxidation Number drops DOWN!). Crucial twist: the species that GETS Oxidized is actually the REDUCING AGENT (it handed its electrons away to reduce the partner)! Quick rules: free elements = 0, Group 1 = +1, Hydrogen = +1, Oxygen = −2 (except peroxides −1).",
                    keyFormula = "\\text{MnO}_4^- \\to \\text{Mn}^{2+}\\; (+7 \\to +2:\\text{ Oxidizing Agent})   |   E^\\circ_{\\text{cell}} = E^\\circ_{\\text{red}} - E^\\circ_{\\text{ox}}",
                    examTip =
                      "Spot KMnO₄ (purple → colourless) and acidified K₂Cr₂O₇ (orange → green): both are superstar OXIDIZING agents!",
                  ),
                  ConceptBlock(
                    heading = "2. Galvanic Cells vs Electrolytic Cells (AN OX / RED CAT)",
                    explanation =
                      "Across BOTH galvanic batteries and electrolysis tanks, Oxidation ALWAYS happens at the ANODE (AN OX) and Reduction ALWAYS happens at the CATHODE (RED CAT)! In a spontaneous battery (Daniell Zn/Cu cell), Zn is higher in reactivity so Zn becomes the negative Anode (−), shooting electrons across the wire to the positive Cu Cathode (+).",
                    keyFormula = "\\text{Zn}(s) | \\text{Zn}^{2+}(aq) || \\text{Cu}^{2+}(aq) | \\text{Cu}(s)   |   \\text{Salt Bridge completes circuit}",
                    examTip =
                      "Positive E°_cell (> 0 V) proves the cell reaction runs spontaneously!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding Oxidation State of Chromium in K₂Cr₂O₇",
                    examSource = "JAMB UTME 2024 • Chem Q25",
                    problemStatement = "Calculate the oxidation number of Chromium in Potassium Dichromate(VI), K₂Cr₂O₇.",
                    steps =
                      listOf(
                        "Step 1 — Neutral compound sum = 0. Set K = +1, O = −2, Cr = x.",
                        "Step 2 — 2(+1) + 2x + 7(−2) = 0 ⇒ 2 + 2x − 14 = 0.",
                        "Step 3 — 2x = +12 ⇒ x = +6.",
                      ),
                    finalAnswer = "+6",
                    examinerTrap = "Forgetting to divide by 2 for the two Chromium atoms in Cr₂O₇²⁻!",
                  ),
                  WorkedExample(
                    title = "Computing Standard Cell EMF",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q27",
                    problemStatement = "Given Zn²⁺/Zn E° = −0.76 V and Cu²⁺/Cu E° = +0.34 V, find E°_cell.",
                    steps =
                      listOf(
                        "Step 1 — Higher E° (+0.34 V) is reduced at Cathode (Cu); lower E° (−0.76 V) oxidizes at Anode (Zn).",
                        "Step 2 — E°_cell = E°_cathode − E°_anode = +0.34 − (−0.76) = +1.10 V.",
                      ),
                    finalAnswer = "+1.10 V",
                    examinerTrap = "Multiplying E° values by stoichiometric coefficients—NEVER multiply E° volts!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Breathalyzer Dichromate Test & Zinc Sacrificial Anodes",
                    industryDomain = "Law Enforcement & Marine Engineering",
                    narrative =
                      "Classic breathalyzers turn orange Cr₂O₇²⁻ to green Cr³⁺ when alcohol vapour reduces Chromium, while ships bolt Zinc blocks onto steel hulls so Zinc rusts instead of iron!",
                    examConnection = "Sacrificial protection prevents iron rust using E° differences.",
                  ),
                ),
              labType = PracticalLabType.REDOX_ELECTROLYSIS_LAB,
              labBrief =
                "Inspect half-cell oxidation/reduction reactions, track electron flow AN OX → RED CAT, and check cell EMF voltage live.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_09_1",
                    topic = "Oxidation Number",
                    examType = "JAMB",
                    year = 2024,
                    question = "What is the oxidation state of Chlorine in KClO₃?",
                    options = listOf("+1", "+3", "+5", "+7"),
                    correctIndex = 2,
                    hint = "+1 + x + 3(−2) = 0.",
                    stepByStepSolution = "x − 5 = 0 ⇒ x = +5 (Potassium chlorate(V)).",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_09_2",
                    topic = "Redox Agents",
                    examType = "WAEC",
                    year = 2023,
                    question = "In Fe₂O₃ + 3CO → 2Fe + 3CO₂, which substance is the REDUCING AGENT?",
                    options = listOf("Fe₂O₃", "CO", "Fe", "CO₂"),
                    correctIndex = 1,
                    hint = "Reducing agent steals oxygen (gets oxidized itself).",
                    stepByStepSolution = "CO (+2) oxidizes to CO₂ (+4) while reducing Fe³⁺ to Fe.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_09_3",
                    topic = "Non-Redox Check",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which reaction is NOT a redox reaction?",
                    options = listOf("Zn + CuSO₄ → ZnSO₄ + Cu", "NaOH + HCl → NaCl + H₂O", "2Mg + O₂ → 2MgO", "Cl₂ + 2KI → 2KCl + I₂"),
                    correctIndex = 1,
                    hint = "Neutralization & double precipitation never change oxidation states!",
                    stepByStepSolution = "In NaOH + HCl → NaCl + H₂O, Na stays +1, O −2, H +1, Cl −1.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_09_4",
                    topic = "Electrochemical Cell EMF",
                    examType = "WAEC",
                    year = 2022,
                    question = "Given Ag⁺/Ag (+0.80 V) and Fe²⁺/Fe (−0.44 V), calculate E°_cell.",
                    options = listOf("+0.36 V", "+1.24 V", "−1.24 V", "+1.16 V"),
                    correctIndex = 1,
                    hint = "E°_cathode − E°_anode = 0.80 − (−0.44).",
                    stepByStepSolution = "0.80 + 0.44 = +1.24 V.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_09_5",
                    topic = "Salt Bridge Role",
                    examType = "JAMB",
                    year = 2021,
                    question = "What is the purpose of the KNO₃ salt bridge in an electrochemical cell?",
                    options = listOf("Supply electrons to anode", "Allow ion migration to maintain electrical neutrality", "Increase electrode mass", "Act as catalyst"),
                    correctIndex = 1,
                    hint = "Ions flow inside the salt bridge; electrons only travel through the external wire!",
                    stepByStepSolution = "The salt bridge completes the internal circuit by letting ions migrate between half-cells.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_09_6",
                    topic = "Peroxide Exception",
                    examType = "WAEC",
                    year = 2024,
                    question = "What is the oxidation number of Oxygen in Hydrogen Peroxide (H₂O₂)?",
                    options = listOf("−2", "−1", "+1", "0"),
                    correctIndex = 1,
                    hint = "2(+1) + 2x = 0.",
                    stepByStepSolution = "2x = −2 ⇒ x = −1.",
                  ),
                ),
            ),

            Lesson(
              id = "chem_10_electrolysis",
              moduleId = "chem_mod_3",
              subjectId = "chemistry",
              title = "Electrolysis, Selective Discharge & Faraday's Laws",
              durationMinutes = 21,
              syllabusTag = "JAMB Sec. 12 • WAEC Electrolysis & Quantitative Plating",
              learningOutcomes =
                listOf(
                  "Predict Cathode and Anode products using the 3 Selective Discharge rules (Position, Concentration, Electrode Nature).",
                  "Apply Faraday's First & Second Laws: Q = I t and m = (M I t) / (n F) cleanly.",
                  "Explain copper refining, electroplating, and Downs/Hall-Héroult industrial extraction.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Who Wins at the Electrodes? (3 Simple Rules)",
                    explanation =
                      "Drop salt into water and FOUR ions swim around (Na⁺, H⁺, Cl⁻, OH⁻). Who wins discharge? Rule 1 (Position): Easier-to-discharge ions WIN (Cu²⁺ beats H⁺; H⁺ beats Na⁺/K⁺! On the anion side, OH⁻ usually beats SO₄²⁻ & NO₃⁻). Rule 2 (High Concentration): Concentrating Brine floods the anode with Cl⁻ so Chlorine gas wins instead of OH⁻! Rule 3 (Active Electrode): Copper anode dissolves into Cu²⁺ instead of releasing O₂!",
                    keyFormula = "\\text{Cations discharged easily: } \\text{Ag}^+ > \\text{Cu}^{2+} > \\text{H}^+ > \\text{Zn}^{2+} > \\text{Na}^+ > \\text{K}^+",
                    examTip =
                      "During electrolysis of Dilute H₂SO₄ (acidified water): 2 volumes of H₂ form at Cathode while 1 volume of O₂ forms at Anode!",
                  ),
                  ConceptBlock(
                    heading = "2. Faraday's Laws: Counting Electrons by Current & Time",
                    explanation =
                      "1 Faraday (F = 96,500 C) equals the electric charge of 1 MOLE of electrons! To plate 1 mole of Ag⁺ takes 1 F; 1 mole of Cu²⁺ takes 2 F (two hands needed!); 1 mole of Al³⁺ takes 3 F! If the same current flows through two baths in series (Second Law), moles deposited scale inversely with ion charge n!",
                    keyFormula = "Q = I t\\; (t\\text{ in s!})   |   m = \\frac{M I t}{n F},\\quad F = 96{,}500\\text{ C/mol}",
                    examTip =
                      "ALWAYS convert minutes or hours into SECONDS (× 60 or × 3600) before multiplying I × t!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Mass of Copper Plated by Faraday's First Law",
                    examSource = "JAMB UTME 2024 • Chem Q27",
                    problemStatement =
                      "Calculate the mass of Copper deposited when a current of 2.0 A passes through CuSO₄(aq) for 965 s. (Cu = 64, F = 96,500 C/mol)",
                    steps =
                      listOf(
                        "Step 1 — Charge Q = I × t = 2.0 × 965 = 1,930 C.",
                        "Step 2 — Cu²⁺ + 2e⁻ → Cu, so n = 2.",
                        "Step 3 — m = (64 × 1930) / (2 × 96500) = (64 / 2) × (1/50) = 32 / 50 = 0.64 g.",
                      ),
                    finalAnswer = "0.64 g",
                    examinerTrap = "Forgetting n = 2 for Cu²⁺ doubles the answer to 1.28 g!",
                  ),
                  WorkedExample(
                    title = "Series Baths (Faraday's Second Law)",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q29",
                    problemStatement =
                      "The same electricity deposits 0.1 mol of Ag from AgNO₃. How many moles of Al are deposited from molten Al₂O₃ in series?",
                    steps =
                      listOf(
                        "Step 1 — Ag⁺ + e⁻ → Ag uses 0.1 F of charge.",
                        "Step 2 — Al³⁺ + 3e⁻ → Al requires 3 F per mole.",
                        "Step 3 — Moles Al = 0.1 / 3 = 0.033 mol.",
                      ),
                    finalAnswer = "0.033 mol (1/30 mol)",
                    examinerTrap = "Compare MOLE ratios first (1:3), NOT gram masses directly!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Silver Jewelry Electroplating & 99.99% Pure Copper Wire",
                    industryDomain = "Metallurgy & Electronics",
                    narrative =
                      "Impure blister copper is wired as the ANODE in CuSO₄(aq): pure copper dissolves off the anode and plates cleanly onto pure copper cathodes, while gold/silver impurities fall as valuable anode sludge.",
                    examConnection = "Electroplating puts the object to coat at the CATHODE!",
                  ),
                ),
              labType = PracticalLabType.REDOX_ELECTROLYSIS_LAB,
              labBrief =
                "Adjust Current I (A), Time t (min), and ion valence (Ag⁺ / Cu²⁺ / Al³⁺) to watch cathode mass grow via Faraday's m = MIt/(nF)!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_10_1",
                    topic = "Faraday First Law",
                    examType = "JAMB",
                    year = 2024,
                    question = "How many Faradays are needed to deposit 54 g of Aluminium from AlCl₃? (Al = 27)",
                    options = listOf("2 F", "3 F", "6 F", "1 F"),
                    correctIndex = 2,
                    hint = "54 g / 27 = 2 moles Al; each Al³⁺ needs 3e⁻.",
                    stepByStepSolution = "2 mol Al × 3 = 6 Faradays.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_10_2",
                    topic = "Active Copper Anode",
                    examType = "WAEC",
                    year = 2023,
                    question = "During electrolysis of CuSO₄(aq) using COPPER electrodes, what happens at the anode?",
                    options = listOf("Oxygen gas evolves", "Hydrogen gas evolves", "Copper anode dissolves as Cu²⁺", "Blue colour fades quickly"),
                    correctIndex = 2,
                    hint = "Copper is an active electrode that oxidizes preferentially.",
                    stepByStepSolution = "Cu(s) → Cu²⁺(aq) + 2e⁻; blue Cu²⁺ concentration stays constant!",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_10_3",
                    topic = "Concentrated Brine",
                    examType = "JAMB",
                    year = 2025,
                    question = "Electrolysis of concentrated NaCl(aq) using carbon electrodes yields which gas at the ANODE?",
                    options = listOf("Oxygen", "Hydrogen", "Chlorine", "Sodium vapour"),
                    correctIndex = 2,
                    hint = "High [Cl⁻] concentration overrides OH⁻.",
                    stepByStepSolution = "2Cl⁻ → Cl₂(g) + 2e⁻ at anode (H₂ at cathode; NaOH left in bath).",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_10_4",
                    topic = "Mass Calculation",
                    examType = "WAEC",
                    year = 2022,
                    question = "Find mass of Silver (Ag = 108) deposited by 0.5 F of electricity.",
                    options = listOf("27 g", "54 g", "108 g", "216 g"),
                    correctIndex = 1,
                    hint = "Ag⁺ + e⁻ → Ag (1 F deposits 108 g).",
                    stepByStepSolution = "0.5 × 108 = 54 g.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_10_5",
                    topic = "Electroplating Rule",
                    examType = "JAMB",
                    year = 2021,
                    question = "To electroplate an iron spoon with Chromium, the iron spoon must serve as the:",
                    options = listOf("Anode", "Cathode", "Salt bridge", "Electrolyte"),
                    correctIndex = 1,
                    hint = "Metal cations Cr³⁺ migrate to and plate at the negative Cathode.",
                    stepByStepSolution = "Object to be plated is always wired as Cathode; plating metal as Anode.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_10_6",
                    topic = "Acidified Water",
                    examType = "WAEC",
                    year = 2024,
                    question = "If 40 cm³ of Hydrogen is collected at the cathode during electrolysis of dilute H₂SO₄, what volume of Oxygen collects at the anode?",
                    options = listOf("80 cm³", "40 cm³", "20 cm³", "10 cm³"),
                    correctIndex = 2,
                    hint = "H₂ : O₂ volume ratio in 2H₂O → 2H₂ + O₂ is 2 : 1.",
                    stepByStepSolution = "Half of 40 cm³ = 20 cm³ O₂.",
                  ),
                ),
            ),
          ),
      ),

      CourseModule(
        id = "chem_mod_4",
        subjectId = "chemistry",
        indexLabel = "Module 04",
        title = "Non-Metals, Water, Metals & Blast Furnace Extraction",
        summary = "Master Water hardness & treatment, Ammonia/Haber, Sulphur/Contact Process, Halogens, Metals & Iron/Aluminium extraction.",
        lessons =
          listOf(
            Lesson(
              id = "chem_11_nonmetals_water",
              moduleId = "chem_mod_4",
              subjectId = "chemistry",
              title = "Water, Air, Carbon, Nitrogen, Sulphur & Halogens",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 13 • WAEC Non-Metals & Water Hardness",
              learningOutcomes =
                listOf(
                  "Compare Temporary Water Hardness Ca(HCO₃)₂ vs Permanent CaSO₄/MgSO₄ and removal tricks.",
                  "Recall lab preps, drying agents & fountain/bleaching properties of NH₃, HCl, SO₂, and Cl₂.",
                  "Explain Nitrogen/Carbon allotropes, Haber (NH₃), Ostwald (HNO₃), and Contact (H₂SO₄) processes.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Hard Water & Drying Agents (Never Mix Acid & Base Dryers!)",
                    explanation =
                      "Hard water wastes soap into cloudy scum because Ca²⁺ and Mg²⁺ ions hijack soap! Temporary hardness comes from Ca(HCO₃)₂—simply BOIL it to break it into chalk (kettle fur!) or add slaked lime. Permanent hardness (CaSO₄/MgSO₄) survives boiling—beat it using Washing Soda (Na₂CO₃) or Ion-Exchange resin! Watch gas dryers: Ammonia NH₃ is ALKALINE, so NEVER dry it with acidic Conc. H₂SO₄ or CaCl₂ (it reacts!)—use Quicklime (CaO)!",
                    keyFormula = "\\text{Temp: } \\text{Ca(HCO}_3)_2 \\xrightarrow{\\Delta} \\text{CaCO}_3\\!\\downarrow + \\text{H}_2\\text{O} + \\text{CO}_2",
                    examTip =
                      "Conc. H₂SO₄ dehydrates H₂S too—dry H₂S using fused CaCl₂! Only NH₃ fails CaCl₂ (forms CaCl₂·8NH₃)!",
                  ),
                  ConceptBlock(
                    heading = "2. Signature Halogens, NH₃ & SO₂ Bleaching Duel",
                    explanation =
                      "Chlorine (Cl₂) bleaches permanently by OXIDATION (kills dye using O from HOCl). Sulphur Dioxide (SO₂) bleaches ONLY temporarily by REDUCTION—air oxygen slowly turns straw hats & newsprint yellow again! Both NH₃ and HCl are ultra-soluble gases demonstrated by the classic Fountain Experiment.",
                    keyFormula = "\\text{Haber: } \\text{N}_2+3\\text{H}_2 \\xrightarrow{450^\\circ\\text{C, Fe}} 2\\text{NH}_3   |   \\text{Contact: } 2\\text{SO}_2+\\text{O}_2 \\xrightarrow{\\text{V}_2\\text{O}_5} 2\\text{SO}_3",
                    examTip =
                      "In Contact Process, SO₃ is dissolved in 98% H₂SO₄ (Oleum H₂S₂O₇) FIRST—never straight water, or it creates dangerous acid mist!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Halogen Displacement Down Group 17",
                    examSource = "JAMB UTME 2024 • Chem Q29",
                    problemStatement = "Predict what happens when Chlorine gas bubbles through aqueous Potassium Bromide (KBr).",
                    steps =
                      listOf(
                        "Step 1 — Reactivity down Group 17: F₂ > Cl₂ > Br₂ > I₂.",
                        "Step 2 — Cl₂ is stronger than Br₂, so Cl₂ displaces Br⁻: Cl₂ + 2KBr → 2KCl + Br₂.",
                        "Step 3 — Solution turns reddish-brown due to free Bromine.",
                      ),
                    finalAnswer = "Reddish-brown Br₂ forms (Cl₂ displaces Br⁻)",
                    examinerTrap = "Down Group 17 (non-metals) reactivity DECREASES—opposite of Group 1 metals!",
                  ),
                  WorkedExample(
                    title = "Removing Both Temporary & Permanent Hardness",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q31",
                    problemStatement = "Which chemical softens BOTH temporary and permanent hard water in one step?",
                    steps =
                      listOf(
                        "Step 1 — Washing Soda (Na₂CO₃) precipitates dissolved Ca²⁺/Mg²⁺ as insoluble CaCO₃ / MgCO₃.",
                        "Step 2 — Boiling ONLY removes temporary Ca(HCO₃)₂, NOT permanent CaSO₄.",
                      ),
                    finalAnswer = "Washing Soda (Na₂CO₃) or Ion-exchange resin",
                    examinerTrap = "Choosing Slaked Lime Ca(OH)₂—that only treats temporary hardness!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Municipal Water Treatment Steps (Coagulation to Chlorination)",
                    industryDomain = "Public Health Engineering",
                    narrative =
                      "City water passes through Alum coagulation (clumping mud), Sand filtration, Chlorination (killing microbes), and fluoridation before reaching kitchen taps.",
                    examConnection = "Classic WAEC sequential 4-step water purification diagram question.",
                  ),
                ),
              labType = PracticalLabType.INORGANIC_GASES_METALS_LAB,
              labBrief =
                "Select NH₃, SO₂, Cl₂, CO₂, or H₂S to test litmus colour, bleaching mode, drying agent compatibility, and halogen displacement!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_11_1",
                    topic = "Drying Ammonia",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which drying agent is suitable for pure Ammonia gas (NH₃)?",
                    options = listOf("Concentrated H₂SO₄", "Fused CaCl₂", "Quicklime (CaO)", "P₄O₁₀"),
                    correctIndex = 2,
                    hint = "Basic gas needs a basic oxide dryer.",
                    stepByStepSolution = "CaO dries alkaline NH₃ without reacting (H₂SO₄ & P₄O₁₀ are acidic; CaCl₂ forms a complex).",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_11_2",
                    topic = "Bleaching Contrast",
                    examType = "WAEC",
                    year = 2023,
                    question = "Why does bleaching by Chlorine last permanently whereas bleaching by SO₂ fades back?",
                    options = listOf("Cl₂ acts by oxidation; SO₂ acts by reduction", "Cl₂ is stronger acid", "SO₂ evaporates", "Cl₂ contains oxygen"),
                    correctIndex = 0,
                    hint = "Atmospheric oxygen slowly re-oxidizes SO₂-reduced dyes.",
                    stepByStepSolution = "Oxidation permanently breaks chromophore bonds; SO₂ reduction is reversed by air O₂.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_11_3",
                    topic = "Water Hardness",
                    examType = "JAMB",
                    year = 2025,
                    question = "Temporary hardness of water is caused by the presence of dissolved:",
                    options = listOf("CaSO₄", "MgCl₂", "Ca(HCO₃)₂", "Na₂SO₄"),
                    correctIndex = 2,
                    hint = "Only hydrogencarbonates decompose on boiling.",
                    stepByStepSolution = "Ca(HCO₃)₂ precipitates into insoluble CaCO₃ when boiled.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_11_4",
                    topic = "Allotropes of Sulphur",
                    examType = "WAEC",
                    year = 2022,
                    question = "Which allotrope of Sulphur is stable below 96°C and consists of S₈ crown rings?",
                    options = listOf("Monoclinic sulphur", "Rhombic sulphur", "Plastic sulphur", "Colloidal sulphur"),
                    correctIndex = 1,
                    hint = "96°C is the transition temperature between Rhombic (<96°C) and Monoclinic (>96°C).",
                    stepByStepSolution = "Rhombic (α) sulphur is stable below 96°C.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_11_5",
                    topic = "Acid Rain",
                    examType = "JAMB",
                    year = 2021,
                    question = "Which two oxides are the primary causes of industrial acid rain?",
                    options = listOf("CO and N₂O", "SO₂ and NO₂", "CO₂ and CH₄", "CO and SO₃"),
                    correctIndex = 1,
                    hint = "Forms H₂SO₃/H₂SO₄ and HNO₃ in clouds.",
                    stepByStepSolution = "SO₂ and NO₂ dissolve in rainwater to form strong mineral acids.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_11_6",
                    topic = "Contact Process Catalyst",
                    examType = "WAEC",
                    year = 2024,
                    question = "Name the catalyst used to oxidize SO₂ to SO₃ in the Contact Process.",
                    options = listOf("Finely divided Iron", "Vanadium(V) oxide (V₂O₅)", "Nickel", "Manganese(IV) oxide"),
                    correctIndex = 1,
                    hint = "Iron is for Haber; Nickel for hydrogenation.",
                    stepByStepSolution = "V₂O₅ catalyzes 2SO₂ + O₂ ⇌ 2SO₃ at ~450°C.",
                  ),
                ),
            ),

            Lesson(
              id = "chem_12_metals_extraction",
              moduleId = "chem_mod_4",
              subjectId = "chemistry",
              title = "Metals Reactivity Series, Blast Furnace & Alloys",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 14 • WAEC Metals, Ores & Extraction",
              learningOutcomes =
                listOf(
                  "Match extraction methods to Activity Series (Electrolysis for K–Al vs Carbon/CO reduction for Zn–Cu).",
                  "Trace all reactions in the Iron Blast Furnace (Fe₂O₃ + CO; Limestone CaCO₃ removing SiO₂ slag).",
                  "Recall major ores (Bauxite, Haematite, Cassiterite, Galena) and compositions of Brass, Bronze, Duralumin & Solder.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. The Metal Reactivity Ladder & Extraction Choice",
                    explanation =
                      "Please Stop Calling Me A Careless Zebra Instead Try Learning How Copper Saves Gold: K, Na, Ca, Mg, Al hold onto Oxygen SO fiercely that charcoal CANNOT tear it away—only raw ELECTROLYSIS frees them! Middle metals (Zn, Fe, Sn, Pb) give up Oxygen when roasted with Carbon / CO in a furnace!",
                    keyFormula = "\\text{Al (Bauxite Al}_2\\text{O}_3\\text{ in Cryolite)} \\to \\text{Electrolysis}   |   \\text{Fe (Haematite Fe}_2\\text{O}_3\\text{)} \\xrightarrow{\\text{CO}} \\text{Fe}",
                    examTip =
                      "Why add Cryolite (Na₃AlF₆) to Al₂O₃? It slashes the melting point from 2050°C down to ~950°C—saving huge electricity!",
                  ),
                  ConceptBlock(
                    heading = "2. Inside the Blast Furnace & Essential Alloys",
                    explanation =
                      "Three solids pour into the top of the Blast Furnace: Haematite (Fe₂O₃), Coke (C), and Limestone (CaCO₃). Coke burns to CO₂ then reduces to CO—the true reducing agent that strips oxygen from Fe₂O₃! Meanwhile Limestone breaks into CaO and gobbles sandy SiO₂ impurity into floating liquid Slag (CaSiO₃)!",
                    keyFormula = "\\text{Fe}_2\\text{O}_3 + 3\\text{CO} \\to 2\\text{Fe} + 3\\text{CO}_2   |   \\text{CaO} + \\text{SiO}_2 \\to \\text{CaSiO}_3\\text{ (Slag)}",
                    examTip =
                      "Memorize top Alloys: Brass = Cu + Zn; Bronze = Cu + Sn; Solder = Pb + Sn; Stainless Steel = Fe + Cr + Ni; Duralumin = Al + Cu + Mg + Mn!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Role of Limestone in Iron Extraction",
                    examSource = "JAMB UTME 2024 • Chem Q32",
                    problemStatement = "What is the main chemical purpose of adding Limestone (CaCO₃) to the Blast Furnace?",
                    steps =
                      listOf(
                        "Step 1 — Heat decomposes CaCO₃ → CaO (basic oxide) + CO₂.",
                        "Step 2 — CaO neutralizes acidic silica sand impurity: CaO + SiO₂ → CaSiO₃ (molten slag).",
                      ),
                    finalAnswer = "Removes acidic SiO₂ impurity as molten slag",
                    examinerTrap = "Don't say Limestone reduces Fe₂O₃—Coke/CO reduces iron oxide; Limestone cleans silica!",
                  ),
                  WorkedExample(
                    title = "Thermal Decomposition of Metal Nitrates",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q33",
                    problemStatement = "Compare heat decomposition products of NaNO₃ vs Cu(NO₃)₂ vs AgNO₃.",
                    steps =
                      listOf(
                        "Step 1 — K/Na nitrates give Nitrite + O₂ ONLY: 2NaNO₃ → 2NaNO₂ + O₂ (NO brown NO₂ gas!).",
                        "Step 2 — Ca to Cu nitrates give Oxide + brown NO₂ + O₂: 2Cu(NO₃)₂ → 2CuO + 4NO₂ + O₂.",
                        "Step 3 — Ag/Hg nitrates give pure Metal + NO₂ + O₂: 2AgNO₃ → 2Ag + 2NO₂ + O₂.",
                      ),
                    finalAnswer = "Na→Nitrite  |  Cu→Oxide + NO₂  |  Ag→Metal + NO₂",
                    examinerTrap = "K and Na nitrates NEVER produce brown NO₂ fumes on heating!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Aircraft Duralumin & Copper-Nickel Coins",
                    industryDomain = "Aerospace Metallurgy",
                    narrative =
                      "Pure Aluminium is too soft to build wings, so adding 4% Cu + Mg creates Duralumin—lightweight yet tougher than structural steel.",
                    examConnection = "Explains how foreign atoms disrupt crystal slip planes to harden alloys.",
                  ),
                ),
              labType = PracticalLabType.INORGANIC_GASES_METALS_LAB,
              labBrief =
                "Inspect the Reactivity Ladder, test Carbon reduction vs Electrolysis thresholds, and trace Blast Furnace & Thermal Nitrate reactions!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_chem_12_1",
                    topic = "Cryolite Function",
                    examType = "JAMB",
                    year = 2024,
                    question = "Cryolite (Na₃AlF₆) is mixed with molten Al₂O₃ during Aluminium extraction in order to:",
                    options = listOf("Oxidize bauxite", "Lower the melting point of Al₂O₃", "Act as an anode", "Remove iron rust"),
                    correctIndex = 1,
                    hint = "Pure Al₂O₃ melts above 2000°C.",
                    stepByStepSolution = "Cryolite lowers the operating temperature to ~950°C and improves conductivity.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_12_2",
                    topic = "Blast Furnace Reducer",
                    examType = "WAEC",
                    year = 2023,
                    question = "The major reducing agent converting Fe₂O₃ to molten iron inside the Blast Furnace is:",
                    options = listOf("Limestone", "Carbon monoxide (CO)", "Slag", "Carbon dioxide"),
                    correctIndex = 1,
                    hint = "Hot CO₂ reacts with coke to form CO.",
                    stepByStepSolution = "Fe₂O₃ + 3CO → 2Fe + 3CO₂.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_12_3",
                    topic = "Brass Composition",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which two metals make up the alloy Brass?",
                    options = listOf("Copper and Tin", "Copper and Zinc", "Lead and Tin", "Iron and Chromium"),
                    correctIndex = 1,
                    hint = "BraZZ has Zinc (Cu + Zn); BronZe has Tin (Cu + Sn).",
                    stepByStepSolution = "Brass is an alloy of Copper and Zinc.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_12_4",
                    topic = "Thermal Nitrate Product",
                    examType = "WAEC",
                    year = 2022,
                    question = "Which trioxonitrate(V) leaves a shiny metallic residue when strongly heated?",
                    options = listOf("Cu(NO₃)₂", "Pb(NO₃)₂", "AgNO₃", "KNO₃"),
                    correctIndex = 2,
                    hint = "Unreactive Ag/Hg/Au oxides decompose immediately to free metal.",
                    stepByStepSolution = "2AgNO₃ → 2Ag(s) + 2NO₂ + O₂.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_12_5",
                    topic = "Amalgam Definition",
                    examType = "JAMB",
                    year = 2021,
                    question = "An amalgam is any alloy that specifically contains:",
                    options = listOf("Gold", "Mercury", "Silver", "Aluminium"),
                    correctIndex = 1,
                    hint = "Dental fillings use silver-tin-mercury amalgam.",
                    stepByStepSolution = "Mercury alloys with metals (except iron/platinum) are called amalgams.",
                  ),
                  PracticeQuestion(
                    id = "ex_chem_12_6",
                    topic = "Iron Corrosion",
                    examType = "WAEC",
                    year = 2024,
                    question = "Which pair of agents are BOTH strictly required for iron to rust?",
                    options = listOf("Nitrogen and water", "Oxygen and moisture (water)", "Carbon dioxide and heat", "Dry oxygen only"),
                    correctIndex = 1,
                    hint = "Dry air won't rust a nail; boiled oil-sealed water won't either!",
                    stepByStepSolution = "Rusting forms hydrated iron(III) oxide needing both O₂ and H₂O.",
                  ),
                ),
            ),
          ),
      ),
    )
}
