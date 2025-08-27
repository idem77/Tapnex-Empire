package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.components.PrimaryButton

@Composable
fun ProfileScreen(goToSettings: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Profile")
        PrimaryButton("Settings", onClick = goToSettings)
    }
}
