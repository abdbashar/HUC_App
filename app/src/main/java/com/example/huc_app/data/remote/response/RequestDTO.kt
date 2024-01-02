package com.example.huc_app.data.remote.response

data class RequestDTO(
    val created_date: String,
    val id: Int,
    val status: String,
    val title: String,
    val type: String
)