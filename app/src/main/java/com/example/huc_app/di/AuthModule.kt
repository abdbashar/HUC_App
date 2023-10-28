package com.example.huc_app.di

import android.app.Application
import android.content.Context
import com.example.huc_app.BuildConfig
import com.example.huc_app.data.auth.AuthService
import com.example.huc_app.data.auth.AuthServiceImp
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    private const val SIGN_IN_REQUEST = "sign_in_request"
    private const val SIGN_UP_REQUEST = "sign_up_request"

    @Provides
    @Singleton
    fun provideOneTapClient(@ApplicationContext context: Context): SignInClient =
        Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQUEST)
    fun provideSignInRequest(): BeginSignInRequest =
        BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()

    @Provides
    @Named(SIGN_UP_REQUEST)
    fun provideSignUpRequest(): BeginSignInRequest =
        BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

    @Provides
    @Singleton
    fun provideGoogleSignInOptions(): GoogleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.WEB_CLIENT_ID)
            .requestEmail()
            .build()

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions
    ): GoogleSignInClient = GoogleSignIn.getClient(app, options)

    @Provides
    @Singleton
    fun provideAuthService(
        oneTapClient: SignInClient,
        @Named(SIGN_IN_REQUEST) signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQUEST) signUpRequest: BeginSignInRequest
    ): AuthService = AuthServiceImp(oneTapClient, signInRequest, signUpRequest)
}
