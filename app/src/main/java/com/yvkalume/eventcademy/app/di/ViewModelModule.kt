package com.yvkalume.eventcademy.app.di

import com.yvkalume.eventcademy.app.di.mavericks.AssistedViewModelFactory
import com.yvkalume.eventcademy.app.di.mavericks.MavericksViewModelComponent
import com.yvkalume.eventcademy.app.di.mavericks.ViewModelKey
import com.yvkalume.eventcademy.ui.screen.eventdetail.business.EventDetailsViewModel
import com.yvkalume.eventcademy.ui.screen.home.business.HomeViewModel
import com.yvkalume.eventcademy.ui.screen.login.business.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun loginViewModelFactory(factory: LoginViewModel.Factory) : AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModelFactory(factory: HomeViewModel.Factory) : AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailsViewModel::class)
    fun eventDetailsViewModelFactory(factory: EventDetailsViewModel.Factory) : AssistedViewModelFactory<*, *>
}