package com.skc.app.droid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.skc.app.droid.ui.card.ygo.Card
import com.skc.app.droid.ui.home.Home
import com.skc.app.droid.ui.misc.Trending
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.ui.utility.BottomNavBar
import com.skc.app.droid.util.SearchOverlay
import com.skc.app.droid.viewmodel.SearchViewModel
import com.skc.app.droid.x.parent

data object HomeKey : NavKey
data object TrendingKey : NavKey
data class YGOCardKey(val cardID: String) : NavKey

enum class Route(val value: NavKey, val tabImage: ImageVector) {
    HOME(value = HomeKey, tabImage = Icons.Default.Home),
    TRENDING(value = TrendingKey, tabImage = Icons.Filled.Whatshot),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SKCTheme {
                var isSearchUIVisible by remember { mutableStateOf(false) }

                val backStack = remember { mutableStateListOf<Any>(HomeKey) }
                val isBottomBarVisible =
                    !isSearchUIVisible && listOf(HomeKey, TrendingKey).contains(backStack.last())

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    floatingActionButton = {
                        AnimatedVisibility(
                            visible = isBottomBarVisible,
                            enter = slideInVertically(initialOffsetY = { it + 250 }),
                            exit = slideOutVertically(targetOffsetY = { it + 250 })
                        ) {
                            FloatingActionButton(
                                onClick = { isSearchUIVisible = true }
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
                            BottomNavBar(backStack)
                        }
                    }
                ) { innerPadding ->
                    val searchModel: SearchViewModel = viewModel()
                    val searchListState = rememberLazyListState()

                    val search: @Composable () -> Unit = {
                        SearchOverlay(
                            model = searchModel,
                            onDismiss = { isSearchUIVisible = false },
                            onCardSelected = { cardID ->
                                backStack.add(YGOCardKey(cardID))
                            },
                            state = searchListState,
                            modifier = Modifier
                                .parent()
                        )
                    }

                    NavDisplay(
                        modifier = Modifier
                            .padding(top = innerPadding.calculateTopPadding()),
                        backStack = backStack,
                        onBack = {
                            if (isSearchUIVisible && listOf(
                                    HomeKey,
                                    TrendingKey
                                ).contains(backStack.last())
                            ) {
                                isSearchUIVisible = false
                            } else {
                                backStack.removeLastOrNull()
                            }
                        },
                        transitionSpec = {
                            ContentTransform(
                                fadeIn(tween(200)),
                                fadeOut(tween(200))
                            )
                        },
                        popTransitionSpec = {
                            ContentTransform(
                                fadeIn(tween(200)),
                                fadeOut(tween(200))
                            )
                        },
                        entryProvider = { key ->
                            when (key) {
                                is HomeKey -> NavEntry(key) {
                                    val homeScrollState = rememberScrollState()
                                    if (!isSearchUIVisible) {
                                        Home(
                                            backStack = backStack,
                                            state = homeScrollState,
                                            modifier = Modifier
                                                .parent()
                                                .padding(bottom = 72.dp)
                                        )
                                    } else {
                                        search()
                                    }
                                }

                                is TrendingKey -> NavEntry(key) {
                                    val trendingScrollState = rememberLazyListState()
                                    if (!isSearchUIVisible) {
                                        Trending(
                                            backStack = backStack,
                                            state = trendingScrollState,
                                            modifier = Modifier
                                                .parent()
                                                .padding(bottom = 72.dp)
                                        )
                                    } else {
                                        search()
                                    }
                                }

                                is YGOCardKey -> NavEntry(key) {
                                    Card(cardID = key.cardID)
                                }

                                else -> NavEntry(Unit) { Text(text = "Invalid Key: $it") }
                            }
                        }
                    )
                }
            }
        }
    }
}
