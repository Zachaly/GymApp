package com.example.gymapp

import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.gymapp.databinding.FragmentMainBinding
import com.google.android.material.button.MaterialButton

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWorkouts.setOnClickListener{
            findNavController().navigate(R.id.workoutsFragment)
        }

        binding.btnExercises.setOnClickListener{
            findNavController().navigate(R.id.exercicesFragment)
        }
    }
}