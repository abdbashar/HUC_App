package com.example.huc_app.ui.menu

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.StudentDetails
import com.example.huc_app.ui.menu.menuUIState.StudentDetailsUIState
import javax.inject.Inject

class StudentDetailsUIMapper  @Inject constructor() : Mapper<StudentDetails, StudentDetailsUIState>() {
    override fun map(input: StudentDetails): StudentDetailsUIState {
        return StudentDetailsUIState(
            email = input.email ?: "",
            fullName = input.fullNameInArabic ?: "",
            personalPicture = input.personalPicture ?: "",
        )
    }
}