package com.skc.app.droid.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.navigation.NavHostController
import com.skc.app.droid.viewmodel.HomeViewModel

@Composable
fun Home(
    model: HomeViewModel,
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    LaunchedEffect("Home View Model") {
        model.fetchData()
    }

    val isRefreshing by model.isRefreshing.collectAsState()
    val stats by model.dbStats.collectAsState()
    val cardOfTheDay by model.cotd.collectAsState()
    val upcomingYGOProducts by model.upcomingYGOProducts.collectAsState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            model.fetchData()
        },
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            DBStats(stats)
            CardOfTheDay(cotd = cardOfTheDay)
            UpcomingTCGProducts(upcomingYGOProducts)
        }
    }
}