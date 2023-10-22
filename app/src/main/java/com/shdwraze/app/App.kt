package com.shdwraze.app

import android.app.Application
import com.shdwraze.app.data.AppContainer
import com.shdwraze.app.data.DefaultAppContainer

class App : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}