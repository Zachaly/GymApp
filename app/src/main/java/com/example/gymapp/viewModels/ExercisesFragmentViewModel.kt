package com.example.gymapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.application.factory.ExerciseFactory
import com.example.gymapp.application.repository.ExerciseRepository
import com.example.gymapp.domain.models.ExerciseMenuItem
import kotlinx.coroutines.launch

class ExercisesFragmentViewModel(
    private val repository: ExerciseRepository,
    private val factory: ExerciseFactory
) : ViewModel() {
    val exercises by lazy {
        MutableLiveData<MutableList<ExerciseMenuItem>>()
    }

    fun loadData(){
        viewModelScope.launch {
            exercises.value = repository.getList()
        }
    }

    fun addExercise(name: String){
        viewModelScope.launch {
            var exercise = factory.create(name)
            repository.insertAll(exercise)
        }
    }

    fun deleteExercise(id: Int){
        viewModelScope.launch{
            repository.deleteById(id)
            exercises.value?.remove(exercises.value?.find { it.id == id })
            exercises.notifyObserver()
        }
    }

    fun doesExist(name: String) : Boolean {
        return exercises.value!!.any { it.name == name }
    }
}