package com.example.huc_app.ui.studentDocs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.useCases.CheckConnectivityUseCase
import com.example.huc_app.domain.useCases.GetStudentDocumentsUseCase
import com.example.huc_app.ui.studentDocs.studentDocsUIState.GetStudentDocsUIState
import com.example.huc_app.ui.studentDocs.studentDocsUIState.DocumentUIState
import com.example.huc_app.util.Event
import com.example.huc_app.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentDocsViewModel @Inject constructor(
    private val getStudentDocumentsUseCase: GetStudentDocumentsUseCase,
    private val studentDocumentsUIMapper: StudentDocumentsUIMapper,
    private val checkConnectivityUseCase: CheckConnectivityUseCase,
) : ViewModel(), StudentDocsClicksListener {

    private val _studentDocsUIState = MutableStateFlow(GetStudentDocsUIState())
    val studentDocsUIState: StateFlow<GetStudentDocsUIState> get() = _studentDocsUIState

    private val _studentDocsItemUIState = MutableLiveData<Event<DocumentUIState>>()
    val studentDocsItemUIState: LiveData<Event<DocumentUIState>> get() = _studentDocsItemUIState

    init {
        getStudentDocs()
    }

    private fun getStudentDocs() {
        viewModelScope.launch {
            if (checkConnectivityUseCase()) {
                _studentDocsUIState.update { it.copy(isLoading = true, isInternetUnAvailable = false) }
                try {
                    val studentDocuments =
                        studentDocumentsUIMapper.map(getStudentDocumentsUseCase())
                    _studentDocsUIState.update {
                        it.copy(
                            isSuccess = true,
                            isLoading = false,
                            studentDocs = studentDocuments
                        )
                    }
                } catch (e: Exception) {
                    _studentDocsUIState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = false,
                            isFailure = true,
                            error = e.message.toString()
                        )
                    }
                }
            } else {
                _studentDocsUIState.update { it.copy(isLoading = false, isInternetUnAvailable = true) }
            }
        }
    }

    override fun onListClick(item: DocumentUIState) {
        _studentDocsItemUIState.postEvent(item)
    }

    override fun onOpenDoc(item: DocumentUIState) {
        TODO("Not implemented yet")
    }

    override fun onDownloadDoc(item: DocumentUIState) {
        TODO("Not implemented yet")
    }

}
