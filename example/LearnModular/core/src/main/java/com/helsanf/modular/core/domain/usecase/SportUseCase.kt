package com.helsanf.modular.core.domain.usecase

import com.helsanf.modular.core.data.Resource
import com.helsanf.modular.core.domain.model.SportBall
import kotlinx.coroutines.flow.Flow

interface SportUseCase {
    fun getAllTeam(): Flow<Resource<List<SportBall>>>
    fun getAllTeamFavorite() : Flow<List<SportBall>>
    fun getDetailTeam(idteam: String): Flow<SportBall>
    suspend fun updateFavoriteTeam(sport : SportBall,stateFav : Boolean)
}