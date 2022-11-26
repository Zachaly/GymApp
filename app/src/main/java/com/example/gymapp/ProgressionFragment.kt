package com.example.gymapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Space
import com.example.gymapp.application.service.WorkoutService
import com.example.gymapp.databinding.FragmentProgressionBinding
import com.example.gymapp.domain.models.WorkoutMenuItem
import com.google.android.material.button.MaterialButton
import org.koin.android.ext.android.inject

class ProgressionFragment : Fragment() {

    private var _binding: FragmentProgressionBinding? = null
    private var _workouts: MutableList<WorkoutMenuItem> = mutableListOf<WorkoutMenuItem>()

    private val binding get() = _binding!!
    private val workouts get() = _workouts
    private val workoutService by inject<WorkoutService>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgressionBinding.inflate(inflater, container, false)

        _workouts = workoutService.getWorkouts()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (wrk in workouts){
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