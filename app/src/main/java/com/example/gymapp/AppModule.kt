package com.example.gymapp

import androidx.room.Room
import com.example.gymapp.application.factory.WorkoutFactory
import com.example.gymapp.application.factory.WorkoutFactoryImpl
import com.example.gymapp.application.repository.WorkoutRepository
import com.example.gymapp.application.repository.WorkoutRepositoryImpl
import com.example.gymapp.database.GymDatabase
import com.example.gymapp.viewModels.ProgressionFragmentViewModel
import com.example.gymapp.viewModels.WorkoutViewFragmentViewModel
import com.example.gymapp.viewModels.WorkoutsFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            GymDatabase::class.java,
            "gym-database"
        ).allowMainThreadQueries()
        .build()
    }
    factory<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    factory<WorkoutFactory> { WorkoutFactoryImpl() }
    viewModel { WorkoutsFragmentViewModel(get(), get()) }
    viewModel { WorkoutViewFragmentViewModel(get()) }
    viewModel { ProgressionFragmentViewModel(get()) }
}