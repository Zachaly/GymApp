package com.example.gymapp

import androidx.room.Room
import com.example.gymapp.application.SetupManager
import com.example.gymapp.application.factory.*
import com.example.gymapp.application.repository.ExerciseRepository
import com.example.gymapp.application.repository.ExerciseRepositoryImpl
import com.example.gymapp.application.repository.WorkoutRepository
import com.example.gymapp.application.repository.WorkoutRepositoryImpl
import com.example.gymapp.database.GymDatabase
import com.example.gymapp.viewModels.*
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
    factory { SetupManager(get()) }
    factory<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    factory<WorkoutFactory> { WorkoutFactoryImpl() }
    factory<ExerciseRepository> { ExerciseRepositoryImpl(get(), get()) }
    factory<ExerciseFactory> { ExerciseFactoryImpl() }
    factory<ExerciseRecordFactory> { ExerciseRecordFactoryImpl() }
    viewModel { WorkoutsFragmentViewModel(get(), get()) }
    viewModel { WorkoutViewFragmentViewModel(get()) }
    viewModel { ProgressionFragmentViewModel(get()) }
    viewModel { ExercisesFragmentViewModel(get(), get())}
    viewModel { ExerciseViewFragmentViewModel(get(), get(), get()) }
}