package com.shdwraze.app.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.shdwraze.app.App
import com.shdwraze.app.ui.screen.ScheduleViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ScheduleViewModel(scheduleApplication().container.scheduleRepository)
        }
    }
}

fun CreationExtras.scheduleApplication(): App =
    (this[APPLICATION_KEY] as App)