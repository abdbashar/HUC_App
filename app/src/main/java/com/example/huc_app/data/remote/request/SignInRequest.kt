package com.example.huc_app.data.remote.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("device_type") val deviceType: String,
    @SerializedName("token") val token: String
)
