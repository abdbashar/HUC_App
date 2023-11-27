package com.example.huc_app.ui.studentID.studentIDStatusUIState

data class GetStudentIDStatusUIState(
    val studentDetails: StudentDetailsUIState = StudentDetailsUIState(),
    val studentIDStatusUIState: StudentIDStatusUIState = StudentIDStatusUIState(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isInternetUnAvailable: Boolean = false,
    val error: String = ""
)
