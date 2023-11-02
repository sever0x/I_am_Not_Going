package com.shdwraze.notgoing.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.notgoing.data.repository.api.ScheduleApiRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import java.time.ZoneId

class ScheduleViewModel(
    private val scheduleApiRepository: ScheduleApiRepository
) : ViewModel() {

    var scheduleUiState: ScheduleUiState by mutableStateOf(ScheduleUiState.Loading)
        private set

    init {
        getSchedule()
    }

    private fun getSchedule() {
        viewModelScope.launch {
            scheduleUiState = try {
                ScheduleUiState.Success(
                    scheduleApiRepository.getSchedule(
                        getStartTime(),
                        getEndTime()
                    )
                )
            } catch (e: IOException) {
                ScheduleUiState.Error
            }
        }
    }

    private fun getStartTime(): Long =
        LocalDate.now().atStartOfDay(ZoneId.of("Europe/Kiev"))
            .toInstant().epochSecond

    private fun getEndTime(): Long =
        LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("Europe/Kiev"))
            .toInstant().epochSecond
}