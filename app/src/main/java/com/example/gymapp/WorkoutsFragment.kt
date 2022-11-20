package com.example.gymapp

import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gymapp.databinding.FragmentWorkoutsBinding
import com.google.android.material.button.MaterialButton

class WorkoutsFragment : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddWorkout.setOnClickListener {
            val txt = binding.editAddWorkout.text.toString()

            if(txt == ""){
                return@setOnClickListener
            }

            val btn = MaterialButton(ContextThemeWrapper(activity, R.style.MenuButton))
            btn.text = txt
            binding.workoutList.addView(btn)
            binding.editAddWorkout.setText("")
        }
    }
}