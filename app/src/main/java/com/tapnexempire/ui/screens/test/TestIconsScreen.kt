package com.tapnexempire.ui.screens.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.tapnexempire.R

@Composable
fun TestIconsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        IconItem("Coin", R.drawable.ic_coin)
        IconItem("Wallet", R.drawable.ic_wallet)
        IconItem("Gift", R.drawable.ic_gift)
        IconItem("Shop", R.drawable.ic_shop)
        IconItem("Deposit", R.drawable.ic_deposit)
        IconItem("Crown", R.drawable.ic_crown)
    }
}

@Composable
fun IconItem(name: String, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name, style = MaterialTheme.typography.bodyLarge)
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}
