package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.models.TaskModel
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold

@Composable
fun TaskItem(task: TaskModel) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = task.title,
                fontSize = 18.sp,
                color = CharcoalBlack
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = task.description,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "+${task.reward} coins",
                    fontSize = 14.sp,
                    color = CharcoalBlack
                )

                Button(
                    onClick = { /* later: completeTask */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Gold),
                    enabled = !task.isCompleted
                ) {
                    Text(
                        text = if (task.isCompleted) "Completed" else "Claim",
                        color = CharcoalBlack
                    )
                }
            }
        }
    }
}
