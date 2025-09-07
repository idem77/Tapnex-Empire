package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.navigation.AppNavGraph
import com.tapnexempire.ui.theme.TapnexEmpireTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapnexEmpireTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)  // ✅ navController pass karo
            }
        }
    }
}
