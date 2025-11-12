package com.skc.app.droid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skc.app.droid.ui.home.CardOfTheDay
import com.skc.app.droid.ui.home.DBStats
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel: HomeViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            SKCTheme {
                LaunchedEffect("Home View Model") {
                    homeViewModel.fetchData()
                }

                val tabs = listOf(
                    Icons.Default.Home,
                    Icons.Filled.Whatshot
                )
                var selectedIndex by remember { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier.height(72.dp),
                        ) {
                            tabs.forEachIndexed { index, icon ->
                                NavigationBarItem(
                                    selected = selectedIndex == index,
                                    onClick = { selectedIndex = index },
                                    icon = {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = null
                                        )
                                    },
                                    label = null,
                                    alwaysShowLabel = false,
                                    colors = NavigationBarItemDefaults.colors(
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                    )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(40.dp)
                    ) {
                        val stats by homeViewModel.dbStats.collectAsState()
                        val cardOfTheDay by homeViewModel.cotd.collectAsState()

                        DBStats(stats)
                        CardOfTheDay(cotd = cardOfTheDay)
                    }
                }
            }
        }
    }
}