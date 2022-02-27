package com.yvkalume.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.data.repository.UserRepositoryImpl
import com.yvkalume.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(firebaseAuth: FirebaseAuth,firestore: FirebaseFirestore) : UserRepository {
        return UserRepositoryImpl(firebaseAuth, firestore)
    }
}