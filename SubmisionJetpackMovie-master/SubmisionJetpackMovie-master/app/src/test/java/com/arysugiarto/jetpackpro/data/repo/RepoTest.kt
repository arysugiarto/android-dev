package com.arysugiarto.jetpackpro.data.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.arysugiarto.jetpackpro.data.repo.local.LocalRepo
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.data.repo.remote.RemoteDataSource
import com.arysugiarto.jetpackpro.utils.AppExecutors
import com.arysugiarto.jetpackpro.utils.DataDummy
import com.arysugiarto.jetpackpro.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepoTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalRepo::class.java)
    private val appExecutors = mock(AppExecutors::class.java)


    private val repository = FakeRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy().generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy().generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponses[0].id

    private val dummyMovies = DataDummy().generateRemoteDummyMovies()[0]
    private val dummyTvShow = DataDummy().generateRemoteDummyTvShows()[0]

    @Test
    fun getAllMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy().generateDummyMovies()
        `when`(local.getAllMovies()).thenReturn(dummyMovies)

        val movieEntities = LiveDataTestUtil.getValue(repository.getAllMovies())
        verify(local).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }


    @Test
    fun getDetailMovie() {

        val dummyDetailMovie = MutableLiveData<MovieEntity>()
        dummyDetailMovie.value =
            DataDummy().generateDummyDetailMovie(DataDummy().generateDummyMovies()[0], false)
        `when`(local.getDetailMovie(movieId)).thenReturn(dummyDetailMovie)

        val movieEntities = LiveDataTestUtil.getValue(repository.getDetailMovie(movieId))

        verify(local).getDetailMovie(eq(movieId))
        assertNotNull(movieEntities)
        assertNotNull(movieEntities.data?.title)
        assertEquals(dummyMovies.title, movieEntities.data?.title)
    }

    @Test
    fun getAllTvShows() {
        val dummyTvShow = MutableLiveData<List<TvEntity>>()
        dummyTvShow.value = DataDummy().generateDummyTvShows()
        `when`(local.getAllTv()).thenReturn(dummyTvShow)

        val tvShowEntities = LiveDataTestUtil.getValue(repository.getTvShows())
        verify(local).getAllTv()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTvShow() {

        val dummyDetailTvShow = MutableLiveData<TvEntity>()
        dummyDetailTvShow.value =
            DataDummy().generateDummyDetaiTvShow(DataDummy().generateDummyTvShows()[0], false)
        `when`(local.getDetailTv(tvShowId)).thenReturn(dummyDetailTvShow)

        val tvShowEntities = LiveDataTestUtil.getValue(repository.getDetailTvShow(tvShowId))

        verify(local).getDetailTv(eq(tvShowId))
        assertNotNull(tvShowEntities)
        assertNotNull(tvShowEntities.data?.name)
        assertEquals(dummyTvShow.name, tvShowEntities.data?.name)
    }
}