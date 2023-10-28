package com.example.huc_app.data.auth

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthServiceImp @Inject constructor(
    private val signInClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
    private val signUpRequest: BeginSignInRequest
) : AuthService {

    override suspend fun googleSignIn(): BeginSignInResult {
        return suspendCancellableCoroutine { continuation ->
            try {
                createTask(signInRequest).addOnSuccessListener { result ->
                     continuation.resume(result)
                 }.addOnFailureListener { exception ->
                     if (exception is ApiException && exception.statusCode == 8) {
                         continuation.resumeWithException(exception)
                     } else {
                         continuation.resumeWithException(exception)
                    }
                }
            } catch (exception: Exception) {
                continuation.resumeWithException(Exception("Failed to sign in. Please check your network connection and try again."))
            }
        }
    }

    override suspend fun googleSignUp(): BeginSignInResult {
        return suspendCancellableCoroutine { continuation ->
            try {
                createTask(signUpRequest).addOnSuccessListener { result ->
                    continuation.resume(result)
                }.addOnFailureListener { exception ->
                    if (exception is ApiException && exception.statusCode == 8) {
                        continuation.resumeWithException(Exception("Internal error occurred. Please try again later."))
                    } else {
                        continuation.resumeWithException(exception)
                    }
                }
            } catch (exception: Exception) {
                continuation.resumeWithException(Exception("Failed to sign up. Please check your network connection and try again."))
            }
        }
    }


    private fun createTask(request: BeginSignInRequest) =
        signInClient.beginSignIn(request)
}
