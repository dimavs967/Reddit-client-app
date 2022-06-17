package com.task.reddit.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkUtility : ConnectivityManager.NetworkCallback() {

    private val networkState = MutableLiveData<Boolean>()
    private var capabilities: NetworkCapabilities? = null
    private var isAvailable = false

    fun getStateLiveData(context: Context): LiveData<Boolean> {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            manager.registerDefaultNetworkCallback(this)
        } else {
            manager.registerNetworkCallback(NetworkRequest.Builder().build(), this)
        }

        for (allNetwork in manager.allNetworks) {
            capabilities = manager.getNetworkCapabilities(allNetwork)!!
            if (capabilities!!.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                isAvailable = true
                break
            }
        }
        networkState.postValue(isAvailable)
        return networkState
    }

    override fun onLost(@NonNull network: Network) {
        networkState.postValue(false)
    }

    override fun onAvailable(@NonNull network: Network) {
        networkState.postValue(true)
    }

}