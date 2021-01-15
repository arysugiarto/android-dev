package com.helsanf.modular.core.data.source.remote

import com.helsanf.modular.core.data.source.remote.network.ApiResponse
import com.helsanf.modular.core.data.source.remote.network.ApiService
import com.helsanf.modular.core.data.source.remote.respone.SportResponses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService){
    suspend fun getAllTeam() : Flow<ApiResponse<SportResponses?>> {
        return flow {
            try {
                val respone = apiService.fetchAllTeam("English Premier League")
                if(respone.isSuccessful){
                emit(ApiResponse.Success(respone.body()))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }
}