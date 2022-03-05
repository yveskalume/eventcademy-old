package com.yvkalume.eventcademy.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yvkalume.eventcademy.ui.screen.agenda.AgendaScreen
import com.yvkalume.eventcademy.ui.screen.eventdetail.EventDetailScreen
import com.yvkalume.eventcademy.ui.screen.home.HomeScreen

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.surface ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                getBottomNavItems().forEach { screen ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = { navController.navigate(screen.route) },
                        selectedContentColor = MaterialTheme.colors.primary,
                        icon = {
                               Icon(imageVector = screen.icon as ImageVector, contentDescription = null)
                        },
                        label = {
                            Text(text = screen.label)
                        }
                    )
                }
            }
        },
        content = { contentPadding ->
            NavHost(modifier = Modifier.padding(contentPadding),navController = navController, startDestination = Screen.Home.route) {
                composable(route = Screen.Home.route) {
                    HomeScreen(navController = navController)
                }

                composable(route = "${Screen.EventDetails.route}/{uid}") {
                    EventDetailScreen(eventUid = it.arguments?.getString("uid").toString(), navController = navController)
                }
                composable(route = Screen.Agenda.route) {
                    AgendaScreen(navController = navController)
                }
            }
        }
    )
}