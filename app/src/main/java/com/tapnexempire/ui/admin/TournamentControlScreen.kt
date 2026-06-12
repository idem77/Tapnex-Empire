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
fun TournamentControlScreen(
    vm: AdminViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val db = FirebaseFirestore.getInstance()

    var tournaments by remember { mutableStateOf(listOf<com.google.firebase.firestore.DocumentSnapshot>()) }

    var title by remember { mutableStateOf("") }
    var fee by remember { mutableStateOf("") }
    var prize by remember { mutableStateOf("") }
    var maxPlayers by remember { mutableStateOf("") }
   
     
    // 🔥 LIVE TOURNAMENT STREAM
    LaunchedEffect(true) {
        db.collection("tournaments")
            .addSnapshotListener { snap, _ ->
                if (snap != null) tournaments = snap.documents
            }
    }

    Column(Modifier.fillMaxSize().padding(12.dp)) {

        Text("🏆 Tournament Control Panel",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )

        OutlinedTextField(
            value = fee,
            onValueChange = { fee = it },
            label = { Text("Entry Fee") }
        )

        OutlinedTextField(
            value = prize,
            onValueChange = { prize = it },
            label = { Text("Prize Pool") }
        )

        OutlinedTextField(
    value = maxPlayers,
    onValueChange = { maxPlayers = it },
    label = { Text("Max Players") }
)

        Button(onClick = {
            vm.createTournament(
                title,
                fee.toLongOrNull() ?: 0,
                prize.toLongOrNull() ?: 0
            )
        }) {
            Text("Create Tournament")
        }

        Spacer(Modifier.height(20.dp))

        LazyColumn {

            items(tournaments) { t ->

                val id = t.id
                val title = t.getString("title") ?: ""
                val status = t.getString("status") ?: ""

                Card(Modifier.padding(8.dp)) {

                    Column(Modifier.padding(12.dp)) {

                        Text("🏆 $title")
                        Text("🔒 Status: $status")

                        Row {

                            Button(onClick = {
                                vm.closeTournament(id)
                            }) {
                                Text("Close")
                            }

                            Spacer(Modifier.width(6.dp))

                            Button(onClick = {
                                vm.deleteTournament(id)
                            }) {
                                Text("Delete")
                            }

                            Spacer(Modifier.width(6.dp))

                            Button(onClick = {
                                // open participants screen navigation
                            }) {
                                Text("Players")
                            }
                        }
                    }
                }
            }
        }
    }
}
