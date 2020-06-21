package com.nemochen.twoapis.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemochen.twoapis.model.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val statusRepository: StatusRepository) : ViewModel() {
    companion object {
        const val LOADING_MESSAGE = "Loading"
        const val FAIL_MESSAGE = "Get status fail"
        const val STATUS_PREFIX = "Service status: "
    }

    val statusString = MutableLiveData<String>()

    fun getStatus() {
        viewModelScope.launch {
            statusString.value = LOADING_MESSAGE
            launch(Dispatchers.IO) {
                statusString.postValue(STATUS_PREFIX + statusRepository.getStatus().status)
            }
        }
    }

}