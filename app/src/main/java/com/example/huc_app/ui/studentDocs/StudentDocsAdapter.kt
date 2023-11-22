package com.example.huc_app.ui.studentDocs

import com.example.huc_app.R
import com.example.huc_app.ui.base.BaseAdapter
import com.example.huc_app.ui.base.BaseInteractionListener
import com.example.huc_app.ui.studentDocs.studentDocsUIState.DocumentUIState

class StudentDocsAdapter(
    list: List<DocumentUIState>,
    private val listener: StudentDocsClicksListener
) : BaseAdapter<DocumentUIState>(list, listener) {
    override val layoutID: Int = R.layout.item_document
}

interface StudentDocsClicksListener : BaseInteractionListener {
}