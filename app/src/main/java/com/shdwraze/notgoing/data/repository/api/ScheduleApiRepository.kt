package com.shdwraze.notgoing.data.repository.api

import com.shdwraze.notgoing.network.ScheduleApiService
import com.shdwraze.notgoing.data.entity.Lesson

interface ScheduleApiRepository {
    suspend fun getSchedule(startTime: Long, endTime: Long): List<Lesson>
}

class NetworkScheduleApiRepository(private val scheduleApiService: ScheduleApiService) :
    ScheduleApiRepository {
    override suspend fun getSchedule(startTime: Long, endTime: Long): List<Lesson> =
        scheduleApiService.getSchedule(startTime = startTime, endTime = endTime).map { data ->
            Lesson(
                startTime = data.startTime,
                endTime = data.endTime,
                type = data.type,
                teacher = data.teachers?.firstOrNull()?.shortName,
                subjectFull = data.subject.title,
                subjectBrief = data.subject.brief,
                scheduleId = 0
            )
        }
}