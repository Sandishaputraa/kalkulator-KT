package com.byputra.kalkulator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.view.Gravity
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.text = "Berhasil Build Kotlin dari Termux!"
        textView.textSize = 24f
        textView.gravity = Gravity.CENTER
        textView.setTextColor(Color.WHITE)
        textView.setBackgroundColor(Color.parseColor("#0f172a"))
        setContentView(textView)
    }
}
