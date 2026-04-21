package com.tapnexempire.ui.auth

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
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
                .requestIdToken("564500505897-68ck0ml5ohc0muahct9oqccf7ilu0fiu.apps.googleusercontent.com")
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

            println("🔥 TOKEN 👉 ${account.idToken}")

            viewModel.loginWithGoogle(account.idToken!!)

        } catch (e: Exception) {
            println("❌ LOGIN FAILED 👉 ${e.message}")
        }
    }

    LaunchedEffect(loginState) {
        if (loginState) {
            println("🚀 NAVIGATING HOME")
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Button(
        onClick = {
            println("🔥 BUTTON CLICKED")
            launcher.launch(googleSignInClient.signInIntent)
        }
    ) {
        Text("Continue with Google 🚀")
    }
}
