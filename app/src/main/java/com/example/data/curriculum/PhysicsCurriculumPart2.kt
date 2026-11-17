package com.example.data.curriculum

object PhysicsCurriculumPart2 {

  val modules: List<CourseModule> =
    listOf(
      CourseModule(
        id = "phys_mod_3",
        subjectId = "physics",
        indexLabel = "Module 03",
        title = "Waves, Sound & Geometrical Optics",
        summary = "Explore SHM, wave interference, resonance in pipes, Snell's refraction law, and mirrors/lenses.",
        lessons =
          listOf(
            Lesson(
              id = "phys_07_waves_shm",
              moduleId = "phys_mod_3",
              subjectId = "physics",
              title = "SHM, Progressive Waves & Sound Resonance",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 5.1–5.5 • WAEC Waves",
              learningOutcomes =
                listOf(
                  "Apply v = fλ and y = A·sin(2πft − 2πx/λ) to progressive waves.",
                  "Distinguish open vs closed pipe harmonics and Doppler/echo calculations.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1 (From Scratch): Waves Transfer Energy, Not Matter",
                    explanation =
                      "When a pebble drops into a pond, water particles bob up and down locally while the ripple carries energy outward. Amplitude A is max crest height; Wavelength λ is crest-to-crest distance; Frequency f (Hz) is waves per second.",
                    keyFormula = "v = f\\lambda   |   T = \\frac{1}{f}   |   T_{pendulum} = 2\\pi\\sqrt{\\frac{L}{g}}",
                    examTip =
                      "Pendulum period T depends ONLY on length L and gravity g — NOT on bob mass! Quadrupling L doubles T.",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Stationary Waves & Air Columns",
                    explanation =
                      "In an air pipe, a closed end forces a displacement Node (N) while an open end creates an Antinode (A). A CLOSED pipe resonates only at ODD harmonics (f₀, 3f₀, 5f₀) with L = λ/4 at fundamental. An OPEN pipe resonates at ALL harmonics (f₀, 2f₀, 3f₀) with L = λ/2.",
                    keyFormula = "f_{closed} = \\frac{v}{4L}   |   f_{open} = \\frac{v}{2L}   |   v_{echo} = \\frac{2d}{t}",
                    examTip =
                      "In echo problems sound goes TO the cliff and BACK, so total distance is 2d!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Harmonic Progressive Wave Equation",
                    examSource = "JAMB UTME 2024 • Physics Q25",
                    problemStatement =
                      "A transverse wave is given by y = 0.05·sin(40πt − 0.5πx) where x and y are in metres. Find (a) wavelength and (b) wave speed.",
                    steps =
                      listOf(
                        "Step 1 — Match to y = A·sin(2πft − 2πx/λ).",
                        "Step 2 — 2πf = 40π => f = 20 Hz; 2π/λ = 0.5π => λ = 4.0 m.",
                        "Step 3 — Wave speed: v = fλ = 20 × 4.0 = 80 m/s.",
                      ),
                    finalAnswer = "λ = 4.0 m, v = 80 m/s",
                    examinerTrap = "Always factor out 2π inside the sine bracket before reading f and λ!",
                  ),
                  WorkedExample(
                    title = "Closed Pipe Fundamental & Resonance",
                    examSource = "WAEC SSCE 2022 • Paper 2 Q7",
                    problemStatement =
                      "A closed organ pipe of length 0.85 m resonates at its fundamental frequency when speed of sound is 340 m/s. Find the fundamental and first overtone frequencies.",
                    steps =
                      listOf(
                        "Step 1 — Fundamental: f₀ = v / (4L) = 340 / (4 × 0.85) = 340 / 3.4 = 100 Hz.",
                        "Step 2 — Closed pipe omits even harmonics, so 1st overtone = 3rd harmonic = 3 × 100 = 300 Hz.",
                      ),
                    finalAnswer = "f₀ = 100 Hz, 1st overtone = 300 Hz",
                    examinerTrap = "Closed pipes have NO 2f₀ overtone; the first overtone is 3f₀!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Ultrasonic Echo Depth Sounders on Fishing Trawlers",
                    industryDomain = "Marine Acoustics",
                    narrative =
                      "Trawlers pulse high-frequency sound downward and time the returning seabed echo t to map depth d = ½·v·t safely.",
                    examConnection = "Direct match for sonar/echo distance questions in WAEC.",
                  ),
                  RealLifeScenario(
                    title = "Guitar String Tension Tuning",
                    industryDomain = "Musical Acoustics",
                    narrative =
                      "Tightening a guitar peg raises string tension T, increasing fundamental frequency f = (1/2L)√(T/μ).",
                    examConnection = "Tests f ∝ √T in JAMB stationary wave questions.",
                  ),
                ),
              labType = PracticalLabType.WAVE_OPTICS_BENCH,
              labBrief =
                "Adjust Wave Frequency in the Wave & Optics Bench below to inspect how wavelength λ shrinks inversely as frequency rises in a medium of fixed speed.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phys_w1",
                    topic = "Waves",
                    examType = "JAMB",
                    year = 2025,
                    question = "A simple pendulum has a period of 2.0 s. What is its new period if its length is made 4 times longer?",
                    options = listOf("1.0 s", "4.0 s", "8.0 s", "16.0 s"),
                    correctIndex = 1,
                    hint = "T ∝ √L.",
                    stepByStepSolution = "T₂ = T₁√4 = 2.0 × 2 = 4.0 s.",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_w2",
                    topic = "Sound & Echo",
                    examType = "WAEC",
                    year = 2023,
                    question = "A man standing 510 m from a high wall claps his hands. How soon does he hear the echo if speed of sound is 340 m/s?",
                    options = listOf("1.5 s", "2.0 s", "3.0 s", "4.0 s"),
                    correctIndex = 2,
                    hint = "Total sound travel = 2 × 510 m.",
                    stepByStepSolution = "t = 2d / v = (2 × 510) / 340 = 1020 / 340 = 3.0 s.",
                    waecMarkingScheme = "M1: t = 2d/v; A1: 3.0 s.",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_w3",
                    topic = "Waves",
                    examType = "JAMB",
                    year = 2022,
                    question = "Which property distinguishes transverse waves from longitudinal waves?",
                    options = listOf("Refraction", "Diffraction", "Polarization", "Interference"),
                    correctIndex = 2,
                    hint = "Only waves vibrating perpendicular to travel direction can be polarized.",
                    stepByStepSolution = "Polarization is unique to transverse waves (light, EM waves); sound (longitudinal) cannot be polarized.",
                  ),
                ),
            ),
            Lesson(
              id = "phys_08_optics",
              moduleId = "phys_mod_3",
              subjectId = "physics",
              title = "Refraction, Snell's Law, Mirrors & Lenses",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 5.6–5.10 • WAEC Optics",
              learningOutcomes =
                listOf(
                  "Apply Snell's Law n₁sin(i) = n₂sin(r), refractive index n = c/v, and real/apparent depth.",
                  "Solve spherical mirror and thin lens problems using the Real-Is-Positive sign convention.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Why Light Bends (Refraction & Critical Angle)",
                    explanation =
                      "Light slows down when entering a denser optical medium like glass or water, bending TOWARD the normal line. Frequency NEVER changes during refraction; only speed v and wavelength λ change! Leaving glass into air at angles beyond Critical Angle c causes Total Internal Reflection (TIR).",
                    keyFormula = "n = \\frac{\\sin i}{\\sin r} = \\frac{c}{v} = \\frac{\\text{Real Depth}}{\\text{Apparent Depth}}   |   \\sin c = \\frac{1}{n}",
                    examTip =
                      "Total Internal Reflection happens ONLY when light travels from DENSER -> LESS DENSE medium AND i > c!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Real-Is-Positive Mirror & Lens Formula",
                    explanation =
                      "Use the unified focal equation 1/f = 1/u + 1/v. Real objects, real images, converging lenses, and concave mirrors have POSITIVE f and v. Virtual images (upright!), diverging lenses, and convex mirrors have NEGATIVE f or v.",
                    keyFormula = "\\frac{1}{f} = \\frac{1}{u} + \\frac{1}{v}   |   m = \\frac{v}{u} = \\frac{h_i}{h_o}   |   P(D) = \\frac{1}{f(m)}",
                    examTip =
                      "Optical Power in Dioptres (D) requires focal length f in METRES! A +20 cm converging lens has Power = 1 / 0.20 = +5.0 D.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Converging Lens Real Image Projection",
                    examSource = "JAMB UTME 2023 • Physics Q28",
                    problemStatement =
                      "An object is placed 30 cm in front of a converging lens of focal length 12 cm. Find the image position and magnification.",
                    steps =
                      listOf(
                        "Step 1 — Converging lens => f = +12 cm, real object => u = +30 cm.",
                        "Step 2 — 1/v = 1/12 − 1/30 = (5 − 2)/60 = 3/60 = 1/20 => v = +20 cm (real, inverted).",
                        "Step 3 — Magnification m = v / u = 20 / 30 = 0.67.",
                      ),
                    finalAnswer = "20 cm behind lens (Real), m = 2/3",
                    examinerTrap = "Always check sign of v: + means Real & Inverted; − means Virtual & Upright!",
                  ),
                  WorkedExample(
                    title = "Apparent Depth of a Swimming Pool",
                    examSource = "WAEC SSCE 2021 • Paper 1 Q29",
                    problemStatement =
                      "A coin lies at the bottom of a pool 2.0 m deep. If water has refractive index 4/3, how deep does the coin appear from straight above?",
                    steps =
                      listOf(
                        "Step 1 — n = Real Depth / Apparent Depth => Apparent Depth = 2.0 / (4/3).",
                        "Step 2 — Apparent Depth = 2.0 × 3 / 4 = 1.5 m (apparent upward shift = 0.5 m).",
                      ),
                    finalAnswer = "1.5 m",
                    examinerTrap = "Apparent depth is ALWAYS shallower than real depth!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Fibre-Optic Submarine Internet Cables",
                    industryDomain = "Telecommunications",
                    narrative =
                      "Undersea internet cables landing in Lagos bounce laser pulses miles underwater inside silica glass cores using Total Internal Reflection (sin c = 1/n).",
                    examConnection = "Standard WAEC practical application of TIR & critical angle.",
                  ),
                  RealLifeScenario(
                    title = "Optometry Spectacle Correction",
                    industryDomain = "Clinical Vision Science",
                    narrative =
                      "Short-sightedness (myopia) is corrected with a diverging concave lens (−D), while long-sightedness (hypermetropia) uses a converging convex lens (+D).",
                    examConnection = "Frequently tested eye-defect lens matching in JAMB & WAEC.",
                  ),
                ),
              labType = PracticalLabType.WAVE_OPTICS_BENCH,
              labBrief =
                "Switch between Water (1.33), Glass (1.50), and Diamond (2.42) in the Optics Bench below and drag Incidence Angle i to watch refraction ray bending and critical angle c!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phys_op1",
                    topic = "Optics",
                    examType = "JAMB",
                    year = 2024,
                    question = "What is the critical angle for light traveling from a medium of refractive index √2 into air?",
                    options = listOf("30°", "45°", "60°", "90°"),
                    correctIndex = 1,
                    hint = "sin(c) = 1 / n.",
                    stepByStepSolution = "sin(c) = 1 / √2 => c = 45°.",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_op2",
                    topic = "Lenses",
                    examType = "WAEC",
                    year = 2023,
                    question = "To correct myopia (short-sightedness), a doctor prescribes spectacles made of:",
                    options = listOf("Convex lenses", "Concave lenses", "Cylindrical lenses", "Plane glass"),
                    correctIndex = 1,
                    hint = "Myopic eyes focus parallel rays too early (in front of retina) and need divergence.",
                    stepByStepSolution = "Concave (diverging) lenses spread incoming rays slightly so they focus right on the retina.",
                    waecMarkingScheme = "A1: Concave / diverging lens.",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_op3",
                    topic = "Mirrors",
                    examType = "JAMB",
                    year = 2022,
                    question = "An object placed 10 cm from a concave mirror of focal length 15 cm produces an image that is:",
                    options = listOf("Real and diminished", "Real and magnified", "Virtual and magnified", "Virtual and diminished"),
                    correctIndex = 2,
                    hint = "Object is inside focal point (u < f) of a concave mirror (like a shaving mirror).",
                    stepByStepSolution = "1/v = 1/15 − 1/10 = −1/30 => v = −30 cm (virtual, upright, 3× magnified).",
                  ),
                ),
            ),
          ),
      ),
      CourseModule(
        id = "phys_mod_4",
        subjectId = "physics",
        indexLabel = "Module 04",
        title = "Electricity, Electromagnetism & Modern Physics",
        summary = "Master DC circuits, capacitors, transformers, AC impedance, photoelectric effect, and nuclear half-life.",
        lessons =
          listOf(
            Lesson(
              id = "phys_09_dc_circuits",
              moduleId = "phys_mod_4",
              subjectId = "physics",
              title = "DC Circuits, Internal Resistance & Capacitors",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 6 • WAEC Electricity",
              learningOutcomes =
                listOf(
                  "Solve series/parallel resistor networks and internal battery drop V = E − Ir.",
                  "Compare series vs parallel capacitor laws and stored energy E = ½CV².",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Series/Parallel Resistors & Internal Resistance",
                    explanation =
                      "In series, current I is uniform and total resistance adds (R_s = R₁ + R₂). In parallel, potential difference V is uniform and total resistance drops (1/R_p = 1/R₁ + 1/R₂). Real batteries have internal resistance r that consumes 'lost volts' I·r inside the casing.",
                    keyFormula = "E = I(R + r) = V + Ir   |   P = I^2R = \\frac{V^2}{R}",
                    examTip =
                      "Terminal p.d. equals EMF ONLY on open circuit (I = 0). Remember electrical energy paid on PHCN meters is in kWh: 1 kWh = 3.6 × 10⁶ J!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Capacitors (Opposite Algebraic Rules to Resistors!)",
                    explanation =
                      "A capacitor stores electric charge Q = C·V in Farads. Parallel capacitors ADD directly (C_p = C₁ + C₂), whereas Series capacitors combine reciprocally (1/C_s = 1/C₁ + 1/C₂) — the exact opposite of resistors!",
                    keyFormula = "Q = CV   |   U = \\frac{1}{2}CV^2   |   C_{parallel} = C_1 + C_2",
                    examTip =
                      "Do NOT drop the ½ in capacitor energy U = ½CV² = ½QV!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Parallel Load + Internal Resistance Circuit",
                    examSource = "WAEC SSCE 2022 • Paper 1 Q38",
                    problemStatement =
                      "A 12 V battery of internal resistance 1 Ω drives 6 Ω and 3 Ω resistors in parallel. Find total current and terminal p.d.",
                    steps =
                      listOf(
                        "Step 1 — R_p = (6 × 3) / (6 + 3) = 18 / 9 = 2 Ω.",
                        "Step 2 — Total current: I = E / (R_p + r) = 12 / (2 + 1) = 4.0 A.",
                        "Step 3 — Terminal p.d.: V = I·R_p = 4 × 2 = 8.0 V (Lost volts = 4 × 1 = 4 V).",
                      ),
                    finalAnswer = "I = 4.0 A, V_terminal = 8.0 V",
                    examinerTrap = "Never forget internal resistance r in series with the external network!",
                  ),
                  WorkedExample(
                    title = "Energy Stored in Parallel Capacitors",
                    examSource = "JAMB UTME 2024 • Physics Q31",
                    problemStatement =
                      "Two capacitors of 4 μF and 6 μF are connected in parallel across a 100 V supply. Calculate total energy stored.",
                    steps =
                      listOf(
                        "Step 1 — Parallel capacitance: C_eq = 4 + 6 = 10 μF = 10 × 10⁻⁶ F.",
                        "Step 2 — U = ½·C·V² = 0.5 × 10⁻⁵ × 100² = 0.5 × 10⁻⁵ × 10⁴ = 0.05 J.",
                      ),
                    finalAnswer = "0.05 J (5.0 × 10⁻² J)",
                    examinerTrap = "Convert μF to F (× 10⁻⁶) or express energy carefully in Joules!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Home Solar Inverter Battery Sag Under Load",
                    industryDomain = "Renewable Power",
                    narrative =
                      "Domestic outlets run in parallel so each appliance gets 230 V, while aging inverter batteries gain internal resistance r causing voltage sag V = E − Ir when heavy pumps start.",
                    examConnection = "Explains parallel household wiring and internal resistance drops.",
                  ),
                  RealLifeScenario(
                    title = "Camera Xenon Flash Capacitors",
                    industryDomain = "Consumer Electronics",
                    narrative =
                      "Charging a capacitor slowly from small AA cells and dumping ½CV² in milliseconds creates an intense camera flash pulse.",
                    examConnection = "Connects Q = CV and energy storage.",
                  ),
                ),
              labType = PracticalLabType.OHMS_CIRCUIT,
              labBrief =
                "Use the DC Circuit Bench below with V = 12 V, R₁ = 6 Ω, R₂ = 3 Ω: flip Series vs Parallel to see current jump from 1.33 A to 6.00 A!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phys_dc1",
                    topic = "DC Circuits",
                    examType = "JAMB",
                    year = 2024,
                    question = "Three 6 Ω resistors connected in parallel across a 12 V ideal battery dissipate total power:",
                    options = listOf("24 W", "36 W", "72 W", "144 W"),
                    correctIndex = 2,
                    hint = "R_eq = 6/3 = 2 Ω, then P = V² / R_eq.",
                    stepByStepSolution = "R_eq = 2 Ω => P = 12² / 2 = 144 / 2 = 72 W.",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_dc2",
                    topic = "Capacitors",
                    examType = "WAEC",
                    year = 2023,
                    question = "What is the equivalent capacitance of two 6 μF capacitors connected in series?",
                    options = listOf("3 μF", "6 μF", "12 μF", "36 μF"),
                    correctIndex = 0,
                    hint = "Series capacitors obey 1/C_s = 1/C₁ + 1/C₂.",
                    stepByStepSolution = "1/C_s = 1/6 + 1/6 = 2/6 = 1/3 => C_s = 3 μF.",
                    waecMarkingScheme = "M1: 1/Cs = 1/6 + 1/6; A1: 3 μF.",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_dc3",
                    topic = "Electrical Billing",
                    examType = "JAMB",
                    year = 2023,
                    question = "A 2 kW electric kettle runs for 30 minutes daily for 10 days. At ₦50 per kWh, what is the energy cost?",
                    options = listOf("₦250", "₦500", "₦1000", "₦1500"),
                    correctIndex = 1,
                    hint = "Total kWh = 2 kW × (0.5 hr × 10 days).",
                    stepByStepSolution = "Energy = 2 kW × 5 hr = 10 kWh. Cost = 10 × ₦50 = ₦500.",
                  ),
                ),
            ),
            Lesson(
              id = "phys_10_ac_transformers",
              moduleId = "phys_mod_4",
              subjectId = "physics",
              title = "Electromagnetism, Transformers & AC Impedance",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 7 & 8 • WAEC AC Circuits",
              learningOutcomes =
                listOf(
                  "Apply Faraday's & Lenz's Laws and transformer turns ratio V_s/V_p = N_s/N_p.",
                  "Calculate inductive/capacitive reactance (X_L, X_C), impedance Z, and resonance frequency.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Transformers & National Grid Transmission",
                    explanation =
                      "A changing current in the primary coil induces an alternating magnetic flux in a laminated soft-iron core (lamination cuts Eddy Current heat!). Step-up transformers have N_s > N_p to transmit electricity at HIGH voltage and LOW current, slashing I²R cable losses.",
                    keyFormula = "\\frac{V_s}{V_p} = \\frac{N_s}{N_p}   |   \\eta = \\frac{V_s I_s}{V_p I_p} \\times 100\\%",
                    examTip =
                      "Transformers ONLY work on Alternating Current (AC) — never steady DC, because DC produces zero changing magnetic flux!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: RLC Alternating Current & Resonance",
                    explanation =
                      "In AC circuits, inductors oppose high frequencies (X_L = 2πfL) while capacitors oppose low frequencies (X_C = 1/(2πfC)). Total opposition is Impedance Z = √(R² + (X_L − X_C)²). Resonance occurs when X_L = X_C, making Z = R (minimum) and current MAXIMUM!",
                    keyFormula = "Z = \\sqrt{R^2 + (X_L - X_C)^2}   |   f_0 = \\frac{1}{2\\pi\\sqrt{LC}}",
                    examTip =
                      "Remember rms vs peak values: I_rms = I₀ / √2 and V_rms = V₀ / √2!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Non-Ideal Transformer Current Calculation",
                    examSource = "WAEC SSCE 2023 • Paper 2 Q9",
                    problemStatement =
                      "A 90% efficient transformer steps down 240 V to 12 V to power a 36 W lamp. Calculate the primary current.",
                    steps =
                      listOf(
                        "Step 1 — Output power P_out = 36 W.",
                        "Step 2 — Efficiency η = P_out / P_in => P_in = 36 / 0.90 = 40 W.",
                        "Step 3 — Primary current: I_p = P_in / V_p = 40 / 240 = 0.167 A (1/6 A).",
                      ),
                    finalAnswer = "0.17 A",
                    examinerTrap = "Do NOT assume I_p = P_out/V_p unless efficiency is 100%!",
                  ),
                  WorkedExample(
                    title = "Series RL Impedance & Current",
                    examSource = "JAMB UTME 2022 • Physics Q35",
                    problemStatement =
                      "A 30 Ω resistor is connected in series with an inductor of reactance 40 Ω across a 100 V rms AC supply. Find the current.",
                    steps =
                      listOf(
                        "Step 1 — Use Pythagorean triangle 3–4–5: Z = √(30² + 40²) = √2500 = 50 Ω.",
                        "Step 2 — I_rms = V_rms / Z = 100 / 50 = 2.0 A.",
                      ),
                    finalAnswer = "2.0 A",
                    examinerTrap = "Never add R + X_L directly (30 + 40 = 70 is wrong); reactances are 90° out of phase with resistance!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "330 kV National Grid Step-Up Substations",
                    industryDomain = "Electrical Grid Infrastructure",
                    narrative =
                      "Power generated at Egbin or Shiroro is stepped up to 330 kV before long-distance transmission because cutting current by 10× reduces I²R heat losses by 100×!",
                    examConnection = "Classic WAEC Theory question: why transmit AC at very high voltage?",
                  ),
                  RealLifeScenario(
                    title = "FM Radio Station RLC Resonance Tuning",
                    industryDomain = "RF Broadcast Engineering",
                    narrative =
                      "Turning a radio dial tunes capacitance C until f₀ = 1/(2π√LC) matches your chosen FM station, boosting signal current exclusively at that station.",
                    examConnection = "Tests resonance condition X_L = X_C in JAMB.",
                  ),
                ),
              labType = PracticalLabType.OHMS_CIRCUIT,
              labBrief =
                "Verify how total opposition (resistance/impedance) inversely controls circuit current I = V/Z in the circuit analyzer below.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phys_ac1",
                    topic = "Transformers",
                    examType = "JAMB",
                    year = 2024,
                    question = "Why is the core of a practical transformer laminated into thin insulated iron sheets?",
                    options =
                      listOf(
                        "To increase hysteresis loss",
                        "To reduce eddy current heat loss",
                        "To convert AC to DC",
                        "To decrease mutual inductance",
                      ),
                    correctIndex = 1,
                    hint = "Thin insulated laminations block circulating induced currents in the core.",
                    stepByStepSolution = "Laminating the soft-iron core increases resistance to circulating eddy currents, minimizing I²R core heating.",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_ac2",
                    topic = "AC Circuits",
                    examType = "WAEC",
                    year = 2022,
                    question = "An AC source has peak voltage 220√2 V. What is its root-mean-square (rms) voltage?",
                    options = listOf("110 V", "220 V", "311 V", "440 V"),
                    correctIndex = 1,
                    hint = "V_rms = V₀ / √2.",
                    stepByStepSolution = "V_rms = (220√2) / √2 = 220 V.",
                    waecMarkingScheme = "A1: 220 V.",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_ac3",
                    topic = "AC Resonance",
                    examType = "JAMB",
                    year = 2023,
                    question = "At resonance in a series RLC circuit, which statement is TRUE?",
                    options = listOf("Z is maximum", "Current is zero", "X_L equals X_C", "Power factor is zero"),
                    correctIndex = 2,
                    hint = "Capacitive and inductive reactances cancel each other completely.",
                    stepByStepSolution = "When X_L = X_C, Z = R (minimum impedance), giving maximum current and unity power factor.",
                  ),
                ),
            ),
            Lesson(
              id = "phys_11_modern_nuclear",
              moduleId = "phys_mod_4",
              subjectId = "physics",
              title = "Photoelectric Effect, Bohr Atom & Nuclear Half-Life",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 9 • WAEC Modern Physics",
              learningOutcomes =
                listOf(
                  "Apply Einstein's photoelectric equation hf = Φ + KE_max and threshold frequency f₀.",
                  "Balance α/β/γ nuclear decays and compute half-life N = N₀(½)^n.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Photons & Einstein's Photoelectric Equation",
                    explanation =
                      "Light arrives in discrete packets (photons) of energy E = h·f = h·c/λ. Electrons escape a metal surface ONLY if photon energy hf exceeds the Work Function Φ = h·f₀. Higher light intensity emits MORE electrons per second (current), while higher frequency increases electron SPEED (KE_max)!",
                    keyFormula = "hf = \\Phi + KE_{max}   |   \\Phi = hf_0   |   1\\text{ eV} = 1.6\\times 10^{-19}\\text{ J}",
                    examTip =
                      "Intensity controls photoelectron CURRENT; Frequency controls maximum KINETIC ENERGY (and stopping potential eV_s)!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Radioactivity & Half-Life Decay Law",
                    explanation =
                      "Alpha (⁴₂He) lowers mass number by 4 and atomic number by 2. Beta⁻ (⁰₋₁e) keeps mass number unchanged and raises atomic number by +1. Gamma rays change neither! Half-life T_½ is the constant time for half the unstable nuclei to decay.",
                    keyFormula = "N = N_0 \\left(\\frac{1}{2}\\right)^n   |   n = \\frac{t}{T_{1/2}}",
                    examTip =
                      "Be careful whether a question asks for fraction REMAINING (1/2^n) or fraction DECAYED (1 − 1/2^n)!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Half-Life Activity Calculation",
                    examSource = "JAMB UTME 2024 • Physics Q39",
                    problemStatement =
                      "A radioactive isotope has a half-life of 6 hours. What fraction of the original sample remains after 24 hours?",
                    steps =
                      listOf(
                        "Step 1 — Number of half-lives: n = 24 / 6 = 4.",
                        "Step 2 — Remaining fraction: N / N₀ = (1/2)⁴ = 1/16.",
                      ),
                    finalAnswer = "1/16 remaining (15/16 decayed)",
                    examinerTrap = "Do not divide 1 by 4; each half-life halves the PREVIOUS quantity (1 -> ½ -> ¼ -> ⅛ -> 1/16).",
                  ),
                  WorkedExample(
                    title = "Photoelectric Stopping Kinetic Energy",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q48",
                    problemStatement =
                      "Light of photon energy 4.5 eV strikes a zinc plate whose work function is 3.3 eV. Find the maximum kinetic energy of emitted electrons in Joules (1 eV = 1.6 × 10⁻¹⁹ J).",
                    steps =
                      listOf(
                        "Step 1 — KE_max = hf − Φ = 4.5 − 3.3 = 1.2 eV.",
                        "Step 2 — Convert to Joules: 1.2 × 1.6 × 10⁻¹⁹ = 1.92 × 10⁻¹⁹ J.",
                      ),
                    finalAnswer = "1.92 × 10⁻¹⁹ J",
                    examinerTrap = "Always subtract in the same unit (both eV or both Joules) before converting!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Solar Photovoltaic Panels & Carbon-14 Archaeological Dating",
                    industryDomain = "Clean Energy & Archaeology",
                    narrative =
                      "Solar cells turn photon energy directly into electrical current using silicon bandgaps, while archaeologists date Nok terracotta artifacts from remaining Carbon-14 half-lives (5,730 years).",
                    examConnection = "Links photoelectric quantum threshold and half-life decay.",
                  ),
                  RealLifeScenario(
                    title = "Medical Cobalt-60 Radiotherapy",
                    industryDomain = "Oncology & Nuclear Medicine",
                    narrative =
                      "Controlled gamma beams target deep tumors selectively while short-lived Tc-99m tracers image organs without prolonged radiation exposure.",
                    examConnection = "Tests penetrating power of α (paper) < β (aluminum) < γ (thick lead).",
                  ),
                ),
              labType = PracticalLabType.WAVE_OPTICS_BENCH,
              labBrief =
                "Use the Frequency slider in the bench below to visualize how photon frequency f governs wave energy E = hf.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phys_n1",
                    topic = "Modern Physics",
                    examType = "JAMB",
                    year = 2025,
                    question = "A nuclide ²³⁸₉₂U emits an alpha particle followed by a beta particle. What are the mass and atomic numbers of the daughter nuclide?",
                    options = listOf("234 and 91", "234 and 90", "236 and 91", "234 and 89"),
                    correctIndex = 0,
                    hint = "Alpha: A−4, Z−2. Beta⁻: A−0, Z+1.",
                    stepByStepSolution = "A = 238 − 4 = 234. Z = 92 − 2 + 1 = 91.",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_n2",
                    topic = "Photoelectric Effect",
                    examType = "WAEC",
                    year = 2023,
                    question = "Which property of incident light increases the maximum kinetic energy of emitted photoelectrons?",
                    options = listOf("Intensity", "Frequency", "Exposure time", "Beam area"),
                    correctIndex = 1,
                    hint = "KE_max = hf − Φ.",
                    stepByStepSolution = "Increasing frequency f increases photon energy hf, raising photoelectron kinetic energy.",
                    waecMarkingScheme = "A1: Frequency (or shorter wavelength).",
                  ),
                  PracticeQuestion(
                    id = "ex_phys_n3",
                    topic = "Radioactivity",
                    examType = "JAMB",
                    year = 2024,
                    question = "A sample decays from 1600 counts/min to 200 counts/min in 15 days. What is its half-life?",
                    options = listOf("3 days", "5 days", "7.5 days", "10 days"),
                    correctIndex = 1,
                    hint = "1600 -> 800 -> 400 -> 200 is 3 half-lives.",
                    stepByStepSolution = "1600/200 = 8 = 2³, so n = 3 half-lives. T_½ = 15 / 3 = 5 days.",
                  ),
                ),
            ),
          ),
      ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion(
        id = "pq_phys_2025_1",
        topic = "Units & Dimensions",
        examType = "JAMB",
        year = 2025,
        question = "Which of the following pairs of physical quantities have identical dimensions?",
        options = listOf("Force and Power", "Torque and Work", "Momentum and Force", "Impulse and Pressure"),
        correctIndex = 1,
        hint = "Check units: Torque is N·m; Work is N·m (Joules).",
        stepByStepSolution = "Both Torque (r × F) and Work (F · d) have dimension M L² T⁻².",
      ),
      PracticeQuestion(
        id = "pq_phys_2025_2",
        topic = "Projectile Motion",
        examType = "WAEC",
        year = 2025,
        question = "A projectile attains a maximum height of 45 m when launched at 30° to the horizontal. Find its initial launch speed (g = 10 m/s²).",
        options = listOf("30 m/s", "45 m/s", "60 m/s", "90 m/s"),
        correctIndex = 2,
        hint = "Use H = u²sin²θ / (2g) with sin(30°) = 0.5.",
        stepByStepSolution = "45 = u²(0.5)² / 20 => u²(0.25) = 900 => u² = 3600 => u = 60 m/s.",
        waecMarkingScheme = "M1: H = u²sin²θ/2g; M1: u² = 3600; A1: 60 m/s.",
      ),
      PracticeQuestion(
        id = "pq_phys_2024_1",
        topic = "Kinematics",
        examType = "JAMB",
        year = 2024,
        question = "A stone drops freely from rest from a height of 125 m. Find the time taken to hit the ground (g = 10 m/s²).",
        options = listOf("2.5 s", "4.0 s", "5.0 s", "12.5 s"),
        correctIndex = 2,
        hint = "Use h = ½gt².",
        stepByStepSolution = "125 = 5t² => t² = 25 => t = 5.0 s.",
      ),
      PracticeQuestion(
        id = "pq_phys_2024_2",
        topic = "DC Circuits",
        examType = "WAEC",
        year = 2024,
        question = "A 6 V battery of internal resistance 0.4 Ω is connected to a 2.6 Ω resistor. Calculate the power dissipated in the external resistor.",
        options = listOf("2.6 W", "4.0 W", "10.4 W", "12.0 W"),
        correctIndex = 2,
        hint = "Find current I = E / (R + r), then P = I²R.",
        stepByStepSolution = "I = 6 / (2.6 + 0.4) = 2.0 A. External power P = 2² × 2.6 = 10.4 W.",
        waecMarkingScheme = "M1: I = 2.0 A; M1: P = I²R; A1: 10.4 W.",
      ),
      PracticeQuestion(
        id = "pq_phys_2023_1",
        topic = "Projectile Motion",
        examType = "JAMB",
        year = 2023,
        question = "A stone is thrown horizontally from the top of a 45 m tower at 20 m/s. How far from the base does it land? (g = 10 m/s²)",
        options = listOf("30 m", "45 m", "60 m", "90 m"),
        correctIndex = 2,
        hint = "Find fall time from h = ½gt², then R = u_x·t.",
        stepByStepSolution = "45 = 5t² => t = 3 s. R = 20 × 3 = 60 m.",
      ),
      PracticeQuestion(
        id = "pq_phys_2023_2",
        topic = "Waves & Sound",
        examType = "WAEC",
        year = 2023,
        question = "A wave of frequency 500 Hz travels at 340 m/s. Find the distance between two consecutive troughs.",
        options = listOf("0.34 m", "0.68 m", "1.47 m", "6.80 m"),
        correctIndex = 1,
        hint = "Consecutive troughs are 1 wavelength λ apart.",
        stepByStepSolution = "λ = v / f = 340 / 500 = 0.68 m.",
        waecMarkingScheme = "M1: λ = v/f; A1: 0.68 m.",
      ),
      PracticeQuestion(
        id = "pq_phys_2022_1",
        topic = "DC Circuits",
        examType = "WAEC",
        year = 2022,
        question = "A cell of EMF 2.0 V and internal resistance 0.5 Ω supplies a 3.5 Ω resistor. Find the terminal p.d.",
        options = listOf("0.25 V", "1.50 V", "1.75 V", "2.00 V"),
        correctIndex = 2,
        hint = "I = E/(R+r), then V = IR.",
        stepByStepSolution = "I = 2.0 / 4.0 = 0.5 A. V = 0.5 × 3.5 = 1.75 V.",
        waecMarkingScheme = "M1: I = 0.5 A; A1: 1.75 V.",
      ),
      PracticeQuestion(
        id = "pq_phys_2022_2",
        topic = "Optics & Refraction",
        examType = "JAMB",
        year = 2022,
        question = "An object is placed 15 cm in front of a convex lens of focal length 10 cm. Where is the image formed?",
        options = listOf("6 cm, virtual", "30 cm, real", "25 cm, real", "30 cm, virtual"),
        correctIndex = 1,
        hint = "1/v = 1/f − 1/u.",
        stepByStepSolution = "1/v = 1/10 − 1/15 = 1/30 => v = +30 cm (real image).",
      ),
      PracticeQuestion(
        id = "pq_phys_2021_1",
        topic = "Optics & Refraction",
        examType = "JAMB",
        year = 2021,
        question = "Light enters glass (n = 1.5) from air (c = 3.0 × 10⁸ m/s). What is its speed in glass?",
        options = listOf("1.5 × 10⁸ m/s", "2.0 × 10⁸ m/s", "3.0 × 10⁸ m/s", "4.5 × 10⁸ m/s"),
        correctIndex = 1,
        hint = "n = c / v.",
        stepByStepSolution = "v = (3.0 × 10⁸) / 1.5 = 2.0 × 10⁸ m/s.",
      ),
      PracticeQuestion(
        id = "pq_phys_2021_2",
        topic = "Work & Energy",
        examType = "WAEC",
        year = 2021,
        question = "An engine develops 4.0 kW while lifting a crate steadily at 2.0 m/s. Find the lifting tension force.",
        options = listOf("500 N", "1000 N", "2000 N", "8000 N"),
        correctIndex = 2,
        hint = "P = F · v.",
        stepByStepSolution = "F = P / v = 4000 / 2.0 = 2000 N.",
        waecMarkingScheme = "M1: P = Fv; A1: 2000 N.",
      ),
      PracticeQuestion(
        id = "pq_phys_2020_1",
        topic = "Linear Momentum",
        examType = "WAEC",
        year = 2020,
        question = "A 2 kg mass moving east at 5 m/s sticks to a 3 kg mass moving west at 2 m/s. Find their common velocity.",
        options = listOf("0.8 m/s East", "0.8 m/s West", "3.2 m/s East", "1.6 m/s East"),
        correctIndex = 0,
        hint = "Take East (+) and West (−).",
        stepByStepSolution = "(2×5) + (3×−2) = 5v => 4 = 5v => v = +0.8 m/s East.",
        waecMarkingScheme = "M1: Sign convention; A1: 0.8 m/s East.",
      ),
      PracticeQuestion(
        id = "pq_phys_2020_2",
        topic = "Thermal Physics",
        examType = "JAMB",
        year = 2020,
        question = "How much heat warms 2 kg of aluminum (c = 900 J/kg·K) from 25 °C to 75 °C?",
        options = listOf("45 kJ", "90 kJ", "135 kJ", "180 kJ"),
        correctIndex = 1,
        hint = "Q = m·c·Δθ.",
        stepByStepSolution = "Q = 2 × 900 × (75 − 25) = 2 × 900 × 50 = 90,000 J = 90 kJ.",
      ),
      PracticeQuestion(
        id = "pq_phys_2019_1",
        topic = "Fluid Pressure",
        examType = "JAMB",
        year = 2019,
        question = "A hydraulic press has pistons of areas 10 cm² and 200 cm². What effort lifts a 4000 N car?",
        options = listOf("20 N", "100 N", "200 N", "800 N"),
        correctIndex = 2,
        hint = "F₁/A₁ = F₂/A₂.",
        stepByStepSolution = "F₁ = 4000 × (10 / 200) = 200 N.",
      ),
      PracticeQuestion(
        id = "pq_phys_2019_2",
        topic = "Transformers",
        examType = "WAEC",
        year = 2019,
        question = "An ideal transformer has 500 primary turns and 2000 secondary turns. If input is 120 V AC, find the secondary voltage.",
        options = listOf("30 V", "240 V", "480 V", "600 V"),
        correctIndex = 2,
        hint = "V_s / V_p = N_s / N_p.",
        stepByStepSolution = "V_s = 120 × (2000 / 500) = 120 × 4 = 480 V.",
        waecMarkingScheme = "M1: Vs/Vp = Ns/Np; A1: 480 V.",
      ),
      PracticeQuestion(
        id = "pq_phys_2018_1",
        topic = "Modern Physics",
        examType = "JAMB",
        year = 2018,
        question = "Which radiation is deflected most strongly toward the positive plate of an electric field?",
        options = listOf("Alpha rays", "Beta⁻ particles", "Gamma rays", "Neutrons"),
        correctIndex = 1,
        hint = "Opposite charges attract; very light particles deflect farthest.",
        stepByStepSolution = "Negatively charged Beta⁻ particles (electrons) have tiny mass and deflect sharply toward the positive plate.",
      ),
      PracticeQuestion(
        id = "pq_phys_2018_2",
        topic = "Simple Machines",
        examType = "WAEC",
        year = 2018,
        question = "A screw jack of pitch 0.5 cm is turned by a lever arm of length 35 cm. Calculate its Velocity Ratio (take π = 22/7).",
        options = listOf("70", "140", "220", "440"),
        correctIndex = 3,
        hint = "VR = 2πR / p.",
        stepByStepSolution = "VR = (2 × 22/7 × 35) / 0.5 = (2 × 110) / 0.5 = 440.",
        waecMarkingScheme = "M1: 2πR/p; A1: 440.",
      ),
    )
}
