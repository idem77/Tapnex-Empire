package com.tapnexempire.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.components.ProfileItem
import com.tapnexempire.ui.theme.CharcoalBlack
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Help

@Composable
fun SettingsScreen(
    notificationsEnabled: Boolean,
    onNotificationToggle: (Boolean) -> Unit,
    onHelpClick: () -> Unit
) {
    var notificationsState by remember { mutableStateOf(notificationsEnabled) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Settings", fontSize = 24.sp, color = CharcoalBlack)

        // Notification toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(text = "Notifications", fontSize = 16.sp, color = CharcoalBlack)
            Switch(
                checked = notificationsState,
                onCheckedChange = {
                    notificationsState = it
                    onNotificationToggle(it)
                }
            )
        }

        // Help / Support
        ProfileItem(
            icon = Icons.Default.Help,
            label = "Help & Support",
            onClick = onHelpClick
        )
    }
}
