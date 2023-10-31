package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.mappers.UserInfoMapper
import com.example.huc_app.domain.models.UserInfo
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userInfoMapper: UserInfoMapper,
) {
    suspend operator fun invoke(): UserInfo{
        val response = userRepository.getUserInfo()
        return response.let { userInfo ->
            userInfoMapper.map(userInfo)
        } ?: throw Throwable("Unable to get content")
    }
}