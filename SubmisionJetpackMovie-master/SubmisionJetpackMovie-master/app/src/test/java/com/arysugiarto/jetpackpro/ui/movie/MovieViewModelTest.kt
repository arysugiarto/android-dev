package com.arysugiarto.jetpackpro.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.arysugiarto.jetpackpro.data.repo.Repo
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.utils.DataDummy
import com.arysugiarto.jetpackpro.viewmodel.MovieViewModel
import com.arysugiarto.jetpackpro.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var repo: Repo

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp() {
        viewModel =
            MovieViewModel(repo)
    }

    //
    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(DataDummy().generateDummyMovies())
        val courses = MutableLiveData<Resource<List<MovieEntity>>>()
        courses.value = dummyMovies

        `when`(repo.getAllMovies()).thenReturn(courses)
        val movie = viewModel.getMovies().value?.data
        verify(repo).getAllMovies()
        assertNotNull(movie)
        assertEquals(10, movie?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)

    }
}