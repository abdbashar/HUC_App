package com.example.huc_app.ui.studentID

import androidx.lifecycle.ViewModel
import com.example.huc_app.util.SettingsService

class StudentIDViewModel : ViewModel() {

    private val settingsService = SettingsService

    init {
        getCurrentAppLanguage()
    }

    private fun getCurrentAppLanguage() {
        TODO("Not implemented yet")
    }

}