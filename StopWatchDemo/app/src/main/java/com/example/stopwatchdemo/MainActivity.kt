package com.example.stopwatchdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.stopwatchdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnStart.setOnClickListener {
            startOrStop()
        }

        binding.btnReset.setOnClickListener {
            reset()
        }
    }

    private fun startOrStop() {
        if (isStarted) {
            stop()
        } else {
            start()
        }
    }

    private fun start() {
        binding.btnStart.text = "Stop"
        isStarted = true
    }

    private fun stop() {
        binding.btnStart.text = "Start"
        isStarted = false
    }

    private fun reset() {

    }
}