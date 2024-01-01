package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.mappers.RequestMapper
import com.example.huc_app.domain.models.Request
import javax.inject.Inject

class GetRequestsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val requestMapper: RequestMapper,
) {
    suspend operator fun invoke(): List<Request> {
       return userRepository.getRequests().let {
            requestMapper.map(it)
        } ?: throw  Throwable("Unable to get content")
    }
}