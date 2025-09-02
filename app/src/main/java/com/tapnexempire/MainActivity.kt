package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.tapnexempire.navigation.AppNavGraph
import com.tapnexempire.ui.Theme.TapnexEmpireTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            TapnexEmpireTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}
