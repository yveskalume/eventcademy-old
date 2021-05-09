package com.yvkalume.eventcademy.di

import com.yvkalume.domain.entity.Club
import com.yvkalume.eventcademy.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.di.mavericks.MavericksViewModelComponent
import com.yvkalume.eventcademy.di.mavericks.ViewModelKey
import com.yvkalume.eventcademy.ui.fragment.event.EventFragmentViewModel
import com.yvkalume.eventcademy.ui.fragment.allclubs.AllClubsViewModel
import com.yvkalume.eventcademy.ui.fragment.clubdetails.ClubDetailsViewModel
import com.yvkalume.eventcademy.ui.fragment.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModelFactory(factory: HomeViewModel.Factory) : AssistedViewModelFactory<*,*>

    @Binds
    @IntoMap
    @ViewModelKey(EventFragmentViewModel::class)
    fun eventFragmentViewModelFactory(factory: EventFragmentViewModel.Factory) : AssistedViewModelFactory<*,*>

    @Binds
    @IntoMap
    @ViewModelKey(AllClubsViewModel::class)
    fun allClubsViewModelFactory(factory: AllClubsViewModel.Factory) : AssistedViewModelFactory<*,*>

    @Binds
    @IntoMap
    @ViewModelKey(ClubDetailsViewModel::class)
    fun allClubDetailsViewModelFactory(factory: ClubDetailsViewModel.Factory) : AssistedViewModelFactory<*,*>
}