package com.example.gymapp.application.service

import com.example.gymapp.domain.models.Workout
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface WorkoutService{
    fun getWorkouts() : MutableList<Workout>
    fun saveWorkouts(workouts: Collection<Workout>)
    fun getWorkout(name: String) : Workout?
}

class WorkoutServiceImpl(private val fileService: FileService) : WorkoutService {
    private val _workoutsFile: String = "workouts.json"

    override fun getWorkouts(): MutableList<Workout> {
        var list = mutableListOf<Workout>()
        try{
            list = Json.decodeFromString(fileService.readTextFile(_workoutsFile))
        }
        catch (ex: Exception){
            println(ex.message)
        }

        return list
    }

    override fun saveWorkouts(workouts: Collection<Workout>) {
        fileService.saveTextFile(_workoutsFile, Json.encodeToString(workouts))
    }

    override fun getWorkout(name: String) : Workout? {
        return Json.decodeFromString<Collection<Workout>>(fileService.readTextFile(_workoutsFile))
                .find { it.name == name }
    }
}