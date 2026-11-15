package com.example.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
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
}

@Database(
  entities = [UserProfile::class, LessonProgress::class, ExamAttempt::class, StudyNote::class],
  version = 1,
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
              .build()
              .also { instance = it }
        }
    }
  }
}
