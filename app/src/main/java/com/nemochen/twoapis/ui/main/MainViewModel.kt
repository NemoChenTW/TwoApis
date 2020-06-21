package com.nemochen.twoapis.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nemochen.twoapis.model.RetrofitManager
import com.nemochen.twoapis.model.StatusData
import com.nemochen.twoapis.model.StatusRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val statusRepository: StatusRepository) : ViewModel() {
    companion object {
        const val LOADING_MESSAGE = "Loading"
        const val FAIL_MESSAGE = "Get status fail"
        const val STATUS_PREFIX = "Service status: "
    }

    val statusString = liveData(Dispatchers.IO) {
        emit(LOADING_MESSAGE)
        var resultString = STATUS_PREFIX + statusRepository.getStatus().status
        emit(resultString)
    }
}