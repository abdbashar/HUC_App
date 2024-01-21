package com.example.huc_app.data.remote.response

import com.google.gson.annotations.SerializedName

data class StudentPaymentDTO(
    @SerializedName("date")
    var endDate: String,

    @SerializedName("paid")
    var paid: Int,

    @SerializedName("remain")
    var remain: Int
)
