package com.skc.app.droid.repository

import com.skc.app.droid.model.Event
import com.skc.app.droid.service.HeartAPIService
import com.skc.app.droid.service.getHeartAPIClient
import retrofit2.Response

interface HeartAPIRepository {
    suspend fun getEvents(
        service: String = "skc",
        tags: String = "product-release"
    ): Response<List<Event>>
}

interface NextRepository : HeartAPIRepository

class NextRepositoryImp(
    private val heartAPIService: HeartAPIService = getHeartAPIClient(),
) : NextRepository {
    override suspend fun getEvents(service: String, tags: String): Response<List<Event>> {
        return heartAPIService.getEvents(service = service, tags = tags)
    }
}