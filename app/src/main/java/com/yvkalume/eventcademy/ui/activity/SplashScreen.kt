package com.yvkalume.eventcademy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.yvkalume.domain.entity.User
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.ActivitySplashScreenBinding
import com.yvkalume.eventcademy.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    private val viewModel by viewModels<SplashViewModel>()
    private val binding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    object IDs {
        const val Google_SignIn = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpSplash()
    }

    private fun setUpSplash() {
        lifecycleScope.launch {
            delay(3000)
            if (auth.currentUser == null) {
                binding.btnLogin.isVisible = true
            } else {
                val intent = Intent(baseContext,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        startActivityForResult(googleSignInClient.signInIntent, IDs.Google_SignIn)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == IDs.Google_SignIn) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(this.toString(), "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(this.toString(), "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(this.toString(), "signInWithCredential:success")
                    val authUser = auth.currentUser
                    if (authUser != null) {
                        val user = User(auth.uid!!,authUser.displayName!!,authUser.email!!,Timestamp.now().toDate())
                    }
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(this.toString(), "signInWithCredential:failure", task.exception)
//                    updateUI(null)
                }
            }
    }
}