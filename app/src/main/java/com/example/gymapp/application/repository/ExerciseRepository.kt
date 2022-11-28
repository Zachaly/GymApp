package com.example.gymapp.application.repository

import com.example.gymapp.application.factory.ExerciseFactory
import com.example.gymapp.database.GymDatabase
import com.example.gymapp.domain.entity.Exercise
import com.example.gymapp.domain.entity.ExerciseRecord
import com.example.gymapp.domain.models.ExerciseMenuItem
import com.example.gymapp.domain.models.ExerciseModel

interface ExerciseRepository {
    suspend fun getList() : MutableList<ExerciseMenuItem>
    suspend fun getById(id: Int) : ExerciseModel
    suspend fun deleteById(id: Int)
    suspend fun insertAll(vararg exercises: Exercise)
    suspend fun insertRecords(vararg records: ExerciseRecord)
}

class ExerciseRepositoryImpl(
    private val _database: GymDatabase,
    private val _factory: ExerciseFactory
    ) : ExerciseRepository {
    override suspend fun getList(): MutableList<ExerciseMenuItem> {
        return _database.exerciseDao().getListItems()
    }

    override suspend fun getById(id: Int): ExerciseModel {
        return _factory.createModel(
            _database.exerciseDao().getById(id),
            _database.exerciseDao().getRecords(id)
        )
    }

    override suspend fun deleteById(id: Int) {
        try{
            _database.exerciseDao().deleteById(id)
        }
        catch (ex: Exception){
            println(ex.message)
        }
    }

    override suspend fun insertAll(vararg exercises: Exercise) {
        try{
            _database.exerciseDao().insertAll(*exercises)
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
}