package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.mappers.StudentDocumentsMapper
import javax.inject.Inject

class GetStudentDocumentsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val studentDocumentsMapper: StudentDocumentsMapper
) {
}