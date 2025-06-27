package com.seros.rooba.core.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seros.rooba.feature.home.HomeScreen
import com.seros.rooba.feature.home.HomeViewModel
import com.seros.rooba.feature.user.UserScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController)
        }
        composable(BottomNavItem.Search.route) {
            Text("Search Screen")
        }
        composable(BottomNavItem.Chat.route) {
            Text("Chat Screen")
        }
        composable(BottomNavItem.Profile.route) {
            Text("Profile Screen")
        }

        composable("user/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            val viewModel: HomeViewModel = koinViewModel()

            LaunchedEffect(userId) {
                userId?.let { viewModel.setSelectedUserById(it) }
            }

            val user = viewModel.selectedUser
            if (user != null) {
                UserScreen(navController, user)
            } else {
                Text("Загрузка профиля...")
            }
        }

    }
}
