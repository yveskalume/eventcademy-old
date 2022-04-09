package com.yvkalume.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.repository.AttendeeRepositoryImpl
import com.yvkalume.data.repository.EventRepositoryImpl
import com.yvkalume.data.repository.UserRepositoryImpl
import com.yvkalume.data.util.FireBasePath
import com.yvkalume.domain.repository.AttendeeRepository
import com.yvkalume.domain.repository.EventRepository
import com.yvkalume.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFirebasePath(): FireBasePath {
        return FireBasePath
    }

    @Provides
    fun provideUserRepository(firebaseAuth: FirebaseAuth,firestore: FirebaseFirestore) : UserRepository {
        return UserRepositoryImpl(firebaseAuth, firestore)
    }

    @Provides
    fun provideEventRepository(firestore: FirebaseFirestore) : EventRepository {
        return EventRepositoryImpl(firestore)
    }

    @Provides
    fun provideAttendeeRepository(firestore: FirebaseFirestore,firebaseAuth: FirebaseAuth,firebasePath: FireBasePath) : AttendeeRepository {
        return AttendeeRepositoryImpl(firestore,firebasePath)
    }
}