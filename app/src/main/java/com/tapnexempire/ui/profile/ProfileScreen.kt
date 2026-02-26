package com.tapnexempire.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tapnexempire.R

@Composable
fun ProfileScreen(
    phoneNumber: String = "",
    onEditProfile: () -> Unit,
    onSettings: () -> Unit,
    onLogout: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 🔥 BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.profile_bg), // make sure this exists
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 70.dp) // bottom nav safe space
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "My Profile 👤",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileCard(
                phoneNumber = phoneNumber
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onEditProfile,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit Profile")
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
                Text(
                    text = "Logout",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun ProfileCard(
    phoneNumber: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "User Information",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Divider()

            ProfileItem("Phone Number", phoneNumber.ifEmpty { "Not Available" })
            ProfileItem("User Type", "Tapnex Player")
            ProfileItem("Status", "Active")
        }
    }
}

@Composable
private fun ProfileItem(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
