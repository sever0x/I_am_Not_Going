package com.shdwraze.notgoing.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.shdwraze.notgoing.IamNotGoingApp
import com.shdwraze.notgoing.ui.screen.ScheduleViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ScheduleViewModel(scheduleApplication().container.scheduleRepository)
        }
    }
}

fun CreationExtras.scheduleApplication(): IamNotGoingApp =
    (this[APPLICATION_KEY] as IamNotGoingApp)