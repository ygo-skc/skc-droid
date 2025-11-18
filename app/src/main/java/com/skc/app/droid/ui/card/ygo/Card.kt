package com.skc.app.droid.ui.card.ygo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skc.app.droid.ui.card.ygo.components.Stats
import com.skc.app.droid.viewmodel.CardViewModel

@Composable
fun Card(cardID: String, innerPadding: PaddingValues) {
    val model: CardViewModel = viewModel(
        factory = CardViewModel.Factory,
        extras = MutableCreationExtras().apply {
            set(CardViewModel.KEY, cardID)
        })
    val isFetching by model.isFetching.collectAsState()

    LaunchedEffect("Card Model") {
        model.fetchData()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        if (isFetching) {
            CircularProgressIndicator(
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Stats(card = model.card)
        }
    }
}