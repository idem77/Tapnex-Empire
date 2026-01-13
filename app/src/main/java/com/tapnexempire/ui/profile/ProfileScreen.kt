package com.tapnexempire.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.LightCream

@Composable
fun ProfileScreen(
    phoneNumber: String = "",
    onEditProfile: () -> Unit,
    onSettings: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "My Profile 👤",
            fontSize = 24.sp,
            color = CharcoalBlack
        )

        Spacer(modifier = Modifier.height(24.dp))

        ProfileItem(label = "Phone Number", value = phoneNumber.ifEmpty { "Not Available" })
        ProfileItem(label = "User Type", value = "Tapnex Player")
        ProfileItem(label = "Status", value = "Active")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onEditProfile,
            colors = ButtonDefaults.buttonColors(containerColor = Gold),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Profile", color = CharcoalBlack)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onSettings,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Settings")
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = onLogout
        ) {
            Text("Logout", color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
private fun ProfileItem(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(text = label, fontSize = 14.sp, color = CharcoalBlack)
        Text(text = value, fontSize = 16.sp, color = CharcoalBlack)
    }
}
