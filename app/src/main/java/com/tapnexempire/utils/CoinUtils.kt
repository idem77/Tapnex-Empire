package com.tapnexempire.utils

import com.tapnexempire.data.model.WalletModel

fun deductCoins(
    wallet: WalletModel,
    amount: Long
): WalletModel {

    var remaining = amount

    var bonus = wallet.bonusCoins
    var deposit = wallet.depositCoins
    var withdrawable = wallet.withdrawableCoins

    if (bonus >= remaining) {
        bonus -= remaining
        remaining = 0
    } else {
        remaining -= bonus
        bonus = 0
    }

    if (remaining > 0) {
        if (deposit >= remaining) {
            deposit -= remaining
            remaining = 0
        } else {
            remaining -= deposit
            deposit = 0
        }
    }

    if (remaining > 0) {
        if (withdrawable >= remaining) {
            withdrawable -= remaining
        } else {
            throw Exception("Not enough balance")
        }
    }

    return wallet.copy(
        bonusCoins = bonus,
        depositCoins = deposit,
        withdrawableCoins = withdrawable
    )
}
