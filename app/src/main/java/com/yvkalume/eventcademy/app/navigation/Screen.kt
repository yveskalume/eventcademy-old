package com.yvkalume.eventcademy.app.navigation

sealed class Screen(val route: String) {
    object MainNavHost : Screen("main-navhost")
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object EventDetails : Screen("event-details")
}