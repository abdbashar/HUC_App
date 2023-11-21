package com.example.huc_app.ui.studentDocs.studentDocsUIState

data class StudentDocumentsUIState(
    var documents: List<DocumentUIState> = emptyList(),
    val message: String = ""
)
