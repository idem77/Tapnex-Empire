package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Data class for tasks
data class TaskItem(
    val id: String,
    val title: String,
    val reward: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(navController: NavController) {
    val tasks = listOf(
        TaskItem("ad1", "Watch Ad: 25 coins", 25),
        TaskItem("daily", "Daily Login: 10 coins", 10)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tasks") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp)
        ) {
            items(tasks) { t ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = {
                        // TODO: handle task click (like navigate or reward)
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = t.title, style = MaterialTheme.typography.bodyLarge)
                        Text(text = "+${t.reward} coins", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
