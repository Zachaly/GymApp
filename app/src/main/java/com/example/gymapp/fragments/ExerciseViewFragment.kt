package com.example.gymapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gymapp.R
import com.example.gymapp.databinding.FragmentExerciseViewBinding
import com.example.gymapp.domain.entity.ExerciseRecord
import com.example.gymapp.viewModels.ExerciseViewFragmentViewModel
import com.google.android.material.textview.MaterialTextView
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class ExerciseViewFragment : Fragment() {

    private var _binding: FragmentExerciseViewBinding? = null

    private val binding get() = _binding!!
    private val args: ExerciseViewFragmentArgs by navArgs()
    private val viewModel: ExerciseViewFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseViewBinding.inflate(inflater, container, false)

        viewModel.exercise.observe(viewLifecycleOwner){
            refreshTable()
            binding.exerciseFilters.text = viewModel.exercise.value!!.filters.joinToString(", ")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.load(args.id)

        binding.goAddRecordButton.setOnClickListener{
            val action = ExerciseViewFragmentDirections
                .actionExerciseViewFragmentToAddRecordFragment(args.id)
            findNavController().navigate(action)
        }
    }

    private fun addRow(record: ExerciseRecord){

        val row = createRow()

        val recordView = createTableView("${record.weight}kg x ${record.reps}")

        val dateView = createTableView(SimpleDateFormat("dd-MM-yyyy").format(record.date))

        row.addView(recordView)
        row.addView(dateView)

        binding.recordTable.addView(row)
    }

    private fun createTableView(viewText: String) : TextView{
        val view = TextView(ContextThemeWrapper(activity, R.style.exercise_table_item))
        view.apply {
            text = viewText
            width = 0
        }

        return view
    }

    private fun createRow() : TableRow{
        val row = TableRow(activity)
        row.layoutParams = TableLayout.LayoutParams()
        row.layoutParams.apply {
            width = LinearLayout.LayoutParams.MATCH_PARENT
        }

        return row
    }

    private fun refreshTable(){
        println(viewModel.exercise.value)

        binding.exerciseTitle.text = viewModel.exercise.value!!.name

        binding.recordTable.removeAllViews()

        val row = createRow()
        val record = createTableView("Record")
        val date = createTableView("Date")

        row.addView(record)
        row.addView(date)

        binding.recordTable.addView(row)

        for(rec in viewModel.exercise.value!!.records){
            addRow(rec)
        }
    }
}