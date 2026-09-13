package com.byputra.kalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var tvHistory: TextView
    private var currentInput = ""
    private var previousInput = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        tvHistory = findViewById(R.id.tvHistory)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, 
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                appendNumber((it as Button).text.toString())
            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { setOperator("×") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { setOperator("÷") }
        findViewById<Button>(R.id.btnMod).setOnClickListener { setOperator("%") }
        
        findViewById<Button>(R.id.btnDot).setOnClickListener { appendNumber(".") }
        findViewById<Button>(R.id.btnC).setOnClickListener { clear() }
        findViewById<Button>(R.id.btnDel).setOnClickListener { deleteLast() }
        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculate() }
    }

    private fun appendNumber(num: String) {
        if (currentInput == "0" && num != ".") currentInput = ""
        currentInput += num
        updateDisplay()
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            if (previousInput.isNotEmpty()) calculate()
            previousInput = currentInput
            operator = op
            currentInput = ""
            tvHistory.text = "$previousInput $operator"
            tvResult.text = "0"
        }
    }

    private fun calculate() {
        if (previousInput.isEmpty() || currentInput.isEmpty()) return
        val prev = previousInput.toDoubleOrNull() ?: 0.0
        val curr = currentInput.toDoubleOrNull() ?: 0.0
        var result = 0.0

        when (operator) {
            "+" -> result = prev + curr
            "-" -> result = prev - curr
            "×" -> result = prev * curr
            "÷" -> result = if (curr != 0.0) prev / curr else Double.NaN
            "%" -> result = prev % curr
        }

        currentInput = if (result.isNaN()) "Error" else {
            // Hilangkan .0 di belakang kalau angkanya bulat
            if (result == result.toLong().toDouble()) result.toLong().toString() else result.toString()
        }
        tvHistory.text = ""
        previousInput = ""
        operator = ""
        updateDisplay()
    }

    private fun clear() {
        currentInput = "0"
        previousInput = ""
        operator = ""
        tvHistory.text = ""
        updateDisplay()
    }

    private fun deleteLast() {
        if (currentInput.length > 1) {
            currentInput = currentInput.dropLast(1)
        } else {
            currentInput = "0"
        }
        updateDisplay()
    }

    private fun updateDisplay() {
        tvResult.text = if (currentInput.isEmpty()) "0" else currentInput
    }
}
