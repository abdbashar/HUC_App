package com.example.huc_app.ui.secondarySchoolInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetUserSecondarySchoolInfoUseCase
import com.example.huc_app.util.Event
import com.example.huc_app.ui.secondarySchoolInfo.secondarySchoolUIState.GetUserSecondarySchoolInfoUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondarySchoolInfoViewModel @Inject constructor(
    private val getUserSecondarySchoolInfoUseCase: GetUserSecondarySchoolInfoUseCase,
    private val getUserSecondarySchoolUIMapper: UserSecondarySchoolUIMapper,
    private val checkConnectivityUseCase: CheckConnectivityUseCase
) : ViewModel() {

    private val _userSecondarySchoolInfoUIState =
        MutableStateFlow(GetUserSecondarySchoolInfoUIState())
    val userSecondarySchoolInfoUIState: StateFlow<GetUserSecondarySchoolInfoUIState> get() = _userSecondarySchoolInfoUIState

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    init {
        getUserSecondarySchoolInfo()
    }

    private fun getUserSecondarySchoolInfo() {
        viewModelScope.launch {
            if (checkConnectivityUseCase()) {
                try {
                    _userSecondarySchoolInfoUIState.update { it.copy(isLoading = true, isInternetUnAvailable = false) }
                    val userSecondarySchoolInfo =
                        getUserSecondarySchoolUIMapper.map(getUserSecondarySchoolInfoUseCase())
                    _userSecondarySchoolInfoUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            isFailure = false,
                            userSecondarySchoolInfo = userSecondarySchoolInfo
                        )
                    }
                } catch (e: Throwable) {
                    _userSecondarySchoolInfoUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = false,
                            isFailure = true,
                            error = e.message.toString()
                        )
                    }
                }
            } else {
                _userSecondarySchoolInfoUIState.update { it.copy(isLoading = false, isInternetUnAvailable = true) }
            }
        }
    }

    fun getData() {
        viewModelScope.launch {
            _userSecondarySchoolInfoUIState.update {
                it.copy(
                    isLoading = false,
                    isSuccess = false,
                    isFailure = false,
                    isInternetUnAvailable = false,
                )
            }
        }
        getUserSecondarySchoolInfo()
    }

    fun onNavigateBackClick() {
        _isArrowBackClicked.postValue(Event(true))
    }
}