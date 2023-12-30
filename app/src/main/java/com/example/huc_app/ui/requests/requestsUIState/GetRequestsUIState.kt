package com.example.huc_app.ui.requests.requestsUIState

data class GetRequestsUIState(
    val requests: List<RequestUIState> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isInternetUnAvailable: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String = "",
)
