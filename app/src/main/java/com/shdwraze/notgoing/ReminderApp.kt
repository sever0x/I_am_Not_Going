package com.shdwraze.notgoing

import android.app.Application
import com.shdwraze.notgoing.data.container.AppContainer
import com.shdwraze.notgoing.data.container.DefaultAppContainer

class ReminderApp : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}