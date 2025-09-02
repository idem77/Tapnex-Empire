package com.tapnexempire.screens.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun GameScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Games Coming Soon...")
        Image(painter = painterResource(id = android.R.drawable.ic_menu_gallery), contentDescription = "icon")
        Image(painter = painterResource(id = android.R.drawable.ic_menu_gallery), contentDescription = "icon")
    }
}
