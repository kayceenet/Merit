package com.example.data.curriculum

/**
 * Shared specification describing the Academy-standard pedagogical payload for a single lesson.
 * Every topic across all 76 JAMB/WAEC/NECO lessons provides a topic-authentic spec through
 * the per-subject pack registries so zero assumed understanding, clear mechanisms,
 * misconception diagnostics, 3-hint guided practice, Nigerian capstone projects, and cheat sheets
 * are always available consistently.
 */
data class LessonAcademySpec(
  val prerequisites: List<String>,
  val dependencySummary: String,
  val vocabulary: List<VocabularyEntry>,
  val mechanismNotes: List<String>,
  val examIntelligenceNotes: List<String>,
  val recaps: List<String>,
  val extraSections: List<ConceptBlock>,
  val misconceptions: List<MisconceptionAnalysis>,
  val guidedPractice: GuidedPracticeTask,
  val masteryProject: TopicMasteryProject,
  val cheatSheet: TopicCheatSheet,
)

object AcademyCurriculumEnricher {

  private val allPacks: Map<String, LessonAcademySpec> by lazy {
    buildMap {
      putAll(PhysicsAcademyPacks.specs)
      putAll(MathAcademyPacks.specs)
      putAll(ChemistryAcademyPacks.specs)
      putAll(BiologyAcademyPacks.specs)
      putAll(EnglishAcademyPacks.specs)
    }
  }

  private val citationRegex =
    Regex("""\b(JAMB|UTME|WAEC|WASSCE|NECO|SSCE)[^•\n]*\b(20\d{2})\b[^•\n]*""")

  fun sanitizeExamSource(raw: String): String =
    when {
      raw.contains("NECO", ignoreCase = true) -> "NECO SSCE-Style Worked Problem"
      raw.contains("WAEC", ignoreCase = true) || raw.contains("WASSCE", ignoreCase = true) ->
        "WAEC WASSCE-Style Worked Problem"
      else -> "JAMB UTME-Style Worked Problem"
    }

  fun sanitizeText(raw: String): String {
    if (raw.isBlank()) return raw
    return raw
      .replace(Regex("""\(\s*(?:JAMB|WAEC|NECO)[^)]*20\d{2}[^)]*\)"""), "")
      .replace(citationRegex, "")
      .replace(Regex("""\s{2,}"""), " ")
      .trim()
  }

  fun sanitizeQuestion(q: PracticeQuestion, indexInLesson: Int = 0): PracticeQuestion {
    val normalizedBoard =
      when {
        q.examType.equals("NECO", ignoreCase = true) -> "NECO"
        q.examType.equals("WAEC", ignoreCase = true) -> "WAEC"
        q.id.endsWith("_6") && q.examType.equals("JAMB", ignoreCase = true) -> "NECO"
        else -> "JAMB"
      }
    val cogLevel =
      if (q.cognitiveLevel != "Application" || indexInLesson == 0) {
        when (indexInLesson % 5) {
          0 -> "Foundational"
          4 -> "Analysis"
          else -> q.cognitiveLevel.ifBlank { "Application" }
        }
      } else {
        q.cognitiveLevel
      }
    val remediation =
      q.remediationTip.ifBlank {
        "Re-read the concept mechanism for ${q.topic}, verify every unit or rule condition, and trace the step-by-step derivation again before retrying."
      }
    return q.copy(
      examType = normalizedBoard,
      question = sanitizeText(q.question),
      hint = sanitizeText(q.hint),
      stepByStepSolution = sanitizeText(q.stepByStepSolution),
      waecMarkingScheme = q.waecMarkingScheme?.let(::sanitizeText),
      cognitiveLevel = cogLevel,
      remediationTip = remediation,
    )
  }

  fun sanitizeWorkedExample(ex: WorkedExample): WorkedExample =
    ex.copy(
      examSource = sanitizeExamSource(ex.examSource),
      problemStatement = sanitizeText(ex.problemStatement),
      steps = ex.steps.map(::sanitizeText),
      examinerTrap = sanitizeText(ex.examinerTrap),
    )

  fun enrich(lesson: Lesson): Lesson {
    val spec = allPacks[lesson.id]
    val enrichedBaseConcepts =
      lesson.concepts.mapIndexed { idx, block ->
        val mech =
          block.mechanismWhyHow.ifBlank {
            spec?.mechanismNotes?.getOrNull(idx)
              ?: "Underlying mechanism: focus on how each variable or rule behaves physically or logically before memorizing a shortcut."
          }
        val intel =
          block.examIntelligence.ifBlank {
            spec?.examIntelligenceNotes?.getOrNull(idx)
              ?: "JAMB tests quick conceptual discrimination under time pressure; WAEC/NECO award marks for stating the guiding law, correct substitution, and proper unit/format."
          }
        val recap =
          block.recap.ifBlank {
            spec?.recaps?.getOrNull(idx)
              ?: "Checkpoint: explain '${block.heading}' in one plain sentence before moving to the next section."
          }
        block.copy(
          mechanismWhyHow = mech,
          examIntelligence = intel,
          recap = recap,
        )
      }

    val combinedConcepts =
      if (enrichedBaseConcepts.size >= 5 || spec == null) {
        enrichedBaseConcepts
      } else {
        (enrichedBaseConcepts + spec.extraSections).take(6)
      }

    val cleanedExamples = lesson.workedExamples.map(::sanitizeWorkedExample)
    val cleanedExercises =
      lesson.exercises.mapIndexed { idx, q -> sanitizeQuestion(q, idx) }

    if (spec == null) {
      return lesson.copy(
        concepts = combinedConcepts,
        workedExamples = cleanedExamples,
        exercises = cleanedExercises,
      )
    }

    return lesson.copy(
      concepts = combinedConcepts,
      workedExamples = cleanedExamples,
      exercises = cleanedExercises,
      prerequisites = lesson.prerequisites.ifEmpty { spec.prerequisites },
      dependencySummary = lesson.dependencySummary.ifBlank { spec.dependencySummary },
      vocabulary = lesson.vocabulary.ifEmpty { spec.vocabulary },
      misconceptions = lesson.misconceptions.ifEmpty { spec.misconceptions },
      guidedPractice = lesson.guidedPractice ?: spec.guidedPractice,
      masteryProject = lesson.masteryProject ?: spec.masteryProject,
      cheatSheet = lesson.cheatSheet ?: spec.cheatSheet,
    )
  }
}
