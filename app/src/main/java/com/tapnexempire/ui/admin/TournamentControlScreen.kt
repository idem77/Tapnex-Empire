package com.tapnexempire.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import androidx.navigation.NavController
import com.tapnexempire.navigation.Routes
import com.tapnexempire.domain.TournamentEngine
import com.tapnexempire.viewmodel.AdminViewModel

@Composable
fun TournamentControlScreen(
    navController: NavController,
    vm: AdminViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val db = FirebaseFirestore.getInstance()
    var title by remember { mutableStateOf("") }
    var tournaments by remember { mutableStateOf(listOf<com.google.firebase.firestore.DocumentSnapshot>()) }
    var fee by remember { mutableStateOf("") }
    var prize by remember { mutableStateOf("") }
    var maxPlayers by remember { mutableStateOf("") }
    var durationMinutes by remember {
    mutableStateOf("30")
    }
   
     
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

      OutlinedTextField(

    value = durationMinutes,

    onValueChange = {
        durationMinutes = it
    },

    label = {
        Text("Duration Minutes")
    }
)
        
        Button(onClick = {
            vm.createTournament(
                title,
                fee.toLongOrNull() ?: 0,
                prize.toLongOrNull() ?: 0,
                maxPlayers.toLongOrNull() ?: 0,
                durationMinutes.toLongOrNull() ?: 30
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

                            Button(
    onClick = {

        vm.closeTournament(id)

        TournamentEngine.runTournament(id)
    }
) {
    Text("🏆 Close & Process")
                            }

                            Spacer(Modifier.width(6.dp))

                            Button(onClick = {
                                vm.deleteTournament(id)
                            }) {
                                Text("Delete")
                            }

                            Spacer(Modifier.width(6.dp))

                            Button(onClick = {

    navController.navigate(
        "${Routes.TOURNAMENT_PARTICIPANTS}/$id"
    )

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
