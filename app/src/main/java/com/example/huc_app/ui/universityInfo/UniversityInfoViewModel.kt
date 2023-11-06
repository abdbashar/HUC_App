package com.example.huc_app.ui.universityInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetUserUniversityInfoIUseCase
import com.example.huc_app.ui.universityInfo.userUniversityInfoUIState.GetUniversityInfoUIState
import com.example.huc_app.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityInfoViewModel @Inject constructor(
    private val getUserUniversityInfoIUseCase: GetUserUniversityInfoIUseCase,
    private val getUserUniversityUIMapper: UserUniversityUIMapper,
    private val checkConnectivityUseCase: CheckConnectivityUseCase,
) : ViewModel() {

    private val _userUniversityInfoUIState =
        MutableStateFlow(GetUniversityInfoUIState())
    val userUniversityInfoUIState: StateFlow<GetUniversityInfoUIState> get() = _userUniversityInfoUIState

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    init {
        getUserUniversityInfo()
    }

    private fun getUserUniversityInfo() {
        viewModelScope.launch {
            if (checkConnectivityUseCase()) {
                try {
                    _userUniversityInfoUIState.update { it.copy(isLoading = true, isInternetUnAvailable = false) }
                    val userUniversityInfo =
                        getUserUniversityUIMapper.map(getUserUniversityInfoIUseCase())
                    _userUniversityInfoUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            isFailure = false,
                            userUniversityInfo = userUniversityInfo
                        )
                    }
                } catch (e: Throwable) {
                    _userUniversityInfoUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = false,
                            isFailure = true,
                            error = e.message.toString()
                        )
                    }
                }
            } else {
                _userUniversityInfoUIState.update { it.copy(isLoading = false, isInternetUnAvailable = true) }
            }
        }
    }

    fun getData() {
        viewModelScope.launch {
            _userUniversityInfoUIState.update {
                it.copy(
                    isLoading = false,
                    isSuccess = false,
                    isFailure = false,
                    isInternetUnAvailable = false
                )
            }
        }
        getUserUniversityInfo()
    }

    fun onNavigateBackClick() {
        _isArrowBackClicked.postValue(Event(true))
    }
}