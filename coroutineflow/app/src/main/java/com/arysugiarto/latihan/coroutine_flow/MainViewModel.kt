package com.arysugiarto.latihan.coroutine_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arysugiarto.latihan.coroutine_flow.network.ApiConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel :ViewModel() {

    private val accesToken = ("pk.eyJ1IjoiYXJ5c3VnaWFydG8iLCJhIjoiY2tpdHprb2ptMDM0ZTJzb2E0bzgzM3AwYiJ9.2GsOlLcm2Cd--gstxmXdWQ")
    val queryChannel  = BroadcastChannel<String>(Channel.CONFLATED)

    val resultSearch= queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            ApiConfig.provideApiService().getCountry(it,accesToken).feature
        }
        .asLiveData()
}