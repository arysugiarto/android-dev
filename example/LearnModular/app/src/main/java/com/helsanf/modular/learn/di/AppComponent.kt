package com.helsanf.modular.learn.di

import com.helsanf.modular.core.di.CoreComponent
import com.helsanf.modular.core.main.AppModule
import com.helsanf.modular.core.main.AppScope
import com.helsanf.modular.learn.ui.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
}