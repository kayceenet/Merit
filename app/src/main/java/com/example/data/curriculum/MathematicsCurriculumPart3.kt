package com.example.data.curriculum

object MathematicsCurriculumPart3 {

  val module5: CourseModule =
    CourseModule(
      id = "math_mod_5",
      subjectId = "mathematics",
      indexLabel = "Module 05",
      title = "Calculus, Permutations, Probability & Statistics",
      summary = "Go from zero to mastery in Limits, Differentiation, Optimization, Integration, nPr/nCr, Probability & Grouped Statistics.",
      lessons =
        listOf(
          Lesson(
            id = "math_13_calculus",
            moduleId = "math_mod_5",
            subjectId = "mathematics",
            title = "Limits, Differentiation & Integration from Scratch",
            durationMinutes = 23,
            syllabusTag = "JAMB Sec. 4.1–4.3 • WAEC Differential & Integral Calculus",
            learningOutcomes =
              listOf(
                "Evaluate simple algebraic limits and differentiate axⁿ + sin x + cos x.",
                "Find tangent equations, stationary turning points (dy/dx = 0), and maxima/minima.",
                "Integrate polynomials ∫axⁿ dx + C and compute exact area under a curve.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. Differentiation (dy/dx): Speedometer of Slope!",
                  explanation =
                    "A straight line has one constant slope, whereas a curve's slope bends at every step. Differentiation gives you the EXACT slope at any point x! Power rule in 2 words: 'BRING DOWN, SUBTRACT 1'—multiply the front number by the power n, then drop the power to n − 1. Any lonely constant vanishes to 0 because a flat number doesn't tilt!",
                  keyFormula = "\\frac{d}{dx}(ax^n) = anx^{n-1}   |   \\text{Turning Point: } \\frac{dy}{dx} = 0   |   \\frac{d^2y}{dx^2} < 0 \\implies \\text{Max}",
                  examTip =
                    "In Kinematics: differentiate Distance s → Velocity v (ds/dt); differentiate Velocity v → Acceleration a (dv/dt)!",
                ),
                ConceptBlock(
                  heading = "2. Integration (∫): Running the Machine Backwards for Area!",
                  explanation =
                    "Integration reverses differentiation: 'ADD 1 TO POWER, DIVIDE BY NEW POWER!' And never forget + C on indefinite integrals because constants vanish during differentiation. Want the shaded Area under curve y = f(x) between x = a and x = b? Integrate f(x), plug top b, plug bottom a, and subtract F(b) − F(a)!",
                  keyFormula = "\\int ax^n\\,dx = \\frac{ax^{n+1}}{n+1} + C\\; (n \\neq -1)   |   \\text{Area} = \\int_a^b y\\,dx",
                  examTip =
                    "Recall trigonometric calculus twins: d/dx(sin kx) = k cos kx, while d/dx(cos kx) = −k sin kx!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Finding Local Maximum / Minimum Points",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement = "Find the coordinates of the minimum point on y = x² − 4x + 7.",
                  steps =
                    listOf(
                      "Step 1 — Differentiate: dy/dx = 2x − 4.",
                      "Step 2 — Set dy/dx = 0 ⇒ 2x = 4 ⇒ x = 2.",
                      "Step 3 — d²y/dx² = +2 > 0 (confirmed minimum). Plug x = 2: y = 4 − 8 + 7 = 3.",
                    ),
                  finalAnswer = "(2, 3)",
                  examinerTrap = "Returning only x = 2 when the question asks for coordinates (x, y) or minimum value y = 3!",
                ),
                WorkedExample(
                  title = "Definite Integral Area Under Parabola",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement = "Evaluate the area bounded by y = 3x² + 2, the x-axis, x = 0, and x = 2.",
                  steps =
                    listOf(
                      "Step 1 — Integrate: F(x) = x³ + 2x.",
                      "Step 2 — Evaluate F(2) − F(0) = (2³ + 2(2)) − 0 = 8 + 4 = 12.",
                    ),
                  finalAnswer = "12 sq. units",
                  examinerTrap = "Forgetting to divide by the new power (n+1): 3x³/3 simplifies cleanly to x³!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Maximizing Box Volume from a Metal Sheet",
                  industryDomain = "Packaging & Manufacturing",
                  narrative =
                    "Factories cut square corners x × x off metal sheets and fold up sides—calculus dV/dx = 0 finds the exact cut size that holds the most product.",
                  examConnection = "Classic JAMB optimization word problem (Max Area / Min Cost).",
                ),
              ),
            labType = PracticalLabType.COORDINATE_CALCULUS_LAB,
            labBrief =
              "Slide point x₀ along y = ax² + bx + c to watch the live tangent slope dy/dx = 2ax₀ + b and shaded integral area ∫ y dx!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_math_13_1",
                  topic = "Polynomial Derivative",
                  examType = "JAMB",
                  year = 2024,
                  question = "Find dy/dx if y = 3x⁴ − 5x² + 9.",
                  options = listOf("12x³ − 10x", "12x³ − 10x + 9", "3x³ − 5x", "12x⁴ − 10x²"),
                  correctIndex = 0,
                  hint = "Power rule on each term; derivative of constant 9 is 0.",
                  stepByStepSolution = "3(4)x³ − 5(2)x + 0 = 12x³ − 10x.",
                ),
                PracticeQuestion(
                  id = "ex_math_13_2",
                  topic = "Algebraic Limit",
                  examType = "WAEC",
                  year = 2023,
                  question = "Evaluate lim (x → 3) (x² − 9) / (x − 3).",
                  options = listOf("0", "3", "6", "Undefined"),
                  correctIndex = 2,
                  hint = "Factor numerator as (x − 3)(x + 3) and cancel before plugging x = 3.",
                  stepByStepSolution = "lim (x + 3) = 3 + 3 = 6.",
                ),
                PracticeQuestion(
                  id = "ex_math_13_3",
                  topic = "Definite Integral",
                  examType = "JAMB",
                  year = 2025,
                  question = "Evaluate ∫₁³ (2x + 1) dx.",
                  options = listOf("8", "10", "12", "6"),
                  correctIndex = 1,
                  hint = "Antiderivative is x² + x.",
                  stepByStepSolution = "(3² + 3) − (1² + 1) = 12 − 2 = 10.",
                ),
                PracticeQuestion(
                  id = "ex_math_13_4",
                  topic = "Stationary Point",
                  examType = "JAMB",
                  year = 2022,
                  question = "Find the value of x for which y = x³ − 12x + 5 has a local minimum.",
                  options = listOf("2", "−2", "4", "0"),
                  correctIndex = 0,
                  hint = "dy/dx = 3x² − 12 = 0 ⇒ x = ±2; minimum has d²y/dx² = 6x > 0.",
                  stepByStepSolution = "At x = 2, d²y/dx² = 12 > 0 (minimum).",
                ),
                PracticeQuestion(
                  id = "ex_math_13_5",
                  topic = "Trig Differentiation",
                  examType = "JAMB",
                  year = 2023,
                  question = "Differentiate y = cos(3x) with respect to x.",
                  options = listOf("−3 sin(3x)", "3 sin(3x)", "−sin(3x)", "3 cos(3x)"),
                  correctIndex = 0,
                  hint = "Chain rule multiplies by derivative of inner 3x.",
                  stepByStepSolution = "dy/dx = −3 sin(3x).",
                ),
                PracticeQuestion(
                  id = "ex_math_13_6",
                  topic = "Optimization",
                  examType = "NECO",
                  year = 2024,
                  question = "Two positive numbers add up to 20. What is the maximum possible product?",
                  options = listOf("96", "100", "84", "64"),
                  correctIndex = 1,
                  hint = "P(x) = x(20 − x) = 20x − x² ⇒ dP/dx = 20 − 2x = 0.",
                  stepByStepSolution = "x = 10, other = 10 ⇒ Max product = 10 × 10 = 100.",
                ),
              ),
          ),

          Lesson(
            id = "math_14_perm_comb_prob",
            moduleId = "math_mod_5",
            subjectId = "mathematics",
            title = "Permutations, Combinations & Probability",
            durationMinutes = 20,
            syllabusTag = "JAMB Sec. 5.1–5.2 • WAEC Combinatorics & Probability",
            learningOutcomes =
              listOf(
                "Distinguish Permutation nPr (order matters, e.g., President + VP) from Combination nCr (team committee).",
                "Count arrangements with repeated letters like 'MATHEMATICS' or circular tables (n − 1)!.",
                "Apply Addition (OR), Multiplication (AND), Conditional, and Tree-diagram probability.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. Permutations (Order Matters) vs Combinations (Order Doesn't!)",
                  explanation =
                    "Picking 3 medals (Gold, Silver, Bronze) from 10 runners? ORDER matters because Gold ≠ Bronze—use Permutation nPr! Picking a 3-person committee to sweep the hall? Chidi+Ada+Bola is the exact same committee as Bola+Chidi+Ada—order does NOT matter, use Combination nCr!",
                  keyFormula = "{}^{n}P_r = \\frac{n!}{(n-r)!}   |   {}^{n}C_r = \\frac{n!}{r!(n-r)!}   |   \\text{Circle: } (n-1)!",
                  examTip =
                    "Counting letter arrangements like 'SUCCESS' (7 letters, 3 S's, 2 C's)? Divide total factorial by repeat factorials: 7! / (3! × 2!)!",
                ),
                ConceptBlock(
                  heading = "2. Probability: AND Multiplies, OR Adds, 'At Least One' = 1 − None!",
                  explanation =
                    "Probability always stays between 0 (impossible) and 1 (certain). Independent events happening together (Head AND 6) MULTIPLY! Mutually exclusive choices (Red OR Blue) ADD! Hard problem asking 'probability of AT LEAST ONE success'? Flip it: calculate 1 − P(ALL FAIL)!",
                  keyFormula = "P(A \\cup B) = P(A) + P(B) - P(A \\cap B)   |   P(\\text{at least 1}) = 1 - P(\\text{none})",
                  examTip =
                    "'Without replacement' shrinks the denominator by 1 on the second draw!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Committee Selection with Minimum Condition",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement =
                    "In how many ways can a committee of 3 be chosen from 4 boys and 3 girls if it must include 2 boys and 1 girl?",
                  steps =
                    listOf(
                      "Step 1 — Ways to pick 2 boys from 4 = ⁴C₂ = (4×3)/2 = 6.",
                      "Step 2 — Ways to pick 1 girl from 3 = ³C₁ = 3.",
                      "Step 3 — Multiply (both happen): 6 × 3 = 18 ways.",
                    ),
                  finalAnswer = "18 ways",
                  examinerTrap = "Adding ⁴C₂ + ³C₁ instead of multiplying for an 'AND' selection!",
                ),
                WorkedExample(
                  title = "Drawing Balls Without Replacement",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement =
                    "A bag holds 4 red and 6 blue balls. Two balls are picked without replacement. Find P(both red).",
                    steps =
                    listOf(
                      "Step 1 — 1st ball red = 4/10.",
                      "Step 2 — 3 red left out of 9 total ⇒ 2nd red = 3/9 = 1/3.",
                      "Step 3 — Multiply: (4/10) × (3/9) = 12/90 = 2/15.",
                    ),
                  finalAnswer = "2/15",
                  examinerTrap = "Keeping denominator 10 on the second pick instead of reducing to 9!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "PIN Passcodes & Medical Screening False Positives",
                  industryDomain = "Security & Healthcare",
                  narrative =
                    "4-digit bank PINs allow 10⁴ = 10,000 permutations (with repeats), while conditional probability determines how reliable a rapid malaria test is.",
                  examConnection = "Directly tests multiplication counting principle and tree diagrams.",
                ),
              ),
            labType = PracticalLabType.PROBABILITY_STATISTICS_LAB,
            labBrief =
              "Adjust total items n, picks r, and favorable counts to calculate nPr, nCr, with/without replacement probability & 1 − P(none).",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_math_14_1",
                  topic = "Repeated Permutations",
                  examType = "JAMB",
                  year = 2024,
                  question = "In how many ways can the letters of the word 'BOOK' be arranged?",
                  options = listOf("24", "12", "6", "4"),
                  correctIndex = 1,
                  hint = "4 letters total, with 'O' repeated twice: 4! / 2!.",
                  stepByStepSolution = "24 / 2 = 12 ways.",
                ),
                PracticeQuestion(
                  id = "ex_math_14_2",
                  topic = "Combination Choice",
                  examType = "WAEC",
                  year = 2023,
                  question = "Evaluate ⁶C₂.",
                  options = listOf("30", "15", "12", "20"),
                  correctIndex = 1,
                  hint = "(6 × 5) / (2 × 1).",
                  stepByStepSolution = "30 / 2 = 15.",
                ),
                PracticeQuestion(
                  id = "ex_math_14_3",
                  topic = "Two Dice Probability",
                  examType = "JAMB",
                  year = 2025,
                  question = "Two fair dice are thrown once. Find the probability of getting a sum of 7.",
                  options = listOf("1/6", "1/12", "5/36", "7/36"),
                  correctIndex = 0,
                  hint = "36 pairs total; (1,6),(2,5),(3,4),(4,3),(5,2),(6,1) give 6 pairs.",
                  stepByStepSolution = "6 / 36 = 1/6.",
                ),
                PracticeQuestion(
                  id = "ex_math_14_4",
                  topic = "Circular Permutation",
                  examType = "JAMB",
                  year = 2022,
                  question = "In how many ways can 5 people sit around a round table?",
                  options = listOf("120", "24", "60", "20"),
                  correctIndex = 1,
                  hint = "Round table uses (n − 1)!.",
                  stepByStepSolution = "(5 − 1)! = 4! = 24 ways.",
                ),
                PracticeQuestion(
                  id = "ex_math_14_5",
                  topic = "At Least One Success",
                  examType = "WAEC",
                  year = 2024,
                  question = "A coin is tossed 3 times. Find the probability of getting at least one Head.",
                  options = listOf("1/8", "3/8", "7/8", "1/2"),
                  correctIndex = 2,
                  hint = "1 − P(all 3 Tails).",
                  stepByStepSolution = "1 − (1/2)³ = 1 − 1/8 = 7/8.",
                ),
                PracticeQuestion(
                  id = "ex_math_14_6",
                  topic = "Independent Events",
                  examType = "NECO",
                  year = 2021,
                  question = "P(A passes) = 2/3 and P(B passes) = 3/4. Find P(only A passes).",
                  options = listOf("1/6", "1/2", "5/12", "1/12"),
                  correctIndex = 0,
                  hint = "A passes AND B fails: (2/3) × (1 − 3/4).",
                  stepByStepSolution = "(2/3) × (1/4) = 2/12 = 1/6.",
                ),
              ),
          ),

          Lesson(
            id = "math_15_statistics",
            moduleId = "math_mod_5",
            subjectId = "mathematics",
            title = "Statistics: Averages, Histogram, Variance & Std Deviation",
            durationMinutes = 20,
            syllabusTag = "JAMB Sec. 5.3 • WAEC Grouped Data & Dispersion",
            learningOutcomes =
              listOf(
                "Compute Mean, Median, Mode, and Quartiles from frequency tables and Ogives.",
                "Remember how adding/multiplying data shifts Mean vs Standard Deviation.",
                "Calculate Variance σ² and Standard Deviation σ accurately.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. Mean, Median, Mode & Frequency Tables",
                  explanation =
                    "Mean (x̄) adds everything up and shares fairly (∑fx / ∑f). Median lines numbers in order and grabs the dead-center middle. Mode is the most popular value. On a Pie Chart, convert score to angle using (value / Total) × 360°!",
                  keyFormula = "\\bar{x} = \\frac{\\sum f x}{\\sum f}   |   \\text{Pie Sector Angle} = \\frac{f}{N} \\times 360^\\circ",
                  examTip =
                    "On a grouped frequency table, use CLASS MIDPOINTS x_mid before multiplying by frequency f!",
                ),
                ConceptBlock(
                  heading = "2. Range, Variance & Standard Deviation (How Spread Out Is Data?)",
                  explanation =
                    "Two classes can both have Mean = 60%, yet one class has everyone scoring 58–62% while the other has 20% and 100%! Standard Deviation (σ) measures how spread out scores are. Best shortcut: add +5 to every score? Mean increases by +5, but SPREAD (Range, Variance, σ) stays UNCHANGED! Multiply every score by 3? Both Mean AND σ multiply by 3!",
                  keyFormula = "\\sigma = \\sqrt{\\frac{\\sum f(x - \\bar{x})^2}{\\sum f}} = \\sqrt{\\frac{\\sum x^2}{N} - \\bar{x}^2}",
                  examTip =
                    "Memorize the shift rule: Mean obeys ax + b, while Standard Deviation ONLY obeys |a|σ (ignores + b)!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Standard Deviation of a Small Set",
                  examSource = "JAMB UTME-Style Worked Problem",
                  problemStatement = "Find the standard deviation of the numbers 2, 4, 6, 8, 10.",
                  steps =
                    listOf(
                      "Step 1 — Mean x̄ = (2 + 4 + 6 + 8 + 10)/5 = 30/5 = 6.",
                      "Step 2 — Squared deviations from 6: (−4)² + (−2)² + 0² + 2² + 4² = 16 + 4 + 0 + 4 + 16 = 40.",
                      "Step 3 — Variance = 40/5 = 8 ⇒ SD σ = √8 = 2√2.",
                    ),
                  finalAnswer = "√8 (or 2√2)",
                  examinerTrap = "Forgetting to take the square root of Variance (giving 8 instead of √8)!",
                ),
                WorkedExample(
                  title = "Median from Frequency Table",
                  examSource = "WAEC WASSCE-Style Worked Problem",
                  problemStatement =
                    "Scores 1, 2, 3, 4 have frequencies 3, 5, 4, 3 respectively. Find the median and mean.",
                    steps =
                    listOf(
                      "Step 1 — Total frequency N = 3 + 5 + 4 + 3 = 15 (odd).",
                      "Step 2 — Median position = (15 + 1)/2 = 8th item. Cumulative freq reaches 3 + 5 = 8 at score 2 ⇒ Median = 2.",
                      "Step 3 — Mean = (1×3 + 2×5 + 3×4 + 4×3)/15 = 37/15 ≈ 2.47.",
                    ),
                  finalAnswer = "Median = 2  |  Mean = 2.47",
                  examinerTrap = "Taking median of the scores 1,2,3,4 directly instead of expanding by frequency!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Quality Control in Drug Tablet Manufacturing",
                  industryDomain = "Pharmaceutical QA",
                  narrative =
                    "Even if average tablet weight is 500 mg, high standard deviation means some pills dangerously overdose while others underdose.",
                  examConnection = "Small σ = high consistency & reliability.",
                ),
              ),
            labType = PracticalLabType.PROBABILITY_STATISTICS_LAB,
            labBrief =
              "Inspect grouped histogram bars live and observe Mean, Median, Range, Variance σ² & Standard Deviation σ update instantaneously.",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_math_15_1",
                  topic = "Mean Modification",
                  examType = "JAMB",
                  year = 2024,
                  question = "The mean of 5 numbers is 12. When a 6th number x is included, the mean rises to 14. Find x.",
                  options = listOf("16", "20", "24", "22"),
                  correctIndex = 2,
                  hint = "New sum − old sum = x.",
                  stepByStepSolution = "(6 × 14) − (5 × 12) = 84 − 60 = 24.",
                ),
                PracticeQuestion(
                  id = "ex_math_15_2",
                  topic = "Pie Chart Angle",
                  examType = "WAEC",
                  year = 2023,
                  question = "In a budget of ₦36,000, Rent takes ₦9,000. What sector angle represents Rent?",
                  options = listOf("45°", "60°", "90°", "120°"),
                  correctIndex = 2,
                  hint = "(9000 / 36000) × 360°.",
                  stepByStepSolution = "(1/4) × 360° = 90°.",
                ),
                PracticeQuestion(
                  id = "ex_math_15_3",
                  topic = "Variance",
                  examType = "JAMB",
                  year = 2025,
                  question = "If the standard deviation of a data set is 4, what is the variance after adding 10 to every value?",
                  options = listOf("4", "16", "26", "116"),
                  correctIndex = 1,
                  hint = "Adding a constant does NOT change spread or variance; Variance = σ².",
                  stepByStepSolution = "σ stays 4 ⇒ Variance = 4² = 16.",
                ),
                PracticeQuestion(
                  id = "ex_math_15_4",
                  topic = "Mode & Range",
                  examType = "WAEC",
                  year = 2022,
                  question = "Find the sum of the Mode and Range of: 4, 7, 2, 7, 9, 5, 7.",
                  options = listOf("14", "12", "11", "16"),
                  correctIndex = 0,
                  hint = "Mode = 7; Range = 9 − 2 = 7.",
                  stepByStepSolution = "7 + 7 = 14.",
                ),
                PracticeQuestion(
                  id = "ex_math_15_5",
                  topic = "Ogive / Quartiles",
                  examType = "WAEC",
                  year = 2024,
                  question = "On a cumulative frequency curve (Ogive) for 80 students, the upper quartile Q₃ corresponds to cumulative frequency:",
                  options = listOf("20", "40", "60", "75"),
                  correctIndex = 2,
                  hint = "Q₃ = (3/4) × N.",
                  stepByStepSolution = "(3/4) × 80 = 60.",
                ),
                PracticeQuestion(
                  id = "ex_math_15_6",
                  topic = "Scaling SD",
                  examType = "NECO",
                  year = 2023,
                  question = "The standard deviation of {a, b, c} is 2.5. What is the standard deviation of {2a, 2b, 2c}?",
                  options = listOf("2.5", "5.0", "10.0", "1.25"),
                  correctIndex = 1,
                  hint = "Multiplying every item by k multiplies σ by |k|.",
                  stepByStepSolution = "2 × 2.5 = 5.0.",
                ),
              ),
          ),
        ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion("pq_math_01", "Number Bases", "JAMB", 2025, "Convert 213₄ to Base 10.", listOf("39", "35", "41", "45"), 0, "2(16)+1(4)+3(1).", "32+4+3=39."),
      PracticeQuestion("pq_math_02", "Modular Arithmetic", "WAEC", 2024, "Find the least positive integer x such that 4x ≡ 3 (mod 5).", listOf("1", "2", "3", "4"), 1, "Test x = 2: 8 ≡ 3 (mod 5).", "4(2) = 8 = 5(1) + 3."),
      PracticeQuestion("pq_math_03", "Indices", "JAMB", 2024, "If 8^{x/2} = 2^{3/2} \\times 4^x, find x.", listOf("−3", "−2", "2", "3"), 0, "Convert to base 2: 2^{3x/2} = 2^{3/2 + 2x}.", "\\frac{3}{2}x = \\frac{3}{2} + 2x \\implies -\\frac{x}{2} = \\frac{3}{2} \\implies x = -3."),
      PracticeQuestion("pq_math_04", "Logarithms", "WAEC", 2023, "Evaluate log₂ 8 + log₂ 4 − log₂ 2.", listOf("4", "5", "3", "6"), 0, "3 + 2 − 1.", "log₂(8×4/2) = log₂ 16 = 4."),
      PracticeQuestion("pq_math_05", "Surds", "NECO", 2023, "If √50 − √18 = k√2, find k.", listOf("2", "3", "4", "8"), 0, "5√2 − 3√2.", "2√2 ⇒ k = 2."),
      PracticeQuestion("pq_math_06", "Sets & Venn", "WAEC", 2024, "Of 60 pupils, 35 like Maths, 30 like English, 10 like neither. How many like BOTH?", listOf("15", "20", "10", "5"), 0, "Union = 60 − 10 = 50.", "35 + 30 − 50 = 15."),
      PracticeQuestion("pq_math_07", "Variation", "JAMB", 2025, "If x varies directly as y³ and x = 16 when y = 2, find x when y = 3.", listOf("54", "24", "48", "27"), 0, "k = 16/8 = 2.", "x = 2(3³) = 54."),
      PracticeQuestion("pq_math_08", "Inequalities", "WAEC", 2022, "Solve (x + 2)(x − 4) < 0.", listOf("−2 < x < 4", "x < −2 or x > 4", "−4 < x < 2", "x < 4"), 0, "Negative product traps x between roots.", "−2 < x < 4."),
      PracticeQuestion("pq_math_09", "Quadratics", "JAMB", 2024, "If α and β are roots of x² + 5x − 3 = 0, find α + β − αβ.", listOf("−2", "−8", "8", "2"), 0, "α+β = −5, αβ = −3.", "−5 − (−3) = −2."),
      PracticeQuestion("pq_math_10", "Polynomials", "NECO", 2021, "Find the remainder when 2x³ − 3x + 5 is divided by (x + 1).", listOf("6", "4", "0", "10"), 0, "Evaluate P(−1).", "2(−1) − 3(−1) + 5 = −2 + 3 + 5 = 6."),
      PracticeQuestion("pq_math_11", "AP Sequence", "JAMB", 2023, "The 4th term of an AP is 13 and the 7th term is 22. Find the first term.", listOf("4", "3", "5", "1"), 0, "3d = 9 ⇒ d = 3.", "a = 13 − 3(3) = 4."),
      PracticeQuestion("pq_math_12", "GP Sequence", "WAEC", 2025, "Find the 5th term of the GP: 81, 27, 9, ...", listOf("1", "3", "1/3", "1/9"), 0, "r = 1/3.", "81 × (1/3)⁴ = 1."),
      PracticeQuestion("pq_math_13", "Binary Operations", "JAMB", 2022, "If m * n = m² − n², evaluate 5 * (3 * 2).", listOf("0", "−24", "24", "−5"), 0, "Compute inner 3*2 = 9−4 = 5 first.", "5 * 5 = 25 − 25 = 0."),
      PracticeQuestion("pq_math_14", "Matrices", "WAEC", 2024, "Find x if det [[x+1, 2], [3, x]] = 0 (take x > 0).", listOf("2", "3", "1", "6"), 0, "x(x+1) − 6 = 0 ⇒ x² + x − 6 = 0.", "(x+3)(x−2) = 0 ⇒ x = 2."),
      PracticeQuestion("pq_math_15", "Polygons", "NECO", 2021, "The exterior angle of a regular polygon is 40°. Find the sum of its interior angles.", listOf("1260°", "1080°", "1440°", "900°"), 0, "n = 360/40 = 9.", "(9 − 2) × 180° = 1260°."),
      PracticeQuestion("pq_math_16", "Circle Theorems", "WAEC", 2023, "A,B,C,D on circle; chord AD parallel BC. If ∠DAB = 72°, find ∠DCB.", listOf("108°", "72°", "36°", "144°"), 0, "Opposite angles in cyclic quad sum to 180°.", "180° − 72° = 108°."),
      PracticeQuestion("pq_math_17", "Mensuration", "JAMB", 2024, "A cylinder and cone have equal base radius and equal volume. Find ratio of cylinder height to cone height.", listOf("1 : 3", "3 : 1", "1 : 2", "2 : 3"), 0, "πr²h₁ = (1/3)πr²h₂.", "h₁ / h₂ = 1/3."),
      PracticeQuestion("pq_math_18", "Trigonometry", "WAEC", 2022, "Evaluate sin 30° cos 60° + cos 30° sin 60°.", listOf("1", "1/2", "√3/2", "0"), 0, "Equals sin(30°+60°) = sin 90°.", "(0.5)(0.5) + (√3/2)(√3/2) = 1/4 + 3/4 = 1."),
      PracticeQuestion("pq_math_19", "Bearings", "JAMB", 2020, "Point Y is 5 km due East of X and Z is 5 km due South of Y. Find the bearing of Z from X.", listOf("135°", "045°", "225°", "315°"), 0, "East = 090°, South turns 45° diagonal into SE.", "090° + 45° = 135°."),
      PracticeQuestion("pq_math_20", "Coordinate Geometry", "NECO", 2025, "Find the y-intercept of the line 4x − 2y + 10 = 0.", listOf("5", "−5", "2", "−2.5"), 0, "Set x = 0.", "−2y = −10 ⇒ y = 5."),
      PracticeQuestion("pq_math_21", "Differentiation", "JAMB", 2024, "Find the gradient of curve y = 2x³ − 4x at point x = 1.", listOf("2", "−2", "6", "0"), 0, "dy/dx = 6x² − 4.", "6(1)² − 4 = 2."),
      PracticeQuestion("pq_math_22", "Integration", "WAEC", 2023, "Evaluate ∫₀² 3x² dx.", listOf("8", "12", "6", "4"), 0, "[x³] from 0 to 2.", "2³ − 0³ = 8."),
      PracticeQuestion("pq_math_23", "Probability", "JAMB", 2021, "A card is drawn at random from a standard 52-card pack. Find P(Ace or King).", listOf("2/13", "1/13", "1/26", "4/13"), 0, "4 Aces + 4 Kings = 8 cards.", "8/52 = 2/13."),
      PracticeQuestion("pq_math_24", "Statistics", "NECO", 2024, "If the mean of x, x+2, x+4, x+6 is 11, find x.", listOf("8", "9", "7", "10"), 0, "Middle of symmetric sequence = 11 ⇒ x + 3 = 11.", "(4x + 12)/4 = 11 ⇒ x + 3 = 11 ⇒ x = 8."),
    )
}
