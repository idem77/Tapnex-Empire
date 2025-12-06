package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.ui.theme.TapnexEmpireTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TapnexEmpireTheme {
                Surface(color = Color.White) {
                    // ⬇️ TEMPORARY TEST SCREEN QUEEN KA ORDER
                    TestIconsScreen()
                    // ⬆️ Yahi run karega, nav graph abhi off
                }
            }
        }
    }
}
