package com.nemochen.twoapis.model

import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException

class StatusRepository {
    companion object {
        const val NETWORK_ERROR_STRING = "Network Error"
        const val UNKNOWN_ERROR_STRING = "Unknown Error"
    }

    private val publicStatusService = RetrofitManager.getPublicAPI()
    private val privateStatusService = RetrofitManager.getPrivateAPI()

    suspend fun getPublicServiceStatus(): StatusData {
        return getStatus(publicStatusService)
    }
    suspend fun getPrivateServiceStatus(): StatusData {
        return getStatus(privateStatusService)
    }


    private suspend fun getStatus(stateService: StatusService): StatusData {
        delay(2000)

        try {
            return if (stateService.getStatus().isSuccessful) {
                stateService.getStatus().body()!!
            } else {
                // TODO: The error can handle by adding CallAdapterFactory to Retrofit
                StatusData(NETWORK_ERROR_STRING)
            }
        } catch (ex: Exception) {
            return when (ex) {
                is HttpException,
                is SocketTimeoutException -> {
                    StatusData(NETWORK_ERROR_STRING)
                }
                else -> StatusData(UNKNOWN_ERROR_STRING)
            }
        }
    }
}