package com.arysugiarto.jetpackpro.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arysugiarto.jetpackpro.data.repo.remote.RemoteDataSource
import com.arysugiarto.jetpackpro.data.model.ModelMovie
import com.arysugiarto.jetpackpro.data.model.ModelTvShow
import com.arysugiarto.jetpackpro.data.repo.local.LocalRepo
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.data.repo.remote.ApiResponse
import com.arysugiarto.jetpackpro.utils.AppExecutors
import com.arysugiarto.jetpackpro.vo.Resource

class FakeRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localRepo: LocalRepo,
    private val appExecutors: AppExecutors
) : DataSource {
    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<ModelMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> = localRepo.getAllMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModelMovie>?>> =
                remoteDataSource.getDataMovie()

            override fun saveCallResult(data: List<ModelMovie>?) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data!!) {
                    val movie = MovieEntity(
                        response.id!!,
                        response.title,
                        response.poster_path,
                        response.overview,
                        response.release_date,
                        response.vote_average
                    )
                    movieList.add(movie)
                }
                localRepo.insertMovies(movieList)
            }


        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvEntity>>> {
        return object : NetworkBoundResource<List<TvEntity>, List<ModelTvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvEntity>> =
                localRepo.getAllTv()

            override fun shouldFetch(data: List<TvEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModelTvShow>?>> =
                remoteDataSource.getDataTvShow()

            override fun saveCallResult(data: List<ModelTvShow>?) {
                val tvShowList = ArrayList<TvEntity>()
                for (response in data!!) {
                    val tvShow = TvEntity(
                        response.id!!,
                        response.name,
                        response.poster_path,
                        response.overview,
                        response.first_air_date,
                        response.vote_average
                    )
                    tvShowList.add(tvShow)
                }
                localRepo.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(movieId: String?): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<ModelMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localRepo.getDetailMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<ModelMovie>?>> =
                remoteDataSource.getDataMovie()

            override fun saveCallResult(data: List<ModelMovie>?) {

            }

        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: String?): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, List<ModelTvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<TvEntity> =
                localRepo.getDetailTv(tvShowId)

            override fun shouldFetch(data: TvEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<ModelTvShow>?>> =
                remoteDataSource.getDataTvShow()

            override fun saveCallResult(data: List<ModelTvShow>?) {

            }

        }.asLiveData()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean?) =
        appExecutors.diskIO().execute { localRepo.setMovieFavorite(movie, state) }

    override fun setTvShowFavorite(tv: TvEntity, state: Boolean?) =
        appExecutors.diskIO().execute { localRepo.setTvShowFavorite(tv, state) }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localRepo.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localRepo.getFavoriteTv(), config).build()
    }


}