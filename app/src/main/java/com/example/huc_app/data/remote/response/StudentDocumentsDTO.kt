package com.example.huc_app.data.remote.response

data class StudentDocumentsDTO(
    val documents: List<FileDTO>,
    val msg: String
)