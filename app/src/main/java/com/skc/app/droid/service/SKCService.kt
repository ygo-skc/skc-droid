package com.skc.app.droid.service

import com.skc.app.droid.model.DBStats
import retrofit2.Response
import retrofit2.http.GET

interface SKCService {
    @GET("/api/v1/stats")
    suspend fun getDBStats(): Response<DBStats>
}