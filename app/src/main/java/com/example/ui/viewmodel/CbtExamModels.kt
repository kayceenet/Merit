package com.example.ui.viewmodel

import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.PracticeQuestion

enum class CbtExamStage {
  SETUP,
  RUNNING,
  SUMMARY,
}

data class ComboSubjectBreakdown(
  val subjectId: String,
  val subjectName: String,
  val correctCount: Int,
  val totalQuestions: Int,
  val scaledOutOf100: Int,
)

data class QuestionReviewItem(
  val questionIndex: Int,
  val question: PracticeQuestion,
  val selectedOptionIndex: Int?,
  val timeSpentSeconds: Int,
  val subjectId: String = "",
) {
  val isCorrect: Boolean
    get() = selectedOptionIndex != null && selectedOptionIndex == question.correctIndex

  val isUnanswered: Boolean
    get() = selectedOptionIndex == null
}

data class ExamResultSummary(
  val examBoard: String,
  val subjectId: String,
  val subjectTitle: String,
  val correctCount: Int,
  val wrongCount: Int,
  val unansweredCount: Int,
  val totalQuestions: Int,
  val percentage: Int,
  val timeTakenSeconds: Int,
  val totalAllowedSeconds: Int,
  val xpEarned: Long,
  val weakTopics: List<String>,
  val reviewItems: List<QuestionReviewItem>,
  val isUtmeCombo: Boolean = false,
  val utmeAggregateOutOf400: Int? = null,
  val comboBreakdowns: List<ComboSubjectBreakdown> = emptyList(),
)

data class CbtExamState(
  val stage: CbtExamStage = CbtExamStage.SETUP,
  val selectedSubjectId: String = CurriculumCatalog.tracks.first().id,
  val selectedExamBoard: String = "JAMB",
  val selectedQuestionLimit: Int = 15,
  val isUtmeComboMode: Boolean = false,
  val selectedComboElectives: List<String> = listOf("mathematics", "physics", "chemistry"),
  val comboQuestionSubjects: List<String> = emptyList(),
  val comboSubjectTabs: List<String> = emptyList(),
  val activeComboSubjectTab: String? = null,
  val questions: List<PracticeQuestion> = emptyList(),
  val currentIndex: Int = 0,
  val selectedAnswers: Map<Int, Int> = emptyMap(),
  val flaggedQuestions: Set<Int> = emptySet(),
  val perQuestionSeconds: Map<Int, Int> = emptyMap(),
  val remainingSeconds: Int = 300,
  val elapsedSeconds: Int = 0,
  val totalAllowedSeconds: Int = 300,
  val isTimerPaused: Boolean = false,
  val isLoadingQuestions: Boolean = false,
  val resultSummary: ExamResultSummary? = null,
)
