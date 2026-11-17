package com.example.data.curriculum

object MathematicsCurriculum {

  val track =
    SubjectTrack(
      id = "mathematics",
      title = "Mathematics Specialization",
      shortName = "Math",
      tagline = "Zero-to-distinction mastery in Number Systems, Algebra, Trigonometry, Calculus & Probability",
      examCoverage = "JAMB 40 Qs • WAEC Paper 1 & Paper 2 Theory",
      modules =
        listOf(
          CourseModule(
            id = "math_mod_1",
            subjectId = "mathematics",
            indexLabel = "Module 01",
            title = "Number Bases, Indices, Logarithms & Sequences",
            summary = "Start from arithmetic zero: convert number bases, simplify surds/indices/logs, and sum AP/GP series.",
            lessons =
              listOf(
                Lesson(
                  id = "math_01_bases_surds",
                  moduleId = "math_mod_1",
                  subjectId = "mathematics",
                  title = "Number Bases, Modular Arithmetic & Surds",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. I.1–I.2 • WAEC Number Systems",
                  learningOutcomes =
                    listOf(
                      "Convert numbers smoothly between Base 10 and any Base b (2, 5, 8, etc.).",
                      "Apply modular arithmetic (mod m) and simplify/rationalize surds cleanly.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1 (Zero Knowledge): Place Value in Any Base b",
                        explanation =
                          "Everyday counting uses Base 10 (digits 0–9, powers 10⁰, 10¹, 10²). Binary (Base 2) uses only digits 0 and 1 (powers 2⁰, 2¹, 2²). To convert ANY base b to Base 10, expand digits × powers of b from right to left. To convert Base 10 to Base b, repeatedly divide by b and read remainders UPWARD.",
                        keyFormula = "(d_2 d_1 d_0)_b = d_2 b^2 + d_1 b^1 + d_0 b^0",
                        examTip =
                          "Digits inside a Base b number must ALWAYS be strictly less than b (e.g., '38₅' is impossible!).",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Conjugate Surd Rationalization",
                        explanation =
                          "A surd like √5 or √3 cannot simplify to an exact fraction. Never leave a square root in a denominator! Multiply numerator and denominator by the CONJUGATE (flip the middle sign: conjugate of a + √b is a − √b) so (a + √b)(a − √b) = a² − b becomes rational.",
                        keyFormula = "\\frac{1}{a + \\sqrt{b}} = \\frac{a - \\sqrt{b}}{a^2 - b}   |   \\sqrt{ab} = \\sqrt{a}\\cdot\\sqrt{b}",
                        examTip =
                          "Always simplify √75 = √(25×3) = 5√3 BEFORE adding or subtracting surd terms!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Solving Unknown Base Equation",
                        examSource = "JAMB UTME 2024 • Mathematics Q2",
                        problemStatement = "Find the positive integer base x if 23ₓ + 14ₓ = 42ₓ (or 34ₓ = 25₁₀).",
                        steps =
                          listOf(
                            "Step 1 — Expand 34ₓ into Base 10: 3x¹ + 4x⁰ = 3x + 4.",
                            "Step 2 — Equate to 25: 3x + 4 = 25 => 3x = 21 => x = 7.",
                          ),
                        finalAnswer = "x = 7 (Base 7)",
                        examinerTrap = "Always verify your answer x exceeds every digit inside the problem (here 7 > 4).",
                      ),
                      WorkedExample(
                        title = "Rationalizing Surd Fractions",
                        examSource = "WAEC SSCE 2023 • Paper 1 Q8",
                        problemStatement = "Express 6 / (√5 − √2) in the form a√5 + b√2.",
                        steps =
                          listOf(
                            "Step 1 — Multiply top and bottom by conjugate (√5 + √2).",
                            "Step 2 — Denominator = (√5)² − (√2)² = 5 − 2 = 3.",
                            "Step 3 — 6(√5 + √2) / 3 = 2(√5 + √2) = 2√5 + 2√2.",
                          ),
                        finalAnswer = "2√5 + 2√2",
                        examinerTrap = "Never distribute before checking if the denominator cancels cleanly with the numerator!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Computer Memory Binary & Clock Modular Arithmetic",
                        industryDomain = "Computer Science & Cryptography",
                        narrative =
                          "Every mobile processor stores data in Base 2 bits, while 12-hour/24-hour clocks and bank OTP encryption rely on modular remainders (mod m).",
                        examConnection = "Directly underpins JAMB Base 2 addition/multiplication & mod arithmetic.",
                      ),
                      RealLifeScenario(
                        title = "AC Electrical Phasor Ratios",
                        industryDomain = "Electrical Engineering",
                        narrative =
                          "Three-phase power calculations simplify √3 denominators exactly via conjugate rationalization.",
                        examConnection = "Connects surd simplification to Physics impedance problems.",
                      ),
                    ),
                  labType = PracticalLabType.QUADRATIC_TANGENT,
                  labBrief =
                    "Use the Graph Explorer below to inspect how real roots x = (−b ± √Δ)/(2a) naturally emerge as surds whenever Δ is not a perfect square!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_math_b1",
                        topic = "Number Bases",
                        examType = "JAMB",
                        year = 2024,
                        question = "Convert 11011₂ to Base 10.",
                        options = listOf("23", "25", "27", "29"),
                        correctIndex = 2,
                        hint = "Expand 1×2⁴ + 1×2³ + 0×2² + 1×2¹ + 1×2⁰.",
                        stepByStepSolution = "16 + 8 + 0 + 2 + 1 = 27₁₀.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_b2",
                        topic = "Surds",
                        examType = "WAEC",
                        year = 2023,
                        question = "Simplify √75 − √48 + √12.",
                        options = listOf("3√3", "5√3", "7√3", "√3"),
                        correctIndex = 0,
                        hint = "Factor out the perfect squares 25, 16, and 4.",
                        stepByStepSolution = "5√3 − 4√3 + 2√3 = (5 − 4 + 2)√3 = 3√3.",
                        waecMarkingScheme = "M1: Express all as multiples of √3; A1: 3√3.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_b3",
                        topic = "Modular Arithmetic",
                        examType = "JAMB",
                        year = 2023,
                        question = "Find the least positive value of x such that 2x + 5 ≡ 1 (mod 6).",
                        options = listOf("1", "2", "4", "5"),
                        correctIndex = 0,
                        hint = "Test x = 1: 2(1) + 5 = 7 ≡ 1 (mod 6).",
                        stepByStepSolution = "2x ≡ −4 ≡ 2 (mod 6). Testing integers gives x = 1 (since 7 = 6×1 + 1).",
                      ),
                    ),
                ),
                Lesson(
                  id = "math_02_indices_logs",
                  moduleId = "math_mod_1",
                  subjectId = "mathematics",
                  title = "Laws of Indices, Exponential Equations & Logarithms",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. I.3 • WAEC Indices & Logs",
                  learningOutcomes =
                    listOf(
                      "Master zero, negative, and fractional indices (a^{p/q} = (q√a)^p).",
                      "Switch seamlessly between exponential form b^x = y and log form log_b(y) = x.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Index Rules Build From Repeated Multiplication",
                        explanation =
                          "x³ means x·x·x. Multiplying same bases adds powers (xᵃ·xᵇ = xᵃ⁺ᵇ); dividing subtracts powers (xᵃ/xᵇ = xᵃ⁻ᵇ). A negative exponent means reciprocal (x⁻ⁿ = 1/xⁿ), and a fractional exponent m/n means 'nth root raised to power m'!",
                        keyFormula = "a^m \\cdot a^n = a^{m+n}   |   a^{-n} = \\frac{1}{a^n}   |   a^{m/n} = (\\sqrt[n]{a})^m",
                        examTip =
                          "Notice 27^{-2/3}: take the cube root FIRST (³√27 = 3), flip for minus (1/3), then square => 1/9!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Logarithms Are Inverted Exponents",
                        explanation =
                          "Asking 'log₂(8) = ?' simply asks '2 raised to what power gives 8?' Answer: 3! Because logs are powers, log of a product splits into SUM of logs, and log of a quotient splits into DIFFERENCE of logs.",
                        keyFormula = "\\log_b(MN) = \\log_b M + \\log_b N   |   \\log_b\\left(\\frac{M}{N}\\right) = \\log_b M - \\log_b N   |   \\log_b(M^k) = k\\log_b M",
                        examTip =
                          "Watch for log₁₀(10) = 1 and log_b(1) = 0! Never fall for the trap log(M + N) = log M + log N (FALSE!).",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Exponential Substitution Equation",
                        examSource = "JAMB UTME 2023 • Mathematics Q6",
                        problemStatement = "Solve for x: 9^{x+1} = 27^{2x−1}.",
                        steps =
                          listOf(
                            "Step 1 — Convert both sides to common base 3: 9 = 3², 27 = 3³.",
                            "Step 2 — 3^{2(x+1)} = 3^{3(2x−1)} => Equate powers: 2x + 2 = 6x − 3.",
                            "Step 3 — 5 = 4x => x = 5/4.",
                          ),
                        finalAnswer = "x = 5/4 (1.25)",
                        examinerTrap = "Multiply the outer exponent by BOTH terms inside (x + 1), not just x!",
                      ),
                      WorkedExample(
                        title = "Logarithmic Equation Solving",
                        examSource = "WAEC SSCE 2022 • Paper 2 Q3",
                        problemStatement = "Solve log₁₀(x + 6) − log₁₀(x − 3) = 1.",
                        steps =
                          listOf(
                            "Step 1 — Combine quotient: log₁₀((x + 6)/(x − 3)) = 1.",
                            "Step 2 — Convert from log to exponential (base 10): (x + 6)/(x − 3) = 10¹ = 10.",
                            "Step 3 — x + 6 = 10x − 30 => 9x = 36 => x = 4.",
                          ),
                        finalAnswer = "x = 4",
                        examinerTrap = "Write 1 = log₁₀(10), NOT log₁₀(1)! Always check domain x − 3 > 0.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Chemistry pH & Earthquake Richter Scales",
                        industryDomain = "Analytical Science & Geophysics",
                        narrative =
                          "Every +1 jump on the pH or Richter scale represents a 10× jump in H⁺ concentration or seismic energy via log₁₀ compression.",
                        examConnection = "Bridges Chemistry pH = −log₁₀[H⁺] with Math log laws.",
                      ),
                      RealLifeScenario(
                        title = "Compound Interest & Population Doubling",
                        industryDomain = "Banking & Actuarial Science",
                        narrative =
                          "Solving A = P(1 + r)^n for years n requires taking logarithms on both sides using log(M^k) = k·log(M).",
                        examConnection = "Standard WAEC financial growth word problem.",
                      ),
                    ),
                  labType = PracticalLabType.TITRATION_PH,
                  labBrief =
                    "Run the pH Titration Lab below to watch how every 10× dilution of [H⁺] shifts pH = −log₁₀[H⁺] by exactly 1.0 unit!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_math_i1",
                        topic = "Indices",
                        examType = "JAMB",
                        year = 2025,
                        question = "Evaluate (16/81)^{-3/4}.",
                        options = listOf("8/27", "27/8", "16/27", "81/16"),
                        correctIndex = 1,
                        hint = "Negative flips the fraction to (81/16)^{3/4}; 4th root of 81 is 3 and 16 is 2.",
                        stepByStepSolution = "(81/16)^{3/4} = (3/2)³ = 27/8.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_i2",
                        topic = "Logarithms",
                        examType = "WAEC",
                        year = 2023,
                        question = "If log₁₀ 2 = 0.3010 and log₁₀ 3 = 0.4771, evaluate log₁₀ 12.",
                        options = listOf("0.7781", "0.9542", "1.0791", "1.2552"),
                        correctIndex = 2,
                        hint = "12 = 2² × 3 => log 12 = 2 log 2 + log 3.",
                        stepByStepSolution = "2(0.3010) + 0.4771 = 0.6020 + 0.4771 = 1.0791.",
                        waecMarkingScheme = "M1: log(2²×3) = 2log2 + log3; A1: 1.0791.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_i3",
                        topic = "Indices",
                        examType = "JAMB",
                        year = 2024,
                        question = "If 8^x = 32, find x.",
                        options = listOf("3/5", "5/3", "4", "5/2"),
                        correctIndex = 1,
                        hint = "Write 8 = 2³ and 32 = 2⁵.",
                        stepByStepSolution = "2^{3x} = 2^5 => 3x = 5 => x = 5/3.",
                      ),
                    ),
                ),
                Lesson(
                  id = "math_03_sets_sequences",
                  moduleId = "math_mod_1",
                  subjectId = "mathematics",
                  title = "Sets, Venn Diagrams & AP / GP Sequences",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. I.5 & II.2 • WAEC Sets & Series",
                  learningOutcomes =
                    listOf(
                      "Solve 2-set and 3-set Venn diagram cardinality problems accurately.",
                      "Find nth terms and sums for Arithmetic (AP) and Geometric (GP) Progressions including S_∞.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Sets & Two-Circle Inclusion-Exclusion",
                        explanation =
                          "Draw overlapping Venn circles from the INSIDE OUT: put the intersection n(A ∩ B) FIRST, then subtract it from n(A) and n(B) to get 'A only' and 'B only'!",
                        keyFormula = "n(A \\cup B) = n(A) + n(B) - n(A \\cap B)",
                        examTip =
                          "Always verify whether 'neither A nor B' exists outside the circles before equating to Universal Set U!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Arithmetic (AP) vs Geometric (GP) Progressions",
                        explanation =
                          "An AP adds a constant difference d = T₂ − T₁ each step; a GP multiplies by a constant ratio r = T₂ / T₁. When |r| < 1, an infinite GP converges to a finite sum S_∞!",
                        keyFormula = "AP: T_n = a + (n-1)d   |   S_n = \\frac{n}{2}[2a + (n-1)d]   |   GP: T_n = ar^{n-1},\\; S_\\infty = \\frac{a}{1-r}",
                        examTip =
                          "Notice a shortcut: in any AP, Sum = n × (First + Last) / 2! And for infinite GP, check |r| < 1 first.",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Two-Subject Class Venn Diagram",
                        examSource = "WAEC SSCE 2024 • Paper 2 Q1",
                        problemStatement =
                          "In a class of 50 students, 32 offer Physics, 28 offer Chemistry, and 5 offer neither. How many offer BOTH subjects?",
                        steps =
                          listOf(
                            "Step 1 — Students offering at least one: n(P ∪ C) = 50 − 5 = 45.",
                            "Step 2 — 45 = 32 + 28 − x => 45 = 60 − x => x = 15 students.",
                          ),
                        finalAnswer = "15 students",
                        examinerTrap = "Subtract the 5 offering 'neither' from 50 BEFORE applying n(A ∪ B)!",
                      ),
                      WorkedExample(
                        title = "Sum to Infinity of a Geometric Series",
                        examSource = "JAMB UTME 2023 • Mathematics Q15",
                        problemStatement = "Find the sum to infinity of the GP: 12, 4, 4/3, ...",
                        steps =
                          listOf(
                            "Step 1 — First term a = 12; common ratio r = 4 / 12 = 1/3 (|r| < 1).",
                            "Step 2 — S_∞ = a / (1 − r) = 12 / (1 − 1/3) = 12 / (2/3) = 18.",
                          ),
                        finalAnswer = "18",
                        examinerTrap = "Make sure r = T₂ / T₁, not T₁ / T₂!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Loan Installments & Depreciation Tables",
                        industryDomain = "Personal Finance",
                        narrative =
                          "Fixed monthly principal paydowns form an AP, while annual vehicle value depreciation (losing 15% per year) follows a GP with r = 0.85.",
                        examConnection = "Tests T_n = ar^{n−1} compound depreciation questions.",
                      ),
                      RealLifeScenario(
                        title = "University Admission Screening Cohort Overlap",
                        industryDomain = "Data & Admissions Analytics",
                        narrative =
                          "Admissions offices use Venn set intersections to count candidates meeting both UTME ≥ 250 AND 5 O'Level credits.",
                        examConnection = "Direct real-world match for WAEC Venn diagram sets.",
                      ),
                    ),
                  labType = PracticalLabType.QUADRATIC_TANGENT,
                  labBrief =
                    "Notice how partial sums of an AP (S_n = (d/2)n² + ...) form a quadratic parabola plotted in the graph explorer below!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_math_s1",
                        topic = "Sequences",
                        examType = "JAMB",
                        year = 2024,
                        question = "The 3rd term of an AP is 11 and the 7th term is 27. Find the first term and common difference.",
                        options = listOf("a=3, d=4", "a=4, d=3", "a=2, d=4", "a=3, d=5"),
                        correctIndex = 0,
                        hint = "T₇ − T₃ = 4d = 27 − 11.",
                        stepByStepSolution = "4d = 16 => d = 4. Then a + 2(4) = 11 => a = 3.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_s2",
                        topic = "Sets",
                        examType = "WAEC",
                        year = 2022,
                        question = "If P = {factors of 12} and Q = {even numbers < 10}, find n(P ∩ Q).",
                        options = listOf("2", "3", "4", "5"),
                        correctIndex = 1,
                        hint = "P = {1,2,3,4,6,12}, Q = {2,4,6,8}.",
                        stepByStepSolution = "P ∩ Q = {2, 4, 6}, so n(P ∩ Q) = 3.",
                        waecMarkingScheme = "M1: List P and Q; A1: 3.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_s3",
                        topic = "Sequences",
                        examType = "JAMB",
                        year = 2025,
                        question = "Find the geometric mean between 4 and 36.",
                        options = listOf("12", "16", "20", "24"),
                        correctIndex = 0,
                        hint = "For GP (4, x, 36), x² = 4 × 36.",
                        stepByStepSolution = "x = √(4 × 36) = √144 = 12 (positive geometric mean).",
                      ),
                    ),
                ),
              ),
          ),
          CourseModule(
            id = "math_mod_2",
            subjectId = "mathematics",
            indexLabel = "Module 02",
            title = "Algebra, Quadratics, Variation & Inequalities",
            summary = "Build algebraic fluency from variation ratios to quadratic roots, simultaneous systems, and inequalities.",
            lessons =
              listOf(
                Lesson(
                  id = "math_04_variation",
                  moduleId = "math_mod_2",
                  subjectId = "mathematics",
                  title = "Algebraic Factorisation & Joint/Partial Variation",
                  durationMinutes = 18,
                  syllabusTag = "JAMB Sec. II.1 • WAEC Algebra",
                  learningOutcomes =
                    listOf(
                      "Factorize difference of two squares and change formula subjects confidently.",
                      "Set up Direct, Inverse, Joint, and Partial Variation equations accurately.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Difference of Squares & Formula Transposition",
                        explanation =
                          "Always look for common factors first, then spot x² − y² = (x − y)(x + y). When changing the subject of a formula, collect every term containing the target variable onto ONE side and factorize it out.",
                        keyFormula = "a^2 - b^2 = (a - b)(a + b)   |   (a + b)^2 = a^2 + 2ab + b^2",
                        examTip =
                          "Compute 103² − 97² mentally in 3 seconds: (103 − 97)(103 + 97) = 6 × 200 = 1200!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Direct, Inverse & Partial Variation",
                        explanation =
                          "'y varies directly as x' -> y = kx. 'y varies inversely as x²' -> y = k/x². 'y is partly constant and partly varies as x' -> y = a + bx (uses TWO constants a and b solved via simultaneous equations!).",
                        keyFormula = "y = kx   |   y = \\frac{k}{x^2}   |   y = a + bx",
                        examTip =
                          "Partial variation ALWAYS has TWO parameters (a + bx) and requires TWO data pairs to solve!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Partial Variation Cost Model",
                        examSource = "WAEC SSCE 2023 • Paper 2 Q4",
                        problemStatement =
                          "The cost C of hosting n guests is partly constant and partly varies directly as n. Cost is ₦14,000 for 20 guests and ₦22,000 for 40 guests. Find C for 50 guests.",
                        steps =
                          listOf(
                            "Step 1 — Write law: C = a + bn.",
                            "Step 2 — Equations: (1) a + 20b = 14,000; (2) a + 40b = 22,000.",
                            "Step 3 — Subtract (2)−(1): 20b = 8,000 => b = 400. Then a = 14,000 − 8,000 = 6,000.",
                            "Step 4 — For n = 50: C = 6,000 + 400(50) = ₦26,000.",
                          ),
                        finalAnswer = "₦26,000",
                        examinerTrap = "Do NOT confuse 'partly constant' (a + bn) with pure direct variation (kn)!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Ride-Hailing Base Fare + Per-Km Pricing",
                        industryDomain = "Mobility & Pricing",
                        narrative =
                          "Ride-hailing apps charge a fixed flag-drop fee plus a rate proportional to distance d: Fare = a + b·d (classic partial variation).",
                        examConnection = "Directly mirrors WAEC partial variation word problems.",
                      ),
                    ),
                  labType = PracticalLabType.QUADRATIC_TANGENT,
                  labBrief =
                    "Set a = 0 in the Graph Explorer below to observe how linear variation y = bx + c forms a straight line with constant slope b.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_math_v1",
                        topic = "Variation",
                        examType = "JAMB",
                        year = 2024,
                        question = "y varies inversely as x². When x = 2, y = 9. Find y when x = 3.",
                        options = listOf("4", "6", "12", "36"),
                        correctIndex = 0,
                        hint = "Find k = y·x² first.",
                        stepByStepSolution = "k = 9 × 2² = 36. When x = 3, y = 36 / 3² = 36 / 9 = 4.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_v2",
                        topic = "Factorisation",
                        examType = "WAEC",
                        year = 2022,
                        question = "Simplify (x² − 9) / (x² − 5x + 6).",
                        options = listOf("(x+3)/(x−2)", "(x−3)/(x+2)", "(x+3)/(x+2)", "(x−3)/(x−2)"),
                        correctIndex = 0,
                        hint = "Factorize numerator (x−3)(x+3) and denominator (x−3)(x−2).",
                        stepByStepSolution = "Cancel (x − 3) to leave (x + 3) / (x − 2).",
                        waecMarkingScheme = "M1: Factorize both; A1: (x+3)/(x−2).",
                      ),
                      PracticeQuestion(
                        id = "ex_math_v3",
                        topic = "Formula Transposition",
                        examType = "JAMB",
                        year = 2023,
                        question = "Make u the subject of the formula 1/f = 1/u + 1/v.",
                        options = listOf("fv / (v − f)", "fv / (f − v)", "(v − f) / fv", "f + v"),
                        correctIndex = 0,
                        hint = "1/u = 1/f − 1/v = (v − f) / fv.",
                        stepByStepSolution = "1/u = (v − f) / fv => Invert both sides: u = fv / (v − f).",
                      ),
                    ),
                ),
                Lesson(
                  id = "math_05_quadratics",
                  moduleId = "math_mod_2",
                  subjectId = "mathematics",
                  title = "Quadratic Roots, Discriminant & Vieta's Theorem",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. II.3 • WAEC Quadratics",
                  learningOutcomes =
                    listOf(
                      "Use Δ = b² − 4ac to classify real, equal, or complex roots without solving.",
                      "Evaluate symmetric expressions α + β, αβ, α² + β², and build equations from given roots.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Discriminant Δ & Nature of Roots",
                        explanation =
                          "In ax² + bx + c = 0 (a ≠ 0), the discriminant Δ = b² − 4ac dictates x-axis intersections: Δ > 0 gives 2 distinct real roots; Δ = 0 gives 1 repeated (equal) root where the parabola just kisses the x-axis; Δ < 0 gives no real roots.",
                        keyFormula = "\\Delta = b^2 - 4ac   |   x = \\frac{-b \\pm \\sqrt{b^2 - 4ac}}{2a}",
                        examTip =
                          "'Tangent to x-axis' or 'equal roots' ALWAYS triggers setting b² − 4ac = 0!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Vieta's Root Sum/Product Shortcut",
                        explanation =
                          "If α and β are roots of ax² + bx + c = 0, we never need the quadratic formula to evaluate expressions in α and β! Simply read α + β = −b/a and αβ = c/a.",
                        keyFormula = "\\alpha + \\beta = -\\frac{b}{a}   |   \\alpha\\beta = \\frac{c}{a}   |   \\alpha^2 + \\beta^2 = (\\alpha + \\beta)^2 - 2\\alpha\\beta",
                        examTip =
                          "To form a quadratic equation from roots r₁ and r₂: x² − (r₁ + r₂)x + r₁r₂ = 0.",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Symmetric Root Expressions",
                        examSource = "JAMB UTME 2023 • Mathematics Q19",
                        problemStatement =
                          "If α and β are the roots of 2x² − 7x + 3 = 0, find (1/α) + (1/β) and α² + β².",
                        steps =
                          listOf(
                            "Step 1 — a = 2, b = −7, c = 3 => α + β = 7/2, αβ = 3/2.",
                            "Step 2 — 1/α + 1/β = (α + β)/(αβ) = (7/2) / (3/2) = 7/3.",
                            "Step 3 — α² + β² = (7/2)² − 2(3/2) = 49/4 − 3 = 37/4.",
                          ),
                        finalAnswer = "7/3 and 37/4",
                        examinerTrap = "Watch −b/a when b = −7: double negative makes α + β = +7/2!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Startup Unit-Price Profit Maximization",
                        industryDomain = "Business Economics",
                        narrative =
                          "Profit P(x) = −2x² + 120x − 1000 forms an inverted parabola peaking at vertex x = −b/(2a) = 30.",
                        examConnection = "Directly connects vertex formula to WAEC optimization problems.",
                      ),
                    ),
                  labType = PracticalLabType.QUADRATIC_TANGENT,
                  labBrief =
                    "Set a = 1, b = −4, c = 4 to observe Δ = 0 (equal roots touching the x-axis), then raise c to 6 to see Δ < 0 (no real roots).",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_math_q1",
                        topic = "Quadratics",
                        examType = "JAMB",
                        year = 2024,
                        question = "Find k if x² + kx + 9 = 0 has equal roots.",
                        options = listOf("3", "±3", "±6", "9"),
                        correctIndex = 2,
                        hint = "b² − 4ac = 0.",
                        stepByStepSolution = "k² − 36 = 0 => k = ±6.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_q2",
                        topic = "Quadratics",
                        examType = "WAEC",
                        year = 2023,
                        question = "What is the maximum value of y = 5 + 4x − x²?",
                        options = listOf("2", "5", "9", "11"),
                        correctIndex = 2,
                        hint = "Complete the square or use x = −b/(2a) = −4/−2 = 2.",
                        stepByStepSolution = "At x = 2, y_max = 5 + 4(2) − 2² = 5 + 8 − 4 = 9.",
                        waecMarkingScheme = "M1: x = 2; A1: y_max = 9.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_q3",
                        topic = "Quadratics",
                        examType = "JAMB",
                        year = 2022,
                        question = "If α and β are roots of 3x² − 6x + 5 = 0, find αβ(α + β).",
                        options = listOf("10/3", "−10/3", "5/3", "2"),
                        correctIndex = 0,
                        hint = "α + β = 6/3 = 2; αβ = 5/3.",
                        stepByStepSolution = "αβ(α + β) = (5/3) × 2 = 10/3.",
                      ),
                    ),
                ),
                Lesson(
                  id = "math_06_inequalities",
                  moduleId = "math_mod_2",
                  subjectId = "mathematics",
                  title = "Quadratic Inequalities, Simultaneous Systems & Remainder Theorem",
                  durationMinutes = 19,
                  syllabusTag = "JAMB Sec. II.4 • WAEC Polynomials",
                  learningOutcomes =
                    listOf(
                      "Solve linear and quadratic inequalities with sign-flipping discipline.",
                      "Apply Remainder & Factor Theorems f(a) = 0 to factorize cubic polynomials.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Quadratic Inequalities & Sign Intervals",
                        explanation =
                          "Multiplying or dividing an inequality by a NEGATIVE number flips '>' to '<'. For quadratic (x − 2)(x − 5) < 0, critical roots are 2 and 5: '< 0' selects the INSIDE interval (2 < x < 5), whereas '> 0' selects the TWO OUTSIDE wings (x < 2 or x > 5)!",
                        keyFormula = "(x - a)(x - b) < 0 \\Rightarrow a < x < b   |   (x - a)(x - b) > 0 \\Rightarrow x < a \\text{ or } x > b",
                        examTip =
                          "Never drop negative signs! Sketch a quick U-parabola crossing a and b: below x-axis = between roots.",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Remainder & Factor Theorems",
                        explanation =
                          "When polynomial f(x) is divided by (x − a), the remainder is simply f(a). If (x − a) is a FACTOR, then f(a) = 0!",
                        keyFormula = "f(x) \\div (x - a) \\text{ leaves remainder } f(a)",
                        examTip =
                          "Dividing by (2x − 1) corresponds to plugging x = 1/2 into f(x)!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Quadratic Inequality Solution Range",
                        examSource = "JAMB UTME 2024 • Mathematics Q21",
                        problemStatement = "Find the range of values of x satisfying x² − 2x − 8 ≥ 0.",
                        steps =
                          listOf(
                            "Step 1 — Factorize: (x − 4)(x + 2) ≥ 0. Roots are −2 and 4.",
                            "Step 2 — Greater-than-or-equal picks outside wings: x ≤ −2 or x ≥ 4.",
                          ),
                        finalAnswer = "x ≤ −2 or x ≥ 4",
                        examinerTrap = "Writing '−2 ≤ x ≤ 4' gives the NEGATIVE region instead of ≥ 0!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Break-Even Revenue Zones in Manufacturing",
                        industryDomain = "Operations Research",
                        narrative =
                          "Factories keep production quantity x inside the profitable region where Revenue − Cost ≥ 0 via quadratic inequality boundaries.",
                        examConnection = "Direct real-world modeling of feasible inequality intervals.",
                      ),
                    ),
                  labType = PracticalLabType.QUADRATIC_TANGENT,
                  labBrief =
                    "Set a = 1, b = −2, c = −8 in the Graph Explorer: observe where the curve sits ABOVE the x-axis (x ≤ −2 and x ≥ 4) vs BELOW (−2 < x < 4)!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_math_in1",
                        topic = "Inequalities",
                        examType = "JAMB",
                        year = 2024,
                        question = "Solve the inequality x² − 5x + 6 < 0.",
                        options = listOf("x < 2 or x > 3", "2 < x < 3", "−3 < x < −2", "x ≤ 2"),
                        correctIndex = 1,
                        hint = "(x − 2)(x − 3) < 0 takes the region between roots.",
                        stepByStepSolution = "Roots are 2 and 3. Less than 0 holds strictly between them: 2 < x < 3.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_in2",
                        topic = "Polynomials",
                        examType = "WAEC",
                        year = 2023,
                        question = "Find the remainder when f(x) = x³ − 2x² + 4x − 5 is divided by (x − 2).",
                        options = listOf("−3", "0", "3", "11"),
                        correctIndex = 2,
                        hint = "Evaluate f(2).",
                        stepByStepSolution = "f(2) = 8 − 2(4) + 4(2) − 5 = 8 − 8 + 8 − 5 = 3.",
                        waecMarkingScheme = "M1: Substitute x = 2; A1: 3.",
                      ),
                      PracticeQuestion(
                        id = "ex_math_in3",
                        topic = "Simultaneous Equations",
                        examType = "JAMB",
                        year = 2022,
                        question = "If 2x + y = 7 and x − 2y = 1, find x + y.",
                        options = listOf("3", "4", "5", "6"),
                        correctIndex = 1,
                        hint = "Double first eq: 4x + 2y = 14; add x − 2y = 1 => 5x = 15.",
                        stepByStepSolution = "x = 3; y = 7 − 6 = 1. Thus x + y = 3 + 1 = 4.",
                      ),
                    ),
                ),
              ),
          ),
        ) + MathematicsCurriculumPart2.modules,
      pastQuestions = MathematicsCurriculumPart2.pastQuestions,
    )
}
