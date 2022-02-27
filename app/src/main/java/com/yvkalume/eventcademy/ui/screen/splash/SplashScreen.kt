package com.yvkalume.eventcademy.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yvkalume.eventcademy.app.navigation.Screen
import com.yvkalume.eventcademy.ui.screen.splash.business.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, viewModel: SplashViewModel = hiltViewModel()) {

    val isAuth by viewModel.isAuth.collectAsState()

    LaunchedEffect(viewModel) {
        delay(1500)
        if (isAuth) {
            navController.navigate(Screen.MainNavHost.route)
        } else {
            navController.navigate(Screen.Login.route)
        }
    }

}