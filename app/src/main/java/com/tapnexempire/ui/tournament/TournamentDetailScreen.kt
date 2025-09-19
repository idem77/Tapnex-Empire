package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentDetailScreen(
    navController: NavController,
    tournamentId: String
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tournament Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Tournament ID: $tournamentId",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text("Details about the tournament will appear here.")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                // Yaha tum join logic ya payment logic laga sakte ho
            }) {
                Text("Join Tournament")
            }
        }
    }
}
