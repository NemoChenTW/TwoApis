package com.nemochen.twoapis.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nemochen.twoapis.model.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.ref.WeakReference

class MainViewModel(application: Application, private val statusRepository: StatusRepository) : AndroidViewModel(application) {
    companion object {
        const val LOADING_MESSAGE = "Loading"
        const val FAIL_MESSAGE = "Get status fail"
        const val STATUS_PREFIX_PUBLIC = "Public service: "
        const val STATUS_PREFIX_PRIVATE = "Private service: "
    }

    val publicStatusString = MutableLiveData<String>()
    val privateStatusString = MutableLiveData<String>()

    val weakContext = WeakReference(application.applicationContext)

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