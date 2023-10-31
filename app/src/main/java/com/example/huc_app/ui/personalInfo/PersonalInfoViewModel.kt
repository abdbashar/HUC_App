package com.example.huc_app.ui.personalInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetUserInfoUseCase
import com.example.huc_app.ui.personalInfo.userInfoUIState.GetUserInfoUIState
import com.example.huc_app.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalInfoViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val userInfoUIMapper: UserInfoUIMapper,
    private val checkConnectivityUseCase: CheckConnectivityUseCase
) : ViewModel() {

    private val _userInfoUIState = MutableStateFlow(GetUserInfoUIState())
    val userInfoUIState: StateFlow<GetUserInfoUIState> get() = _userInfoUIState

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    init {
        getPersonalInfo()
    }

    private fun getPersonalInfo() {
        viewModelScope.launch {
            if (checkConnectivityUseCase()) {
                try {
                    _userInfoUIState.update { it.copy(isLoading = true, isInternetUnAvailable = false) }
                    val userInfo = userInfoUIMapper.map(getUserInfoUseCase())
                    _userInfoUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            isFailure = false,
                            userInfo = userInfo
                        )
                    }
                } catch (e: Throwable) {
                    _userInfoUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = false,
                            isFailure = true,
                            error = e.message.toString()
                        )
                    }
                }
            } else {
                _userInfoUIState.update { it.copy(isLoading = false, isInternetUnAvailable = true) }
            }
        }
    }

    fun getData(){
        viewModelScope.launch {
            _userInfoUIState.update {
                it.copy(
                    isLoading = false,
                    isSuccess = false,
                    isFailure = false,
                    isInternetUnAvailable = false,
                )
            }
        }
        getPersonalInfo()
    }

    fun onNavigateBackClick() {
        _isArrowBackClicked.postValue(Event(true))
    }
}