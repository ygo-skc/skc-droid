package com.skc.app.droid.service

import com.skc.app.droid.model.CardOfTheDay
import com.skc.app.droid.model.TrendingData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuggestionEngineService {
    @GET("/api/v1/suggestions/card-of-the-day")
    suspend fun getCardOfTheDay(): Response<CardOfTheDay>

    @GET("/api/v1/suggestions/trending/{resource}")
    suspend fun getCardOfTheDay(@Path("resource") resource: String): Response<TrendingData>
}