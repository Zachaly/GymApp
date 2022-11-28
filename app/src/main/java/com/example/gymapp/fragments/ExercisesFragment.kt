package com.example.gymapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Space
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.gymapp.R
import com.example.gymapp.databinding.FragmentExercicesBinding
import com.example.gymapp.viewModels.ExercisesFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ExercisesFragment : FragmentModel() {

    private var _binding: FragmentExercicesBinding? = null

    private val binding get() = _binding!!
    private val viewModel: ExercisesFragmentViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExercicesBinding.inflate(inflater, container, false)

        viewModel.exercises.observe(viewLifecycleOwner){
            refreshButtons()
        }

        viewModel.loadData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoAddExercise.setOnClickListener{
            findNavController().navigate(R.id.action_exercicesFragment_to_addExerciseFragment)
        }
    }

    private fun refreshButtons(){
        binding.exerciseList.removeAllViews()

        for(exercise in viewModel.exercises.value!!){

            val btn = createRemovableButton(exercise.name, {
                val action = ExercisesFragmentDirections
                    .actionExercicesFragmentToExerciseViewFragment(exercise.id)
                findNavController().navigate(action)
            }, { viewModel.deleteExercise(exercise.id) })

            val space = Space(activity)
            space.layoutParams = LinearLayout.LayoutParams(
                0,
                resources.getDimension(R.dimen.btn_spacing).toInt()
            )

            binding.exerciseList.addView(btn)
            binding.exerciseList.addView(space)
        }
    }
}