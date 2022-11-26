package com.example.gymapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "workout")
data class Workout (
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "created") val created: Date
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}