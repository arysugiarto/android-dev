package com.helsanf.modular.learn

import android.app.Application
import com.helsanf.modular.core.di.CoreComponent
import com.helsanf.modular.core.di.DaggerCoreComponent
import com.helsanf.modular.learn.di.AppComponent
import com.helsanf.modular.learn.di.DaggerAppComponent

open class MyApplication : Application(){

    private val coreComponent : CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }



}