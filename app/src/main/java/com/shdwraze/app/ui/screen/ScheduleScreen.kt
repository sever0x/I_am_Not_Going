package com.shdwraze.app.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shdwraze.app.ui.AppViewModelProvider
import com.shdwraze.app.ui.component.LessonCard
import com.shdwraze.app.ui.component.LessonList

@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel = viewModel(factory = AppViewModelProvider.Factory),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    when (val scheduleUiState = scheduleViewModel.scheduleUiState) {
        is ScheduleUiState.Success -> LessonList(lessons = scheduleUiState.schedule.sortedBy { it.startTime })

        is ScheduleUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is ScheduleUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}