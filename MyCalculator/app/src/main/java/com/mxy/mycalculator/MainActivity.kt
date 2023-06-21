package com.mxy.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    private var currentNumber = ""
    private var previousDouble: Double? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        if (currentNumber.length >= 10) { return }
        val text = (view as Button).text
        currentNumber += text.toString()
        tvInput?.append(text)
    }

    fun onZero(view: View) {
        if (currentNumber == "0") { return }
        onDigit(view)
    }

    fun onClear(view: View) {
        tvInput?.text = ""
        currentNumber = ""
        previousDouble = null
        operator = null
    }

    fun onDecimalPoint(view: View) {
        if (currentNumber.contains('.') || currentNumber.isEmpty()) { return }
        onDigit(view)
    }

    fun onOperator(view: View) {
        if (previousDouble != null || operator != null) { return }
        if (currentNumber.isEmpty() || currentNumber == "-" || currentNumber.endsWith(".")) { return }
        previousDouble = currentNumber.toDouble()
        currentNumber = ""
        val text = (view as Button).text
        operator = text.toString()
        tvInput?.append(text)
    }

    fun onMinus(view: View) {
        if (currentNumber.isEmpty()) {
            onDigit(view)
        } else {
            onOperator(view)
        }
    }

    fun onEqual(view: View) {
        if (previousDouble == null || operator == null) { return }
        if (currentNumber.isEmpty() || currentNumber == "-" || currentNumber.endsWith(".")) { return }
        if (operator == "/" && currentNumber == "0") { return }
        val currentDouble = currentNumber.toDouble()
        val result = if (operator == "+") {
            previousDouble!! + currentDouble
        } else if (operator == "-") {
            previousDouble!! - currentDouble
        } else if (operator == "*") {
            previousDouble!! * currentDouble
        } else {
            previousDouble!! / currentDouble
        }
        val resultNumber = result.toString()
        currentNumber = resultNumber
        previousDouble = null
        operator = null
        tvInput?.text = resultNumber
    }
}
