package com.tapnexempire.ui.auth

import android.app.Activity
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(navController: NavHostController) {

    val context = LocalContext.current

    // 👑 Google Sign-In Client
    val googleSignInClient = remember {
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("PASTE_YOUR_CLIENT_ID_HERE")
                .requestEmail()
                .build()
        )
    }

    // 👑 Launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)

            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            FirebaseAuth.getInstance()
                .signInWithCredential(credential)
                .addOnSuccessListener {

                    val uid = FirebaseAuth.getInstance().currentUser?.uid
                    println("✅ LOGIN SUCCESS UID 👉 $uid")

                    navController.navigate("home") {
                        popUpTo("otp_login") { inclusive = true }
                    }
                }

        } catch (e: Exception) {
            println("❌ LOGIN FAILED 👉 ${e.message}")
        }
    }

    // 👑 UI
    Button(
        onClick = {
            launcher.launch(googleSignInClient.signInIntent)
        }
    ) {
        Text("Continue with Google 🚀")
    }
}
