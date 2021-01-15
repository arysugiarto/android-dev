package com.helsanf.modular.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sport")
data class SportEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idTeam")
    val idTeam: String,


    @ColumnInfo(name = "idLeague")
    val idLeague: String,


    @ColumnInfo(name = "strDescriptionEN")
    val strDescriptionEN: String,

    @ColumnInfo(name = "strLeague")
    val strLeague: String,

    @ColumnInfo(name = "strStadium")
    val strStadium: String,


    @ColumnInfo(name = "strTeam")
    val strTeam: String,

    @ColumnInfo(name = "strTeamBadge")
    val strTeamBadge: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite : Boolean = false

)