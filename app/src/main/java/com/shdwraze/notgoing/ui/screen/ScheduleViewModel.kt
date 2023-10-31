package com.shdwraze.notgoing.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shdwraze.notgoing.data.ScheduleRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import java.time.ZoneId

class ScheduleViewModel(
    private val scheduleRepository: ScheduleRepository
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
                    scheduleRepository.getSchedule(
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