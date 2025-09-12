package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tapnexempire.utils.Constants

@Composable
fun TournamentDetailScreen(navController: NavController, tournamentId: String) {
    // TODO: fetch real tournament via id
    val fee = 100
    val players = 100
    val total = fee * players
    val afterTax = total - (total * Constants.TOURNAMENT_TAX_PERCENT / 100)
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tournament: $tournamentId", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        Text("Entry Fee: $fee coins")
        Text("Players: $players")
        Text("Prize Pool after ${Constants.TOURNAMENT_TAX_PERCENT}% tax: $afterTax coins")
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            // TODO: Join: deduct coins, create participant entry (backend)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Join Now (Pay $fee coins)")
        }
    }
}
