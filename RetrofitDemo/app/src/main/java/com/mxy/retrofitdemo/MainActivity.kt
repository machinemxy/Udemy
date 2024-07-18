package com.mxy.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.textView)
        val retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
        val responseLiveData = liveData {
            val response = retService.getAlbums()
            emit(response)
        }
        responseLiveData.observe( this, Observer {
            val albumsList = it.body()?.listIterator()
            albumsList?.let {
                while (it.hasNext()) {
                    val albumsItem = it.next()
                    textView.append("id: ${albumsItem.id}\n")
                    textView.append("user id: ${albumsItem.userId}\n")
                    textView.append("title: ${albumsItem.title}\n\n")
                }
            }
        })
    }
}
