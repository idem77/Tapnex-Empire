package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskItem(task: TaskUiModel) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(text = task.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = task.progress, style = MaterialTheme.typography.bodySmall)
            }

            Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {
                Text(text = "+${task.reward} coins")

                Spacer(modifier = Modifier.height(6.dp))

                Button(
                    onClick = { },
                    enabled = !task.completed
                ) {
                    Text(if (task.completed) "Claimed" else "Claim")
                }
            }
        }
    }
}
