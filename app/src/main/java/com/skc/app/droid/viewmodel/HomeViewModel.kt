package com.skc.app.droid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skc.app.droid.model.CardOfTheDay
import com.skc.app.droid.model.DBStats
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.repository.YGORepository
import com.skc.app.droid.repository.YGORepositoryImp
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
            val res2 = repository.getCardOfTheDay()
            if (res2.isSuccessful) {
                val body = res2.body()
                if (body != null) {
                    _cotd.value = body
                }
            }

            val response = repository.getDBStats()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    _dbStats.value = body
                }
            } else {
                val error = response.errorBody()
                if (error != null) {
                }
            }
        }
    }
}