package com.example.gymapp

import android.app.Application
import com.example.gymapp.application.SetupManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin

class GymApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GymApp)
            modules(appModule)
        }

        Setup().defaultSetup()
    }
}

class Setup : KoinComponent {
    private val _setupManager: SetupManager by inject()

    fun defaultSetup(){
        _setupManager.setAll()
    }
}