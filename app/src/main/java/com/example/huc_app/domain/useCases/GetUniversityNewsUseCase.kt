package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.models.UniversityNews
import com.example.huc_app.domain.mappers.UniversityNewsMapper
import javax.inject.Inject

class GetUniversityNewsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val universityNewsMapper: UniversityNewsMapper,
) {
    suspend operator fun invoke(): List<UniversityNews>{
        val response = userRepository.getLatestNews()
        return response.let { news ->
            universityNewsMapper.map(news)
        } ?: throw Throwable("Unable to get content")
    }
}