package com.skc.app.droid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skc.app.droid.ui.home.Home
import com.skc.app.droid.ui.utility.Route
import com.skc.app.droid.viewmodel.HomeViewModel

@Composable
fun CustomNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "Home",
    ) {
        composable(route = Route.HOME.value) {
            Home(
                model = homeViewModel,
                navController = navController,
                innerPadding = innerPadding
            )
        }
        composable(route = Route.TRENDING.value) {
            Column { }
        }
    }
}
