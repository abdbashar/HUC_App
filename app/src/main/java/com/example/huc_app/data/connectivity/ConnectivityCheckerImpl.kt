package com.example.huc_app.data.connectivity

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class ConnectivityCheckerImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : ConnectivityChecker {
    override suspend fun hasInternetConnection(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
