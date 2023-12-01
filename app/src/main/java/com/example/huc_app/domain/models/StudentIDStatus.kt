package com.example.huc_app.domain.models

data class StudentIDStatus(
    val directEnrolledDate: String,
    val isDirectlyEnrolled: Boolean,
    val isStudentIDPrinted: Boolean,
    val idExpirationDate: String,
)
