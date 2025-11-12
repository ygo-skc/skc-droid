package com.skc.app.droid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skc.app.droid.model.CardOfTheDay
import com.skc.app.droid.model.DBStats
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.repository.YGORepository
import com.skc.app.droid.repository.YGORepositoryImp
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: YGORepository = YGORepositoryImp()) : ViewModel() {
    private val _dbStats = MutableStateFlow(
        DBStats(productTotal = 0, cardTotal = 0, banListTotal = 0)
    )
    private val _cotd = MutableStateFlow(
        CardOfTheDay(
            date = "",
            version = 0,
            card = YGOCard.placeholder
        )
    )

    val dbStats get() = _dbStats.asStateFlow()
    val cotd get() = _cotd.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            val dbDeferred = async { fetchDBStatsData() }
            val cotdDeferred = async { fetchCardOfTheDayData() }

            dbDeferred.await()
            cotdDeferred.await()
        }
    }

    private suspend fun fetchDBStatsData() {
        val res = repository.getDBStats()
        if (res.isSuccessful) {
            val data = res.body()
            if (data != null) {
                _dbStats.value = data
            }
        } else {
            val error = res.errorBody()
            if (error != null) {
            }
        }
    }

    private suspend fun fetchCardOfTheDayData() {
        val res = repository.getCardOfTheDay()
        if (res.isSuccessful) {
            val data = res.body()
            if (data != null) {
                _cotd.value = data
            }
        }
    }
}