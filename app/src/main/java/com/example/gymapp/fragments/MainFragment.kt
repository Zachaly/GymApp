package com.example.gymapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymapp.R
import com.example.gymapp.databinding.FragmentMainBinding

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

        binding.btnProgression.setOnClickListener{
            findNavController().navigate(R.id.progressionFragment)
        }

        binding.btnIdeas.setOnClickListener{
            findNavController().navigate(R.id.ideasFragment)
        }
    }
}