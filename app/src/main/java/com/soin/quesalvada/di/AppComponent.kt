package com.soin.quesalvada.di

import android.app.Application
import com.soin.quesalvada.QueSalvadaApplication
import com.soin.quesalvada.di.viewModels.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by jjimenez on 11/10/18.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelsModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(app: QueSalvadaApplication)

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}