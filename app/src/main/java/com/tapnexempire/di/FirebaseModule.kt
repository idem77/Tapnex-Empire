package com.tapnexempire.di

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
object AppModule {

@Provides  
@Singleton  
fun provideFirestore(): FirebaseFirestore {  
    return FirebaseFirestore.getInstance()  
}  

@Provides  
@Singleton  
fun provideWalletRepository(  
    firestore: FirebaseFirestore  
): WalletRepository {  
    return WalletRepository(firestore)  
}  

@Provides  
@Singleton  
fun provideTournamentRepository(  
    firestore: FirebaseFirestore  
): TournamentRepository {  
    return TournamentRepository(firestore)  
}

}
