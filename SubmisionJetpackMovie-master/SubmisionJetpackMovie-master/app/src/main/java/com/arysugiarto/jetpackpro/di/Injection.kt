package com.arysugiarto.jetpackpro.di

import android.content.Context
import com.arysugiarto.jetpackpro.data.repo.Repo
import com.arysugiarto.jetpackpro.data.repo.local.LocalRepo
import com.arysugiarto.jetpackpro.data.repo.local.room.DbMovie
import com.arysugiarto.jetpackpro.data.repo.remote.RemoteDataSource
import com.arysugiarto.jetpackpro.helper.RestAPI
import com.arysugiarto.jetpackpro.helper.ApiInterface
import com.arysugiarto.jetpackpro.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repo {

        val database = DbMovie.getInstance(context)


        val remoteDataSource = RemoteDataSource.getInstance(
            RestAPI().getClient().create(ApiInterface::class.java)
        )
        val localDataSource = LocalRepo.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return Repo.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}