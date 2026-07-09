package com.tapnexempire.ui.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.R
import com.tapnexempire.data.model.WalletModel
import com.tapnexempire.navigation.Routes
import com.tapnexempire.ui.components.EmpireWalletCard
import com.tapnexempire.ui.theme.EmpireGold
import com.tapnexempire.ui.theme.EmpireWhite
import com.tapnexempire.utils.UiState
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.components.AppButton

@Composable
fun WalletScreen(

    userId: String,

    navController: NavController,

    onTransactionClick: () -> Unit,

    viewModel: WalletViewModel = hiltViewModel()
) {

    if (userId.isEmpty()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "User not logged in ❌",
                color = EmpireWhite
            )
        }

        return
    }

    val state by viewModel.walletState.collectAsState()

    LaunchedEffect(userId) {

        viewModel.startWalletListener(userId)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 👑 BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.wallet_bg),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        when (state) {

            is UiState.Loading -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator(
                        color = EmpireGold
                    )
                }
            }

            is UiState.Error -> {

                val message =
                    (state as UiState.Error).message

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "❌ $message",
                        color = EmpireWhite
                    )
                }
            }

            is UiState.Success -> {

                val wallet =
                    (state as UiState.Success<WalletModel>).data

                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 18.dp)
                        .padding(top = 50.dp)
                        .verticalScroll(rememberScrollState()),

                    verticalArrangement =
                        Arrangement.spacedBy(20.dp)
                ) {

                    // 👑 TITLE
                    Text(

                        text = "👑 Empire Wallet",

                        style =
                            MaterialTheme.typography.headlineLarge,

                        color = EmpireWhite
                    )

                    // 💰 WALLET CARD
                    EmpireWalletCard(

                        depositCoins =
                            wallet.depositCoins,

                        withdrawableCoins =
                            wallet.withdrawableCoins,

                        bonusCoins =
                            wallet.bonusCoins
                    )

                    Card(

    modifier = Modifier.fillMaxWidth(),

    shape = RoundedCornerShape(24.dp),

    colors = CardDefaults.cardColors(
        containerColor = Color(0xCC173650)
    ),

    border = BorderStroke(
        1.dp,
        Color(0x3362D9FF)
    ),

    elevation = CardDefaults.cardElevation(
        defaultElevation = 10.dp
    )

) {

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),

        verticalArrangement = Arrangement.spacedBy(14.dp)

    ) {

        Text(

            text = "👑 Empire Control Center",

            color = Color(0xFF62D9FF),

            style = MaterialTheme.typography.titleLarge

        )

        Text(

            text = "Every reward, withdrawal and redeem request is securely managed by the Empire. Our systems continuously verify and process every request to ensure a safe and trusted experience.",

            color = Color(0xFFD9F4FF),

            style = MaterialTheme.typography.bodyMedium

        )

        HorizontalDivider(
            color = Color.White.copy(alpha = 0.10f)
        )

        Column {

            Text(

                text = "💸 Withdraw Requests",

                color = Color.White,

                style = MaterialTheme.typography.titleSmall

            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(

                text = "Estimated Processing Time • 24–48 Hours",

                color = Color(0xFFD9F4FF)

            )

        }

        Column {

            Text(

                text = "🎁 Reward & Redeem Delivery",

                color = Color.White,

                style = MaterialTheme.typography.titleSmall

            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(

                text = "Estimated Delivery Time • 12–24 Hours",

                color = Color(0xFFD9F4FF)

            )

        }

        HorizontalDivider(
            color = Color.White.copy(alpha = 0.10f)
        )

        Text(

            text = "🛡️ Empire Security protects every transaction. Once approved, your rewards, redeem codes and withdrawals will be delivered automatically to your Empire account.",

            color = Color(0xFF9FE7FF),

            style = MaterialTheme.typography.bodyMedium

        )

        HorizontalDivider(
    color = Color.White.copy(alpha = 0.10f)
)

Spacer(modifier = Modifier.height(8.dp))

Text(
    text = "⚡ Processing times may vary during special events or high traffic periods. This helps the Empire maintain secure, fair and reliable service for every member.",
    color = Color(0xFF8EDFFF),
    style = MaterialTheme.typography.bodySmall
)

    }

                    }
                    // 💰 DEPOSIT BUTTON
                    AppButton(

                        text = "💰 Deposit Coins",

                        onClick = {

                            navController.navigate(
                                Routes.DEPOSIT
                            )
                        }
                    )

                    // 💸 WITHDRAW BUTTON
                    AppButton(

                        text = "💸 Withdraw Coins",

                        onClick = {

                            navController.navigate(
                                Routes.WITHDRAW
                            )
                        }
                    )

                   //🎁My withdraw screen 
                    AppButton(

    text = "🎁 My Rewards",

    onClick = {

        navController.navigate(
            Routes.MY_WITHDRAWS
        )

    }

)

                    // 📜 TRANSACTION BUTTON
                    AppButton(

                        text = "📜 Transaction History",

                        onClick = onTransactionClick
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}
