package com.tapnexempire.ui.auth

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.tapnexempire.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.tapnexempire.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavHostController) {

    val context = LocalContext.current
    val viewModel: AuthViewModel = hiltViewModel()

    val loginState by viewModel.loginState.collectAsState()

    val googleSignInClient = remember {
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)

            val token = account.idToken

            if (token == null) {
                Toast.makeText(context, "❌ TOKEN NULL", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "✅ TOKEN OK", Toast.LENGTH_SHORT).show()

                viewModel.loginWithGoogle(token)
            }

        } catch (e: Exception) {
            Toast.makeText(context, "❌ LOGIN FAILED: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(loginState) {
        if (loginState) {
            Toast.makeText(context, "🚀 LOGIN SUCCESS", Toast.LENGTH_SHORT).show()

            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Button(
        onClick = {

            // 🔥 Reset Google session (important)
            googleSignInClient.signOut().addOnCompleteListener {
                launcher.launch(googleSignInClient.signInIntent)
            }
        }
    ) {
        Text("Continue with Google 🚀")
    }
}
