package com.example.huc_app.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.useCases.CheckUserLoggedInUseCase
import com.example.huc_app.domain.useCases.GetStudentDetailsUseCase
import com.example.huc_app.ui.menu.menuEvent.MenuEvent
import com.example.huc_app.ui.menu.menuEvent.toEvent
import com.example.huc_app.ui.menu.menuUIState.GetMenuUIState
import com.example.huc_app.util.Event
import com.example.huc_app.util.asEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getStudentDetailsUseCase: GetStudentDetailsUseCase,
    private val studentDetailsUIMapper: StudentDetailsUIMapper,
    private val checkUserLoggedInUseCase: CheckUserLoggedInUseCase,
) : ViewModel() {

    private val _menuEvent = MutableLiveData<MenuEvent>().asEvent()
    val menuEvent: LiveData<Event<MenuEvent>> = _menuEvent

    private val _menuUIState = MutableStateFlow(GetMenuUIState())
    val menuUIState: StateFlow<GetMenuUIState> get() = _menuUIState

    init {
        viewModelScope.launch {
            if (checkUserLoggedInUseCase()) {
                getUserProfile()
            }
        }
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            _menuUIState.update { it.copy(isLoading = true) }
            try {
                val userDetails = studentDetailsUIMapper.map(getStudentDetailsUseCase())
                _menuUIState.update {
                    it.copy(
                        isLoading = false,
                        isUserLoggedIn = true,
                        isSuccess = true,
                        studentDetails = userDetails
                    )
                }
            } catch (e: Throwable) {
                _menuUIState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        error = e.message.toString()
                    )
                }
            }
        }
    }

    fun onPersonalInfoClicked() {
        _menuEvent.value = MenuEvent.PersonalInfoClicked.toEvent()
    }

    fun onSecondarySchoolInfoClicked() {
        _menuEvent.value = MenuEvent.SecondarySchoolInfoClicked.toEvent()
    }

    fun onUniversityInfoClicked() {
        _menuEvent.value = MenuEvent.UniversityInfoClicked.toEvent()
    }

    fun onStudentDocsClicked() {
        _menuEvent.value = MenuEvent.StudentDocsClicked.toEvent()
    }
}