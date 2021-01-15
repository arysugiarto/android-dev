package com.helsanf.modular.detail.di

import com.helsanf.modular.core.di.CoreComponent
import com.helsanf.modular.core.main.AppModule
import com.helsanf.modular.core.main.AppScope
import com.helsanf.modular.detail.DetailFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, DetailViewModelModule::class]
)
interface DetailComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): DetailComponent
    }

    fun inject(fragment: DetailFragment)
}