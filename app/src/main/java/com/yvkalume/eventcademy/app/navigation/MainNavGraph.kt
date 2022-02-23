package com.yvkalume.eventcademy.app.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            BottomNavigation {
                getBottomNavItems().forEach {
                    BottomNavigationItem(
                        selected = navController.currentDestination?.route == it.route,
                        onClick = { navController.navigate(it.route) },
                        icon = {
                               Icon(imageVector = it.icon as ImageVector, contentDescription = null)
                        },
                        label = {
                            Text(text = it.label)
                        }
                    )
                }
            }
        },
        content = {
            NavHost(navController = navController, startDestination = Screen.Home.route) {
                composable(route = Screen.Home.route) {
                    HomeScreen(navController = navController)
                }

                composable(route = Screen.EventDetails.route) {
                    EventDetailScreen(navController = navController)
                }
                composable(route = Screen.Agenda.route) {
                    AgendaScreen(navController = navController)
                }
            }
        }
    )
}