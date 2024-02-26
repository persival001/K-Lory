package com.persival.k_lory.di

import com.persival.k_lory.data.api_service.OpenFoodFactsRepositoryImpl
import com.persival.k_lory.domain.api.OpenFoodFactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindingModule {

    @Singleton
    @Binds
    abstract fun bindOpenFoodFactsRepository(impl: OpenFoodFactsRepositoryImpl): OpenFoodFactsRepository

}