package com.helsanf.modular.favorite.di

import com.helsanf.modular.core.di.CoreComponent
import com.helsanf.modular.core.main.AppModule
import com.helsanf.modular.core.main.AppScope
import com.helsanf.modular.favorite.FavoriteFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, FavoriteViewModelModule::class]
)
interface FavoriteComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): FavoriteComponent
    }

    fun inject(fragment: FavoriteFragment)
}