package com.example.huc_app.ui.menu.menuUIState

data class GetMenuUIState(
    val studentDetails: StudentDetailsUIState = StudentDetailsUIState(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isUserLoggedIn: Boolean = false,
    val error: String = "",
)
