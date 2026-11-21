package com.example.data.curriculum

enum class ExamBoard(val label: String) {
  JAMB("JAMB UTME-Style"),
  WAEC("WAEC WASSCE-Style"),
  NECO("NECO SSCE-Style"),
  BOTH("All Boards (JAMB • WAEC • NECO)"),
}

enum class PracticalLabType(val title: String, val subtitle: String) {
  // Physics
  MEASUREMENT_VECTOR_LAB(
    title = "Measurements & Resultant Vector Lab",
    subtitle = "Read vernier/micrometer scales with zero error and resolve force parallelograms live",
  ),
  PROJECTILE_KINEMATICS(
    title = "Kinematics, Newton's Laws & Projectile Motion Lab",
    subtitle = "Predict range, flight time, and maximum height before firing across launch angles",
  ),
  TORQUE_MACHINES_LAB(
    title = "Moments, Equilibrium & Simple Machines Bench",
    subtitle = "Investigate clockwise vs anticlockwise moments and measure MA, VR & efficiency",
  ),
  SHM_FLUIDS_LAB(
    title = "SHM Pendulum, Elasticity & Archimedes Fluid Lab",
    subtitle = "Test length vs mass independence on period T and verify displaced-fluid upthrust",
  ),
  THERMAL_EXPANSION_CALORIMETRY(
    title = "Thermal Expansion, Gas Laws & Calorimetry Bench",
    subtitle = "Observe linear expansion ΔL, Kelvin gas laws, and flat latent-heat phase plateaus",
  ),
  WAVE_OPTICS_BENCH(
    title = "Waves, Sound Harmonics & Optics Ray Bench",
    subtitle = "Investigate converging lens images (1/u + 1/v = 1/f) and open vs closed pipe harmonics",
  ),
  ELECTROSTATIC_CAPACITOR_LAB(
    title = "Electrostatics & Capacitor Network Bench",
    subtitle = "Switch series vs parallel capacitors and inspect stored charge Q and energy U",
  ),
  OHMS_CIRCUIT(
    title = "DC Circuit, Internal Resistance & Power Bench",
    subtitle = "Explore series/parallel resistance and terminal voltage loss V = E − Ir across loads",
  ),
  EM_TRANSFORMER_AC_LAB(
    title = "Electromagnetism, Transformers & Digital Logic Bench",
    subtitle = "Test step-up/step-down turns ratios and evaluate AND/OR/NAND/XOR truth tables",
  ),
  QUANTUM_NUCLEAR_LAB(
    title = "Photoelectric Quantum & Half-Life Decay Lab",
    subtitle = "Compare threshold frequency f₀ across metals and trace N = N₀(1/2)ⁿ radioactive decay",
  ),

  // Mathematics
  NUMBER_BASE_INDICES_LAB(
    title = "Number Bases, Modular Clock & Indices Studio",
    subtitle = "Convert bases 2–16 step-by-step, wrap mod-n remainders, and map indices to logarithms",
  ),
  SETS_VENN_LAB(
    title = "Interactive Venn Diagram & Set Operations Studio",
    subtitle = "Vary n(U), n(A), n(B), and n(A∩B) to inspect region shading and complement counts",
  ),
  ALGEBRA_VARIATION_LAB(
    title = "Direct, Inverse & Joint Variation Graph Lab",
    subtitle = "Compare linear y = kx against hyperbolic y = k/x scaling constants interactively",
  ),
  QUADRATIC_TANGENT(
    title = "Quadratic Roots, Vertex & Discriminant Lab",
    subtitle = "Tune parabola coefficients b and c to track Δ = b² − 4ac, vertex (h, k), and roots",
  ),
  SEQUENCE_MATRIX_LAB(
    title = "AP/GP Progressions & 2×2 Matrix Determinant Bench",
    subtitle = "Compute nth terms, series sums Sₙ, and non-singular matrix determinants ad − bc",
  ),
  POLYGON_CIRCLE_THEOREM_LAB(
    title = "Euclidean Polygons & Circle Theorems Workbench",
    subtitle = "Verify interior/exterior polygon sums and Center Angle = 2×Circumference Angle live",
  ),
  MENSURATION_3D_LAB(
    title = "3D Solids, Slant Height & Volume Mensuration Lab",
    subtitle = "Explore cones, cylinders, and spheres across radius r and height h scaling",
  ),
  TRIG_BEARING_LAB(
    title = "Unit Circle Trigonometry & 3-Figure Bearing Compass",
    subtitle = "Trace clockwise bearings from North and convert forward bearings to back-bearings",
  ),
  COORDINATE_CALCULUS_LAB(
    title = "Coordinate Calculus, Tangents & Integral Area Lab",
    subtitle = "Slide x₀ along y = ½x² to inspect instantaneous gradient dy/dx and definite area",
  ),
  PROBABILITY_STATISTICS_LAB(
    title = "Combinatorics, Probability & Dispersion Statistics Lab",
    subtitle = "Contrast nPr vs nCr and observe how constant shifts vs scaling affect Variance & SD",
  ),

  // Chemistry
  SEPARATION_ATOMIC_LAB(
    title = "Separation Methods & Bohr Electron Shell Builder",
    subtitle = "Match mixtures to separation apparatus and build K-L-M-N shells for elements Z = 1–20",
  ),
  BONDING_PERIODIC_LAB(
    title = "Electronegativity & Chemical Bonding Simulator",
    subtitle = "Compare ΔEN differences to classify ionic transfer, polar covalent, and dative bonds",
  ),
  STOICHIOMETRY_MOLE_LAB(
    title = "Mole Concept, Limiting Reagent & STP Yield Lab",
    subtitle = "Adjust H₂ and O₂ moles to identify limiting reactants and compute STP gas volumes",
  ),
  GAS_LAW_PISTON(
    title = "Ideal Gas Laws & Graham Effusion Piston",
    subtitle = "Vary Kelvin T, pressure P, and molar mass M to test effusion speed ratio √(M₁/M₂)",
  ),
  TITRATION_PH(
    title = "Acid-Base Titration & Indicator Endpoint Lab",
    subtitle = "Compare strong HCl vs weak CH₃COOH equivalence pH jumps and indicator colour changes",
  ),
  ENERGETICS_KINETICS_LAB(
    title = "Enthalpy Barrier, Catalyst & Le Chatelier Lab",
    subtitle = "Observe catalysts lowering Eₐ without shifting ΔH and tune Haber P/T yield",
  ),
  REDOX_ELECTROLYSIS_LAB(
    title = "Redox & Faraday Electrolysis Plating Bench",
    subtitle = "Control current I (A), time t, and ion valency (Ag⁺, Cu²⁺, Al³⁺) to measure mass plated",
  ),
  INORGANIC_GASES_METALS_LAB(
    title = "Inorganic Gas Tests & Metal Extraction Furnace",
    subtitle = "Verify NH₃, Cl₂, SO₂, CO₂ confirmatory tests and industrial electrolysis/blast furnace paths",
  ),
  ORGANIC_HYDROCARBON_LAB(
    title = "Homologous Series & Unsaturation Bench",
    subtitle = "Compare Br₂ water, KMnO₄, ammoniacal Cu⁺, and esterification diagnostic reactions",
  ),
  QUALITATIVE_ANALYSIS_LAB(
    title = "WAEC Qualitative Cation & Anion Reagent Bench",
    subtitle = "Add NaOH(aq) and NH₃(aq) in drops and excess to distinguish Cu²⁺, Fe²⁺, Fe³⁺, Zn²⁺, Pb²⁺",
  ),

  // Biology
  CELL_OSMOSIS_LAB(
    title = "Microscopy Calibration & Osmosis Cell Chamber",
    subtitle = "Compute micrometre specimen sizes and compare plasmolysis vs haemolysis across baths",
  ),
  BIOENERGETICS_ENZYME_LAB(
    title = "Enzyme Temperature Activity & Denaturation Curve",
    subtitle = "Test Pepsin, Amylase, and Trypsin across 0–65°C to locate optimum activity vs denaturation",
  ),
  NUTRITION_TRANSPORT_LAB(
    title = "Biochemical Food Tests & Double Circulation Trace",
    subtitle = "Run Iodine/Benedict's/Biuret tests and trace RA → RV → Lungs → LA → LV cardiac flow",
  ),
  RESPIRATION_KIDNEY_LAB(
    title = "Breathing Mechanics & Kidney ADH Nephron Lab",
    subtitle = "Inspect thoracic volume-pressure changes and ADH control of urine concentration",
  ),
  NERVOUS_ENDOCRINE_LAB(
    title = "Eye Lens Defects & Pancreatic Homeostasis Lab",
    subtitle = "Match Myopia/Hypermetropia retinal focus errors to lenses and track Insulin/Glucagon feedback",
  ),
  REPRODUCTION_FLOWER_LAB(
    title = "28-Day Menstrual Hormone & Ovulation Lab",
    subtitle = "Slide Day 1–28 to correlate FSH, LH surge, Estrogen, Progesterone, and uterine lining state",
  ),
  MENDELIAN_GENETICS(
    title = "Mendelian Punnett Cross & Genotype Ratio Lab",
    subtitle = "Combine parent AA/AS/SS genotypes to derive exact F₁ probability percentages",
  ),
  EVOLUTION_VARIATION_LAB(
    title = "Variation & Natural Selection Population Simulator",
    subtitle = "Track moth allele frequency shifts across 5 generations on soot-darkened vs clean bark",
  ),
  ECOLOGY_ENERGY_PYRAMID(
    title = "Lindeman Trophic Energy Pyramid & Ecological Efficiency",
    subtitle = "Vary ecological transfer efficiency (5–20%) across Producer → Primary → Tertiary levels",
  ),
  BIOGEOCHEMICAL_SOIL_LAB(
    title = "Nitrogen Cycle Bacteria & Soil Porosity Lab",
    subtitle = "Trace Rhizobium, Nitrosomonas, Nitrobacter, Pseudomonas & compare Sand/Loam/Clay profiles",
  ),

  // Use of English
  PARTS_CLAUSES_LAB(
    title = "Clause, Word Class & Grammatical Function Inspector",
    subtitle = "Diagnose Noun, Adjectival, and Adverbial clauses and fix dangling modifiers step-by-step",
  ),
  SYNTAX_LEXIS_STUDIO(
    title = "Concord, Conditionals & Question Tag Studio",
    subtitle = "Test proximity vs parenthetical concord, Type 3 inversion, and polarity-flip tags",
  ),
  LEXIS_IDIOMS_REGISTER_LAB(
    title = "Nigerian Register, Preposition & Idiom Switchboard",
    subtitle = "Inspect Judicial, Medical, Banking & Aviation registers plus WAEC prepositional collocations",
  ),
  ORAL_ENGLISH_STRESS(
    title = "Syllable Stress Rules & Emphatic Contrast Studio",
    subtitle = "Test noun/verb stress shifts, -tion/-ic/-graphy rules & JAMB emphatic sentence contrast",
  ),
  PHONETICS_CONSONANTS_LAB(
    title = "IPA Vowel Contrast, -ed Allomorphs & Silent Letters",
    subtitle = "Compare /iː/ vs /ɪ/, /θ/ vs /ð/, past-tense /t/ vs /d/ vs /ɪd/, and silent consonants",
  ),
  COMPREHENSION_ESSAY_LAB(
    title = "WAEC Summary Paraphrase & Formal/Informal Essay Lab",
    subtitle = "Spot lifted phrase penalties vs own-word sentences and verify WAEC letter format rules",
  ),

  // Economics & Government
  ECONOMICS_ELASTICITY_MARKET_LAB(
    title = "Demand Elasticity & Market Equilibrium Lab",
    subtitle = "Adjust PED |ε|, demand/supply shifts, and indirect tax per unit to track revenue & tax incidence",
  ),
  ECONOMICS_MACRO_POLICY_LAB(
    title = "National Income, Inflation & CBN Monetary Lab",
    subtitle = "Simulate GDP = C + I + G + (X − M), credit multiplier 1/r, and CBN Cash Reserve / MPR levers",
  ),
  GOVERNMENT_ORGANS_FEDERALISM_LAB(
    title = "Separation of Powers & Nigerian Federalism Lab",
    subtitle = "Inspect executive-legislative-judicial checks, revenue allocation, and 1963 vs 1979 vs 1999 constitutions",
  ),
}

enum class LessonPillar(val key: String, val shortLabel: String, val fullLabel: String) {
  CONCEPT("concept", "Concepts", "Concepts & Theory"),
  EXAMPLES("examples", "Examples", "Worked Examples"),
  SCENARIO("scenario", "Context", "Applications & Notes"),
  LAB("lab", "Virtual Lab", "Virtual Lab"),
  PROJECT("project", "Project", "Applied Project"),
  EXERCISE("exercise", "Quiz", "Mastery Quiz"),
}

data class VocabularyEntry(
  val term: String,
  val definition: String,
  val simpleMeaning: String,
  val examUsage: String,
)

data class MisconceptionAnalysis(
  val wrongBelief: String,
  val whyStudentsBelieveIt: String,
  val correctUnderstanding: String,
  val avoidanceRule: String,
)

data class GuidedPracticeTask(
  val prompt: String,
  val difficulty: String,
  val estimatedMinutes: Int,
  val hints: List<String>,
  val expectedApproach: String,
  val completeSolution: String,
)

data class ProjectVerificationTask(
  val id: String,
  val criterionTitle: String,
  val prompt: String,
  val options: List<String>,
  val correctIndex: Int,
  val feedback: String,
)

data class TopicMasteryProject(
  val title: String,
  val format: String,
  val nigerianScenario: String,
  val stages: List<String>,
  val rubricCriteria: List<String>,
  val modelResponse: String,
  val verificationTasks: List<ProjectVerificationTask> = emptyList(),
)

data class TopicCheatSheet(
  val overview: String,
  val symbolLegend: List<String>,
  val commonTraps: List<String>,
  val memoryAid: String,
  val spacedRevisionDays: List<Int> = listOf(1, 3, 7, 14, 30),
)

data class MicroDrill(
  val prompt: String,
  val options: List<String>,
  val correctIndex: Int,
  val hint: String,
  val explanation: String,
)

data class VerifiedExternalResource(
  val title: String,
  val provider: String,
  val kind: String,
  val url: String,
  val description: String,
)

data class ConceptBlock(
  val heading: String,
  val explanation: String,
  val keyFormula: String? = null,
  val examTip: String,
  val mechanismWhyHow: String = "",
  val examIntelligence: String = "",
  val recap: String = "",
  val animationKey: String = "",
  val microDrills: List<MicroDrill> = emptyList(),
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
  val cognitiveLevel: String = "Application",
  val marks: Int = 20,
  val remediationTip: String = "",
  val passageText: String? = null,
) {
  val boardStyleLabel: String
    get() =
      when {
        examType.equals("WAEC", ignoreCase = true) -> "WAEC WASSCE-Style Practice"
        examType.equals("NECO", ignoreCase = true) -> "NECO SSCE-Style Practice"
        else -> "JAMB UTME-Style Practice"
      }
}

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
  val prerequisites: List<String> = emptyList(),
  val dependencySummary: String = "",
  val vocabulary: List<VocabularyEntry> = emptyList(),
  val misconceptions: List<MisconceptionAnalysis> = emptyList(),
  val guidedPractice: GuidedPracticeTask? = null,
  val masteryProject: TopicMasteryProject? = null,
  val cheatSheet: TopicCheatSheet? = null,
  val externalResources: List<VerifiedExternalResource> = emptyList(),
) {
  companion object {
    const val ACADEMY_NANODEGREE =
      "Nigerian Senior Secondary Excellence Nanodegree (JAMB UTME • WAEC WASSCE • NECO SSCE)"
  }
}

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

