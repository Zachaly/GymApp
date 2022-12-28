package com.example.gymapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Space
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymapp.R
import com.example.gymapp.databinding.FragmentWorkoutsBinding
import com.example.gymapp.domain.models.WorkoutMenuItem
import com.example.gymapp.viewModels.WorkoutsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class WorkoutsFragment : FragmentModel() {

    private var _binding: FragmentWorkoutsBinding? = null

    private val binding get() = _binding!!
    private val viewModel by activityViewModel<WorkoutsFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)

        viewModel.workouts.observe(viewLifecycleOwner) {
            refreshButtons()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadWorkouts()

        binding.btnAddWorkout.setOnClickListener {
            val txt = binding.editAddWorkout.text.toString()

            if(txt.isEmpty()) {
                Toast.makeText(activity, R.string.name_empty, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(viewModel.workouts.value!!.any { it.name == txt }) {
                Toast.makeText(activity, R.string.name_taken, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.editAddWorkout.setText("")

            viewModel.addWorkout(txt)
        }

        refreshButtons()
    }

    private fun addButton(workout: WorkoutMenuItem){
        var layout = createRemovableButton(workout.name, {
            val action = WorkoutsFragmentDirections
                .actionWorkoutsFragmentToWorkoutViewFragment(workout.id)
            findNavController().navigate(action)
        }, {
            viewModel.deleteWorkout(workout.id)
        })

        val bottomSpace = Space(activity)
        bottomSpace.layoutParams = LinearLayout.LayoutParams(
            0,
            resources.getDimension(R.dimen.btn_spacing).toInt()
        )

        binding.workoutList.addView(layout)
        binding.workoutList.addView(bottomSpace)
    }

    private fun refreshButtons(){
        binding.workoutList.removeAllViews()
        for (wrk in viewModel.workouts.value!!){
            addButton(wrk)
        }
    }
}