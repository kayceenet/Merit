package com.example.data.curriculum

object MathematicsCurriculum {

  val track: SubjectTrack by lazy {
    SubjectTrack(
      id = "mathematics",
      title = "Mathematics Specialization",
      shortName = "Mathematics",
      subtitle = "15 Zero-to-A1 lessons covering Number Bases, Sets, Algebra, Circle Theorems, Calculus & Probability",
      modules = module1And2 + MathematicsCurriculumPart2.module3And4 + listOf(MathematicsCurriculumPart3.module5),
      pastQuestions = MathematicsCurriculumPart3.pastQuestions,
    )
  }

  val module1And2: List<CourseModule> =
    listOf(
      CourseModule(
        id = "math_mod_1",
        subjectId = "mathematics",
        indexLabel = "Module 01",
        title = "Number Systems, Bases, Indices, Logarithms & Sets",
        summary = "Build rock-solid number sense from scratch: counting in Base 2–16, modular clock arithmetic, surds, indices, logs, and Venn diagrams.",
        lessons =
          listOf(
            Lesson(
              id = "math_01_bases_modular",
              moduleId = "math_mod_1",
              subjectId = "mathematics",
              title = "Number Bases & Modular (Clock) Arithmetic",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 1.1 • WAEC Number Bases & Mod",
              learningOutcomes =
                listOf(
                  "Convert any whole or decimal number between Base 2, Base 5, Base 8, Base 10, and Base 16.",
                  "Add, subtract, and solve unknown base equations like 24ₓ = 18₁₀ without guessing.",
                  "Find clock remainders using Modular Arithmetic (a ≡ b mod n).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. What Is a Number Base? (Bundles of Fingers!)",
                    explanation =
                      "We normally count in Base 10 because humans have 10 fingers—so every bundle of 10 kicks us into the next column (1s, 10s, 100s). Computers only have ON/OFF switches (2 fingers!), so they count in Base 2 (columns: 1, 2, 4, 8, 16). An alien with 5 fingers uses Base 5 (columns: 1, 5, 25). Rule: digits in Base B are ALWAYS smaller than B!",
                    keyFormula = "(a_2 a_1 a_0)_b = a_2 b^2 + a_1 b^1 + a_0 b^0",
                    examTip =
                      "To convert from Base 10 to Base b, divide repeatedly by b and read the REMAINDERS from bottom to top!",
                  ),
                  ConceptBlock(
                    heading = "2. Modular Arithmetic: The 12-Hour Clock Game",
                    explanation =
                      "If it is 10 o'clock and you wait 5 hours, the clock doesn't say 15—it wraps around past 12 and lands on 3! That is literally 10 + 5 ≡ 3 (mod 12). In mod n arithmetic, you ONLY care about the positive remainder left after dividing by n. Even negative numbers work: add multiples of n until the answer becomes 0, 1, ..., n − 1.",
                    keyFormula = "a \\equiv r \\pmod{n} \\iff a = qn + r,\\quad 0 \\le r < n",
                    examTip =
                      "When adding/multiplying large numbers in mod n, reduce each number mod n FIRST before multiplying!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding Unknown Base x",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "If 34ₓ = 25₁₀ (or find x given 112ₓ = 22₁₀), solve 23ₓ = 17₁₀.",
                    steps =
                      listOf(
                        "Step 1 — Expand Left-Hand Side in powers of x: 2x¹ + 3x⁰ = 2x + 3.",
                        "Step 2 — Set equal to 17: 2x + 3 = 17.",
                        "Step 3 — 2x = 14 ⇒ x = 7 (valid since digits 2 and 3 are < 7).",
                      ),
                    finalAnswer = "x = 7",
                    examinerTrap = "Always reject any base x that is less than or equal to the largest digit in the question!",
                  ),
                  WorkedExample(
                    title = "Binary Fractional Multiplication & Remainder Mod",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Evaluate 23 × 14 (mod 5) and convert 13.5₁₀ to Base 2.",
                    steps =
                      listOf(
                        "Step 1 — Reduce first: 23 ≡ 3 (mod 5) and 14 ≡ 4 (mod 5).",
                        "Step 2 — Multiply remainders: 3 × 4 = 12 ≡ 2 (mod 5).",
                        "Step 3 — For 13.5₁₀: 13 = 8 + 4 + 0 + 1 = 1101₂, and 0.5 = 1/2 = 0.1₂ ⇒ 1101.1₂.",
                      ),
                    finalAnswer = "2 (mod 5)  |  1101.1₂",
                    examinerTrap = "After a binary point, multiply the fractional part by 2 repeatedly and read whole digits TOP to bottom.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Every WhatsApp Message Uses Base 2 & Mod-p",
                    industryDomain = "Cybersecurity & Computing",
                    narrative =
                      "Your phone encodes photos in Base 2 (1s and 0s) and encrypts bank PINs using prime-number modular clocks (RSA cryptography) so eavesdroppers cannot invert the remainder.",
                    examConnection = "Direct real-world home of binary expansion and modular remainders.",
                  ),
                ),
              labType = PracticalLabType.NUMBER_BASE_INDICES_LAB,
              labBrief =
                "Convert integers across Base 2–16 step-by-step and spin the Modulo n clock interactively to watch remainders wrap around.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_01_1",
                    topic = "Number Bases",
                    examType = "JAMB",
                    year = 2024,
                    question = "Convert 45₁₀ to binary (Base 2).",
                    options = listOf("101101₂", "110101₂", "101011₂", "111001₂"),
                    correctIndex = 0,
                    hint = "45 = 32 + 8 + 4 + 1.",
                    stepByStepSolution = "32 + 0(16) + 8 + 4 + 0(2) + 1 = 101101₂.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_01_2",
                    topic = "Unknown Base",
                    examType = "WAEC",
                    year = 2023,
                    question = "Find the base y such that 121_y = 49₁₀.",
                    options = listOf("5", "6", "7", "8"),
                    correctIndex = 1,
                    hint = "Expand y² + 2y + 1 = 49, which is (y + 1)² = 49.",
                    stepByStepSolution = "y² + 2y + 1 = 49 ⇒ (y + 1)² = 49 ⇒ y + 1 = 7 ⇒ y = 6.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_01_3",
                    topic = "Modular Arithmetic",
                    examType = "JAMB",
                    year = 2025,
                    question = "What is the least positive value of x such that 17 + x ≡ 3 (mod 7)?",
                    options = listOf("0", "2", "5", "6"),
                    correctIndex = 0,
                    hint = "Find 17 mod 7 first.",
                    stepByStepSolution = "17 = 2(7) + 3, so 17 ≡ 3 (mod 7). Thus 3 + x ≡ 3 ⇒ x = 0.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_01_4",
                    topic = "Base Arithmetic",
                    examType = "WAEC",
                    year = 2022,
                    question = "Calculate 1011₂ + 1110₂ in Base 2.",
                    options = listOf("11001₂", "10101₂", "11011₂", "10011₂"),
                    correctIndex = 0,
                    hint = "11₁₀ + 14₁₀ = 25₁₀.",
                    stepByStepSolution = "11 + 14 = 25 = 16 + 8 + 1 = 11001₂.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_01_5",
                    topic = "Modular Arithmetic",
                    examType = "JAMB",
                    year = 2021,
                    question = "Evaluate (−17) (mod 5).",
                    options = listOf("1", "2", "3", "4"),
                    correctIndex = 2,
                    hint = "Add multiples of 5 to −17 until positive.",
                    stepByStepSolution = "−17 + 4(5) = −17 + 20 = 3 (mod 5).",
                  ),
                  PracticeQuestion(
                    id = "ex_math_01_6",
                    topic = "Base Subtraction",
                    examType = "NECO",
                    year = 2024,
                    question = "Subtract 23₅ from 41₅ in Base 5.",
                    options = listOf("12₅", "13₅", "14₅", "23₅"),
                    correctIndex = 1,
                    hint = "Borrowing 1 from the 5s column adds 5 to the units column.",
                    stepByStepSolution = "(5 + 1) − 3 = 3; remaining 3 − 2 = 1 ⇒ 13₅.",
                  ),
                ),
            ),

            Lesson(
              id = "math_02_indices_logs_surds",
              moduleId = "math_mod_1",
              subjectId = "mathematics",
              title = "Fractions, Percentages, Indices, Logs & Surds",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 1.2–1.5 • WAEC Indices, Logs & Surds",
              learningOutcomes =
                listOf(
                  "Apply negative and fractional powers: x^{-n} = \\frac{1}{x^n} and x^{a/b} = (\\sqrt[b]{x})^a.",
                  "Switch smoothly between exponential a^x = N and logarithm \\log_a N = x.",
                  "Simplify surds and rationalize denominators using conjugate pairs.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Indices & Logarithms: Twin Opposite Buttons",
                    explanation =
                      "An index (power) tells you how many times to multiply a base: 2^3 = 8. A Logarithm simply asks the backwards question: '2 to what power gives 8?' Answer: \\log_2 8 = 3. Because indices and logs are inverses, multiplying inside a log turns into ADDING two logs, and dividing turns into SUBTRACTING.",
                    keyFormula = "a^0 = 1,\\quad a^{m/n} = (\\sqrt[n]{a})^m   |   \\log_b(xy) = \\log_b x + \\log_b y,\\quad \\log_b(x^k) = k\\log_b x",
                    examTip =
                      "Any time you see 2^x = \\frac{1}{8} or 9^{x+1} = 27^x, rewrite every term to the SAME small prime base (2 or 3) and equate powers!",
                  ),
                  ConceptBlock(
                    heading = "2. Surds & Conjugates: Simplifying Irrational Denominators",
                    explanation =
                      "For any non-perfect square, factor out the largest perfect square inside the radical—for instance, \\sqrt{12} = \\sqrt{4 \\times 3} = 2\\sqrt{3}. Standard mathematical notation avoids irrational roots in denominators; to rationalize \\frac{1}{a + \\sqrt{b}}, multiply both numerator and denominator by the conjugate (a - \\sqrt{b}) so that the difference of two squares eliminates the radical: (a + \\sqrt{b})(a - \\sqrt{b}) = a^2 - b.",
                    keyFormula = "\\frac{1}{a + \\sqrt{b}} = \\frac{a - \\sqrt{b}}{a^2 - b}",
                    examTip =
                      "Also know Profit \\% = \\frac{\\text{Profit}}{\\text{Cost Price}} \\times 100\\% and Compound Interest A = P(1 + \\frac{r}{100})^n!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Exponential Equation with Base 3",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Solve for x: 9^{2x - 1} = 27^{x + 1}.",
                    steps =
                      listOf(
                        "Step 1 — Write 9 = 3^2 and 27 = 3^3.",
                        "Step 2 — 3^{2(2x - 1)} = 3^{3(x + 1)} \\implies 4x - 2 = 3x + 3.",
                        "Step 3 — x = 5.",
                      ),
                    finalAnswer = "x = 5",
                    examinerTrap = "Multiply the outer exponent by BOTH terms inside (2x - 1), not just 2x!",
                  ),
                  WorkedExample(
                    title = "Rationalizing Surd Fractions",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Simplify \\frac{6}{\\sqrt{5} - \\sqrt{2}} in the form a\\sqrt{5} + b\\sqrt{2}.",
                    steps =
                      listOf(
                        "Step 1 — Multiply numerator and denominator by conjugate (\\sqrt{5} + \\sqrt{2}).",
                        "Step 2 — Denominator = (\\sqrt{5})^2 - (\\sqrt{2})^2 = 5 - 2 = 3.",
                        "Step 3 — \\frac{6(\\sqrt{5} + \\sqrt{2})}{3} = 2\\sqrt{5} + 2\\sqrt{2}.",
                      ),
                    finalAnswer = "2\\sqrt{5} + 2\\sqrt{2}",
                    examinerTrap = "Always simplify the denominator before cancelling with the numerator!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Compound Interest & Richter Earthquake Scale",
                    industryDomain = "Banking & Geophysics",
                    narrative =
                      "Bank savings grow via exponential powers A = P(1 + r)ⁿ, while earthquake strength uses Base-10 logarithms—so a Magnitude 7 quake shakes 10 times harder than Magnitude 6.",
                    examConnection = "Connects commercial percentages directly to indices and log scales.",
                  ),
                ),
              labType = PracticalLabType.NUMBER_BASE_INDICES_LAB,
              labBrief =
                "Test fractional and negative powers a^{m/n}, check \\log_b(x) transformations, and compare simple vs compound growth live.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_02_1",
                    topic = "Indices",
                    examType = "JAMB",
                    year = 2024,
                    question = "Evaluate \\left(\\frac{27}{8}\\right)^{-2/3}.",
                    options = listOf("\\frac{4}{9}", "\\frac{9}{4}", "-\\frac{9}{4}", "\\frac{2}{3}"),
                    correctIndex = 0,
                    hint = "A negative power flips the fraction to \\left(\\frac{8}{27}\\right)^{2/3}.",
                    stepByStepSolution = "\\left(\\sqrt[3]{\\frac{8}{27}}\\right)^2 = \\left(\\frac{2}{3}\\right)^2 = \\frac{4}{9}.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_02_2",
                    topic = "Logarithms",
                    examType = "WAEC",
                    year = 2023,
                    question = "If log₁₀ 2 = 0.3010 and log₁₀ 3 = 0.4771, find log₁₀ 18.",
                    options = listOf("0.7781", "1.2552", "1.0791", "1.4313"),
                    correctIndex = 1,
                    hint = "Write 18 = 2 × 3².",
                    stepByStepSolution = "log 2 + 2 log 3 = 0.3010 + 2(0.4771) = 1.2552.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_02_3",
                    topic = "Surds",
                    examType = "JAMB",
                    year = 2025,
                    question = "Simplify √75 + √27 − √48.",
                    options = listOf("4√3", "6√3", "2√3", "8√3"),
                    correctIndex = 0,
                    hint = "Factor out 3 inside every square root.",
                    stepByStepSolution = "5√3 + 3√3 − 4√3 = (5 + 3 − 4)√3 = 4√3.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_02_4",
                    topic = "Percentage Profit",
                    examType = "WAEC",
                    year = 2022,
                    question = "A trader sells a radio for ₦14,400 making a 20% profit. Find the cost price.",
                    options = listOf("₦11,520", "₦12,000", "₦12,400", "₦13,000"),
                    correctIndex = 1,
                    hint = "Selling Price = 120% of Cost Price.",
                    stepByStepSolution = "1.20 × CP = 14,400 ⇒ CP = 14,400 / 1.2 = ₦12,000.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_02_5",
                    topic = "Logarithm Equations",
                    examType = "JAMB",
                    year = 2023,
                    question = "Solve log₃(x + 1) + log₃(x − 1) = 1.",
                    options = listOf("x = 2", "x = 3", "x = ±2", "x = 4"),
                    correctIndex = 0,
                    hint = "Combine logs to log₃(x² − 1) = 1, then convert to 3¹.",
                    stepByStepSolution = "x² − 1 = 3 ⇒ x² = 4 ⇒ x = 2 (x = −2 invalid for log argument).",
                  ),
                  PracticeQuestion(
                    id = "ex_math_02_6",
                    topic = "Surd Rationalization",
                    examType = "NECO",
                    year = 2024,
                    question = "Simplify (√3 + √2) / (√3 − √2).",
                    options = listOf("5 + 2√6", "5 − 2√6", "1 + 2√6", "5 + √6"),
                    correctIndex = 0,
                    hint = "Multiply top & bottom by (√3 + √2).",
                    stepByStepSolution = "(√3 + √2)² / (3 − 2) = (3 + 2 + 2√6) / 1 = 5 + 2√6.",
                  ),
                ),
            ),

            Lesson(
              id = "math_03_sets_venn_functions",
              moduleId = "math_mod_1",
              subjectId = "mathematics",
              title = "Sets, Venn Diagrams & Functions / Mapping",
              durationMinutes = 19,
              syllabusTag = "JAMB Sec. 1.6 & 2.6 • WAEC Sets & Mapping",
              learningOutcomes =
                listOf(
                  "Solve 2-set and 3-set Venn diagram word problems without double-counting the middle overlap.",
                  "Find subsets (2ⁿ), complements A′, and De Morgan's Laws.",
                  "Compute composite functions f(g(x)) and inverse functions f⁻¹(x).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. The Golden Rule of Venn Diagrams: Fill From Inside Out!",
                    explanation =
                      "Suppose 30 students like Football and 20 like Music in a class of 40. Why does 30 + 20 = 50 (more than 40!)? Because people who like BOTH were counted TWICE! Think of two overlapping hula-hoops: always put the middle overlap x first, then subtract x from each hoop so nobody gets counted twice.",
                    keyFormula = "n(A \\cup B) = n(A) + n(B) - n(A \\cap B)   |   \\text{Subsets} = 2^n",
                    examTip =
                      "'Only A' means n(A) − n(A ∩ B). If '5 study neither', subtract 5 from n(U) BEFORE solving the hoops!",
                  ),
                  ConceptBlock(
                    heading = "2. Functions f(x) & Inverse f⁻¹(x): The Forward & Undo Machines",
                    explanation =
                      "A function f(x) = 2x + 3 is like a factory machine: drop x in, it doubles x then adds 3. Its inverse f⁻¹(x) is the UNDO machine: write y = 2x + 3, swap x and y (x = 2y + 3), and solve for y to get f⁻¹(x) = (x − 3)/2!",
                    keyFormula = "f(g(x)) = f\\big(g(x)\\big)   |   f\\big(f^{-1}(x)\\big) = x",
                    examTip =
                      "Never confuse f(g(x)) with g(f(x)): compute the INNER bracket first and plug that answer into the outer function!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Class Survey 2-Set Venn Diagram",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "In a class of 50 students, 32 offer Biology, 24 offer Chemistry, and 4 offer neither. How many offer ONLY Biology?",
                    steps =
                      listOf(
                        "Step 1 — Students offering at least one: 50 − 4 = 46.",
                        "Step 2 — Overlap Both = 32 + 24 − 46 = 10.",
                        "Step 3 — Biology ONLY = 32 − 10 = 22 students.",
                      ),
                    finalAnswer = "22 students",
                    examinerTrap = "Forgetting to subtract the 4 'neither' students before adding 32 + 24!",
                  ),
                  WorkedExample(
                    title = "Finding Inverse Function f⁻¹(x)",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Given f(x) = (3x − 1) / 2, find f⁻¹(4).",
                    steps =
                      listOf(
                        "Step 1 — Let y = (3x − 1) / 2 ⇒ 2y = 3x − 1 ⇒ x = (2y + 1) / 3.",
                        "Step 2 — Thus f⁻¹(x) = (2x + 1) / 3.",
                        "Step 3 — f⁻¹(4) = (2(4) + 1) / 3 = 9 / 3 = 3.",
                      ),
                    finalAnswer = "3",
                    examinerTrap = "Don't plug 4 into f(x) directly; undo the algebra or solve (3x−1)/2 = 4!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Search Engines & Music App Recommendations",
                    industryDomain = "Data Science & SQL",
                    narrative =
                      "SQL database queries use Set Intersection (∩), Union (∪), and Difference (−) millions of times per second to match users who bought Phone A AND Earbuds B.",
                    examConnection = "Venn overlaps are literally boolean database filters.",
                  ),
                ),
              labType = PracticalLabType.SETS_VENN_LAB,
              labBrief =
                "Drag Set A, Set B, Universe n(U), and Overlap n(A∩B) sliders to watch Venn regions shade live and verify Inclusion-Exclusion.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_03_1",
                    topic = "2-Set Venn",
                    examType = "JAMB",
                    year = 2024,
                    question = "In a club of 40 members, 25 play tennis, 22 play chess, and everyone plays at least one. How many play BOTH?",
                    options = listOf("3", "7", "15", "18"),
                    correctIndex = 1,
                    hint = "n(A ∩ B) = n(A) + n(B) − n(A ∪ B).",
                    stepByStepSolution = "25 + 22 − 40 = 7 play both.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_03_2",
                    topic = "Subsets & Power Set",
                    examType = "WAEC",
                    year = 2023,
                    question = "How many proper subsets does the set P = {2, 3, 5, 7} have?",
                    options = listOf("8", "15", "16", "14"),
                    correctIndex = 1,
                    hint = "Total subsets = 2ⁿ; proper subsets exclude P itself (2ⁿ − 1).",
                    stepByStepSolution = "2⁴ − 1 = 16 − 1 = 15 proper subsets.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_03_3",
                    topic = "Complement of Set",
                    examType = "JAMB",
                    year = 2025,
                    question = "If U = {1,2,3,4,5,6}, A = {1,3,5}, B = {2,3,6}, find (A ∪ B)′.",
                    options = listOf("{4}", "{3}", "{1, 2}", "{4, 5}"),
                    correctIndex = 0,
                    hint = "Find A ∪ B first, then take elements in U outside it.",
                    stepByStepSolution = "A ∪ B = {1, 2, 3, 5, 6} ⇒ complement = {4}.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_03_4",
                    topic = "Composite Functions",
                    examType = "JAMB",
                    year = 2022,
                    question = "If f(x) = x² + 1 and g(x) = 2x − 3, find f(g(2)).",
                    options = listOf("2", "5", "7", "10"),
                    correctIndex = 0,
                    hint = "Compute g(2) first.",
                    stepByStepSolution = "g(2) = 4 − 3 = 1; then f(1) = 1² + 1 = 2.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_03_5",
                    topic = "3-Set Venn",
                    examType = "WAEC",
                    year = 2024,
                    question = "If n(P)=18, n(Q)=15, n(P∩Q)=6, n(neither)=3, find the universal set n(U).",
                    options = listOf("27", "30", "33", "36"),
                    correctIndex = 1,
                    hint = "n(U) = n(P ∪ Q) + n(neither).",
                    stepByStepSolution = "(18 + 15 − 6) + 3 = 27 + 3 = 30.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_03_6",
                    topic = "Domain Restriction",
                    examType = "NECO",
                    year = 2023,
                    question = "For what value of x is the function f(x) = (x + 5) / (x² − 9) undefined?",
                    options = listOf("−5", "3 or −3", "9", "0"),
                    correctIndex = 1,
                    hint = "A fraction blows up when its denominator equals zero.",
                    stepByStepSolution = "x² − 9 = 0 ⇒ x = ±3.",
                  ),
                ),
            ),
          ),
      ),

      CourseModule(
        id = "math_mod_2",
        subjectId = "mathematics",
        indexLabel = "Module 02",
        title = "Algebra, Quadratics, Polynomials, Sequences & Matrices",
        summary = "Master Variation, Inequalities, Factor/Remainder Theorems, AP/GP progressions, Binary Operations & Matrix Inverses.",
        lessons =
          listOf(
            Lesson(
              id = "math_04_linear_variation_ineq",
              moduleId = "math_mod_2",
              subjectId = "mathematics",
              title = "Variation, Simultaneous Equations & Inequalities",
              durationMinutes = 19,
              syllabusTag = "JAMB Sec. 2.2–2.4 • WAEC Variation & Inequalities",
              learningOutcomes =
                listOf(
                  "Translate Direct (y \\propto x), Inverse (y \\propto \\frac{1}{x}), Joint, and Partial Variation sentences into equations.",
                  "Solve linear simultaneous equations and quadratic inequalities cleanly.",
                  "Flip inequality signs ONLY when multiplying or dividing by a NEGATIVE number.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. The 4 Variation Recipes (Always Find Constant k First!)",
                    explanation =
                      "Think of Variation as a recipe ratio: Direct (y \\propto x) means when x doubles, y doubles (y = kx). Inverse (y \\propto \\frac{1}{x}) means more workers take LESS time (y = \\frac{k}{x}). Partial Variation has a fixed base fee PLUS a variable rate—just like a ride-hailing taxi: Base fare + Naira/km \\implies C = a + bx.",
                    keyFormula = "\\text{Direct: } y = kx   |   \\text{Inverse: } y = \\frac{k}{x}   |   \\text{Partial: } y = a + bx",
                    examTip =
                      "Read wording carefully: 'varies PARTLY as x and partly as x²' needs TWO constants: y = ax + bx², not one!",
                  ),
                  ConceptBlock(
                    heading = "2. Inequalities: The Negative Flip Rule",
                    explanation =
                      "Inequalities act just like equals (=), EXCEPT for one trap: 2 < 5 is true, multiply both sides by −1 and you MUST flip it to −2 > −5! For quadratic inequalities like x² − 5x + 6 < 0, factor to (x − 2)(x − 3) < 0: '< 0' traps x BETWEEN the roots (2 < x < 3), while '> 0' sends x OUTSIDE (x < 2 or x > 3).",
                    keyFormula = "(x - r_1)(x - r_2) < 0 \\implies r_1 < x < r_2\\quad (r_1 < r_2)",
                    examTip =
                      "Never say 'x > 2 and x > 3' for (x−2)(x−3) > 0; check a test point like x = 0 to be 100% certain!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Joint & Inverse Variation Word Problem",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "P varies directly as Q and inversely as √R. When Q = 6 and R = 9, P = 8. Find P when Q = 10 and R = 25.",
                    steps =
                      listOf(
                        "Step 1 — Write formula: P = kQ / √R.",
                        "Step 2 — Plug initial values: 8 = 6k / √9 = 6k / 3 = 2k ⇒ k = 4.",
                        "Step 3 — New P = 4(10) / √25 = 40 / 5 = 8.",
                      ),
                    finalAnswer = "P = 8",
                    examinerTrap = "Don't forget the square root on R when plugging R = 25!",
                  ),
                  WorkedExample(
                    title = "Quadratic Inequality Interval",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Find the range of values of x for which x² − x − 6 ≤ 0.",
                    steps =
                      listOf(
                        "Step 1 — Factor: (x − 3)(x + 2) ≤ 0.",
                        "Step 2 — Boundary roots are x = −2 and x = 3.",
                        "Step 3 — Test x = 0: (0−3)(0+2) = −6 ≤ 0 (true!), so −2 ≤ x ≤ 3.",
                      ),
                    finalAnswer = "−2 ≤ x ≤ 3",
                    examinerTrap = "Writing x ≤ −2 or x ≤ 3 instead of the single trapped interval −2 ≤ x ≤ 3.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Fuel Budget Constraints & Ohm's Law Variation",
                    industryDomain = "Logistics & Electrical Design",
                    narrative =
                      "Delivery fleets optimize trucks with linear inequalities (cost ≤ budget, payload ≥ order), while Physics' Ohm's Law I = V/R is literally direct + inverse variation.",
                    examConnection = "Links linear constraints directly to Linear Programming feasible regions.",
                  ),
                ),
              labType = PracticalLabType.ALGEBRA_VARIATION_LAB,
              labBrief =
                "Switch between Direct, Inverse, and Partial variation or slide inequality bounds to see exact shaded intervals and constants k.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_04_1",
                    topic = "Inverse Variation",
                    examType = "JAMB",
                    year = 2024,
                    question = "y varies inversely as x². If y = 3 when x = 2, find y when x = 6.",
                    options = listOf("1/3", "1", "9", "1/9"),
                    correctIndex = 0,
                    hint = "y = k / x², so k = 3 × 2² = 12.",
                    stepByStepSolution = "y = 12 / 6² = 12 / 36 = 1/3.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_04_2",
                    topic = "Linear Inequality",
                    examType = "WAEC",
                    year = 2023,
                    question = "Solve 5 − 2x > 11.",
                    options = listOf("x < −3", "x > −3", "x < 3", "x > 8"),
                    correctIndex = 0,
                    hint = "Subtract 5, then divide by −2 and flip '>' to '<'.",
                    stepByStepSolution = "−2x > 6 ⇒ x < −3.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_04_3",
                    topic = "Partial Variation",
                    examType = "WAEC",
                    year = 2022,
                    question = "C is partly constant and partly varies as n. When n=10, C=45; when n=20, C=75. Find C when n=30.",
                    options = listOf("95", "105", "110", "120"),
                    correctIndex = 1,
                    hint = "C = a + bn gives 45 = a + 10b and 75 = a + 20b.",
                    stepByStepSolution = "Subtracting gives 10b = 30 ⇒ b = 3, a = 15. C(30) = 15 + 3(30) = 105.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_04_4",
                    topic = "Quadratic Inequality",
                    examType = "JAMB",
                    year = 2025,
                    question = "Find the set of values satisfying 2x² − 5x − 3 > 0.",
                    options = listOf("x < −1/2 or x > 3", "−1/2 < x < 3", "x < −3 or x > 1/2", "x > 3"),
                    correctIndex = 0,
                    hint = "Factor as (2x + 1)(x − 3) > 0.",
                    stepByStepSolution = "Roots are −1/2 and 3; '>' sends x outside both roots: x < −1/2 or x > 3.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_04_5",
                    topic = "Simultaneous Equations",
                    examType = "JAMB",
                    year = 2021,
                    question = "If 3x + 2y = 12 and x − y = 4, find x + y.",
                    options = listOf("4", "6", "8", "2"),
                    correctIndex = 0,
                    hint = "Multiply second by 2 and add.",
                    stepByStepSolution = "5x = 20 ⇒ x = 4, y = 0 ⇒ x + y = 4.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_04_6",
                    topic = "Change of Subject",
                    examType = "NECO",
                    year = 2024,
                    question = "Make r the subject of V = (1/3)πr²h.",
                    options = listOf("√(3V / πh)", "3V / πh", "√(V / 3πh)", "√(πh / 3V)"),
                    correctIndex = 0,
                    hint = "Multiply by 3, divide by πh, then take square root.",
                    stepByStepSolution = "r² = 3V/(πh) ⇒ r = √(3V / πh).",
                  ),
                ),
            ),

            Lesson(
              id = "math_05_quadratics_polynomials",
              moduleId = "math_mod_2",
              subjectId = "mathematics",
              title = "Quadratics, Sum/Product of Roots & Polynomial Theorems",
              durationMinutes = 21,
              syllabusTag = "JAMB Sec. 2.1 • WAEC Quadratics & Polynomials",
              learningOutcomes =
                listOf(
                  "Use discriminant b² − 4ac to predict whether a quadratic has 2, 1, or 0 real roots.",
                  "Find α + β = −b/a and αβ = c/a WITHOUT solving the quadratic first.",
                  "Apply Remainder Theorem P(a) and Factor Theorem to higher polynomials.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Quadratics, Turning Point & Root Shortcuts (α + β, αβ)",
                    explanation =
                      "Every equation ax² + bx + c = 0 draws a U-shaped parabola. Its discriminant Δ = b² − 4ac acts like a weather forecast: Δ > 0 crosses the x-axis twice (2 real roots), Δ = 0 kisses it once (equal roots), Δ < 0 floats above/below (no real roots). Best shortcut in JAMB: Sum of roots α + β = −b/a and Product αβ = c/a!",
                    keyFormula = "\\alpha + \\beta = -\\frac{b}{a},\\quad \\alpha\\beta = \\frac{c}{a}   |   \\alpha^2 + \\beta^2 = (\\alpha + \\beta)^2 - 2\\alpha\\beta",
                    examTip =
                      "To make a brand new equation with roots m and n, simply write x² − (m + n)x + mn = 0!",
                  ),
                  ConceptBlock(
                    heading = "2. Remainder & Factor Theorems (Zero Long Division!)",
                    explanation =
                      "Want the remainder when a huge polynomial P(x) is divided by (x − a)? Don't do messy long division! Just plug x = a straight into P(x): the number P(a) IS your remainder! If P(a) = 0, then (x − a) divides cleanly and IS a factor.",
                    keyFormula = "\\text{Divisor } (px - q) \\implies \\text{Remainder} = P\\!\\left(\\frac{q}{p}\\right)",
                    examTip =
                      "Dividing by (x + 3) means x + 3 = 0, so plug x = −3 (flip the sign!).",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding α² + β² Using Root Shortcuts",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "If α and β are roots of 2x² − 6x + 5 = 0, find α² + β².",
                    steps =
                      listOf(
                        "Step 1 — α + β = −(−6)/2 = 3; αβ = 5/2.",
                        "Step 2 — α² + β² = (α + β)² − 2αβ.",
                        "Step 3 — 3² − 2(5/2) = 9 − 5 = 4.",
                      ),
                    finalAnswer = "4",
                    examinerTrap = "(α + β)² is NOT α² + β²—always subtract 2αβ!",
                  ),
                  WorkedExample(
                    title = "Remainder Theorem Constant k",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "When P(x) = x³ + kx² − 5x + 6 is divided by (x − 2), the remainder is 8. Find k.",
                    steps =
                      listOf(
                        "Step 1 — Set P(2) = 8.",
                        "Step 2 — 2³ + k(2²) − 5(2) + 6 = 8 ⇒ 8 + 4k − 10 + 6 = 8.",
                        "Step 3 — 4 + 4k = 8 ⇒ 4k = 4 ⇒ k = 1.",
                      ),
                    finalAnswer = "k = 1",
                    examinerTrap = "Forgetting the free constant +6 or substituting −2 instead of +2!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Finding Maximum Revenue Point for a Business",
                    industryDomain = "Business Economics",
                    narrative =
                      "Raising ticket prices too high makes fewer people buy, so revenue R(x) = −ax² + bx forms a downward parabola whose vertex gives maximum profit.",
                    examConnection = "Vertex turning point x = −b/(2a) is maximum/minimum value of any quadratic.",
                  ),
                ),
              labType = PracticalLabType.QUADRATIC_TANGENT,
              labBrief =
                "Adjust coefficients a, b, c interactively to observe real-time parabola roots, vertex x = −b/2a, and discriminant Δ = b² − 4ac.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_05_1",
                    topic = "Equal Roots Condition",
                    examType = "JAMB",
                    year = 2024,
                    question = "Find k so that x² − kx + 9 = 0 has equal roots.",
                    options = listOf("±6", "±3", "9", "±18"),
                    correctIndex = 0,
                    hint = "Set b² − 4ac = 0.",
                    stepByStepSolution = "(−k)² − 4(1)(9) = 0 ⇒ k² = 36 ⇒ k = ±6.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_05_2",
                    topic = "Factor Theorem",
                    examType = "WAEC",
                    year = 2023,
                    question = "If (x + 1) is a factor of 2x³ + 3x² − ax + 4, find a.",
                    options = listOf("−5", "5", "3", "−3"),
                    correctIndex = 0,
                    hint = "Plug x = −1 and set P(−1) = 0.",
                    stepByStepSolution = "2(−1) + 3(1) − a(−1) + 4 = 0 ⇒ −2 + 3 + a + 4 = 0 ⇒ a = −5.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_05_3",
                    topic = "Roots Construction",
                    examType = "JAMB",
                    year = 2025,
                    question = "Form a quadratic equation whose roots are 3 and −4.",
                    options = listOf("x² + x − 12 = 0", "x² − x − 12 = 0", "x² + x + 12 = 0", "x² − 7x + 12 = 0"),
                    correctIndex = 0,
                    hint = "Sum = 3 + (−4) = −1; Product = −12; x² − (sum)x + prod = 0.",
                    stepByStepSolution = "x² − (−1)x + (−12) = x² + x − 12 = 0.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_05_4",
                    topic = "Vertex Minimum",
                    examType = "WAEC",
                    year = 2022,
                    question = "Find the minimum value of y = x² − 6x + 11.",
                    options = listOf("2", "3", "11", "−2"),
                    correctIndex = 0,
                    hint = "Complete the square: (x − 3)² + 2.",
                    stepByStepSolution = "Occurs at x = 3 where y = 9 − 18 + 11 = 2.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_05_5",
                    topic = "Remainder Theorem",
                    examType = "JAMB",
                    year = 2023,
                    question = "Find the remainder when 4x³ − 2x + 1 is divided by (2x − 1).",
                    options = listOf("1/2", "1", "3/2", "0"),
                    correctIndex = 0,
                    hint = "Set 2x − 1 = 0 ⇒ x = 1/2.",
                    stepByStepSolution = "P(1/2) = 4(1/8) − 2(1/2) + 1 = 1/2 − 1 + 1 = 1/2.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_05_6",
                    topic = "Reciprocal Roots",
                    examType = "NECO",
                    year = 2021,
                    question = "If α, β are roots of 3x² + 9x − 2 = 0, find (1/α) + (1/β).",
                    options = listOf("9/2", "−9/2", "3", "−2/9"),
                    correctIndex = 0,
                    hint = "(1/α) + (1/β) = (α + β) / (αβ).",
                    stepByStepSolution = "α+β = −3, αβ = −2/3 ⇒ (−3)/(−2/3) = +9/2.",
                  ),
                ),
            ),

            Lesson(
              id = "math_06_sequences_binary",
              moduleId = "math_mod_2",
              subjectId = "mathematics",
              title = "Sequences (AP & GP) and Binary Operations",
              durationMinutes = 19,
              syllabusTag = "JAMB Sec. 2.5 & 2.7 • WAEC Progressions & Operations",
              learningOutcomes =
                listOf(
                  "Calculate nth terms and sums for Arithmetic Progressions (AP) and Geometric Progressions (GP).",
                  "Evaluate GP Sum to Infinity S∞ = a / (1 − r) when |r| < 1.",
                  "Check commutativity, associativity, identity element e, and inverse x⁻¹ for any custom rule a * b.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. AP (Adding Step-by-Step) vs GP (Multiplying Step-by-Step)",
                    explanation =
                      "Climb stairs 3 steps at a time (2, 5, 8, 11...): that steady ADDITION (+d) is an Arithmetic Progression (AP). Double your savings every day (₦2, ₦4, ₦8, ₦16...): steady MULTIPLICATION (×r) is a Geometric Progression (GP). If r is a proper fraction like 1/2, adding forever never explodes—it stops neatly at S∞ = a/(1 − r)!",
                    keyFormula = "\\text{AP: } T_n = a + (n-1)d,\\quad S_n = \\frac{n}{2}[2a + (n-1)d]   |   \\text{GP: } T_n = ar^{n-1},\\quad S_\\infty = \\frac{a}{1-r}",
                    examTip =
                      "Always divide consecutive GP terms (T₂/T₁) for r, and subtract (T₂ − T₁) for AP difference d!",
                  ),
                  ConceptBlock(
                    heading = "2. Binary Operations (*): Secret Recipe Rules",
                    explanation =
                      "A binary operation like x * y = x + y + 2xy is just a custom cooking rule combining two numbers. Identity element e is the 'do-nothing' number that gives you back what you started with: solve x * e = x! Inverse element x⁻¹ is the partner that brings you back to e: solve x * x⁻¹ = e.",
                    keyFormula = "a * e = a \\text{ (Identity } e)   |   a * a^{-1} = e \\text{ (Inverse } a^{-1})",
                    examTip =
                      "Check commutativity simply by swapping letters: does a * b always give the same formula as b * a?",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding GP Terms & Sum to Infinity",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "The 2nd and 5th terms of a GP are 6 and 48. Find the first term a and S₄.",
                    steps =
                      listOf(
                        "Step 1 — T₅ / T₂ = ar⁴ / ar = r³ = 48 / 6 = 8 ⇒ r = 2.",
                        "Step 2 — Since ar = 6, 2a = 6 ⇒ a = 3.",
                        "Step 3 — S₄ = 3(2⁴ − 1) / (2 − 1) = 3 × 15 = 45.",
                      ),
                    finalAnswer = "a = 3, S₄ = 45",
                    examinerTrap = "For GP divide T₅ ÷ T₂; only subtract for AP!",
                  ),
                  WorkedExample(
                    title = "Identity and Inverse Under Custom Operation",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Define a * b = a + b − ab on ℝ. Find (i) identity e and (ii) inverse of 3.",
                    steps =
                      listOf(
                        "Step 1 — Solve a * e = a ⇒ a + e − ae = a ⇒ e(1 − a) = 0 ⇒ e = 0.",
                        "Step 2 — Solve 3 * x = 0 ⇒ 3 + x − 3x = 0 ⇒ 3 − 2x = 0 ⇒ x = 3/2.",
                      ),
                    finalAnswer = "Identity e = 0  |  Inverse of 3 is 3/2",
                    examinerTrap = "You MUST find identity e first before solving for an element's inverse!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Bouncing Ball Decay & Loan Repayment Schedules",
                    industryDomain = "Physics & Finance",
                    narrative =
                      "A basketball bouncing to 60% of its previous height travels a total distance given by a GP Sum to Infinity S∞!",
                    examConnection = "Classic JAMB bouncing-ball word problem using S∞ = a/(1−r).",
                  ),
                ),
              labType = PracticalLabType.SEQUENCE_MATRIX_LAB,
              labBrief =
                "Generate AP & GP term charts, watch GP Sum to Infinity converge when |r| < 1, and verify exact partial sums Sₙ live.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_06_1",
                    topic = "AP Sum",
                    examType = "JAMB",
                    year = 2024,
                    question = "Find the sum of the first 10 terms of the AP: 3, 7, 11, ...",
                    options = listOf("210", "190", "220", "185"),
                    correctIndex = 0,
                    hint = "a = 3, d = 4, n = 10.",
                    stepByStepSolution = "S₁₀ = (10/2)[2(3) + 9(4)] = 5(6 + 36) = 5 × 42 = 210.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_06_2",
                    topic = "GP Sum to Infinity",
                    examType = "WAEC",
                    year = 2023,
                    question = "Find the sum to infinity of the series 12 + 4 + 4/3 + ...",
                    options = listOf("16", "18", "24", "36"),
                    correctIndex = 1,
                    hint = "a = 12, r = 4/12 = 1/3.",
                    stepByStepSolution = "S∞ = 12 / (1 − 1/3) = 12 / (2/3) = 18.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_06_3",
                    topic = "AP Unknown Term",
                    examType = "JAMB",
                    year = 2025,
                    question = "If x − 1, 2x + 1, and 5x − 1 are three consecutive terms of an AP, find x.",
                    options = listOf("2", "3", "4", "1"),
                    correctIndex = 0,
                    hint = "Middle term equals average of the two outer terms, or T₂ − T₁ = T₃ − T₂.",
                    stepByStepSolution = "(2x+1) − (x−1) = (5x−1) − (2x+1) ⇒ x + 2 = 3x − 2 ⇒ 2x = 4 ⇒ x = 2.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_06_4",
                    topic = "Binary Identity",
                    examType = "WAEC",
                    year = 2022,
                    question = "Under x * y = x + y + 3, find the identity element e.",
                    options = listOf("0", "−3", "3", "−1"),
                    correctIndex = 1,
                    hint = "Set x * e = x.",
                    stepByStepSolution = "x + e + 3 = x ⇒ e = −3.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_06_5",
                    topic = "Binary Inverse",
                    examType = "JAMB",
                    year = 2023,
                    question = "If p * q = pq / 2 on non-zero reals, find the inverse of 5.",
                    options = listOf("4/5", "2/5", "5/2", "1/5"),
                    correctIndex = 0,
                    hint = "First identity p*e/2 = p ⇒ e = 2; then 5 * x = 2.",
                    stepByStepSolution = "5x / 2 = 2 ⇒ x = 4/5.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_06_6",
                    topic = "Geometric Mean",
                    examType = "NECO",
                    year = 2024,
                    question = "Insert one positive geometric mean between 4 and 36.",
                    options = listOf("12", "16", "20", "18"),
                    correctIndex = 0,
                    hint = "Geometric mean of a and b is √(ab).",
                    stepByStepSolution = "√(4 × 36) = √144 = 12.",
                  ),
                ),
            ),
          ),
      ),
    )
}
