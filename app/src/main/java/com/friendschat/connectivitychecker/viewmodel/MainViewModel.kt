package com.friendschat.connectivitychecker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendschat.connectivitychecker.connectivity.NetworkConnectivityObserver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class MainViewModel(private val connectivityObserver: NetworkConnectivityObserver) : ViewModel() {
    private val _networkMutableStateFlow =
        MutableStateFlow(NetworkConnectivityObserver.Status.Unavailable)
    val networkStateFlow: StateFlow<NetworkConnectivityObserver.Status> = _networkMutableStateFlow

    init {
        viewModelScope.launch {
            _networkMutableStateFlow.emitAll(connectivityObserver.observe())
        }
    }
}