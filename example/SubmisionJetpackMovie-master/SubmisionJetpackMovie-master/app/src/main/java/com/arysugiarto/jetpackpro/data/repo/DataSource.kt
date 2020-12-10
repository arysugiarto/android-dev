package com.arysugiarto.jetpackpro.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.vo.Resource

interface DataSource {
    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getTvShows(): LiveData<Resource<List<TvEntity>>>
    fun getDetailMovie(movieId: String?): LiveData<Resource<MovieEntity>>
    fun getDetailTvShow(tvShowId: String?): LiveData<Resource<TvEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean?)
    fun setTvShowFavorite(tv: TvEntity, state: Boolean?)
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun getFavoriteTv(): LiveData<PagedList<TvEntity>>
}