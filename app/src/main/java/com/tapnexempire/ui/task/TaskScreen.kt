package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class TaskItem(val id: String, val title: String, val coins: Int)

@Composable
fun TaskScreen(navController: androidx.navigation.NavController) {
    val tasks = listOf(TaskItem("ad1","Watch Ad: 25 coins",25), TaskItem("daily","Daily Login",10))
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Tasks") }) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(12.dp)) {
            items(tasks) { t ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                    Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(t.title)
                        Button(onClick = { /* TODO: show rewarded ad, then credit coins */ }) { Text("Do") }
                    }
                }
            }
        }
    }
}
