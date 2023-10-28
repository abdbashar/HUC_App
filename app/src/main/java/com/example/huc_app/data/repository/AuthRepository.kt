package com.example.huc_app.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult

interface AuthRepository {
    suspend fun signInOrSignUpWithGoogle(): BeginSignInResult
}