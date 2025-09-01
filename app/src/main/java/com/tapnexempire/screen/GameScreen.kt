package com.tapnexempire.screens.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.R
import com.tapnexempire.ui.theme.NeonBlue

data class GameItem(val name: String, val icon: Int)

@Composable
fun GamesScreen() {
    val games = listOf(
        GameItem("Ludo", R.drawable.ic_ludo),
        GameItem("Quiz", R.drawable.ic_quiz),
        GameItem("Spin Wheel", R.drawable.ic_spin),
        GameItem("Memory", R.drawable.ic_memory),
        GameItem("Snake", R.drawable.ic_snake),
        GameItem("Cards", R.drawable.ic_cards)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Available Games",
            color = NeonBlue,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(games) { game ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clickable { /* Navigate to game */ },
                    shape = MaterialTheme.shapes.large,
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = game.icon),
                            contentDescription = game.name,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = game.name,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
