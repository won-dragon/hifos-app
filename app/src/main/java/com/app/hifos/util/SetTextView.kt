package com.app.hifos.util

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import androidx.core.view.setPadding
import com.app.hifos.R

class SetTextView {
    companion object {
        fun setText(ct: Context, value: String): TextView {
            val textView = TextView(ct)
            textView.text = value
            textView.gravity = Gravity.CENTER
            textView.setPadding(10)
            textView.setBackgroundResource(R.drawable.text_shape)

            return textView
        }
    }
}