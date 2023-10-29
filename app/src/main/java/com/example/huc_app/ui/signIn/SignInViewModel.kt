package com.example.huc_app.ui.signIn

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huc_app.domain.useCases.*
import com.example.huc_app.util.Event
import com.example.huc_app.util.postEvent
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val getGoogleSignInResultUseCase: GetGoogleSignInResultUseCase,
    private val signInUseCase: SignInUseCase,
    private val signInClient: SignInClient,
    private val checkConnectivityUseCase: CheckConnectivityUseCase,
    private val saveGoogleSignInCancellationTimeUseCase: SaveGoogleSignInCancellationTimeUseCase,
    private val coolDownUseCase: CoolDownUseCase,
) : ViewModel() {

    private val _signInUiState = MutableStateFlow(SignInUiState())
    val signInUiState: StateFlow<SignInUiState> get() = _signInUiState

    private val _isSignInButtonClicked = MutableLiveData<Event<Boolean>>()
    val isSignInButtonClicked: LiveData<Event<Boolean>> = _isSignInButtonClicked

    private val _signInEvent = MutableLiveData<Event<Boolean>>()
    val signInEvent: LiveData<Event<Boolean>> = _signInEvent

    fun onSignInButtonClicked() {
        _isSignInButtonClicked.value = Event(true)
    }


    suspend fun checkInternetConnection(): Boolean = checkConnectivityUseCase()

    suspend fun beginGoogleOneTapSignIn(): BeginSignInResult {
        _signInUiState.update { it.copy(isLoading = true) }
        return try {
            getGoogleSignInResultUseCase().let { googleSignInResult ->
                _signInUiState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                }
                googleSignInResult
            }
        } catch (e: Exception) {
            _signInUiState.update {
                it.copy(
                    isLoading = false,
                    isError = true,
                    error = e.message.toString()
                )
            }
            throw e
        }
    }

    fun getGoogleSignInCredentials(data: Intent?) {
        viewModelScope.launch {
            try {
                val googleSignInCredentials = signInClient.getSignInCredentialFromIntent(data)
                signIn(googleSignInCredentials)
            } catch (e: ApiException) {
                when (e.statusCode) {
                    CommonStatusCodes.CANCELED -> {
                        onGoogleSignInCanceled()
                    }
                    CommonStatusCodes.NETWORK_ERROR -> {
                        _signInUiState.update {
                            it.copy(
                                isError = true,
                                error = CommonStatusCodes.NETWORK_ERROR.toString()
                            )
                        }
                    }
                    else -> {
                        _signInUiState.update {
                            it.copy(
                                isError = true,
                                error = e.localizedMessage!!.toString()
                            )
                        }
                    }
                }
            }
        }
    }


    fun signIn(googleIdToken: SignInCredential) {
        viewModelScope.launch {
            try {
                _signInUiState.update { it.copy(isLoading = true) }
                signInUseCase(googleIdToken)
                _signInUiState.update {
                    it.copy(
                        isSuccess = true,
                        isLoading = false,
                        isError = false
                    )
                }
                _signInEvent.postEvent(true)
            } catch (e: Exception) {
                Log.d("SignInError","from view model / ${e.message}")
                _signInUiState.update {
                    it.copy(
                        error = e.message.toString(),
                        isLoading = false,
                        isError = true,
                    )
                }
            }
        }
    }

    private fun onGoogleSignInCanceled() {
        viewModelScope.launch {
            try {
                saveGoogleSignInCancellationTimeUseCase()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun isCoolDownPeriodActive(): Boolean = coolDownUseCase()

    fun getRemainingTime(): Int {
        return try {
            coolDownUseCase.getRemainingCoolDownTime()
        } catch (e: Exception) {
            throw e
        }
    }
}

