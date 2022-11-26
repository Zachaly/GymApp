package com.example.gymapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Space
import com.example.gymapp.R
import com.example.gymapp.databinding.FragmentProgressionBinding
import com.example.gymapp.viewModels.ProgressionFragmentViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProgressionFragment : Fragment() {

    private var _binding: FragmentProgressionBinding? = null

    private val binding get() = _binding!!
    private val viewModel:ProgressionFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgressionBinding.inflate(inflater, container, false)

        viewModel.workouts.observe(viewLifecycleOwner) {
            loadButtons()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWorkouts()
    }

    private fun loadButtons(){
        for (wrk in viewModel.workouts.value!!){
            val btn = MaterialButton(ContextThemeWrapper(activity, R.style.MenuButton))
            btn.layoutParams = LinearLayout.LayoutParams(
                resources.getDimension(R.dimen.btn_width).toInt(),
                resources.getDimension(R.dimen.btn_height).toInt()
            )
            (btn.layoutParams as LinearLayout.LayoutParams).apply{
                gravity = Gravity.CENTER
            }
            btn.text = wrk.name

            val space = Space(activity)
            space.layoutParams = LinearLayout.LayoutParams(
                0,
                resources.getDimension(R.dimen.btn_spacing).toInt()
            )

            binding.progressionBtns.addView(btn)
            binding.progressionBtns.addView(space)
        }
    }
}