package com.example.gymapp.database.dao

import androidx.room.*
import com.example.gymapp.domain.entity.Workout
import com.example.gymapp.domain.models.WorkoutMenuItem

@Dao
interface WorkoutDao {
    @Query("SELECT id, name FROM workout")
    suspend fun getListItems() : MutableList<WorkoutMenuItem>

    @Query("SELECT * FROM workout WHERE id = :id")
    suspend fun getById(id: Int) : Workout

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(vararg workouts: Workout)

    @Query("DELETE FROM workout WHERE id = :id")
    suspend fun delete(id: Int)
}