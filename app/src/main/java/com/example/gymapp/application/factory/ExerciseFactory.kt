package com.example.gymapp.application.factory

import com.example.gymapp.domain.entity.Exercise
import com.example.gymapp.domain.entity.ExerciseRecord
import com.example.gymapp.domain.models.ExerciseMenuItem
import com.example.gymapp.domain.models.ExerciseModel

interface ExerciseFactory {
    fun create(name: String) : Exercise
    fun createMenuItem(exercise: Exercise) : ExerciseMenuItem
    fun createModel(exercise: Exercise, records: MutableList<ExerciseRecord>) : ExerciseModel
}

class ExerciseFactoryImpl : ExerciseFactory{
    override fun create(name: String) : Exercise {
        return Exercise(name)
    }

    override fun createMenuItem(exercise: Exercise) : ExerciseMenuItem {
        return ExerciseMenuItem(exercise.id, exercise.name)
    }

    override fun createModel(exercise: Exercise, records: MutableList<ExerciseRecord>) : ExerciseModel {
        return ExerciseModel(exercise.id, exercise.name, records)
    }
}