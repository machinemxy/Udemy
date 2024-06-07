package com.mxy.roomdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxy.roomdemo.db.Subscriber
import com.mxy.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository): ViewModel() {
    val subscribers = repository.subscribers
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val saveOrUpdateButtonText = MutableLiveData<String>("Save")
    val clearAllOrDeleteButtonText = MutableLiveData<String>("Clear All")

    fun saveOrUpdate() {
        insert(Subscriber(0, inputName.value ?: "", inputEmail.value ?: ""))
        inputName.value = ""
        inputEmail.value = ""
    }

    fun clearAllOrDelete() {
        clearAll()
    }

    fun insert(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(subscriber)
        }
    }

    fun update(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(subscriber)
        }
    }

    fun delete(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(subscriber)
        }
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
