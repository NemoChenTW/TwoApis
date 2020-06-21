package com.nemochen.twoapis.model

import com.google.gson.annotations.SerializedName

data class StatusData(
    @SerializedName("success")
    val status: String
)
