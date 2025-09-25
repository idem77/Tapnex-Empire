package com.tapnexempire.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.components.AppButton
import com.tapnexempire.components.ProfileItem
import com.tapnexempire.ui.theme.CharcoalBlack
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Person

@Composable
fun ProfileScreen(
    userName: String,
    onEditProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // User Info
        Text(text = "Hello, $userName", fontSize = 24.sp, color = CharcoalBlack)

        AppButton(
            text = "Edit Profile",
            onClick = onEditProfileClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Settings Item
        ProfileItem(
            icon = Icons.Default.Settings,
            label = "Settings",
            onClick = onSettingsClick
        )
    }
}
