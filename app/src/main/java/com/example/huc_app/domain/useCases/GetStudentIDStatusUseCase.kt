package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.mappers.StudentIDStatusMapper
import javax.inject.Inject

class GetStudentIDStatusUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val studentIDStatusMapper: StudentIDStatusMapper,
) {
}