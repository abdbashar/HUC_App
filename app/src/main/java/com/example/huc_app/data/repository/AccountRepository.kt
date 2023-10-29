package com.example.huc_app.data.repository

import com.example.huc_app.data.remote.response.AccountDetailsDto
import com.google.android.gms.auth.api.identity.SignInCredential

interface AccountRepository {
    suspend fun signIn(credential: SignInCredential): AccountDetailsDto
}