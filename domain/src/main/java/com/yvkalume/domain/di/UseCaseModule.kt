package com.yvkalume.domain.di

import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.domain.usecase.event.*
import com.yvkalume.domain.usecase.user.AddUserUseCase
import com.yvkalume.domain.usecase.user.SetHasGoingToAnEventUseCase
import com.yvkalume.domain.usecase.user.SignInWithGoogleUseCase
import com.yvkalume.util.annotation.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllEventUseCase(repository: EventRepository,@IoDispatcher dispatcher: CoroutineDispatcher) : GetAllEventUseCase {
        return GetAllEventUseCase(repository,dispatcher)
    }

    @Provides
    fun provideGetNextEventUseCase(repository: EventRepository,@IoDispatcher dispatcher: CoroutineDispatcher) : GetNextEventUseCase {
        return GetNextEventUseCase(repository,dispatcher)
    }

    @Provides
    fun provideGetOfflineEventUseCase(repository: EventRepository,@IoDispatcher dispatcher: CoroutineDispatcher) : GetOfflineEventsUseCase {
        return GetOfflineEventsUseCase(repository,dispatcher)
    }

    @Provides
    fun provideGetOnlineEventUseCase(repository: EventRepository,@IoDispatcher dispatcher: CoroutineDispatcher) : GetOnlineEventsUseCase {
        return GetOnlineEventsUseCase(repository,dispatcher)
    }

    @Provides
    fun provideGetOneEventBiUidUseCase(repository: EventRepository,@IoDispatcher dispatcher: CoroutineDispatcher) : GetOneEventByUidUseCase {
        return GetOneEventByUidUseCase(repository,dispatcher)
    }

    @Provides
    fun provideAddUserUseCase(repository: UserRepository,@IoDispatcher dispatcher: CoroutineDispatcher) : AddUserUseCase {
        return AddUserUseCase(repository,dispatcher)
    }

    @Provides
    fun provideSignInWithGoogleUseCase(repository: UserRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : SignInWithGoogleUseCase {
        return SignInWithGoogleUseCase(repository,dispatcher)
    }

    @Provides
    fun provideSetHasGoingToAnEventUseCase(repository: UserRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : SetHasGoingToAnEventUseCase {
        return SetHasGoingToAnEventUseCase(repository,dispatcher)
    }
}