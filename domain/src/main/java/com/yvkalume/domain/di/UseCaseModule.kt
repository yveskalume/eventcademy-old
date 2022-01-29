package com.yvkalume.domain.di

import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.repository.UserRepository
import com.yvkalume.domain.usecase.club.GetAllClubUseCase
import com.yvkalume.domain.usecase.club.GetEventsByClubUidUseCase
import com.yvkalume.domain.usecase.club.GetOneClubByUidUseCase
import com.yvkalume.domain.usecase.event.*
import com.yvkalume.domain.usecase.user.AddUserUseCase
import com.yvkalume.domain.usecase.user.GetUserByUidUseCase
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
    fun provideGetAllEventUseCase(repository: EventRepository,@IoDispatcher dispatcher: CoroutineDispatcher) : GetComingEventUseCase {
        return GetComingEventUseCase(repository,dispatcher)
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

    @Provides
    fun provideGetAttendeesUseCase(repository: EventRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : GetAttendeesUseCase {
        return GetAttendeesUseCase(repository,dispatcher)
    }

    @Provides
    fun provideGetUserByUseCase(repository: UserRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : GetUserByUidUseCase {
        return GetUserByUidUseCase(repository,dispatcher)
    }

    @Provides
    fun provideCheckIfUserIsAttendingUseCase(repository: EventRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : CheckIfUserIsAttendingUseCase {
        return CheckIfUserIsAttendingUseCase(repository,dispatcher)
    }

    @Provides
    fun provideGetAllClubUseCase(repository: ClubRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : GetAllClubUseCase {
        return GetAllClubUseCase(repository,dispatcher)
    }

    @Provides
    fun provideGetOneClubByUidUseCase(repository: ClubRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : GetOneClubByUidUseCase {
        return GetOneClubByUidUseCase(repository,dispatcher)
    }

    @Provides
    fun provideGetEventsByClubUidUseCase(repository: ClubRepository, @IoDispatcher dispatcher: CoroutineDispatcher) : GetEventsByClubUidUseCase {
        return GetEventsByClubUidUseCase(repository,dispatcher)
    }

}