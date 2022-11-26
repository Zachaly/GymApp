package com.example.gymapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.gymapp.databinding.FragmentWorkoutViewBinding
import com.example.gymapp.viewModels.WorkoutViewFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkoutViewFragment : Fragment() {
    private var _binding: FragmentWorkoutViewBinding? = null

    private val binding get() = _binding!!
    private val args: WorkoutViewFragmentArgs by navArgs()
    private val viewModel: WorkoutViewFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutViewBinding.inflate(inflater, container, false)

        viewModel.workout.observe(viewLifecycleOwner){
            val txtView = TextView(activity)
            txtView.text = "Placeholder for ${viewModel.workout.value?.name} \n" +
                    "Created ${viewModel.workout.value?.created} \n" +
                    "Id ${viewModel.workout.value?.id}"
            binding.workoutLayout.addView(txtView)
        }

        viewModel.set(args.id)

        return binding.root
    }
}