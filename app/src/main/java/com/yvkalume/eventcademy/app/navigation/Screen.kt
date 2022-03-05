package com.yvkalume.eventcademy.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String = "", val icon: ImageVector? = null) {
    object MainNavHost : Screen(route = "main-navhost")
    object Splash : Screen(route = "splash")
    object Login : Screen(route = "login")
    object Home : Screen(route = "home", label = "Accueil", icon = Icons.Rounded.Home)
    object Agenda : Screen(route = "Agenda", label = "Agenda", icon = Icons.Rounded.DateRange)
    object EventDetails : Screen(route = "event-details")
}

fun Screen.withArgument(arg: String): String {
    return "${route}/$arg"
}

fun getBottomNavItems(): List<Screen> {
    return listOf(Screen.Home, Screen.Agenda)
}

