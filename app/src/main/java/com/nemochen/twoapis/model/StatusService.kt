package com.nemochen.twoapis.model

import retrofit2.Response
import retrofit2.http.GET

interface StatusService {

    @GET("echo/get/json")
    suspend fun getStatus(): Response<StatusData>
}