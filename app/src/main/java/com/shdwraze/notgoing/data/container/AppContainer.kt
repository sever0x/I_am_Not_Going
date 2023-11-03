package com.shdwraze.notgoing.data.container

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shdwraze.notgoing.data.database.ReminderDatabase
import com.shdwraze.notgoing.data.repository.api.NetworkScheduleApiRepository
import com.shdwraze.notgoing.data.repository.api.ScheduleApiRepository
import com.shdwraze.notgoing.data.repository.room.LessonDatabaseRepository
import com.shdwraze.notgoing.data.repository.room.OfflineLessonDatabaseRepository
import com.shdwraze.notgoing.data.repository.room.OfflineScheduleDatabaseRepository
import com.shdwraze.notgoing.data.repository.room.ScheduleDatabaseRepository
import com.shdwraze.notgoing.network.ScheduleApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val scheduleApiRepository: ScheduleApiRepository

    val scheduleDatabaseRepository: ScheduleDatabaseRepository

    val lessonDatabaseRepository: LessonDatabaseRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {
    override val scheduleApiRepository: ScheduleApiRepository by lazy {
        NetworkScheduleApiRepository(retrofitService)
    }

    override val scheduleDatabaseRepository: ScheduleDatabaseRepository by lazy {
        OfflineScheduleDatabaseRepository(ReminderDatabase.getDatabase(context).scheduleDao())
    }

    override val lessonDatabaseRepository: LessonDatabaseRepository by lazy {
        OfflineLessonDatabaseRepository(ReminderDatabase.getDatabase(context).lessonDao())
    }

    private val baseUrl = "https://nure-dev.pp.ua/api/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ScheduleApiService by lazy {
        retrofit.create(ScheduleApiService::class.java)
    }
}