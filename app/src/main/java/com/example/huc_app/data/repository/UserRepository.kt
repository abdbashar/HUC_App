package com.example.huc_app.data.repository

import com.example.huc_app.data.remote.response.UniversityNewsDTO
import com.example.huc_app.data.remote.response.UserInfoDTO
import com.example.huc_app.data.remote.response.UserUndergradInfoDTO
import com.example.huc_app.data.remote.response.UserUniversityInfoDTO

interface UserRepository {

    suspend fun getUserInfo(): UserInfoDTO

    suspend fun getUserUnderGradeInfo(): UserUndergradInfoDTO

    suspend fun getUserUniversityInfo(): UserUniversityInfoDTO

    suspend fun getLatestNews(): UniversityNewsDTO

}