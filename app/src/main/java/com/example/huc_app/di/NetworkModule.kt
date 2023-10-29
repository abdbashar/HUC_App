package com.example.huc_app.di

import android.app.DownloadManager
import android.content.Context
import android.net.ConnectivityManager
import com.example.huc_app.data.connectivity.ConnectivityChecker
import com.example.huc_app.data.connectivity.ConnectivityCheckerImpl
import com.example.huc_app.data.download.DownloadHelper
import com.example.huc_app.data.download.DownloadHelperImpl
import com.example.huc_app.data.remote.AuthInterceptor
import com.example.huc_app.data.remote.service.HUCApiService
import com.example.huc_app.util.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHUCApiService(
        retrofit: Retrofit,
    ): HUCApiService {
        return retrofit.create(HUCApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val builder = OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideConnectivityChecker(@ApplicationContext context: Context): ConnectivityChecker {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return ConnectivityCheckerImpl(connectivityManager)
    }

    @Singleton
    @Provides
    fun provideDownloadManager(@ApplicationContext context: Context): DownloadManager {
        return context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    @Singleton
    @Provides
    fun provideDownloadHelper(downloadManager: DownloadManager): DownloadHelper {
        return DownloadHelperImpl(downloadManager)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}
