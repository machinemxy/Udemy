package com.mxy.viewmodelchallenge

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var sum = 0

    fun add(value: Int) {
        sum += value
    }
}
