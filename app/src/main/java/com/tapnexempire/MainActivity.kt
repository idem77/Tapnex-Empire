package com.tapnexempire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.navigation.AppNavGraph
import com.tapnexempire.ui.theme.TapnexEmpireTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapnexEmpireTheme {
                val navController = rememberNavController()

                val authViewModel = AuthViewModel()
                val walletViewModel = WalletViewModel()
                val tournamentViewModel = TournamentViewModel()

                AppNavGraph(
                    navController = navController,
                    authViewModel = authViewModel,
                    walletViewModel = walletViewModel,
                    tournamentViewModel = tournamentViewModel
                )
            }
        }
    }
}
