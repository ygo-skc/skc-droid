package com.skc.app.droid.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skc.app.droid.ui.utility.DateBadge
import com.skc.app.droid.util.DatesUtil
import com.skc.app.droid.viewmodel.HomeViewModel

@Composable
fun Home(model: HomeViewModel, innerPadding: PaddingValues) {
    LaunchedEffect("Home View Model") {
        model.fetchData()
    }

    var isRefreshing by remember { mutableStateOf(false) }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            isRefreshing = false
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
            val stats by model.dbStats.collectAsState()
            val cardOfTheDay by model.cotd.collectAsState()
            val upcomingYGOProducts by model.upcomingYGOProducts.collectAsState()

            DBStats(stats)
            CardOfTheDay(cotd = cardOfTheDay)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                upcomingYGOProducts.events.forEach { event ->
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        DateBadge(
                            date = event.eventDate,
                            formatter = DatesUtil.HEART_API_DATE_FORMAT
                        )
                        Column {
                            Text(text = event.name, fontWeight = FontWeight.Bold)
                            Text(text = event.notes)
                        }
                    }
                }
            }
        }
    }
}