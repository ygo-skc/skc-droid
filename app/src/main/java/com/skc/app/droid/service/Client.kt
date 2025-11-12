package com.skc.app.droid.service

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
    val original = chain.request()

    val requestBuilder = original.newBuilder()
        .header("Accept", "application/json")
        .header("Content-Type", "application/json")
        .header("Connection", "keep-alive")
        .header("User-Agent", "SKCDroid")
        .header("CLIENT_ID", "SKCDroid")
//        .header("Accept-Encoding", "gzip, deflate")

    val request = requestBuilder.build()
    chain.proceed(request)
}
    .connectTimeout(1, TimeUnit.SECONDS)
    .readTimeout(3, TimeUnit.SECONDS)
    .connectionPool(
        ConnectionPool(8, 30, TimeUnit.SECONDS)
    )


fun getSKCAPIClient(): SKCService {
    return Retrofit.Builder().baseUrl("https://skc-ygo-api.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
        .create(SKCService::class.java)
}

fun getSuggestionEngineClient(): SuggestionEngineService {
    return Retrofit.Builder().baseUrl("https://suggestions.skc-ygo-api.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
        .create(SuggestionEngineService::class.java)
}