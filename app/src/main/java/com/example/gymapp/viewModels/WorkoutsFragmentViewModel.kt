package com.example.gymapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.application.factory.WorkoutFactory
import com.example.gymapp.application.repository.WorkoutRepository
import com.example.gymapp.domain.models.WorkoutMenuItem
import kotlinx.coroutines.launch

class WorkoutsFragmentViewModel(
    private val repository: WorkoutRepository,
    private val factory: WorkoutFactory
) : ViewModel() {

    val workouts by lazy {
        MutableLiveData<MutableList<WorkoutMenuItem>>()
    }

    init {
        workouts.value = mutableListOf()
    }

    fun loadWorkouts(){
        viewModelScope.launch {
            workouts.value = repository.getWorkouts()
            workouts.notifyObserver()
        }
    }

    fun addWorkout(name: String){
        val workout = factory.create(name)

        viewModelScope.launch {
            repository.addWorkouts(workout)
            workouts.value = repository.getWorkouts()
        }
    }

    fun deleteWorkout(id: Int){
        viewModelScope.launch {
            repository.deleteWorkout(id)
            workouts.value!!.remove(workouts.value!!.find { it.id == id })
            workouts.notifyObserver()
        }
    }
}