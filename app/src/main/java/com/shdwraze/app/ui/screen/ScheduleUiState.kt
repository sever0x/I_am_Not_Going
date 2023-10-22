package com.shdwraze.app.ui.screen

import com.shdwraze.app.network.Lesson

sealed interface ScheduleUiState {
    data class Success(val schedule: List<Lesson>) : ScheduleUiState
    object Error : ScheduleUiState
    object Loading : ScheduleUiState
}