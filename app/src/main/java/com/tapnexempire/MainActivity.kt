package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tapnexempire.ui.theme.TapnexEmpireTheme
import com.tapnexempire.ui.navigation.AppNavHost
import com.tapnexempire.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapnexEmpireTheme {
                Surface {
                    val vm: MainViewModel = viewModel()
                    AppNavHost(viewModel = vm)
                }
            }
        }
    }
}
