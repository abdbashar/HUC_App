package com.example.huc_app.ui.studentDocs

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.File
import com.example.huc_app.ui.studentDocs.studentDocsUIState.DocumentUIState
import javax.inject.Inject

class DocumentUIMapper @Inject constructor(): Mapper<File, DocumentUIState>() {
    override fun map(input: File): DocumentUIState {
        return DocumentUIState(
            documentUrl = input.fileUrl,
            documentName = input.fileName,
            pageCount = input.pageCount,
            thumbnailDoc = input.thumbnail
        )
    }
}