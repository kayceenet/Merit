package com.example.data.curriculum

import kotlin.random.Random

/**
 * Prepares fresh, unrepeated practice & CBT mock questions on every retry,
 * reshuffling both question ordering and option positions (A-D) while preserving
 * the accurate [PracticeQuestion.correctIndex].
 */
object QuestionShuffler {

  fun shuffleOptions(
    question: PracticeQuestion,
    random: Random = Random.Default,
  ): PracticeQuestion {
    if (question.options.size <= 1 || question.correctIndex !in question.options.indices) {
      return question
    }
    val indexed = question.options.mapIndexed { idx, text -> idx to text }.shuffled(random)
    val updatedCorrectIndex = indexed.indexOfFirst { it.first == question.correctIndex }.coerceAtLeast(0)
    return question.copy(
      options = indexed.map { it.second },
      correctIndex = updatedCorrectIndex,
    )
  }

  fun prepareFreshQuestionSet(
    pool: List<PracticeQuestion>,
    count: Int,
    previouslySeenIds: Set<String> = emptySet(),
    lastRoundIds: Set<String> = emptySet(),
    random: Random = Random.Default,
  ): List<PracticeQuestion> {
    if (pool.isEmpty()) return emptyList()
    val targetCount = if (count <= 0) pool.size else minOf(count, pool.size)

    val unseen = pool.filter { it.id !in previouslySeenIds }.shuffled(random)
    val picked =
      if (unseen.size >= targetCount) {
        unseen.take(targetCount)
      } else {
        val seenOlder =
          pool.filter { it.id in previouslySeenIds && it.id !in lastRoundIds }.shuffled(random)
        val seenLastRound =
          pool.filter { it.id in previouslySeenIds && it.id in lastRoundIds }.shuffled(random)
        (unseen + seenOlder + seenLastRound)
          .distinctBy { it.id }
          .take(targetCount)
      }

    return picked.map { shuffleOptions(it, random) }
  }
}
