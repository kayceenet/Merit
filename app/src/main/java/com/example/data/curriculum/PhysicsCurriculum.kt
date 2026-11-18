package com.example.data.curriculum

object PhysicsCurriculum {

  val track: SubjectTrack by lazy {
    SubjectTrack(
      id = "physics",
      title = "Physics Specialization",
      shortName = "Physics",
      subtitle = "16 Zero-to-A1 lessons covering Mechanics, Heat, Waves, Optics, Circuits & Nuclear Physics",
      modules = module1And2 + PhysicsCurriculumPart2.module3And4 + listOf(PhysicsCurriculumPart3.module5),
      pastQuestions = PhysicsCurriculumPart3.pastQuestions,
    )
  }

  val module1And2: List<CourseModule> =
    listOf(
      CourseModule(
        id = "phys_mod_1",
        subjectId = "physics",
        indexLabel = "Module 01",
        title = "Measurements, Vectors & Motion from Scratch",
        summary = "Start from zero: SI units, reading vernier/micrometers, arrows (vectors), motion graphs, projectiles, and Newton's laws.",
        lessons =
          listOf(
            Lesson(
              id = "phys_01_units",
              moduleId = "phys_mod_1",
              subjectId = "physics",
              title = "SI Units, Precision Tools & Dimensions",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 1.1–1.3 • WAEC Physical Quantities",
              learningOutcomes =
                listOf(
                  "Tell apart the 7 basic building-block units from derived units.",
                  "Check whether any formula makes sense using [M], [L], [T] dimensions.",
                  "Read Vernier Calipers (0.01 cm) and Micrometers (0.001 cm) and remove zero error.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. The Seven Fundamental Quantities & Dimensional Equations",
                    explanation =
                      "Just as all English words are formed from a fixed alphabet, every physical measurement in Physics reduces to seven independent base standards: Length l (metre, m), Mass m (kilogram, kg), Time t (second, s), Electric Current I (ampere, A), Thermodynamic Temperature T (kelvin, K), Amount of Substance n (mole, mol), and Luminous Intensity I_v (candela, cd). Derived quantities combine these base units—for example, velocity v = displacement / time (m/s), force F = mass × acceleration (kg·m/s² = N), work/energy E = force × distance (J), and power P = work / time (W). Dimensions represent Mass, Length, and Time generically as [M], [L], and [T].",
                    keyFormula = "[v] = L T^{-1}   |   [F] = M L T^{-2}   |   [E] = M L^{2} T^{-2}   |   [P] = M L^{2} T^{-3}",
                    examTip =
                      "Mass m (kg) measures quantity of matter and remains invariant anywhere; Weight W = mg (N) is gravitational pull and changes with local field strength g.",
                  ),
                  ConceptBlock(
                    heading = "2. Choosing the Right Measuring Tool & Removing Zero Error",
                    explanation =
                      "A school ruler spots changes as small as 0.1 cm (1 mm). Need to measure a bottle cap's thickness? Use a Vernier Caliper (reads to 0.01 cm). Measuring a single hair or copper wire? Use a Micrometer Screw Gauge (reads to 0.001 cm = 0.01 mm). If the tool doesn't start at zero when shut, subtract a positive zero error or add back a negative one.",
                    keyFormula = "\\text{True Reading} = \\text{Main Scale} + (\\text{Coinciding Div} \\times \\text{Least Count}) - \\text{Zero Error}",
                    examTip =
                      "JAMB favorite: Metre rule = 0.1 cm, Vernier Caliper = 0.01 cm, Micrometer Screw Gauge = 0.001 cm.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding Dimensions of Pressure and Power",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Express the dimensions of (a) Pressure and (b) Power using M, L, and T.",
                    steps =
                      listOf(
                        "Step 1 — Pressure = Force / Area. Force = Mass × Accel = M · L · T⁻².",
                        "Step 2 — Divide by Area (L²): [Pressure] = (M · L · T⁻²) / L² = M · L⁻¹ · T⁻².",
                        "Step 3 — Power = (Force × Distance) / Time = (M · L² · T⁻²) / T = M · L² · T⁻³.",
                      ),
                    finalAnswer = "Pressure: M L⁻¹ T⁻²  |  Power: M L² T⁻³",
                    examinerTrap = "Work/Energy and Turning Moment (Torque = F × d) share identical dimensions M L² T⁻²!",
                  ),
                  WorkedExample(
                    title = "Micrometer Screw Gauge with Positive Zero Error",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "A micrometer has a zero error of +0.03 mm. When measuring a wire, the sleeve shows 2.5 mm and the 34th thimble mark lines up (1 div = 0.01 mm). Find true diameter.",
                    steps =
                      listOf(
                        "Step 1 — Observed reading = 2.5 + (34 × 0.01) = 2.5 + 0.34 = 2.84 mm.",
                        "Step 2 — Subtract +0.03 mm zero error: 2.84 − 0.03 = 2.81 mm.",
                      ),
                    finalAnswer = "2.81 mm",
                    examinerTrap = "Positive zero error is SUBTRACTED; negative zero error is ADDED.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Machining Car Pistons to 0.01 mm",
                    industryDomain = "Automotive Manufacturing",
                    narrative =
                      "Engine pistons fail if they are even 0.05 mm too wide or loose. Technicians use micrometers and calipers daily to verify tolerance.",
                    examConnection = "Shows why 0.001 cm micrometer precision beats a 0.1 cm ruler.",
                  ),
                ),
              labType = PracticalLabType.MEASUREMENT_VECTOR_LAB,
              labBrief =
                "Use the Vernier & Vector Lab below to practice reading main + vernier scales with zero error and see dimensional powers live.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_01_1",
                    topic = "Units & Dimensions",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which set contains ONLY fundamental SI quantities?",
                    options = listOf("Mass, Force, Time", "Length, Mass, Electric Current", "Velocity, Time, Kelvin", "Work, Pressure, Length"),
                    correctIndex = 1,
                    hint = "Force, Velocity, Work, and Pressure are all derived.",
                    stepByStepSolution = "Length (m), Mass (kg), and Electric Current (A) are 3 of the 7 SI base quantities.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_01_2",
                    topic = "Measuring Instruments",
                    examType = "WAEC",
                    year = 2023,
                    question = "Which instrument gives the highest precision when measuring the diameter of a thin copper wire?",
                    options = listOf("Metre rule", "Vernier caliper", "Micrometer screw gauge", "Spring balance"),
                    correctIndex = 2,
                    hint = "Think smallest least count (0.001 cm).",
                    stepByStepSolution = "A micrometer screw gauge measures down to 0.01 mm (0.001 cm), 10× finer than a vernier caliper.",
                    waecMarkingScheme = "A1: Micrometer screw gauge.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_01_3",
                    topic = "Dimensions",
                    examType = "JAMB",
                    year = 2025,
                    question = "What are the dimensions of Kinetic Energy (½mv²)?",
                    options = listOf("M L T⁻²", "M L² T⁻²", "M L⁻¹ T⁻²", "M L² T⁻³"),
                    correctIndex = 1,
                    hint = "Ignore the pure number ½. Multiply M by (L/T)².",
                    stepByStepSolution = "[m][v]² = M × (L T⁻¹)² = M L² T⁻².",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_01_4",
                    topic = "Derived Units",
                    examType = "WAEC",
                    year = 2022,
                    question = "The SI unit of Momentum (kg·m/s) can also be written equivalently as:",
                    options = listOf("N·m", "N·s", "J/m", "W·s"),
                    correctIndex = 1,
                    hint = "Impulse = Force × Time = Change in momentum.",
                    stepByStepSolution = "N·s = (kg·m/s²) × s = kg·m/s.",
                    waecMarkingScheme = "A1: N·s.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_01_5",
                    topic = "Zero Error",
                    examType = "JAMB",
                    year = 2021,
                    question = "A vernier caliper with a zero error of −0.02 cm reads 3.14 cm on a metal block. What is the true length?",
                    options = listOf("3.12 cm", "3.14 cm", "3.16 cm", "3.18 cm"),
                    correctIndex = 2,
                    hint = "True = Observed − Zero Error = 3.14 − (−0.02).",
                    stepByStepSolution = "Subtracting −0.02 adds +0.02: 3.14 − (−0.02) = 3.16 cm.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_01_6",
                    topic = "Units & Dimensions",
                    examType = "NECO",
                    year = 2024,
                    question = "Which pair of physical quantities shares the exact same dimensions M L² T⁻²?",
                    options = listOf("Power and Force", "Work and Torque (Moment)", "Pressure and Impulse", "Acceleration and Velocity"),
                    correctIndex = 1,
                    hint = "Both are Force multiplied by Distance.",
                    stepByStepSolution = "Work = F·d and Torque = F·r, so both have dimensions M L² T⁻².",
                    waecMarkingScheme = "A1: Work and Torque.",
                  ),
                ),
            ),
            Lesson(
              id = "phys_02_vectors",
              moduleId = "phys_mod_1",
              subjectId = "physics",
              title = "Scalars, Vectors & Force Resolution",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 1.4 • WAEC Vectors",
              learningOutcomes =
                listOf(
                  "Sort any physics quantity as Scalar (size only) or Vector (size + direction).",
                  "Combine two forces at any angle using Pythagoras or Cosine rule.",
                  "Split an angled force F into horizontal F·cos(θ) and vertical F·sin(θ) parts.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Scalar vs Vector Quantities & Resultant Addition",
                    explanation =
                      "Giving only a magnitude such as '5 km' describes Distance—a SCALAR completely defined by size and unit (e.g., mass m, speed v, time t, energy E, temperature T, pressure P). Specifying '5 km due North' gives Displacement—a VECTOR requiring both magnitude and direction (e.g., force F, velocity, acceleration a, momentum p). Two non-parallel forces F_1 and F_2 separated by angle θ combine via the parallelogram cosine law to produce resultant magnitude R.",
                    keyFormula = "R = \\sqrt{F_1^2 + F_2^2 + 2 F_1 F_2 \\cos(\\theta)}   \\quad (\\text{At } \\theta = 90^{\\circ}:\\; R = \\sqrt{F_1^2 + F_2^2})",
                    examTip =
                      "Two equal forces of magnitude F inclined at 120° produce a resultant of exact magnitude F.",
                  ),
                  ConceptBlock(
                    heading = "2. Orthogonal Force Resolution (X and Y Components)",
                    explanation =
                      "Consider a warehouse technician towing a cargo trolley via a handle tilted at angle θ above horizontal ground. Part of the applied pull F acts horizontally forward (F_x = F cos θ, adjacent to θ) while part lifts vertically upward (F_y = F sin θ, opposite θ). For three or more coplanar forces, sum all horizontal components (ΣF_x) and all vertical components (ΣF_y) separately, then compute the resultant angle α relative to the horizontal axis.",
                    keyFormula = "F_x = F\\cos(\\theta)   |   F_y = F\\sin(\\theta)   |   \\tan(\\alpha) = \\frac{\\Sigma F_y}{\\Sigma F_x}",
                    examTip =
                      "When angle θ is measured from the vertical rather than horizontal, cos θ assigns to the vertical component and sin θ to the horizontal.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Boat Crossing a Flowing River",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A boat heads straight North across a river at 4.0 m/s while the water current pushes East at 3.0 m/s. Find the boat's actual resultant speed.",
                    steps =
                      listOf(
                        "Step 1 — North and East meet at 90°, forming a right-angled triangle.",
                        "Step 2 — R = √(4.0² + 3.0²) = √(16 + 9) = √25 = 5.0 m/s.",
                      ),
                    finalAnswer = "5.0 m/s",
                    examinerTrap = "Do NOT add 4 + 3 = 7; perpendicular velocities add via Pythagoras!",
                  ),
                  WorkedExample(
                    title = "Pulling a Suitcase at 60°",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "A traveler pulls a suitcase handle with 50 N at 60° above horizontal ground. Find the horizontal dragging force.",
                    steps =
                      listOf(
                        "Step 1 — Horizontal hugs the 60° angle: F_x = 50 × cos(60°).",
                        "Step 2 — Since cos(60°) = 0.5, F_x = 50 × 0.5 = 25 N.",
                      ),
                    finalAnswer = "25 N",
                    examinerTrap = "Don't swap cos(60°) = 0.5 with sin(60°) ≈ 0.866.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Crosswind Aircraft Landings",
                    industryDomain = "Aviation",
                    narrative =
                      "Pilots point airplane noses slightly into sideways crosswinds so the combined vector of engine thrust + wind points straight down the runway centerline.",
                    examConnection = "Identical vector triangle math used in JAMB Relative Velocity questions.",
                  ),
                ),
              labType = PracticalLabType.MEASUREMENT_VECTOR_LAB,
              labBrief =
                "Adjust Force F₁, Force F₂, and angle θ in the Vector Sandbox below to watch the resultant arrow shrink at 180° and grow at 0°!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_02_1",
                    topic = "Vectors vs Scalars",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which of the following pairs contains ONLY vector quantities?",
                    options = listOf("Speed and Mass", "Displacement and Momentum", "Energy and Weight", "Power and Temperature"),
                    correctIndex = 1,
                    hint = "Both must have magnitude AND direction.",
                    stepByStepSolution = "Displacement and Momentum (m·v) both depend on direction.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_02_2",
                    topic = "Vector Addition",
                    examType = "WAEC",
                    year = 2023,
                    question = "Forces of 5 N and 12 N act at right angles (90°) on a pin. What is the resultant force?",
                    options = listOf("7 N", "13 N", "17 N", "60 N"),
                    correctIndex = 1,
                    hint = "Use √(5² + 12²).",
                    stepByStepSolution = "R = √(25 + 144) = √169 = 13 N.",
                    waecMarkingScheme = "M1: √(5²+12²); A1: 13 N.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_02_3",
                    topic = "Equal Vectors",
                    examType = "JAMB",
                    year = 2022,
                    question = "Two equal forces of 10 N each act on a body at an angle of 120° to each other. Find their resultant.",
                    options = listOf("0 N", "10 N", "10√2 N", "20 N"),
                    correctIndex = 1,
                    hint = "Recall cos(120°) = −0.5.",
                    stepByStepSolution = "R = √(10² + 10² + 2(10)(10)(−0.5)) = √(100 + 100 − 100) = 10 N.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_02_4",
                    topic = "Vector Resolution",
                    examType = "WAEC",
                    year = 2021,
                    question = "A force of 40 N acts at 30° above the horizontal. What is its vertical upward component?",
                    options = listOf("20 N", "20√3 N", "40 N", "10 N"),
                    correctIndex = 0,
                    hint = "Vertical component is F·sin(30°).",
                    stepByStepSolution = "F_y = 40 × sin(30°) = 40 × 0.5 = 20 N.",
                    waecMarkingScheme = "A1: 20 N.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_02_5",
                    topic = "Maximum & Minimum Resultant",
                    examType = "JAMB",
                    year = 2025,
                    question = "Two forces of 9 N and 6 N act on a point. Which value CANNOT be their resultant?",
                    options = listOf("3 N", "10 N", "15 N", "18 N"),
                    correctIndex = 3,
                    hint = "Resultant must lie between |9 − 6| and 9 + 6.",
                    stepByStepSolution = "Range is 3 N to 15 N, so 18 N is impossible.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_02_6",
                    topic = "Relative Velocity",
                    examType = "NECO",
                    year = 2020,
                    question = "Two cars travel in OPPOSITE directions along a straight highway at 25 m/s and 15 m/s. Find their relative speed.",
                    options = listOf("10 m/s", "20 m/s", "40 m/s", "375 m/s"),
                    correctIndex = 2,
                    hint = "Opposite directions rush toward/away faster: add the speeds.",
                    stepByStepSolution = "v_rel = 25 − (−15) = 40 m/s.",
                  ),
                ),
            ),
            Lesson(
              id = "phys_03_kinematics",
              moduleId = "phys_mod_1",
              subjectId = "physics",
              title = "Motion Graphs, SUVAT Equations & Projectiles",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 2.1–2.3 • WAEC Kinematics",
              learningOutcomes =
                listOf(
                  "Read acceleration (slope) and distance (shaded area) off a velocity-time graph.",
                  "Pick and solve the right SUVAT equation for falling objects.",
                  "Predict projectile Time of Flight, Max Height, and Horizontal Range.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Velocity-Time Graphs: Slope = Acceleration, Area = Distance",
                    explanation =
                      "Picture a car accelerating smoothly from traffic lights. On a velocity-vs-time graph, steepness (slope = Δv/Δt) tells you how quickly it speeds up (acceleration). The total area trapped under the line—usually a triangle plus a rectangle—equals the exact distance traveled!",
                    keyFormula = "v = u + at   |   s = ut + \\frac{1}{2}at^2   |   v^2 = u^2 + 2as",
                    examTip =
                      "If a stone is 'dropped from rest', immediately set u = 0! At maximum height when thrown straight up, final vertical velocity v = 0.",
                  ),
                  ConceptBlock(
                    heading = "2. Projectiles: Two Independent Movies Playing at Once",
                    explanation =
                      "Kick a football across a field! Sideways motion has ZERO gravity pulling it, so horizontal speed u_x = u·cos θ stays constant all flight. Up-and-down motion obeys gravity g pulling downward, pausing briefly at the top (v_y = 0) before falling back down. Launch at 45° gives the farthest horizontal range!",
                    keyFormula = "T = \\frac{2u\\sin\\theta}{g}   |   H = \\frac{u^2\\sin^2\\theta}{2g}   |   R = \\frac{u^2\\sin(2\\theta)}{g}",
                    examTip =
                      "At the very highest peak of a projectile, vertical velocity v_y = 0, BUT speed is NOT zero—horizontal speed u·cos θ is still moving! Complementary angles like 30° and 60° land at the EXACT SAME range!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Braking Car Distance from v-t Area",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "A car slows uniformly from 20 m/s to a full stop in 5 s. Find deceleration and braking distance.",
                    steps =
                      listOf(
                        "Step 1 — a = (0 − 20) / 5 = −4 m/s² (deceleration = 4 m/s²).",
                        "Step 2 — Area of v-t triangle = ½ × base × height = ½ × 5 × 20 = 50 m.",
                      ),
                    finalAnswer = "Deceleration = 4 m/s² | Distance = 50 m",
                    examinerTrap = "Always report positive magnitude when asked for 'deceleration' or 'retardation'.",
                  ),
                  WorkedExample(
                    title = "Football Projectile Range & Max Height",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A ball is kicked at 40 m/s at 30° to level ground (g = 10 m/s²). Calculate Time of Flight T and Range R.",
                    steps =
                      listOf(
                        "Step 1 — T = 2u·sin(30°) / g = 2(40)(0.5) / 10 = 4.0 s.",
                        "Step 2 — u_x = 40·cos(30°) = 40(0.866) = 34.64 m/s.",
                        "Step 3 — R = u_x × T = 34.64 × 4.0 ≈ 138.6 m (or 80√3 m).",
                      ),
                    finalAnswer = "T = 4.0 s  |  R ≈ 138.6 m",
                    examinerTrap = "Don't forget the factor of 2 in T = 2u sin θ / g (up + down).",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Emergency Road Braking Safety Margins",
                    industryDomain = "Road Safety Engineering",
                    narrative =
                      "Since v² = 2as, doubling highway speed from 50 km/h to 100 km/h multiplies braking distance by FOUR (2² = 4)!",
                    examConnection = "Tests quadratic scaling of stopping distance with initial velocity.",
                  ),
                ),
              labType = PracticalLabType.PROJECTILE_KINEMATICS,
              labBrief =
                "Launch virtual projectiles below: set angle θ to 45° for maximum Range, or compare 30° vs 60° to watch them hit the same spot!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_03_1",
                    topic = "Free Fall",
                    examType = "JAMB",
                    year = 2024,
                    question = "A stone drops freely from rest from a bridge and hits water after 3 s (g = 10 m/s²). How high is the bridge?",
                    options = listOf("15 m", "30 m", "45 m", "90 m"),
                    correctIndex = 2,
                    hint = "Use s = ½gt² with u = 0.",
                    stepByStepSolution = "h = ½ × 10 × 3² = 5 × 9 = 45 m.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_03_2",
                    topic = "Projectiles",
                    examType = "WAEC",
                    year = 2023,
                    question = "Which launch angle gives the maximum horizontal range on level ground?",
                    options = listOf("30°", "45°", "60°", "90°"),
                    correctIndex = 1,
                    hint = "sin(2θ) peaks at sin(90°) = 1.",
                    stepByStepSolution = "2θ = 90° => θ = 45° maximizes R = u²sin(2θ)/g.",
                    waecMarkingScheme = "A1: 45°.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_03_3",
                    topic = "Motion Graphs",
                    examType = "JAMB",
                    year = 2022,
                    question = "The area enclosed under a velocity-time graph represents:",
                    options = listOf("Acceleration", "Displacement / Distance", "Impulse", "Force"),
                    correctIndex = 1,
                    hint = "Multiply m/s (vertical) by s (horizontal).",
                    stepByStepSolution = "v × t = displacement (distance in one direction).",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_03_4",
                    topic = "Projectiles",
                    examType = "JAMB",
                    year = 2025,
                    question = "A projectile fires at 20 m/s at 30° above ground (g = 10 m/s²). Find its maximum height.",
                    options = listOf("5 m", "10 m", "15 m", "20 m"),
                    correctIndex = 0,
                    hint = "H = (u sin 30°)² / (2g).",
                    stepByStepSolution = "u_y = 20 × 0.5 = 10 m/s; H = 10² / (2×10) = 100 / 20 = 5 m.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_03_5",
                    topic = "Equal Range Angles",
                    examType = "WAEC",
                    year = 2021,
                    question = "A cannon fired at 25° lands at range R. What other angle with the same speed hits the exact same range R?",
                    options = listOf("50°", "65°", "75°", "45°"),
                    correctIndex = 1,
                    hint = "Complementary angles sum to 90°.",
                    stepByStepSolution = "90° − 25° = 65° gives equal horizontal range.",
                    waecMarkingScheme = "A1: 65°.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_03_6",
                    topic = "SUVAT",
                    examType = "NECO",
                    year = 2020,
                    question = "A bullet enters a wood block at 100 m/s and stops after penetrating 0.25 m. Find its deceleration.",
                    options = listOf("2,000 m/s²", "10,000 m/s²", "20,000 m/s²", "40,000 m/s²"),
                    correctIndex = 2,
                    hint = "Use 0 = u² + 2as.",
                    stepByStepSolution = "a = −(100²) / (2 × 0.25) = −10,000 / 0.5 = −20,000 m/s².",
                  ),
                ),
            ),
            Lesson(
              id = "phys_04_newton_equilibrium",
              moduleId = "phys_mod_1",
              subjectId = "physics",
              title = "Newton's Laws, Momentum & Equilibrium",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 2.4–2.6 • WAEC Forces & Moments",
              learningOutcomes =
                listOf(
                  "Apply Newton's 3 laws and conservation of linear momentum to collisions.",
                  "Solve balanced see-saw beam problems using clockwise = anticlockwise moments.",
                  "Identify stable, unstable, and neutral equilibrium centers of gravity.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Newton's Laws & Why Seatbelts Matter (Momentum)",
                    explanation =
                      "Objects are stubborn—a rolling ball keeps rolling unless friction stops it (1st Law: Inertia). Push a light shopping cart vs a heavy truck with the same push: the lighter one shoots forward faster (2nd Law: F = ma). When a cannon fires a cannonball forward, the cannon kicks backward equally hard (3rd Law & Momentum Conservation)!",
                    keyFormula = "F = ma = \\frac{m(v - u)}{t}   |   m_1 u_1 + m_2 u_2 = m_1 v_1 + m_2 v_2",
                    examTip =
                      "Momentum is ALWAYS conserved in explosions & collisions with no outside push, even when objects stick together!",
                  ),
                  ConceptBlock(
                    heading = "2. Equilibrium: No Sliding AND No Tipping Over",
                    explanation =
                      "Try sitting on a see-saw with an older cousin: you sit farther from the pivot to balance their heavier weight! That turning effect is Moment = Force × perpendicular distance. For total equilibrium, total Up force = Down force AND Sum of Clockwise Moments = Sum of Anticlockwise Moments.",
                    keyFormula = "\\Sigma F = 0   \\quad\\text{AND}\\quad   \\Sigma(\\text{Clockwise Moments}) = \\Sigma(\\text{Anticlockwise Moments})",
                    examTip =
                      "Uniform metre rules balance weight W right at the 50.0 cm midpoint! Wide bases and low centers of gravity make racing cars stable.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Inelastic Coupling of Two Railway Wagons",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A 4,000 kg truck moving at 6 m/s collides and sticks to a stationary 2,000 kg car. Find their shared speed afterward.",
                    steps =
                      listOf(
                        "Step 1 — Momentum before = (4,000 × 6) + (2,000 × 0) = 24,000 kg·m/s.",
                        "Step 2 — Combined mass = 4,000 + 2,000 = 6,000 kg.",
                        "Step 3 — v = 24,000 / 6,000 = 4.0 m/s.",
                      ),
                    finalAnswer = "4.0 m/s",
                    examinerTrap = "Don't equate kinetic energy in sticky (inelastic) collisions—some energy turns into crash heat and sound!",
                  ),
                  WorkedExample(
                    title = "Finding Unknown Weight on a Uniform Metre Rule",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "A uniform metre rule balances at the 40 cm mark when a 60 N mass hangs at the 10 cm mark. Find the weight W of the rule.",
                    steps =
                      listOf(
                        "Step 1 — Weight W of uniform rule acts at 50 cm (distance from pivot 40 cm = 10 cm clockwise).",
                        "Step 2 — 60 N hangs at 10 cm (distance from pivot 40 cm = 30 cm anticlockwise).",
                        "Step 3 — W × 10 = 60 × 30 => W = 1,800 / 10 = 180 N.",
                      ),
                    finalAnswer = "180 N",
                    examinerTrap = "Always measure distances FROM THE PIVOT, not from the 0 cm end of the ruler!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Car Crumple Zones & Airbags",
                    industryDomain = "Automotive Safety",
                    narrative =
                      "Airbags lengthen crash impact time t in F = Δp / t, drastically shrinking force F on the passenger's head.",
                    examConnection = "Explains impulse-momentum questions in WAEC/JAMB.",
                  ),
                ),
              labType = PracticalLabType.TORQUE_MACHINES_LAB,
              labBrief =
                "Balance the virtual lever beam below: move left and right masses and distances from the pivot until CW = ACW torque!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_04_1",
                    topic = "Newton's Laws",
                    examType = "JAMB",
                    year = 2024,
                    question = "A 5 kg mass accelerates at 3 m/s² across a frictionless ice sheet. What net horizontal force acts on it?",
                    options = listOf("1.67 N", "8 N", "15 N", "45 N"),
                    correctIndex = 2,
                    hint = "Use F = ma.",
                    stepByStepSolution = "F = 5 × 3 = 15 N.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_04_2",
                    topic = "Recoil Momentum",
                    examType = "WAEC",
                    year = 2023,
                    question = "A 4 kg rifle fires a 0.02 kg bullet forward at 400 m/s. Find the rifle's recoil speed.",
                    options = listOf("0.5 m/s", "2.0 m/s", "4.0 m/s", "8.0 m/s"),
                    correctIndex = 1,
                    hint = "m_rifle v_rifle = m_bullet v_bullet.",
                    stepByStepSolution = "4 × v = 0.02 × 400 = 8 => v = 8 / 4 = 2.0 m/s.",
                    waecMarkingScheme = "M1: 4v = 8; A1: 2.0 m/s.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_04_3",
                    topic = "Moments",
                    examType = "JAMB",
                    year = 2022,
                    question = "A 30 N force is applied 0.4 m perpendicularly from a door hinge. Calculate the moment about the hinge.",
                    options = listOf("7.5 N·m", "12 N·m", "30.4 N·m", "75 N·m"),
                    correctIndex = 1,
                    hint = "Moment = Force × perpendicular distance.",
                    stepByStepSolution = "M = 30 × 0.4 = 12 N·m.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_04_4",
                    topic = "Stability",
                    examType = "WAEC",
                    year = 2021,
                    question = "A cone resting sideways on its curved slant surface is in:",
                    options = listOf("Stable equilibrium", "Unstable equilibrium", "Neutral equilibrium", "Dynamic imbalance"),
                    correctIndex = 2,
                    hint = "Does rolling it change the height of its center of gravity?",
                    stepByStepSolution = "Rolling a cone on its side keeps center-of-gravity height constant -> Neutral equilibrium.",
                    waecMarkingScheme = "A1: Neutral equilibrium.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_04_5",
                    topic = "Impulse",
                    examType = "JAMB",
                    year = 2025,
                    question = "A football of mass 0.5 kg initially at rest is kicked to 24 m/s in 0.04 s. Find the average kicking force.",
                    options = listOf("12 N", "120 N", "300 N", "600 N"),
                    correctIndex = 2,
                    hint = "F = m(v − u) / t.",
                    stepByStepSolution = "F = 0.5 × 24 / 0.04 = 12 / 0.04 = 300 N.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_04_6",
                    topic = "Couple",
                    examType = "NECO",
                    year = 2020,
                    question = "Two equal, opposite parallel forces separated by distance d form a:",
                    options = listOf("Resultant force", "Couple", "Centroid", "Upthrust"),
                    correctIndex = 1,
                    hint = "Used when turning a car steering wheel or water tap.",
                    stepByStepSolution = "Two equal opposite parallel forces not along the same line produce pure rotation called a Couple (Torque = F × d).",
                  ),
                ),
            ),
          ),
      ),
      CourseModule(
        id = "phys_mod_2",
        subjectId = "physics",
        indexLabel = "Module 02",
        title = "Work, Energy, Machines, SHM & Fluid Statics",
        summary = "Master energy conservation, levers/pulleys/inclines, simple harmonic oscillation, and fluid pressure/upthrust.",
        lessons =
          listOf(
            Lesson(
              id = "phys_05_work_machines",
              moduleId = "phys_mod_2",
              subjectId = "physics",
              title = "Work, Energy, Power & Simple Machines",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 3.1–3.4 • WAEC Machines",
              learningOutcomes =
                listOf(
                  "Switch between Kinetic Energy (½mv²) and Potential Energy (mgh) cleanly.",
                  "Compute Mechanical Advantage (MA), Velocity Ratio (VR), and % Efficiency.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Energy Never Disappears—It Changes Outfit",
                    explanation =
                      "Lift a mango high up a tree and it stores height energy (Potential Energy = mgh). Let go and it speeds up as height energy trades seamlessly into motion energy (Kinetic Energy = ½mv²). Carrying a heavy bag horizontally without lifting? Physics says W = F·s·cos(90°) = 0 J of gravitational work!",
                    keyFormula = "W = F s \\cos\\theta   |   KE = \\frac{1}{2}mv^2   |   PE = mgh   |   P = \\frac{W}{t} = Fv",
                    examTip =
                      "Work requires movement IN the direction of force. No displacement = ZERO physics work!",
                  ),
                  ConceptBlock(
                    heading = "2. Machines Make Lifting Easier (MA, VR & Efficiency)",
                    explanation =
                      "A ramp or pulley lets you lift a heavy motorcycle using less muscle force—at the cost of pulling a longer distance! Mechanical Advantage (MA = Load / Effort) measures force boost; Velocity Ratio (VR = Effort distance / Load distance) is set by geometry (number of pulley ropes supporting load, or 1/sin θ on a ramp).",
                    keyFormula = "MA = \\frac{L}{E}   |   VR = \\frac{d_E}{d_L}   |   \\text{Efficiency } \\eta = \\frac{MA}{VR} \\times 100\\%",
                    examTip =
                      "Real machines ALWAYS have Efficiency < 100% because friction wastes part of your pull as heat! Inclined plane VR = 1 / sin θ; Hydraulic press VR = (R/r)².",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Pulley System Efficiency",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "A block-and-tackle pulley system with 4 ropes (VR = 4) lifts an 800 N engine using 250 N effort. Find MA and Efficiency.",
                    steps =
                      listOf(
                        "Step 1 — MA = Load / Effort = 800 / 250 = 3.2.",
                        "Step 2 — Efficiency = (MA / VR) × 100% = (3.2 / 4) × 100% = 80%.",
                      ),
                    finalAnswer = "MA = 3.2  |  Efficiency = 80%",
                    examinerTrap = "Never write Efficiency > 100%—if you get 125%, you swapped Load and Effort!",
                  ),
                  WorkedExample(
                    title = "Roller Coaster Speed Conservation",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A 2 kg trolley slides from rest down a smooth 5 m high ramp (g = 10 m/s²). Find speed at bottom.",
                    steps =
                      listOf(
                        "Step 1 — Set PE_top = KE_bottom: mgh = ½mv² (mass m cancels!).",
                        "Step 2 — v = √(2gh) = √(2 × 10 × 5) = √100 = 10 m/s.",
                      ),
                    finalAnswer = "10 m/s",
                    examinerTrap = "Mass doesn't affect speed at the bottom on a frictionless ramp!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Wheelchair Ramps & Car Screw Jacks",
                    industryDomain = "Mechanical Accessibility",
                    narrative =
                      "Gentle wheelchair ramps have small angle θ, giving huge VR = 1/sin θ so little effort pushes a wheelchair upward.",
                    examConnection = "Directly models inclined plane MA/VR in WAEC.",
                  ),
                ),
              labType = PracticalLabType.TORQUE_MACHINES_LAB,
              labBrief =
                "Switch to the Machine Mode inside the Torque & Machines Bench to test inclined angle θ, pulley ropes VR, and friction efficiency!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_05_1",
                    topic = "Work & Power",
                    examType = "JAMB",
                    year = 2024,
                    question = "A crane lifts a 400 kg steel beam vertically 15 m in 20 s (g = 10 m/s²). Calculate its output power.",
                    options = listOf("300 W", "3,000 W", "6,000 W", "60,000 W"),
                    correctIndex = 1,
                    hint = "P = mgh / t.",
                    stepByStepSolution = "P = (400 × 10 × 15) / 20 = 60,000 / 20 = 3,000 W (3 kW).",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_05_2",
                    topic = "Inclined Plane",
                    examType = "WAEC",
                    year = 2023,
                    question = "An ideal frictionless plane inclined at 30° to the horizontal has a Velocity Ratio of:",
                    options = listOf("0.5", "1.5", "2.0", "√3"),
                    correctIndex = 2,
                    hint = "VR = 1 / sin(30°).",
                    stepByStepSolution = "sin(30°) = 0.5 => VR = 1 / 0.5 = 2.0.",
                    waecMarkingScheme = "A1: 2.0.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_05_3",
                    topic = "Screw Jack",
                    examType = "JAMB",
                    year = 2022,
                    question = "A screw jack handle of length r = 35 cm lifts a car with screw pitch p = 0.5 cm (π = 22/7). Find its Velocity Ratio.",
                    options = listOf("220", "440", "70", "110"),
                    correctIndex = 1,
                    hint = "VR = 2πr / p.",
                    stepByStepSolution = "VR = (2 × 22/7 × 35) / 0.5 = 220 / 0.5 = 440.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_05_4",
                    topic = "Energy",
                    examType = "WAEC",
                    year = 2024,
                    question = "If the speed of a moving motorcycle is TRIPLED, its kinetic energy increases by a factor of:",
                    options = listOf("3", "6", "9", "12"),
                    correctIndex = 2,
                    hint = "KE ∝ v².",
                    stepByStepSolution = "3² = 9 times larger.",
                    waecMarkingScheme = "A1: 9.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_05_5",
                    topic = "Machine Efficiency",
                    examType = "JAMB",
                    year = 2021,
                    question = "A 5-pulley machine operates at 80% efficiency. How much effort lifts a 1,000 N crate?",
                    options = listOf("200 N", "250 N", "400 N", "160 N"),
                    correctIndex = 1,
                    hint = "MA = Efficiency × VR = 0.80 × 5 = 4; then Effort = Load / MA.",
                    stepByStepSolution = "MA = 0.8 × 5 = 4 => E = 1,000 / 4 = 250 N.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_05_6",
                    topic = "Power",
                    examType = "NECO",
                    year = 2020,
                    question = "A car engine exerts a constant forward force of 1,500 N while cruising at 20 m/s. What power is delivered?",
                    options = listOf("75 W", "3,000 W", "30,000 W", "15,000 W"),
                    correctIndex = 2,
                    hint = "P = Force × velocity.",
                    stepByStepSolution = "P = 1,500 × 20 = 30,000 W (30 kW).",
                  ),
                ),
            ),
            Lesson(
              id = "phys_06_shm_circular",
              moduleId = "phys_mod_2",
              subjectId = "physics",
              title = "Simple Harmonic Motion (SHM) & Circular Motion",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 2.7–2.8 • WAEC Oscillations",
              learningOutcomes =
                listOf(
                  "Relate centripetal force mv²/r to circular tracks and satellites.",
                  "Calculate pendulum period T = 2π√(L/g) and max SHM speed ωA.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Circular Motion: Pulling Inward Keeps You Turning",
                    explanation =
                      "Swing a stone tied to a string around your head. Your hand pulls INWARD along the string—that inward pull is Centripetal Force! Let go, and the stone flies straight along a tangent, NOT radially outward.",
                    keyFormula = "F_c = \\frac{mv^2}{r} = m\\omega^2 r   |   v = \\omega r   |   \\omega = 2\\pi f",
                    examTip =
                      "Banking curved highways tilts the road inward so normal reaction helps provide centripetal force even on wet tarmac!",
                  ),
                  ConceptBlock(
                    heading = "2. Simple Harmonic Motion (SHM): Swing Back & Forth",
                    explanation =
                      "Watch a grandfather clock pendulum swing. At the extreme ends, velocity is ZERO and restoring force/acceleration is MAXIMUM. Passing straight through the middle, acceleration is ZERO and speed is MAXIMUM! Notice mass m does NOT change a pendulum's time period T—only length L and gravity g do!",
                    keyFormula = "a = -\\omega^2 x   |   T_{\\text{pendulum}} = 2\\pi\\sqrt{\\frac{L}{g}}   |   T_{\\text{spring}} = 2\\pi\\sqrt{\\frac{m}{k}}",
                    examTip =
                      "To DOUBLE a pendulum's period T, you must quadruple (4×) its length L, because T ∝ √L!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Pendulum Clock Length Scaling",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A simple pendulum has period 2.0 s when length is 1.0 m. What length gives period 4.0 s?",
                    steps =
                      listOf(
                        "Step 1 — T ∝ √L => T² ∝ L.",
                        "Step 2 — Doubling T (from 2 s to 4 s) multiplies L by 2² = 4.",
                        "Step 3 — L_new = 4 × 1.0 m = 4.0 m.",
                      ),
                    finalAnswer = "4.0 m",
                    examinerTrap = "Don't multiply length by 2—it scales as T²!",
                  ),
                  WorkedExample(
                    title = "Maximum Speed of Oscillating Piston",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "A particle in SHM oscillates with angular frequency ω = 10 rad/s and amplitude A = 0.05 m. Find maximum speed.",
                    steps =
                      listOf(
                        "Step 1 — Maximum speed occurs at center: v_max = ωA.",
                        "Step 2 — v_max = 10 × 0.05 = 0.50 m/s.",
                      ),
                    finalAnswer = "0.50 m/s",
                    examinerTrap = "Always convert amplitude A from cm to metres before multiplying!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Car Shock Absorbers & Damping",
                    industryDomain = "Vehicle Suspension",
                    narrative =
                      "Springs smooth bumps with SHM while hydraulic dampers stop the car from bouncing down the street for minutes afterward.",
                    examConnection = "Connects SHM spring equation T = 2π√(m/k) and energy damping.",
                  ),
                ),
              labType = PracticalLabType.SHM_FLUIDS_LAB,
              labBrief =
                "Adjust pendulum length L and gravity g in the Oscillating SHM Bench below to watch frequency f, period T, and center kinetic energy!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_06_1",
                    topic = "Simple Pendulum",
                    examType = "JAMB",
                    year = 2024,
                    question = "Doubling the bob mass on a simple pendulum without changing its string length causes its period T to:",
                    options = listOf("Double", "Halve", "Remain unchanged", "Increase by √2"),
                    correctIndex = 2,
                    hint = "Does m appear in T = 2π√(L/g)?",
                    stepByStepSolution = "Period of a simple pendulum depends only on length L and gravity g, not bob mass.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_06_2",
                    topic = "SHM Position",
                    examType = "WAEC",
                    year = 2023,
                    question = "Where in Simple Harmonic Motion is acceleration MAXIMUM and velocity ZERO?",
                    options = listOf("At equilibrium center", "At extreme amplitudes", "Halfway out", "Everywhere constant"),
                    correctIndex = 1,
                    hint = "Where does the swing stop to turn around?",
                    stepByStepSolution = "At maximum displacement ±A, v = 0 and |a| = ω²A is maximum.",
                    waecMarkingScheme = "A1: Extreme ends / maximum displacement.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_06_3",
                    topic = "Circular Motion",
                    examType = "JAMB",
                    year = 2022,
                    question = "A 2 kg mass whirls in a horizontal circle of radius 0.5 m at 4 m/s. Find centripetal force.",
                    options = listOf("8 N", "16 N", "32 N", "64 N"),
                    correctIndex = 3,
                    hint = "F = mv² / r.",
                    stepByStepSolution = "F = 2 × 4² / 0.5 = 32 / 0.5 = 64 N.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_06_4",
                    topic = "Spring SHM",
                    examType = "JAMB",
                    year = 2025,
                    question = "If frequency of oscillation is 5 Hz, what is the angular frequency ω?",
                    options = listOf("5 rad/s", "10π rad/s", "2.5π rad/s", "20π rad/s"),
                    correctIndex = 1,
                    hint = "ω = 2πf.",
                    stepByStepSolution = "ω = 2π × 5 = 10π rad/s.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_06_5",
                    topic = "Pendulum Length",
                    examType = "WAEC",
                    year = 2021,
                    question = "To triple the time period of a simple pendulum, its length must be multiplied by:",
                    options = listOf("3", "6", "9", "√3"),
                    correctIndex = 2,
                    hint = "L ∝ T².",
                    stepByStepSolution = "3² = 9 times longer.",
                    waecMarkingScheme = "A1: 9.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_06_6",
                    topic = "SHM Acceleration",
                    examType = "NECO",
                    year = 2020,
                    question = "A body executes SHM with ω = 4 rad/s. Find acceleration when displacement x = 0.25 m.",
                    options = listOf("1 m/s²", "2 m/s²", "4 m/s²", "16 m/s²"),
                    correctIndex = 2,
                    hint = "|a| = ω²x.",
                    stepByStepSolution = "a = 4² × 0.25 = 16 × 0.25 = 4 m/s².",
                  ),
                ),
            ),
            Lesson(
              id = "phys_07_fluids_elasticity",
              moduleId = "phys_mod_2",
              subjectId = "physics",
              title = "Pressure, Upthrust, Archimedes, Elasticity & Viscosity",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 3.5–4.3 • WAEC Hydrostatics",
              learningOutcomes =
                listOf(
                  "Calculate fluid pressure P = hρg and hydraulic press multiplication.",
                  "Apply Archimedes' Principle, Flotation, Hooke's Law, and Young's Modulus.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Why Broad Tires Don't Sink & Water Pushes Upward",
                    explanation =
                      "Pressing with a sharp thumbtack hurts more than pressing with your flat thumb because Pressure = Force ÷ Area. Under water, fluid squeezes harder the deeper you dive (P = hρg). Push a beach ball underwater and the water fights back upward with a lift equal to the weight of water shoved aside—that's Archimedes' Upthrust!",
                    keyFormula = "P = \\frac{F}{A} = h\\rho g   |   U = V\\rho_{\\text{fluid}}g = W_{\\text{air}} - W_{\\text{fluid}}   |   RD = \\frac{W_{\\text{air}}}{W_{\\text{air}} - W_{\\text{water}}}",
                    examTip =
                      "A floating ship displaces its OWN ENTIRE WEIGHT of water (Law of Flotation). That's why steel ships with hollow air spaces float!",
                  ),
                  ConceptBlock(
                    heading = "2. Hooke's Law, Surface Tension & Viscosity",
                    explanation =
                      "Pull a steel spring gently and stretch e grows neatly in step with load F (Hooke's Law: F = ke). Meanwhile water drops bead into spheres because surface molecules pull inward (Surface Tension—soap drops lower it!), and thick honey drips slowly because fluid layers drag against each other (Viscosity—heat lowers liquid viscosity!).",
                    keyFormula = "F = k e   |   E_{\\text{elastic}} = \\frac{1}{2} F e = \\frac{1}{2} k e^2   |   \\text{Stress} = \\frac{F}{A}, \\; \\text{Strain} = \\frac{e}{L}",
                    examTip =
                      "Heating liquid engine oil REDUCES viscosity (flows thinner), whereas heating a gas INCREASES viscosity! Hot water washes clothes better because heat & detergent slash surface tension.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Relative Density via Archimedes Loss in Weight",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A brass statue weighs 40 N in air and 35 N when completely dipped in water. Find its Relative Density.",
                    steps =
                      listOf(
                        "Step 1 — Upthrust in water = Weight loss = 40 − 35 = 5 N.",
                        "Step 2 — RD = Weight in air / Upthrust in water = 40 / 5 = 8.0.",
                      ),
                    finalAnswer = "8.0",
                    examinerTrap = "Relative density has NO units because Newtons divide out!",
                  ),
                  WorkedExample(
                    title = "Spring Elastic Potential Energy",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "A 20 N weight stretches a spring from 10.0 cm to 14.0 cm. Find work stored in the spring.",
                    steps =
                      listOf(
                        "Step 1 — Extension e = 14.0 − 10.0 = 4.0 cm = 0.04 m.",
                        "Step 2 — E = ½ F e = ½ × 20 × 0.04 = 0.40 J.",
                      ),
                    finalAnswer = "0.40 J",
                    examinerTrap = "Always subtract unstretched length from total length, AND convert cm to metres!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Hydraulic Car Brake Boosters",
                    industryDomain = "Mechanical Systems",
                    narrative =
                      "Pascal's Principle pushes tiny brake pedal force through incompressible oil onto wide caliper pistons stopping 2-ton SUVs effortlessly.",
                    examConnection = "Directly tests F₁/A₁ = F₂/A₂ in WAEC Hydrostatics.",
                  ),
                ),
              labType = PracticalLabType.SHM_FLUIDS_LAB,
              labBrief =
                "Switch to Archimedes Fluid Mode below: change object density vs liquid density to watch whether the block floats or sinks, and read live Upthrust!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_phy_07_1",
                    topic = "Hydrostatic Pressure",
                    examType = "JAMB",
                    year = 2024,
                    question = "Find the water pressure at the bottom of a 5 m deep swimming pool (ρ = 1,000 kg/m³, g = 10 m/s²).",
                    options = listOf("500 Pa", "5,000 Pa", "50,000 Pa", "500,000 Pa"),
                    correctIndex = 2,
                    hint = "P = hρg.",
                    stepByStepSolution = "P = 5 × 1,000 × 10 = 50,000 Pa.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_07_2",
                    topic = "Archimedes Principle",
                    examType = "WAEC",
                    year = 2023,
                    question = "An object weighs 12 N in air, 8 N in water, and 9 N in oil. Find the relative density of the oil.",
                    options = listOf("0.75", "1.33", "0.67", "1.50"),
                    correctIndex = 0,
                    hint = "RD_oil = Upthrust in oil / Upthrust in water.",
                    stepByStepSolution = "U_oil = 12 − 9 = 3 N; U_water = 12 − 8 = 4 N => RD = 3 / 4 = 0.75.",
                    waecMarkingScheme = "M1: 3/4; A1: 0.75.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_07_3",
                    topic = "Hooke's Law",
                    examType = "JAMB",
                    year = 2022,
                    question = "A spring of constant k = 500 N/m stretches by 0.04 m. Calculate the stored elastic energy.",
                    options = listOf("0.2 J", "0.4 J", "0.8 J", "10 J"),
                    correctIndex = 1,
                    hint = "E = ½ke².",
                    stepByStepSolution = "E = ½ × 500 × (0.04)² = 250 × 0.0016 = 0.40 J.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_07_4",
                    topic = "Surface Tension",
                    examType = "WAEC",
                    year = 2024,
                    question = "Which action lowers the surface tension of water so soap cleans greasy clothes effectively?",
                    options = listOf("Adding salt", "Adding detergent and heating", "Cooling to 0°C", "Increasing container depth"),
                    correctIndex = 1,
                    hint = "Soaps and higher temperatures weaken surface hydrogen bonds.",
                    stepByStepSolution = "Detergents and raising temperature reduce surface tension.",
                    waecMarkingScheme = "A1: Adding detergent and heating.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_07_5",
                    topic = "Pascal Hydraulic Press",
                    examType = "JAMB",
                    year = 2025,
                    question = "In a hydraulic lift, small piston area is 0.01 m² and large piston area is 0.50 m². A 40 N force on the small piston lifts what weight?",
                    options = listOf("200 N", "500 N", "2,000 N", "4,000 N"),
                    correctIndex = 2,
                    hint = "F₂ = F₁ × (A₂/A₁).",
                    stepByStepSolution = "F₂ = 40 × (0.50 / 0.01) = 40 × 50 = 2,000 N.",
                  ),
                  PracticeQuestion(
                    id = "ex_phy_07_6",
                    topic = "Flotation",
                    examType = "NECO",
                    year = 2021,
                    question = "An ice block of density 900 kg/m³ floats in water of density 1,000 kg/m³. What fraction is submerged?",
                    options = listOf("10%", "80%", "90%", "100%"),
                    correctIndex = 2,
                    hint = "Fraction submerged = ρ_object / ρ_liquid.",
                    stepByStepSolution = "900 / 1,000 = 0.90 = 90% submerged (10% visible above water).",
                  ),
                ),
            ),
          ),
      ),
    )
}
