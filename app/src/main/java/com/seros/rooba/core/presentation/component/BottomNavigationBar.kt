package com.seros.rooba.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.seros.rooba.core.presentation.navigation.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavItem>
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEachIndexed { index, item ->

            val selected = when {
                currentRoute?.startsWith("user/") == true && item.route == BottomNavItem.Home.route -> true
                else -> currentRoute == item.route
            }

            val modifier = when (index) {
                0 -> Modifier.padding(start = 24.dp)
                items.lastIndex -> Modifier.padding(end = 24.dp)
                else -> Modifier
            }

            NavigationBarItem(
                modifier = modifier,
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(if (selected) item.selectedIcon else item.unselectedIcon),

                            contentDescription = item.title,
                            tint = if (selected) MaterialTheme.colorScheme.primary else Color.LightGray
                        )
                        if (selected) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}