package com.example.gymapp.domain.models

import com.example.gymapp.domain.entity.ExerciseRecord

data class ExerciseModel(
    val id: Int,
    val name: String,
    val records: MutableList<ExerciseRecord>
)