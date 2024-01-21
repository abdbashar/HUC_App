package com.example.huc_app.data.remote.response

data class FeesDTO(
    var fee: Int?,
    var fee_after_discount: Int?,
    var number_of_payments: Int?,
    var paid: Int?,
    var payments: List<PaymentDTO?>?,
    var remain: Int?
)