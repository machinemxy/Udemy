package com.mxy.viewmodelchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(startingTotal: Int): ViewModel() {
    val addNum = MutableStateFlow<String>("")

    private val _flowTotal = MutableStateFlow<Int>(startingTotal)
    val flowTotal: StateFlow<Int>
        get() = _flowTotal

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String>
        get() = _message

    fun add() {
        if (addNum.value.isNotEmpty()) {
            _flowTotal.value = (_flowTotal.value).plus(addNum.value.toInt())
            addNum.value = ""
            viewModelScope.launch {
                _message.emit("Total Updated")
            }
        }
    }
}
