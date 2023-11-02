package com.shdwraze.notgoing.network

import com.shdwraze.notgoing.network.model.LessonData
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApiService {
    @GET("schedule")
    suspend fun getSchedule(
        @Query("type") type: String = "group",
        @Query("id") id: String = "8476358",
        @Query("start_time") startTime: Long,
        @Query("end_time") endTime: Long,
    ): List<LessonData>
}