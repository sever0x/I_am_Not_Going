package com.shdwraze.notgoing.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.notgoing.data.entity.Lesson
import com.shdwraze.notgoing.data.entity.Schedule
import com.shdwraze.notgoing.data.repository.api.ScheduleApiRepository
import com.shdwraze.notgoing.data.repository.room.LessonDatabaseRepository
import com.shdwraze.notgoing.data.repository.room.ScheduleDatabaseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import java.time.ZoneId

class ScheduleViewModel(
    private val scheduleApiRepository: ScheduleApiRepository,
    private val scheduleDatabaseRepository: ScheduleDatabaseRepository,
    private val lessonDatabaseRepository: LessonDatabaseRepository
) : ViewModel() {

    var scheduleUiState: ScheduleUiState by mutableStateOf(ScheduleUiState.Loading)
        private set

    init {
        getSchedule()
    }

    private fun getSchedule() {
        viewModelScope.launch {
            scheduleUiState = try {
                if (!isScheduleUpToDate()) {
                    updateSchedule()
                }
                val lessons = getScheduleFromDatabase()
                ScheduleUiState.Success(lessons)
            } catch (e: IOException) {
                ScheduleUiState.Error
            }
        }
    }

    private suspend fun isScheduleUpToDate(): Boolean {
        val today = LocalDate.now()
        val schedule = scheduleDatabaseRepository.getScheduleById(today.hashCode()).firstOrNull()
        return schedule != null && schedule.lastUpdateDate == today
    }

    private suspend fun updateSchedule() {
        val today = LocalDate.now()
        val scheduleId = today.hashCode()
        val lessons = scheduleApiRepository.getSchedule(getStartTime(), getEndTime())
        val schedule = Schedule(id = scheduleId, lastUpdateDate = today)
        scheduleDatabaseRepository.insertSchedule(schedule)
        lessons.forEach { lesson ->
            lessonDatabaseRepository.insertLesson(lesson.copy(scheduleId = scheduleId))
        }
    }

    private suspend fun getScheduleFromDatabase(): List<Lesson> {
        val today = LocalDate.now()
        val scheduleId = today.hashCode()
        return lessonDatabaseRepository.getLessonsBySchedule(scheduleId).first()
    }

    private fun getStartTime(): Long =
        LocalDate.now().atStartOfDay(ZoneId.of("Europe/Kiev"))
            .toInstant().epochSecond

    private fun getEndTime(): Long =
        LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("Europe/Kiev"))
            .toInstant().epochSecond
}