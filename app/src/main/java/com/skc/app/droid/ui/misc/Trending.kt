package com.skc.app.droid.ui.misc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingFlat
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.skc.app.droid.Route
import com.skc.app.droid.ui.card.ygo.YGOCardListItem
import com.skc.app.droid.viewmodel.TrendingViewModel

@Composable
fun Trending(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
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
            model.trendingCards.forEachIndexed { ind, trendingMetric ->
                YGOCardListItem(card = trendingMetric.resource, onClick = {
                    navController.navigate(
                        Route.YGO_CARD.value.replace(
                            "{cardID}",
                            trendingMetric.resource.cardID
                        )
                    )
                }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TrendingSummary(
                            change = trendingMetric.change,
                            occurrences = trendingMetric.occurrences
                        )
                        Text(
                            text = "#${ind + 1}",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrendingSummary(change: Int, occurrences: Int) {
    val changeIcon = when {
        change > 0 -> Icons.AutoMirrored.Filled.TrendingUp
        change == 0 -> Icons.AutoMirrored.Filled.TrendingFlat
        else -> Icons.AutoMirrored.Filled.TrendingDown
    }
    val changeIconDescription = when {
        change > 0 -> "Trending up"
        change == 0 -> "Trending flat"
        else -> "Trending down"
    }
    val changeColor = when {
        change > 0 -> Color(0xFF43a047)
        change == 0 -> Color(0xFFfdd835)
        else -> Color(0xFFff1744)
    }

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                changeIcon, changeIconDescription,
                modifier = Modifier.size(18.dp),
                tint = changeColor,
            )
            Text(
                text = change.toString(),
                style = MaterialTheme.typography.labelLarge,
                color = changeColor
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.BarChart, "Bar chart",
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = occurrences.toString(),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}