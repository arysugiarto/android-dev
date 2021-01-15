package com.helsanf.modular.core.data

import com.helsanf.modular.core.data.source.local.LocalDataSource
import com.helsanf.modular.core.data.source.remote.RemoteDataSource
import com.helsanf.modular.core.data.source.remote.network.ApiResponse
import com.helsanf.modular.core.data.source.remote.respone.SportResponses
import com.helsanf.modular.core.domain.model.SportBall
import com.helsanf.modular.core.domain.repository.IDomainRepository
import com.helsanf.modular.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IDomainRepository {
    override fun getAllTeam(): Flow<Resource<List<SportBall>>> =
        object : NetworkBoundResource<List<SportBall>, SportResponses?>() {
            override fun loadFromDB(): Flow<List<SportBall>> {
                return localDataSource.getAllTeam().map {
                    DataMapper.mapsEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<SportBall>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<SportResponses?>> {
                return remoteDataSource.getAllTeam()
            }

            override suspend fun saveCallResult(data: SportResponses?) {
                val sportList = DataMapper.mapResponsesToEntity(data?.teams)
                localDataSource.insertSport(sportList)
            }
        }.asFlow()

    override fun getAllTeamFavorite(): Flow<List<SportBall>> {
        return localDataSource.getAllTeamFavorite().map {
            DataMapper.mapsEntityToDomain(it)
        }
    }

    override fun getDetailTeam(idteam: String): Flow<SportBall> {
        return localDataSource.getDetailTeam(idteam).map {
            DataMapper.mapsEntityToDomainDetail(it)
        }
    }

    override suspend fun updateFavoriteTeam(sportBall: SportBall, stateFav: Boolean) {
        val sportEntity = DataMapper.mapDomainToEntity(sportBall)
        localDataSource.updateTeam(sportEntity, stateFav)
    }


}