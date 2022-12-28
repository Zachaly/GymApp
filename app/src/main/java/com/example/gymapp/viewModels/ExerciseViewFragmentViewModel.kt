package com.example.gymapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.application.factory.ExerciseFactory
import com.example.gymapp.application.factory.ExerciseRecordFactory
import com.example.gymapp.application.repository.ExerciseRepository
import com.example.gymapp.domain.entity.Exercise
import com.example.gymapp.domain.entity.ExerciseRecord
import com.example.gymapp.domain.models.ExerciseModel
import kotlinx.coroutines.launch
import java.util.*

class ExerciseViewFragmentViewModel(
    private val repository: ExerciseRepository,
    private val exerciseFactory: ExerciseFactory,
    private val exerciseRecordFactory: ExerciseRecordFactory
) : ViewModel() {

    val exercise by lazy{
        MutableLiveData<ExerciseModel>()
    }

    fun load(id: Int){
        viewModelScope.launch {
            exercise.value = repository.getById(id)
        }
    }

    fun addRecord(reps: Int, weight: Double){
        val record = exerciseRecordFactory.create(exercise.value!!.id, reps, weight)
        addRecord(record)
    }

    fun addRecord(reps: Int, weight: Double, date: Date){
        val record = exerciseRecordFactory.create(exercise.value!!.id, reps, weight, date)
        addRecord(record)
    }

    private fun addRecord(record: ExerciseRecord){
        viewModelScope.launch {
            repository.insertRecords(record)
            exercise.value = repository.getById(exercise.value!!.id)
        }
    }
}