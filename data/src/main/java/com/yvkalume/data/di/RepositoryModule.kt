package com.yvkalume.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.repository.EventRepositoryImpl
import com.yvkalume.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideEventRepository(firestore: FirebaseFirestore) : EventRepository {
        return EventRepositoryImpl(firestore)
    }
}