package com.alexteddy.amphibians

import android.app.Application
import com.alexteddy.amphibians.data.AppContainer
import com.alexteddy.amphibians.data.DefaultAppContainer

class AmpsApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}