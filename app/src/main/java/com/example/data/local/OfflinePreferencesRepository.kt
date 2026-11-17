package com.example.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

private val Context.meritPrepDataStore: DataStore<Preferences> by
  preferencesDataStore(name = "meritprep_offline_prefs")

enum class ThemeMode(val label: String) {
  SYSTEM("System"),
  LIGHT("Light"),
  DARK("Dark"),
}

data class ThemePreferences(
  val themeMode: ThemeMode = ThemeMode.SYSTEM,
  val useDynamicColor: Boolean = true,
)

data class CachedExamResultSnapshot(
  val subjectId: String,
  val subjectTitle: String,
  val examBoard: String,
  val correctCount: Int,
  val totalQuestions: Int,
  val percentage: Int,
  val timeTakenSeconds: Int,
  val timestampMs: Long,
)

data class OfflineProgressSnapshot(
  val completedLessonIds: Set<String> = emptySet(),
  val lastLessonId: String? = null,
  val lastExamSubjectId: String? = null,
  val offlineXp: Long = 0L,
  val offlineStreakDays: Long = 1L,
  val totalExamsTaken: Int = 0,
  val recentResults: List<CachedExamResultSnapshot> = emptyList(),
)

class OfflinePreferencesRepository(private val dataStore: DataStore<Preferences>) {

  constructor(context: Context) : this(context.applicationContext.meritPrepDataStore)

  val themePreferences: Flow<ThemePreferences> =
    dataStore.data.map { prefs ->
      val modeName = prefs[KEY_THEME_MODE] ?: ThemeMode.SYSTEM.name
      val mode =
        ThemeMode.entries.find { it.name == modeName } ?: ThemeMode.SYSTEM
      val dynamicColor = prefs[KEY_USE_DYNAMIC_COLOR] ?: true
      ThemePreferences(themeMode = mode, useDynamicColor = dynamicColor)
    }

  val offlineSnapshot: Flow<OfflineProgressSnapshot> =
    dataStore.data.map { prefs ->
      OfflineProgressSnapshot(
        completedLessonIds = prefs[KEY_COMPLETED_LESSON_IDS] ?: emptySet(),
        lastLessonId = prefs[KEY_LAST_LESSON_ID],
        lastExamSubjectId = prefs[KEY_LAST_EXAM_SUBJECT_ID],
        offlineXp = prefs[KEY_OFFLINE_XP] ?: 0L,
        offlineStreakDays = prefs[KEY_OFFLINE_STREAK_DAYS] ?: 1L,
        totalExamsTaken = prefs[KEY_TOTAL_EXAMS_TAKEN] ?: 0,
        recentResults = parseCachedResults(prefs[KEY_RECENT_EXAM_RESULTS_JSON]),
      )
    }

  suspend fun setThemeMode(mode: ThemeMode) {
    dataStore.edit { prefs -> prefs[KEY_THEME_MODE] = mode.name }
  }

  suspend fun toggleDarkTheme(currentlyDark: Boolean) {
    setThemeMode(if (currentlyDark) ThemeMode.LIGHT else ThemeMode.DARK)
  }

  suspend fun setUseDynamicColor(enabled: Boolean) {
    dataStore.edit { prefs -> prefs[KEY_USE_DYNAMIC_COLOR] = enabled }
  }

  suspend fun recordLessonSnapshot(
    lessonId: String,
    isCompleted: Boolean,
    totalXp: Long? = null,
    streakDays: Long? = null,
  ) {
    dataStore.edit { prefs ->
      prefs[KEY_LAST_LESSON_ID] = lessonId
      val currentSet = prefs[KEY_COMPLETED_LESSON_IDS]?.toMutableSet() ?: mutableSetOf()
      if (isCompleted) {
        currentSet.add(lessonId)
      }
      prefs[KEY_COMPLETED_LESSON_IDS] = currentSet
      if (totalXp != null) prefs[KEY_OFFLINE_XP] = totalXp
      if (streakDays != null) prefs[KEY_OFFLINE_STREAK_DAYS] = streakDays
    }
  }

  suspend fun syncProgressIds(completedIds: Set<String>, xp: Long, streakDays: Long) {
    dataStore.edit { prefs ->
      prefs[KEY_COMPLETED_LESSON_IDS] = completedIds
      prefs[KEY_OFFLINE_XP] = xp
      prefs[KEY_OFFLINE_STREAK_DAYS] = streakDays
    }
  }

  suspend fun recordExamResultSnapshot(snapshot: CachedExamResultSnapshot) {
    dataStore.edit { prefs ->
      prefs[KEY_LAST_EXAM_SUBJECT_ID] = snapshot.subjectId
      val prevCount = prefs[KEY_TOTAL_EXAMS_TAKEN] ?: 0
      prefs[KEY_TOTAL_EXAMS_TAKEN] = prevCount + 1
      val updatedList =
        (listOf(snapshot) + parseCachedResults(prefs[KEY_RECENT_EXAM_RESULTS_JSON])).take(12)
      prefs[KEY_RECENT_EXAM_RESULTS_JSON] = encodeCachedResults(updatedList)
    }
  }

  private fun parseCachedResults(raw: String?): List<CachedExamResultSnapshot> {
    if (raw.isNullOrBlank()) return emptyList()
    return runCatching {
      val array = JSONArray(raw)
      buildList {
        for (i in 0 until array.length()) {
          val obj = array.optJSONObject(i) ?: continue
          add(
            CachedExamResultSnapshot(
              subjectId = obj.optString("subjectId", ""),
              subjectTitle = obj.optString("subjectTitle", ""),
              examBoard = obj.optString("examBoard", "JAMB"),
              correctCount = obj.optInt("correctCount", 0),
              totalQuestions = obj.optInt("totalQuestions", 1),
              percentage = obj.optInt("percentage", 0),
              timeTakenSeconds = obj.optInt("timeTakenSeconds", 0),
              timestampMs = obj.optLong("timestampMs", 0L),
            )
          )
        }
      }
    }.getOrDefault(emptyList())
  }

  private fun encodeCachedResults(items: List<CachedExamResultSnapshot>): String {
    val array = JSONArray()
    items.forEach { item ->
      array.put(
        JSONObject().apply {
          put("subjectId", item.subjectId)
          put("subjectTitle", item.subjectTitle)
          put("examBoard", item.examBoard)
          put("correctCount", item.correctCount)
          put("totalQuestions", item.totalQuestions)
          put("percentage", item.percentage)
          put("timeTakenSeconds", item.timeTakenSeconds)
          put("timestampMs", item.timestampMs)
        }
      )
    }
    return array.toString()
  }

  private companion object {
    val KEY_THEME_MODE = stringPreferencesKey("theme_mode")
    val KEY_USE_DYNAMIC_COLOR = booleanPreferencesKey("use_dynamic_color")
    val KEY_COMPLETED_LESSON_IDS = stringSetPreferencesKey("completed_lesson_ids")
    val KEY_LAST_LESSON_ID = stringPreferencesKey("last_lesson_id")
    val KEY_LAST_EXAM_SUBJECT_ID = stringPreferencesKey("last_exam_subject_id")
    val KEY_OFFLINE_XP = longPreferencesKey("offline_xp")
    val KEY_OFFLINE_STREAK_DAYS = longPreferencesKey("offline_streak_days")
    val KEY_TOTAL_EXAMS_TAKEN = intPreferencesKey("total_exams_taken")
    val KEY_RECENT_EXAM_RESULTS_JSON = stringPreferencesKey("recent_exam_results_json")
  }
}
