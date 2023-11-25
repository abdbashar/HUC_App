package com.example.huc_app.domain.models

data class File (
    val fileUrl: String,
    val fileName: String,
    val thumbnail: String,
    val pageCount: Int
)