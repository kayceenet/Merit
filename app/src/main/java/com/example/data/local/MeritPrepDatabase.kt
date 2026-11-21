package com.example.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.ActiveCbtSessionEntity
import com.example.data.model.ExamAttempt
import com.example.data.model.LearningChapterEntity
import com.example.data.model.LearningModuleEntity
import com.example.data.model.LessonProgress
import com.example.data.model.Paper2AttemptEntity
import com.example.data.model.PastQuestionEntity
import com.example.data.model.SpacedReviewCardEntity
import com.example.data.model.StudyNote
import com.example.data.model.UserProfile
import kotlinx.coroutines.flow.Flow
import org.json.JSONArray

class StringListConverters {
  @TypeConverter
  fun fromStringList(value: List<String>): String {
    val array = JSONArray()
    value.forEach { array.put(it) }
    return array.toString()
  }

  @TypeConverter
  fun toStringList(value: String): List<String> {
    if (value.isBlank()) return emptyList()
    return runCatching {
      val array = JSONArray(value)
      List(array.length()) { index -> array.optString(index, "") }.filter { it.isNotEmpty() }
    }.getOrDefault(emptyList())
  }
}

@Dao
interface MeritPrepDao {
  @Query("SELECT * FROM user_profiles WHERE userId = :userId LIMIT 1")
  fun observeProfile(userId: String): Flow<UserProfile?>

  @Query("SELECT * FROM user_profiles WHERE userId = :userId LIMIT 1")
  suspend fun getProfileOnce(userId: String): UserProfile?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertProfile(profile: UserProfile)

  @Query("SELECT * FROM lesson_progress WHERE userId = :userId")
  fun observeLessonProgress(userId: String): Flow<List<LessonProgress>>

  @Query("SELECT * FROM lesson_progress WHERE userId = :userId")
  suspend fun getAllLessonProgressOnce(userId: String): List<LessonProgress>

  @Query("SELECT * FROM lesson_progress WHERE id = :id LIMIT 1")
  suspend fun getLessonProgressOnce(id: String): LessonProgress?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertLessonProgress(progress: LessonProgress)

  @Query("SELECT * FROM exam_attempts WHERE userId = :userId ORDER BY createdAt DESC")
  fun observeExamAttempts(userId: String): Flow<List<ExamAttempt>>

  @Query("SELECT * FROM exam_attempts WHERE userId = :userId ORDER BY createdAt DESC")
  suspend fun getAllExamAttemptsOnce(userId: String): List<ExamAttempt>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertExamAttempt(attempt: ExamAttempt)

  @Query("SELECT * FROM study_notes WHERE userId = :userId ORDER BY updatedAt DESC, createdAt DESC")
  fun observeStudyNotes(userId: String): Flow<List<StudyNote>>

  @Query("SELECT * FROM study_notes WHERE userId = :userId ORDER BY updatedAt DESC, createdAt DESC")
  suspend fun getAllStudyNotesOnce(userId: String): List<StudyNote>

  @Query("SELECT * FROM study_notes WHERE id = :id LIMIT 1")
  suspend fun getStudyNoteOnce(id: String): StudyNote?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertStudyNote(note: StudyNote)

  @Query("DELETE FROM study_notes WHERE id = :id AND userId = :userId")
  suspend fun deleteStudyNote(userId: String, id: String)

  // Spaced Repetition Active Recall Deck
  @Query(
    "SELECT * FROM spaced_review_cards WHERE userId = :userId AND dueAtMillis <= :nowMillis ORDER BY dueAtMillis ASC LIMIT :limit"
  )
  fun observeDueReviewCards(
    userId: String,
    nowMillis: Long,
    limit: Int,
  ): Flow<List<SpacedReviewCardEntity>>

  @Query("SELECT * FROM spaced_review_cards WHERE userId = :userId ORDER BY dueAtMillis ASC")
  fun observeAllReviewCards(userId: String): Flow<List<SpacedReviewCardEntity>>

  @Query("SELECT * FROM spaced_review_cards WHERE userId = :userId ORDER BY dueAtMillis ASC")
  suspend fun getAllReviewCardsOnce(userId: String): List<SpacedReviewCardEntity>

  @Query("SELECT * FROM spaced_review_cards WHERE id = :id LIMIT 1")
  suspend fun getReviewCardOnce(id: String): SpacedReviewCardEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertReviewCard(card: SpacedReviewCardEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertReviewCards(cards: List<SpacedReviewCardEntity>)

  @Query("DELETE FROM spaced_review_cards WHERE userId = :userId")
  suspend fun deleteAllReviewCards(userId: String)

  // Structured Learning Modules & Chapters
  @Query("SELECT * FROM learning_modules ORDER BY subjectId ASC, orderIndex ASC")
  fun observeAllModules(): Flow<List<LearningModuleEntity>>

  @Query("SELECT * FROM learning_chapters ORDER BY subjectId ASC, orderIndex ASC")
  fun observeAllChapters(): Flow<List<LearningChapterEntity>>

  @Query("SELECT COUNT(*) FROM learning_modules")
  suspend fun getModuleCount(): Int

  @Query("SELECT COUNT(*) FROM learning_chapters")
  suspend fun getChapterCount(): Int

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertModules(modules: List<LearningModuleEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertChapters(chapters: List<LearningChapterEntity>)

  // Local Past Questions Bank for CBT Engine
  @Query("SELECT COUNT(*) FROM past_questions")
  suspend fun getPastQuestionCount(): Int

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPastQuestions(questions: List<PastQuestionEntity>)

  @Query("DELETE FROM learning_modules")
  suspend fun deleteAllModules()

  @Query("DELETE FROM learning_chapters")
  suspend fun deleteAllChapters()

  @Query("DELETE FROM past_questions")
  suspend fun deleteAllPastQuestions()

  @Transaction
  suspend fun replaceCurriculum(
    modules: List<LearningModuleEntity>,
    chapters: List<LearningChapterEntity>,
    questions: List<PastQuestionEntity>,
  ) {
    deleteAllModules()
    deleteAllChapters()
    deleteAllPastQuestions()
    insertModules(modules)
    insertChapters(chapters)
    insertPastQuestions(questions)
  }

  @Query(
    "SELECT * FROM past_questions WHERE subjectId = :subjectId AND LOWER(examType) = LOWER(:examType) ORDER BY orderIndex ASC"
  )
  suspend fun getPastQuestionsBySubjectAndBoard(
    subjectId: String,
    examType: String,
  ): List<PastQuestionEntity>

  @Query("SELECT * FROM past_questions WHERE subjectId = :subjectId ORDER BY orderIndex ASC")
  suspend fun getPastQuestionsBySubject(subjectId: String): List<PastQuestionEntity>

  // Paper 2 Theory & Alternative-to-Practical Attempts
  @Query("SELECT * FROM paper2_attempts WHERE userId = :userId ORDER BY updatedAtMillis DESC")
  fun observePaper2Attempts(userId: String): Flow<List<Paper2AttemptEntity>>

  @Query("SELECT * FROM paper2_attempts WHERE userId = :userId")
  suspend fun getAllPaper2AttemptsOnce(userId: String): List<Paper2AttemptEntity>

  @Query("SELECT * FROM paper2_attempts WHERE id = :id LIMIT 1")
  suspend fun getPaper2AttemptOnce(id: String): Paper2AttemptEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertPaper2Attempt(attempt: Paper2AttemptEntity)

  // Resumable CBT Exam Session Recovery
  @Query("SELECT * FROM active_cbt_sessions WHERE userId = :userId LIMIT 1")
  fun observeActiveCbtSession(userId: String): Flow<ActiveCbtSessionEntity?>

  @Query("SELECT * FROM active_cbt_sessions WHERE userId = :userId LIMIT 1")
  suspend fun getActiveCbtSessionOnce(userId: String): ActiveCbtSessionEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertActiveCbtSession(session: ActiveCbtSessionEntity)

  @Query("DELETE FROM active_cbt_sessions WHERE userId = :userId")
  suspend fun clearActiveCbtSession(userId: String)
}

@Database(
  entities =
    [
      UserProfile::class,
      LessonProgress::class,
      ExamAttempt::class,
      StudyNote::class,
      LearningModuleEntity::class,
      LearningChapterEntity::class,
      PastQuestionEntity::class,
      SpacedReviewCardEntity::class,
      Paper2AttemptEntity::class,
      ActiveCbtSessionEntity::class,
    ],
  version = 4,
  exportSchema = false,
)
@TypeConverters(StringListConverters::class)
abstract class MeritPrepDatabase : RoomDatabase() {
  abstract fun meritPrepDao(): MeritPrepDao

  companion object {
    @Volatile private var instance: MeritPrepDatabase? = null

    val MIGRATION_2_3 =
      object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
          db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS spaced_review_cards (
              id TEXT NOT NULL PRIMARY KEY,
              userId TEXT NOT NULL,
              subjectId TEXT NOT NULL,
              lessonId TEXT NOT NULL,
              questionId TEXT NOT NULL,
              topic TEXT NOT NULL,
              prompt TEXT NOT NULL,
              answer TEXT NOT NULL,
              explanation TEXT NOT NULL,
              intervalDays INTEGER NOT NULL,
              easeFactor REAL NOT NULL,
              repetitions INTEGER NOT NULL,
              dueAtMillis INTEGER NOT NULL,
              lastReviewedAtMillis INTEGER NOT NULL,
              updatedAtMillis INTEGER NOT NULL
            )
            """.trimIndent()
          )
        }
      }

    val MIGRATION_3_4 =
      object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
          db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS paper2_attempts (
              id TEXT NOT NULL PRIMARY KEY,
              userId TEXT NOT NULL,
              promptId TEXT NOT NULL,
              subjectId TEXT NOT NULL,
              topic TEXT NOT NULL,
              studentWorking TEXT NOT NULL,
              checkedStepIndicesJson TEXT NOT NULL,
              earnedMarks INTEGER NOT NULL,
              totalMarks INTEGER NOT NULL,
              updatedAtMillis INTEGER NOT NULL
            )
            """.trimIndent()
          )
          db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS active_cbt_sessions (
              userId TEXT NOT NULL PRIMARY KEY,
              selectedSubjectId TEXT NOT NULL,
              selectedExamBoard TEXT NOT NULL,
              selectedQuestionLimit INTEGER NOT NULL,
              isUtmeComboMode INTEGER NOT NULL,
              selectedComboElectivesJson TEXT NOT NULL,
              comboQuestionSubjectsJson TEXT NOT NULL,
              comboSubjectTabsJson TEXT NOT NULL,
              activeComboSubjectTab TEXT,
              questionIdsJson TEXT NOT NULL,
              currentIndex INTEGER NOT NULL,
              selectedAnswersJson TEXT NOT NULL,
              flaggedQuestionsJson TEXT NOT NULL,
              perQuestionSecondsJson TEXT NOT NULL,
              remainingSeconds INTEGER NOT NULL,
              elapsedSeconds INTEGER NOT NULL,
              totalAllowedSeconds INTEGER NOT NULL,
              updatedAtMillis INTEGER NOT NULL
            )
            """.trimIndent()
          )
        }
      }

    fun getInstance(context: Context): MeritPrepDatabase {
      return instance
        ?: synchronized(this) {
          instance
            ?: Room.databaseBuilder(
              context.applicationContext,
              MeritPrepDatabase::class.java,
              "meritprep.db",
            )
              .addMigrations(MIGRATION_2_3, MIGRATION_3_4)
              .fallbackToDestructiveMigration(dropAllTables = true)
              .build()
              .also { instance = it }
        }
    }
  }
}
