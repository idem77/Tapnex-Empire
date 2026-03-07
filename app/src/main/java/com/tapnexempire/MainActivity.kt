package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dagger.hilt.android.AndroidEntryPoint
import com.tapnexempire.navigation.AppNavGraph
import com.tapnexempire.navigation.BottomNavBar
import com.tapnexempire.ui.theme.TapnexEmpireTheme

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

                        if (
                            currentRoute != Routes.WITHDRAW &&
                            !currentRoute.orEmpty().startsWith(Routes.TOURNAMENT_DETAIL)
                        ) {
                            BottomNavBar(navController)
                        }

                    }

                ) { innerPadding ->

                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )

                }

            }

        }
    }
}
