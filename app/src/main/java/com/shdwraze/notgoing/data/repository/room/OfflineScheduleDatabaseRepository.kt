package com.shdwraze.notgoing.data.repository.room

import com.shdwraze.notgoing.data.dao.ScheduleDao
import com.shdwraze.notgoing.data.entity.Schedule
import com.shdwraze.notgoing.data.entity.ScheduleWithLessons
import kotlinx.coroutines.flow.Flow

class OfflineScheduleDatabaseRepository(
    private val scheduleDao: ScheduleDao
) : ScheduleDatabaseRepository {
    override suspend fun insertSchedule(schedule: Schedule) {
        scheduleDao.insert(schedule)
    }

    override suspend fun updateSchedule(schedule: Schedule) {
        scheduleDao.update(schedule)
    }

    override suspend fun deleteSchedule(schedule: Schedule) {
        scheduleDao.delete(schedule)
    }

    override fun getScheduleById(scheduleId: Int): Flow<Schedule> {
        return scheduleDao.getScheduleById(scheduleId)
    }

    override fun getAllSchedules(): Flow<List<Schedule>> {
        return scheduleDao.getAllSchedules()
    }

    override fun getSchedulesWithLessons(): Flow<List<ScheduleWithLessons>> {
        return scheduleDao.getSchedulesWithLessons()
    }
}