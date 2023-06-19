package com.mxy.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    private var isLastNumeric = false
    private var isLastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        isLastNumeric = true
        isLastDot = false
    }

    fun onClear(view: View) {
        tvInput?.text = ""
        isLastNumeric = false
        isLastDot = false
    }

    fun onDecimalPoint(view: View) {
        if (isLastNumeric && !isLastDot) {
            tvInput?.append(".")
            isLastNumeric = false
            isLastDot = true
        }
    }
}
