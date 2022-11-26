package com.example.gymapp.application.repository

import com.example.gymapp.database.GymDatabase
import com.example.gymapp.domain.entity.Exercise
import com.example.gymapp.domain.models.ExerciseMenuItem

interface ExerciseRepository {
    suspend fun getList() : MutableList<ExerciseMenuItem>
    suspend fun getById(id: Int) : Exercise
    suspend fun deleteById(id: Int)
    suspend fun insertAll(vararg exercises: Exercise)
}

class ExerciseRepositoryImpl(
    private val _database: GymDatabase
    ) : ExerciseRepository {
    override suspend fun getList(): MutableList<ExerciseMenuItem> {
        return _database.exerciseDao().getListItems()
    }

    override suspend fun getById(id: Int): Exercise {
        return _database.exerciseDao().getById(id)
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

}