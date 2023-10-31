package com.shdwraze.notgoing.network.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Embedded val lessons: List<Lesson>,
    val lastUpdateDate: LocalDate
)

@Entity(tableName = "lessons")
data class Lesson(
    val startTime: Long,
    val endTime: Long,
    val type: String,
    val teacher: String?,
    val subjectFull: String,
    val subjectBrief: String
)