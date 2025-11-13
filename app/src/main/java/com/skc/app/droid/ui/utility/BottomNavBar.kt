package com.skc.app.droid.ui.utility

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

private val tabs = listOf(
    Icons.Default.Home,
    Icons.Filled.Whatshot
)
private val screens = listOf(
    Route.HOME,
    Route.TRENDING
)

enum class Route(val value: String) {
    HOME("home"),
    TRENDING("trending"),
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val selectedIndex = remember { mutableIntStateOf(0) }

    NavigationBar(
        modifier = Modifier.height(72.dp),
    ) {
        tabs.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = selectedIndex.value == index,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(route = screens[index].value)
                },
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
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        }
    }
}