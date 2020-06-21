package com.nemochen.twoapis.model

import retrofit2.Call
import retrofit2.http.GET

interface StatusService {

    @GET("echo/get/json")
    fun getStatus(): Call<StatusData>
}