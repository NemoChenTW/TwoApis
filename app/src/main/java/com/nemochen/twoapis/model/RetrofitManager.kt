package com.nemochen.twoapis.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    private val publicStatusService = createService("https://code-test.migoinc-dev.com")
    private val privateStatusService = createService("https://192.168.2.2")

    private fun createService(url: String): StatusService {
        return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(StatusService::class.java)
    }

    fun getPublicAPI(): StatusService {
        return publicStatusService
    }

    fun getPrivateAPI(): StatusService {
        return privateStatusService
    }


}