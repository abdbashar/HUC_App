package com.example.huc_app.ui.bills

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.Payment
import com.example.huc_app.ui.bills.billsUIState.PaymentUIState
import javax.inject.Inject

class PaymentUIMapper @Inject constructor() : Mapper<Payment, PaymentUIState>() {
    override fun map(input: Payment): PaymentUIState {
        return PaymentUIState(
            paid = input.paid.toString(),
            remain = input.remain.toString(),
            endDate = input.endDate ?: "",
        )
    }
}
