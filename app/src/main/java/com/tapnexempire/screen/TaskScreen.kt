package com.tapnexempire.screen.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme

data class TaskItem(
    val title: String,
    val reward: Int,
    val isCompleted: Boolean = false
)

@Composable
fun TasksScreen() {
    val tasks = listOf(
        TaskItem("Watch a video", 50),
        TaskItem("Daily login bonus", 100),
        TaskItem("Complete 1 game", 200),
        TaskItem("Invite a friend", 500),
        TaskItem("Complete 5 quizzes", 300)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Daily Tasks",
            color = NeonBlue,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(tasks) { task ->
                TaskCard(task)
            }
        }
    }
}

@Composable
fun TaskCard(task: TaskItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable { /* handle task click */ },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = task.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "+${task.reward} Coins",
                    color = NeonBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (task.isCompleted) {
                Text(
                    text = "Completed",
                    color = Color.Green,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Button(
                    onClick = { /* mark as complete */ },
                    colors = ButtonDefaults.buttonColors(containerColor = NeonBlue)
                ) {
                    Text("Start", color = Color.Black)
                }
            }
        }
    }
}
