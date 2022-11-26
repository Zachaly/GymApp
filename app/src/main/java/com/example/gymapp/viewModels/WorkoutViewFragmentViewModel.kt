package com.example.gymapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.application.repository.WorkoutRepository
import com.example.gymapp.domain.entity.Workout
import kotlinx.coroutines.launch

class WorkoutViewFragmentViewModel(
    private val repository: WorkoutRepository
    ) : ViewModel() {

        val workout by lazy {
            MutableLiveData<Workout>()
        }

        fun set(id: Int){
            viewModelScope.launch {
                workout.value = repository.getById(id)
            }
        }

}