package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Data class for tournament
data class TournamentItem(
    val id: String,
    val title: String,
    val entryFee: Int,
    val prize: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentScreen(navController: NavController) {
    val tournaments = listOf(
        TournamentItem("1", "Ludo Tournament", 10, 100),
        TournamentItem("2", "Quiz Tournament", 5, 50)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tournaments") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp)
        ) {
            items(tournaments) { t ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = {
                        // TODO: Navigate to tournament details if needed
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = t.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Entry Fee: ${t.entryFee} coins")
                        Text(text = "Prize: ${t.prize} coins")
                    }
                }
            }
        }
    }
}
