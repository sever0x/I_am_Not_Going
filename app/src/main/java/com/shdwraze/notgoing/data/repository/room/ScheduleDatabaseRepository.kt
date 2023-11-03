package com.shdwraze.notgoing.data.repository.room

import com.shdwraze.notgoing.data.entity.Schedule
import com.shdwraze.notgoing.data.entity.ScheduleWithLessons
import kotlinx.coroutines.flow.Flow

interface ScheduleDatabaseRepository {

    suspend fun insertSchedule(schedule: Schedule)

    suspend fun updateSchedule(schedule: Schedule)

    suspend fun deleteSchedule(schedule: Schedule)

    fun getScheduleById(scheduleId: Int): Flow<Schedule>

    fun getAllSchedules(): Flow<List<Schedule>>

    fun getSchedulesWithLessons(): Flow<List<ScheduleWithLessons>>
}