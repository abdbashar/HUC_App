package com.example.huc_app.data.remote.response

import com.google.gson.annotations.SerializedName

data class StudentFeesDTO(
    @SerializedName("fee")
    var fees: Int,

    @SerializedName("fee_after_discount")
    var feesAfterDiscount: Int,

    @SerializedName("number_of_payments")
    var numberOfPayments: Int,

    @SerializedName("paid")
    var totalPaid: Int,

    @SerializedName("payments")
    var payments: List<StudentPaymentDTO>,

    @SerializedName("remain")
    var totalRemain: Int
)
