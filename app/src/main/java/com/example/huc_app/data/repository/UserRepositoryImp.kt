package com.example.huc_app.data.repository

import com.example.huc_app.data.remote.response.*
import com.example.huc_app.data.remote.service.HUCApiService
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val hucApiService: HUCApiService,
) : UserRepository {

    override suspend fun getUserInfo(): UserInfoDTO {
        return hucApiService.getUserInfo()
    }

    override suspend fun getUserUnderGradeInfo(): UserUndergradInfoDTO {
        return hucApiService.getUserUnderGraduatedInfo()
    }

    override suspend fun getUserUniversityInfo(): UserUniversityInfoDTO {
        return hucApiService.getUserUniversityInfo()

    }

    override suspend fun getLatestNews(): UniversityNewsDTO {
        return hucApiService.getLatestNews()
    }
    override suspend fun getStudentDocs(): StudentDocumentsDTO {
        return hucApiService.getStudentDocs()
    }

    override suspend fun getStudentIDStatus(): StudentIDStatusDTO {
        return hucApiService.getStudentIDStatus()
    }
}
