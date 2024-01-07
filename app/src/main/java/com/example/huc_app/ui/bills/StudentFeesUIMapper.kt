package com.example.huc_app.ui.bills

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.StudentFees
import com.example.huc_app.ui.bills.billsUIState.StudentFeesUIState
import javax.inject.Inject

class StudentFeesUIMapper @Inject constructor(
    private val paymentUIMapper: PaymentUIMapper
) : Mapper<StudentFees, StudentFeesUIState>() {
    override fun map(input: StudentFees): StudentFeesUIState {
        val paymentsUIState = input.payments.map { paymentUIMapper.map(it) } ?: emptyList()
        return StudentFeesUIState(
            payments = paymentsUIState,
            feesAfterDiscount = input.feesAfterDiscount ?: 0,
            feesBeforeDiscount = if(input.feesAfterDiscount < input.fees) input.fees else 0 ?: 0,
            totalPaid = input.totalPaid ?: 0,
            totalRemain = input.totalRemain ?: 0,
            numberOfPayments = input.numberOfPayments ?: 0,
        )
    }
}