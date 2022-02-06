package com.yvkalume.eventcademy.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yvkalume.eventcademy.ui.screen.login.LoginScreen
import com.yvkalume.eventcademy.ui.screen.splash.SplashScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.MainNavHost.route) {
            MainNavGraph(navController = navController)
        }
    }
}