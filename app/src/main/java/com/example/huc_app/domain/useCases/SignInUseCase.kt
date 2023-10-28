package com.example.huc_app.domain.useCases

import com.example.huc_app.data.repository.AccountRepository
import com.google.android.gms.auth.api.identity.SignInCredential
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(credential: SignInCredential) {
        accountRepository.signIn(credential)
    }
}