package com.example.huc_app.ui.requests

import com.example.huc_app.R
import com.example.huc_app.ui.base.BaseAdapter
import com.example.huc_app.ui.base.BaseInteractionListener
import com.example.huc_app.ui.requests.requestsUIState.RequestUIState

class RequestsAdapter(
    list: List<RequestUIState>,
    listener: RequestsClicksListener
) : BaseAdapter<RequestUIState>(list, listener) {

    override val layoutID: Int = R.layout.item_request
}

interface RequestsClicksListener : BaseInteractionListener {
    fun onListClick(item: RequestUIState)
}