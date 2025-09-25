package com.tapnexempire.ui.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.CardBackground
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.components.AppButton

data class Task(
    val title: String,
    val reward: Int,
    val isCompleted: Boolean
)

@Composable
fun TaskScreen(
    tasks: List<Task>,
    onTaskComplete: (Task) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Tasks", fontSize = 24.sp, color = CharcoalBlack)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = CardDefaults.shape,
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = task.title, fontSize = 18.sp, color = CharcoalBlack)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Reward: ${task.reward} Coins", fontSize = 16.sp, color = Gold)

                            AppButton(
                                text = if (task.isCompleted) "Completed" else "Complete",
                                onClick = { if (!task.isCompleted) onTaskComplete(task) },
                                enabled = !task.isCompleted
                            )
                        }
                    }
                }
            }
        }
    }
}
