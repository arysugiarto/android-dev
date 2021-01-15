package com.helsanf.modular.core.di

import android.content.Context
import com.helsanf.modular.core.domain.repository.IDomainRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : CoreComponent
    }
    fun provideRepository() : IDomainRepository
}