package com.shdwraze.notgoing.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shdwraze.notgoing.ui.AppViewModelProvider
import com.shdwraze.notgoing.ui.component.LessonList
import com.shdwraze.notgoing.ui.component.NoLessonsTodayText
import com.shdwraze.notgoing.ui.component.ScheduleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel = viewModel(factory = AppViewModelProvider.Factory),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ScheduleTopAppBar(
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            when (val scheduleUiState = scheduleViewModel.scheduleUiState) {
                is ScheduleUiState.Success -> if (scheduleUiState.schedule.isNotEmpty())
                    LessonList(lessons =
                    scheduleUiState.schedule.sortedBy { lesson ->
                        lesson.startTime
                    }) else NoLessonsTodayText()

                is ScheduleUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
                is ScheduleUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
            }
        }
    }
}