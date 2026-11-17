package com.example.data.curriculum

import com.example.data.model.LearningChapterEntity
import com.example.data.model.LearningModuleEntity
import com.example.data.model.PastQuestionEntity
import org.json.JSONArray
import org.json.JSONObject

object CurriculumEntityMapper {

  fun buildModuleEntities(): List<LearningModuleEntity> =
    CurriculumCatalog.tracks.flatMap { track ->
      track.modules.mapIndexed { idx, mod ->
        LearningModuleEntity(
          id = mod.id,
          subjectId = mod.subjectId,
          indexLabel = mod.indexLabel,
          title = mod.title,
          summary = mod.summary,
          orderIndex = idx,
        )
      }
    }

  fun buildChapterEntities(): List<LearningChapterEntity> =
    CurriculumCatalog.tracks.flatMap { track ->
      track.modules.flatMap { mod ->
        mod.lessons.mapIndexed { idx, lesson ->
          LearningChapterEntity(
            id = lesson.id,
            moduleId = lesson.moduleId,
            subjectId = lesson.subjectId,
            title = lesson.title,
            durationMinutes = lesson.durationMinutes,
            syllabusTag = lesson.syllabusTag,
            labTypeKey = lesson.labType.name,
            labBrief = lesson.labBrief,
            orderIndex = idx,
            learningOutcomesJson = encodeStringList(lesson.learningOutcomes),
            conceptsJson = encodeConcepts(lesson.concepts),
            workedExamplesJson = encodeExamples(lesson.workedExamples),
            scenariosJson = encodeScenarios(lesson.scenarios),
            exercisesJson = encodeQuestions(lesson.exercises),
          )
        }
      }
    }

  fun buildPastQuestionEntities(): List<PastQuestionEntity> =
    CurriculumCatalog.tracks.flatMap { track ->
      val combined = (track.pastQuestions + track.allLessons.flatMap { it.exercises }).distinctBy { it.id }
      combined.mapIndexed { idx, q ->
        PastQuestionEntity(
          id = "${track.id}_${q.id}",
          subjectId = track.id,
          topic = q.topic,
          examType = q.examType,
          year = q.year,
          question = q.question,
          optionsJson = encodeStringList(q.options),
          correctIndex = q.correctIndex,
          hint = q.hint,
          stepByStepSolution = q.stepByStepSolution,
          waecMarkingScheme = q.waecMarkingScheme,
          orderIndex = idx,
        )
      }
    }

  fun toDomainModules(
    modules: List<LearningModuleEntity>,
    chapters: List<LearningChapterEntity>,
  ): List<CourseModule> {
    val chaptersByModule = chapters.groupBy { it.moduleId }
    return modules
      .sortedBy { it.orderIndex }
      .map { mod ->
        CourseModule(
          id = mod.id,
          subjectId = mod.subjectId,
          indexLabel = mod.indexLabel,
          title = mod.title,
          summary = mod.summary,
          lessons =
            (chaptersByModule[mod.id] ?: emptyList())
              .sortedBy { it.orderIndex }
              .map(::toDomainLesson),
        )
      }
  }

  fun toDomainLesson(entity: LearningChapterEntity): Lesson {
    val labType =
      PracticalLabType.entries.find { it.name == entity.labTypeKey }
        ?: PracticalLabType.PROJECTILE_KINEMATICS
    return Lesson(
      id = entity.id,
      moduleId = entity.moduleId,
      subjectId = entity.subjectId,
      title = entity.title,
      durationMinutes = entity.durationMinutes,
      syllabusTag = entity.syllabusTag,
      learningOutcomes = decodeStringList(entity.learningOutcomesJson),
      concepts = decodeConcepts(entity.conceptsJson),
      workedExamples = decodeExamples(entity.workedExamplesJson),
      scenarios = decodeScenarios(entity.scenariosJson),
      labType = labType,
      labBrief = entity.labBrief,
      exercises = decodeQuestions(entity.exercisesJson),
    )
  }

  fun toDomainQuestion(entity: PastQuestionEntity): PracticeQuestion =
    PracticeQuestion(
      id = entity.id,
      topic = entity.topic,
      examType = entity.examType,
      year = entity.year,
      question = entity.question,
      options = decodeStringList(entity.optionsJson),
      correctIndex = entity.correctIndex,
      hint = entity.hint,
      stepByStepSolution = entity.stepByStepSolution,
      waecMarkingScheme = entity.waecMarkingScheme,
    )

  private fun encodeStringList(items: List<String>): String =
    JSONArray().apply { items.forEach { put(it) } }.toString()

  private fun decodeStringList(raw: String): List<String> =
    runCatching {
      val arr = JSONArray(raw)
      List(arr.length()) { i -> arr.optString(i, "") }
    }.getOrDefault(emptyList())

  private fun encodeConcepts(items: List<ConceptBlock>): String =
    JSONArray().apply {
      items.forEach { c ->
        put(
          JSONObject().apply {
            put("heading", c.heading)
            put("explanation", c.explanation)
            put("keyFormula", c.keyFormula ?: JSONObject.NULL)
            put("examTip", c.examTip)
          }
        )
      }
    }.toString()

  private fun decodeConcepts(raw: String): List<ConceptBlock> =
    runCatching {
      val arr = JSONArray(raw)
      List(arr.length()) { i ->
        val o = arr.getJSONObject(i)
        val kf = o.opt("keyFormula")
        ConceptBlock(
          heading = o.optString("heading", ""),
          explanation = o.optString("explanation", ""),
          keyFormula = if (kf == null || kf == JSONObject.NULL) null else kf.toString(),
          examTip = o.optString("examTip", ""),
        )
      }
    }.getOrDefault(emptyList())

  private fun encodeExamples(items: List<WorkedExample>): String =
    JSONArray().apply {
      items.forEach { ex ->
        put(
          JSONObject().apply {
            put("title", ex.title)
            put("examSource", ex.examSource)
            put("problemStatement", ex.problemStatement)
            put("steps", JSONArray(ex.steps))
            put("finalAnswer", ex.finalAnswer)
            put("examinerTrap", ex.examinerTrap)
          }
        )
      }
    }.toString()

  private fun decodeExamples(raw: String): List<WorkedExample> =
    runCatching {
      val arr = JSONArray(raw)
      List(arr.length()) { i ->
        val o = arr.getJSONObject(i)
        val stepsArr = o.optJSONArray("steps") ?: JSONArray()
        WorkedExample(
          title = o.optString("title", ""),
          examSource = o.optString("examSource", ""),
          problemStatement = o.optString("problemStatement", ""),
          steps = List(stepsArr.length()) { idx -> stepsArr.optString(idx, "") },
          finalAnswer = o.optString("finalAnswer", ""),
          examinerTrap = o.optString("examinerTrap", ""),
        )
      }
    }.getOrDefault(emptyList())

  private fun encodeScenarios(items: List<RealLifeScenario>): String =
    JSONArray().apply {
      items.forEach { s ->
        put(
          JSONObject().apply {
            put("title", s.title)
            put("industryDomain", s.industryDomain)
            put("narrative", s.narrative)
            put("examConnection", s.examConnection)
          }
        )
      }
    }.toString()

  private fun decodeScenarios(raw: String): List<RealLifeScenario> =
    runCatching {
      val arr = JSONArray(raw)
      List(arr.length()) { i ->
        val o = arr.getJSONObject(i)
        RealLifeScenario(
          title = o.optString("title", ""),
          industryDomain = o.optString("industryDomain", ""),
          narrative = o.optString("narrative", ""),
          examConnection = o.optString("examConnection", ""),
        )
      }
    }.getOrDefault(emptyList())

  private fun encodeQuestions(items: List<PracticeQuestion>): String =
    JSONArray().apply {
      items.forEach { q ->
        put(
          JSONObject().apply {
            put("id", q.id)
            put("topic", q.topic)
            put("examType", q.examType)
            put("year", q.year)
            put("question", q.question)
            put("options", JSONArray(q.options))
            put("correctIndex", q.correctIndex)
            put("hint", q.hint)
            put("stepByStepSolution", q.stepByStepSolution)
            put("waecMarkingScheme", q.waecMarkingScheme ?: JSONObject.NULL)
          }
        )
      }
    }.toString()

  private fun decodeQuestions(raw: String): List<PracticeQuestion> =
    runCatching {
      val arr = JSONArray(raw)
      List(arr.length()) { i ->
        val o = arr.getJSONObject(i)
        val optsArr = o.optJSONArray("options") ?: JSONArray()
        val scheme = o.opt("waecMarkingScheme")
        PracticeQuestion(
          id = o.optString("id", ""),
          topic = o.optString("topic", ""),
          examType = o.optString("examType", "JAMB"),
          year = o.optInt("year", 2023),
          question = o.optString("question", ""),
          options = List(optsArr.length()) { idx -> optsArr.optString(idx, "") },
          correctIndex = o.optInt("correctIndex", 0),
          hint = o.optString("hint", ""),
          stepByStepSolution = o.optString("stepByStepSolution", ""),
          waecMarkingScheme = if (scheme == null || scheme == JSONObject.NULL) null else scheme.toString(),
        )
      }
    }.getOrDefault(emptyList())
}
