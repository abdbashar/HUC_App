package com.example.huc_app.ui.studentDocs.studentDocsUIState


data class GetStudentDocsUIState(
    val studentDocs: StudentDocumentsUIState = StudentDocumentsUIState(),
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isInternetUnAvailable: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String = ""
)
