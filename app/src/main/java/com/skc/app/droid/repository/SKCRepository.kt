package com.skc.app.droid.repository

import com.skc.app.droid.model.DBStats
import com.skc.app.droid.service.SKCService
import com.skc.app.droid.service.getSKCAPIClient
import retrofit2.Response

interface SKCRepositoryInterface {
    suspend fun getDBStats(): Response<DBStats>
}

class SKCRepository(
    private val service: SKCService = getSKCAPIClient()
) : SKCRepositoryInterface {
    override suspend fun getDBStats(): Response<DBStats> {
        return service.getDBStats()
    }
}