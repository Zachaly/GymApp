package com.example.gymapp.application.factory

import com.example.gymapp.domain.entity.Workout
import com.example.gymapp.domain.models.WorkoutMenuItem
import java.util.*

interface WorkoutFactory{
    fun create(name: String) : Workout
    fun createMenuItem(workout: Workout) : WorkoutMenuItem
}

class WorkoutFactoryImpl : WorkoutFactory {
    override fun create(name: String) : Workout {
        return Workout(name, Date())
    }

    override fun createMenuItem(workout: Workout): WorkoutMenuItem {
        return WorkoutMenuItem(workout.name, workout.id)
    }
}