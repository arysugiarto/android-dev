package com.helsanf.modular.core.domain.model

data class SportBall(
    val idTeam: String,
    val idLeague: String,
    val descriptionTeam: String,
    val logoTeam: String,
    val titleTeam: String,
    val stadiumName: String,
    val titleLeague: String,
    val isFavorite: Boolean
)