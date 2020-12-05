package com.arysugiarto.jetpackpro.data.repo.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentity")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "poster_path")
    var poster_path: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "release_date")
    var release_date: String?,
    @ColumnInfo(name = "vote_average")
    var vote_average: String?,
    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean? = false
)