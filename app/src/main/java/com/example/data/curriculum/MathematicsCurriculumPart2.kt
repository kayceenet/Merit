package com.example.data.curriculum

object MathematicsCurriculumPart2 {

  val module3And4: List<CourseModule> =
    listOf(
      CourseModule(
        id = "math_mod_3",
        subjectId = "mathematics",
        indexLabel = "Module 03",
        title = "Matrices, Polygons, Circle Theorems & Mensuration",
        summary = "Master 2×2 determinants & inverses, polygon angle sums, all 7 Circle Theorems, loci, sectors, cones, cylinders, spheres & frustums.",
        lessons =
          listOf(
            Lesson(
              id = "math_07_matrices_determinants",
              moduleId = "math_mod_3",
              subjectId = "mathematics",
              title = "Matrices, Determinants & Inverses",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 2.8 • WAEC Further Matrices",
              learningOutcomes =
                listOf(
                  "Multiply compatible matrices row-by-column cleanly without mixing up order.",
                  "Compute determinants of 2×2 and 3×3 matrices and spot singular matrices (|A| = 0).",
                  "Find inverse matrix A⁻¹ and solve simultaneous systems via Cramér's/Inverse rule.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Multiplying Matrices: Dive Across the Row, Down the Column!",
                    explanation =
                      "Think of a matrix as a neatly organized table of numbers. To multiply A × B, take Row 1 of A horizontally and dive down Column 1 of B vertically—multiply matching buddies and add them! Matrix multiplication is NOT commutative: A × B usually gives a totally different answer from B × A.",
                    keyFormula = "(m \\times n) \\times (n \\times p) \\to (m \\times p)   |   \\det\\begin{pmatrix}a & b\\\\ c & d\\end{pmatrix} = ad - bc",
                    examTip =
                      "If a matrix has NO inverse ('singular matrix'), immediately set its determinant ad − bc = 0 and solve!",
                  ),
                  ConceptBlock(
                    heading = "2. The 2×2 Inverse Shortcut (Swap, Negate, Divide!)",
                    explanation =
                      "To invert A = [[a, b], [c, d]], remember three quick moves: (1) SWAP the main diagonal (d and a trade seats), (2) flip the SIGNS of the other diagonal (−b and −c), and (3) DIVIDE every entry by the determinant (ad − bc).",
                    keyFormula = "A^{-1} = \\frac{1}{ad - bc}\\begin{pmatrix}d & -b\\\\ -c & a\\end{pmatrix}",
                    examTip =
                      "For a 3×3 determinant, expand along row 1 using (+, −, +) signs on each 2×2 minor!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding Unknown x in a Singular Matrix",
                    examSource = "JAMB UTME 2024 • Maths Q17",
                    problemStatement = "Given A = [[x, 3], [4, x − 1]] is singular, find the positive value of x.",
                    steps =
                      listOf(
                        "Step 1 — Singular means det(A) = 0.",
                        "Step 2 — x(x − 1) − (3 × 4) = 0 ⇒ x² − x − 12 = 0.",
                        "Step 3 — (x − 4)(x + 3) = 0 ⇒ x = 4 (positive).",
                      ),
                    finalAnswer = "x = 4",
                    examinerTrap = "Don't forget the minus sign in ad − bc!",
                  ),
                  WorkedExample(
                    title = "Computing a 2×2 Inverse Matrix",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q21",
                    problemStatement = "Find the inverse of M = [[5, 3], [2, 2]].",
                    steps =
                      listOf(
                        "Step 1 — det(M) = (5 × 2) − (3 × 2) = 10 − 6 = 4.",
                        "Step 2 — Swap 5 and 2; negate 3 and 2 ⇒ [[2, −3], [−2, 5]].",
                        "Step 3 — Multiply by 1/4: [[1/2, −3/4], [−1/2, 5/4]].",
                      ),
                    finalAnswer = "(1/4)[[2, −3], [−2, 5]]",
                    examinerTrap = "Swap ONLY a and d—never swap b and c, only flip their signs!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Rotating & Scaling 3D Game Graphics",
                    industryDomain = "Computer Graphics & Robotics",
                    narrative =
                      "Every time your character turns in a 3D mobile game, the GPU multiplies every vertex coordinate by a 3×3 rotation matrix.",
                    examConnection = "Linear transformations preserve lines and invert via A⁻¹.",
                  ),
                ),
              labType = PracticalLabType.SEQUENCE_MATRIX_LAB,
              labBrief =
                "Adjust 2×2 matrix entries [a, b; c, d] interactively to compute det(A), check singularity, and inspect A⁻¹.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_07_1",
                    topic = "Determinant",
                    examType = "JAMB",
                    year = 2024,
                    question = "Evaluate the determinant of [[4, −2], [3, 5]].",
                    options = listOf("14", "26", "20", "−26"),
                    correctIndex = 1,
                    hint = "det = (4×5) − (−2×3).",
                    stepByStepSolution = "20 − (−6) = 20 + 6 = 26.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_07_2",
                    topic = "Singular Matrix",
                    examType = "WAEC",
                    year = 2023,
                    question = "For what value of k is [[k, 6], [2, 3]] singular?",
                    options = listOf("4", "9", "1", "12"),
                    correctIndex = 0,
                    hint = "Set 3k − 12 = 0.",
                    stepByStepSolution = "3k − (6×2) = 0 ⇒ 3k = 12 ⇒ k = 4.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_07_3",
                    topic = "Matrix Multiplication",
                    examType = "JAMB",
                    year = 2025,
                    question = "If A = [[2, 1], [0, 3]] and B = [[1, 4], [2, −1]], find the top-right entry of A × B.",
                    options = listOf("7", "4", "8", "−3"),
                    correctIndex = 0,
                    hint = "Row 1 of A dot Column 2 of B.",
                    stepByStepSolution = "(2 × 4) + (1 × −1) = 8 − 1 = 7.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_07_4",
                    topic = "Inverse Matrix",
                    examType = "WAEC",
                    year = 2022,
                    question = "Find the determinant of the inverse of P = [[3, 1], [4, 2]].",
                    options = listOf("2", "1/2", "−2", "1/10"),
                    correctIndex = 1,
                    hint = "det(P⁻¹) = 1 / det(P).",
                    stepByStepSolution = "det(P) = 3(2) − 1(4) = 2 ⇒ det(P⁻¹) = 1/2.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_07_5",
                    topic = "Matrix Addition",
                    examType = "JAMB",
                    year = 2021,
                    question = "If [[x, 2], [3, y]] + [[1, −1], [2, 4]] = [[5, 1], [5, 7]], find x + y.",
                    options = listOf("7", "8", "6", "9"),
                    correctIndex = 0,
                    hint = "x + 1 = 5 and y + 4 = 7.",
                    stepByStepSolution = "x = 4, y = 3 ⇒ x + y = 7.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_07_6",
                    topic = "3×3 Determinant",
                    examType = "JAMB",
                    year = 2024,
                    question = "Evaluate det [[1, 0, 2], [0, 3, 1], [0, 0, 4]].",
                    options = listOf("12", "8", "24", "6"),
                    correctIndex = 0,
                    hint = "Upper-triangular matrix determinant is the product of the main diagonal!",
                    stepByStepSolution = "1 × (3×4 − 0) = 12.",
                  ),
                ),
            ),

            Lesson(
              id = "math_08_polygons_parallels",
              moduleId = "math_mod_3",
              subjectId = "mathematics",
              title = "Parallel Lines, Triangles & Polygon Angles",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. 3.1 • WAEC Euclidean Geometry",
              learningOutcomes =
                listOf(
                  "Identify Z-angles (Alternate), F-angles (Corresponding), and C-angles (Co-interior = 180°).",
                  "Calculate Interior Sum (n − 2) × 180° and Exterior Angle 360°/n of any n-sided polygon.",
                  "Solve triangle congruence and similarity area-ratio problems (Area Ratio = k²).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. The 3 Parallel-Line Letters: Z, F, and C!",
                    explanation =
                      "Whenever a straight line cuts across two parallel tracks: look for a 'Z' shape—the inner corners are ALTERNATE and EQUAL! Look for an 'F' shape—the corners underneath the bars are CORRESPONDING and EQUAL! Look for a 'C' (or U) bracket—both inside corners add up to 180°!",
                    keyFormula = "\\text{Interior Sum} = (n - 2)\\times 180^\\circ   |   \\text{Each Exterior (Regular)} = \\frac{360^\\circ}{n}",
                    examTip =
                      "Need the number of sides n quickly? Always find ONE exterior angle (180° − interior), then divide n = 360° / exterior!",
                  ),
                  ConceptBlock(
                    heading = "2. Similar Shapes: Lengths Scale by k, Areas Scale by k²!",
                    explanation =
                      "If you zoom a photo of a triangle to 3× its height, every side multiplies by 3, AND its Area grows by 3 × 3 = 9 times (k²)! Likewise, volumes of similar 3D shapes scale by k³.",
                    keyFormula = "\\frac{L_1}{L_2} = k \\implies \\frac{A_1}{A_2} = k^2,\\quad \\frac{V_1}{V_2} = k^3",
                    examTip =
                      "In triangles, an exterior angle equals the SUM of the two opposite interior angles!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding Number of Sides from Interior Angle",
                    examSource = "JAMB UTME 2024 • Maths Q19",
                    problemStatement = "Each interior angle of a regular polygon is 150°. How many sides does it have?",
                    steps =
                      listOf(
                        "Step 1 — Exterior angle = 180° − 150° = 30°.",
                        "Step 2 — Sides n = 360° / 30° = 12 sides (Dodecagon).",
                      ),
                    finalAnswer = "12 sides",
                    examinerTrap = "Using 360°/150° directly instead of subtracting from 180° first!",
                  ),
                  WorkedExample(
                    title = "Similar Triangles Area Ratio",
                    examSource = "WAEC SSCE 2023 • Paper 1 Q24",
                    problemStatement =
                      "Two similar triangles have corresponding sides 4 cm and 6 cm. If the smaller has area 24 cm², find the area of the larger.",
                    steps =
                      listOf(
                        "Step 1 — Linear ratio k = 6/4 = 3/2.",
                        "Step 2 — Area ratio = k² = 9/4.",
                        "Step 3 — Larger Area = 24 × (9/4) = 54 cm².",
                      ),
                    finalAnswer = "54 cm²",
                    examinerTrap = "Multiplying 24 by 6/4 instead of squaring the scale factor!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Honeycomb Hexagons & Roof Trusses",
                    industryDomain = "Architecture & Biology",
                    narrative =
                      "Bees build regular hexagons (interior angle 120°) because 120° × 3 = 360° tiles flat with zero wasted wax!",
                    examConnection = "Explains why only triangles, squares, and hexagons tessellate neatly.",
                  ),
                ),
              labType = PracticalLabType.POLYGON_CIRCLE_THEOREM_LAB,
              labBrief =
                "Adjust side count n (3–12) to inspect interior/exterior angle geometry and verify Sum = (n − 2) × 180°.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_08_1",
                    topic = "Polygon Sides",
                    examType = "JAMB",
                    year = 2024,
                    question = "The sum of interior angles of a polygon is 1080°. Find the number of sides.",
                    options = listOf("6", "7", "8", "9"),
                    correctIndex = 2,
                    hint = "(n − 2) × 180 = 1080.",
                    stepByStepSolution = "n − 2 = 1080 / 180 = 6 ⇒ n = 8 (Octagon).",
                  ),
                  PracticeQuestion(
                    id = "ex_math_08_2",
                    topic = "Parallel Transversal",
                    examType = "WAEC",
                    year = 2023,
                    question = "Two co-interior angles between parallel lines are (3x − 10)° and (2x + 40)°. Find x.",
                    options = listOf("25°", "30°", "36°", "40°"),
                    correctIndex = 1,
                    hint = "Co-interior C-angles sum to 180°.",
                    stepByStepSolution = "5x + 30 = 180 ⇒ 5x = 150 ⇒ x = 30°.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_08_3",
                    topic = "Exterior Angle Sum",
                    examType = "JAMB",
                    year = 2025,
                    question = "The exterior angles of a pentagon are x, 2x, 2x, 3x, and 4x. Find x.",
                    options = listOf("24°", "30°", "36°", "45°"),
                    correctIndex = 1,
                    hint = "All exterior angles of ANY convex polygon sum to 360°.",
                    stepByStepSolution = "12x = 360 ⇒ x = 30°.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_08_4",
                    topic = "Similar Shapes",
                    examType = "WAEC",
                    year = 2022,
                    question = "Two similar cones have heights 3 cm and 9 cm. Find the ratio of their volumes.",
                    options = listOf("1 : 3", "1 : 9", "1 : 27", "1 : 81"),
                    correctIndex = 2,
                    hint = "Volume ratio is k³.",
                    stepByStepSolution = "(3/9)³ = (1/3)³ = 1 : 27.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_08_5",
                    topic = "Isosceles Triangle",
                    examType = "JAMB",
                    year = 2023,
                    question = "One base angle of an isosceles triangle is 52°. Find the vertex angle.",
                    options = listOf("76°", "64°", "52°", "128°"),
                    correctIndex = 0,
                    hint = "Both base angles equal 52°.",
                    stepByStepSolution = "180 − (52 + 52) = 76°.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_08_6",
                    topic = "Regular Decagon",
                    examType = "WAEC",
                    year = 2024,
                    question = "Find the size of one interior angle of a regular 10-sided polygon.",
                    options = listOf("135°", "140°", "144°", "150°"),
                    correctIndex = 2,
                    hint = "Exterior = 360°/10 = 36°.",
                    stepByStepSolution = "Interior = 180° − 36° = 144°.",
                  ),
                ),
            ),

            Lesson(
              id = "math_09_circle_theorems_loci",
              moduleId = "math_mod_3",
              subjectId = "mathematics",
              title = "Circle Theorems & Loci Constructions",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 3.2 • WAEC Circle Geometry & Loci",
              learningOutcomes =
                listOf(
                  "Apply all 7 Circle Theorems instantaneously on any diagram.",
                  "Identify Cyclic Quadrilaterals (opposite angles sum to 180°) & Alternate Segment Theorem.",
                  "Describe the 4 fundamental Loci (circle around point, bisector of segment, angle bisector, parallel rails).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. The Top 7 Circle Theorem Cheat-Sheet",
                    explanation =
                      "Every circle puzzle uses at least one of these 7 simple rules: (1) Angle at Center = TWICE angle at circumference standing on the same arc! (2) Angle in a Semicircle = 90°! (3) Angles in the same segment = EQUAL (bow-tie rule)! (4) Opposite angles of a 4-corner Cyclic Quad add to 180°! (5) Radius meets Tangent at 90°! (6) Alternate Segment: tangent-chord angle equals the far interior triangle angle! (7) Perpendicular from center cuts a chord cleanly in half.",
                    keyFormula = "\\angle_{\\text{center}} = 2\\angle_{\\text{circ}}   |   \\angle_{\\text{semi}} = 90^\\circ   |   \\text{Cyclic Opp Sum} = 180^\\circ",
                    examTip =
                      "Always check if a chord passes through the CENTER O first—if it does, mark that 90° diameter angle right away!",
                  ),
                  ConceptBlock(
                    heading = "2. The 4 Standard Loci (Path of a Moving Point)",
                    explanation =
                      "A Locus is the trail left by a point obeying a strict rule: Fixed distance r from 1 point = Circle! Equal distance from 2 points = Perpendicular Bisector line! Equal distance from 2 intersecting lines = Angle Bisector! Fixed distance from a straight track = Two Parallel Rails!",
                    keyFormula = "|PA| = |PB| \\implies \\perp\\text{ bisector of } AB",
                    examTip =
                      "Two tangents from the same outside point T to a circle always have EQUAL lengths: TA = TB!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Center Angle + Cyclic Quadrilateral Double Step",
                    examSource = "WAEC SSCE 2024 • Paper 2 Q6",
                    problemStatement =
                      "A, B, C, D lie on a circle with center O. Angle AOC = 130° (minor arc). Find angle ADC on major arc and angle ABC on minor arc.",
                    steps =
                      listOf(
                        "Step 1 — Major circumference angle ∠ADC = (1/2) × 130° = 65°.",
                        "Step 2 — ABCD is a cyclic quadrilateral, so ∠ADC + ∠ABC = 180°.",
                        "Step 3 — ∠ABC = 180° − 65° = 115°.",
                      ),
                    finalAnswer = "∠ADC = 65°  |  ∠ABC = 115°",
                    examinerTrap = "Ensure the circumference point is on the OPPOSITE side of the arc from O before halving!",
                  ),
                  WorkedExample(
                    title = "Chord Distance from Circle Center",
                    examSource = "JAMB UTME 2023 • Maths Q22",
                    problemStatement = "A chord of length 24 cm is 5 cm from the center of a circle. Find the radius.",
                    steps =
                      listOf(
                        "Step 1 — Perpendicular from center bisects the chord: half-chord = 24/2 = 12 cm.",
                        "Step 2 — Pythagoras: r² = 12² + 5² = 144 + 25 = 169.",
                        "Step 3 — r = 13 cm.",
                      ),
                    finalAnswer = "13 cm",
                    examinerTrap = "Forgetting to cut the chord length in half (using 24 instead of 12)!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Cell Tower Coverage & Telescope Mirrors",
                    industryDomain = "Telecommunications",
                    narrative =
                      "Phone location triangulation intersects circular distance loci from three cell towers to pinpoint a device within metres.",
                    examConnection = "Intersection of two circular loci yields exact meeting coordinates.",
                  ),
                ),
              labType = PracticalLabType.POLYGON_CIRCLE_THEOREM_LAB,
              labBrief =
                "Drag the point around the circle circumference to prove Center Angle = 2×Circumference, test the 90° Semicircle theorem, and verify cyclic sums!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_09_1",
                    topic = "Angle at Center",
                    examType = "JAMB",
                    year = 2024,
                    question = "Points P, Q, R lie on a circle center O. If ∠PQR = 110°, find reflex ∠POR.",
                    options = listOf("220°", "140°", "55°", "110°"),
                    correctIndex = 0,
                    hint = "Angle subtended at center on the same side as Q is 2 × ∠PQR.",
                    stepByStepSolution = "Reflex ∠POR = 2 × 110° = 220° (obtuse ∠POR = 140°).",
                  ),
                  PracticeQuestion(
                    id = "ex_math_09_2",
                    topic = "Angle in Semicircle",
                    examType = "WAEC",
                    year = 2023,
                    question = "AB is a diameter of a circle and C is on the circle. If ∠CAB = 38°, find ∠CBA.",
                    options = listOf("38°", "52°", "62°", "90°"),
                    correctIndex = 1,
                    hint = "∠ACB = 90° (angle in semicircle).",
                    stepByStepSolution = "180° − 90° − 38° = 52°.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_09_3",
                    topic = "Tangent-Radius",
                    examType = "JAMB",
                    year = 2025,
                    question = "A tangent from point T meets a circle of radius 6 cm at P. If OT = 10 cm, find length PT.",
                    options = listOf("8 cm", "4 cm", "11.7 cm", "16 cm"),
                    correctIndex = 0,
                    hint = "∠OPT = 90°, so triangle OPT is right-angled.",
                    stepByStepSolution = "PT = √(10² − 6²) = √(100 − 36) = √64 = 8 cm.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_09_4",
                    topic = "Alternate Segment Theorem",
                    examType = "WAEC",
                    year = 2022,
                    question = "XY is tangent at A to circle ABC. If ∠YAB = 64°, find ∠ACB in the alternate segment.",
                    options = listOf("26°", "64°", "116°", "128°"),
                    correctIndex = 1,
                    hint = "Tangent-chord angle equals angle subtended by that chord in the opposite segment.",
                    stepByStepSolution = "By Alternate Segment Theorem, ∠ACB = ∠YAB = 64°.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_09_5",
                    topic = "Cyclic Quadrilateral",
                    examType = "JAMB",
                    year = 2021,
                    question = "PQRS is a cyclic quad with ∠P = 75°. What is the exterior angle at R?",
                    options = listOf("105°", "75°", "150°", "90°"),
                    correctIndex = 1,
                    hint = "Exterior angle of cyclic quad = opposite interior angle!",
                    stepByStepSolution = "∠R = 180 − 75 = 105°; exterior at R = 180 − 105 = 75°.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_09_6",
                    topic = "Loci",
                    examType = "WAEC",
                    year = 2024,
                    question = "The locus of points equidistant from two fixed points A and B is:",
                    options = listOf("Circle with diameter AB", "Perpendicular bisector of AB", "Angle bisector", "Line parallel to AB"),
                    correctIndex = 1,
                    hint = "Think symmetry down the middle of segment AB.",
                    stepByStepSolution = "The perpendicular bisector of AB keeps every point equidistant from A and B.",
                  ),
                ),
            ),

            Lesson(
              id = "math_10_mensuration_3d",
              moduleId = "math_mod_3",
              subjectId = "mathematics",
              title = "Mensuration: Arcs, Sectors, Cones, Spheres & Frustums",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 3.3 • WAEC 2D/3D Mensuration",
              learningOutcomes =
                listOf(
                  "Calculate arc length, sector area, and chord segment area cleanly.",
                  "Find curved surface area and volume of Cylinders, Cones (l = √(r² + h²)), Pyramids, and Spheres.",
                  "Fold a 2D sector of radius R into a 3D cone (sector arc = cone base 2πr).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Pizza Slices (Sectors) & Cone Magic",
                    explanation =
                      "A sector of angle θ° is simply the fraction θ/360 of a full pizza! So its arc length is (θ/360)×2πr and its slice area is (θ/360)×πr². Take a paper sector and tape the two straight edges together—it folds into a 3D ICE-CREAM CONE! The sector radius R becomes the cone's slant height l, and the curved pizza crust becomes the cone base 2πr.",
                    keyFormula = "L_{\\text{arc}} = \\frac{\\theta}{360}\\cdot 2\\pi r   |   V_{\\text{cone}} = \\frac{1}{3}\\pi r^2 h,\\quad \\text{CSA}_{\\text{cone}} = \\pi r l",
                    examTip =
                      "When a sector of angle θ folds into a cone, base radius r = (θ / 360) × R_sector! Huge JAMB shortcut!",
                  ),
                  ConceptBlock(
                    heading = "2. Spheres, Hemispheres & Frustums",
                    explanation =
                      "A sphere has Surface Area 4πr² and Volume (4/3)πr³. Cut it in half to get a solid Hemisphere: its curved bowl is 2πr² PLUS the flat circular top πr² = 3πr² total! Cut the tip off a cone/pyramid to get a bucket-shaped Frustum: subtract small top volume from big full volume!",
                    keyFormula = "A_{\\text{sphere}} = 4\\pi r^2,\\quad V_{\\text{sphere}} = \\frac{4}{3}\\pi r^3   |   V_{\\text{frustum}} = \\frac{1}{3}\\pi H(R^2 + Rr + r^2)",
                    examTip =
                      "For a closed cone or cylinder, ALWAYS ask: does Total Surface Area include the circular lid/base? Usually YES (+ πr² or + 2πr²)!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Folding a Sector into a Cone",
                    examSource = "WAEC SSCE 2024 • Paper 2 Q8",
                    problemStatement =
                      "A sector of radius 10 cm and angle 216° is folded into a cone. Find the base radius r and height h.",
                    steps =
                      listOf(
                        "Step 1 — r = (216 / 360) × 10 = 0.6 × 10 = 6 cm.",
                        "Step 2 — Slant height l = sector radius = 10 cm.",
                        "Step 3 — Height h = √(l² − r²) = √(100 − 36) = 8 cm.",
                      ),
                    finalAnswer = "r = 6 cm, h = 8 cm",
                    examinerTrap = "Thinking sector radius R becomes the cone's vertical height h instead of slant edge l!",
                  ),
                  WorkedExample(
                    title = "Melting a Cylinder into Spheres",
                    examSource = "JAMB UTME 2023 • Maths Q25",
                    problemStatement =
                      "How many solid spheres of radius 3 cm can be recast from a solid cylinder of radius 6 cm and height 12 cm?",
                    steps =
                      listOf(
                        "Step 1 — V_cyl = π(6²)(12) = 432π cm³.",
                        "Step 2 — V_sphere = (4/3)π(3³) = 36π cm³.",
                        "Step 3 — Number = 432π / 36π = 12 spheres.",
                      ),
                    finalAnswer = "12 spheres",
                    examinerTrap = "Cancel π on top and bottom before multiplying by 22/7—saves minutes!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Grain Silos & Water Reservoir Design",
                    industryDomain = "Civil Engineering & Packaging",
                    narrative =
                      "Engineers design silo roofs (cones) and storage tanks (cylinders) to maximize capacity while minimizing expensive metal sheet area.",
                    examConnection = "Composite solid mensuration sums cylinder + cone or hemisphere volumes.",
                  ),
                ),
              labType = PracticalLabType.MENSURATION_3D_LAB,
              labBrief =
                "Switch between Cone, Cylinder, Sphere, and Sector and slide radius r, height h, or angle θ to compute slant edge, surface area & volume.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_10_1",
                    topic = "Sector Area",
                    examType = "JAMB",
                    year = 2024,
                    question = "Find the area of a sector of radius 7 cm and angle 90° (π = 22/7).",
                    options = listOf("38.5 cm²", "77 cm²", "154 cm²", "19.25 cm²"),
                    correctIndex = 0,
                    hint = "(90/360) × (22/7) × 7².",
                    stepByStepSolution = "(1/4) × 154 = 38.5 cm².",
                  ),
                  PracticeQuestion(
                    id = "ex_math_10_2",
                    topic = "Cone Curved Surface",
                    examType = "WAEC",
                    year = 2023,
                    question = "A cone has base radius 5 cm and vertical height 12 cm. Find its curved surface area in terms of π.",
                    options = listOf("60π cm²", "65π cm²", "90π cm²", "130π cm²"),
                    correctIndex = 1,
                    hint = "First find slant height l = √(5² + 12²) = 13 cm.",
                    stepByStepSolution = "CSA = πrl = π(5)(13) = 65π cm².",
                  ),
                  PracticeQuestion(
                    id = "ex_math_10_3",
                    topic = "Sphere Volume",
                    examType = "JAMB",
                    year = 2025,
                    question = "If the surface area of a sphere is 100π cm², find its volume.",
                    options = listOf("(500/3)π cm³", "100π cm³", "250π cm³", "(400/3)π cm³"),
                    correctIndex = 0,
                    hint = "4πr² = 100π ⇒ r = 5 cm.",
                    stepByStepSolution = "V = (4/3)π(5³) = 500π / 3 cm³.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_10_4",
                    topic = "Solid Hemisphere TSA",
                    examType = "WAEC",
                    year = 2022,
                    question = "What is the TOTAL surface area of a solid hemisphere of radius 6 cm?",
                    options = listOf("72π cm²", "108π cm²", "144π cm²", "36π cm²"),
                    correctIndex = 1,
                    hint = "Curved (2πr²) + flat base (πr²) = 3πr².",
                    stepByStepSolution = "3π(6²) = 108π cm².",
                  ),
                  PracticeQuestion(
                    id = "ex_math_10_5",
                    topic = "Cylinder Volume",
                    examType = "JAMB",
                    year = 2021,
                    question = "A cylinder has volume 300π cm³ and height 12 cm. Find its base diameter.",
                    options = listOf("5 cm", "10 cm", "25 cm", "15 cm"),
                    correctIndex = 1,
                    hint = "πr²(12) = 300π ⇒ r = 5 cm; diameter = 2r!",
                    stepByStepSolution = "r² = 25 ⇒ r = 5 ⇒ diameter = 10 cm.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_10_6",
                    topic = "Latitude Circle Distance",
                    examType = "WAEC",
                    year = 2024,
                    question = "On Earth (radius R), what is the radius r of the parallel of latitude 60°N?",
                    options = listOf("R", "0.5 R", "0.866 R", "0.25 R"),
                    correctIndex = 1,
                    hint = "r = R cos(latitude).",
                    stepByStepSolution = "r = R cos 60° = 0.5 R.",
                  ),
                ),
            ),
          ),
      ),

      CourseModule(
        id = "math_mod_4",
        subjectId = "mathematics",
        indexLabel = "Module 04",
        title = "Trigonometry, Bearings & Coordinate Geometry",
        summary = "Master SOHCAHTOA, CAST quadrants, Sine & Cosine Rules, 3-figure bearings, elevation angles, line equations & perpendicular bisectors.",
        lessons =
          listOf(
            Lesson(
              id = "math_11_trig_bearings",
              moduleId = "math_mod_4",
              subjectId = "mathematics",
              title = "Trigonometry, Sine/Cosine Rules & 3-Figure Bearings",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 3.5 • WAEC Trig & Bearings",
              learningOutcomes =
                listOf(
                  "Use special triangles (30°, 45°, 60°) and the CAST rule to find trig values in all 4 quadrants.",
                  "Choose cleanly between SOHCAHTOA (90°), Sine Rule (matching pair), and Cosine Rule (SAS or SSS).",
                  "Read 3-figure bearings clockwise from True North and reverse reverse-bearings (±180°).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. SOHCAHTOA, Special Angles & The CAST Compass",
                    explanation =
                      "In right triangles: Sin = Opp/Hyp, Cos = Adj/Hyp, Tan = Opp/Adj. Memorize exact special triangles: sin 30° = cos 60° = 1/2, sin 45° = cos 45° = √2/2, sin 60° = cos 30° = √3/2! For angles above 90°, follow CAST starting Quadrant 1: All (+), Sine (+ in Q2), Tan (+ in Q3), Cos (+ in Q4).",
                    keyFormula = "\\sin^2\\theta + \\cos^2\\theta = 1   |   \\sin(30^\\circ)=\\frac{1}{2},\\; \\cos(60^\\circ)=\\frac{1}{2},\\; \\tan(45^\\circ)=1",
                    examTip =
                      "Angle of ELEVATION (looking up from horizontal) equals Angle of DEPRESSION (looking down from top) by Z-alternate angles!",
                  ),
                  ConceptBlock(
                    heading = "2. Non-Right Triangles & Bearings Clockwise from North",
                    explanation =
                      "No 90° corner? If you know an angle and the side straight ACROSS from it, use the Sine Rule! If you have Side–Angle–Side (a corner trapped between two known sides) or all 3 sides, use Cosine Rule! For bearings: ALWAYS draw a little 'N↑' cross at the 'FROM' station and measure CLOCKWISE with 3 digits (e.g., 045°).",
                    keyFormula = "\\frac{a}{\\sin A} = \\frac{b}{\\sin B}   |   c^2 = a^2 + b^2 - 2ab\\cos C   |   \\text{Area} = \\frac{1}{2}ab\\sin C",
                    examTip =
                      "Back-bearing shortcut: if Bearing A→B is < 180°, ADD 180°; if > 180°, SUBTRACT 180°!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Finding Back Bearing & Ship Distance",
                    examSource = "WAEC SSCE 2024 • Paper 2 Q9",
                    problemStatement =
                      "A boat sails 6 km on bearing 040° from P to Q, then 8 km on bearing 130° to R. Find distance PR and bearing of P from Q.",
                    steps =
                      listOf(
                        "Step 1 — At Q, South line is 180°. Bearing of P from Q = 040° + 180° = 220°.",
                        "Step 2 — Interior ∠PQR = 220° − 130° = 90° (right angle!).",
                        "Step 3 — By Pythagoras, PR = √(6² + 8²) = 10 km.",
                      ),
                    finalAnswer = "PR = 10 km  |  Back bearing = 220°",
                    examinerTrap = "Always draw North arrows at EVERY turning point before subtracting angles!",
                  ),
                  WorkedExample(
                    title = "Cosine Rule for Enclosed Angle",
                    examSource = "JAMB UTME 2023 • Maths Q28",
                    problemStatement = "In ΔXYZ, x = 5, y = 8, and ∠Z = 60°. Find side z.",
                    steps =
                      listOf(
                        "Step 1 — z² = x² + y² − 2xy cos Z.",
                        "Step 2 — z² = 25 + 64 − 2(5)(8)(0.5) = 89 − 40 = 49.",
                        "Step 3 — z = 7.",
                      ),
                    finalAnswer = "z = 7",
                    examinerTrap = "Do NOT confuse −2ab cos C with +2ab cos C!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Aviation Flight Paths & Land Surveying",
                    industryDomain = "Navigation & Surveying",
                    narrative =
                      "Pilots fly 3-figure magnetic headings clockwise from North while land surveyors measure building heights remotely using clinometer elevation angles.",
                    examConnection = "Uses Sine/Cosine Rule whenever wind pushes a plane off straight-line track.",
                  ),
                ),
              labType = PracticalLabType.TRIG_BEARING_LAB,
              labBrief =
                "Spin the 3-Figure Bearing Compass (000°–359°) clockwise from True North to read sin θ, cos θ, tan θ, CAST quadrant signs & back-bearings.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_11_1",
                    topic = "Exact Trig Identity",
                    examType = "JAMB",
                    year = 2024,
                    question = "If sin θ = 5/13 and θ is obtuse (90° < θ < 180°), find cos θ.",
                    options = listOf("−12/13", "12/13", "−5/12", "8/13"),
                    correctIndex = 0,
                    hint = "Obtuse means Quadrant 2 (Sine positive, Cosine NEGATIVE).",
                    stepByStepSolution = "Adjacent = 12 from 5-12-13 triangle; Q2 makes cos θ = −12/13.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_11_2",
                    topic = "Back Bearing",
                    examType = "WAEC",
                    year = 2023,
                    question = "The bearing of town B from A is 245°. What is the bearing of A from B?",
                    options = listOf("065°", "115°", "045°", "295°"),
                    correctIndex = 0,
                    hint = "245° > 180°, so subtract 180°.",
                    stepByStepSolution = "245° − 180° = 065°.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_11_3",
                    topic = "Angle of Elevation",
                    examType = "JAMB",
                    year = 2025,
                    question = "A ladder leans at 60° to the ground against a wall, reaching height 6√3 m. Find the ladder's length.",
                    options = listOf("12 m", "6 m", "12√3 m", "8 m"),
                    correctIndex = 0,
                    hint = "sin 60° = Opposite / Hypotenuse = (6√3) / L.",
                    stepByStepSolution = "√3/2 = 6√3 / L ⇒ L = 12 m.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_11_4",
                    topic = "Area of Triangle",
                    examType = "WAEC",
                    year = 2022,
                    question = "Find the area of ΔABC where b = 8 cm, c = 10 cm, and ∠A = 30°.",
                    options = listOf("20 cm²", "40 cm²", "20√3 cm²", "10 cm²"),
                    correctIndex = 0,
                    hint = "Area = (1/2) b c sin A.",
                    stepByStepSolution = "(1/2)(8)(10)(0.5) = 20 cm².",
                  ),
                  PracticeQuestion(
                    id = "ex_math_11_5",
                    topic = "Complementary Trig",
                    examType = "JAMB",
                    year = 2021,
                    question = "If sin(2x + 10°) = cos(x + 20°) for acute angles, find x.",
                    options = listOf("20°", "30°", "15°", "25°"),
                    correctIndex = 0,
                    hint = "sin α = cos β ⇒ α + β = 90°.",
                    stepByStepSolution = "2x + 10 + x + 20 = 90 ⇒ 3x = 60 ⇒ x = 20°.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_11_6",
                    topic = "Sine Rule",
                    examType = "JAMB",
                    year = 2024,
                    question = "In ΔPQR, p = 10 cm, ∠P = 30°, ∠Q = 45°. Find q.",
                    options = listOf("10√2 cm", "5√2 cm", "10 cm", "20 cm"),
                    correctIndex = 0,
                    hint = "q / sin 45° = 10 / sin 30°.",
                    stepByStepSolution = "q = 10 × (√2/2) / (1/2) = 10√2 cm.",
                  ),
                ),
            ),

            Lesson(
              id = "math_12_coordinate_geometry",
              moduleId = "math_mod_4",
              subjectId = "mathematics",
              title = "Coordinate Geometry: Midpoints, Gradients & Circles",
              durationMinutes = 19,
              syllabusTag = "JAMB Sec. 3.4 • WAEC Coordinate Geometry",
              learningOutcomes =
                listOf(
                  "Calculate distance, midpoint, and gradient m = (y₂ − y₁)/(x₂ − x₁) between two points.",
                  "Identify parallel (m₁ = m₂) and perpendicular (m₁m₂ = −1) straight lines.",
                  "Write equations of straight lines y − y₁ = m(x − x₁) and circles (x − a)² + (y − b)² = r².",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Distance, Midpoint & Slope (Rise over Run)",
                    explanation =
                      "Plot two points on graph paper: Midpoint simply averages their x's and y's! Distance makes a hidden right triangle and runs Pythagoras: √[(Δx)² + (Δy)²]! Slope (gradient m) measures steepness: Rise ÷ Run. Parallel lines keep identical slope (m₁ = m₂); Perpendicular lines turn 90°, flipping the fraction upside-down AND switching its sign (m₂ = −1/m₁)!",
                    keyFormula = "d = \\sqrt{(x_2-x_1)^2 + (y_2-y_1)^2},\\quad m = \\frac{y_2-y_1}{x_2-x_1}   |   m_1 m_2 = -1 \\text{ (Perp)}",
                    examTip =
                      "Before reading gradient from ax + by + c = 0, rewrite it into y = mx + c—do NOT blindly pick a!",
                  ),
                  ConceptBlock(
                    heading = "2. Equation of a Circle from Center & Radius",
                    explanation =
                      "A circle is just all points (x, y) staying exact distance r from center (a, b): (x − a)² + (y − b)² = r². Given expanded form x² + y² + 2gx + 2fy + c = 0, divide the x and y numbers by −2 to get the center (−g, −f) instantly!",
                    keyFormula = "(x - a)^2 + (y - b)^2 = r^2   |   x^2+y^2+2gx+2fy+c=0 \\implies r = \\sqrt{g^2+f^2-c}",
                    examTip =
                      "To find a perpendicular bisector of AB, find MIDPOINT of AB and use m_perp = −1/m_AB!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Line Perpendicular to Given Line",
                    examSource = "JAMB UTME 2024 • Maths Q30",
                    problemStatement = "Find the equation of the line passing through (2, −1) perpendicular to 2x + 3y = 6.",
                    steps =
                      listOf(
                        "Step 1 — Rewrite given line: 3y = −2x + 6 ⇒ y = (−2/3)x + 2, so m₁ = −2/3.",
                        "Step 2 — Perpendicular slope m₂ = −1/(−2/3) = +3/2.",
                        "Step 3 — y − (−1) = (3/2)(x − 2) ⇒ 2y + 2 = 3x − 6 ⇒ 3x − 2y − 8 = 0.",
                      ),
                    finalAnswer = "3x − 2y − 8 = 0",
                    examinerTrap = "Forgetting to flip the sign when computing m₂ = −1/m₁!",
                  ),
                  WorkedExample(
                    title = "Center and Radius of Expanded Circle",
                    examSource = "WAEC Further / JAMB 2023 • Q31",
                    problemStatement = "Find the center and radius of circle x² + y² − 6x + 8y − 11 = 0.",
                    steps =
                      listOf(
                        "Step 1 — Center = (−(−6)/2, −(8)/2) = (3, −4).",
                        "Step 2 — r = √(3² + (−4)² − (−11)) = √(9 + 16 + 11) = √36 = 6.",
                      ),
                    finalAnswer = "Center (3, −4), Radius = 6",
                    examinerTrap = "Watch out for subtracting (−11) becoming +11 inside the square root!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "GPS Geofencing & Drone Flight Corridors",
                    industryDomain = "Geolocation & Robotics",
                    narrative =
                      "Delivery apps check (x − a)² + (y − b)² ≤ r² every few seconds to know when a rider enters the 500 m delivery circle.",
                    examConnection = "Points inside vs outside a circle depend on comparing distance² vs r².",
                  ),
                ),
              labType = PracticalLabType.COORDINATE_CALCULUS_LAB,
              labBrief =
                "Slide point x₀ along linear/polynomial curves to measure exact secant vs tangent gradient m = dy/dx and intercepts.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_math_12_1",
                    topic = "Distance Between Points",
                    examType = "JAMB",
                    year = 2024,
                    question = "Find the distance between A(−1, 2) and B(5, 10).",
                    options = listOf("8", "10", "14", "√52"),
                    correctIndex = 1,
                    hint = "Δx = 6, Δy = 8.",
                    stepByStepSolution = "√(6² + 8²) = √(36 + 64) = √100 = 10.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_12_2",
                    topic = "Midpoint",
                    examType = "WAEC",
                    year = 2023,
                    question = "If M(3, −2) is the midpoint of P(1, 4) and Q(x, y), find Q.",
                    options = listOf("(5, −8)", "(2, 1)", "(5, 0)", "(4, −6)"),
                    correctIndex = 0,
                    hint = "(1 + x)/2 = 3 and (4 + y)/2 = −2.",
                    stepByStepSolution = "x = 6 − 1 = 5; y = −4 − 4 = −8 ⇒ (5, −8).",
                  ),
                  PracticeQuestion(
                    id = "ex_math_12_3",
                    topic = "Parallel Lines",
                    examType = "JAMB",
                    year = 2025,
                    question = "If lines kx − 3y + 1 = 0 and 4x − 2y + 5 = 0 are parallel, find k.",
                    options = listOf("6", "2", "3/2", "12"),
                    correctIndex = 0,
                    hint = "Equate gradients: k/3 = 4/2.",
                    stepByStepSolution = "y = (k/3)x... vs y = 2x + 5/2 ⇒ k/3 = 2 ⇒ k = 6.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_12_4",
                    topic = "Perpendicular Slope",
                    examType = "WAEC",
                    year = 2022,
                    question = "Find the gradient of a line perpendicular to 3y − 6x = 9.",
                    options = listOf("2", "−1/2", "1/2", "−2"),
                    correctIndex = 1,
                    hint = "m₁ = 6/3 = 2.",
                    stepByStepSolution = "m₂ = −1 / 2 = −1/2.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_12_5",
                    topic = "Circle Equation",
                    examType = "JAMB",
                    year = 2023,
                    question = "Write the equation of a circle centered at (2, −3) with radius 4.",
                    options = listOf("(x−2)² + (y+3)² = 16", "(x+2)² + (y−3)² = 16", "(x−2)² + (y+3)² = 4", "(x−2)² − (y+3)² = 16"),
                    correctIndex = 0,
                    hint = "Plug a = 2, b = −3, r² = 16 into (x − a)² + (y − b)² = r².",
                    stepByStepSolution = "(x − 2)² + (y + 3)² = 16.",
                  ),
                  PracticeQuestion(
                    id = "ex_math_12_6",
                    topic = "Collinear Points",
                    examType = "JAMB",
                    year = 2021,
                    question = "If points (1, 2), (3, 6), and (5, k) lie on a straight line, find k.",
                    options = listOf("8", "9", "10", "12"),
                    correctIndex = 2,
                    hint = "Equate gradients: (6 − 2)/(3 − 1) = (k − 6)/(5 − 3).",
                    stepByStepSolution = "2 = (k − 6)/2 ⇒ k − 6 = 4 ⇒ k = 10.",
                  ),
                ),
            ),
          ),
      ),
    )
}
