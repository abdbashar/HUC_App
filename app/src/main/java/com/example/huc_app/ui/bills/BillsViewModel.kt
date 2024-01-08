package com.example.huc_app.ui.bills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.types.Language
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetPaymentStatusUseCase
import com.example.huc_app.domain.useCases.GetStudentFeesUseCase
import com.example.huc_app.ui.bills.billsUIState.GetStudentFeesUIState
import com.example.huc_app.util.Event
import com.example.huc_app.util.SettingsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillsViewModel @Inject constructor(
    private val getStudentFeesUseCase: GetStudentFeesUseCase,
    private val studentFeesUIMapper: StudentFeesUIMapper,
    private val getPaymentStatusUseCase: GetPaymentStatusUseCase,
    private val checkConnectivityUseCase: CheckConnectivityUseCase
) : ViewModel() {

    private val _studentFeesUIState = MutableStateFlow(GetStudentFeesUIState())
    val studentFeesUIState: StateFlow<GetStudentFeesUIState> get() = _studentFeesUIState

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    private val _currentLanguage = MutableLiveData<Language>()
    val currentLanguage: LiveData<Language> get() = _currentLanguage

    private val settingsService = SettingsService

    init {
        getCurrentAppLanguage()
        getStudentFees()
    }

    private fun getCurrentAppLanguage() {
        _currentLanguage.postValue(settingsService.getCurrentLanguage())
    }

    private fun getStudentFees() {
        viewModelScope.launch {
            if (checkConnectivityUseCase()) {
                _studentFeesUIState.update { it.copy(isLoading = true, isInternetUnAvailable = false) }
                try {
                    val studentFeesResponse = studentFeesUIMapper.map(getStudentFeesUseCase())

                    val updatedPayments =
                        studentFeesResponse.payments.mapIndexed { index, payment ->
                            val paymentStatus =
                                getPaymentStatusUseCase(payment.paid, payment.remain)
                            payment.copy(
                                paymentStatus = paymentStatus,
                                paymentNumber = (index + 1).toString(),
                                totalPaymentsNumber = studentFeesResponse.numberOfPayments.toString(),
                                paymentProgress = getFeesPaymentProgressPercentage(
                                    payment.paid.toInt(),
                                    payment.remain.toInt()
                                )
                            )
                        }

                    val updatedStudentFees = studentFeesResponse.copy(
                        payments = updatedPayments,
                        totalFeesPaymentProgress = getTotalFeesPaymentProgressPercentage(
                            studentFeesResponse.totalPaid,
                            studentFeesResponse.feesAfterDiscount
                        )
                    )

                    _studentFeesUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            studentFees = updatedStudentFees
                        )
                    }
                } catch (e: Throwable) {
                    _studentFeesUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = false,
                            isFailure = true,
                            error = e.message.toString()
                        )
                    }
                }
            } else {
                _studentFeesUIState.update { it.copy(isLoading = false, isInternetUnAvailable = true) }
            }
        }
    }


    fun onNavigateBackClick() {
        _isArrowBackClicked.postValue(Event(true))
    }


    private fun getTotalFeesPaymentProgressPercentage(totalPaid: Int, feesAfterDiscount: Int): Int {
        return (totalPaid.toDouble() / feesAfterDiscount * 100).toInt()
    }

    private fun getFeesPaymentProgressPercentage(paid: Int, remain: Int): Int {
        val totalAmount = paid + remain
        val progressPercentage = if (totalAmount != 0) {
            (paid.toFloat() / totalAmount) * 100
        } else {
            0f
        }

        return progressPercentage.toInt()
    }

    fun getData(){
        viewModelScope.launch {
            _studentFeesUIState.update {
                it.copy(
                    isLoading = false,
                    isSuccess = false,
                    isFailure = false,
                    isInternetUnAvailable = false,
                )
            }
        }
        getStudentFees()
    }
}
