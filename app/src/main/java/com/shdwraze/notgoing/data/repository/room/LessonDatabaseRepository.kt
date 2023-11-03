package com.shdwraze.notgoing.data.repository.room

import com.shdwraze.notgoing.data.entity.Lesson
import kotlinx.coroutines.flow.Flow

interface LessonDatabaseRepository {

    suspend fun insertLesson(lesson: Lesson)

    suspend fun updateLesson(lesson: Lesson)

    suspend fun deleteLesson(lesson: Lesson)

    fun getLessonById(lessonId: Int): Flow<Lesson>

    fun getLessonsBySchedule(scheduleId: Int): Flow<List<Lesson>>
}