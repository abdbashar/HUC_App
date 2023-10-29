package com.example.huc_app.data.repository

import android.util.Log
import com.example.huc_app.data.DataClassParser
import com.example.huc_app.data.local.datastore.DataStorePreferences
import com.example.huc_app.data.remote.request.SignInRequest
import com.example.huc_app.data.remote.response.AccountDetailsDto
import com.example.huc_app.data.remote.response.SignInError
import com.example.huc_app.data.remote.service.HUCApiService
import com.example.huc_app.util.Constants.ACCESS_TOKEN_KEY
import com.example.huc_app.util.Constants.DEPARTMENT_NAME_AR_KEY
import com.example.huc_app.util.Constants.DEPARTMENT_NAME_EN_KEY
import com.example.huc_app.util.Constants.EMAIL_KEY
import com.example.huc_app.util.Constants.STAGE_KEY
import com.example.huc_app.util.Constants.STUDY_SYSTEM_TYPE_KEY
import com.example.huc_app.util.Constants.TIME_STAMP_KEY
import com.example.huc_app.util.Constants.USER_IMAGE_KEY
import com.example.huc_app.util.Constants.USER_NAME_AR_KEY
import com.example.huc_app.util.Constants.USER_NAME_EN_KEY
import com.google.android.gms.auth.api.identity.SignInCredential
import org.json.JSONException
import retrofit2.HttpException
import javax.inject.Inject

class AccountRepositoryImp @Inject constructor(
    private val hucApiService: HUCApiService,
    private val dataStorePreferences: DataStorePreferences,
    private val dataClassParser: DataClassParser
) : AccountRepository {

    override suspend fun signIn(credential: SignInCredential): AccountDetailsDto {
        val request = SignInRequest("android", credential.googleIdToken.toString())
        val response = hucApiService.signIn(request)
        if (response.isSuccessful) {
            val accountDetails = response.body()
            if (accountDetails?.token != null) {
                saveUserInfoToDataStore(accountDetails, credential)
                return accountDetails
            } else {
                throw Exception("Invalid account details")
            }
        } else {
            val errorBody = response.errorBody()?.string()
            if (response.code() == 404 && errorBody != null) {
                try {
                    val errorResponse = dataClassParser.parseFromJson(
                        errorBody,
                        SignInError::class.java
                    )
                    val detail = errorResponse.detail
                    throw Exception("Not Found: $detail")
                } catch (e: JSONException) {
                    throw Exception("Not Found: Unknown error")
                }
            } else {
                throw HttpException(response)
            }
        }
    }


    private suspend fun saveUserInfoToDataStore(
        response: AccountDetailsDto,
        credential: SignInCredential
    ) {
        response.token?.access?.let {
            Log.d("huc_token",it)
            dataStorePreferences.writeString(ACCESS_TOKEN_KEY, it)
        }
        response.timestamp?.let {
            dataStorePreferences.writeString(TIME_STAMP_KEY,it)
        }
        response.email?.let {
            dataStorePreferences.writeString(EMAIL_KEY, it)
        }
        response.full_name?.let {
            dataStorePreferences.writeString(USER_NAME_AR_KEY, it)
        }
        response.en_full_name?.let{
            dataStorePreferences.writeString(USER_NAME_EN_KEY, it)
        }
        credential.profilePictureUri?.let {
            dataStorePreferences.writeString(USER_IMAGE_KEY, it.toString())
        }
        response.dep_name?.let {
            dataStorePreferences.writeString(DEPARTMENT_NAME_AR_KEY, it)
        }
        response.en_dep_name?.let {
            dataStorePreferences.writeString(DEPARTMENT_NAME_EN_KEY, it)
        }
        response.edu?.let {
            dataStorePreferences.writeString(STUDY_SYSTEM_TYPE_KEY, it)
        }
        response.stage?.let {
            dataStorePreferences.writeString(STAGE_KEY, it)
        }

    }

}