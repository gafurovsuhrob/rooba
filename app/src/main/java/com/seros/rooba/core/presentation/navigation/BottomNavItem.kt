package com.seros.rooba.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.seros.rooba.R


sealed class BottomNavItem(
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val title: String
) {
    data object Home : BottomNavItem(
        "home",
        R.drawable.home_filled,
        R.drawable.home,
        "Home"
    )
    data object Search : BottomNavItem(
        "search",
        R.drawable.search_filled,
        R.drawable.search_outlined,
        "Search"
    )
    data object Chat : BottomNavItem(
        "chat",
        R.drawable.messages_filled,
        R.drawable.messages_outliend,
        "Chat"
    )
    data object Profile : BottomNavItem(
        "profile",
        R.drawable.profile_filled,
        R.drawable.profile_outlined,
        "Profile"
    )
}
