@file:OptIn(ExperimentalMaterial3Api::class) // Queen’s magic for no warnings 😏💖

package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tapnexempire.ui.theme.TapnexEmpireTheme
import com.tapnexempire.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapnexEmpireTheme {
                // App Navigation Graph
                AppNavGraph()
            }
        }
    }
}
