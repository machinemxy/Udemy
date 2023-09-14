package com.mxy.recyclerviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val fruitsList = listOf<Fruit>(
        Fruit("Mango", "Joe"),
        Fruit("Apple", "Frank"),
        Fruit("Banana", "Tom"),
        Fruit("Guava", "Joe"),
        Fruit("Lemon", "Alex"),
        Fruit("Pear", "Joe"),
        Fruit("Orange", "Alex")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setBackgroundColor(Color.YELLOW)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(fruitsList) {
            listItemClicked(it)
        }
    }

    private fun listItemClicked(fruit: Fruit) {
        Toast.makeText(this, "Fruit is: ${fruit.name}", Toast.LENGTH_SHORT).show()
    }
}
