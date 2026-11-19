package com.example.data.curriculum

object CurriculumCatalog {

  val tracks: List<SubjectTrack> =
    listOf(
      PhysicsCurriculum.track,
      MathematicsCurriculum.track,
      ChemistryCurriculum.track,
      BiologyCurriculum.track,
      EnglishCurriculum.track,
      EconomicsCurriculum.track,
      GovernmentCurriculum.track,
    )

  private val passageLookupByEntityId: Map<String, String> by lazy {
    buildMap {
      tracks.forEach { track ->
        val allQs = track.pastQuestions + track.allLessons.flatMap { it.exercises }
        allQs.forEach { q ->
          if (!q.passageText.isNullOrBlank()) {
            put("${track.id}_${q.id}", q.passageText)
            put(q.id, q.passageText)
          }
        }
      }
    }
  }

  fun passageForEntityId(entityId: String): String? = passageLookupByEntityId[entityId]

  fun trackById(subjectId: String): SubjectTrack? = tracks.find { it.id == subjectId }

  fun lessonById(lessonId: String): Lesson? =
    tracks.flatMap { it.allLessons }.find { it.id == lessonId }

  fun allLessons(): List<Lesson> = tracks.flatMap { it.allLessons }

  fun pastQuestionsFor(subjectId: String, examFilter: String): List<PracticeQuestion> {
    val track = trackById(subjectId) ?: return emptyList()
    val pool = track.pastQuestions + track.allLessons.flatMap { it.exercises }
    return if (examFilter == "ALL") {
      pool
    } else {
      pool.filter { it.examType.equals(examFilter, ignoreCase = true) }
    }
  }
}
