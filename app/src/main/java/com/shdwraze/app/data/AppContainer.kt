package com.shdwraze.app.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shdwraze.app.network.ScheduleApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val scheduleRepository: ScheduleRepository
}

class DefaultAppContainer : AppContainer {
    override val scheduleRepository: ScheduleRepository by lazy {
        NetworkScheduleRepository(retrofitService)
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