package com.shdwraze.notgoing.ui.screen

import com.shdwraze.notgoing.data.entity.Lesson

sealed interface ScheduleUiState {
    data class Success(val schedule: List<Lesson>) : ScheduleUiState
    object Error : ScheduleUiState
    object Loading : ScheduleUiState
}