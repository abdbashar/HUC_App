package com.example.huc_app.ui.newsDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.huc_app.util.Event
import com.example.huc_app.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = NewsDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    private val _newsDetailsUIState = MutableStateFlow(NewsDetailsUIState())
    val newsDetailsUIState: StateFlow<NewsDetailsUIState> get() = _newsDetailsUIState

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    init {
        getData()
    }

    private fun getData() {
        _newsDetailsUIState.update {
            it.copy(
                imagePath = args.imagePath,
                title = args.title,
                details = args.details
            )
        }
    }

    fun onNavigateBackClick() {
        _isArrowBackClicked.postEvent(true)
    }

}