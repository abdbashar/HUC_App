package com.example.huc_app.domain.models

data class UserInfo(
    val id: Int,
    val username: String,
    val fullName: String,
    val motherName: String,
    val birthYear: Int,
    val address: String,
    val personalPhoneNumber: String,
    val parentPhoneNumber: String,
    val gender: String,
    val nationality: Int,
    val religion: Int,
    val nationalism: Int,
    val governorate: String
)