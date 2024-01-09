package com.example.huc_app.domain.mappers

import com.example.huc_app.data.remote.response.FeesDTO
import com.example.huc_app.domain.models.StudentFees
import javax.inject.Inject

class StudentFeesMapper @Inject constructor(
    private val paymentMapper: PaymentMapper
) : Mapper<FeesDTO, StudentFees>() {
    override fun map(input: FeesDTO): StudentFees {
        val payments = input.payments?.map { paymentMapper.map(it!!) } ?: emptyList()
        return StudentFees(
            fees = input.fee ?: 0,
            feesAfterDiscount = input.fee_after_discount ?: 0,
            numberOfPayments = input.number_of_payments ?: 0,
            totalPaid = input.paid ?: 0,
            payments = payments,
            totalRemain = input.remain ?: 0
        )
    }
}