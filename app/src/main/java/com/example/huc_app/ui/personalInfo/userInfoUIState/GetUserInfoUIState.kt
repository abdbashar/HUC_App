package com.example.huc_app.ui.personalInfo.userInfoUIState

data class GetUserInfoUIState(
    val userInfo: UserInfoUIState = UserInfoUIState(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isInternetUnAvailable: Boolean = false,
    val error: String = ""
)
