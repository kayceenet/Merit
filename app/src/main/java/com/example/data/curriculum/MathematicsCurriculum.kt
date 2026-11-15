package com.example.data.curriculum

object MathematicsCurriculum {

  val track =
    SubjectTrack(
      id = "mathematics",
      title = "Mathematics Mastery",
      shortName = "Math",
      tagline = "Algebra, Calculus, Coordinate Geometry & Probability for distinction scores",
      examCoverage = "JAMB 40 Qs • WAEC Paper 1 & Paper 2 Theory",
      modules =
        listOf(
          CourseModule(
            id = "math_mod_1",
            subjectId = "mathematics",
            indexLabel = "Module 01",
            title = "Quadratics & Differential Calculus",
            summary = "Link algebraic roots and discriminants directly to parabolic turning points and tangents.",
            lessons =
              listOf(
                Lesson(
                  id = "math_quad_01",
                  moduleId = "math_mod_1",
                  subjectId = "mathematics",
                  title = "Quadratic Roots, Discriminant & Turning Points",
                  durationMinutes = 17,
                  syllabusTag = "JAMB Sec. I.4 • WAEC Algebra",
                  learningOutcomes =
                    listOf(
                      "Use Δ = b² − 4ac to classify real, equal, or complex roots without solving.",
                      "Compute symmetric root expressions α + β, αβ, and α² + β² using Vieta's relations.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Discriminant & Root Nature",
                        explanation =
                          "For ax² + bx + c = 0 (a ≠ 0), the discriminant Δ = b² − 4ac controls how many times the parabola y = ax² + bx + c crosses the x-axis. Δ > 0 gives two distinct real roots; Δ = 0 means the vertex touches the x-axis tangentially (equal roots); Δ < 0 means no real roots.",
                        keyFormula =
                          "Δ = b² − 4ac   |   α + β = −b/a   |   αβ = c/a   |   x_vertex = −b/(2a)",
                        examTip =
                          "For α² + β², never solve for α and β individually if the discriminant is messy — use α² + β² = (α + β)² − 2αβ!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Symmetric Functions of Quadratic Roots",
                        examSource = "JAMB UTME 2022 • Mathematics Q19",
                        problemStatement =
                          "If α and β are the roots of 2x² − 7x + 3 = 0, find the value of (1/α) + (1/β) and α² + β².",
                        steps =
                          listOf(
                            "Step 1 — Read coefficients: a = 2, b = −7, c = 3.",
                            "Step 2 — Vieta's sums: α + β = −(−7)/2 = 7/2; αβ = 3/2.",
                            "Step 3 — Reciprocal sum: (1/α) + (1/β) = (α + β)/(αβ) = (7/2) ÷ (3/2) = 7/3.",
                            "Step 4 — Sum of squares: α² + β² = (α + β)² − 2αβ = (7/2)² − 2(3/2) = 49/4 − 3 = 37/4.",
                          ),
                        finalAnswer = "7/3 and 37/4",
                        examinerTrap =
                          "Watch the negative sign in α + β = −b/a! Since b = −7, −b/a = +7/2, not −7/2.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "E-Commerce Pricing & Profit Maximization",
                        industryDomain = "Business Analytics & Economics",
                        narrative =
                          "A Lagos fintech or agro-processing startup modeling daily profit P(x) = −2x² + 120x − 1000 as a function of unit price x gets an inverted parabola (a < 0). Finding the vertex x = −b/(2a) = 30 tells management the exact optimal unit price that maximizes revenue before demand drops.",
                        examConnection =
                          "Directly underpins WAEC optimization word problems involving fenced rectangular gardens or profit quadratics.",
                      ),
                    ),
                  labType = PracticalLabType.QUADRATIC_TANGENT,
                  labBrief =
                    "In the Quadratic Explorer below: (1) Set a = 1, b = −4, c = 4 to observe Δ = 0 (tangent vertex on x-axis). (2) Slide c to 6 to see Δ < 0 (floating parabola, no real roots). (3) Drag x₀ to watch the tangent line slope dy/dx turn zero right at the vertex!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_math_quad_1",
                        topic = "Quadratics",
                        examType = "JAMB",
                        year = 2023,
                        question =
                          "Find the value(s) of k for which x² + kx + 9 = 0 has equal roots.",
                        options = listOf("k = 3 only", "k = ±3", "k = ±6", "k = 9"),
                        correctIndex = 2,
                        hint = "Equal roots occur when Δ = b² − 4ac = 0.",
                        stepByStepSolution =
                          "b² − 4ac = 0 => k² − 4(1)(9) = 0 => k² = 36 => k = ±6.",
                      ),
                    ),
                ),
                Lesson(
                  id = "math_calc_02",
                  moduleId = "math_mod_1",
                  subjectId = "mathematics",
                  title = "Differentiation: Tangents, Maxima & Minima",
                  durationMinutes = 19,
                  syllabusTag = "JAMB Sec. III.1 • WAEC Calculus",
                  learningOutcomes =
                    listOf(
                      "Differentiate polynomials and apply the Second Derivative Test to classify stationary points.",
                      "Find equations of tangents and normals to curves at any point (x₀, y₀).",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Stationary Points & Second Derivative Test",
                        explanation =
                          "At a turning (stationary) point, the curve is momentarily flat so dy/dx = 0. To distinguish a local maximum from a local minimum, evaluate d²y/dx² at that x-value: negative (< 0) means Maximum (concave down); positive (> 0) means Minimum (concave up).",
                        keyFormula =
                          "dy/dx = 0 (stationary)   |   d²y/dx² < 0 ⇒ Max   |   d²y/dx² > 0 ⇒ Min",
                        examTip =
                          "Remember that if a question asks for the 'maximum value of y', substitute the stationary x BACK into the original y equation!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Finding Local Maximum and Minimum Values",
                        examSource = "WAEC SSCE 2021 • Paper 2 Q8",
                        problemStatement =
                          "Find the coordinates and nature of the turning points of y = x³ − 3x² − 9x + 5.",
                        steps =
                          listOf(
                            "Step 1 — Differentiate: dy/dx = 3x² − 6x − 9.",
                            "Step 2 — Set dy/dx = 0: 3(x² − 2x − 3) = 0 => (x − 3)(x + 1) = 0 => x = 3 or x = −1.",
                            "Step 3 — Second derivative: d²y/dx² = 6x − 6.",
                            "Step 4 — Classify x = −1: y'' = 6(−1) − 6 = −12 < 0 (Max). y(−1) = −1 − 3 + 9 + 5 = 10 => Max at (−1, 10).",
                            "Step 5 — Classify x = 3: y'' = 6(3) − 6 = +12 > 0 (Min). y(3) = 27 − 27 − 27 + 5 = −22 => Min at (3, −22).",
                          ),
                        finalAnswer = "Max (−1, 10), Min (3, −22)",
                        examinerTrap =
                          "Many students stop after finding x = −1 and x = 3, forgetting to compute y or mixing up < 0 with Minimum.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Minimum-Material Tin Packaging",
                        industryDomain = "Manufacturing & Industrial Design",
                        narrative =
                          "Beverage and paint factories design cylindrical cans holding a fixed 500 cm³ volume while minimizing aluminum sheet surface area A(r) = 2πr² + 1000/r. Setting dA/dr = 0 gives the exact cost-minimizing radius where height equals diameter.",
                        examConnection =
                          "Classic WAEC Paper 2 Section B applied calculus question.",
                      ),
                    ),
                  labType = PracticalLabType.QUADRATIC_TANGENT,
                  labBrief =
                    "Use the Quadratic & Derivative Explorer: set a = 2, b = −8, c = 3 and move x₀ across x = 2. Watch the tangent line turn completely horizontal (dy/dx = 0.00) at the minimum point!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_math_calc_1",
                        topic = "Calculus",
                        examType = "JAMB",
                        year = 2022,
                        question =
                          "Find the gradient of the normal to the curve y = 2x² − 3x + 1 at x = 2.",
                        options = listOf("5", "−5", "1/5", "−1/5"),
                        correctIndex = 3,
                        hint = "Normal gradient m_n = −1 / m_tangent.",
                        stepByStepSolution =
                          "dy/dx = 4x − 3. At x = 2, tangent gradient m_t = 4(2) − 3 = 5. Normal gradient = −1/5.",
                      ),
                    ),
                ),
              ),
          ),
          CourseModule(
            id = "math_mod_2",
            subjectId = "mathematics",
            indexLabel = "Module 02",
            title = "Probability & Combinatorics",
            summary = "Master permutations, combinations, and conditional probability trees.",
            lessons =
              listOf(
                Lesson(
                  id = "math_prob_01",
                  moduleId = "math_mod_2",
                  subjectId = "mathematics",
                  title = "Permutations, Combinations & Sample Spaces",
                  durationMinutes = 14,
                  syllabusTag = "JAMB Sec. V.3 • WAEC Statistics",
                  learningOutcomes =
                    listOf(
                      "Distinguish ordered arrangements (nPr) from unordered selections (nCr).",
                      "Apply complementary probability P(at least one) = 1 − P(none).",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "1. Order Matters (nPr) vs Selection Only (nCr)",
                        explanation =
                          "Use Permutation nPr when positions or ranks differ (e.g., President & Secretary, PIN digits). Use Combination nCr when choosing a committee or subset where order inside the group does not matter.",
                        keyFormula = "nPr = n! / (n − r)!   |   nCr = n! / [r!(n − r)!]",
                        examTip =
                          "When arranging letters of a word with repeated letters (e.g., 'MISSISSIPPI'), divide n! by the factorial of each repeated letter count!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Committee Selection with Constraints",
                        examSource = "JAMB UTME 2020 • Mathematics Q36",
                        problemStatement =
                          "A committee of 3 boys and 2 girls is to be formed from 5 boys and 4 girls. In how many ways can this be done?",
                        steps =
                          listOf(
                            "Step 1 — Choose 3 boys from 5: ⁵C₃ = (5 × 4) / (2 × 1) = 10 ways.",
                            "Step 2 — Choose 2 girls from 4: ⁴C₂ = (4 × 3) / (2 × 1) = 6 ways.",
                            "Step 3 — Apply multiplication principle ('AND'): 10 × 6 = 60 ways.",
                          ),
                        finalAnswer = "60 ways",
                        examinerTrap =
                          "Students often ADD (10 + 6 = 16) instead of MULTIPLYING. Use × for 'AND', + for 'OR'.",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "JAMB CBT Randomized Question Shuffling",
                        industryDomain = "Educational Assessment & Cybersecurity",
                        narrative =
                          "CBT exam servers generate distinct question permutations for neighboring candidates using combinatorics so no two seats receive identical ordering, while calculating collision probabilities across thousands of centers.",
                        examConnection =
                          "Demonstrates nPr ordering vs nCr subset selection.",
                      ),
                    ),
                  labType = PracticalLabType.QUADRATIC_TANGENT,
                  labBrief =
                    "Explore how parabolic variance curves relate to binomial distributions in the visual plotter below.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_math_prob_1",
                        topic = "Probability",
                        examType = "WAEC",
                        year = 2021,
                        question =
                          "A bag contains 4 red and 6 blue balls. Two balls are drawn without replacement. What is the probability that both are red?",
                        options = listOf("4/25", "2/15", "6/25", "1/5"),
                        correctIndex = 1,
                        hint = "After drawing the first red ball, 3 red and 9 total remain.",
                        stepByStepSolution = "P(R₁ ∩ R₂) = (4/10) × (3/9) = 12/90 = 2/15.",
                        waecMarkingScheme = "M1: (4/10)×(3/9); A1: 2/15.",
                      ),
                    ),
                ),
              ),
          ),
        ),
      pastQuestions =
        listOf(
          PracticeQuestion(
            id = "pq_math_1",
            topic = "Calculus",
            examType = "JAMB",
            year = 2023,
            question = "Find the minimum value of y = x² − 6x + 11.",
            options = listOf("2", "3", "5", "11"),
            correctIndex = 0,
            hint = "Set dy/dx = 0 to locate x, then evaluate y.",
            stepByStepSolution = "dy/dx = 2x − 6 = 0 => x = 3. Minimum y = 3² − 6(3) + 11 = 9 − 18 + 11 = 2.",
          ),
          PracticeQuestion(
            id = "pq_math_2",
            topic = "Quadratics",
            examType = "WAEC",
            year = 2022,
            question = "Find the quadratic equation whose roots are 3/2 and −4.",
            options =
              listOf(
                "2x² + 5x − 12 = 0",
                "2x² − 5x − 12 = 0",
                "2x² + 5x + 12 = 0",
                "x² + 5x − 6 = 0",
              ),
            correctIndex = 0,
            hint = "Use x² − (sum of roots)x + (product of roots) = 0.",
            stepByStepSolution =
              "Sum = 3/2 + (−4) = −5/2; Product = (3/2)(−4) = −6. x² − (−5/2)x − 6 = 0 => 2x² + 5x − 12 = 0.",
            waecMarkingScheme = "M1: Sum = −5/2, Product = −6; A1: 2x² + 5x − 12 = 0.",
          ),
          PracticeQuestion(
            id = "pq_math_3",
            topic = "Probability",
            examType = "JAMB",
            year = 2021,
            question = "In how many ways can the letters of the word 'BANANA' be arranged?",
            options = listOf("720", "120", "60", "30"),
            correctIndex = 2,
            hint = "6 letters total, with 3 A's and 2 N's repeated.",
            stepByStepSolution = "6! / (3! × 2!) = 720 / (6 × 2) = 720 / 12 = 60.",
          ),
        ),
    )
}
