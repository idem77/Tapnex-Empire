package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.models.TaskModel
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.viewmodel.TaskViewModel

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel()
) {
    // ⚠️ Abhi repo sirf fetch kar raha hai, UI dummy list se chalega
    val tasks = remember {
        listOf(
            TaskModel("1", "Play 1 Match", "Complete one game", 50),
            TaskModel("2", "Daily Login", "Open app today", 20),
            TaskModel("3", "Invite Friend", "Invite 1 friend", 100)
        )
    }

    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tasks 📋",
            fontSize = 24.sp,
            color = CharcoalBlack
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
    }
}
