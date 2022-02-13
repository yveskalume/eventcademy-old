package com.yvkalume.eventcademy.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yvkalume.eventcademy.ui.screen.eventdetail.EventDetailScreen
import com.yvkalume.eventcademy.ui.screen.home.HomeScreen

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.EventDetails.route) {
            EventDetailScreen(navController = navController)
        }
    }
}