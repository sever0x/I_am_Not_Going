package com.shdwraze.notgoing.data

import com.shdwraze.notgoing.network.Lesson
import com.shdwraze.notgoing.network.ScheduleApiService

interface ScheduleRepository {
    suspend fun getSchedule(startTime: Long, endTime: Long): List<Lesson>
}

class NetworkScheduleRepository(private val scheduleApiService: ScheduleApiService) : ScheduleRepository {
    override suspend fun getSchedule(startTime: Long, endTime: Long): List<Lesson> =
        scheduleApiService.getSchedule(startTime = startTime, endTime = endTime)
}