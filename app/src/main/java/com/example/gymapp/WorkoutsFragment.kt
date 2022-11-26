package com.example.gymapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymapp.databinding.FragmentWorkoutsBinding
import com.google.android.material.button.MaterialButton
import com.example.gymapp.domain.models.WorkoutMenuItem
import com.example.gymapp.viewModels.WorkoutsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class WorkoutsFragment : Fragment() {

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
                Toast.makeText(activity, R.string.workout_name_empty, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(viewModel.workouts.value!!.any { it.name == txt }) {
                Toast.makeText(activity, R.string.workout_name_taken, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.editAddWorkout.setText("")

            viewModel.addWorkout(txt)
        }

        refreshButtons()
    }

    private fun addButton(workout: WorkoutMenuItem){
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
            println(workout)
            val action = WorkoutsFragmentDirections
                .actionWorkoutsFragmentToWorkoutViewFragment(workout.id)
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
            viewModel.deleteWorkout(workout.id)
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
        for (wrk in viewModel.workouts.value!!){
            addButton(wrk)
        }
    }
}