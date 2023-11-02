package com.example.huc_app.data.repository

import com.example.huc_app.data.remote.response.UserInfoDTO

interface UserRepository {

    suspend fun getUserInfo(): UserInfoDTO
}