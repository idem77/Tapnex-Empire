package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tapnexempire.navigation.AppNavGraph
import com.tapnexempire.ui.theme.TapnexEmpireTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapnexEmpireTheme {
                AppNavGraph() // ✅ no navController param
            }
        }
    }
}
