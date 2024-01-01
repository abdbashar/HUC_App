package com.example.huc_app.domain.models

data class Request(
    val id: Int,
    val status: String,
    val addressedTo: String,
    val requestType: String,
    val date: String,
)
