package com.example.gymapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymapp.domain.entity.Workout
import com.example.gymapp.domain.models.WorkoutMenuItem

@Dao
interface WorkoutDao {
    @Query("SELECT id, name FROM workout")
    fun getListItems() : MutableList<WorkoutMenuItem>

    @Query("SELECT * FROM workout WHERE id = :id")
    fun getById(id: Int) : Workout

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(vararg workouts: Workout)
}