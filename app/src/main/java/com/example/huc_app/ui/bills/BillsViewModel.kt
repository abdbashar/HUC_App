package com.example.huc_app.ui.bills

import androidx.lifecycle.ViewModel
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetPaymentStatusUseCase
import com.example.huc_app.domain.useCases.GetStudentFeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BillsViewModel @Inject constructor(
    private val getStudentFeesUseCase: GetStudentFeesUseCase,
    private val studentFeesUIMapper: StudentFeesUIMapper,
    private val getPaymentStatusUseCase: GetPaymentStatusUseCase,
    private val checkConnectivityUseCase: CheckConnectivityUseCase
) : ViewModel() {

}
