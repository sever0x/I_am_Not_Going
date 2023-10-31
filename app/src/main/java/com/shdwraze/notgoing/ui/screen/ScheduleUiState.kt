package com.shdwraze.notgoing.ui.screen

import com.shdwraze.notgoing.network.Lesson

sealed interface ScheduleUiState {
    data class Success(val schedule: List<Lesson>) : ScheduleUiState
    object Error : ScheduleUiState
    object Loading : ScheduleUiState
}