package com.example.huc_app.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserUniversityInfoDTO(
    @SerializedName("dep")
    val department: String,
    @SerializedName("edu")
    val classType: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("start_year")
    val startYear: String,
    @SerializedName("univenumber")
    val universityNumber: String,
    @SerializedName("window")
    val window: String,
)