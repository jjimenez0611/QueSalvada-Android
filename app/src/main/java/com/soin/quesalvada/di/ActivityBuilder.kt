package com.soin.quesalvada.di

import com.soin.quesalvada.ui.activities.DashboardActivity
import com.soin.quesalvada.ui.activities.LoginActivity
import com.soin.quesalvada.ui.activities.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by jjimenez on 11/10/18.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector()
    abstract fun bindDashboardActivity(): DashboardActivity

}