package com.example.huc_app.domain.mappers

import com.example.huc_app.data.remote.response.StudentIDStatusDTO
import com.example.huc_app.domain.models.StudentIDStatus
import javax.inject.Inject

class StudentIDStatusMapper @Inject constructor(): Mapper<StudentIDStatusDTO,StudentIDStatus>() {
    override fun map(input: StudentIDStatusDTO): StudentIDStatus {
        return StudentIDStatus(
            directEnrolledDate = input.directly_date ?: "",
            isDirectlyEnrolled = input.is_directly ?: false,
            isStudentIDPrinted = input.is_printed ?: false,
            idExpirationDate = input.id_expiration_date ?: "",
        )
    }
}