package com.example.huc_app.ui.bills.billsUIState

data class StudentFeesUIState(
    val payments: List<PaymentUIState> = emptyList(),
    val feesAfterDiscount: Int = 0,
    val feesBeforeDiscount: Int = 0,
    val totalPaid: Int = 0,
    val totalRemain: Int = 0,
    val numberOfPayments: Int = 0,
    val totalFeesPaymentProgress: Int = 0,
)
