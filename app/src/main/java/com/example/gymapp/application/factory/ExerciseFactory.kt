package com.example.gymapp.application.factory

import com.example.gymapp.domain.entity.Exercise
import com.example.gymapp.domain.models.ExerciseMenuItem

interface ExerciseFactory {
    fun create(name: String) : Exercise
    fun createMenuItem(exercise: Exercise) : ExerciseMenuItem
}

class ExerciseFactoryImpl : ExerciseFactory{
    override fun create(name: String) : Exercise {
        return Exercise(name)
    }

    override fun createMenuItem(exercise: Exercise) : ExerciseMenuItem {
        return ExerciseMenuItem(exercise.id, exercise.name)
    }

}