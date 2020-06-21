package com.nemochen.twoapis.model

import kotlinx.coroutines.delay

class StatusRepository {
    companion object {
        const val ERROR_STRING = "Error"
    }

    private val statusService = RetrofitManager.getPublicAPI()

    suspend fun getStatus(): StatusData {
        delay(2000)

        return if (statusService.getStatus().isSuccessful) {
            statusService.getStatus().body()!!
        } else {
            // TODO: The error can handle by adding CallAdapterFactory to Retrofit
            StatusData(ERROR_STRING)
        }
    }
}