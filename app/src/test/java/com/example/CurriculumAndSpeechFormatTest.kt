package com.example

import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.LessonPillar
import com.example.ui.components.LessonSpeechFormatter
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CurriculumAndSpeechFormatTest {

  @Test
  fun `lesson pillars and enriched content contain no numbering or tautology prefixes`() {
    val leadingDigitRegex = Regex("""^\d+\.\s+""")
    val tautologyRegex = Regex("""^(Verified|Confirmed|Passed|Correct)\s*:""", RegexOption.IGNORE_CASE)

    LessonPillar.entries.forEach { pillar ->
      assertFalse(leadingDigitRegex.containsMatchIn(pillar.shortLabel))
      assertFalse(pillar.fullLabel.contains("Stage ", ignoreCase = true))
    }

    val allLessons = CurriculumCatalog.tracks.flatMap { it.allLessons }
    assertTrue(allLessons.isNotEmpty())
    allLessons.forEach { rawLesson ->
      val enriched = com.example.data.curriculum.AcademyCurriculumEnricher.enrich(rawLesson)
      listOf(rawLesson, enriched).forEach { lesson ->
        lesson.concepts.forEach { block ->
          assertFalse(
            "Concept heading still numbered in ${lesson.id}: ${block.heading}",
            leadingDigitRegex.containsMatchIn(block.heading),
          )
        }
        lesson.masteryProject?.verificationTasks?.forEach { task ->
          assertFalse(
            "Tautology prefix found in ${lesson.id}/${task.id}: ${task.feedback}",
            tautologyRegex.containsMatchIn(task.feedback.trim()),
          )
          assertFalse(
            "Stage wording found in ${lesson.id}/${task.id}: ${task.prompt}",
            task.prompt.contains("Stage 1", ignoreCase = true),
          )
        }
      }
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
