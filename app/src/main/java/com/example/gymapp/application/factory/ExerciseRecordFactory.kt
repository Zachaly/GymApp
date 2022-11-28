package com.example.gymapp.application.factory

import com.example.gymapp.domain.entity.ExerciseRecord
import java.util.*

interface ExerciseRecordFactory {
    fun create(exerciseId: Int, reps: Int, weight: Double, date: Date) : ExerciseRecord
    fun create(exerciseId: Int, reps: Int, weight: Double) : ExerciseRecord
}

class ExerciseRecordFactoryImpl : ExerciseRecordFactory{
    override fun create(exerciseId: Int, reps: Int, weight: Double, date: Date): ExerciseRecord {
        return ExerciseRecord(exerciseId, reps, weight, date)
    }

    override fun create(exerciseId: Int, reps: Int, weight: Double): ExerciseRecord {
        return ExerciseRecord(exerciseId, reps, weight, Date())
    }
}