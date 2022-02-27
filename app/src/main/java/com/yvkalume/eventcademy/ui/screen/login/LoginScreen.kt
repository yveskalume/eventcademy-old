package com.yvkalume.eventcademy.ui.screen.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.yvkalume.eventcademy.BuildConfig
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.app.App
import com.yvkalume.eventcademy.app.navigation.Screen
import com.yvkalume.eventcademy.ui.screen.login.business.LoginViewModel
import com.yvkalume.eventcademy.ui.screen.login.business.LoginViewState
import com.yvkalume.eventcademy.ui.screen.login.components.LoginButton
import com.yvkalume.eventcademy.ui.sharedcomponents.Toast

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = mavericksViewModel()
) {

    val context = LocalContext.current
    val token = BuildConfig.google_cloud_server_client_id

    val loginState by viewModel.collectAsState(LoginViewState::isSuccess)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                viewModel.signInWithGoogle(account.idToken!!)
                Log.d("Login", "Launched")
            } catch (e: ApiException) {
                Log.e("LoginScreen", "Login with google failed e : ${e.message}")
            }
        }
    )




    Box(modifier = Modifier.fillMaxSize()) {

        when (loginState) {
            is Uninitialized -> {

            }
            is Loading -> {
                LinearProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
                    .align(Alignment.TopCenter)
                )
            }
            is Success -> {
                Log.d("Login", "Success")
                App.restart(context)
            }
            is Fail -> {
                Toast(context = context, message = "${(loginState as Fail<Unit>).error}")
                Log.d("Login", "Fail")
            }
        }

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
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSignInClient.signInIntent)
                },

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
                    style = MaterialTheme.typography.button
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}