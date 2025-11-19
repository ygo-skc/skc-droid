package com.skc.app.droid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
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
import com.skc.app.droid.util.SearchOverlay
import com.skc.app.droid.x.parent

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
            var showSearch by remember { mutableStateOf(false) }
            val isBottomBarVisible = currentRoute in listOf(
                Route.HOME.value,
                Route.TRENDING.value
            ) && !showSearch

            SKCTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        AnimatedVisibility(
                            visible = isBottomBarVisible,
                            enter = slideInVertically(initialOffsetY = { it + 300 }),
                            exit = slideOutVertically(targetOffsetY = { it + 300 })
                        ) {
                            FloatingActionButton(
                                onClick = { showSearch = true }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                            }
                        }
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isBottomBarVisible,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it })
                        ) {
                            BottomNavBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.HOME.value,
                        modifier = Modifier
                            .padding(top = innerPadding.calculateTopPadding())
                    ) {
                        composable(
                            route = Route.HOME.value,
                            enterTransition = { fadeIn(animationSpec = tween(200)) },
                            exitTransition = { fadeOut(animationSpec = tween(200)) },
                        ) {
                            Home(
                                navController = navController,
                                modifier = Modifier
                                    .parent()
                                    .padding(bottom = 72.dp)
                            )

                            if (showSearch) {
                                SearchOverlay(
                                    onDismiss = { showSearch = false },
                                    onCardSelected = { cardId ->
                                        navController.navigate(
                                            Route.YGO_CARD.value.replace("{cardID}", cardId)
                                        )
                                    },
                                    modifier = Modifier
                                        .parent()
                                )
                            }
                        }

                        composable(
                            route = Route.TRENDING.value,
                            enterTransition = { fadeIn(animationSpec = tween(200)) },
                            exitTransition = { fadeOut(animationSpec = tween(200)) },
                        ) {
                            Trending(
                                navController = navController,
                                modifier = Modifier
                                    .parent()
                                    .padding(bottom = 72.dp)
                            )
                        }

                        composable(
                            route = Route.YGO_CARD.value,
                            arguments = listOf(
                                navArgument("cardID") { type = NavType.StringType }
                            ),
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300)
                                )
                            }
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