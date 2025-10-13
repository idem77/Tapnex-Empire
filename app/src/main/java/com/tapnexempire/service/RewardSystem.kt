package com.tapnexempire.service

import com.tapnexempire.models.RewardType

object RewardSystem {

    // 🎁 Reward amounts (you can edit anytime)
    private const val DAILY_LOGIN_REWARD = 500.0      // 500 coins for daily login
    private const val REFERRAL_REWARD = 1000.0        // 1000 coins for each referral
    private const val TASK_REWARD = 200.0             // 200 coins per completed task

    // 📅 Give Daily Reward
    fun giveDailyLoginReward(userId: String) {
        WalletService.addDeposit(userId, DAILY_LOGIN_REWARD)
        println("✅ Daily reward added → +$DAILY_LOGIN_REWARD coins to Deposit for $userId")
    }

    // 🧑‍🤝‍🧑 Referral System Reward
    fun giveReferralReward(referrerId: String, newUserId: String) {
        WalletService.addDeposit(referrerId, REFERRAL_REWARD)
        println("🎉 Referral reward added → +$REFERRAL_REWARD coins to $referrerId (for referring $newUserId)")
    }

    // 🧩 Task Reward
    fun giveTaskReward(userId: String, taskName: String) {
        WalletService.addDeposit(userId, TASK_REWARD)
        println("🏅 Task '$taskName' completed → +$TASK_REWARD coins to Deposit for $userId")
    }
}
