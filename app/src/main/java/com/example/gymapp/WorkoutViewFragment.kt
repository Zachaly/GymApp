package com.example.gymapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.gymapp.databinding.FragmentWorkoutViewBinding
import com.example.gymapp.models.Workout
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.lang.Exception

class WorkoutViewFragment : Fragment() {
    private var _binding: FragmentWorkoutViewBinding? = null
    private var _workout: Workout? = null

    private val binding get() = _binding!!
    private val args: WorkoutViewFragmentArgs by navArgs()
    private val workout get() = _workout!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutViewBinding.inflate(inflater, container, false)

        val path = context?.filesDir
        try{
            val workouts = Json.decodeFromString<List<Workout>>(File(path, "workouts.json").readText())
            _workout = workouts.find { it.name == args.workoutName }
        } catch (ex: Exception){
            println(ex.message)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtView = TextView(activity)
        txtView.text = "Placeholder for ${workout.name}"

        binding.workoutLayout.addView(txtView)
    }
}