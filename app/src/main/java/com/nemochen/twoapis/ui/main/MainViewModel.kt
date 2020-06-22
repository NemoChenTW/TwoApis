package com.nemochen.twoapis.ui.main

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nemochen.twoapis.model.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.ref.WeakReference
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainViewModel(application: Application, private val statusRepository: StatusRepository) : AndroidViewModel(application) {
    companion object {
        const val LOADING_MESSAGE = "Loading"
        const val FAIL_MESSAGE = "Get status fail"
        const val STATUS_PREFIX_PUBLIC = "Public service: "
        const val STATUS_PREFIX_PRIVATE = "Private service: "
    }

    val publicStatusString = MutableLiveData<String>()
    val privateStatusString = MutableLiveData<String>()

    private val weakContext = WeakReference(application.applicationContext)

    fun getStatus() {
        viewModelScope.launch {
            publicStatusString.value = STATUS_PREFIX_PUBLIC + LOADING_MESSAGE
            privateStatusString.value = STATUS_PREFIX_PRIVATE + LOADING_MESSAGE
            launch(Dispatchers.IO) {

                runBlocking {
                    publicStatusString.postValue(STATUS_PREFIX_PUBLIC + statusRepository.getPublicServiceStatus().status)
                    changeNetWorkToWifi()
                    privateStatusString.postValue(STATUS_PREFIX_PRIVATE + statusRepository.getPrivateServiceStatus().status)
                }
            }
        }
    }

    private suspend fun changeNetWorkToWifi() {
        weakContext.get()?.let {
            val connectivityManager = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val request = NetworkRequest.Builder().apply {
                addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            }

            requestNetWork(connectivityManager, request)?.let { network ->
                connectivityManager.bindProcessToNetwork(network)
            }
        }
    }

    private suspend fun requestNetWork(connectivityManager: ConnectivityManager, request: NetworkRequest.Builder): Network =
        suspendCoroutine {
            connectivityManager.requestNetwork(request.build(),
                object: ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        it.resume(network)
                    }
                })
        }
}