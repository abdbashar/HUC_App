package com.example.huc_app.data.auth

import com.google.android.gms.auth.api.identity.BeginSignInResult

interface AuthService {
    suspend fun googleSignIn(): BeginSignInResult
    suspend fun googleSignUp(): BeginSignInResult
}