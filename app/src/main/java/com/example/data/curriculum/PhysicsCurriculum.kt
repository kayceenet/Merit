package com.example.data.curriculum

object PhysicsCurriculum {

  val track =
    SubjectTrack(
      id = "physics",
      title = "Physics Mastery",
      shortName = "Physics",
      tagline = "Mechanics, Electricity, Waves & Modern Optics engineered for UTME & WAEC A1",
      examCoverage = "JAMB 40 Qs • WAEC Paper 1, 2 & Practical",
      modules =
        listOf(
          CourseModule(
            id = "phys_mod_1",
            subjectId = "physics",
            indexLabel = "Module 01",
            title = "Kinematics, Projectiles & Momentum",
            summary = "Resolve 2D motion into independent orthogonal vectors and apply impulse-momentum laws.",
            lessons =
              listOf(
                Lesson(
                  id = "phys_proj_01",
                  moduleId = "phys_mod_1",
                  subjectId = "physics",
                  title = "Projectile Motion & Vector Resolution",
                  durationMinutes = 18,
                  syllabusTag = "JAMB Sec. 2.2 • WAEC Mechanics",
                  learningOutcomes =
                    listOf(
                      "Decompose initial velocity u into horizontal (u·cosθ) and vertical (u·sinθ) components.",
                      "Derive Time of Flight (T), Maximum Height (H), and Horizontal Range (R).",
                      "Prove why complementary angles (θ and 90°−θ) produce identical range.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Independence of Horizontal & Vertical Axes",
                        explanation =
                          "Neglecting air resistance, a projectile experiences zero horizontal acceleration (ax = 0) and constant downward gravitational acceleration (ay = −g). Therefore horizontal velocity remains constant at u·cosθ throughout flight, while vertical velocity obeys v_y = u·sinθ − g·t.",
                        keyFormula = "u_x = u·cos(θ)   |   u_y = u·sin(θ)   |   v_y² = u_y² − 2gH",
                        examTip =
                          "At the highest point of trajectory, vertical velocity v_y = 0, NOT total velocity! Total speed at the peak equals u·cos(θ).",
                      ),
                      ConceptBlock(
                        heading = "2. The Three Core Flight Equations",
                        explanation =
                          "Setting v_y = 0 gives time to peak t_p = u·sinθ / g. By symmetry over level ground, total time of flight T = 2·t_p. Substituting t_p into s = u_y·t − ½gt² gives Maximum Height H, and multiplying u_x by T yields Range R.",
                        keyFormula =
                          "T = (2u·sinθ)/g   |   H = (u²·sin²θ)/(2g)   |   R = (u²·sin2θ)/g",
                        examTip =
                          "Maximum range R_max = u²/g occurs at θ = 45° because sin(2×45°) = sin(90°) = 1. Always check if the launch is horizontal from a cliff (θ = 0°, so u_y = 0) vs ground-to-ground!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Ground-to-Ground Trajectory Analysis",
                        examSource = "JAMB UTME 2021 • Physics Q14",
                        problemStatement =
                          "A mortar shell is fired at 40 m/s at an angle of 30° to the horizontal. Taking g = 10 m/s², calculate (a) Time of flight, (b) Maximum height, and (c) Horizontal range.",
                        steps =
                          listOf(
                            "Step 1 — Resolve components: u_y = 40·sin(30°) = 40 × 0.5 = 20 m/s; u_x = 40·cos(30°) = 40 × 0.866 = 34.64 m/s.",
                            "Step 2 — Time of flight: T = (2 × u_y) / g = (2 × 20) / 10 = 4.0 s.",
                            "Step 3 — Maximum height: H = u_y² / (2g) = 20² / (2 × 10) = 400 / 20 = 20.0 m.",
                            "Step 4 — Horizontal range: R = u_x × T = 34.64 × 4.0 = 138.56 m (exact: 80√3 m).",
                          ),
                        finalAnswer = "T = 4.0 s, H = 20.0 m, R = 80√3 m (138.6 m)",
                        examinerTrap =
                          "Students frequently use u = 40 m/s directly in H = u²/(2g) instead of u·sinθ, giving 80 m instead of 20 m.",
                      ),
                      WorkedExample(
                        title = "Horizontal Projection from a Raised Platform",
                        examSource = "WAEC SSCE 2020 • Paper 2 Q3",
                        problemStatement =
                          "A drone drops a relief parcel horizontally at 25 m/s from a height of 80 m above flat ground (g = 10 m/s²). How far horizontally from the release point does it land?",
                        steps =
                          listOf(
                            "Step 1 — Vertical drop has initial u_y = 0 m/s: h = ½·g·t² => 80 = 0.5 × 10 × t² = 5t².",
                            "Step 2 — Solve for fall time: t² = 16 => t = 4.0 s.",
                            "Step 3 — Horizontal distance: R = u_x × t = 25 × 4.0 = 100 m.",
                          ),
                        finalAnswer = "100 m",
                        examinerTrap =
                          "Do not apply T = 2u·sinθ/g here — the parcel does not go up and come back down; it falls from rest vertically.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Relief Airdrop & Agricultural Drone Spraying",
                        industryDomain = "Aerospace & Precision Logistics",
                        narrative =
                          "Delivery drones flying over flood-affected riverine communities in Bayelsa cannot hover over every rooftop safely. Flight computers calculate wind-adjusted horizontal ground speed u_x and altitude h to release packages ahead of the target zone so parabolic gravity carries them straight onto the drop pad.",
                        examConnection =
                          "Directly tests horizontal-cliff projection (h = ½gt², R = u_x·t) frequently examined in WAEC Paper 2.",
                      ),
                      RealLifeScenario(
                        title = "Firefighting Water Jet Optimization",
                        industryDomain = "Civil & Emergency Engineering",
                        narrative =
                          "When a fire crew aims a high-pressure hose nozzle at an upper-story window across a street, adjusting the elevation angle balances vertical reach H against horizontal reach R. Understanding H = u²sin²θ/(2g) explains why too shallow an angle misses height while too steep an angle falls short horizontally.",
                        examConnection =
                          "Connects complementary launch angles and vertical-vs-horizontal trade-offs in JAMB conceptual questions.",
                      ),
                    ),
                  labType = PracticalLabType.PROJECTILE_KINEMATICS,
                  labBrief =
                    "Experiment in the lab below: (1) Fix speed at 40 m/s and compare Range at 30° vs 60°. (2) Set angle to 45° to verify maximum range. (3) Switch g from Earth (9.81 m/s²) to Moon (1.62 m/s²) and observe the 6× trajectory expansion.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_phys_proj_1",
                        topic = "Projectile Motion",
                        examType = "JAMB",
                        year = 2022,
                        question =
                          "A ball is projected at 30° to the horizontal with speed 60 m/s. What is its speed at the maximum height? (g = 10 m/s²)",
                        options = listOf("0 m/s", "30 m/s", "30√3 m/s", "60 m/s"),
                        correctIndex = 2,
                        hint = "At the highest point, vertical velocity vanishes, leaving only horizontal velocity.",
                        stepByStepSolution =
                          "At maximum height, v_y = 0. Horizontal velocity remains constant: v_x = u·cos(30°) = 60 × (√3/2) = 30√3 m/s.",
                      ),
                      PracticeQuestion(
                        id = "ex_phys_proj_2",
                        topic = "Projectile Motion",
                        examType = "WAEC",
                        year = 2021,
                        question =
                          "Two projectiles are launched at the same speed u at angles 25° and 65° on level ground. Which quantity is identical for both?",
                        options =
                          listOf(
                            "Maximum height",
                            "Time of flight",
                            "Horizontal range",
                            "Vertical initial velocity",
                          ),
                        correctIndex = 2,
                        hint = "Add the two angles together: 25° + 65° = 90°.",
                        stepByStepSolution =
                          "Since 25° + 65° = 90°, sin(2×25°) = sin(50°) and sin(2×65°) = sin(130°) = sin(50°). Thus R = u²sin(2θ)/g is identical.",
                        waecMarkingScheme = "M1: Identify complementary angles (25° + 65° = 90°); A1: State sin(50°) = sin(130°) giving equal Range.",
                      ),
                    ),
                ),
                Lesson(
                  id = "phys_mom_02",
                  moduleId = "phys_mod_1",
                  subjectId = "physics",
                  title = "Impulse, Linear Momentum & Collisions",
                  durationMinutes = 15,
                  syllabusTag = "JAMB Sec. 2.4 • WAEC Dynamics",
                  learningOutcomes =
                    listOf(
                      "Relate Newton's 2nd Law F = Δp/Δt to impulse-momentum theorem F·t = m(v − u).",
                      "Distinguish elastic vs inelastic collisions using kinetic energy conservation.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Conservation of Linear Momentum",
                        explanation =
                          "When no net external force acts on an isolated system, total vector momentum before collision equals total vector momentum after collision. Always assign + and − signs to opposite directions!",
                        keyFormula = "m₁u₁ + m₂u₂ = m₁v₁ + m₂v₂   |   F·Δt = m(v − u)",
                        examTip =
                          "In a 'coalesce / stick together' collision, both masses share a common final velocity v: m₁u₁ + m₂u₂ = (m₁ + m₂)v.",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Inelastic Coupling of Railway Trucks",
                        examSource = "WAEC SSCE 2019 • Paper 1 Q11",
                        problemStatement =
                          "A 4 kg trolley moving at 6 m/s collides and sticks to a stationary 2 kg trolley. Find their common velocity after impact.",
                        steps =
                          listOf(
                            "Step 1 — Initial momentum: p_i = (4 × 6) + (2 × 0) = 24 kg·m/s.",
                            "Step 2 — Combined mass: M = 4 + 2 = 6 kg.",
                            "Step 3 — Common velocity: 6v = 24 => v = 4.0 m/s.",
                          ),
                        finalAnswer = "4.0 m/s",
                        examinerTrap =
                          "Do not equate kinetic energy before and after! Kinetic energy is lost to heat/sound in inelastic sticking collisions.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Vehicle Crumple Zones & Airbags",
                        industryDomain = "Automotive Safety Engineering",
                        narrative =
                          "During a sudden crash, the car's momentum change Δp is fixed by its mass and speed. Airbags and crumple zones extend the collision contact time Δt from 0.01 s to 0.15 s, reducing peak impact force F = Δp/Δt on passengers by 15×.",
                        examConnection =
                          "Frequently tested in WAEC Theory: explain why a cricketer pulls their hands back when catching a fast ball.",
                      ),
                    ),
                  labType = PracticalLabType.PROJECTILE_KINEMATICS,
                  labBrief =
                    "Use the kinematics simulator to observe how vertical velocity v_y flips sign symmetrically at impact, illustrating impulse Δp = 2·m·u_y during elastic bounce.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_phys_mom_1",
                        topic = "Linear Momentum",
                        examType = "JAMB",
                        year = 2023,
                        question =
                          "A force of 50 N acts on a 4 kg body initially at rest for 0.8 s. Calculate the final velocity.",
                        options = listOf("5 m/s", "10 m/s", "20 m/s", "40 m/s"),
                        correctIndex = 1,
                        hint = "Use Impulse = Force × Time = m(v − u).",
                        stepByStepSolution = "F·t = m·v => 50 × 0.8 = 4 × v => 40 = 4v => v = 10 m/s.",
                      ),
                    ),
                ),
              ),
          ),
          CourseModule(
            id = "phys_mod_2",
            subjectId = "physics",
            indexLabel = "Module 02",
            title = "DC Circuits, Ohm's Law & Optics",
            summary = "Master series/parallel resistor networks, internal resistance, and optical refraction.",
            lessons =
              listOf(
                Lesson(
                  id = "phys_ohm_01",
                  moduleId = "phys_mod_2",
                  subjectId = "physics",
                  title = "Kirchhoff's Rules, Cells & Resistor Networks",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. 6.2 • WAEC Electricity",
                  learningOutcomes =
                    listOf(
                      "Compute equivalent resistance for series and parallel combinations.",
                      "Account for cell internal resistance r using E = I(R + r) and lost volts I·r.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Series vs Parallel & Internal Resistance",
                        explanation =
                          "In series, current is identical everywhere while voltage divides proportionally to resistance. In parallel, potential difference is identical across branches while current splits inversely with resistance. Real cells have internal resistance r that drops voltage inside the battery.",
                        keyFormula =
                          "R_s = R₁ + R₂   |   1/R_p = 1/R₁ + 1/R₂   |   E = I(R + r) = V + Ir",
                        examTip =
                          "Terminal p.d. V equals EMF (E) ONLY on open circuit (I = 0). Whenever current flows, V = E − Ir < E.",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Parallel Load with Cell Internal Resistance",
                        examSource = "WAEC SSCE 2022 • Paper 1 Q38",
                        problemStatement =
                          "A 12 V battery of internal resistance 1 Ω is connected across 6 Ω and 3 Ω resistors in parallel. Find the total current and terminal p.d.",
                        steps =
                          listOf(
                            "Step 1 — Parallel resistance: R_p = (6 × 3) / (6 + 3) = 18 / 9 = 2 Ω.",
                            "Step 2 — Total circuit current: I = E / (R_p + r) = 12 / (2 + 1) = 4.0 A.",
                            "Step 3 — Terminal p.d.: V = I × R_p = 4.0 × 2 = 8.0 V (or V = 12 − 4×1 = 8.0 V).",
                          ),
                        finalAnswer = "I = 4.0 A, V_terminal = 8.0 V",
                        examinerTrap =
                          "Never forget to add internal resistance r in series with the external equivalent resistance R_p.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Home Solar Inverter & Battery Bank Sizing",
                        industryDomain = "Renewable Power Systems",
                        narrative =
                          "In Nigerian homes and study centers using solar inverters, wiring household sockets in parallel ensures each appliance receives a steady 230 V even if one lamp blows. Meanwhile, aging lead-acid batteries develop higher internal resistance r, causing terminal voltage to sag ('low battery cutoff') under heavy AC/pump loads.",
                        examConnection =
                          "Illustrates both parallel domestic wiring and V = E − Ir voltage sag under high current draw.",
                      ),
                    ),
                  labType = PracticalLabType.OHMS_CIRCUIT,
                  labBrief =
                    "Use the DC Circuit Bench below: switch between Series and Parallel with R₁ = 6 Ω, R₂ = 3 Ω, and V = 12 V to verify why Parallel draws 3× total current and dissipates 9× more power!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_phys_ohm_1",
                        topic = "DC Circuits",
                        examType = "JAMB",
                        year = 2023,
                        question =
                          "Three 6 Ω resistors are connected in parallel across a 12 V ideal battery. What total power is dissipated?",
                        options = listOf("24 W", "36 W", "72 W", "144 W"),
                        correctIndex = 2,
                        hint = "Find R_eq = 6/3 = 2 Ω, then use P = V² / R_eq.",
                        stepByStepSolution =
                          "R_eq = 6 / 3 = 2 Ω. Power P = V² / R_eq = 12² / 2 = 144 / 2 = 72 W.",
                      ),
                    ),
                ),
              ),
          ),
        ),
      pastQuestions =
        listOf(
          PracticeQuestion(
            id = "pq_phys_1",
            topic = "Projectile Motion",
            examType = "JAMB",
            year = 2023,
            question =
              "A stone is thrown horizontally from the top of a 45 m tower with a velocity of 20 m/s. How far from the base of the tower does it strike the ground? (g = 10 m/s²)",
            options = listOf("30 m", "45 m", "60 m", "90 m"),
            correctIndex = 2,
            hint = "Use h = ½gt² to find fall time t first.",
            stepByStepSolution =
              "45 = ½ × 10 × t² => t² = 9 => t = 3 s. Horizontal distance R = u_x × t = 20 × 3 = 60 m.",
          ),
          PracticeQuestion(
            id = "pq_phys_2",
            topic = "DC Circuits",
            examType = "WAEC",
            year = 2022,
            question =
              "A cell of EMF 2.0 V and internal resistance 0.5 Ω supplies current to a 3.5 Ω resistor. What is the terminal potential difference across the resistor?",
            options = listOf("0.25 V", "1.50 V", "1.75 V", "2.00 V"),
            correctIndex = 2,
            hint = "Calculate I = E/(R+r), then V = I·R.",
            stepByStepSolution =
              "I = 2.0 / (3.5 + 0.5) = 2.0 / 4.0 = 0.5 A. Terminal p.d. V = I × R = 0.5 × 3.5 = 1.75 V.",
            waecMarkingScheme = "M1: I = E/(R+r) = 0.5 A; M1: V = IR; A1: 1.75 V.",
          ),
          PracticeQuestion(
            id = "pq_phys_3",
            topic = "Optics & Refraction",
            examType = "JAMB",
            year = 2021,
            question =
              "Light passes from air (n = 1.0) into glass (n = 1.5). If the speed of light in air is 3.0 × 10⁸ m/s, what is its speed in glass?",
            options = listOf("1.5 × 10⁸ m/s", "2.0 × 10⁸ m/s", "3.0 × 10⁸ m/s", "4.5 × 10⁸ m/s"),
            correctIndex = 1,
            hint = "Refractive index n = c / v.",
            stepByStepSolution = "v = c / n = (3.0 × 10⁸) / 1.5 = 2.0 × 10⁸ m/s.",
          ),
          PracticeQuestion(
            id = "pq_phys_4",
            topic = "Linear Momentum",
            examType = "WAEC",
            year = 2020,
            question =
              "A 2 kg mass moving east at 5 m/s collides head-on with a 3 kg mass moving west at 2 m/s. If they stick together, what is their common velocity?",
            options = listOf("0.8 m/s East", "0.8 m/s West", "3.2 m/s East", "1.6 m/s East"),
            correctIndex = 0,
            hint = "Take East as positive (+) and West as negative (−).",
            stepByStepSolution =
              "Total p_i = (2 × 5) + (3 × −2) = 10 − 6 = +4 kg·m/s. Combined mass = 5 kg => v = 4/5 = +0.8 m/s (East).",
            waecMarkingScheme = "M1: Sign convention (+5 and −2); M1: (2+3)v = 4; A1: 0.8 m/s East.",
          ),
        ),
    )
}
