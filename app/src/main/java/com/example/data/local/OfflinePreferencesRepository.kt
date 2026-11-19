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
import kotlinx.coroutines.flow.first
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

enum class ThemePalette(
  val label: String,
  val tagline: String,
  val lightSwatchHex: Long,
  val darkSwatchHex: Long,
) {
  ROYAL_INDIGO("Royal Indigo", "Classic academic cobalt & sapphire", 0xFF0056D2, 0xFF60A5FA),
  EMERALD_SCHOLAR("Emerald Scholar", "Forest green focus & calm contrast", 0xFF047857, 0xFF34D399),
  WARM_AMBER("Warm Amber", "Cream study paper & amber accents", 0xFFB45309, 0xFFFBBF24),
  OLED_HIGH_CONTRAST("OLED Contrast", "Pure black & crisp AAA ink", 0xFF003E9B, 0xFF38BDF8),
  CRIMSON_DISTINCTION("Crimson Honours", "Deep burgundy & bold collegiate tone", 0xFF9F1239, 0xFFFB7185),
}

enum class FontSizeScale(val label: String, val multiplier: Float) {
  COMPACT("Small", 0.94f),
  STANDARD("Medium", 1.08f),
  LARGE("Large", 1.22f),
  EXTRA_LARGE("Extra Large", 1.36f),
}

enum class LineSpacingScale(val label: String, val multiplier: Float) {
  COMPACT("Compact", 1.28f),
  STANDARD("Standard", 1.44f),
  RELAXED("Relaxed", 1.64f),
}

data class ThemePreferences(
  val themeMode: ThemeMode = ThemeMode.SYSTEM,
  val themePalette: ThemePalette = ThemePalette.ROYAL_INDIGO,
  val highContrastBoost: Boolean = false,
  val useDynamicColor: Boolean = true,
  val fontSizeScale: FontSizeScale = FontSizeScale.STANDARD,
  val lineSpacingScale: LineSpacingScale = LineSpacingScale.STANDARD,
  val boldEquations: Boolean = false,
  val cbtSecondsPerQuestion: Int = 60,
  val cbtAutoAdvance: Boolean = false,
  val cbtShowCalculator: Boolean = true,
  val shuffleOptionsOnRetry: Boolean = true,
  val masteryPassPercent: Int = 80,
  val dailyXpTarget: Int = 100,
  val spacedReviewBatchSize: Int = 10,
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
  val placementPassedSubjects: Set<String> = emptySet(),
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
      val paletteName = prefs[KEY_THEME_PALETTE] ?: ThemePalette.ROYAL_INDIGO.name
      val palette =
        ThemePalette.entries.find { it.name == paletteName } ?: ThemePalette.ROYAL_INDIGO
      val highContrast = prefs[KEY_HIGH_CONTRAST_BOOST] ?: false
      val dynamicColor = prefs[KEY_USE_DYNAMIC_COLOR] ?: true
      val fontScaleName = prefs[KEY_FONT_SCALE] ?: FontSizeScale.STANDARD.name
      val fontScale =
        FontSizeScale.entries.find { it.name == fontScaleName } ?: FontSizeScale.STANDARD
      val lineSpacingName = prefs[KEY_LINE_SPACING_SCALE] ?: LineSpacingScale.STANDARD.name
      val lineSpacing =
        LineSpacingScale.entries.find { it.name == lineSpacingName } ?: LineSpacingScale.STANDARD
      ThemePreferences(
        themeMode = mode,
        themePalette = palette,
        highContrastBoost = highContrast,
        useDynamicColor = dynamicColor,
        fontSizeScale = fontScale,
        lineSpacingScale = lineSpacing,
        boldEquations = prefs[KEY_BOLD_EQUATIONS] ?: false,
        cbtSecondsPerQuestion = (prefs[KEY_CBT_SECONDS_PER_Q] ?: 60).coerceIn(30, 120),
        cbtAutoAdvance = prefs[KEY_CBT_AUTO_ADVANCE] ?: false,
        cbtShowCalculator = prefs[KEY_CBT_SHOW_CALCULATOR] ?: true,
        shuffleOptionsOnRetry = prefs[KEY_SHUFFLE_OPTIONS_ON_RETRY] ?: true,
        masteryPassPercent = (prefs[KEY_MASTERY_PASS_PERCENT] ?: 80).coerceIn(65, 95),
        dailyXpTarget = (prefs[KEY_DAILY_XP_TARGET] ?: 100).coerceIn(50, 500),
        spacedReviewBatchSize = (prefs[KEY_SPACED_REVIEW_BATCH_SIZE] ?: 10).coerceIn(5, 30),
      )
    }

  val offlineSnapshot: Flow<OfflineProgressSnapshot> =
    dataStore.data.map { prefs ->
      OfflineProgressSnapshot(
        completedLessonIds = prefs[KEY_COMPLETED_LESSON_IDS] ?: emptySet(),
        placementPassedSubjects = prefs[KEY_PLACEMENT_PASSED_SUBJECTS] ?: emptySet(),
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

  suspend fun setThemePalette(palette: ThemePalette) {
    dataStore.edit { prefs -> prefs[KEY_THEME_PALETTE] = palette.name }
  }

  suspend fun setHighContrastBoost(enabled: Boolean) {
    dataStore.edit { prefs -> prefs[KEY_HIGH_CONTRAST_BOOST] = enabled }
  }

  suspend fun setUseDynamicColor(enabled: Boolean) {
    dataStore.edit { prefs -> prefs[KEY_USE_DYNAMIC_COLOR] = enabled }
  }

  suspend fun setFontSizeScale(scale: FontSizeScale) {
    dataStore.edit { prefs -> prefs[KEY_FONT_SCALE] = scale.name }
  }

  suspend fun setLineSpacingScale(scale: LineSpacingScale) {
    dataStore.edit { prefs -> prefs[KEY_LINE_SPACING_SCALE] = scale.name }
  }

  suspend fun setBoldEquations(enabled: Boolean) {
    dataStore.edit { prefs -> prefs[KEY_BOLD_EQUATIONS] = enabled }
  }

  suspend fun setCbtSecondsPerQuestion(seconds: Int) {
    dataStore.edit { prefs -> prefs[KEY_CBT_SECONDS_PER_Q] = seconds.coerceIn(30, 120) }
  }

  suspend fun setCbtAutoAdvance(enabled: Boolean) {
    dataStore.edit { prefs -> prefs[KEY_CBT_AUTO_ADVANCE] = enabled }
  }

  suspend fun setCbtShowCalculator(enabled: Boolean) {
    dataStore.edit { prefs -> prefs[KEY_CBT_SHOW_CALCULATOR] = enabled }
  }

  suspend fun setShuffleOptionsOnRetry(enabled: Boolean) {
    dataStore.edit { prefs -> prefs[KEY_SHUFFLE_OPTIONS_ON_RETRY] = enabled }
  }

  suspend fun setMasteryPassPercent(percent: Int) {
    dataStore.edit { prefs -> prefs[KEY_MASTERY_PASS_PERCENT] = percent.coerceIn(65, 95) }
  }

  suspend fun setDailyXpTarget(xpTarget: Int) {
    dataStore.edit { prefs -> prefs[KEY_DAILY_XP_TARGET] = xpTarget.coerceIn(50, 500) }
  }

  suspend fun setSpacedReviewBatchSize(batchSize: Int) {
    dataStore.edit { prefs -> prefs[KEY_SPACED_REVIEW_BATCH_SIZE] = batchSize.coerceIn(5, 30) }
  }

  suspend fun getCurriculumVersion(): Int = dataStore.data.first()[KEY_CURRICULUM_VERSION] ?: 0

  suspend fun setCurriculumVersion(version: Int) {
    dataStore.edit { prefs -> prefs[KEY_CURRICULUM_VERSION] = version }
  }

  suspend fun recordPlacementPassed(subjectId: String) {
    dataStore.edit { prefs ->
      val current = prefs[KEY_PLACEMENT_PASSED_SUBJECTS]?.toMutableSet() ?: mutableSetOf()
      current.add(subjectId)
      prefs[KEY_PLACEMENT_PASSED_SUBJECTS] = current
    }
  }

  suspend fun syncPlacementPassedSubjects(subjectIds: Set<String>) {
    dataStore.edit { prefs ->
      prefs[KEY_PLACEMENT_PASSED_SUBJECTS] = subjectIds
    }
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
    val KEY_THEME_PALETTE = stringPreferencesKey("theme_palette")
    val KEY_HIGH_CONTRAST_BOOST = booleanPreferencesKey("high_contrast_boost")
    val KEY_USE_DYNAMIC_COLOR = booleanPreferencesKey("use_dynamic_color")
    val KEY_FONT_SCALE = stringPreferencesKey("font_scale")
    val KEY_LINE_SPACING_SCALE = stringPreferencesKey("line_spacing_scale")
    val KEY_BOLD_EQUATIONS = booleanPreferencesKey("bold_equations")
    val KEY_CBT_SECONDS_PER_Q = intPreferencesKey("cbt_seconds_per_q")
    val KEY_CBT_AUTO_ADVANCE = booleanPreferencesKey("cbt_auto_advance")
    val KEY_CBT_SHOW_CALCULATOR = booleanPreferencesKey("cbt_show_calculator")
    val KEY_SHUFFLE_OPTIONS_ON_RETRY = booleanPreferencesKey("shuffle_options_on_retry")
    val KEY_MASTERY_PASS_PERCENT = intPreferencesKey("mastery_pass_percent")
    val KEY_DAILY_XP_TARGET = intPreferencesKey("daily_xp_target")
    val KEY_SPACED_REVIEW_BATCH_SIZE = intPreferencesKey("spaced_review_batch_size")
    val KEY_COMPLETED_LESSON_IDS = stringSetPreferencesKey("completed_lesson_ids")
    val KEY_PLACEMENT_PASSED_SUBJECTS = stringSetPreferencesKey("placement_passed_subjects")
    val KEY_LAST_LESSON_ID = stringPreferencesKey("last_lesson_id")
    val KEY_LAST_EXAM_SUBJECT_ID = stringPreferencesKey("last_exam_subject_id")
    val KEY_OFFLINE_XP = longPreferencesKey("offline_xp")
    val KEY_OFFLINE_STREAK_DAYS = longPreferencesKey("offline_streak_days")
    val KEY_TOTAL_EXAMS_TAKEN = intPreferencesKey("total_exams_taken")
    val KEY_RECENT_EXAM_RESULTS_JSON = stringPreferencesKey("recent_exam_results_json")
    val KEY_CURRICULUM_VERSION = intPreferencesKey("curriculum_version")
  }
}
