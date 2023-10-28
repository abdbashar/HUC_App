package com.example.huc_app.data.repository

import com.example.huc_app.data.auth.AuthService
import com.google.android.gms.auth.api.identity.BeginSignInResult
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val authService: AuthService,
) : AuthRepository {
    override suspend fun signInOrSignUpWithGoogle(): BeginSignInResult {
        return try {
            authService.googleSignIn()
        } catch (e: Exception) {
            authService.googleSignUp()
        }
    }
}