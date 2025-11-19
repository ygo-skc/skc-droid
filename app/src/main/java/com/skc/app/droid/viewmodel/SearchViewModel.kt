package com.skc.app.droid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.repository.YGORepository
import com.skc.app.droid.repository.YGORepositoryImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SearchViewModel(private val ygoRepo: YGORepository = YGORepositoryImp()) :
    ViewModel() {
    private val _subject = MutableStateFlow("")
    private val _results = MutableStateFlow(emptyList<YGOCard>())

    val subject get() = _subject.asStateFlow()
    val results get() = _results.asStateFlow()

    fun execute(newValue: String) {
        _subject.value = newValue
        viewModelScope.launch {
            val res = ygoRepo.searchCard(limit = 10, cardName = subject.value)
            res.body()?.let {
                _results.value = it
            }
        }
    }

    fun reset() {
        _subject.value = ""
        _results.value = emptyList()
    }
}