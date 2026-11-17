package com.example.data.curriculum

object CurriculumCatalog {

  val tracks: List<SubjectTrack> =
    listOf(
      PhysicsCurriculum.track,
      MathematicsCurriculum.track,
      ChemistryCurriculum.track,
      BiologyCurriculum.track,
      EnglishCurriculum.track,
    )

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
