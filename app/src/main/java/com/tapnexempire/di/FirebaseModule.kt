package com.tapnexempire.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.repository.TournamentRepository
import com.tapnexempire.repository.WithdrawRepository
import com.tapnexempire.repository.TaskRepository
import com.tapnexempire.repository.AuthRepository
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
    fun provideFirebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore =
        FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideTournamentRepository(
        firestore: FirebaseFirestore
    ): TournamentRepository =
        TournamentRepository(firestore)

    @Provides
@Singleton
fun provideWithdrawRepository(
    firestore: FirebaseFirestore
): WithdrawRepository {
    return WithdrawRepository(firestore)
}

    @Provides
    @Singleton
    fun provideTaskRepository(
        firestore: FirebaseFirestore
    ): TaskRepository =
        TaskRepository(firestore)

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository =
        AuthRepository(auth, firestore)
}
