package com.soin.quesalvada

import android.util.Log
import com.facebook.appevents.AppEventsLogger
import com.soin.quesalvada.di.DaggerAppComponent
import com.twitter.sdk.android.core.Twitter
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig




class QueSalvadaApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        // FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)
        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig(getString(R.string.CONSUMER_KEY), getString(R.string.CONSUMER_SECRET)))
                .debug(true)
                .build()
        Twitter.initialize(config)

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}