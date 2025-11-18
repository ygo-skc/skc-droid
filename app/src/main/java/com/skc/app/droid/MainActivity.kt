package com.skc.app.droid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skc.app.droid.ui.card.ygo.Card
import com.skc.app.droid.ui.home.Home
import com.skc.app.droid.ui.misc.Trending
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.ui.utility.BottomNavBar

enum class Route(val value: String, val tabImage: ImageVector) {
    HOME("home", Icons.Default.Home),
    TRENDING("trending", Icons.Filled.Whatshot),
    YGO_CARD("ygo/card/{cardID}", Icons.Filled.QuestionMark),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route

            SKCTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute in listOf(Route.HOME.value, Route.TRENDING.value)) {
                            BottomNavBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.HOME.value,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Route.HOME.value) {
                            Home(navController = navController)
                        }

                        composable(route = Route.TRENDING.value) {
                            Trending(navController = navController)
                        }

                        composable(
                            route = Route.YGO_CARD.value,
                            arguments = listOf(
                                navArgument("cardID") { type = NavType.StringType }
                            ),
                        ) { entry ->
                            Card(
                                cardID = entry.arguments?.getString("cardID") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}