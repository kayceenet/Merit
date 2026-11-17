package com.example.data.curriculum

enum class ExamBoard(val label: String) {
  JAMB("JAMB UTME"),
  WAEC("WAEC SSCE"),
  BOTH("JAMB + WAEC"),
}

enum class PracticalLabType(val title: String, val subtitle: String) {
  // Physics
  MEASUREMENT_VECTOR_LAB(
    title = "Measurements & Resultant Vector Lab",
    subtitle = "Read vernier/micrometer scales and combine two forces at any angle θ live",
  ),
  PROJECTILE_KINEMATICS(
    title = "Kinematics & Projectile Motion Lab",
    subtitle = "Adjust launch speed, angle θ & gravity g to watch trajectory, range & flight time",
  ),
  TORQUE_MACHINES_LAB(
    title = "Moments, Equilibrium & Simple Machines Bench",
    subtitle = "Balance clockwise vs anticlockwise torque and calculate MA, VR & machine efficiency",
  ),
  SHM_FLUIDS_LAB(
    title = "SHM Pendulum & Archimedes Fluid Lab",
    subtitle = "Vary pendulum length, fluid density & submerged depth to measure period T and upthrust",
  ),
  THERMAL_EXPANSION_CALORIMETRY(
    title = "Thermal Expansion, Gas & Calorimetry Bench",
    subtitle = "Heat solids and water across phase plateaus using Q = mcΔT + mL and linear expansivity",
  ),
  WAVE_OPTICS_BENCH(
    title = "Waves, Sound Harmonics & Optics Ray Bench",
    subtitle = "Explore Snell's refraction, converging lens images, and open/closed pipe harmonics",
  ),
  ELECTROSTATIC_CAPACITOR_LAB(
    title = "Electrostatics & Capacitor Network Bench",
    subtitle = "Adjust plate area, separation & series/parallel capacitors to observe C, Q, and energy U",
  ),
  OHMS_CIRCUIT(
    title = "DC Circuit, Internal Resistance & Power Bench",
    subtitle = "Toggle Series/Parallel resistors and cell internal resistance r to measure V, I & P",
  ),
  EM_TRANSFORMER_AC_LAB(
    title = "Electromagnetism, Transformers & RLC A.C. Bench",
    subtitle = "Tune primary/secondary coil turns and R-L-C impedance to find resonance frequency f₀",
  ),
  QUANTUM_NUCLEAR_LAB(
    title = "Photoelectric Quantum & Half-Life Decay Lab",
    subtitle = "Vary photon frequency above threshold f₀ and watch N = N₀(1/2)ⁿ radioactive decay live",
  ),

  // Mathematics
  NUMBER_BASE_INDICES_LAB(
    title = "Number Bases, Modular Clock & Surds Studio",
    subtitle = "Convert bases 2–16, inspect mod-n remainders, and evaluate laws of indices & logarithms",
  ),
  SETS_VENN_LAB(
    title = "Interactive 2-Set & 3-Set Venn Diagram Studio",
    subtitle = "Adjust n(U), n(A), n(B), and overlap n(A∩B) to see regions shade and compute complements",
  ),
  ALGEBRA_VARIATION_LAB(
    title = "Linear Inequalities & Direct/Inverse Variation Lab",
    subtitle = "Explore direct/inverse/joint variation constants k and shade inequality solution intervals",
  ),
  QUADRATIC_TANGENT(
    title = "Quadratic Roots, Vertex & Polynomial Lab",
    subtitle = "Tune a, b, c to visualize parabola turning points, discriminant Δ, and factor theorem roots",
  ),
  SEQUENCE_MATRIX_LAB(
    title = "AP/GP Progressions & 2×2 Matrix Determinant Bench",
    subtitle = "Step through n-th terms/sums Sₙ and compute 2×2 determinants & matrix inverses live",
  ),
  POLYGON_CIRCLE_THEOREM_LAB(
    title = "Euclidean Polygons & Circle Theorems Workbench",
    subtitle = "Move chord/circumference angles live to verify Center = 2×Circumference & cyclic sums",
  ),
  MENSURATION_3D_LAB(
    title = "3D Solids & Circle Sector Mensuration Lab",
    subtitle = "Vary radius r and height h to compute cone slant edge, cylinder area, sphere & sector volume",
  ),
  TRIG_BEARING_LAB(
    title = "Unit Circle Trigonometry & 3-Figure Bearing Compass",
    subtitle = "Rotate bearing θ clockwise from North and solve SOHCAHTOA & Sine/Cosine Rule triangles",
  ),
  COORDINATE_CALCULUS_LAB(
    title = "Coordinate Geometry, Derivatives & Integral Area Lab",
    subtitle = "Slide x₀ along curves to watch tangent slope dy/dx and shaded definite integral area update",
  ),
  PROBABILITY_STATISTICS_LAB(
    title = "Combinatorics, Probability & Grouped Statistics Lab",
    subtitle = "Compute nPr, nCr, combined event odds, and inspect bar distribution, mean, median & σ",
  ),

  // Chemistry
  SEPARATION_ATOMIC_LAB(
    title = "Separation Techniques & Bohr Atom Orbital Builder",
    subtitle = "Pick atomic number Z (1–20) to see 1s² 2s² shells build and match pure separation methods",
  ),
  BONDING_PERIODIC_LAB(
    title = "Periodic Trends & Chemical Bonding Simulator",
    subtitle = "Compare element electronegativity ΔEN to predict ionic vs covalent bonds & hydrogen bonding",
  ),
  STOICHIOMETRY_MOLE_LAB(
    title = "Mole Concept, Limiting Reagent & Empirical Formula Lab",
    subtitle = "Vary reactant grams & molar mass Mᵣ to find moles, limiting reactant, and theoretical yield",
  ),
  GAS_LAW_PISTON(
    title = "Ideal Gas Laws & Graham Effusion Piston",
    subtitle = "Adjust Kelvin temperature, pressure & molar mass to observe volume PV = nRT and diffusion rate",
  ),
  TITRATION_PH(
    title = "Acid-Base Titration, pH Curve & Hydrolysis Lab",
    subtitle = "Add NaOH titrant drop-by-drop to HCl or CH₃COOH and watch indicator endpoint color switch",
  ),
  ENERGETICS_KINETICS_LAB(
    title = "Reaction Enthalpy Profile & Le Chatelier Lab",
    subtitle = "Toggle Exothermic/Endothermic ΔH, add a catalyst to lower Eₐ, and shift equilibrium",
  ),
  REDOX_ELECTROLYSIS_LAB(
    title = "Redox Oxidation-State & Faraday Electrolysis Bench",
    subtitle = "Adjust current I (A) and time t to plate Cu/Ag at the cathode using m = MIt / (nF)",
  ),
  INORGANIC_GASES_METALS_LAB(
    title = "Gas Identification & Metal Reactivity Displacement Bench",
    subtitle = "Test NH₃, SO₂, Cl₂, CO₂ indicators and inspect Blast Furnace iron/aluminum extraction steps",
  ),
  ORGANIC_HYDROCARBON_LAB(
    title = "Organic Homologous Series, Esters & Polymers Bench",
    subtitle = "Switch Alkanes, Alkenes, Alkynes, Alkanols & Esters to inspect formulas, C=C tests & soaps",
  ),
  QUALITATIVE_ANALYSIS_LAB(
    title = "WAEC Qualitative Cation & Anion Bench",
    subtitle = "Add NaOH(aq) or NH₃(aq) in drops and excess to identify Cu²⁺, Fe²⁺, Fe³⁺, Zn²⁺, Pb²⁺ & anions",
  ),

  // Biology
  CELL_OSMOSIS_LAB(
    title = "Microscopy, Cell Ultrastructure & Osmosis Chamber",
    subtitle = "Compare hypotonic, isotonic & hypertonic baths to watch plasmolysis vs turgidity live",
  ),
  BIOENERGETICS_ENZYME_LAB(
    title = "Photosynthesis Rate & Enzyme Temp/pH Curve Lab",
    subtitle = "Adjust temperature, pH & light intensity to observe optimum peaks and enzyme denaturation",
  ),
  NUTRITION_TRANSPORT_LAB(
    title = "Food Tests, Mammalian Heart & Double Circulation Lab",
    subtitle = "Inspect Reagent color tests (Biuret/Benedict/Iodine) and trace RA→RV→Lungs→LA→LV flow",
  ),
  RESPIRATION_KIDNEY_LAB(
    title = "Lung Breathing Mechanics & Kidney Nephron Lab",
    subtitle = "Toggle inhalation/exhalation diaphragm movement and trace glomerular filtration & ADH action",
  ),
  NERVOUS_ENDOCRINE_LAB(
    title = "Eye Accommodation, Reflex Arc & Hormone Loop Lab",
    subtitle = "Adjust object distance for Myopia/Hypermetropia lens correction and Insulin blood feedback",
  ),
  REPRODUCTION_FLOWER_LAB(
    title = "Pollination Structure & Menstrual Hormone Cycle Lab",
    subtitle = "Slide Day 1–28 to observe FSH, LH, Estrogen & Progesterone peaks and floral cross-sections",
  ),
  MENDELIAN_GENETICS(
    title = "Mendelian Punnett Square & Sickle-Cell Cross Lab",
    subtitle = "Select Parent 1 & Parent 2 genotypes (AA/AS/SS or X-linked) to calculate exact F₁ ratios",
  ),
  EVOLUTION_VARIATION_LAB(
    title = "Variation Curve & Natural Selection Simulator",
    subtitle = "Compare discontinuous blood-group bars with continuous height bell curves under selection",
  ),
  ECOLOGY_ENERGY_PYRAMID(
    title = "10% Trophic Energy Pyramid & Quadrat Sampling Lab",
    subtitle = "Vary Producer kcal & Lindeman efficiency across food chains and estimate field populations",
  ),
  BIOGEOCHEMICAL_SOIL_LAB(
    title = "Nitrogen Cycle Bacteria, Eutrophication & Soil Lab",
    subtitle = "Trace Nitrosomonas → Nitrobacter → Pseudomonas and compare Sandy vs Loamy vs Clay drainage",
  ),

  // Use of English
  PARTS_CLAUSES_LAB(
    title = "Clause, Phrase & Grammatical Function Inspector",
    subtitle = "Break down underlined clauses into Noun, Adjectival, or Adverbial roles with WAEC phrasing",
  ),
  SYNTAX_LEXIS_STUDIO(
    title = "Concord, Conditionals, Subjunctive & Tag Studio",
    subtitle = "Test Subject-Verb Agreement rules, polarity-flip question tags & conditional timelines",
  ),
  LEXIS_IDIOMS_REGISTER_LAB(
    title = "Synonyms, Antonyms, Idioms & Register Switchboard",
    subtitle = "Contrast Medical, Judicial, Aviation & Banking registers alongside confusable word pairs",
  ),
  ORAL_ENGLISH_STRESS(
    title = "Syllable Stress & Emphatic Contrast Studio",
    subtitle = "Inspect suffix stress shifts (-tion, -ic, -graphy), noun/verb pairs & JAMB emphatic contrast",
  ),
  PHONETICS_CONSONANTS_LAB(
    title = "IPA Vowel Length, Diphthongs & Consonants Studio",
    subtitle = "Compare /iː/ vs /ɪ/, /θ/ vs /ð/, voiced pairs, and silent letters from JAMB Oral English",
  ),
  COMPREHENSION_ESSAY_LAB(
    title = "Comprehension, Summary Paraphrase & Essay Formatter",
    subtitle = "Compare lifted vs own-word WAEC summary answers and verify Formal/Informal letter layouts",
  ),
}

enum class LessonPillar(val key: String, val shortLabel: String, val fullLabel: String) {
  CONCEPT("concept", "1. Easy Guide", "Part 1: Crystal-Clear Foundations & Diagrams"),
  EXAMPLES("examples", "2. Examples", "Part 2: Guided Worked Examples"),
  SCENARIO("scenario", "3. Real-Life", "Part 3: Real-World Exam Connections"),
  LAB("lab", "4. Virtual Lab", "Part 4: Topic Interactive Virtual Lab"),
  EXERCISE("exercise", "5. Mastery Quiz", "Part 5: Fresh Shuffled Quiz (≥ 60% Unlocks Next)"),
}

data class ConceptBlock(
  val heading: String,
  val explanation: String,
  val keyFormula: String? = null,
  val examTip: String,
)

data class WorkedExample(
  val title: String,
  val examSource: String,
  val problemStatement: String,
  val steps: List<String>,
  val finalAnswer: String,
  val examinerTrap: String,
)

data class RealLifeScenario(
  val title: String,
  val industryDomain: String,
  val narrative: String,
  val examConnection: String,
)

data class PracticeQuestion(
  val id: String,
  val topic: String,
  val examType: String,
  val year: Int,
  val question: String,
  val options: List<String>,
  val correctIndex: Int,
  val hint: String,
  val stepByStepSolution: String,
  val waecMarkingScheme: String? = null,
)

data class Lesson(
  val id: String,
  val moduleId: String,
  val subjectId: String,
  val title: String,
  val durationMinutes: Int,
  val syllabusTag: String,
  val learningOutcomes: List<String>,
  val concepts: List<ConceptBlock>,
  val workedExamples: List<WorkedExample>,
  val scenarios: List<RealLifeScenario>,
  val labType: PracticalLabType,
  val labBrief: String,
  val exercises: List<PracticeQuestion>,
)

data class CourseModule(
  val id: String,
  val subjectId: String,
  val indexLabel: String,
  val title: String,
  val summary: String,
  val lessons: List<Lesson>,
)

data class SubjectTrack(
  val id: String,
  val title: String,
  val shortName: String,
  val subtitle: String,
  val modules: List<CourseModule>,
  val pastQuestions: List<PracticeQuestion>,
) {
  val tagline: String
    get() = subtitle

  val allLessons: List<Lesson>
    get() = modules.flatMap { it.lessons }
}
