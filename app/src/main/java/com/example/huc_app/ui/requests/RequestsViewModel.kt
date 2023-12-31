package com.example.huc_app.ui.requests

import androidx.lifecycle.ViewModel
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetRequestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RequestsViewModel @Inject constructor(
    private val getRequestsUseCase: GetRequestsUseCase,
    private val requestUIMapper: RequestUIMapper,
    private val checkConnectivityUseCase: CheckConnectivityUseCase
) : ViewModel() {

}