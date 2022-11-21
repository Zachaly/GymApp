package com.example.gymapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Space
import com.example.gymapp.databinding.FragmentProgressionBinding
import com.example.gymapp.models.Workout
import com.google.android.material.button.MaterialButton
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.lang.Exception

class ProgressionFragment : Fragment() {

    private var _binding: FragmentProgressionBinding? = null
    private var _workouts: MutableList<Workout> = mutableListOf<Workout>()

    private val binding get() = _binding!!
    private val workouts get() = _workouts!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgressionBinding.inflate(inflater, container, false)

        val path = context?.filesDir
        try{
            _workouts = Json.decodeFromString(File(path, "workouts.json").readText())
        } catch (ex: Exception){
            println(ex.message)
        }

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