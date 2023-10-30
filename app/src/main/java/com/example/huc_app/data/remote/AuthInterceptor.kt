package com.example.huc_app.data.remote

import com.example.huc_app.data.local.datastore.DataStorePreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStorePreferences: DataStorePreferences,
) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = dataStorePreferences.readString(ACCESS_TOKEN)
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header(HEADER_AUTHORIZATION, "$BEARER_TOKEN_PREFIX $token")
            .method(original.method(), original.body())

        val request = requestBuilder.build()
        return chain.proceed(request)

    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val BEARER_TOKEN_PREFIX = "Bearer"
        private const val ACCESS_TOKEN = "access_token"
    }
}
