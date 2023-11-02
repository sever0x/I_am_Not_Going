package com.shdwraze.notgoing.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ScheduleWithLessons(
    @Embedded val schedule: Schedule,
    @Relation(
        parentColumn = "id",
        entityColumn = "scheduleId"
    )
    val lessons: List<Lesson>
)