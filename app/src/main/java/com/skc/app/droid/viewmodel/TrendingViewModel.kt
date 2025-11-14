package com.skc.app.droid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skc.app.droid.repository.YGORepository
import com.skc.app.droid.repository.YGORepositoryImp
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TrendingViewModel(
    private val ygoRepo: YGORepository = YGORepositoryImp(),
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(true)

    fun fetchData() {
        if (!_isRefreshing.value) {
            return
        }

        _isRefreshing.value = true
        viewModelScope.launch {
            val trendingCardsDeferred = async { fetchTrendingCards() }

            trendingCardsDeferred.await()
            _isRefreshing.value = false
        }
    }

    suspend fun fetchTrendingCards() {
        val res = ygoRepo.getTrends(resource = "CARD")
        if (res.isSuccessful) {
            res.body()?.let {
            }
        }
    }
}