package com.nemochen.twoapis.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://reqbin.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val statusService = retrofit.create(
        StatusService::class.java)

    fun getAPI(): StatusService {
        return statusService
    }


}