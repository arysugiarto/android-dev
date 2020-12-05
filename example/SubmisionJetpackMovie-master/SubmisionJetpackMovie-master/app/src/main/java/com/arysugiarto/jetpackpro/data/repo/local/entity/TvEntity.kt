package com.arysugiarto.jetpackpro.data.repo.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tventity")
data class TvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "poster_path")
    var poster_path: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "first_air_date")
    var first_air_date: String?,

    @ColumnInfo(name = "vote_average")
    var vote_average: String?,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean? = false
)