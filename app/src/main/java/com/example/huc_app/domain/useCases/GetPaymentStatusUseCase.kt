package com.example.huc_app.domain.useCases

import com.example.huc_app.domain.types.PaymentStatus
import javax.inject.Inject

class GetPaymentStatusUseCase @Inject constructor() {
    operator fun invoke(paid: String, remain: String): PaymentStatus {
        val paidAmount = paid.toInt()
        val remainAmount = remain.toInt()

        return when {
            paidAmount ==0 -> PaymentStatus.UNPAID
            remainAmount == 0 -> PaymentStatus.FULLY_PAID
            else -> PaymentStatus.PARTIALLY_PAID
        }
    }
}
