package com.tapnexempire.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import com.tapnexempire.components.AppButton
import com.tapnexempire.components.ProfileItem
import com.tapnexempire.ui.theme.CharcoalBlack

@Composable
fun ProfileScreen(
    userName: String = "Tapnex Player",
    onEditProfileClick: (() -> Unit)? = null,
    onSettingsClick: (() -> Unit)? = null,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 👤 User Info
        Text(
            text = "Hello, $userName",
            fontSize = 24.sp,
            color = CharcoalBlack
        )

        // ✏️ Edit Profile (Optional)
        onEditProfileClick?.let {
            AppButton(text = "Edit Profile", onClick = it)
        }

        // ⚙️ Settings (Optional)
        onSettingsClick?.let {
            ProfileItem(
                icon = Icons.Default.Settings,
                label = "Settings",
                onClick = it
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🚪 Logout Button
        AppButton(
            text = "Logout",
            onClick = onLogout
        )
    }
}
