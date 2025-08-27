package com.tapnexempire.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun TopBar(
    title: String,
    canNavigateBack: Boolean,
    onBack: () -> Unit,
    actions: @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = { actions() }
    )
}
