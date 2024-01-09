package com.example.huc_app.domain.mappers

import com.example.huc_app.data.remote.response.PaymentDTO
import com.example.huc_app.domain.models.Payment
import javax.inject.Inject

class PaymentMapper @Inject constructor() : Mapper<PaymentDTO, Payment>() {
    override fun map(input: PaymentDTO): Payment {
        return Payment(
            endDate = input.date ?: "",
            paid = input.paid ?: 0,
            remain = input.reamin ?: 0
        )
    }
}
