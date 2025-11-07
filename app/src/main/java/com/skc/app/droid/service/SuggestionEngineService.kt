package com.skc.app.droid.service

import com.skc.app.droid.model.CardOfTheDay
import retrofit2.Response
import retrofit2.http.GET

interface SuggestionEngineService {
    @GET("/api/v1/suggestions/card-of-the-day")
    suspend fun getCardOfTheDay(): Response<CardOfTheDay>
}