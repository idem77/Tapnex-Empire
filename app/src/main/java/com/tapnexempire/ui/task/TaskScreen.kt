package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskScreen() {

    val dummyTasks = listOf(
        TaskUiModel("Play 1 Tournament", "0/1", 100, false),
        TaskUiModel("Play 3 Matches", "1/3", 300, false),
        TaskUiModel("Invite a Friend", "0/1", 1000, false),
        TaskUiModel("Watch Ad", "Completed", 70, true)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        DailyRewardCard(
            rewardCoins = 100,
            claimed = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Today's Tasks",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dummyTasks) { task ->
                TaskItem(task)
            }
        }
    }
}
