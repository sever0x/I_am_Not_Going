package com.shdwraze.app.data

import com.shdwraze.app.network.Lesson
import com.shdwraze.app.network.ScheduleApiService

interface ScheduleRepository {
    suspend fun getSchedule(startTime: Long, endTime: Long): List<Lesson>
}

class NetworkScheduleRepository(private val scheduleApiService: ScheduleApiService) : ScheduleRepository {
    override suspend fun getSchedule(startTime: Long, endTime: Long): List<Lesson> =
        scheduleApiService.getSchedule(startTime = startTime, endTime = endTime)
}