package com.example.gymapp

import android.app.Application
import com.example.gymapp.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class GymApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@GymApp)
            modules(appModule)
        }
    }
}