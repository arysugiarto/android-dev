package com.helsanf.modular.core.main

import com.helsanf.modular.core.domain.usecase.SportInteractor
import com.helsanf.modular.core.domain.usecase.SportUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule{
    @Binds
    abstract fun provideTourismUseCase(sportInteractor: SportInteractor) : SportUseCase
}