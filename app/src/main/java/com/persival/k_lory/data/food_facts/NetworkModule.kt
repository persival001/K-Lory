package com.persival.k_lory.data.food_facts

import com.persival.k_lory.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://api.nal.usda.gov/fdc/v1/"

    fun createFoodDataCentralApi(): FoodDataCentralApi {
        val apiKeyInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val urlWithApiKey = originalRequest.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.FOOD_DATA_CENTRAL_API_KEY)
                .build()
            val newRequest = originalRequest.newBuilder().url(urlWithApiKey).build()
            chain.proceed(newRequest)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodDataCentralApi::class.java)
    }
}
