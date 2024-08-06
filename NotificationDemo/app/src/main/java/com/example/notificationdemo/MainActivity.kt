package com.example.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val channelID = "com.mxy.notificationdemo.channel1"
    private var notificationManager: NotificationManager? = null
    private val keyReply = "key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "DemoChannel", "this is a demo")

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            displayNotification()
        }
    }

    private fun displayNotification() {
        val notificationId = 1

        val tapResultIntent = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_MUTABLE)

        val remoteInput: RemoteInput = RemoteInput.Builder(keyReply).run {
            setLabel("Insert your name here")
            build()
        }
        val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Reply", pendingIntent)
            .addRemoteInput(remoteInput)
            .build()

        val intent2 = Intent(this, DetailsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        }
        val pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_IMMUTABLE)
        val action2: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Details", pendingIntent2).build()

        val intent3 = Intent(this, SettingsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        }
        val pendingIntent3 = PendingIntent.getActivity(this, 0, intent3, PendingIntent.FLAG_IMMUTABLE)
        val action3: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Settings", pendingIntent3).build()

        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification.")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .addAction(action2)
            .addAction(action3)
            .addAction(replyAction)
            .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance).apply {
            description = channelDescription
        }
        notificationManager?.createNotificationChannel(channel)
    }
}