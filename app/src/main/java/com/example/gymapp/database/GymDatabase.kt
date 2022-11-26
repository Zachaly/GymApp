package com.example.gymapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gymapp.database.dao.ExerciseDao
import com.example.gymapp.database.dao.WorkoutDao
import com.example.gymapp.domain.entity.Converters
import com.example.gymapp.domain.entity.Exercise
import com.example.gymapp.domain.entity.Workout

@Database(entities = [Workout::class, Exercise::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GymDatabase: RoomDatabase(){
    abstract fun workoutDao() : WorkoutDao
    abstract fun exerciseDao() : ExerciseDao
}