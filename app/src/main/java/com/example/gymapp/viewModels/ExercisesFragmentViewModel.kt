package com.example.gymapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.application.factory.ExerciseFactory
import com.example.gymapp.application.repository.ExerciseRepository
import com.example.gymapp.domain.entity.ExerciseExerciseFilter
import com.example.gymapp.domain.entity.ExerciseFilter
import com.example.gymapp.domain.models.ExerciseMenuItem
import kotlinx.coroutines.launch

class ExercisesFragmentViewModel(
    private val repository: ExerciseRepository,
    private val factory: ExerciseFactory
) : ViewModel() {
    val exercises by lazy {
        MutableLiveData<MutableList<ExerciseMenuItem>>()
    }

    val filters by lazy {
        MutableLiveData<List<ExerciseFilter>>()
    }

    val selectedFilterIds by lazy {
        MutableLiveData<MutableList<Int>>()
    }

    fun loadData(){
        viewModelScope.launch {
            exercises.value = repository.getList()
            filters.value = repository.loadFilters()
        }
    }

    fun addExercise(name: String){
        viewModelScope.launch {
            var exercise = factory.create(name)
            repository.insertAll(exercise)

            if(selectedFilterIds.value == null || selectedFilterIds.value!!.isEmpty()){
                return@launch
            }

            val filters = selectedFilterIds.value!!
                .map { ExerciseExerciseFilter(exercise.id, it) }
                .toTypedArray()

            repository.addFilters(*filters)

            selectedFilterIds.value = mutableListOf()
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

    fun loadExercisesWithFilter(filter: String){
        viewModelScope.launch {
            val id = filters.value!!.find { it.value == filter }!!.id
            exercises.value = repository.searchByFilter(id)
        }
    }

    fun loadExercises(){
        viewModelScope.launch {
            exercises.value = repository.getList()
        }
    }
}