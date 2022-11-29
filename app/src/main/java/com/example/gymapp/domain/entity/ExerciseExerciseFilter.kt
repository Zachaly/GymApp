package com.example.gymapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "exercise_exercise_filter", primaryKeys = ["exercise_id", "filter_id"])
data class ExerciseExerciseFilter(
    @ColumnInfo(name = "exercise_id") val exerciseId: Int,
    @ColumnInfo(name = "filter_id") val filterId: Int
)