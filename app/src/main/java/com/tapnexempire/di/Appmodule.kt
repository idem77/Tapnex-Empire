package com.tapnexempire.di

import com.tapnexempire.repository.AuthRepository
import com.tapnexempire.repository.WalletRepository
import com.tapnexempire.service.AuthService
import com.tapnexempire.service.WalletService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // ---------------- AUTH ----------------

    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        return AuthService()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
        walletRepository: WalletRepository
    ): AuthRepository {
        return AuthRepository(authService, walletRepository)
    }

    // ---------------- WALLET ----------------

    @Provides
    @Singleton
    fun provideWalletService(): WalletService {
        return WalletService()
    }

    @Provides
    @Singleton
    fun provideWalletRepository(
        walletService: WalletService
    ): WalletRepository {
        return WalletRepository(walletService)
    }
}
