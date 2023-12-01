package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.mappers.StudentIDStatusMapper
import com.example.huc_app.domain.models.StudentIDStatus
import javax.inject.Inject

class GetStudentIDStatusUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val studentIDStatusMapper: StudentIDStatusMapper,
) {
    suspend operator fun invoke(): StudentIDStatus {
        val response = userRepository.getStudentIDStatus()
        return response.let { studentID ->
            studentIDStatusMapper.map(studentID)
        } ?: throw Throwable("Unable to get content")
    }
}