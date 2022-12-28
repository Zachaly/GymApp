package com.example.gymapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    @ColumnInfo(name = "name") var name: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}