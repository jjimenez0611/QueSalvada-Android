package com.soin.quesalvada

import com.soin.quesalvada.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class QueSalvadaApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}