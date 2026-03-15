package com.tapnexempire.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.repository.TournamentRepository
import com.tapnexempire.data.repository.WalletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    // 🔥 ADD THIS
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideTournamentRepository(
        firestore: FirebaseFirestore
    ): TournamentRepository {
        return TournamentRepository(firestore)
    }

    @Provides
    @Singleton
    fun provideWalletRepository(
        firestore: FirebaseFirestore
    ): WalletRepository {
        return WalletRepository(firestore)
    }
}
