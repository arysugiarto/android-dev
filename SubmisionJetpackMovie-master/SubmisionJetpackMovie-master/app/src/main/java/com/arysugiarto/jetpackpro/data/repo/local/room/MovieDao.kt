package com.arysugiarto.jetpackpro.data.repo.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentity")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tventity")
    fun getTv(): LiveData<List<TvEntity>>

    @Query("SELECT * FROM movieentity where bookmarked = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tventity where bookmarked = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movieentity WHERE id = :id")
    fun getMovieId(id: String?): LiveData<MovieEntity>

    @Query("SELECT * FROM tventity WHERE id = :id")
    fun getTvId(id: String?): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tvs: List<TvEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tv: TvEntity)

}