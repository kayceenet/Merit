package com.example.data.curriculum

object PhysicsCurriculumPart2 {

  val module3And4: List<CourseModule> =
    listOf(
      CourseModule(
        id = "phys_mod_3",
        subjectId = "physics",
        indexLabel = "Module 03",
        title = "Thermal Physics, Gas Laws, Waves & Optics",
        summary = "Understand temperature, expansion, specific/latent heat, sound harmonics in pipes, mirrors, lenses & total internal reflection.",
        lessons =
          listOf(
            Lesson(
              id = "phys_08_heat_expansion",
              moduleId = "phys_mod_3",
              subjectId = "physics",
              title = "Temperature Scales, Thermal Expansion & Gas Laws",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 5.1–5.4 • WAEC Thermal Expansion",
              learningOutcomes =
                listOf(
                  "Calibrate ungraduated thermometers using Ice Point (0°C) and Steam Point (100°C).",
                  "Compute linear/area/volume thermal expansion (γ = 3α, β = 2α) and General Gas Law PV/T.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Why Bridges Have Tiny Metal Gaps in Hot Sun",
                    explanation =
                      "Heat makes atoms jiggle violently and push slightly farther apart—so steel rails and bridges grow longer in the afternoon sun! Engineers leave small expansion gaps so tracks don't buckle. Volume expansion coefficient γ is always THREE times linear expansivity α (γ = 3α), and area expansion β = 2α!",
                    keyFormula = "\\Delta L = \\alpha L_1 \\Delta\\theta   |   \\beta = 2\\alpha   |   \\gamma = 3\\alpha   |   \\theta(^{\\circ}C) = \\frac{X_\\theta - X_0}{X_{100} - X_0} \\times 100",
                    examTip =
                      "ALWAYS convert Celsius to Kelvin (T = °C + 273) before dividing in ANY Gas Law formula PV/T = constant!",
                  ),
                  ConceptBlock(
                    heading = "2. Pressure, Volume & Kelvin Temperature of Trapped Gas",
                    explanation =
                      "Squeeze a bicycle pump with your thumb blocking the nozzle: smaller volume packs air molecules closer so Pressure jumps up (Boyle's Law: P₁V₁ = P₂V₂). Heat a sealed balloon: faster molecules push walls outward so Volume expands with Kelvin T (Charles' Law: V₁/T₁ = V₂/T₂).",
                    keyFormula = "\\frac{P_1 V_1}{T_1} = \\frac{P_2 V_2}{T_2}   \\quad (T \\text{ strictly in Kelvin!})",
                    examTip =
                      "Water shrinks between 0°C and 4°C (anomalous expansion)—so ice floats at 0°C while 4°C water sinks, keeping fish alive below frozen lakes!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding Unknown Temperature on Uncalibrated Thermometer",
                    examSource = "JAMB UTME 2024 • Physics Q18",
                    problemStatement =
                      "A mercury thread measures 4.0 cm at 0°C and 24.0 cm at 100°C. What temperature gives length 14.0 cm?",
                    steps =
                      listOf(
                        "Step 1 — Span from 0°C to 100°C = 24.0 − 4.0 = 20.0 cm.",
                        "Step 2 — θ = [(14.0 − 4.0) / 20.0] × 100 = (10 / 20) × 100 = 50°C.",
                      ),
                    finalAnswer = "50°C",
                    examinerTrap = "Remember to subtract the 0°C ice-point reading from BOTH numerator and denominator!",
                  ),
                  WorkedExample(
                    title = "Gas Cylinder Heated at Constant Pressure",
                    examSource = "WAEC SSCE 2023 • Paper 2 Q5",
                    problemStatement =
                      "Gas occupies 300 cm³ at 27°C. What volume does it occupy at 127°C if pressure stays constant?",
                    steps =
                      listOf(
                        "Step 1 — Convert to Kelvin: T₁ = 27 + 273 = 300 K; T₂ = 127 + 273 = 400 K.",
                        "Step 2 — V₂ = V₁(T₂/T₁) = 300 × (400/300) = 400 cm³.",
                      ),
                    finalAnswer = "400 cm³",
                    examinerTrap = "Never compute 300 × (127/27) using Celsius!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Bimetallic Strip Electric Kettle Thermostat",
                    industryDomain = "Home Appliances",
                    narrative =
                      "Brass glued to steel bends when hot because brass expands faster (larger α), snapping the electric circuit open automatically when boiled.",
                    examConnection = "Classic WAEC application of unequal linear expansivities.",
                  ),
                ),
              labType = PracticalLabType.THERMAL_EXPANSION_CALORIMETRY,
              labBrief =
                "Adjust temperature, rod linear expansivity α, and gas piston Kelvin temperature below to watch metal expansion and gas volume respond!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_08_1",
                    topic = "Linear Expansion",
                    examType = "JAMB",
                    year = 2024,
                    question = "A 2.0 m steel rod (α = 1.2 × 10⁻⁵ K⁻¹) heats by 50 K. Find its increase in length.",
                    options = listOf("0.0006 m", "0.0012 m", "0.0024 m", "0.012 m"),
                    correctIndex = 1,
                    hint = "ΔL = α L Δθ.",
                    stepByStepSolution = "ΔL = 1.2×10⁻⁵ × 2.0 × 50 = 1.2 × 10⁻³ m = 0.0012 m (1.2 mm).",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_08_2",
                    topic = "Cubic Expansivity",
                    examType = "WAEC",
                    year = 2023,
                    question = "If linear expansivity of copper is α, its cubic (volume) expansivity γ equals:",
                    options = listOf("α/3", "2α", "3α", "α³"),
                    correctIndex = 2,
                    hint = "Length, breadth, and height all expand.",
                    stepByStepSolution = "γ = 3α.",
                    waecMarkingScheme = "A1: 3α.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_08_3",
                    topic = "Gas Laws",
                    examType = "JAMB",
                    year = 2025,
                    question = "At constant temperature, a gas at 2.0 atm occupies 150 cm³. What volume does it take at 6.0 atm?",
                    options = listOf("50 cm³", "75 cm³", "300 cm³", "450 cm³"),
                    correctIndex = 0,
                    hint = "P₁V₁ = P₂V₂ (Boyle's Law).",
                    stepByStepSolution = "V₂ = (2 × 150) / 6 = 50 cm³.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_08_4",
                    topic = "Thermometers",
                    examType = "WAEC",
                    year = 2022,
                    question = "Why is alcohol preferred over mercury for measuring Arctic temperatures around −60°C?",
                    options = listOf("Alcohol conducts heat faster", "Alcohol freezes much lower (−115°C) than mercury (−39°C)", "Alcohol doesn't wet glass", "Alcohol boils higher"),
                    correctIndex = 1,
                    hint = "Mercury freezes solid below −39°C.",
                    stepByStepSolution = "Mercury solidifies at −39°C, whereas alcohol stays liquid down to −115°C.",
                    waecMarkingScheme = "A1: Lower freezing point (−115°C vs −39°C).",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_08_5",
                    topic = "Anomalous Water",
                    examType = "JAMB",
                    year = 2021,
                    question = "Water reaches its MAXIMUM density at approximately:",
                    options = listOf("0°C", "4°C", "37°C", "100°C"),
                    correctIndex = 1,
                    hint = "Liquid water contracts on warming from 0°C up to this temperature.",
                    stepByStepSolution = "Water achieves maximum density (1,000 kg/m³) at 4°C.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_08_6",
                    topic = "Thermometer Calibration",
                    examType = "JAMB",
                    year = 2020,
                    question = "A resistance thermometer reads 10 Ω at 0°C, 30 Ω at 100°C, and 22 Ω in warm oil. Find oil temperature.",
                    options = listOf("40°C", "50°C", "60°C", "72°C"),
                    correctIndex = 2,
                    hint = "θ = [(22 − 10) / (30 − 10)] × 100.",
                    stepByStepSolution = "(12 / 20) × 100 = 60°C.",
                  ),
                ),
            ),
            Lesson(
              id = "phys_09_calorimetry_heat_transfer",
              moduleId = "phys_mod_3",
              subjectId = "physics",
              title = "Specific & Latent Heat, Vapors & Heat Transfer",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 5.5–5.8 • WAEC Heat & Vapors",
              learningOutcomes =
                listOf(
                  "Calculate heating & melting energies with Q = mcΔθ and Q = mL.",
                  "Explain boiling point pressure effects, relative humidity, and vacuum flask insulation.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Climbing Temperature vs Flat Phase-Change Stops",
                    explanation =
                      "Put an ice cube on a stove. While ice melts into water at 0°C—or water boils into steam at 100°C—the thermometer FREEZES flat! Heat added there doesn't raise temperature; instead, it breaks bonds holding molecules tight (Latent = 'Hidden' Heat, Q = mL).",
                    keyFormula = "Q_{\\text{temp rise}} = m c \\Delta\\theta   |   Q_{\\text{phase change}} = m L   |   \\text{Electrical Heat } = I V t",
                    examTip =
                      "Cooling by evaporation (sweating or alcohol on skin) works because escaping fast molecules steal latent heat of vaporization from your skin!",
                  ),
                  ConceptBlock(
                    heading = "2. Conduction, Convection, Radiation & Vacuum Flasks",
                    explanation =
                      "Heat travels three ways: passed hand-to-hand along a metal spoon (Conduction—stopped by vacuum/plastic), carried upward by rising warm fluid loops (Convection—why AC sits high & kettle heaters sit at the bottom), and beaming across empty space from the Sun as infrared waves (Radiation—black dull surfaces absorb/emit best, shiny silver reflects!).",
                    keyFormula = "\\text{Relative Humidity} = \\frac{\\text{SVP at Dew Point}}{\\text{SVP at Room Temp}} \\times 100\\%",
                    examTip =
                      "Pressure cookers trap steam to raise pressure above 1 atm, lifting water's boiling point above 100°C so tough beans cook way faster!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Method of Mixtures Calorimetry",
                    examSource = "JAMB UTME 2024 • Physics Q20",
                    problemStatement =
                      "A 100 W immersion heater supplies heat for 210 s to warm 0.5 kg of water (c = 4,200 J/kg·K). Find temperature rise Δθ.",
                    steps =
                      listOf(
                        "Step 1 — Electrical heat Q = P × t = 100 × 210 = 21,000 J.",
                        "Step 2 — 21,000 = m c Δθ = 0.5 × 4,200 × Δθ = 2,100 Δθ.",
                        "Step 3 — Δθ = 21,000 / 2,100 = 10 K (10°C).",
                      ),
                    finalAnswer = "10°C (10 K)",
                    examinerTrap = "Never divide watts by minutes—convert time strictly to SECONDS!",
                  ),
                  WorkedExample(
                    title = "Total Energy to Melt Ice and Warm Water",
                    examSource = "WAEC SSCE 2022 • Paper 2 Q6",
                    problemStatement =
                      "How much heat converts 0.2 kg of ice at 0°C completely into water at 20°C? (L_f = 336,000 J/kg, c = 4,200 J/kg·K)",
                    steps =
                      listOf(
                        "Step 1 — Melt ice at 0°C: Q₁ = mL = 0.2 × 336,000 = 67,200 J.",
                        "Step 2 — Warm liquid water 0°C → 20°C: Q₂ = mcΔθ = 0.2 × 4,200 × 20 = 16,800 J.",
                        "Step 3 — Total = 67,200 + 16,800 = 84,000 J (84 kJ).",
                      ),
                    finalAnswer = "84,000 J (84 kJ)",
                    examinerTrap = "Add BOTH melting latent heat AND warming sensible heat!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Vacuum Thermos Flask Engineering",
                    industryDomain = "Thermal Design",
                    narrative =
                      "Silvered inner glass reflects radiant heat, double-wall vacuum kills conduction and convection, and a plastic cork stops heat escape.",
                    examConnection = "Favorite WAEC Paper 2 diagram question.",
                  ),
                ),
              labType = PracticalLabType.THERMAL_EXPANSION_CALORIMETRY,
              labBrief =
                "Switch to Calorimetry Heating Curve Mode below to add Joules of heat to ice and observe the flat melting (0°C) & boiling (100°C) latent plateaus!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_09_1",
                    topic = "Specific Heat",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which property makes water ideal as a car radiator coolant?",
                    options = listOf("Low boiling point", "High specific heat capacity", "High density", "Transparent color"),
                    correctIndex = 1,
                    hint = "It absorbs massive Joules per °C rise.",
                    stepByStepSolution = "Water's high specific heat capacity (4,200 J/kg·K) lets it carry away lots of heat smoothly.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_09_2",
                    topic = "Boiling & Pressure",
                    examType = "WAEC",
                    year = 2023,
                    question = "Water boils at LESS than 100°C high on Mount Cameroon primarily because:",
                    options = listOf("Air is colder", "Atmospheric pressure is lower at high altitude", "Gravity decreases", "Sunlight is stronger"),
                    correctIndex = 1,
                    hint = "Lower pressure lets vapor bubbles escape sooner.",
                    stepByStepSolution = "Reduced atmospheric pressure lowers water's boiling point below 100°C.",
                    waecMarkingScheme = "A1: Lower atmospheric pressure.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_09_3",
                    topic = "Latent Heat",
                    examType = "JAMB",
                    year = 2025,
                    question = "Why does 100°C steam burn skin far worse than 100°C boiling water?",
                    options = listOf("Steam moves faster", "Steam releases huge latent heat of vaporization upon condensing", "Steam is heavier", "Water conducts poorly"),
                    correctIndex = 1,
                    hint = "2,260,000 J/kg extra hidden heat releases during condensation.",
                    stepByStepSolution = "Condensing 100°C steam dumps specific latent heat of vaporization directly onto skin before cooling.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_09_4",
                    topic = "Vacuum Flask",
                    examType = "WAEC",
                    year = 2022,
                    question = "In a thermos flask, heat loss by RADIATION is minimized primarily by:",
                    options = listOf("Vacuum space", "Silvered glass walls", "Plastic stopper", "Foam pad"),
                    correctIndex = 1,
                    hint = "Shiny mirrors reflect infrared electromagnetic waves.",
                    stepByStepSolution = "Silvered walls are poor emitters and good reflectors of infrared radiation.",
                    waecMarkingScheme = "A1: Silvered surfaces.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_09_5",
                    topic = "Calorimetry",
                    examType = "JAMB",
                    year = 2021,
                    question = "How much heat is required to warm 2 kg of aluminum (c = 900 J/kg·K) from 20°C to 70°C?",
                    options = listOf("36 kJ", "90 kJ", "126 kJ", "180 kJ"),
                    correctIndex = 1,
                    hint = "Q = m c (70 − 20).",
                    stepByStepSolution = "Q = 2 × 900 × 50 = 90,000 J = 90 kJ.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_09_6",
                    topic = "Evaporation",
                    examType = "JAMB",
                    year = 2020,
                    question = "Which condition SLOWS down the rate of evaporation from an open bowl of water?",
                    options = listOf("Wind blowing across surface", "Higher temperature", "Higher air humidity", "Wider surface area"),
                    correctIndex = 2,
                    hint = "Saturated damp air leaves nowhere for fresh vapor molecules to escape.",
                    stepByStepSolution = "High relative humidity reduces net evaporation rate.",
                  ),
                ),
            ),
            Lesson(
              id = "phys_10_waves_sound",
              moduleId = "phys_mod_3",
              subjectId = "physics",
              title = "Waves, Standing Harmonics, Sound & Resonance",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 6.1–6.5 • WAEC Waves & Acoustics",
              learningOutcomes =
                listOf(
                  "Distinguish transverse vs longitudinal waves and use v = fλ cleanly.",
                  "Solve open/closed pipe fundamental harmonics (v/4L vs v/2L) and echo distances.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Waves Move Energy Without Moving Water Forward",
                    explanation =
                      "Drop a pebble in a pond: ripples spread outward carrying energy, while a floating cork just bobs up and down (Transverse wave—light, water, EM waves). Sound travels by squishing air back-and-forth along the travel arrow (Longitudinal wave—needs air/solids, ZERO sound in space vacuum!).",
                    keyFormula = "v = f\\lambda   |   v_{\\text{echo}} = \\frac{2d}{t}   |   \\text{Progressive: } y = A\\sin\\left(2\\pi ft - \\frac{2\\pi x}{\\lambda}\\right)",
                    examTip =
                      "Echo time t includes BOTH there and back, so distance to cliff d = (v × t) / 2! Sound travels FASTEST in steel solids, slower in water, slowest in air.",
                  ),
                  ConceptBlock(
                    heading = "2. Musical Pipes, Beats & Overtones",
                    explanation =
                      "Blow across a closed soda bottle vs an open flute pipe. A CLOSED pipe forces a still air node at the bottom so ¼ wavelength fits inside -> fundamental frequency f₀ = v / (4L) (only ODD harmonics: f₀, 3f₀, 5f₀). An OPEN pipe fits ½ wavelength inside -> f₀ = v / (2L) (ALL harmonics: f₀, 2f₀, 3f₀).",
                    keyFormula = "f_{\\text{closed}} = \\frac{v}{4L} \\; (1, 3, 5\\dots)   |   f_{\\text{open}} = \\frac{v}{2L} \\; (1, 2, 3\\dots)   |   f_{\\text{beat}} = |f_1 - f_2|",
                    examTip =
                      "Pitch depends on Frequency; Loudness depends on Amplitude; Quality (Timbre) depends on Overtones/Harmonics!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Closed Organ Pipe Fundamental & First Overtone",
                    examSource = "JAMB UTME 2024 • Physics Q23",
                    problemStatement =
                      "A pipe closed at one end is 0.25 m long. If sound speed in air is 340 m/s, find fundamental frequency f₀ and first overtone f₁.",
                    steps =
                      listOf(
                        "Step 1 — Closed pipe fundamental: f₀ = v / (4L) = 340 / (4 × 0.25) = 340 / 1.0 = 340 Hz.",
                        "Step 2 — Closed pipes only allow ODD harmonics, so first overtone = 3 × f₀ = 3 × 340 = 1,020 Hz.",
                      ),
                    finalAnswer = "f₀ = 340 Hz  |  First Overtone = 1,020 Hz",
                    examinerTrap = "Don't multiply closed pipe f₀ by 2—first overtone of a closed pipe is 3f₀!",
                  ),
                  WorkedExample(
                    title = "Extracting Frequency & Wavelength from Wave Equation",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q20",
                    problemStatement =
                      "A wave is given by y = 5 sin(20π t − 0.4π x) in SI units. Find its frequency and wavelength.",
                    steps =
                      listOf(
                        "Step 1 — Compare with y = A sin(2π f t − 2π x / λ).",
                        "Step 2 — 2π f = 20π => f = 10 Hz.",
                        "Step 3 — 2π / λ = 0.4π => λ = 2 / 0.4 = 5.0 m.",
                      ),
                    finalAnswer = "f = 10 Hz  |  λ = 5.0 m",
                    examinerTrap = "Read coefficients carefully before π cancels cleanly.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Hospital Ultrasound & Ship Sonar Depth Mapping",
                    industryDomain = "Medical Imaging & Ocean Mapping",
                    narrative =
                      "Ships time underwater ultrasound bounces to map ocean trenches without touching the seabed, using d = vt / 2.",
                    examConnection = "Classic application of wave reflection (echo) in JAMB.",
                  ),
                ),
              labType = PracticalLabType.WAVE_OPTICS_BENCH,
              labBrief =
                "Switch to Sound Harmonics & Standing Waves inside the Bench below: adjust frequency f and pipe length L to see standing nodes & antinodes!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_10_1",
                    topic = "Wave Equation",
                    examType = "JAMB",
                    year = 2024,
                    question = "A radio station broadcasts at 100 MHz (1.0 × 10⁸ Hz). Using c = 3.0 × 10⁸ m/s, find the wavelength.",
                    options = listOf("0.33 m", "3.0 m", "30 m", "300 m"),
                    correctIndex = 1,
                    hint = "λ = c / f.",
                    stepByStepSolution = "λ = (3.0 × 10⁸) / (1.0 × 10⁸) = 3.0 m.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_10_2",
                    topic = "Polarization",
                    examType = "WAEC",
                    year = 2023,
                    question = "Which property distinguishes Transverse waves (like light) from Longitudinal waves (like sound)?",
                    options = listOf("Reflection", "Refraction", "Polarization", "Diffraction"),
                    correctIndex = 2,
                    hint = "Only sideways-vibrating waves can be filtered in one plane.",
                    stepByStepSolution = "Only transverse waves can undergo Polarization; longitudinal waves vibrate parallel to propagation.",
                    waecMarkingScheme = "A1: Polarization.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_10_3",
                    topic = "Echoes",
                    examType = "JAMB",
                    year = 2025,
                    question = "A boy claps near a tall cliff and hears an echo 1.5 s later. If sound speed is 340 m/s, how far is the cliff?",
                    options = listOf("255 m", "340 m", "510 m", "1,020 m"),
                    correctIndex = 0,
                    hint = "d = v t / 2.",
                    stepByStepSolution = "d = (340 × 1.5) / 2 = 510 / 2 = 255 m.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_10_4",
                    topic = "Beats",
                    examType = "JAMB",
                    year = 2022,
                    question = "Tuning forks of 256 Hz and 260 Hz are sounded together. How many beats are heard per second?",
                    options = listOf("2 Hz", "4 Hz", "258 Hz", "516 Hz"),
                    correctIndex = 1,
                    hint = "f_beat = |f₁ − f₂|.",
                    stepByStepSolution = "|260 − 256| = 4 beats per second (4 Hz).",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_10_5",
                    topic = "Closed Pipe Harmonics",
                    examType = "WAEC",
                    year = 2021,
                    question = "An open pipe and a closed pipe have the SAME fundamental frequency. The ratio of open length to closed length is:",
                    options = listOf("1 : 2", "2 : 1", "1 : 4", "4 : 1"),
                    correctIndex = 1,
                    hint = "Equate v / (2L_open) = v / (4L_closed).",
                    stepByStepSolution = "2L_open = 4L_closed => L_open / L_closed = 2 : 1.",
                    waecMarkingScheme = "M1: v/2Lo = v/4Lc; A1: 2 : 1.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_10_6",
                    topic = "Sound Medium",
                    examType = "JAMB",
                    year = 2020,
                    question = "In which medium does sound travel FASTEST at room temperature?",
                    options = listOf("Vacuum", "Dry air", "Fresh water", "Steel rail"),
                    correctIndex = 3,
                    hint = "Tightly packed stiff atomic bonds pass vibrations quickest.",
                    stepByStepSolution = "Sound travels fastest in stiff solids like steel (~5,000 m/s), slower in water (~1,500 m/s), slowest in air (~340 m/s).",
                  ),
                ),
            ),
            Lesson(
              id = "phys_11_optics_lenses",
              moduleId = "phys_mod_3",
              subjectId = "physics",
              title = "Optics: Mirrors, Refraction (TIR), Lenses & Eye Defects",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 7.1–7.6 • WAEC Light & Optics",
              learningOutcomes =
                listOf(
                  "Apply Snell's Law (n = sin i / sin r) and Critical Angle (sin c = 1/n).",
                  "Use Mirror/Lens equation (1/u + 1/v = 1/f) and diagnose Myopia vs Hypermetropia.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Refraction & Total Internal Reflection (Why Diamonds Sparkle)",
                    explanation =
                      "Light slows down when stepping from air into glass or water—so it bends TOWARD the normal line! Leaving glass back into air at a shallow steep angle larger than Critical Angle c bounces 100% of the light back inside (Total Internal Reflection)—powering fiber-optic internet cables and diamond sparkle!",
                    keyFormula = "n = \\frac{\\sin i}{\\sin r} = \\frac{c_{\\text{air}}}{v_{\\text{medium}}} = \\frac{\\text{Real Depth}}{\\text{Apparent Depth}}   |   \\sin c = \\frac{1}{n}",
                    examTip =
                      "Critical angle ONLY happens going from a DENSE medium (glass/water) toward a LESS dense medium (air)—never from air into glass!",
                  ),
                  ConceptBlock(
                    heading = "2. Lenses, Mirrors & Fixing Blurry Vision",
                    explanation =
                      "Magnifying glasses use Converging (Convex) lenses with f > 0. Under 'Real Is Positive', virtual images have negative v. Near-sighted (MYOPIA—can't see far) focuses too early in front of the retina -> fix with Diverging (Concave) lens. Far-sighted (HYPERMETROPIA—can't read close) focuses behind retina -> fix with Converging (Convex) lens!",
                    keyFormula = "\\frac{1}{u} + \\frac{1}{v} = \\frac{1}{f}   |   m = \\frac{v}{u}   |   P_{\\text{dioptres}} = \\frac{1}{f(\\text{m})}",
                    examTip =
                      "Convex mirrors ALWAYS give upright, diminished virtual images with wide field of view (perfect for car side mirrors!).",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Converging Lens Real Image Distance",
                    examSource = "JAMB UTME 2024 • Physics Q25",
                    problemStatement =
                      "An object sits 30 cm in front of a convex lens of focal length 10 cm. Locate image v and magnification m.",
                    steps =
                      listOf(
                        "Step 1 — 1/v = 1/f − 1/u = 1/10 − 1/30 = (3 − 1)/30 = 2/30 = 1/15.",
                        "Step 2 — v = +15 cm (Real, inverted, on opposite side).",
                        "Step 3 — m = v / u = 15 / 30 = 0.5.",
                      ),
                    finalAnswer = "v = +15 cm (Real)  |  m = 0.5",
                    examinerTrap = "For a diverging (concave) lens, ALWAYS plug negative focal length (−f)!",
                  ),
                  WorkedExample(
                    title = "Apparent Depth of Coin in Swimming Pool",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q24",
                    problemStatement =
                      "A coin sits at the bottom of a 2.0 m deep water tank (n = 1.33 ≈ 4/3). How deep does it appear from directly above?",
                    steps =
                      listOf(
                        "Step 1 — n = Real Depth / Apparent Depth.",
                        "Step 2 — Apparent Depth = 2.0 / (4/3) = 2.0 × 0.75 = 1.50 m.",
                      ),
                    finalAnswer = "1.50 m",
                    examinerTrap = "Water ALWAYS makes pool floors look SHALLOWER (Apparent < Real)!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Undersea Fiber-Optic Internet Cables",
                    industryDomain = "Telecommunications",
                    narrative =
                      "Subsea glass fibers bounce laser data pulses miles through curves using Total Internal Reflection with almost zero signal loss.",
                    examConnection = "Directly tests critical angle sin c = 1/n.",
                  ),
                ),
              labType = PracticalLabType.WAVE_OPTICS_BENCH,
              labBrief =
                "Use the Snell's Refraction & Lens Ray Bench below: bend incident angle i past Critical Angle c to trigger Total Internal Reflection!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_11_1",
                    topic = "Eye Defects",
                    examType = "JAMB",
                    year = 2024,
                    question = "Short-sightedness (Myopia), where distant objects focus in front of the retina, is corrected with a:",
                    options = listOf("Convex lens", "Concave (diverging) lens", "Cylindrical lens", "Plane mirror"),
                    correctIndex = 1,
                    hint = "Rays converge too strongly—spread them slightly before entering the eye.",
                    stepByStepSolution = "A concave (diverging) lens diverges incoming parallel rays so they focus cleanly onto the retina.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_11_2",
                    topic = "Critical Angle",
                    examType = "WAEC",
                    year = 2023,
                    question = "A glass block has refractive index n = 2.0. Find its critical angle in air.",
                    options = listOf("30°", "42°", "45°", "60°"),
                    correctIndex = 0,
                    hint = "sin(c) = 1 / n.",
                    stepByStepSolution = "sin(c) = 1 / 2.0 = 0.5 => c = 30°.",
                    waecMarkingScheme = "M1: sin c = 0.5; A1: 30°.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_11_3",
                    topic = "Convex Mirrors",
                    examType = "JAMB",
                    year = 2022,
                    question = "Why are convex mirrors fitted as vehicle side-view driving mirrors?",
                    options = listOf("They magnify distant trucks", "They give an upright image and wider field of view", "They invert upside-down rays", "They focus heat"),
                    correctIndex = 1,
                    hint = "Convex mirrors never form real inverted images.",
                    stepByStepSolution = "Convex mirrors always produce upright, diminished images covering a much wider field of view.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_11_4",
                    topic = "Lens Power",
                    examType = "JAMB",
                    year = 2025,
                    question = "Calculate the optical power (in Dioptres) of a convex lens with focal length +25 cm.",
                    options = listOf("+0.04 D", "+2.5 D", "+4.0 D", "+25 D"),
                    correctIndex = 2,
                    hint = "Convert 25 cm to 0.25 m before P = 1 / f.",
                    stepByStepSolution = "P = 1 / 0.25 m = +4.0 D.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_11_5",
                    topic = "Dispersion",
                    examType = "WAEC",
                    year = 2021,
                    question = "Passing white light through a glass prism bends which spectrum color the MOST?",
                    options = listOf("Red", "Yellow", "Green", "Violet"),
                    correctIndex = 3,
                    hint = "Shortest wavelength slows down most in glass.",
                    stepByStepSolution = "Violet has the shortest wavelength, highest refractive index in glass, and deviates most (Red deviates least).",
                    waecMarkingScheme = "A1: Violet.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_11_6",
                    topic = "Mirror Formula",
                    examType = "JAMB",
                    year = 2020,
                    question = "A concave mirror of focal length 12 cm forms a real image at 24 cm. Where is the object placed?",
                    options = listOf("8 cm", "12 cm", "24 cm", "36 cm"),
                    correctIndex = 2,
                    hint = "When v = 2f, u = 2f.",
                    stepByStepSolution = "1/u = 1/12 − 1/24 = 1/24 => u = 24 cm (at Center of Curvature C).",
                  ),
                ),
            ),
          ),
      ),
      CourseModule(
        id = "phys_mod_4",
        subjectId = "physics",
        indexLabel = "Module 04",
        title = "Electrostatics, Capacitors & Current Electricity",
        summary = "Master Coulomb charges, capacitors in series/parallel, Ohm's law, EMF internal resistance, meters & kWh electricity bills.",
        lessons =
          listOf(
            Lesson(
              id = "phys_12_electrostatics_capacitors",
              moduleId = "phys_mod_4",
              subjectId = "physics",
              title = "Electrostatics, Electric Fields & Capacitors",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 8.1–8.3 • WAEC Electrostatics",
              learningOutcomes =
                listOf(
                  "Apply Coulomb's inverse-square law F = kq₁q₂/r² and electric field E = V/d.",
                  "Combine capacitors in Series (reciprocal) vs Parallel (direct sum) and find stored energy ½CV².",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Static Sparks & Electric Push Fields",
                    explanation =
                      "Rub a plastic pen on dry hair and it picks up paper bits! Opposite charges attract; same charges push apart according to Coulomb's Law: move charges TWICE as far apart, and electric force drops FOUR times weaker (1/r²). Sharp lightning rods leak electric charge safely to earth via 'Action of Points'.",
                    keyFormula = "F = \\frac{k q_1 q_2}{r^2}   |   E = \\frac{F}{q} = \\frac{V}{d}   |   W = q V",
                    examTip =
                      "Careful! Adding resistors in SERIES adds directly (R₁+R₂), but Capacitors are OPPOSITE: PARALLEL adds directly (C = C₁ + C₂) and SERIES uses reciprocals!",
                  ),
                  ConceptBlock(
                    heading = "2. Capacitors: Tiny Fast-Charge Buckets",
                    explanation =
                      "A capacitor stores electric charge between two parallel metal plates separated by an insulator (dielectric). Bigger plate area A or better dielectric boosts Capacitance C; wider plate gap d shrinks C.",
                    keyFormula = "C = \\frac{Q}{V} = \\frac{\\varepsilon_0 A}{d}   |   U = \\frac{1}{2} C V^2 = \\frac{1}{2} Q V   |   C_{\\text{parallel}} = C_1 + C_2",
                    examTip =
                      "Inserting an insulating plastic dielectric slab between capacitor plates INCREASES capacitance C!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Series vs Parallel Capacitor Equivalence",
                    examSource = "JAMB UTME 2024 • Physics Q28",
                    problemStatement =
                      "A 3 μF and a 6 μF capacitor are connected in SERIES across a 12 V battery. Find total capacitance C_T and stored energy.",
                    steps =
                      listOf(
                        "Step 1 — Series capacitors: 1/C_T = 1/3 + 1/6 = 3/6 = 1/2 => C_T = 2.0 μF.",
                        "Step 2 — Stored energy U = ½ C_T V² = ½ × (2×10⁻⁶) × 12² = 1.44 × 10⁻⁴ J.",
                      ),
                    finalAnswer = "C_T = 2.0 μF  |  U = 1.44 × 10⁻⁴ J",
                    examinerTrap = "Always convert microfarads (μF = 10⁻⁶ F) when computing energy in Joules!",
                  ),
                  WorkedExample(
                    title = "Coulomb Distance Halving",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q27",
                    problemStatement =
                      "Two point charges repel with 16 N at distance d. What is the force if distance doubles to 2d?",
                    steps =
                      listOf(
                        "Step 1 — F ∝ 1/r².",
                        "Step 2 — Doubling distance divides force by 2² = 4: 16 / 4 = 4.0 N.",
                      ),
                    finalAnswer = "4.0 N",
                    examinerTrap = "Don't just halve 16 to 8—remember the square in r²!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Camera Flash & Defibrillator Pulse Storage",
                    industryDomain = "Electronics & Medical Tech",
                    narrative =
                      "Batteries slowly charge a flash capacitor, which then dumps all ½CV² energy in 1 millisecond to light up a bright camera flash!",
                    examConnection = "Explains Q = CV rapid discharge in WAEC electronics.",
                  ),
                ),
              labType = PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB,
              labBrief =
                "Adjust capacitor plate area A, spacing d, voltage V, and Series/Parallel switch below to observe Capacitance C, Charge Q, and stored Joules!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_12_1",
                    topic = "Capacitors",
                    examType = "JAMB",
                    year = 2024,
                    question = "Two capacitors of 4 μF and 12 μF are joined in SERIES. Find their effective capacitance.",
                    options = listOf("16 μF", "8 μF", "3 μF", "0.33 μF"),
                    correctIndex = 2,
                    hint = "Series: (4 × 12) / (4 + 12).",
                    stepByStepSolution = "48 / 16 = 3.0 μF.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_12_2",
                    topic = "Parallel Plate Capacitor",
                    examType = "WAEC",
                    year = 2023,
                    question = "Which change INCREASES the capacitance of a parallel-plate air capacitor?",
                    options = listOf("Increasing plate separation d", "Reducing plate overlap area", "Inserting a glass dielectric", "Lowering battery voltage"),
                    correctIndex = 2,
                    hint = "C = ε A / d.",
                    stepByStepSolution = "Inserting a dielectric raises permittivity ε, increasing C.",
                    waecMarkingScheme = "A1: Inserting a dielectric.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_12_3",
                    topic = "Electroscope",
                    examType = "JAMB",
                    year = 2022,
                    question = "What instrument detects whether a body carries static electric charge and tests its sign?",
                    options = listOf("Galvanometer", "Gold-leaf electroscope", "Hydrometer", "Wheatstone bridge"),
                    correctIndex = 1,
                    hint = "Leaves diverge when like charges repel.",
                    stepByStepSolution = "A gold-leaf electroscope detects charge and identifies polarity via divergence.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_12_4",
                    topic = "Electric Field",
                    examType = "WAEC",
                    year = 2024,
                    question = "Two parallel plates 0.02 m apart hold a 400 V potential difference. Find the electric field strength between them.",
                    options = listOf("8 V/m", "800 V/m", "20,000 V/m", "200 V/m"),
                    correctIndex = 2,
                    hint = "E = V / d.",
                    stepByStepSolution = "E = 400 / 0.02 = 20,000 V/m.",
                    waecMarkingScheme = "M1: 400/0.02; A1: 20,000 V/m.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_12_5",
                    topic = "Capacitor Energy",
                    examType = "JAMB",
                    year = 2025,
                    question = "A 10 μF capacitor is charged to 100 V. Calculate the energy stored.",
                    options = listOf("0.05 J", "0.50 J", "1.0 J", "50 J"),
                    correctIndex = 0,
                    hint = "U = ½ C V².",
                    stepByStepSolution = "U = 0.5 × 10⁻⁵ × 10,000 = 0.05 J.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_12_6",
                    topic = "Lightning Rods",
                    examType = "JAMB",
                    year = 2021,
                    question = "Lightning conductors installed on tall rooftops work on the principle of:",
                    options = listOf("Electromagnetic induction", "Action of sharp points (corona discharge)", "Thermionic emission", "Eddy currents"),
                    correctIndex = 1,
                    hint = "Charges concentrate heavily at sharp tips.",
                    stepByStepSolution = "Sharp points concentrate electric field, ionizing air to neutralize storm clouds quietly.",
                  ),
                ),
            ),
            Lesson(
              id = "phys_13_circuits_emf",
              moduleId = "phys_mod_4",
              subjectId = "physics",
              title = "Current Electricity, Internal Resistance & Meters",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 8.4–8.8 • WAEC Electric Circuits",
              learningOutcomes =
                listOf(
                  "Solve Series & Parallel resistor loops plus cell internal resistance E = I(R + r).",
                  "Compute electrical energy/bills in kWh and convert galvanometers into ammeters/voltmeters.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Water-Pipe Analogy for Voltage, Current & Ohm's Law",
                    explanation =
                      "Think of electrons flowing around copper wire like water flowing through pipes. Voltage V is the water-pump push, Current I is how many litres flow per second, and Resistance R is a narrow pinch in the pipe! Even a real battery has tiny internal friction r inside its chemicals, stealing a small 'lost volt' (Ir) from EMF E.",
                    keyFormula = "V = I R   |   E = I(R + r) = V + I r   |   P = IV = I^2 R = \\frac{V^2}{R}",
                    examTip =
                      "To make a Galvanometer measure huge CURRENT (Ammeter), put a tiny SHUNT resistor in PARALLEL. To measure huge VOLTAGE (Voltmeter), put a huge MULTIPLIER resistor in SERIES!",
                  ),
                  ConceptBlock(
                    heading = "2. Buying Electricity from Utility Meters (kWh)",
                    explanation =
                      "NEPA/DISCO meters charge by Kilowatt-Hours (kWh), NOT watts alone! Divide Watts by 1,000 to get Kilowatts, multiply by Hours run, then multiply by price per kWh.",
                    keyFormula = "R = \\frac{\\rho L}{A}   |   \\text{Energy (kWh)} = \\frac{\\text{Watts}}{1000} \\times \\text{Hours}",
                    examTip =
                      "Doubling a wire's LENGTH doubles resistance R; doubling its DIAMETER shrinks resistance by FOUR (1/2² = ¼) because Area = πd²/4!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Battery EMF & Internal Resistance Circuit",
                    examSource = "JAMB UTME 2024 • Physics Q30",
                    problemStatement =
                      "A cell of EMF 1.5 V and internal resistance 0.5 Ω feeds a 2.5 Ω resistor. Find circuit current I and terminal p.d. V.",
                    steps =
                      listOf(
                        "Step 1 — Total resistance = R + r = 2.5 + 0.5 = 3.0 Ω.",
                        "Step 2 — Current I = E / (R + r) = 1.5 / 3.0 = 0.5 A.",
                        "Step 3 — Terminal voltage V = I R = 0.5 × 2.5 = 1.25 V (Lost volts = 0.25 V).",
                      ),
                    finalAnswer = "I = 0.5 A  |  V = 1.25 V",
                    examinerTrap = "NEVER divide E by external R alone when internal resistance r is given!",
                  ),
                  WorkedExample(
                    title = "Monthly Heater Electricity Bill in kWh",
                    examSource = "WAEC SSCE 2023 • Paper 2 Q8",
                    problemStatement =
                      "A 2,000 W electric iron runs for 3 hours daily over 10 days at ₦60 per kWh. Calculate total electricity cost.",
                    steps =
                      listOf(
                        "Step 1 — Power = 2,000 W = 2.0 kW. Total time = 3 × 10 = 30 hours.",
                        "Step 2 — Energy = 2.0 kW × 30 h = 60 kWh.",
                        "Step 3 — Cost = 60 × ₦60 = ₦3,600.",
                      ),
                    finalAnswer = "60 kWh  |  ₦3,600",
                    examinerTrap = "Convert Watts to kW first before multiplying by hours!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Parallel Home Sockets vs Christmas Fairy Lights",
                    industryDomain = "Residential Electrical Wiring",
                    narrative =
                      "Nigerian house sockets are wired in parallel at 230 V so switching off a bedroom lamp never turns off the refrigerator!",
                    examConnection = "Tests why parallel appliances share full mains voltage.",
                  ),
                ),
              labType = PracticalLabType.OHMS_CIRCUIT,
              labBrief =
                "Toggle Series vs Parallel resistors R₁, R₂ and tune battery EMF and internal resistance r in the DC Circuit Bench below!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_13_1",
                    topic = "Resistors in Parallel",
                    examType = "JAMB",
                    year = 2024,
                    question = "Resistors of 6 Ω and 3 Ω are wired in PARALLEL. What is their equivalent resistance?",
                    options = listOf("2 Ω", "4.5 Ω", "9 Ω", "18 Ω"),
                    correctIndex = 0,
                    hint = "(6 × 3) / (6 + 3).",
                    stepByStepSolution = "18 / 9 = 2.0 Ω.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_13_2",
                    topic = "Galvanometer Conversion",
                    examType = "WAEC",
                    year = 2023,
                    question = "To convert a sensitive moving-coil galvanometer into a high-range AMMETER, connect:",
                    options = listOf("High resistance in series", "Low shunt resistance in parallel", "Capacitor in parallel", "Fuse in series"),
                    correctIndex = 1,
                    hint = "Let excess current bypass the coil.",
                    stepByStepSolution = "A low-resistance shunt placed in parallel diverts excess current around the coil.",
                    waecMarkingScheme = "A1: Low resistance (shunt) in parallel.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_13_3",
                    topic = "Wire Resistivity",
                    examType = "JAMB",
                    year = 2025,
                    question = "A wire has resistance 8 Ω. Another copper wire of the SAME length but TWICE the diameter has resistance:",
                    options = listOf("2 Ω", "4 Ω", "16 Ω", "32 Ω"),
                    correctIndex = 0,
                    hint = "R ∝ 1/d².",
                    stepByStepSolution = "Doubling diameter multiplies cross-sectional area by 4, dividing R by 4 -> 8 / 4 = 2.0 Ω.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_13_4",
                    topic = "Internal Resistance",
                    examType = "WAEC",
                    year = 2022,
                    question = "A 12 V battery of internal resistance 1 Ω feeds a 5 Ω lamp. What is the voltage drop across the 5 Ω lamp?",
                    options = listOf("2 V", "10 V", "12 V", "6 V"),
                    correctIndex = 1,
                    hint = "I = 12 / (5 + 1) = 2 A; V = I × 5.",
                    stepByStepSolution = "I = 2 A => V = 2 × 5 = 10 V.",
                    waecMarkingScheme = "M1: I = 2 A; A1: 10 V.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_13_5",
                    topic = "Potentiometer",
                    examType = "JAMB",
                    year = 2021,
                    question = "Why is a potentiometer superior to a voltmeter when measuring a cell's EMF?",
                    options = listOf("It draws zero current at balance point", "It works only on AC", "It costs less copper", "It measures temperature too"),
                    correctIndex = 0,
                    hint = "No current drawn means ZERO lost volts Ir.",
                    stepByStepSolution = "At null deflection balance point, zero current flows from the test cell so V_measured equals true EMF E.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_13_6",
                    topic = "Fuse Safety",
                    examType = "JAMB",
                    year = 2020,
                    question = "A 2,400 W electric kettle operates on 240 V mains. Which fuse rating is safest?",
                    options = listOf("5 A", "10 A", "13 A", "30 A"),
                    correctIndex = 2,
                    hint = "I = P/V = 10 A; pick the standard fuse just ABOVE normal load.",
                    stepByStepSolution = "Normal current = 2,400/240 = 10 A, so a 10 A fuse might blow on surge—use 13 A fuse.",
                  ),
                ),
            ),
          ),
      ),
    )
}
