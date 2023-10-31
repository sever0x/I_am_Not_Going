package com.shdwraze.notgoing.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    @SerialName("start_time")
    val startTime: Long,
    @SerialName("end_time")
    val endTime: Long,
    val type: String,
    val teachers: List<Teacher>,
    val subject: Subject
)

@Serializable
data class Teacher(
    @SerialName("short_name")
    val shortName: String
)

@Serializable
data class Subject(
    val brief: String,
    val title: String
)