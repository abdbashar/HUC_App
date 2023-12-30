package com.example.huc_app.ui.requests.requestsUIState

data class RequestUIState(
    val id: Int = 0,
    val requestType: String = "",
    val status: String = "",
    val addressedTo: String = "",
    val date: String = "",
)