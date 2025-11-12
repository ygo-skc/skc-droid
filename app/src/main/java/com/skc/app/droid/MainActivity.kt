package com.skc.app.droid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.skc.app.droid.ui.home.Home
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.util.BottomNavBar
import com.skc.app.droid.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel: HomeViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            SKCTheme {
                val selectedIndex = remember { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = { BottomNavBar(selectedIndex = selectedIndex) }
                ) { innerPadding ->
                    when (selectedIndex.intValue) {
                        1 -> Column { }
                        else -> Home(model = homeViewModel, innerPadding = innerPadding)
                    }
                }
            }
        }
    }
}