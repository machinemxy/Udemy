package com.example.servicedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
        val serviceIntent = Intent(this, MyBackgroundService::class.java)
        serviceIntent.putExtra("NAME", "Alex")
        binding.btnStart.setOnClickListener {
            Log.i("MYTAG", "Service starting")
            startService(serviceIntent)
        }

        binding.btnStop.setOnClickListener {
            Log.i("MYTAG", "Service stoping")
            stopService(serviceIntent)
        }
    }
}