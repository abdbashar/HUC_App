package com.example.huc_app.ui.studentID.studentIDStatusUIState

data class StudentIDStatusUIState(
    var directEnrolledDate: String = "",
    var isDirectlyEnrolled: Boolean = false,
    var isStudentIDPrinted: Boolean = false,
    var studentIDExpirationDate: String = ""
)
