package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.LeaderboardPlayer
import com.tapnexempire.data.repository.LeaderboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(

    private val repo: LeaderboardRepository

) : ViewModel() {

    fun listenLeaderboard(

        onResult: (List<LeaderboardPlayer>) -> Unit

    ) {

        repo.listenLeaderboard(onResult)
    }
}
