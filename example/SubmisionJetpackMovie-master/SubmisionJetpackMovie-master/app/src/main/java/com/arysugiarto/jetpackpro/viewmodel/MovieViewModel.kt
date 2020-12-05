package com.arysugiarto.jetpackpro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.arysugiarto.jetpackpro.data.repo.Repo
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.vo.Resource

class MovieViewModel(private val repo: Repo) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>> = repo.getAllMovies()

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = repo.getFavoriteMovies()
}