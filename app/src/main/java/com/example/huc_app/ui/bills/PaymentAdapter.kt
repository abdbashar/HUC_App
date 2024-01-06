package com.example.huc_app.ui.bills

import com.example.huc_app.R
import com.example.huc_app.ui.base.BaseAdapter
import com.example.huc_app.ui.base.BaseInteractionListener
import com.example.huc_app.ui.bills.billsUIState.PaymentUIState

class PaymentAdapter(
    list : List<PaymentUIState>,
    listener: BillsClicksListener
) : BaseAdapter<PaymentUIState>(list,listener) {
    override val layoutID: Int = R.layout.item_payment
}

interface BillsClicksListener : BaseInteractionListener {
    fun onListClick(item: PaymentUIState)
}