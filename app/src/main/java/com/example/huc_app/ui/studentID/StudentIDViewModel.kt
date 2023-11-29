package com.example.huc_app.ui.studentID

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.huc_app.domain.types.Language
import com.example.huc_app.util.SettingsService

class StudentIDViewModel : ViewModel() {

    private val _currentLanguage = MutableLiveData<Language>()
    val currentLanguage: LiveData<Language> get() = _currentLanguage

    private val settingsService = SettingsService

    init {
        getCurrentAppLanguage()
    }

    private fun getCurrentAppLanguage() {
        _currentLanguage.postValue(settingsService.getCurrentLanguage())
    }

}