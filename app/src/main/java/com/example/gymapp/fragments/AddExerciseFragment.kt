package com.example.gymapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymapp.R
import com.example.gymapp.databinding.FragmentAddExerciseBinding
import com.example.gymapp.viewModels.ExercisesFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AddExerciseFragment : Fragment() {

    private var _binding: FragmentAddExerciseBinding? = null

    private val binding get() = _binding!!
    private val viewModel: ExercisesFragmentViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddExerciseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddExercise.setOnClickListener{
            val txt = binding.exerciseNameInput.text.toString()

            if(txt.isEmpty()){
                Toast.makeText(activity, R.string.name_empty, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(viewModel.doesExist(txt)){
                Toast.makeText(activity, R.string.name_taken, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.addExercise(txt)

            findNavController().navigate(R.id.action_addExerciseFragment_to_exercicesFragment)
        }
    }
}