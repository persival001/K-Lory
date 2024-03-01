package com.persival.k_lory.di

import com.persival.k_lory.data.food_facts.FoodDataCentralRepositoryImpl
import com.persival.k_lory.domain.food_facts.FoodDataCentralRepository
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
    abstract fun bindFoodDataCentralRepository(impl: FoodDataCentralRepositoryImpl): FoodDataCentralRepository
}