package com.example.huc_app.ui.payFees

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.ui.payFees.payFeesUIState.GetPayFeesUIState
import com.example.huc_app.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayFeesViewModel @Inject constructor(
    private val checkConnectivityUseCase: CheckConnectivityUseCase,
) : ViewModel() {

    private val _payFeesUIState = MutableStateFlow(GetPayFeesUIState())
    val payFeesUIState: StateFlow<GetPayFeesUIState> get() = _payFeesUIState

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    init {
        loadWebPage()
    }

    private fun loadWebPage() {
        viewModelScope.launch {
            if (checkConnectivityUseCase()) {
                _payFeesUIState.update { it.copy(isLoading = true, isInternetUnAvailable = false) }

            } else {
                _payFeesUIState.update { it.copy(isLoading = false, isInternetUnAvailable = true) }
            }
        }
    }
}