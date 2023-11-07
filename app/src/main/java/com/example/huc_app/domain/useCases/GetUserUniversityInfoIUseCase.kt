package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.mappers.UserUniversityInfoMapper
import com.example.huc_app.domain.models.UserUniversityInfo
import javax.inject.Inject

class GetUserUniversityInfoIUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userUniversityInfoMapper: UserUniversityInfoMapper,
) {
    suspend operator fun invoke(): UserUniversityInfo {
        val response = userRepository.getUserUniversityInfo()
         return response.let {
            userUniversityInfoMapper.map(it)
        } ?: throw Throwable("Unable to get content")
    }
}