package com.example.data.curriculum

/**
 * Shared specification describing the Academy-standard pedagogical payload for a single lesson.
 * Every topic across all 76 JAMB/WAEC/NECO lessons provides a topic-authentic spec through
 * the per-subject pack registries so clear definitions, underlying mechanisms,
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

  private val bespokeProjectChecks: Map<String, List<ProjectVerificationTask>> =
    mapOf(
      "phys_01_units" to
        listOf(
          ProjectVerificationTask(
            id = "vp_phys_01_1",
            criterionTitle = "Instrument Selection & Least Count",
            prompt =
              "You need to verify the 1.50 mm diameter of incoming copper wire reels in Kano. Which instrument set provides the exact required precision?",
            options =
              listOf(
                "Metre rule (1 mm) for wire diameter and spring balance for mass",
                "Micrometer screw gauge (0.01 mm) for wire diameter, steel rule (1 mm) for length, and digital balance (0.1 g) for mass",
                "Vernier caliper (0.1 mm) alone without checking zero error",
                "Clinical thermometer and measuring cylinder by water displacement",
              ),
            correctIndex = 1,
            feedback =
              "A 1.50 mm wire requires a micrometer screw gauge (0.01 mm least count) to resolve hundredths of a millimetre accurately.",
          ),
          ProjectVerificationTask(
            id = "vp_phys_01_2",
            criterionTitle = "Signed Zero-Error Calculation",
            prompt =
              "Before measuring, the closed micrometer reads −0.02 mm (negative zero error). With the copper wire clamped, the observed reading is 1.48 mm. What is the true wire diameter?",
            options = listOf("1.46 mm", "1.48 mm", "1.50 mm", "1.52 mm"),
            correctIndex = 2,
            feedback =
              "True Reading = Observed − (Signed Zero Error) = 1.48 − (−0.02) = 1.50 mm. Subtracting a negative offset adds its magnitude.",
          ),
          ProjectVerificationTask(
            id = "vp_phys_01_3",
            criterionTitle = "Dimensional Consistency Audit",
            prompt =
              "To verify copper density ρ = 4m / (π d² L), confirm the SI dimensions of the volume denominator (π d² L / 4) and resulting density ρ.",
            options =
              listOf(
                "Volume is [L²] and Density is [M L⁻²]",
                "Volume is [L³] and Density is [M L⁻³]",
                "Volume is [L³] and Density is [M L³]",
                "Volume is [M L²] and Density is [L⁻² T⁻¹]",
              ),
            correctIndex = 1,
            feedback =
              "Dimensionless constants π and 4 drop out, leaving [d²·L] = [L³] for volume and [M L⁻³] for density.",
          ),
        ),
      "phys_02_vectors" to
        listOf(
          ProjectVerificationTask(
            id = "vp_phys_02_1",
            criterionTitle = "Upstream Heading Cancellation",
            prompt =
              "A ferry rated at 5.0 m/s in still water must cross straight North across a river flowing East at 3.0 m/s. Which upstream heading angle θ West of North cancels the eastward river drift?",
            options =
              listOf(
                "cos θ = 3/5 → θ = 53.1° West of North",
                "sin θ = 3/5 → θ = 36.9° West of North",
                "tan θ = 5/3 → θ = 59.0° West of North",
                "Aim straight North (0°) and halve the engine speed",
              ),
            correctIndex = 1,
            feedback =
              "To cancel 3.0 m/s East, the Westward engine component 5.0 sin θ must equal 3.0 m/s, giving sin θ = 0.6 (36.9°).",
          ),
          ProjectVerificationTask(
            id = "vp_phys_02_2",
            criterionTitle = "Orthogonal Crossing Speed & Time",
            prompt =
              "With the bow aimed 36.9° West of North at 5.0 m/s against the 3.0 m/s East current, calculate the straight-North crossing velocity and time for a 400 m wide channel.",
            options =
              listOf(
                "8.0 m/s and 50 s",
                "2.0 m/s and 200 s",
                "4.0 m/s and 100 s",
                "5.0 m/s and 80 s",
              ),
            correctIndex = 2,
            feedback =
              "Resultant North speed v_N = √(5.0² − 3.0²) = 4.0 m/s (3-4-5 right triangle), so crossing time t = 400 / 4.0 = 100 s.",
          ),
          ProjectVerificationTask(
            id = "vp_phys_02_3",
            criterionTitle = "Equilibrant & Vector Classification Check",
            prompt =
              "If two tugboats pull with a resultant of 60.8 N at 34.7° North of East, what equilibrant vector holds the vessel stationary?",
            options =
              listOf(
                "60.8 N at 34.7° North of East",
                "60.8 N at 34.7° South of West",
                "30.4 N at 55.3° South of West",
                "0 N since tension is a scalar quantity",
              ),
            correctIndex = 1,
            feedback =
              "The equilibrant always matches the resultant magnitude (60.8 N) while pointing exactly 180° opposite (34.7° South of West).",
          ),
        ),
      "chem_01_separation_kinetic" to
        listOf(
          ProjectVerificationTask(
            id = "vp_chem_01_1",
            criterionTitle = "Immiscible vs Miscible Mixture Separation",
            prompt =
              "A water-quality sample from a mechanic workshop contains diesel oil floating over salted water with suspended rust grit. Select the right separation order.",
            options =
              listOf(
                "Evaporate everything first → Paper chromatography → Magnet",
                "Filter off solid rust grit → Separating funnel for diesel vs brine → Simple distillation to recover pure water from salt",
                "Sublimation → Separating funnel → Fractional distillation of rust",
                "Direct titration with methyl orange without filtration",
              ),
            correctIndex = 1,
            feedback =
              "Insoluble solids come out first by filtration, immiscible liquids split cleanly in a separating funnel, and pure water is distilled from brine.",
          ),
          ProjectVerificationTask(
            id = "vp_chem_01_2",
            criterionTitle = "Purity Confirmation via Boiling Point",
            prompt =
              "After distilling the brine filtrate, how does the technician confirm the distillate is pure H₂O at 1 atm rather than merely containing water?",
            options =
              listOf(
                "Drop anhydrous CuSO₄ powder and check for a blue colour change",
                "Measure sharp constant melting at 0.0°C and boiling at 100.0°C",
                "Taste a drop or measure volume in a burette",
                "Shake with soap solution until lather persists",
              ),
            correctIndex = 1,
            feedback =
              "CuSO₄ only detects the presence of water, whereas sharp constant MP/BP proves purity (impurities raise BP and lower MP).",
          ),
          ProjectVerificationTask(
            id = "vp_chem_01_3",
            criterionTitle = "Kinetic Plateau Interpretation",
            prompt =
              "While heating crushed ice steadily past 0°C, the thermometer stays completely flat at 0°C for 4 minutes. What explains this plateau?",
            options =
              listOf(
                "The Bunsen burner temporarily stopped transferring thermal energy",
                "Supplied heat energy breaks rigid intermolecular bonds (latent heat of fusion) rather than raising kinetic speed",
                "Ice sublimes directly into steam above 0°C",
                "Water's density falls to zero during melting",
              ),
            correctIndex = 1,
            feedback =
              "During a phase transition, thermal energy goes into potential energy to overcome lattice forces while average kinetic energy (temperature) stays constant.",
          ),
        ),
      "math_01_bases_modular" to
        listOf(
          ProjectVerificationTask(
            id = "vp_math_01_1",
            criterionTitle = "Base Conversion Accuracy",
            prompt =
              "A digital transit counter records 45 passengers in base 10. Convert 45₁₀ cleanly into binary (base 2) for the display register.",
            options = listOf("101100₂", "101101₂", "110101₂", "100111₂"),
            correctIndex = 1,
            feedback =
              "45 = 32 + 8 + 4 + 1 = 1·2⁵ + 0·2⁴ + 1·2³ + 1·2² + 0·2¹ + 1·2⁰ = 101101₂.",
          ),
          ProjectVerificationTask(
            id = "vp_math_01_2",
            criterionTitle = "Modular Schedule Arithmetic",
            prompt =
              "If a shift schedule cycles every 7 days (mod 7) and Duty Day 0 falls on Tuesday, which weekday corresponds to Day 52?",
            options = listOf("Tuesday", "Wednesday", "Friday", "Saturday"),
            correctIndex = 2,
            feedback =
              "52 ≡ 3 (mod 7) since 52 = 7×7 + 3; counting 3 days forward from Tuesday lands on Friday.",
          ),
          ProjectVerificationTask(
            id = "vp_math_01_3",
            criterionTitle = "Digit Bounds in Mixed Bases",
            prompt =
              "During audit of an unknown base equation 23_b = 19₁₀, why can b never be 2 or 3?",
            options =
              listOf(
                "Bases above 10 are disallowed in JAMB questions",
                "In any base b, every digit must be strictly less than b (so digit '3' requires b ≥ 4)",
                "Base 2 is reserved solely for fractional decimals",
                "Odd numbers cannot be written in bases below 5",
              ),
            correctIndex = 1,
            feedback =
              "base-b place notation permits only digits 0 through (b − 1). Here 2b + 3 = 19 solves to b = 8.",
          ),
        ),
      "bio_01_diversity_microscopy" to
        listOf(
          ProjectVerificationTask(
            id = "vp_bio_01_1",
            criterionTitle = "Binomial Nomenclature Formatting",
            prompt =
              "Which scientific label correctly follows Linnaeus binomial rules for cassava or maize in a WAEC specimen report?",
            options =
              listOf(
                "Zea Mays (both capitalized, un-underlined)",
                "Zea mays (Genus capitalized, species lowercase, italicized or underlined separately)",
                "zea mays (all lowercase)",
                "ZEA MAYS (all uppercase)",
              ),
            correctIndex = 1,
            feedback =
              "binomial names capitalize only the Genus, keep the specific epithet lowercase, and italicize (or underline separately by hand).",
          ),
          ProjectVerificationTask(
            id = "vp_bio_01_2",
            criterionTitle = "Compound Microscope Sizing Calculation",
            prompt =
              "A student draws a cheek cell 6.0 cm wide observed under a ×10 eyepiece and ×40 objective (total magnification ×400). Find the actual cell diameter in μm (1 cm = 10,000 μm).",
            options = listOf("15 μm", "150 μm", "600 μm", "2400 μm"),
            correctIndex = 1,
            feedback =
              "Actual Size = Drawing Size / Magnification = (6.0 × 10,000 μm) / 400 = 150 μm.",
          ),
          ProjectVerificationTask(
            id = "vp_bio_01_3",
            criterionTitle = "Prokaryote vs Eukaryote Verification",
            prompt =
              "Under an electron micrograph, a specimen shows circular DNA suspended freely in cytoplasm with 70S ribosomes and no nuclear membrane. Classify it.",
            options =
              listOf(
                "Eukaryotic plant palisade cell",
                "Prokaryotic bacterium (Monera)",
                "Fungal hypha cell",
                "Unicellular Protozoan (Amoeba)",
              ),
            correctIndex = 1,
            feedback =
              "prokaryotes lack a true membrane-bound nucleus and mitochondria, distinguishing Monera from all eukaryotic kingdoms.",
          ),
        ),
      "eng_01_parts_affixes" to
        listOf(
          ProjectVerificationTask(
            id = "vp_eng_01_1",
            criterionTitle = "Uncountable Mass-Noun Correction",
            prompt =
              "Which revision accurately corrects the prefect's draft: 'We bought new furnitures and many informations for the library'?",
            options =
              listOf(
                "We bought new furniture's and plenty informations for the library.",
                "We bought new furniture and much information for the library.",
                "We bought fewer furniture and many information for the library.",
                "We bought a new furniture and several information for the library.",
              ),
            correctIndex = 1,
            feedback =
              "'furniture' and 'information' are non-count nouns—they take no '-s', reject 'a/an', and pair with 'much/pieces of' rather than 'many'.",
          ),
          ProjectVerificationTask(
            id = "vp_eng_01_2",
            criterionTitle = "Preposition Case & Quantifier Precision",
            prompt =
              "Select the standard formal correction for: 'Between you and I, less students read the notice.'",
            options =
              listOf(
                "Between you and I, lesser students read the notice.",
                "Between you and me, fewer students read the notice.",
                "Between you and myself, less students read the notice.",
                "Among you and I, few student read the notice.",
              ),
            correctIndex = 1,
            feedback =
              "'between' is a preposition requiring object case ('you and me'), and plural count nouns ('students') require 'fewer', not 'less'.",
          ),
          ProjectVerificationTask(
            id = "vp_eng_01_3",
            criterionTitle = "OSASCOMP Adjective Order Audit",
            prompt =
              "Reorder the stacked adjectives cleanly before 'table' according to English OSASCOMP sequence (Opinion-Size-Age-Shape-Colour-Origin-Material-Purpose):",
            options =
              listOf(
                "a brown large Nigerian wooden new table",
                "a large new brown Nigerian wooden study table",
                "a wooden Nigerian large brown new table",
                "a Nigerian large new wooden brown table",
              ),
            correctIndex = 1,
            feedback =
              "Size ('large') → Age ('new') → Colour ('brown') → Origin ('Nigerian') → Material ('wooden') → Purpose ('study') follows OSASCOMP order.",
          ),
        ),
    )

  private val allBespokeProjectChecks: Map<String, List<ProjectVerificationTask>> by lazy {
    buildMap {
      putAll(bespokeProjectChecks)
      putAll(BespokeProjectChecksPhysicsMath.checks)
      putAll(BespokeProjectChecksChemBio.checks)
      putAll(BespokeProjectChecksEngHum.checks)
    }
  }

  private val shoutingProseWords: Map<String, String> =
    listOf(
      "ALWAYS",
      "NEVER",
      "ONLY",
      "EVERY",
      "BOTH",
      "THREE",
      "FOUR",
      "FIVE",
      "ZERO",
      "MULTIPLY",
      "SUBSTITUTION",
      "DISSOLVE",
      "STAYS",
      "SAME",
      "DIFFERENT",
      "COLOURLESS",
      "DIRTY",
      "GREEN",
      "REDDISH-BROWN",
      "BLUE",
      "WHITE",
      "RED",
      "EXCESS",
      "MUST",
      "WITHOUT",
      "FIRST",
      "SECOND",
      "INSTANTLY",
      "BEFORE",
      "AFTER",
      "COMPLETELY",
      "STRICTLY",
      "DIRECTLY",
      "ABOVE",
      "BELOW",
      "INSIDE",
      "OUTSIDE",
      "SPREAD",
      "UNCHANGED",
      "CLASS",
      "MIDPOINTS",
      "AT",
      "LEAST",
      "ONE",
      "FAIL",
      "SUBTRACTED",
      "ADDED",
      "DEOXYGENATED",
      "OXYGENATED",
      "SHADED",
      "SONS",
      "DILUTE",
      "SILENT",
      "IN",
      "FRONT",
      "OPPOSITE",
      "NEAREST",
      "NEXT",
    ).associateWith { it.lowercase() }

  private val proseCapsPattern = Regex("""\b[A-Z]{2,}(?:-[A-Z]{2,})?\b""")
  private val proseExclaimPattern = Regex("""([A-Za-z]{2,})!+""")
  private val parenExclaimPattern = Regex("""([A-Za-z]{2,}\))!+""")

  /**
   * Standardizes informal exclamation marks and shouting uppercase prose fillers while strictly
   * preserving chemical symbols, acronyms, Roman numerals, math factorials (e.g. 7!, n!), and
   * English emphatic-stress capitalization drills.
   */
  fun editorialStandardize(raw: String, lessonId: String = ""): String {
    if (raw.isBlank()) return raw
    val isStressDrill = lessonId == "eng_14_stress_rhyme"
    val normalizedCaps =
      if (isStressDrill) {
        raw
      } else {
        proseCapsPattern.replace(raw) { match ->
          val token = match.value
          // Skip quoted or parenthetical technical acronyms when ambiguous, only soften known shouting fillers
          val replacement = shoutingProseWords[token] ?: return@replace token
          val start = match.range.first
          // Preserve if at start of sentence / string (capitalize first letter)
          val atSentenceStart =
            start == 0 ||
              raw.substring(0, start).trimEnd().let { prefix ->
                prefix.isEmpty() || prefix.endsWith(".") || prefix.endsWith("?") || prefix.endsWith(":") || prefix.endsWith("\n")
              }
          if (atSentenceStart) {
            replacement.replaceFirstChar { it.titlecase() }
          } else {
            replacement
          }
        }
      }
    return normalizedCaps
      .replace(parenExclaimPattern, "$1.")
      .replace(proseExclaimPattern, "$1.")
      .replace("?.", "?")
      .replace("..", ".")
      .replace(" .", ".")
      .trim()
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

  fun sanitizeText(raw: String, lessonId: String = ""): String {
    if (raw.isBlank()) return raw
    val cleaned =
      raw
        .replace(Regex("""\(\s*(?:JAMB|WAEC|NECO)[^)]*20\d{2}[^)]*\)"""), "")
        .replace(citationRegex, "")
        .replace(Regex("""\s{2,}"""), " ")
        .trim()
    return editorialStandardize(cleaned, lessonId)
  }

  fun sanitizeQuestion(q: PracticeQuestion, indexInLesson: Int = 0, lessonId: String = ""): PracticeQuestion {
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
      question = sanitizeText(q.question, lessonId),
      hint = sanitizeText(q.hint, lessonId),
      stepByStepSolution = sanitizeText(q.stepByStepSolution, lessonId),
      waecMarkingScheme = q.waecMarkingScheme?.let { sanitizeText(it, lessonId) },
      cognitiveLevel = cogLevel,
      remediationTip = editorialStandardize(remediation, lessonId),
    )
  }

  fun sanitizeWorkedExample(ex: WorkedExample, lessonId: String = ""): WorkedExample =
    ex.copy(
      examSource = sanitizeExamSource(ex.examSource),
      problemStatement = sanitizeText(ex.problemStatement, lessonId),
      steps = ex.steps.map { sanitizeText(it, lessonId) },
      examinerTrap = sanitizeText(ex.examinerTrap, lessonId),
    )

  private fun buildVerificationTasksForProject(
    lesson: Lesson,
    project: TopicMasteryProject,
    misconceptions: List<MisconceptionAnalysis>,
    examples: List<WorkedExample>,
  ): List<ProjectVerificationTask> {
    if (project.verificationTasks.isNotEmpty()) return project.verificationTasks
    allBespokeProjectChecks[lesson.id]?.let { return it }

    val seed = (lesson.id.hashCode() and 0x7fffffff)
    val misc1 = misconceptions.getOrNull(0)
    val misc2 = misconceptions.getOrNull(1)
    val ex1 = examples.getOrNull(0)

    fun placeOptions(correct: String, distractors: List<String>, shift: Int): Pair<List<String>, Int> {
      val cleanDistractors = distractors.filter { it.isNotBlank() && it != correct }.distinct().take(3)
      val padded =
        cleanDistractors +
          listOf(
            "Skip boundary conditions and substitute unverified estimates directly.",
            "Rely purely on intuition without checking dimensional or rule consistency.",
            "Ignore unit conversions and sign conventions during final evaluation.",
          ).take((3 - cleanDistractors.size).coerceAtLeast(0))
      val slot = (seed + shift) % 4
      val result = mutableListOf<String>()
      var dIdx = 0
      for (i in 0 until 4) {
        if (i == slot) {
          result.add(correct)
        } else {
          result.add(padded.getOrElse(dIdx++) { "Alternative invalid step" })
        }
      }
      return result to slot
    }

    val tasks = mutableListOf<ProjectVerificationTask>()
    val crit1 = project.rubricCriteria.getOrNull(0) ?: "Core Principle & Parameter Selection"
    val step1 = project.stages.getOrNull(0) ?: crit1
    val (opts1, idx1) =
      placeOptions(
        correct = crit1,
        distractors =
          listOfNotNull(
            misc1?.wrongBelief,
            misc2?.wrongBelief,
            ex1?.examinerTrap?.let { "Apply unverified assumption: $it" },
          ),
        shift = 1,
      )
    tasks.add(
      ProjectVerificationTask(
        id = "vt_${lesson.id}_1",
        criterionTitle = "Checkpoint 1 • Protocol & Rule Setup",
        prompt = "To implement Step 1 ($step1) in \"${project.title}\", which specification satisfies the academic standard?",
        options = opts1,
        correctIndex = idx1,
        feedback = "$crit1." + (ex1?.examinerTrap?.let { " Key safeguard: $it" } ?: ""),
      )
    )
    if (misc1 != null) {
      val crit2 = project.rubricCriteria.getOrNull(1) ?: "Technical Error Audit"
      val (opts2, idx2) =
        placeOptions(
          correct = misc1.correctUnderstanding,
          distractors =
            listOf(
              "Accept the draft claim because ${misc1.whyStudentsBelieveIt.replaceFirstChar { it.lowercase() }}",
              misc2?.wrongBelief ?: "Apply the inverse formula without checking constraints.",
              "Omit the governing rule condition from the final report.",
            ),
          shift = 2,
        )
      tasks.add(
        ProjectVerificationTask(
          id = "vt_${lesson.id}_2",
          criterionTitle = "Checkpoint 2 • Technical Diagnostic ($crit2)",
          prompt =
            "Field Review Alert: A teammate's draft for \"${project.title}\" assumes: \"${misc1.wrongBelief}\". Which correction should replace it?",
          options = opts2,
          correctIndex = idx2,
          feedback = "${misc1.correctUnderstanding} Rule: ${misc1.avoidanceRule}",
        )
      )
    }
    val crit3 = project.rubricCriteria.getOrNull(2) ?: project.rubricCriteria.lastOrNull() ?: "Final Verification"
    val targetCorrect3 = misc2?.correctUnderstanding ?: crit3
    val (opts3, idx3) =
      placeOptions(
        correct = targetCorrect3,
        distractors =
          listOfNotNull(
            misc2?.wrongBelief,
            ex1?.examinerTrap,
            "Leave units, edge cases, or formatting unverified before sign-off.",
          ),
        shift = 3,
      )
    tasks.add(
      ProjectVerificationTask(
        id = "vt_${lesson.id}_3",
        criterionTitle = "Checkpoint 3 • Final Sign-Off Verification",
        prompt =
          if (misc2 != null) {
            "Before submitting \"${project.title}\", verify why a draft warning flags: \"${misc2.wrongBelief}\". Select the valid sign-off standard:"
          } else {
            "Select the condition that satisfies final verification ($crit3) for \"${project.title}\":"
          },
        options = opts3,
        correctIndex = idx3,
        feedback = misc2?.avoidanceRule ?: crit3,
      )
    )
    return tasks
  }

  private fun drillsForSection(lessonId: String, sectionIndex: Int): List<MicroDrill> {
    val p1 = ConceptMicroDrillsPart1.drillsFor(lessonId, sectionIndex)
    if (p1.isNotEmpty()) return p1
    return ConceptMicroDrillsPart2.drillsFor(lessonId, sectionIndex)
  }

  fun enrich(lesson: Lesson): Lesson {
    val spec = allPacks[lesson.id]
    val footerMeta = TopicSectionFooters.forLesson(lesson.id)

    val enrichedBaseConcepts =
      lesson.concepts.mapIndexed { idx, block ->
        val mech =
          block.mechanismWhyHow.ifBlank {
            spec?.mechanismNotes?.getOrNull(idx)
              ?: "Underlying mechanism: examine how each physical or formal variable interacts before applying shortcuts."
          }
        val intel =
          block.examIntelligence.ifBlank {
            spec?.examIntelligenceNotes?.getOrNull(idx)
              ?: "JAMB tests rapid conceptual discrimination; WAEC/NECO award method marks for stating governing rules, clean substitutions, and accurate units."
          }
        val recap =
          block.recap.ifBlank {
            spec?.recaps?.getOrNull(idx)
              ?: "Checkpoint: summarize '${block.heading.replace(Regex("""^\d+\.\s+"""), "").trim()}' clearly in your own words."
          }
        block.copy(
          heading = block.heading.replace(Regex("""^\d+\.\s+"""), "").trim(),
          explanation = editorialStandardize(block.explanation, lesson.id),
          examTip = editorialStandardize(block.examTip, lesson.id),
          mechanismWhyHow = editorialStandardize(mech, lesson.id),
          examIntelligence = editorialStandardize(intel, lesson.id),
          recap = editorialStandardize(recap, lesson.id),
        )
      }

    // Apply topic-specific Section 4 & Section 5 footers onto pack extra sections (indices 3 & 4 overall)
    val topicPackExtras =
      if (spec == null || enrichedBaseConcepts.size >= 5) {
        emptyList()
      } else {
        spec.extraSections.mapIndexed { extraIdx, blk ->
          val cleanBlk = blk.copy(heading = blk.heading.replace(Regex("""^\d+\.\s+"""), "").trim())
          val withFooter =
            when {
              extraIdx == 1 && footerMeta != null ->
                cleanBlk.copy(
                  mechanismWhyHow = footerMeta.sec4Mech,
                  examIntelligence = footerMeta.sec4Intel,
                  recap = footerMeta.sec4Recap,
                )
              extraIdx == 2 && footerMeta != null ->
                cleanBlk.copy(
                  mechanismWhyHow = footerMeta.sec5Mech,
                  examIntelligence = footerMeta.sec5Intel,
                  recap = footerMeta.sec5Recap,
                )
              else -> cleanBlk
            }
          withFooter.copy(
            explanation = editorialStandardize(withFooter.explanation, lesson.id),
            examTip = editorialStandardize(withFooter.examTip, lesson.id),
            mechanismWhyHow = editorialStandardize(withFooter.mechanismWhyHow, lesson.id),
            examIntelligence = editorialStandardize(withFooter.examIntelligence, lesson.id),
            recap = editorialStandardize(withFooter.recap, lesson.id),
          )
        }
      }

    // Flexible, syllabus-driven exhaustive sections appended idempotently with zero artificial count cap
    val existingHeadings =
      (enrichedBaseConcepts + topicPackExtras)
        .map { it.heading.replace(Regex("""^\d+\.\s+"""), "").trim().lowercase() }
        .toSet()

    val exhaustiveExtras =
      TopicExhaustiveSections.extraFor(lesson.id)
        .filter { blk ->
          val cleanHeading = blk.heading.replace(Regex("""^\d+\.\s+"""), "").trim().lowercase()
          cleanHeading !in existingHeadings
        }
        .map { blk ->
          blk.copy(
            heading = blk.heading.replace(Regex("""^\d+\.\s+"""), "").trim(),
            explanation = editorialStandardize(blk.explanation, lesson.id),
            examTip = editorialStandardize(blk.examTip, lesson.id),
            mechanismWhyHow = editorialStandardize(blk.mechanismWhyHow, lesson.id),
            examIntelligence = editorialStandardize(blk.examIntelligence, lesson.id),
            recap = editorialStandardize(blk.recap, lesson.id),
          )
        }

    val combinedConcepts =
      (enrichedBaseConcepts + topicPackExtras + exhaustiveExtras).mapIndexed { finalIdx, blk ->
        val sectionDrills = blk.microDrills.ifEmpty { drillsForSection(lesson.id, finalIdx) }
        blk.copy(
          animationKey = blk.animationKey.ifBlank { "${lesson.id}_sec_$finalIdx" },
          microDrills = sectionDrills,
        )
      }

    val cleanedExamples = lesson.workedExamples.map { sanitizeWorkedExample(it, lesson.id) }
    val cleanedExercises =
      lesson.exercises.mapIndexed { idx, q -> sanitizeQuestion(q, idx, lesson.id) }

    val resolvedMisconceptions = lesson.misconceptions.ifEmpty { spec?.misconceptions.orEmpty() }
    val baseProject = lesson.masteryProject ?: spec?.masteryProject
    val verifiedProject =
      baseProject?.copy(
        verificationTasks =
          buildVerificationTasksForProject(
            lesson = lesson,
            project = baseProject,
            misconceptions = resolvedMisconceptions,
            examples = cleanedExamples,
          ).map { task ->
            val cleanFb =
              task.feedback
                .replace(Regex("""^(?:Verified|Confirmed|Passed|Correct)\s*:\s*""", RegexOption.IGNORE_CASE), "")
                .trim()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            task.copy(feedback = editorialStandardize(cleanFb, lesson.id))
          }
      )

    val resolvedResources = lesson.externalResources.ifEmpty { VerifiedResourceCatalog.forLesson(lesson) }

    if (spec == null) {
      return lesson.copy(
        concepts = combinedConcepts,
        workedExamples = cleanedExamples,
        exercises = cleanedExercises,
        misconceptions = resolvedMisconceptions,
        masteryProject = verifiedProject,
        externalResources = resolvedResources,
      )
    }

    return lesson.copy(
      concepts = combinedConcepts,
      workedExamples = cleanedExamples,
      exercises = cleanedExercises,
      prerequisites = lesson.prerequisites.ifEmpty { spec.prerequisites },
      dependencySummary = lesson.dependencySummary.ifBlank { spec.dependencySummary },
      vocabulary = lesson.vocabulary.ifEmpty { spec.vocabulary },
      misconceptions = resolvedMisconceptions,
      guidedPractice = lesson.guidedPractice ?: spec.guidedPractice,
      masteryProject = verifiedProject,
      cheatSheet = lesson.cheatSheet ?: spec.cheatSheet,
      externalResources = resolvedResources,
    )
  }
}
