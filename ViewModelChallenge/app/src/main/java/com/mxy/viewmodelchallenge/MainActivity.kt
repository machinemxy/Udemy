package com.mxy.viewmodelchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mxy.viewmodelchallenge.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelFactory = MainActivityViewModelFactory(666)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
        viewModel.sum.observe(this, Observer {
            binding.resultText.text = it.toString()
        })
        binding.addButton.setOnClickListener {
            val strInput = binding.input.text.toString()
            try {
                val intInput = Integer.parseInt(strInput)
                viewModel.add(intInput)
                binding.input.text.clear()
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
