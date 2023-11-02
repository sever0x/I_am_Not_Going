package com.shdwraze.notgoing.data.repository.room

import com.shdwraze.notgoing.data.dao.LessonDao
import com.shdwraze.notgoing.data.entity.Lesson
import kotlinx.coroutines.flow.Flow

class OfflineLessonDatabaseRepository(
    private val lessonDao: LessonDao
) : LessonDatabaseRepository {

    override suspend fun insertLesson(lesson: Lesson) {
        lessonDao.insert(lesson)
    }

    override suspend fun updateLesson(lesson: Lesson) {
        lessonDao.update(lesson)
    }

    override suspend fun deleteLesson(lesson: Lesson) {
        lessonDao.delete(lesson)
    }

    override fun getLessonById(lessonId: Int): Flow<Lesson> {
        return lessonDao.getLessonById(lessonId)
    }

    override fun getLessonsBySchedule(scheduleId: Int): Flow<List<Lesson>> {
        return lessonDao.getLessonsBySchedule(scheduleId)
    }
}