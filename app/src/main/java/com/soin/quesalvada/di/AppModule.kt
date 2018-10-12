package com.soin.quesalvada.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by jjimenez on 11/10/18.
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun provideContext(application: Application): Context
}