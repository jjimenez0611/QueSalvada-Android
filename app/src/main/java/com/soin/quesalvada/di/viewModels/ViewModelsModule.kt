package com.soin.quesalvada.di.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soin.quesalvada.viewModel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

      @Binds
      @IntoMap
      @ViewModelKey(LoginViewModel::class)
      abstract fun bindLoginViewModel(dataViewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}