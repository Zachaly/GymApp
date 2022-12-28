package com.example.gymapp.application

import com.example.gymapp.database.GymDatabase
import com.example.gymapp.domain.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetupManager(
    private val _database: GymDatabase,
) {
    private val _defaultFilters = listOf("Chest", "Legs", "Back", "Biceps", "Triceps", "Shoulder")

    private var _createdExercises = listOf<String>()

    private val _defaultExercises = mapOf(
        "Bench press" to listOf("Chest"),
        "Deadlift" to listOf("Back", "Legs"),
        "Snatch" to listOf("Triceps", "Shoulder", "Back", "Legs"),
        "Pull up" to listOf("Biceps", "Back"),
        "Overhead press" to listOf("Shoulder"),
        "Bicep curl" to listOf("Biceps"),
        "Tricep extension" to listOf("Triceps"),
        "Lateral raises" to listOf("Shoulder"),
        "Squat" to listOf("Legs")
    )

    private suspend fun setFilters(){
        val existingFilters = _database.exerciseDao().getFilters().map { it.value }
        val filtersToAdd = _defaultFilters.filter { !existingFilters.contains(it) }

        if(filtersToAdd.isEmpty()) {
            return
        }

        val filters = filtersToAdd.map { ExerciseFilter(it) }
        _database.exerciseDao().addFilters(*filters.toTypedArray())
    }


    private suspend fun setExercises(){
        val existingExercises = _database.exerciseDao().getListItems().map { it.name }
        val exercisesToAdd = _defaultExercises.keys.filter { !existingExercises.contains(it) }

        if(exercisesToAdd.isEmpty()) {
            return
        }

        val exercises = exercisesToAdd.map { Exercise(it) }
        _createdExercises = exercisesToAdd

        _database.exerciseDao().insertAll(*exercises.toTypedArray())
    }


    private suspend fun setExerciseWithFilter(){
        if(_createdExercises.isEmpty()){
            return
        }
        val exercises = _database.exerciseDao().getListItems().filter { _createdExercises.contains(it.name) }
        val filters = _database.exerciseDao().getFilters()

        for(exercise in exercises){
            val exerciseExerciseFilters = filters.filter {
                _defaultExercises[exercise.name]!!.contains(it.value)
            }.map { ExerciseExerciseFilter(exercise.id, it.id) }
            println(exercise)
            _database.exerciseDao().addFilters(*exerciseExerciseFilters.toTypedArray())
        }
    }

    fun setAll(){
        CoroutineScope(Dispatchers.Main).launch {
            setFilters()
            setExercises()
            setExerciseWithFilter()
        }
    }
}