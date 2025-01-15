package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    init {
        Log.i("MYTAG", "Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("MYTAG", "Service started")
        val name = intent?.getStringExtra("NAME")
        Log.i("MYTAG", "My name is $name")
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i("MYTAG", "Service destroyed")
        super.onDestroy()
    }
}