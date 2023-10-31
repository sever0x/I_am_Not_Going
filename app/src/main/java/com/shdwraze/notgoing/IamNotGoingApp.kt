package com.shdwraze.notgoing

import android.app.Application
import com.shdwraze.notgoing.data.AppContainer
import com.shdwraze.notgoing.data.DefaultAppContainer

class IamNotGoingApp : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}