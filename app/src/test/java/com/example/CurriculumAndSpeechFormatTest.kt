package com.example

import com.example.data.curriculum.AcademyCurriculumEnricher
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.Paper2AndPlacementBank
import com.example.data.curriculum.PracticalLabType
import com.example.ui.components.LessonSpeechFormatter
import com.example.ui.labs.GamifiedLabChallenges
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.net.URI

class CurriculumAndSpeechFormatTest {

  private val approvedHosts =
    setOf(
      "colorado.edu",
      "phet.colorado.edu",
      "olabs.edu.in",
      "www.olabs.edu.in",
      "labster.com",
      "praxilabs.com",
      "vlaby.com",
      "labxchange.org",
      "www.labxchange.org",
      "chemcollective.org",
      "explorelearning.com",
      "scisim.org",
      "concord.org",
      "geogebra.org",
      "www.geogebra.org",
      "desmos.com",
      "www.desmos.com",
      "myphysicslab.com",
      "www.myphysicslab.com",
      "sciencebysimulation.com",
      "biomanbio.com",
      "biointeractive.org",
      "www.biointeractive.org",
      "avhol.com",
      "physicsclassroom.com",
      "www.physicsclassroom.com",
      "wikimedia.org",
      "commons.wikimedia.org",
      "upload.wikimedia.org",
      "giphy.com",
      "pixabay.com",
      "classroomclipart.com",
      "khanacademy.org",
      "www.khanacademy.org",
      "isaacscience.org",
      "mit.edu",
      "ocw.mit.edu",
      "rsc.org",
      "edu.rsc.org",
      "gsu.edu",
      "hyperphysics.phy-astr.gsu.edu",
      "chemicalaid.com",
      "www.chemicalaid.com",
    )

  @Test
  fun `all 96 lessons enrich cleanly without numbering, tautology, or template fallbacks`() {
    val leadingDigitRegex = Regex("""^\d+\.\s+""")
    val tautologyRegex = Regex("""^(Verified|Confirmed|Passed|Correct)\s*:""", RegexOption.IGNORE_CASE)

    LessonPillar.entries.forEach { pillar ->
      assertFalse(leadingDigitRegex.containsMatchIn(pillar.shortLabel))
      assertFalse(pillar.fullLabel.contains("Stage ", ignoreCase = true))
    }

    val rawLessons = CurriculumCatalog.tracks.flatMap { it.allLessons }
    assertEquals("Expected exact 96 curriculum lessons", 96, rawLessons.size)

    val enrichedLessons = rawLessons.map { AcademyCurriculumEnricher.enrich(it) }

    // Ensure flexible exhaustive section cap removal is working on key dense lessons
    val expandedCount = enrichedLessons.count { it.concepts.size > 5 }
    assertTrue("Expected at least 20 syllabus-expanded lessons (> 5 sections)", expandedCount >= 20)

    enrichedLessons.forEach { lesson ->
      val reEnriched = AcademyCurriculumEnricher.enrich(lesson)
      assertEquals(
        "enrich() is not idempotent for concept count on ${lesson.id}",
        lesson.concepts.size,
        reEnriched.concepts.size,
      )
      assertTrue("${lesson.id} has empty concepts", lesson.concepts.isNotEmpty())
      lesson.concepts.forEach { block ->
        assertFalse(
          "Concept heading still numbered in ${lesson.id}: ${block.heading}",
          leadingDigitRegex.containsMatchIn(block.heading),
        )
        assertTrue(
          "Animation key missing in ${lesson.id}/${block.heading}",
          block.animationKey.isNotBlank(),
        )
        block.microDrills.forEach { drill ->
          assertTrue(
            "Invalid microDrill options in ${lesson.id}/${block.heading}",
            drill.options.size >= 2 && drill.correctIndex in drill.options.indices,
          )
        }
      }

      val totalDrills = lesson.concepts.sumOf { it.microDrills.size }
      assertTrue("Expected at least 1 concept micro-drill in ${lesson.id}", totalDrills >= 1)

      val projectTasks = lesson.masteryProject?.verificationTasks.orEmpty()
      assertEquals("Expected 3 bespoke project checks in ${lesson.id}", 3, projectTasks.size)
      projectTasks.forEach { task ->
        assertFalse(
          "Tautology prefix found in ${lesson.id}/${task.id}: ${task.feedback}",
          tautologyRegex.containsMatchIn(task.feedback.trim()),
        )
        assertFalse(
          "Stage wording found in ${lesson.id}/${task.id}: ${task.prompt}",
          task.prompt.contains("Stage 1", ignoreCase = true),
        )
        assertFalse(
          "Algorithmic fallback leaked into ${lesson.id}/${task.id}",
          task.prompt.contains("Field Review Alert: A teammate's draft"),
        )
      }

      assertTrue("Expected curated external resources for ${lesson.id}", lesson.externalResources.isNotEmpty())
      lesson.externalResources.forEach { res ->
        val host = URI(res.url).host?.lowercase().orEmpty()
        assertTrue(
          "Unapproved domain '$host' (${res.url}) in ${lesson.id}",
          approvedHosts.any { approved -> host == approved || host.endsWith(".$approved") },
        )
      }
    }
  }

  @Test
  fun `section 4 and 5 footers in pack subjects are topic-distinct rather than boilerplate`() {
    val enrichedBySubject =
      CurriculumCatalog.tracks
        .flatMap { it.allLessons }
        .map { AcademyCurriculumEnricher.enrich(it) }
        .groupBy { it.subjectId }

    listOf("physics", "mathematics", "chemistry", "biology", "english").forEach { subj ->
      val lessons = enrichedBySubject[subj].orEmpty()
      val sec4Mechanisms = lessons.mapNotNull { it.concepts.getOrNull(3)?.mechanismWhyHow }.toSet()
      val sec5Mechanisms = lessons.mapNotNull { it.concepts.getOrNull(4)?.mechanismWhyHow }.toSet()
      assertTrue(
        "Section 4 mechanisms collapsed to shared boilerplate in $subj",
        sec4Mechanisms.size >= (lessons.size - 1),
      )
      assertTrue(
        "Section 5 mechanisms collapsed to shared boilerplate in $subj",
        sec5Mechanisms.size >= (lessons.size - 1),
      )
    }
  }

  @Test
  fun `editorial standardization preserves factorials and stress words while curbing prose shouting`() {
    val enriched =
      CurriculumCatalog.tracks
        .flatMap { it.allLessons }
        .associate { it.id to AcademyCurriculumEnricher.enrich(it) }

    val probabilityLesson = enriched.getValue("math_14_perm_comb_prob")
    val combText =
      probabilityLesson.concepts.joinToString("\n") {
        listOfNotNull(it.explanation, it.keyFormula, it.mechanismWhyHow, it.examTip).joinToString(" ")
      }
    assertTrue("Expected factorial n! preserved in math_14_probability", combText.contains("n!") || combText.contains("!"))

    val stressLesson = enriched.getValue("eng_14_stress_rhyme")
    val stressQuestions = stressLesson.exercises.joinToString(" ") { it.question }
    assertTrue(
      "Expected emphatic stress ALL-CAPS tokens preserved in eng_14_stress_rhyme",
      Regex("""\b[A-Z]{3,}\b""").containsMatchIn(stressQuestions),
    )

    val paper2 = Paper2AndPlacementBank.allPaper2Prompts
    assertTrue("Expected expanded Paper 2 prompts (>= 25)", paper2.size >= 25)
    assertEquals(7, paper2.map { it.subjectId }.toSet().size)
    paper2.forEach { p ->
      val combined = "${p.prompt} ${p.rubricSteps.joinToString(" ") { it.description }} ${p.modelSolution}"
      assertFalse("Drafting note found in ${p.id}", combined.contains("wait if equation", ignoreCase = true))
      assertFalse("Drafting note found in ${p.id}", combined.contains("Wait find", ignoreCase = false))
    }
  }

  @Test
  fun `every PracticalLabType builds 3 gamified virtual lab challenges cleanly`() {
    PracticalLabType.entries.forEach { labType ->
      val rounds = GamifiedLabChallenges.roundsFor(labType)
      assertEquals("Expected 3 gamified rounds for $labType", 3, rounds.size)
    }
  }

  @Test
  fun `speech formatter converts formulas into clear spoken text`() {
    val spoken =
      LessonSpeechFormatter.prepareForSpeech(
        """v^{2} = u^{2} + 2as \quad\text{and}\quad \sqrt{x^{2} + y^{2}} \rightarrow 90^\circ"""
      )
    assertTrue(spoken.contains("squared"))
    assertTrue(spoken.contains("square root of"))
    assertTrue(spoken.contains("yields"))
    assertFalse(spoken.contains("\\quad"))
    assertFalse(spoken.contains("\\text"))
  }
}
