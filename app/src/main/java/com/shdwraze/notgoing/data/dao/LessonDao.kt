package com.shdwraze.notgoing.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shdwraze.notgoing.data.entity.Lesson
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Insert
    suspend fun insert(lesson: Lesson)

    @Update
    suspend fun update(lesson: Lesson)

    @Delete
    suspend fun delete(lesson: Lesson)

    @Query("SELECT * FROM lessons WHERE id = :lessonId")
    fun getLessonById(lessonId: Int): Flow<Lesson>

    @Query("SELECT * FROM lessons WHERE scheduleId = :scheduleId")
    fun getLessonsBySchedule(scheduleId: Int): Flow<List<Lesson>>
}