package com.soin.quesalvada.di.viewModels

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelsModule {

    /*   @Binds
       @IntoMap
       @ViewModelKey(AnnouncementListViewModel::class)
       abstract fun bindAnnouncementViewModel(announcementListViewModel: AnnouncementListViewModel): ViewModel

       @Binds
       @IntoMap
       @ViewModelKey(ChurchListViewModel::class)
       abstract fun bindChurchMapViewModel(churchListViewModel: ChurchListViewModel): ViewModel

       @Binds
       @IntoMap
       @ViewModelKey(ParishViewModel::class)
       abstract fun bindParishViewModel(parishViewModel: ParishViewModel): ViewModel

       @Binds
       @IntoMap
       @ViewModelKey(MapViewModel::class)
       abstract fun bindMapViewModel(mapViewModel: MapViewModel) : ViewModel

       @Binds
       @IntoMap
       @ViewModelKey(ChurchViewModel::class)
       abstract fun bindDetailMapViewModel(mapViewModel: ChurchViewModel): ViewModel

       @Binds
       @IntoMap
       @ViewModelKey(EucharistListViewModel::class)
       abstract fun bindEucharistViewModel(eucharistListViewModel: EucharistListViewModel): ViewModel*/

    /*  @Binds
      @IntoMap
      @ViewModelKey(DataViewModel::class)
      abstract fun bindDataViewModel(dataViewModel: DataViewModel): ViewModel*/

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}