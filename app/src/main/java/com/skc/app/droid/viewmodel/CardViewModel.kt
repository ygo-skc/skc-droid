package com.skc.app.droid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.repository.YGORepository
import com.skc.app.droid.repository.YGORepositoryImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardViewModel(
    private val cardID: String,
    private val ygoRepo: YGORepository = YGORepositoryImp(),
) : ViewModel() {
    private val _isFetching = MutableStateFlow(true)

    var card = YGOCard.placeholder

    val isFetching get() = _isFetching.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            val res = ygoRepo.getCardInfo(cardID, allInfo = true)
            res.body()?.let {
                Log.i("Card", it.toString())
                card = it
                _isFetching.value = false
            }
        }
    }

    companion object {
        @JvmStatic
        val KEY = object : CreationExtras.Key<String> {}

        @JvmStatic
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val cardID = this[KEY] as String
                CardViewModel(cardID)
            }
        }
    }
}