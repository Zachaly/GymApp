package com.example.gymapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gymapp.databinding.FragmentExercicesBinding

class ExercicesFragment : Fragment() {

    private var _binding: FragmentExercicesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExercicesBinding.inflate(inflater, container, false)

        return binding.root
    }
}