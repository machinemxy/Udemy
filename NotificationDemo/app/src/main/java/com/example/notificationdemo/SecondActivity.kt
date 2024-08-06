package com.example.notificationdemo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.RemoteInput
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        receiveInput()
    }

    private fun receiveInput() {
        val remoteInput = RemoteInput.getResultsFromIntent(this.intent)
        remoteInput?.let {
            val inputString = it.getCharSequence("key_reply").toString()
            val resultTextView = findViewById<TextView>(R.id.result_text_view)
            resultTextView.text = inputString
        }
    }
}