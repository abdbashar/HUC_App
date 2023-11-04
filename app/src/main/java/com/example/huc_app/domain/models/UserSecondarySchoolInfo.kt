package com.example.huc_app.domain.models

data class UserSecondarySchoolInfo(
    val schoolName: String,
    val directorate: String,
    val graduationRecordNumber: String,
    val graduationRecordDate: String,
    val branch: String,
    val attempt: String,
    val secondarySchoolCertificate: String
)
