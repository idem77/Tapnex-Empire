package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapnexEmpireApp()
        }
    }
}

@Composable
fun TapnexEmpireApp() {
    MaterialTheme {
        Text(text = "Welcome to Tapnex Empire 👑🚀")
    }
}
