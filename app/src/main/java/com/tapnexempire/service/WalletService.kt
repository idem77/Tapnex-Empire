package com.tapnexempire.service

import com.tapnexempire.models.WalletModel
import kotlinx.coroutines.delay

class WalletService {

    private var wallet = WalletModel()

    suspend fun getWallet(): WalletModel {
        delay(200)
        return wallet
    }

    suspend fun updateWallet(updated: WalletModel) {
        delay(200)
        wallet = updated
    }
}
