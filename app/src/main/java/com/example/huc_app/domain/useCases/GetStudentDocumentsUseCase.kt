package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.UserRepository
import com.example.huc_app.domain.mappers.StudentDocumentsMapper
import com.example.huc_app.domain.models.StudentDocuments
import javax.inject.Inject

class GetStudentDocumentsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val studentDocumentsMapper: StudentDocumentsMapper
) {
    suspend operator fun invoke(): StudentDocuments{
        val response = userRepository.getStudentDocs()
        return response.let { docs ->
            studentDocumentsMapper.map(docs)
        } ?: throw Throwable("Unable to get content")
    }
}