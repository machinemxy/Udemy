package com.mxy.viewmodelscopedemo1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/* When not using view model scope:
class MainActivityViewModel: ViewModel() {
    private val myJob = Job()
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    fun getUserData() {
        myScope.launch {
            // ...
        }
    }

    override fun onCleared() {
        super.onCleared()
        myJob.cancel()
    }
}
 */

// When using view model scope:
class MainActivityViewModel: ViewModel() {
    fun getUserData() {
        viewModelScope.launch {
            // ...
        }
    }
}
