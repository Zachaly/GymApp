package com.example.gymapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gymapp.R
import com.example.gymapp.databinding.FragmentAddRecordBinding
import com.example.gymapp.viewModels.ExerciseViewFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddRecordFragment : Fragment() {

    private var _binding: FragmentAddRecordBinding? = null

    private val binding get() = _binding!!
    private val viewModel: ExerciseViewFragmentViewModel by viewModel()
    private val args: AddRecordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRecordBinding.inflate(inflater, container, false)

        viewModel.load(args.exerciseId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addRecordSubmitBtn.setOnClickListener{
            val weight = binding.recordWeightInput.text.toString().toDouble()
            val reps = getIntInputValue(binding.recordRepsInput)

            try {
                val day = getIntInputValue(binding.recordDayInput)
                val month = getIntInputValue(binding.recordMonthInput)
                val year = getIntInputValue(binding.recordYearInput)

                val date = GregorianCalendar(year, month - 1, day, 0, 0).time

                viewModel.addRecord(reps, weight, date)
            } catch (ex: Exception){
                viewModel.addRecord(reps, weight)
            }

            val action = AddRecordFragmentDirections.
                actionAddRecordFragmentToExerciseViewFragment(args.exerciseId)
            findNavController().navigate(action)
        }
    }

    fun getIntInputValue(input: EditText) : Int{
        return input.text.toString().toInt()
    }
}