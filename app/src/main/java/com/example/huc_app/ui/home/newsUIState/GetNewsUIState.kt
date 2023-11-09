package com.example.huc_app.ui.home.newsUIState

data class GetNewsUIState(
    val news: List<NewsUIState> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isEmpty: Boolean = false,
    val isInternetUnAvailable: Boolean = false,
    val error: String = ""
)
