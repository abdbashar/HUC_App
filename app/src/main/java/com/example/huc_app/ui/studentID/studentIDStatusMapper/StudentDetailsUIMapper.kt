package com.example.huc_app.ui.studentID.studentIDStatusMapper

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.StudentDetails
import com.example.huc_app.ui.studentID.studentIDStatusUIState.StudentDetailsUIState
import javax.inject.Inject

class StudentDetailsUIMapper  @Inject constructor() : Mapper<StudentDetails, StudentDetailsUIState>() {
    override fun map(input: StudentDetails): StudentDetailsUIState {
        return StudentDetailsUIState(
            fullName = input.fullNameInArabic,
            personalPicture = input.personalPicture,
            departmentName = input.departmentNameInArabic,
            departmentNameInEnglish = input.departmentNameInEnglish,
            stage = input.stage,
            studySystemType = input.studySystemType
        )
    }
}