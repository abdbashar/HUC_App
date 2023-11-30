package com.example.huc_app.data.repository

import com.example.huc_app.data.remote.response.*

interface UserRepository {

    suspend fun getUserInfo(): UserInfoDTO

    suspend fun getUserUnderGradeInfo(): UserUndergradInfoDTO

    suspend fun getUserUniversityInfo(): UserUniversityInfoDTO

    suspend fun getLatestNews(): UniversityNewsDTO

    suspend fun getStudentDocs(): StudentDocumentsDTO

    suspend fun getStudentIDStatus(): StudentIDStatusDTO
}