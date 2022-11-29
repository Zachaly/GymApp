package com.example.gymapp.database.dao

import androidx.room.*
import com.example.gymapp.domain.entity.Exercise
import com.example.gymapp.domain.entity.ExerciseExerciseFilter
import com.example.gymapp.domain.entity.ExerciseFilter
import com.example.gymapp.domain.entity.ExerciseRecord
import com.example.gymapp.domain.models.ExerciseMenuItem
import com.example.gymapp.domain.models.ExerciseModel

@Dao
interface ExerciseDao {
    @Query("SELECT name, id FROM exercise")
    suspend fun getListItems() : MutableList<ExerciseMenuItem>

    @Insert
    suspend fun insertAll(vararg exercises: Exercise) : Array<Long>

    @Query("DELETE FROM exercise WHERE id = :id;")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM exercise WHERE id = :id")
    suspend fun getById(id: Int) : Exercise

    @Query("SELECT * FROM exercise_record WHERE exercise_id = :exerciseId")
    suspend fun getRecords(exerciseId: Int) : MutableList<ExerciseRecord>

    @Query("DELETE FROM exercise_record WHERE exercise_id = :exerciseId")
    suspend fun deleteRecords(exerciseId: Int)

    @Insert
    suspend fun insertRecords(vararg record: ExerciseRecord)

    @Query("SELECT DISTINCT exercise.name, exercise.id FROM exercise " +
            "INNER JOIN exercise_exercise_filter ON exercise_exercise_filter.exercise_id = exercise.id " +
            "INNER JOIN exercise_filter ON exercise_exercise_filter.filter_id = exercise_filter.id " +
            "WHERE exercise_filter.id=:filterId")
    suspend fun searchByFilter(filterId: Int) : MutableList<ExerciseMenuItem>

    @Query("SELECT name, id FROM exercise WHERE name LIKE '%' || :exerciseName || '%'")
    suspend fun searchByName(exerciseName: String) : MutableList<ExerciseMenuItem>

    @Query("SELECT Count(id) FROM exercise_filter")
    suspend fun filtersCount() : Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addFilters(vararg filters: ExerciseFilter)

    @Query("SELECT id, value FROM exercise_filter")
    suspend fun getFilters() : List<ExerciseFilter>

    @Insert
    suspend fun addFilters(vararg filters: ExerciseExerciseFilter)

    @Query("SELECT exercise_filter.value FROM exercise_exercise_filter " +
            "INNER JOIN exercise_filter ON exercise_filter.id = exercise_exercise_filter.filter_id " +
            "WHERE exercise_exercise_filter.exercise_id = :exerciseId")
    suspend fun getExerciseFilters(exerciseId: Int) : List<String>
}