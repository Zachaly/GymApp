package com.example.gymapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.marginLeft
import com.example.gymapp.databinding.FragmentWorkoutsBinding
import com.google.android.material.button.MaterialButton
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import com.example.gymapp.models.Workout
import java.io.File
import java.lang.Exception


class WorkoutsFragment : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null
    private var _workouts: MutableList<Workout> = mutableListOf()

    private val binding get() = _binding!!
    private val workouts get() = _workouts

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)

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

        binding.btnAddWorkout.setOnClickListener {
            val txt = binding.editAddWorkout.text.toString()

            if(txt == ""){
                return@setOnClickListener
            }

            if(workouts.any { it.name == txt }){
                return@setOnClickListener
            }
            binding.editAddWorkout.setText("")

            val wrk = Workout(txt)

            addButton(wrk)
            workouts.add(wrk)

            saveWorkouts()
        }

        refreshButtons()
    }

    private fun addButton(workout: Workout){
        val btn = MaterialButton(ContextThemeWrapper(activity, R.style.MenuButton))
        btn.layoutParams = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.btn_width).toInt(),
            resources.getDimension(R.dimen.btn_height).toInt()
        )
        (btn.layoutParams as LinearLayout.LayoutParams).apply{
            gravity = Gravity.CENTER
        }
        btn.text = workout.name

        val removeBtn = TextView(activity)
        removeBtn.background = resources.getDrawable(R.drawable.cross)
        removeBtn.layoutParams  = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.btn_height).toInt(),
            resources.getDimension(R.dimen.btn_height).toInt()
        )
        (removeBtn.layoutParams as LinearLayout.LayoutParams).apply{
            gravity = Gravity.CENTER_HORIZONTAL
        }

        removeBtn.setOnClickListener{
            workouts.remove(workout)
            saveWorkouts()
            refreshButtons()
        }

        val space = Space(activity)
        space.layoutParams = LinearLayout.LayoutParams(
            0,
            resources.getDimension(R.dimen.btn_spacing).toInt()
        )

        var layout = LinearLayout(activity)
        layout.orientation = LinearLayout.HORIZONTAL
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        (layout.layoutParams as LinearLayout.LayoutParams).apply{
            gravity = Gravity.CENTER
        }

        layout.addView(btn)
        layout.addView(removeBtn)

        binding.workoutList.addView(layout)
        binding.workoutList.addView(space)
    }

    private fun saveWorkouts(){
        val path = context?.filesDir
        try{
            File(path, "workouts.json").writeText(Json.encodeToString(workouts))
        } catch (ex: Exception){
            println(ex.message)
        }
    }

    private fun refreshButtons(){
        binding.workoutList.removeAllViews()
        for (wrk in workouts){
            addButton(wrk)
        }
    }
}