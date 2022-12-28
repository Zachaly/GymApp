package com.example.gymapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_filter",
    indices = [Index(value = ["value"], unique = true)])
data class ExerciseFilter(
    @ColumnInfo(name = "value") val value: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}