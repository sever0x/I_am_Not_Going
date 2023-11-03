package com.shdwraze.notgoing.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.shdwraze.notgoing.data.entity.Schedule
import com.shdwraze.notgoing.data.entity.ScheduleWithLessons
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert
    suspend fun insert(schedule: Schedule)

    @Update
    suspend fun update(schedule: Schedule)

    @Delete
    suspend fun delete(schedule: Schedule)

    @Query("SELECT * FROM schedule WHERE id = :scheduleId")
    fun getScheduleById(scheduleId: Int): Flow<Schedule>

    @Query("SELECT * FROM schedule")
    fun getAllSchedules(): Flow<List<Schedule>>

    @Transaction
    @Query("SELECT * FROM schedule")
    fun getSchedulesWithLessons(): Flow<List<ScheduleWithLessons>>
}