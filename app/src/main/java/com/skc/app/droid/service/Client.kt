package com.skc.app.droid.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getSKCAPIClient(): SKCService {
    return Retrofit.Builder().baseUrl("https://skc-ygo-api.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
        .create(SKCService::class.java)
}

fun getSuggestionEngineClient(): SuggestionEngineService {
    return Retrofit.Builder().baseUrl("https://suggestions.skc-ygo-api.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
        .create(SuggestionEngineService::class.java)
}