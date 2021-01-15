package com.helsanf.modular.core.data.source.local

import com.helsanf.modular.core.data.source.local.entity.SportEntity
import com.helsanf.modular.core.data.source.local.room.SportDao
import com.helsanf.modular.core.domain.model.SportBall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val sportDao: SportDao){

    fun getAllTeam() : Flow<List<SportEntity>> = sportDao.getAllTeam()

    fun getAllTeamFavorite() : Flow<List<SportEntity>> = sportDao.getAllTeamFavorite()

    fun getDetailTeam(idTeam : String) : Flow<SportEntity> = sportDao.getDetailTeam(idTeam)

    suspend fun insertSport(sport : List<SportEntity>) = sportDao.insertSportTeam(sport)

    fun updateTeam(sport : SportEntity,favoriteState : Boolean){
        sport.isFavorite = favoriteState
        sportDao.updateFavoriteTeam(sport)
    }
}