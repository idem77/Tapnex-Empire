package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.components.GameTile

@Composable
fun GameScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        GameTile(title = "Ludo") { /* TODO Phase-2 */ }
        GameTile(title = "Quiz") { /* TODO Phase-2 */ }
        GameTile(title = "Spin & Win") { /* TODO Phase-2 */ }
    }
}
