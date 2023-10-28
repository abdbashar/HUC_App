package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.AuthRepositoryImp
import com.google.android.gms.auth.api.identity.BeginSignInResult
import javax.inject.Inject

class GetGoogleSignInResultUseCase @Inject constructor(
    private val authRepositoryImp: AuthRepositoryImp,
) {
    suspend operator fun invoke(): BeginSignInResult {
        return authRepositoryImp.signInOrSignUpWithGoogle()
    }
}