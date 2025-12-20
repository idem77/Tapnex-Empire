package com.tapnexempire.ui.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val reward: Int,
    var isCompleted: Boolean = false
)

@Composable
fun TaskScreen(
    tasks: List<Task>,
    onClaim: (Task) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Daily Tasks",
            fontSize = 28.sp,
            color = Color(0xFF333333),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks) { task ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (task.isCompleted) Color(0xFFD9FFD9) else Color(0xFFFFE0E0)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = task.title, fontSize = 18.sp, color = Color(0xFF333333))
                            Text(text = task.description, fontSize = 14.sp, color = Color.Gray)
                        }
                        Button(
                            onClick = { if (!task.isCompleted) onClaim(task) },
                            enabled = !task.isCompleted,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (!task.isCompleted) Color(0xFFFFC1C1) else Color.Gray
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = if (task.isCompleted) "Claimed" else "Claim ${task.reward} coins",
                                color = Color(0xFF333333),
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
