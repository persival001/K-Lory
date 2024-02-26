package com.persival.k_lory.di

import com.persival.k_lory.data.api_service.OpenFoodFactsApi
import com.persival.k_lory.data.api_service.OpenFoodFactsFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = OpenFoodFactsFactory.create()

    @Provides
    @Singleton
    fun providePlacesApi(retrofit: Retrofit): OpenFoodFactsApi = retrofit.create()

}