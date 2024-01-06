package com.example.huc_app.ui.bills.billsUIState

data class GetStudentFeesUIState(
    val studentFees: StudentFeesUIState = StudentFeesUIState(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isInternetUnAvailable: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String = "",
)
