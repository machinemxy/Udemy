package com.mxy.roomdemo

import android.util.Patterns
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
        val inputNameValue = inputName.value ?: ""
        val inputEmailValue = inputEmail.value ?: ""
        if (inputNameValue.isBlank()) {
            statusMessage.value = Event("Please input name!")
        } else if (inputEmailValue.isBlank()) {
            statusMessage.value = Event("Please input email!")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmailValue).matches()) {
            statusMessage.value = Event("Invalid email!")
        } else {
            subscriberToUpdateOrDelete?.let {
                it.name = inputNameValue
                it.email = inputEmailValue
                update(it)
            } ?: run {
                insert(Subscriber(0, inputNameValue, inputEmailValue))
            }
        }
    }

    fun clearAllOrDelete() {
        subscriberToUpdateOrDelete?.let {
            delete(it)
        } ?: run {
            clearAll()
        }
    }

    private fun insert(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            val newRowId = repository.insert(subscriber)
            withContext(Dispatchers.Main) {
                inputName.value = ""
                inputEmail.value = ""
                if (newRowId > -1) {
                    statusMessage.value = Event("Inserted successfully! Id: $newRowId")
                } else {
                    statusMessage.value = Event("Insert failed!")
                }
            }
        }
    }

    private fun update(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfRows = repository.update(subscriber)
            withContext(Dispatchers.Main) {
                inputName.value = ""
                inputEmail.value = ""
                subscriberToUpdateOrDelete = null
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                if (numberOfRows > 0) {
                    statusMessage.value = Event("$numberOfRows rows were updated successfully!")
                } else {
                    statusMessage.value = Event("Update failed!")
                }
            }
        }
    }

    private fun delete(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfRows = repository.delete(subscriber)
            withContext(Dispatchers.Main) {
                inputName.value = ""
                inputEmail.value = ""
                subscriberToUpdateOrDelete = null
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                if (numberOfRows > 0) {
                    statusMessage.value = Event("$numberOfRows rows were deleted successfully!")
                } else {
                    statusMessage.value = Event("Delete failed!")
                }
            }
        }
    }

    private fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfRows = repository.deleteAll()
            withContext(Dispatchers.Main) {
                statusMessage.value = Event("$numberOfRows rows were deleted successfully!")
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
