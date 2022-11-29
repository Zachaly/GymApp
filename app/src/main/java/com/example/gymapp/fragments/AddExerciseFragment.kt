package com.example.gymapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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

        viewModel.selectedFilterIds.value = mutableListOf()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for(filter in viewModel.filters.value!!){
            val checkBox = CheckBox(activity)
            checkBox.text = filter.value

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    println(filter.id)
                    viewModel.selectedFilterIds.value!!.add(filter.id)
                } else{
                    viewModel.selectedFilterIds.value!!.remove(filter.id)
                }
            }

            binding.addFiltersCheckboxes.addView(checkBox)
        }

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