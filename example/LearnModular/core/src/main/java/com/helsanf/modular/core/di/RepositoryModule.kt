package com.helsanf.modular.core.di

import com.helsanf.modular.core.data.SportRepository
import com.helsanf.modular.core.domain.repository.IDomainRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class,DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(sportRepository: SportRepository) : IDomainRepository
}