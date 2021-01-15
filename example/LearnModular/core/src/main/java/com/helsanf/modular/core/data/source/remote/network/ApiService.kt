package com.helsanf.modular.core.data.source.remote.network

import com.helsanf.modular.core.data.source.remote.respone.SportResponses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("search_all_teams.php")
    suspend fun fetchAllTeam(@Query("l") liga : String) : Response<SportResponses>
}