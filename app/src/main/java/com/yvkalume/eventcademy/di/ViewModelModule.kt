package com.yvkalume.eventcademy.di

import com.yvkalume.eventcademy.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.di.mavericks.MavericksViewModelComponent
import com.yvkalume.eventcademy.di.mavericks.ViewModelKey
import com.yvkalume.eventcademy.ui.fragment.home.HomeViewModel
import com.yvkalume.eventcademy.ui.fragment.home.HomeViewState
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
}