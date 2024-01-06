package com.example.huc_app.ui.bills.billsUIState

import com.example.huc_app.domain.types.PaymentStatus


data class PaymentUIState(
    val paid: String = "",
    val remain: String = "",
    val endDate: String = "",
    val paymentNumber: String = "",
    val totalPaymentsNumber: String = "",
    val paymentStatus: PaymentStatus = PaymentStatus.UNPAID,
    val paymentProgress: Int = 0,
)
