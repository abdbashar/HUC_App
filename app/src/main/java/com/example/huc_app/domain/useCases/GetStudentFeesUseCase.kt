package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.mappers.StudentFeesMapper
import com.example.huc_app.domain.models.StudentFees
import javax.inject.Inject

class GetStudentFeesUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val studentFeesMapper: StudentFeesMapper,
) {
    suspend operator fun invoke(): StudentFees {
        val response = userRepository.getStudentFees()
        return response.let { studentFees ->
            studentFeesMapper.map(studentFees)
        } ?: throw Throwable("Unable to get content")
    }
}