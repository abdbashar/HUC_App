package com.example.huc_app.ui.secondarySchoolInfo.secondarySchoolUIState

data class GetUserSecondarySchoolInfoUIState(
    val userSecondarySchoolInfo: UserSecondaryInfoUIState = UserSecondaryInfoUIState(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isInternetUnAvailable: Boolean = false,
    val error: String = ""
)
