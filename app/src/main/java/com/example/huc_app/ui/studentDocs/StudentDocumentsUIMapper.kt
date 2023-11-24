package com.example.huc_app.ui.studentDocs

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.StudentDocuments
import com.example.huc_app.ui.studentDocs.studentDocsUIState.StudentDocumentsUIState
import javax.inject.Inject

class StudentDocumentsUIMapper @Inject constructor(
    private val documentUIMapper: DocumentUIMapper
): Mapper<StudentDocuments, StudentDocumentsUIState>() {
    override fun map(input: StudentDocuments): StudentDocumentsUIState {
        val documents = input.documents.map { documentUIMapper.map(it) }
        return StudentDocumentsUIState(
            documents = documents,
            message = input.message
        )
    }
}