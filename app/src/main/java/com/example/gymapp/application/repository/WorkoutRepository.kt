package com.example.gymapp.application.repository

import com.example.gymapp.database.GymDatabase
import com.example.gymapp.domain.entity.Workout
import com.example.gymapp.domain.models.WorkoutMenuItem

interface WorkoutRepository{
    suspend fun getWorkouts() : MutableList<WorkoutMenuItem>
    suspend fun addWorkouts(vararg workouts: Workout)
    suspend fun deleteWorkout(id: Int)
    suspend fun getById(id: Int) : Workout
}

class WorkoutRepositoryImpl(
    private val _database: GymDatabase
    ) : WorkoutRepository {

    override suspend fun getWorkouts(): MutableList<WorkoutMenuItem> {
        return _database.workoutDao().getListItems()
    }

    override suspend fun addWorkouts(vararg workouts: Workout) {
        try {
            _database.workoutDao().insertAll(*workouts)
        }
        catch (ex: Exception) {
            println(ex.message)
        }
    }

    override suspend fun deleteWorkout(id: Int) {
        try{
            _database.workoutDao().delete(id)
        }
        catch (ex: Exception){
            println(ex.message)
        }
    }

    override suspend fun getById(id: Int): Workout {
        return _database.workoutDao().getById(id)
    }
}