package com.helsanf.modular.core.utils

import com.helsanf.modular.core.data.source.local.entity.SportEntity
import com.helsanf.modular.core.data.source.remote.respone.SportResponses
import com.helsanf.modular.core.domain.model.SportBall

object DataMapper {
    fun mapResponsesToEntity(input: List<SportResponses.Team>?): List<SportEntity> {
        val sportList = ArrayList<SportEntity>()
        input?.map {
            val sport = SportEntity(
                idTeam = it.idTeam,
                idLeague = it.idLeague,
                strDescriptionEN = it.strDescriptionEN,
                strLeague = it.strLeague,
                strStadium = it.strStadium,
                strTeam = it.strTeam,
                strTeamBadge = it.strTeamBadge,
            )
            sportList.add(sport)
        }
        return sportList
    }
    fun mapsEntityToDomain(input : List<SportEntity>) : List<SportBall> =
        input.map {
            SportBall(
                idTeam = it.idTeam,
                titleTeam = it.strTeam,
                descriptionTeam = it.strDescriptionEN,
                logoTeam = it.strTeamBadge,
                idLeague = it.idLeague,
                stadiumName = it.strStadium,
                titleLeague = it.strLeague,
                isFavorite = it.isFavorite
            )
        }

    fun mapsEntityToDomainDetail(input : SportEntity) : SportBall{
        return SportBall(
            idTeam = input.idTeam,
            descriptionTeam = input.strDescriptionEN,
            titleTeam = input.strTeam,
            logoTeam = input.strTeamBadge,
            idLeague = input.idLeague,
            stadiumName = input.strStadium,
            titleLeague = input.strLeague,
            isFavorite = input.isFavorite
        )
    }


    fun mapDomainToEntity(input : SportBall) = SportEntity(
        idTeam = input.idTeam,
        strTeam = input.titleTeam,
        strDescriptionEN = input.descriptionTeam,
        strTeamBadge = input.logoTeam,
        idLeague = input.idLeague,
        strStadium = input.stadiumName,
        strLeague = input.titleLeague,
        isFavorite = input.isFavorite

    )
}