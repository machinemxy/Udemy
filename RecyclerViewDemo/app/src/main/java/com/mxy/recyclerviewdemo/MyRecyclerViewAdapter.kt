package com.mxy.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(
    private val fruitsList: List<Fruit>,
    private val clickHandler: (Fruit) -> Unit
): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(listItem, clickHandler)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(fruitsList[position])
    }
}

class MyViewHolder(
    private val view: View,
    private val clickHandler: (Fruit) -> Unit
): RecyclerView.ViewHolder(view) {
    fun bind(fruit: Fruit) {
        val myTextView: TextView = view.findViewById<TextView>(R.id.tvName)
        myTextView.text = fruit.name

        view.setOnClickListener {
            clickHandler(fruit)
        }
    }
}
