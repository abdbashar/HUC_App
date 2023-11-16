package com.example.huc_app.ui.menu.menuEvent

sealed class MenuEvent {
    object PersonalInfoClicked : MenuEvent()
    object SecondarySchoolInfoClicked : MenuEvent()
    object UniversityInfoClicked : MenuEvent()
}

