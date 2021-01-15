package com.helsanf.modular.learn.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.helsanf.modular.core.main.ViewModelKey
import com.helsanf.modular.core.ui.ViewModelFactory
import com.helsanf.modular.learn.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}