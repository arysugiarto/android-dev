package com.arysugiarto.jetpackpro.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.arysugiarto.jetpackpro.data.repo.Repo
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.utils.DataDummy
import com.arysugiarto.jetpackpro.viewmodel.TvShowViewModel
import com.arysugiarto.jetpackpro.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var repo: Repo

    @Mock
    private lateinit var observer: Observer<Resource<List<TvEntity>>>

    @Before
    fun setUp() {
        viewModel =
            TvShowViewModel(repo)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = Resource.success(DataDummy().generateDummyTvShows())
        val courses = MutableLiveData<Resource<List<TvEntity>>>()

        courses.value = dummyTvShows
        Mockito.`when`(repo.getTvShows()).thenReturn(courses)
        val tvShow = viewModel.getTvShows().value?.data
        verify(repo).getTvShows()
        assertNotNull(tvShow)
        assertEquals(10, tvShow?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}