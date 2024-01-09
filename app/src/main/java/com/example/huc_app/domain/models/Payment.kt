package com.example.huc_app.domain.models

data class Payment(
    val endDate: String,
    val paid: Int,
    val remain: Int
)
