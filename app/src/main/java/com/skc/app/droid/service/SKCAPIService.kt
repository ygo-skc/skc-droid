package com.skc.app.droid.service

import com.skc.app.droid.model.DBStats
import com.skc.app.droid.model.YGOCard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SKCAPIService {
    @GET("/api/v1/stats")
    suspend fun getDBStats(): Response<DBStats>

    @GET("/api/v1/card/{cardID}")
    suspend fun getCardInfo(
        @Path("cardID") cardID: String,
        @Query("allInfo") allInfo: Boolean
    ): Response<YGOCard>

    @GET("/api/v1/card/search")
    suspend fun searchCard(
        @Query("limit") limit: Int,
        @Query("cName") cardName: String,
    ): Response<List<YGOCard>>
}