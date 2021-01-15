package com.helsanf.modular.core.domain.usecase

import com.helsanf.modular.core.data.Resource
import com.helsanf.modular.core.domain.model.SportBall
import com.helsanf.modular.core.domain.repository.IDomainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SportInteractor @Inject constructor(private val iDomainRepository: IDomainRepository) : SportUseCase{
    override fun getAllTeam(): Flow<Resource<List<SportBall>>> = iDomainRepository.getAllTeam()
    override fun getAllTeamFavorite(): Flow<List<SportBall>> {
        return iDomainRepository.getAllTeamFavorite()
    }

    override fun getDetailTeam(idteam: String): Flow<SportBall> {
        return iDomainRepository.getDetailTeam(idteam)
    }

    override suspend fun updateFavoriteTeam(sport: SportBall, stateFav: Boolean) {
        iDomainRepository.updateFavoriteTeam(sport,stateFav)
    }

}