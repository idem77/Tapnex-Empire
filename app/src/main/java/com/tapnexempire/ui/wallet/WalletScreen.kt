package com.tapnexempire.ui.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
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
