package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.navigation.AppNavGraph
import com.tapnexempire.navigation.BottomNavBar
import com.tapnexempire.navigation.Routes
import com.tapnexempire.ui.theme.TapnexEmpireTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TapnexEmpireTheme {

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        // Hide bottom bar on detail screen
                        if (currentRoute != "${Routes.TOURNAMENT_DETAIL}/{tournamentId}"
                            && currentRoute != Routes.WITHDRAW
                        ) {
                            BottomNavBar(navController)
                        }
                    }
                ) { innerPadding ->

                    AppNavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}
