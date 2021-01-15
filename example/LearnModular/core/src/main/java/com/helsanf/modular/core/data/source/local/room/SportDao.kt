package com.helsanf.modular.core.data.source.local.room

import androidx.room.*
import com.helsanf.modular.core.data.Resource
import com.helsanf.modular.core.data.source.local.entity.SportEntity
import com.helsanf.modular.core.domain.model.SportBall
import kotlinx.coroutines.flow.Flow

@Dao
interface SportDao{
    @Query("SELECT * FROM sport")
    fun getAllTeam() : Flow<List<SportEntity>>

    @Query("SELECT * FROM sport WHERE isFavorite = 1")
    fun getAllTeamFavorite() : Flow<List<SportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSportTeam(sport: List<SportEntity>)

    @Query("SELECT * FROM sport WHERE idTeam= :idteam")
    fun getDetailTeam(idteam : String) : Flow<SportEntity>

    @Update
    fun updateFavoriteTeam(sport : SportEntity)
}