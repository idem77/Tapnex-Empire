package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.DailyTaskViewModel
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DailyTaskScreen(
    viewModel: DailyTaskViewModel =
        hiltViewModel(),

    onWatchVideo: (String) -> Unit
) {


    val tasks by
    viewModel.tasks.collectAsState()


    var answer by remember {
        mutableStateOf("")
    }


    var message by remember {
        mutableStateOf("")
    }



    LaunchedEffect(Unit){

        viewModel.loadTasks()

    }



    val task =
        tasks.firstOrNull()



    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement =
        Arrangement.spacedBy(16.dp)

    ){


        if(task != null){


            Text(
                text = task.animeName,
                style =
                MaterialTheme.typography.headlineSmall
            )


            Card(
                modifier =
                Modifier.fillMaxWidth()
            ){

                Text(
                    text = task.question,
                    modifier =
                    Modifier.padding(16.dp)
                )

            }



            OutlinedTextField(

                value = answer,

                onValueChange = {
                    answer = it
                },

                label = {
                    Text(
                        "Your Answer"
                    )
                },

                modifier =
                Modifier.fillMaxWidth()

            )



            Button(

                modifier =
                Modifier.fillMaxWidth(),

                onClick = {


                    val result =
                    viewModel.checkAnswer(
                        answer,
                        task.correctAnswer
                    )


                    message =
                    if(result)
                        "✅ Correct +${task.rewardCoins} Coins"
                    else
                        "❌ Try Again"

                }

            ){

                Text("Submit")

            }



            Text(
                text = message
            )



            TextButton(

                onClick = {

                    onWatchVideo(
                        task.youtubeLink
                    )

                }

            ){

                Text(
                    "▶ Watch Explanation"
                )

            }


        }else{

            Text(
                "No Daily Task Available"
            )

        }


    }

}
