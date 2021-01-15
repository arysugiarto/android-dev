package com.helsanf.modular.core.domain.repository

import com.helsanf.modular.core.data.Resource
import com.helsanf.modular.core.domain.model.SportBall
import kotlinx.coroutines.flow.Flow


interface IDomainRepository{
    fun getAllTeam() : Flow<Resource<List<SportBall>>>
    fun getAllTeamFavorite() : Flow<List<SportBall>>
    fun getDetailTeam(idteam : String) : Flow<SportBall>
    suspend fun updateFavoriteTeam(sportBall: SportBall,stateFav : Boolean)
}