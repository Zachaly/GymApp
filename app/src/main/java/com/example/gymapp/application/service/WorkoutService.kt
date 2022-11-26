package com.example.gymapp.application.service

import com.example.gymapp.domain.models.WorkoutMenuItem
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface WorkoutService{
    fun getWorkouts() : MutableList<WorkoutMenuItem>
    fun saveWorkouts(workouts: Collection<WorkoutMenuItem>)
    fun getWorkout(name: String) : WorkoutMenuItem?
}

class WorkoutServiceImpl(private val fileService: FileService) : WorkoutService {
    private val _workoutsFile: String = "workouts.json"

    override fun getWorkouts(): MutableList<WorkoutMenuItem> {
        var list = mutableListOf<WorkoutMenuItem>()
        try{
            list = Json.decodeFromString(fileService.readTextFile(_workoutsFile))
        }
        catch (ex: Exception){
            println(ex.message)
        }

        return list
    }

    override fun saveWorkouts(workouts: Collection<WorkoutMenuItem>) {
        fileService.saveTextFile(_workoutsFile, Json.encodeToString(workouts))
    }

    override fun getWorkout(name: String) : WorkoutMenuItem? {
        return Json.decodeFromString<Collection<WorkoutMenuItem>>(fileService.readTextFile(_workoutsFile))
                .find { it.name == name }
    }
}