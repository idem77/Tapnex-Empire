package com.tapnexempire.di

import android.content.Context
import com.tapnexempire.sdk.LudoSdkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SdkModule {

    @Provides
    @Singleton
    fun provideLudoSdkManager(
        @ApplicationContext context: Context
    ): LudoSdkManager {
        return LudoSdkManager(context)
    }
}
