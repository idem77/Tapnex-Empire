package com.tapnexempire.ui.tournament.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.ui.theme.LightCream
import com.tapnexempire.ui.theme.CharcoalBlack

@Composable
fun TournamentDetailScreen(
    tournamentId: String
) {
    Box(
        modifier = Modifier.fillMaxSize().background(LightCream),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Tournament Detail Coming Soon 🏆", color = CharcoalBlack)
    }
}
