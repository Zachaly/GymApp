package com.example.gymapp.application.repository

import com.example.gymapp.application.factory.ExerciseFactory
import com.example.gymapp.database.GymDatabase
import com.example.gymapp.domain.entity.Exercise
import com.example.gymapp.domain.entity.ExerciseExerciseFilter
import com.example.gymapp.domain.entity.ExerciseFilter
import com.example.gymapp.domain.entity.ExerciseRecord
import com.example.gymapp.domain.models.ExerciseMenuItem
import com.example.gymapp.domain.models.ExerciseModel

interface ExerciseRepository {
    suspend fun getList() : MutableList<ExerciseMenuItem>
    suspend fun getById(id: Int) : ExerciseModel
    suspend fun deleteById(id: Int)
    suspend fun insertAll(vararg exercises: Exercise)
    suspend fun insertRecords(vararg records: ExerciseRecord)
    suspend fun searchByFilter(filterId: Int) : MutableList<ExerciseMenuItem>
    suspend fun searchByName(name: String) : MutableList<ExerciseMenuItem>
    suspend fun loadFilters() : List<ExerciseFilter>
    suspend fun addFilters(vararg filters: ExerciseExerciseFilter)
}

class ExerciseRepositoryImpl(
    private val _database: GymDatabase,
    private val _factory: ExerciseFactory
    ) : ExerciseRepository {

    private val _defaultFilters = listOf("Chest", "Legs", "Back", "Biceps", "Triceps")

    override suspend fun getList(): MutableList<ExerciseMenuItem> {
        return _database.exerciseDao().getListItems()
    }

    override suspend fun getById(id: Int): ExerciseModel {
        return _factory.createModel(
            _database.exerciseDao().getById(id),
            _database.exerciseDao().getRecords(id),
            _database.exerciseDao().getExerciseFilters(id)
        )
    }

    override suspend fun deleteById(id: Int) {
        try{
            _database.exerciseDao().deleteRecords(id)
            _database.exerciseDao().deleteById(id)
        }
        catch (ex: Exception){
            println(ex.message)
        }
    }

    override suspend fun insertAll(vararg exercises: Exercise) {
        try{
            val id = _database.exerciseDao().insertAll(*exercises)[0]
            exercises[0].id = id.toInt()
        }
        catch (ex: Exception){
            println(ex.message)
        }
    }

    override suspend fun insertRecords(vararg records: ExerciseRecord) {
        try{
            _database.exerciseDao().insertRecords(*records)
        }
        catch (ex: Exception){
            println(ex.message)
        }
    }

    override suspend fun searchByFilter(filterId: Int) : MutableList<ExerciseMenuItem>{
        return _database.exerciseDao().searchByFilter(filterId)
    }

    override suspend fun searchByName(name: String) : MutableList<ExerciseMenuItem>{
        return _database.exerciseDao().searchByName(name)
    }

    override suspend fun loadFilters() : List<ExerciseFilter>{
        if(_database.exerciseDao().filtersCount() < _defaultFilters.count()){
            val filters = _defaultFilters.map { ExerciseFilter(it) }.toTypedArray()
            _database.exerciseDao().addFilters(*filters)
        }

        return _database.exerciseDao().getFilters()
    }

    override suspend fun addFilters(vararg filters: ExerciseExerciseFilter){
        _database.exerciseDao().addFilters(*filters)
    }
}