package com.tapnexempire.ui.admin

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.AdminDailyTaskViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AdminCreateTaskScreen(
    viewModel: AdminDailyTaskViewModel = hiltViewModel()
) {

    val isLoading by viewModel.isLoading.collectAsState()
    val message by viewModel.message.collectAsState()

    var animeName by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var question by remember { mutableStateOf("") }
    var correctAnswer by remember { mutableStateOf("") }
    var hint by remember { mutableStateOf("") }
    var youtubeLink by remember { mutableStateOf("") }
    var rewardCoins by remember { mutableStateOf("100") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Create Daily Anime Task",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = animeName,
            onValueChange = { animeName = it },
            label = { Text("Anime Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Task Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = question,
            onValueChange = { question = it },
            label = { Text("Question") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correctAnswer,
            onValueChange = { correctAnswer = it },
            label = { Text("Correct Answer") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = hint,
            onValueChange = { hint = it },
            label = { Text("Hint") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = youtubeLink,
            onValueChange = { youtubeLink = it },
            label = { Text("YouTube Link") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = rewardCoins,
            onValueChange = { rewardCoins = it },
            label = { Text("Reward Coins") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.createTask(
                    animeName = animeName,
                    title = title,
                    description = description,
                    question = question,
                    correctAnswer = correctAnswer,
                    hint = hint,
                    youtubeLink = youtubeLink,
                    rewardCoins = rewardCoins.toIntOrNull() ?: 0
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(
                if (isLoading) "Publishing..."
                else "Publish Task"
            )
        }

        if (message.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
