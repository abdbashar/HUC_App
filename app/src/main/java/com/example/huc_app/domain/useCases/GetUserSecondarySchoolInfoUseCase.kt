package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.mappers.UserSecondarySchoolInfoMapper
import com.example.huc_app.domain.models.UserSecondarySchoolInfo
import javax.inject.Inject

class GetUserSecondarySchoolInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userSecondarySchoolInfoMapper: UserSecondarySchoolInfoMapper,
) {
    suspend operator fun invoke(): UserSecondarySchoolInfo {
        val response = userRepository.getUserUnderGradeInfo()
        return response.let {
            userSecondarySchoolInfoMapper.map(it)
        } ?: throw Throwable("Unable to get content")
    }
}