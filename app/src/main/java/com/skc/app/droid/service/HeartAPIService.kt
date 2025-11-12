package com.skc.app.droid.service

import com.skc.app.droid.model.Events
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeartAPIService {
    @GET("/api/v1/events")
    suspend fun getEvents(
        @Query("service") service: String,
        @Query("tags") tags: String,
    ): Response<Events>
}