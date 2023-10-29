package com.example.huc_app.ui.home

import androidx.lifecycle.*
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetUniversityNewsUseCase
import com.example.huc_app.util.Event
import com.example.huc_app.ui.home.newsUIState.GetNewsUIState
import com.example.huc_app.ui.home.newsUIState.NewsUIState
import com.example.huc_app.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUniversityNewsUseCase: GetUniversityNewsUseCase,
    private val newsUIMapper: NewsUIMapper,
    private val checkConnectivityUseCase: CheckConnectivityUseCase
) : ViewModel(), HomeClicksListener {

    private val _newsUIState = MutableStateFlow(GetNewsUIState())
    val newsUIState: StateFlow<GetNewsUIState> get() = _newsUIState

    private val _newsDetails = MutableLiveData<Event<NewsUIState>>()
    val newsDetails: LiveData<Event<NewsUIState>> get() = _newsDetails

    init {
        getUniversityNews()
    }

    private suspend fun isInternetAvailable(): Boolean = checkConnectivityUseCase()

    private fun getUniversityNews() {
        viewModelScope.launch {
            if (isInternetAvailable()) {
                try {
                    _newsUIState.update { it.copy(isInternetUnAvailable = false,isLoading = true) }
                    val news = getUniversityNewsUseCase().map {
                        newsUIMapper.map(it)
                    }
                    _newsUIState.update {
                        it.copy(
                            isLoading = false,
                            isEmpty = news.isEmpty(),
                            isSuccess = true,
                            isFailure = false,
                            news = news
                        )
                    }
                } catch (e: Throwable) {
                    _newsUIState.update {
                        it.copy(
                            isInternetUnAvailable = false,
                            isLoading = false,
                            isSuccess = false,
                            isFailure = true,
                            error = e.message.toString()
                        )
                    }
                }
            } else {
                _newsUIState.update { it.copy(isLoading = false,isInternetUnAvailable = true) }
            }
        }
    }

    fun getData() {
        viewModelScope.launch {
            _newsUIState.update {
                it.copy(
                    isLoading = false,
                    isInternetUnAvailable = false,
                    isSuccess = false,
                    isFailure = false,
                )
            }
        }
        getUniversityNews()
    }

    override fun onListClick(item: NewsUIState) {
        _newsDetails.postEvent(item)
    }

}