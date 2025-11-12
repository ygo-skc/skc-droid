package com.skc.app.droid.repository

import com.skc.app.droid.model.CardOfTheDay
import com.skc.app.droid.model.DBStats
import com.skc.app.droid.service.SKCAPIService
import com.skc.app.droid.service.SuggestionEngineService
import com.skc.app.droid.service.getSKCAPIClient
import com.skc.app.droid.service.getSuggestionEngineClient
import retrofit2.Response

interface SKCRepository {
    suspend fun getDBStats(): Response<DBStats>
}

interface SuggestionEngineRepository {
    suspend fun getCardOfTheDay(): Response<CardOfTheDay>
}

interface YGORepository : SKCRepository, SuggestionEngineRepository

class YGORepositoryImp(
    private val skcAPIClient: SKCAPIService = getSKCAPIClient(),
    private val suggestionEngineClient: SuggestionEngineService = getSuggestionEngineClient(),
) : YGORepository {
    override suspend fun getDBStats(): Response<DBStats> {
        return skcAPIClient.getDBStats()
    }

    override suspend fun getCardOfTheDay(): Response<CardOfTheDay> {
        return suggestionEngineClient.getCardOfTheDay()
    }
}