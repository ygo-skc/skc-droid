package com.skc.app.droid.ui.misc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skc.app.droid.ui.card.YGOCardListItem
import com.skc.app.droid.viewmodel.TrendingViewModel

@Composable
fun Trending(innerPadding: PaddingValues) {
    val model: TrendingViewModel = viewModel()

    val isFetching by model.isFetching.collectAsState()

    LaunchedEffect("Trending View Model") {
        model.fetchData()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 15.dp)
            .padding(bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Trending",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )

        if (isFetching) {
            CircularProgressIndicator(
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            model.trendingCards.forEach { trendingMetric ->
                YGOCardListItem(card = trendingMetric.resource)
            }
        }
    }
}