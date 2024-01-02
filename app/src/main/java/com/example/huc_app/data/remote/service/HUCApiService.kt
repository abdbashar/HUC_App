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

    @GET("departments/User General info")
    suspend fun getUserInfo(): UserInfoDTO

    @GET("departments/User Undergraduate info")
    suspend fun getUserUnderGraduatedInfo(): UserUndergradInfoDTO

    @GET("departments/User university info")
    suspend fun getUserUniversityInfo(): UserUniversityInfoDTO

    @GET("HUC Website/latest news")
    suspend fun getLatestNews(): UniversityNewsDTO

    @GET("get_student_documents")
    suspend fun getStudentDocs(): StudentDocumentsDTO

    @GET("departments/Student id and direct status")
    suspend fun getStudentIDStatus(): StudentIDStatusDTO

    @GET("Registration/get_requests")
    suspend fun getRequests(): RequestsDTO
}