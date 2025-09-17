package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tapnexempire.navigation.Screen
import com.tapnexempire.utils.Constants

data class TournamentItem(val id: String, val title: String, val fee: Int, val players: Int)

@Composable
fun TournamentListScreen(navController: NavController) {
    val items = listOf(
        Tournament("b1","Bronze Arena",100,100),
        Tournament("s1","Silver Arena",250,100),
        Tournament("g1","Gold Arena",500,50)
    )
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Tournaments") }) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(12.dp)) {
            items(items) { t ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(t.title, style = MaterialTheme.typography.titleMedium)
                        Text("Entry: ${t.fee} coins • Players: ${t.players}")
                        Spacer(Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                            TextButton(onClick = { navController.navigate(Screen.TournamentDetail.createRoute(t.id)) }) { Text("View") }
                        }
                    }
                }
            }
        }
    }
}
