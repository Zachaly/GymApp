package com.example.gymapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "exercise_record")
data class ExerciseRecord(
    @ColumnInfo(name = "exercise_id") var exerciseId: Int,
    @ColumnInfo(name = "reps") var reps: Int,
    @ColumnInfo(name = "weight") var weight: Double,
    @ColumnInfo(name = "date") var date: Date
    ){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
