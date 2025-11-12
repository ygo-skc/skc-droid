package com.skc.app.droid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.skc.app.droid.ui.home.Home
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel: HomeViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            SKCTheme {
                Home(homeViewModel)
            }
        }
    }
}