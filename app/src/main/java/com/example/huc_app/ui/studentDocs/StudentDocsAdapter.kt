package com.example.huc_app.ui.studentDocs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
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

    private fun showPopupMenu(anchorView: View, item: DocumentUIState) {
        val inflater = anchorView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.dialog_option_menu, null)

        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        val popupWindow = PopupWindow(popupView, width, height, true)

        popupView.findViewById<View>(R.id.open_doc).setOnClickListener {
             listener.onOpenDoc(item)
            popupWindow.dismiss()
        }

        popupView.findViewById<View>(R.id.download_doc).setOnClickListener {
            listener.onDownloadDoc(item)
            popupWindow.dismiss()
        }
        val offsetX = 0
        val offsetY = -anchorView.height + 200
        popupWindow.showAsDropDown(anchorView, offsetX, offsetY)
    }
}


interface StudentDocsClicksListener : BaseInteractionListener {
    fun onListClick(item: DocumentUIState)
    fun onOpenDoc(item: DocumentUIState)
    fun onDownloadDoc(item: DocumentUIState)
}