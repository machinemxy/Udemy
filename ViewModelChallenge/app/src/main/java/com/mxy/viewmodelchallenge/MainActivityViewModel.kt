package com.mxy.viewmodelchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int): ViewModel() {
    private val _sum = MutableLiveData(startingTotal)
    val sum: LiveData<Int> get() = _sum

    fun add(value: Int) {
        _sum.value = _sum.value?.plus(value)
    }
}
