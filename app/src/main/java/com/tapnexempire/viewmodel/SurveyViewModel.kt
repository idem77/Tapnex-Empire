package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.repository.SurveyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val repo: SurveyRepository
) : ViewModel() {

    private val _leaderboard =
        MutableStateFlow<List<Pair<String, Long>>>(emptyList())
    val leaderboard: StateFlow<List<Pair<String, Long>>> = _leaderboard

    fun startLeaderboard(type: String) {
        repo.listenLeaderboard(type) {
            _leaderboard.value = it
        }
    }

    fun addSurveyScore(userId: String, type: String, points: Long) {
        repo.addScore(userId, type, points)
    }
}
