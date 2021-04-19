package com.yvkalume.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.repository.EventRepositoryImpl
import com.yvkalume.data.repository.UserRepositoryImpl
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.repository.UserRepository
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

    @Provides
    fun provideUserRepository(firestore: FirebaseFirestore,firebaseAuth: FirebaseAuth) : UserRepository {
        return UserRepositoryImpl(firestore,firebaseAuth)
    }
}