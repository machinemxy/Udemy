package com.mxy.viewmodelchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int): ViewModel() {
    private val _sum = MutableLiveData(startingTotal)
    val sum: LiveData<Int> get() = _sum
    val addNum = MutableLiveData("")

    fun add() {
        addNum.value?.let {
            if (it.isNotEmpty()) {
                _sum.value = _sum.value?.plus(it.toInt())
                addNum.value = ""
            }
        }
    }
}
