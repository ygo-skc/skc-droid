package com.skc.app.droid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.skc.app.droid.ui.home.DBStats
import com.skc.app.droid.ui.theme.SKCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SKCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DBStats(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}