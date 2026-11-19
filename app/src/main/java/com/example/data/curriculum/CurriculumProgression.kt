package com.example.data.curriculum

import com.example.data.model.LessonProgress

enum class MasteryBand(val label: String, val passesGate: Boolean, val summary: String) {
  NOT_YET_COMPETENT(
    label = "Not Yet Competent (<60%)",
    passesGate = false,
    summary = "Foundational concepts need immediate guided re-teaching before retrying.",
  ),
  DEVELOPING(
    label = "Developing (60–69%)",
    passesGate = false,
    summary = "Partial understanding shown, but procedural accuracy is below mastery.",
  ),
  APPROACHING_MASTERY(
    label = "Approaching Mastery (70–79%)",
    passesGate = false,
    summary = "Close to readiness—clear the remaining weak competency to reach 80%.",
  ),
  MASTERED(
    label = "Mastered (80–89%)",
    passesGate = true,
    summary = "Core and applied competencies demonstrated cleanly at Academy standard.",
  ),
  STRONG_MASTERY(
    label = "Strong Mastery (90–100%)",
    passesGate = true,
    summary = "Independent mastery across recall, application, and unfamiliar analysis.",
  ),
}

data class LessonUnlockState(
  val isUnlocked: Boolean,
  val isMastered: Boolean,
  val prerequisiteLessonTitle: String?,
  val completedPillarsCount: Int,
  val exerciseScore: Long,
) {
  val totalPillars: Int = LessonPillar.entries.size
  val progressRatio: Float
    get() = (completedPillarsCount / totalPillars.toFloat()).coerceIn(0f, 1f)
}

object CurriculumProgression {
  const val PASS_THRESHOLD_PERCENT: Long = 80L

  fun bandForPercentage(percentage: Long): MasteryBand =
    when {
      percentage >= 90L -> MasteryBand.STRONG_MASTERY
      percentage >= 80L -> MasteryBand.MASTERED
      percentage >= 70L -> MasteryBand.APPROACHING_MASTERY
      percentage >= 60L -> MasteryBand.DEVELOPING
      else -> MasteryBand.NOT_YET_COMPETENT
    }

  fun isLessonMastered(
    progress: LessonProgress?,
    passThresholdPercent: Long = PASS_THRESHOLD_PERCENT,
  ): Boolean {
    if (progress == null) return false
    return progress.isCompleted ||
      (progress.exerciseScore >= passThresholdPercent &&
        LessonPillar.entries.all { it.key in progress.completedSteps })
  }

  fun unlockStateForLesson(
    orderedTrackLessons: List<Lesson>,
    lessonId: String,
    progressMap: Map<String, LessonProgress>,
    placementPassedSubjects: Set<String> = emptySet(),
    passThresholdPercent: Long = PASS_THRESHOLD_PERCENT,
  ): LessonUnlockState {
    val index = orderedTrackLessons.indexOfFirst { it.id == lessonId }
    val prog = progressMap[lessonId]
    val completedCount = prog?.completedSteps?.distinct()?.size ?: 0
    val mastered = isLessonMastered(prog, passThresholdPercent)
    if (index <= 0) {
      return LessonUnlockState(
        isUnlocked = true,
        isMastered = mastered,
        prerequisiteLessonTitle = null,
        completedPillarsCount = completedCount,
        exerciseScore = prog?.exerciseScore ?: 0L,
      )
    }
    val lessonSubjectId = orderedTrackLessons[index].subjectId
    val placementUnlockLimit = ((orderedTrackLessons.size * 3) / 5).coerceAtLeast(1)
    val unlockedViaPlacement =
      lessonSubjectId in placementPassedSubjects && index <= placementUnlockLimit
    val previousLesson = orderedTrackLessons[index - 1]
    val prevMastered = isLessonMastered(progressMap[previousLesson.id], passThresholdPercent)
    return LessonUnlockState(
      isUnlocked = prevMastered || mastered || unlockedViaPlacement,
      isMastered = mastered,
      prerequisiteLessonTitle = previousLesson.title,
      completedPillarsCount = completedCount,
      exerciseScore = prog?.exerciseScore ?: 0L,
    )
  }

  fun isPillarUnlocked(pillar: LessonPillar, completedSteps: Set<String>): Boolean {
    val idx = LessonPillar.entries.indexOf(pillar)
    if (idx <= 0) return true
    val previousPillar = LessonPillar.entries[idx - 1]
    return previousPillar.key in completedSteps || pillar.key in completedSteps
  }

  fun nextUnlockedLesson(
    orderedTrackLessons: List<Lesson>,
    progressMap: Map<String, LessonProgress>,
    placementPassedSubjects: Set<String> = emptySet(),
    passThresholdPercent: Long = PASS_THRESHOLD_PERCENT,
  ): Lesson? {
    if (orderedTrackLessons.isEmpty()) return null
    val firstIncomplete =
      orderedTrackLessons.firstOrNull { lesson ->
        val state =
          unlockStateForLesson(
            orderedTrackLessons = orderedTrackLessons,
            lessonId = lesson.id,
            progressMap = progressMap,
            placementPassedSubjects = placementPassedSubjects,
            passThresholdPercent = passThresholdPercent,
          )
        state.isUnlocked && !state.isMastered
      }
    return firstIncomplete ?: orderedTrackLessons.first()
  }

  fun nextLessonAfter(
    orderedTrackLessons: List<Lesson>,
    currentLessonId: String,
  ): Lesson? {
    val idx = orderedTrackLessons.indexOfFirst { it.id == currentLessonId }
    return if (idx in 0 until orderedTrackLessons.lastIndex) orderedTrackLessons[idx + 1] else null
  }

  fun prerequisiteLessonFor(
    orderedTrackLessons: List<Lesson>,
    lessonId: String,
  ): Lesson? {
    val idx = orderedTrackLessons.indexOfFirst { it.id == lessonId }
    return if (idx > 0) orderedTrackLessons[idx - 1] else null
  }
}

