package com.example.gymapp

import androidx.room.Room
import com.example.gymapp.application.repository.WorkoutRepository
import com.example.gymapp.application.repository.WorkoutRepositoryImpl
import com.example.gymapp.application.service.FileService
import com.example.gymapp.application.service.FileServiceImpl
import com.example.gymapp.application.service.WorkoutService
import com.example.gymapp.application.service.WorkoutServiceImpl
import com.example.gymapp.database.GymDatabase
import com.example.gymapp.viewModels.WorkoutsFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<FileService> { FileServiceImpl(get()) }
    single<WorkoutService> { WorkoutServiceImpl(get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            GymDatabase::class.java,
            "gym-database"
        ).allowMainThreadQueries()
        .build()
    }
    factory<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    viewModel { WorkoutsFragmentViewModel(get()) }
}