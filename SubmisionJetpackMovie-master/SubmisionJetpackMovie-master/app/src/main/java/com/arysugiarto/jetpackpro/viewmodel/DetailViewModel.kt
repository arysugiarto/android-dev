package com.arysugiarto.jetpackpro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arysugiarto.jetpackpro.data.repo.Repo
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.vo.Resource

class DetailViewModel(private val repo: Repo) : ViewModel() {
    private var movieId: String? = null
    private var tvShowId: String? = null

    fun setSelectedMovie(movieId: String?) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: String?) {
        this.tvShowId = tvShowId
    }

    fun getMovies(): LiveData<Resource<MovieEntity>> = repo.getDetailMovie(movieId)

    fun getTvShows(): LiveData<Resource<TvEntity>> = repo.getDetailTvShow(tvShowId)

    fun setFavoriteMovie(movieEntity: MovieEntity?) {
        if (movieEntity != null) {
            val newState = !movieEntity.bookmarked!!
            repo.setMovieFavorite(movieEntity, newState)
        }
    }

    fun setFavoriteTvShow(tvEntity: TvEntity?) {
        if (tvEntity != null) {
            val newState = !tvEntity.bookmarked!!
            repo.setTvShowFavorite(tvEntity, newState)
        }
    }

}