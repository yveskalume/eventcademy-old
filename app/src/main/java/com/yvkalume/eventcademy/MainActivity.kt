package com.yvkalume.eventcademy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.yvkalume.eventcademy.app.navigation.RootNavGraph
import com.yvkalume.eventcademy.ui.theme.EventCademyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventCademyTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    RootNavGraph(navController = navController)
                }
            }
        }
    }
}