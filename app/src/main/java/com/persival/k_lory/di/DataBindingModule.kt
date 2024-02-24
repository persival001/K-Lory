package com.persival.k_lory.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindingModule {

    /*@Singleton
    @Binds
    abstract fun bindFirebaseRepositoryImpl(firebaseRepository: FirebaseRepository): FirebaseRepositoryImpl*/

}