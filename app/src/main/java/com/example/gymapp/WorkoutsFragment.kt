package com.example.gymapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Space
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
            btn.layoutParams = LinearLayout.LayoutParams(
                resources.getDimension(R.dimen.btn_width).toInt(),
                resources.getDimension(R.dimen.btn_height).toInt()
            )
            (btn.layoutParams as LinearLayout.LayoutParams).apply{
                gravity = Gravity.CENTER
            }
            btn.text = txt

            val space = Space(activity)
            space.layoutParams = LinearLayout.LayoutParams(
                0,
                resources.getDimension(R.dimen.btn_spacing).toInt()
            )

            binding.workoutList.addView(btn)
            binding.workoutList.addView(space)
            binding.editAddWorkout.setText("")
        }
    }
}