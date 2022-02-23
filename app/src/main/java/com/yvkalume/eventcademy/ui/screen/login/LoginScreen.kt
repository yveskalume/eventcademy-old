package com.yvkalume.eventcademy.ui.screen.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.app.navigation.Screen
import com.yvkalume.eventcademy.ui.screen.login.business.LoginViewModel
import com.yvkalume.eventcademy.ui.screen.login.components.LoginButton

@Composable
fun LoginScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x22FFFFFF),
                        Color(0xE40C0C0C)
                    ),
                    startY = 100.0f,
                    endY = 2100f
                )
            ), content = {})

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "EventCademy",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = White
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Decouvrez et participez aux meilleurs évènements academiques et meetups",
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    color = White
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            LoginButton(
                onClick = { navController.navigate(Screen.MainNavHost.route) },
                borderStroke = BorderStroke(1.dp, color = White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Se connecter avec Google",
                    color = Black,
                    style = MaterialTheme.typography.button
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}