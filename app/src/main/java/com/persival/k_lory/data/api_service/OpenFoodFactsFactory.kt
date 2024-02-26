package com.persival.k_lory.data.api_service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenFoodFactsFactory {
    fun create(): Retrofit = Retrofit.Builder()
        .baseUrl("https://world.openfoodfacts.org/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .addInterceptor(
                    Interceptor { chain: Interceptor.Chain ->
                        chain.proceed(
                            chain.request().let {
                                it.newBuilder().url(
                                    it.url.newBuilder()
                                        .build()
                                ).build()
                            }
                        )
                    }
                )
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}