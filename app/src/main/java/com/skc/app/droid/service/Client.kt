package com.skc.app.droid.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val converterFactory = MoshiConverterFactory.create(moshi)

private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
    val original = chain.request()

    val requestBuilder = original.newBuilder()
        .header("Connection", "keep-alive")
        .header("User-Agent", "SKCDroid")
        .header("CLIENT_ID", "SKCDroid")

    val request = requestBuilder.build()
    chain.proceed(request)
}
    .connectTimeout(1, TimeUnit.SECONDS)
    .readTimeout(3, TimeUnit.SECONDS)
    .connectionPool(
        ConnectionPool(8, 5, TimeUnit.MINUTES)
    )
    .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
    .build()


fun getSKCAPIClient(): SKCAPIService = Retrofit.Builder()
    .commonConfigs("https://skc-ygo-api.com")
    .create(SKCAPIService::class.java)


fun getSuggestionEngineClient(): SuggestionEngineService = Retrofit.Builder()
    .commonConfigs("https://suggestions.skc-ygo-api.com")
    .create(SuggestionEngineService::class.java)


fun getHeartAPIClient(): HeartAPIService = Retrofit.Builder()
    .commonConfigs("https://heart-api.com")
    .create(HeartAPIService::class.java)


private fun Retrofit.Builder.commonConfigs(baseURL: String): Retrofit = this
    .baseUrl(baseURL)
    .addConverterFactory(converterFactory)
    .client(httpClient)
    .build()
