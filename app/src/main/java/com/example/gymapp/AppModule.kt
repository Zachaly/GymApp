package com.example.gymapp

import com.example.gymapp.application.service.FileService
import com.example.gymapp.application.service.FileServiceImpl
import com.example.gymapp.application.service.WorkoutService
import com.example.gymapp.application.service.WorkoutServiceImpl
import org.koin.dsl.module

val appModule = module {
    single<FileService> { FileServiceImpl(get()) }
    single<WorkoutService> { WorkoutServiceImpl(get()) }
}