package com.nemochen.twoapis.ui.main

import com.nemochen.twoapis.model.StatusData
import retrofit2.Call
import retrofit2.http.GET

interface StatusService {

    @GET("echo/get/json")
    fun getStatus(): Call<StatusData>
}