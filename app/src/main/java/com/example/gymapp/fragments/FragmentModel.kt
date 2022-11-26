package com.example.gymapp.fragments

import android.view.ContextThemeWrapper
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gymapp.R
import com.google.android.material.button.MaterialButton

open class FragmentModel : Fragment() {

    fun createRemovableButton(text: String, onClick: () -> Unit, onRemove: () -> Unit) : LinearLayout {
        val btn = MaterialButton(ContextThemeWrapper(activity, R.style.MenuButton))
        btn.layoutParams = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.btn_width).toInt(),
            resources.getDimension(R.dimen.btn_height).toInt()
        )
        (btn.layoutParams as LinearLayout.LayoutParams).apply{
            gravity = Gravity.CENTER
        }
        btn.text = text
        btn.setOnClickListener{
            onClick()
        }

        val removeBtn = TextView(activity)
        removeBtn.background = resources.getDrawable(R.drawable.cross)
        removeBtn.layoutParams  = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.remove_btn_size).toInt(),
            resources.getDimension(R.dimen.remove_btn_size).toInt()
        )
        (removeBtn.layoutParams as LinearLayout.LayoutParams).apply{
            gravity = Gravity.CENTER
        }

        removeBtn.setOnClickListener{
            onRemove()
        }

        val removeBtnSpace = Space(activity)
        removeBtnSpace.layoutParams = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.btn_spacing).toInt(),
            0
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
        layout.addView(removeBtnSpace)
        layout.addView(removeBtn)

        return layout
    }
}