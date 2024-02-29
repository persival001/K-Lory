package com.persival.k_lory.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.persival.k_lory.data.food_facts.OpenFoodFactsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOpenFoodFactApi(@OpenFoodFactsRetrofit retrofit: Retrofit): OpenFoodFactsApi =
        retrofit.create(OpenFoodFactsApi::class.java)

    @Provides
    @Singleton
    @OpenFoodFactsRetrofit
    fun provideOpenFoodFactsRetrofit(
        @OpenFoodFactsOkHttpClient okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nal.usda.gov/fdc/v1/foods/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    @OpenFoodFactsOkHttpClient
    fun provideOpenFoodFactsOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OpenFoodFactsRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OpenFoodFactsOkHttpClient
}