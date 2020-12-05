package com.arysugiarto.jetpackpro.data.repo.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.data.repo.local.room.MovieDao

class LocalRepo private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalRepo? = null

        fun getInstance(mMovieDao: MovieDao): LocalRepo =
            INSTANCE ?: LocalRepo(mMovieDao)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mMovieDao.getMovies()

    fun getAllTv(): LiveData<List<TvEntity>> = mMovieDao.getTv()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getFavoriteMovie()

    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity> = mMovieDao.getFavoriteTv()

    fun getDetailMovie(id: String?): LiveData<MovieEntity> = mMovieDao.getMovieId(id)

    fun getDetailTv(id: String?): LiveData<TvEntity> = mMovieDao.getTvId(id)

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun insertTvShows(tvs: List<TvEntity>) = mMovieDao.insertTv(tvs)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean?) {
        movie.bookmarked = newState
        mMovieDao.updateMovie(movie)
    }

    fun setTvShowFavorite(tv: TvEntity, newState: Boolean?) {
        tv.bookmarked = newState
        mMovieDao.updateTvShow(tv)
    }

}