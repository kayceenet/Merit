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
import com.example.data.model.ExamAttempt
import com.example.data.model.LearningChapterEntity
import com.example.data.model.LearningModuleEntity
import com.example.data.model.LessonProgress
import com.example.data.model.PastQuestionEntity
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

  @Query("SELECT * FROM lesson_progress WHERE id = :id LIMIT 1")
  suspend fun getLessonProgressOnce(id: String): LessonProgress?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertLessonProgress(progress: LessonProgress)

  @Query("SELECT * FROM exam_attempts WHERE userId = :userId ORDER BY createdAt DESC")
  fun observeExamAttempts(userId: String): Flow<List<ExamAttempt>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertExamAttempt(attempt: ExamAttempt)

  @Query("SELECT * FROM study_notes WHERE userId = :userId ORDER BY updatedAt DESC, createdAt DESC")
  fun observeStudyNotes(userId: String): Flow<List<StudyNote>>

  @Query("SELECT * FROM study_notes WHERE id = :id LIMIT 1")
  suspend fun getStudyNoteOnce(id: String): StudyNote?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertStudyNote(note: StudyNote)

  @Query("DELETE FROM study_notes WHERE id = :id AND userId = :userId")
  suspend fun deleteStudyNote(userId: String, id: String)

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
    ],
  version = 2,
  exportSchema = false,
)
@TypeConverters(StringListConverters::class)
abstract class MeritPrepDatabase : RoomDatabase() {
  abstract fun meritPrepDao(): MeritPrepDao

  companion object {
    @Volatile private var instance: MeritPrepDatabase? = null

    fun getInstance(context: Context): MeritPrepDatabase {
      return instance
        ?: synchronized(this) {
          instance
            ?: Room.databaseBuilder(
              context.applicationContext,
              MeritPrepDatabase::class.java,
              "meritprep.db",
            )
              .fallbackToDestructiveMigration(dropAllTables = true)
              .build()
              .also { instance = it }
        }
    }
  }
}
