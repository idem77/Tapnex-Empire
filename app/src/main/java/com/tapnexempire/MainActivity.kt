package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tapnexempire.ui.theme.TapnexEmpireTheme
import com.tapnexempire.screen.GameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapnexEmpireTheme {
                GameScreen() // 👈 default screen (later we add navigation)
            }
        }
    }
}
