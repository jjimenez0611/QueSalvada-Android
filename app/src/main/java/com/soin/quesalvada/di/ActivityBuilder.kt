package com.soin.quesalvada.di

import com.soin.quesalvada.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by jjimenez on 11/10/18.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

}