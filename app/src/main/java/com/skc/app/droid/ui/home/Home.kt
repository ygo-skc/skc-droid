package com.skc.app.droid.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skc.app.droid.YGOCardKey
import com.skc.app.droid.viewmodel.HomeViewModel
import com.skc.app.droid.x.parent
import java.time.LocalDateTime

@Composable
fun Home(
    backStack: SnapshotStateList<Any>,
    state: ScrollState,
    paddingValues: PaddingValues
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
            modifier = Modifier
                .parent()
                .padding(top = paddingValues.calculateTopPadding(), bottom = 72.dp)
                .verticalScroll(state),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            DBStats(stats)

            CardOfTheDay(cotd = cardOfTheDay) {
                backStack.add(YGOCardKey(cardOfTheDay.card.cardID))
            }
            UpcomingTCGProducts(upcomingYGOProducts) { link ->
                if (link.startsWith(prefix = "/card")) {
                    backStack.add(
                        YGOCardKey(cardID = link.replace(oldValue = "/card/", newValue = ""))
                    )
                }
            }
        }
    }
}