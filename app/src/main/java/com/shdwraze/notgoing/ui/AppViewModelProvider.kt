package com.shdwraze.notgoing.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.shdwraze.notgoing.ReminderApp
import com.shdwraze.notgoing.ui.screen.ScheduleViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ScheduleViewModel(scheduleApplication().container.scheduleApiRepository)
        }
    }
}

fun CreationExtras.scheduleApplication(): ReminderApp =
    (this[APPLICATION_KEY] as ReminderApp)