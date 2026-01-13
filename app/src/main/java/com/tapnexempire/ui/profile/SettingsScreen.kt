package com.tapnexempire.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.LightCream

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onPrivacyPolicy: () -> Unit,
    onTerms: () -> Unit,
    onSupport: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Settings ⚙️",
            fontSize = 24.sp,
            color = CharcoalBlack
        )

        Spacer(modifier = Modifier.height(24.dp))

        SettingsItem(
            title = "Privacy Policy",
            onClick = onPrivacyPolicy
        )

        SettingsItem(
            title = "Terms & Conditions",
            onClick = onTerms
        )

        SettingsItem(
            title = "Help & Support",
            onClick = onSupport
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}

@Composable
private fun SettingsItem(
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = LightCream)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp),
            color = CharcoalBlack
        )
    }
}
