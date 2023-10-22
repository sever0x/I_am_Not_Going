package com.shdwraze.app.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shdwraze.app.ui.AppViewModelProvider

@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val scheduleUiState = scheduleViewModel.scheduleUiState

    when (scheduleUiState) {
        is ScheduleUiState.Success -> Text(text = scheduleUiState.schedule[0].subject.title)
        is ScheduleUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is ScheduleUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}