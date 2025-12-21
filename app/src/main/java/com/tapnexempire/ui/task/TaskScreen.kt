package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.models.TaskModel
import com.tapnexempire.viewmodel.TaskViewModel

@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel
) {
    // Get tasks from ViewModel
    val tasks = taskViewModel.taskState.collectAsState().value

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(tasks.size) { index ->
            val task = tasks[index]
            TaskCard(task = task, onClick = { 
                // Handle task click, reward claim
                taskViewModel.completeTask(task.id)
            })
        }
    }
}

@Composable
fun TaskCard(task: TaskModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(text = task.title, style = MaterialTheme.typography.bodyLarge)
            Button(onClick = onClick) {
                Text("Claim ${task.reward} Coins")
            }
        }
    }
}
