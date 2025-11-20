package com.skc.app.droid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skc.app.droid.model.CardOfTheDay
import com.skc.app.droid.model.DBStats
import com.skc.app.droid.model.Events
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.repository.NextRepository
import com.skc.app.droid.repository.NextRepositoryImp
import com.skc.app.droid.repository.YGORepository
import com.skc.app.droid.repository.YGORepositoryImp
import com.skc.app.droid.x.isDateInvalidated
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

sealed class HomeViewModel(
    private val ygoRepo: YGORepository = YGORepositoryImp(),
    private val nextRepo: NextRepository = NextRepositoryImp(),
) : ViewModel() {
    private val _isFetchingData = MutableStateFlow(true)
    private val _dbStats =
        MutableStateFlow(value = DBStats(productTotal = 0, cardTotal = 0, banListTotal = 0))
    private val _cotd = MutableStateFlow(
        value = CardOfTheDay(date = "", version = 0, card = YGOCard.placeholder)
    )
    private val _upcomingYGOProducts =
        MutableStateFlow(value = Events(service = "skc", events = emptyList()))

    var lastFetchTimestamp: LocalDateTime = LocalDateTime.MIN
        private set

    val isFetchingData get() = _isFetchingData.asStateFlow()
    val dbStats get() = _dbStats.asStateFlow()
    val cotd get() = _cotd.asStateFlow()
    val upcomingYGOProducts get() = _upcomingYGOProducts.asStateFlow()


    fun fetchData(forceRefresh: Boolean) {
        if (lastFetchTimestamp == LocalDateTime.MIN || (forceRefresh && lastFetchTimestamp.isDateInvalidated())) {
            _isFetchingData.value = true
            viewModelScope.launch {
                val dbDeferred = async { fetchDBStatsData() }
                val cotdDeferred = async { fetchCardOfTheDayData() }
                val upcomingDeferred = async { fetchUpcomingYGOProducts() }

                dbDeferred.await()
                cotdDeferred.await()
                upcomingDeferred.await()
                lastFetchTimestamp = LocalDateTime.now()
                _isFetchingData.value = false
            }
        }
    }

    private suspend fun fetchDBStatsData() {
        val res = ygoRepo.getDBStats()
        if (res.isSuccessful) {
            res.body()?.let {
                _dbStats.value = it
            }
        }
    }

    private suspend fun fetchCardOfTheDayData() {
        val res = ygoRepo.getCardOfTheDay()
        if (res.isSuccessful) {
            res.body()?.let {
                _cotd.value = it
            }
        }
    }

    private suspend fun fetchUpcomingYGOProducts() {
        val res = nextRepo.getEvents()
        if (res.isSuccessful) {
            res.body()?.let {
                _upcomingYGOProducts.value = it
            }
        }
    }
}