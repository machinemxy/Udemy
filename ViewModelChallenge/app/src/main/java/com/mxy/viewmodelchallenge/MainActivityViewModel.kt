package com.mxy.viewmodelchallenge

import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int): ViewModel() {
    var sum = startingTotal

    fun add(value: Int) {
        sum += value
    }
}
