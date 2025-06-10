package com.mxy.viewmodelchallenge

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel(startingTotal: Int): ViewModel() {
    val addNum = MutableStateFlow<String>("")

    private val _flowTotal = MutableStateFlow<Int>(startingTotal)
    val flowTotal: StateFlow<Int>
        get() = _flowTotal

    fun add() {
        if (addNum.value.isNotEmpty()) {
            _flowTotal.value = (_flowTotal.value).plus(addNum.value.toInt())
            addNum.value = ""
        }
    }
}
