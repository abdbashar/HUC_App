package com.example.huc_app.ui.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetRequestsUseCase
import com.example.huc_app.ui.requests.requestsUIState.GetRequestsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestsViewModel @Inject constructor(
    private val getRequestsUseCase: GetRequestsUseCase,
    private val requestUIMapper: RequestUIMapper,
    private val checkConnectivityUseCase: CheckConnectivityUseCase
) : ViewModel() {

    private val _getRequestsUIState = MutableStateFlow(GetRequestsUIState())
    val getRequestsUIState: StateFlow<GetRequestsUIState> get() = _getRequestsUIState

    init {
        getRequests()
    }

     fun getRequests() {
        viewModelScope.launch {
            if (checkConnectivityUseCase()) {
                _getRequestsUIState.update { it.copy(isLoading = true, isInternetUnAvailable = false) }
                try {
                    val requests = requestUIMapper.map(getRequestsUseCase())
                    _getRequestsUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            requests = requests
                        )
                    }
                } catch (e: Exception) {
                    _getRequestsUIState.update {
                        it.copy(
                            isLoading = false,
                            isFailure = true,
                            error = e.message.toString()
                        )
                    }
                }
            } else {
                _getRequestsUIState.update { it.copy(isLoading = false, isInternetUnAvailable = true) }
            }
        }
    }
}