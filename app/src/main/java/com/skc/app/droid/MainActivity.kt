package com.skc.app.droid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
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