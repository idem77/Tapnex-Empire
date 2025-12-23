package com.tapnexempire.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.ui.theme.*
import com.tapnexempire.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit,
    onLogout: () -> Unit
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val userName by viewModel.userName.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = userName, fontSize = 24.sp, color = CharcoalBlack)

        Spacer(modifier = Modifier.height(32.dp))

        ProfileItem("Settings") { onSettingsClick() }
        ProfileItem("Logout") {
            viewModel.logout()
            onLogout()
        }
    }
}

@Composable
private fun ProfileItem(title: String, onClick: () -> Unit) {
    Text(
        text = title,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        color = CharcoalBlack
    )
}
