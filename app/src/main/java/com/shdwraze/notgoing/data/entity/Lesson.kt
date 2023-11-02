package com.shdwraze.notgoing.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val scheduleId: Int,
    val startTime: Long,
    val endTime: Long,
    val type: String,
    val teacher: String?,
    val subjectFull: String,
    val subjectBrief: String
)