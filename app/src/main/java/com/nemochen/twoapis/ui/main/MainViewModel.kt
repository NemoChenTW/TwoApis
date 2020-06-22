package com.nemochen.twoapis.ui.main

import androidx.lifecycle.*
import com.nemochen.twoapis.model.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private val statusRepository: StatusRepository) : ViewModel() {
    companion object {
        const val LOADING_MESSAGE = "Loading"
        const val FAIL_MESSAGE = "Get status fail"
        const val STATUS_PREFIX_PUBLIC = "Public service: "
        const val STATUS_PREFIX_PRIVATE = "Private service: "
    }

    val publicStatusString = MutableLiveData<String>()
    val privateStatusString = MutableLiveData<String>()

    fun getStatus() {
        viewModelScope.launch {
            publicStatusString.value = STATUS_PREFIX_PUBLIC + LOADING_MESSAGE
            privateStatusString.value = STATUS_PREFIX_PRIVATE + LOADING_MESSAGE
            launch(Dispatchers.IO) {
                runBlocking {
                    publicStatusString.postValue(STATUS_PREFIX_PUBLIC + statusRepository.getPublicServiceStatus().status)
                }

                runBlocking {
                    privateStatusString.postValue(STATUS_PREFIX_PRIVATE + statusRepository.getPrivateServiceStatus().status)
                }
            }
        }
    }

}