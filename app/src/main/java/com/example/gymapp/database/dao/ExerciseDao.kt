package com.example.gymapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gymapp.domain.entity.Exercise
import com.example.gymapp.domain.entity.ExerciseRecord
import com.example.gymapp.domain.models.ExerciseMenuItem
import com.example.gymapp.domain.models.ExerciseModel

@Dao
interface ExerciseDao {
    @Query("SELECT name, id FROM exercise")
    suspend fun getListItems() : MutableList<ExerciseMenuItem>

    @Insert
    suspend fun insertAll(vararg exercises: Exercise)

    @Query("DELETE FROM exercise WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM exercise WHERE id = :id")
    suspend fun getById(id: Int) : Exercise

    @Query("SELECT * FROM exercise_record WHERE exercise_id = :exerciseId")
    suspend fun getRecords(exerciseId: Int) : MutableList<ExerciseRecord>

    @Insert
    suspend fun insertRecords(vararg record: ExerciseRecord)
}