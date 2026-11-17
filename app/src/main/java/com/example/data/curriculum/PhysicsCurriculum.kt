package com.example.data.curriculum

object PhysicsCurriculum {

  val track =
    SubjectTrack(
      id = "physics",
      title = "Physics Specialization",
      shortName = "Physics",
      tagline = "Zero-to-A1 mastery across Mechanics, Thermal Physics, Waves, Optics, Circuits & Nuclear Physics",
      examCoverage = "JAMB 40 Qs • WAEC Paper 1, 2 & Practical",
      modules =
        listOf(
          CourseModule(
            id = "phys_mod_1",
            subjectId = "physics",
            indexLabel = "Module 01",
            title = "Foundations of Measurement & Kinematics",
            summary = "Start from zero: physical quantities, dimensional checks, vectors, motion graphs, and 2D projectiles.",
            lessons =
              listOf(
                Lesson(
                  id = "phys_01_units",
                  moduleId = "phys_mod_1",
                  subjectId = "physics",
                  title = "Measurements, Dimensions & Vectors from Scratch",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. 1.1–1.4 • WAEC Quantities",
                  learningOutcomes =
                    listOf(
                      "Distinguish the 7 fundamental SI base quantities from derived quantities.",
                      "Verify equation validity using dimensional analysis ([M], [L], [T]).",
                      "Separate scalar quantities from vectors and resolve angled vectors into X/Y components.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1 (Zero Knowledge): What Is a Physical Quantity?",
                        explanation =
                          "Physics measures the universe using numbers paired with units. Just 7 fundamental building blocks—Length (metre, m), Mass (kilogram, kg), Time (second, s), Electric Current (ampere, A), Temperature (kelvin, K), Amount of Substance (mole, mol), and Luminous Intensity (candela, cd)—combine to form every derived quantity like speed (m/s), force (kg·m/s² = N), and energy (kg·m²/s² = J).",
                        keyFormula = "[Speed] = L T^{-1}   |   [Force] = M L T^{-2}   |   [Work / Energy] = M L^{2} T^{-2}",
                        examTip =
                          "Never confuse Weight (a Force measured in Newtons, N) with Mass (amount of matter in kg). Also, Kelvin (K) and Ampere (A) are fundamental; Coulomb (C = A·s) and Newton (N) are derived!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Precision Instruments — Vernier vs Micrometer",
                        explanation =
                          "A metre rule reads to 0.1 cm (1 mm). To measure a cylinder diameter accurately, a Vernier Caliper achieves 0.01 cm (0.1 mm) precision. To measure a thin wire or paper sheet, a Micrometer Screw Gauge reaches 0.001 cm (0.01 mm). Always subtract zero error carefully!",
                        keyFormula = "Total Reading = Main Scale + (Vernier/Thimble Division × Least Count) − Zero Error",
                        examTip =
                          "JAMB frequently contrasts precision limits: Metre rule = 0.1 cm, Vernier Caliper = 0.01 cm, Micrometer Screw Gauge = 0.001 cm.",
                      ),
                      ConceptBlock(
                        heading = "Step 3: Scalars vs Vectors & Orthogonal Resolution",
                        explanation =
                          "A scalar has magnitude only (mass, speed, distance, energy, time). A vector has BOTH magnitude AND direction (displacement, velocity, force, momentum, electric field). A vector F tilted at angle θ to the horizontal splits cleanly into perpendicular components so horizontal and vertical effects can be added separately.",
                        keyFormula = "F_x = F·cos(\\theta)   |   F_y = F·sin(\\theta)   |   R = \\sqrt{R_x^2 + R_y^2}",
                        examTip =
                          "Two forces of equal magnitude F acting at 120° to each other produce a resultant of magnitude F! When θ = 0° (same direction) R = F₁ + F₂; when θ = 180° (opposite) R = |F₁ − F₂|.",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Dimensional Analysis of Power & Pressure",
                        examSource = "JAMB UTME 2023 • Physics Q2",
                        problemStatement =
                          "Express the dimensions of (a) Pressure and (b) Power in terms of M, L, and T.",
                        steps =
                          listOf(
                            "Step 1 — Pressure = Force / Area. Force = Mass × Acceleration = M × L·T⁻² = M·L·T⁻².",
                            "Step 2 — Divide by Area (L²): [Pressure] = (M·L·T⁻²) / L² = M·L⁻¹·T⁻².",
                            "Step 3 — Power = Work / Time = (Force × Distance) / Time = (M·L·T⁻² × L) / T = M·L²·T⁻³.",
                          ),
                        finalAnswer = "Pressure: M L⁻¹ T⁻²  |  Power: M L² T⁻³",
                        examinerTrap =
                          "Energy and Moment of a Force (Torque = F × d) share the exact same dimension M L² T⁻², even though Torque is a vector and Energy is a scalar.",
                      ),
                      WorkedExample(
                        title = "Resultant of Two Perpendicular Forces",
                        examSource = "WAEC SSCE 2022 • Paper 2 Q1",
                        problemStatement =
                          "Forces of 6 N due East and 8 N due North act simultaneously on a 2 kg block. Calculate the magnitude and direction of the resultant acceleration.",
                        steps =
                          listOf(
                            "Step 1 — Since East and North are 90° apart, resultant force R = √(6² + 8²) = √(36 + 64) = √100 = 10 N.",
                            "Step 2 — Direction bearing θ North of East: tan(θ) = 8/6 = 1.333 => θ = 53.1° North of East (bearing 036.9°).",
                            "Step 3 — Newton's 2nd Law: a = R / m = 10 / 2 = 5.0 m/s².",
                          ),
                        finalAnswer = "5.0 m/s² at 53.1° N of E",
                        examinerTrap =
                          "Do NOT add vector magnitudes arithmetically (6 + 8 = 14 is wrong unless θ = 0°).",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "River Ferry Crossing & Wind Drift Navigation",
                        industryDomain = "Marine & Aviation Navigation",
                        narrative =
                          "When a ferry steers straight across the River Niger perpendicular to a fast downstream current, the boat moves diagonally. Vector addition combines engine velocity and river current to pinpoint the true landing dock.",
                        examConnection =
                          "Tests vector triangle addition (R² = v_boat² + v_river²) in JAMB Kinematics.",
                      ),
                      RealLifeScenario(
                        title = "Bridge Cable Tension Engineering",
                        industryDomain = "Structural Civil Engineering",
                        narrative =
                          "Suspension bridge cables on the Third Mainland Bridge carry loads at angles; engineers resolve tension T into vertical support T·sinθ to prevent deck collapse.",
                        examConnection =
                          "Explains why steeper cables carry smaller tension for the same vertical load.",
                      ),
                    ),
                  labType = PracticalLabType.PROJECTILE_KINEMATICS,
                  labBrief =
                    "Use the simulator below to observe how a single launch vector u splits immediately into horizontal u_x = u·cos(θ) and vertical u_y = u·sin(θ) before changing.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_phys_u1",
                        topic = "Units & Dimensions",
                        examType = "JAMB",
                        year = 2024,
                        question = "Which of the following sets contains ONLY fundamental SI quantities?",
                        options =
                          listOf(
                            "Mass, Force, Time",
                            "Length, Mass, Electric Current",
                            "Velocity, Time, Kelvin",
                            "Work, Pressure, Length",
                          ),
                        correctIndex = 1,
                        hint = "Electric current (Ampere) is fundamental, whereas Force, Velocity, and Work are derived.",
                        stepByStepSolution =
                          "The 7 SI base quantities include Length (m), Mass (kg), and Electric Current (A). Force, Velocity, Work, and Pressure are derived.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_u2",
                        topic = "Vectors",
                        examType = "WAEC",
                        year = 2023,
                        question =
                          "Two forces of magnitude 8 N and 8 N act at an angle of 120° to each other. What is the magnitude of their resultant?",
                        options = listOf("0 N", "8 N", "8√2 N", "16 N"),
                        correctIndex = 1,
                        hint = "Use R² = P² + Q² + 2PQ·cos(120°), noting cos(120°) = −0.5.",
                        stepByStepSolution =
                          "R² = 8² + 8² + 2(8)(8)(−0.5) = 64 + 64 − 64 = 64 => R = 8 N.",
                        waecMarkingScheme = "M1: R² = P²+Q²+2PQcos120°; A1: 8 N.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_u3",
                        topic = "Measurement Precision",
                        examType = "JAMB",
                        year = 2022,
                        question =
                          "Which instrument is most suitable for measuring the diameter of a thin copper wire accurately to 0.001 cm?",
                        options = listOf("Metre rule", "Vernier caliper", "Micrometer screw gauge", "Tape measure"),
                        correctIndex = 2,
                        hint = "Micrometer least count is 0.01 mm = 0.001 cm.",
                        stepByStepSolution =
                          "A micrometer screw gauge has a reading accuracy of 0.01 mm (0.001 cm), 10× finer than a Vernier caliper.",
                      ),
                    ),
                ),
                Lesson(
                  id = "phys_02_kinematics",
                  moduleId = "phys_mod_1",
                  subjectId = "physics",
                  title = "Linear Kinematics, Motion Graphs & Equations",
                  durationMinutes = 22,
                  syllabusTag = "JAMB Sec. 2.1 • WAEC Linear Motion",
                  learningOutcomes =
                    listOf(
                      "Interpret Displacement–Time (s–t) and Velocity–Time (v–t) slopes and areas.",
                      "Select the exact SUVAT equation for any uniformly accelerated problem.",
                      "Solve vertical free-fall and deceleration braking-distance problems cleanly.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Speed vs Velocity & Motion Graphs",
                        explanation =
                          "Distance is total ground covered (scalar); Displacement s is shortest straight-line change in position (vector). On a Displacement–Time graph, the GRADIENT equals velocity. On a Velocity–Time graph, the GRADIENT equals acceleration a, while the AREA UNDER THE GRAPH equals total displacement s!",
                        keyFormula = "Slope(v–t) = a = \\frac{v - u}{t}   |   Area(v–t) = s",
                        examTip =
                          "When a car returns to its starting point, total displacement = 0 and average velocity = 0, even though total distance and average speed are non-zero!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Deriving the Three SUVAT Equations",
                        explanation =
                          "From constant acceleration a = (v − u)/t we rearrange to get (1) v = u + at. The area of the trapezium under a v–t graph is s = ½(u + v)t; substituting v = u + at yields (2) s = ut + ½at². Eliminating t gives (3) v² = u² + 2as.",
                        keyFormula = "v = u + at   |   s = ut + \\frac{1}{2}at^2   |   v^2 = u^2 + 2as",
                        examTip =
                          "Read phrases like code words: 'starts from rest' means u = 0; 'comes to rest' means v = 0; 'decelerates / retards' means a is NEGATIVE.",
                      ),
                      ConceptBlock(
                        heading = "Step 3: Vertical Motion Under Gravity",
                        explanation =
                          "Near Earth's surface all objects fall in vacuum with constant downward acceleration g ≈ 10 m/s² (or 9.8 m/s²). If thrown upward, take upward as positive so a = −g, and v = 0 at the highest point.",
                        keyFormula = "h_{max} = \\frac{u^2}{2g}   |   t_{up} = \\frac{u}{g}",
                        examTip =
                          "Air resistance ignored, the time to rise equals the time to fall back, and impact speed equals launch speed!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Trapezium Velocity–Time Graph Braking Problem",
                        examSource = "WAEC SSCE 2023 • Paper 2 Q2",
                        problemStatement =
                          "A train accelerates uniformly from rest to 20 m/s in 10 s, cruises at 20 m/s for 30 s, then decelerates uniformly to rest in 5 s. Find (a) deceleration and (b) total distance.",
                        steps =
                          listOf(
                            "Step 1 — Deceleration in last 5 s: a = (0 − 20) / 5 = −4.0 m/s² (retardation = 4.0 m/s²).",
                            "Step 2 — Total time at top = 30 s; total base time = 10 + 30 + 5 = 45 s; height = 20 m/s.",
                            "Step 3 — Area of trapezium: s = ½ × (top + base) × height = ½ × (30 + 45) × 20 = 75 × 10 = 750 m.",
                          ),
                        finalAnswer = "Deceleration = 4.0 m/s², Total distance = 750 m",
                        examinerTrap =
                          "Students confuse '30 s cruise time' with 'total time 30 s'. Always draw a quick v–t trapezium sketch!",
                      ),
                      WorkedExample(
                        title = "Emergency Braking Distance (Time-Independent SUVAT)",
                        examSource = "JAMB UTME 2022 • Physics Q7",
                        problemStatement =
                          "A car traveling at 30 m/s brakes uniformly to stop in 90 m. Calculate the retardation.",
                        steps =
                          listOf(
                            "Step 1 — Given u = 30 m/s, v = 0, s = 90 m, find a (no time t given => use v² = u² + 2as).",
                            "Step 2 — 0² = 30² + 2a(90) => 180a = −900 => a = −5.0 m/s².",
                          ),
                        finalAnswer = "5.0 m/s² retardation",
                        examinerTrap =
                          "Braking distance scales with u²! Doubling speed from 30 to 60 m/s quadruples stopping distance (4×).",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Highway Safe Following Distance",
                        industryDomain = "Transport Safety",
                        narrative =
                          "Because stopping distance s = u²/(2a) grows with the SQUARE of speed, driving at 100 km/h instead of 50 km/h demands 4× longer braking distance, not 2×.",
                        examConnection = "Explains non-linear braking questions in JAMB Mechanics.",
                      ),
                      RealLifeScenario(
                        title = "Lagos–Ibadan Express Rail Acceleration Profile",
                        industryDomain = "Railway Engineering",
                        narrative =
                          "Engineers limit train acceleration and deceleration to comfortable values (~0.8 m/s²) using v–t trapezium profiles between stations.",
                        examConnection = "Direct match for 3-stage v–t trapezium distance calculations.",
                      ),
                    ),
                  labType = PracticalLabType.PROJECTILE_KINEMATICS,
                  labBrief =
                    "Set launch angle close to 85° (nearly straight up) in the lab to watch pure vertical acceleration under gravity g = 10 m/s² reach peak height H = u_y²/(2g).",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_phys_k1",
                        topic = "Kinematics",
                        examType = "JAMB",
                        year = 2024,
                        question =
                          "A body accelerates uniformly from rest at 4 m/s² for 5 s. Calculate the distance covered in the 5th second ONLY.",
                        options = listOf("18 m", "32 m", "50 m", "100 m"),
                        correctIndex = 0,
                        hint = "Compute s(5 s) − s(4 s), or use s_n = u + a(n − ½).",
                        stepByStepSolution =
                          "s₅ = ½(4)(5²) = 50 m; s₄ = ½(4)(4²) = 32 m. Distance in 5th second = 50 − 32 = 18 m.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_k2",
                        topic = "Kinematics",
                        examType = "WAEC",
                        year = 2022,
                        question = "What physical quantity does the area under a velocity–time graph represent?",
                        options = listOf("Acceleration", "Displacement", "Momentum", "Jerk"),
                        correctIndex = 1,
                        hint = "Area = velocity × time.",
                        stepByStepSolution =
                          "Multiplying vertical axis (m/s) by horizontal axis (s) gives metres (m), representing Displacement.",
                        waecMarkingScheme = "A1: Displacement (distance in a specified direction).",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_k3",
                        topic = "Free Fall",
                        examType = "JAMB",
                        year = 2023,
                        question =
                          "A stone is projected vertically upward with a speed of 30 m/s. How long does it take to return to the thrower's hand? (g = 10 m/s²)",
                        options = listOf("1.5 s", "3.0 s", "6.0 s", "9.0 s"),
                        correctIndex = 2,
                        hint = "Total time = 2 × time to reach maximum height (t_up = u/g).",
                        stepByStepSolution = "t_up = u/g = 30/10 = 3.0 s. Total return time T = 2 × 3.0 = 6.0 s.",
                      ),
                    ),
                ),
                Lesson(
                  id = "phys_03_projectiles",
                  moduleId = "phys_mod_1",
                  subjectId = "physics",
                  title = "2D Projectile Motion & Trajectory Synthesis",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. 2.2 • WAEC Projectiles",
                  learningOutcomes =
                    listOf(
                      "Combine Lesson 1 vectors with Lesson 2 SUVAT equations to analyze 2D parabolic motion.",
                      "Derive Time of Flight (T), Maximum Height (H), and Range (R) from scratch.",
                      "Solve horizontal cliff-drop and complementary-angle problems without errors.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Building 2D Motion from Lessons 1 & 2",
                        explanation =
                          "Combine Lesson 1 (resolving velocity u into u_x = u·cosθ and u_y = u·sinθ) with Lesson 2 (1D SUVAT equations)! Horizontally, ax = 0 so x = (u·cosθ)t forever. Vertically, ay = −g so vertical motion behaves identically to throwing a ball straight up at speed u_y = u·sinθ!",
                        keyFormula = "u_x = u·cos(\\theta)   |   u_y = u·sin(\\theta)   |   v_{peak} = u·cos(\\theta)",
                        examTip =
                          "At the highest point of flight, vertical velocity v_y = 0, NOT total velocity! Total speed at peak is u·cos(θ).",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Time of Flight, Max Height & Horizontal Range",
                        explanation =
                          "Setting v_y = 0 in v = u + at gives peak time t_p = u·sinθ / g, so level-ground Time of Flight T = 2u·sinθ / g. Plugging u_y into v² = u² − 2gH gives H = u²sin²θ / (2g). Multiplying u_x by T uses the identity 2sinθcosθ = sin2θ to yield Range R.",
                        keyFormula = "T = \\frac{2u\\sin\\theta}{g}   |   H = \\frac{u^2\\sin^2\\theta}{2g}   |   R = \\frac{u^2\\sin 2\\theta}{g}",
                        examTip =
                          "Maximum range R_max = u²/g occurs at θ = 45°! Complementary angles like 30° and 60° always share the same horizontal Range.",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Ground-to-Ground Trajectory Analysis",
                        examSource = "JAMB UTME 2021 • Physics Q14",
                        problemStatement =
                          "A mortar shell is fired at 40 m/s at 30° to the horizontal (g = 10 m/s²). Find (a) T, (b) H, and (c) R.",
                        steps =
                          listOf(
                            "Step 1 — Resolve: u_y = 40·sin30° = 20 m/s; u_x = 40·cos30° = 34.64 m/s.",
                            "Step 2 — Time of flight: T = 2u_y / g = 40 / 10 = 4.0 s.",
                            "Step 3 — Max height: H = u_y² / (2g) = 400 / 20 = 20.0 m.",
                            "Step 4 — Range: R = u_x × T = 34.64 × 4.0 = 138.6 m (80√3 m).",
                          ),
                        finalAnswer = "T = 4.0 s, H = 20.0 m, R = 80√3 m",
                        examinerTrap =
                          "Never plug total u = 40 into H = u²/(2g); always use the vertical component u·sinθ!",
                      ),
                      WorkedExample(
                        title = "Horizontal Cliff / Drone Drop",
                        examSource = "WAEC SSCE 2020 • Paper 2 Q3",
                        problemStatement =
                          "A drone flying horizontally at 25 m/s drops a relief box from 80 m height (g = 10 m/s²). Find horizontal distance traveled before impact.",
                        steps =
                          listOf(
                            "Step 1 — Horizontal release implies u_y = 0, so h = ½gt² => 80 = 5t² => t² = 16 => t = 4.0 s.",
                            "Step 2 — Horizontal travel: R = u_x × t = 25 × 4.0 = 100 m.",
                          ),
                        finalAnswer = "100 m",
                        examinerTrap =
                          "Do not use T = 2u·sinθ/g when launched horizontally from a height; vertical drop starts from rest.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Flood Relief Drone Airdrops in Riverine Communities",
                        industryDomain = "Aerospace & Humanitarian Logistics",
                        narrative =
                          "Delivery drones compute exact release distance ahead of target zones using h = ½gt² and R = u_x·t so relief supplies land accurately without hovering.",
                        examConnection = "Classic horizontal projection formula test in WAEC Theory.",
                      ),
                      RealLifeScenario(
                        title = "Football Free-Kick Curve & Elevation Angle",
                        industryDomain = "Sports Biomechanics",
                        narrative =
                          "Strikers balance loft angle θ and strike speed u to clear a defensive wall (Height H) while landing below the crossbar.",
                        examConnection = "Connects parabolic symmetry H vs R.",
                      ),
                    ),
                  labType = PracticalLabType.PROJECTILE_KINEMATICS,
                  labBrief =
                    "Set u = 40 m/s in the lab below: compare 30° vs 60° (same Range, different H!), then verify 45° achieves maximum Range.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_phys_proj_1",
                        topic = "Projectile Motion",
                        examType = "JAMB",
                        year = 2024,
                        question = "A ball is projected at 30° to the horizontal with speed 60 m/s. What is its speed at maximum height?",
                        options = listOf("0 m/s", "30 m/s", "30√3 m/s", "60 m/s"),
                        correctIndex = 2,
                        hint = "At peak, v_y = 0; only u_x = u·cos30° remains.",
                        stepByStepSolution = "v_peak = 60·cos(30°) = 60 × (√3/2) = 30√3 m/s.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_proj_2",
                        topic = "Projectile Motion",
                        examType = "WAEC",
                        year = 2021,
                        question = "Two projectiles launched at speed u with angles 25° and 65° share the same:",
                        options = listOf("Maximum height", "Time of flight", "Horizontal range", "Vertical velocity"),
                        correctIndex = 2,
                        hint = "Notice 25° + 65° = 90° (complementary angles).",
                        stepByStepSolution = "sin(2×25°) = sin(50°) = sin(130°) = sin(2×65°), yielding equal Horizontal Range.",
                        waecMarkingScheme = "M1: 25°+65° = 90°; A1: Equal Horizontal Range.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_proj_3",
                        topic = "Projectile Motion",
                        examType = "JAMB",
                        year = 2023,
                        question = "A particle is projected with speed 20 m/s at 45°. What is its maximum horizontal range? (g = 10 m/s²)",
                        options = listOf("20 m", "40 m", "60 m", "80 m"),
                        correctIndex = 1,
                        hint = "At 45°, R_max = u² / g.",
                        stepByStepSolution = "R_max = 20² / 10 = 400 / 10 = 40 m.",
                      ),
                    ),
                ),
              ),
          ),
          CourseModule(
            id = "phys_mod_2",
            subjectId = "physics",
            indexLabel = "Module 02",
            title = "Dynamics, Energy, Fluids & Thermal Physics",
            summary = "Progress from kinematics to forces, momentum conservation, work-energy, fluid pressure, and heat.",
            lessons =
              listOf(
                Lesson(
                  id = "phys_04_momentum",
                  moduleId = "phys_mod_2",
                  subjectId = "physics",
                  title = "Newton's Laws, Impulse & Linear Momentum",
                  durationMinutes = 19,
                  syllabusTag = "JAMB Sec. 2.4 • WAEC Dynamics",
                  learningOutcomes =
                    listOf(
                      "Apply Newton's Three Laws and F = Δp/Δt to dynamic bodies and elevators.",
                      "Solve head-on elastic, inelastic, and recoil momentum problems using sign conventions.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Inertia, Net Force & Action–Reaction",
                        explanation =
                          "Newton's 1st Law explains inertia (why passengers lurch forward when brakes slam). Newton's 2nd Law links net external force F_net to momentum rate of change: F_net = (mv − mu)/t = ma. Newton's 3rd Law pairs equal, opposite forces acting on TWO DIFFERENT bodies (e.g., gun pushes bullet forward; bullet pushes gun backward).",
                        keyFormula = "F\\cdot \\Delta t = m(v - u)   |   F_{net} = ma",
                        examTip =
                          "Impulse (N·s) has the exact same units and dimensions as Momentum (kg·m/s)!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Conservation of Linear Momentum & Recoil",
                        explanation =
                          "If no net external force acts along the collision line, total momentum before impact equals total momentum after impact. Assign + to Right/East and − to Left/West before writing a single number!",
                        keyFormula = "m_1 u_1 + m_2 u_2 = m_1 v_1 + m_2 v_2   |   M V_{recoil} = -m v_{bullet}",
                        examTip =
                          "When objects 'coalesce / stick together', they move at one shared velocity v: m₁u₁ + m₂u₂ = (m₁ + m₂)v.",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Opposite-Direction Inelastic Collision",
                        examSource = "WAEC SSCE 2022 • Paper 2 Q4",
                        problemStatement =
                          "A 2 kg mass moving East at 5 m/s collides head-on and sticks to a 3 kg mass moving West at 2 m/s. Find their common velocity.",
                        steps =
                          listOf(
                            "Step 1 — Sign convention: East = (+), West = (−).",
                            "Step 2 — Initial momentum: p_i = (2 × +5) + (3 × −2) = 10 − 6 = +4 kg·m/s.",
                            "Step 3 — Common velocity: (2 + 3)v = 4 => v = +0.8 m/s (East).",
                          ),
                        finalAnswer = "0.8 m/s East",
                        examinerTrap = "Forgetting the minus sign on West velocity gives 16/5 = 3.2 m/s (wrong!).",
                      ),
                      WorkedExample(
                        title = "Rifle Recoil Speed Calculation",
                        examSource = "JAMB UTME 2023 • Physics Q11",
                        problemStatement =
                          "A 4 kg rifle fires a 20 g bullet at 400 m/s. Find the recoil velocity of the rifle.",
                        steps =
                          listOf(
                            "Step 1 — Convert 20 g to kg: m = 0.020 kg.",
                            "Step 2 — Total initial momentum = 0, so M·V + m·v = 0.",
                            "Step 3 — 4V + (0.02 × 400) = 0 => 4V = −8 => V = −2.0 m/s (2.0 m/s backward).",
                          ),
                        finalAnswer = "2.0 m/s opposite to bullet",
                        examinerTrap = "Always convert grams to kilograms (divide by 1000) before multiplying!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Airbags, Crumple Zones & Soft-Catch Technique",
                        industryDomain = "Automotive Crash Physics",
                        narrative =
                          "Airbags lengthen impact duration Δt by 15× for the same momentum drop Δp, reducing peak passenger impact force F = Δp/Δt to survivable levels.",
                        examConnection = "Favorite WAEC Theory short-essay question on impulse.",
                      ),
                      RealLifeScenario(
                        title = "Spacecraft Rocket Thruster Propulsion",
                        industryDomain = "Aerospace Propulsion",
                        narrative =
                          "In space vacuum there is no air to push against; rockets accelerate forward by ejecting hot exhaust mass backward via momentum conservation.",
                        examConnection = "Tests Newton's 3rd Law and variable-mass momentum rate.",
                      ),
                    ),
                  labType = PracticalLabType.PROJECTILE_KINEMATICS,
                  labBrief =
                    "Observe vertical velocity reversal u_y -> −u_y in the kinematics plotter, modeling momentum change Δp = 2mu_y during elastic bounce.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_phys_mom_1",
                        topic = "Linear Momentum",
                        examType = "JAMB",
                        year = 2024,
                        question = "A 50 N force acts on a 4 kg body initially at rest for 0.8 s. Calculate the final velocity.",
                        options = listOf("5 m/s", "10 m/s", "20 m/s", "40 m/s"),
                        correctIndex = 1,
                        hint = "Impulse F·t = m(v − 0).",
                        stepByStepSolution = "50 × 0.8 = 4v => 40 = 4v => v = 10 m/s.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_mom_2",
                        topic = "Linear Momentum",
                        examType = "WAEC",
                        year = 2021,
                        question = "Which quantity is conserved in BOTH elastic and inelastic isolated collisions?",
                        options = listOf("Kinetic energy", "Linear momentum", "Mechanical energy", "Speed"),
                        correctIndex = 1,
                        hint = "Heat and sound consume kinetic energy in inelastic impacts.",
                        stepByStepSolution = "Total linear momentum is always conserved when no external force acts, regardless of elasticity.",
                        waecMarkingScheme = "A1: Linear momentum.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_mom_3",
                        topic = "Newton's Laws",
                        examType = "JAMB",
                        year = 2022,
                        question = "A 60 kg student stands in a lift accelerating upward at 2 m/s². What is the floor reaction force? (g = 10 m/s²)",
                        options = listOf("480 N", "600 N", "720 N", "120 N"),
                        correctIndex = 2,
                        hint = "Upward acceleration increases apparent weight: R = m(g + a).",
                        stepByStepSolution = "R − mg = ma => R = m(g + a) = 60(10 + 2) = 720 N.",
                      ),
                    ),
                ),
                Lesson(
                  id = "phys_05_energy_machines",
                  moduleId = "phys_mod_2",
                  subjectId = "physics",
                  title = "Work, Energy, Power & Simple Machines",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. 2.5–2.6 • WAEC Machines",
                  learningOutcomes =
                    listOf(
                      "Compute Work W = F·d·cosθ, Kinetic Energy ½mv², Gravitational PE mgh, and Power P = F·v.",
                      "Distinguish Mechanical Advantage (MA), Velocity Ratio (VR), and Machine Efficiency (η).",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Mechanical Energy Conservation",
                        explanation =
                          "Work is done only when a force moves a point of application along its line of action: W = F·d·cosθ (carrying a bucket horizontally does ZERO gravitational work because cos90° = 0!). Falling converts potential energy mgh into kinetic energy ½mv².",
                        keyFormula = "KE = \\frac{1}{2}mv^2   |   PE = mgh   |   P = \\frac{W}{t} = F\\cdot v",
                        examTip =
                          "Notice mass m cancels in mgh = ½mv² ⇒ v = √(2gh)! Heavy and light stones dropped from the same height hit vacuum ground at the same speed.",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Simple Machines — MA, VR & Efficiency",
                        explanation =
                          "Machines multiply force (Load / Effort = MA). Velocity Ratio VR depends ONLY on geometry (e.g., number of pulleys supporting the load, or 1/sinθ for an inclined plane, or 2πr/pitch for a screw jack). Friction lowers MA, never VR, so Efficiency η = (MA/VR) × 100% < 100%.",
                        keyFormula = "MA = \\frac{L}{E}   |   VR = \\frac{d_E}{d_L}   |   \\eta = \\frac{MA}{VR} \\times 100\\%",
                        examTip =
                          "For a block-and-tackle pulley system with n pulleys, ideal VR = n! For a smooth inclined plane of angle θ, VR = 1 / sin(θ).",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Inclined Plane Efficiency Calculation",
                        examSource = "WAEC SSCE 2023 • Paper 2 Q5",
                        problemStatement =
                          "A 600 N crate is pulled up a 5 m plane inclined at 30° by an effort of 400 N. Calculate (a) VR, (b) MA, and (c) Efficiency.",
                        steps =
                          listOf(
                            "Step 1 — Geometry gives VR = 1 / sin(30°) = 1 / 0.5 = 2.0.",
                            "Step 2 — MA = Load / Effort = 600 / 400 = 1.5.",
                            "Step 3 — Efficiency η = (MA / VR) × 100% = (1.5 / 2.0) × 100% = 75%.",
                          ),
                        finalAnswer = "VR = 2.0, MA = 1.5, η = 75%",
                        examinerTrap = "Do not mix up MA and VR! MA is always ≤ VR in any real machine.",
                      ),
                      WorkedExample(
                        title = "Pendulum Bob Energy Conservation",
                        examSource = "JAMB UTME 2021 • Physics Q16",
                        problemStatement =
                          "A 0.5 kg pendulum bob is released from height 0.8 m above its lowest point. Find its speed at the bottom (g = 10 m/s²).",
                        steps =
                          listOf(
                            "Step 1 — Equate loss in PE to gain in KE: mgh = ½mv² => v = √(2gh).",
                            "Step 2 — v = √(2 × 10 × 0.8) = √16 = 4.0 m/s.",
                          ),
                        finalAnswer = "4.0 m/s",
                        examinerTrap = "Notice the mass 0.5 kg cancels cleanly and saves arithmetic!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Kainji & Shiroro Hydroelectric Turbines",
                        industryDomain = "Electrical Power Generation",
                        narrative =
                          "Dam water stored at height h holds gravitational potential energy mgh, driving turbine blades at high speed to generate megawatts P = ρ·Q·g·h·η.",
                        examConnection = "Connects energy conversion PE -> KE -> Electrical power.",
                      ),
                      RealLifeScenario(
                        title = "Hydraulic Screw Jack for Tire Changing",
                        industryDomain = "Mechanical Maintenance",
                        narrative =
                          "A long jack handle (radius R) combined with fine thread pitch p achieves a huge VR = 2πR/p so human arm effort lifts a 1.5-tonne SUV.",
                        examConnection = "Standard WAEC Screw Jack VR formula question.",
                      ),
                    ),
                  labType = PracticalLabType.OHMS_CIRCUIT,
                  labBrief =
                    "Inspect electrical power dissipation P = V·I in the DC Circuit Lab—just like mechanical power P = W/t = F·v, rates of energy transfer always measure in Watts (J/s).",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_phys_e1",
                        topic = "Machines",
                        examType = "JAMB",
                        year = 2024,
                        question =
                          "A block and tackle system of 4 pulleys is used to lift a load of 1200 N with an efficiency of 75%. What effort is applied?",
                        options = listOf("300 N", "400 N", "450 N", "900 N"),
                        correctIndex = 1,
                        hint = "VR = 4. First find MA = η × VR, then Effort = Load / MA.",
                        stepByStepSolution = "MA = 0.75 × 4 = 3.0. Effort = 1200 / 3.0 = 400 N.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_e2",
                        topic = "Work & Power",
                        examType = "WAEC",
                        year = 2022,
                        question = "A pump lifts 200 kg of water vertically through 15 m in 10 s. Calculate its useful power output. (g = 10 m/s²)",
                        options = listOf("300 W", "1500 W", "3000 W", "30000 W"),
                        correctIndex = 2,
                        hint = "P = mgh / t.",
                        stepByStepSolution = "Work = 200 × 10 × 15 = 30,000 J. Power = 30,000 / 10 = 3000 W (3 kW).",
                        waecMarkingScheme = "M1: mgh = 30000 J; A1: P = 3000 W.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_e3",
                        topic = "Work & Energy",
                        examType = "JAMB",
                        year = 2023,
                        question = "When a car's speed triples, its kinetic energy increases by a factor of:",
                        options = listOf("3", "6", "9", "27"),
                        correctIndex = 2,
                        hint = "KE ∝ v².",
                        stepByStepSolution = "Since KE = ½mv², replacing v with 3v gives ½m(3v)² = 9 × (½mv²).",
                      ),
                    ),
                ),
                Lesson(
                  id = "phys_06_thermal_fluids",
                  moduleId = "phys_mod_2",
                  subjectId = "physics",
                  title = "Fluid Pressure, Archimedes & Thermal Heat Laws",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. 3 & 4 • WAEC Heat & Fluids",
                  learningOutcomes =
                    listOf(
                      "Apply Pascal's hydraulic press, P = ρgh, and Archimedes' upthrust principle.",
                      "Calculate specific heat capacity (mcΔθ) and latent heat (mL) across phase changes.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Fluid Pressure & Archimedes' Principle",
                        explanation =
                          "Pressure P = Force / Area (Pascals, N/m²). Inside a liquid of density ρ at depth h, hydrostatic pressure is P = ρgh regardless of container shape! By Archimedes' Principle, any immersed body feels upward buoyant force equal to the weight of fluid displaced.",
                        keyFormula = "P = \\rho g h   |   \\frac{F_1}{A_1} = \\frac{F_2}{A_2}   |   Upthrust = V_{immersed} \\cdot \\rho_{fluid} \\cdot g",
                        examTip =
                          "A floating body displaces its OWN WEIGHT of fluid! Remember Specific Gravity (Relative Density) = Weight in air / (Weight in air − Weight in water).",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Sensible Heat vs Latent Heat",
                        explanation =
                          "Heat energy raises temperature when phase stays the same: Q = m·c·Δθ. During melting or boiling, temperature stays CONSTANT while bonds break: Q = m·L (where L_f for ice = 336,000 J/kg and L_v for steam = 2.26 × 10⁶ J/kg).",
                        keyFormula = "Q_{temp} = m c \\Delta\\theta   |   Q_{phase} = m L   |   Heat Lost = Heat Gained",
                        examTip =
                          "Steam at 100 °C causes worse burns than boiling water at 100 °C because steam releases huge latent heat mL_v upon condensing on skin!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Method of Mixtures (Calorimetry)",
                        examSource = "JAMB UTME 2023 • Physics Q20",
                        problemStatement =
                          "100 g of water at 80 °C is mixed with 200 g of cold water at 20 °C in an insulated cup. Find the final equilibrium temperature.",
                        steps =
                          listOf(
                            "Step 1 — Let final temperature = T. Heat lost by hot water = Heat gained by cold water.",
                            "Step 2 — 100 × c × (80 − T) = 200 × c × (T − 20). Divide both sides by 100·c.",
                            "Step 3 — 80 − T = 2T − 40 => 3T = 120 => T = 40 °C.",
                          ),
                        finalAnswer = "40 °C",
                        examinerTrap = "Always write (T_hot − T) on one side and (T − T_cold) on the other so both temperature changes are positive!",
                      ),
                      WorkedExample(
                        title = "Relative Density by Upthrust",
                        examSource = "WAEC SSCE 2021 • Paper 1 Q18",
                        problemStatement =
                          "A metal block weighs 50 N in air and 42 N when completely submerged in water. What is its relative density?",
                        steps =
                          listOf(
                            "Step 1 — Upthrust in water = 50 − 42 = 8 N.",
                            "Step 2 — Relative density = Weight in air / Upthrust in water = 50 / 8 = 6.25.",
                          ),
                        finalAnswer = "6.25",
                        examinerTrap = "Relative density is a pure ratio with NO units!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Auto Mechanic Hydraulic Garage Lifts",
                        industryDomain = "Fluid Power Engineering",
                        narrative =
                          "By Pascal's principle, pressing a small 2 cm² master cylinder transmits identical fluid pressure P to a wide 200 cm² lift piston, multiplying force by 100×.",
                        examConnection = "Directly tests F₂/F₁ = A₂/A₁ = (r₂/r₁)².",
                      ),
                      RealLifeScenario(
                        title = "Refrigerator Cooling Coils & Sweat Evaporation",
                        industryDomain = "Thermodynamics & Physiology",
                        narrative =
                          "Liquid refrigerant absorbs latent heat of vaporization L_v from inside the fridge box, just as evaporating sweat extracts latent heat from human skin.",
                        examConnection = "Explains cooling-by-evaporation conceptual items in JAMB.",
                      ),
                    ),
                  labType = PracticalLabType.GAS_LAW_PISTON,
                  labBrief =
                    "Use the Thermal Gas Piston lab below to observe how Kelvin temperature and pressure scale gas volume directly.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_phys_th1",
                        topic = "Fluids",
                        examType = "JAMB",
                        year = 2024,
                        question = "At what depth in freshwater (ρ = 1000 kg/m³) does gauge water pressure equal 5.0 × 10⁴ Pa? (g = 10 m/s²)",
                        options = listOf("0.5 m", "5.0 m", "50 m", "500 m"),
                        correctIndex = 1,
                        hint = "P = ρgh.",
                        stepByStepSolution = "h = P / (ρg) = 50,000 / (1000 × 10) = 5.0 m.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_th2",
                        topic = "Thermal Physics",
                        examType = "WAEC",
                        year = 2023,
                        question = "How much heat turns 0.2 kg of ice at 0 °C completely into water at 0 °C? (L_f = 3.36 × 10⁵ J/kg)",
                        options = listOf("16.8 kJ", "33.6 kJ", "67.2 kJ", "168 kJ"),
                        correctIndex = 2,
                        hint = "Pure melting at 0 °C uses Q = m·L_f.",
                        stepByStepSolution = "Q = 0.2 × 336,000 = 67,200 J = 67.2 kJ.",
                        waecMarkingScheme = "M1: Q = mL; A1: 67.2 kJ.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_th3",
                        topic = "Thermal Expansion",
                        examType = "JAMB",
                        year = 2022,
                        question = "A brass rod of length 2.0 m is heated by 50 °C. If linear expansivity α = 1.8 × 10⁻⁵ K⁻¹, find its extension.",
                        options = listOf("0.18 mm", "1.8 mm", "18 mm", "0.09 mm"),
                        correctIndex = 1,
                        hint = "ΔL = α·L₀·Δθ.",
                        stepByStepSolution = "ΔL = 1.8 × 10⁻⁵ × 2.0 × 50 = 1.8 × 10⁻³ m = 1.8 mm.",
                      ),
                    ),
                ),
              ),
          ),
        ) + PhysicsCurriculumPart2.modules,
      pastQuestions = PhysicsCurriculumPart2.pastQuestions,
    )
}
