package com.skc.app.droid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skc.app.droid.model.TrendingMetric
import com.skc.app.droid.repository.YGORepository
import com.skc.app.droid.repository.YGORepositoryImp
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class TrendingViewModel(
    private val ygoRepo: YGORepository = YGORepositoryImp(),
) : ViewModel() {
    private val _isFetching = MutableStateFlow(true)

    var trendingCards = emptyList<TrendingMetric>()

    val isFetching get() = _isFetching.asStateFlow()

    fun fetchData() {
        if (!_isFetching.value) {
            return
        }

        _isFetching.value = true
        viewModelScope.launch {
            val trendingCardsDeferred = async { fetchTrendingCards() }

            trendingCardsDeferred.await()
            _isFetching.value = false
        }
    }

    suspend fun fetchTrendingCards() {
        val res = ygoRepo.getTrends(resource = "CARD")
        if (res.isSuccessful) {
            res.body()?.let {
                trendingCards = it.metrics
            }
        }
    }
}