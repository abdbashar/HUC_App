package com.example.huc_app.data.repository

import com.example.huc_app.data.remote.response.UserInfoDTO
import com.example.huc_app.data.remote.response.UserUndergradInfoDTO

interface UserRepository {

    suspend fun getUserInfo(): UserInfoDTO

    suspend fun getUserUnderGradeInfo(): UserUndergradInfoDTO

}