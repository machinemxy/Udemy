package com.mxy.roomdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxy.roomdemo.db.Subscriber
import com.mxy.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewModel(private val repository: SubscriberRepository): ViewModel() {
    val subscribers = repository.subscribers
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val saveOrUpdateButtonText = MutableLiveData<String>("Save")
    val clearAllOrDeleteButtonText = MutableLiveData<String>("Clear All")
    val statusMessage = MutableLiveData<Event<String>>()
    var subscriberToUpdateOrDelete: Subscriber? = null

    fun saveOrUpdate() {
        subscriberToUpdateOrDelete?.let {
            it.name = inputName.value ?: ""
            it.email = inputEmail.value ?: ""
            update(it)
        } ?: run {
            insert(Subscriber(0, inputName.value ?: "", inputEmail.value ?: ""))
        }
    }

    fun clearAllOrDelete() {
        subscriberToUpdateOrDelete?.let {
            delete(it)
        } ?: run {
            clearAll()
        }
    }

    fun insert(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(subscriber)
            withContext(Dispatchers.Main) {
                inputName.value = ""
                inputEmail.value = ""
                statusMessage.value = Event("Inserted successfully!")
            }
        }
    }

    fun update(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(subscriber)
            withContext(Dispatchers.Main) {
                inputName.value = ""
                inputEmail.value = ""
                subscriberToUpdateOrDelete = null
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("Updated successfully!")
            }
        }
    }

    fun delete(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(subscriber)
            withContext(Dispatchers.Main) {
                inputName.value = ""
                inputEmail.value = ""
                subscriberToUpdateOrDelete = null
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("Deleted successfully!")
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
            withContext(Dispatchers.Main) {
                statusMessage.value = Event("Cleared successfully!")
            }
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }
}
