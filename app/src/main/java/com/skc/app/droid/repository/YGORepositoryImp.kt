package com.skc.app.droid.repository

import com.skc.app.droid.model.CardOfTheDay
import com.skc.app.droid.model.DBStats
import com.skc.app.droid.model.TrendingData
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.service.SKCAPIService
import com.skc.app.droid.service.SuggestionEngineService
import com.skc.app.droid.service.getSKCAPIClient
import com.skc.app.droid.service.getSuggestionEngineClient
import retrofit2.Response

interface SKCRepository {
    suspend fun getDBStats(): Response<DBStats>
    suspend fun getCardInfo(cardID: String): Response<YGOCard>
}

interface SuggestionEngineRepository {
    suspend fun getCardOfTheDay(): Response<CardOfTheDay>
    suspend fun getTrends(resource: String): Response<TrendingData>
}

interface YGORepository : SKCRepository, SuggestionEngineRepository

class YGORepositoryImp(
    private val skcAPIClient: SKCAPIService = getSKCAPIClient(),
    private val suggestionEngineClient: SuggestionEngineService = getSuggestionEngineClient(),
) : YGORepository {
    override suspend fun getDBStats(): Response<DBStats> {
        return skcAPIClient.getDBStats()
    }

    override suspend fun getCardInfo(cardID: String): Response<YGOCard> {
        return skcAPIClient.getCardInfo(cardID)
    }

    override suspend fun getCardOfTheDay(): Response<CardOfTheDay> {
        return suggestionEngineClient.getCardOfTheDay()
    }

    override suspend fun getTrends(resource: String): Response<TrendingData> {
        return suggestionEngineClient.getCardOfTheDay(resource)
    }
}