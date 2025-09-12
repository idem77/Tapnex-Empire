package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Tx(val title: String, val amount: String, val date: String)

@Composable
fun TransactionHistoryScreen(navController: NavController) {
    val list = listOf(Tx("Tournament win", "+500", "10 Sep"), Tx("Withdraw", "-1000", "09 Sep"), Tx("Ad Reward", "+25", "09 Sep"))
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Transactions") }) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(12.dp)) {
            items(list) { tx ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                    Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column { Text(tx.title); Text(tx.date, style = MaterialTheme.typography.bodySmall) }
                        Text(tx.amount)
                    }
                }
            }
        }
    }
}
