package com.example.ui.components

import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.data.curriculum.ConceptBlock
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.WorkedExample
import java.util.Locale
import java.util.UUID

@Stable
class LessonTtsController {
  var isReady by mutableStateOf(false)
    internal set

  var isSpeaking by mutableStateOf(false)
    internal set

  var activeUtteranceKey by mutableStateOf<String?>(null)
    internal set

  var speechRate by mutableFloatStateOf(1.0f)
    private set

  private var tts: TextToSpeech? = null
  private val mainHandler = Handler(Looper.getMainLooper())

  internal fun attach(engine: TextToSpeech?) {
    tts = engine
  }

  fun cycleSpeed() {
    speechRate =
      when {
        speechRate < 0.95f -> 1.0f
        speechRate < 1.15f -> 1.25f
        else -> 0.85f
      }
    runCatching { tts?.setSpeechRate(speechRate) }
  }

  fun toggleSpeak(utteranceKey: String, rawText: String) {
    if (isSpeaking && activeUtteranceKey == utteranceKey) {
      stop()
      return
    }
    speak(utteranceKey, rawText)
  }

  fun speak(utteranceKey: String, rawText: String) {
    val engine = tts ?: return
    if (!isReady) return
    val spoken = LessonSpeechFormatter.prepareForSpeech(rawText)
    if (spoken.isBlank()) return

    val id = "utme_tts_${UUID.randomUUID()}"
    activeUtteranceKey = utteranceKey
    isSpeaking = true
    runCatching {
      engine.setSpeechRate(speechRate)
      // Chunk very long sections safely below TextToSpeech.getMaxSpeechInputLength() (~4000 chars)
      val chunks = chunkSpeechText(spoken, maxLen = 3400)
      chunks.forEachIndexed { idx, chunk ->
        val queueMode = if (idx == 0) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD
        val chunkId = if (idx == chunks.lastIndex) id else "${id}_part_$idx"
        engine.speak(chunk, queueMode, null, chunkId)
      }
    }.onFailure {
      isSpeaking = false
      activeUtteranceKey = null
    }
  }

  fun stop() {
    isSpeaking = false
    activeUtteranceKey = null
    runCatching { tts?.stop() }
  }

  internal fun onUtteranceFinished(utteranceId: String?) {
    if (utteranceId == null || !utteranceId.contains("_part_")) {
      mainHandler.post {
        isSpeaking = false
        activeUtteranceKey = null
      }
    }
  }

  private fun chunkSpeechText(text: String, maxLen: Int): List<String> {
    if (text.length <= maxLen) return listOf(text)
    val result = mutableListOf<String>()
    var remaining = text
    while (remaining.length > maxLen) {
      val splitAt =
        remaining.lastIndexOf('.', maxLen).takeIf { it > maxLen / 2 }
          ?: remaining.lastIndexOf(' ', maxLen).takeIf { it > maxLen / 2 }
          ?: maxLen
      result += remaining.substring(0, splitAt + 1).trim()
      remaining = remaining.substring(splitAt + 1).trim()
    }
    if (remaining.isNotEmpty()) result += remaining
    return result
  }
}

@Composable
fun rememberLessonTtsController(): LessonTtsController {
  val context = LocalContext.current
  val controller = remember { LessonTtsController() }

  DisposableEffect(context) {
    var localEngine: TextToSpeech? = null
    runCatching {
      localEngine =
        TextToSpeech(context.applicationContext) { status ->
          if (status == TextToSpeech.SUCCESS) {
            val engine = localEngine
            if (engine != null) {
              val localeRes = engine.setLanguage(Locale.UK)
              if (localeRes == TextToSpeech.LANG_MISSING_DATA ||
                localeRes == TextToSpeech.LANG_NOT_SUPPORTED
              ) {
                engine.setLanguage(Locale.getDefault())
              }
              engine.setOnUtteranceProgressListener(
                object : UtteranceProgressListener() {
                  override fun onStart(utteranceId: String?) {}
                  override fun onDone(utteranceId: String?) {
                    controller.onUtteranceFinished(utteranceId)
                  }

                  @Deprecated("Deprecated in Java")
                  override fun onError(utteranceId: String?) {
                    controller.onUtteranceFinished(utteranceId)
                  }

                  override fun onError(utteranceId: String?, errorCode: Int) {
                    controller.onUtteranceFinished(utteranceId)
                  }
                }
              )
              controller.isReady = true
            }
          }
        }
      controller.attach(localEngine)
    }

    onDispose {
      controller.stop()
      runCatching { localEngine?.shutdown() }
      controller.attach(null)
    }
  }

  return controller
}

object LessonSpeechFormatter {

  fun prepareForSpeech(raw: String): String {
    if (raw.isBlank()) return ""
    var s = ScientificNotationEngine.normalizeMarkup(raw)
    s = s.replace(Regex("""\^\{2\}"""), " squared")
    s = s.replace(Regex("""\^\{3\}"""), " cubed")
    s = s.replace(Regex("""\^\{([^}]+)\}"""), " to the power of $1")
    s = s.replace(Regex("""_\{([^}]+)\}"""), " $1")
    s = s.replace(Regex("""√\(([^)]+)\)"""), "square root of $1")
    s = s.replace("√", "square root of ")
    s =
      s.replace("⇌", " is in equilibrium with ")
        .replace("⇒", " implies ")
        .replace("→", " yields ")
        .replace("←", " from ")
        .replace("≥", " is greater than or equal to ")
        .replace("≤", " is less than or equal to ")
        .replace("≠", " is not equal to ")
        .replace("≈", " is approximately equal to ")
        .replace("∝", " is proportional to ")
        .replace("×", " times ")
        .replace("÷", " divided by ")
        .replace("±", " plus or minus ")
        .replace("Δ", " delta ")
        .replace("θ", " theta ")
        .replace("π", " pi ")
        .replace("λ", " lambda ")
        .replace("μ", " micro ")
        .replace("Ω", " ohms ")
        .replace("°C", " degrees Celsius ")
        .replace("°", " degrees ")
        .replace("•", ". ")
        .replace("|", ". ")
    s = s.replace(Regex("""\s{2,}"""), " ")
    return s.trim()
  }

  fun buildConceptSpeechText(block: ConceptBlock): String =
    buildString {
      append(block.heading).append(". ")
      append(block.explanation).append(". ")
      if (block.mechanismWhyHow.isNotBlank()) {
        append("Mechanism: ").append(block.mechanismWhyHow).append(". ")
      }
      if (!block.keyFormula.isNullOrBlank()) {
        append("Key formula: ").append(block.keyFormula).append(". ")
      }
      if (block.examTip.isNotBlank()) {
        append("Examiner tip: ").append(block.examTip).append(". ")
      }
      if (block.recap.isNotBlank()) {
        append(block.recap).append(".")
      }
    }

  fun buildExampleSpeechText(example: WorkedExample): String =
    buildString {
      append(example.title).append(". ")
      append("Problem: ").append(example.problemStatement).append(". ")
      if (example.steps.isNotEmpty()) {
        append("Steps: ")
        example.steps.forEach { step -> append(step).append(". ") }
      }
      append("Final answer: ").append(example.finalAnswer).append(". ")
      if (example.examinerTrap.isNotBlank()) {
        append("Avoid this trap: ").append(example.examinerTrap).append(".")
      }
    }

  fun buildPillarSpeechText(lesson: Lesson, pillar: LessonPillar): String =
    when (pillar) {
      LessonPillar.CONCEPT ->
        buildString {
          append(lesson.title).append(". Concepts and theory. ")
          if (lesson.learningOutcomes.isNotEmpty()) {
            append("Learning outcomes: ").append(lesson.learningOutcomes.joinToString(". ")).append(". ")
          }
          if (lesson.vocabulary.isNotEmpty()) {
            append("Key terms: ")
            lesson.vocabulary.forEach { entry ->
              append(entry.term).append(": ").append(entry.simpleMeaning).append(". ")
            }
          }
          lesson.concepts.forEachIndexed { idx, block ->
            append("Section ").append(idx + 1).append(": ").append(buildConceptSpeechText(block)).append(" ")
          }
        }

      LessonPillar.EXAMPLES ->
        buildString {
          append(lesson.title).append(". Worked examples. ")
          lesson.workedExamples.forEachIndexed { idx, ex ->
            append("Example ").append(idx + 1).append(": ").append(buildExampleSpeechText(ex)).append(" ")
          }
          lesson.guidedPractice?.let { task ->
            append("Guided practice: ").append(task.prompt).append(". ")
            append("Expected strategy: ").append(task.expectedApproach).append(".")
          }
        }

      LessonPillar.SCENARIO ->
        buildString {
          append(lesson.title).append(". Applications and revision notes. ")
          lesson.scenarios.forEach { sc ->
            append(sc.title).append(". ").append(sc.narrative).append(". ")
            append("Exam connection: ").append(sc.examConnection).append(". ")
          }
          lesson.misconceptions.forEach { misc ->
            append("Common misconception: ").append(misc.wrongBelief).append(". ")
            append("Correct understanding: ").append(misc.correctUnderstanding).append(". ")
          }
          lesson.cheatSheet?.let { cheat ->
            append("Summary: ").append(cheat.overview).append(". ")
            if (cheat.memoryAid.isNotBlank()) {
              append("Memory anchor: ").append(cheat.memoryAid).append(".")
            }
          }
        }

      LessonPillar.LAB ->
        buildString {
          append(lesson.title).append(". Virtual laboratory. ")
          append(lesson.labType.title).append(". ")
          append(lesson.labType.subtitle).append(". ")
          append(lesson.labBrief)
        }

      LessonPillar.PROJECT ->
        buildString {
          append(lesson.title).append(". Applied project. ")
          lesson.masteryProject?.let { proj ->
            append(proj.title).append(". ")
            append(proj.nigerianScenario).append(". ")
            if (proj.stages.isNotEmpty()) {
              append("Workflow: ").append(proj.stages.joinToString(". ")).append(".")
            }
          }
        }

      LessonPillar.EXERCISE ->
        buildString {
          append(lesson.title).append(". Mastery quiz. ")
          append("Complete the objective questions below with full working to unlock the next topic.")
        }
    }
}
