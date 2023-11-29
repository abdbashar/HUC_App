package com.example.huc_app.ui.studentID.studentIDStatusMapper

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.StudentIDStatus
import com.example.huc_app.ui.studentID.studentIDStatusUIState.StudentIDStatusUIState
import javax.inject.Inject

class StudentIDStatusUIMapper @Inject constructor(): Mapper<StudentIDStatus,StudentIDStatusUIState>() {
    override fun map(input: StudentIDStatus): StudentIDStatusUIState {
        return StudentIDStatusUIState(
            directEnrolledDate = input.directEnrolledDate,
            isDirectlyEnrolled = input.isDirectlyEnrolled,
            isStudentIDPrinted = input.isStudentIDPrinted,
            studentIDExpirationDate = input.idExpirationDate,
        )
    }
}