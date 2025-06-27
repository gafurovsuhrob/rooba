package com.seros.rooba.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.seros.rooba.core.presentation.component.BottomNavigationBar
import com.seros.rooba.core.presentation.navigation.NavigationGraph
import com.seros.rooba.core.presentation.navigation.BottomNavItem

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Chat,
        BottomNavItem.Profile
    )

    val systemUiController = rememberSystemUiController()
    val topAppBarColor = MaterialTheme.colorScheme.background

    SideEffect {
        systemUiController.setStatusBarColor(
            color = topAppBarColor,
            darkIcons = true
        )
    }


    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
        contentWindowInsets = WindowInsets.systemBars,
        bottomBar = {
            BottomNavigationBar(navController = navController, items = bottomItems)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController = navController)
        }
    }
}
