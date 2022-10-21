package com.friendschat.connectivitychecker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendschat.connectivitychecker.connectivity.NetworkConnectivityObserver

class MyViewModelFactory(private val connectivityObserver: NetworkConnectivityObserver): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(connectivityObserver) as T
    }
}