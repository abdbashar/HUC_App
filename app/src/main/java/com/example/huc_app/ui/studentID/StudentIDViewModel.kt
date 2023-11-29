package com.example.huc_app.ui.studentID

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.types.Language
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetStudentDetailsUseCase
import com.example.huc_app.domain.useCases.GetStudentIDStatusUseCase
import com.example.huc_app.ui.studentID.studentIDStatusMapper.StudentDetailsUIMapper
import com.example.huc_app.ui.studentID.studentIDStatusMapper.StudentIDStatusUIMapper
import com.example.huc_app.ui.studentID.studentIDStatusUIState.GetStudentIDStatusUIState
import com.example.huc_app.util.Event
import com.example.huc_app.util.SettingsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentIDViewModel @Inject constructor(
    private val getStudentDetailsUseCase: GetStudentDetailsUseCase,
    private val studentDetailsUIMapper: StudentDetailsUIMapper,
    private val getStudentIDStatusUseCase: GetStudentIDStatusUseCase,
    private val studentIDStatusUIMapper: StudentIDStatusUIMapper,
    private val checkConnectivityUseCase: CheckConnectivityUseCase
) : ViewModel() {

    private val _studentIDStatus = MutableStateFlow(GetStudentIDStatusUIState())
    val studentIDStatus: StateFlow<GetStudentIDStatusUIState> get() = _studentIDStatus

    private val _currentLanguage = MutableLiveData<Language>()
    val currentLanguage: LiveData<Language> get() = _currentLanguage

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    private val settingsService = SettingsService

    init {
        getCurrentAppLanguage()
        getUserProfile()
        getStudentIDStatus()
    }

    private fun getCurrentAppLanguage() {
        _currentLanguage.postValue(settingsService.getCurrentLanguage())
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            val userDetails = studentDetailsUIMapper.map(getStudentDetailsUseCase())
            _studentIDStatus.update {
                it.copy(
                    studentDetails = userDetails
                )
            }
        }
    }

    private fun getStudentIDStatus() {
        viewModelScope.launch {
            if (checkConnectivityUseCase()) {
                _studentIDStatus.update { it.copy(isLoading = true, isInternetUnAvailable = false) }
                try {
                    val studentIDStatus = studentIDStatusUIMapper.map(getStudentIDStatusUseCase())
                    _studentIDStatus.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            studentIDStatusUIState = it.studentIDStatusUIState.copy(
                                directEnrolledDate = studentIDStatus.directEnrolledDate,
                                isDirectlyEnrolled = studentIDStatus.isDirectlyEnrolled,
                                isStudentIDPrinted = studentIDStatus.isStudentIDPrinted,
                                studentIDExpirationDate = studentIDStatus.studentIDExpirationDate
                            )
                        )
                    }
                } catch (e: Exception) {
                    _studentIDStatus.update {
                        it.copy(
                            isLoading = false,
                            isFailure = true,
                            error = e.message.toString()
                        )
                    }
                }
            } else {
                _studentIDStatus.update { it.copy(isLoading = false, isInternetUnAvailable = true) }
            }
        }
    }

    fun onNavigateBackClick() {
        _isArrowBackClicked.postValue(Event(true))
    }

    fun getData() {
        viewModelScope.launch {
            _studentIDStatus.update {
                it.copy(
                    isLoading = false,
                    isSuccess = false,
                    isFailure = false,
                    isInternetUnAvailable = false,
                )
            }
        }
        getUserProfile()
        getStudentIDStatus()
    }
}