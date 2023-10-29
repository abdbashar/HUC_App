package com.example.huc_app.ui.signIn

data class SignInUiState(
    val isLoading: Boolean = false,
    var isSuccess: Boolean = false,
    var isError: Boolean = false,
    val error: String = "",
)