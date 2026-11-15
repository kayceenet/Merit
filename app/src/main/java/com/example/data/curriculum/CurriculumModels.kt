package com.example.data.curriculum

enum class ExamBoard(val label: String) {
  JAMB("JAMB UTME"),
  WAEC("WAEC SSCE"),
  BOTH("JAMB + WAEC"),
}

enum class PracticalLabType(val title: String, val subtitle: String) {
  PROJECTILE_KINEMATICS(
    title = "Projectile Kinematics Lab",
    subtitle = "Adjust launch speed, angle & gravity to inspect parabolic trajectory, range & flight time",
  ),
  OHMS_CIRCUIT(
    title = "DC Circuit & Power Bench",
    subtitle = "Toggle Series vs Parallel resistors and watch current, voltage drops & power dissipation",
  ),
  QUADRATIC_TANGENT(
    title = "Quadratic & Derivative Explorer",
    subtitle = "Tune coefficients a, b, c to visualize roots, discriminant Δ & live tangent slope dy/dx",
  ),
  TITRATION_PH(
    title = "Acid-Base Titration Lab",
    subtitle = "Add NaOH titrant to HCl/CH₃COOH and observe the pH sigmoid curve & indicator color",
  ),
  MENDELIAN_GENETICS(
    title = "Mendelian & Sickle-Cell Cross Lab",
    subtitle = "Select parental genotypes (HbA/HbS or Traits) to inspect Punnett ratios & clinical risk",
  ),
  ORAL_ENGLISH_STRESS(
    title = "Syllable Stress & Concord Studio",
    subtitle = "Test JAMB primary stress shifts and build syntactically valid concord clauses",
  ),
}

enum class LessonPillar(val key: String, val shortLabel: String, val fullLabel: String) {
  CONCEPT("concept", "1. Concept", "Systematic Concept & Formulas"),
  EXAMPLES("examples", "2. Examples", "Worked Past-Paper Examples"),
  SCENARIO("scenario", "3. Real-Life", "Real-World Applications"),
  LAB("lab", "4. Lab Demo", "Interactive Practical Demonstration"),
  EXERCISE("exercise", "5. Exercise", "Check-Your-Grasp Mastery Drill"),
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
  val tagline: String,
  val examCoverage: String,
  val modules: List<CourseModule>,
  val pastQuestions: List<PracticeQuestion>,
) {
  val allLessons: List<Lesson>
    get() = modules.flatMap { it.lessons }
}
