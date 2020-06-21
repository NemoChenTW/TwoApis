package com.nemochen.twoapis.model

import kotlinx.coroutines.delay

class StatusRepository {
    private val statusService = RetrofitManager.getAPI()

    suspend fun getStatus(): StatusData {
        delay(2000)
        return statusService.getStatus()
    }
}