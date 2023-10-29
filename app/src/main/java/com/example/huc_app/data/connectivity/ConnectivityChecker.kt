package com.example.huc_app.data.connectivity

interface ConnectivityChecker {
    suspend fun hasInternetConnection(): Boolean
}
