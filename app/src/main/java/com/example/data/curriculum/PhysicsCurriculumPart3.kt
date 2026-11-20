package com.example.data.curriculum

object PhysicsCurriculumPart3 {

  val module5: CourseModule =
    CourseModule(
      id = "phys_mod_5",
      subjectId = "physics",
      indexLabel = "Module 05",
      title = "Electromagnetism, AC Circuits, Quantum & Nuclear Physics",
      summary = "Master magnetic forces, Faraday induction, transformers, RLC AC resonance, semiconductors, photoelectric effect & radioactivity.",
      lessons =
        listOf(
          Lesson(
            id = "phys_14_em_ac",
            moduleId = "phys_mod_5",
            subjectId = "physics",
            title = "Magnetism, Induction, Transformers & A.C. Circuits",
            durationMinutes = 22,
            syllabusTag = "JAMB Sec. 9.1–9.5 • WAEC Electromagnetism & AC",
            learningOutcomes =
              listOf(
                "Compute magnetic force F = BIL sin θ, transformer turns ratio, and grid I²R power loss.",
                "Calculate AC Impedance Z = √(R² + (X_L − X_C)²) and RLC Resonance frequency.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "Magnets + Moving Wires = Motors & Generators",
                  explanation =
                    "Run electric current through a wire inside a magnet and the wire KICKS sideways (Fleming's Left-Hand Motor Rule, F = BIL). Push a magnet into a coil of wire and electricity SPRINGS out (Faraday/Lenz Law—the coil always pushes back against the change!). Transformers use changing AC magnetic flux to boost voltage UP or DOWN cleanly.",
                  keyFormula = "F = B I L\\sin\\theta   |   \\frac{V_s}{V_p} = \\frac{N_s}{N_p}   |   \\text{Eff } \\eta = \\frac{I_s V_s}{I_p V_p}\\times 100\\%",
                  examTip =
                    "Transformers NEVER work on steady DC batteries—because DC creates steady magnetic flux with zero change!",
                ),
                ConceptBlock(
                  heading = "Alternating Current (A.C.): Reactance & Radio Tuning Resonance",
                  explanation =
                    "In AC, coils (Inductors L) block fast high frequencies (X_L = 2πfL) while Capacitors C pass high frequencies easily (X_C = 1/(2πfC)). When X_L equals X_C perfectly, they cancel out—Current shoots to MAXIMUM at Resonance Frequency f₀ (exactly how your FM radio locks onto one station!).",
                  keyFormula = "Z = \\sqrt{R^2 + (X_L - X_C)^2}   |   V_{\\text{rms}} = \\frac{V_0}{\\sqrt{2}}   |   f_0 = \\frac{1}{2\\pi\\sqrt{L C}}",
                  examTip =
                    "Power transmission uses ultra-high Voltage (330 kV) so Current I drops tiny and cable heat waste (P_loss = I²R) plunges!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Non-Ideal Step-Down Transformer Current",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement =
                    "A 240 V to 12 V step-down transformer at 80% efficiency lights a 12 V, 24 W bulb. Find primary current I_p.",
                  steps =
                    listOf(
                      "Step 1 — P_out = 24 W.",
                      "Step 2 — P_in = P_out / 0.80 = 24 / 0.80 = 30 W.",
                      "Step 3 — I_p = P_in / V_p = 30 / 240 = 0.125 A.",
                    ),
                  finalAnswer = "0.125 A",
                  examinerTrap = "Only set I_p V_p = I_s V_s when efficiency is 100% (ideal)!",
                ),
                WorkedExample(
                  title = "Series RLC Impedance & Current",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement =
                    "A 3 Ω resistor, 10 Ω inductive reactance coil, and 6 Ω capacitor sit in series across 25 V rms AC. Find Impedance Z and Current I.",
                  steps =
                    listOf(
                      "Step 1 — Net reactance = X_L − X_C = 10 − 6 = 4 Ω.",
                      "Step 2 — Z = √(3² + 4²) = √25 = 5.0 Ω.",
                      "Step 3 — I_rms = V_rms / Z = 25 / 5 = 5.0 A.",
                    ),
                  finalAnswer = "Z = 5.0 Ω  |  I = 5.0 A",
                  examinerTrap = "Add R and (X_L − X_C) by Pythagoras, NEVER arithmetically 3 + 4 = 7!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Nigerian 330 kV National Grid Transmission",
                  industryDomain = "Power Transmission",
                  narrative =
                    "Kainji Dam turbines step voltage up to 330,000 V so line current stays low over 500 km to Lagos, slashing I²R heat waste.",
                  examConnection = "Frequently tested in WAEC Paper 2 transformer theory.",
                ),
              ),
            labType = PracticalLabType.EM_TRANSFORMER_AC_LAB,
            labBrief =
              "Adjust Primary/Secondary coil turns ratio and RLC resistance/reactances below to inspect step-up voltages and AC resonance peaks!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_phy_14_1",
                  topic = "AC Circuits",
                  examType = "JAMB",
                  year = 2024,
                  question = "In a series RLC AC circuit at resonance frequency f₀, which condition holds true?",
                  options = listOf("Impedance Z is maximum", "Inductive reactance X_L equals capacitive reactance X_C", "Current I is zero", "Power factor is zero"),
                  correctIndex = 1,
                  hint = "Net reactance drops to zero so Z = R minimum.",
                  stepByStepSolution = "At resonance, X_L = X_C, so Z = R minimum and current reaches peak.",
                ),
                PracticeQuestion(
                  id = "ex_phy_14_2",
                  topic = "Transformers",
                  examType = "WAEC",
                  year = 2023,
                  question = "Why is a transformer's soft-iron core built from thin varnished laminated sheets rather than a solid block?",
                  options = listOf("Make it lighter", "Minimize eddy current heat losses", "Lower magnetic flux", "Increase copper resistance"),
                  correctIndex = 1,
                  hint = "Varnish blocks circulating eddy current loops.",
                  stepByStepSolution = "Laminating the core breaks large circulating eddy current paths, reducing I²R core heating.",
                  waecMarkingScheme = "A1: Reduce eddy current energy loss.",
                ),
                PracticeQuestion(
                  id = "ex_phy_14_3",
                  topic = "RMS Voltage",
                  examType = "JAMB",
                  year = 2025,
                  question = "An AC supply has peak voltage V₀ = 200√2 V. What is its Root-Mean-Square (V_rms) value?",
                  options = listOf("100 V", "141.4 V", "200 V", "400 V"),
                  correctIndex = 2,
                  hint = "V_rms = V₀ / √2.",
                  stepByStepSolution = "(200√2) / √2 = 200 V.",
                ),
                PracticeQuestion(
                  id = "ex_phy_14_4",
                  topic = "Magnetic Force",
                  examType = "WAEC",
                  year = 2022,
                  question = "A 0.5 m straight wire carries 4 A perpendicularly across a 0.6 T magnetic field. Find the magnetic force.",
                  options = listOf("0.8 N", "1.2 N", "2.4 N", "4.8 N"),
                  correctIndex = 1,
                  hint = "F = B I L sin(90°).",
                  stepByStepSolution = "F = 0.6 × 4 × 0.5 × 1 = 1.2 N.",
                  waecMarkingScheme = "M1: 0.6×4×0.5; A1: 1.2 N.",
                ),
                PracticeQuestion(
                  id = "ex_phy_14_5",
                  topic = "Lenz Law",
                  examType = "JAMB",
                  year = 2021,
                  question = "Lenz's Law of electromagnetic induction is a direct consequence of the conservation of:",
                  options = listOf("Mass", "Charge", "Energy", "Magnetic poles"),
                  correctIndex = 2,
                  hint = "Mechanical work pushes against opposing induced force.",
                  stepByStepSolution = "Induced current always opposes the motion creating it, preserving Energy Conservation.",
                ),
                PracticeQuestion(
                  id = "ex_phy_14_6",
                  topic = "Grid Power Loss",
                  examType = "NECO",
                  year = 2020,
                  question = "Why is electrical power transmitted over long distances at very high voltage?",
                  options = listOf("High voltage travels faster", "Reduces current I and thus slashes I²R cable heat loss", "Copper melts at low voltage", "Eliminates transformers"),
                  correctIndex = 1,
                  hint = "For same Power P = IV, higher V gives smaller I.",
                  stepByStepSolution = "Stepping up voltage cuts current I, reducing resistive cable heating P = I²R drastically.",
                ),
              ),
          ),
          Lesson(
            id = "phys_15_quantum_electronics",
            moduleId = "phys_mod_5",
            subjectId = "physics",
            title = "Photoelectric Effect, Bohr Atom, X-Rays & Semiconductors",
            durationMinutes = 20,
            syllabusTag = "JAMB Sec. 10.1–10.4 • WAEC Modern & Electronics",
            learningOutcomes =
              listOf(
                "Apply Einstein's photoelectric equation hf = Φ + KE_max and Bohr photon jumps.",
                "Distinguish p-type vs n-type semiconductors, forward/reverse diode rectification, and X-rays.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "Light Bullets (Photons) Knocking Out Metal Electrons",
                  explanation =
                    "Think of light as tiny energy packets called PHOTONS (E = hf). Shining dim ultraviolet photons onto zinc pops electrons right out—even brighter red light can't budge a single one if its photon energy hf is smaller than the metal's Work Function Φ (unlock fee)! Once past f₀, leftover energy becomes electron speed KE_max.",
                  keyFormula = "E = hf = \\frac{hc}{\\lambda}   |   KE_{\\max} = hf - \\Phi = e V_s   |   \\Delta E = E_2 - E_1 = hf",
                  examTip =
                    "Changing light INTENSITY (brightness) changes NUMBER of electrons ejected (current)—only FREQUENCY f raises electron kinetic energy!",
                ),
                ConceptBlock(
                  heading = "Semiconductors: Doping & Diode One-Way Valves",
                  explanation =
                    "Pure silicon conducts poorly. Sprinkle tiny Group 5 Phosphorus atoms (5 valence electrons) -> extra free NEGATIVE electrons -> N-TYPE. Sprinkle Group 3 Boron (3 valence electrons) -> missing positive HOLES -> P-TYPE. Join P to N to get a DIODE—it acts like a one-way valve turning AC wall power into phone-charger DC (Rectification)!",
                  keyFormula = "\\text{N-type: Donor Group V (electrons)}   |   \\text{P-type: Acceptor Group III (holes)}",
                  examTip =
                    "Remember N for Negative (Group 5 pentavalent donor, extra electrons); P for Positive (Group 3 trivalent acceptor, extra holes)!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Photoelectric Maximum Kinetic Energy & Stopping Voltage",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement =
                    "Photons of energy 5.0 eV strike cesium of work function 2.1 eV. Find max kinetic energy KE_max and stopping potential V_s.",
                  steps =
                    listOf(
                      "Step 1 — KE_max = E − Φ = 5.0 − 2.1 = 2.9 eV.",
                      "Step 2 — Stopping potential V_s = 2.9 V directly (since e·V_s = 2.9 eV).",
                    ),
                  finalAnswer = "KE_max = 2.9 eV  |  V_s = 2.9 V",
                  examinerTrap = "Don't multiply by 1.6×10⁻¹⁹ when the answer is requested in eV or Volts directly!",
                ),
                WorkedExample(
                  title = "Bohr Hydrogen Emission Transition",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement =
                    "An electron drops from level −3.4 eV to −13.6 eV. Find emitted photon energy.",
                  steps =
                    listOf(
                      "Step 1 — ΔE = E_high − E_low = (−3.4) − (−13.6) = +10.2 eV.",
                    ),
                  finalAnswer = "10.2 eV",
                  examinerTrap = "Mind the double minus signs (−13.6) when subtracting negative energy levels!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Solar Rooftop Panels & Phone Charger Rectifiers",
                  industryDomain = "Clean Energy & Consumer Electronics",
                  narrative =
                    "Silicon solar cells convert sunlight photons straight into DC voltage via p-n junctions, while bridge diodes rectify AC mains inside every USB charger.",
                  examConnection = "Tests semiconductor doping and rectification in JAMB.",
                ),
              ),
            labType = PracticalLabType.QUANTUM_NUCLEAR_LAB,
            labBrief =
              "Slide incoming photon frequency f past Metal Threshold f₀ in the Quantum Lab below to watch electrons eject and gauge Stopping Potential!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_phy_15_1",
                  topic = "Photoelectric Effect",
                  examType = "JAMB",
                  year = 2024,
                  question = "In the photoelectric effect, increasing the intensity (brightness) of valid UV light increases:",
                  options = listOf("Threshold frequency f₀", "Maximum kinetic energy of electrons", "Photoelectric current (electrons per second)", "Metal work function Φ"),
                  correctIndex = 2,
                  hint = "More photons per second kick out more electrons, each with the same energy.",
                  stepByStepSolution = "Higher intensity supplies more photons/sec -> larger photoelectric current without increasing individual electron KE.",
                ),
                PracticeQuestion(
                  id = "ex_phy_15_2",
                  topic = "Semiconductors",
                  examType = "WAEC",
                  year = 2023,
                  question = "Doping pure silicon with a small amount of Group III Boron produces:",
                  options = listOf("N-type semiconductor with majority electrons", "P-type semiconductor with majority holes", "A superconductor", "An insulator"),
                  correctIndex = 1,
                  hint = "Group III lacks 1 electron per bond -> positive hole.",
                  stepByStepSolution = "Trivalent Group III impurities create electron-accepting positive holes -> P-type semiconductor.",
                  waecMarkingScheme = "A1: P-type semiconductor (majority holes).",
                ),
                PracticeQuestion(
                  id = "ex_phy_15_3",
                  topic = "Rectification",
                  examType = "JAMB",
                  year = 2025,
                  question = "The primary function of a p-n junction diode in an AC-to-DC power supply adapter is:",
                  options = listOf("Amplification", "Rectification (allowing one-way current)", "Voltage step-up", "Modulation"),
                  correctIndex = 1,
                  hint = "Conducts forward bias, blocks reverse bias.",
                  stepByStepSolution = "Diodes rectify alternating current into unidirectional DC.",
                ),
                PracticeQuestion(
                  id = "ex_phy_15_4",
                  topic = "X-Rays",
                  examType = "WAEC",
                  year = 2022,
                  question = "Hardness (penetrating power) of X-rays from an X-ray tube increases when you raise the:",
                  options = listOf("Filament heating current", "Anode accelerating high voltage", "Cooling water flow", "Target melting point"),
                  correctIndex = 1,
                  hint = "Faster electrons hit target harder -> higher frequency photons.",
                  stepByStepSolution = "Raising tube voltage boosts electron energy eV and emitted X-ray frequency (hardness).",
                  waecMarkingScheme = "A1: Accelerating potential (high voltage).",
                ),
                PracticeQuestion(
                  id = "ex_phy_15_5",
                  topic = "Thermionic Emission",
                  examType = "JAMB",
                  year = 2021,
                  question = "Heating a metal tungsten filament in vacuum to boil off free electrons is called:",
                  options = listOf("Photoelectric effect", "Thermionic emission", "Radioactive decay", "Ionization"),
                  correctIndex = 1,
                  hint = "Therm = heat + ions/electrons.",
                  stepByStepSolution = "Thermionic emission uses thermal energy to eject electrons inside CRTs and X-ray tubes.",
                ),
                PracticeQuestion(
                  id = "ex_phy_15_6",
                  topic = "Photon Energy",
                  examType = "NECO",
                  year = 2020,
                  question = "Find the energy of a photon of frequency 5.0 × 10¹⁴ Hz (h = 6.6 × 10⁻³⁴ J·s).",
                  options = listOf("3.3 × 10⁻¹⁹ J", "1.3 × 10⁻⁴⁸ J", "3.3 × 10⁻²⁰ J", "6.6 × 10⁻¹⁹ J"),
                  correctIndex = 0,
                  hint = "E = h × f.",
                  stepByStepSolution = "E = 6.6×10⁻³⁴ × 5.0×10¹⁴ = 33 × 10⁻²⁰ = 3.3 × 10⁻¹⁹ J.",
                ),
              ),
          ),
          Lesson(
            id = "phys_16_nuclear",
            moduleId = "phys_mod_5",
            subjectId = "physics",
            title = "Nuclear Physics: Radioactivity, Half-Life & Binding Energy",
            durationMinutes = 18,
            syllabusTag = "JAMB Sec. 10.5–10.7 • WAEC Nuclear Physics",
            learningOutcomes =
              listOf(
                "Compare α, β, and γ radiation properties and balance nuclear decay equations.",
                "Solve radioactive half-life N = N₀(1/2)ⁿ and Einstein mass-defect energy E = Δmc².",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "Three Siblings Escaping Unstable Atomic Nuclei",
                  explanation =
                    "Heavy nuclei like Uranium-238 are overcrowded and eject particles to calm down: ALPHA (α = ⁴₂He nucleus—heavy +2 charge, stopped by paper); BETA (β = ⁰₋₁e fast electron—light −1 charge, stopped by aluminum foil); GAMMA (γ = pure neutral energy ray—Zero mass/charge, needs thick lead!).",
                  keyFormula = "\\alpha: (A-4, Z-2)   |   \\beta^-: (A, Z+1)   |   \\gamma: (A, Z \\text{ unchanged})   |   \\Delta E = \\Delta m\\,c^2",
                  examTip =
                    "In a magnetic field, α bends one way slightly (heavy), β bends sharply the OPPOSITE way (light & negative), and γ flies straight!",
                ),
                ConceptBlock(
                  heading = "Half-Life: The Halving Clock That Never Speeds Up",
                  explanation =
                    "Imagine 100 coins tossed once every hour: roughly half land tails and leave each round (100 → 50 → 25 → 12.5). Half-life T_½ is the time for HALF of any sample to decay. Boiling or freezing an isotope NEVER changes its half-life!",
                  keyFormula = "N = N_0 \\left(\\frac{1}{2}\\right)^n   \\quad \\text{where } n = \\frac{\\text{Total Time } t}{T_{1/2}}",
                  examTip =
                    "Nuclear FISSION splits heavy Uranium nuclei (power plants); Nuclear FUSION joins light Hydrogen into Helium (powers the Sun!).",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Counting Half-Lives to Find Remaining Mass",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement =
                    "Cobalt-60 has a half-life of 5 years. Starting with 80 g, how much remains undecayed after 20 years?",
                  steps =
                    listOf(
                      "Step 1 — Number of half-lives n = 20 / 5 = 4 half-lives.",
                      "Step 2 — Halve 4 times: 80 → 40 → 20 → 10 → 5.0 g.",
                    ),
                  finalAnswer = "5.0 g",
                  examinerTrap = "Read carefully whether the question asks for mass REMAINING (5 g) or mass DECAYED (80 − 5 = 75 g)!",
                ),
                WorkedExample(
                  title = "Balancing Successive α and β Decays",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement =
                    "Nucleus ²³⁸₉₂U emits 1 alpha particle and 2 beta particles. Find final mass number A and atomic number Z.",
                  steps =
                    listOf(
                      "Step 1 — 1 α lowers mass by 4 and Z by 2: (238−4 = 234, 92−2 = 90).",
                      "Step 2 — 2 β⁻ leave mass unchanged and add +2 to Z: (234, 90+2 = 92).",
                    ),
                  finalAnswer = "²³⁴₉₂U (isotope of Uranium!)",
                  examinerTrap = "Remember Beta⁻ emission INCREASES atomic number Z by +1!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Carbon-14 Fossil Dating & Radiotherapy",
                  industryDomain = "Archaeology & Oncology",
                  narrative =
                    "Scientists measure surviving C-14 fraction (T_½ = 5,730 yrs) in ancient Nok terracotta charcoal, while Co-60 gamma beams shrink tumors precisely.",
                  examConnection = "Direct real-life uses of radioactive isotopes in JAMB/WAEC.",
                ),
              ),
            labType = PracticalLabType.QUANTUM_NUCLEAR_LAB,
            labBrief =
              "Switch to Half-Life Decay Counter Mode below: set initial atoms N₀ and Half-Life T_½ to watch the exponential decay curve drop step by step!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_phy_16_1",
                  topic = "Half-Life",
                  examType = "JAMB",
                  year = 2024,
                  question = "A radioactive sample drops from 640 counts/min to 40 counts/min in 24 hours. What is its half-life?",
                  options = listOf("4 hours", "6 hours", "8 hours", "12 hours"),
                  correctIndex = 1,
                  hint = "640 → 320 → 160 → 80 → 40 counts 4 half-lives.",
                  stepByStepSolution = "640 / 40 = 16 = 2⁴ => 4 half-lives in 24 h => T_½ = 24 / 4 = 6 hours.",
                ),
                PracticeQuestion(
                  id = "ex_phy_16_2",
                  topic = "Decay Particles",
                  examType = "WAEC",
                  year = 2023,
                  question = "Which nuclear radiation is NOT deflected by either electric or magnetic fields?",
                  options = listOf("Alpha (α)", "Beta (β)", "Gamma (γ)", "Positron"),
                  correctIndex = 2,
                  hint = "Only neutral electromagnetic radiation flies straight.",
                  stepByStepSolution = "Gamma rays carry zero electric charge and zero rest mass.",
                  waecMarkingScheme = "A1: Gamma rays.",
                ),
                PracticeQuestion(
                  id = "ex_phy_16_3",
                  topic = "Fission vs Fusion",
                  examType = "JAMB",
                  year = 2025,
                  question = "The tremendous energy pouring continuously out of our Sun comes from:",
                  options = listOf("Uranium fission", "Nuclear fusion of Hydrogen into Helium", "Coal combustion", "Alpha radioactivity"),
                  correctIndex = 1,
                  hint = "Light nuclei fuse under extreme core heat/pressure.",
                  stepByStepSolution = "Solar energy comes from thermonuclear fusion converting Hydrogen into Helium.",
                ),
                PracticeQuestion(
                  id = "ex_phy_16_4",
                  topic = "Nuclear Equations",
                  examType = "WAEC",
                  year = 2022,
                  question = "When ²³⁴₉₀Th decays by emitting one Beta (⁰₋₁e) particle, the daughter nucleus has:",
                  options = listOf("A = 230, Z = 88", "A = 234, Z = 89", "A = 234, Z = 91", "A = 233, Z = 90"),
                  correctIndex = 2,
                  hint = "Balance top 234 = A + 0 and bottom 90 = Z + (−1).",
                  stepByStepSolution = "A stays 234 and Z increases to 91 (²³⁴₉₁Pa).",
                  waecMarkingScheme = "A1: A = 234, Z = 91.",
                ),
                PracticeQuestion(
                  id = "ex_phy_16_5",
                  topic = "Mass-Energy",
                  examType = "JAMB",
                  year = 2021,
                  question = "Calculate the energy equivalent of a mass defect of 2.0 × 10⁻³ kg (c = 3.0 × 10⁸ m/s).",
                  options = listOf("6.0 × 10⁵ J", "1.8 × 10¹⁴ J", "9.0 × 10¹³ J", "1.8 × 10¹⁷ J"),
                  correctIndex = 1,
                  hint = "E = Δm c².",
                  stepByStepSolution = "E = 2.0×10⁻³ × (3.0×10⁸)² = 2.0×10⁻³ × 9.0×10¹⁶ = 1.8 × 10¹⁴ J.",
                ),
                PracticeQuestion(
                  id = "ex_phy_16_6",
                  topic = "Radiation Safety",
                  examType = "NECO",
                  year = 2020,
                  question = "Which shielding material is most effective against penetrating Gamma radiation?",
                  options = listOf("Cardboard sheet", "Aluminum foil", "Thick Lead block", "Perspex glass"),
                  correctIndex = 2,
                  hint = "High-density heavy-nucleus metal.",
                  stepByStepSolution = "Thick dense Lead (Pb) attenuates high-energy gamma photons best.",
                ),
              ),
          ),
        ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion("pq_phy_25_1", "Projectiles", "JAMB", 2025, "A ball is projected horizontally from a 45 m cliff at 20 m/s (g = 10 m/s²). How far from the cliff base does it land?", listOf("30 m", "45 m", "60 m", "90 m"), 2, "Find fall time t from 45 = ½(10)t², then x = 20t.", "t = √(90/10) = 3 s => x = 20 × 3 = 60 m."),
      PracticeQuestion("pq_phy_25_2", "Circuits", "WAEC", 2025, "Three 12 Ω resistors wired in parallel give a total effective resistance of:", listOf("4 Ω", "6 Ω", "12 Ω", "36 Ω"), 0, "Divide 12 Ω by 3 equal branches.", "R_eq = 12 / 3 = 4.0 Ω.", "A1: 4 Ω."),
      PracticeQuestion("pq_phy_24_1", "SHM", "JAMB", 2024, "If a pendulum clock ticks accurately on Earth (g = 9.8 m/s²), carrying it to the Moon (g = 1.6 m/s²) makes the clock:", listOf("Run faster", "Run slower (lose time)", "Keep exact time", "Stop oscillating"), 1, "T = 2π√(L/g) increases when g drops.", "Smaller g makes period T much longer so the clock ticks slower."),
      PracticeQuestion("pq_phy_24_2", "Optics", "WAEC", 2024, "Which optical instrument uses two convex lenses to form a magnified, inverted image of distant stars?", listOf("Simple magnifier", "Astronomical telescope", "Periscope", "Slide projector"), 1, "Objective forms small real image; eyepiece magnifies it.", "Astronomical refracting telescope uses objective + eyepiece convex lenses.", "A1: Astronomical telescope."),
      PracticeQuestion("pq_phy_23_1", "Equilibrium", "JAMB", 2023, "A uniform beam 4 m long weighing 200 N is pivoted at its center. A 300 N weight sits 1 m left of center. Where must a 150 N weight sit on the right to balance?", listOf("1.0 m", "1.5 m", "2.0 m", "2.5 m"), 2, "300 × 1 = 150 × d.", "d = 300 / 150 = 2.0 m from pivot."),
      PracticeQuestion("pq_phy_23_2", "Heat Capacity", "WAEC", 2023, "2 kg of metal cools from 90°C to 40°C while releasing 40,000 J. Find its specific heat capacity.", listOf("200 J/kg·K", "400 J/kg·K", "800 J/kg·K", "1,000 J/kg·K"), 1, "c = Q / (m Δθ).", "c = 40,000 / (2 × 50) = 400 J/kg·K.", "M1: 40000/(2×50); A1: 400 J/kg·K."),
      PracticeQuestion("pq_phy_22_1", "Fluid Pressure", "JAMB", 2022, "Calculate the gauge pressure at the bottom of a kerosene tank 2.5 m deep (ρ = 800 kg/m³, g = 10 m/s²).", listOf("2,000 Pa", "20,000 Pa", "25,000 Pa", "80,000 Pa"), 1, "P = h ρ g.", "P = 2.5 × 800 × 10 = 20,000 Pa."),
      PracticeQuestion("pq_phy_22_2", "Sound Harmonics", "WAEC", 2022, "Which note series is produced by an organ pipe CLOSED at one end?", listOf("Even harmonics only", "Odd harmonics only", "All whole-number harmonics", "Sub-harmonics"), 1, "Node at closed end, antinode at open end.", "Closed pipes resonate only at f₀, 3f₀, 5f₀ (odd harmonics).", "A1: Odd harmonics only."),
      PracticeQuestion("pq_phy_21_1", "Magnetism", "JAMB", 2021, "Heating a steel bar magnet above its Curie temperature or dropping it repeatedly:", listOf("Strengthens poles", "Demagnetizes it", "Reverses poles", "Makes it diamagnetic"), 1, "Heat/hammering scrambles aligned magnetic domains.", "Thermal agitation randomizes magnetic domain alignment."),
      PracticeQuestion("pq_phy_21_2", "Capacitors", "WAEC", 2021, "Two capacitors of 6 μF each are joined in PARALLEL across 10 V. Find total charge stored.", listOf("30 μC", "60 μC", "120 μC", "600 μC"), 2, "C_p = 6 + 6 = 12 μF; Q = C V.", "Q = 12 μF × 10 V = 120 μC.", "M1: C=12 μF; A1: 120 μC."),
      PracticeQuestion("pq_phy_20_1", "Transformers", "JAMB", 2020, "A transformer has 500 primary turns and 2,000 secondary turns. What output voltage comes from 120 V AC input?", listOf("30 V", "240 V", "480 V", "600 V"), 2, "V_s = V_p × (N_s / N_p).", "V_s = 120 × (2000/500) = 120 × 4 = 480 V."),
      PracticeQuestion("pq_phy_20_2", "Work & Machines", "WAEC", 2020, "An effort of 60 N raises a 180 N load using a machine of Velocity Ratio 4. Find Efficiency.", listOf("25%", "50%", "75%", "80%"), 2, "MA = 180/60 = 3; Eff = (3/4)×100%.", "Efficiency = 75%.", "M1: MA=3; A1: 75%."),
      PracticeQuestion("pq_phy_19_1", "Gas Laws", "JAMB", 2019, "A sealed rigid steel tank of gas is heated from 27°C to 327°C. Pressure becomes:", listOf("Half", "Double", "Triple", "Four times"), 1, "Convert 27°C→300 K and 327°C→600 K.", "T doubles in Kelvin -> P doubles."),
      PracticeQuestion("pq_phy_19_2", "Optics Refraction", "WAEC", 2019, "What causes a straight ruler dipped obliquely into clear water to appear bent at the surface?", listOf("Dispersion", "Refraction of light", "Interference", "Diffraction"), 1, "Speed change bends light leaving water.", "Light from submerged part refracts away from normal into air.", "A1: Refraction."),
      PracticeQuestion("pq_phy_18_1", "Radioactivity", "JAMB", 2018, "Which of these cannot be altered by extreme temperature, pressure, or chemical combination?", listOf("Reaction rate", "Radioactive half-life", "Gas pressure", "Electrical conductivity"), 1, "Decay happens deep inside the shielded nucleus.", "Half-life is a purely nuclear constant."),
      PracticeQuestion("pq_phy_18_2", "Kinetic Energy", "WAEC", 2018, "A 3 kg stone falls 20 m from rest (g = 10 m/s²). What kinetic energy does it strike ground with?", listOf("60 J", "300 J", "600 J", "1,200 J"), 2, "All initial mgh converts to KE.", "KE = mgh = 3 × 10 × 20 = 600 J.", "A1: 600 J."),
      PracticeQuestion("pq_phy_26_1", "Circular Motion", "JAMB", 2025, "A satellite orbits Earth steadily at constant speed. Which statement is true?", listOf("Acceleration is zero", "Net force points radially inward toward Earth's center", "Momentum is constant", "Centripetal force does positive work"), 1, "Direction changes constantly toward the circle center.", "Gravity pulls radially inward providing centripetal acceleration."),
      PracticeQuestion("pq_phy_26_2", "Photoelectric Effect", "WAEC", 2025, "The minimum frequency of light needed to eject electrons from a given metal surface is its:", listOf("Resonance frequency", "Fundamental frequency", "Threshold frequency", "Cut-off wavelength"), 2, "Corresponds to Work Function Φ = h f₀.", "Threshold frequency f₀.", "A1: Threshold frequency."),
      PracticeQuestion("pq_phy_24_3", "Dimensions", "JAMB", 2024, "Which physical quantity is dimensionless?", listOf("Force", "Refractive index", "Momentum", "Pressure"), 1, "Ratio of speed c in vacuum to speed v in medium.", "Refractive index n = c/v cancels all units."),
      PracticeQuestion("pq_phy_23_3", "Current Electricity", "WAEC", 2023, "A 60 W, 240 V lamp operates at normal rating. Calculate filament resistance.", listOf("4 Ω", "16 Ω", "960 Ω", "14,400 Ω"), 2, "R = V² / P.", "R = 240² / 60 = 57,600 / 60 = 960 Ω.", "M1: 240²/60; A1: 960 Ω."),
      PracticeQuestion("pq_phy_22_3", "Conduction", "JAMB", 2022, "Why does a tile floor feel colder to bare feet than a wooden rug at the same room temperature?", listOf("Tiles are actually colder", "Tiles conduct heat out of warm feet much faster than wood", "Wood radiates infrared heat", "Tiles evaporate moisture"), 1, "Thermal conductivity of ceramic beats wood.", "High thermal conductivity draws body heat away rapidly."),
      PracticeQuestion("pq_phy_21_3", "Electric Energy", "WAEC", 2021, "How much charge flows when a 2.5 A current runs for 4 minutes?", listOf("10 C", "100 C", "600 C", "1,000 C"), 2, "Q = I × t (convert 4 min to 240 s).", "Q = 2.5 × 240 = 600 C.", "M1: 2.5×240; A1: 600 C."),
      PracticeQuestion("pq_phy_19_3", "Electrostatics", "JAMB", 2019, "If charge +Q is moved perpendicular to an electric field line between equipotential points, work done is:", listOf("Positive maximum", "Negative", "Zero", "Infinite"), 2, "Equipotential means ΔV = 0.", "W = q ΔV = 0 J."),
      PracticeQuestion("pq_phy_18_3", "Electromagnetism", "WAEC", 2018, "Which device converts mechanical rotational energy directly into electrical energy?", listOf("Electric motor", "Transformer", "Dynamo / Generator", "Ammeter"), 2, "Coils spin inside a magnet via electromagnetic induction.", "A generator / dynamo.", "A1: Generator (Dynamo)."),
    )
}
