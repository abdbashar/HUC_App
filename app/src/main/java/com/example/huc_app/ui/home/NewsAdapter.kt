package com.example.huc_app.ui.home

import com.example.huc_app.R
import com.example.huc_app.ui.base.BaseAdapter
import com.example.huc_app.ui.base.BaseInteractionListener
import com.example.huc_app.ui.home.newsUIState.NewsUIState

class NewsAdapter(
list : List<NewsUIState>,
listener: HomeClicksListener
) : BaseAdapter<NewsUIState>(list,listener) {
    override val layoutID: Int = R.layout.item_home
}

interface HomeClicksListener : BaseInteractionListener {
    fun onListClick(item: NewsUIState)
}