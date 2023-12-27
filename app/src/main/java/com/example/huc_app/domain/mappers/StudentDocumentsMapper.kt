package com.example.huc_app.domain.mappers

import com.example.huc_app.data.remote.response.StudentDocumentsDTO
import com.example.huc_app.domain.models.StudentDocuments
import javax.inject.Inject

class StudentDocumentsMapper @Inject constructor(
    private val fileMapper: FileMapper,
) :
    Mapper<StudentDocumentsDTO, StudentDocuments>() {
    override fun map(input: StudentDocumentsDTO): StudentDocuments {
        val documents = input.documents.map { fileMapper.map(it) } ?: emptyList()
        return StudentDocuments(
            documents = documents,
            message = input.msg ?: ""
        )
    }
}