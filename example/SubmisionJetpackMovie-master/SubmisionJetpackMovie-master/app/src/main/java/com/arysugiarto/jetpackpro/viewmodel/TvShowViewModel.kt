package com.arysugiarto.jetpackpro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.arysugiarto.jetpackpro.data.repo.Repo
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.vo.Resource

class TvShowViewModel(private val repo: Repo) : ViewModel() {

    fun getTvShows(): LiveData<Resource<List<TvEntity>>> = repo.getTvShows()

    fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>> = repo.getFavoriteTv()

}