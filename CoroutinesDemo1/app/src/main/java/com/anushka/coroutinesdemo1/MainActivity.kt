package com.anushka.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var btnDownloadUserData : Button
    private lateinit var btnCount : Button
    private lateinit var tvCount : TextView
    private lateinit var tvUserMessage: TextView
    private lateinit var btnAsyncAwait: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDownloadUserData = findViewById(R.id.btnDownloadUserData)
        btnCount = findViewById(R.id.btnCount)
        tvCount = findViewById(R.id.tvCount)
        tvUserMessage = findViewById(R.id.tvUserMessage)
        btnAsyncAwait = findViewById(R.id.btn_async_await)

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }
        btnDownloadUserData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.i("MyTag", "Thread: ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.i("MyTag", "Thread: ${Thread.currentThread().name}")
        }

        btnAsyncAwait.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.i("MyTag", "Calculation started")
                val stock1 = async { getStock1() }
                val stock2 = async { getStock2() }
                val total = stock1.await() + stock2.await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Total is $total", Toast.LENGTH_SHORT).show()
                }
                Log.i("MyTag", "Total is $total")
            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
        }
    }

    private suspend fun getStock1(): Int {
        delay(1000)
        Log.i("MyTag", "stock 1 returned")
        return 55000
    }

    private suspend fun getStock2(): Int {
        delay(2000)
        Log.i("MyTag", "stock 2 returned")
        return 35000
    }
}
