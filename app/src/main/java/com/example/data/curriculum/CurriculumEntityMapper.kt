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
        mod.lessons.mapIndexed { idx, rawLesson ->
          val lesson = AcademyCurriculumEnricher.enrich(rawLesson)
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
            scenariosJson = encodeScenarioEnvelope(lesson),
            exercisesJson = encodeQuestions(lesson.exercises),
          )
        }
      }
    }

  fun buildPastQuestionEntities(): List<PastQuestionEntity> =
    CurriculumCatalog.tracks.flatMap { track ->
      val extra = ExpandedPastQuestionBank.extraBySubject[track.id].orEmpty()
      val combined = (track.pastQuestions + extra + track.allLessons.flatMap { it.exercises }).distinctBy { it.id }
      combined.mapIndexed { idx, raw ->
        val q = AcademyCurriculumEnricher.sanitizeQuestion(raw, idx)
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
    val envelope = decodeScenarioEnvelope(entity.scenariosJson)
    val base =
      Lesson(
        id = entity.id,
        moduleId = entity.moduleId,
        subjectId = entity.subjectId,
        title = entity.title,
        durationMinutes = entity.durationMinutes,
        syllabusTag = entity.syllabusTag,
        learningOutcomes = decodeStringList(entity.learningOutcomesJson),
        concepts = decodeConcepts(entity.conceptsJson),
        workedExamples = decodeExamples(entity.workedExamplesJson),
        scenarios = envelope.scenarios,
        labType = labType,
        labBrief = entity.labBrief,
        exercises = decodeQuestions(entity.exercisesJson),
        prerequisites = envelope.prerequisites,
        dependencySummary = envelope.dependencySummary,
        vocabulary = envelope.vocabulary,
        misconceptions = envelope.misconceptions,
        guidedPractice = envelope.guidedPractice,
        masteryProject = envelope.masteryProject,
        cheatSheet = envelope.cheatSheet,
        externalResources = envelope.externalResources,
      )
    return AcademyCurriculumEnricher.enrich(base)
  }

  fun toDomainQuestion(entity: PastQuestionEntity): PracticeQuestion =
    AcademyCurriculumEnricher.sanitizeQuestion(
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
        passageText = CurriculumCatalog.passageForEntityId(entity.id),
        diagramSpec = CurriculumCatalog.diagramSpecForEntityId(entity.id),
      )
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
            put("mechanismWhyHow", c.mechanismWhyHow)
            put("examIntelligence", c.examIntelligence)
            put("recap", c.recap)
            put("animationKey", c.animationKey)
            put(
              "microDrills",
              JSONArray().apply {
                c.microDrills.forEach { d ->
                  put(
                    JSONObject().apply {
                      put("prompt", d.prompt)
                      put("options", JSONArray(d.options))
                      put("correctIndex", d.correctIndex)
                      put("hint", d.hint)
                      put("explanation", d.explanation)
                    }
                  )
                }
              },
            )
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
        val drillsArr = o.optJSONArray("microDrills") ?: JSONArray()
        val drills =
          List(drillsArr.length()) { idx ->
            val d = drillsArr.getJSONObject(idx)
            MicroDrill(
              prompt = d.optString("prompt", ""),
              options = jsonArrayToStrings(d.optJSONArray("options")),
              correctIndex = d.optInt("correctIndex", 0),
              hint = d.optString("hint", ""),
              explanation = d.optString("explanation", ""),
            )
          }
        ConceptBlock(
          heading = o.optString("heading", ""),
          explanation = o.optString("explanation", ""),
          keyFormula = if (kf == null || kf == JSONObject.NULL) null else kf.toString(),
          examTip = o.optString("examTip", ""),
          mechanismWhyHow = o.optString("mechanismWhyHow", ""),
          examIntelligence = o.optString("examIntelligence", ""),
          recap = o.optString("recap", ""),
          animationKey = o.optString("animationKey", ""),
          microDrills = drills,
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

  private data class ScenarioEnvelope(
    val scenarios: List<RealLifeScenario>,
    val prerequisites: List<String> = emptyList(),
    val dependencySummary: String = "",
    val vocabulary: List<VocabularyEntry> = emptyList(),
    val misconceptions: List<MisconceptionAnalysis> = emptyList(),
    val guidedPractice: GuidedPracticeTask? = null,
    val masteryProject: TopicMasteryProject? = null,
    val cheatSheet: TopicCheatSheet? = null,
    val externalResources: List<VerifiedExternalResource> = emptyList(),
  )

  private fun encodeScenarioEnvelope(lesson: Lesson): String =
    JSONObject().apply {
      put(
        "scenarios",
        JSONArray().apply {
          lesson.scenarios.forEach { s ->
            put(
              JSONObject().apply {
                put("title", s.title)
                put("industryDomain", s.industryDomain)
                put("narrative", s.narrative)
                put("examConnection", s.examConnection)
              }
            )
          }
        },
      )
      put("prerequisites", JSONArray(lesson.prerequisites))
      put("dependencySummary", lesson.dependencySummary)
      put(
        "vocabulary",
        JSONArray().apply {
          lesson.vocabulary.forEach { v ->
            put(
              JSONObject().apply {
                put("term", v.term)
                put("definition", v.definition)
                put("simpleMeaning", v.simpleMeaning)
                put("examUsage", v.examUsage)
              }
            )
          }
        },
      )
      put(
        "misconceptions",
        JSONArray().apply {
          lesson.misconceptions.forEach { m ->
            put(
              JSONObject().apply {
                put("wrongBelief", m.wrongBelief)
                put("whyStudentsBelieveIt", m.whyStudentsBelieveIt)
                put("correctUnderstanding", m.correctUnderstanding)
                put("avoidanceRule", m.avoidanceRule)
              }
            )
          }
        },
      )
      val gp = lesson.guidedPractice
      if (gp != null) {
        put(
          "guidedPractice",
          JSONObject().apply {
            put("prompt", gp.prompt)
            put("difficulty", gp.difficulty)
            put("estimatedMinutes", gp.estimatedMinutes)
            put("hints", JSONArray(gp.hints))
            put("expectedApproach", gp.expectedApproach)
            put("completeSolution", gp.completeSolution)
          },
        )
      }
      val mp = lesson.masteryProject
      if (mp != null) {
        put(
          "masteryProject",
          JSONObject().apply {
            put("title", mp.title)
            put("format", mp.format)
            put("nigerianScenario", mp.nigerianScenario)
            put("stages", JSONArray(mp.stages))
            put("rubricCriteria", JSONArray(mp.rubricCriteria))
            put("modelResponse", mp.modelResponse)
            put(
              "verificationTasks",
              JSONArray().apply {
                mp.verificationTasks.forEach { task ->
                  put(
                    JSONObject().apply {
                      put("id", task.id)
                      put("criterionTitle", task.criterionTitle)
                      put("prompt", task.prompt)
                      put("options", JSONArray(task.options))
                      put("correctIndex", task.correctIndex)
                      put("feedback", task.feedback)
                    }
                  )
                }
              },
            )
          },
        )
      }
      val cs = lesson.cheatSheet
      if (cs != null) {
        put(
          "cheatSheet",
          JSONObject().apply {
            put("overview", cs.overview)
            put("symbolLegend", JSONArray(cs.symbolLegend))
            put("commonTraps", JSONArray(cs.commonTraps))
            put("memoryAid", cs.memoryAid)
            put("spacedRevisionDays", JSONArray(cs.spacedRevisionDays))
          },
        )
      }
      if (lesson.externalResources.isNotEmpty()) {
        put(
          "externalResources",
          JSONArray().apply {
            lesson.externalResources.forEach { res ->
              put(
                JSONObject().apply {
                  put("title", res.title)
                  put("provider", res.provider)
                  put("kind", res.kind)
                  put("url", res.url)
                  put("description", res.description)
                }
              )
            }
          },
        )
      }
    }.toString()

  private fun decodeScenarioEnvelope(raw: String): ScenarioEnvelope =
    runCatching {
      val trimmed = raw.trim()
      if (trimmed.startsWith("[")) {
        return ScenarioEnvelope(scenarios = parseScenarioArray(JSONArray(trimmed)))
      }
      val obj = JSONObject(trimmed)
      val scenarios = parseScenarioArray(obj.optJSONArray("scenarios") ?: JSONArray())
      val prerequisites = jsonArrayToStrings(obj.optJSONArray("prerequisites"))
      val dependencySummary = obj.optString("dependencySummary", "")
      val vocabArr = obj.optJSONArray("vocabulary") ?: JSONArray()
      val vocab =
        List(vocabArr.length()) { i ->
          val v = vocabArr.getJSONObject(i)
          VocabularyEntry(
            term = v.optString("term", ""),
            definition = v.optString("definition", ""),
            simpleMeaning = v.optString("simpleMeaning", ""),
            examUsage = v.optString("examUsage", ""),
          )
        }
      val miscArr = obj.optJSONArray("misconceptions") ?: JSONArray()
      val misconceptions =
        List(miscArr.length()) { i ->
          val m = miscArr.getJSONObject(i)
          MisconceptionAnalysis(
            wrongBelief = m.optString("wrongBelief", ""),
            whyStudentsBelieveIt = m.optString("whyStudentsBelieveIt", ""),
            correctUnderstanding = m.optString("correctUnderstanding", ""),
            avoidanceRule = m.optString("avoidanceRule", ""),
          )
        }
      val gpObj = obj.optJSONObject("guidedPractice")
      val gp =
        gpObj?.let {
          GuidedPracticeTask(
            prompt = it.optString("prompt", ""),
            difficulty = it.optString("difficulty", "Intermediate"),
            estimatedMinutes = it.optInt("estimatedMinutes", 5),
            hints = jsonArrayToStrings(it.optJSONArray("hints")),
            expectedApproach = it.optString("expectedApproach", ""),
            completeSolution = it.optString("completeSolution", ""),
          )
        }
      val mpObj = obj.optJSONObject("masteryProject")
      val mp =
        mpObj?.let {
          val vtArr = it.optJSONArray("verificationTasks") ?: JSONArray()
          val vTasks =
            List(vtArr.length()) { idx ->
              val v = vtArr.getJSONObject(idx)
              ProjectVerificationTask(
                id = v.optString("id", "vt_$idx"),
                criterionTitle = v.optString("criterionTitle", ""),
                prompt = v.optString("prompt", ""),
                options = jsonArrayToStrings(v.optJSONArray("options")),
                correctIndex = v.optInt("correctIndex", 0),
                feedback = v.optString("feedback", ""),
              )
            }
          TopicMasteryProject(
            title = it.optString("title", ""),
            format = it.optString("format", "Applied Investigation"),
            nigerianScenario = it.optString("nigerianScenario", ""),
            stages = jsonArrayToStrings(it.optJSONArray("stages")),
            rubricCriteria = jsonArrayToStrings(it.optJSONArray("rubricCriteria")),
            modelResponse = it.optString("modelResponse", ""),
            verificationTasks = vTasks,
          )
        }
      val csObj = obj.optJSONObject("cheatSheet")
      val cs =
        csObj?.let {
          val daysArr = it.optJSONArray("spacedRevisionDays")
          val days =
            if (daysArr == null) listOf(1, 3, 7, 14, 30)
            else List(daysArr.length()) { idx -> daysArr.optInt(idx, 1) }
          TopicCheatSheet(
            overview = it.optString("overview", ""),
            symbolLegend = jsonArrayToStrings(it.optJSONArray("symbolLegend")),
            commonTraps = jsonArrayToStrings(it.optJSONArray("commonTraps")),
            memoryAid = it.optString("memoryAid", ""),
            spacedRevisionDays = days,
          )
        }
      val extArr = obj.optJSONArray("externalResources") ?: JSONArray()
      val externalResources =
        List(extArr.length()) { idx ->
          val e = extArr.getJSONObject(idx)
          VerifiedExternalResource(
            title = e.optString("title", ""),
            provider = e.optString("provider", ""),
            kind = e.optString("kind", ""),
            url = e.optString("url", ""),
            description = e.optString("description", ""),
          )
        }
      ScenarioEnvelope(
        scenarios = scenarios,
        prerequisites = prerequisites,
        dependencySummary = dependencySummary,
        vocabulary = vocab,
        misconceptions = misconceptions,
        guidedPractice = gp,
        masteryProject = mp,
        cheatSheet = cs,
        externalResources = externalResources,
      )
    }.getOrDefault(ScenarioEnvelope(emptyList()))

  private fun parseScenarioArray(arr: JSONArray): List<RealLifeScenario> =
    List(arr.length()) { i ->
      val o = arr.getJSONObject(i)
      RealLifeScenario(
        title = o.optString("title", ""),
        industryDomain = o.optString("industryDomain", ""),
        narrative = o.optString("narrative", ""),
        examConnection = o.optString("examConnection", ""),
      )
    }

  private fun jsonArrayToStrings(arr: JSONArray?): List<String> =
    if (arr == null) emptyList() else List(arr.length()) { idx -> arr.optString(idx, "") }

  private fun encodeQuestions(items: List<PracticeQuestion>): String =
    JSONArray().apply {
      items.forEach { raw ->
        val q = AcademyCurriculumEnricher.sanitizeQuestion(raw)
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
            put("cognitiveLevel", q.cognitiveLevel)
            put("marks", q.marks)
            put("remediationTip", q.remediationTip)
            put("passageText", q.passageText ?: JSONObject.NULL)
            put("diagramSpec", q.diagramSpec?.name ?: JSONObject.NULL)
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
        val passage = o.opt("passageText")
        val specRaw = o.opt("diagramSpec")
        val qId = o.optString("id", "")
        val parsedSpec =
          if (specRaw == null || specRaw == JSONObject.NULL) CurriculumCatalog.diagramSpecForEntityId(qId)
          else QuestionDiagramSpec.entries.find { it.name == specRaw.toString() }
        AcademyCurriculumEnricher.sanitizeQuestion(
          PracticeQuestion(
            id = qId,
            topic = o.optString("topic", ""),
            examType = o.optString("examType", "JAMB"),
            year = o.optInt("year", 2023),
            question = o.optString("question", ""),
            options = List(optsArr.length()) { idx -> optsArr.optString(idx, "") },
            correctIndex = o.optInt("correctIndex", 0),
            hint = o.optString("hint", ""),
            stepByStepSolution = o.optString("stepByStepSolution", ""),
            waecMarkingScheme = if (scheme == null || scheme == JSONObject.NULL) null else scheme.toString(),
            cognitiveLevel = o.optString("cognitiveLevel", "Application"),
            marks = o.optInt("marks", 20),
            remediationTip = o.optString("remediationTip", ""),
            passageText =
              if (passage == null || passage == JSONObject.NULL) CurriculumCatalog.passageForEntityId(qId)
              else passage.toString(),
            diagramSpec = parsedSpec,
          )
        )
      }
    }.getOrDefault(emptyList())
}

