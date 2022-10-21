package com.friendschat.connectivitychecker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.friendschat.connectivitychecker.connectivity.NetworkConnectivityObserver
import com.friendschat.connectivitychecker.connectivity.NetworkConnectivityObserverImpl
import com.friendschat.connectivitychecker.viewmodel.MainViewModel
import com.friendschat.connectivitychecker.viewmodel.MyViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val networkConnectivityObserver: NetworkConnectivityObserver = NetworkConnectivityObserverImpl(this)
        mainViewModel = ViewModelProvider(this, MyViewModelFactory(networkConnectivityObserver))[MainViewModel::class.java]
        observeConnectivity()
    }

    private fun observeConnectivity() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
               mainViewModel?.let {
                   it.networkStateFlow.collect { status ->
                       findViewById<TextView>(R.id.status).text = String.format(resources.getString(R.string.connectivity_status), status.name)
                   }
               }
            }
        }
    }
}