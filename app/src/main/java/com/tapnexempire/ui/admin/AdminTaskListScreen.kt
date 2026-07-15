package com.tapnexempire.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.AdminDailyTaskViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AdminTaskListScreen(
    onEditClick: (String) -> Unit,
    viewModel: AdminDailyTaskViewModel = hiltViewModel()
) {

    val taskList by viewModel.taskList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(taskList) { task ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = task.animeName,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(task.title)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Button(
                            onClick = {
                                onEditClick(task.id)
                            }
                        ) {
                            Text("Edit")
                        }

                        OutlinedButton(
                            onClick = {
                                viewModel.deleteTask(task.id)
                            }
                        ) {
                            Text("Delete")
                        }

                    }
                }

            }

        }

    }

}
