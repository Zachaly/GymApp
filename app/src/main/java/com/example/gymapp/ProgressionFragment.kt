package com.example.gymapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gymapp.databinding.FragmentProgressionBinding

class ProgressionFragment : Fragment() {

    private var _binding: FragmentProgressionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgressionBinding.inflate(inflater, container, false)

        return binding.root
    }
}