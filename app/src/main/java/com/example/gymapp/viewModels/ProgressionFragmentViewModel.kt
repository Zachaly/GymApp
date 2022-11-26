package com.example.gymapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.application.repository.WorkoutRepository
import com.example.gymapp.domain.models.WorkoutMenuItem
import kotlinx.coroutines.launch

class ProgressionFragmentViewModel(
    private val repository: WorkoutRepository
    ) : ViewModel() {
    val workouts by lazy {
        MutableLiveData<List<WorkoutMenuItem>>()
    }

    fun getWorkouts(){
        viewModelScope.launch{
            workouts.value = repository.getWorkouts()
        }
    }
}