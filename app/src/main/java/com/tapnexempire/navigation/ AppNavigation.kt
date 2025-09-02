package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tapnexempire.navigation.NavGraph
import com.tapnexempire.ui.theme.TapnexEmpireTheme

class App : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapnexEmpireTheme {
                // Yahan se pura navigation start hoga
                NavGraph()
            }
        }
    }
}
