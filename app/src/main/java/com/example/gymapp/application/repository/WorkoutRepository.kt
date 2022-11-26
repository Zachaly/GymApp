package com.example.gymapp.application.repository

import com.example.gymapp.database.GymDatabase
import com.example.gymapp.domain.entity.Workout
import com.example.gymapp.domain.models.WorkoutMenuItem

interface WorkoutRepository{
    fun getWorkouts() : MutableList<WorkoutMenuItem>
    fun addWorkouts(vararg workouts: Workout)
}

class WorkoutRepositoryImpl(
    private val _database: GymDatabase
    ) : WorkoutRepository {

    override fun getWorkouts(): MutableList<WorkoutMenuItem> {
        return _database.workoutDao().getListItems()
    }

    override fun addWorkouts(vararg workouts: Workout) {
        try {
            _database.workoutDao().insertAll(*workouts)
        }
        catch (ex: Exception) {
            println(ex.message)
        }
    }
}