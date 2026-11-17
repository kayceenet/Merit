package com.example.data.curriculum

object MathematicsCurriculumPart2 {

  val modules: List<CourseModule> =
    listOf(
      CourseModule(
        id = "math_mod_3",
        subjectId = "mathematics",
        indexLabel = "Module 03",
        title = "Geometry, Coordinate Lines & Trigonometry",
        summary = "Master circle theorems, mensuration solids, coordinate geometry gradients, sine/cosine rules, and bearings.",
        lessons =
          listOf(
            Lesson(
              id = "math_07_geometry_mensuration",
              moduleId = "math_mod_3",
              subjectId = "mathematics",
              title = "Circle Theorems, Polygons & Solid Mensuration",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. IV.1–IV.2 • WAEC Geometry",
              learningOutcomes =
                listOf(
                  "Apply angle at center = 2× angle at circumference, cyclic quadrilaterals, and alternate segment theorem.",
                  "Calculate surface area & volume of sectors, cones, cylinders, spheres, and frustums.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: The Five High-Yield Circle Theorems",
                    explanation =
                      "1. Angle at center is 2× angle at circumference standing on the same arc. 2. Angle in a semicircle is 90°. 3. Angles in the same segment are equal. 4. Opposite angles of a cyclic quadrilateral sum to 180°. 5. Tangent meets radius at 90°, and angle between tangent and chord equals angle in alternate segment.",
                    keyFormula = "\\theta_{center} = 2\\theta_{circ}   |   A + C = 180^\\circ \\text{ (cyclic)}",
                    examTip =
                      "Look for a diameter first in any circle diagram — it immediately locks a 90° right triangle!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Sectors, Cones & Spheres Mensuration",
                    explanation =
                      "When a circular sector of radius L and angle θ folds into a cone, sector arc length (θ/360)2πL becomes cone base circumference 2πr, giving slant radius relation r = (θ/360)L!",
                    keyFormula = "V_{cone} = \\frac{1}{3}\\pi r^2 h   |   A_{curved,cone} = \\pi r L   |   V_{sphere} = \\frac{4}{3}\\pi r^3",
                    examTip =
                      "Don't forget slant height L = √(r² + h²) when finding total surface area of a cone!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Sector Folded into a Right Circular Cone",
                    examSource = "WAEC SSCE 2023 • Paper 2 Q6",
                    problemStatement =
                      "A 216° sector of a circle of radius 10 cm is folded into a cone. Find the cone's base radius and vertical height.",
                    steps =
                      listOf(
                        "Step 1 — Sector radius becomes cone slant height: L = 10 cm.",
                        "Step 2 — Base radius: r = (216 / 360) × 10 = 0.6 × 10 = 6 cm.",
                        "Step 3 — Vertical height via 3–4–5 triangle: h = √(10² − 6²) = √64 = 8 cm.",
                      ),
                    finalAnswer = "r = 6 cm, h = 8 cm",
                    examinerTrap = "Sector radius (10 cm) is SLANT HEIGHT L of the cone, NOT vertical height h!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Grain Silos & Petrol Storage Tank Capacities",
                    industryDomain = "Agro-Industrial Engineering",
                    narrative =
                      "Silos combine cylindrical bodies with conical roofs; total volume sums πr²H + (1/3)πr²h_cone.",
                    examConnection = "Classic WAEC composite solid volume & surface area problem.",
                  ),
                ),
              labType = PracticalLabType.WAVE_OPTICS_BENCH,
              labBrief =
                "Inspect angle-geometry symmetry inside the ray bench below, reinforcing how perpendicular normals govern circle tangent geometry.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_g1",
                    topic = "Polygons",
                    examType = "JAMB",
                    year = 2024,
                    question = "Each interior angle of a regular polygon is 150°. How many sides does it have?",
                    options = listOf("8", "10", "12", "15"),
                    correctIndex = 2,
                    hint = "Exterior angle = 180° − 150° = 30°, and n = 360° / ext.",
                    stepByStepSolution = "Exterior angle = 30°. Number of sides n = 360 / 30 = 12.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_g2",
                    topic = "Circle Theorems",
                    examType = "WAEC",
                    year = 2023,
                    question = "PQRS is a cyclic quadrilateral. If ∠P = 78° and ∠Q = 105°, find ∠R.",
                    options = listOf("75°", "78°", "102°", "105°"),
                    correctIndex = 2,
                    hint = "Opposite angles P and R sum to 180°.",
                    stepByStepSolution = "∠R = 180° − 78° = 102°.",
                    waecMarkingScheme = "M1: Opposite angles of cyclic quad = 180°; A1: 102°.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_g3",
                    topic = "Mensuration",
                    examType = "JAMB",
                    year = 2022,
                    question = "A sphere has radius 3 cm. What is its volume in terms of π?",
                    options = listOf("12π cm³", "27π cm³", "36π cm³", "108π cm³"),
                    correctIndex = 2,
                    hint = "V = (4/3)πr³.",
                    stepByStepSolution = "V = (4/3)π(3³) = (4/3)π(27) = 36π cm³.",
                  ),
                ),
            ),
            Lesson(
              id = "math_08_coord_trig",
              moduleId = "math_mod_3",
              subjectId = "mathematics",
              title = "Coordinate Geometry, Trigonometry & Bearings",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. IV.3–IV.4 • WAEC Trig & Bearings",
              learningOutcomes =
                listOf(
                  "Compute midpoint, distance, parallel (m₁=m₂) and perpendicular (m₁m₂ = −1) line equations.",
                  "Apply SOH-CAH-TOA, CAST quadrant signs, Sine Rule, Cosine Rule, and 3-figure bearings.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Coordinate Line Geometry",
                    explanation =
                      "Given points A(x₁, y₁) and B(x₂, y₂): gradient m = (y₂ − y₁)/(x₂ − x₁). Parallel lines have IDENTICAL slopes (m₁ = m₂); perpendicular lines obey m₁ · m₂ = −1 (negative reciprocal!).",
                    keyFormula = "d = \\sqrt{(x_2-x_1)^2 + (y_2-y_1)^2}   |   m_1 m_2 = -1   |   y - y_1 = m(x - x_1)",
                    examTip =
                      "Perpendicular bisector of segment AB passes through the MIDPOINT of AB with gradient −1/m_AB!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Sine Rule, Cosine Rule & Exact Trig Angles",
                    explanation =
                      "In non-right triangles, use Sine Rule when you know an angle-opposite-side pair (a/sinA = b/sinB). Use Cosine Rule for 2 sides + included angle (SAS) or 3 sides (SSS). Memorize 30°-45°-60° exact surds!",
                    keyFormula = "\\frac{a}{\\sin A} = \\frac{b}{\\sin B}   |   a^2 = b^2 + c^2 - 2bc\\cos A   |   \\sin^2\\theta + \\cos^2\\theta = 1",
                    examTip =
                      "Three-figure bearings always measure CLOCKWISE from North (000°–360°). Reverse bearing from B back to A differs by ±180°!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Perpendicular Line Through a Given Point",
                    examSource = "JAMB UTME 2024 • Mathematics Q28",
                    problemStatement = "Find the equation of the line through (4, 1) perpendicular to 2x + y = 5.",
                    steps =
                      listOf(
                        "Step 1 — Rewrite 2x + y = 5 as y = −2x + 5, so m₁ = −2.",
                        "Step 2 — Perpendicular slope: m₂ = −1 / (−2) = +1/2.",
                        "Step 3 — Line equation: y − 1 = ½(x − 4) => 2y − 2 = x − 4 => x − 2y − 2 = 0.",
                      ),
                    finalAnswer = "x − 2y − 2 = 0 (or y = ½x − 1)",
                    examinerTrap = "Negate AND flip the slope: perpendicular to −2 is +1/2, not +2!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Airport Runway Bearings & Land Surveying",
                    industryDomain = "Aviation & Geomatics",
                    narrative =
                      "Air-traffic control uses 3-digit clockwise bearings from North + Cosine Rule triangles to maintain safe aircraft separation.",
                    examConnection = "Direct match for JAMB/WAEC 2-leg bearing triangle questions.",
                  ),
                ),
              labType = PracticalLabType.PROJECTILE_KINEMATICS,
              labBrief =
                "Experiment with launch angle θ in the simulator to watch complementary trig projections cos(θ) and sin(θ) trade horizontal vs vertical distance!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_c1",
                    topic = "Coordinate Geometry",
                    examType = "JAMB",
                    year = 2025,
                    question = "Find the distance between points P(−2, 3) and Q(4, −5).",
                    options = listOf("8", "10", "12", "14"),
                    correctIndex = 1,
                    hint = "Δx = 6, Δy = −8; 6–8–10 right triangle!",
                    stepByStepSolution = "d = √((4 − (−2))² + (−5 − 3)²) = √(6² + (−8)²) = √100 = 10.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_c2",
                    topic = "Trigonometry",
                    examType = "WAEC",
                    year = 2023,
                    question = "If sin θ = 5/13 and θ is obtuse (90° < θ < 180°), find cos θ.",
                    options = listOf("12/13", "−12/13", "5/12", "−5/12"),
                    correctIndex = 1,
                    hint = "5–12–13 triangle in Quadrant 2 where ONLY Sine is positive (CAST).",
                    stepByStepSolution = "In Q2 cosine is negative: cos θ = −12/13.",
                    waecMarkingScheme = "M1: 5-12-13 triangle & Quadrant 2 negative cosine; A1: −12/13.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_c3",
                    topic = "Bearings",
                    examType = "JAMB",
                    year = 2022,
                    question = "The bearing of town Q from town P is 065°. What is the bearing of P from Q?",
                    options = listOf("115°", "245°", "295°", "025°"),
                    correctIndex = 1,
                    hint = "Add 180° when initial bearing < 180°.",
                    stepByStepSolution = "065° + 180° = 245°.",
                  ),
                ),
            ),
          ),
      ),
      CourseModule(
        id = "math_mod_4",
        subjectId = "mathematics",
        indexLabel = "Module 04",
        title = "Calculus, Integration, Probability & Statistics",
        summary = "Differentiate curves, integrate areas/volumes, and conquer permutations, combinations, and statistics.",
        lessons =
          listOf(
            Lesson(
              id = "math_09_differentiation",
              moduleId = "math_mod_4",
              subjectId = "mathematics",
              title = "Differentiation: Tangents, Normals & Maxima/Minima",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. III.1 • WAEC Calculus",
              learningOutcomes =
                listOf(
                  "Apply Power, Chain, Product, and Quotient rules to differentiate functions.",
                  "Locate and classify stationary points using dy/dx = 0 and d²y/dx².",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Power Rule & Instantaneous Slope",
                    explanation =
                      "For any power term y = ax^n, bring the exponent down to multiply and reduce the exponent by 1: dy/dx = n·a·x^{n−1}. The derivative of any constant is 0. This gives the EXACT tangent slope at any x.",
                    keyFormula = "\\frac{d}{dx}(ax^n) = nax^{n-1}   |   m_{normal} = -\\frac{1}{m_{tangent}}",
                    examTip =
                      "Rewrite 1/x² as x^{-2} and √x as x^{1/2} BEFORE applying Power Rule!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Turning Points & Second Derivative Test",
                    explanation =
                      "At a peak or valley, slope dy/dx = 0. Plug those x-values into d²y/dx²: negative (< 0) proves Maximum; positive (> 0) proves Minimum.",
                    keyFormula = "\\frac{dy}{dx} = 0 \\text{ (Stationary)}   |   \\frac{d^2y}{dx^2} < 0 \\Rightarrow \\text{Max},\\; > 0 \\Rightarrow \\text{Min}",
                    examTip =
                      "When asked for 'the maximum value of y', substitute the stationary x BACK into y!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding Local Max & Min of a Cubic Curve",
                    examSource = "WAEC SSCE 2022 • Paper 2 Q8",
                    problemStatement = "Find the stationary points of y = x³ − 3x² − 9x + 5 and distinguish their nature.",
                    steps =
                      listOf(
                        "Step 1 — dy/dx = 3x² − 6x − 9 = 0 => (x − 3)(x + 1) = 0 => x = −1 or x = 3.",
                        "Step 2 — d²y/dx² = 6x − 6.",
                        "Step 3 — At x = −1: y'' = −12 < 0 (Max), y = 10 => Max (−1, 10). At x = 3: y'' = +12 > 0 (Min), y = −22 => Min (3, −22).",
                      ),
                    finalAnswer = "Max (−1, 10), Min (3, −22)",
                    examinerTrap = "Don't swap < 0 and > 0! Remember negative d²y/dx² bends downward like an umbrella (Maximum).",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Minimum-Material Tin Packaging Design",
                    industryDomain = "Industrial Manufacturing",
                    narrative =
                      "Minimizing sheet aluminum A(r) = 2πr² + 1000/r via dA/dr = 0 yields the optimal height-equals-diameter tin geometry.",
                    examConnection = "Favorite WAEC Paper 2 optimization problem.",
                  ),
                ),
              labType = PracticalLabType.QUADRATIC_TANGENT,
              labBrief =
                "Set a = 2, b = −8, c = 3 and drag x₀ to x = 2.0 to watch tangent slope dy/dx reach 0.00 right at the vertex!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_d1",
                    topic = "Calculus",
                    examType = "JAMB",
                    year = 2024,
                    question = "Find the gradient of the normal to y = 2x² − 3x + 1 at x = 2.",
                    options = listOf("5", "−5", "1/5", "−1/5"),
                    correctIndex = 3,
                    hint = "m_normal = −1 / m_tangent.",
                    stepByStepSolution = "dy/dx = 4x − 3. At x = 2, m_t = 5 => m_n = −1/5.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_d2",
                    topic = "Calculus",
                    examType = "WAEC",
                    year = 2023,
                    question = "Differentiate y = (3x − 1)⁴ with respect to x.",
                    options = listOf("4(3x − 1)³", "12(3x − 1)³", "3(3x − 1)³", "12(3x − 1)⁴"),
                    correctIndex = 1,
                    hint = "Chain rule: multiply by inner derivative (3).",
                    stepByStepSolution = "dy/dx = 4(3x − 1)³ × 3 = 12(3x − 1)³.",
                    waecMarkingScheme = "M1: Chain rule factor 3; A1: 12(3x − 1)³.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_d3",
                    topic = "Calculus",
                    examType = "JAMB",
                    year = 2023,
                    question = "Differentiate y = sin(4x) with respect to x.",
                    options = listOf("cos(4x)", "−4cos(4x)", "4cos(4x)", "4sin(4x)"),
                    correctIndex = 2,
                    hint = "d/dx sin(kx) = k·cos(kx).",
                    stepByStepSolution = "By Chain Rule, dy/dx = 4cos(4x).",
                  ),
                ),
            ),
            Lesson(
              id = "math_10_integration",
              moduleId = "math_mod_4",
              subjectId = "mathematics",
              title = "Integration, Area Under Curves & Motion Displacement",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. III.2 • WAEC Integral Calculus",
              learningOutcomes =
                listOf(
                  "Integrate polynomial and trigonometric terms accurately (+ constant C).",
                  "Compute definite integrals for bounded curve areas and velocity-to-displacement motion.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Integration Reverses Differentiation",
                    explanation =
                      "Raise the exponent by +1, then divide by the new exponent: ∫ x^n dx = x^{n+1}/(n+1) + C (for n ≠ −1). Definite integrals between x = a and x = b cancel + C cleanly by subtracting F(b) − F(a).",
                    keyFormula = "\\int ax^n\\,dx = \\frac{ax^{n+1}}{n+1} + C   |   \\int_a^b f(x)\\,dx = F(b) - F(a)",
                    examTip =
                      "Indefinite integrals in JAMB ALWAYS include '+ C'! Also, displacement s = ∫ v dt reverses velocity = ds/dt!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Definite Area Bounded by a Parabola",
                    examSource = "JAMB UTME 2024 • Mathematics Q34",
                    problemStatement = "Evaluate the area under y = 3x² + 2 between x = 1 and x = 3.",
                    steps =
                      listOf(
                        "Step 1 — Antiderivative: F(x) = x³ + 2x.",
                        "Step 2 — F(3) = 27 + 6 = 33; F(1) = 1 + 2 = 3.",
                        "Step 3 — Area = 33 − 3 = 30 sq. units.",
                      ),
                    finalAnswer = "30 square units",
                    examinerTrap = "Don't add F(3) + F(1); definite integration always subtracts F(upper) − F(lower)!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Total Energy & Reservoir Inflow Accumulation",
                    industryDomain = "Hydrology & Physics",
                    narrative =
                      "Integrating flow rate Q(t) over 24 hours tells dam operators exact total cubic metres of water stored.",
                    examConnection = "Direct physical meaning of Area Under Curve.",
                  ),
                ),
              labType = PracticalLabType.QUADRATIC_TANGENT,
              labBrief =
                "Inspect the parabolic curve y = ax² + bx + c below — the area shaded between any two x-limits is given by its definite integral.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_int1",
                    topic = "Integration",
                    examType = "JAMB",
                    year = 2025,
                    question = "Evaluate ∫ (6x² − 4x + 1) dx.",
                    options = listOf("2x³ − 2x² + x + C", "3x³ − 2x² + x + C", "12x − 4 + C", "6x³ − 4x² + x + C"),
                    correctIndex = 0,
                    hint = "Raise power by 1 and divide: 6x³/3 = 2x³.",
                    stepByStepSolution = "6(x³/3) − 4(x²/2) + x + C = 2x³ − 2x² + x + C.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_int2",
                    topic = "Integration",
                    examType = "WAEC",
                    year = 2023,
                    question = "Evaluate ∫₀² (3x² + 1) dx.",
                    options = listOf("8", "10", "12", "14"),
                    correctIndex = 1,
                    hint = "F(x) = x³ + x.",
                    stepByStepSolution = "F(2) − F(0) = (2³ + 2) − 0 = 8 + 2 = 10.",
                    waecMarkingScheme = "M1: [x³ + x]₀²; A1: 10.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_int3",
                    topic = "Kinematics Calculus",
                    examType = "JAMB",
                    year = 2022,
                    question = "A particle moves with velocity v = 4t + 3 m/s from t = 0. Find distance covered in the first 2 seconds.",
                    options = listOf("10 m", "11 m", "14 m", "18 m"),
                    correctIndex = 2,
                    hint = "s = ∫₀² (4t + 3) dt.",
                    stepByStepSolution = "[2t² + 3t]₀² = 2(4) + 3(2) = 8 + 6 = 14 m.",
                  ),
                ),
            ),
            Lesson(
              id = "math_11_prob_stats",
              moduleId = "math_mod_4",
              subjectId = "mathematics",
              title = "Permutations, Combinations, Probability & Statistics",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. V.1–V.3 • WAEC Probability & Stats",
              learningOutcomes =
                listOf(
                  "Distinguish ordered nPr from unordered nCr and solve conditional 'without replacement' draws.",
                  "Compute Mean, Median, Mode, Variance, and Standard Deviation cleanly.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: nPr vs nCr & Probability Laws",
                    explanation =
                      "Use nPr when positions/ranks differ; use nCr for committees where internal order doesn't matter. Use P(at least 1) = 1 − P(none) for multi-draw questions.",
                    keyFormula = "^nP_r = \\frac{n!}{(n-r)!}   |   ^nC_r = \\frac{n!}{r!(n-r)!}   |   P(\\text{at least 1}) = 1 - P(\\text{none})",
                    examTip =
                      "Repeated-letter anagrams (e.g., BANANA) divide n! by factorials of every repeated letter count!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Measures of Dispersion (Range, Variance, SD)",
                    explanation =
                      "Adding a constant k to every score raises Mean by k, leaving Standard Deviation (SD) UNCHANGED! Multiplying every score by k multiplies BOTH Mean and SD by k (and Variance by k²).",
                    keyFormula = "\\sigma = \\sqrt{\\frac{\\sum (x - \\bar{x})^2}{n}}   |   \\text{Variance} = \\sigma^2",
                    examTip =
                      "Standard Deviation is ALWAYS the positive square root of Variance!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Committee Selection & Repeated Letters",
                    examSource = "JAMB UTME 2023 • Mathematics Q36",
                    problemStatement =
                      "From 5 boys and 4 girls, in how many ways can a committee of 3 boys and 2 girls be selected?",
                    steps =
                      listOf(
                        "Step 1 — Boys: ⁵C₃ = 10. Girls: ⁴C₂ = 6.",
                        "Step 2 — Multiply for 'AND': 10 × 6 = 60 ways.",
                      ),
                    finalAnswer = "60 ways",
                    examinerTrap = "Multiply independent choices ('AND'); never add 10 + 6!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "JAMB CBT Randomized Exam Question Permutations",
                    industryDomain = "Assessment Engineering",
                    narrative =
                      "Exam engines generate thousands of unique question orderings using nPr so adjacent candidates face non-identical tests.",
                    examConnection = "Illustrates ordered permutations vs unordered selections.",
                  ),
                ),
              labType = PracticalLabType.QUADRATIC_TANGENT,
              labBrief =
                "Inspect how variance curves behave around mean centers in the interactive plotter below.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_p1",
                    topic = "Probability",
                    examType = "WAEC",
                    year = 2024,
                    question = "A bag holds 4 red and 6 blue balls. Two are drawn without replacement. Find P(both red).",
                    options = listOf("4/25", "2/15", "6/25", "1/5"),
                    correctIndex = 1,
                    hint = "(4/10) × (3/9).",
                    stepByStepSolution = "12 / 90 = 2 / 15.",
                    waecMarkingScheme = "M1: (4/10)×(3/9); A1: 2/15.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_p2",
                    topic = "Combinatorics",
                    examType = "JAMB",
                    year = 2023,
                    question = "In how many distinct ways can the letters of 'BANANA' be arranged?",
                    options = listOf("720", "120", "60", "30"),
                    correctIndex = 2,
                    hint = "6! / (3! × 2!).",
                    stepByStepSolution = "720 / (6 × 2) = 60.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_p3",
                    topic = "Statistics",
                    examType = "JAMB",
                    year = 2022,
                    question = "The variance of a dataset is 16. If every observation is multiplied by 3, what is the new Standard Deviation?",
                    options = listOf("4", "12", "48", "144"),
                    correctIndex = 1,
                    hint = "Old SD = √16 = 4; scaling data by 3 multiplies SD by 3.",
                    stepByStepSolution = "Old SD = 4 => New SD = 3 × 4 = 12.",
                  ),
                ),
            ),
          ),
      ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion(
        id = "pq_math_2025_1",
        topic = "Calculus",
        examType = "JAMB",
        year = 2025,
        question = "Find the minimum value of y = x² − 6x + 11.",
        options = listOf("2", "3", "5", "11"),
        correctIndex = 0,
        hint = "Set dy/dx = 0.",
        stepByStepSolution = "2x − 6 = 0 => x = 3. Min y = 9 − 18 + 11 = 2.",
      ),
      PracticeQuestion(
        id = "pq_math_2025_2",
        topic = "Number Bases",
        examType = "WAEC",
        year = 2025,
        question = "If 124_n = 232_5, find the value of base n.",
        options = listOf("6", "7", "8", "9"),
        correctIndex = 2,
        hint = "Convert 232_5 to Base 10 first: 2(25) + 3(5) + 2 = 67.",
        stepByStepSolution = "n² + 2n + 4 = 67 => n² + 2n − 63 = 0 => (n + 9)(n − 7) = 0? Wait: 67−4=63 => n = 7! Check: 7²+14+4=49+18=67. Base 7 -> index 1.",
        waecMarkingScheme = "M1: Expand both sides to base 10; A1: n = 7.",
      ).let {
        // Wait: verify arithmetic carefully! 7² + 2(7) + 4 = 49 + 14 + 4 = 67! Let's fix correctIndex = 1 ("7")!
        it.copy(
          correctIndex = 1,
          stepByStepSolution = "232_5 = 2(25) + 3(5) + 2 = 67. Thus n² + 2n + 4 = 67 => n² + 2n − 63 = 0 => (n − 7)(n + 9) = 0 => n = 7.",
        )
      },
      PracticeQuestion(
        id = "pq_math_2024_1",
        topic = "Quadratics",
        examType = "WAEC",
        year = 2024,
        question = "Find the quadratic equation whose roots are 3/2 and −4.",
        options = listOf("2x² + 5x − 12 = 0", "2x² − 5x − 12 = 0", "2x² + 5x + 12 = 0", "x² + 5x − 6 = 0"),
        correctIndex = 0,
        hint = "Sum = 3/2 − 4 = −5/2; Product = −6.",
        stepByStepSolution = "x² − (−5/2)x − 6 = 0 => 2x² + 5x − 12 = 0.",
        waecMarkingScheme = "M1: Sum & Product; A1: 2x² + 5x − 12 = 0.",
      ),
      PracticeQuestion(
        id = "pq_math_2024_2",
        topic = "Matrices",
        examType = "JAMB",
        year = 2024,
        question = "If matrix A = [[3, 2], [k, 4]] is singular, find the value of k.",
        options = listOf("3", "6", "8", "12"),
        correctIndex = 1,
        hint = "A singular matrix has determinant ad − bc = 0.",
        stepByStepSolution = "(3)(4) − (2)(k) = 0 => 12 = 2k => k = 6.",
      ),
      PracticeQuestion(
        id = "pq_math_2023_1",
        topic = "Logarithms",
        examType = "JAMB",
        year = 2023,
        question = "Evaluate log₂ 32 + log₃ 9 − log₅ 1.",
        options = listOf("5", "6", "7", "8"),
        correctIndex = 2,
        hint = "32 = 2⁵, 9 = 3², and log₅(1) = 0.",
        stepByStepSolution = "5 + 2 − 0 = 7.",
      ),
      PracticeQuestion(
        id = "pq_math_2023_2",
        topic = "Trigonometry",
        examType = "WAEC",
        year = 2023,
        question = "In ΔABC, a = 6 cm, b = 10 cm, and ∠C = 60°. Find side c.",
        options = listOf("7 cm", "2√19 cm", "8 cm", "√76 cm"),
        correctIndex = 1,
        hint = "Cosine Rule: c² = a² + b² − 2ab·cos(60°), where cos 60° = 0.5.",
        stepByStepSolution = "c² = 36 + 100 − 2(6)(10)(0.5) = 136 − 60 = 76 => c = √76 = 2√19 cm.",
        waecMarkingScheme = "M1: Cosine rule; A1: 2√19 cm.",
      ),
      PracticeQuestion(
        id = "pq_math_2022_1",
        topic = "Probability",
        examType = "JAMB",
        year = 2022,
        question = "A fair die is tossed once. Find the probability of obtaining a prime OR even number.",
        options = listOf("1/2", "2/3", "5/6", "1"),
        correctIndex = 2,
        hint = "Primes = {2, 3, 5}; Evens = {2, 4, 6}; Union = {2, 3, 4, 5, 6}.",
        stepByStepSolution = "5 outcomes out of 6 => 5/6.",
      ),
      PracticeQuestion(
        id = "pq_math_2022_2",
        topic = "Mensuration",
        examType = "WAEC",
        year = 2022,
        question = "Calculate the curved surface area of a cylinder of radius 7 cm and height 10 cm (π = 22/7).",
        options = listOf("220 cm²", "440 cm²", "616 cm²", "1540 cm²"),
        correctIndex = 1,
        hint = "Curved area = 2πrh.",
        stepByStepSolution = "2 × (22/7) × 7 × 10 = 44 × 10 = 440 cm².",
        waecMarkingScheme = "M1: 2πrh; A1: 440 cm².",
      ),
      PracticeQuestion(
        id = "pq_math_2021_1",
        topic = "Sequences",
        examType = "JAMB",
        year = 2021,
        question = "Find the sum of the first 10 terms of the AP: 2, 5, 8, ...",
        options = listOf("145", "150", "155", "160"),
        correctIndex = 2,
        hint = "a = 2, d = 3, n = 10.",
        stepByStepSolution = "S₁₀ = (10/2)[2(2) + 9(3)] = 5(4 + 27) = 5 × 31 = 155.",
      ),
      PracticeQuestion(
        id = "pq_math_2021_2",
        topic = "Integration",
        examType = "WAEC",
        year = 2021,
        question = "Find the equation of a curve whose gradient at (x, y) is 4x − 3 and which passes through (1, 2).",
        options = listOf("y = 2x² − 3x + 3", "y = 2x² − 3x + 1", "y = 4x² − 3x + 1", "y = 2x² + 3x − 3"),
        correctIndex = 0,
        hint = "Integrate dy/dx to get y = 2x² − 3x + C, then plug x = 1, y = 2.",
        stepByStepSolution = "2 = 2(1) − 3(1) + C => C = 3. Hence y = 2x² − 3x + 3.",
        waecMarkingScheme = "M1: Integrate; A1: C = 3; A1: Equation.",
      ),
      PracticeQuestion(
        id = "pq_math_2020_1",
        topic = "Coordinate Geometry",
        examType = "JAMB",
        year = 2020,
        question = "Find the midpoint of the line joining A(−4, 7) and B(8, −3).",
        options = listOf("(2, 2)", "(6, 5)", "(2, 5)", "(4, 4)"),
        correctIndex = 0,
        hint = "Average x and y coordinates.",
        stepByStepSolution = "((−4+8)/2, (7−3)/2) = (4/2, 4/2) = (2, 2).",
      ),
      PracticeQuestion(
        id = "pq_math_2019_1",
        topic = "Statistics",
        examType = "WAEC",
        year = 2019,
        question = "The mean of 5 numbers is 14. When a 6th number is included, the mean becomes 16. Find the 6th number.",
        options = listOf("18", "22", "24", "26"),
        correctIndex = 3,
        hint = "Total of 6 − Total of 5.",
        stepByStepSolution = "(6 × 16) − (5 × 14) = 96 − 70 = 26.",
        waecMarkingScheme = "M1: 96 − 70; A1: 26.",
      ),
      PracticeQuestion(
        id = "pq_math_2018_1",
        topic = "Indices",
        examType = "JAMB",
        year = 2018,
        question = "Simplify 27^{2/3} × 16^{-1/2}.",
        options = listOf("9/4", "9/2", "3/4", "36"),
        correctIndex = 0,
        hint = "27^{2/3} = 3² = 9; 16^{-1/2} = 1/4.",
        stepByStepSolution = "9 × (1/4) = 9/4.",
      ),
      PracticeQuestion(
        id = "pq_math_2018_2",
        topic = "Geometry",
        examType = "WAEC",
        year = 2018,
        question = "An arc of a circle of radius 14 cm subtends 90° at the center. Find its length (π = 22/7).",
        options = listOf("11 cm", "22 cm", "44 cm", "154 cm"),
        correctIndex = 1,
        hint = "Arc = (θ/360) × 2πr.",
        stepByStepSolution = "(90/360) × 2 × (22/7) × 14 = (1/4) × 88 = 22 cm.",
        waecMarkingScheme = "M1: (90/360)×2πr; A1: 22 cm.",
      ),
    )
}
