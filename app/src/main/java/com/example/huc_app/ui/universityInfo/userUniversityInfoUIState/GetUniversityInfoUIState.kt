package com.example.huc_app.ui.universityInfo.userUniversityInfoUIState

data class GetUniversityInfoUIState (
    val userUniversityInfo: UserUniversityInfoUIState = UserUniversityInfoUIState(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isInternetUnAvailable: Boolean = false,
    val error: String = ""
)
