package com.example.huc_app.ui.menu.menuEvent

import com.example.huc_app.util.Event

sealed class MenuEvent {
    object PersonalInfoClicked : MenuEvent()
    object SecondarySchoolInfoClicked : MenuEvent()
    object UniversityInfoClicked : MenuEvent()
    object StudentDocsClicked : MenuEvent()
    object StudentIDClicked : MenuEvent()
}

fun MenuEvent.toEvent(): Event<MenuEvent> = Event(this)
