package com.skc.app.droid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skc.app.droid.ui.home.Home

@Composable
fun CustomNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            Home(navController = navController, innerPadding = innerPadding)
        }
        composable(route = "Trending") {
            Column { }
        }
    }
}
