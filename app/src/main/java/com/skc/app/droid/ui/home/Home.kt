package com.skc.app.droid.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.skc.app.droid.Route
import com.skc.app.droid.viewmodel.HomeViewModel
import java.time.LocalDateTime

@Composable
fun Home(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val model: HomeViewModel = viewModel()
    LaunchedEffect("Home View Model") {
        model.fetchData(forceRefresh = false)
    }

    val isFetchingData by model.isFetchingData.collectAsState()
    val stats by model.dbStats.collectAsState()
    val cardOfTheDay by model.cotd.collectAsState()
    val upcomingYGOProducts by model.upcomingYGOProducts.collectAsState()

    PullToRefreshBox(
        isRefreshing = isFetchingData && model.lastFetchTimestamp != LocalDateTime.MIN,
        onRefresh = {
            model.fetchData(forceRefresh = true)
        },
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            DBStats(stats)

            CardOfTheDay(cotd = cardOfTheDay) {
                navController.navigate(
                    Route.YGO_CARD.value.replace(
                        oldValue = "{cardID}",
                        newValue = cardOfTheDay.card.cardID
                    )
                )
            }
            UpcomingTCGProducts(upcomingYGOProducts) { link ->
                if (link.startsWith(prefix = "/card")) {
                    navController.navigate(
                        Route.YGO_CARD.value.replace(
                            oldValue = "{cardID}",
                            newValue = link.replace(oldValue = "/card/", newValue = "")
                        )
                    )
                }
            }
        }
    }
}