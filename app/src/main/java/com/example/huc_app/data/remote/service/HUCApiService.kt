package com.example.huc_app.data.remote.service

import com.example.huc_app.data.remote.request.SignInRequest
import com.example.huc_app.data.remote.response.*
import retrofit2.Response
import retrofit2.http.*

interface HUCApiService {
    @POST("signin")
    suspend fun signIn(
        @Body request: SignInRequest
    ): Response<AccountDetailsDto>
}