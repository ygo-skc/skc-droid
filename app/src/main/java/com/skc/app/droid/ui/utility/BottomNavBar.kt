package com.skc.app.droid.ui.utility

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skc.app.droid.Route

private val bottomBarScreens = listOf(
    Route.HOME,
    Route.TRENDING
)

@Composable
fun BottomNavBar(backStack: SnapshotStateList<Any>) {
    NavigationBar(
        modifier = Modifier.height(72.dp),
    ) {
        bottomBarScreens.forEach { screen ->
            NavigationBarItem(
                selected = backStack.last() == screen.value,
                onClick = {
                    val existingIndex = backStack.indexOfFirst { it == screen.value }

                    if (existingIndex != -1) {
                        val entry = backStack.removeAt(existingIndex)
                        backStack.add(entry)
                    } else {
                        backStack.add(screen.value)
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.tabImage,
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