package com.example.gymapp.domain.models

import androidx.room.ColumnInfo

data class WorkoutMenuItem(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "id") val id: Int
)