package com.example.gymapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymapp.application.service.WorkoutService
import com.example.gymapp.databinding.FragmentWorkoutsBinding
import com.google.android.material.button.MaterialButton
import com.example.gymapp.domain.models.Workout
import org.koin.android.ext.android.inject


class WorkoutsFragment() : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null
    private var _workouts: MutableList<Workout> = mutableListOf()

    private val binding get() = _binding!!
    private val workouts get() = _workouts
    private val workoutService by inject<WorkoutService>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)

        _workouts = workoutService.getWorkouts()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddWorkout.setOnClickListener {
            val txt = binding.editAddWorkout.text.toString()

            if(txt == "") {
                Toast.makeText(activity, R.string.workout_name_empty, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(workouts.any { it.name == txt }) {
                Toast.makeText(activity, R.string.workout_name_taken, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.editAddWorkout.setText("")

            val wrk = Workout(txt)

            addButton(wrk)
            workouts.add(wrk)

            workoutService.saveWorkouts(workouts)
        }

        refreshButtons()
    }

    private fun addButton(workout: Workout){
        val btn = MaterialButton(ContextThemeWrapper(activity, R.style.MenuButton))
        btn.layoutParams = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.btn_width).toInt(),
            resources.getDimension(R.dimen.btn_height).toInt()
        )
        (btn.layoutParams as LinearLayout.LayoutParams).apply{
            gravity = Gravity.CENTER
        }
        btn.text = workout.name
        btn.setOnClickListener{
            val action = WorkoutsFragmentDirections.actionWorkoutsFragmentToWorkoutViewFragment(workout.name)
            findNavController().navigate(action)
        }

        val removeBtn = TextView(activity)
        removeBtn.background = resources.getDrawable(R.drawable.cross)
        removeBtn.layoutParams  = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.remove_btn_size).toInt(),
            resources.getDimension(R.dimen.remove_btn_size).toInt()
        )
        (removeBtn.layoutParams as LinearLayout.LayoutParams).apply{
            gravity = Gravity.CENTER
        }

        removeBtn.setOnClickListener{
            workouts.remove(workout)
            workoutService.saveWorkouts(workouts)
            refreshButtons()
        }

        val removeBtnSpace = Space(activity)
        removeBtnSpace.layoutParams = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.btn_spacing).toInt(),
            0
        )

        val bottomSpace = Space(activity)
        bottomSpace.layoutParams = LinearLayout.LayoutParams(
            0,
            resources.getDimension(R.dimen.btn_spacing).toInt()
        )

        var layout = LinearLayout(activity)
        layout.orientation = LinearLayout.HORIZONTAL
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        (layout.layoutParams as LinearLayout.LayoutParams).apply{
            gravity = Gravity.CENTER
        }

        layout.addView(btn)
        layout.addView(removeBtnSpace)
        layout.addView(removeBtn)

        binding.workoutList.addView(layout)
        binding.workoutList.addView(bottomSpace)
    }

    private fun refreshButtons(){
        binding.workoutList.removeAllViews()
        for (wrk in workouts){
            addButton(wrk)
        }
    }
}