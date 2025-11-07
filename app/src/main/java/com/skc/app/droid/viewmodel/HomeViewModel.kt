package com.skc.app.droid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skc.app.droid.model.DBStats
import com.skc.app.droid.repository.SKCRepository
import com.skc.app.droid.repository.SKCRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SKCRepositoryInterface = SKCRepository()) :
    ViewModel() {
    private val _dbStats = MutableStateFlow(
        DBStats(productTotal = 0, cardTotal = 0, banListTotal = 0, yearsOfBanListCoverage = 0)
    )

    val dbStats: StateFlow<DBStats> get() = _dbStats.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
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