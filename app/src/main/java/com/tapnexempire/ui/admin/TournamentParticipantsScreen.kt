package com.tapnexempire.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.viewmodel.AdminViewModel

@Composable
fun TournamentParticipantsScreen(
    tournamentId: String,
    vm: AdminViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val db = FirebaseFirestore.getInstance()

    var players by remember { mutableStateOf(listOf<com.google.firebase.firestore.DocumentSnapshot>()) }

    LaunchedEffect(tournamentId) {
        db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .addSnapshotListener { snap, _ ->
                if (snap != null) players = snap.documents
            }
    }

    Column(Modifier.fillMaxSize().padding(12.dp)) {

        Text("👥 Participants",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(10.dp))

        LazyColumn {

            items(players) { p ->

                val userId = p.getString("userId") ?: ""
                val score = p.getLong("score") ?: 0L
                val rank = p.getLong("rank") ?: 0L
                val rewarded = p.getBoolean("rewarded") ?: false

                Card(Modifier.padding(8.dp)) {

                    Column(Modifier.padding(12.dp)) {

                        Text("👤 $userId")
                        Text("📊 Score: $score")
                        Text("🏅 Rank: $rank")

                        Text(if (rewarded) "✅ Rewarded" else "⏳ Pending")

                        Spacer(Modifier.height(8.dp))

                        Row {

                            Button(onClick = {
                                vm.updateScore(tournamentId, userId, score + 10)
                            }) {
                                Text("+Score")
                            }

                            Spacer(Modifier.width(6.dp))

                            Button(onClick = {
                                vm.setRank(tournamentId, userId, 1)
                            }) {
                                Text("Rank 1")
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        Button(
                            enabled = !rewarded,
                            onClick = {
                                vm.rewardUser(
                                    tournamentId,
                                    userId,
                                    1000
                                )
                            }
                        ) {
                            Text("Reward Player")
                        }
                    }
                }
            }
        }
    }
}
