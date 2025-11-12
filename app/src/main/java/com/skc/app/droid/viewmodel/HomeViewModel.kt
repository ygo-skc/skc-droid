package com.skc.app.droid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skc.app.droid.model.CardOfTheDay
import com.skc.app.droid.model.DBStats
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.repository.NextRepository
import com.skc.app.droid.repository.NextRepositoryImp
import com.skc.app.droid.repository.YGORepository
import com.skc.app.droid.repository.YGORepositoryImp
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val ygoRepo: YGORepository = YGORepositoryImp(),
    private val nextRepo: NextRepository = NextRepositoryImp(),
) : ViewModel() {
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
            xxx()
            val dbDeferred = async { fetchDBStatsData() }
            val cotdDeferred = async { fetchCardOfTheDayData() }

            dbDeferred.await()
            cotdDeferred.await()
        }
    }

    private suspend fun fetchDBStatsData() {
        val res = ygoRepo.getDBStats()
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
        val res = ygoRepo.getCardOfTheDay()
        if (res.isSuccessful) {
            val data = res.body()
            if (data != null) {
                _cotd.value = data
            }
        }
    }

    private suspend fun xxx() {
        val res = nextRepo.getEvents()
        if (res.isSuccessful) {
            val data = res.body()
            Log.i("Temp", data.toString())
        }
    }
}