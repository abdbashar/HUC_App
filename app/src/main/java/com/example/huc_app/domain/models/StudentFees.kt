package com.example.huc_app.domain.models

data class StudentFees(
    val fees: Int,
    val feesAfterDiscount: Int,
    val numberOfPayments: Int,
    val totalPaid: Int,
    val payments: List<Payment>,
    val totalRemain: Int
)