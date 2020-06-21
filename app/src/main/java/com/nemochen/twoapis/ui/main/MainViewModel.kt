package com.nemochen.twoapis.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemochen.twoapis.model.StatusData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    companion object {
        const val FAIL_MESSAGE = "Get status fail"
    }

    var statusString = MutableLiveData<String>()

    fun getStatus() {
        RetrofitManager.getAPI().getStatus().enqueue(object : Callback<StatusData> {
            override fun onFailure(call: Call<StatusData>, t: Throwable) {
                statusString.value = FAIL_MESSAGE
            }

            override fun onResponse(call: Call<StatusData>, response: Response<StatusData>) {
                statusString.value = response.body()!!.status
            }
        })
    }

}