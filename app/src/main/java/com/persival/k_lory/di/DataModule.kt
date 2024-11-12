package com.persival.k_lory.di

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.persival.k_lory.BuildConfig
import com.persival.k_lory.data.food_facts.FoodDataCentralApi
import com.persival.k_lory.ui.utils.ResourceProvider
import com.persival.k_lory.ui.utils.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
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
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val urlWithApiKey = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.FOOD_DATA_CENTRAL_API_KEY)
            .build()
        val newRequest = originalRequest.newBuilder().url(urlWithApiKey).build()
        Log.d("ApiKeyInterceptor", "Request URL: ${newRequest.url}")
        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideFoodDataCentralApi(@OpenFoodFactsRetrofit retrofit: Retrofit): FoodDataCentralApi =
        retrofit.create(FoodDataCentralApi::class.java)

    @Provides
    @Singleton
    @OpenFoodFactsRetrofit
    fun provideFoodDataCentralRetrofit(
        @OpenFoodFactsOkHttpClient okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nal.usda.gov/fdc/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    @OpenFoodFactsOkHttpClient
    fun provideFoodDataCentralOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(apiKeyInterceptor)
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
