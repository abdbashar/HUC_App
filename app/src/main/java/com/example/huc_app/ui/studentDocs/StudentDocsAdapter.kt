package com.example.huc_app.ui.studentDocs

import android.view.View
import com.example.huc_app.R
import com.example.huc_app.ui.base.BaseAdapter
import com.example.huc_app.ui.base.BaseInteractionListener
import com.example.huc_app.ui.studentDocs.studentDocsUIState.DocumentUIState

class StudentDocsAdapter(
    list: List<DocumentUIState>,
    private val listener: StudentDocsClicksListener
) : BaseAdapter<DocumentUIState>(list, listener) {
    override val layoutID: Int = R.layout.item_document

    override fun bind(holder: ItemViewHolder, position: Int) {
        super.bind(holder, position)

        holder.itemView.findViewById<View>(R.id.kebabMenu).setOnClickListener { view ->
            showPopupMenu(view, getItemAt(position))
        }
    }
}


interface StudentDocsClicksListener : BaseInteractionListener {
    fun onListClick(item: DocumentUIState)
    fun onOpenDoc(item: DocumentUIState)
    fun onDownloadDoc(item: DocumentUIState)
}