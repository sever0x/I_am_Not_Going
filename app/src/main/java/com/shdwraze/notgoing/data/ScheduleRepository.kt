package com.shdwraze.notgoing.data

import com.shdwraze.notgoing.network.model.serializable.LessonData
import com.shdwraze.notgoing.network.ScheduleApiService
import com.shdwraze.notgoing.network.model.entity.Lesson

interface ScheduleRepository {
    suspend fun getSchedule(startTime: Long, endTime: Long): List<Lesson>
}

class NetworkScheduleRepository(private val scheduleApiService: ScheduleApiService) : ScheduleRepository {
    override suspend fun getSchedule(startTime: Long, endTime: Long): List<Lesson> =
        scheduleApiService.getSchedule(startTime = startTime, endTime = endTime).map {data ->
            Lesson(
                startTime = data.startTime,
                endTime = data.endTime,
                type = data.type,
                teacher = data.teachers?.get(0)?.shortName,
                subjectFull = data.subject.title,
                subjectBrief = data.subject.brief
            )
        }
}